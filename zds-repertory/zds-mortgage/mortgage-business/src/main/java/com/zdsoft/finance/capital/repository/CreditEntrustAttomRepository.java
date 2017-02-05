package com.zdsoft.finance.capital.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.zdsoft.finance.capital.entity.CreditEntrustAttom;
import com.zdsoft.finance.common.base.CustomRepository;

/**
 * 信托计划转让Repository
 * 
 * @createTime:2017年1月10日
 * @author liuwei
 * @version 1.0
 */
public interface CreditEntrustAttomRepository extends CustomRepository<CreditEntrustAttom, String> {

	/**
	 * 根据临时id查询信托计划转让列表
	 * 
	 * @param tempUuid
	 *            临时id
	 * @return 信托计划转让列表
	 */
	@Query("select t from CreditEntrustAttom t where t.isDeleted = false and t.tempUuid = :tempUuid ")
	public List<CreditEntrustAttom> findByTempUuid(@Param("tempUuid") String tempUuid);

	/**
	 * 根据查询条件查询信托计划转让列表
	 * 
	 * @param conditions
	 *            查询条件
	 * @return 信托计划转让列表
	 */
	public List<CreditEntrustAttom> findByConditions(Map<String, Object> conditions);

}
