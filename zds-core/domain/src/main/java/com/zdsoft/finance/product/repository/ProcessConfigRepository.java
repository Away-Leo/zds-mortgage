package com.zdsoft.finance.product.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.finance.product.entity.ProcessConfig;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.Pageable;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title: ProcessConfigRepository.java 
 * @ClassName: ProcessConfigRepository 
 * @Description: 流程配置
 * @author gufeng 
 * @date 2017年3月6日 下午5:43:19 
 * @version V1.0
 */
public interface ProcessConfigRepository extends CustomRepository<ProcessConfig, String>{

	/**
	 * @Title: findPage 
	 * @Description: 查询流程配置列表并分页
	 * @author gufeng 
	 * @param processConfig 参数
	 * @param pageable 分页
	 * @return 分页数据
	 */
	public Page<ProcessConfig> findPage(ProcessConfig processConfig,Pageable pageable);
	
	/**
	 * @Title: findByProductId 
	 * @Description: 查询产品所有配置
	 * @author gufeng 
	 * @param productId 产品id
	 * @return 有效流程配置
	 */
	@Query("select pc from ProcessConfig pc where pc.isDeleted=false and pc.product.id=:productId")
	public List<ProcessConfig> findByProductId(@Param("productId")String productId);
	
	/**
	 * @Title: findByProductIdAndProcessConfigCode 
	 * @Description: 查询流程配置
	 * @author gufeng 
	 * @param productId 产品id
	 * @param processCode 流程代码
	 * @return 流程配置
	 */
	@Query("select pc from ProcessConfig pc where pc.isDeleted=false and pc.product.id=:productId and pc.processCode=:processCode ")
	public List<ProcessConfig> findByProductIdAndProcessCode(@Param("productId")String productId, @Param("processCode")String processCode);

}
