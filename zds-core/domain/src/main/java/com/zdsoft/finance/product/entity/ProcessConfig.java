package com.zdsoft.finance.product.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import com.zdsoft.framework.core.common.domain.BaseEntity;

/**
 * 产品流程配置
 * @author longwei
 * @date 2016/12/28
 * @version 1.0
 */
@Entity
@Table(name="prct_process_config")
public class ProcessConfig extends BaseEntity{

	/**
	 * 流程名称
	 */
	@Column(length=255)
	private String processName;
	
	/**
	 * 代码
	 */
	@Column(length=32)
	private String processFormCd;
	
	/**
	 * 代码
	 */
	@Column(length=255)
	private String processFormNm;
	
	/**
	 * 流程key
	 */
	@Column(length=255)
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

	public String getProcessFormCd() {
		return processFormCd;
	}

	public void setProcessFormCd(String processFormCd) {
		this.processFormCd = processFormCd;
	}

	public String getProcessFormNm() {
		return processFormNm;
	}

	public void setProcessFormNm(String processFormNm) {
		this.processFormNm = processFormNm;
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
	
}
