package com.zdsoft.finance.loan.service.process;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zdsoft.bpm.annotation.client.AutoJob;
import com.zdsoft.bpm.service.client.BPM;
import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.finance.common.utils.AmountConversionUtil;
import com.zdsoft.finance.loan.entity.LoanApply;
import com.zdsoft.finance.loan.service.LoanApplySerivce;
import com.zdsoft.framework.core.common.annotation.Log;
import com.zdsoft.framework.core.common.dto.DelegateExecution;
import com.zdsoft.framework.core.common.service.JavaDelegate;
import com.zdsoft.framework.core.common.util.ObjectHelper;


/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title: LoanProcessShunt.java 
 * @ClassName: LoanProcessShunt 
 * @Description: 请款金额判断
 * @author gufeng 
 * @date 2017年3月8日 下午3:51:39 
 * @version V1.0
 */
@Service
@AutoJob(label = "请款金额判断", resource = "com.zdsoft.finance.loan.service.process.loanProcessShunt")
@Lazy(false)
public class LoanProcessShunt implements JavaDelegate {

	@Log
	private Logger logger;
	@Autowired
	private BPM BPM;
	@Autowired
	private LoanApplySerivce loanApplySerivce;

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void execute(DelegateExecution de) throws Exception {
		String busiId = (String)de.getVariable("businessKey");
		String processInstanceId = (String)de.getVariable("processInstanceId");
		if(ObjectHelper.isEmpty(busiId) || ObjectHelper.isEmpty(processInstanceId)){
			logger.info("10000000001","=LoanProcessShunt====流程businessKey或processInstanceId不存在");
			throw new BusinessException("10000000001", "=LoanProcessShunt====流程businessKey或processInstanceId不存在");
		}
		LoanApply apply = loanApplySerivce.findOne(busiId);
		if(ObjectHelper.isEmpty(apply) || ObjectHelper.isEmpty(apply.getApplyAmount())){
			logger.info("10000000002","=LoanProcessShunt====还款申请不存在，或金额为空，id：" + busiId);
			throw new BusinessException("10000000002","=LoanProcessShunt====还款申请不存在，或金额为空，id：" + busiId);
		}
		BPM.setEngineVariable(processInstanceId, "process_amount", AmountConversionUtil.convertToWYuan(apply.getApplyAmount()).intValue()+"");
	}

}
