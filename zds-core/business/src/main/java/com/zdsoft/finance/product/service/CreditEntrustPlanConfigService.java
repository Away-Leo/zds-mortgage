package com.zdsoft.finance.product.service;

import java.util.List;

import com.zdsoft.essential.dto.emp.EmpDto;
import com.zdsoft.finance.base.service.BaseService;
import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.finance.product.entity.CreditEntrustPlanConfig;
import com.zdsoft.finance.product.entity.Product;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.Pageable;

/**
 * 资金计划配置接口
 * @author longwei
 * @date 2017/01/13
 * @version 1.0
 */
public interface CreditEntrustPlanConfigService extends BaseService<CreditEntrustPlanConfig> {

	/**
	 * 资金计划配置列表
	 * @param creditEntrustPlanConfig
	 * @param pageable
	 * @return
	 * @throws BusinessException
	 */
	public Page<CreditEntrustPlanConfig> findByPage(CreditEntrustPlanConfig creditEntrustPlanConfig,Pageable pageable) throws BusinessException;
	
	/**
	 * 保存或修改
	 * @param creditEntrustPlanConfig
	 * @return
	 * @throws BusinessException
	 */
	public CreditEntrustPlanConfig saveOrUpdate(CreditEntrustPlanConfig creditEntrustPlanConfig) throws BusinessException;
	
	/**
	 * 删除
	 * @param creditEntrustPlanConfigId
	 * @throws BusinessException
	 */
	public void delete(String creditEntrustPlanConfigId) throws BusinessException;
	
	/**
	 * 查询
	 * @return
	 * @throws BusinessException
	 */
	public List<CreditEntrustPlanConfig> findByProductId(String productId) throws BusinessException;
	
	/**
	 * 复制
	 * @param product
	 * @param empDto
	 * @throws BusinessException
	 */
	public void copy(Product oldProduct,Product newProduct,EmpDto empDto) throws BusinessException;
	
	/**
	 * 查询
	 * @param productId
	 * @param creditId
	 * @return
	 * @throws BusinessException
	 */
	public CreditEntrustPlanConfig findByProductIdAndCapitalistId(String productId,String capitalistId) throws BusinessException;
}
