package com.zdsoft.finance.product.service;

import java.util.List;

import com.zdsoft.finance.base.service.BaseService;
import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.finance.product.entity.Category;

/**
 * 产品类别接口
 * @author longwei
 * @date 2016/01/10
 * @version 1.0
 */
public interface CategoryService extends BaseService<Category> {

	/**
	 * 查询树
	 * @return 树结构
	 * @throws BusinessException
	 */
	public List<Category> findTree(boolean isTree) ;
	
	/**
	 * 验证产品类别名称
	 * @param name
	 * @throws BusinessException
	 */
	public boolean checkName(String name) throws BusinessException;
	
	/**
	 * 保存或修改产品类别
	 * @param category
	 * @return
	 * @throws BusinessException
	 */
	public Category saveOrUpdate(Category category) throws BusinessException;
	
	/**
	 * 删除产品分类
	 * @param categoryId
	 * @throws BusinessException
	 */
	public void delete(String categoryId) throws BusinessException;
}
