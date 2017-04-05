package com.zdsoft.finance.product.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zdsoft.essential.dto.emp.EmpDto;
import com.zdsoft.finance.base.service.impl.BaseServiceImpl;
import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.finance.product.entity.ApprovalOpinion;
import com.zdsoft.finance.product.entity.Product;
import com.zdsoft.finance.product.repository.ApprovalOpinionRepository;
import com.zdsoft.finance.product.service.ApprovalOpinionService;
import com.zdsoft.finance.product.service.ProductService;
import com.zdsoft.framework.core.common.annotation.Log;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.Pageable;
import com.zdsoft.framework.core.common.util.ObjectHelper;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title: ApprovalOpinionServiceImpl.java 
 * @ClassName: ApprovalOpinionServiceImpl 
 * @Description: 产品审批意见
 * @author gufeng 
 * @date 2017年3月6日 下午7:27:37 
 * @version V1.0
 */
@Service("approvalOpinionService")
public class ApprovalOpinionServiceImpl extends BaseServiceImpl<ApprovalOpinion, CustomRepository<ApprovalOpinion,String>> implements ApprovalOpinionService {

	@Log
	private Logger logger;
	
	@Autowired
	private ApprovalOpinionRepository approvalOpinionRepository;
	@Autowired
	private ProductService productService;
	
	@Override
	public Page<ApprovalOpinion> findPage(ApprovalOpinion approvalOpinion, Pageable pageable) throws BusinessException {
		if(ObjectHelper.isEmpty(approvalOpinion) || ObjectHelper.isEmpty(pageable)){
			logger.error("错误，请求参数不能为空");
			throw new BusinessException("错误，请求参数不能为空");
		}
		
		return approvalOpinionRepository.findPage(approvalOpinion, pageable);
	}

	@Override
	@Transactional
	public ApprovalOpinion saveOrUpdate(ApprovalOpinion approvalOpinion) throws BusinessException {
		
		if(ObjectHelper.isEmpty(approvalOpinion) || ObjectHelper.isEmpty(approvalOpinion.getProduct().getId())){
			logger.error("参数为空");
			throw new BusinessException("参数为空");
		}
		
		if(ObjectHelper.isEmpty(approvalOpinion.getId())){
			Product product=productService.findOne(approvalOpinion.getProduct().getId());
			if(ObjectHelper.isEmpty(product)){
				logger.error("产品已不存在，请检查数据");
				throw new BusinessException("产品已不存在，请检查数据");
			}
			approvalOpinion.setProduct(product);
			approvalOpinion=this.saveEntity(approvalOpinion);
		}else{
			ApprovalOpinion old=this.findOne(approvalOpinion.getId());
			if(ObjectHelper.isEmpty(old)){
				logger.error("该数据已不存在，请检查数据");
				throw new BusinessException("该数据已不存在，请检查数据");
			}
			BeanUtils.copyProperties(approvalOpinion, old, new String[]{"id","isDeleted","createTime","createBy","createOrgCd","product"});
			approvalOpinion=this.updateEntity(old);
		}
		
		return approvalOpinion;
	}

	@Override
	@Transactional
	public void copy(Product oldProduct,Product newProduct, EmpDto empDto) throws BusinessException {
		if(ObjectHelper.isEmpty(oldProduct) || ObjectHelper.isEmpty(oldProduct.getId()) || ObjectHelper.isEmpty(newProduct)){
			logger.error("参数不合法");
			throw new BusinessException("参数不合法");
		}
		
		List<ApprovalOpinion> list=this.findByProductId(oldProduct.getId());
		if(ObjectHelper.isNotEmpty(list)){
			for(ApprovalOpinion approvalOpinion:list){
				ApprovalOpinion newApprovalOpinion=new ApprovalOpinion();
				BeanUtils.copyProperties(approvalOpinion, newApprovalOpinion,new String[]{"id","version","isDeleted","updateTime","updateBy","updateOrgCd","product"});
				newApprovalOpinion.setCreateBy(empDto.getEmpCd());
				newApprovalOpinion.setCreateOrgCd(empDto.getOrgCd());
				newApprovalOpinion.setCreateTime(new Date());
				newApprovalOpinion.setProduct(newProduct);
				this.saveEntity(newApprovalOpinion);
			}
		}
	}

	@Override
	public List<ApprovalOpinion> findByProductId(String productId) throws BusinessException {
		if(ObjectHelper.isEmpty(productId)){
			logger.error("参数为空");
			throw new BusinessException("参数为空");
		}
		
		return approvalOpinionRepository.findByProductId(productId);
	}

	@Override
	public List<ApprovalOpinion> findByProductIdAndApprovalTypeCode(String productId, String approvalType)
			throws BusinessException {
		if(ObjectHelper.isEmpty(productId) || ObjectHelper.isEmpty(approvalType)){
			throw new BusinessException("100000001","传入参数错误");
		}
		return approvalOpinionRepository.findByProductIdAndApprovalType(productId,approvalType);
	}
}
