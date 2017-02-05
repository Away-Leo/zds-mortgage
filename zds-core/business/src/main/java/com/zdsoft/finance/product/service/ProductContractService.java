package com.zdsoft.finance.product.service;

import java.util.List;
import java.util.Map;

import com.zdsoft.essential.dto.emp.EmpDto;
import com.zdsoft.finance.base.service.BaseService;
import com.zdsoft.finance.common.base.QueryObj;
import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.finance.product.entity.Product;
import com.zdsoft.finance.product.entity.ProductContract;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.PageRequest;

/**
 * 产品合同中间表
 * @author <a href="mailto:gufeng@zdsoft.cn">gufeng</a>
 * @date  2016-12-28
 */
/**
 * 产品合同模板关系
 * @createTime 2017年1月10日 下午3:52:19
 * @author <a href="mailto:gufeng@zdsoft.cn">gufeng</a>
 * @version 1.0
 */
public interface ProductContractService extends BaseService<ProductContract>{

	/**
	 * 删除
	 * @param productId 产品id
	 * @throws BusinessException 异常
	 */
	public void deleteByProductId(String productId)throws BusinessException;

	/**
	 * sql 分页
	 * @param pageable 分页参数
	 * @param queryObjs 条件
	 * @return 分页数据
	 */
	public Page<Map<String, Object>> getProductContractPage(PageRequest pageable, List<QueryObj> queryObjs);
	
	/**
	 * 查询合同选择
	 * @param productId
	 * @return 合同集合
	 * @throws BusinessException 异常
	 */
	public List<Map<String,Object>> selectContract(String productId)throws BusinessException;

	/**
	 * 保存
	 * @param po 需要保存的对象
	 * @throws BusinessException 异常
	 */
	public void saveOrUpdate(ProductContract po)throws BusinessException;
	
	/**
	 * 查询
	 * @param productId
	 * @return
	 * @throws BusinessException
	 */
	public List<ProductContract> findByProductId(String productId) throws BusinessException;
	
	/**
	 * 复制
	 * @param product
	 * @param empDto
	 * @throws BusinessException
	 */
	public void copy(Product oldProduct,Product newProduct,EmpDto empDto) throws BusinessException;
}
