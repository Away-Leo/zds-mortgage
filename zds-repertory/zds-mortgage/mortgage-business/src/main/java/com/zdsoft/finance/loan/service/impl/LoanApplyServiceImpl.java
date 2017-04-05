package com.zdsoft.finance.loan.service.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.Gson;
import com.zdsoft.essential.client.service.CED;
import com.zdsoft.essential.dto.emp.EmpDto;
import com.zdsoft.essential.dto.permission.DataOperPermDto;
import com.zdsoft.finance.base.service.impl.BaseServiceImpl;
import com.zdsoft.finance.busiform.entity.BusiForm;
import com.zdsoft.finance.busiform.entity.BusiFormStatus;
import com.zdsoft.finance.busiform.service.BusiFormService;
import com.zdsoft.finance.casemanage.receivablePlan.entity.BankAccount;
import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.finance.common.base.QueryObj;
import com.zdsoft.finance.common.enums.busiform.ApplyModelTypeEnum;
import com.zdsoft.finance.loan.entity.LoanApply;
import com.zdsoft.finance.loan.repository.LoanApplyRepository;
import com.zdsoft.finance.loan.service.LoanApplySerivce;
import com.zdsoft.finance.marketing.entity.CaseApply;
import com.zdsoft.finance.marketing.service.CaseApplyService;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.Pageable;
import com.zdsoft.framework.core.common.util.ObjectHelper;

import io.netty.util.internal.StringUtil;
/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * 
 * @Title: LoanApplyServiceImpl.java
 * @ClassName: LoanApplyServiceImpl
 * @Description: 放款申请ServiceImpl
 * @author huangwei
 * @date 2017年2月14日 上午10:37:26
 * @version V1.0
 */
