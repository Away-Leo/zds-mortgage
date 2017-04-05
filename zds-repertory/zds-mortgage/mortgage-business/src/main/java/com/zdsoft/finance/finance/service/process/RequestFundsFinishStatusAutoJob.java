package com.zdsoft.finance.finance.service.process;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zdsoft.bpm.annotation.client.AutoJob;
import com.zdsoft.finance.finance.entity.RequestFunds;
import com.zdsoft.finance.finance.service.RequestFundsService;
import com.zdsoft.framework.core.common.dto.DelegateExecution;
import com.zdsoft.framework.core.common.service.JavaDelegate;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title: RequestFundsFinishStatusAutoJob.java 
 * @ClassName: RequestFundsFinishStatusAutoJob 
 * @Description: 案件请款同意事项
 * @author jincheng 
 * @date 2017年2月20日 下午5:48:03 
 * @version V1.0
 */
@Service
@AutoJob(label = "案件请款同意事项", resource = "com.zdsoft.finance.fee.service.process.requestFundsFinishStatusAutoJob")
@Lazy(false)
public class RequestFundsFinishStatusAutoJob implements JavaDelegate {
	
//	 @Autowired
//	 private FeeInfomationService feeInfomationService;
//	 
//	 @Autowired
//	 private RequestFundsDetailService requestFundsDetailService;
	 
	 @Autowired
	 private RequestFundsService requestFundsService;

	@Override
	@Transactional(rollbackFor=Exception.class)
	 public void execute(DelegateExecution delegateExecution) throws Exception {
		// 流程业务id 
		String businessId = (String) delegateExecution.getVariable("businessKey");
		
		RequestFunds funds=requestFundsService.findOne(businessId);
		
//		List<RequestFundsDetail> fundsDetailList=requestFundsDetailService.findRequestFundsDetailByReqFundsId(businessId);
//
//		for(RequestFundsDetail fundsDetail:fundsDetailList){
//			List<FeeInfomation> feeList=feeInfomationService.findByCaseApplyIdAndFeeItemAndFeeType(fundsDetail.getCaseApplyId(), fundsDetail.getFeeItem(), fundsDetail.getFeeType());
//			for(FeeInfomation fee:feeList){
//				BigDecimal paidAmount=BigDecimal.ZERO;
//				if(ObjectHelper.isNotEmpty(fee.getPaidAmount())){
//					paidAmount=fee.getPaidAmount();
//				}
//				fee.setPaidAmount(paidAmount.add(fundsDetail.getReqFundsAmount()));
//				fee.setBalanceAmount(fee.getReceivedAmount().subtract(fee.getPaidAmount()));
//				feeInfomationService.updateEntity(fee);
//			}
//		}
		
		funds.setApplyState(2);
		requestFundsService.updateEntity(funds);
	}
}
