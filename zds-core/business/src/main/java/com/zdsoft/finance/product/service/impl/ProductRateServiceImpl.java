package com.zdsoft.finance.product.service.impl;

import java.util.Date;
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
import com.zdsoft.finance.common.base.QueryObj;
import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.finance.product.entity.Product;
import com.zdsoft.finance.product.entity.ProductRate;
import com.zdsoft.finance.product.repository.ProductRateRepository;
import com.zdsoft.finance.product.service.ProductRateService;
import com.zdsoft.framework.core.common.annotation.Log;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.PageRequest;
import com.zdsoft.framework.core.common.util.ObjectHelper;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title: ProductRateServiceImpl.java 
 * @ClassName: ProductRateServiceImpl 
 * @Description: 产品利率
 * @author gufeng 
 * @date 2017年3月6日 下午8:27:57 
 * @version V1.0
 */
@Service("productRateService")
public class ProductRateServiceImpl extends BaseServiceImpl<ProductRate, CustomRepository<ProductRate,String>> implements ProductRateService{

	@Autowired
	private ProductRateRepository productRateRepository;
	@Log
	private Logger logger;
	
	@Override
	public List<ProductRate> findByProductId(String productId) throws BusinessException {
		if(ObjectHelper.isEmpty(productId)){
			logger.error("传入productId为空");
			throw new BusinessException("传入productId为空");
		}
		return productRateRepository.findByProductId(productId);
	}
	
	@Override
	public List<ProductRate> findByProductIdAndIdNot(String productId, String rateId) throws BusinessException {
		if(ObjectHelper.isEmpty(productId) || ObjectHelper.isEmpty(rateId)){
			throw new BusinessException("传入productId为空或rateId为空");
		}
		return productRateRepository.findByProductIdAndIdNotAndIsDeleted(productId,rateId,false);
	}
	
    @Override
    public  Page<Map<String, Object>> findBySqlProductRate(PageRequest pageable,List<QueryObj> queryObjs,String orgCd,String companyCd) {
    	 return productRateRepository.getProductList(pageable, queryObjs,orgCd,companyCd);
    }

	@Override
	@Transactional
	public void copy(Product oldProduct,Product newProduct, EmpDto empDto) throws BusinessException {
		if(ObjectHelper.isEmpty(oldProduct) || ObjectHelper.isEmpty(empDto) || ObjectHelper.isEmpty(newProduct)){
			logger.error("参数为空");
			throw new BusinessException("参数为空");
		}
		
		List<ProductRate> list=this.findByProductId(oldProduct.getId());
		if(ObjectHelper.isNotEmpty(list)){
			for(ProductRate productRate:list){
				ProductRate newProductRate=new ProductRate();
				BeanUtils.copyProperties(productRate, newProductRate, new String[]{"id","version","isDeleted","updateTime","updateBy","updateOrgCd","product"});
				newProductRate.setCreateBy(empDto.getEmpCd());
				newProductRate.setCreateOrgCd(empDto.getOrgCd());
				newProductRate.setCreateTime(new Date());
				newProductRate.setProduct(newProduct);
				this.saveEntity(newProductRate);
			}
		}
	}

}
