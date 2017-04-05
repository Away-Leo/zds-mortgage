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
 * 版权所有：重庆正大华日软件有限公司
 * @Title: ProcessConfigService.java 
 * @ClassName: ProcessConfigService 
 * @Description: 流程配置
 * @author gufeng 
 * @date 2017年3月6日 下午5:46:32 
 * @version V1.0
 */
public interface ProcessConfigService extends BaseService<ProcessConfig>{

	/**
	 * @Title: findPage 
	 * @Description: 查询流程配置列表并分页
	 * @author gufeng 
	 * @param processConfig 条件
	 * @param pageable 分页
	 * @return 流程配置分页
	 * @throws BusinessException 查询异常
	 */
	public Page<ProcessConfig> findPage(ProcessConfig processConfig,Pageable pageable) throws BusinessException;
	
	/**
	 * @Title: saveOrUpdate 
	 * @Description: 修改或保存流程配置
	 * @author gufeng 
	 * @param processConfig 操作数据
	 * @return 操作后数据
	 * @throws Exception 操作异常
	 */
	public ProcessConfig saveOrUpdate(ProcessConfig processConfig) throws Exception;
	
	/**
	 * @Title: findByProductId 
	 * @Description: 查询
	 * @author gufeng 
	 * @param productId 产品id
	 * @return 所有流程配置
	 * @throws BusinessException 查询异常
	 */
	public List<ProcessConfig> findByProductId(String productId) throws BusinessException;
	
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
	
	/**
	 * @Title: findByProductIdAndProcessCode 
	 * @Description: 查询流程配置
	 * @author gufeng 
	 * @param productId 产品id
	 * @param processCode 配置编号
	 * @return 流程配置
	 * @throws BusinessException 查询异常
	 */
	public ProcessConfig findByProductIdAndProcessCode(String productId,String processCode) throws BusinessException;
}
