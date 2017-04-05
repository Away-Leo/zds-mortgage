package com.zdsoft.finance.credit.service.assist;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.finance.credit.entity.HmAssetsDisposalInfo;
import com.zdsoft.finance.credit.entity.HmBeOverdueInfoOverview;
import com.zdsoft.finance.credit.entity.HmBeOverdueSummaryInfo;
import com.zdsoft.finance.credit.entity.HmCompulsoryExecutionRecord;
import com.zdsoft.finance.credit.entity.HmCreditCardInfo;
import com.zdsoft.finance.credit.entity.HmCreditLoanApproQueryDetailed;
import com.zdsoft.finance.credit.entity.HmCreditPromptInfo;
import com.zdsoft.finance.credit.entity.HmGuarantorCompenInfo;
import com.zdsoft.finance.credit.entity.HmHousingReservePayRecord;
import com.zdsoft.finance.credit.entity.HmIdentityInfo;
import com.zdsoft.finance.credit.entity.HmLoanInfo;
import com.zdsoft.finance.credit.entity.HmNoCAQuasiCreditCardInfoSum;
import com.zdsoft.finance.credit.entity.HmNoCancelCreditCardSummaryInfo;
import com.zdsoft.finance.credit.entity.HmNotSettledLoanSummaryInfo;
import com.zdsoft.finance.credit.entity.HmOccupationInfo;
import com.zdsoft.finance.credit.entity.HmOverdue;
import com.zdsoft.finance.credit.entity.HmPensionInsurancePayRecord;
import com.zdsoft.finance.credit.entity.HmPerGuaranteeInfo;
import com.zdsoft.finance.credit.entity.HmPerSummaryGuarantee;
import com.zdsoft.finance.credit.entity.HmQuasiCreditCard;
import com.zdsoft.finance.credit.entity.HmQueryRecordSum;
import com.zdsoft.finance.credit.entity.HmReportBasics;
import com.zdsoft.finance.credit.entity.HmReportExplain;
import com.zdsoft.finance.credit.entity.HmResideInfo;
import com.zdsoft.finance.credit.entity.HmSpecTra;
import com.zdsoft.finance.credit.entity.HmSpouseInfo;
import com.zdsoft.finance.credit.repository.HmAssetsDisposalInfoRepository;
import com.zdsoft.finance.credit.repository.HmBeOverdueInfoOverviewRepository;
import com.zdsoft.finance.credit.repository.HmBeOverdueSummaryInfoRepository;
import com.zdsoft.finance.credit.repository.HmCompulsoryExecutionRecordRepository;
import com.zdsoft.finance.credit.repository.HmCreditCardInfoRepository;
import com.zdsoft.finance.credit.repository.HmCreditLoanApproQueryDetailedRepository;
import com.zdsoft.finance.credit.repository.HmCreditPromptInfoRepository;
import com.zdsoft.finance.credit.repository.HmGuarantorCompenInfoRepository;
import com.zdsoft.finance.credit.repository.HmHousingReservePayRecordRepository;
import com.zdsoft.finance.credit.repository.HmIdentityInfoRepository;
import com.zdsoft.finance.credit.repository.HmLoanInfoRepository;
import com.zdsoft.finance.credit.repository.HmNoCAQuasiCreditCardInfoSumRepository;
import com.zdsoft.finance.credit.repository.HmNoCancelCreditCardSummaryInfoRepository;
import com.zdsoft.finance.credit.repository.HmNotSettledLoanSummaryInfoRepository;
import com.zdsoft.finance.credit.repository.HmOccupationInfoRepository;
import com.zdsoft.finance.credit.repository.HmOverdueRepository;
import com.zdsoft.finance.credit.repository.HmPensionInsurancePayRecordRepository;
import com.zdsoft.finance.credit.repository.HmPerGuaranteeInfoRepository;
import com.zdsoft.finance.credit.repository.HmPerSummaryGuaranteeRepository;
import com.zdsoft.finance.credit.repository.HmQuasiCreditCardRepository;
import com.zdsoft.finance.credit.repository.HmQueryRecordSumRepository;
import com.zdsoft.finance.credit.repository.HmReportBasicsRepository;
import com.zdsoft.finance.credit.repository.HmReportExplainRepository;
import com.zdsoft.finance.credit.repository.HmResideInfoRepository;
import com.zdsoft.finance.credit.repository.HmSpecTraRepository;
import com.zdsoft.finance.credit.repository.HmSpouseInfoRepository;
import com.zdsoft.framework.core.common.annotation.Log;
import com.zdsoft.framework.core.common.util.ObjectHelper;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title: CreditSaveService.java 
 * @ClassName: CreditSaveService 
 * @Description: 征信保存
 * @author gufeng 
 * @date 2017年2月22日 上午11:37:00 
 * @version V1.0
 */
@Service("creditSaveService")
public class CreditSaveService {

	@Log
	private Logger logger;
	
