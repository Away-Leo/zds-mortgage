package com.zdsoft.finance.marketing.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zdsoft.bpm.dto.ProcessInstanceDto;
import com.zdsoft.bpm.service.client.BPM;
import com.zdsoft.essential.client.service.CED;
import com.zdsoft.essential.dto.emp.EmpDto;
import com.zdsoft.finance.base.service.impl.BaseServiceImpl;
import com.zdsoft.finance.busiform.entity.BusiForm;
import com.zdsoft.finance.busiform.entity.BusiFormStatus;
import com.zdsoft.finance.busiform.service.BusiFormService;
import com.zdsoft.finance.common.enums.busiform.ApplyModelTypeEnum;
import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.finance.customer.entity.BeforePersonalAssociation;
import com.zdsoft.finance.customer.entity.BeforePersonalCustomer;
import com.zdsoft.finance.customer.entity.BeforeWorkUnit;
import com.zdsoft.finance.customer.entity.Credit;
import com.zdsoft.finance.customer.entity.CreditAttachmentTypeEnum;
import com.zdsoft.finance.customer.entity.CustomerCreateType;
import com.zdsoft.finance.customer.service.BeforePersonalAssociationService;
import com.zdsoft.finance.customer.service.BeforePersonalCustomerService;
import com.zdsoft.finance.customer.service.BeforeWorkUnitService;
import com.zdsoft.finance.customer.service.BlanckListService;
import com.zdsoft.finance.customer.service.CreditService;
import com.zdsoft.finance.marketing.entity.BeforehandApply;
import com.zdsoft.finance.marketing.entity.CaseApply;
import com.zdsoft.finance.marketing.entity.CaseApplyBeforeCustomer;
import com.zdsoft.finance.marketing.entity.CaseApplyStageEnumSimpleCodeEnum;
import com.zdsoft.finance.marketing.entity.HouseProperty;
import com.zdsoft.finance.marketing.entity.PropertyOwner;
import com.zdsoft.finance.marketing.entity.TerminalCaseApprovalOpinion;
import com.zdsoft.finance.marketing.repository.BeforehandApplyRepository;
import com.zdsoft.finance.marketing.service.BeforehandApplyService;
import com.zdsoft.finance.marketing.service.CaseApplyBeforeCustomerService;
import com.zdsoft.finance.marketing.service.CaseApplyService;
import com.zdsoft.finance.marketing.service.HousePropertyService;
import com.zdsoft.finance.marketing.service.TerminalCaseApprovalOpinionService;
import com.zdsoft.finance.product.entity.ProcessConfig;
import com.zdsoft.finance.product.entity.Product;
import com.zdsoft.finance.product.entity.ProductRate;
import com.zdsoft.finance.product.service.ProcessConfigService;
import com.zdsoft.finance.product.service.ProductService;
import com.zdsoft.finance.risk.entity.HuifaRequest;
import com.zdsoft.finance.risk.huifa.service.BusinessDetailService;
import com.zdsoft.finance.risk.huifa.service.HuifaDetailService;
import com.zdsoft.finance.rule.enums.RuleApprovalFinal;
import com.zdsoft.finance.rule.service.RuleApprovalService;
import com.zdsoft.framework.core.common.exception.AppException;
import com.zdsoft.framework.core.common.util.DateHelper;
import com.zdsoft.framework.core.common.util.ObjectHelper;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: BeforehandApplyServiceImpl.java 
 * @ClassName: BeforehandApplyServiceImpl 
 * @Description: 案件预申请服务类
 * @author zhoushichao 
 * @date 2017年3月14日 下午6:05:19 
 * @version V1.0
 */
@Service("beforehandApplyService")
public class BeforehandApplyServiceImpl extends BaseServiceImpl<BeforehandApply, BeforehandApplyRepository> implements BeforehandApplyService{

