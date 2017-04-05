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
 * 版权所有：重庆正大华日软件有限公司
 * @Title: RepayPlanConfigService.java 
 * @ClassName: RepayPlanConfigService 
 * @Description: 还款计划配置操作接口
 * @author gufeng 
 * @date 2017年3月6日 下午5:00:27 
 * @version V1.0
 */
public interface RepayPlanConfigService extends BaseService<RepayPlanConfig>{

	/**
	 * @Title: findPage 
	 * @Description: 查询还款计划配置列表并分页
	 * @author gufeng 
	 * @param repayPlanConfig 查询条件
	 * @param pageable 分页
	 * @return 分页数据
	 * @throws BusinessException 查询异常
	 */
	public Page<RepayPlanConfig> findPage(RepayPlanConfig repayPlanConfig,Pageable pageable) throws BusinessException;
	
	/**
	 * @Title: saveOrUpdate 
	 * @Description: 修改或保存还款计划配置
	 * @author gufeng 
	 * @param repayPlanConfig 数据
	 * @return 操作后数据
	 * @throws BusinessException 异常
	 */
	public RepayPlanConfig saveOrUpdate(RepayPlanConfig repayPlanConfig) throws BusinessException;
	
	/**
	 * @Title: findByProductId 
	 * @Description: 整个产品查询
	 * @author gufeng
	 * @param productId 产品id 
	 * @return 有效还款计划配置
	 * @throws BusinessException
	 */
	public List<RepayPlanConfig> findByProductId(String productId) throws BusinessException;
	
	/**
	 * @Title: copy 
	 * @Description: 复制
	 * @author gufeng 
	 * @param oldProduct 旧产品
	 * @param newProduct 新产品
	 * @param empDto 员工
	 * @throws BusinessException 复制异常
	 */
	public void copy(Product oldProduct,Product newProduct,EmpDto empDto) throws BusinessException;
}
