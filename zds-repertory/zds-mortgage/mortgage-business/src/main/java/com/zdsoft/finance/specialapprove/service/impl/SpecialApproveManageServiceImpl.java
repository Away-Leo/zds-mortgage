package com.zdsoft.finance.specialapprove.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zdsoft.essential.client.service.CED;
import com.zdsoft.finance.base.service.impl.BaseServiceImpl;
import com.zdsoft.finance.busiform.entity.BusiForm;
import com.zdsoft.finance.busiform.entity.BusiFormStatus;
import com.zdsoft.finance.busiform.service.BusiFormService;
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
import com.zdsoft.finance.specialapprove.entity.FeeRules;
import com.zdsoft.finance.specialapprove.entity.RiskRules;
import com.zdsoft.finance.specialapprove.entity.SpecialApproveFee;
import com.zdsoft.finance.specialapprove.entity.SpecialApproveManage;
import com.zdsoft.finance.specialapprove.entity.SpecialApproveThings;
import com.zdsoft.finance.specialapprove.repository.SpecialApproveManageRepository;
import com.zdsoft.finance.specialapprove.service.FeeRulesService;
import com.zdsoft.finance.specialapprove.service.RiskRulesService;
import com.zdsoft.finance.specialapprove.service.SpecialApproveManageService;
import com.zdsoft.framework.core.common.util.ObjectHelper;

/** 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: SpecialApproveManageServiceImpl.java 
 * @ClassName: SpecialApproveManageServiceImpl 
 * @Description: 特批管理实现
 * @author wangrongwei
 * @date 2017年3月6日 下午4:06:09 
 * @version V1.0 
 */ 
@Service
public class SpecialApproveManageServiceImpl extends BaseServiceImpl<SpecialApproveManage, CustomRepository<SpecialApproveManage,String>> implements SpecialApproveManageService{

	@Autowired
	private SpecialApproveManageRepository SpecialApproveManageRepository;
	
	@Autowired
	private CaseApplyService caseApplyService;
	
	@Autowired
	private ProcessConfigService processConfigService;
	
	@Autowired
	private BusiFormService busiFormService;
	
	@Autowired
	private SpecialApproveManageService specialApproveManageService;
	
	@Autowired
	private CaseApplyBeforeCustomerService caseApplyBeforeCustomerService;
	
	@Autowired
	private CED ced;
	
	@Autowired
	private RiskRulesService riskRulesService;
	
	@Autowired
	private FeeRulesService feeRulesService;
	
