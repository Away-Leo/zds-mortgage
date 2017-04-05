package com.zdsoft.finance.capital.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.zdsoft.finance.capital.entity.LoanCapital;
import com.zdsoft.finance.common.base.CustomRepository;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * 
 * @Title: LoanCapitalRepository.java
 * @ClassName: LoanCapitalRepository
 * @Description: 专户贷方资金跟踪Repository
 * @author liuwei
 * @date 2017年2月8日 上午10:32:11
 * @version V1.0
 */
public interface LoanCapitalRepository extends CustomRepository<LoanCapital, String> {

	/**
	 * 
	 * @Title: findByTempUuid
	 * @Description: 根据临时id查询专户贷方资金跟踪列表
	 * @author liuwei
	 * @param tempUuid
	 *            临时id
	 * @return 专户贷方资金跟踪列表
	 */
	@Query(" select t from LoanCapital t where t.isDeleted = false and t.tempUuid = :tempUuid ")
	public List<LoanCapital> findByTempUuid(@Param("tempUuid") String tempUuid);

	/**
	 * 
	 * @Title: findByConditions
	 * @Description: 根据查询条件查询专户贷方跟踪列表
	 * @author liuwei
	 * @param conditions
	 *            查询条件
	 * @return 专户贷方资金跟踪列表
	 */
	public List<LoanCapital> findByConditions(Map<String, Object> conditions);

}
