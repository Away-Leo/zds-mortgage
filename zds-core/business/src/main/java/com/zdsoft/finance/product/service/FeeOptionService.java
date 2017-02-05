package com.zdsoft.finance.product.service;

import java.util.List;

import com.zdsoft.essential.dto.emp.EmpDto;
import com.zdsoft.finance.base.service.BaseService;
import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.finance.product.entity.FeeOption;
import com.zdsoft.finance.product.entity.Product;

/**
 * 机构产品费用项service
 * @author LiaoGuoWei
 * @create 2017-01-03 10:22
 **/
public interface FeeOptionService extends BaseService<FeeOption> {

    /**
     * 按照ID查找
     * @param id
     * @return
     * @throws BusinessException
     */
    public FeeOption findById(String id)throws BusinessException;

    /**
     * 按照ID逻辑删除
     * @param id
     * @return
     * @throws BusinessException
     */
    public FeeOption deleteById(String id) throws BusinessException;

    /**
     * 保存机构产品费用项
     * @param feeOption
     * @return
     * @throws BusinessException
     */
    public FeeOption saveFeeOption(FeeOption feeOption) throws BusinessException;

    /**
     * 更新机构产品费用项
     * @param feeOption
     * @return
     * @throws BusinessException
     */
    public FeeOption updateFeeOption(FeeOption feeOption) throws BusinessException;

    /**
     * 更新或保存机构费用项
     * @param feeOption
     * @return
     * @throws BusinessException
     */
    public FeeOption saveOrUpdateFeeOption(FeeOption feeOption) throws BusinessException;
    /**
     * 
     * 根据产品编号和费用类型查询费用项
     *
     * @author laijun
     * @date:2017年1月11日 下午4:39:00
     * @param productCd
     * @return
     * @throws BusinessException 
     */
    public List<FeeOption> findAllByProductIdAndChargeTypeCode(String productId, String ChargeTypeCd) throws BusinessException;
    
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
    public List<FeeOption> findDistinctChargeTypeCodeByProductId(String productId) throws BusinessException;
    
    /**
     * 查询
     * @param product
     * @param empDto
     * @return
     * @throws BusinessException
     */
    public List<FeeOption> findByProductId(String productId) throws BusinessException;
    
    /**
     * 复制
     * @param product
     * @param empDto
     * @throws BusinessException
     */
    public void copy(Product oldProduct, Product newProduct, EmpDto empDto) throws BusinessException;

}
