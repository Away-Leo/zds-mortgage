package com.zdsoft.finance.loan.service.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.zdsoft.essential.client.service.CED;
import com.zdsoft.finance.base.service.impl.BaseServiceImpl;
import com.zdsoft.finance.casemanage.interview.entity.Interview;
import com.zdsoft.finance.casemanage.interview.repository.InterviewRepository;
import com.zdsoft.finance.casemanage.limitApply.entity.CaseLimitApply;
import com.zdsoft.finance.casemanage.limitApply.repository.CaseLimitApplyRepository;
import com.zdsoft.finance.casemanage.receivablePlan.entity.BankAccount;
import com.zdsoft.finance.casemanage.receivablePlan.entity.ReceivableInfo;
import com.zdsoft.finance.casemanage.receivablePlan.entity.ReceivablePlan;
import com.zdsoft.finance.casemanage.receivablePlan.entity.ReceivablePlanForm;
import com.zdsoft.finance.casemanage.receivablePlan.entity.RepayPlan;
import com.zdsoft.finance.casemanage.receivablePlan.service.ReceivablePlanService;
import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.finance.common.base.QueryObj;
import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.finance.common.utils.RateUtil;
import com.zdsoft.finance.customer.entity.BeforeAddress;
import com.zdsoft.finance.customer.entity.BeforeContact;
import com.zdsoft.finance.customer.entity.BeforeCustomer;
import com.zdsoft.finance.customer.entity.BeforePersonalCustomer;
import com.zdsoft.finance.customer.repository.BeforePersonalCusomerRepository;
import com.zdsoft.finance.loan.entity.LoanApply;
import com.zdsoft.finance.loan.entity.LoanOperateLog;
import com.zdsoft.finance.loan.entity.LoanRecord;
import com.zdsoft.finance.loan.repository.LoanApplyRepository;
import com.zdsoft.finance.loan.repository.LoanOperateLogRepository;
import com.zdsoft.finance.loan.repository.LoanRecordRepository;
import com.zdsoft.finance.loan.service.LoanFollowUpService;
import com.zdsoft.finance.marketing.entity.CaseApply;
import com.zdsoft.finance.marketing.entity.CaseApplyBeforeCustomer;
import com.zdsoft.finance.marketing.entity.CaseApplyStageEnumSimpleCodeEnum;
import com.zdsoft.finance.marketing.repository.CaseApplyBeforeCustomerRepository;
import com.zdsoft.finance.marketing.service.CaseApplyService;
import com.zdsoft.finance.prcostitem.entity.PrCostItem;
import com.zdsoft.finance.prcostitem.repository.PrCostItemRepository;
import com.zdsoft.finance.product.entity.Product;
import com.zdsoft.finance.product.repository.ProductRepository;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.Pageable;
import com.zdsoft.framework.core.common.util.ObjectHelper;
/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title: LoanFollowUpServiceImpl.java 
 * @ClassName: LoanFollowUpServiceImpl 
 * @Description: 放款跟进serviceimpl
 * @author huangwei 
 * @date 2017年2月22日 下午2:36:53 
 * @version V1.0
 */
