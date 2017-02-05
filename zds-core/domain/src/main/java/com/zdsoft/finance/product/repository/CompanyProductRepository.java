package com.zdsoft.finance.product.repository;

import java.util.List;

import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.finance.product.entity.CompanyProduct;

/**
 * 公司产品中间表操作仓库
 * @author longwei
 * @date 2016/12/22
 * @version 1.0
 */
public interface CompanyProductRepository extends CustomRepository<CompanyProduct, String>{

	/**
	 * 通过产品编号查询
	 * @return
	 * @throws BusinessException
	 */
	public List<CompanyProduct> findByProductId(String productId) throws BusinessException;
}
