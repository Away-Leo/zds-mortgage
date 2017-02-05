package com.zdsoft.finance.capital.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.zdsoft.finance.capital.entity.CreditEntrustRedemPrinci;
import com.zdsoft.finance.common.base.CustomRepository;

public interface CreditEntrustRedemPrinciRepository extends CustomRepository<CreditEntrustRedemPrinci, String> {

	/**
	 * 根据临时id查询信托计划本金赎回
	 * 
	 * @param tempUuid
	 *            临时id
	 * @return 信托计划本金赎回
	 */
	@Query("select t from CreditEntrustRedemPrinci t where t.isDeleted = false and t.tempUuid = :tempUuid ")
	public List<CreditEntrustRedemPrinci> findByTempUuid(@Param("tempUuid") String tempUuid);

	/**
	 * 根据查询条件查询本金赎回
	 * 
	 * @param conditions
	 *            查询条件
	 * @return 本金赎回集合
	 */
	public List<CreditEntrustRedemPrinci> findByConditions(Map<String, Object> conditions);

}
