package com.zdsoft.finance.marketing.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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
import com.zdsoft.finance.casemanage.material.service.CaseMaterialListAttaService;
import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.finance.customer.entity.BeforePersonalCustomer;
import com.zdsoft.finance.customer.entity.CustomerCreateType;
import com.zdsoft.finance.customer.service.BeforePersonalCustomerService;
import com.zdsoft.finance.customer.service.BlanckListService;
import com.zdsoft.finance.marketing.entity.BeforehandApply;
import com.zdsoft.finance.marketing.entity.CaseApply;
import com.zdsoft.finance.marketing.entity.HouseProperty;
import com.zdsoft.finance.marketing.repository.BeforehandApplyRepository;
import com.zdsoft.finance.marketing.service.BeforehandApplyService;
import com.zdsoft.finance.marketing.service.CaseApplyService;
import com.zdsoft.finance.marketing.service.HousePropertyService;
import com.zdsoft.finance.product.entity.ProcessConfig;
import com.zdsoft.finance.product.entity.Product;
import com.zdsoft.finance.product.service.ProcessConfigService;
import com.zdsoft.finance.product.service.ProductService;
import com.zdsoft.framework.core.common.exception.AppException;
import com.zdsoft.framework.core.common.util.DateHelper;
import com.zdsoft.framework.core.common.util.ObjectHelper;
import com.zdsoft.framework.cra.client.service.CRA;
import com.zdsoft.framework.cra.dto.AccountDTO;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title:BeforehandApplyServiceImpl.java
 * @Package:com.zdsoft.finance.marketing.service.impl
 * @Description:案件预申请服务类
 * @author: zhoushichao
 * @date:2017年1月10日 下午9:50:47
 * @version:v1.0
 */