	@Autowired
	private HmCreditPromptInfoRepository hmCreditPromptInfoRepository;
	@Autowired
	private HmIdentityInfoRepository hmIdentityInfoRepository;
	@Autowired
	private HmNoCancelCreditCardSummaryInfoRepository hmNoCancelCreditCardSummaryInfoRepository;
	@Autowired
	private HmPerSummaryGuaranteeRepository hmPerSummaryGuaranteeRepository;
	@Autowired
	private HmQueryRecordSumRepository hmQueryRecordSumRepository;
	@Autowired
	private HmReportBasicsRepository hmReportBasicsRepository;
	@Autowired
	private HmReportExplainRepository hmReportExplainRepository;
	@Autowired
	private HmSpouseInfoRepository hmSpouseInfoRepository;
	@Autowired
	private HmBeOverdueSummaryInfoRepository hmBeOverdueSummaryInfoRepository;
	@Autowired
	private HmBeOverdueInfoOverviewRepository hmBeOverdueInfoOverviewRepository;
	@Autowired
	private HmNoCAQuasiCreditCardInfoSumRepository hmNoCAQuasiCreditCardInfoSumRepository;
	@Autowired
	private HmNotSettledLoanSummaryInfoRepository hmNotSettledLoanSummaryInfoRepository;
	@Autowired
	private HmAssetsDisposalInfoRepository hmAssetsDisposalInfoRepository;
	@Autowired
	private HmCompulsoryExecutionRecordRepository hmCompulsoryExecutionRecordRepository;
	@Autowired
	private HmCreditLoanApproQueryDetailedRepository hmCreditLoanApproQueryDetailedRepository;
	@Autowired
	private HmGuarantorCompenInfoRepository hmGuarantorCompenInfoRepository;
	@Autowired
	private HmHousingReservePayRecordRepository hmHousingReservePayRecordRepository;
	@Autowired
	private HmLoanInfoRepository hmLoanInfoRepository;
	@Autowired
	private HmOccupationInfoRepository hmOccupationInfoRepository;
	@Autowired
	private HmPensionInsurancePayRecordRepository hmPensionInsurancePayRecordRepository;
	@Autowired
	private HmPerGuaranteeInfoRepository hmPerGuaranteeInfoRepository;
	@Autowired
	private HmQuasiCreditCardRepository hmQuasiCreditCardRepository;
	@Autowired
	private HmResideInfoRepository hmResideInfoRepository;
	@Autowired
	private HmSpecTraRepository hmSpecTraRepository;
	@Autowired
	private HmCreditCardInfoRepository hmCreditCardInfoRepository;
	@Autowired
	private HmOverdueRepository hmOverdueRepository;
	
