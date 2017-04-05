package com.zdsoft.finance.specialapprove.entity;

import java.math.BigDecimal;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: OrganizationRisk.java 
 * @ClassName: OrganizationRisk 
 * @Description: 机构风险信息 
 * @author jingjy 
 * @date 2017年2月15日 下午5:57:57 
 * @version V1.0
 */
public class OrganizationRisk{

	/**   
	 * @Fields serialVersionUID :     
	 */ 
	private static final long serialVersionUID = 1L;
	public static final String PRODUCT = "产品";
	public static final String ORGANIZATION = "机构";
	public static final String DEVELOPMENTMANAGER = "营销人员";
     
    /**
     * 类型
     */
    private String riskType;
    
    /**
     * 放款金额
     */
    private BigDecimal loanAmount = BigDecimal.ZERO;
    
    /**
     * 在贷余额
     */
    private BigDecimal loanBalance = BigDecimal.ZERO;

    /**
     * 在贷案件数
     */
    private String loansNumber;
    
    /**
     * 逾期案件数
     */
    private String overdueLoansNumber;
    
    /**
     * 逾期案件数占比
     */
    private String overdueLoansProportion;

	public String getRiskType() {
		return riskType;
	}

	public void setRiskType(String riskType) {
		this.riskType = riskType;
	}

	public BigDecimal getLoanBalance() {
		return loanBalance;
	}

	public void setLoanBalance(BigDecimal loanBalance) {
		this.loanBalance = loanBalance;
	}

	public String getOverdueLoansNumber() {
		return overdueLoansNumber;
	}

	public void setOverdueLoansNumber(String overdueLoansNumber) {
		this.overdueLoansNumber = overdueLoansNumber;
	}

	public String getOverdueLoansProportion() {
		return overdueLoansProportion;
	}

	public void setOverdueLoansProportion(String overdueLoansProportion) {
		this.overdueLoansProportion = overdueLoansProportion;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public BigDecimal getLoanAmount() {
		return loanAmount;
	}

	public void setLoanAmount(BigDecimal loanAmount) {
		this.loanAmount = loanAmount;
	}

	public String getLoansNumber() {
		return loansNumber;
	}

	public void setLoansNumber(String loansNumber) {
		this.loansNumber = loansNumber;
	}


}
