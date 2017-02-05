package com.zdsoft.finance.product.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.finance.product.entity.ApprovalOpinion;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.Pageable;

/**
 * 产品审批意见配置操作仓库
 * @author longwei
 * @date 2016/12/28
 * @version 1.0
 */
public interface ApprovalOpinionRepository extends CustomRepository<ApprovalOpinion, String>{

	/**
	 * 查询审批意见列表并分页
	 * @param approvalOpinion
	 * @return
	 * @throws BusinessException
	 */
	public Page<ApprovalOpinion> findPage(ApprovalOpinion approvalOpinion,Pageable pageable) throws BusinessException;

	/**
	 * 查询
	 * @param productId
	 * @return
	 * @throws BusinessException
	 */
	@Query("select ao from ApprovalOpinion ao where ao.isDeleted=false and ao.product.id=:productId ")
	public List<ApprovalOpinion> findByProductId(@Param("productId")String productId) throws BusinessException;
}
