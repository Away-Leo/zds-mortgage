package com.zdsoft.finance.product.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.finance.product.entity.Category;
import com.zdsoft.finance.product.entity.Product;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.Pageable;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: ProductRepository.java 
 * @ClassName: ProductRepository 
 * @Description: 产品
 * @author gufeng 
 * @date 2017年2月16日 下午5:46:52 
 * @version V1.0
 */
public interface ProductRepository extends CustomRepository<Product, String>{

	
	/**
	 * @Title: find 
	 * @Description: 根据对象查询分页
	 * @author gufeng 
	 * @param product 查询对象
	 * @param empType 类型
	 * @param pageable 分页
	 * @return 分页数据
	 */
	public Page<Product> find(Product product,String empType,Pageable pageable);
	
	/**
	 * @Title: findByName 
	 * @Description: 验证产品名称唯一性
	 * @author gufeng 
	 * @param productName 产品名字
	 * @return 数据
	 */
	@Query("select p from Product p where 1=1 and p.isDeleted = false and p.productName=:productName and category.id=:categoryId")
	public List<Product> findByName(@Param("productName")String productName,@Param("categoryId")String categoryId);
	
	/**
	 * @Title: findByCategory 
	 * @Description: 通过产品分类查询产品
	 * @author gufeng 
	 * @param category 类别
	 * @return 产品数据
	 */
	public List<Product> findByCategory(Category category);

	/**
	 * 根据父产品查找产品
	 * @param parentId
	 * @return
	 */
	/*@Query(" select p from Product p where  p.isDeleted = false and p.parent.id = :parentId")
	public List<Product> findWithParentId(@Param("parentId")String parentId);*/

	/**
	 * @Title: findAllProduct 
	 * @Description: 查找所有有效产品
	 * @author gufeng 
	 * @return 产品数据
	 */
	@Query(" select p from Product p where p.isDeleted = false ")
	public List<Product> findAllProduct();
	
	/**
	 * @Title: findByCategoryIdAndOrgCd 
	 * @Description: 通过产品分类和机构查询产品
	 * @author gufeng 
	 * @param CategoryId 类别id
	 * @param orgCd 部门编号
	 * @param nowDate 现在的日期
	 * @return 产品数据
	 */
	public List<Product> findByCategoryIdAndOrgCd(String categoryId,String orgCd,Long nowDate);
	
}
