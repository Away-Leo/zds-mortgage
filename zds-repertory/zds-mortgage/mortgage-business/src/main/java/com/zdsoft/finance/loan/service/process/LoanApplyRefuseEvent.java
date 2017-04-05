package com.zdsoft.finance.loan.service.process;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zdsoft.bpm.annotation.client.AutoJob;
import com.zdsoft.finance.loan.entity.LoanApply;
import com.zdsoft.finance.loan.repository.LoanApplyRepository;
import com.zdsoft.framework.core.common.dto.DelegateExecution;
import com.zdsoft.framework.core.common.service.JavaDelegate;
/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title: LoanApplyRefuseEvent.java 
 * @ClassName: LoanApplyRefuseEvent 
 * @Description: 放款流程未通过的后置事件
 * @author huangwei 
 * @date 2017年3月29日 下午3:26:27 
 * @version V1.0
 */
	@Service
	@AutoJob(label = "放款申请未通过后置事件", resource = "com.zdsoft.finance.loan.service.process.loanApplyRefuseEvent.autoJob")
	@Lazy(false)
	public class LoanApplyRefuseEvent implements JavaDelegate{
		@Autowired
		LoanApplyRepository loanApplyRepository;
		@Transactional(rollbackFor=Exception.class)
		@Override
		public void execute(DelegateExecution execution) throws Exception {
			String busiId=(String)execution.getVariable("businessKey");
			LoanApply apply=loanApplyRepository.findOne(busiId);
			//设置请款状态为待放款
			apply.setStatus("6");
			loanApplyRepository.updateEntity(apply);
		}

	}


