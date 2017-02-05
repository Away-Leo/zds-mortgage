package com.zdsoft.finance.capital.service;

import java.util.List;
import java.util.Map;

import com.zdsoft.finance.base.service.BaseService;
import com.zdsoft.finance.capital.entity.SpareCapital;

/**
 * 备付资金跟踪Service
 * 
 * @createTime:2017年1月10日
 * @author liuwei
 * @version 1.0
 */
public interface SpareCapitalService extends BaseService<SpareCapital> {

	/**
	 * 新增或修改备付资金跟踪
	 * 
	 * @param spareCapital
	 *            备付资金跟踪信息
	 * @return 备付资金跟踪信息
	 * @throws Exception
	 */
	public SpareCapital saveOrUpdateSpareCapital(SpareCapital spareCapital) throws Exception;

	/**
	 * 通过临时id查询备付资金跟踪
	 * 
	 * @param tempUuid
	 *            临时id
	 * @return 备付资金跟踪集合
	 */
	public List<SpareCapital> findByTempUuid(String tempUuid);

	/**
	 * 根据查询条件查询备付资金跟踪
	 * 
	 * @param conditions
	 *            查询条件
	 * @return 备付资金跟踪集合
	 */
	public List<SpareCapital> findByConditions(Map<String, Object> conditions);

	/**
	 * 根据信托计划id查询备付资金跟踪信息
	 * 
	 * @param creditEntrustId
	 *            信托计划id
	 * @return 备付资金跟踪集合
	 */
	public List<SpareCapital> findByCreditEntrustIdAndOrderByCreteTime(String creditEntrustId);

}
