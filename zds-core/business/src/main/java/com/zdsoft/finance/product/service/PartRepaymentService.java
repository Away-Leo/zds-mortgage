package com.zdsoft.finance.product.service;

import java.util.List;

import com.zdsoft.essential.dto.emp.EmpDto;
import com.zdsoft.finance.base.service.BaseService;
import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.finance.product.entity.PartRepayment;
import com.zdsoft.finance.product.entity.Product;

/**
 * 分段还款
 * @createTime 2017年1月10日 下午2:48:43
 * @author <a href="mailto:gufeng@zdsoft.cn">gufeng</a>
 * @version 1.0
 */
public interface PartRepaymentService extends BaseService<PartRepayment>{

	/**
	 * 保存或更新
	 * @param po 需要保存的对象
	 * @return 保存后的对象
	 * @throws BusinessException 异常
	 */
	public PartRepayment saveOrUpdate(PartRepayment po)throws BusinessException;

	/**
	 * 删除
	 * @param productId 产品id
	 * @throws BusinessException 异常
	 */
	public void deleteByProductId(String productId)throws BusinessException;
	
	/**
	 * 查询
	 * @param productId
	 * @return
	 * @throws BusinessException
	 */
	public List<PartRepayment> findByProductId(String productId) throws BusinessException;
	
	/**
	 * 复制
	 * @param product
	 * @param empDto
	 * @throws BusinessException
	 */
	public void copy(Product oldProduct,Product newProduct ,EmpDto empDto) throws BusinessException;
}
