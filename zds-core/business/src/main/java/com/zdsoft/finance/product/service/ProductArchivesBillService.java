package com.zdsoft.finance.product.service;

import java.util.List;

import com.zdsoft.essential.dto.emp.EmpDto;
import com.zdsoft.finance.base.service.BaseService;
import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.finance.product.entity.Product;
import com.zdsoft.finance.product.entity.ProductArchivesBill;

/**
 * 档案清单
 * @createTime 2017年1月10日 上午11:43:20
 * @author <a href="mailto:gufeng@zdsoft.cn">gufeng</a>
 * @version 1.0
 */
public interface ProductArchivesBillService extends BaseService<ProductArchivesBill>{

	/**
	 * 保存或更新
	 * @param po 需要保存的对象
	 * @return 保存后对象
	 * @throws BusinessException 异常
	 */
	public ProductArchivesBill saveOrUpdate(ProductArchivesBill po) throws BusinessException;

	/**
	 * 批量设置
	 * @param ids 需要设置的id 多个以，分割
	 * @param archivesLevel 档案等级
	 * @param archivesType 档案类型
	 * @throws BusinessException 异常
	 */
	public void sets(String ids, String archivesLevel, String archivesType)throws BusinessException ;

	/**
	 * 删除
	 * @param productId 产品id
	 * @throws BusinessException 异常
	 */
	public void deleteByProductId(String productId)throws BusinessException;
	
	/**
	 * 查询
	 * @param productId
	 * @return
	 * @throws BusinessException
	 */
	public List<ProductArchivesBill> findByProductId(String productId) throws BusinessException;
	
	/**
	 * 复制
	 * @param product
	 * @param empDto
	 * @throws BusinessException
	 */
	public void copy(Product oldProduct,Product newProduct ,EmpDto empDto) throws BusinessException;
}