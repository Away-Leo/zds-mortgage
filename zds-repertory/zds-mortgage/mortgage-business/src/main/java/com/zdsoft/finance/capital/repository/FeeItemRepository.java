package com.zdsoft.finance.capital.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.zdsoft.finance.capital.entity.FeeItem;
import com.zdsoft.finance.common.base.CustomRepository;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * 
 * @Title: FeeItemRepository.java
 * @ClassName: FeeItemRepository
 * @Description: 费用项Repository
 * @author liuwei
 * @date 2017年2月8日 上午10:31:48
 * @version V1.0
 */
public interface FeeItemRepository extends CustomRepository<FeeItem, String> {

	/**
	 * 
	 * @Title: findByAttribution 
	 * @Description: 根据费用项归属查询列表
	 * @author liuwei 
	 * @param attribution 费用项归属
	 * @return 费用列表
	 */
	@Query("select t from FeeItem t where t.isDeleted = false and t.attribution = :attribution ")
	public List<FeeItem> findByAttribution(@Param("attribution") String attribution);

}