	@Override
	public List<SpecialApproveManage> findByCaseApplyId(String caseApplyId) {
		return SpecialApproveManageRepository.findByCaseApplyIdAndSpecialApproveType(caseApplyId, 1);
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public BusiForm saveSpecialApproveFreeApply(String caseApplyId,String specialApproveManageId, String remark, Boolean isSubmit) throws Exception{
		if (ObjectHelper.isEmpty(caseApplyId)) {
			throw new BusinessException("案件ID为空");
		}
		CaseApply caseApply = caseApplyService.findOne(caseApplyId);
		if (ObjectHelper.isEmpty(caseApply)) {
			throw new BusinessException("案件ID有误");
		}
		//特批管理
		SpecialApproveManage specialApproveManage = null;
		BusiForm busiForm = null;
		if (ObjectHelper.isNotEmpty(specialApproveManageId)) {
			specialApproveManage = this.findOne(specialApproveManageId);
			busiForm = specialApproveManage.getBusiForm();
		}else{
			specialApproveManage = new SpecialApproveManage();
			busiForm = new BusiForm();
		}
		//保存数据
		busiForm = this.saveOrUpdateApply(caseApply, busiForm, specialApproveManage,remark);
		
		//启动工作流
		if (isSubmit) {
			ProcessConfig processConfig = processConfigService
					.findByProductIdAndProcessCode(caseApply.getProductSubtypeId(), SpecialApproveManage.PROCESS_FREE_CODE);
			if (ObjectHelper.isEmpty(processConfig)) {
				throw new BusinessException("流程未配置");
			}
			specialApproveManage.setSpecialApproveStatus(SpecialApproveManage.SPECIAL_APPROVE_STATUS_APPROVE);//设置状态为审批中
			String processKey = processConfig.getProcessKey();
			busiForm.setProcessKey(processKey);
			busiForm = busiFormService.startProcess(busiForm, new HashMap<>(), new HashMap<>());
		}
		return busiForm;
	}
	
	/** 
	 * @Title: saveData 
	 * @Description: 保存自由还款特批
	 * @author wangrongwei
	 * @param caseApply 案件
	 * @param busiForm 
	 * @param specialApproveManage 特批管理
	 * @return  
	 * @throws Exception 
	 */ 
	public BusiForm saveOrUpdateApply(CaseApply caseApply,BusiForm busiForm,SpecialApproveManage specialApproveManage,String remark) throws Exception{
		specialApproveManage.setRemark(remark);
		if (ObjectHelper.isEmpty(specialApproveManage.getId())) {
			specialApproveManage.setSpecialApproveType(2);//设置特批类型为自由还款特批
			//设置关联
			specialApproveManage.setCaseApply(caseApply);
			specialApproveManage.setCreateBy(ced.getLoginUser().getId());
			specialApproveManage.setSpecialApproveStatus(SpecialApproveManage.SPECIAL_APPROVE_STATUS_DRAFT);
		}
		
        //保存自由还款特批管理
        specialApproveManage = specialApproveManageService.saveOrUpdateEntity(specialApproveManage);
        if (ObjectHelper.isEmpty(busiForm.getId())) {
        	logger.info("开始保存busiForm......");
        	//保存busiForm
        	busiForm.setBusinessEntityId(specialApproveManage.getId());
        	busiForm.setComponentsEntityId(caseApply.getId());
        	busiForm.setComponentsEntityName(CaseApply.class.getSimpleName());
        	busiForm.setBusinessEntityName(SpecialApproveManage.class.getSimpleName());
        	if (busiForm.getFormStatus() == null) {
        		busiForm.setFormStatus(BusiFormStatus.DRAFT.value);//草稿
        	}
        	//面签申请：标题 案件号+主借人+特批类型      业务编号：案件号 
    		List<BeforeCustomer> caseApplyIdAndJoinType = caseApplyBeforeCustomerService.findCustomerByCaseApplyIdAndJoinType(caseApply.getId(), CaseApplyBeforeCustomer.MAIN_BORROW);
    		if (ObjectHelper.isNotEmpty(caseApplyIdAndJoinType)&&caseApplyIdAndJoinType.size()>0) {
    			busiForm.setApplyTitle(caseApply.getCaseApplyCode()+caseApplyIdAndJoinType.get(0).getCustomerName()+caseApply.getProductSubtypeName());
    		}
        	busiForm.setBusinessCode(caseApply.getCaseApplyCode());
        	busiForm.setModelType(ApplyModelTypeEnum.FREE_SPECIALAPPROVE.value);
        	busiForm = busiFormService.saveBusiForm(busiForm);
        	//设置busiForm关联
        	specialApproveManage.setBusiForm(busiForm);
        	logger.info("保存busiForm结束......");
		}
		return busiForm;
	}

	@Override
	public SpecialApproveManage findByCaseApplyIdAndSpecialApproveTypeAndSpecialApproveStatus(String caseApplyId,
			int specialApproveType, String specialApproveStatus) {
		String empId;
		try {
			empId = ced.getLoginUser().getId();
		} catch (Exception e) {
			empId = "";
			logger.info("查询草稿状态特批---获取当前登录人失败");
			e.printStackTrace();
		}
		return SpecialApproveManageRepository.findByCaseApplyIdAndSpecialApproveTypeAndSpecialApproveStatusAndCreateBy(caseApplyId, specialApproveType, specialApproveStatus,empId);
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public void specialApproveAbandoned(String businessKey) throws BusinessException {
		SpecialApproveManage approveManage = specialApproveManageService.findOne(businessKey);
		if (1==approveManage.getSpecialApproveType()) {
			for (SpecialApproveThings sat : approveManage.getListSpecialApproveThings()) {
				riskRulesService.findOne(sat.getExpRiskRulesId()).setSpecialStatus("");
			}
		}
		if (3==approveManage.getSpecialApproveType()) {
			for (SpecialApproveFee saf : approveManage.getListSpecialApproveFee()) {
				feeRulesService.findOne(saf.getExpFeeRulesId()).setSpecialStatus("");
			}
		}
		approveManage.setSpecialApproveStatus(SpecialApproveManage.SPECIAL_APPROVE_STATUS_ABANDONED);
		approveManage.getBusiForm().setFormStatus(BusiFormStatus.SCRAPPED.value);
	}

	@Override
	public Boolean validateSysSpecProcessStatus(String caseApplyId, String specialApproveType) throws BusinessException {
		if ("1".equals(specialApproveType)) {//风险特批
			List<RiskRules> list = riskRulesService.findByCaseApplyIdAndSpecialStatus(caseApplyId, "");
			if (ObjectHelper.isNotEmpty(list)) {
				return true;
			}
		}else if ("3".equals(specialApproveType)) {//费用特批
			List<FeeRules> list = feeRulesService.findFeeRulesInfoByCaseApplyIdAndSpecialStatus(caseApplyId, "");
			if (ObjectHelper.isNotEmpty(list)) {
				return true;
			}
		}else {
			throw new BusinessException("特批类型有误");
		}
		return false;
	}
	
}
