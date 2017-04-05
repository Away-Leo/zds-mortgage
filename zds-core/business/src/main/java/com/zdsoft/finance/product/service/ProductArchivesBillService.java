package com.zdsoft.finance.product.service;

import java.util.List;

import com.zdsoft.essential.dto.emp.EmpDto;
import com.zdsoft.finance.base.service.BaseService;
import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.finance.product.entity.Product;
import com.zdsoft.finance.product.entity.ProductArchivesBill;


/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title: ProductArchivesBillService.java 
 * @ClassName: ProductArchivesBillService 
 * @Description: 档案清单
 * @author gufeng 
 * @date 2017年3月13日 下午4:44:59 
 * @version V1.0
 */
public interface ProductArchivesBillService extends BaseService<ProductArchivesBill>{

	/**
	 * @Title: saveOrUpdate 
	 * @Description: 保存或更新
	 * @author gufeng 
	 * @param po 需要保存的对象
	 * @return 保存后对象
	 * @throws BusinessException 异常
	 */
	public ProductArchivesBill saveOrUpdate(ProductArchivesBill po) throws BusinessException;

	/**
	 * @Title: sets 
	 * @Description: 批量设置
	 * @author gufeng 
	 * @param ids 需要设置的id 多个以，分割
	 * @param archivesLevel 档案等级
	 * @param archivesType 档案类型
	 * @throws BusinessException 异常
	 */
	public void sets(String ids, String archivesLevel, String archivesType)throws BusinessException ;

	/**
	 * @Title: deleteByProductId 
	 * @Description: 删除
	 * @author gufeng 
	 * @param productId 产品id
	 * @throws BusinessException 异常
	 */
	public void deleteByProductId(String productId)throws BusinessException;
	
	/**
	 * @Title: findByProductId 
	 * @Description: 查询
	 * @author gufeng 
	 * @param productId 产品id
	 * @return 档案清单
	 * @throws BusinessException 查询异常
	 */
	public List<ProductArchivesBill> findByProductId(String productId) throws BusinessException;
	
	/**
	 * @Title: copy 
	 * @Description: 复制
	 * @author gufeng 
	 * @param oldProduct 旧产品
	 * @param newProduct 新产品
	 * @param empDto 员工
	 * @throws BusinessException 异常
	 */
	public void copy(Product oldProduct,Product newProduct ,EmpDto empDto) throws BusinessException;
}