package com.zdsoft.finance.capital.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.zdsoft.finance.capital.entity.CreditEntrustAttom;
import com.zdsoft.finance.common.base.CustomRepository;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * 
 * @Title: CreditEntrustAttomRepository.java
 * @ClassName: CreditEntrustAttomRepository
 * @Description: 信托计划转让Repository
 * @author liuwei
 * @date 2017年2月8日 上午10:25:51
 * @version V1.0
 */
public interface CreditEntrustAttomRepository extends CustomRepository<CreditEntrustAttom, String> {

	/**
	 * 
	 * @Title: findByTempUuid
	 * @Description: 根据临时id查询信托计划转让列表
	 * @author liuwei
	 * @param tempUuid
	 *            临时id
	 * @return 信托计划转让列表
	 */
	@Query("select t from CreditEntrustAttom t where t.isDeleted = false and t.tempUuid = :tempUuid ")
	public List<CreditEntrustAttom> findByTempUuid(@Param("tempUuid") String tempUuid);

	/**
	 * 
	 * @Title: findByConditions 
	 * @Description: 根据查询条件查询信托计划转让列表
	 * @author liuwei 
	 * @param conditions 查询条件
	 * @return 信托计划转让列表
	 */
	public List<CreditEntrustAttom> findByConditions(Map<String, Object> conditions);

}
