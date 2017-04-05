package com.zdsoft.finance.specialapprove.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zdsoft.essential.client.service.CED;
import com.zdsoft.essential.dto.emp.EmpDto;
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
import com.zdsoft.finance.specialapprove.entity.SpecialApproveManage;
import com.zdsoft.finance.specialapprove.entity.SpecialApproveRemission;
import com.zdsoft.finance.specialapprove.service.SpecialApproveManageService;
import com.zdsoft.finance.specialapprove.service.SpecialApproveRemissionService;
import com.zdsoft.framework.core.common.util.ObjectHelper;

/** 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: SpecialApproveRemissionServiceImpl.java 
 * @ClassName: SpecialApproveRemissionServiceImpl 
 * @Description: 费用减免特批实现
 * @author wangrongwei
 * @date 2017年3月6日 下午4:06:32 
 * @version V1.0 
 */ 
@Service
public class SpecialApproveRemissionServiceImpl
		extends BaseServiceImpl<SpecialApproveRemission, CustomRepository<SpecialApproveRemission, String>>
		implements SpecialApproveRemissionService {
	
	@Autowired
	private CaseApplyService caseApplyService;
	
	@Autowired
	private SpecialApproveManageService specialApproveManageService;
	
	@Autowired
	private BusiFormService busiFormService;
	
	@Autowired
	private ProcessConfigService processConfigService;
	
	@Autowired
	private CaseApplyBeforeCustomerService caseApplyBeforeCustomerService;
	
	@Autowired
	private CED ced;

	@Override
	@Transactional(rollbackFor=Exception.class)
	public BusiForm saveOrCommitSpecialApproveRemissionApply(String caseApplyId, String specialApproveManageId,
			String remark, String penaltyUseStandard,List<SpecialApproveRemission> list, Boolean isSubmit) throws Exception {
		if (ObjectHelper.isEmpty(caseApplyId)) {
			throw new BusinessException("案件ID为空");
		}
		CaseApply caseApply = caseApplyService.findOne(caseApplyId);
		SpecialApproveManage specialApproveManage = null;
		BusiForm busiForm = null;
		if (ObjectHelper.isEmpty(caseApply)) {
			throw new BusinessException("案件ID不存在");
		}
		if (ObjectHelper.isNotEmpty(specialApproveManageId)) {
			specialApproveManage = specialApproveManageService.findOne(specialApproveManageId);
			busiForm = specialApproveManage.getBusiForm();
		}
		//保存
		busiForm = this.saveData(caseApply, busiForm, specialApproveManage,list, remark, penaltyUseStandard);
		
		if (isSubmit) {
			ProcessConfig processConfig = processConfigService
					.findByProductIdAndProcessCode(caseApply.getProductSubtypeId(), SpecialApproveManage.PROCESS_REMISSION_CODE);
			if (ObjectHelper.isEmpty(processConfig)) {
				throw new BusinessException("流程未配置");
			}
			specialApproveManage.setSpecialApproveStatus(SpecialApproveManage.SPECIAL_APPROVE_STATUS_APPROVE);//设置状态为审批中
			String processKey = processConfig.getProcessKey();
			busiForm.setProcessKey(processKey);
			busiForm = busiFormService.startProcess(busiForm, new HashMap<>(), new HashMap<>());//启动工作流
		}
		return busiForm;
	}
	
	/** 
	 * @Title: saveData 
	 * @Description: 保存费用减免特批申请
	 * @author wangrongwei
	 * @param caseApply 案件
	 * @param busiForm 
	 * @param specialApproveManage 特批管理
	 * @param remissionList 费用减免特批事项
	 * @param remark 备注
	 * @param penaltyUseStandard 罚息挂钩标准
	 * @return
	 * @throws Exception 
	 */ 
	private BusiForm saveData(CaseApply caseApply,BusiForm busiForm,SpecialApproveManage specialApproveManage,
			List<SpecialApproveRemission> remissionList,String remark,String penaltyUseStandard) throws Exception{
		logger.info("保存费用减免特批申请...");
		if (ObjectHelper.isEmpty(specialApproveManage)) {
			specialApproveManage = new SpecialApproveManage();
		}
		if (ObjectHelper.isEmpty(busiForm)) {
			busiForm = new BusiForm();
		}
		specialApproveManage.setRemark(remark);
		specialApproveManage.setPenaltyUseStandard(penaltyUseStandard);
		specialApproveManage.getListFeeRemission().clear();//清空费用减免特批事项
		specialApproveManage.setListFeeRemission(remissionList);//设置特批事项
        if (ObjectHelper.isEmpty(specialApproveManage.getId())) {
        	EmpDto emp = ced.getLoginUser();
        	specialApproveManage.setSpecialApproveType(4);
        	specialApproveManage.setCompanyName(emp.getCompanyNm());
        	specialApproveManage.setCreateByName(emp.getEmpNm());
        	specialApproveManage.setCreateOrgName(emp.getOrgNm());
        	specialApproveManage.setCaseApply(caseApply);
        	specialApproveManage.setCreateBy(ced.getLoginUser().getId());
        	specialApproveManage.setSpecialApproveStatus(SpecialApproveManage.SPECIAL_APPROVE_STATUS_DRAFT);//草稿
        }
		specialApproveManage = specialApproveManageService.saveOrUpdateEntity(specialApproveManage);
		
		if (ObjectHelper.isEmpty(busiForm.getId())) {
			logger.info("费用减免特批申请,保存busiForm...");
			//保存busiForm
			busiForm.setBusinessEntityId(specialApproveManage.getId());
			busiForm.setComponentsEntityId(caseApply.getId());
			busiForm.setComponentsEntityName(CaseApply.class.getSimpleName());
			busiForm.setBusinessEntityName(SpecialApproveManage.class.getSimpleName());
			busiForm.setFormStatus(BusiFormStatus.DRAFT.value);//草稿
			List<BeforeCustomer> caseApplyIdAndJoinType = caseApplyBeforeCustomerService.findCustomerByCaseApplyIdAndJoinType(caseApply.getId(), CaseApplyBeforeCustomer.MAIN_BORROW);
			//面签申请：标题 案件号+主借人+特批类型      业务编号：案件号 
			if (ObjectHelper.isNotEmpty(caseApplyIdAndJoinType)&&caseApplyIdAndJoinType.size()>0) {
				busiForm.setApplyTitle(caseApply.getCaseApplyCode()+caseApplyIdAndJoinType.get(0).getCustomerName()+caseApply.getProductSubtypeName());
			}
			busiForm.setBusinessCode(caseApply.getCaseApplyCode());
			busiForm.setModelType(ApplyModelTypeEnum.REMISSION_SPECIALAPPROVE.value);
			busiForm.setBusinessCode("P"+new Date().getTime());
			busiForm = busiFormService.saveOrUpdateBusiForm(busiForm);
			//设置busiForm关联
			specialApproveManage.setBusiForm(busiForm);
			logger.info("费用减免特批申请,保存busiForm结束......");
		}
        
		return busiForm;
	}
}
