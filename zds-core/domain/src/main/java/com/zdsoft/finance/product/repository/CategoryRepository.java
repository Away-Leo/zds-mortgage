package com.zdsoft.finance.product.repository;

import java.util.List;

import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.finance.product.entity.Category;

/**
 * 产品类别操作仓库
 * @author longwei
 * @date 2016/01/10
 * @version 1.0
 */
public interface CategoryRepository extends CustomRepository<Category, String> {

	/**
	 * 查询所有有效产品类别
	 */
	public List<Category> findAll(boolean isTree);
	
	/**
	 * 通过名称查询
	 * @param name
	 * @return
	 */
	public boolean findByName(String name);
}
