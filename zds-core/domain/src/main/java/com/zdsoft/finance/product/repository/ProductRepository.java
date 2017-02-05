package com.zdsoft.finance.product.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.finance.product.entity.Category;
import com.zdsoft.finance.product.entity.Product;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.Pageable;

/**
 * 产品主表操作仓库
 * @author longwei
 * @date 2016/12/22
 * @version 1.0
 */
public interface ProductRepository extends CustomRepository<Product, String>{

	
	/**
	 * 根据对象查询分页
	 * @param product 查询对象
	 * @param pageable 分页
	 * @return 分页信息
	 * @throws BusinessException
	 */
	public Page<Product> find(Product product,String empType,Pageable pageable) throws BusinessException;
	
	/**
	 * 验证产品名称唯一性
	 * @return
	 * @throws BusinessException
	 */
	@Query("select p from Product p where 1=1 and p.isDeleted = false and p.productName=:productName ")
	public Product findByName(@Param("productName")String productName) throws BusinessException;
	
	/**
	 * 通过产品分类查询产品
	 * @return
	 * @throws BusinessException
	 */
	public List<Product> findByCategory(Category category) throws BusinessException ;

	/**
	 * 根据父产品查找产品
	 * @param parentId
	 * @return
	 * @throws BusinessException
	 */
	/*@Query(" select p from Product p where  p.isDeleted = false and p.parent.id = :parentId")
	public List<Product> findWithParentId(@Param("parentId")String parentId) throws BusinessException;*/

	/**
	 * 查找所有有效产品
	 * @return
	 * @throws BusinessException
	 */
	@Query(" select p from Product p where p.isDeleted = false ")
	public List<Product> findAllProduct() throws BusinessException;
	
	/**
	 * 通过产品分类和机构查询产品
	 * @param CategoryId
	 * @param orgCd
	 * @return
	 */
	public List<Product> findByCategoryIdAndOrgCd(String CategoryId,String orgCd);
	
}
