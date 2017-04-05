package com.zdsoft.finance.product.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.finance.product.entity.FeeOption;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title: FeeOptionRepository.java 
 * @ClassName: FeeOptionRepository 
 * @Description: 机构产品费用项
 * @author gufeng 
 * @date 2017年3月6日 上午10:54:36 
 * @version V1.0
 */
public interface FeeOptionRepository extends CustomRepository<FeeOption,String> {
	
	/**
	 * @Title: findAllByProductIdAndFeeType 
	 * @Description: 根据产品id和费用类型
	 * @author gufeng 
	 * @param productId 产品id
	 * @param feeType 费用类型
	 * @return 费用项
	 */
	public List<FeeOption> findByProductIdAndFeeType(String productId,String feeType);
    
	/**
	 * @Title: findByProductId 
	 * @Description: 查询
	 * @author gufeng 
	 * @param productId 产品id
	 * @return 费用项
	 */
	@Query("select fo from FeeOption fo where fo.isDeleted=false and fo.productId=:productId ")
	public List<FeeOption> findByProductId(@Param("productId")String productId);
	
	/**
	 * @Title: findCostItemByCode 
	 * @Description: 根据编号获取名字
	 * @author gufeng 
	 * @param code 编号
	 * @return 名字
	 */
	public String findCostItemByCode(String code);
}
