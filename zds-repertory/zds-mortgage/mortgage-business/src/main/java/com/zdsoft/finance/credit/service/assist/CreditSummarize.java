package com.zdsoft.finance.credit.service.assist;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zdsoft.finance.businesssetting.entity.OrgAuthConn;
import com.zdsoft.finance.businesssetting.service.OrgAuthConnService;
import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.finance.credit.entity.CustomerCreditStatistics;
import com.zdsoft.finance.credit.entity.HmBeOverdueInfoOverview;
import com.zdsoft.finance.credit.entity.HmBeOverdueSummaryInfo;
import com.zdsoft.finance.credit.entity.HmCreditCardInfo;
import com.zdsoft.finance.credit.entity.HmCreditPromptInfo;
import com.zdsoft.finance.credit.entity.HmLoanInfo;
import com.zdsoft.finance.credit.entity.HmNoCancelCreditCardSummaryInfo;
import com.zdsoft.finance.credit.entity.HmNotSettledLoanSummaryInfo;
import com.zdsoft.finance.credit.entity.HmPerSummaryGuarantee;
import com.zdsoft.finance.credit.entity.HmQuery;
import com.zdsoft.finance.credit.repository.HmBeOverdueInfoOverviewRepository;
import com.zdsoft.finance.credit.repository.HmBeOverdueSummaryInfoRepository;
import com.zdsoft.finance.credit.repository.HmCreditCardInfoRepository;
import com.zdsoft.finance.credit.repository.HmCreditPromptInfoRepository;
import com.zdsoft.finance.credit.repository.HmLoanInfoRepository;
import com.zdsoft.finance.credit.repository.HmNoCancelCreditCardSummaryInfoRepository;
import com.zdsoft.finance.credit.repository.HmNotSettledLoanSummaryInfoRepository;
import com.zdsoft.finance.credit.repository.HmPerSummaryGuaranteeRepository;
import com.zdsoft.finance.credit.service.CustomerCreditStatisticsService;
import com.zdsoft.finance.customer.dto.CaseRelationCustomerDto;
import com.zdsoft.finance.customer.service.BeforeCustomerService;
import com.zdsoft.finance.marketing.entity.CaseApply;
import com.zdsoft.finance.marketing.service.CaseApplyService;
import com.zdsoft.framework.core.common.annotation.Log;
import com.zdsoft.framework.core.common.util.ObjectHelper;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title: CreditSummarize.java 
 * @ClassName: CreditSummarize 
 * @Description: 征信汇总
 * @author gufeng 
 * @date 2017年2月24日 上午11:00:12 
 * @version V1.0
 */
@Service("creditSummarize")
public class CreditSummarize {

	@Log
	private Logger logger;
	@Autowired
	private CaseApplyService caseApplyService;
	@Autowired
	private OrgAuthConnService orgAuthConnService;
	
	@Autowired
	private BeforeCustomerService beforeCustomerService;
	@Autowired
	private HmLoanInfoRepository hmLoanInfoRepository;
	@Autowired
	private HmCreditPromptInfoRepository hmCreditPromptInfoRepository;
	@Autowired
	private HmNotSettledLoanSummaryInfoRepository hmNotSettledLoanSummaryInfoRepository;
	@Autowired
	private HmNoCancelCreditCardSummaryInfoRepository hmNoCancelCreditCardSummaryInfoRepository;
	@Autowired
	private HmBeOverdueSummaryInfoRepository hmBeOverdueSummaryInfoRepository;
	@Autowired
	private HmCreditCardInfoRepository hmCreditCardInfoRepository;
	@Autowired
	private HmPerSummaryGuaranteeRepository hmPerSummaryGuaranteeRepository;
	@Autowired
	private HmBeOverdueInfoOverviewRepository hmBeOverdueInfoOverviewRepository;
	
	@Autowired
	private CustomerCreditStatisticsService customerCreditStatisticsService;
	
