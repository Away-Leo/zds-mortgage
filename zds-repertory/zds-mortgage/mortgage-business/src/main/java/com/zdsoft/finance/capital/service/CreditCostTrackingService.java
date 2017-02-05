package com.zdsoft.finance.capital.service;

import java.util.List;
import java.util.Map;

import com.zdsoft.finance.base.service.BaseService;
import com.zdsoft.finance.capital.entity.CreditCostTracking;

/**
 * 应付资金跟踪service
 * 
 * @createTime:2017年1月10日
 * @author liuwei
 * @version 1.0
 */
public interface CreditCostTrackingService extends BaseService<CreditCostTracking> {

	/**
	 * 保存或修改应付资金跟踪
	 * 
	 * @param creditCostTracking
	 *            应付资金跟踪信息
	 * @return 应付资金跟踪信息
	 * @throws Exception
	 *             调用平台接口出现Exception
	 */
	public CreditCostTracking saveOrUpdateCostTracking(CreditCostTracking creditCostTracking) throws Exception;

	/**
	 * 通过临时uuid查找信息
	 * 
	 * @param tempUuid
	 *            临时id
	 * @return 应付资金跟踪列表
	 */
	public List<CreditCostTracking> findByTempUuid(String tempUuid);

	/**
	 * 通过查询条件查询应付资金跟踪
	 * 
	 * @param conditions
	 *            查询条件
	 * @return 应付资金跟踪列表
	 */
	public List<CreditCostTracking> findByConditions(Map<String, Object> conditions);

}