	/**
	 * @Title: saveBean 
	 * @Description: 保存数据
	 * @author gufeng 
	 * @param name 对象名字
	 * @param data 数据
	 * @param queryId 查询id
	 * @param idCard 证件号
	 * @return 是否保存成功
	 * @throws BusinessException 保存异常
	 */
	@Transactional(rollbackFor = Exception.class)
	public boolean saveBean(String name, Map<String, Object> data,String queryId, String idCard)throws BusinessException {
		logger.info("=====保存BEAN:" + name + "==>data:" + data + "==>queryId:" + queryId + "==>idCard:" + idCard);
		if(ObjectHelper.isEmpty(name) || ObjectHelper.isEmpty(queryId)){
			throw new BusinessException("10000000001","不满足保存条件，BEAN:" + name + "==>queryId:" + queryId);
		}
		if(ObjectHelper.isEmpty(data)){
			return true;
		}
		boolean flag = false;
		switch (name) {
		case "HmCreditPromptInfo":
			HmCreditPromptInfo hmCreditPromptInfo = new HmCreditPromptInfo();
			CreditCommon.populate(hmCreditPromptInfo, data);
			hmCreditPromptInfo.setQueryId(queryId);
			hmCreditPromptInfo.setIdCard(idCard);
			flag = true;
			logger.info("=====1==>HmCreditPromptInfo");
			hmCreditPromptInfoRepository.saveEntity(hmCreditPromptInfo);
			break;
		case "HmIdentityInfo":
			HmIdentityInfo hmIdentityInfo = new HmIdentityInfo();
			CreditCommon.populate(hmIdentityInfo, data);
			hmIdentityInfo.setQueryId(queryId);
			hmIdentityInfo.setIdCard(idCard);
			flag = true;
			logger.info("=====2==>HmIdentityInfo");
			hmIdentityInfoRepository.saveEntity(hmIdentityInfo);
			break;
		case "HmNoCancelCreditCardSummaryInfo":
			HmNoCancelCreditCardSummaryInfo hmNoCancelCreditCardSummaryInfo = new HmNoCancelCreditCardSummaryInfo();
			hmNoCancelCreditCardSummaryInfo.setHairpinLegalOrgNum(CreditCommon.integerValue(data.get("hairpinLegalOrgNum")));
			hmNoCancelCreditCardSummaryInfo.setHairpinOrgNum(CreditCommon.integerValue(data.get("hairpinOrgNum")));
			hmNoCancelCreditCardSummaryInfo.setAccountNum(CreditCommon.integerValue(data.get("accountNum")));
			hmNoCancelCreditCardSummaryInfo.setFinanceProfits(CreditCommon.bigDecimalValue(data.get("financeProfits")));
			hmNoCancelCreditCardSummaryInfo.setSingleBankMaxFinanceLimit(CreditCommon.bigDecimalValue(data.get("singleBankMaxFinanceLimit")));
			hmNoCancelCreditCardSummaryInfo.setSingleBankMinFinanceLimit(CreditCommon.bigDecimalValue(data.get("singleBankMinFinanceLimit")));
			hmNoCancelCreditCardSummaryInfo.setUsedCreditLimit(CreditCommon.bigDecimalValue(data.get("usedCreditLimit")));
			hmNoCancelCreditCardSummaryInfo.setLast6MonthsAvgUseLimit(CreditCommon.bigDecimalValue(data.get("last6MonthsAvgUseLimit")));
			hmNoCancelCreditCardSummaryInfo.setQueryId(queryId);
			hmNoCancelCreditCardSummaryInfo.setIdCard(idCard);
			flag = true;
			logger.info("=====3==>HmNoCancelCreditCardSummaryInfo");
			hmNoCancelCreditCardSummaryInfoRepository.saveEntity(hmNoCancelCreditCardSummaryInfo);
			break;
		case "HmPerSummaryGuarantee":
			HmPerSummaryGuarantee hmPerSummaryGuarantee = new HmPerSummaryGuarantee();
			hmPerSummaryGuarantee.setPerGuarMount(CreditCommon.integerValue(data.get("perGuarMount")));
			hmPerSummaryGuarantee.setPerGuarAmount(CreditCommon.bigDecimalValue(data.get("perGuarAmount")));
			hmPerSummaryGuarantee.setPerGuarPrincipalAmount(CreditCommon.bigDecimalValue(data.get("perGuarPrincipalAmount")));
			hmPerSummaryGuarantee.setQueryId(queryId);
			hmPerSummaryGuarantee.setIdCard(idCard);
			flag = true;
			logger.info("=====4==>HmPerSummaryGuarantee");
			hmPerSummaryGuaranteeRepository.saveEntity(hmPerSummaryGuarantee);
			break;
		case "HmQueryRecordSum":
			HmQueryRecordSum hmQueryRecordSum = new HmQueryRecordSum();
			hmQueryRecordSum.setLast1MonthsOrgLoanApprovalSum(CreditCommon.integerValue(data.get("last1MonthsOrgLoanApprovalSum")));
			hmQueryRecordSum.setLast1MonthsOrgCreditCardApprovalSum(CreditCommon.integerValue(data.get("last1MonthsOrgCreditCardApprovalSum")));
			hmQueryRecordSum.setLast1MonthsLoanApprovalSum(CreditCommon.integerValue(data.get("last1MonthsLoanApprovalSum")));
			hmQueryRecordSum.setLast1MonthsCreditCardApprovalSum(CreditCommon.integerValue(data.get("last1MonthsCreditCardApprovalSum")));
			hmQueryRecordSum.setLast1MonthsSelfQuerySum(CreditCommon.integerValue(data.get("last1MonthsSelfQuerySum")));
			hmQueryRecordSum.setLast2YearsLoanMangeSum(CreditCommon.integerValue(data.get("last2YearsLoanMangeSum")));
			hmQueryRecordSum.setLast2YearsGuarApproSum(CreditCommon.integerValue(data.get("last2YearsGuarApproSum")));
			hmQueryRecordSum.setLast2YearsSpeMerchApproSum(CreditCommon.integerValue(data.get("last2YearsSpeMerchApproSum")));
			hmQueryRecordSum.setQueryId(queryId);
			hmQueryRecordSum.setIdCard(idCard);
			flag = true;
			logger.info("=====5==>HmQueryRecordSum");
			hmQueryRecordSumRepository.saveEntity(hmQueryRecordSum);
			break;
		case "HmReportBasics":
			HmReportBasics hmReportBasics = new HmReportBasics();
			CreditCommon.populate(hmReportBasics, data);
			hmReportBasics.setQueryId(queryId);
			hmReportBasics.setIdCard(idCard);
			flag = true;
			logger.info("=====6==>HmReportBasics");
			hmReportBasicsRepository.saveEntity(hmReportBasics);
			break;
		case "HmSpouseInfo":
			HmSpouseInfo hmSpouseInfo = new HmSpouseInfo();
			CreditCommon.populate(hmSpouseInfo, data);
			hmSpouseInfo.setQueryId(queryId);
			hmSpouseInfo.setIdCard(idCard);
			flag = true;
			logger.info("=====7==>HmSpouseInfo");
			hmSpouseInfoRepository.saveEntity(hmSpouseInfo);
			break;
		case "HmNotSettledLoanSummaryInfo":
			HmNotSettledLoanSummaryInfo hmNotSettledLoanSummaryInfo = new HmNotSettledLoanSummaryInfo();
			hmNotSettledLoanSummaryInfo.setQueryId(queryId);
			hmNotSettledLoanSummaryInfo.setIdCard(idCard);
			hmNotSettledLoanSummaryInfo.setLoanLegalOrgNum(CreditCommon.integerValue(data.get("loanLegalOrgNum")));
			hmNotSettledLoanSummaryInfo.setLoanOrgNum(CreditCommon.integerValue(data.get("loanOrgNum")));
			hmNotSettledLoanSummaryInfo.setCountNum(CreditCommon.integerValue(data.get("countNum")));
			hmNotSettledLoanSummaryInfo.setContractProfits(CreditCommon.bigDecimalValue(data.get("contractProfits")));
			hmNotSettledLoanSummaryInfo.setLast6MothsAvgRepayAmount(CreditCommon.bigDecimalValue(data.get("last6MothsAvgRepayAmount")));
			hmNotSettledLoanSummaryInfo.setBalance(CreditCommon.bigDecimalValue(data.get("balance")));
			flag = true;
			logger.info("=====8==>HmNotSettledLoanSummaryInfo");
			hmNotSettledLoanSummaryInfoRepository.saveEntity(hmNotSettledLoanSummaryInfo);
			break;
		case "HmBeOverdueSummaryInfo":
			HmBeOverdueSummaryInfo hmBeOverdueSummaryInfo = new HmBeOverdueSummaryInfo();
			hmBeOverdueSummaryInfo.setLoanOverdueAccountNum(CreditCommon.integerValue(data.get("loanOverdueAccountNum")));
			hmBeOverdueSummaryInfo.setLoanOverdueMonthNum(CreditCommon.integerValue(data.get("loanOverdueMonthNum")));
			hmBeOverdueSummaryInfo.setLoanOverdueHighestSingleMothOverdueAmount(CreditCommon.bigDecimalValue(data.get("loanOverdueHighestSingleMothOverdueAmount")));
			hmBeOverdueSummaryInfo.setLoanOverdueLongOverdueMonths(CreditCommon.integerValue(data.get("loanOverdueLongOverdueMonths")));
			hmBeOverdueSummaryInfo.setCreditCardOverdueAccountNum(CreditCommon.integerValue(data.get("creditCardOverdueAccountNum")));
			hmBeOverdueSummaryInfo.setCreditCardOverdueMothNum(CreditCommon.integerValue(data.get("creditCardOverdueMothNum")));
			hmBeOverdueSummaryInfo.setCreditCardOverdueMaxSingleMothOverdueAmount(CreditCommon.bigDecimalValue(data.get("creditCardOverdueMaxSingleMothOverdueAmount")));
			hmBeOverdueSummaryInfo.setCreditCardOverdraftLongOverdueMonths(CreditCommon.integerValue(data.get("creditCardOverdraftLongOverdueMonths")));
			hmBeOverdueSummaryInfo.setAssetsDisposalInfoSumAmount(CreditCommon.bigDecimalValue(data.get("assetsDisposalInfoSumAmount")));
			hmBeOverdueSummaryInfo.setQuasiCreditCardOverdraftAccountNum(CreditCommon.integerValue(data.get("quasiCreditCardOverdraftAccountNum")));
			hmBeOverdueSummaryInfo.setQuasiCreditCardOverdraft0DaysMothNum(CreditCommon.integerValue(data.get("quasiCreditCardOverdraft0DaysMothNum")));
			hmBeOverdueSummaryInfo.setQuasiCreditCardMaxSingleMothOverdueAmount(CreditCommon.bigDecimalValue(data.get("quasiCreditCardMaxSingleMothOverdueAmount")));
			hmBeOverdueSummaryInfo.setQuasiCreditCardOverdueLongOverdueMonths(CreditCommon.integerValue(data.get("quasiCreditCardOverdueLongOverdueMonths")));
			hmBeOverdueSummaryInfo.setQueryId(queryId);
			hmBeOverdueSummaryInfo.setIdCard(idCard);
			hmBeOverdueSummaryInfoRepository.saveEntity(hmBeOverdueSummaryInfo);
			flag = true;
			logger.info("=====9==>HmBeOverdueSummaryInfo");
			break;
		case "HmBeOverdueInfoOverview":
			HmBeOverdueInfoOverview hmBeOverdueInfoOverview = new HmBeOverdueInfoOverview();
			hmBeOverdueInfoOverview.setBadDebtsInfoSumNumber(CreditCommon.integerValue(data.get("badDebtsInfoSumNumber")));
			hmBeOverdueInfoOverview.setBadDebtsInfoSumAmount(CreditCommon.bigDecimalValue(data.get("badDebtsInfoSumAmount")));
			hmBeOverdueInfoOverview.setAssetsDisposalInfoSumNumber(CreditCommon.integerValue(data.get("assetsDisposalInfoSumNumber")));
			hmBeOverdueInfoOverview.setAssetsDisposalInfoSumAmount(CreditCommon.bigDecimalValue(data.get("assetsDisposalInfoSumAmount")));
			hmBeOverdueInfoOverview.setGuarantorCompenInfoSumNumber(CreditCommon.integerValue(data.get("guarantorCompenInfoSumNumber")));
			hmBeOverdueInfoOverview.setGuarantorCompenInfoSumAmount(CreditCommon.bigDecimalValue(data.get("guarantorCompenInfoSumAmount")));
			hmBeOverdueInfoOverview.setQueryId(queryId);
			hmBeOverdueInfoOverview.setIdCard(idCard);
			hmBeOverdueInfoOverviewRepository.saveEntity(hmBeOverdueInfoOverview);
			flag = true;
			logger.info("=====10==>HmBeOverdueInfoOverview");
			break;
		case "HmNoCAQuasiCreditCardInfoSum":
			HmNoCAQuasiCreditCardInfoSum hmNoCAQuasiCreditCardInfoSum = new HmNoCAQuasiCreditCardInfoSum();
			hmNoCAQuasiCreditCardInfoSum.setSendCardLegalPersonOrgNum(CreditCommon.integerValue(data.get("sendCardLegalPersonOrgNum")));
			hmNoCAQuasiCreditCardInfoSum.setSendCardOrgNum(CreditCommon.integerValue(data.get("sendCardOrgNum")));
			hmNoCAQuasiCreditCardInfoSum.setAccountNumber(CreditCommon.integerValue(data.get("accountNumber")));
			hmNoCAQuasiCreditCardInfoSum.setCreditTotalAmount(CreditCommon.bigDecimalValue(data.get("creditTotalAmount")));
			hmNoCAQuasiCreditCardInfoSum.setAloneBankHighestCreditAmount(CreditCommon.bigDecimalValue(data.get("aloneBankHighestCreditAmount")));
			hmNoCAQuasiCreditCardInfoSum.setAloneBankLowestCreditAmount(CreditCommon.bigDecimalValue(data.get("aloneBankLowestCreditAmount")));
			hmNoCAQuasiCreditCardInfoSum.setOverdraftAmount(CreditCommon.bigDecimalValue(data.get("overdraftAmount")));
			hmNoCAQuasiCreditCardInfoSum.setRecent6MonthAveOverdAmount(CreditCommon.bigDecimalValue(data.get("recent6MonthAveOverdAmount")));
			hmNoCAQuasiCreditCardInfoSum.setQueryId(queryId);
			hmNoCAQuasiCreditCardInfoSum.setIdCard(idCard);
			hmNoCAQuasiCreditCardInfoSumRepository.saveEntity(hmNoCAQuasiCreditCardInfoSum);
			flag = true;
			logger.info("=====11==>HmNoCAQuasiCreditCardInfoSum");
			break;
		default:
			flag = true;
			logger.info("=====进入default错误name：" + name + "=>data:" + data);
			break;
		}
		return flag;
	}
	
