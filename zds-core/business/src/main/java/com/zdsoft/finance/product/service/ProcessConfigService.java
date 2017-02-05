package com.zdsoft.finance.product.service;

import java.util.List;

import com.zdsoft.essential.dto.emp.EmpDto;
import com.zdsoft.finance.base.service.BaseService;
import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.finance.product.entity.ProcessConfig;
import com.zdsoft.finance.product.entity.Product;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.Pageable;

/**
 * 流程配置操作接口
 * @author longwei
 * @date 2016/12/28
 * @version 1.0
 */
public interface ProcessConfigService extends BaseService<ProcessConfig>{

	/**
	 * 查询流程配置列表并分页
	 * @param processConfig
	 * @param pageable
	 * @return
	 * @throws BusinessException
	 */
	public Page<ProcessConfig> findPage(ProcessConfig processConfig,Pageable pageable) throws BusinessException;
	
	/**
	 * 修改或保存流程配置
	 * @param processConfig
	 * @return
	 * @throws BusinessException
	 */
	public ProcessConfig saveOrUpdate(ProcessConfig processConfig) throws BusinessException;
	
	/**
	 * 查询
	 * @return
	 * @throws BusinessException
	 */
	public List<ProcessConfig> findByProductId(String productId) throws BusinessException;
	
	/**
	 * 复制
	 * @param product
	 * @param empDto
	 * @throws BusinessException
	 */
	public void copy(Product oldProduct,Product newProduct,EmpDto empDto) throws BusinessException;
	
	/**
	 * 查询流程配置
	 * @param productId
	 * @param processConfigCode
	 * @return
	 * @throws BusinessException
	 */
	public ProcessConfig findByProductIdAndProcessConfigCode(String productId,String processConfigCode) throws BusinessException;
}
