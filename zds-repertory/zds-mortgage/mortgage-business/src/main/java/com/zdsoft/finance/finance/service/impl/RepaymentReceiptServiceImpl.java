package com.zdsoft.finance.finance.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.zdsoft.essential.dto.emp.EmpDto;
import com.zdsoft.essential.dto.permission.DataOperPermDto;
import com.zdsoft.finance.base.service.impl.BaseServiceImpl;
import com.zdsoft.finance.capital.entity.CreditEntrust;
import com.zdsoft.finance.capital.service.CreditEntrustService;
import com.zdsoft.finance.casemanage.limitApply.entity.CaseLimitApply;
import com.zdsoft.finance.casemanage.limitApply.service.CaseLimitApplyService;
import com.zdsoft.finance.casemanage.receivablePlan.entity.BankAccount;
import com.zdsoft.finance.casemanage.receivablePlan.entity.ReceivablePlan;
import com.zdsoft.finance.casemanage.receivablePlan.service.BankAccountService;
import com.zdsoft.finance.casemanage.receivablePlan.service.ReceivablePlanService;
import com.zdsoft.finance.common.base.QueryObj;
import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.finance.common.utils.BigDecimalCalculateTwo;
import com.zdsoft.finance.common.utils.TimeUtil;
import com.zdsoft.finance.customer.entity.BeforeCustomer;
import com.zdsoft.finance.finance.entity.CustomerReceivable;
import com.zdsoft.finance.finance.entity.RepaymentAmountAllot;
import com.zdsoft.finance.finance.entity.RepaymentReceipt;
import com.zdsoft.finance.finance.repository.RepaymentReceiptRepostory;
import com.zdsoft.finance.finance.service.CustomerReceivableService;
import com.zdsoft.finance.finance.service.RepaymentAmountAllotService;
import com.zdsoft.finance.finance.service.RepaymentReceiptService;
import com.zdsoft.finance.marketing.entity.CaseApply;
import com.zdsoft.finance.marketing.entity.CaseApplyBeforeCustomer;
import com.zdsoft.finance.marketing.entity.CaseApplyStageEnumSimpleCodeEnum;
import com.zdsoft.finance.marketing.service.CaseApplyBeforeCustomerService;
import com.zdsoft.finance.marketing.service.CaseApplyService;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.PageRequest;
import com.zdsoft.framework.core.common.util.ObjectHelper;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: RepaymentReceiptServiceImpl.java 
 * @ClassName: RepaymentReceiptServiceImpl 
 * @Description: 还款-收款单接口实现
 * @author jincheng 
 * @date 2017年2月17日 下午5:06:29 
 * @version V1.0
 */
@Service("repaymentReceiptService")
public class RepaymentReceiptServiceImpl extends BaseServiceImpl<RepaymentReceipt, RepaymentReceiptRepostory>  implements RepaymentReceiptService{

	@Autowired   
	private com.zdsoft.essential.client.service.CED CED;
	
	@Autowired  
	private RepaymentAmountAllotService repaymentAmountAllotService;
	
	@Autowired  
	private CaseApplyService caseApplyService;
	
	@Autowired
	private CustomerReceivableService customerReceivableService;
	
	@Autowired
	private BankAccountService bankAccountService;
	
	@Autowired
	private CaseLimitApplyService caseLimitApplyService;
	
	@Autowired
	private CreditEntrustService creditEntrustService;
	
	@Autowired
	private CaseApplyBeforeCustomerService caseApplyBeforeCustomerService;
	
