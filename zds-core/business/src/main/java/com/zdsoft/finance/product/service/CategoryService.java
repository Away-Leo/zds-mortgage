package com.zdsoft.finance.product.service;

import java.util.List;

import com.zdsoft.finance.base.service.BaseService;
import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.finance.product.entity.Category;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title: CategoryService.java 
 * @ClassName: CategoryService 
 * @Description: 产品类别接口
 * @author gufeng 
 * @date 2017年2月13日 上午10:23:13 
 * @version V1.0
 */
public interface CategoryService extends BaseService<Category> {

	/**
	 * @Title: findTree 
	 * @Description: 查询树
	 * @author gufeng 
	 * @param isTree 是否树
	 * @return 产品类别
	 */
	public List<Category> findTree(boolean isTree) ;
	
	/**
	 * @Title: checkName 
	 * @Description: 验证产品类别名称
	 * @author gufeng 
	 * @param name 类别名称
	 * @return 是否存在
	 * @throws BusinessException 查询异常
	 */
	public boolean checkName(String name) throws BusinessException;
	
	/**
	 * @Title: saveOrUpdate 
	 * @Description: 保存或修改产品类别
	 * @author gufeng 
	 * @param category 类别数据
	 * @return 保存后的类别
	 * @throws BusinessException 保存异常
	 */
	public Category saveOrUpdate(Category category) throws BusinessException;
	
	/**
	 * @Title: delete 
	 * @Description: 删除产品分类
	 * @author gufeng 
	 * @param categoryId 类别id
	 * @throws BusinessException 产出异常
	 */
	public void delete(String categoryId) throws BusinessException;
}
