package com.zdsoft.finance.capital.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.zdsoft.finance.capital.entity.CreditCostTracking;
import com.zdsoft.finance.common.base.CustomRepository;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * 
 * @Title: CreditCostTrackingRepository.java
 * @ClassName: CreditCostTrackingRepository
 * @Description: 应付费用跟踪Repository
 * @author liuwei
 * @date 2017年2月8日 上午10:25:08
 * @version V1.0
 */
public interface CreditCostTrackingRepository extends CustomRepository<CreditCostTracking, String> {

	/**
	 * 
	 * @Title: findByTempUuid
	 * @Description: 根据临时id查询应付费用跟踪
	 * @author liuwei
	 * @param tempUuid
	 *            临时id
	 * @return 应付费用跟踪列表
	 */
	@Query("select t from CreditCostTracking t where t.isDeleted = false and t.tempUuid = :tempUuid ")
	public List<CreditCostTracking> findByTempUuid(@Param("tempUuid") String tempUuid);

	/**
	 * 
	 * @Title: findByConditions
	 * @Description: 通过查询条件查询应付费用跟踪
	 * @author liuwei
	 * @param conditions
	 *            查询条件
	 * @return 应付费用跟踪列表
	 */
	public List<CreditCostTracking> findByConditions(Map<String, Object> conditions);
}
