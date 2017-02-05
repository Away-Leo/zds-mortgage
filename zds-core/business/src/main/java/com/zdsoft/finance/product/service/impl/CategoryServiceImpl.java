package com.zdsoft.finance.product.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zdsoft.finance.base.service.impl.BaseServiceImpl;
import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.finance.product.entity.Category;
import com.zdsoft.finance.product.repository.CategoryRepository;
import com.zdsoft.finance.product.service.CategoryService;
import com.zdsoft.framework.core.common.annotation.Log;
import com.zdsoft.framework.core.common.util.ObjectHelper;

/**
 * 产品类别接口实现
 * @author longwei
 * @date 2016/01/10
 * @version 1.0
 */
@Service("categoryService")
public class CategoryServiceImpl extends BaseServiceImpl<Category, CustomRepository<Category,String>> implements CategoryService {

	@Log
	private Logger logger;
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Override
	public List<Category> findTree(boolean isTree) {
		return categoryRepository.findAll(isTree);
	}

	@Override
	public boolean checkName(String name) throws BusinessException {
		if(ObjectHelper.isEmpty(name)){
			logger.error("参数为空");
			throw new BusinessException("参数为空");
		}
		
		return categoryRepository.findByName(name);
	}

	@Override
	@Transactional
	public Category saveOrUpdate(Category category) throws BusinessException {
		if(ObjectHelper.isEmpty(category)){
			logger.error("传入参数为空");
			throw new BusinessException("传入参数为空");
		}
		
		if(ObjectHelper.isNotEmpty(category.getId())){
			Category old=this.findOne(category.getId());
			if(ObjectHelper.isEmpty(old)){
				logger.error("原产品类别不存在");
				throw new BusinessException("原产品类别不存在");
			}
			BeanUtils.copyProperties(category, old, new String[]{"id","createTime","createBy","createOrgCd","parent"});
			category=this.updateEntity(category);
		}else{
			Category parent=this.findOne(category.getParent().getId());
			if(ObjectHelper.isEmpty(parent)){
				logger.error("父级产品类别不存在");
				throw new BusinessException("父级产品类别不存在");
			}
			category.setParent(parent);
			category=this.saveEntity(category);
		}
		return category;
	}

	@Override
	@Transactional
	public void delete(String categoryId) throws BusinessException {
		if(ObjectHelper.isEmpty(categoryId)){
			logger.error("参数为空");
			throw new BusinessException("参数为空");
		}
		
		Category category=this.findOne(categoryId);
		if(ObjectHelper.isEmpty(category)){
			logger.error("查询对象不存在");
			throw new BusinessException("查询对象不存在");
		}
		
		this.logicDelete(category);
	}

}
