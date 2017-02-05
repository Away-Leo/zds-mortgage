package com.zdsoft.finance.cedit.api.entity;

//import com.thoughtworks.xstream.annotations.XStreamAlias;

//	@XStreamAlias("PensionInsurancePayRecord") // 养老保险金缴存记录
public class PensionInsurancePayRecord {
	//	@XStreamAlias("PayArea") //参保地
	private String PayArea;
	
	//	@XStreamAlias("PayDate") //参保日期
	private String PayDate;
	
	//	@XStreamAlias("CumulativePayMonths") //累计缴费月数
	private String CumulativePayMonths;
	
	//	@XStreamAlias("WorkMonth") //参加工作月份
	private String WorkMonth;
	
	//	@XStreamAlias("PayState") //缴费状态
	private String PayState;
	
	//	@XStreamAlias("PersonPayBase") //个人缴费基数
	private String PersonPayBase;
	
	//	@XStreamAlias("ThisMonthPayAmount") //本月缴费金额 
	private String ThisMonthPayAmount;
	
	//	@XStreamAlias("InfoUpdateDate") //信息更新日期
	private String InfoUpdateDate;
	
	//	@XStreamAlias("PayCompany") //缴费单位
	private String PayCompany;
	
	//	@XStreamAlias("CancelPayReason") //中断或终止缴费原因
	private String CancelPayReason;

	public String getPayArea() {
		return PayArea;
	}

	public void setPayArea(String payArea) {
		PayArea = payArea;
	}

	public String getPayDate() {
		return PayDate;
	}

	public void setPayDate(String payDate) {
		PayDate = payDate;
	}

	public String getCumulativePayMonths() {
		return CumulativePayMonths;
	}

	public void setCumulativePayMonths(String cumulativePayMonths) {
		CumulativePayMonths = cumulativePayMonths;
	}

	public String getWorkMonth() {
		return WorkMonth;
	}

	public void setWorkMonth(String workMonth) {
		WorkMonth = workMonth;
	}

	public String getPayState() {
		return PayState;
	}

	public void setPayState(String payState) {
		PayState = payState;
	}

	public String getPersonPayBase() {
		return PersonPayBase;
	}

	public void setPersonPayBase(String personPayBase) {
		PersonPayBase = personPayBase;
	}

	public String getThisMonthPayAmount() {
		return ThisMonthPayAmount;
	}

	public void setThisMonthPayAmount(String thisMonthPayAmount) {
		ThisMonthPayAmount = thisMonthPayAmount;
	}

	public String getInfoUpdateDate() {
		return InfoUpdateDate;
	}

	public void setInfoUpdateDate(String infoUpdateDate) {
		InfoUpdateDate = infoUpdateDate;
	}

	public String getPayCompany() {
		return PayCompany;
	}

	public void setPayCompany(String payCompany) {
		PayCompany = payCompany;
	}

	public String getCancelPayReason() {
		return CancelPayReason;
	}

	public void setCancelPayReason(String cancelPayReason) {
		CancelPayReason = cancelPayReason;
	}


	
	

}
