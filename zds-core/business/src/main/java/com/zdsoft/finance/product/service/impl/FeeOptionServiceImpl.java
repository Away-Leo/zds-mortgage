package com.zdsoft.finance.product.service.impl;

import com.zdsoft.essential.dto.emp.EmpDto;
import com.zdsoft.finance.base.service.impl.BaseServiceImpl;
import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.finance.product.entity.FeeOption;
import com.zdsoft.finance.product.entity.Product;
import com.zdsoft.finance.product.repository.FeeOptionRepository;
import com.zdsoft.finance.product.service.FeeOptionService;
import com.zdsoft.framework.core.common.util.ObjectHelper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;


/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: FeeOptionServiceImpl.java 
 * @ClassName: FeeOptionServiceImpl 
 * @Description: 机构产品费用项
 * @author gufeng 
 * @date 2017年3月6日 上午11:17:34 
 * @version V1.0
 */
@Service("feeOptionService")
public class FeeOptionServiceImpl extends BaseServiceImpl<FeeOption, FeeOptionRepository> implements FeeOptionService {

	@Autowired
	private FeeOptionRepository feeOptionRepository;

	@Override
	public FeeOption findById(String id) throws BusinessException {
		if (ObjectHelper.isNotEmpty(id)) {
			FeeOption feeOption = feeOptionRepository.findOne(id);
			if (ObjectHelper.isNotEmpty(feeOption) && ObjectHelper.isNotEmpty(feeOption.getId())) {
				return feeOption;
			} else {
				throw new BusinessException("10010002", "根据参数未找到相应数据！");
			}
		} else {
			throw new BusinessException("10010004", "未传入相关参数！");
		}
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void deleteById(String id) throws BusinessException {
		if (ObjectHelper.isNotEmpty(id)) {
			FeeOption feeOption = this.findById(id);
			this.feeOptionRepository.logicDelete(feeOption);
		} else {
			throw new BusinessException("10010004", "未传入相关数据");
		}
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public FeeOption saveFeeOption(FeeOption feeOption) throws BusinessException {
		if (ObjectHelper.isNotEmpty(feeOption)) {
			FeeOption savedFeeOption = this.feeOptionRepository.saveEntity(feeOption);
			return savedFeeOption;
		} else {
			throw new BusinessException("10010004", "未传入相关参数");
		}
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public FeeOption updateFeeOption(FeeOption feeOption) throws BusinessException {
		FeeOption bean = null;
		if (ObjectHelper.isNotEmpty(feeOption) && ObjectHelper.isNotEmpty(feeOption.getId())) {
			bean = this.findById(feeOption.getId());
		} else {
			throw new BusinessException("10010004", "传入参数有误");
		}
		if(ObjectHelper.isEmpty(bean)){
			throw new BusinessException("10010004", "未找到费用数据,id=" + feeOption.getId());
		}
		BeanUtils.copyProperties(feeOption, bean,new String[]{"isDeleted","createBy","createOrgCd","createTime","version"});
		bean = this.feeOptionRepository.updateEntity(bean);
		return bean;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public FeeOption saveOrUpdateFeeOption(FeeOption feeOption) throws BusinessException {
		if (ObjectHelper.isNotEmpty(feeOption)) {
			if (ObjectHelper.isEmpty(feeOption.getId())) {
				return this.saveFeeOption(feeOption);
			} else {
				return this.updateFeeOption(feeOption);
			}
		} else {
			throw new BusinessException("10010004", "未传入相关参数");
		}
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public List<FeeOption> findByProductIdAndFeeType(String productId, String chargeTypeCd)
			throws BusinessException {
		if (ObjectHelper.isNotEmpty(productId) && ObjectHelper.isNotEmpty(chargeTypeCd)) {
			return feeOptionRepository.findByProductIdAndFeeType(productId, chargeTypeCd);
		} else {
			throw new BusinessException("10010004", "未传入相关参数");
		}
	}

	@Override
	public List<FeeOption> findByProductId(String productId) throws BusinessException {
		if(ObjectHelper.isEmpty(productId)){
			logger.error("参数为空");
			throw new BusinessException("参数为空");
		}
		return feeOptionRepository.findByProductId(productId);
	}

	@Override
	@Transactional
	public void copy(Product oldProduct,Product newProduct, EmpDto empDto) throws BusinessException {
		if(ObjectHelper.isEmpty(oldProduct) || ObjectHelper.isEmpty(empDto) || ObjectHelper.isEmpty(newProduct)){
			logger.error("参数不合法");
			throw new BusinessException("参数不合法");
		}
		List<FeeOption> list=this.findByProductId(oldProduct.getId());
		if(ObjectHelper.isNotEmpty(list)){
			for(FeeOption feeOption:list){
				FeeOption newFeeOption=new FeeOption();
				BeanUtils.copyProperties(feeOption, newFeeOption,new String[]{"id","version","isDeleted","updateTime","updateBy","updateOrgCd","productId"});
				newFeeOption.setCreateBy(empDto.getEmpCd());
				newFeeOption.setCreateOrgCd(empDto.getOrgCd());
				newFeeOption.setCreateTime(new Date());
				newFeeOption.setProductId(newProduct.getId());
				this.saveEntity(newFeeOption);
			}
		}
	}

	@Override
	public String getCostItemName(String code) throws BusinessException {
		if(ObjectHelper.isEmpty(code)){
			throw new BusinessException("1000000001","参数为空");
		}
		return feeOptionRepository.findCostItemByCode(code);
	}
}
