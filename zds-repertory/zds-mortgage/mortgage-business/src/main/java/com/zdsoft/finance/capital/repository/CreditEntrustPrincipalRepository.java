package com.zdsoft.finance.capital.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.zdsoft.finance.capital.entity.CreditEntrustPrincipal;
import com.zdsoft.finance.common.base.CustomRepository;

/**
 * 信托计划本金投入Repository
 * 
 * @createTime:2017年1月10日
 * @author liuwei
 * @version 1.0
 */
public interface CreditEntrustPrincipalRepository extends CustomRepository<CreditEntrustPrincipal, String> {

	/**
	 * 根据临时id查询信托计划本金投入
	 * 
	 * @param tempUuid
	 *            临时id
	 * @return 信托计划本金投入列表
	 */
	@Query("select t from CreditEntrustPrincipal t where t.isDeleted = false and t.tempUuid = :tempUuid ")
	public List<CreditEntrustPrincipal> findByTempUuid(@Param("tempUuid") String tempUuid);

	/**
	 * 通过查询条件查询本金投入列表
	 * 
	 * @param conditions
	 * @return
	 */
	public List<CreditEntrustPrincipal> findByConditions(Map<String, Object> conditions);

}
