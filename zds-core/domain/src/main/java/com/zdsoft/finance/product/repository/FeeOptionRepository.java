package com.zdsoft.finance.product.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.finance.product.entity.FeeOption;

/**
 * 机构产品费用项操作库
 * @author LiaoGuoWei
 * @create 2017-01-03 10:21
 **/
public interface FeeOptionRepository extends CustomRepository<FeeOption,String> {
	/**
	 * 
	 * 根据产品编号和费用类型查询费用项
	 *
	 * @author laijun
	 * @date:2017年1月11日 下午5:21:07
	 * @param productCd
	 * @param chargeTypeCd
	 * @return
	 */
	List<FeeOption> findAllByProductCodeAndChargeTypeCode(String productCd,String chargeTypeCd);
    
    /**
     * 
     * 根据产品编号查询不重复费用类型
     *
     * @author laijun
     * @date:2017年1月11日 下午4:39:00
     * @param productCd
     * @return
     * @throws BusinessException 
     */
	@Query("select distinct id,chargeTypeCode,chargeTypeName from FeeOption fee where fee.productCode = ?1")
    public List<FeeOption> findDistinctChargeTypeCdByProductCd(String productCd) throws BusinessException;
	

	/**
	 * 查询
	 * @param productId
	 * @return
	 * @throws BusinessException
	 */
	@Query("select fo from FeeOption fo where fo.isDeleted=false and fo.productCode=:productId ")
	public List<FeeOption> findByProductId(@Param("productId")String productId) throws BusinessException;
}
