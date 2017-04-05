package com.zdsoft.finance.capital.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.zdsoft.finance.capital.entity.CreditEntrustPrincipal;
import com.zdsoft.finance.common.base.CustomRepository;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * 
 * @Title: CreditEntrustPrincipalRepository.java
 * @ClassName: CreditEntrustPrincipalRepository
 * @Description: 信托计划本金投入Repository
 * @author liuwei
 * @date 2017年2月8日 上午10:27:36
 * @version V1.0
 */
public interface CreditEntrustPrincipalRepository extends CustomRepository<CreditEntrustPrincipal, String> {

	/**
	 * 
	 * @Title: findByTempUuid
	 * @Description: 根据临时id查询信托计划本金投入
	 * @author liuwei
	 * @param tempUuid
	 *            临时id
	 * @return 信托计划本金投入列表
	 */
	@Query("select t from CreditEntrustPrincipal t where t.isDeleted = false and t.tempUuid = :tempUuid ")
	public List<CreditEntrustPrincipal> findByTempUuid(@Param("tempUuid") String tempUuid);

	/**
	 * 
	 * @Title: findByConditions
	 * @Description: 通过查询条件查询本金投入集合
	 * @author liuwei
	 * @param conditions
	 *            查询条件
	 * @return 本金投入集合
	 */
	public List<CreditEntrustPrincipal> findByConditions(Map<String, Object> conditions);

}
