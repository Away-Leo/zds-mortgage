package com.zdsoft.finance.product.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.finance.product.entity.ApprovalOpinion;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.Pageable;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title: ApprovalOpinionRepository.java 
 * @ClassName: ApprovalOpinionRepository 
 * @Description: 产品审批意见配置
 * @author gufeng 
 * @date 2017年3月6日 下午7:24:51 
 * @version V1.0
 */
public interface ApprovalOpinionRepository extends CustomRepository<ApprovalOpinion, String>{

	/**
	 * @Title: findPage 
	 * @Description: 查询审批意见列表并分页
	 * @author gufeng 
	 * @param approvalOpinion 条件
	 * @param pageable 分页
	 * @return 分页数据
	 */
	public Page<ApprovalOpinion> findPage(ApprovalOpinion approvalOpinion,Pageable pageable);

	/**
	 * @Title: findByProductId 
	 * @Description: 根据产品查询
	 * @author gufeng 
	 * @param productId 产品id
	 * @return 有效数据
	 */
	@Query("select ao from ApprovalOpinion ao where ao.isDeleted=false and ao.product.id=:productId ")
	public List<ApprovalOpinion> findByProductId(@Param("productId")String productId);

	/**
	 * @Title: findByProductIdAndApprovalType 
	 * @Description: 
	 * @author gufeng 
	 * @param productId 产品id
	 * @param approvalTypeCd 审批类型
	 * @return 审批配置数据
	 */
	@Query("select ao from ApprovalOpinion ao where ao.isDeleted=false and ao.product.id=:productId  and ao.approvalType=:approvalType")
	public List<ApprovalOpinion> findByProductIdAndApprovalType(@Param("productId")String productId, @Param("approvalType")String approvalType);
}
