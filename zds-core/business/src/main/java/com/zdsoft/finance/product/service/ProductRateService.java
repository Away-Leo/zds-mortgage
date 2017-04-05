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
 * 版权所有：重庆正大华日软件有限公司
 * @Title: ProductRateService.java 
 * @ClassName: ProductRateService 
 * @Description: 产品利率操作
 * @author gufeng 
 * @date 2017年2月6日 上午11:04:05 
 * @version V1.0
 */
public interface ProductRateService extends BaseService<ProductRate>{

	/**
	 * @Title: findByProductId 
	 * @Description: 通过产品查询产品利率
	 * @author gufeng 
	 * @param productId 产品id
	 * @return 产品利率
	 * @throws BusinessException 查询异常
	 */
	public List<ProductRate> findByProductId(String productId) throws BusinessException;
	
	/**
	 * @Title: copy 
	 * @Description: 复制
	 * @author gufeng 
	 * @param oldProduct 旧产品
	 * @param newProduct 新产品
	 * @param empDto 员工
	 * @throws BusinessException 复制异常
	 */
	public void copy(Product oldProduct,Product newProduct,EmpDto empDto) throws BusinessException;

	/**
	 * @Title: findBySqlProductRate 
	 * @Description: 查询产品利率
	 * @author gufeng 
	 * @param pageable 分页信息
	 * @param queryObjs 查询条件
	 * @return 分页数据
	 */
    public Page<Map<String, Object>> findBySqlProductRate(PageRequest pageable,List<QueryObj> queryObjs,String orgCd,String companyCd);

    /**
     * @Title: findByProductIdAndIdNot 
     * @Description: 利率查询
     * @author gufeng 
     * @param productId 产品id
     * @param rateId 利率id
     * @return 利率数据
     */
	public List<ProductRate> findByProductIdAndIdNot(String productId, String rateId)throws BusinessException;
}
