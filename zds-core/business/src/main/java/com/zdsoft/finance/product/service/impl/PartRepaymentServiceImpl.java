package com.zdsoft.finance.product.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zdsoft.essential.dto.emp.EmpDto;
import com.zdsoft.finance.base.service.impl.BaseServiceImpl;
import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.finance.product.entity.PartRepayment;
import com.zdsoft.finance.product.entity.Product;
import com.zdsoft.finance.product.repository.PartRepaymentRepository;
import com.zdsoft.finance.product.service.PartRepaymentService;
import com.zdsoft.framework.core.common.util.ObjectHelper;

/**
 * 分段还款
 * @createTime 2017年1月10日 下午2:49:52
 * @author <a href="mailto:gufeng@zdsoft.cn">gufeng</a>
 * @version 1.0
 */
@Service
public class PartRepaymentServiceImpl extends BaseServiceImpl<PartRepayment, CustomRepository<PartRepayment,String>> 
implements PartRepaymentService {

	@Autowired
	private PartRepaymentRepository partRepaymentRepository;

	@Override
	@Transactional(rollbackFor = BusinessException.class)
	public PartRepayment saveOrUpdate(PartRepayment po) throws BusinessException {
		PartRepayment bean = null;
		if(ObjectHelper.isNotEmpty(po.getId())){
			bean = partRepaymentRepository.findOne(po.getId());
			if(ObjectHelper.isEmpty(bean)){
				throw new BusinessException("100000001", "未找到分段还款");
			}
			BeanUtils.copyProperties(po, bean,new String[]{"createBy","createTime","createOrgCd","id","version","isDeleted"});
			bean = partRepaymentRepository.updateEntity(bean);
		}else{
			bean = partRepaymentRepository.saveEntity(po);
		}
		return bean;
	}

	@Override
	@Transactional(rollbackFor = BusinessException.class)
	public void deleteByProductId(String productId) throws BusinessException {
		if(ObjectHelper.isEmpty(productId)){
			throw new BusinessException("100000001","产品id未空");
		}
		partRepaymentRepository.logicByProduct(productId);
	}
	
	@Override
	public List<PartRepayment> findByProductId(String productId) throws BusinessException {
		if(ObjectHelper.isEmpty(productId)){
			logger.error("参数为空");
			throw new BusinessException("参数为空");
		}
		return partRepaymentRepository.findByProductId(productId);
	}

	@Override
	@Transactional
	public void copy(Product oldProduct,Product newProduct, EmpDto empDto) throws BusinessException {
		if(ObjectHelper.isEmpty(oldProduct) || ObjectHelper.isEmpty(empDto) || ObjectHelper.isEmpty(newProduct)){
			logger.error("参数不合法");
			throw new BusinessException("参数不合法");
		}
		
		List<PartRepayment> list=this.findByProductId(oldProduct.getId());
		if(ObjectHelper.isNotEmpty(list)){
			for(PartRepayment partRepayment:list){
				PartRepayment newPartRepayment=new PartRepayment();
				BeanUtils.copyProperties(partRepayment, newPartRepayment,new String[]{"id","version","isDeleted","updateTime","updateBy","updateOrgCd","productId"});
				newPartRepayment.setCreateBy(empDto.getEmpCd());
				newPartRepayment.setCreateOrgCd(empDto.getOrgCd());
				newPartRepayment.setCreateTime(new Date());
				newPartRepayment.setProductId(newProduct.getId());
				this.saveEntity(newPartRepayment);
			}
		}
	}
}