	@Autowired
	private ReceivablePlanService  receivablePlanService;
	
//	@Autowired
//	private RepaymentUseAmountService repaymentUseAmountService;
//	
//	@Autowired
//	private FeeInfomationService feeInfomationService;
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	public RepaymentReceipt saveOrUpdateRepaymentReceipt(RepaymentReceipt entity, String receiptList) throws Exception {
		EmpDto emp=CED.getLoginUser();
		if(ObjectHelper.isNotEmpty(entity.getId())){
			RepaymentReceipt old_entity=this.findOne(entity.getId());
			BeanUtils.copyProperties(entity, old_entity,new String[]{"createBy","createOrgCd","createTime","orgId","orgName"});
			old_entity.setUpdateBy(emp.getEmpCd());
			old_entity.setUpdateOrgCd(emp.getOrgCd());
			old_entity.setUpdateTime(new Date());
			if (ObjectHelper.isEmpty(old_entity.getStatus())) {
				old_entity.setStatus(0);
			}
			entity=this.updateEntity(old_entity);
		}else{
			entity.setCreateTime(new Date());
			entity.setCreateBy(emp.getEmpCd());
			entity.setCreateOrgCd(emp.getOrgCd());
			entity.setOrgId(emp.getOrgId());
			entity.setOrgName(emp.getOrgNm());
			if (ObjectHelper.isEmpty(entity.getStatus())) {
				entity.setStatus(0);
			}
			entity=this.saveEntity(entity);
		}
		
		if(1==entity.getReceiptType()){//判断是否是收款
			//删除以前的还款分配
			List<RepaymentAmountAllot> allotList=repaymentAmountAllotService.findByReceiptId(entity.getId());
			for(RepaymentAmountAllot allot:allotList){
				repaymentAmountAllotService.deleteRepaymentAmountAllot(allot.getId());
			}
			
			if("YWDM009402".equals(entity.getRecordMethod())){//自由录入
				List<RepaymentAmountAllot> jsonList=JSON.parseArray(receiptList, RepaymentAmountAllot.class);
				for(RepaymentAmountAllot allot:jsonList){
					if(!this.judgeRepaymentAmountGtZero(allot)){//判断实还是否大于0
						continue;
					}
					allot.setCaseApplyId(entity.getCaseApplyId());
					allot.setReceiptId(entity.getId());
					allot.setPaidType(1);
					repaymentAmountAllotService.saveEntity(allot);
				}
			}else{
				
//				List<RepaymentUseAmount> ruaList=repaymentUseAmountService.findByReceiptId(entity.getId());
				
//				if("YWDM009404".equals(entity.getRecordMethod())){
//					List<RepaymentReceipt> rrtList=this.findByCaseApplyIdAndReceiptTypeAndStatus(entity.getCaseApplyId(),2,2); 
//					for(RepaymentReceipt rrt:rrtList){
//						List<RepaymentUseAmount> ruaList2=repaymentUseAmountService.findByFeeAmountId(rrt.getId());
//						if(ObjectHelper.isEmpty(ruaList2)||ruaList2.size()==0){
//							RepaymentUseAmount rua1=new RepaymentUseAmount();
//							rua1.setCaseApplyId(entity.getCaseApplyId());
//							rua1.setFeeAmountId(rrt.getId());
//							rua1.setReceiptId(entity.getId());
//							rua1.setFeeAmount(rrt.getCollectionAmount());
//							rua1.setAmountType("YWDM009404");
//							rua1.setStatus(0);
//							repaymentUseAmountService.saveEntity(rua1);
//						}
//					}
//				}
//				
//				if("YWDM009403".equals(entity.getRecordMethod())){
//					List<FeeInfomation> feeList= feeInfomationService.findByCaseApplyId(entity.getCaseApplyId());
//					for(FeeInfomation fee:feeList){
//						if("FYDM000003".equals(fee.getFeeItem())){
//							List<RepaymentUseAmount> ruaList3=repaymentUseAmountService.findByFeeAmountId(fee.getId());
//							if(ObjectHelper.isEmpty(ruaList3)||ruaList3.size()==0){
//								RepaymentUseAmount rua2=new RepaymentUseAmount();
//								rua2.setCaseApplyId(entity.getCaseApplyId());
//								rua2.setFeeAmountId(fee.getId());
//								rua2.setReceiptId(entity.getId());
//								rua2.setFeeAmount(fee.getReceivedAmount());
//								rua2.setAmountType("YWDM009403");
//								rua2.setStatus(0);
//								repaymentUseAmountService.saveEntity(rua2);
//							}
//						}
//					}
//				}
				
				//重新写入还款分配(调用自动分配还款金额的方法)
				List<RepaymentAmountAllot> objectList=repaymentAmountAllotService.getRepaymentAmountAllotList(entity);
				for(RepaymentAmountAllot allot:objectList){
					if(!this.judgeRepaymentAmountGtZero(allot)){//判断实还是否大于0
						continue;
					}
					allot.setCaseApplyId(entity.getCaseApplyId());
					allot.setReceiptId(entity.getId());
					allot.setPaidType(1);
					repaymentAmountAllotService.saveEntity(allot);
				}
			}
		}
		return entity;
	}
	
