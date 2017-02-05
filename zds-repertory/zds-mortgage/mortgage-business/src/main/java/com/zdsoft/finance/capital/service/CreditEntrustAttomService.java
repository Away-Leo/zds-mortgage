package com.zdsoft.finance.capital.service;

import java.util.List;
import java.util.Map;

import com.zdsoft.finance.base.service.BaseService;
import com.zdsoft.finance.capital.entity.CreditEntrustAttom;

/**
 * 信托计划转让Service
 * 
 * @createTime:2017年1月10日
 * @author liuwei
 * @version 1.0
 */
public interface CreditEntrustAttomService extends BaseService<CreditEntrustAttom> {

	/**
	 * 新增或修改计划转让信息
	 * 
	 * @param creditEntrustAttom
	 *            计划转让信息
	 * @return 计划转让信息
	 * @throws Exception
	 */
	public CreditEntrustAttom saveOrUpdateEntrustAttom(CreditEntrustAttom creditEntrustAttom) throws Exception;

	/**
	 * 根据tempId查询计划转让信息
	 * 
	 * @param tempUuid
	 *            临时id
	 * @return 计划转让列表
	 */
	public List<CreditEntrustAttom> findByTempUuid(String tempUuid);

	/**
	 * 根据查询条件查询计划转让信息
	 * 
	 * @param conditions
	 *            查询条件
	 * @return 计划转让信息
	 */
	public List<CreditEntrustAttom> findByConditions(Map<String, Object> conditions);

}