@Service("beforehandApplyService")
public class BeforehandApplyServiceImpl extends BaseServiceImpl<BeforehandApply, CustomRepository<BeforehandApply, String>>
implements BeforehandApplyService{

	@Autowired
	BeforehandApplyRepository beforehandApplyRepository;
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
	private CRA CRA;
	@Autowired
	private CED CED;
	@Autowired
	private ProcessConfigService processConfigService;
	@Autowired
	private BusiFormService busiFormService;
	
	@Autowired
	private BlanckListService blanckListService;
	@Autowired
	private CaseMaterialListAttaService caseMaterialListAttaService;
	
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
		this.saveMaterialList(caseApply.getId(),product.getId(),creditAttachmentIds);
		EmpDto loginUser = CED.getLoginUser();
		if(ObjectHelper.isEmpty(busiForm)){
			busiForm = new BusiForm();
			//状态
			busiForm.setStatus(BusiFormStatus.DRAFT.value);
			//产品编号
			busiForm.setProductCd(caseApply.getProductSubtypeId());
			//产品名称
			busiForm.setProductNm(product.getProductName());
			//申请人编号
			busiForm.setApplyEmpCd(loginUser.getEmpCd());
			//申请人名称
			busiForm.setApplyEmpNm(loginUser.getEmpNm());
			//申请时间
			busiForm.setApplyTime(DateHelper.dateToLong(new Date(), DateHelper.DATE_LONG_SIMPLE_FORMAT));
			//关联业务表单id
			busiForm.setBusinessEntityId(caseApply.getId());
			//关联表单域对象类名 例如表单为Project时 此字段存入的值为Project
			busiForm.setBusinessEntityNm(CaseApply.class.getSimpleName());
			//关联组件数据ID 例如 项目表ID 合同ID
			busiForm.setComponentsEntityId(caseApply.getId());
			//关联组件域对象类名 例如表单为Project时 此字段存入的值为Project
			busiForm.setComponentsEntityNm(CaseApply.class.getSimpleName());
		}
		
		//启动流程
		if(submitStatus){
			logger.info("开始启动流程");
			// 判断黑名单
			Boolean blackFlg = blanckListService.checkBlankList(caseApply.getId());
			if (ObjectHelper.isEmpty(blackFlg) || blackFlg ) {
				throw new BusinessException("黑名单校验不通过！案件记录为：" + caseApply.getId());
			}
			ProcessConfig processConfig = processConfigService.findByProductIdAndProcessConfigCode(caseApply.getProductSubtypeId(), CaseApply.PROCESS_KEY_CODE);
			busiForm.setStatus(BusiFormStatus.APPROVAL.value);
			String processKey = processConfig.getProcessKey();
			
			//设置引擎参数
	        Map<String, String> engineVarible = new HashMap<String, String>();
	        //设置业务参数
	        Map<String, String> businessVarible = new HashMap<String, String>();
	        //流程实例
	        ProcessInstanceDto instanceDto = null;
	        try {
				instanceDto = BPM.startMainProcess(processKey, caseApply.getId(), caseApply.getId(), processKey, engineVarible, businessVarible);
				//流程实例key
				busiForm.setProcessInstanceKey(instanceDto.getProcessInstanceId());
				logger.info("processInstanceKey:"+instanceDto.getProcessInstanceId());
				//流程开始时间
				busiForm.setProcessStartTime(DateHelper.dateToLong(new Date(), DateHelper.DATE_LONG_SIMPLE_FORMAT));
				//流程发起人编号
				busiForm.setProcessApplayEmpCd(loginUser.getEmpCd());
				logger.info("processApplayEmpCd:"+loginUser.getEmpCd());
				//流程发起人姓名
				busiForm.setProcessApplayEmpNm(loginUser.getEmpNm());
				//流程名称
				busiForm.setProcessKey(processKey);
				//当前处理人名称
				busiForm.setCurrentDealEmpNm(instanceDto.getCurrentCandidate());
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
	      
		}
		busiFormService.saveEntity(busiForm);
		caseApply.setBusiForm(busiForm);
		
		Map<String,Object> resultMap = new HashMap<String,Object>();
		//客户信息
		resultMap.put("customer", beforePersonalCustomer);
		//案件欲申请实体类
		resultMap.put("beforehandApply", beforehandApplys);
		//案件实体类
		resultMap.put("caseApply", caseApply);
		//押品信息
		resultMap.put("houseProperty", houseProperty);
		return resultMap;
	}
	/**
	 * 
	 * 保存征信附件
	 *
	 * @author xj
	 * @param productCode
	 * @param creditAttachmentIds
	 * @throws BusinessException 
	 */
	private void saveMaterialList(String caseApplyId,String productId, String creditAttachmentIds) throws Exception {
		String[] attachmentIds =null;
		//MaterialList materialList = materiaListService.findByMateriaTypeCdAndProductCdAndMateriaCd("maclass4", productId, "maclass_warrant5");
		if(ObjectHelper.isNotEmpty(creditAttachmentIds)){
			attachmentIds = creditAttachmentIds.split(",");
		}
		caseMaterialListAttaService.saveCaseApplyCreditAtta(caseApplyId, caseApplyId, productId, BeforehandApplyService.CREDIT_CLASS, attachmentIds);
		
	}
	/**
	 * 
	 * 封装营销申请数据
	 *
	 * @author zhoushichao
	 * @param beforehandApply
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
			beforehandApplys.setApplyDeadline(beforehandApply.getApplyDeadline());
			beforehandApplys.setApplyDeadlineUnit(beforehandApply.getApplyDeadlineUnit());
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
			caseApply.setApplyDeadline(beforehandApplys.getApplyDeadline());
			caseApply.setApplyDeadlineUnit(beforehandApplys.getApplyDeadlineUnit());
			caseApply.setCapitalSource(beforehandApplys.getCapitalSource());
			
			//获取登录人信息
			AccountDTO dto = CRA.getAccount();
			EmpDto emp = CED.getLoginUser(dto.getId());
			//设置登录人信息
			caseApply.setDevelopmentManagerCode(emp.getEmpCd());
			caseApply.setDevelopmentManagerName(emp.getEmpNm());
			caseApply.setDevelopmentDepartmentCode(emp.getDepartmentCd());
			caseApply.setDevelopmentDepartmentName(emp.getDepartmentName());
			caseApply.setMechanismCode(emp.getOrgCd());
			caseApply.setMechanismName(emp.getOrgNm());
			
			beforehandVoCaseMap.put("beforehandApplys", beforehandApplys);
			beforehandVoCaseMap.put("caseApply", caseApply);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return beforehandVoCaseMap;
		
	}

	@Override
	public BeforehandApply findByCaseApplyId(String caseApplyId) {
		return beforehandApplyRepository.findByCaseApplyId(caseApplyId);
	}
}
