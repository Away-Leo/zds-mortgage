package com.zdsoft.finance.product.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.zdsoft.framework.core.common.domain.BaseEntity;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title: Category.java 
 * @ClassName: Category 
 * @Description: 产品类别
 * @author gufeng 
 * @date 2017年2月13日 上午10:20:30 
 * @version V1.0
 */
@Entity
@Table(name="prd_category")
public class Category extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8460633313274169287L;

	/**
	 * 类别名称
	 */
	@Column(length=64)
	private String name;
	
	/**
	 * 顺序号
	 */
	@Column
	private Integer orderNumber;
	
	/**
	 * 父级
	 */
	@ManyToOne
	@JoinColumn(name="parent_id")
	private Category parent;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(Integer orderNumber) {
		this.orderNumber = orderNumber;
	}

	public Category getParent() {
		return parent;
	}

	public void setParent(Category parent) {
		this.parent = parent;
	}
	
}
