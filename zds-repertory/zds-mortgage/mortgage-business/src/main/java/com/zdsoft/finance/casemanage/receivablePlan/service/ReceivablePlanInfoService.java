package com.zdsoft.finance.casemanage.receivablePlan.service;

import java.util.List;
import java.util.Map;

import com.zdsoft.finance.base.service.BaseService;
import com.zdsoft.finance.casemanage.receivablePlan.entity.ReceivableInfo;
import com.zdsoft.finance.casemanage.receivablePlan.entity.ReceivablePlanTemp;
import com.zdsoft.finance.common.exception.BusinessException;

/**
* 版权所有：重庆正大华日软件有限公司   
* @Title: ReceivablePlanInfoService.java 
* @Package com.zdsoft.finance.casemanage.receivablePlan.service 
* @Description: 还款计划信息service（资调）
* @author zjx  
* @date 2017年2月22日 下午4:27:49 
* @version V1.0   
*/
public interface ReceivablePlanInfoService extends BaseService<ReceivableInfo>{
	/** 
	 * @Title: receivablePlanGenerate 
	 * @Description: 根据还款计划编写的基本信息调用计划生成算出还款计划
	 * @author zjx 
	 * @param receivableInfo还款计划基本信息 
	 * @return
	 * @throws BusinessException  
	 */ 
	public List<ReceivablePlanTemp> receivablePlanGenerate(ReceivableInfo receivableInfo) throws BusinessException;
	/** 
	 * @Title: calculateOtherRate 
	 * @Description: 通过贷款利率和单位和申请金额算出动态利率和综合利率
	 * @author zjx 
	 * @param info还款计划基本信息 
	 * @return
	 * @throws BusinessException  
	 */ 
	public Map<String, Object> calculateOtherRate(ReceivableInfo info) throws BusinessException;
}
