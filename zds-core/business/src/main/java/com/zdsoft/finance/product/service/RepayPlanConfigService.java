package com.zdsoft.finance.product.service;

import java.util.List;

import com.zdsoft.essential.dto.emp.EmpDto;
import com.zdsoft.finance.base.service.BaseService;
import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.finance.product.entity.Product;
import com.zdsoft.finance.product.entity.RepayPlanConfig;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.Pageable;

/**
 * 还款计划配置操作接口
 * @author longwei
 * @date 2016/12/28
 * @version 1.0
 */
public interface RepayPlanConfigService extends BaseService<RepayPlanConfig>{

	/**
	 * 查询还款计划配置列表并分页
	 * @param repayPlanConfig
	 * @param pageable
	 * @return
	 * @throws BusinessException
	 */
	public Page<RepayPlanConfig> findPage(RepayPlanConfig repayPlanConfig,Pageable pageable) throws BusinessException;
	
	/**
	 * 修改或保存还款计划配置
	 * @param repayPlanConfig
	 * @return
	 * @throws BusinessException
	 */
	public RepayPlanConfig saveOrUpdate(RepayPlanConfig repayPlanConfig) throws BusinessException;
	
	/**
	 * 查询
	 * @param productId
	 * @return
	 * @throws BusinessException
	 */
	public List<RepayPlanConfig> findByProductId(String productId) throws BusinessException;
	
	/**
	 * 复制
	 * @param product
	 * @param empDto
	 * @throws BusinessException
	 */
	public void copy(Product oldProduct,Product newProduct,EmpDto empDto) throws BusinessException;
}
