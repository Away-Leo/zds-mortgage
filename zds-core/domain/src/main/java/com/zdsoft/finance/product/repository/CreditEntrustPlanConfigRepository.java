package com.zdsoft.finance.product.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.finance.product.entity.CreditEntrustPlanConfig;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.Pageable;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title: CreditEntrustPlanConfigRepository.java 
 * @ClassName: CreditEntrustPlanConfigRepository 
 * @Description: 资金计划配置
 * @author gufeng 
 * @date 2017年3月6日 下午7:53:57 
 * @version V1.0
 */
public interface CreditEntrustPlanConfigRepository extends CustomRepository<CreditEntrustPlanConfig, String> {

	/**
	 * @Title: findByPage 
	 * @Description: 查询分页列表
	 * @author gufeng 
	 * @param creditEntrustPlanConfig 条件
	 * @param pageable 分页
	 * @return 分页数据
	 */
	public Page<CreditEntrustPlanConfig> findByPage(CreditEntrustPlanConfig creditEntrustPlanConfig, Pageable pageable);

	/**
	 * @Title: findByProductId 
	 * @Description: 产品查询
	 * @author gufeng 
	 * @param productId 产品id
	 * @return 有效数据
	 */
	@Query("select pc from CreditEntrustPlanConfig pc where pc.isDeleted=false and pc.product.id=:productId")
	public List<CreditEntrustPlanConfig> findByProductId(@Param("productId") String productId);

	/**
	 * @Title: findByProductIdAndCapitalistId 
	 * @Description: 资金计划查询
	 * @author gufeng 
	 * @param productId 产品id
	 * @param capitalistId 资方id
	 * @return 资金计划配置数据
	 */
	public List<CreditEntrustPlanConfig> findByProductIdAndCapitalistId(String productId, String capitalistId);

	/**
	 * @Title: findByProductIdAndCapitalistId 
	 * @Description: 查询
	 * @author gufeng 
	 * @param productId 产品id
	 * @param capitalistId 资方id
	 * @param evaluateNum 评估数
	 * @return 资金计划配置数据
	 */
	public List<CreditEntrustPlanConfig> findByProductIdAndCapitalistId(String productId, String capitalistId, String evaluateNum);
	
	/**
	 * @Title: findByProductIdAndCapitalistIdAndCapitalPlanIdAndIsDeleted 
	 * @Description: 资金计划
	 * @author gufeng 
	 * @param productId 产品id
	 * @param capitalistId 资方id
	 * @param capitalPlanId 资金计划id
	 * @param isDeleted 是否删除
	 * @return 资金计划
	 */
	public List<CreditEntrustPlanConfig> findByProductIdAndCapitalistIdAndCapitalPlanIdAndIsDeleted(String productId,
			String capitalistId, String capitalPlanId, boolean isDeleted);
	
	/**
	 * @Title: findByProductIdAndCapitalistIdAndCapitalPlanIdAndIdNotAndIsDeleted 
	 * @Description: 资金计划
	 * @author gufeng 
	 * @param id 产品id
	 * @param capitalistId 资方id
	 * @param capitalPlanId 资金计划id
	 * @param id 主键
	 * @param isDeleted 是否删除
	 * @return 资金计划
	 */
	public List<CreditEntrustPlanConfig> findByProductIdAndCapitalistIdAndCapitalPlanIdAndIdNotAndIsDeleted(String productId,
			String capitalistId, String capitalPlanId, String id, boolean isDeleted);
}