	/**
	 * @Title: creditSummarizeSave 
	 * @Description: 汇总信息保存
	 * @author gufeng 
	 * @param hq 查询对象
	 * @throws BusinessException 查询异常
	 */
	@Transactional(rollbackFor = Exception.class)
	public void creditSummarizeSave(HmQuery hq)throws BusinessException{
		if(ObjectHelper.isEmpty(hq) || ObjectHelper.isEmpty(hq.getId())){
			throw new BusinessException("100000000001","参数值错误");
		}
		String queryId = hq.getId();
		String objectId = hq.getObjectId();
		if(ObjectHelper.isNotEmpty(objectId) && objectId.indexOf("_") != -1){
			objectId = objectId.split("_")[0];
		}
		
		//汇总信息
		CustomerCreditStatistics bean = new CustomerCreditStatistics();
		bean.setCaseApplyId(objectId);
		bean.setSourceFrom(CustomerCreditStatistics.SOURCE_OFFLINE);
		bean.setSourceMarkId(queryId);
		// 客户Id
		//客户姓名
		String customerName = hq.getQueryName();
		String customerId = CreditCommon.getCertificateType(hq.getQueryCertType()) + ":" + hq.getQueryCredNum();
		// 根据案件id查询客户
		List<CaseRelationCustomerDto> relationCust=null;
		try {
			relationCust = beforeCustomerService.findRelationCustomerByCaseApplyId(objectId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (ObjectHelper.isNotEmpty(relationCust)) {
			String type = CreditCommon.getCertificateType(hq.getQueryCertType());
			String number = hq.getQueryCredNum();
			for (CaseRelationCustomerDto dto : relationCust) {
				if (type.equals(dto.getCredentialType()) && number.equals(dto.getCredentialNo())) {
					customerId = dto.getCustomerId();
					customerName = dto.getCustomerName();
					break;
				}
			}
		}
		bean.setCustomerId(customerId);
		bean.setCustomerName(customerName);
		//当前逾期金额
		BigDecimal currentOverdueAmount = BigDecimal.ZERO;
		//获取M
		List<String> mList = new ArrayList<>();
		//贷款总额   =>hm_loan_info表 Principal_Amount 本金余额     合计
		BigDecimal loanTotalAmount = BigDecimal.ZERO;
		//已结清贷款 title
		List<String> titleInfoList = new ArrayList<>();
		List<HmLoanInfo> loanList = hmLoanInfoRepository.findByQueryId(queryId);
		if(ObjectHelper.isNotEmpty(loanList)){
			for (HmLoanInfo hmLoanInfo : loanList) {
				loanTotalAmount = loanTotalAmount.add(CreditCommon.bigDecimalValue(hmLoanInfo.getPrincipalAmount()));
				currentOverdueAmount = currentOverdueAmount.add(CreditCommon.bigDecimalValue(hmLoanInfo.getCurrentOverdueAmount()));
				String s = CreditCommon.stringValue(hmLoanInfo.getMonth24RepayStatus());
				if(ObjectHelper.isNotEmpty(s)){
					mList.add(s);
				}
				if(ObjectHelper.isNotEmpty(hmLoanInfo.getAccountStatus()) && hmLoanInfo.getAccountStatus().equals("结清")){
					titleInfoList.add(CreditCommon.stringValue(hmLoanInfo.getTitleInfo()));
				}
			}
		}
		bean.setLoanTotalAmount(loanTotalAmount);
		//贷款总笔数
		Integer loanTotalNum = 0;
		List<HmCreditPromptInfo> loanTotalList = hmCreditPromptInfoRepository.findByQueryId(queryId);
		if(ObjectHelper.isNotEmpty(loanTotalList)){
			for (HmCreditPromptInfo hmCreditPromptInfo : loanTotalList) {
				loanTotalNum += CreditCommon.integerValue(hmCreditPromptInfo.getHouseLoanNum());
				loanTotalNum += CreditCommon.integerValue(hmCreditPromptInfo.getOtherLoanNum());
				loanTotalNum += CreditCommon.integerValue(hmCreditPromptInfo.getPersonalHouseLoanNum());
			}
		}
		//贷款总笔数 =>hm_credit_prompt_info表 House_Loan_Num 个人商用房（包括商住两用）贷款笔数 加上  Other_Loan_Num 其他贷款笔数 加上 Personal_House_Loan_Num个人住房贷款笔数
		bean.setLoanTotalNum(loanTotalNum);
		//已结清贷款笔数 =>hm_loan_info表  Principal_Amount 本金余额 =0”数据行数
		Integer endLoanTotalNum = hmLoanInfoRepository.countByQueryId(queryId);
		bean.setEndLoanTotalNum(endLoanTotalNum);
		//已结清贷款总额 =>hm_loan_info表 “Principal_Amoun本金余额数据 合计 
		BigDecimal endLoanTotalAmount = this.getEndLoanTotalAmount(titleInfoList);
		bean.setEndLoanTotalAmount(endLoanTotalAmount);
		//未结清笔数
		Integer loaningTotalNum = 0;
		//未结清总额
		BigDecimal loaningTotalAmount = BigDecimal.ZERO;
		List<HmNotSettledLoanSummaryInfo> loaningList = hmNotSettledLoanSummaryInfoRepository.findByQueryId(queryId);
		if(ObjectHelper.isNotEmpty(loaningList)){
			for (HmNotSettledLoanSummaryInfo hmNotSettledLoanSummaryInfo : loaningList) {
				loaningTotalNum += CreditCommon.integerValue(hmNotSettledLoanSummaryInfo.getCountNum());
				loaningTotalAmount = loaningTotalAmount.add(CreditCommon.bigDecimalValue(hmNotSettledLoanSummaryInfo.getContractProfits()));
			}
		}
		//未结清笔数 => hm_not_settled_loan_summaryinf 表 Count_Num
		bean.setLoaningTotalNum(loaningTotalNum);
		//未结清总额 =>hm_not_settled_loan_summaryinf 表 Contract_Profits
		bean.setLoaningTotalAmount(loaningTotalAmount);
		
		
		//信用卡发卡总额
		BigDecimal creditApplyTotalAmount = BigDecimal.ZERO;
		//发放张数
		Integer creditApplyNum = 0;
		//已使用额度 
		BigDecimal creditUsedAmount = BigDecimal.ZERO;
		//信用卡使用率
		Double creditUsedRate = 0d;	
		List<HmNoCancelCreditCardSummaryInfo> applyList = hmNoCancelCreditCardSummaryInfoRepository.findByQueryId(queryId);
		if(ObjectHelper.isNotEmpty(applyList)){
			for (HmNoCancelCreditCardSummaryInfo hmNoCancelCreditCardSummaryInfo : applyList) {
				creditApplyTotalAmount = creditApplyTotalAmount.add(CreditCommon.bigDecimalValue(hmNoCancelCreditCardSummaryInfo.getFinanceProfits()));
				creditApplyNum += CreditCommon.integerValue(hmNoCancelCreditCardSummaryInfo.getAccountNum());
				creditUsedAmount = creditUsedAmount.add(hmNoCancelCreditCardSummaryInfo.getUsedCreditLimit());
				creditUsedRate += CreditCommon.bigDecimalValue(hmNoCancelCreditCardSummaryInfo.getLast6MonthsAvgUseLimit()).doubleValue();
			}
		}
		//信用卡发卡总额 =>hm_no_cancel_credit_card_summary_info表 Finance_Profits
		bean.setCreditApplyTotalAmount(creditApplyTotalAmount);
		//发放张数 =>hm_no_cancel_credit_card_summary_info表 Account_Num
		bean.setCreditApplyNum(creditApplyNum);
		//已使用额度 =>  hm_no_cancel_credit_card_summary_info表    Used_Credit_Limit
		bean.setCreditUsedAmount(creditUsedAmount);
		//信用卡使用率 => hm_no_cancel_credit_card_summary_info表  Last6_Months_Avg_Use_Limit
		bean.setCreditUsedRate(creditUsedRate);
		
		
		//贷款逾期：逾期总笔数 
		Integer loanOverdueNum = 0;
		//贷款逾期：单笔最高逾期期数
		Integer loanMaxOverduePeriods = 0;
		//贷款逾期：单笔最高逾期金额 
		BigDecimal loanMaxOverAmount = BigDecimal.ZERO;
		//贷记卡逾期：逾期总笔数
		Integer cardOverdueNum = 0;
		//贷记卡逾期：单笔最高逾期期数
		Integer cardMaxOverPeriods = 0;
		//贷记卡逾期：单笔最高逾期金额
		BigDecimal cardMaxOverAmount = BigDecimal.ZERO;
		List<HmBeOverdueSummaryInfo> overdueList = hmBeOverdueSummaryInfoRepository.findByQueryId(queryId);
		if(ObjectHelper.isNotEmpty(overdueList)){
			for (HmBeOverdueSummaryInfo hmBeOverdueSummaryInfo : overdueList) {
				loanOverdueNum += CreditCommon.integerValue(hmBeOverdueSummaryInfo.getLoanOverdueAccountNum());
				loanMaxOverduePeriods += CreditCommon.integerValue(hmBeOverdueSummaryInfo.getLoanOverdueLongOverdueMonths());
				loanMaxOverAmount = loanMaxOverAmount.add(CreditCommon.bigDecimalValue(hmBeOverdueSummaryInfo.getLoanOverdueHighestSingleMothOverdueAmount()));
				cardOverdueNum += CreditCommon.integerValue(hmBeOverdueSummaryInfo.getCreditCardOverdueAccountNum());
				cardMaxOverPeriods += CreditCommon.integerValue(hmBeOverdueSummaryInfo.getCreditCardOverdraftLongOverdueMonths());
				cardMaxOverAmount = cardMaxOverAmount.add(hmBeOverdueSummaryInfo.getCreditCardOverdueMaxSingleMothOverdueAmount());
			}
		}
		//贷款逾期：逾期总笔数 => hm_be_over_due_summary_info 表 Loan_Over_due_Account_Num 字段
		bean.setLoanOverdueNum(loanOverdueNum);
		//贷款逾期：未逾期笔数 =>总的贷款笔数-逾期总笔数
		Integer loanNotOverNum = loanTotalNum - loanOverdueNum;
		bean.setLoanNotOverNum(loanNotOverNum);
		//贷款逾期：超标笔数 =>方法里
		Integer loanOverMarkNum = this.getMarkNum(mList, currentOverdueAmount, objectId);
		bean.setLoanOverMarkNum(loanOverMarkNum);
		//贷款逾期：单笔最高逾期期数 =>hm_be_over_due_summary_info 表 Loan_Over_due_Long_Over_due_Months
		bean.setLoanMaxOverduePeriods(loanMaxOverduePeriods);
		//贷款逾期：单笔最高逾期金额 =>hm_be_over_due_summary_info 表 Loan_Over_due_Highest_Single_Moth_Over_due_Amount
		bean.setLoanMaxOverAmount(loanMaxOverAmount);
		
		
		//贷记卡逾期：逾期总笔数 =>hm_be_over_due_summary_info 表 Credit_Card_Over_due_Account_Num
		bean.setCardOverdueNum(cardOverdueNum);
		//贷记卡逾期：未逾期笔数 = >发放张数 - 逾期总笔数
		Integer cardNotOverNum = creditApplyNum - cardOverdueNum;
		bean.setCardNotOverNum(cardNotOverNum);
		
		List<String> cList = new ArrayList<>();
		BigDecimal cardAmount = BigDecimal.ZERO;
		List<HmCreditCardInfo> cardList = hmCreditCardInfoRepository.findByQueryId(queryId);
		if(ObjectHelper.isNotEmpty(cardList)){
			for (HmCreditCardInfo hmCreditCardInfo : cardList) {
				String s = CreditCommon.stringValue(hmCreditCardInfo.getMonth24RepayStatus());
				if(ObjectHelper.isNotEmpty(s)){
					cList.add(s);
				}
				cardAmount = cardAmount.add(CreditCommon.bigDecimalValue(hmCreditCardInfo.getCurrentOverdueAmount()));
			}
		}
		//贷记卡逾期：超标笔数
		Integer cardOverMarkNum = this.getMarkNum(cList, cardAmount, objectId);
		bean.setCardOverMarkNum(cardOverMarkNum);
		//贷记卡逾期：单笔最高逾期期数 =>hm_be_over_due_summary_info 表 Credit_Card_Over_draft_Long_Over_due_Months
		bean.setCardMaxOverPeriods(cardMaxOverPeriods);
		//贷记卡逾期：单笔最高逾期金额 =>hm_be_over_due_summary_info 表 Credit_Card_Over_due_Max_Single_Moth_Over_due_Amount
		bean.setCardMaxOverAmount(cardMaxOverAmount);

		//对外担保笔数
		Integer externalGuaranteeNum = 0;
		//对外担保金额 
		BigDecimal externalGuaranteeAmount = BigDecimal.ZERO;
		List<HmPerSummaryGuarantee> exList = hmPerSummaryGuaranteeRepository.findByQueryId(queryId);
		if(ObjectHelper.isNotEmpty(exList)){
			for (HmPerSummaryGuarantee hmPerSummaryGuarantee : exList) {
				externalGuaranteeNum += CreditCommon.integerValue(hmPerSummaryGuarantee.getPerGuarMount());
				externalGuaranteeAmount = externalGuaranteeAmount.add(hmPerSummaryGuarantee.getPerGuarAmount());
			}
		}
		//对外担保笔数 =>hm_per_summary_guarantee表Per_Guar_Mount担保笔数
		bean.setExternalGuaranteeNum(externalGuaranteeNum);
		//对外担保金额 =>hm_per_summary_guarantee表Per_Guar_Amount 担保金额
		bean.setExternalGuaranteeAmount(externalGuaranteeAmount);
		//一年累计逾期  一年内最高逾期期数
		List<String> mcList = new ArrayList<>();
		mcList.addAll(cList);
		mcList.addAll(mList);
		Map<String,Integer> mcMap = this.getOver(mcList);
		//一年累计逾期 =>见方法 getOver
		Integer yearOverNum = mcMap.get("mNum");
		bean.setYearOverNum(yearOverNum);
		//一年内最高逾期期数 =>见方法 getOver
		Integer yearMaxOverNum = mcMap.get("max");
		bean.setYearMaxOverNum(yearMaxOverNum);
		//呆账超标
		Integer excessiveBadNum = 0;
		List<HmBeOverdueInfoOverview> overList = hmBeOverdueInfoOverviewRepository.findByQueryId(queryId);
		if(ObjectHelper.isNotEmpty(overList)){
			for (HmBeOverdueInfoOverview hmBeOverdueInfoOverview : overList) {
				excessiveBadNum += CreditCommon.integerValue(hmBeOverdueInfoOverview.getBadDebtsInfoSumNumber());
			}
		}
		//呆账超标 =>"hm_be_over_due_info_over_view表bad_Debts_Info_Sum_Number（呆账信息汇总笔数）
		bean.setExcessiveBadNum(excessiveBadNum);
		//冻结超标
		Integer excessiveFreezeNum = 0;
		List<String> status = new ArrayList<>();
		status.add("D");
		status.add("冻结");
		List<HmCreditCardInfo> accountList = hmCreditCardInfoRepository.findByQueryIdAndAccountStatusIn(queryId,status);
		if(ObjectHelper.isNotEmpty(accountList)){
			excessiveFreezeNum = accountList.size();
		}
		//冻结超标 =>hm_credit_card_info表Account_Status( 账号状态) 其中为D或者为冻结的值
		bean.setExcessiveFreezeNum(excessiveFreezeNum);
		try {
			customerCreditStatisticsService.saveOrUpdateStatistic(bean);
		} catch (Exception e) {
			e.printStackTrace();
			throw new BusinessException("统计数据保存出错",e.getMessage());
		}
	}
	
	/**
	 * @Title: getOver 
	 * @Description: 特批累计
	 * @author gufeng 
	 * @param mcList 数据
	 * @return 特批统计
	 */
	private Map<String,Integer> getOver(List<String> mcList){
		/*hm_loan_info表Month_24Repay_Status（账户）还款前面的信息 等待接口数据分析这个字段
		hm_credit_card_info表Month_24Repay_Status
		前12个值的状态 没真实数据无法分析*/
		/*hm_loan_info表Month_24Repay_Status（账户）还款前面的信息 等待接口数据分析这个字段
		hm_credit_card_info表Month_24Repay_Status
		前12个值的状态 没真实数据无法分析*/
		if(ObjectHelper.isEmpty(mcList)){
			return null;
		}
		int mNum = 0;
		int max = 0;
		for (String mc : mcList) {
			int index = 0;
			String[] s = mc.split(" ");
			for (int i = s.length - 1; i >= 0; i--) {
				if(index < 12){
					try{
						int Mx = Integer.parseInt(s[i]); 
						if(Mx > 0){
							mNum ++;
						}
						if(max < Mx){
							max = Mx;
						}
						index ++;
					}catch(Exception e){
						logger.info("1000002","不是数字");
					}
				}
			}
		}
		Map<String,Integer> map = new HashMap<>();
		map.put("mNum", mNum);
		map.put("max", max);
		return map;
	}
	
	/**
	 * 
	 * @Title: getMarkNum 
	 * @Description: 逾期笔数
	 * @author gufeng 
	 * @param mList 判定格式
	 * @param currentOverdueAmount 逾期金额
	 * @param objectId 案件id
	 * @return 逾期笔数
	 */
	private Integer getMarkNum(List<String> mList,BigDecimal currentOverdueAmount,String objectId){
		/*近12个月内，M3及以上≥0且当前逾期金额＞1000；
		      近12个月内，A或A+级机构，M3及以上≤0且1≤M2≤12且M1≥9；
		      近12个月内，B或C或D级机构，M3及以上≤0且5≤M2≤12；
		      近12个月内，B或C或D级机构，M3及以上≤0且1≤M2≤4且M1≥9；
		注：M1代表逾期30天，M2代表逾期60天，M3代表逾期90天，以此类推。
		M3可以从 表单中“最近12个月还款状态记录”的最高逾期期数获取
		机构等级在业务设置“授权额度管理-机构评级”中获取
		当前逾期金额 “表单中直接取”
		任一命中即为超标，并展示命中规则*/
		int total = 0;
		int m3num = 0;
		int m2num = 0;
		int m1num = 0;
		//评级
		String authGradeName = this.getAuthGradeName(objectId);
		for (String m : mList) {
			//规则1 近12个月内，M3及以上≥0且当前逾期金额＞1000；
			int index = 0;
			String[] s = m.split(" ");
			for (int i = s.length - 1; i >= 0 ; i--) {
				if(index < 12){
					try{
						int Mx = Integer.parseInt(s[i]); 
						if(Mx >= 3){
							m3num ++;
						}
						if(Mx >= 2){
							m2num ++;
						}
						if(Mx >= 1){
							m1num ++;
						}
						index ++;
					}catch(Exception e){
						logger.info("1000002","不是数字");
					}
				}
			}
			if(m3num >= 0 && currentOverdueAmount.compareTo(new BigDecimal("1000")) == 1){
				total ++;
			}
			//规则2  近12个月内，A或A+级机构，M3及以上≤0且1≤M2≤12且M1≥9；
			if(ObjectHelper.isEmpty(authGradeName)){
				continue;
			}
			if(authGradeName.equals("A") || authGradeName.equals("A+")){
				if(m3num <= 0 && m2num >= 1 && m2num <= 12 && m1num >= 9){
					total ++;
				}
			}
			//规则3  近12个月内，B或C或D级机构，M3及以上≤0且5≤M2≤12；
			if(authGradeName.equals("A") || authGradeName.equals("A+") || authGradeName.equals("D")){
				if(m3num <= 0 && m2num >= 5 && m2num <= 12){
					total ++;
				}
			}
			//规则4  近12个月内，B或C或D级机构，M3及以上≤0且1≤M2≤4且M1≥9；
			if(authGradeName.equals("A") || authGradeName.equals("A+") || authGradeName.equals("D")){
				if(m3num <= 0 && m2num >= 1 && m2num <= 4 && m1num >= 9){
					total ++;
				}
			}
		}
		return total;
	}
	/**
	 * 
	 * @Title: getAuthGradeName 
	 * @Description: 获取机构评级
	 * @author gufeng 
	 * @param objectId 业务id
	 * @return 评级字符串
	 */
	@SuppressWarnings("null")
	private String getAuthGradeName(String objectId){
		String s = null;
		CaseApply ca = null;
		try {
			 ca = caseApplyService.findOne(objectId);
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		if(ObjectHelper.isEmpty(ca) || ObjectHelper.isEmpty(ca.getMechanismCode())){
			return s;
		}
		OrgAuthConn oac = null;
		try {
			orgAuthConnService.findByOrgCode(ca.getMechanismCode());
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(ObjectHelper.isEmpty(oac) || ObjectHelper.isEmpty(oac.getAuthGradeName())){
			return s;
		}
		return oac.getAuthGradeName();
	}
	
	/**
	 * @Title: getEndLoanTotalAmount 
	 * @Description: 取出金额
	 * @author gufeng 
	 * @param titleInfoList 金额List数据
	 * @return 金额值
	 */
	private BigDecimal getEndLoanTotalAmount(List<String> titleInfoList){
		BigDecimal total = BigDecimal.ZERO;
		for (String str : titleInfoList) {
			BigDecimal bd = this.getPattern(str);
			if(ObjectHelper.isNotEmpty(bd)){
				total = total.add(bd);
			}
		}
		return total;
	}
	/**
	 * @Title: getPattern 
	 * @Description: 获取金额
	 * @author gufeng 
	 * @param s 数据
	 * @return 金额值
	 */
	private BigDecimal getPattern(String s){
		BigDecimal bd = null;
		String regex = "\\d{1,3}(,?\\d{3})*(\\.\\d{1,2})?元";
		if(ObjectHelper.isEmpty(s)){
			return null;
		}
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(regex);
		while (m.find()) { 
			String value = m.group(0);
			if(ObjectHelper.isNotEmpty(value)){
				bd = CreditCommon.bigDecimalValue(value.substring(0,value.length() - 1));
			}
		}
		return bd;
	}
	
	
}
