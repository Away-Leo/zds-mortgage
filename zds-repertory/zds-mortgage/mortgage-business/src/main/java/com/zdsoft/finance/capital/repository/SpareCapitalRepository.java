package com.zdsoft.finance.capital.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.zdsoft.finance.capital.entity.SpareCapital;
import com.zdsoft.finance.common.base.CustomRepository;

/**
 * 备付资金跟踪Repository
 * 
 * @createTime:2017年1月10日
 * @author liuwei
 * @version 1.0
 */
public interface SpareCapitalRepository extends CustomRepository<SpareCapital, String> {

	/**
	 * 根据临时id查询备付资金跟踪列表
	 * 
	 * @param tempUuid
	 *            临时id
	 * @return 备付资金跟踪列表
	 */
	@Query(" select  t from SpareCapital t where t.isDeleted = false and t.tempUuid = :tempUuid ")
	public List<SpareCapital> findByTempUuid(@Param("tempUuid") String tempUuid);

	/**
	 * 根据查询条件查询备付资金跟踪列表
	 * 
	 * @param conditions
	 *            查询条件
	 * @return 备付资金跟踪列表
	 */
	public List<SpareCapital> findByConditions(Map<String, Object> conditions);

	/**
	 * 根据信托计划id查询备付资金跟踪列表，并排序
	 * 
	 * @param creditEntrustId
	 *            信托计划id
	 * @return 备付资金跟踪列表
	 */
	@Query(" select t from SpareCapital t where t.isDeleted = false and t.creditEntrust.id = :creditEntrustId order by t.createTime ")
	public List<SpareCapital> findByCreditEntrustIdAndOrderByCreteTime(
			@Param("creditEntrustId") String creditEntrustId);

}
