package com.zdsoft.finance.product.vo;

import com.zdsoft.finance.common.base.BaseVo;
import com.zdsoft.finance.product.entity.Category;
import com.zdsoft.framework.core.common.util.ObjectHelper;

/**
 * 产品树vo
 * @author longwei
 * @date 2016/12/23
 * @version 1.0
 */
public class CategoryVo extends BaseVo<Category>{

	// 产品名称
	private String name;
	
	// 父产品id
	private String parentId;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	
	public CategoryVo(){}
	
	public CategoryVo(Category category){
		super(category, new String[]{"parentId"});
		if(ObjectHelper.isNotEmpty(category.getParent())){
			this.setParentId(category.getParent().getId());
		}
	}
	
	public Category toPo(){
		Category category=new Category();
		super.toPo(this, category, new String[]{"parentId"});
		Category parent=new Category();
		if(ObjectHelper.isNotEmpty(this.getParentId())){
			parent.setId(parentId);
		}
		category.setParent(parent);
		return category;
	}
}
