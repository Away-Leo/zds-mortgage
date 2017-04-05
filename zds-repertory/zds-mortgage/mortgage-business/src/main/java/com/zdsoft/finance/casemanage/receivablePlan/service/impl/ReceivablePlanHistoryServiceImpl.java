package com.zdsoft.finance.casemanage.receivablePlan.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.zdsoft.finance.base.service.impl.BaseServiceImpl;
import com.zdsoft.finance.casemanage.receivablePlan.entity.ReceivablePlanHistory;
import com.zdsoft.finance.casemanage.receivablePlan.repository.ReceivablePlanHistoryRepository;
import com.zdsoft.finance.casemanage.receivablePlan.service.ReceivablePlanHistoryService;
import com.zdsoft.finance.common.exception.BusinessException;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title: ReceivablePlanHistoryServiceImpl.java 
 * @ClassName: ReceivablePlanHistoryServiceImpl 
 * @Description: 还款计划历史接口实现
 * @author jincheng 
 * @date 2017年2月16日 上午11:27:09 
 * @version V1.0
 */
@Service("receivablePlanHistoryService")
public class ReceivablePlanHistoryServiceImpl extends BaseServiceImpl<ReceivablePlanHistory, ReceivablePlanHistoryRepository>implements ReceivablePlanHistoryService {

	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	public void saveReceivablePlanHistory(List<ReceivablePlanHistory> planList) throws BusinessException {
		for(ReceivablePlanHistory history:planList){
			this.saveEntity(history);
		}
	}

}
