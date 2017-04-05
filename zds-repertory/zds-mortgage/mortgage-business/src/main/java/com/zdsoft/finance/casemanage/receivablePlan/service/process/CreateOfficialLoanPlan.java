package com.zdsoft.finance.casemanage.receivablePlan.service.process;

import java.util.List;

import org.slf4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zdsoft.bpm.annotation.client.AutoJob;
import com.zdsoft.finance.casemanage.receivablePlan.entity.ReceivableInfo;
import com.zdsoft.finance.casemanage.receivablePlan.entity.ReceivablePlan;
import com.zdsoft.finance.casemanage.receivablePlan.entity.ReceivablePlanHistory;
import com.zdsoft.finance.casemanage.receivablePlan.entity.ReceivablePlanTemp;
import com.zdsoft.finance.casemanage.receivablePlan.service.ReceivableInfoService;
import com.zdsoft.finance.casemanage.receivablePlan.service.ReceivablePlanHistoryService;
import com.zdsoft.finance.casemanage.receivablePlan.service.ReceivablePlanService;
import com.zdsoft.finance.casemanage.receivablePlan.service.ReceivablePlanTempService;
import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.framework.core.common.annotation.Log;
import com.zdsoft.framework.core.common.dto.DelegateExecution;
import com.zdsoft.framework.core.common.service.JavaDelegate;
import com.zdsoft.framework.core.common.util.ObjectHelper;


/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title: CreateOfficialLoanPlan.java 
 * @ClassName: CreateOfficialLoanPlan 
 * @Description: 还款计划进入正式还款计划
 * @author gufeng 
 * @date 2017年3月8日 下午3:53:54 
 * @version V1.0
 */
@Service
@AutoJob(label = "还款计划进入正式还款计划", resource = "com.zdsoft.finance.loan.service.process.createOfficialLoanPlan")
@Lazy(false)
public class CreateOfficialLoanPlan implements JavaDelegate{
	
	@Log
	private Logger logger;
	@Autowired
	private ReceivableInfoService receivableInfoService;
	@Autowired
	private ReceivablePlanService receivablePlanService;
	@Autowired
	private ReceivablePlanHistoryService receivablePlanHistoryService;
	@Autowired
	private ReceivablePlanTempService receivablePlanTempService;
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public void execute(DelegateExecution de) throws Exception {
		String busiId = (String)de.getVariable("businessKey");
		if(ObjectHelper.isEmpty(busiId)){
			logger.info("10000000001","=CreateOfficialLoanPlan====流程businessKey不存在");
			throw new BusinessException("10000000001", "=CreateOfficialLoanPlan====流程businessKey不存在");
		}
		//基本信息
		ReceivableInfo info = receivableInfoService.findOne(busiId);
		if(ObjectHelper.isEmpty(info) || ObjectHelper.isEmpty(info.getCaseApplyId())){
			logger.info("10000000002","=ReceivableInfo==或CaseApplyId==不存在");
			throw new BusinessException("10000000001", "=ReceivableInfo==或CaseApplyId==不存在");
		}
		//原还款计划
		List<ReceivablePlan> list = receivablePlanService.findByCaseApplyId(info.getCaseApplyId());
		//存入历史
		if(ObjectHelper.isNotEmpty(list)){
			for (ReceivablePlan receivablePlan : list) {
				ReceivablePlanHistory hi = new ReceivablePlanHistory();
				BeanUtils.copyProperties(receivablePlan, hi, new String[]{"id","createTime","updateTime"});
				receivablePlanHistoryService.saveEntity(hi);
				receivablePlanService.delete(receivablePlan.getId());
			}
		}
		//还款计划临时
		List<ReceivablePlanTemp> tempList = receivablePlanTempService.findReceivablePlanTempByReceivableInfoId(busiId);
		if(ObjectHelper.isNotEmpty(tempList)){
			for (ReceivablePlanTemp temp : tempList) {
				ReceivablePlan bean = new ReceivablePlan();
				BeanUtils.copyProperties(temp, bean, new String[]{"id"});
				receivablePlanService.saveEntity(bean);
				receivablePlanTempService.logicDelete(temp);
			}
		}
	}

}
