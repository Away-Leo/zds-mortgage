package com.zdsoft.finance.casemanage.receivablePlan.service;

import java.util.List;

import com.zdsoft.finance.base.service.BaseService;
import com.zdsoft.finance.casemanage.receivablePlan.entity.ReceivablePlanHistory;
import com.zdsoft.finance.common.exception.BusinessException;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title: ReceivablePlanHistoryService.java 
 * @ClassName: ReceivablePlanHistoryService 
 * @Description: 还款计划历史接口
 * @author jincheng 
 * @date 2017年2月16日 上午11:24:32 
 * @version V1.0
 */
public interface ReceivablePlanHistoryService extends BaseService<ReceivablePlanHistory> {

	/**
	 * @Title: saveReceivablePlanHistory 
	 * @Description: 保存还款计划历史
	 * @author jincheng 
	 * @param planList
	 * @throws BusinessException
	 */
	public void saveReceivablePlanHistory(List<ReceivablePlanHistory> planList)throws BusinessException;
}
