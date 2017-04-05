package com.zdsoft.finance.product.vo;

import com.zdsoft.finance.common.base.BaseVo;
import com.zdsoft.finance.product.entity.Category;
import com.zdsoft.framework.core.common.util.ObjectHelper;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: CategoryVo.java 
 * @ClassName: CategoryVo 
 * @Description: 产品树vo
 * @author gufeng 
 * @date 2017年2月13日 上午10:24:21 
 * @version V1.0
 */
public class CategoryVo extends BaseVo<Category>{

	private static final long serialVersionUID = -7711062728683931300L;

	/**
	 * 产品名称
	 */
	private String name;
	
	/**
	 * 父产品id
	 */
	private String parentId;
	
	/**
	 * 顺序
	 */
	private Integer orderNumber;

	public Integer getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(Integer orderNumber) {
		this.orderNumber = orderNumber;
	}

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