	@Autowired
	CaseApplyService caseApplyService;
	@Autowired
	private HousePropertyService housePropertyService;
	@Autowired
	private BeforePersonalCustomerService beforePersonalCustomerService;
	@Autowired
	private ProductService productService;
	@Autowired
	private BPM BPM;
	@Autowired
	private CED CED;
	@Autowired
	private ProcessConfigService processConfigService;
	@Autowired
	private BusiFormService busiFormService;
	@Autowired
	private BlanckListService blanckListService;
	@Autowired
	private HuifaDetailService huifaDetailService;
	@Autowired
	private CaseApplyBeforeCustomerService caseApplyBeforeCustomerService;
	@Autowired
	private BeforeWorkUnitService beforeWorkUnitService;
	@Autowired
	private BusinessDetailService businessDetailService;
	@Autowired
	private RuleApprovalService ruleApprovalService;
	@Autowired
	private TerminalCaseApprovalOpinionService terminalCaseApprovalOpinionService;
	@Autowired
	private BeforePersonalAssociationService beforePersonalAssociationService;
	@Autowired
	private CreditService creditService;
	@Autowired
	private BeforehandApplyRepository beforehandApplyRepository;
	@Override
	@Transactional(rollbackFor=Exception.class)
	public Map<String,Object> saveOrUpdateBeforehandApply(BeforehandApply beforehandApply, BeforePersonalCustomer mainCustomer,
			 HouseProperty houseProperty,String creditAttachmentIds,Boolean submitStatus) throws Exception{
		
		BeforehandApply beforehandApplys = new BeforehandApply();
		CaseApply caseApply=new CaseApply();
		
		//转换数据
		Map<String, Object> beforehandVoCaseMap = this.beforehandVoCase(beforehandApply);
		
		//获取数据
		beforehandApplys = (BeforehandApply) beforehandVoCaseMap.get("beforehandApplys");
		caseApply = (CaseApply) beforehandVoCaseMap.get("caseApply");
		
		//保存案件申请信息
		caseApply = caseApplyService.saveOrUpdateCaseApply(caseApply);
		
		//保存案件预申请信息
		beforehandApplys.setCaseApplyId(caseApply.getId());
		beforehandApplys = this.saveOrUpdateEntity(beforehandApplys);
		
		//设置创建类型
		mainCustomer.setCreateType(CustomerCreateType.MAIN_CUSTOMER.value());
		BeforePersonalCustomer spouse = mainCustomer.getSpouse();
		if(ObjectHelper.isNotEmpty(spouse)){
			spouse.setCreateType(CustomerCreateType.MAIN_CUSTOMER.value());
		}
		//保存客户信息
		BeforePersonalCustomer beforePersonalCustomer = beforePersonalCustomerService.saveOrUpdateCustomer(mainCustomer, caseApply.getId());
		
		//保存房产信息
		houseProperty.setCaseApply(caseApply);
		houseProperty = housePropertyService.saveOrUpdateHouseProperty(houseProperty);
		
		BusiForm busiForm = caseApply.getBusiForm();
		//产品
		Product product = productService.findOne(caseApply.getProductSubtypeId());
		//附件信息
		this.saveMaterialList(caseApply.getId(),product.getId(),creditAttachmentIds,beforePersonalCustomer.getId());
		EmpDto loginUser = CED.getLoginUser();
		if(ObjectHelper.isEmpty(busiForm)){
			busiForm = new BusiForm();
			//状态
			busiForm.setFormStatus(BusiFormStatus.DRAFT.value);
			//申请人编号
            busiForm.setLaunchEmpCode(loginUser.getEmpCd());
            //申请人名称
            busiForm.setLaunchEmpName(loginUser.getEmpNm());
			//申请时间
			busiForm.setApplyDate(DateHelper.dateToLong(new Date(), DateHelper.DATE_LONG_SIMPLE_FORMAT));
			//关联业务表单id
			busiForm.setBusinessEntityId(caseApply.getId());
			//标题
			busiForm.setApplyTitle(caseApply.getCaseApplyCode()+"-"+mainCustomer.getCustomerName()+"-"+product.getProductName());
			//业务编号
			busiForm.setBusinessCode(caseApply.getCaseApplyCode());
			//申请类型
			busiForm.setModelType(ApplyModelTypeEnum.CASE_APPLY.value);
			//关联表单域对象类名 例如表单为Project时 此字段存入的值为Project
			busiForm.setBusinessEntityName(CaseApply.class.getSimpleName());
			//关联组件数据ID 例如 项目表ID 合同ID
			busiForm.setComponentsEntityId(caseApply.getId());
			//关联组件域对象类名 例如表单为Project时 此字段存入的值为Project
			busiForm.setComponentsEntityName(CaseApply.class.getSimpleName());
			//保存进入营销登记状态
			caseApply.setStage(CaseApplyStageEnumSimpleCodeEnum.ACCEPTANCE.value);
		}else if(ObjectHelper.isNotEmpty(busiForm) && busiForm.getFormStatus() == BusiFormStatus.SCRAPPED.value){
			busiForm.setFormStatus(BusiFormStatus.DRAFT.value);
		}
		busiForm = busiFormService.saveEntity(busiForm);
		Boolean isRefuse = false;
		//启动流程
		if(submitStatus){
			//一审判断（）
			if(!firstApprovalDetermine(caseApply,busiForm)){
				logger.info("开始启动流程");
				ProcessConfig processConfig = processConfigService.findByProductIdAndProcessCode(caseApply.getProductSubtypeId(), CaseApply.PROCESS_KEY_CODE);
				busiForm.setFormStatus(BusiFormStatus.APPROVAL.value);
				String processKey = processConfig.getProcessKey();
				
				//设置引擎参数
		        Map<String, String> engineVarible = new HashMap<String, String>();
		        //设置业务参数
		        Map<String, String> businessVarible = new HashMap<String, String>();
		        businessVarible.put(BusiForm.PROCESS_STORE_KEY, busiForm.getId());
		        //流程实例
		        ProcessInstanceDto instanceDto = null;
		        try {
					instanceDto = BPM.startMainProcess(processKey, caseApply.getId(), caseApply.getId(), processKey, engineVarible, businessVarible);
					//流程实例key
					busiForm.setProcessInstanceKey(instanceDto.getProcessInstanceId());
					logger.info("processInstanceKey:"+instanceDto.getProcessInstanceId());
					//流程开始时间
					busiForm.setProcessStartDate(DateHelper.dateToLong(new Date(), DateHelper.DATE_LONG_SIMPLE_FORMAT));
					//流程名称
					busiForm.setProcessKey(processKey);
					//当前处理人名称
					busiForm.setCurrentDealEmpNm(instanceDto.getCurrentCandidate());
					//派工状态
					caseApply.setStage(CaseApplyStageEnumSimpleCodeEnum.RECRUITMENT.value);
					logger.info("currentDealEmpNm:"+instanceDto.getCurrentCandidate());
					logger.info("启动流程成功");
		        } catch (AppException e) {
					e.printStackTrace();
					logger.error("流程启动错误1",e.getMessage());
					throw new BusinessException("10000004","流程启动错误1");
				}
		        if(ObjectHelper.isEmpty(instanceDto)){
		        	throw new BusinessException("10000005","流程启动错误2");
		        }
			}else{
				logger.info("更改案件状态为拒绝");
				// busiForm状态置为规则拒绝
				busiForm.setFormStatus(BusiFormStatus.RULESREFUSE.value);
				caseApply.setStage(CaseApplyStageEnumSimpleCodeEnum.VETO.value);
				busiForm.setHadRulesRefuse(true);
				isRefuse = true;
			}
	      
		}
		busiForm = busiFormService.updateBusiForm(busiForm);
		caseApply.setBusiForm(busiForm);
		
		//返回页面信息
		Map<String,Object> resultMap = new HashMap<String,Object>();
		//下一处理人
		if(ObjectHelper.isNotEmpty(busiForm)){
			resultMap.put("currentDealEmpNm", busiForm.getCurrentDealEmpNm());
		}
		//客户id
		resultMap.put("customerId", beforePersonalCustomer.getId());
		//配偶id
		if(ObjectHelper.isNotEmpty(beforePersonalCustomer.getSpouse())){
			resultMap.put("spouseId", beforePersonalCustomer.getSpouse().getId());
		}
		//押品信息
		resultMap.put("housePropertyId", houseProperty.getId());
		//预申请Id
		resultMap.put("beforehandApplyId", beforehandApplys.getId());
		//是否被规则拒绝（方便前台显示弹出框）
		resultMap.put("isRefuse",isRefuse);
		return resultMap;
	}
	
