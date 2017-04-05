package com.zdsoft.finance.product.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.finance.product.entity.PartRepayment;


/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title: PartRepaymentRepository.java 
 * @ClassName: PartRepaymentRepository 
 * @Description: 分段还款
 * @author gufeng 
 * @date 2017年3月13日 下午4:47:28 
 * @version V1.0
 */
public interface PartRepaymentRepository extends CustomRepository<PartRepayment, String>{

	/**
	 * @Title: logicByProduct 
	 * @Description: 逻辑删除
	 * @author gufeng 
	 * @param productId 产品id
	 */
	@Modifying
	@Query("update PartRepayment set isDeleted = 1 where productId = :productId")
	public void logicByProduct(@Param("productId")String productId);

	/**
	 * @Title: findByProductId 
	 * @Description: 查询
	 * @author gufeng 
	 * @param productId 产品id
	 * @return 条件数据
	 */
	@Query("select pr from PartRepayment pr where pr.isDeleted=false and pr.productId=:productId ")
	public List<PartRepayment> findByProductId(@Param("productId")String productId);
	
	/**
	 * @Title: findByTimeSectionAndProductIdAndIsDeleted 
	 * @Description: 分段还款查询
	 * @author gufeng 
	 * @param timeSection 时间段
	 * @param productId 产品id
	 * @param isDeleted 删除
	 * @return 分段还款数据
	 */
	public List<PartRepayment> findByTimeSectionAndProductIdAndIsDeleted(String timeSection, String productId,
			boolean isDeleted);
	
	/**
	 * @Title: findByTimeSectionAndProductIdAndIsDeletedAndIdNot 
	 * @Description: 分段还款查询
	 * @author gufeng 
	 * @param timeSection 时间段
	 * @param productId 产品id
	 * @param isDeleted 删除
	 * @param id 主键
	 * @return 分段还款数据
	 */
	public List<PartRepayment> findByTimeSectionAndProductIdAndIsDeletedAndIdNot(String timeSection, String productId,
			boolean isDeleted, String id);
}
