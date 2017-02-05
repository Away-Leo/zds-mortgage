package com.zdsoft.finance.capital.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.zdsoft.finance.capital.entity.CreditEntrustDebit;
import com.zdsoft.finance.common.base.CustomRepository;

/**
 * 信托计划借方资金Repository
 * 
 * @createTime:2017年1月10日
 * @author liuwei
 * @version 1.0
 */
public interface CreditEntrustDebitRepository extends CustomRepository<CreditEntrustDebit, String> {

	/**
	 * 根据临时id查询信托计划借方资金列表
	 * 
	 * @param tempUuid
	 *            临时id
	 * @return 信托计划借方资金列表
	 */
	@Query("select t from CreditEntrustDebit t where t.isDeleted = false and t.tempUuid = :tempUuid ")
	public List<CreditEntrustDebit> findByTempUuid(@Param("tempUuid") String tempUuid);

	/**
	 * 多条件查询借方资金列表
	 * 
	 * @param conditions
	 *            查询条件
	 * @return 借方资金列表
	 */
	public List<CreditEntrustDebit> findByConditions(Map<String, Object> conditions);

}
