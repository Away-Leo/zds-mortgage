package com.zdsoft.finance.capital.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.zdsoft.finance.capital.entity.FeeItem;
import com.zdsoft.finance.common.base.CustomRepository;

/**
 * 费用项Repository
 * 
 * @createTime:2017年1月10日
 * @author liuwei
 * @version 1.0
 */
public interface FeeItemRepository extends CustomRepository<FeeItem, String> {

	/**
	 * 根据费用项归属查询列表
	 * 
	 * @param attribution
	 *            费用项归属
	 * @return 费用列表
	 */
	@Query("select t from FeeItem t where t.isDeleted = false and t.attribution = :attribution ")
	public List<FeeItem> findByAttribution(@Param("attribution") String attribution);

}