	/**
	 * @Title: judgeRepaymentAmountGtZero 
	 * @Description: 判断实收是否大于0
	 * @author jincheng 
	 * @param allot
	 * @return
	 */
	private boolean judgeRepaymentAmountGtZero(RepaymentAmountAllot allot) {
		boolean bool=false;
		BigDecimal currentPaidAmount=BigDecimal.ZERO;
		currentPaidAmount=currentPaidAmount.add(allot.getPaidDamages()).add(allot.getPaidInterestAmount()).add(allot.getPaidPenalty()).add(allot.getPaidPrincipalAmount()).add(allot.getPaidServiceFee());
		if(currentPaidAmount.compareTo(BigDecimal.ZERO)>0){
			bool=true;
		}
		return bool;
	}

	@Override
	public Page<Map<String, Object>> repaymentApplyList(PageRequest pageRequest, List<QueryObj> li,DataOperPermDto dataOperPermDto) throws BusinessException {
//		StringBuffer data_auth=this.developmentManagerDataAuth(dataOperPermDto, "mca");
		
			StringBuffer _sql=new StringBuffer();
			_sql.append(" select  ");
			_sql.append("  receipt.id,receipt.caseApplyId,receipt.billCode,receipt.recordMethod,receipt.receiptType,receipt.orgName,receipt.collectionAmount,receipt.colloectionCode,receipt.status,receipt.paidRepayDate,receipt.receipts,receipt.payer,  ");
			_sql.append("  mca.caseApplyCode,mca.productSubtypeName,mca.capitalSource, mca.Productsubtypeid, mca.Producttypeid, mca.Mechanismname,mca.developmentManagerName, ");
			_sql.append("  xx.PAIDPRINCIPALAMOUNT,xx.PAIDINTERESTAMOUNT,xx.PAIDPENALTY,xx.PAIDSERVICEFEE,xx.PAIDDAMAGES,xx.ovreDueDay, ");
			_sql.append("  pp.capitalistName,   ");//资金来源
			_sql.append("  cla.fundPlanName,cla.id fundPlanId,  ");//信托计划
			_sql.append("  cc.customerName ");
			_sql.append("  from fin_receipt receipt inner join mkt_case_apply mca  on mca.id=receipt.caseApplyId   ");
			_sql.append("  left join ( SELECT RECEIPTID,max(ovreDueDay) ovreDueDay,SUM (PAIDPRINCIPALAMOUNT) PAIDPRINCIPALAMOUNT,	SUM (PAIDINTERESTAMOUNT) PAIDINTERESTAMOUNT,SUM (PAIDPENALTY) PAIDPENALTY,SUM (PAIDSERVICEFEE) PAIDSERVICEFEE,SUM (PAIDDAMAGES) PAIDDAMAGES   ");
			_sql.append("  FROM FIN_AMOUNT_ALLOT GROUP BY RECEIPTID )xx  on xx.RECEIPTID=receipt.ID   ");
			_sql.append("  LEFT JOIN prd_product pp  on pp.id=mca.productSubtypeId   ");
			_sql.append("  left join case_after_customer cfc on cfc.caseApplyId=mca.id and cfc.joinType='YWDM0051036'  ");
			_sql.append("  left join cust_after_customer cc on cc.id=cfc.customerId  ");
			_sql.append("  LEFT JOIN case_limit_apply cla on cla.caseApplyId=mca.id ");
			_sql.append("  where 1=1 ");
//			_sql.append(data_auth);
			StringBuffer _extendSql=new StringBuffer();
			_extendSql.append(" order by receipt.createTime desc ");
			return this.customReposity.getListObjectBySql(pageRequest, li, _sql, _extendSql);
	}


	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	public void deleteRepaymentReceipt(String receiptId) throws Exception { 
		List<RepaymentAmountAllot> allotList=repaymentAmountAllotService.findByReceiptId(receiptId);
		for(RepaymentAmountAllot allot:allotList){
			repaymentAmountAllotService.deleteRepaymentAmountAllot(allot.getId());
		}
		this.customReposity.delete(receiptId);
	}


	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	public void updateStatus(String ids, Integer status) throws Exception { 
		String[] idArray=ids.split(",");
		for(String id:idArray){
			RepaymentReceipt rt=this.findOne(id);
			if(2==status&&1==rt.getReceiptType()){//还款通过
				rt.setSuccDate(TimeUtil.getCurrentDateInteger().longValue());//设置集团复核通过时间
				List<RepaymentAmountAllot> allotList=repaymentAmountAllotService.findByReceiptId(rt.getId());
				BigDecimal paidPrincipalAmount=BigDecimal.ZERO;
				for(RepaymentAmountAllot allot:allotList){
					paidPrincipalAmount=BigDecimalCalculateTwo.add(paidPrincipalAmount, allot.getPaidPrincipalAmount());
					allot.setIsReview(true);
					allot.setPaidRepayDate(rt.getPaidRepayDate());
					repaymentAmountAllotService.updateEntity(allot);
					if(this.judgeRepaymentAmountIsSettlement(allot)){
						ReceivablePlan plan=receivablePlanService.findOne(allot.getPlanId());
						plan.setSettlement(true);
						receivablePlanService.updateEntity(plan);
					}
				}
				
				CaseApply caseApply=caseApplyService.findOne(rt.getCaseApplyId());
				caseApply.setCaseApplyBalance(caseApply.getCaseApplyBalance().subtract(paidPrincipalAmount));
				caseApplyService.updateEntity(caseApply);
				
				//调用跑批方法
				List<CaseApply> applyList=new ArrayList<CaseApply>();
				applyList.add(caseApply);
				customerReceivableService.caseApplyBatch(applyList, rt.getPaidRepayDate());
			}
			
			rt.setStatus(status);
			this.updateEntity(rt);
		}
	}

