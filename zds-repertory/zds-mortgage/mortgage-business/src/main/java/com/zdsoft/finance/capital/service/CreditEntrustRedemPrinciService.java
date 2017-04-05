package com.zdsoft.finance.capital.service;

import java.util.List;
import java.util.Map;

import com.zdsoft.finance.base.service.BaseService;
import com.zdsoft.finance.capital.entity.CreditEntrustRedemPrinci;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * 
 * @Title: CreditEntrustRedemPrinciService.java
 * @ClassName: CreditEntrustRedemPrinciService
 * @Description: 信托计划本金赎回Service
 * @author liuwei
 * @date 2017年2月8日 上午10:37:05
 * @version V1.0
 */
public interface CreditEntrustRedemPrinciService extends BaseService<CreditEntrustRedemPrinci> {

	/**
	 * 
	 * @Title: saveOrUpdateEntrustRedemPrinci
	 * @Description: 保存或修改计划本金赎回
	 * @author liuwei
	 * @param creditEntrustRedemPrinci
	 *            计划本金赎回信息
	 * @return 计划本金赎回信息
	 * @throws Exception
	 *             调用平台出现Exception
	 */
	public CreditEntrustRedemPrinci saveOrUpdateEntrustRedemPrinci(CreditEntrustRedemPrinci creditEntrustRedemPrinci)
			throws Exception;

	/**
	 * 
	 * @Title: findByTempUuid
	 * @Description: 通过临时id查询计划本金赎回
	 * @author liuwei
	 * @param tempUuid
	 *            临时id
	 * @return 计划本金赎回列表
	 */
	public List<CreditEntrustRedemPrinci> findByTempUuid(String tempUuid);

	/**
	 * 
	 * @Title: findByConditions
	 * @Description: 通过查询条件查询本金赎回
	 * @author liuwei
	 * @param conditions
	 *            查询条件
	 * @return 多条件查询本金赎回信息
	 */
	public List<CreditEntrustRedemPrinci> findByConditions(Map<String, Object> conditions);

}
