package com.zdsoft.finance.capital.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.zdsoft.finance.capital.entity.CreditEntrustFeeItem;
import com.zdsoft.finance.common.base.CustomRepository;

/**
 * 信托计划费用Repository
 * 
 * @createTime:2017年1月10日
 * @author liuwei
 * @version 1.0
 */
public interface CreditEntrustFeeItemRepository extends CustomRepository<CreditEntrustFeeItem, String> {

	/**
	 * 根据业务标识id查询信托计划费用列表
	 * 
	 * @param businessId
	 *            业务表示id
	 * @return 信托计划费用列表
	 */
	@Query("select t from CreditEntrustFeeItem t where t.isDeleted = false and t.businessId = :businessId ")
	public List<CreditEntrustFeeItem> findByBusinessId(@Param("businessId") String businessId);

}