@Service
public class LoanFollowUpServiceImpl extends BaseServiceImpl<LoanApply, CustomRepository<LoanApply, String>>
implements LoanFollowUpService {
	@Autowired
	LoanApplyRepository loanApplyRepository;
	@Autowired
	private CaseApplyService caseApplyService;
	@Autowired
	private CED CED;
	@Autowired
	LoanOperateLogRepository loanOperateLogRepository;
	@Autowired
	private LoanFollowUpService loanFollowUpService;
	@Autowired
	private CaseApplyBeforeCustomerRepository caseApplyBeforeCustomerRepository;
	@Autowired
	private BeforePersonalCusomerRepository beforePersonalCusomerRepository;
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private PrCostItemRepository prCostItemRepository;
	@Autowired
	private InterviewRepository interviewRepository;
	@Autowired
	private LoanRecordRepository loanRecordRepository;
	@Autowired
	private ReceivablePlanService receivablePlanService;
	 /**
	  * @Title: getLoanFollowUpList 
	  * @Description: 获取放款跟进显示列表
	  * @author huangwei 
	  * @param loanApplyId
	  * @return
	  * @throws Exception
	  */
	@Override
	public Page<Map<String,Object>> getLoanFollowUpList(Pageable pageable,  List<QueryObj> queryObjs) throws Exception {
		Page<Map<String, Object>> caseApplyPage = loanApplyRepository.getLoanApplyDetailById(pageable, queryObjs);
		List<Map<String, Object>> dataList=caseApplyPage.getRecords();
		SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat format1=new SimpleDateFormat("yyyyMMdd");
		for(Map<String, Object> data : dataList)
		{
			//计算待放款金额
			if(data.get("STATUS").equals("3"))
			{
				data.put("pendingLoan","0");
			}
			else
			{
				data.put("pendingLoan",data.get("APPLYAMOUNT"));
			}
			//批次号
			if(data.get("BATCHNUMBER")!=null)
			{
				data.put("batchNumber",CED.loadSimpleCodeNameByFullCode(data.get("BATCHNUMBER").toString()));
			}
			//放款日期
			if(data.get("ACTUALDATE")!=null)
			{
				
				data.put("applyDate",format.format(format1.parse(data.get("ACTUALDATE").toString())));
			}
			//贷款期限
			if(data.get("APPLYTERM")!=null&&data.get("APPLYTERMUNIT")!=null)
			{
				data.put("applyTerm",data.get("APPLYTERM")+CED.loadSimpleCodeNameByFullCode(data.get("APPLYTERMUNIT")+""));
			}
			//计划天数
			if(data.get("CONTRACTSTARTDATE")!=null&&data.get("CONTRACTENDDATE")!=null)
			{
				String strDate=data.get("CONTRACTSTARTDATE").toString();
				String endDate=data.get("CONTRACTENDDATE").toString();
				SimpleDateFormat format2 =new SimpleDateFormat("yyyyMMdd");
				SimpleDateFormat format3 =new SimpleDateFormat("yyyyMMddHHmmss");
				Date strD;
				Date endD;
				if(strDate.length()==8)
				{
					strD=format2.parse(strDate);
				}
				else
				{
					strD=format3.parse(strDate);
				}
				if(endDate.length()==8)
				{
					endD=format2.parse(endDate);
				}
				else
				{
					endD=format3.parse(endDate);
				}
				data.put("days",daysOfTwo(strD,endD));
			}
			//备付资金分配
			if(data.get("ACTUALLIMITSTATUS")!=null)
			{
				String ac=data.get("ACTUALLIMITSTATUS").toString();
				if(ac.equals(CaseLimitApply.NOT_APPLY))
				{
					data.put("actualLimitStatus","额度未申请");
				}
				else if(ac.equals(CaseLimitApply.FILED_WITHOUT_MONEY))
				{
					data.put("actualLimitStatus","已申请未配资金");
				}
				else if(ac.equals(CaseLimitApply.ALLOCATED_FUNDS))
				{
					data.put("actualLimitStatus","已匹配资金");
				}
			}
			//null处理
			if(data.get("CAPITALNAME")==null)
			{
				data.put("CAPITALNAME", "");
			}
			if(data.get("FUNDPLANNAME")==null)
			{
				data.put("FUNDPLANNAME", "");
			}
		}
		return caseApplyPage;
	}
	
	 /**
	  * @Title: daysOfTwo 
	  * @Description: 计算2个日期之间的天数
	  * @author huangwei 
	  * @param fDate
	  * @param oDate
	  * @return
	  */
	 private int daysOfTwo(Date fDate, Date oDate) {

		 if (null == fDate || null == oDate) {

	           return -1;

	       }

	       long intervalMilli = oDate.getTime()-fDate.getTime();

	       return (int) (intervalMilli / (24 * 60 * 60 * 1000));

	    }
	/**
	  * @Title: getAllowLoanForm 
	  * @Description: 查找准放款表单信息
	  * @author huangwei 
	  * @param formMap
	  * @return
	  * @throws Exception
	  */
	public Map<String,Object> getAllowLoanForm(Map<String,Object> formMap) throws Exception{
		String caseApplyId=formMap.get("CASEAPPLYID").toString();
		//获取银行信息
		 CaseApply caseApply=caseApplyService.findOne(caseApplyId);
		 List<BankAccount> caseBankAccount = caseApply.getCaseBankAccount();
		 if(ObjectHelper.isNotEmpty(caseBankAccount)){
	           for(BankAccount bank : caseBankAccount){
	               //收款账户
	               if(bank.getAccountType()==0){
	               	formMap.put("receiveBankAccountName", bank.getCardholderName());
	               	formMap.put("receiveBankName", bank.getBankAccount());
	               	formMap.put("receiveBankAccount", bank.getBankNo());
	               	formMap.put("receiveBankCode",bank.getBankCode());
	               	break;
	               }
	           }
	       }
		 //生成贷款期限
		 String loanTerm=formMap.get("APPLYTERM").toString();
		 switch(formMap.get("APPLYTERMUNIT").toString())
		 {
		  	case "0931001": loanTerm+="年";break;
		  	case "0931002": loanTerm+="月";break;
		  	case "0931003": loanTerm+="日";break;
		 }
		 formMap.put("loanTerm",loanTerm);
		 //如果操作为取消准放款，还需查出放款批次和准放款日期
		 if(formMap.get("operate").equals("cancel"))
		 {
			 LoanApply apply=loanApplyRepository.findOne(formMap.get("ID").toString());
			 formMap.put("batchNumber", apply.getBatchNumber());
			 SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
			 formMap.put("quasiLoanDate", format.format(apply.getQuasiLoanDate()));
			 formMap.put("remark", apply.getRemark());
			 formMap.put("operate", "cancel");
		 }
		 else if(formMap.get("operate").equals("sure"))
		 {
			 formMap.put("operate", "sure");
		 }
		 //转换日期格式
		 Date date=caseApply.getCreateTime();
		 SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
		 formMap.put("CREATETIME",format.format(date));
		//风险审批通过时间
		 SimpleDateFormat format1=new SimpleDateFormat("yyyyMMddHHmmss");
		 Interview interview= interviewRepository.findByCaseApplyId(caseApply.getId());
		 if(interview!=null&&interview.getBusiForm()!=null&&interview.getBusiForm().getProcessEndDate()!=null)
		 {
			 Long passDateStr=interview.getBusiForm().getProcessEndDate();
			 formMap.put("passDate", format.format(format1.parse(passDateStr+"")+"(截止放款前已过"+daysOfTwo(new Date(),format1.parse(passDateStr+""))+"天)"));
		 }
		 return formMap;
	}
	/**
	 * @Title: getLoanForm 
	 * @Description: 查找放款表单信息
	 * @author huangwei 
	 * @param formMap
	 * @return
	 * @throws Exception
	 */
	public Map<String,Object> getLoanForm(Map<String,Object> formMap) throws Exception{
		String caseApplyId=formMap.get("CASEAPPLYID").toString();
		//获取银行信息
		 CaseApply caseApply=caseApplyService.findOne(caseApplyId);
		 List<BankAccount> caseBankAccount = caseApply.getCaseBankAccount();
		 if(ObjectHelper.isNotEmpty(caseBankAccount)){
	           for(BankAccount bank : caseBankAccount){
	               //收款账户
	               if(bank.getAccountType()==0){
	               	formMap.put("receiveBankAccountName", bank.getCardholderName());
	               	formMap.put("receiveBankName", bank.getBankAccount());
	               	formMap.put("receiveBankAccount", bank.getBankNo());
	               	formMap.put("receiveBankCode",bank.getBankCode());
	               	break;
	               }
	           }
	       }
		 //生成贷款期限
		 String loanTerm=formMap.get("APPLYTERM").toString();
		 switch(formMap.get("APPLYTERMUNIT").toString())
		 {
		  	case "0931001": loanTerm+="年";break;
		  	case "0931002": loanTerm+="月";break;
		  	case "0931003": loanTerm+="日";break;
		 }
		 formMap.put("loanTerm",loanTerm);
		 //已放款金额
		 formMap.put("loanAmount",caseApply.getLoanApplyAnount());
		 //转换日期格式
		 Date date=caseApply.getCreateTime();
		 SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
		 SimpleDateFormat format1=new SimpleDateFormat("yyyyMMddHHmmss");
		 formMap.put("CREATETIME",format.format(date));
		 //风险审批通过时间
		 Interview interview= interviewRepository.findByCaseApplyId(caseApply.getId());
		 if(interview!=null&&interview.getBusiForm()!=null&&interview.getBusiForm().getProcessEndDate()!=null)
		 {
			 Long passDateStr=interview.getBusiForm().getProcessEndDate();
			 formMap.put("passDate", format.format(format1.parse(passDateStr+"")+"(截止放款前已过"+daysOfTwo(new Date(),format1.parse(passDateStr+""))+"天)"));
		 }
		 return formMap;
	}
	/**
	 * @Title: sureAllowLoan 
	 * @Description: 确认准放款
	 * @author huangwei 
	 * @param request
	 */
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	public void sureAllowLoan(Map<String,String[]> param)throws Exception{
		String id=param.get("loanApplyId")[0];
		//批次号
		String batchNumber=param.get("batchNumber")[0];
		String remark=param.get("remark")[0];
		String date=param.get("loanDate")[0];
		SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
		LoanApply apply=loanApplyRepository.findOne(id);
		//修改状态为准放款
		apply.setStatus("2");
		//准放款金额
		apply.setQuasiLoanAmount(apply.getApplyAmount());
		//准放款批次号
		apply.setBatchNumber(batchNumber);
		apply.setRemark(remark);
		//准放款日期
		apply.setQuasiLoanDate(format.parse(date).getTime());
		loanApplyRepository.updateEntity(apply);
		//保存历史信息
		LoanOperateLog log=new LoanOperateLog();
		log.setDealType("1");
		log.setDealAmount(apply.getQuasiLoanAmount());
		log.setHandlerCode(CED.getLoginUser().getId());
		log.setDealDate(new Date().getTime());
		log.setLoanApplyId(id);
		log.setRemark(apply.getRemark());
		log.setIsDeleted(false);
		log.setCreateTime(new Date());
		loanOperateLogRepository.save(log);
		//改修案件状态为准放款
		CaseApply caseApply=caseApplyService.findOne(apply.getCaseApplyId());
		caseApply.setStage(CaseApplyStageEnumSimpleCodeEnum.QUASILENDERS.value);
		caseApplyService.saveOrUpdateEntity(caseApply);
	}
	
	/**
	 * Title: sureLoan 
	 * Description: 确认放款
	 * @param param
	 * @throws Exception   
	 * @see com.zdsoft.finance.loan.service.LoanFollowUpService#sureLoan(java.util.Map)
	 */
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	public void sureLoan(Map<String,String[]> param)throws Exception{
		//1、修改状态为放款
		String id=param.get("loanApplyId")[0];
		String operateType=param.get("operateType")[0];
		String remark=param.get("remark")[0];
		//实际放款日期
		String date=param.get("loanDate")[0];
		String loanAmount=param.get("loanAmount")[0];
		SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat format1=new SimpleDateFormat("yyyyMMdd");
		LoanApply apply=loanApplyRepository.findOne(id);
		apply.setStatus("3");
		apply.setLoanAmount(apply.getLoanAmount().add(new BigDecimal(loanAmount)));//放款金额
		loanApplyRepository.updateEntity(apply);
		
		
		//2、保存历史信息
		LoanOperateLog log=new LoanOperateLog();
		log.setDealType("3");
		log.setDealAmount(apply.getQuasiLoanAmount());
		log.setHandlerCode(CED.getLoginUser().getId());
		log.setDealDate(new Date().getTime());
		log.setDealRealDate(Long.parseLong(format1.format(format.parse(date))));
		log.setLoanApplyId(id);
		log.setRemark(remark);
		log.setIsDeleted(false);
		log.setCreateTime(new Date());
		loanOperateLogRepository.save(log);
		
		
		//3、修改案件的放款金额
		String caseApplyId=apply.getCaseApplyId();
		CaseApply caseApply=caseApplyService.findOne(caseApplyId);
		caseApply.setLoanApplyAnount(caseApply.getLoanApplyAnount().add(new BigDecimal(loanAmount)));
		caseApply.setCaseApplyBalance(caseApply.getCaseApplyBalance().add(new BigDecimal(loanAmount)));
		//修改案件阶段为以放款阶段
		caseApply.setStage(CaseApplyStageEnumSimpleCodeEnum.LOAN_ALREADY.value);
		caseApplyService.saveOrUpdateEntity(caseApply);
		//保存放款基本信息表
		LoanRecord record=new LoanRecord();
		//实际放款时间
		record.setActualDate(Long.parseLong(format1.format(format.parse(date))));
		record.setLoanApply(apply);
		record.setLoanAmount(apply.getLoanAmount());
		record.setRemark(remark);
		//操作时间
		record.setOperationDate(log.getDealDate());
		record.setOperationType(operateType);
		//操作人员
		record.setOperatorCode(CED.getLoginUser().getId());
		loanRecordRepository.save(record);
		
		
		//4、还款计划生成
		ReceivablePlanForm form = new ReceivablePlanForm();
		form.setLoanDate(record.getActualDate());//放款日期
		form.setPrincipalAmount(record.getLoanAmount());//贷款金额
		
		Double rate = null ;
		if(ObjectHelper.isNotEmpty(caseApply.getApplyRateUnit())&&ReceivableInfo.RECEIVABLEINFO_DAY.equals(caseApply.getApplyRateUnit())){
			rate = new BigDecimal(RateUtil.percentRate2(caseApply.getApplyRate().doubleValue(), true)).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
		}else{
			rate = new BigDecimal(RateUtil.percentRate(caseApply.getApplyRate().doubleValue())).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
		}
		
		//Double rate = RateConvertUtil.convertToYFeeRate(caseApply.getApplyRate().doubleValue(), caseApply.getApplyRateUnit());
		
		form.setRate(new BigDecimal(rate));//年利率
		form.setTerm(this.convertToMTerm(caseApply.getApplyTerm(), caseApply.getApplyTermUnit()));//期限
		form.setRepayMethod(caseApply.getRepayMethod());//还款方式
		form.setRepaymentDate(caseApply.getRepaymentDate());//每月还款号数
		List<RepayPlan> planList=receivablePlanService.receivablePlanGuarate(form);
		for (RepayPlan repayPlan : planList) {//保存还款计划
			ReceivablePlan plan = new ReceivablePlan();
			plan.setPeriods(repayPlan.getPeriods()); //期数
			plan.setPlanRepayDate(repayPlan.getPlanRepayDate()); //当前还款日期
			plan.setStartDate(repayPlan.getStartDate()); //当前开始日期
			plan.setEndDate(repayPlan.getEndDate()); //当前结束日期
			plan.setPlanPrincipalAmount(repayPlan.getPlanPrincipalAmount()); //当期本金
			plan.setPlanInterestAmount(repayPlan.getPlanInterestAmount()); //当期利息
			plan.setPlanServiceFee(repayPlan.getPlanServiceFee()); //当期服务费
			plan.setRemainPrincipal(repayPlan.getRemainPrincipal()); //当期剩余本金
			plan.setCaseApplyId(caseApply.getId()); //案件id
			plan.setLoanApplyId(apply.getId()); //放款ID
			plan.setOrgId(repayPlan.getOrgId()); //所属机构
			receivablePlanService.saveEntity(plan);
		}
	}
		
		/**
		 * @Title: convertToMTerm 
		 * @Description: 期限转换
		 * @author gufeng 
		 * @param term 期限
		 * @param util 期限单位
		 * @return 月期限
		 */
		private Integer convertToMTerm(Integer term,String util){
			if(ObjectHelper.isEmpty(term)){
				return 0;
			}
			switch (util) {
			case "0931001":
				term *= 12;
				break;
			case "0931003":
				term /= 30;
				break;
			default:
				break;
			}
			return term;
		}
	
	/**
	 * @Title: cancelAllowLoan 
	 * @Description: 取消准放款
	 * @author huangwei 
	 * @param request
	 * @throws Exception
	 */
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	public void cancelAllowLoan(Map<String,String[]> param)throws Exception{
		String id=param.get("loanApplyId")[0];
		LoanApply apply=loanApplyRepository.findOne(id);
		//修改状态为待放款
		apply.setStatus("1");
		//取消放款批次
		apply.setBatchNumber("");
		//取消备注信息
		apply.setRemark("");
		loanApplyRepository.updateEntity(apply);
		//保存历史信息
		LoanOperateLog log=new LoanOperateLog();
		log.setDealType("2");
		log.setDealAmount(apply.getQuasiLoanAmount());
		log.setHandlerCode(CED.getLoginUser().getId());
		log.setDealDate(new Date().getTime());
		log.setLoanApplyId(id);
		log.setRemark(apply.getRemark());
		loanOperateLogRepository.save(log);
		//改修案件状态为待放款
		CaseApply caseApply=caseApplyService.findOne(apply.getCaseApplyId());
		caseApply.setStage(CaseApplyStageEnumSimpleCodeEnum.TOBELENT.value);
		caseApplyService.saveOrUpdateEntity(caseApply);
	}
	/**
	 * @Title: findLoanApplyLog 
	 * @Description: 根据放款申请id获取操作历史
	 * @author huangwei 
	 * @param loanApplyId
	 */
	public List<LoanOperateLog> getLoanApplyLog(String loanApplyId)throws Exception{
		List<LoanOperateLog> list=loanOperateLogRepository.findByLoanApplyId(loanApplyId);
		for(LoanOperateLog log : list)
		{
			//获取操作人姓名
			log.setHandlerCode(CED.loadEmployeeById(log.getHandlerCode()).getEmpNm());
			if(log.getDealRealDate()>1)
			{
				//实际操作日期
				SimpleDateFormat format1=new SimpleDateFormat("yyyyMMdd");
				log.setDealRealDate((format1.parse(log.getDealRealDate()+"").getTime()));
			}
		}
		return list;
	}
	/**
	 * @Title: exportLoanApply 
	 * @Description: 导出准放款
	 * @author huangwei 
	 * @param codes   要导出的放款申请id
	 */
	public String  exportLoanApply(String[] codes)throws Exception{
		String result="";
		for(int i=0;i<codes.length-1;i++)
		{
			result+=createApplyExportMessage(codes[i])+"\n";
		}
		return result;
	}
	/**
	 * @Title: createApplyExportMessage 
	 * @Description: 生成请款申请的导出字符串
	 * @author huangwei 
	 * @param loanApplyId 放款申请Id
	 * @return
	 */
	private String createApplyExportMessage(String loanApplyId)throws Exception{
		StringBuilder targetStr=new StringBuilder("");
		try {
			//字段分隔符
			String separator="|+|";
			//请款对象
			LoanApply loanApply=loanFollowUpService.findOne(loanApplyId);
			//案件对象
			CaseApply caseApply=caseApplyService.findOne(loanApply.getCaseApplyId());
			//贷前贷款人对象
			BeforeCustomer beforeCustomer=null;
			//个人信息
			BeforePersonalCustomer personalCustomer=null;
			List<CaseApplyBeforeCustomer> caseApplyBeforeCustomerList=caseApplyBeforeCustomerRepository.findByCaseApplyId(loanApply.getCaseApplyId());
			 for(CaseApplyBeforeCustomer cabc : caseApplyBeforeCustomerList)
			 {
				 //查找主借人信息
				 if(cabc.getJoinType().equals(CaseApplyBeforeCustomer.MAIN_BORROW))
				 {
					 beforeCustomer=cabc.getBeforeCustomer();
				 }
			 }
			 personalCustomer=beforePersonalCusomerRepository.findOne(beforeCustomer.getId());
			 //贷前人地址对象列表
			 List<BeforeAddress> beforeAddress=beforeCustomer.getBeforeAddresss();
			 //家庭地址对象
			 BeforeAddress homeAdress=null;
			 //户籍地址对象
			 BeforeAddress realAdress=null;
			 for(BeforeAddress adress : beforeAddress)
			 {
				 if(adress.getAddressType().equals(BeforeAddress.HOME_ADDRESS))
				 {
					 homeAdress=adress;
				 }
				 if(adress.getAddressType().equals(BeforeAddress.HOUSEHOLD_REGISTRATION_ADDRESS))
				 {
					 realAdress=adress;
				 }
			 }
			List<BeforeContact> beforeContacts=beforeCustomer.getBeforeContacts();
			//手机号码YWDM0011701
			BeforeContact mobileNum=null;
			//家庭电话
			BeforeContact phoneNum=null;
			for(BeforeContact contact : beforeContacts)
			{
				if(contact.getContactType().equals(BeforeContact.MOBILE_NUMBER))
				{
					
					mobileNum=contact;
				}
				else if(contact.getContactType().equals(BeforeContact.PHONE_NUMBER))
				{
					phoneNum=contact;
				}
			}
			 List<BankAccount> caseBankAccount = caseApply.getCaseBankAccount();
			 //还款银行账户对象
			 BankAccount account=null;
			 if(ObjectHelper.isNotEmpty(caseBankAccount)){
		           for(BankAccount bank : caseBankAccount){
		        	   if(bank.getAccountType().equals(BankAccount.REPAYMENT))
			               //还款账户
			        	   account=bank;
		               }
		    }
			 //收款银行账户对象
			 BankAccount account1=null;
			 if(ObjectHelper.isNotEmpty(caseBankAccount)){
		           for(BankAccount bank : caseBankAccount){
		        	   if(bank.getAccountType().equals(BankAccount.RECEIVABLES))
			               //还款账户
			        	   account1=bank;
		               }
		    }
			//产品对象
			Product product=productRepository.findOne(caseApply.getProductSubtypeId());
			//机构产品费用对象l列表
			List<PrCostItem> prCostItems=prCostItemRepository.findByProductId(product.getId());
			//机构产品费用对象
			PrCostItem prCostItem=null;
			if(prCostItems!=null&&prCostItems.size()>0)
			{
				prCostItem=prCostItemRepository.findByProductId(product.getId()).get(0);
			}
			//还款计划基本信息
			ReceivableInfo receivableInfo=caseApply.getReceivableInfo();
			//字段容器
			Map<String,Object> columnMap=getExportColumns(caseApply.getId());
			//1机构代码
			targetStr.append("01300"+separator);
			//2合同号
			targetStr.append(columnMap.get("contractNo")+separator);
			//3申请地点?????
//			if(CED.loadEmployeeById(loanApply.getEmpId()).getDepartmentCd()!=null&&CED.findCompanyByDepartMentCd(CED.loadEmployeeById(loanApply.getEmpId()).getDepartmentCd())!=null)
//			{
//				targetStr.append(CED.findCompanyByDepartMentCd(CED.loadEmployeeById(loanApply.getEmpId()).getDepartmentCd()).getRegion()+separator);
//
//			}
//			else
//			{
//				targetStr.append(""+separator);
//			}
			targetStr.append(""+separator);
			//4申请号
			targetStr.append(loanApply.getLoanApplyCode()+separator);
			//5渠道来源
			targetStr.append("外贸信托"+separator);
			//6证件类型
			targetStr.append("0"+separator);
			//7证件号码
			targetStr.append(beforeCustomer.getCredentialNo()+separator);
			//8姓名
			targetStr.append(beforeCustomer.getCustomerName()+separator);
			//9联系电话
			targetStr.append(""+separator);
			//10移动电话
			if(mobileNum==null)
			{
				targetStr.append(""+separator);
			}
			else
			{
				targetStr.append(mobileNum.getPhoneNumber()+separator);
			}
			//11邮政编码
			targetStr.append(""+separator);
			//12通讯地址
			if(homeAdress==null)
			{
				targetStr.append(""+separator);
			}
			else
			{
				targetStr.append(homeAdress.getAddress()+separator);
			}
			//13申请用途
			targetStr.append(caseApply.getCapitalUseFor()+separator);
			//14合同金额
			targetStr.append(columnMap.get("contractAmount")+separator);
			//15实付金额
			targetStr.append(loanApply.getQuasiLoanAmount()+separator);
			//16申请币种
			targetStr.append("01"+separator);
			//17申请期限
			String unit=caseApply.getApplyTermUnit();
			Integer num=caseApply.getApplyTerm();
			targetStr.append(caseApply.getMechanismCode()+separator);
			if(unit.equals(CaseApply.DATEUNIT_YEAR))
			{
				num=num*12;
			}
			else if(unit.equals(CaseApply.DATEUNIT_DAY))
			{
				num=num/30+1;
			}
			targetStr.append(num+separator);
			//18还款帐户类型
			targetStr.append("0200"+separator);
			//19还款帐号
			if(account==null)
			{
				targetStr.append(""+separator);
			}
			else
			{
				targetStr.append(account.getBankNo()+separator);
			}
			//20还款方式
			targetStr.append(CED.loadSimpleCodeByFullCode(caseApply.getRepayMethod()).getSysCode()+separator);
			//21扣款日类型
			targetStr.append("02"+separator);
			//22扣款日类别
			targetStr.append(""+separator);
			//23扣款日期
			targetStr.append(""+separator);
			//24婚姻状况
			targetStr.append(CED.loadSimpleCodeByFullCode(personalCustomer.getMaritalStatus()).getSysCode()+separator);
			//25学历
			targetStr.append(CED.loadSimpleCodeByFullCode(personalCustomer.getDegree()).getSysCode()+separator);
			//26户籍
			targetStr.append(""+separator);
			//27个人月收入(年收入/12)
			BigDecimal yearIncom=personalCustomer.getAnnualIncomeAmmount();
			if(yearIncom==null)
			{
				targetStr.append(""+separator);
			}
			else
			{
				targetStr.append(yearIncom.divide(new BigDecimal(12),2)+separator);
			}
			//28家庭住址
			targetStr.append(homeAdress.getAddress()+separator);
			//29家庭邮编
			targetStr.append(""+separator);
			//30住宅电话
			targetStr.append(""+separator);
			//31经办机构
			targetStr.append(caseApply.getMechanismName()+separator);
			//32缴费方式
			targetStr.append("01"+separator);
			//33贷款类型
			targetStr.append("04"+separator);
			//34借款人类型
			targetStr.append("03"+separator);
			//35产品编号
			targetStr.append(product.getProductCode()+separator);
			//36产品名称
			targetStr.append(product.getProductName()+separator);
			//37手续费
			targetStr.append("0"+separator);
			//38手续费率
			targetStr.append("0"+separator);
			//39利率(月)(年/12,日*30)
			BigDecimal applyRate=caseApply.getApplyRate();
			String rateUnit=caseApply.getApplyRateUnit();
			if(ReceivableInfo.RECEIVABLEINFO_DAY.equals(rateUnit))
			{
				applyRate=applyRate.divide(new BigDecimal(30),4);
			}
			else if(ReceivableInfo.RECEIVABLEINFO_YEAR.equals(rateUnit))
			{
				applyRate=applyRate.multiply(new BigDecimal(12));
			}
			targetStr.append(applyRate+separator);
			//40提前还款违约金比率
			targetStr.append("0"+separator);
			//41罚息率
			if(receivableInfo==null)
			{
				targetStr.append(""+separator);
			}
			else
			{
				BigDecimal overdueDailyRate=receivableInfo.getOverdueDailyRate();
				String overdueDailyRateUnit=receivableInfo.getOverdueDailyRateUnit();
				if("YWDM0011903".equals(overdueDailyRateUnit))
				{
					overdueDailyRate=overdueDailyRate.multiply(new BigDecimal(30));
				}
				else if("YWDM0011901".equals(overdueDailyRateUnit))
				{
					overdueDailyRate=overdueDailyRate.divide(new BigDecimal(12),4);
				}
				targetStr.append(overdueDailyRate+separator);
			}
			//42履行担保天数
			targetStr.append("0"+separator);
			//43服务费
			targetStr.append("0"+separator);
			//44服务费率
			targetStr.append("0"+separator);
			//45担保费
			targetStr.append(""+separator);
			//46担保费率
			targetStr.append(""+separator);
			//47银行代码
			if(account==null)
			{
				targetStr.append(""+separator);
			}
			else
			{
				targetStr.append(account.getBankCode()+separator);
			}
			//48开户省市
			targetStr.append(""+separator);
			//49费用项一
			targetStr.append(""+separator);
			//50费用项二
			targetStr.append(""+separator);
			//51费用项三
			targetStr.append(""+separator);
			//52费用项四
			targetStr.append(""+separator);
			//53费用项五
			targetStr.append(""+separator);
			//54放款日期
			if(loanApply.getStatus().equals("3"))
			{
				Set<LoanRecord> recordList=loanApply.getLoanRecords();
				for(LoanRecord record : recordList)
				{
					targetStr.append(record.getActualDate()+separator);
				}
			}
			else
			{
				targetStr.append(""+separator);
			}
			//55放款账户类型
			targetStr.append("11"+separator);
			//56放款银行代码
			if(account1!=null)
			{
				targetStr.append(account1.getBankCode()+separator);
			}
			else
			{
				targetStr.append(""+separator);
			}
			//57放款账户名称
			if(account1!=null)
			{
				targetStr.append(account1.getCardholderName()+separator);
			}
			else
			{
				targetStr.append(""+separator);
			}
			//57放款账户帐号
			if(account1!=null)
			{
				targetStr.append(account1.getBankAccount()+separator);
			}
			else
			{
				targetStr.append(""+separator);
			}
			
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		return targetStr.toString().replaceAll("\\|null\\|","\\|\\|");
	}
	/**
	 * @Title: getExportColumns 
	 * @Description: 查询导出准放款信息的部分字段信息
	 * @author huangwei 
	 * @param caseApplyId
	 */
	private  Map<String, Object> getExportColumns(String caseApplyId){
		return loanApplyRepository.getExportColumns(caseApplyId);
	}
	

}
