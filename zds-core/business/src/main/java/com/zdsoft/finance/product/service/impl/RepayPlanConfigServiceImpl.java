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
import com.zdsoft.finance.product.entity.Product;
import com.zdsoft.finance.product.entity.RepayPlanConfig;
import com.zdsoft.finance.product.repository.RepayPlanConfigRepository;
import com.zdsoft.finance.product.service.ProductService;
import com.zdsoft.finance.product.service.RepayPlanConfigService;
import com.zdsoft.framework.core.common.annotation.Log;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.Pageable;
import com.zdsoft.framework.core.common.util.ObjectHelper;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title: RepayPlanConfigServiceImpl.java 
 * @ClassName: RepayPlanConfigServiceImpl 
 * @Description: 还款计划配置接口实现
 * @author gufeng 
 * @date 2017年3月6日 下午5:00:39 
 * @version V1.0
 */
@Service("repayPlanConfigService")
public class RepayPlanConfigServiceImpl extends BaseServiceImpl<RepayPlanConfig, CustomRepository<RepayPlanConfig,String>> implements RepayPlanConfigService{

	@Log
	private Logger logger;
	@Autowired
	private RepayPlanConfigRepository repayPlanConfigRepository;
	@Autowired
	private ProductService productService;
	
	@Override
	public Page<RepayPlanConfig> findPage(RepayPlanConfig repayPlanConfig, Pageable pageable) throws BusinessException {
		if(ObjectHelper.isEmpty(repayPlanConfig) || ObjectHelper.isEmpty(pageable)){
			logger.error("错误，请求参数不能为空");
			throw new BusinessException("错误，请求参数不能为空");
		}
		
		return repayPlanConfigRepository.findPage(repayPlanConfig, pageable);
	}

	@Override
	@Transactional
	public RepayPlanConfig saveOrUpdate(RepayPlanConfig repayPlanConfig) throws BusinessException {
		
		if(ObjectHelper.isEmpty(repayPlanConfig) || ObjectHelper.isEmpty(repayPlanConfig.getProduct().getId())){
			logger.error("参数为空");
			throw new BusinessException("参数为空");
		}
		//验证
		this.verify(repayPlanConfig);;
		if(ObjectHelper.isEmpty(repayPlanConfig.getId())){
			Product product=productService.findOne(repayPlanConfig.getProduct().getId());
			if(ObjectHelper.isEmpty(product)){
				logger.error("产品已不存在，请检查数据");
				throw new BusinessException("产品已不存在，请检查数据");
			}
			repayPlanConfig.setProduct(product);
			repayPlanConfig=this.saveEntity(repayPlanConfig);
		}else{
			RepayPlanConfig old=this.findOne(repayPlanConfig.getId());
			if(ObjectHelper.isEmpty(old)){
				logger.error("该数据已不存在，请检查数据");
				throw new BusinessException("该数据已不存在，请检查数据");
			}
			BeanUtils.copyProperties(repayPlanConfig, old, new String[]{"id","isDeleted","createTime","createBy","createOrgCd","product"});
			repayPlanConfig=this.updateEntity(old);
		}
		
		return repayPlanConfig;
	}
	
	/**
	 * @Title: verify 
	 * @Description: 验证
	 * @author gufeng 
	 * @param bean 还款计划
	 * @throws BusinessException 异常
	 */
	public void verify(RepayPlanConfig bean) throws BusinessException{
		Product product = bean.getProduct();
		String feeCode = bean.getFeeCode();
		String id = bean.getId();
		
		if(ObjectHelper.isEmpty(product)){
			throw new BusinessException("1000000001","产品不存在");
		}
		if(ObjectHelper.isEmpty(feeCode)){
			throw new BusinessException("1000000002","费用项目为空");
		}
		List<RepayPlanConfig> list = null;
		if(ObjectHelper.isEmpty(id)){
			list = repayPlanConfigRepository.findByProductIdAndFeeCodeAndIsDeleted(product.getId(),feeCode,false);
		}else{
			list = repayPlanConfigRepository.findByProductIdAndFeeCodeAndIdNotAndIsDeleted(product.getId(),feeCode,id,false);
		}
		if(ObjectHelper.isNotEmpty(list) || list.size() > 0){
			throw new BusinessException("100000003","已存在相同费用项");
		}
	}
	
	@Override
	public List<RepayPlanConfig> findByProductId(String productId) throws BusinessException {
		if(ObjectHelper.isEmpty(productId)){
			logger.error("参数为空");
			throw new BusinessException("参数为空");
		}
		return repayPlanConfigRepository.findByProductId(productId);
	}

	@Override
	@Transactional
	public void copy(Product oldProduct,Product newProduct, EmpDto empDto) throws BusinessException {
		if(ObjectHelper.isEmpty(oldProduct) || ObjectHelper.isEmpty(empDto) || ObjectHelper.isEmpty(newProduct)){
			logger.error("参数不合法");
			throw new BusinessException("参数不合法");
		}
		List<RepayPlanConfig> list=this.findByProductId(oldProduct.getId());
		if(ObjectHelper.isNotEmpty(list)){
			for(RepayPlanConfig repayPlanConfig:list){
				RepayPlanConfig newRepayPlanConfig=new RepayPlanConfig();
				BeanUtils.copyProperties(repayPlanConfig, newRepayPlanConfig,new String[]{"id","version","isDeleted","updateTime","updateBy","updateOrgCd","product"});
				newRepayPlanConfig.setCreateBy(empDto.getEmpCd());
				newRepayPlanConfig.setCreateOrgCd(empDto.getOrgCd());
				newRepayPlanConfig.setCreateTime(new Date());
				newRepayPlanConfig.setProduct(newProduct);
				this.saveEntity(newRepayPlanConfig);
			}
		}
	}
	
}
