package com.zdsoft.finance.specialapprove.service.impl;

import java.util.ArrayList;
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
import com.zdsoft.finance.specialapprove.entity.SpecialApproveFee;
import com.zdsoft.finance.specialapprove.entity.SpecialApproveManage;
import com.zdsoft.finance.specialapprove.service.FeeRulesService;
import com.zdsoft.finance.specialapprove.service.SpecialApproveFeeService;
import com.zdsoft.finance.specialapprove.service.SpecialApproveManageService;
import com.zdsoft.framework.core.common.util.ObjectHelper;

/** 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: SpecialApproveFeeServiceImpl.java 
 * @ClassName: SpecialApproveFeeServiceImpl 
 * @Description: 费用特批实现
 * @author wangrongwei
 * @date 2017年3月6日 下午4:05:50 
 * @version V1.0 
 */ 
@Service
public class SpecialApproveFeeServiceImpl
		extends BaseServiceImpl<SpecialApproveFee, CustomRepository<SpecialApproveFee, String>>
		implements SpecialApproveFeeService {
	
	@Autowired
	private SpecialApproveManageService specialApproveManageService;
	
	@Autowired
	private FeeRulesService feeRulesService;
	
	@Autowired
	private CaseApplyService caseApplyService;
	
	@Autowired
	private CaseApplyBeforeCustomerService caseApplyBeforeCustomerService;
	
	@Autowired
	private BusiFormService busiFormService;
	
	@Autowired
	private ProcessConfigService processConfigService;
	
	@Autowired
	private CED ced;

	@Override
	@Transactional(rollbackFor=Exception.class)
	public BusiForm saveOrCommitFeeSpecialApproveApply(String caseApplyId, String specialApproveManageId, String remark,
			Boolean isSubmit) throws Exception {
		BusiForm busiForm = new BusiForm();
		if (ObjectHelper.isEmpty(caseApplyId)) {
			throw new BusinessException("案件ID为空");
		}
		CaseApply caseApply = caseApplyService.findOne(caseApplyId);
		logger.info("保存提交费用特批申请，案件ID为：" + caseApplyId+" ------ 特批管理ID为：" + specialApproveManageId);
		
		if (ObjectHelper.isNotEmpty(specialApproveManageId)) {
			SpecialApproveManage approveManage = specialApproveManageService.findOne(specialApproveManageId);
			approveManage.setRemark(remark);
			busiForm = approveManage.getBusiForm();
		}else{
			busiForm = this.saveData(caseApply, busiForm, remark);
		}
		
		//启动工作流
		if (isSubmit) {
			ProcessConfig processConfig = processConfigService
					.findByProductIdAndProcessCode(caseApply.getProductSubtypeId(), SpecialApproveManage.PROCESS_FEE_CODE);
			if (ObjectHelper.isEmpty(processConfig)) {
				throw new BusinessException("流程未配置");
			}
			List<FeeRules> listFeeRules = feeRulesService.findFeeRulesInfoByCaseApplyIdAndSpecialStatus(caseApply.getId(),SpecialApproveManage.SPECIAL_APPROVE_STATUS_DRAFT_SYS);
			for (FeeRules feeRules : listFeeRules) {
				//设置状态为审批中
				feeRules.setSpecialStatus(SpecialApproveManage.SPECIAL_APPROVE_STATUS_APPROVE);
			}
			SpecialApproveManage approveManage = specialApproveManageService.findOne(specialApproveManageId);
			//设置状态为审批中
			approveManage.setSpecialApproveStatus(SpecialApproveManage.SPECIAL_APPROVE_STATUS_APPROVE);
			String processKey = processConfig.getProcessKey();
			busiForm.setProcessKey(processKey);
			busiForm = busiFormService.startProcess(busiForm, new HashMap<>(), new HashMap<>());
		}
		return busiForm;
	}

	/** 
	 * @Title: saveData 
	 * @Description: 保存费用特批申请 
	 * @author wangrongwei
	 * @param caseApply 案件ID
	 * @param busiForm 业务表单
	 * @param remark 备注
	 * @throws Exception  
	 */ 
	private BusiForm saveData(CaseApply caseApply,BusiForm busiForm,String remark) throws Exception{
		logger.info("开始保存费用特批申请......");
		SpecialApproveManage specialApproveManage=new SpecialApproveManage();
		specialApproveManage.setSystem(true);
		specialApproveManage.setSpecialApproveType(3);//设置类型为费用特批
		specialApproveManage.setRemark(remark);
		specialApproveManage.setCaseApply(caseApply);
		specialApproveManage.setCreateBy(ced.getLoginUser().getId());
		specialApproveManage.setSpecialApproveStatus(SpecialApproveManage.SPECIAL_APPROVE_STATUS_DRAFT_SYS);
		
		//保存费用特批管理
        specialApproveManage = specialApproveManageService.saveEntity(specialApproveManage);
		
        logger.info("开始保存busiForm......");
		//保存busiForm
		busiForm.setBusinessEntityId(specialApproveManage.getId());
		busiForm.setComponentsEntityId(caseApply.getId());
		busiForm.setComponentsEntityName(CaseApply.class.getSimpleName());
		busiForm.setBusinessEntityName(SpecialApproveManage.class.getSimpleName());
		if (busiForm.getFormStatus() == null) {
			busiForm.setFormStatus(BusiFormStatus.DRAFT.value);
		}
		//面签申请：标题 案件号+主借人+特批类型      业务编号：案件号 
		List<BeforeCustomer> caseApplyIdAndJoinType = caseApplyBeforeCustomerService.findCustomerByCaseApplyIdAndJoinType(caseApply.getId(), CaseApplyBeforeCustomer.MAIN_BORROW);
		if (ObjectHelper.isNotEmpty(caseApplyIdAndJoinType)&&caseApplyIdAndJoinType.size()>0) {
			busiForm.setApplyTitle(caseApply.getCaseApplyCode()+caseApplyIdAndJoinType.get(0).getCustomerName()+caseApply.getProductSubtypeName());
		}
        busiForm.setBusinessCode(caseApply.getCaseApplyCode());
		busiForm.setModelType(ApplyModelTypeEnum.FEE_SPECIALAPPROVE.value);
        busiForm = busiFormService.saveBusiForm(busiForm);
        logger.info("保存busiForm结束......");
        logger.info("保存费用特批详情...");
        
        //查询系统触发的费用特批详情
		List<FeeRules> listFeeRules = feeRulesService.findFeeRulesInfoByCaseApplyIdAndSpecialStatus(caseApply.getId(),"");
		List<SpecialApproveFee> listSAFaa = new ArrayList<>();
		for (FeeRules feeRules : listFeeRules) {
			//更新费用详情状态为草稿
			feeRules.setSpecialStatus(SpecialApproveManage.SPECIAL_APPROVE_STATUS_DRAFT_SYS);
			feeRules = feeRulesService.updateEntity(feeRules);
			SpecialApproveFee approveFee=new SpecialApproveFee();
			approveFee.setReceiveFeeId(feeRules.getReceiveFeeId());
			approveFee.setStandardAmount(feeRules.getStandardAmount());
			approveFee.setExpFeeRulesId(feeRules.getId());//设置收费规则ID
			listSAFaa.add(approveFee);
		}
		specialApproveManage.setListSpecialApproveFee(listSAFaa);
		
		//设置busiForm关联
        specialApproveManage.setBusiForm(busiForm);
		
		logger.info("保存费用特批申请成功......");
		return busiForm;
	}
	
}
