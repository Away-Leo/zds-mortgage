package com.zdsoft.finance.product.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.finance.product.entity.PartRepayment;

/**
 * 分段还款
 * @createTime 2017年1月10日 下午2:47:48
 * @author <a href="mailto:gufeng@zdsoft.cn">gufeng</a>
 * @version
 */
public interface PartRepaymentRepository extends CustomRepository<PartRepayment, String>{

	/**
	 * 逻辑删除
	 * @param productId 产品id
	 */
	@Modifying
	@Query("update PartRepayment set isDeleted = 1 where productId = :productId")
	public void logicByProduct(@Param("productId")String productId);

	/**
	 * 查询
	 * @param productId
	 * @return
	 * @throws BusinessException
	 */
	@Query("select pr from PartRepayment pr where pr.isDeleted=false and pr.productId=:productId ")
	public List<PartRepayment> findByProductId(@Param("productId")String productId) throws BusinessException;
}