	/** 
	 * @Title: firstApprovalDetermine 
	 * @Description: 一审判断（）
	 * @author zjx 
	 * @param caseApply案件类
	 * @return 返回true代表中了拒绝/返回false代表未拒绝
	 * @throws Exception  
	 */ 
	public Boolean firstApprovalDetermine(CaseApply caseApply,BusiForm busiForm) throws Exception {
		Integer isRefuse = RuleApprovalFinal.RUN_NORMAL.getValue();
		if(!busiForm.getHadRulesRefuse()){
			// 判断黑名单
			logger.info("开始调用黑名单接口");
			Boolean blackFlg = blanckListService.checkBlankList(caseApply.getId());
			logger.info("调用黑名单接口结果="+blackFlg);
			if (ObjectHelper.isEmpty(blackFlg) || blackFlg ) {
				return true;
			}
		}else{
			isRefuse = RuleApprovalFinal.RUN_NOREFUSE.getValue();
		}
		//调用汇法接口数据和工商数据
		callHuifaAndBusinessData(caseApply);
		// 调用规则引擎
		logger.info("开始调用一审规则引擎接口");
		Boolean approvalResult = ruleApprovalService.firstApproval(caseApply.getId(),isRefuse);
		logger.info("调用一审规则引擎接口结果="+approvalResult);
		if(approvalResult){
			return true;
		}
		return false;
	}
	/** 
	 * @Title: callHuifaAndBusinessData 
	 * @Description: 查询工商信息和汇法信息
	 * @author zjx 
	 * @param caseApply案件类  
	 */ 
	private void callHuifaAndBusinessData(CaseApply caseApply) throws BusinessException{
//		huifaDetailService.callHuifaInterface(type, name, code, businessId);
		logger.info("开始调用工商汇法接口，获取caseApply="+caseApply );
		List<CaseApplyBeforeCustomer> customers = caseApplyBeforeCustomerService.queryByCaseApplyId(caseApply.getId());
		for(CaseApplyBeforeCustomer customer : customers){
			BeforePersonalCustomer personalCustomer = (BeforePersonalCustomer) customer.getBeforeCustomer();
			logger.info("查询汇法个人信息=====》"+personalCustomer.getCustomerName()+"===>"+personalCustomer.getCredentialNo());
			Boolean preHuifaResult = huifaDetailService.callHuifaInterface(String.valueOf(HuifaRequest.STYPE_PERSONAL),
					personalCustomer.getCustomerName(), personalCustomer.getCredentialNo(), caseApply.getId());
			logger.info("查询汇法个人信息返回=====》"+preHuifaResult);
			if(!preHuifaResult){
//				throw new BusinessException("10000004","查询汇法个人信息失败");
			}
			//客户关系（配偶等信息）（由于规则判断只要是人都查，配偶等信息可能不是共借人和担保人，故单独查询一次）
			List<BeforePersonalAssociation> associations = beforePersonalAssociationService.queryCustomerId(personalCustomer.getId());
			for(BeforePersonalAssociation association : associations){
				BeforePersonalCustomer customer2 = association.getBeforePersonalCusomer();
				//查询出汇法信息
				Boolean preHuifaResult2 = huifaDetailService.callHuifaInterface(String.valueOf(HuifaRequest.STYPE_PERSONAL),
						customer2.getCustomerName(), customer2.getCredentialNo(), caseApply.getId());
				if(!preHuifaResult2){
//					throw new BusinessException("10000004","查询汇法个人信息失败");
				}
			}
			List<BeforeWorkUnit> workUnits =  beforeWorkUnitService.queryByCustomerId(personalCustomer.getId());
			for(BeforeWorkUnit workUnit : workUnits){
				if(BeforeWorkUnit.CHAIRMAN_OF_THE_BOARD.equals(workUnit.getPosition())
						||BeforeWorkUnit.COMPANY_NAME_CONTROLLER.equals(workUnit.getPosition())
						||BeforeWorkUnit.COMPANY_ACTUAL_CONTROLLER.equals(workUnit.getPosition())){
					logger.info("查询汇法企业信息=====》"+workUnit.getCompanyName());
					Boolean entHuifaResult = huifaDetailService.callHuifaInterface(String.valueOf(HuifaRequest.STYPE_COMPANY),
							workUnit.getCompanyName(), "", caseApply.getId());
					logger.info("查询汇法企业信息返回=====》"+entHuifaResult);
					if(!entHuifaResult){
//						throw new BusinessException("10000004","查询汇法企业信息失败");
					}
					logger.info("查询工商企业信息=====》"+workUnit.getCompanyName());
					Boolean entBusinessResult = businessDetailService.callBusinessInterface(workUnit.getCompanyName(), caseApply.getId(), "", "");
					logger.info("查询工商信息返回=====》"+entBusinessResult);
					if(!entBusinessResult){
//						throw new BusinessException("10000004","查询工商信息失败");
					}
				}
			}
			
		}
		//获取产权人（页面没产权人的工作信息，故为获取工商信息）的汇法数据
		List<HouseProperty> houseProperties = housePropertyService.findByCaseApplyId(caseApply.getId());
		for(HouseProperty houseProperty:houseProperties){
			//获取产权人汇法信息（和上面客户信息重的几率很大 ）
			List<PropertyOwner> owners = houseProperty.getPropertyOwnerList();
			for(PropertyOwner owner : owners){
				//查询出汇法信息
				Boolean preHuifaResult = huifaDetailService.callHuifaInterface(String.valueOf(HuifaRequest.STYPE_PERSONAL),
						owner.getOwnerName(), owner.getCredentialNo(), caseApply.getId());
				if(!preHuifaResult){
//					throw new BusinessException("10000004","查询汇法个人信息失败");
				}
			}
		}
		logger.info("调用工商汇法接口完成");
	}
	
