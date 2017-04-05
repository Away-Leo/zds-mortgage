package com.zdsoft.finance.capital.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.zdsoft.finance.capital.entity.SpareCapital;
import com.zdsoft.finance.common.base.CustomRepository;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * 
 * @Title: SpareCapitalRepository.java
 * @ClassName: SpareCapitalRepository
 * @Description: 备付资金跟踪Repository
 * @author liuwei
 * @date 2017年2月8日 上午10:32:49
 * @version V1.0
 */
public interface SpareCapitalRepository extends CustomRepository<SpareCapital, String> {

	/**
	 * 
	 * @Title: findByTempUuid
	 * @Description: 根据临时id查询备付资金跟踪列表
	 * @author liuwei
	 * @param tempUuid
	 *            临时id
	 * @return 备付资金跟踪列表
	 */
	@Query(" select  t from SpareCapital t where t.isDeleted = false and t.tempUuid = :tempUuid ")
	public List<SpareCapital> findByTempUuid(@Param("tempUuid") String tempUuid);

	/**
	 * 
	 * @Title: findByConditions
	 * @Description: 根据查询条件查询备付资金跟踪列表
	 * @author liuwei
	 * @param conditions
	 *            查询条件
	 * @return 备付资金跟踪列表
	 */
	public List<SpareCapital> findByConditions(Map<String, Object> conditions);

	/**
	 * 
	 * @Title: findByCreditEntrustIdAndOrderByCreteTime
	 * @Description: 根据信托计划id查询备付资金跟踪列表，并排序
	 * @author liuwei
	 * @param creditEntrustId
	 *            信托计划id
	 * @return 备付资金跟踪列表
	 */
	@Query(" select t from SpareCapital t where t.isDeleted = false and t.creditEntrust.id = :creditEntrustId order by t.createTime ")
	public List<SpareCapital> findByCreditEntrustIdAndOrderByCreteTime(
			@Param("creditEntrustId") String creditEntrustId);

}
