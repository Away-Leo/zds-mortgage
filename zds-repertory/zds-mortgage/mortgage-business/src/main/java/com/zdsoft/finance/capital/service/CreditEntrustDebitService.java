package com.zdsoft.finance.capital.service;

import java.util.List;
import java.util.Map;

import com.zdsoft.finance.base.service.BaseService;
import com.zdsoft.finance.capital.entity.CreditEntrustDebit;

/**
 * 信托计划借方资金Service
 * 
 * @createTime:2017年1月10日
 * @author liuwei
 * @version 1.0
 */
public interface CreditEntrustDebitService extends BaseService<CreditEntrustDebit> {

	/**
	 * 新增或保存借方资金
	 * 
	 * @param creditEntrustDebit
	 *            借方资金
	 * @return 借方资金
	 * @throws Exception
	 */
	public CreditEntrustDebit saveOrUpdateEntrustDebit(CreditEntrustDebit creditEntrustDebit) throws Exception;

	/**
	 * 通过临时id查询借方资金列表
	 * 
	 * @param tempUuid
	 * @return 借方资金列表
	 */
	public List<CreditEntrustDebit> findByTempUuid(String tempUuid);

	/**
	 * 多条件查询借方资金列表
	 * 
	 * @param conditions
	 *            查询条件
	 * @return 借方资金列表
	 */
	public List<CreditEntrustDebit> findByConditions(Map<String, Object> conditions);

}
