package com.zdsoft.finance.product.service;

import java.util.List;
import java.util.Map;

import com.zdsoft.essential.dto.emp.EmpDto;
import com.zdsoft.finance.base.service.BaseService;
import com.zdsoft.finance.common.base.QueryObj;
import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.finance.product.entity.Product;
import com.zdsoft.finance.product.entity.ProductRate;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.PageRequest;

/**
 * 产品利率操作
 * @author longwei
 * @date 2016/12/26
 * @version 1.0
 */
public interface ProductRateService extends BaseService<ProductRate>{

	/**
	 * 通过产品查询产品利率
	 * @param productId
	 * @return
	 * @throws BusinessException
	 */
	public List<ProductRate> findByProductId(String productId) throws BusinessException;
	
	/**
	 * 复制
	 * @param product
	 * @param empDto
	 * @throws BusinessException
	 */
	public void copy(Product oldProduct,Product newProduct,EmpDto empDto) throws BusinessException;

	/**
     * 查询产品利率
     * @param productId
     * @return
     * @throws BusinessException
     */
    public Page<Map<String, Object>> findBySqlProductRate(PageRequest pageable,List<QueryObj> queryObjs);
}
