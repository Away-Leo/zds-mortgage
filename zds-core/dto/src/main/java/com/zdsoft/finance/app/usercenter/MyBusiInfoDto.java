package com.zdsoft.finance.app.usercenter;

import java.math.BigDecimal;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title:MyBusiInfoDto.java
 * @Package:com.zdsoft.finance.app.usercenter
 * @Description:我的
 * @author: jingyh
 * @date:2017年1月13日 下午6:31:56
 * @version:v1.0
 */
public class MyBusiInfoDto {

	/**
	 * 案件申请Id
	 */
	private String caseApplyId;
	
	/**
	 * 案件编号
	 */
	private String caseCode;
	
	/**
	 * 贷款金额
	 */
	private BigDecimal applyAmount;
	
	/**
	 * 产品名称
	 */
	private String productName;
	
	/**
	 * 综合评估价
	 */
	private BigDecimal estimate;
	
	/**
	 * 客户姓名
	 */
	private String clientName;
	
	/**
	 * 联系方式
	 */
	private String contact;
	
	/**
	 * 审批状态
	 */
	private String caseApplyStatus;
	
	/**
	 * 评估价抵押成数
	 */
	private String assessedPriceMortgage;
	/**
	 * 案件阶段
	 */
	private Integer stage;

	public String getCaseApplyId() {
		return caseApplyId;
	}

	public void setCaseApplyId(String caseApplyId) {
		this.caseApplyId = caseApplyId;
	}

	public String getCaseCode() {
		return caseCode;
	}

	public void setCaseCode(String caseCode) {
		this.caseCode = caseCode;
	}

	public BigDecimal getApplyAmount() {
		return applyAmount;
	}

	public void setApplyAmount(BigDecimal applyAmount) {
		this.applyAmount = applyAmount;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public BigDecimal getEstimate() {
		return estimate;
	}

	public void setEstimate(BigDecimal estimate) {
		this.estimate = estimate;
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getCaseApplyStatus() {
		return caseApplyStatus;
	}

	public void setCaseApplyStatus(String caseApplyStatus) {
		this.caseApplyStatus = caseApplyStatus;
	}

	public String getAssessedPriceMortgage() {
		return assessedPriceMortgage;
	}

	public void setAssessedPriceMortgage(String assessedPriceMortgage) {
		this.assessedPriceMortgage = assessedPriceMortgage;
	}

	public Integer getStage() {
		return stage;
	}

	public void setStage(Integer stage) {
		this.stage = stage;
	}

	public MyBusiInfoDto() {
		super();
	}
}
