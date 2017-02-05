package com.zdsoft.finance.capital.service;

import java.util.List;
import java.util.Map;

import com.zdsoft.finance.base.service.BaseService;
import com.zdsoft.finance.capital.entity.CreditEntrustPrincipal;

/**
 * 信托计划本金投入service
 * 
 * @createTime:2017年1月10日
 * @author liuwei
 * @version 1.0
 */
public interface CreditEntrustPrincipalService extends BaseService<CreditEntrustPrincipal> {

	/**
	 * 保存或修改计划本金投入
	 * 
	 * @param creditEntrsutPrincipal
	 *            计划本金投入信息
	 * @return 计划本金投入信息
	 * @throws Exception
	 *             调用平台出现Exception
	 */
	public CreditEntrustPrincipal saveOrUpdateEntrustPrincipal(CreditEntrustPrincipal creditEntrsutPrincipal)
			throws Exception;

	/**
	 * 通过临时id查询计划本金投入
	 * 
	 * @param tempUuid
	 *            临时id
	 * @return 计划本金投入列表
	 */
	public List<CreditEntrustPrincipal> findByTempUuid(String tempUuid);

	/**
	 * 通过查询条件查询本金投入信息
	 * 
	 * @param conditions
	 *            查询条件
	 * @return 本金投入列表
	 */
	public List<CreditEntrustPrincipal> findByConditions(Map<String, Object> conditions);

}
