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
 * 版权所有：重庆正大华日软件有限公司
 * @Title: ProductContractService.java 
 * @ClassName: ProductContractService 
 * @Description: 产品合同中间表
 * @author gufeng 
 * @date 2017年3月13日 下午4:45:12 
 * @version V1.0
 */
public interface ProductContractService extends BaseService<ProductContract>{

	/**
	 * @Title: deleteByProductId 
	 * @Description: 删除
	 * @author gufeng 
	 * @param productId 产品id
	 * @throws BusinessException 异常
	 */
	public void deleteByProductId(String productId)throws BusinessException;

	/**
	 * @Title: getProductContractPage 
	 * @Description: sql 分页
	 * @author gufeng 
	 * @param pageable 分页参数
	 * @param queryObjs 条件
	 * @return 分页数据
	 */
	public Page<Map<String, Object>> getProductContractPage(PageRequest pageable, List<QueryObj> queryObjs);
	
	/**
	 * @Title: selectContract 
	 * @Description: 查询合同选择
	 * @author gufeng 
	 * @param productId 产品id
	 * @return 合同集合
	 * @throws BusinessException 异常
	 */
	public List<Map<String,Object>> selectContract(String productId)throws BusinessException;

	/**
	 * @Title: saveOrUpdate 
	 * @Description: 保存
	 * @author gufeng 
	 * @param po 需要保存的对象
	 * @throws BusinessException 异常
	 */
	public void saveOrUpdate(ProductContract po)throws BusinessException;
	
	/**
	 * @Title: findByProductId 
	 * @Description: 查询
	 * @author gufeng 
	 * @param productId 产品id
	 * @return 机构合同
	 * @throws BusinessException 异常
	 */
	public List<ProductContract> findByProductId(String productId) throws BusinessException;
	
	/**
	 * @Title: copy 
	 * @Description: 复制
	 * @author gufeng 
	 * @param oldProduct 旧产品
	 * @param newProduct 新产品
	 * @param empDto 员工
	 * @throws BusinessException 异常
	 */
	public void copy(Product oldProduct,Product newProduct,EmpDto empDto) throws BusinessException;
}
