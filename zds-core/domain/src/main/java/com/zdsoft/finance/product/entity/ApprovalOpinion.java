package com.zdsoft.finance.product.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import com.zdsoft.framework.core.common.domain.BaseEntity;

/**
 * 产品审批意见配置
 * @author longwei
 * @date 2016/12/28
 * @version 1.0
 */
@Entity
@Table(name="prct_approval_opinion")
public class ApprovalOpinion extends BaseEntity{

	/**
	 * 审批类型
	 */
	@Column(length=32)
	private String approvalTypeCd;
	
	/**
	 * 审批类型
	 */
	@Column(length=255)
	private String approvalTypeNm;
	
	/**
	 * 抵押顺序
	 */
	@Column(length=32)
	private String mortgageOrderCd;
	
	/**
	 * 抵押顺序
	 */
	@Column(length=32)
	private String mortgageOrderNm;
	
	/**
	 * 是否启用
	 */
	@Column
	@Type(type="true_false")
	private Boolean isEnable;
	
	/**
	 * 审批用语
	 */
	@Column(length=500)
	private String remark;
	
	/**
	 * 所属产品
	 */
	@ManyToOne
	@JoinColumn(name="product_id")
	private Product product;

	public String getApprovalTypeCd() {
		return approvalTypeCd;
	}

	public void setApprovalTypeCd(String approvalTypeCd) {
		this.approvalTypeCd = approvalTypeCd;
	}

	public String getApprovalTypeNm() {
		return approvalTypeNm;
	}

	public void setApprovalTypeNm(String approvalTypeNm) {
		this.approvalTypeNm = approvalTypeNm;
	}

	public String getMortgageOrderCd() {
		return mortgageOrderCd;
	}

	public void setMortgageOrderCd(String mortgageOrderCd) {
		this.mortgageOrderCd = mortgageOrderCd;
	}

	public String getMortgageOrderNm() {
		return mortgageOrderNm;
	}

	public void setMortgageOrderNm(String mortgageOrderNm) {
		this.mortgageOrderNm = mortgageOrderNm;
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
