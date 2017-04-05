package com.zdsoft.finance.finance.timer;

import java.util.List;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.finance.common.utils.TimeUtil;
import com.zdsoft.finance.finance.service.CustomerReceivableService;
import com.zdsoft.finance.marketing.entity.CaseApply;
import com.zdsoft.finance.marketing.entity.CaseApplyStageEnumSimpleCodeEnum;
import com.zdsoft.finance.marketing.service.CaseApplyService;
import com.zdsoft.framework.core.common.annotation.Log;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title: CustomerReceivableTimmer.java 
 * @ClassName: CustomerReceivableTimmer 
 * @Description: 客户应还跑批
 * @author jincheng 
 * @date 2017年2月27日 下午5:44:19 
 * @version V1.0
 */
@Component
public class CustomerReceivableTimmer {
	@Log
	private Logger log;
	
	@Autowired
	private CaseApplyService caseApplyService;
	
	@Autowired
	private CustomerReceivableService customerReceivableService;
	 
	
	@Scheduled(cron = "0 0 2 * * ?") //  每天凌晨2点
//	@Scheduled(cron = "0 */3 * * * ?") //  每三分一次
	public void batchRun(){
		try {
			String stage= CaseApplyStageEnumSimpleCodeEnum.LOAN_ALREADY.value;
			List<CaseApply> applyList=caseApplyService.findByStage(stage);
			Long batchDay=TimeUtil.getCurrentDateInteger().longValue();
			customerReceivableService.caseApplyBatch(applyList, batchDay);
		} catch (BusinessException e) {
			e.printStackTrace();
		}
    }

}
