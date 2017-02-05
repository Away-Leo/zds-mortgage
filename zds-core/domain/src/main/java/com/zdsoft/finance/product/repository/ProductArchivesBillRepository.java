package com.zdsoft.finance.product.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.finance.product.entity.ProductArchivesBill;

/**
 * 档案清单
 * @createTime 2017年1月10日 上午11:42:41
 * @author <a href="mailto:gufeng@zdsoft.cn">gufeng</a>
 * @version 1.0
 */
public interface ProductArchivesBillRepository extends CustomRepository<ProductArchivesBill, String>{

	/**
	 * 逻辑删除
	 * @param productId 产品id
	 */
	@Modifying
	@Query("update ProductArchivesBill set isDeleted = 1 where productId = :productId")
	public void logicByProduct(@Param("productId")String productId);

	/**
	 * 查询
	 * @param productId
	 * @return
	 * @throws BusinessException
	 */
	@Query("select pab from ProductArchivesBill pab where pab.isDeleted=false and pab.productId=:productId ")
	public List<ProductArchivesBill> findByProductId(@Param("productId")String productId) throws BusinessException;
}