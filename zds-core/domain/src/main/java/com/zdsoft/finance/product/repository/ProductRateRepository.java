package com.zdsoft.finance.product.repository;

import java.util.List;
import java.util.Map;

import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.finance.common.base.QueryObj;
import com.zdsoft.finance.common.utils.TimeUtil;
import com.zdsoft.finance.product.entity.ProductRate;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.PageRequest;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title: ProductRateRepository.java 
 * @ClassName: ProductRateRepository 
 * @Description: 产品贷款利率
 * @author gufeng 
 * @date 2017年3月6日 下午8:27:00 
 * @version V1.0
 */
public interface ProductRateRepository extends CustomRepository<ProductRate, String>{

	/**
	 * @Title: findByProductId 
	 * @Description: 通过产品查询产品利率
	 * @author gufeng 
	 * @param productId 产品id
	 * @return 利率数据
	 */
	public List<ProductRate> findByProductId(String productId);
	
	
	/**
     * SQL
     */
    public StringBuffer sql = new StringBuffer("SELECT " + 
                    "  product.id as id,product.productName as productName,product.capitalist_id as capitalistId,product.capitalistName as capitalistName,"
                    + " rate.endDate as endDate, rate.startDate as startDate,rate.startDateUnit as startDateUnit, "+ 
                    " rate.endDateUnit as endDateUnit,rate.rate as rate,rate.rateUnit as rateUnit,category.name as categoryName"+  
                    "   from  prd_product product   "+
                    "    LEFT JOIN prd_company_product   companyproduct ON companyproduct.product_id = product.id "+
                    "    INNER JOIN prd_product_rate    rate ON rate.product_id = product.id "+
                    "    LEFT JOIN prd_category    category  ON category.id = product.category_id    "+
                    
                    "  WHERE 1=1   and product.capitalist_id is not null "+ 
                    " AND product.isValid ='F' "+
                    " AND product.startTime <=  " + "'"+TimeUtil.getCurrentDateInteger()+"'"+
                    " AND product.endTime >=  " + "'"+TimeUtil.getCurrentDateInteger()+"'"
    );
    /**
     * 拼接sql
     */
    public StringBuffer extendSql = new StringBuffer("  GROUP BY  product.id  ,product.productName  ,product.capitalist_id  ,product.capitalistName  ,"
                    + " rate.endDate  , rate.startDate  ,rate.startDateUnit  , "+ 
                    " rate.endDateUnit  ,rate.rate  ,rate.rateUnit  ,category.name  ");
    
    /**
     * @Title: findByProductIdAndIdNotAndIsDeleted 
     * @Description: 利率查询
     * @author gufeng 
     * @param productId 产品id
     * @param id 利率id
     * @param isDeleted 是否删除
     * @return 利率数据
     */
	public List<ProductRate> findByProductIdAndIdNotAndIsDeleted(String productId, String id, boolean isDeleted);

	/**
	 * 
	 * @param pageable
	 * @param queryObjs
	 * @param orgCd
	 * 			部门CODE
	 * @param companyCd
	 * 			公司CODE
	 * @return
	 */
	public default Page<Map<String, Object>> getProductList(PageRequest pageable, List<QueryObj> queryObjs, String orgCd,String companyCd){
		StringBuffer extendSql = new StringBuffer("SELECT " + 
                    "  product.id as id,product.productName as productName,product.capitalist_id as capitalistId,product.capitalistName as capitalistName,"
                    + " rate.endDate as endDate, rate.startDate as startDate,rate.startDateUnit as startDateUnit, "+ 
                    " rate.endDateUnit as endDateUnit,rate.rate as rate,rate.rateUnit as rateUnit,category.name as categoryName"+  
                    "   from  prd_product product   "+
                    "    LEFT JOIN prd_company_product   companyproduct ON companyproduct.product_id = product.id "+
                    "    INNER JOIN prd_product_rate    rate ON rate.product_id = product.id "+
                    "    LEFT JOIN prd_category    category  ON category.id = product.category_id    "+
                    "  INNER JOIN (SELECT prd_company_product.product_id FROM prd_company_product LEFT JOIN prd_company  ON prd_company_product.company_id = prd_company.ID  "+
                    " WHERE prd_company.Code ='"+orgCd+"' OR prd_company.Code ='"+companyCd+"' GROUP BY prd_company_product.product_id) prd_company_product ON prd_company_product.product_id =  product.id  "+  
                    "  WHERE 1=1   and product.capitalist_id is not null "+ 
                    " AND rate.Isdeleted = 'F' "+
                    " AND product.Isdeleted = 'F'"+
                    " AND companyproduct.Isdeleted = 'F'"+
                    " AND product.isValid='T' "+
                    " AND product.startTime <=  " + "'"+TimeUtil.getCurrentDateInteger()+"'"+
                    " AND product.endTime >=  " + "'"+TimeUtil.getCurrentDateInteger()+"'"
				);
		 StringBuffer extendSql_ = new StringBuffer("  GROUP BY  product.id  ,product.productName  ,product.capitalist_id  ,product.capitalistName  ,"
                + " rate.endDate  , rate.startDate  ,rate.startDateUnit  , "+ 
                " rate.endDateUnit  ,rate.rate  ,rate.rateUnit  ,category.name  ");
	        //查询数据	
			return this.getListObjectBySql(pageable, queryObjs, extendSql, extendSql_); 
	}
	
}
