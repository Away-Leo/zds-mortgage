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
 * 版权所有：重庆正大华日软件有限公司
 * @Title: PartRepaymentServiceImpl.java 
 * @ClassName: PartRepaymentServiceImpl 
 * @Description: 分段还款
 * @author gufeng 
 * @date 2017年3月13日 下午4:44:17 
 * @version V1.0
 */
@Service("partRepaymentService")
public class PartRepaymentServiceImpl extends BaseServiceImpl<PartRepayment, CustomRepository<PartRepayment,String>> 
implements PartRepaymentService {

	@Autowired
	private PartRepaymentRepository partRepaymentRepository;

	@Override
	@Transactional(rollbackFor = BusinessException.class)
	public PartRepayment saveOrUpdate(PartRepayment po) throws BusinessException {
		PartRepayment bean = null;
		//时间段验证
		boolean b = this.timeSectionVerify(po);
		if(!b){
			throw new BusinessException("10000000001","存在相同时间段数据");
		}
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
	
	/**
	 * @Title: timeSectionVerify 
	 * @Description: 时间断校验
	 * @author gufeng 
	 * @param po
	 * @return
	 * @throws BusinessException
	 */
	private boolean timeSectionVerify(PartRepayment po)throws BusinessException{
		String timeSection = po.getTimeSection();
		String productId = po.getProductId();
		String id = po.getId();
		if(ObjectHelper.isEmpty(timeSection)){
			throw new BusinessException("10000000001","时间段为空");
		}
		if(ObjectHelper.isEmpty(productId)){
			throw new BusinessException("10000000002","产品id为空");
		}
		List<PartRepayment> list = null;
		if(ObjectHelper.isEmpty(id)){
			list = partRepaymentRepository.findByTimeSectionAndProductIdAndIsDeleted(timeSection,productId,false);
		}else{
			list = partRepaymentRepository.findByTimeSectionAndProductIdAndIsDeletedAndIdNot(timeSection,productId,false,id);
		}
		if(ObjectHelper.isEmpty(list) || list.size() == 0){
			return true;
		}else{
			return false;
		}
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
