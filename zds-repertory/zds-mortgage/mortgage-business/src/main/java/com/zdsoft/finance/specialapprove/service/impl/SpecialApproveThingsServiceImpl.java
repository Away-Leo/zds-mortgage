package com.zdsoft.finance.specialapprove.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zdsoft.essential.client.service.CED;
import com.zdsoft.essential.dto.basedata.SimpleCodeDto;
import com.zdsoft.finance.base.service.impl.BaseServiceImpl;
import com.zdsoft.finance.busiform.entity.BusiForm;
import com.zdsoft.finance.busiform.entity.BusiFormStatus;
import com.zdsoft.finance.busiform.service.BusiFormService;
import com.zdsoft.finance.businesssetting.entity.ExceptMatter;
import com.zdsoft.finance.businesssetting.repository.ExceptMatterRepository;
import com.zdsoft.finance.businesssetting.service.ExceptMatterService;
import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.finance.common.enums.busiform.ApplyModelTypeEnum;
import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.finance.customer.entity.BeforeCustomer;
import com.zdsoft.finance.marketing.entity.CaseApply;
import com.zdsoft.finance.marketing.entity.CaseApplyBeforeCustomer;
import com.zdsoft.finance.marketing.service.CaseApplyBeforeCustomerService;
import com.zdsoft.finance.marketing.service.CaseApplyService;
import com.zdsoft.finance.product.entity.ProcessConfig;
import com.zdsoft.finance.product.service.ProcessConfigService;
import com.zdsoft.finance.specialapprove.entity.RiskRules;
import com.zdsoft.finance.specialapprove.entity.SpecialApproveManage;
import com.zdsoft.finance.specialapprove.entity.SpecialApproveThings;
import com.zdsoft.finance.specialapprove.repository.RiskRulesRepository;
import com.zdsoft.finance.specialapprove.repository.SpecialApproveManageRepository;
import com.zdsoft.finance.specialapprove.repository.SpecialApproveThingsRepository;
import com.zdsoft.finance.specialapprove.service.RiskRulesService;
import com.zdsoft.finance.specialapprove.service.SpecialApproveManageService;
import com.zdsoft.finance.specialapprove.service.SpecialApproveThingsService;
import com.zdsoft.framework.core.common.util.ObjectHelper;

/** 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: SpecialApproveThingsServiceImpl.java 
 * @ClassName: SpecialApproveThingsServiceImpl 
 * @Description: 风险特批实现
 * @author wangrongwei
 * @date 2017年3月6日 下午4:06:53 
 * @version V1.0 
 */ 
