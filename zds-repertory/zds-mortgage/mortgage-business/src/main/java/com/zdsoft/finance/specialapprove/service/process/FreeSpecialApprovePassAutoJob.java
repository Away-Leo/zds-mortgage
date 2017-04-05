package com.zdsoft.finance.specialapprove.service.process;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zdsoft.bpm.annotation.client.AutoJob;
import com.zdsoft.finance.busiform.entity.BusiFormStatus;
import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.finance.specialapprove.entity.SpecialApproveManage;
import com.zdsoft.finance.specialapprove.service.SpecialApproveManageService;
import com.zdsoft.framework.core.common.dto.DelegateExecution;
import com.zdsoft.framework.core.common.service.JavaDelegate;
import com.zdsoft.framework.core.common.util.ObjectHelper;

/** 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: FreeSpecialApproveAutoJob.java 
 * @ClassName: FreeSpecialApproveAutoJob 
 * @Description: 不通过自由还款特批自动事项
 * @author wangrongwei
 * @date 2017年2月25日 上午10:27:57 
 * @version V1.0 
 */ 
@Service
@AutoJob(resource="com.zdsoft.finance.specialApprove.passFreeSpecialApprove",label="自由还款特批不通过")
public class FreeSpecialApprovePassAutoJob implements JavaDelegate {

	private Logger logger = LoggerFactory.getLogger(FreeSpecialApprovePassAutoJob.class);
	
	@Autowired
	private SpecialApproveManageService specialApproveManageService;
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public void execute(DelegateExecution exec) throws Exception {
		logger.info("开始执行自由还款特批不通过 自动事项...");
		String specialApproveId = ObjectHelper.isEmpty(exec.getVariable("businessKey"))?"":exec.getVariable("businessKey").toString();
		logger.info("业务ID：" + specialApproveId);
		
		SpecialApproveManage approveManage = specialApproveManageService.findOne(specialApproveId);
		if (ObjectHelper.isNotEmpty(approveManage)) {
			approveManage.setSpecialApproveStatus(SpecialApproveManage.SPECIAL_APPROVE_STATUS_NOT_ADOPT);
			approveManage.getBusiForm().setFormStatus(BusiFormStatus.NOTAPPROVALED.value);
		}else{
			logger.info("----特批管理ID不存在或为空----");
			throw new BusinessException("数据异常");
		}
	}
}