	/**
	 * @Title: judgeRepaymentAmountIsSettlement 
	 * @Description: 判断当期是否还清
	 * @author jincheng 
	 * @param allot
	 * @return
	 */
	private boolean judgeRepaymentAmountIsSettlement(RepaymentAmountAllot allot) {
		boolean bool=false;
		if(allot.getDcurrentPlanPenalty().compareTo(allot.getCurrentPaidPenalty())<=0
			&&allot.getDplanDamages().compareTo(allot.getPaidDamages())<=0	
			&&allot.getDplanInterestAmount().compareTo(allot.getPaidInterestAmount())<=0	
			&&allot.getDplanPenalty().compareTo(allot.getPaidPenalty())<=0	
			&&allot.getDplanPrincipalAmount().compareTo(allot.getPaidPrincipalAmount())<=0	
			&&allot.getDplanServiceFee().compareTo(allot.getPaidServiceFee())<=0	
				){
			bool=true;
		}
		return bool;
	}

	/**
	 * <p>Description: 在贷阶段-YWDM009223</p>   
	 * @param path
	 * @return
	 * @throws Exception   
	 */
	@Override
	public XSSFWorkbook exportReceivableMonthToExcel(String path) throws Exception {
		   File file=new File(path);
		   FileInputStream inputStream=new FileInputStream(file);
		   XSSFWorkbook wb = new XSSFWorkbook(inputStream);    
	       XSSFSheet sheet = wb.getSheetAt(0);
	       XSSFCellStyle style = wb.createCellStyle();    
	       style.setAlignment(HSSFCellStyle.ALIGN_CENTER); 
	       List<CaseApply> caseApplyList=caseApplyService.findByStage(CaseApplyStageEnumSimpleCodeEnum.LOAN_ALREADY.value);
		   for(int i=0;i<caseApplyList.size();i++){
				CaseApply ca=caseApplyList.get(i);
				XSSFRow row = sheet.createRow(2+i);    
				row.createCell(0).setCellValue(this.transToString(ca.getDevelopmentDepartmentName()));//机构
	            row.createCell(1).setCellValue(this.transToString(ca.getCaseApplyCode())); //案件号  
	          //获取主借人
				List<BeforeCustomer> customerList=caseApplyBeforeCustomerService.findCustomerByCaseApplyIdAndJoinType(ca.getId(), CaseApplyBeforeCustomer.MAIN_BORROW);
	            row.createCell(2).setCellValue(this.transToString(ObjectHelper.isNotEmpty(customerList)?customerList.get(0).getCustomerName():"")); //主借人  
	            row.createCell(3).setCellValue(this.transToString(ca.getDevelopmentDepartmentName())); //部门 
	            row.createCell(4).setCellValue(this.transToString(ca.getDevelopmentManagerName())); //业务员  
	            row.createCell(5).setCellValue(this.transToString(ca.getProductTypeName())); //一级分类  
	            row.createCell(6).setCellValue(this.transToString(ca.getProductSubtypeName())); //二级分类 
	            row.createCell(7).setCellValue(this.transToString("")); //委贷银行（支行）
	            CustomerReceivable cr=customerReceivableService.findByCaseApplyIdAndIsEffect(ca.getId(), true);
	            if(ObjectHelper.isNotEmpty(cr)){
		            row.createCell(8).setCellValue(this.transToString(cr.getPeriods())); //期次
		            row.createCell(9).setCellValue(this.transToString(cr.getPlanRepayDate())); //应还日期  
		            row.createCell(10).setCellValue(this.transToString(cr.getPlanPrincipalAmount()+"")); //本金
		            row.createCell(11).setCellValue(this.transToString(cr.getPlanServiceFee()+"")); //服务费  
		            row.createCell(12).setCellValue(this.transToString(cr.getPlanInterestAmount()+"")); //利息  
		            row.createCell(13).setCellValue(this.transToString(cr.getRemainPrincipal()+"")); //剩余本金  -----------------------------
		            row.createCell(14).setCellValue(this.transToString(cr.getPaidPrincipalAmount()+"")); //已还本金
		            row.createCell(15).setCellValue(this.transToString(cr.getPaidServiceFee()+"")); //已还服务费
		            row.createCell(16).setCellValue(this.transToString(cr.getPaidInterestAmount()+"")); //已还利息
		            row.createCell(17).setCellValue(this.transToString(cr.getPlanInterestAmount().add(cr.getPaidInterestAmount())+"")); //待还利息  
		            row.createCell(18).setCellValue(this.transToString(cr.getPlanServiceFee().add(cr.getPaidServiceFee())+"")); //待还服务费
		            row.createCell(19).setCellValue(this.transToString(cr.getPlanPrincipalAmount().add(cr.getPaidPrincipalAmount()).add(cr.getPlanInterestAmount()).subtract(cr.getPaidInterestAmount())+"")); //待还本息
		            row.createCell(20).setCellValue(this.transToString("")); //结清本息日期----------------------
		            row.createCell(21).setCellValue(this.transToString(cr.getPlanPenalty()+"")); //逾期罚息
		            row.createCell(22).setCellValue(this.transToString(cr.getReductionPenalty()+"")); //减免 
		            row.createCell(23).setCellValue(this.transToString(cr.getPaidPenalty()+"")); //实收填充  
		            row.createCell(24).setCellValue(this.transToString(cr.getPlanPenalty().subtract(cr.getPaidPenalty()).subtract(cr.getReductionPenalty())+"")); //罚息未收
		            row.createCell(25).setCellValue(this.transToString(cr.getCurrentPlanPenalty()+"")); //当前罚息  
		            row.createCell(26).setCellValue(this.transToString(cr.getCurrentPaidPenalty()+"")); //实收填充
		            row.createCell(27).setCellValue(this.transToString(cr.getCurrentPlanPenalty().subtract(cr.getCurrentPaidPenalty())+"")); //当前罚息未收
		            row.createCell(28).setCellValue(this.transToString(cr.getPlanDamages()+"")); //违约金应收
		            row.createCell(29).setCellValue(this.transToString(cr.getReductionDamages()+"")); //减免
		            row.createCell(30).setCellValue(this.transToString(cr.getPaidDamages()+"")); //实收  
		            row.createCell(31).setCellValue(this.transToString(cr.getPlanDamages().subtract(cr.getPaidDamages()).subtract(cr.getReductionDamages())+"")); //未收
	            }
	            
	            BankAccount ba=bankAccountService.findByCaseApplyIdAndAccountType(ca.getId(), BankAccount.REPAYMENT);
	            if(ObjectHelper.isNotEmpty(ba)){
	            	row.createCell(32).setCellValue(this.transToString(ba.getBankAccount())); //开户行  
	            	row.createCell(33).setCellValue(this.transToString(ba.getBankCode())); //银行代码  
	            	row.createCell(34).setCellValue(this.transToString(ba.getCardholderName())); //账户名  
	            	row.createCell(35).setCellValue(this.transToString(ba.getBankNo())); //账户  
	            }
	            
	            List<CaseLimitApply> claList= caseLimitApplyService.findByCaseApplyId(ca.getId());
	            if(ObjectHelper.isNotEmpty(claList)){
	            	CreditEntrust cet= creditEntrustService.findOne(claList.get(0).getFundPlanId());
	            	if(ObjectHelper.isNotEmpty(cet)){
	            		row.createCell(36).setCellValue(this.transToString(cet.getClearingAccount())); //清分账号  
	            	}
	            }
			}
		return wb;
	}
	
