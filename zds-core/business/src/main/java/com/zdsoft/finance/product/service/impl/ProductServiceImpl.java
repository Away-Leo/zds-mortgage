package com.zdsoft.finance.product.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zdsoft.essential.dto.emp.EmpDto;
import com.zdsoft.finance.base.service.impl.BaseServiceImpl;
import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.finance.product.entity.Category;
import com.zdsoft.finance.product.entity.Company;
import com.zdsoft.finance.product.entity.CompanyProduct;
import com.zdsoft.finance.product.entity.Product;
import com.zdsoft.finance.product.entity.ProductRate;
import com.zdsoft.finance.product.repository.CategoryRepository;
import com.zdsoft.finance.product.repository.ProductRepository;
import com.zdsoft.finance.product.service.ApprovalOpinionService;
import com.zdsoft.finance.product.service.CompanyProductService;
import com.zdsoft.finance.product.service.CompanyService;
import com.zdsoft.finance.product.service.CreditEntrustPlanConfigService;
import com.zdsoft.finance.product.service.FeeOptionService;
import com.zdsoft.finance.product.service.MateriaListService;
import com.zdsoft.finance.product.service.PartRepaymentService;
import com.zdsoft.finance.product.service.ProcessConfigService;
import com.zdsoft.finance.product.service.ProductArchivesBillService;
import com.zdsoft.finance.product.service.ProductContractService;
import com.zdsoft.finance.product.service.ProductRateService;
import com.zdsoft.finance.product.service.ProductService;
import com.zdsoft.finance.product.service.RepayPlanConfigService;
import com.zdsoft.framework.core.common.annotation.Log;
import com.zdsoft.framework.core.common.domain.BaseEntity;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.Pageable;
import com.zdsoft.framework.core.common.util.ObjectHelper;

/**
 * 产品主表操作服务实现
 * @author longwei
 * @date 2016/12/22
 * @version 1.0
 */
@Service("productService")
public class ProductServiceImpl extends BaseServiceImpl<Product, CustomRepository<Product,String>> implements ProductService{

	@Log
	private Logger logger;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private ProductRateService productRateService;
	
	@Autowired
	private CompanyService companyService;
	
	@Autowired
	private CompanyProductService companyProductService;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private ApprovalOpinionService approvalOpinionService;
	
	@Autowired
	private MateriaListService materiaListService;
	
	@Autowired
	private ProductArchivesBillService productArchivesBillService;
	
	@Autowired
	private PartRepaymentService partRepaymentService;
	
	@Autowired
	private ProductContractService productContractService;
	
	@Autowired
	private FeeOptionService feeOptionService;
	
	@Autowired
	private RepayPlanConfigService repayPlanConfigService;
	
	@Autowired
	private ProcessConfigService processConfigService;
	
	@Autowired
	private CreditEntrustPlanConfigService creditEntrustPlanConfigService;
	
	@Override
	public Page<Product> find(Product product,String empType, Pageable pageable) throws BusinessException {
		return productRepository.find(product,empType, pageable);
	}

	@Override
	@Transactional
	public Product save(Product product) throws BusinessException {
		if(ObjectHelper.isEmpty(product) && ObjectHelper.isEmpty(product.getCategory().getId())){
			logger.error("传入对象不合法");
			throw new BusinessException("传入对象不合法");
		}
		
		Category category=categoryRepository.findOne(product.getCategory().getId());
		if(ObjectHelper.isEmpty(category)){
			logger.error("产品分类为空");
			throw new BusinessException("产品分类为空");
		}
		product.setCategory(category);
		
		return productRepository.saveEntity(product);
	}

	@Override
	@Transactional
	public Map<String, Object> update(Product product, List<Company> companys)
			throws BusinessException {
		Map<String, Object> map=new HashMap<String,Object>();
		//修改主表
		//查询原产品
		Product oldProduct=this.findOne(product.getId());
		if(ObjectHelper.isEmpty(oldProduct)){
			logger.error("原产品不存在，请检查是否已被删除");
			throw new BusinessException("原产品不存在，请检查是否已被删除");
		}
		BeanUtils.copyProperties(product, oldProduct,new String[]{"id","isDeleted","createTime","createBy","createOrgCd","category","productName","productCode"});
		product=this.updateEntity(oldProduct);
		map.put("product", product);
		
		//机构：先删除原维护机构，在新增机构
		if(ObjectHelper.isNotEmpty(companys)){
			//查询、删除
			List<CompanyProduct> companyProducts=companyProductService.findByProductId(product.getId());
			for(CompanyProduct companyProduct:companyProducts){
				//删除机构产品
				companyProductService.delete(companyProduct.getId());
				//删除机构
				companyService.delete(companyProduct.getCompany().getId());
			}
			
			//新增
			for(Company company:companys){
				//机构
				Company newCompany=companyService.saveEntity(company);
				CompanyProduct newCompanyProduct=new CompanyProduct();
				newCompanyProduct.setProduct(product);
				newCompanyProduct.setCompany(newCompany);
				//机构产品
				companyProductService.saveEntity(newCompanyProduct);
			}
		}
		return map;
	}

