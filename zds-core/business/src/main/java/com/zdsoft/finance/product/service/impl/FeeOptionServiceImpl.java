package com.zdsoft.finance.product.service.impl;

import com.zdsoft.essential.dto.emp.EmpDto;
import com.zdsoft.finance.base.service.impl.BaseServiceImpl;
import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.finance.common.utils.ObjectProperUtil;
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
 * 机构产品费用项service
 *
 * @author LiaoGuoWei
 * @create 2017-01-03 10:23
 **/
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
	public FeeOption deleteById(String id) throws BusinessException {
		if (ObjectHelper.isNotEmpty(id)) {
			FeeOption feeOption = this.findById(id);
			feeOption = this.feeOptionRepository.logicDelete(feeOption);
			if (ObjectHelper.isNotEmpty(feeOption) && feeOption.getIsDeleted()) {
				return feeOption;
			} else {
				throw new BusinessException("10010009", "逻辑删除相应数据出错");
			}
		} else {
			throw new BusinessException("10010004", "未传入相关数据");
		}
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public FeeOption saveFeeOption(FeeOption feeOption) throws BusinessException {
		if (ObjectHelper.isNotEmpty(feeOption)) {
			if (ObjectHelper.isEmpty(feeOption.getId())) {
				FeeOption savedFeeOption = this.feeOptionRepository.saveEntity(feeOption);
				if (ObjectHelper.isNotEmpty(savedFeeOption) && ObjectHelper.isNotEmpty(savedFeeOption.getId())) {
					return savedFeeOption;
				} else {
					throw new BusinessException("10010010", "保存数据出错");
				}
			} else {
				throw new BusinessException("10010003", "传入参数有误");
			}
		} else {
			throw new BusinessException("10010004", "未传入相关参数");
		}
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public FeeOption updateFeeOption(FeeOption feeOption) throws BusinessException {
		if (ObjectHelper.isNotEmpty(feeOption)) {
			if (ObjectHelper.isNotEmpty(feeOption.getId())) {
				FeeOption oldFeeOption = this.findById(feeOption.getId());
				// 将新值复制到旧值上
				oldFeeOption = (FeeOption) ObjectProperUtil.compareAndValue(feeOption, oldFeeOption, false);
				oldFeeOption = this.feeOptionRepository.updateEntity(oldFeeOption);
				return oldFeeOption;
			} else {
				throw new BusinessException("10010003", "传入参数有误");
			}
		} else {
			throw new BusinessException("10010004", "未传入相关参数");
		}
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
	public List<FeeOption> findAllByProductIdAndChargeTypeCode(String productId, String chargeTypeCd)
			throws BusinessException {
		if (ObjectHelper.isNotEmpty(productId)&&ObjectHelper.isNotEmpty(chargeTypeCd)) {
			List<FeeOption> feeOptionList = feeOptionRepository.findAllByProductCodeAndChargeTypeCode(productId, chargeTypeCd);
			return feeOptionList;
		} else {
			throw new BusinessException("10010004", "未传入相关参数");
		}

	}

	@Override
	public List<FeeOption> findDistinctChargeTypeCodeByProductId(String productId)
			throws BusinessException {
		if (ObjectHelper.isNotEmpty(productId)) {
			List<FeeOption> feeOptionList = feeOptionRepository.findDistinctChargeTypeCdByProductCd(productId);
			return feeOptionList;
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
				BeanUtils.copyProperties(feeOption, newFeeOption,new String[]{"id","version","isDeleted","updateTime","updateBy","updateOrgCd","productCode"});
				newFeeOption.setCreateBy(empDto.getEmpCd());
				newFeeOption.setCreateOrgCd(empDto.getOrgCd());
				newFeeOption.setCreateTime(new Date());
				newFeeOption.setProductCode(newProduct.getId());
				this.saveEntity(newFeeOption);
			}
		}
	}
}
