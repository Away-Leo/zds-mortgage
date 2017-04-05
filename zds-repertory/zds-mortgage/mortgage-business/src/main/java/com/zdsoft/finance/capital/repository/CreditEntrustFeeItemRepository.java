package com.zdsoft.finance.capital.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.zdsoft.finance.capital.entity.CreditEntrustFeeItem;
import com.zdsoft.finance.common.base.CustomRepository;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * 
 * @Title: CreditEntrustFeeItemRepository.java
 * @ClassName: CreditEntrustFeeItemRepository
 * @Description: 信托计划费用Repository
 * @author liuwei
 * @date 2017年2月8日 上午10:27:10
 * @version V1.0
 */
public interface CreditEntrustFeeItemRepository extends CustomRepository<CreditEntrustFeeItem, String> {

	/**
	 * 
	 * @Title: findByBusinessId 
	 * @Description: 根据业务标识id查询信托计划费用列表
	 * @author liuwei 
	 * @param businessId 业务表示id
	 * @return 信托计划费用列表
	 */
	@Query("select t from CreditEntrustFeeItem t where t.isDeleted = false and t.businessId = :businessId ")
	public List<CreditEntrustFeeItem> findByBusinessId(@Param("businessId") String businessId);

}
