package com.zdsoft.finance.product.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.finance.product.entity.RepayPlanConfig;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.Pageable;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title: RepayPlanConfigRepository.java 
 * @ClassName: RepayPlanConfigRepository 
 * @Description: 还款计划配置操作仓库
 * @author gufeng 
 * @date 2017年3月6日 下午5:00:10 
 * @version V1.0
 */
public interface RepayPlanConfigRepository extends CustomRepository<RepayPlanConfig, String>{

	/**
	 * @Title: findPage 
	 * @Description: 还款计划列表并分页
	 * @author gufeng 
	 * @param repayPlanConfig 查询条件
	 * @param pageable 分页 
	 * @return 分页数据
	 */
	public Page<RepayPlanConfig> findPage(RepayPlanConfig repayPlanConfig,Pageable pageable);

	/**
	 * @Title: findByProductId 
	 * @Description: 有效数据查询
	 * @author gufeng 
	 * @param productId 产品id
	 * @return 有效数据
	 */
	@Query("select rpc from RepayPlanConfig rpc where rpc.isDeleted=false and rpc.product.id=:productId ")
	public List<RepayPlanConfig> findByProductId(@Param("productId")String productId);
	
	/**
	 * @Title: findByProductIdAndFeeCodeAndIsDeleted 
	 * @Description: 还款计划配置查询
	 * @author gufeng 
	 * @param productId 产品id
	 * @param feeCode 费用项
	 * @param isDeleted 是否删除
	 * @return 还款计划配置
	 */
	public List<RepayPlanConfig> findByProductIdAndFeeCodeAndIsDeleted(String productId, String feeCode, boolean isDeleted);

	/**
	 * @Title: findByProductIdAndFeeCodeAndIdNotAndIsDeleted 
	 * @Description: 还款计划配置查询
	 * @author gufeng 
	 * @param productId 产品id
	 * @param feeCode 费用项
	 * @param id 主键
	 * @param isDeleted 是否删除
	 * @return 还款计划配置
	 */
	public List<RepayPlanConfig> findByProductIdAndFeeCodeAndIdNotAndIsDeleted(String productId, String feeCode, String id,
			boolean isDeleted);
}
