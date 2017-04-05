package com.zdsoft.finance.product.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.finance.product.entity.ProductContract;


/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title: ProductContractRepository.java 
 * @ClassName: ProductContractRepository 
 * @Description: 产品合同模板关系
 * @author gufeng 
 * @date 2017年3月13日 下午4:47:45 
 * @version V1.0
 */
public interface ProductContractRepository extends CustomRepository<ProductContract, String>{

	/**
	 * @Title: logicByProduct 
	 * @Description: 批量逻辑删除
	 * @author gufeng 
	 * @param productId 产品id
	 */
	@Modifying
	@Query("update ProductContract set isDeleted = 1 where productId = :productId")
	public void logicByProduct(@Param("productId")String productId);
	
	/**
	 * sql分页查询的主体sql
	 */
	public StringBuffer sql = new StringBuffer("select pc.id, c.id as contractId, c.contractName,c.attachmentId,c.contractType from prd_product_contract pc " +
			"left join  con_contract_tpl c " +
			"on pc.contractId = c.id and c.contractTplState='Enable' and c.isDeleted = 'F' " +
			"where pc.isDeleted = 'F' ");
	
	/**
	 * @Title: selectContract 
	 * @Description: 合同集合
	 * @author gufeng 
	 * @param productId 产品id
	 * @param isAdd 是否已添加
	 * @return 合同集合数据
	 */
	public List<Map<String, Object>> selectContract(String productId, boolean isAdd);
	
	/**
	 * @Title: findByProductId 
	 * @Description: 查询
	 * @author gufeng 
	 * @param productId 产品id
	 * @return 数据
	 */
	@Query("select pc from ProductContract pc where pc.isDeleted=false and pc.productId=:productId ")
	public List<ProductContract> findByProductId(@Param("productId")String productId);
}
