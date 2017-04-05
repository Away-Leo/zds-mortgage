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
 * 版权所有：重庆正大华日软件有限公司
 * @Title: ApprovalOpinionService.java 
 * @ClassName: ApprovalOpinionService 
 * @Description: 产品审批意见操作接口
 * @author gufeng 
 * @date 2017年3月14日 下午4:46:11 
 * @version V1.0
 */
public interface ApprovalOpinionService extends BaseService<ApprovalOpinion>{

	/**
	 * @Title: findPage 
	 * @Description: 查询对象列表并分页
	 * @author gufeng 
	 * @param approvalOpinion 条件参数
	 * @param pageable 分页参数
	 * @return 分页数据
	 * @throws BusinessException 查询异常
	 */
	public Page<ApprovalOpinion> findPage(ApprovalOpinion approvalOpinion,Pageable pageable) throws BusinessException;
	
	/**
	 * @Title: saveOrUpdate 
	 * @Description: 保存或修改审批意见
	 * @author gufeng 
	 * @param approvalOpinion 查询条件
	 * @return 审批意见
	 * @throws BusinessException 查询异常
	 */
	public ApprovalOpinion saveOrUpdate(ApprovalOpinion approvalOpinion) throws BusinessException;
	
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
	
	/**
	 * @Title: findByProductId 
	 * @Description: 查询
	 * @author gufeng 
	 * @param productId 产品id
	 * @return 审批意见
	 * @throws BusinessException 查询异常
	 */
	public List<ApprovalOpinion> findByProductId(String productId) throws BusinessException;
	
	/**
	 * @Title: findByProductIdAndApprovalTypeCode 
	 * @Description: 审批意见查询
	 * @author gufeng 
	 * @param productId 产品id
	 * @param approvalTypeCode 审批类型
	 * @return 审批意见
	 * @throws BusinessException 查询异常
	 */
	public List<ApprovalOpinion> findByProductIdAndApprovalTypeCode(String productId,String approvalTypeCode) throws BusinessException;
}
