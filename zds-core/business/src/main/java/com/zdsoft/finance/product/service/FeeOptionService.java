package com.zdsoft.finance.product.service;

import java.util.List;

import com.zdsoft.essential.dto.emp.EmpDto;
import com.zdsoft.finance.base.service.BaseService;
import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.finance.product.entity.FeeOption;
import com.zdsoft.finance.product.entity.Product;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title: FeeOptionService.java 
 * @ClassName: FeeOptionService 
 * @Description: 机构产品费用项
 * @author gufeng 
 * @date 2017年3月6日 上午11:00:49 
 * @version V1.0
 */
public interface FeeOptionService extends BaseService<FeeOption> {

	/**
	 * @Title: findById 
	 * @Description: 按照ID查找
	 * @author gufeng 
	 * @param id 主键
	 * @return 费用
	 * @throws BusinessException 异常
	 */
    public FeeOption findById(String id)throws BusinessException;

    /**
     * @Title: deleteById 
     * @Description: 按照ID逻辑删除
     * @author gufeng 
     * @param id 主键
     * @throws BusinessException 异常
     */
    public void deleteById(String id) throws BusinessException;

    /**
     * @Title: saveFeeOption 
     * @Description: 保存机构产品费用项
     * @author gufeng 
     * @param feeOption 费用数据
     * @return 保存后的费用 
     * @throws BusinessException 异常
     */
    public FeeOption saveFeeOption(FeeOption feeOption) throws BusinessException;

    /**
     * @Title: updateFeeOption 
     * @Description: 更新机构产品费用项
     * @author gufeng 
     * @param feeOption 费用数据
     * @return 更新后的费用 
     * @throws BusinessException 异常
     */
    public FeeOption updateFeeOption(FeeOption feeOption) throws BusinessException;

    /**
     * @Title: saveOrUpdateFeeOption 
     * @Description: 更新或保存机构费用项
     * @author gufeng 
     * @param feeOption  费用数据
     * @return 更新后的费用 
     * @throws BusinessException 异常
     */
    public FeeOption saveOrUpdateFeeOption(FeeOption feeOption) throws BusinessException;

    /**
     * @Title: findAllByProductIdAndChargeTypeCode 
     * @Description: 根据产品编号和费用类型查询费用项
     * @author gufeng 
     * @param productId 产品id
     * @param feeType 费用类型
     * @return 费用项
     * @throws BusinessException 异常
     */
    public List<FeeOption> findByProductIdAndFeeType(String productId, String feeType) throws BusinessException;
    
    /**
     * @Title: findByProductId 
     * @Description: 查询
     * @author gufeng 
     * @param productId 产品id
     * @return 费用项
     * @throws BusinessException 异常
     */
    public List<FeeOption> findByProductId(String productId) throws BusinessException;
    
    /**
     * @Title: copy 
     * @Description: 复制
     * @author gufeng 
     * @param oldProduct 旧的产品
     * @param newProduct 产品
     * @param empDto 员工
     * @throws BusinessException 复制异常
     */
    public void copy(Product oldProduct, Product newProduct, EmpDto empDto) throws BusinessException;
    
    /**
     * @Title: getCostItemName 
     * @Description: 获取名字
     * @author gufeng 
     * @param code 编号
     * @return 名称
     * @throws BusinessException 异常
     */
    public String getCostItemName(String code) throws BusinessException;

}
