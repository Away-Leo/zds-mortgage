package com.zdsoft.finance.prcostitem.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.zdsoft.framework.core.common.domain.BaseEntity;

/**
 * 按键特批(业务监控临时需要)
 * @createTime 2017-01-11
 * @author <a href="mailto:gufeng@zdsoft.cn">gufeng</a>
 * @version 1.0
 */
@Entity
@Table(name = "zf_test_project_special")
public class TestProjectSpecial extends BaseEntity {

	private static final long serialVersionUID = -4212120160501475372L;

	/**
	 * 案件号
	 */
	@Column(length = 32)
	private String projectCd;
	
	/**
	 * 主借人
	 */
	@Column
	private String creditMember;
	/**
	 * 产品分类
	 */
	@Column
	private String parenNm;

	/**
	 * 子产品
	 */
	@Column
	private String productNm;
	
	/**
	 *  贷款期限
	 */
	@Column
	private String deadline;
	
	/**
	 * 申请金额
	 */
	@Column(precision = 30,scale = 12)
	private BigDecimal amount;
	
	/**
	 * 资金来源
	 */
	@Column
	private String source;

	public String getProjectCd() {
		return projectCd;
	}

	public void setProjectCd(String projectCd) {
		this.projectCd = projectCd;
	}


	public String getCreditMember() {
		return creditMember;
	}

	public void setCreditMember(String creditMember) {
		this.creditMember = creditMember;
	}

	public String getParenNm() {
		return parenNm;
	}

	public void setParenNm(String parenNm) {
		this.parenNm = parenNm;
	}

	public String getProductNm() {
		return productNm;
	}

	public void setProductNm(String productNm) {
		this.productNm = productNm;
	}

	public String getDeadline() {
		return deadline;
	}

	public void setDeadline(String deadline) {
		this.deadline = deadline;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}
	
}
