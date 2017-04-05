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
import com.zdsoft.finance.product.entity.Category;
import com.zdsoft.finance.product.entity.Company;
import com.zdsoft.finance.product.entity.CompanyProduct;
import com.zdsoft.finance.product.entity.Product;
import com.zdsoft.finance.product.entity.ProductRate;
import com.zdsoft.finance.product.repository.ProductRepository;
import com.zdsoft.finance.product.service.ApprovalOpinionService;
import com.zdsoft.finance.product.service.CategoryService;
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
import com.zdsoft.framework.core.common.util.DateHelper;
import com.zdsoft.framework.core.common.util.ObjectHelper;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: ProductServiceImpl.java 
 * @ClassName: ProductServiceImpl 
 * @Description: 产品
 * @author gufeng 
 * @date 2017年2月16日 下午5:48:42 
 * @version V1.0
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
	private CategoryService categoryService;
	
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
		
		Category category = categoryService.findOne(product.getCategory().getId());
		if(ObjectHelper.isEmpty(category)){
			logger.error("产品分类为空");
			throw new BusinessException("产品分类为空");
		}
		product.setCategory(category);
		
		return productRepository.saveEntity(product);
	}

	@Override
	@Transactional
	public Product saveOrUpdate(Product bean, List<Company> companys)
			throws BusinessException {
		Product po = null;
		if(ObjectHelper.isNotEmpty(bean.getId())){
			po = this.findOne(bean.getId());
		}
		if(ObjectHelper.isNotEmpty(po)){//修改
			BeanUtils.copyProperties(bean, po,new String[]{"id","isDeleted","createTime","createBy","createOrgCd","category"});
		}else{//添加
			po = this.saveEntity(bean);
			Category category = categoryService.findOne(bean.getCategoryId());
			if(ObjectHelper.isNotEmpty(category)){
				po.setCategory(category);
			}
		}
		po = this.updateEntity(po);
		
		//机构：先删除原维护机构，在新增机构
		if(ObjectHelper.isNotEmpty(companys)){
			//查询、删除
			List<CompanyProduct> companyProducts=companyProductService.findByProductId(po.getId());
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
				newCompanyProduct.setProduct(po);
				newCompanyProduct.setCompany(newCompany);
				//机构产品
				companyProductService.saveEntity(newCompanyProduct);
			}
		}
		return po;
	}

	@Override
	@Transactional
	public ProductRate saveOrUpdateRate(ProductRate productRate) throws BusinessException {
		if(ObjectHelper.isEmpty(productRate.getProduct()) || ObjectHelper.isEmpty(productRate.getProduct().getId())){
			logger.error("参数异常");
			throw new BusinessException("10000001","参数异常");
		}
		Product product = this.findOne(productRate.getProduct().getId());
		if(ObjectHelper.isEmpty(product)){
			logger.error("产品未空");
			throw new BusinessException("产品未空");
		}
		//期限范围判断
		boolean b = this.intervalJudge(productRate);
		if(!b){
			throw new BusinessException("10000003","期限判断错误");
		}
		if(ObjectHelper.isEmpty(productRate.getId())){
			productRate.setProduct(product);
			productRate = productRateService.saveEntity(productRate);
		}else{
			ProductRate old = productRateService.findOne(productRate.getId());
			if(ObjectHelper.isEmpty(old)){
				logger.error("原产品利率不存在");
				throw new BusinessException("10000002","原产品利率不存在");
			}
			BeanUtils.copyProperties(productRate, old, new String[]{"id","product","isDeleted","createTime","createBy","createOrgCd"});
			productRate = productRateService.updateEntity(old);
		}
		return productRate;
	}
	
	/**
	 * @Title: intervalJudge 
	 * @Description: 区间判断
	 * @author gufeng 
	 * @param productRate 利率
	 * @return 是否可以加入
	 * @throws BusinessException 判断异常
	 */
	private boolean intervalJudge(ProductRate productRate) throws BusinessException{
		String rateId = productRate.getId();
		String productId = productRate.getProduct().getId();
		Long startDate = productRate.getStartDate();
		String startDateUnit = productRate.getStartDateUnit();
		Long endDate = productRate.getEndDate();
		String endDateUnit = productRate.getEndDateUnit();
		if(ObjectHelper.isEmpty(productId)){
			throw new BusinessException("1000000001","产品id为空");
		}
		if(ObjectHelper.isEmpty(startDate)){
			throw new BusinessException("1000000002","期限数据错误");
		}
		if(ObjectHelper.isEmpty(startDateUnit)){
			throw new BusinessException("1000000003","期限数据错误");
		}
		if(ObjectHelper.isEmpty(endDate)){
			throw new BusinessException("1000000004","期限数据错误");
		}
		if(ObjectHelper.isEmpty(endDateUnit)){
			throw new BusinessException("1000000005","期限数据错误");
		}
		//大小判断
		Long start = this.rateDate(startDate, startDateUnit);
		Long end = this.rateDate(endDate, endDateUnit);
		if(start > end){
			throw new BusinessException("100000000006","期限范围错误");
		}
		//利率
		List<ProductRate> list = null;
		if(ObjectHelper.isEmpty(rateId)){
			list = productRateService.findByProductId(productId);
		}else{
			list = productRateService.findByProductIdAndIdNot(productId,rateId);
		}
		if(ObjectHelper.isEmpty(list) || list.size() == 0){
			return true;
		}
		//是否重复
		for (ProductRate rate : list) {
			Long rStart = this.rateDate(rate.getStartDate(), rate.getStartDateUnit());
			Long rEnd = this.rateDate(rate.getEndDate(), rate.getEndDateUnit());
			//左开右闭
			if(rStart < start && start < rEnd){
				throw new BusinessException("10000000007","期限范围存在重复区间");
			}
			if(rStart < end && end < rEnd){
				throw new BusinessException("10000000008","期限范围存在重复区间");
			}
			//反向判断
			if(start < rStart && rStart < end){
				throw new BusinessException("10000000009","期限范围存在重复区间");
			}
			if(start < rEnd && rEnd < end){
				throw new BusinessException("10000000010","期限范围存在重复区间");
			}
			//相同值
			if(start.equals(rStart) && end.equals(rEnd) && !rate.getId().equals(rateId)){
				throw new BusinessException("10000000011","期限范围存在重复区间");
			}
		}
		return true;
	}
	
	/**
	 * @Title: rateDate 
	 * @Description: 期限转化为日
	 * @author gufeng 
	 * @param date 期限
	 * @param util 期限单位
	 * @return 日期限
	 */
	private Long rateDate(Long date,String util){
		if(ObjectHelper.isEmpty(date) || ObjectHelper.isEmpty(util)){
			return 0l;
		}
		switch (util) {
		case "0931001"://年
			date = date * 12 * 30;
			break;
		case "0931002"://月
			date = date * 30;
			break;
		default:
			break;
		}
		return date;
	}

	@Override
	public boolean checkName(String name,String categoryId) throws BusinessException {
		if(ObjectHelper.isEmpty(name)){
			logger.error("传入参数为空");
			throw new BusinessException("传入参数为空");
		}
		List<Product> products = productRepository.findByName(name,categoryId);
		if(ObjectHelper.isEmpty(products) || products.size() == 0){
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
		Long nowDate = DateHelper.dateToLong(new Date(), DateHelper.DATE_SHORT_SIMPLE_FORMAT);
		return this.productRepository.findByCategoryIdAndOrgCd(categoryId,orgCd,nowDate);
	}
	
	@Override
	public ProductRate findByProductIdAndDeadline(String productId, Long date, String dateUtil)
			throws BusinessException {
		if(ObjectHelper.isEmpty(productId) || ObjectHelper.isEmpty(date) || ObjectHelper.isEmpty(dateUtil)){
			throw new BusinessException("1000000001","传入参数为空，productId：" + productId + ",date:" + date + ",dateUtil:" + dateUtil);
		}
		Product p = productRepository.findOne(productId);
		if(ObjectHelper.isEmpty(p)){
			throw new BusinessException("100000002","未找到产品信息");
		}
		List<ProductRate> list = productRateService.findByProductId(productId);
		if(ObjectHelper.isEmpty(list) || list.size() == 0){
			return null;
		}
		Long rDate = this.rateDate(date, dateUtil);
		ProductRate rate = null;
		for (ProductRate productRate : list) {
			Long sRate = this.rateDate(productRate.getStartDate(), productRate.getStartDateUnit());
			Long eRate = this.rateDate(productRate.getEndDate(), productRate.getEndDateUnit());
			if(sRate <= rDate && rDate <= eRate){
				rate = productRate;
				break;
			}
		}
		return rate;
	}
}
