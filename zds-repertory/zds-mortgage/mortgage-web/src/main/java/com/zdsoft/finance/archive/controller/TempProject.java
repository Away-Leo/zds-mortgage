package com.zdsoft.finance.archive.controller;

import java.math.BigDecimal;


/**
 * 案件 TODO 临时使用
 * @author <a href="mailto:gufeng@zdsoft.cn">gufeng</a>
 * @date 2016-12-21
 */
public class TempProject{

	private String id = "id1";
	/**
	 * 案件号
	 */
	private String projectCd = "案件号1";
	/**
	 * 客户名称
	 */
	private String customerNm = "客户名称1";
	/**
	 * 产品名称
	 */
	private String productNm = "产品名称1";
	/**
	 * 子产品
	 */
	private String productChild = "子产品1";
	/**
	 * 贷款金额
	 */
	private BigDecimal loanAmount = new BigDecimal("10000");
	/**
	 * 银行
	 */
	private String bank = "银行1";
	/**
	 * 申请人
	 */
	private String applyBy = "申请人1";
	/**
	 * 申请时间 
	 */
	private Long applyTime = 20161222l;
	/**
	 * 案件状态
	 */
	private Integer projectStatus = 1;
	/**
	 * 产品类型
	 */
	private String productType = "产品类型1";
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getProjectCd() {
		return projectCd;
	}
	public void setProjectCd(String projectCd) {
		this.projectCd = projectCd;
	}
	public String getCustomerNm() {
		return customerNm;
	}
	public void setCustomerNm(String customerNm) {
		this.customerNm = customerNm;
	}
	public String getProductNm() {
		return productNm;
	}
	public void setProductNm(String productNm) {
		this.productNm = productNm;
	}
	public String getProductChild() {
		return productChild;
	}
	public void setProductChild(String productChild) {
		this.productChild = productChild;
	}
	public BigDecimal getLoanAmount() {
		return loanAmount;
	}
	public void setLoanAmount(BigDecimal loanAmount) {
		this.loanAmount = loanAmount;
	}
	public String getBank() {
		return bank;
	}
	public void setBank(String bank) {
		this.bank = bank;
	}
	public String getApplyBy() {
		return applyBy;
	}
	public void setApplyBy(String applyBy) {
		this.applyBy = applyBy;
	}
	public Long getApplyTime() {
		return applyTime;
	}
	public void setApplyTime(Long applyTime) {
		this.applyTime = applyTime;
	}
	public Integer getProjectStatus() {
		return projectStatus;
	}
	public void setProjectStatus(Integer projectStatus) {
		this.projectStatus = projectStatus;
	}
	public String getProductType() {
		return productType;
	}
	public void setProductType(String productType) {
		this.productType = productType;
	}
}