	@Override
	@Transactional
	public ProductRate saveOrUpdateRate(ProductRate productRate) throws BusinessException {
		
		if(ObjectHelper.isEmpty(productRate.getProduct()) || ObjectHelper.isEmpty(productRate.getProduct().getId())){
			logger.error("参数异常");
			throw new BusinessException("参数异常");
		}
		
		Product product=this.findOne(productRate.getProduct().getId());
		if(ObjectHelper.isEmpty(product)){
			logger.error("产品未空");
			throw new BusinessException("产品未空");
		}
		
		if(ObjectHelper.isEmpty(productRate.getId())){
			productRate.setProduct(product);
			productRate=productRateService.saveEntity(productRate);
		}else{
			ProductRate old=productRateService.findOne(productRate.getId());
			if(ObjectHelper.isEmpty(old)){
				logger.error("原产品利率不存在");
				throw new BusinessException("原产品利率不存在");
			}
			BeanUtils.copyProperties(productRate, old, new String[]{"id","product","isDeleted","createTime","createBy","createOrgCd"});
			productRate=productRateService.updateEntity(old);
		}
		return productRate;
	}

	@Override
	public boolean checkName(String name) throws BusinessException {
		if(ObjectHelper.isEmpty(name)){
			logger.error("传入参数为空");
			throw new BusinessException("传入参数为空");
		}
		Product product=productRepository.findByName(name);
		if(ObjectHelper.isEmpty(product)){
			return true;
		}
		return false;
	}

	@Override
	@Transactional
	public void invalid(String productId) throws BusinessException {
		
		//查询
		Product product=this.findOne(productId);
		if(ObjectHelper.isEmpty(product)){
			logger.error("产品不存在，不能删除");
			throw new BusinessException("产品不存在，不能删除");
		}
		
		this.logicDelete(product);
	}

	@Override
	@Transactional
	public Product copy(Product product,EmpDto empDto) throws BusinessException {
		if(ObjectHelper.isEmpty(product) || ObjectHelper.isEmpty(empDto)){
			logger.error("参数为空");
			throw new BusinessException("参数为空");
		}
		
		//查询
		Product soruce=this.findOne(product.getId());
		if(ObjectHelper.isEmpty(soruce)){
			logger.error("复制源，产品不存在");
			throw new BusinessException("复制源，产品不存在");
		}
		
		Product target=new Product();
		BeanUtils.copyProperties(soruce, target, new String[]{"id","productName","category","isDeleted","updateTime","updateBy","updateOrgCd"});
		target.setProductName(product.getProductName());
		target.setCategory(soruce.getCategory());
		if(ObjectHelper.isNotEmpty(product.getCreateBy())){
			target.setCreateBy(product.getCreateBy());
		}
		if(ObjectHelper.isNotEmpty(product.getCreateOrgCd())){
			target.setCreateOrgCd(product.getCreateOrgCd());
		}
		if(ObjectHelper.isNotEmpty(product.getCreateTime())){
			target.setCreateTime(product.getCreateTime());
		}

		Product newProduct=this.saveEntity(target);
		// 复制机构
		companyProductService.copy(product,newProduct,empDto);
		// 复制产品利率
		productRateService.copy(product,newProduct,empDto);
		// 复制审批意见
		approvalOpinionService.copy(product,newProduct,empDto);
		// 资料清单
		materiaListService.copy(product,newProduct, empDto);
		// 档案清单
		productArchivesBillService.copy(product,newProduct, empDto);
		// 分段还款
		partRepaymentService.copy(product,newProduct, empDto);
		// 机构合同模板
		productContractService.copy(product,newProduct, empDto);
		// 机构费用项
		feeOptionService.copy(product,newProduct, empDto);
		// 还款计划
		repayPlanConfigService.copy(product,newProduct, empDto);
		// 流程配置
		processConfigService.copy(product,newProduct, empDto);
		// 资金计划配置
		creditEntrustPlanConfigService.copy(product, newProduct, empDto);
		
		return newProduct;
	}

	@Override
	public List<Product> findByCategory(Category category) throws BusinessException {
		if(ObjectHelper.isEmpty(category)){
			logger.error("参数为空");
			throw new BusinessException("参数为空");
		}
		return productRepository.findByCategory(category);
	}

	@Override
	public List<Product> findAllProduct() throws BusinessException {
		return this.productRepository.findAllProduct();
	}

	@Override
	@Transactional
	public void restore(String productId) throws BusinessException {
		
		//查询
		Product product=this.findOne(productId);
		if(ObjectHelper.isEmpty(product)){
			logger.error("产品不存在，不能删除");
			throw new BusinessException("产品不存在，不能删除");
		}
		
		product.setIsDeleted(!BaseEntity.DELETED);
		this.updateEntity(product);
	}

	@Override
	public List<Product> findByCategoryIdAndOrgCd(String categoryId, String orgCd) throws BusinessException {
		if(ObjectHelper.isEmpty(categoryId) || ObjectHelper.isEmpty(orgCd)){
			logger.error("参数为空");
			throw new BusinessException("参数为空");
		}
		return this.productRepository.findByCategoryIdAndOrgCd(categoryId,orgCd);
	}
}