	/** 
	 * @Title: saveMaterialList 
	 * @Description: 保存征信附件
	 * @author liuhuan 
	 * @param caseApplyId 案件id
	 * @param productId 产品id
	 * @param creditAttachmentIds 附件id集(多个以逗号分开)
	 * @param customerId 客户id
	 * @throws Exception  
	 */ 
	private void saveMaterialList(String caseApplyId,String productId, String creditAttachmentIds, String customerId) throws Exception {
		if(ObjectHelper.isEmpty(caseApplyId) || ObjectHelper.isEmpty(productId) || ObjectHelper.isEmpty(creditAttachmentIds)
				||  ObjectHelper.isEmpty(customerId)){
			return ;
		}
		String[] attachmentIds =null;
		if(ObjectHelper.isNotEmpty(creditAttachmentIds)){
			attachmentIds = creditAttachmentIds.split(",");
		}
		String materiaCode = CreditAttachmentTypeEnum.CREDIT_REPORT.value;//营销阶段-只能上传征信报告
		String stage = CaseApplyStageEnumSimpleCodeEnum.ACCEPTANCE.value;//案件状态-营销
		String creditLinkCode = Credit.LINK_CODE_APPLY;//营销录入
		creditService.saveCredit(caseApplyId, caseApplyId, productId, materiaCode, attachmentIds, stage, creditLinkCode, customerId);
	}
	/**
	 * 
	 * @Title: beforehandVoCase 
	 * @Description: 封装营销申请数据
	 * @author zhoushichao  
	 * @param beforehandApply 营销申请数据
	 * @return
	 */
	public Map<String,Object> beforehandVoCase(BeforehandApply beforehandApply) {
		Map<String, Object> beforehandVoCaseMap =new HashMap<String, Object>();
		
		BeforehandApply beforehandApplys = new BeforehandApply();
		CaseApply caseApply=new CaseApply();
		
		try {
			if (ObjectHelper.isNotEmpty(beforehandApply.getId())) {
				beforehandApplys = this.findOne(beforehandApply.getId());
			}
			//获取产品信息
			if(ObjectHelper.isNotEmpty(beforehandApply.getProductSubtypeId())){
				Product product = productService.findOne(beforehandApply.getProductSubtypeId());
				beforehandApplys.setProductTypeId(product.getCategory().getId());
				beforehandApplys.setProductTypeName(product.getCategory().getName());
				beforehandApplys.setProductSubtypeId(product.getId());
				beforehandApplys.setProductSubtypeName(product.getProductName());
			}
			
			beforehandApplys.setCapitalUseFor(beforehandApply.getCapitalUseFor());
			beforehandApplys.setTerminalId(beforehandApply.getTerminalId());
			beforehandApplys.setCreditMember(beforehandApply.getCreditMember());
			beforehandApplys.setAssessedPriceMortgage(beforehandApply.getAssessedPriceMortgage());
			beforehandApplys.setLoanNumber(beforehandApply.getLoanNumber());
			beforehandApplys.setApplyAmount(beforehandApply.getApplyAmount());
			beforehandApplys.setApplyDate(beforehandApply.getApplyDate());
			beforehandApplys.setApplyTerm(beforehandApply.getApplyTerm());
			beforehandApplys.setApplyTermUnit(beforehandApply.getApplyTermUnit());
			beforehandApplys.setCapitalSource(beforehandApply.getCapitalSource());
			
			//初始案件状态设置为     正常
			if (ObjectHelper.isEmpty(beforehandApplys.getCaseApplyStatus())) {
				beforehandApplys.setCaseApplyStatus(BeforehandApply.NORMAL);
			}
			if (ObjectHelper.isNotEmpty(beforehandApplys.getCaseApplyId())) {
				caseApply = caseApplyService.findOne(beforehandApplys.getCaseApplyId());
			}
			
			//案件预申请复杂到案件表数据
			caseApply.setProductTypeId(beforehandApplys.getProductTypeId());
			caseApply.setProductTypeName(beforehandApplys.getProductTypeName());
			caseApply.setProductSubtypeId(beforehandApplys.getProductSubtypeId());
			caseApply.setProductSubtypeName(beforehandApplys.getProductSubtypeName());
			caseApply.setCapitalUseFor(beforehandApplys.getCapitalUseFor());
			caseApply.setTerminalId(beforehandApplys.getTerminalId());
			caseApply.setCreditMember(beforehandApplys.getCreditMember());
			caseApply.setAssessedPriceMortgage(beforehandApplys.getAssessedPriceMortgage());
			caseApply.setLoanNumber(beforehandApplys.getLoanNumber());
			caseApply.setApplyAmount(beforehandApplys.getApplyAmount());
			caseApply.setApplyDate(beforehandApplys.getApplyDate());
			caseApply.setApplyTerm(beforehandApplys.getApplyTerm());
			caseApply.setApplyTermUnit(beforehandApplys.getApplyTermUnit());
			caseApply.setCapitalSource(beforehandApplys.getCapitalSource());
			
			//获取贷款利率
			ProductRate productRate = productService.findByProductIdAndDeadline(beforehandApply.getProductSubtypeId(),new Long(beforehandApply.getApplyTerm()), beforehandApply.getApplyTermUnit());
			if (ObjectHelper.isNotEmpty(productRate)) {
				caseApply.setApplyRate(new BigDecimal(productRate.getRate()));
				caseApply.setApplyRateUnit(productRate.getRateUnit());
			}
			
			//获取登录人信息
			EmpDto emp = CED.getLoginUser();
			//设置登录人信息
			caseApply.setDevelopmentManagerCode(emp.getEmpCd());
			caseApply.setDevelopmentManagerName(emp.getEmpNm());
			caseApply.setDevelopmentDepartmentCode(emp.getOrgCd());
			caseApply.setDevelopmentDepartmentName(emp.getOrgNm());
			caseApply.setMechanismCode(emp.getCompanyCd());
			caseApply.setMechanismName(emp.getCompanyNm());
			
			beforehandVoCaseMap.put("beforehandApplys", beforehandApplys);
			beforehandVoCaseMap.put("caseApply", caseApply);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return beforehandVoCaseMap;
		
	}

	@Override
	public BeforehandApply findByCaseApplyId(String caseApplyId) {
		return this.customReposity.findByCaseApplyId(caseApplyId);
	}
	
	/**
	 * 
	 * @Title: saveOrUpdateTerminalCaseApply 
	 * @Description: 保存终端进件编辑信息
	 * @author xiongpan
	 * @param beforehandApply 终端进件的预申请
	 * @param mainCustomer 主借人
	 * @param houseProperty 房产
	 * @param terminalCaseApprovalOpinion 终端进件的审批意见
	 * @param submitStatus 提交状态
	 * @return
	 */
	
	@Override
	@Transactional(rollbackFor=Exception.class)
	public Map<String, Object> saveOrUpdateTerminalCaseApply(BeforehandApply beforehandApply,
			BeforePersonalCustomer mainCustomer, HouseProperty houseProperty,
			TerminalCaseApprovalOpinion terminalCaseApprovalOpinion, Boolean submitStatus) throws Exception{

		BeforehandApply beforehandApplys = new BeforehandApply();
		CaseApply caseApply=new CaseApply();
		Map<String,Object> resultMap = new HashMap<String,Object>();
		
		//转换数据
		Map<String, Object> beforehandVoCaseMap = this.beforehandVoCase(beforehandApply);
		
		//获取数据
		beforehandApplys = (BeforehandApply) beforehandVoCaseMap.get("beforehandApplys");
		caseApply = (CaseApply) beforehandVoCaseMap.get("caseApply");
		
		//保存案件申请信息
		caseApply = caseApplyService.saveOrUpdateCaseApply(caseApply);
		//保存案件预申请信息
		beforehandApplys.setCaseApplyId(caseApply.getId());
		beforehandApplys = this.saveOrUpdateEntity(beforehandApplys);
		//案件欲申请实体类
		resultMap.put("beforehandApply", beforehandApplys);
		
		if(ObjectHelper.isNotEmpty(mainCustomer)){
			//设置创建类型
			mainCustomer.setCreateType(CustomerCreateType.MAIN_CUSTOMER.value());
			BeforePersonalCustomer spouse = mainCustomer.getSpouse();
			if(ObjectHelper.isNotEmpty(spouse)){
				spouse.setCreateType(CustomerCreateType.MAIN_CUSTOMER.value());
			}
			//保存客户信息
			BeforePersonalCustomer beforePersonalCustomer = beforePersonalCustomerService.saveOrUpdateCustomer(mainCustomer, caseApply.getId());
			//客户信息
			resultMap.put("customer", beforePersonalCustomer);
		}
		
		if(ObjectHelper.isNotEmpty(houseProperty)){
			//保存房产信息
			houseProperty.setCaseApply(caseApply);
			houseProperty = housePropertyService.saveOrUpdateHouseProperty(houseProperty);
			//押品信息
			resultMap.put("houseProperty", houseProperty);
		}
		
		//提交终端进件
		if(submitStatus && "YWDM007001".equals(terminalCaseApprovalOpinion.getApprovalResult())){
			BusiForm busiForm = caseApply.getBusiForm();
			EmpDto loginUser = CED.getLoginUser();
			//产品
			Product product = productService.findOne(caseApply.getProductSubtypeId());
			if(ObjectHelper.isEmpty(busiForm)){
				busiForm = new BusiForm();
				//设置状态
				busiForm.setFormStatus(BusiFormStatus.DRAFT.value);
				//申请人编号
	            busiForm.setLaunchEmpCode(loginUser.getEmpCd());
	            //申请人名称
	            busiForm.setLaunchEmpName(loginUser.getEmpNm());
	            //申请时间
				busiForm.setApplyDate(DateHelper.dateToLong(new Date(), DateHelper.DATE_LONG_SIMPLE_FORMAT));
				//关联业务表单id
				busiForm.setBusinessEntityId(caseApply.getId());
				//标题
				busiForm.setApplyTitle(caseApply.getCaseApplyCode()+"-"+mainCustomer.getCustomerName()+"-"+product.getProductName());
				//业务编号
				busiForm.setBusinessCode(caseApply.getCaseApplyCode());
				//申请类型
				busiForm.setModelType(ApplyModelTypeEnum.CASE_APPLY.value);
				//关联表单域对象类名 例如表单为Project时 此字段存入的值为Project
				busiForm.setBusinessEntityName(CaseApply.class.getSimpleName());
				//关联组件数据ID 例如 项目表ID 合同ID
				busiForm.setComponentsEntityId(caseApply.getId());
				//关联组件域对象类名 例如表单为Project时 此字段存入的值为Project
				busiForm.setComponentsEntityName(CaseApply.class.getSimpleName());
				//提交进入营销登记状态
				caseApply.setStage(CaseApplyStageEnumSimpleCodeEnum.ACCEPTANCE.value);
				
			}
			
		}
		if(submitStatus && "YWDM007002".equals(terminalCaseApprovalOpinion.getApprovalResult())){
			//提交进入营销登记状态
			caseApply.setStage(CaseApplyStageEnumSimpleCodeEnum.VETO.value);
		}
		
		caseApply = caseApplyService.saveOrUpdateCaseApply(caseApply);
		
		//保存终端进件审批意见信息
		if(ObjectHelper.isNotEmpty(terminalCaseApprovalOpinion)){
			terminalCaseApprovalOpinion.setCaseApplyId(caseApply.getId());
			terminalCaseApprovalOpinion = terminalCaseApprovalOpinionService.saveOrUpdateEntity(terminalCaseApprovalOpinion);
		}
		
		//案件实体类
		resultMap.put("caseApply", caseApply);
		//终端进件审批意见
		resultMap.put("terminalCaseApprovalOpinion", terminalCaseApprovalOpinion);
		
		return resultMap;
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public BeforehandApply saveOrUpdateAppBeforehandApply(BeforehandApply beforehandApply) throws Exception {
		//获取登录人信息
		EmpDto emp = CED.getLoginUser();
		BeforehandApply beforehand = new BeforehandApply();
		CaseApply caseApply=new CaseApply();
		
		if(ObjectHelper.isNotEmpty(beforehandApply.getCaseApplyId())){
			caseApply = caseApplyService.findOne(beforehandApply.getCaseApplyId());
		}
		caseApply.setProductTypeId(beforehandApply.getProductTypeId());
		caseApply.setProductTypeName(beforehandApply.getProductTypeName());
		caseApply.setProductSubtypeId(beforehandApply.getProductSubtypeId());
		caseApply.setProductSubtypeName(beforehandApply.getProductSubtypeName());
		caseApply.setTerminalId(beforehandApply.getTerminalId());
		caseApply.setApplyAmount(beforehandApply.getApplyAmount());
		caseApply.setCapitalSource(beforehandApply.getCapitalSource());
		caseApply.setCapitalUseFor(beforehandApply.getCapitalUseFor());
		caseApply.setApplyTerm(beforehandApply.getApplyTerm());
		caseApply.setApplyTermUnit(beforehandApply.getApplyTermUnit());
		//获取贷款利率
		ProductRate productRate = productService.findByProductIdAndDeadline(beforehandApply.getProductSubtypeId(),new Long(beforehandApply.getApplyTerm()), beforehandApply.getApplyTermUnit());
		if (ObjectHelper.isNotEmpty(productRate)) {
			caseApply.setApplyRate(new BigDecimal(productRate.getRate()));
			caseApply.setApplyRateUnit(productRate.getRateUnit());
		}
		//设置登录人信息
		caseApply.setDevelopmentManagerCode(emp.getEmpCd());
		caseApply.setDevelopmentManagerName(emp.getEmpNm());
		caseApply.setDevelopmentDepartmentCode(emp.getOrgCd());
		caseApply.setDevelopmentDepartmentName(emp.getOrgNm());
		caseApply.setMechanismCode(emp.getCompanyCd());
		caseApply.setMechanismName(emp.getCompanyNm());
		
		caseApply = caseApplyService.saveOrUpdateCaseApply(caseApply);
		
		if(ObjectHelper.isNotEmpty(beforehandApply.getId())){
			beforehand = this.findOne(beforehandApply.getId());
		}
		BeanUtils.copyProperties(beforehandApply, beforehand,new String[]{"id","createTime"});
		//设置案件预申请信息
		beforehand.setCaseApplyId(caseApply.getId());
		//初始保存案件状态为正常
 		if(ObjectHelper.isEmpty(beforehand.getCaseApplyStatus())){
 			beforehand.setCaseApplyStatus(BeforehandApply.NORMAL);
		}
		
		return this.saveOrUpdateEntity(beforehand);
	}
	
	/**
	 * 
	 * <p>Title: checkLoanTerm</p>   
	 * <p>Description:检查贷款期限是否在产品期限范围内 </p>   
	 * @param productSubtypeId
	 * @param applyTerm
	 * @return   
	 * @see com.zdsoft.finance.marketing.service.BeforehandApplyService#checkLoanTerm(java.lang.String, int)
	 */
	public Long checkLoanTerm(String productSubtypeId,int applyTerm){
		
		return beforehandApplyRepository.checkLoanTerm(productSubtypeId, applyTerm);
	}
}
