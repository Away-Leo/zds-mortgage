package com.zdsoft.finance.capital.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.zdsoft.finance.capital.entity.LoanCapital;
import com.zdsoft.finance.common.base.CustomRepository;

/**
 * 专户贷方资金跟踪Repository
 * 
 * @createTime:2017年1月10日
 * @author liuwei
 * @version 1.0
 */
public interface LoanCapitalRepository extends CustomRepository<LoanCapital, String> {

	/**
	 * 根据临时id查询专户贷方资金跟踪列表
	 * 
	 * @param tempUuid
	 *            临时id
	 * @return 专户贷方资金跟踪列表
	 */
	@Query(" select t from LoanCapital t where t.isDeleted = false and t.tempUuid = :tempUuid ")
	public List<LoanCapital> findByTempUuid(@Param("tempUuid") String tempUuid);

	/**
	 * 根据查询条件查询专户贷方跟踪列表
	 * 
	 * @param conditions
	 *            查询条件
	 * @return 专户贷方资金跟踪列表
	 */
	public List<LoanCapital> findByConditions(Map<String, Object> conditions);

}
