package com.zdsoft.finance.finance.service.process;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zdsoft.bpm.annotation.client.AutoJob;
import com.zdsoft.finance.finance.entity.RequestFunds;
import com.zdsoft.finance.finance.entity.RequestFundsDetail;
import com.zdsoft.finance.finance.service.RequestFundsDetailService;
import com.zdsoft.finance.finance.service.RequestFundsService;
import com.zdsoft.framework.core.common.dto.DelegateExecution;
import com.zdsoft.framework.core.common.service.JavaDelegate;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title: RequestFundsAbolishStatusAutoJob.java 
 * @ClassName: RequestFundsAbolishStatusAutoJob 
 * @Description: "案件请款不同意事项
 * @author jincheng 
 * @date 2017年2月20日 下午5:45:06 
 * @version V1.0
 */
@Service
@AutoJob(label = "案件请款不同意事项", resource = "com.zdsoft.finance.fee.service.process.requestFundsAbolishStatusAutoJob")
@Lazy(false)
public class RequestFundsAbolishStatusAutoJob implements JavaDelegate  {

	 @Autowired
	 private RequestFundsDetailService requestFundsDetailService;
	 
	 @Autowired
	 private RequestFundsService requestFundsService;
	
	@Override
	@Transactional(rollbackFor=Exception.class)
	 public void execute(DelegateExecution delegateExecution) throws Exception {
		// 流程业务id 
		String businessId = (String) delegateExecution.getVariable("businessKey");
		RequestFunds funds=requestFundsService.findOne(businessId);
		
		List<RequestFundsDetail> fundsDetailList=requestFundsDetailService.findRequestFundsDetailByReqFundsId(businessId);
		for(RequestFundsDetail fundsDetail:fundsDetailList){
			requestFundsDetailService.deleteRequestFundsDetailById(fundsDetail.getId());
		}
		funds.setApplyState(3);
		requestFundsService.updateEntity(funds);
	}

}