@Service
public class LoanApplyServiceImpl extends BaseServiceImpl<LoanApply, CustomRepository<LoanApply, String>>
		implements LoanApplySerivce {
	@Autowired
	LoanApplyRepository loanApplyRepository;
	@Autowired
	private CaseApplyService caseApplyService;
	@Autowired
	private CED CED;
	@Autowired
	private BusiFormService busiFormService;
	/**
	 * Title: getCaseList   
	 * Description: 获取案件列表
	 * @param pageable
	 * @param queryObjs
	 * @return
	 * @throws Exception   
	 * @see com.zdsoft.finance.loan.service.LoanApplySerivce#getCaseList(com.zdsoft.framework.core.common.page.Pageable, java.util.List)
	 */
	 public  Page<Map<String,Object>> getCaseList(Pageable pageable,  List<QueryObj> queryObjs,DataOperPermDto dataOperPermDto) throws Exception {
		 	StringBuffer dataAuth = this.developmentManagerDataAuth(dataOperPermDto, "c");
		    Page<Map<String, Object>> caseApplyPage = loanApplyRepository.getCaseList(pageable, queryObjs,dataAuth);
		    //计算请款金额和放款金额
			for(Map<String,Object> caseApply : caseApplyPage.getRecords())
			{
				String caseId=caseApply.get("ID").toString();
				//请款总金额
				caseApply.put("loanApplyAmount",getCaseLoanApplyNum(caseId).toString());
				//计算贷款天数,转换日期格式
				if(caseApply.get("CONTRACTSTARTDATE")!=null&&caseApply.get("CONTRACTENDDATE")!=null)
				{
					String strDate=caseApply.get("CONTRACTSTARTDATE").toString();
					String endDate=caseApply.get("CONTRACTENDDATE").toString();
					SimpleDateFormat format2 =new SimpleDateFormat("yyyyMMdd");
					SimpleDateFormat format3 =new SimpleDateFormat("yyyyMMddHHmmss");
					SimpleDateFormat format1 =new SimpleDateFormat("yyyy-MM-dd");
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
					caseApply.put("days",daysOfTwo(strD,endD));
					caseApply.put("CONTRACTSTARTDATE", format1.format(strD));
					caseApply.put("CONTRACTENDDATE", format1.format(endD));
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
	  * @Title: getCaseLoanApplyNum 
	  * @Description: 计算案件的请款金额之和
	  * @author huangwei 
	  * @param caseId
	  * @return
	  */
	 private BigDecimal getCaseLoanApplyNum(String caseId) {
		 //找到案件对应的所有请款
		 List<LoanApply> caseIdLoanApply=loanApplyRepository.findByCaseApplyId(caseId);
		 BigDecimal loanAmount=new BigDecimal(0);
		 for(LoanApply loanApply : caseIdLoanApply)
		 {
			 if(!loanApply.getStatus().equals("5")&&!loanApply.getStatus().equals("6")&&!loanApply.getIsDeleted())
			 {
				 loanAmount=loanAmount.add(loanApply.getApplyAmount());
			 }
		 }
		 return loanAmount;
	 }
	/**
	 * Title: getLoanApplyForm 
	 * Description: 获取放款申请表单信息   
	 * @param caseId
	 * @return
	 * @throws Exception   
	 * @see com.zdsoft.finance.loan.service.LoanApplySerivce#getLoanApplyForm(java.lang.String)
	 */
	 public Map<String,Object> getLoanApplyForm(String caseId) throws Exception{
		 Map<String,Object> apply=new HashMap<String,Object>();
		 //获取银行信息
		 CaseApply caseApply=caseApplyService.findOne(caseId);
		 List<BankAccount> caseBankAccount = caseApply.getCaseBankAccount();
		 if(ObjectHelper.isNotEmpty(caseBankAccount)){
	            for(BankAccount bank : caseBankAccount){
	                //收款账户
	                if(bank.getAccountType()==0){
	                	apply.put("receiveBankAccountName", bank.getCardholderName());
	                	apply.put("receiveBankName", bank.getBankAccount());
		           		apply.put("receiveBankAccount", bank.getBankNo());
		           		break;
	                }
	            }
	        }
		 //请款金额
		 apply.put("applyAmount", caseApply.getApplyAmount());
		 apply.put("productId", caseApply.getProductSubtypeId());
		 apply.put("ID", caseApply.getId());
		 //获取员工信息
		apply.put("applyPerson", CED.getLoginUser().getEmpNm());
		apply.put("empId",  CED.getLoginUser().getId());
		apply.put("deptId", CED.getLoginUser().getDepartmentId());
		apply.put("applyPersonDep", CED.getLoginUser().getDepartmentName());
		Date date=new Date();
		apply.put("applyDate",new SimpleDateFormat("yyyy-MM-dd HH:mm").format(date));
		apply.put("applyOrg", CED.getLoginUser().getOrgNm());
		apply.put("branchId", CED.getLoginUser().getOrgId());
		apply.put("company", CED.getLoginUser().getCompanyNm());
		apply.put("companyId", CED.getLoginUser().getCompanyId());
		apply.put("loanApplyCode", "DH"+new Date().getTime());
		apply.put("caseApplyId", caseId);
		return apply;
	 }
	 /**
	  * Title: getLoanApplyCase   
	  * Description: 获取请款申请相关的案件信息  
	  * @param paramMap
	  * @return
	  * @throws Exception   
	  * @see com.zdsoft.finance.loan.service.LoanApplySerivce#getLoanApplyCase(java.util.Map)
	  */
	 public Map<String,Object> getLoanApplyCase(Map<String,Object> paramMap) throws Exception{
		 Map<String,Object> caseObj=new HashMap<String,Object>();
		caseObj.put("CUSTOMERNAME", paramMap.get("CUSTOMERNAME"));
		caseObj.put("PRODUCTTYPENAME", paramMap.get("PRODUCTTYPENAME"));
		caseObj.put("PRODUCTSUBTYPENAME", paramMap.get("PRODUCTSUBTYPENAME"));
		caseObj.put("APPLYAMOUNT", paramMap.get("APPLYAMOUNT"));
		caseObj.put("CONTRACTSTARTDATE", paramMap.get("CONTRACTSTARTDATE"));
		caseObj.put("CONTRACTENDDATE", paramMap.get("CONTRACTENDDATE"));
		caseObj.put("DYNAMICRATE", paramMap.get("DYNAMICRATE"));
		caseObj.put("days", paramMap.get("days"));
		caseObj.put("loanApplyAmount", paramMap.get("loanApplyAmount"));
		return caseObj;
	 }
	 /**
	  * Title: submitLoanApply   
	  * Description:   提交请款申请
	  * @param param
	  * @throws Exception   
	  * @see com.zdsoft.finance.loan.service.LoanApplySerivce#submitLoanApply(java.util.Map)
	  */
	 @Transactional(propagation=Propagation.REQUIRED,rollbackFor=Exception.class) 
	 public String submitLoanApply(Map<String,String[]> param)throws Exception{
		 SimpleDateFormat saveFormat=new SimpleDateFormat("yyyyMMddHHmm");
		 Map<String,Object> paramConver=new HashMap<String,Object>();
		 LoanApply loanApply=new LoanApply();
		 //如果该申请已保存过，则只需修改请款金额和日期
		 if(!StringUtil.isNullOrEmpty(param.get("id")[0]))
		 {
			 loanApply=loanApplyRepository.findOne(param.get("id")[0]);
			 //设置请款金额
			 loanApply.setApplyAmount(new BigDecimal(param.get("applyAmount")[0]));
			 SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm");
			 //设置申请日期
			 loanApply.setApplyDate(Long.parseLong(saveFormat.format(format.parse(param.get("applyDate")[0]))));
		 }
		 else
		 {
			 for(String key:param.keySet())
			 {
				 if(key.equals("applyDate"))
				 {
					 SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm");
					 paramConver.put(key,Long.parseLong(saveFormat.format(format.parse(param.get(key)[0]))));
				 }
				 else
				 {
					 paramConver.put(key,param.get(key)[0]);
				 }
			 }
			 Gson gson=new Gson();
			 String paramJson=gson.toJson(paramConver);
			 loanApply=gson.fromJson(paramJson,LoanApply.class);
			 //设置放款金额为0
			 loanApply.setLoanAmount(new BigDecimal(0));
		 }
		 //设置状态为未提交审批
		 loanApply.setStatus("5");
		 //操作类型为保存或提交
	     String operate=param.get("operate")[0].toString();
		 if("submit".equals(operate)) 
		 {
			//设置状态为审批中
			 loanApply.setStatus("4");
		 }
		 //创建人和机构
		 loanApply.setCreateBy(CED.getLoginUser().getId());
		 loanApply.setCreateOrgCd(CED.getLoginUser().getOrgCd());
		 //保存放款申请信息
		 loanApply = this.saveOrUpdateEntity(loanApply);
		 //保存basiform
		 BusiForm busiForm = loanApply.getBusiForm();
		 CaseApply caseApply=caseApplyService.findOne(loanApply.getCaseApplyId());
        if(ObjectHelper.isEmpty(busiForm)){
            busiForm = new BusiForm();
        }
        busiForm.setBusinessEntityId(loanApply.getId());
        busiForm.setBusinessEntityName(LoanApply.class.getSimpleName());
        busiForm.setComponentsEntityId(loanApply.getCaseApplyId());
        busiForm.setComponentsEntityName(CaseApply.class.getSimpleName());
        //设置表单状态
        if("submit".equals(operate)) 
		 {
        	 busiForm.setFormStatus(BusiFormStatus.APPROVAL.value);
		 }
        else
        {
        	busiForm.setFormStatus(BusiFormStatus.DRAFT.value);
        }
        //放款申请：标题 案件号+主借人+产品      业务编号：案件号
        busiForm.setApplyTitle(caseApply.getCaseApplyCode()+param.get("CUSTOMERNAME")[0]+caseApply.getProductSubtypeName());
        busiForm.setBusinessCode(caseApply.getCaseApplyCode());
        //所属模块
        busiForm.setModelType(ApplyModelTypeEnum.LOAN_APPLY.value);
        busiForm = busiFormService.saveOrUpdateBusiForm(busiForm);
        
        loanApply.setBusiForm(busiForm);
        loanApply = this.saveOrUpdateEntity(loanApply);
        //sava为保存，submit为提交.如果为提交，则启动流程
        if("submit".equals(operate)) {
        	 //功能代码
            busiForm.setFunctionCode(LoanApply.processCode);
            //产品子类型id
            busiForm.setProductId(caseApply.getProductSubtypeId());
            //启动流程
            busiForm = busiFormService.startProcess(busiForm, null, null);
            
        }
        return loanApply.getId();
	 }
	/**
	 * 
	 * Title: getLoanApplyFormByBusiId  
	 * Description: 根据表单id获取表单信息  
	 * @param busiId
	 * @return
	 * @throws Exception   
	 * @see com.zdsoft.finance.loan.service.LoanApplySerivce#getLoanApplyFormByBusiId(java.lang.String)
	 */
	public Map<String,Object> getLoanApplyFormByBusiId(String busiId) throws Exception{
		 Map<String,Object> formMap=new HashMap<String,Object>();
		 //请款申请对象
		 LoanApply apply=loanApplyRepository.findOne(busiId);
		 //添加请款申请信息
		 formMap.put("loanApplyCode", apply.getLoanApplyCode());
		 formMap.put("applyAmount", apply.getLoanAmount());
		 //案件id
		 String caseId=apply.getCaseApplyId();
		 //获取案件相关信息
		 formMap.putAll(loanApplyRepository.getCaseDetailByCaseId(caseId));
		 //将键值变为大写
		 Set<String> keys=formMap.keySet();
		 Map<String,Object> caseMap=new HashMap<String,Object>();
		 for(String key : keys)
		 {
			 caseMap.put(key.toUpperCase(), formMap.get(key));
		 }
		 formMap.putAll(caseMap);
		 //获取银行信息
		 CaseApply caseApply=caseApplyService.findOne(caseId);
		//请款总金额
		 formMap.put("loanApplyAmount",getCaseLoanApplyNum(caseId).toString());
		 //产品id
		 formMap.put("productId", caseApply.getProductSubtypeId());
		 //案件id
		 formMap.put("caseApplyId", caseApply.getId());
		 List<BankAccount> caseBankAccount = caseApply.getCaseBankAccount();
		 if(ObjectHelper.isNotEmpty(caseBankAccount)){
	            for(BankAccount bank : caseBankAccount){
	                //收款账户
	                if(bank.getAccountType()==0){
	                	formMap.put("receiveBankAccountName", bank.getCardholderName());
	                	formMap.put("receiveBankName", bank.getBankAccount());
	                	formMap.put("receiveBankAccount", bank.getBankNo());
	                	break;
	                }
	            }
	        }
		 SimpleDateFormat format1 =new SimpleDateFormat("yyyy-MM-dd HH:mm");
		 SimpleDateFormat format =new SimpleDateFormat("yyyy-MM-dd");
		 SimpleDateFormat format2 =new SimpleDateFormat("yyyyMMddHHmmss");
		 SimpleDateFormat format3 =new SimpleDateFormat("yyyyMMdd");
		 SimpleDateFormat format4 =new SimpleDateFormat("yyyyMMddHHmm");
		 //获取申请人相关信息
		 EmpDto emp=CED.loadEmployeeById(apply.getEmpId());
		 formMap.put("applyPerson", emp.getEmpNm());
		 formMap.put("applyPersonDep", emp.getDepartmentName());
		 formMap.put("applyOrg",emp.getOrgNm());
		 formMap.put("company",emp.getCompanyNm());
		 //其他信息
		 formMap.put("applyDate",format1.format(format4.parse(apply.getApplyDate()+"")));
		 formMap.put("applyAmount",apply.getApplyAmount());
		//计算贷款天数
			if(formMap.get("contractStartDate")!=null&&formMap.get("contractEndDate")!=null)
			{
				String strDate=formMap.get("contractStartDate").toString();
				String endDate=formMap.get("contractEndDate").toString();
				Date strD=null;
				Date endD=null;
				if(strDate.length()==8)
				{
					strD=format3.parse(strDate);
				}
				else
				{
					strD=format2.parse(strDate);
				}
				if(endDate.length()==8)
				{
					endD=format3.parse(endDate);
				}
				else
				{
					endD=format2.parse(endDate);
				}
				formMap.put("days",daysOfTwo(strD,endD));
				formMap.put("CONTRACTSTARTDATE", format.format(strD));
				formMap.put("CONTRACTENDDATE", format.format(endD));
			}
		 return formMap;
	 }
	
	 
	 /**
	  * @Title: loanApplyProcessEditSave 
	  * @Description: 放款流程审批保存事件
	  * @author huangwei 
	  * @param busiId
	  * @param applyAmount
	  */
	 public void loanApplyProcessEditSave(String busiId,String applyAmount)throws Exception{
		//请款申请对象
		 LoanApply apply=loanApplyRepository.findOne(busiId);
		 apply.setApplyAmount(new BigDecimal(applyAmount));
		 loanApplyRepository.updateEntity(apply);
	 }
	 /**
	 * @Title: findByCaseApplyId 
	 * @Description: 根据案件id查找请款
	 * @author huangwei 
	 * @param caseId
	 * @return
	 */
	public List<LoanApply> findByCaseApplyId(String caseId){
		return loanApplyRepository.findByCaseApplyId(caseId);
	}
 
}
