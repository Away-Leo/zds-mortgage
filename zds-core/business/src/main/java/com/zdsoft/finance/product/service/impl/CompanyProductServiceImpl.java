package com.zdsoft.finance.product.service.impl;

import java.util.Collections;
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
import com.zdsoft.finance.product.entity.Company;
import com.zdsoft.finance.product.entity.CompanyProduct;
import com.zdsoft.finance.product.entity.Product;
import com.zdsoft.finance.product.repository.CompanyProductRepository;
import com.zdsoft.finance.product.service.CompanyProductService;
import com.zdsoft.finance.product.service.CompanyService;
import com.zdsoft.framework.core.common.annotation.Log;
import com.zdsoft.framework.core.common.util.ObjectHelper;


/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title: CompanyProductServiceImpl.java 
 * @ClassName: CompanyProductServiceImpl 
 * @Description: 中间表机构产品接口实现
 * @author gufeng 
 * @date 2017年3月14日 下午4:56:52 
 * @version V1.0
 */
@Service("companyProductService")
public class CompanyProductServiceImpl extends BaseServiceImpl<CompanyProduct, CustomRepository<CompanyProduct,String>> implements CompanyProductService{

	@Log
	private Logger logger;
	
	@Autowired
	private CompanyProductRepository companyProductRepository;
	@Autowired
	private CompanyService companyService;
	
	@Override
	public void delete(String id) throws BusinessException {
		if(ObjectHelper.isEmpty(id)){
			logger.error("传入id为空");
			throw new BusinessException("传入id为空");
		}
		CompanyProduct companyProduct=companyProductRepository.findOne(id);
		if(ObjectHelper.isEmpty(companyProduct)){
			logger.error("实体不存在");
			throw new BusinessException("实体不存在");
		}
		companyProductRepository.delete(companyProduct);
	}

	@Override
	public List<CompanyProduct> findByProductId(String productId) throws BusinessException {
		if(ObjectHelper.isEmpty(productId)){
			logger.error("传入productId为空");
			throw new BusinessException("传入productId为空");
		}
		List<CompanyProduct> companyProducts=companyProductRepository.findByProductId(productId);
		if(ObjectHelper.isEmpty(companyProducts)){
			return Collections.emptyList();
		}
		return companyProducts;
	}
	
	@Override
	@Transactional
	public void copy(Product oldProduct,Product newProduct,EmpDto empDto) throws BusinessException {
		if(ObjectHelper.isEmpty(oldProduct) || ObjectHelper.isEmpty(oldProduct.getId()) || ObjectHelper.isEmpty(newProduct)){
			logger.error("参数不合法");
			throw new BusinessException("参数不合法");
		}
		
		List<CompanyProduct> list = this.findByProductId(oldProduct.getId());
		if(ObjectHelper.isNotEmpty(list)){
			for(CompanyProduct companyProduct:list){
				//机构
				Company newCompany=new Company();
				newCompany.setCreateBy(empDto.getEmpCd());
				newCompany.setCreateOrgCd(empDto.getOrgCd());
				newCompany.setCreateTime(new Date());
				BeanUtils.copyProperties(companyProduct.getCompany(), newCompany,new String[]{"id","version","isDeleted","updateTime","updateBy","updateOrgCd"});
				newCompany=companyService.saveEntity(newCompany);
				//产品机构
				CompanyProduct newCompanyProduct=new CompanyProduct();
				newCompanyProduct.setCreateBy(empDto.getEmpCd());
				newCompanyProduct.setCreateOrgCd(empDto.getOrgCd());
				newCompanyProduct.setCreateTime(new Date());
				newCompanyProduct.setCompany(newCompany);
				newCompanyProduct.setProduct(newProduct);
				this.saveEntity(newCompanyProduct);
			}
		}
	}
}
