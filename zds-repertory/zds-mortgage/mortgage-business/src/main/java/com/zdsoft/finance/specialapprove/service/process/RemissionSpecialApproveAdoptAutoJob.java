package com.zdsoft.finance.specialapprove.service.process;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zdsoft.bpm.annotation.client.AutoJob;
import com.zdsoft.finance.busiform.entity.BusiFormStatus;
import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.finance.finance.entity.RepaymentAmountAllot;
import com.zdsoft.finance.finance.service.RepaymentAmountAllotService;
import com.zdsoft.finance.specialapprove.entity.SpecialApproveManage;
import com.zdsoft.finance.specialapprove.entity.SpecialApproveRemission;
import com.zdsoft.finance.specialapprove.service.SpecialApproveManageService;
import com.zdsoft.framework.core.common.dto.DelegateExecution;
import com.zdsoft.framework.core.common.service.JavaDelegate;
import com.zdsoft.framework.core.common.util.ObjectHelper;

/** 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: RemissionSpecialApproveAutoJob.java 
 * @ClassName: RemissionSpecialApproveAutoJob 
 * @Description: 通过自由还款特批自动事项
 * @author wangrongwei
 * @date 2017年2月25日 上午10:27:57 
 * @version V1.0 
 */ 
@Service
@AutoJob(resource="com.zdsoft.finance.specialApprove.adoptRemissionSpecialApprove",label="费用减免特批通过")
@Lazy(false)
public class RemissionSpecialApproveAdoptAutoJob implements JavaDelegate {

	private Logger logger = LoggerFactory.getLogger(RemissionSpecialApproveAdoptAutoJob.class);
	
	@Autowired
	private SpecialApproveManageService specialApproveManageService;
	
	@Autowired
	private RepaymentAmountAllotService repaymentAmountAllotService;
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public void execute(DelegateExecution exec) throws Exception {
		logger.info("开始执行 通过费用减免特批自动事项...");
		String specialApproveId = ObjectHelper.isEmpty(exec.getVariable("businessKey"))?"":exec.getVariable("businessKey").toString();
		logger.info("业务ID：" + specialApproveId);
		
		SpecialApproveManage approveManage = specialApproveManageService.findOne(specialApproveId);
		if (ObjectHelper.isNotEmpty(approveManage)) {
			List<SpecialApproveRemission> remissionList = approveManage.getListFeeRemission();
			if (ObjectHelper.isNotEmpty(remissionList)) {
				for (SpecialApproveRemission specialApproveRemission : remissionList) {
					RepaymentAmountAllot allot = repaymentAmountAllotService.findOne(specialApproveRemission.getRepaymentAmountId());
					RepaymentAmountAllot newAllot = new RepaymentAmountAllot();
					BeanUtils.copyProperties(allot, newAllot);
					newAllot.setId("");
					newAllot.setPaidType(2);
					if (SpecialApproveManage.AMOUNT_TYPE_PENALTY.equals(specialApproveRemission.getBeRemissionItemCode())) {
						//罚息
						if ("0".equals(specialApproveRemission.getPenaltyUseStandard())) {//当前本金
							newAllot.setCurrentPaidPenalty(specialApproveRemission.getAnnulAmount());
						}else if("1".equals(specialApproveRemission.getPenaltyUseStandard())){//剩余本金
							newAllot.setPaidPenalty(specialApproveRemission.getAnnulAmount());
						}
					}else if (SpecialApproveManage.AMOUNT_TYPE_PLANDAMAGES.equals(specialApproveRemission.getBeRemissionItemCode())) {
						//违约金
						newAllot.setPaidDamages(specialApproveRemission.getAnnulAmount());
					}
					//写入实收
					repaymentAmountAllotService.saveEntity(newAllot);
				}
				
			}
			
			approveManage.setSpecialApproveStatus(SpecialApproveManage.SPECIAL_APPROVE_STATUS_ADOPT);
			approveManage.getBusiForm().setFormStatus(BusiFormStatus.THROUAPPROVAL.value);
		}else{
			logger.info("----特批管理ID不存在或为空----");
			throw new BusinessException("数据异常");
		}
	}
}
