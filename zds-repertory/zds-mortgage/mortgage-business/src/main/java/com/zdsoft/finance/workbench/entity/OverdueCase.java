package com.zdsoft.finance.workbench.entity;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title: OverdueCase.java 
 * @ClassName: OverdueCase 
 * @Description: 逾期案件查询列表；只用于接收数据
 * @author longwei 
 * @date 2017年2月23日 上午10:07:33 
 * @version V1.0
 */
public class OverdueCase {
	
	/**
	 * 逾期天数 60天以上（含60天）
	 */
	 public static final String DAYS_60 = "YWDM0015804";
	 
	/**
	 * 逾期天数  30~59天
	 */	 
	 public static final String DAYS_59 = "YWDM0015803";
	 
	 /**
	  * 逾期天数 20~29天
	  */	
	 public static final String DAYS_30 = "YWDM0015802";
	 
	 /**
	  * 逾期天数 1~19天
	  */	
	 public static final String DAYS_19 = "YWDM0015801";
	 
	 /**
	  * 逾期天数（无限制）
	  */
	 public static final String DAYS_0 = "0";

	/**
	 * 主键
	 */
	private String id;
	
	/**
	 * 机构
	 */
	private String orgCode;
	
	/**
	 * 案件号
	 */
	private String applayCaseCode;
	
	/**
	 * 主借人
	 */
	private String customerName;
	
	/**
	 * 产品分类
	 */
	private String categoryName;
	
	/**
	 * 子产品
	 */
	private String productName;
	
	/**
	 * 申请天数
	 */
	private String applayDays;
	
	/**
	 * 已放款
	 */
	private String loanedAmount;
	
	/**
	 * 未回款
	 */
	private String paymentAmount;
	
	/**
	 * 未回款(至)
	 */
	private String paymentAmountEnd;
	
	/**
	 * 逾期天数
	 */
	private String overdueDays;
	
	/**
	 * 放款日期
	 */
	private String startloanDate;
	
	/**
	 * 放款日期(至)
	 */
	private String endLoanDate;
	
	/**
	 * 贷后状态
	 */
	private String afterLoanStatus;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public String getApplayCaseCode() {
		return applayCaseCode;
	}

	public void setApplayCaseCode(String applayCaseCode) {
		this.applayCaseCode = applayCaseCode;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getApplayDays() {
		return applayDays;
	}

	public void setApplayDays(String applayDays) {
		this.applayDays = applayDays;
	}

	public String getLoanedAmount() {
		return loanedAmount;
	}

	public void setLoanedAmount(String loanedAmount) {
		this.loanedAmount = loanedAmount;
	}

	public String getPaymentAmount() {
		return paymentAmount;
	}

	public void setPaymentAmount(String paymentAmount) {
		this.paymentAmount = paymentAmount;
	}

	public String getPaymentAmountEnd() {
		return paymentAmountEnd;
	}

	public void setPaymentAmountEnd(String paymentAmountEnd) {
		this.paymentAmountEnd = paymentAmountEnd;
	}


	public String getStartloanDate() {
		return startloanDate;
	}

	public void setStartloanDate(String startloanDate) {
		this.startloanDate = startloanDate;
	}

	public String getEndLoanDate() {
		return endLoanDate;
	}

	public void setEndLoanDate(String endLoanDate) {
		this.endLoanDate = endLoanDate;
	}

	public String getOverdueDays() {
		return overdueDays;
	}

	public void setOverdueDays(String overdueDays) {
		this.overdueDays = overdueDays;
	}


	public String getAfterLoanStatus() {
		return afterLoanStatus;
	}

	public void setAfterLoanStatus(String afterLoanStatus) {
		this.afterLoanStatus = afterLoanStatus;
	}

	
	public OverdueCase() {}

	public OverdueCase(String id, String applayCaseCode, String customerName, String categoryName, String productName,
			String applayDays, String loanedAmount, String paymentAmount, String paymentAmountEnd, String overdueDays, String endloanDate,
			String startloanDate,String afterLoanStatus) {
		super();
		this.id = id;
		this.applayCaseCode = applayCaseCode;
		this.customerName = customerName;
		this.categoryName = categoryName;
		this.productName = productName;
		this.applayDays = applayDays;
		this.loanedAmount = loanedAmount;
		this.paymentAmount = paymentAmount;
		this.paymentAmountEnd = paymentAmountEnd;
		this.overdueDays = overdueDays;
		this.startloanDate = startloanDate;
		this.endLoanDate = endloanDate;
		this.afterLoanStatus = afterLoanStatus;
	}
}
