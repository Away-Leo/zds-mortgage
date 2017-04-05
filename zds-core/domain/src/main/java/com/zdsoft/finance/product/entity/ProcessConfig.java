package com.zdsoft.finance.product.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import com.zdsoft.framework.core.common.domain.BaseEntity;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title: ProcessConfig.java 
 * @ClassName: ProcessConfig 
 * @Description: 产品流程配置
 * @author gufeng 
 * @date 2017年3月6日 下午5:40:23 
 * @version V1.0
 */
@Entity
@Table(name="prd_process_config")
public class ProcessConfig extends BaseEntity{

	private static final long serialVersionUID = -4223611313203662805L;

	/**
	 * 流程名称
	 */
	@Column(length=64)
	private String processName;
	
	/**
	 * 代码
	 */
	@Column(length=20)
	private String processCode;
	
	/**
	 * 流程key
	 */
	@Column(length=64)
	private String processKey;
	
	/**
	 * 状态
	 */
	@Column
	@Type(type="true_false")
	private Boolean isEnable;
	
	/**
	 * 备注
	 */
	@Column(length=500)
	private String remark;
	
	/**
	 * 所属产品
	 */
	@ManyToOne
	@JoinColumn(name="product_id")
	private Product product;

	public String getProcessName() {
		return processName;
	}

	public void setProcessName(String processName) {
		this.processName = processName;
	}

	public String getProcessKey() {
		return processKey;
	}

	public void setProcessKey(String processKey) {
		this.processKey = processKey;
	}

	public Boolean getIsEnable() {
		return isEnable;
	}

	public void setIsEnable(Boolean isEnable) {
		this.isEnable = isEnable;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public String getProcessCode() {
		return processCode;
	}

	public void setProcessCode(String processCode) {
		this.processCode = processCode;
	}
	
}
