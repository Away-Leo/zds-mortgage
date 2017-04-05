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
 * 版权所有：重庆正大华日软件有限公司
 * @Title: CreditEntrustPlanConfigService.java 
 * @ClassName: CreditEntrustPlanConfigService 
 * @Description: 资金计划配置
 * @author gufeng 
 * @date 2017年3月6日 下午7:56:29 
 * @version V1.0
 */
public interface CreditEntrustPlanConfigService extends BaseService<CreditEntrustPlanConfig> {

	/**
	 * @Title: findByPage 
	 * @Description: 资金计划配置列表
	 * @author gufeng 
	 * @param creditEntrustPlanConfig 条件
	 * @param pageable 分页
	 * @return 分页数据
	 * @throws BusinessException 查询异常
	 */
	public Page<CreditEntrustPlanConfig> findByPage(CreditEntrustPlanConfig creditEntrustPlanConfig, Pageable pageable)
			throws BusinessException;

	/**
	 * @Title: saveOrUpdate 
	 * @Description: 保存或修改
	 * @author gufeng 
	 * @param creditEntrustPlanConfig 数据
	 * @return 保存后数据
	 * @throws BusinessException 保存异常
	 */
	public CreditEntrustPlanConfig saveOrUpdate(CreditEntrustPlanConfig creditEntrustPlanConfig)
			throws BusinessException;

	/**
	 * @Title: delete 
	 * @Description: 删除
	 * @author gufeng 
	 * @param creditEntrustPlanConfigId 资金计划id
	 * @throws BusinessException 删除异常
	 */
	public void delete(String creditEntrustPlanConfigId) throws BusinessException;

	/**
	 * @Title: findByProductId 
	 * @Description: 查询
	 * @author gufeng 
	 * @param productId 产品id
	 * @return 有效数据
	 * @throws BusinessException 查询异常
	 */
	public List<CreditEntrustPlanConfig> findByProductId(String productId) throws BusinessException;

	/**
	 * @Title: copy 
	 * @Description: 复制
	 * @author gufeng 
	 * @param oldProduct 旧产品
	 * @param newProduct 新产品
	 * @param empDto 员工
	 * @throws BusinessException 复制异常
	 */
	public void copy(Product oldProduct, Product newProduct, EmpDto empDto) throws BusinessException;

	/**
	 * @Title: findByProductIdAndCapitalistId 
	 * @Description: 计划规则查询
	 * @author gufeng 
	 * @param productId 产品id
	 * @param capitalistId 资方id
	 * @return 资金计划规则
	 * @throws BusinessException 查询异常
	 */
	public CreditEntrustPlanConfig findByProductIdAndCapitalistId(String productId, String capitalistId)
			throws BusinessException;

	/**
	 * @Title: findByProductIdAndCapitalistId 
	 * @Description: 计划规则查询
	 * @author gufeng 
	 * @param productId 产品id
	 * @param capitalistId 资方id
	 * @param evaluateNum 评估数
	 * @return 资金计划规则
	 * @throws BusinessException 查询异常
	 */
	public CreditEntrustPlanConfig findByProductIdAndCapitalistId(String productId, String capitalistId,
			String evaluateNum) throws BusinessException;
}
