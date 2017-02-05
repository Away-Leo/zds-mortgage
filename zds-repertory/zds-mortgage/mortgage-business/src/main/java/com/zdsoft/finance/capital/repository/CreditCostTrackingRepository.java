package com.zdsoft.finance.capital.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.zdsoft.finance.capital.entity.CreditCostTracking;
import com.zdsoft.finance.common.base.CustomRepository;

/**
 * 应付费用跟踪Repository
 * 
 * @createTime:2017年1月10日
 * @author liuwei
 * @version 1.0
 */
public interface CreditCostTrackingRepository extends CustomRepository<CreditCostTracking, String> {

	/**
	 * 根据临时id查询应付费用跟踪
	 * 
	 * @param tempUuid
	 *            临时id
	 * @return 应付费用跟踪列表
	 */
	@Query("select t from CreditCostTracking t where t.isDeleted = false and t.tempUuid = :tempUuid ")
	public List<CreditCostTracking> findByTempUuid(@Param("tempUuid") String tempUuid);

	/**
	 * 通过查询条件查询应付费用跟踪
	 * 
	 * @param conditions
	 *            查询条件
	 * @return 应付费用跟踪列表
	 */
	public List<CreditCostTracking> findByConditions(Map<String, Object> conditions);
}
