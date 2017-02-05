package com.zdsoft.finance.product.service;

import java.util.List;

import com.zdsoft.essential.dto.emp.EmpDto;
import com.zdsoft.finance.base.service.BaseService;
import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.finance.product.entity.ApprovalOpinion;
import com.zdsoft.finance.product.entity.Product;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.Pageable;

/**
 * 产品审批意见操作接口
 * @author longwe
 * @date 2016/12/28
 * @version 1.0
 */
public interface ApprovalOpinionService extends BaseService<ApprovalOpinion>{

	/**
	 * 查询对象列表并分页
	 * @param approvalOpinion
	 * @param pageable
	 * @return
	 * @throws BusinessException
	 */
	public Page<ApprovalOpinion> findPage(ApprovalOpinion approvalOpinion,Pageable pageable) throws BusinessException;
	
	/**
	 * 保存或修改审批意见
	 * @param approvalOpinion
	 * @return
	 * @throws BusinessException
	 */
	public ApprovalOpinion saveOrUpdate(ApprovalOpinion approvalOpinion) throws BusinessException;
	
	/**
	 * 复制
	 * @param newProduct
	 * @param empDto
	 * @throws BusinessException
	 */
	public void copy(Product oldProduct,Product newProduct,EmpDto empDto) throws BusinessException;
	
	/**
	 * 查询
	 * @param newProduct
	 * @param empDto
	 * @throws BusinessException
	 */
	public List<ApprovalOpinion> findByProductId(String productId) throws BusinessException;
}
