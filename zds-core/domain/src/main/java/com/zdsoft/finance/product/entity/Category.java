package com.zdsoft.finance.product.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.zdsoft.framework.core.common.domain.BaseEntity;

/**
 * 产品类别
 * @author longwei
 * @date 2016/01/10
 * @version 1.0
 */
@Entity
@Table(name="prct_category")
public class Category extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8460633313274169287L;

	/**
	 * 类别名称
	 */
	@Column(length=255)
	private String name;
	
	/**
	 * 顺序号
	 */
	@Column
	private Long orderNumber;
	
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

	public Long getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(Long orderNumber) {
		this.orderNumber = orderNumber;
	}

	public Category getParent() {
		return parent;
	}

	public void setParent(Category parent) {
		this.parent = parent;
	}
	
}
