package com.zdsoft.finance.workbench.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.finance.workbench.entity.RemindPool;

/**
 * 提醒池
 * @createTime 2017-01-17
 * @author <a href="mailto:gufeng@zdsoft.cn">gufeng</a>
 * @version 1.0
 */
public interface RemindPoolRepository extends CustomRepository<RemindPool, String>{
	
	/**
	 * 根据分类查询
	 * @param category
	 * @return
	 */
	@Query("from RemindPool rp where rp.isDeleted=false and rp.category=:category ")
	public List<RemindPool> findbyCategory(@Param("category")String category);

}
