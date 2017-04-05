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
import com.zdsoft.finance.common.exception.CodeException;
import com.zdsoft.finance.product.entity.ProcessConfig;
import com.zdsoft.finance.product.entity.Product;
import com.zdsoft.finance.product.repository.ProcessConfigRepository;
import com.zdsoft.finance.product.service.ProcessConfigService;
import com.zdsoft.finance.product.service.ProductService;
import com.zdsoft.framework.core.common.annotation.Log;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.Pageable;
import com.zdsoft.framework.core.common.util.ObjectHelper;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title: ProcessConfigServiceImpl.java 
 * @ClassName: ProcessConfigServiceImpl 
 * @Description: 流程配置
 * @author gufeng 
 * @date 2017年3月6日 下午5:50:12 
 * @version V1.0
 */
@Service("processConfigService")
public class ProcessConfigServiceImpl extends BaseServiceImpl<ProcessConfig, CustomRepository<ProcessConfig,String>> implements ProcessConfigService{

	@Log
	private Logger logger;
	
	@Autowired
	private ProcessConfigRepository processConfigRepository;
	
	@Autowired
	private ProductService productService;
	
	@Override
	public Page<ProcessConfig> findPage(ProcessConfig processConfig, Pageable pageable) throws BusinessException {
		if(ObjectHelper.isEmpty(processConfig) || ObjectHelper.isEmpty(pageable)){
			logger.error("错误，请求参数不能为空");
			throw new BusinessException("错误，请求参数不能为空");
		}
		return processConfigRepository.findPage(processConfig, pageable);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public ProcessConfig saveOrUpdate(ProcessConfig processConfig) throws Exception {
		if(ObjectHelper.isEmpty(processConfig) || ObjectHelper.isEmpty(processConfig.getProduct().getId())){
			logger.error("参数为空");
			throw new BusinessException("参数为空");
		}
		if(ObjectHelper.isEmpty(processConfig.getId())){
			Product product=productService.findOne(processConfig.getProduct().getId());
			if(ObjectHelper.isEmpty(product)){
				logger.error("产品已不存在，请检查数据");
				throw new BusinessException("产品已不存在，请检查数据");
			}
			processConfig.setProduct(product);
			processConfig=this.saveEntity(processConfig);
		}else{
			ProcessConfig old=this.findOne(processConfig.getId());
			if(ObjectHelper.isEmpty(old)){
				logger.error("该数据已不存在，请检查数据");
				throw new BusinessException("该数据已不存在，请检查数据");
			}
			BeanUtils.copyProperties(processConfig, old, new String[]{"id","isDeleted","createTime","createBy","createOrgCd","product"});
			processConfig=this.updateEntity(old);
		}
		//根据产品判断流程配置代码唯一性
				List<ProcessConfig> processConfigs = processConfigRepository.findByProductIdAndProcessCode(processConfig.getProduct().getId(), processConfig.getProcessCode());
				if(processConfigs.size()>1){
					logger.error("添加产品流程配置代码重复（processCode："+processConfig.getProcessCode()+"）");	
					throw new CodeException("10010002","该数据已不存在，请检查数据");
				}
		return processConfig;
	}
	
	@Override
	public List<ProcessConfig> findByProductId(String productId) throws BusinessException {
		if(ObjectHelper.isEmpty(productId)){
			logger.error("参数为空");
			throw new BusinessException("参数为空");
		}
		return processConfigRepository.findByProductId(productId);
	}

	@Override
	@Transactional
	public void copy(Product oldProduct,Product newProduct, EmpDto empDto) throws BusinessException {
		if(ObjectHelper.isEmpty(oldProduct) || ObjectHelper.isEmpty(empDto) || ObjectHelper.isEmpty(newProduct)){
			logger.error("参数不合法");
			throw new BusinessException("参数不合法");
		}
		List<ProcessConfig> list=this.findByProductId(oldProduct.getId());
		if(ObjectHelper.isNotEmpty(list)){
			for(ProcessConfig processConfig:list){
				ProcessConfig newProcessConfig=new ProcessConfig();
				BeanUtils.copyProperties(processConfig, newProcessConfig,new String[]{"id","version","isDeleted","updateTime","updateBy","updateOrgCd","product"});
				newProcessConfig.setCreateBy(empDto.getEmpCd());
				newProcessConfig.setCreateOrgCd(empDto.getOrgCd());
				newProcessConfig.setCreateTime(new Date());
				newProcessConfig.setProduct(newProduct);
				this.saveEntity(newProcessConfig);
			}
		}
	}

	@Override
	public ProcessConfig findByProductIdAndProcessCode(String productId, String processCode)
			throws BusinessException {
		if(ObjectHelper.isEmpty(productId) || ObjectHelper.isEmpty(processCode)){
			logger.error("参数为空");
			throw new BusinessException("参数为空");
		}
		
		List<ProcessConfig> list=processConfigRepository.findByProductIdAndProcessCode(productId, processCode);
		if(ObjectHelper.isNotEmpty(list)){
			return list.get(0);
		}
		return null;
	}
}
