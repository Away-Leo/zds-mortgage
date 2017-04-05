package com.zdsoft.finance.capital.service;

import java.util.List;
import java.util.Map;

import com.zdsoft.finance.base.service.BaseService;
import com.zdsoft.finance.capital.entity.CreditCostTracking;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * 
 * @Title: CreditCostTrackingService.java
 * @ClassName: CreditCostTrackingService
 * @Description: 应付资金跟踪service
 * @author liuwei
 * @date 2017年2月8日 上午10:33:45
 * @version V1.0
 */
public interface CreditCostTrackingService extends BaseService<CreditCostTracking> {

	/**
	 * 
	 * @Title: saveOrUpdateCostTracking
	 * @Description: 保存或修改应付资金跟踪
	 * @author liuwei
	 * @param creditCostTracking
	 *            应付资金跟踪信息
	 * @return 应付资金跟踪信息
	 * @throws Exception
	 *             调用平台接口出现Exception
	 */
	public CreditCostTracking saveOrUpdateCostTracking(CreditCostTracking creditCostTracking) throws Exception;

	/**
	 * 
	 * @Title: findByTempUuid
	 * @Description: 通过临时uuid查找信息
	 * @author liuwei
	 * @param tempUuid
	 *            临时id
	 * @return 应付资金跟踪列表
	 */
	public List<CreditCostTracking> findByTempUuid(String tempUuid);

	/**
	 * 
	 * @Title: findByConditions
	 * @Description: 通过查询条件查询应付资金跟踪
	 * @author liuwei
	 * @param conditions
	 *            查询条件
	 * @return 应付资金跟踪列表
	 */
	public List<CreditCostTracking> findByConditions(Map<String, Object> conditions);

}
