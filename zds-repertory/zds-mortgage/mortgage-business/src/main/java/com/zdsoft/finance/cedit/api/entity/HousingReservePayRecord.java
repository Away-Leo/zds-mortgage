package com.zdsoft.finance.cedit.api.entity;

//import com.thoughtworks.xstream.annotations.XStreamAlias;

//	@XStreamAlias("HousingReservePayRecord")  // 未注销借记卡 信息
public class HousingReservePayRecord {

	//	@XStreamAlias("PayArea") //参缴地
	private String PayArea;
	
	//	@XStreamAlias("PayDate") //参缴日期
	private String PayDate;
	
	//	@XStreamAlias("FirstPayMonth") //初缴月份
	private String FirstPayMonth;
	
	//	@XStreamAlias("PayToMonth") //缴至月份
	private String PayToMonth;
	
	//	@XStreamAlias("PayState") //缴费状态
	private String PayState;
	
	//	@XStreamAlias("MonthPayDeposit") //月缴存额
	private String MonthPayDeposit;
	
	//	@XStreamAlias("PersonPayProportion") //个人缴存比例
	private String PersonPayProportion;
	
	//	@XStreamAlias("CompanyPayProportion") //单位缴存比例
	private String CompanyPayProportion;

	//	@XStreamAlias("PayCompany") //缴费单位
	private String PayCompany;
	
	//	@XStreamAlias("InfoUpdateDate") //信息更新日期
	private String InfoUpdateDate;

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

	public String getFirstPayMonth() {
		return FirstPayMonth;
	}

	public void setFirstPayMonth(String firstPayMonth) {
		FirstPayMonth = firstPayMonth;
	}

	public String getPayToMonth() {
		return PayToMonth;
	}

	public void setPayToMonth(String payToMonth) {
		PayToMonth = payToMonth;
	}

	public String getPayState() {
		return PayState;
	}

	public void setPayState(String payState) {
		PayState = payState;
	}

	public String getMonthPayDeposit() {
		return MonthPayDeposit;
	}

	public void setMonthPayDeposit(String monthPayDeposit) {
		MonthPayDeposit = monthPayDeposit;
	}

	public String getPersonPayProportion() {
		return PersonPayProportion;
	}

	public void setPersonPayProportion(String personPayProportion) {
		PersonPayProportion = personPayProportion;
	}

	public String getCompanyPayProportion() {
		return CompanyPayProportion;
	}

	public void setCompanyPayProportion(String companyPayProportion) {
		CompanyPayProportion = companyPayProportion;
	}

	public String getPayCompany() {
		return PayCompany;
	}

	public void setPayCompany(String payCompany) {
		PayCompany = payCompany;
	}

	public String getInfoUpdateDate() {
		return InfoUpdateDate;
	}

	public void setInfoUpdateDate(String infoUpdateDate) {
		InfoUpdateDate = infoUpdateDate;
	}
	
	
	
	
	
	
}
