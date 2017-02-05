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
 * 产品利率操作实现
 * @author longwei
 * @date 2016/12/26
 * @version 1.0
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
	
	/**
	 * @author jingjiyan
	 */
    @Override
    public  Page<Map<String, Object>> findBySqlProductRate(PageRequest pageable,List<QueryObj> queryObjs) {
        return productRateRepository.getListObjectBySql(pageable, queryObjs, ProductRateRepository.sql, ProductRateRepository.extendSql);
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
