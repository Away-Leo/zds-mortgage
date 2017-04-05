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
 * @Title: ApprovalOpinion.java 
 * @ClassName: ApprovalOpinion 
 * @Description: 产品审批意见配置
 * @author gufeng 
 * @date 2017年3月6日 下午7:20:59 
 * @version V1.0
 */
@Entity
@Table(name="prd_approval_opinion")
public class ApprovalOpinion extends BaseEntity{

	private static final long serialVersionUID = 10002835878298545L;

	/**
	 * 审批类型
	 */
	@Column(length=20)
	private String approvalType;
	
	/**
	 * 审批类型
	 */
	@Column(length=64)
	private String approvalTypeName;
	
	/**
	 * 抵押顺序
	 */
	@Column(length=20)
	private String mortgageOrder;
	
	/**
	 * 抵押顺序
	 */
	@Column(length=64)
	private String mortgageOrderName;
	
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

	public String getApprovalType() {
		return approvalType;
	}

	public void setApprovalType(String approvalType) {
		this.approvalType = approvalType;
	}

	public String getApprovalTypeName() {
		return approvalTypeName;
	}

	public void setApprovalTypeName(String approvalTypeName) {
		this.approvalTypeName = approvalTypeName;
	}

	public String getMortgageOrder() {
		return mortgageOrder;
	}

	public void setMortgageOrder(String mortgageOrder) {
		this.mortgageOrder = mortgageOrder;
	}

	public String getMortgageOrderName() {
		return mortgageOrderName;
	}

	public void setMortgageOrderName(String mortgageOrderName) {
		this.mortgageOrderName = mortgageOrderName;
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
