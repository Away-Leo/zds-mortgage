package com.zdsoft.finance.product.repository;

import java.util.List;

import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.finance.product.entity.Category;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: CategoryRepository.java 
 * @ClassName: CategoryRepository 
 * @Description: 产品类别操作仓库
 * @author gufeng 
 * @date 2017年2月13日 上午10:21:44 
 * @version V1.0
 */
public interface CategoryRepository extends CustomRepository<Category, String> {

	/**
	 * @Title: findAll 
	 * @Description: 查询所有有效产品类别
	 * @author gufeng 
	 * @param isTree 是否初始数据
	 * @return 类别数据
	 */
	public List<Category> findAll(boolean isTree);
	
	/**
	 * @Title: findByName 
	 * @Description: 通过名称查询
	 * @author gufeng 
	 * @param name 产品名称
	 * @return 是否存在
	 */
	public boolean findByName(String name);
}
