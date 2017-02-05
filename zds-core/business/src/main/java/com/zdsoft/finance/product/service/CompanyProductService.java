package com.zdsoft.finance.product.service;

import java.util.List;

import com.zdsoft.essential.dto.emp.EmpDto;
import com.zdsoft.finance.base.service.BaseService;
import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.finance.product.entity.CompanyProduct;
import com.zdsoft.finance.product.entity.Product;

/**
 * 中间表机构产品接口
 * @author longwei
 * @date 2016/12/26
 * @version 1.0
 */
public interface CompanyProductService extends BaseService<CompanyProduct>{

	/**
	 * 物理删除
	 * @param id
	 * @throws BusinessException
	 */
	public void delete(String id) throws BusinessException;
	
	/**
	 * 通过产品id查询
	 * @param productId
	 * @return
	 * @throws BusinessException
	 */
	public List<CompanyProduct> findByProductId(String productId) throws BusinessException;
	
	/**
	 * 复制
	 * @param product
	 * @throws BusinessException
	 */
	public void copy(Product oldProduct,Product newProduct,EmpDto empDto) throws BusinessException;
}
