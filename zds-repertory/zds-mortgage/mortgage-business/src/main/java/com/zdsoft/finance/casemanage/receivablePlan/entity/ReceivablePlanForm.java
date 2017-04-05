package com.zdsoft.finance.casemanage.receivablePlan.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import com.zdsoft.framework.core.common.util.ObjectHelper;

/**
 * 版权所有：重庆正大华日软件有限公司
 * 
 * @Title: ReceivablePlanForm.java
 * @ClassName: ReceivablePlanForm
 * @Description: 还款计划表单参数
 * @author jincheng
 * @date 2017年2月13日 下午4:32:43
 * @version V1.0
 */
public class ReceivablePlanForm implements Serializable {

	private static final long serialVersionUID = -3052334612139578988L;

	private String caseApplyId;//案件id

	private String loanId;// 放款id

	private BigDecimal principalAmount = BigDecimal.ZERO;// 本金

	private String businessType;// 业务类型--兴业贷1、过桥贷2(只有一次付息按期还本还款方式)

	private Integer term;// 期限(月)
	
	private String termUnit;// 期限单位(月)
	
	private BigDecimal rate = BigDecimal.ZERO;// 贷款利率(年)
	
	private String rateUnit;// 贷款利率单位(年)
	
	private Integer spTerm;//先息后本还款期限(月)
	
	private BigDecimal spRate= BigDecimal.ZERO;//先息后本月利率
	
	private Integer loanDays;//垫资天数
	
	private String repayMethod;// 还款方式

	private String eachRepayMethod;// 每期还款方式      首月按天计息按还款日还 	对月对日还款

	private String splictPeriod;// 分段还款还款期数

	//季度还本比例
	
	//第一年 2% 第二年 % 第三年 % 第四年 % 第五年 %    利率性	

	private String rateNature;// 利率性质

	private Long loanDate;// 放款日期

	private Integer repaymentDate;// 每月还款号数

	private Integer repaymentDay = 360;// 还款计算天数
	
	private Integer decimalPlace=2;//小数位

	public String getCaseApplyId() {
		return caseApplyId;
	}

	public void setCaseApplyId(String caseApplyId) {
		this.caseApplyId = caseApplyId;
	}

	public String getLoanId() {
		return loanId;
	}

	public void setLoanId(String loanId) {
		this.loanId = loanId;
	}

	public BigDecimal getPrincipalAmount() {
		return principalAmount;
	}

	public void setPrincipalAmount(BigDecimal principalAmount) {
		this.principalAmount = principalAmount;
	}

	public String getBusinessType() {
		return businessType;
	}

	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}

	public Integer getTerm() {
		return term;
	}

	public void setTerm(Integer term) {
		this.term = term;
	}

	public String getRepayMethod() {
		return repayMethod;
	}

	public void setRepayMethod(String repayMethod) {
		this.repayMethod = repayMethod;
	}

	public String getEachRepayMethod() {
		return eachRepayMethod;
	}

	public void setEachRepayMethod(String eachRepayMethod) {
		this.eachRepayMethod = eachRepayMethod;
	}

	public Integer getSpTerm() {
		return spTerm;
	}

	public void setSpTerm(Integer spTerm) {
		this.spTerm = spTerm;
	}

	public BigDecimal getSpRate() {
		return spRate;
	}

	public void setSpRate(BigDecimal spRate) {
		this.spRate = spRate;
	}

	public String getSplictPeriod() {
		return splictPeriod;
	}

	public void setSplictPeriod(String splictPeriod) {
		this.splictPeriod = splictPeriod;
	}

	public BigDecimal getRate() {
		return rate;
	}

	public void setRate(BigDecimal rate) {
		this.rate = rate;
	}

	public String getRateNature() {
		return rateNature;
	}

	public void setRateNature(String rateNature) {
		this.rateNature = rateNature;
	}

	public Long getLoanDate() {
		return loanDate;
	}

	public void setLoanDate(Long loanDate) {
		this.loanDate = loanDate;
	}

	public Integer getLoanDays() {
		return loanDays;
	}

	public void setLoanDays(Integer loanDays) {
		this.loanDays = loanDays;
	}

	public Integer getRepaymentDate() {
		if(ObjectHelper.isEmpty(repaymentDate)&&ObjectHelper.isNotEmpty(this.loanDate)&&Integer.parseInt((""+this.loanDate).substring(6, 8))>28){
			repaymentDate=Integer.parseInt((""+this.loanDate).substring(6, 8));
		}
		return repaymentDate;
	}

	public void setRepaymentDate(Integer repaymentDate) {
		this.repaymentDate = repaymentDate;
	}

	public Integer getRepaymentDay() {
		return repaymentDay;
	}

	public void setRepaymentDay(Integer repaymentDay) {
		this.repaymentDay = repaymentDay;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Integer getDecimalPlace() {
		return decimalPlace;
	}

	public void setDecimalPlace(Integer decimalPlace) {
		this.decimalPlace = decimalPlace;
	}

	public String getTermUnit() {
		return termUnit;
	}

	public void setTermUnit(String termUnit) {
		this.termUnit = termUnit;
	}

	public String getRateUnit() {
		return rateUnit;
	}

	public void setRateUnit(String rateUnit) {
		this.rateUnit = rateUnit;
	}
}