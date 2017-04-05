package com.zdsoft.finance.product.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import com.zdsoft.framework.core.common.domain.BaseEntity;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: Product.java 
 * @ClassName: Product 
 * @Description: 产品
 * @author gufeng 
 * @date 2017年2月16日 下午5:45:58 
 * @version V1.0
 */
@Entity
@Table(name="prd_product")
public class Product extends BaseEntity{

	private static final long serialVersionUID = -6217450078740504410L;

	/**
	 * 产品名称
	 */
	@Column(length=64)
	private String productName;
	
	/**
	 * 是否有效
	 */
	@Column
	@Type(type="true_false")
	Boolean isValid = Boolean.valueOf(false);
	
	/**
	 * 开始日期
	 */
	@Column
	private Long startTime;
	
	/**
	 * 结束日期
	 */
	@Column
	private Long endTime;
	
	/**
	 * 产品编号
	 */
	@Column(length=32)
	private String productCode;
	
	/**
	 * 产品编号-自定义
	 */
	@Column(length=64)
	private String customCode;
	
	/**
	 * 面签资料
	 */
	@Column(length=1500)
	private String faceData;
	
	/**
	 * 产品类别
	 */
	@OneToOne
	@JoinColumn(name="category_id")
	private Category category;
	
	/**
	 * 资方id
	 */
	@Column(length=32,name="capitalist_id")
	private String capitalistId;
	
	/**
	 * 资方名字
	 */
	@Column(length = 64)
	private String capitalistName;
	
	/**
	 * 产品类别id
	 */
	private transient String categoryId;
	
	public String getCapitalistName() {
		return capitalistName;
	}

	public void setCapitalistName(String capitalistName) {
		this.capitalistName = capitalistName;
	}

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Boolean getIsValid() {
		return isValid;
	}

	public void setIsValid(Boolean isValid) {
		this.isValid = isValid;
	}

	public Long getStartTime() {
		return startTime;
	}

	public void setStartTime(Long startTime) {
		this.startTime = startTime;
	}

	public Long getEndTime() {
		return endTime;
	}

	public void setEndTime(Long endTime) {
		this.endTime = endTime;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getCustomCode() {
		return customCode;
	}

	public void setCustomCode(String customCode) {
		this.customCode = customCode;
	}

	public String getFaceData() {
		return faceData;
	}

	public void setFaceData(String faceData) {
		this.faceData = faceData;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public String getCapitalistId() {
		return capitalistId;
	}

	public void setCapitalistId(String capitalistId) {
		this.capitalistId = capitalistId;
	}

}