	private String transToString(Object obj){
		if(ObjectHelper.isEmpty(obj)){
			return "";
		}
		return obj.toString();
	}


	@Override
	public List<RepaymentReceipt> findByCaseApplyIdAndReceiptTypeAndStatus(String caseApplyId, Integer receiptType,	Integer status) {
		return this.customReposity.findByCaseApplyIdAndReceiptTypeAndStatus( caseApplyId,  receiptType,	status);
	}

	@Override
	public XSSFWorkbook exportRepaymentReceiptExcel(String receipts)throws Exception {
		 XSSFWorkbook wb = new XSSFWorkbook();    
	       XSSFSheet sheet = wb.createSheet();
	       XSSFRow row_ = sheet.createRow(0);    
	       row_.createCell(0).setCellValue("机构");
	       row_.createCell(1).setCellValue("单据号");
	       row_.createCell(2).setCellValue("收据号");
	       row_.createCell(3).setCellValue("案件号");
	       row_.createCell(4).setCellValue("主借人");
	       row_.createCell(5).setCellValue("收款总额");
	       row_.createCell(6).setCellValue("本金");
	       row_.createCell(7).setCellValue("服务费");
	       row_.createCell(8).setCellValue("利息");
	       row_.createCell(9).setCellValue("罚息");
	       row_.createCell(10).setCellValue("违约金");
	       row_.createCell(11).setCellValue("收款日期");
	       row_.createCell(12).setCellValue("经手人");
	       row_.createCell(13).setCellValue("产品");
	       XSSFCellStyle style = wb.createCellStyle();    
	       style.setAlignment(HSSFCellStyle.ALIGN_CENTER); 
	       
	       StringBuffer _sql=new StringBuffer();
			_sql.append(" select  ");
			_sql.append("  receipt.id,receipt.caseApplyId,receipt.billCode,receipt.receiptType,receipt.orgName,receipt.collectionAmount,receipt.colloectionCode,receipt.status,receipt.paidRepayDate,receipt.receipts,receipt.payer,  ");
			_sql.append("  mca.caseApplyCode,mca.productSubtypeName,mca.capitalSource, mca.Productsubtypeid, mca.Producttypeid, mca.Mechanismname, ");
			_sql.append("  xx.PAIDPRINCIPALAMOUNT,xx.PAIDINTERESTAMOUNT,xx.PAIDPENALTY,xx.PAIDSERVICEFEE,xx.PAIDDAMAGES, ");
			_sql.append("  cc.customerName ");
			_sql.append("  from fin_receipt receipt inner join mkt_case_apply mca  on mca.id=receipt.caseApplyId   ");
			
			_sql.append("  left join ( SELECT RECEIPTID,SUM (PAIDPRINCIPALAMOUNT) PAIDPRINCIPALAMOUNT,	SUM (PAIDINTERESTAMOUNT) PAIDINTERESTAMOUNT,SUM (PAIDPENALTY) PAIDPENALTY,SUM (PAIDSERVICEFEE) PAIDSERVICEFEE,SUM (PAIDDAMAGES) PAIDDAMAGES   ");
			_sql.append("  FROM FIN_AMOUNT_ALLOT GROUP BY RECEIPTID )xx  on xx.RECEIPTID=receipt.ID   ");
			
			_sql.append("  left join case_after_customer cfc on cfc.caseApplyId=mca.id and cfc.joinType='YWDM0051036'  ");
			_sql.append("  left join cust_after_customer cc on cc.id=cfc.customerId  ");
			_sql.append("  where 1=1 order by receipt.createTime desc ");
			
			List<Map<String,Object>> mapList=this.customReposity.findListMapByCondition(_sql.toString(), new HashMap<String, Object>());
			
		   for(int i=0;i<mapList.size();i++){
			    Map<String,Object> map=mapList.get(i);
			    XSSFRow row = sheet.createRow(1+i);    
				row.createCell(0).setCellValue(this.transToString(map.get("ORGNAME")));
	            row.createCell(1).setCellValue(this.transToString(map.get("BILLCODE")));
	            row.createCell(2).setCellValue(this.transToString(map.get("COLLOECTIONCODE"))); 
	            row.createCell(3).setCellValue(this.transToString(map.get("CASEAPPLYCODE"))); 
	            row.createCell(4).setCellValue(this.transToString(map.get("CUSTOMERNAME"))); 
	            row.createCell(5).setCellValue(this.transToString(map.get("COLLECTIONAMOUNT"))); 
	            row.createCell(6).setCellValue(this.transToString(map.get("PAIDPRINCIPALAMOUNT"))); 
	            row.createCell(7).setCellValue(this.transToString(map.get("PAIDSERVICEFEE")));
	            row.createCell(8).setCellValue(this.transToString(map.get("PAIDINTERESTAMOUNT")));
	            row.createCell(9).setCellValue(this.transToString(map.get("PAIDPENALTY")));
	            row.createCell(10).setCellValue(this.transToString(map.get("PAIDDAMAGES")));
	            row.createCell(11).setCellValue(this.transToString(map.get("PAIDREPAYDATE")));
	            row.createCell(12).setCellValue(this.transToString(map.get("RECEIPTS")));
	            row.createCell(13).setCellValue(this.transToString(map.get("PRODUCTSUBTYPENAME")));
			}
		return wb;
	}
	
}
