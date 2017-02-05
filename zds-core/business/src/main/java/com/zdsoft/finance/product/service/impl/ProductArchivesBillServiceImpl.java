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
import com.zdsoft.finance.product.entity.Product;
import com.zdsoft.finance.product.entity.ProductArchivesBill;
import com.zdsoft.finance.product.repository.ProductArchivesBillRepository;
import com.zdsoft.finance.product.service.ProductArchivesBillService;
import com.zdsoft.framework.core.common.util.ObjectHelper;

/**
 * 档案清单
 * @createTime 2017年1月10日 上午11:47:44
 * @author <a href="mailto:gufeng@zdsoft.cn">gufeng</a>
 * @version 1.0
 */
@Service
public class ProductArchivesBillServiceImpl extends BaseServiceImpl<ProductArchivesBill, CustomRepository<ProductArchivesBill,String>> 
	implements ProductArchivesBillService{
	
	@Autowired
	private ProductArchivesBillRepository productArchivesBillRepository;

	@Override
	@Transactional(rollbackFor = BusinessException.class)
	public ProductArchivesBill saveOrUpdate(ProductArchivesBill po) throws BusinessException{
		ProductArchivesBill bean = null;
		if(ObjectHelper.isNotEmpty(po.getId())){
			bean = productArchivesBillRepository.findOne(po.getId());
			if(ObjectHelper.isEmpty(bean)){
				throw new BusinessException("100000001", "未找到编辑的档案清单");
			}
			BeanUtils.copyProperties(po, bean,new String[]{"createBy","createTime","createOrgCd","id","version","isDeleted"});
			bean = productArchivesBillRepository.updateEntity(bean);
		}else{
			bean = productArchivesBillRepository.saveEntity(po);
		}
		return bean;
	}

	@Override
	@Transactional(rollbackFor = BusinessException.class)
	public void sets(String ids, String archivesLevel, String archivesType) throws BusinessException {
		if(ObjectHelper.isEmpty(ids) || ObjectHelper.isEmpty(archivesLevel) || ObjectHelper.isEmpty(archivesType)){
			throw new BusinessException("10000001","传入数据出错");
		}
		String[] billIds = ids.split(",");
		for (String id : billIds) {
			ProductArchivesBill bean = productArchivesBillRepository.findOne(id);
			if(ObjectHelper.isEmpty(bean)){
				throw new BusinessException("100000002","未找到数据，id:" + id);
			}
			bean.setArchivesLevel(archivesLevel);
			bean.setArchivesType(archivesType);
			productArchivesBillRepository.updateEntity(bean);
		}
	}

	@Override
	@Transactional(rollbackFor = BusinessException.class)
	public void deleteByProductId(String productId) throws BusinessException {
		if(ObjectHelper.isEmpty(productId)){
			throw new BusinessException("100000001","产品id未空");
		}
		productArchivesBillRepository.logicByProduct(productId);
	}
	
	@Override
	public List<ProductArchivesBill> findByProductId(String productId) throws BusinessException {
		if(ObjectHelper.isEmpty(productId)){
			logger.error("参数为空");
			throw new BusinessException("参数为空");
		}
		return productArchivesBillRepository.findByProductId(productId);
	}

	@Override
	@Transactional
	public void copy(Product oldProduct,Product newProduct, EmpDto empDto) throws BusinessException {
		if(ObjectHelper.isEmpty(oldProduct) || ObjectHelper.isEmpty(oldProduct.getId()) || ObjectHelper.isEmpty(newProduct)){
			logger.error("参数不合法");
			throw new BusinessException("参数不合法");
		}
		
		List<ProductArchivesBill> list = this.findByProductId(oldProduct.getId());
		if(ObjectHelper.isNotEmpty(list)){
			for(ProductArchivesBill productArchivesBill:list){
				ProductArchivesBill newProductArchivesBill=new ProductArchivesBill();
				BeanUtils.copyProperties(productArchivesBill, newProductArchivesBill,new String[]{"id","version","isDeleted","updateTime","updateBy","updateOrgCd","productId"});
				newProductArchivesBill.setCreateBy(empDto.getEmpCd());
				newProductArchivesBill.setCreateOrgCd(empDto.getOrgCd());
				newProductArchivesBill.setCreateTime(new Date());
				newProductArchivesBill.setProductId(newProduct.getId());
				this.saveEntity(newProductArchivesBill);
			}
		}
	}
}