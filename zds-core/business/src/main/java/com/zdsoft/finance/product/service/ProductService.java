package com.zdsoft.finance.product.service;

import java.util.List;
import java.util.Map;

import com.zdsoft.essential.dto.emp.EmpDto;
import com.zdsoft.finance.base.service.BaseService;
import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.finance.product.entity.Category;
import com.zdsoft.finance.product.entity.Company;
import com.zdsoft.finance.product.entity.Product;
import com.zdsoft.finance.product.entity.ProductRate;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.Pageable;


/**
 * 产品操作服务
 * @author lognwei
 * @date 2016/12/22
 * @version 1.0
 */
public interface ProductService extends BaseService<Product>{

	/**
	 * 根据对象查询
	 * @param product 查询对象
	 * @param pageable 分页信息
	 * @return 分页对象
	 * @throws BusinessException
	 */
	public Page<Product> find(Product product,String empType,Pageable pageable) throws BusinessException;
	
	/**
	 * 保存
	 * @return 保存后的对象
	 * @throws BusinessException
	 */
	public Product save(Product product) throws BusinessException;
	
	/**
	 * 修改产品
	 * @param product 产品主表
	 * @param productRate 产品利率
	 * @param companys 所属机构
	 * @return
	 * @throws BusinessException
	 */
	public Map<String, Object> update(Product product,List<Company> companys) throws BusinessException;
	
	/**
	 * 保存或修改产品利率
	 * @param productRate
	 * @return
	 * @throws BusinessException
	 */
	public ProductRate saveOrUpdateRate(ProductRate productRate) throws BusinessException;
	
	/**
	 * 验证产品名称唯一性
	 * @return
	 * @throws BusinessException
	 */
	public boolean checkName(String name) throws BusinessException;
	
	/**
	 * 作废产品
	 * @throws BusinessException
	 */
	public void invalid(String productId) throws BusinessException ;
	
	/**
	 * 恢复产品
	 * @throws BusinessException
	 */
	public void restore(String productId) throws BusinessException ;
	
	/**
	 * 复制产品
	 * @return
	 * @throws BusinessException
	 */
	public Product copy(Product product,EmpDto empDto) throws BusinessException;

	/**
	 * 通过产品分类查询产品
	 * @param categoryId
	 * @return
	 * @throws BusinessException
	 */
	public List<Product> findByCategory(Category category) throws BusinessException;

	/**
	 * 查找所有有效产品
	 * @return
	 * @throws BusinessException
	 */
	public List<Product> findAllProduct() throws BusinessException;
	
	/**
	 * 通过产品分类和机构查询产品
	 * @param categoryId
	 * @param orgCd
	 * @return
	 * @throws BusinessException
	 */
	public List<Product> findByCategoryIdAndOrgCd(String categoryId,String orgCd) throws BusinessException;
}
