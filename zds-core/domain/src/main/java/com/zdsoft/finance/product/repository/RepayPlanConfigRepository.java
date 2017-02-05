package com.zdsoft.finance.product.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.finance.product.entity.RepayPlanConfig;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.Pageable;

/**
 * 还款计划配置操作仓库
 * @author longwei
 * @date 2016/12/28
 * @version 1.0
 */
public interface RepayPlanConfigRepository extends CustomRepository<RepayPlanConfig, String>{

	/**
	 * 还款计划列表并分页
	 */
	public Page<RepayPlanConfig> findPage(RepayPlanConfig repayPlanConfig,Pageable pageable) throws BusinessException;

	/**
	 * 查询
	 * @return
	 * @throws BusinessException
	 */
	@Query("select rpc from RepayPlanConfig rpc where rpc.isDeleted=false and rpc.product.id=:productId ")
	public List<RepayPlanConfig> findByProductId(@Param("productId")String productId) throws BusinessException;
}
