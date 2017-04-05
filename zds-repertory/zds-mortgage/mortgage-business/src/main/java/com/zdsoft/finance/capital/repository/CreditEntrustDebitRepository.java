package com.zdsoft.finance.capital.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.zdsoft.finance.capital.entity.CreditEntrustDebit;
import com.zdsoft.finance.common.base.CustomRepository;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * 
 * @Title: CreditEntrustDebitRepository.java
 * @ClassName: CreditEntrustDebitRepository
 * @Description: 信托计划借方资金Repository
 * @author liuwei
 * @date 2017年2月8日 上午10:26:54
 * @version V1.0
 */
public interface CreditEntrustDebitRepository extends CustomRepository<CreditEntrustDebit, String> {

	/**
	 * 
	 * @Title: findByTempUuid 
	 * @Description: 根据临时id查询信托计划借方资金列表
	 * @author liuwei 
	 * @param tempUuid  临时id
	 * @return 信托计划借方资金列表
	 */
	@Query("select t from CreditEntrustDebit t where t.isDeleted = false and t.tempUuid = :tempUuid ")
	public List<CreditEntrustDebit> findByTempUuid(@Param("tempUuid") String tempUuid);

	/**
	 * 
	 * @Title: findByConditions 
	 * @Description: 多条件查询借方资金列表
	 * @author liuwei 
	 * @param conditions 查询条件
	 * @return 借方资金列表
	 */
	public List<CreditEntrustDebit> findByConditions(Map<String, Object> conditions);

}
