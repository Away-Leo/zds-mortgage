package com.zdsoft.finance.product.service;

import java.util.List;

import com.zdsoft.essential.dto.emp.EmpDto;
import com.zdsoft.finance.base.service.BaseService;
import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.finance.product.entity.CompanyProduct;
import com.zdsoft.finance.product.entity.Product;


/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title: CompanyProductService.java 
 * @ClassName: CompanyProductService 
 * @Description: 中间表机构产品接口
 * @author gufeng 
 * @date 2017年3月14日 下午4:50:08 
 * @version V1.0
 */
public interface CompanyProductService extends BaseService<CompanyProduct>{

	/**
	 * @Title: delete 
	 * @Description: 物理删除
	 * @author gufeng 
	 * @param id 主键id
	 * @throws BusinessException 删除异常
	 */
	public void delete(String id) throws BusinessException;
	
	/**
	 * @Title: findByProductId 
	 * @Description: 通过产品id查询
	 * @author gufeng 
	 * @param productId 产品id
	 * @return 中间表数据
	 * @throws BusinessException 查询异常
	 */
	public List<CompanyProduct> findByProductId(String productId) throws BusinessException;
	
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
}
