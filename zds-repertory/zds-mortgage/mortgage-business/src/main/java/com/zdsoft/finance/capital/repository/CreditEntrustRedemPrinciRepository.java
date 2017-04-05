package com.zdsoft.finance.capital.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.zdsoft.finance.capital.entity.CreditEntrustRedemPrinci;
import com.zdsoft.finance.common.base.CustomRepository;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * 
 * @Title: CreditEntrustRedemPrinciRepository.java
 * @ClassName: CreditEntrustRedemPrinciRepository
 * @Description: 信托计划本金赎回Repository
 * @author liuwei
 * @date 2017年2月8日 上午10:27:49
 * @version V1.0
 */
public interface CreditEntrustRedemPrinciRepository extends CustomRepository<CreditEntrustRedemPrinci, String> {

	/**
	 * 
	 * @Title: findByTempUuid 
	 * @Description: 根据临时id查询信托计划本金赎回
	 * @author liuwei 
	 * @param tempUuid 临时id
	 * @return 信托计划本金赎回
	 */
	@Query("select t from CreditEntrustRedemPrinci t where t.isDeleted = false and t.tempUuid = :tempUuid ")
	public List<CreditEntrustRedemPrinci> findByTempUuid(@Param("tempUuid") String tempUuid);

	/**
	 * 
	 * @Title: findByConditions 
	 * @Description: 根据查询条件查询本金赎回
	 * @author liuwei 
	 * @param conditions 查询条件
	 * @return 本金赎回集合
	 */
	public List<CreditEntrustRedemPrinci> findByConditions(Map<String, Object> conditions);

}