	/**
	 * @Title: saveListBean 
	 * @Description: 保存List数据
	 * @author gufeng 
	 * @param name 对象名
	 * @param dataList 数据
	 * @param queryId 查询id
	 * @param idCard 证件号
	 * @return 是否保存成功
	 * @throws BusinessException 保存异常
	 */
	@SuppressWarnings("unchecked")
	@Transactional(rollbackFor = Exception.class)
	public boolean saveListBean(String name, List<Map<String, Object>> dataList,String queryId, String idCard)throws BusinessException {
		logger.info("=====保存LISTBEAN:" + name + "==>data:" + dataList + "==>queryId:" + queryId + "==>idCard:" + idCard);
		if(ObjectHelper.isEmpty(name) || ObjectHelper.isEmpty(queryId)){
			throw new BusinessException("10000000001","不满足保存条件，BEAN:" + name + "==>queryId:" + queryId);
		}
		if(ObjectHelper.isEmpty(dataList)){
			return true;
		}
		boolean flag = false;
		switch (name) {
		case "HmAssetsDisposalInfo":
			for (Map<String, Object> map : dataList) {
				HmAssetsDisposalInfo hmAssetsDisposalInfo = new HmAssetsDisposalInfo();
				hmAssetsDisposalInfo.setNo(CreditCommon.integerValue(map.get("no")));
				hmAssetsDisposalInfo.setAssetManagementCo(CreditCommon.stringValue(map.get("assetManagementCo")));
				hmAssetsDisposalInfo.setDebtReceiveDate(CreditCommon.stringValue(map.get("debtReceiveDate")));
				hmAssetsDisposalInfo.setReceiveRightsAmount(CreditCommon.bigDecimalValue(map.get("receiveRightsAmount")));
				hmAssetsDisposalInfo.setLastPayOffDate(CreditCommon.stringValue(map.get("lastPayOffDate")));
				hmAssetsDisposalInfo.setAssetDisposalOverage(CreditCommon.bigDecimalValue(map.get("assetDisposalOverage")));
				hmAssetsDisposalInfo.setQueryId(queryId);
				hmAssetsDisposalInfo.setIdCard(idCard);
				hmAssetsDisposalInfoRepository.saveEntity(hmAssetsDisposalInfo);
			}
			flag = true;
			logger.info("=====12==>HmAssetsDisposalInfo");
			break;
		case "HmCompulsoryExecutionRecord":
			for (Map<String, Object> map : dataList) {
				HmCompulsoryExecutionRecord hmCompulsoryExecutionRecord = new HmCompulsoryExecutionRecord();
				Object no = map.get("no");
				map.remove("no");
				CreditCommon.populate(hmCompulsoryExecutionRecord, map);
				hmCompulsoryExecutionRecord.setNo(CreditCommon.integerValue(no));
				hmCompulsoryExecutionRecord.setQueryId(queryId);
				hmCompulsoryExecutionRecord.setIdCard(idCard);
				hmCompulsoryExecutionRecordRepository.saveEntity(hmCompulsoryExecutionRecord);
			}
			flag = true;
			logger.info("=====13==>HmCompulsoryExecutionRecord");
			break;
		case "HmCreditLoanApproQueryDetailed":
			for (Map<String, Object> map : dataList) {
				HmCreditLoanApproQueryDetailed hmCreditLoanApproQueryDetailed = new HmCreditLoanApproQueryDetailed();
				CreditCommon.populate(hmCreditLoanApproQueryDetailed, map);
				hmCreditLoanApproQueryDetailed.setQueryId(queryId);
				hmCreditLoanApproQueryDetailed.setIdCard(idCard);
				hmCreditLoanApproQueryDetailedRepository.saveEntity(hmCreditLoanApproQueryDetailed);
			}
			flag = true;
			logger.info("=====14==>HmCreditLoanApproQueryDetailed");
			break;
		case "HmGuarantorCompenInfo":
			for (Map<String, Object> map : dataList) {
				HmGuarantorCompenInfo hmGuarantorCompenInfo = new HmGuarantorCompenInfo();
				hmGuarantorCompenInfo.setCompensationOrganization(CreditCommon.stringValue(map.get("compensationOrganization")));
				hmGuarantorCompenInfo.setLastCompensationDate(CreditCommon.stringValue(map.get("lastCompensationDate")));
				hmGuarantorCompenInfo.setCompensationSum(CreditCommon.bigDecimalValue(map.get("compensationSum")));
				hmGuarantorCompenInfo.setLastPayOffDate(CreditCommon.stringValue(map.get("lastPayOffDate")));
				hmGuarantorCompenInfo.setCompensationOverage(CreditCommon.bigDecimalValue(map.get("compensationOverage")));
				hmGuarantorCompenInfo.setQueryId(queryId);
				hmGuarantorCompenInfo.setIdCard(idCard);
				hmGuarantorCompenInfoRepository.saveEntity(hmGuarantorCompenInfo);
			}
			flag = true;
			logger.info("=====15==>HmGuarantorCompenInfo");
			break;
		case "HmHousingReservePayRecord":
			for (Map<String, Object> map : dataList) {
				HmHousingReservePayRecord hmHousingReservePayRecord = new HmHousingReservePayRecord();
				hmHousingReservePayRecord.setPayArea(CreditCommon.stringValue(map.get("payArea")));
				hmHousingReservePayRecord.setFirstPayMonth(CreditCommon.stringValue(map.get("firstPayMonth")));
				hmHousingReservePayRecord.setPayToMonth(CreditCommon.stringValue(map.get("payToMonth")));
				hmHousingReservePayRecord.setPayState(CreditCommon.stringValue(map.get("payState")));
				hmHousingReservePayRecord.setMonthPayDeposit(CreditCommon.bigDecimalValue(map.get("monthPayDeposit")));
				hmHousingReservePayRecord.setPersonPayProportion(CreditCommon.stringValue(map.get("personPayProportion")));
				hmHousingReservePayRecord.setCompanyPayProportion(CreditCommon.stringValue(map.get("companyPayProportion")));
				hmHousingReservePayRecord.setPayCompany(CreditCommon.stringValue(map.get("payCompany")));
				hmHousingReservePayRecord.setInfoUpdateDate(CreditCommon.stringValue(map.get("infoUpdateDate")));
				hmHousingReservePayRecord.setQueryId(queryId);
				hmHousingReservePayRecord.setIdCard(idCard);
				hmHousingReservePayRecordRepository.saveEntity(hmHousingReservePayRecord);
			}
			flag = true;
			logger.info("=====16==>HmHousingReservePayRecord");
			break;
		case "HmLoanInfo":
			for (Map<String, Object> map : dataList) {
				HmLoanInfo hmLoanInfo = new HmLoanInfo();
				hmLoanInfo.setTitleInfo(CreditCommon.stringValue(map.get("titleInfo")));
				hmLoanInfo.setAccountStatus(CreditCommon.stringValue(map.get("accountStatus")));
				hmLoanInfo.setFiveclasscode(CreditCommon.stringValue(map.get("fiveclasscode")));
				hmLoanInfo.setPrincipalAmount(CreditCommon.bigDecimalValue(map.get("principalAmount")));
				hmLoanInfo.setRemainRepayNum(CreditCommon.integerValue(map.get("remainRepayNum")));
				hmLoanInfo.setThisMonthRepayDay(CreditCommon.stringValue(map.get("thisMonthRepayDay")));
				hmLoanInfo.setThisMonthActualRepayAmount(CreditCommon.bigDecimalValue(map.get("thisMonthActualRepayAmount")));
				hmLoanInfo.setTheLastestRepayDay(CreditCommon.stringValue(map.get("theLastestRepayDay")));
				hmLoanInfo.setCurrentOverdueNum(CreditCommon.integerValue(map.get("currentOverdueNum")));
				hmLoanInfo.setCurrentOverdueAmount(CreditCommon.bigDecimalValue(map.get("currentOverdueAmount")));
				hmLoanInfo.setOverdue31To60Days(CreditCommon.bigDecimalValue(map.get("overdue31To60Days")));
				hmLoanInfo.setOverdue61To90Days(CreditCommon.bigDecimalValue(map.get("overdue61To90Days")));
				hmLoanInfo.setOverdue91To180Days(CreditCommon.bigDecimalValue(map.get("overdue91To180Days")));
				hmLoanInfo.setOverdue180Days(CreditCommon.bigDecimalValue(map.get("overdue180Days")));
				hmLoanInfo.setMonth24RepayTitle(CreditCommon.stringValue(map.get("month24RepayTitle")));
				hmLoanInfo.setMonth24RepayStatus(CreditCommon.stringValue(map.get("month24RepayStatus")));
				hmLoanInfo.setOverdueTitle1(CreditCommon.stringValue(map.get("overdueTitle1")));
				hmLoanInfo.setOverdueTitle2(CreditCommon.stringValue(map.get("overdueTitle2")));
				hmLoanInfo.setThisMonthRepayAmount(CreditCommon.bigDecimalValue(map.get("thisMonthRepayAmount")));
				hmLoanInfo = hmLoanInfoRepository.saveEntity(hmLoanInfo);
				List<Map<String,Object>> child = null;
				Object obj = map.get("overDueList");
				if(ObjectHelper.isNotEmpty(obj) && ObjectHelper.isNotEmpty(obj.toString())){
					child = (List<Map<String, Object>>) obj;
				}
				if(ObjectHelper.isNotEmpty(child)){
					for (Map<String, Object> childData : child) {
						HmOverdue hmOverdue = new HmOverdue();
						hmOverdue.setOverdueAmount1(CreditCommon.bigDecimalValue(childData.get("overdueAmount1")));
						hmOverdue.setOverdueContMonths2(CreditCommon.stringValue(childData.get("overdueContMonths2")));
						hmOverdue.setOverdueAmount2(CreditCommon.bigDecimalValue(childData.get("overdueAmount2")));
						hmOverdue.setOverdueMonth1(CreditCommon.stringValue(childData.get("overdueMonth1")));
						hmOverdue.setOverdueMonth2(CreditCommon.stringValue(childData.get("overdueMonth2")));
						hmOverdue.setOverdueMonthNum1(CreditCommon.integerValue(childData.get("overdueMonthNum1")));
						hmOverdue.setOverdueMonthNum2(CreditCommon.integerValue(childData.get("overdueMonthNum2")));
						hmOverdue.setOverdueContMonths1(CreditCommon.stringValue(childData.get("overdueContMonths1")));
						hmOverdue.setCreditId(hmLoanInfo.getId());
						hmOverdue.setQueryId(queryId);
						hmOverdue.setIdCard(idCard);
						hmOverdueRepository.saveEntity(hmOverdue);
					}
				}
				obj = map.get("specTraList");
				List<Map<String,Object>> childs = null;
				if(ObjectHelper.isNotEmpty(obj) && ObjectHelper.isNotEmpty(obj.toString())){
					childs = (List<Map<String, Object>>) obj;
				}
				if(ObjectHelper.isNotEmpty(childs) ){
					for (Map<String, Object> map2 : childs) {
						HmSpecTra hmSpecTra = new HmSpecTra();
						hmSpecTra.setSpecTraType(CreditCommon.stringValue(map2.get("specTraType")));
						hmSpecTra.setSpecTraDate(CreditCommon.stringValue(map2.get("specTraDate")));
						hmSpecTra.setSpecTraChanMonthNum(CreditCommon.integerValue(map2.get("specTraChanMonthNum")));
						hmSpecTra.setSpecTraAmount(CreditCommon.bigDecimalValue(map2.get("specTraAmount")));
						hmSpecTra.setSpecTraDetailed(CreditCommon.stringValue(map2.get("specTraDetailed")));
						hmSpecTra.setLoanId(hmLoanInfo.getId());
						hmSpecTra.setQueryId(queryId);
						hmSpecTra.setIdCard(idCard);
						hmSpecTraRepository.saveEntity(hmSpecTra);
					}
				}
				hmLoanInfo.setQueryId(queryId);
				hmLoanInfo.setIdCard(idCard);
				hmLoanInfoRepository.saveEntity(hmLoanInfo);
			}
			flag = true;
			logger.info("=====17,18,19==>HmLoanInfo,HmOverdue,HmSpecTra");
			break;
		case "HmOccupationInfo":
			for (Map<String, Object> map : dataList) {
				HmOccupationInfo hmOccupationInfo = new HmOccupationInfo();
				CreditCommon.populate(hmOccupationInfo, map);
				hmOccupationInfo.setQueryId(queryId);
				hmOccupationInfo.setIdCard(idCard);
				hmOccupationInfoRepository.saveEntity(hmOccupationInfo);
			}
			flag = true;
			logger.info("=====20==>HmOccupationInfo");
			break;
		case "HmPensionInsurancePayRecord":
			for (Map<String, Object> map : dataList) {
				HmPensionInsurancePayRecord hmPensionInsurancePayRecord = new HmPensionInsurancePayRecord();
				hmPensionInsurancePayRecord.setPayArea(CreditCommon.stringValue(map.get("payArea")));
				hmPensionInsurancePayRecord.setPayDate(CreditCommon.stringValue(map.get("payDate")));
				hmPensionInsurancePayRecord.setCumulativePayMonths(CreditCommon.integerValue(map.get("cumulativePayMonths")));
				hmPensionInsurancePayRecord.setWorkMonth(CreditCommon.stringValue(map.get("workMonth")));
				hmPensionInsurancePayRecord.setPayState(CreditCommon.stringValue(map.get("payState")));
				hmPensionInsurancePayRecord.setPersonPayBase(CreditCommon.stringValue(map.get("personPayBase")));
				hmPensionInsurancePayRecord.setThisMonthPayAmount(CreditCommon.bigDecimalValue(map.get("thisMonthPayAmount")));
				hmPensionInsurancePayRecord.setInfoUpdateDate(CreditCommon.stringValue(map.get("infoUpdateDate")));
				hmPensionInsurancePayRecord.setPayCompany(CreditCommon.stringValue(map.get("payCompany")));
				hmPensionInsurancePayRecord.setCancelPayReason(CreditCommon.stringValue(map.get("cancelPayReason")));
				hmPensionInsurancePayRecord.setQueryId(queryId);
				hmPensionInsurancePayRecord.setIdCard(idCard);
				hmPensionInsurancePayRecordRepository.saveEntity(hmPensionInsurancePayRecord);
			}
			flag = true;
			logger.info("=====21==>HmPensionInsurancePayRecord");
			break;
		case "HmPerGuaranteeInfo":
			for (Map<String, Object> map : dataList) {
				HmPerGuaranteeInfo hmPerGuaranteeInfo = new HmPerGuaranteeInfo();
				hmPerGuaranteeInfo.setNo(CreditCommon.integerValue(map.get("no")));
				hmPerGuaranteeInfo.setGuarLoanIssuingAgency(CreditCommon.stringValue(map.get("guarLoanIssuingAgency")));
				hmPerGuaranteeInfo.setGuarLoanContractAmount(CreditCommon.bigDecimalValue(map.get("guarLoanContractAmount")));
				hmPerGuaranteeInfo.setGuarLoanIssueDate(CreditCommon.stringValue(map.get("guarLoanIssueDate")));
				hmPerGuaranteeInfo.setGuarLoanDueDate(CreditCommon.stringValue(map.get("guarLoanDueDate")));
				hmPerGuaranteeInfo.setGuarAmount(CreditCommon.bigDecimalValue(map.get("guarAmount")));
				hmPerGuaranteeInfo.setGuarLoanPrincipalAmount(CreditCommon.bigDecimalValue(map.get("guarLoanPrincipalAmount")));
				hmPerGuaranteeInfo.setGuarLoanFiveclasscode(CreditCommon.stringValue(map.get("guarLoanFiveclasscode")));
				hmPerGuaranteeInfo.setSettlementDate(CreditCommon.stringValue(map.get("settlementDate")));
				hmPerGuaranteeInfo.setQueryId(queryId);
				hmPerGuaranteeInfo.setIdCard(idCard);
				hmPerGuaranteeInfoRepository.saveEntity(hmPerGuaranteeInfo);
			}
			flag = true;
			logger.info("=====22==>HmPerGuaranteeInfo");
			break;
		case "HmQuasiCreditCard":
			for (Map<String, Object> map : dataList) {
				HmQuasiCreditCard hmQuasiCreditCard = new HmQuasiCreditCard();
				hmQuasiCreditCard.setTitleInfo(CreditCommon.stringValue(map.get("titleInfo")));
				hmQuasiCreditCard.setAccountStatus(CreditCommon.stringValue(map.get("accountStatus")));
				hmQuasiCreditCard.setSharedCreditLimit(CreditCommon.bigDecimalValue(map.get("sharedCreditLimit")));
				hmQuasiCreditCard.setOverdraftBalance(CreditCommon.bigDecimalValue(map.get("overdraftBalance")));
				hmQuasiCreditCard.setLast6MonthsAveOverBalan(CreditCommon.bigDecimalValue(map.get("last6MonthsAveOverBalan")));
				hmQuasiCreditCard.setMaxOverBalan(CreditCommon.bigDecimalValue(map.get("maxOverBalan")));
				hmQuasiCreditCard.setBillingDate(CreditCommon.stringValue(map.get("billingDate")));
				hmQuasiCreditCard.setTheLastestRepayDay(CreditCommon.stringValue(map.get("theLastestRepayDay")));
				hmQuasiCreditCard.setOverOver180UnpaidBalan(CreditCommon.bigDecimalValue(map.get("overOver180UnpaidBalan")));
				hmQuasiCreditCard.setMonth24RepayTitle(CreditCommon.stringValue(map.get("month24RepayTitle")));
				hmQuasiCreditCard.setMonth24RepayStatus(CreditCommon.stringValue(map.get("month24RepayStatus")));
				hmQuasiCreditCard.setOverdueTile(CreditCommon.stringValue(map.get("overdueTile")));
				hmQuasiCreditCard.setOverdueMonth1(CreditCommon.stringValue(map.get("overdueMonth1")));
				hmQuasiCreditCard.setOverdueMonthNum1(CreditCommon.integerValue(map.get("overdueMonthNum1")));
				hmQuasiCreditCard.setOverdueAmount1(CreditCommon.bigDecimalValue(map.get("overdueAmount1")));
				hmQuasiCreditCard.setOverdueMonth2(CreditCommon.stringValue(map.get("overdueMonth2")));
				hmQuasiCreditCard.setOverdueMonthNum2(CreditCommon.integerValue(map.get("overdueMonthNum2")));
				hmQuasiCreditCard.setOverdueAmount2(CreditCommon.bigDecimalValue(map.get("overdueAmount2")));
				hmQuasiCreditCard.setThisMonthActualRepayAmount(CreditCommon.bigDecimalValue(map.get("thisMonthActualRepayAmount")));
				hmQuasiCreditCard.setQueryId(queryId);
				hmQuasiCreditCard.setIdCard(idCard);
				hmQuasiCreditCardRepository.saveEntity(hmQuasiCreditCard);
			}
			flag = true;
			logger.info("=====23==>HmQuasiCreditCard");
			break;
		case "HmResideInfo":
			for (Map<String, Object> map : dataList) {
				HmResideInfo hmResideInfo = new HmResideInfo();
				CreditCommon.populate(hmResideInfo, map);
				hmResideInfo.setQueryId(queryId);
				hmResideInfo.setIdCard(idCard);
				hmResideInfoRepository.saveEntity(hmResideInfo);
			}
			flag = true;
			logger.info("=====24==>HmResideInfo");
			break;
		case "HmCreditCardInfo":
			for (Map<String, Object> map : dataList) {
				HmCreditCardInfo hmCreditCardInfo = new HmCreditCardInfo();
				hmCreditCardInfo.setSharedCreditLimit(CreditCommon.bigDecimalValue(map.get("sharedCreditLimit")));
				hmCreditCardInfo.setLast6MonthsAve(CreditCommon.bigDecimalValue(map.get("last6MonthsAve")));
				hmCreditCardInfo.setLast6MonthsAveUsedCreditLimit(CreditCommon.bigDecimalValue(map.get("last6MonthsAveUsedCreditLimit")));
				hmCreditCardInfo.setTitleInfo(CreditCommon.stringValue(map.get("titleInfo")));
				hmCreditCardInfo.setAccountStatus(CreditCommon.stringValue(map.get("accountStatus")));
				hmCreditCardInfo.setCurrentOverdueAmount(CreditCommon.bigDecimalValue(map.get("currentOverdueAmount")));
				hmCreditCardInfo.setCurrentOverdueNum(CreditCommon.integerValue(map.get("currentOverdueNum")));
				hmCreditCardInfo.setMonth24RepayStatus(CreditCommon.stringValue(map.get("month24RepayStatus")));
				hmCreditCardInfo.setMonth24RepayTitle(CreditCommon.stringValue(map.get("month24RepayTitle")));
				hmCreditCardInfo.setTheLastestRepayDay(CreditCommon.stringValue(map.get("theLastestRepayDay")));
				hmCreditCardInfo.setThisMonthActualRepayAmount(CreditCommon.bigDecimalValue(map.get("thisMonthActualRepayAmount")));
				hmCreditCardInfo.setThisMonthRepayAmount(CreditCommon.bigDecimalValue(map.get("thisMonthRepayAmount")));
				hmCreditCardInfo.setBillingDate(CreditCommon.stringValue(map.get("billingDate")));
				hmCreditCardInfo.setOverdueTile(CreditCommon.stringValue(map.get("overdueTile")));
				hmCreditCardInfo.setMaxUsedCreditLimit(CreditCommon.bigDecimalValue(map.get("maxUsedCreditLimit")));
				hmCreditCardInfo.setUsedCreditLimit(CreditCommon.bigDecimalValue(map.get("usedCreditLimit")));
				hmCreditCardInfo.setQueryId(queryId);
				hmCreditCardInfo.setIdCard(idCard);
				hmCreditCardInfo = hmCreditCardInfoRepository.saveEntity(hmCreditCardInfo);
				List<Map<String,Object>> child = null;
				Object obj = map.get("overDueList");
				if(ObjectHelper.isNotEmpty(obj) && ObjectHelper.isNotEmpty(obj.toString())){
					child = (List<Map<String, Object>>) obj;
				}
				if(ObjectHelper.isNotEmpty(child)){
					for (Map<String, Object> childData : child) {
						HmOverdue hmOverdue = new HmOverdue();
						hmOverdue.setOverdueAmount1(CreditCommon.bigDecimalValue(childData.get("overdueAmount1")));
						hmOverdue.setOverdueContMonths2(CreditCommon.stringValue(childData.get("overdueContMonths2")));
						hmOverdue.setOverdueAmount2(CreditCommon.bigDecimalValue(childData.get("overdueAmount2")));
						hmOverdue.setOverdueMonth1(CreditCommon.stringValue(childData.get("overdueMonth1")));
						hmOverdue.setOverdueMonth2(CreditCommon.stringValue(childData.get("overdueMonth2")));
						hmOverdue.setOverdueMonthNum1(CreditCommon.integerValue(map.get("overdueMonthNum1")));
						hmOverdue.setOverdueMonthNum2(CreditCommon.integerValue(map.get("overdueMonthNum2")));
						hmOverdue.setOverdueContMonths1(CreditCommon.stringValue(childData.get("overdueContMonths1")));
						hmOverdue.setCreditId(hmCreditCardInfo.getId());
						hmOverdue.setQueryId(queryId);
						hmOverdue.setIdCard(idCard);
						hmOverdueRepository.saveEntity(hmOverdue);
					}
				}
			}
			flag = true;
			logger.info("=====25,26==>HmCreditCardInfo:HmOverdue");
			break;
		case "HmReportExplain":
			for (Map<String, Object> map : dataList) {
				HmReportExplain hmReportExplain = new HmReportExplain();
				hmReportExplain.setNo(CreditCommon.integerValue(map.get("no")));
				hmReportExplain.setExplain(CreditCommon.stringValue(map.get("explain")));
				hmReportExplain.setQueryId(queryId);
				hmReportExplain.setIdCard(idCard);
				hmReportExplainRepository.saveEntity(hmReportExplain);
			}
			flag = true;
			logger.info("=====27==>HmReportExplain");
			break;
		default:
			flag = true;
			logger.info("=====LIST进入default错误name：" + name + "=>data:" + dataList);
			break;
		}
		return flag;
	}

	
}
