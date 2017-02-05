package com.zdsoft.finance.product.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

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
import com.zdsoft.finance.product.entity.ProductContract;
import com.zdsoft.finance.product.repository.ProductContractRepository;
import com.zdsoft.finance.product.service.ProductContractService;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.PageRequest;
import com.zdsoft.framework.core.common.util.ObjectHelper;

/**
 * 产品合同关联关系 
 * @createTime 2017年1月10日 下午3:53:55
 * @author <a href="mailto:gufeng@zdsoft.cn">gufeng</a>
 * @version 1.0
 */
@Service
public class ProductContractServiceImpl extends BaseServiceImpl<ProductContract, CustomRepository<ProductContract,String>> 
	implements ProductContractService{

	@Autowired
	private ProductContractRepository productContractRepository;

	@Override
	@Transactional(rollbackFor = BusinessException.class)
	public void deleteByProductId(String productId) throws BusinessException {
		if(ObjectHelper.isEmpty(productId)){
			throw new BusinessException("100000001","产品id未空");
		}
		productContractRepository.logicByProduct(productId);
	}

	@SuppressWarnings("static-access")
	@Override
	public Page<Map<String, Object>> getProductContractPage(PageRequest pageable, List<QueryObj> queryObjs) {
		return (Page<Map<String, Object>>) productContractRepository.getListObjectBySql(pageable, queryObjs, productContractRepository.sql, null);
	}

	@Override
	public List<Map<String,Object>> selectContract(String productId) throws BusinessException {
		//查询未选择的合同
		return productContractRepository.selectContract(productId,false);
	}

	@Override
	@Transactional(rollbackFor = BusinessException.class)
	public void saveOrUpdate(ProductContract po) throws BusinessException {
		if(ObjectHelper.isEmpty(po.getProductId())){
			throw new BusinessException("10000001","产品id为空");
		}
		if(ObjectHelper.isEmpty(po.getContractIds()) || po.getContractIds().length == 0){
			throw new BusinessException("10000002","合同id为空");
		}
		for (String contractId : po.getContractIds()) {
			ProductContract bean = new ProductContract();
			BeanUtils.copyProperties(po, bean);
			bean.setContractId(contractId);
			productContractRepository.saveEntity(bean);
		}
	}
	
	@Override
	public List<ProductContract> findByProductId(String productId) throws BusinessException {
		if(ObjectHelper.isEmpty(productId)){
			logger.error("参数为空");
			throw new BusinessException("参数为空");
		}
		return productContractRepository.findByProductId(productId);
	}

	@Override
	@Transactional
	public void copy(Product oldProduct,Product newProduct, EmpDto empDto) throws BusinessException {
		if(ObjectHelper.isEmpty(oldProduct) || ObjectHelper.isEmpty(empDto) || ObjectHelper.isEmpty(newProduct)){
			logger.error("参数不合法");
			throw new BusinessException("参数不合法");
		}
		
		List<ProductContract> list = this.findByProductId(oldProduct.getId());
		if(ObjectHelper.isNotEmpty(list)){
			for(ProductContract productContract:list){
				ProductContract newProductContract=new ProductContract();
				BeanUtils.copyProperties(productContract, newProductContract,new String[]{"id","version","isDeleted","updateTime","updateBy","updateOrgCd","productId"});
				newProductContract.setCreateBy(empDto.getEmpCd());
				newProductContract.setCreateOrgCd(empDto.getOrgCd());
				newProductContract.setCreateTime(new Date());
				newProductContract.setProductId(newProduct.getId());
				this.saveEntity(newProductContract);
			}
		}
		
	}
}
