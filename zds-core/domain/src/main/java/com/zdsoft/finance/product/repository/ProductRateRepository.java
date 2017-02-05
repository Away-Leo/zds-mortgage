package com.zdsoft.finance.product.repository;

import java.util.List;

import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.finance.common.utils.TimeUtil;
import com.zdsoft.finance.product.entity.ProductRate;

/**
 * 产品贷款利率操作仓库
 * @author longwei
 * @date 2016/12/22
 * @version 1.0
 */
public interface ProductRateRepository extends CustomRepository<ProductRate, String>{

	/**
	 * 通过产品查询产品利率
	 */
	public List<ProductRate> findByProductId(String productId) throws BusinessException;
	
	
	/**
     * SQL
     */
    public StringBuffer sql = new StringBuffer("SELECT " + 
                    "  product.id as id,product.productName as productName,rate.endDate as endDate, rate.startDate as startDate,rate.startDateUnit as startDateUnit, "+ 
                    " rate.endDateUnit as endDateUnit,rate.rate as rate,rate.rateUnit as rateUnit,category.name as categoryName"+  
                    "   from  prct_product product   "+
                    "    LEFT JOIN prct_company_product   companyproduct ON companyproduct.product_id = product.id "+
                    "    INNER JOIN prct_product_rate    rate ON rate.product_id = product.id "+
                    "    LEFT JOIN prct_category    category  ON category.id = product.category_id    "+
                    


                    "  WHERE 1=1 "+ 
                    " AND product.startTime <=  " + "'"+TimeUtil.getCurrentDateInteger()+"'"+
                    " AND product.endTime >=  " + "'"+TimeUtil.getCurrentDateInteger()+"'"
    );
    
    /**
     * 拼接sql
     */
    public StringBuffer extendSql = new StringBuffer("   ");
	
}
