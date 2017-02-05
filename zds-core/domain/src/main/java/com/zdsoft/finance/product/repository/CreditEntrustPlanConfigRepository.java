package com.zdsoft.finance.product.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.finance.product.entity.CreditEntrustPlanConfig;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.Pageable;

/**
 * 资金计划配置操作仓库
 * @author longwei
 * @date 2017/01/17
 * @version 1.0
 */
public interface CreditEntrustPlanConfigRepository extends CustomRepository<CreditEntrustPlanConfig, String> {

	/**
	 * 查询分页列表
	 * @param creditEntrustPlanConfig
	 * @param pageable
	 * @return
	 * @throws BusinessException
	 */
	public Page<CreditEntrustPlanConfig> findByPage(CreditEntrustPlanConfig creditEntrustPlanConfig,Pageable pageable);
	
	/**
	 * 查询
	 * @param productId
	 * @return
	 * @throws BusinessException
	 */
	@Query("select pc from CreditEntrustPlanConfig pc where pc.isDeleted=false and pc.product.id=:productId")
	public List<CreditEntrustPlanConfig> findByProductId(@Param("productId")String productId) throws BusinessException;
	
	/**
	 * 查询
	 * @param productId
	 * @param creditId
	 * @return
	 */
	public List<CreditEntrustPlanConfig> findByProductIdAndCapitalistId(String productId, String capitalistId);
}
