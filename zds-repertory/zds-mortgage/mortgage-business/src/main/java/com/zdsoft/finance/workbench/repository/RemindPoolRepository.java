package com.zdsoft.finance.workbench.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.finance.workbench.entity.RemindPool;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.Pageable;


/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title: RemindPoolRepository.java 
 * @ClassName: RemindPoolRepository 
 * @Description: 提醒池
 * @author gufeng 
 * @date 2017年3月13日 下午5:08:13 
 * @version V1.0
 */
public interface RemindPoolRepository extends CustomRepository<RemindPool, String>{
	
	/**
	 * 根据分类查询
	 * @param category
	 * @return
	 */
	@Query("from RemindPool rp where rp.isDeleted=false and rp.status=1 and rp.category=:category ")
	public List<RemindPool> findbyCategory(@Param("category")String category);
	
	/**
	 * @Title: findByPage 
	 * @Description: 分页查询列表
	 * @author longwei
	 * @param remindPool
	 * @param pageable
	 * @return
	 * @throws BusinessException
	 */
	Page<RemindPool> findByPage(RemindPool remindPool,Pageable pageable) throws BusinessException;

}
