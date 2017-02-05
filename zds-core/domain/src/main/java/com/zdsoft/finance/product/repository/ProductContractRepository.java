package com.zdsoft.finance.product.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.finance.product.entity.ProductContract;

/**
 * 产品合同模板关系
 * @createTime 2017年1月10日 下午3:28:05
 * @author <a href="mailto:gufeng@zdsoft.cn">gufeng</a>
 * @version
 */
public interface ProductContractRepository extends CustomRepository<ProductContract, String>{

	/**
	 * 批量逻辑删除
	 * @param productId
	 */
	@Modifying
	@Query("update ProductContract set isDeleted = 1 where productId = :productId")
	public void logicByProduct(@Param("productId")String productId);
	
	/**
	 * sql分页查询的主体sql
	 */
	public StringBuffer sql = new StringBuffer("select pc.id,c.id as contractId,c.contractNm,c.attrNm,c.contractType from prct_product_contract pc left join prct_temp_contract c on pc.contractId = c.id and c.isDeleted = 0 where pc.isDeleted = 0");
	
	/**
	 * 合同集合
	 * @param productId 产品id
	 * @param isAdd 是否已添加
	 * @return 合同集合数据
	 */
	public List<Map<String, Object>> selectContract(String productId, boolean isAdd);
	
	/**
	 * 查询
	 * @param productId
	 * @return
	 * @throws BusinessException
	 */
	@Query("select pc from ProductContract pc where pc.isDeleted=false and pc.productId=:productId ")
	public List<ProductContract> findByProductId(@Param("productId")String productId) throws BusinessException;
}
