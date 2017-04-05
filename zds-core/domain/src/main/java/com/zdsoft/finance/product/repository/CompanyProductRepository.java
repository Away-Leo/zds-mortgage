package com.zdsoft.finance.product.repository;

import java.util.List;

import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.finance.product.entity.CompanyProduct;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title: CompanyProductRepository.java 
 * @ClassName: CompanyProductRepository 
 * @Description: 公司产品中间表操作仓库
 * @author gufeng 
 * @date 2017年3月14日 下午4:34:03 
 * @version V1.0
 */
public interface CompanyProductRepository extends CustomRepository<CompanyProduct, String>{

	/**
	 * @Title: findByProductId 
	 * @Description: 通过产品编号查询
	 * @author gufeng 
	 * @param productId 产品id
	 * @return 公司关联表
	 */
	public List<CompanyProduct> findByProductId(String productId);
}
