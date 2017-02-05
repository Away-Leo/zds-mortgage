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
import com.zdsoft.finance.product.entity.CreditEntrustPlanConfig;
import com.zdsoft.finance.product.entity.Product;
import com.zdsoft.finance.product.repository.CreditEntrustPlanConfigRepository;
import com.zdsoft.finance.product.service.CreditEntrustPlanConfigService;
import com.zdsoft.finance.product.service.ProductService;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.Pageable;
import com.zdsoft.framework.core.common.util.ObjectHelper;

/**
 * 资金计划配置接口实现
 * @author longwei 
 * @date 2017/01/13
 * @version 1.0
 */
@Service("creditEntrustPlanConfigService")
public class CreditEntrustPlanConfigServiceImpl extends BaseServiceImpl<CreditEntrustPlanConfig, CustomRepository<CreditEntrustPlanConfig,String>> implements CreditEntrustPlanConfigService {

	@Autowired
	private ProductService productService;
	
	@Autowired
	private CreditEntrustPlanConfigRepository creditEntrustPlanConfigRepository;
	
	@Override
	public Page<CreditEntrustPlanConfig> findByPage(CreditEntrustPlanConfig creditEntrustPlanConfig, Pageable pageable)
			throws BusinessException {
		return creditEntrustPlanConfigRepository.findByPage(creditEntrustPlanConfig,pageable);
	}

	@Override
	@Transactional
	public CreditEntrustPlanConfig saveOrUpdate(CreditEntrustPlanConfig creditEntrustPlanConfig)
			throws BusinessException {
		if(ObjectHelper.isEmpty(creditEntrustPlanConfig.getProduct()) || ObjectHelper.isEmpty(creditEntrustPlanConfig.getProduct().getId())){
			logger.error("参数为空");
			throw new BusinessException("参数为空");
		}
		
		if(ObjectHelper.isEmpty(creditEntrustPlanConfig.getId())){
			// 查询产品
			Product product = productService.findOne(creditEntrustPlanConfig.getProduct().getId());
			if(ObjectHelper.isEmpty(product)){
				logger.error("查询产品不存在");
				throw new BusinessException("查询产品不存在");
			}
			creditEntrustPlanConfig.setProduct(product);
			creditEntrustPlanConfig=this.saveEntity(creditEntrustPlanConfig);
		}else{
			CreditEntrustPlanConfig old=this.findOne(creditEntrustPlanConfig.getId());
			if(ObjectHelper.isEmpty(old)){
				logger.error("未查询到资金计划配置");
				throw new BusinessException("未查询到资金计划配置");
			}
			BeanUtils.copyProperties(creditEntrustPlanConfig, old, new String[]{"id","createTime","isDeleted","createBy","createOrgCd","product"});
			creditEntrustPlanConfig=this.updateEntity(old);
		}
		
		return creditEntrustPlanConfig;
	}

	@Override
	@Transactional
	public void delete(String creditEntrustPlanConfigId) throws BusinessException {
		if(ObjectHelper.isEmpty(creditEntrustPlanConfigId)){
			logger.error("参数为空");
			throw new BusinessException("参数为空");
		}
		
		//查询
		CreditEntrustPlanConfig creditEntrustPlanConfig=this.findOne(creditEntrustPlanConfigId);
		if(ObjectHelper.isEmpty(creditEntrustPlanConfig)){
			logger.error("所要删除的实体为空");
			throw new BusinessException("所要删除的实体为空");
		}
		
		this.logicDelete(creditEntrustPlanConfig);
	}
	
	@Override
	@Transactional
	public void copy(Product oldProduct,Product newProduct, EmpDto empDto) throws BusinessException {
		if(ObjectHelper.isEmpty(oldProduct) || ObjectHelper.isEmpty(empDto) || ObjectHelper.isEmpty(newProduct)){
			logger.error("参数不合法");
			throw new BusinessException("参数不合法");
		}
		
		List<CreditEntrustPlanConfig> list=this.findByProductId(oldProduct.getId());
		if(ObjectHelper.isNotEmpty(list)){
			for(CreditEntrustPlanConfig creditEntrustPlanConfig:list){
				CreditEntrustPlanConfig newCreditEntrustPlanConfig=new CreditEntrustPlanConfig();
				BeanUtils.copyProperties(creditEntrustPlanConfig, newCreditEntrustPlanConfig,new String[]{"id","version","isDeleted","updateTime","updateBy","updateOrgCd","product"});
				newCreditEntrustPlanConfig.setCreateBy(empDto.getEmpCd());
				newCreditEntrustPlanConfig.setCreateOrgCd(empDto.getOrgCd());
				newCreditEntrustPlanConfig.setCreateTime(new Date());
				newCreditEntrustPlanConfig.setProduct(newProduct);
				this.saveEntity(newCreditEntrustPlanConfig);
			}
		}
	}

	@Override
	public List<CreditEntrustPlanConfig> findByProductId(String productId) throws BusinessException {
		if(ObjectHelper.isEmpty(productId)){
			logger.error("参数为空");
			throw new BusinessException("参数为空");
		}
		return creditEntrustPlanConfigRepository.findByProductId(productId);
	}

	@Override
	public CreditEntrustPlanConfig findByProductIdAndCapitalistId(String productId, String capitalistId)
			throws BusinessException {
		if(ObjectHelper.isEmpty(productId) || ObjectHelper.isEmpty(capitalistId)){
			logger.error("参数为空");
			throw new BusinessException("参数为空");
		}
		
		List<CreditEntrustPlanConfig> list=creditEntrustPlanConfigRepository.findByProductIdAndCapitalistId(productId, capitalistId);
		if(ObjectHelper.isNotEmpty(list) && list.size()>0){
			return list.get(0);
		}
		return null;
	}

}
