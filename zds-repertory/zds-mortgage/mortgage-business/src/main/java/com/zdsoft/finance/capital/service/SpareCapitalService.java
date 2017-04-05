package com.zdsoft.finance.capital.service;

import java.util.List;
import java.util.Map;

import com.zdsoft.finance.base.service.BaseService;
import com.zdsoft.finance.capital.entity.SpareCapital;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * 
 * @Title: SpareCapitalService.java
 * @ClassName: SpareCapitalService
 * @Description: 备付资金跟踪Service
 * @author liuwei
 * @date 2017年2月8日 上午10:37:39
 * @version V1.0
 */
public interface SpareCapitalService extends BaseService<SpareCapital> {

	/**
	 * 
	 * @Title: saveOrUpdateSpareCapital
	 * @Description: 新增或修改备付资金跟踪
	 * @author liuwei
	 * @param spareCapital
	 *            备付资金跟踪信息
	 * @return 备付资金跟踪信息
	 * @throws Exception
	 */
	public SpareCapital saveOrUpdateSpareCapital(SpareCapital spareCapital) throws Exception;

	/**
	 * 
	 * @Title: saveOrUpdateSpareCapitalAndMatch
	 * @Description: 新增或修改备付资金跟踪并匹配资金
	 * @author liuwei
	 * @param spareCapital
	 *            备付资金跟踪信息
	 * @return 备付资金跟踪信息
	 * @throws Exception
	 */
	public SpareCapital saveOrUpdateSpareCapitalAndMatch(SpareCapital spareCapital) throws Exception;

	/**
	 * 
	 * @Title: findByTempUuid
	 * @Description: 通过临时id查询备付资金跟踪
	 * @author liuwei
	 * @param tempUuid
	 *            临时id
	 * @return 备付资金跟踪集合
	 */
	public List<SpareCapital> findByTempUuid(String tempUuid);

	/**
	 * 
	 * @Title: findByConditions
	 * @Description: 根据查询条件查询备付资金跟踪
	 * @author liuwei
	 * @param conditions
	 *            查询条件
	 * @return 备付资金跟踪集合
	 */
	public List<SpareCapital> findByConditions(Map<String, Object> conditions);

	/**
	 * 
	 * @Title: findByCreditEntrustIdAndOrderByCreteTime
	 * @Description: 根据信托计划id查询备付资金跟踪信息
	 * @author liuwei
	 * @param creditEntrustId
	 *            信托计划id
	 * @return 备付资金跟踪集合
	 */
	public List<SpareCapital> findByCreditEntrustIdAndOrderByCreteTime(String creditEntrustId);

}
