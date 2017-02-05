package com.zdsoft.finance.capital.service;

import java.util.List;
import java.util.Map;

import com.zdsoft.finance.base.service.BaseService;
import com.zdsoft.finance.capital.entity.CreditEntrustRedemPrinci;

/**
 * 信托计划本金赎回
 * 
 * @createTime:2017年1月14日
 * @author liuwei
 * @version 1.0
 */
public interface CreditEntrustRedemPrinciService extends BaseService<CreditEntrustRedemPrinci> {

	/**
	 * 保存或修改计划本金赎回
	 * 
	 * @param CreditEntrustRedemPrinci
	 *            计划本金赎回信息
	 * @return 计划本金赎回信息
	 * @throws Exception
	 *             调用平台出现Exception
	 */
	public CreditEntrustRedemPrinci saveOrUpdateEntrustRedemPrinci(CreditEntrustRedemPrinci creditEntrustRedemPrinci)
			throws Exception;

	/**
	 * 通过临时id查询计划本金赎回
	 * 
	 * @param tempUuid
	 *            临时id
	 * @return 计划本金赎回列表
	 */
	public List<CreditEntrustRedemPrinci> findByTempUuid(String tempUuid);

	/**
	 * 通过查询条件查询本金赎回
	 * 
	 * @param conditions
	 * @return
	 */
	public List<CreditEntrustRedemPrinci> findByConditions(Map<String, Object> conditions);

}
