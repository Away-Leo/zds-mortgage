package com.zdsoft.finance.product.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.finance.product.entity.ProcessConfig;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.Pageable;

/**
 * 流程配置操作仓库
 * @author longwei
 * @date 2016/12/28
 * @version 1.0
 */
public interface ProcessConfigRepository extends CustomRepository<ProcessConfig, String>{

	/**
	 * 查询流程配置列表并分页
	 */
	public Page<ProcessConfig> findPage(ProcessConfig processConfig,Pageable pageable) throws BusinessException;
	
	/**
	 * 查询
	 * @param productId
	 * @return
	 * @throws BusinessException
	 */
	@Query("select pc from ProcessConfig pc where pc.isDeleted=false and pc.product.id=:productId")
	public List<ProcessConfig> findByProductId(@Param("productId")String productId) throws BusinessException;
	
	/**
	 * 查询流程配置
	 * @param productId
	 * @param processConfigCode
	 * @return
	 * @throws BusinessException
	 */
	@Query("select pc from ProcessConfig pc where pc.isDeleted=false and pc.product.id=:productId and pc.processFormCd=:processFormCd ")
	public List<ProcessConfig> findByProductIdAndProcessConfigCode(@Param("productId")String productId, @Param("processFormCd")String processConfigCode) throws BusinessException;

}
