package com.zdsoft.finance.casemanage.receivablePlan.service.process;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zdsoft.bpm.annotation.client.AutoJob;
import com.zdsoft.finance.casemanage.receivablePlan.entity.ReceivableInfo;
import com.zdsoft.finance.casemanage.receivablePlan.repository.ReceivableInfoRepository;
import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.framework.core.common.annotation.Log;
import com.zdsoft.framework.core.common.dto.DelegateExecution;
import com.zdsoft.framework.core.common.service.JavaDelegate;
import com.zdsoft.framework.core.common.util.ObjectHelper;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: GroupConfirmation.java 
 * @ClassName: GroupConfirmation 
 * @Description: 还款计划-集团确认
 * @author zhoushichao 
 * @date 2017年3月13日 下午9:32:13 
 * @version V1.0
 */
@Service
@AutoJob(label = "还款计划-集团确认", resource = "com.zdsoft.finance.loan.service.process.groupConfirmation")
@Lazy(false)
public class GroupConfirmation implements JavaDelegate{
	@Log
	private Logger logger;
	@Autowired
	ReceivableInfoRepository receivableInfoRepository;
	
	@Override
	@Transactional(rollbackFor=Exception.class)
	public void execute(DelegateExecution execution) throws Exception {
		String busiId = (String)execution.getVariable("businessKey");
		if(ObjectHelper.isEmpty(busiId)){
			logger.info("10000000001","=ReceivableInfo====流程businessKey不存在");
			throw new BusinessException("10000000001", "=ReceivableInfo====流程businessKey不存在");
		}
		//基本信息
		ReceivableInfo info = receivableInfoRepository.findOne(busiId);
		//设置集团确认为已确认
		info.setGroupConfirmation(ReceivableInfo.ALREADY_CONFIRMED);
		receivableInfoRepository.updateEntity(info);
	}

}
