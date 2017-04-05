package com.zdsoft.finance.specialapprove.service.process;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zdsoft.bpm.annotation.client.AutoJob;
import com.zdsoft.finance.busiform.entity.BusiFormStatus;
import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.finance.specialapprove.entity.FeeRules;
import com.zdsoft.finance.specialapprove.entity.SpecialApproveFee;
import com.zdsoft.finance.specialapprove.entity.SpecialApproveManage;
import com.zdsoft.finance.specialapprove.service.FeeRulesService;
import com.zdsoft.finance.specialapprove.service.SpecialApproveManageService;
import com.zdsoft.framework.core.common.dto.DelegateExecution;
import com.zdsoft.framework.core.common.service.JavaDelegate;
import com.zdsoft.framework.core.common.util.ObjectHelper;

/** 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: SpecialApproveManageAutoJob.java 
 * @ClassName: SpecialApproveManageAutoJob 
 * @Description: 通过费用特批自动事项
 * @author wangrongwei
 * @date 2017年2月23日 上午10:30:06 
 * @version V1.0 
 */ 
@Service
@AutoJob(resource="com.zdsoft.finance.specialApprove.adoptFeeSpecialApprove",label="费用特批通过")
public class FeeSpecialApproveAdoptAutoJob implements JavaDelegate {

	private Logger logger = LoggerFactory.getLogger(FeeSpecialApproveAdoptAutoJob.class);
	
	@Autowired
	private SpecialApproveManageService specialApproveManageService;
	
	@Autowired
	private FeeRulesService feeRulesService;
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public void execute(DelegateExecution exec) throws Exception {
		logger.info("开始执行通过费用特批...");
		String specialApproveId = ObjectHelper.isEmpty(exec.getVariable("businessKey"))?"":exec.getVariable("businessKey").toString();
		logger.info("业务ID：" + specialApproveId);
		
		SpecialApproveManage approveManage = specialApproveManageService.findOne(specialApproveId);
		if (ObjectHelper.isNotEmpty(approveManage)) {
			approveManage.setSpecialApproveStatus(SpecialApproveManage.SPECIAL_APPROVE_STATUS_ADOPT);
			approveManage.getBusiForm().setFormStatus(BusiFormStatus.THROUAPPROVAL.value);
			List<SpecialApproveFee> listFee = approveManage.getListSpecialApproveFee();
			if (ObjectHelper.isNotEmpty(listFee)) {
				logger.info("费用事项长度：" + listFee.size());
				for (SpecialApproveFee fee : listFee) {
					if (ObjectHelper.isNotEmpty(fee.getExpFeeRulesId()) && approveManage.isSystem()) {
						FeeRules feeRules = feeRulesService.findOne(fee.getExpFeeRulesId());
						feeRules.setSpecialStatus(SpecialApproveManage.SPECIAL_APPROVE_STATUS_ADOPT);
					}else if(approveManage.isSystem()){
						throw new BusinessException("更新系统触发费用特批状态数据异常");
					}
				}
			}
		}else{
			logger.info("----特批管理ID不存在或为空----");
			throw new BusinessException("数据异常");
		}
		
	}
}
