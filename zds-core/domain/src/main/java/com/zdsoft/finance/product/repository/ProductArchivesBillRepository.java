package com.zdsoft.finance.product.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.finance.product.entity.ProductArchivesBill;


/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title: ProductArchivesBillRepository.java 
 * @ClassName: ProductArchivesBillRepository 
 * @Description: 档案清单
 * @author gufeng 
 * @date 2017年3月13日 下午4:47:37 
 * @version V1.0
 */
public interface ProductArchivesBillRepository extends CustomRepository<ProductArchivesBill, String>{

	/**
	 * @Title: logicByProduct 
	 * @Description: 逻辑删除
	 * @author gufeng 
	 * @param productId 产品id
	 */
	@Modifying
	@Query("update ProductArchivesBill set isDeleted = 1 where productId = :productId")
	public void logicByProduct(@Param("productId")String productId);

	/**
	 * @Title: findByProductId 
	 * @Description: 查询
	 * @author gufeng 
	 * @param productId 产品id
	 * @return  结果
	 * @throws BusinessException 异常
	 */
	@Query("select pab from ProductArchivesBill pab where pab.isDeleted=false and pab.productId=:productId ")
	public List<ProductArchivesBill> findByProductId(@Param("productId")String productId) throws BusinessException;
}