@Service
public class SpecialApproveThingsServiceImpl
		extends BaseServiceImpl<SpecialApproveThings, CustomRepository<SpecialApproveThings, String>>
		implements SpecialApproveThingsService {

	@Autowired
	private SpecialApproveManageRepository specialApproveManageRepository;
	
	@Autowired
	private SpecialApproveManageService specialApproveManageService;

	@Autowired
	private CaseApplyService caseApplyService;

	@Autowired
	private SpecialApproveThingsRepository specialApproveThingsRepository;

	@Autowired
	private ProcessConfigService processConfigService;
	
	@Autowired
	private RiskRulesRepository riskRulesRepository;
	
	@Autowired
	private ExceptMatterRepository exceptMatterRepository;
	
	@Autowired
	private BusiFormService busiFormService;
	
	@Autowired
	private CaseApplyBeforeCustomerService caseApplyBeforeCustomerService;
	
	@Autowired
	private CED ced;
	
	@Autowired
	private RiskRulesService riskRulesService;
	
	@Autowired
	private ExceptMatterService exceptMatterService;
	
	@Override
	public List<SpecialApproveThings> findByCaseApplyId(String caseApplyId) {
		List<SpecialApproveThings> listThings = new ArrayList<>();
		// 查询风险特批
		List<SpecialApproveManage> list = specialApproveManageRepository
				.findByCaseApplyIdAndSpecialApproveType(caseApplyId, 1);
		// 遍历风险特批
		for (SpecialApproveManage specialApproveManage : list) {
			listThings.addAll(specialApproveManage.getListSpecialApproveThings());
		}
		return listThings;
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public BusiForm saveOrCommitRiskSpecialApproveApply(Map<String, String> riskItemMap, Boolean submitStatus,
			String caseApplyId, String specialApproveManageId, String remark,boolean isSystem,String otherInfo) throws Exception {
		
		//保存风险特批申请
		SpecialApproveManage specialApproveManage = this.saveOrUpdateRiskApply(riskItemMap, caseApplyId,
				specialApproveManageId, remark,isSystem,otherInfo);
		BusiForm busiForm = specialApproveManage.getBusiForm();
		// 启动流程
		if (submitStatus) {
			logger.info("开始启动流程...");
			CaseApply caseApply = caseApplyService.findOne(caseApplyId);
			ProcessConfig processConfig = processConfigService
					.findByProductIdAndProcessCode(caseApply.getProductSubtypeId(), SpecialApproveManage.PROCESS_THINGS_CODE);
			if (ObjectHelper.isEmpty(processConfig)) {
				throw new BusinessException("流程未配置");
			}
			
			// 设置引擎参数
			Map<String, String> engineVarible = new HashMap<String, String>();
			// 设置业务参数
			Map<String, String> businessVarible = new HashMap<String, String>();
			specialApproveManage.setSpecialApproveStatus(SpecialApproveManage.SPECIAL_APPROVE_STATUS_APPROVE);//审批中
			busiForm.setFormStatus(BusiFormStatus.APPROVAL.value);
			
			String processKey = processConfig.getProcessKey();
			List<SpecialApproveThings> list = specialApproveManage.getListSpecialApproveThings();
			if (ObjectHelper.isNotEmpty(list) && isSystem) {
				for (SpecialApproveThings specialApproveThings : list) {
					List<RiskRules> listRiskRules = riskRulesRepository.findByRulesTypeAndCaseApplyId(specialApproveThings.getItemCode(), caseApplyId);
					for (RiskRules riskRules : listRiskRules) {
						riskRules.setSpecialStatus(SpecialApproveManage.SPECIAL_APPROVE_STATUS_APPROVE);
					}
				}
			}
			
			busiForm.setProcessKey(processKey);
			busiForm = busiFormService.startProcess(busiForm, engineVarible, businessVarible);
			// 流程实例key
			logger.info("processInstanceKey:" + busiForm.getProcessInstanceKey());
			logger.info("启动流程成功");
		}
		busiFormService.updateEntity(busiForm);
		return busiForm;
	}

	/** 
	 * @Title: saveOrUpdateRiskApply 
	 * @Description: 保存或更新风险特批申请
	 * @author wangrongwei
	 * @param riskItemMap 风险特批项
	 * @param caseApplyId 案件ID
	 * @param specialApproveManageId 特批管理ID
	 * @param remark 备注
	 * @param isSystem 是否系统触发
	 * @return
	 * @throws Exception  
	 */ 
	public SpecialApproveManage saveOrUpdateRiskApply(Map<String, String> riskItemMap, String caseApplyId,
			String specialApproveManageId, String remark,boolean isSystem,String otherInfo) throws Exception {
		SpecialApproveManage specialApproveManage = null;
		CaseApply caseApply = caseApplyService.findOne(caseApplyId);
		//风险特批管理ID
		if(ObjectHelper.isNotEmpty(specialApproveManageId)){
			//更新
			specialApproveManage = specialApproveManageService.findOne(specialApproveManageId);
		}else {
			specialApproveManage = new SpecialApproveManage();
			specialApproveManage.setSpecialApproveType(1);// 设置类型为风险特批
			specialApproveManage.setCaseApply(caseApply); //设置与案件关联
			specialApproveManage.setCreateBy(ced.getLoginUser().getId());
			//设置特批触发类型
			specialApproveManage.setSystem(isSystem);
			if(ObjectHelper.isEmpty(specialApproveManage.getSpecialApproveStatus())){
				if (isSystem) {
					specialApproveManage.setSpecialApproveStatus(SpecialApproveManage.SPECIAL_APPROVE_STATUS_DRAFT_SYS);//系统触发的风险特批草稿
				}else
					specialApproveManage.setSpecialApproveStatus(SpecialApproveManage.SPECIAL_APPROVE_STATUS_DRAFT);//设置为草稿状态
			}
		}
		specialApproveManage.setRemark(remark);
		// 保存特批管理
		specialApproveManage = specialApproveManageService.saveOrUpdateEntity(specialApproveManage);
		// 风险特批项
		List<SpecialApproveThings> riskList = new ArrayList<>();
		if (ObjectHelper.isEmpty(riskItemMap)) {
			//通过案件ID查询系统触发的风险规则 
			List<RiskRules> list = riskRulesService.findByCaseApplyIdAndSpecialStatus(caseApplyId, "");
			// 查询特批事项分类
			logger.debug("查询系统触发的风险特批事项.....................事项长度：{}",list.size());
			List<SimpleCodeDto> simplecodeList = ced.querySimpleCodeByCategoryId(SpecialApproveThings.THINGS_TYPE_CODE);
			for (SimpleCodeDto simpleCodeDto : simplecodeList) {
				for (RiskRules riskRules : list) {
					//查询事项所属分类
					ExceptMatter exc= exceptMatterService.findByExceptMattercode(riskRules.getRulesType());
					if (simpleCodeDto.getFullCode().equals(exc.getExceptMatterType())) {
						SpecialApproveThings approveThings = new SpecialApproveThings();
						approveThings.setItemType(exc.getExceptMatterType());
						approveThings.setItemCode(exc.getExceptMattercode());
						approveThings.setItemName(exc.getExceptMatterName());
						
						//修改风险规则明细表状态为:草稿
						riskRules.setSpecialStatus(SpecialApproveManage.SPECIAL_APPROVE_STATUS_DRAFT_SYS);
						approveThings.setExpRiskRulesId(riskRules.getId());
						riskList.add(approveThings);
					}
				}
			}
		}else{
			//页面传递进来的map
			Set<String> set = riskItemMap.keySet();
			for (String itemType : set) {
				String itemCodes = riskItemMap.get(itemType) + "";
				if (ObjectHelper.isNotEmpty(itemCodes)) {
					String[] itemarray = itemCodes.split(",");
					for (String itemCode : itemarray) {
						SpecialApproveThings approveThings = new SpecialApproveThings();
						approveThings.setItemType(itemType);
						approveThings.setItemCode(itemCode);
						if (ObjectHelper.isNotEmpty(otherInfo) && "TPDM000006".equals(itemCode)) {
							approveThings.setOtherInfo(otherInfo);
						}
						// 查询事项名称
						String itemName = exceptMatterRepository.findByExceptMattercode(itemCode).getExceptMatterName();
						approveThings.setItemName(itemName);
						
						if (isSystem) {
							//查询风险规则明细
							List<RiskRules> rkList = riskRulesRepository.findByRulesTypeAndCaseApplyId(itemCode, caseApplyId);
							for (RiskRules rk : rkList) {
								if (ObjectHelper.isEmpty(rk.getSpecialStatus())) {
									//修改风险规则明细表状态为:草稿
									rk.setSpecialStatus(SpecialApproveManage.SPECIAL_APPROVE_STATUS_DRAFT_SYS);
								}
								approveThings.setExpRiskRulesId(rk.getId());
							}
						}
						riskList.add(approveThings);
					}
				}
				
			}
		}
		specialApproveManage.getListSpecialApproveThings().clear();
		specialApproveManage.setListSpecialApproveThings(riskList);
		
		BusiForm busiForm = null;
		//保存busiForm
		if (ObjectHelper.isNotEmpty(specialApproveManage.getId())) {
			busiForm = specialApproveManage.getBusiForm();
			if (ObjectHelper.isEmpty(busiForm)) {
				busiForm = new BusiForm();
			}
		}else{
			busiForm = new BusiForm();
		}
		if (ObjectHelper.isEmpty(busiForm.getId())) {
			busiForm.setBusinessEntityId(specialApproveManage.getId());
			busiForm.setComponentsEntityId(caseApplyId);
			busiForm.setComponentsEntityName(CaseApply.class.getSimpleName());
			busiForm.setBusinessEntityName(SpecialApproveManage.class.getSimpleName());
			busiForm.setFormStatus(BusiFormStatus.DRAFT.value);
			
			List<BeforeCustomer> caseApplyIdAndJoinType = caseApplyBeforeCustomerService.findCustomerByCaseApplyIdAndJoinType(caseApplyId, CaseApplyBeforeCustomer.MAIN_BORROW);
			//面签申请：标题 案件号+主借人+特批类型      业务编号：案件号 
			if (ObjectHelper.isNotEmpty(caseApplyIdAndJoinType)&&caseApplyIdAndJoinType.size()>0) {
				busiForm.setApplyTitle(caseApply.getCaseApplyCode()+caseApplyIdAndJoinType.get(0).getCustomerName()+caseApply.getProductSubtypeName());
			}
			
			busiForm.setBusinessCode(caseApply.getCaseApplyCode());
			busiForm.setModelType(ApplyModelTypeEnum.RISK_SPECIALAPPROVE.value);
			busiForm = busiFormService.saveBusiForm(busiForm);
			specialApproveManage.setBusiForm(busiForm);
		}
        return specialApproveManageRepository.updateEntity(specialApproveManage);
	}

	@Override
	public List<SpecialApproveThings> findByItemType(String itemType) {
		return specialApproveThingsRepository.findByItemType(itemType);
	}

}
