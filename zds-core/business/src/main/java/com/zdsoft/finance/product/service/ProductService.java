package com.zdsoft.finance.product.service;

import java.util.List;
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
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: ProductService.java 
 * @ClassName: ProductService 
 * @Description: 产品
 * @author gufeng 
 * @date 2017年2月16日 下午5:48:24 
 * @version V1.0
 */
public interface ProductService extends BaseService<Product>{

	/**
	 * @Title: find 
	 * @Description: 根据对象查询
	 * @author gufeng 
	 * @param product 查询对象
	 * @param empType 机构类型
	 * @param pageable 分页信息
	 * @return 产品分页
	 * @throws BusinessException 查询异常
	 */
	public Page<Product> find(Product product,String empType,Pageable pageable) throws BusinessException;
	
	/**
	 * @Title: save 
	 * @Description: 保存
	 * @author gufeng 
	 * @param product 产品数据
	 * @return 保存后的产品
	 * @throws BusinessException 保存异常
	 */
	public Product save(Product product) throws BusinessException;
	
	/**
	 * @Title: saveOrUpdate 
	 * @Description: 保存或更新产品
	 * @author gufeng 
	 * @param product 产品信息
	 * @param companys 公司信息
	 * @return 产品
	 * @throws BusinessException 保存异常
	 */
	public Product saveOrUpdate(Product product,List<Company> companys) throws BusinessException;
	
	/**
	 * @Title: saveOrUpdateRate 
	 * @Description: 保存或修改产品利率
	 * @author gufeng 
	 * @param productRate 利率数据
	 * @return 利率
	 * @throws BusinessException 利率异常
	 */
	public ProductRate saveOrUpdateRate(ProductRate productRate) throws BusinessException;
	
	/**
	 * @Title: checkName 
	 * @Description: 验证产品名称唯一性
	 * @author gufeng 
	 * @param name 产品名
	 * @param categoryId 类别
	 * @return 是否唯一
	 * @throws BusinessException 验证异常
	 */
	public boolean checkName(String name,String categoryId) throws BusinessException;
	
	/**
	 * @Title: invalid 
	 * @Description: 作废产品
	 * @author gufeng 
	 * @param productId 产品id
	 * @throws BusinessException 作废异常
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
	 * @Title: findAllProduct 
	 * @Description: 查找所有有效产品
	 * @author gufeng 
	 * @return 有效产品
	 * @throws BusinessException 查询异常
	 */
	public List<Product> findAllProduct() throws BusinessException;
	
	/**
	 * @Title: findByCategoryIdAndOrgCd 
	 * @Description: 通过产品分类和机构查询产品
	 * @author gufeng 
	 * @param categoryId 类别id
	 * @param orgCd 机构编号
	 * @return 产品数据
	 * @throws BusinessException 产讯异常
	 */
	public List<Product> findByCategoryIdAndOrgCd(String categoryId,String orgCd) throws BusinessException;

	/**
	 * @Title: findByProductIdAndDeadline 
	 * @Description: 利率查询
	 * @author gufeng 
	 * @param productId 产品id
	 * @param date 期限
	 * @param dateUtil 期限单位
	 * @return 利率
	 * @throws BusinessException 查询异常
	 */
	public ProductRate findByProductIdAndDeadline(String productId,Long date,String dateUtil) throws BusinessException;
}
