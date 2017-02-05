package com.zdsoft.finance.cedit.api.entity;

//import com.thoughtworks.xstream.annotations.XStreamAlias;

//	@XStreamAlias("PerGuaranteeInfo")
public class PerGuaranteeInfo {
	
	//	@XStreamAlias("No") // 编号
	private String no;
	
	//	@XStreamAlias("GuarLoanIssuingAgency")  //担保贷款发放机构
	private String guarLoanIssuingAgency;
	
	//	@XStreamAlias("GuarLoanContractAmount")  //担保贷款合同金额
	private String guarLoanContractAmount;

	
	//	@XStreamAlias("GuarLoanIssueDate")  //担保贷款发放日期
	private String guarLoanIssueDate;
	
	//	@XStreamAlias("GuarLoanDueDate")  //担保贷款到期日期
	private String guarLoanDueDate;
	
	//	@XStreamAlias("GuarAmount")  //担保金额
	private String guarAmount;
	
	//	@XStreamAlias("GuarLoanPrincipalAmount")  //担保贷款本金余额
	private String guarLoanPrincipalAmount;
	
	//	@XStreamAlias("GuarLoanFiveclasscode")  //担保贷款五级分类
	private String guarLoanFiveclasscode;
	
	//	@XStreamAlias("SettlementDate")  //结算日期
	private String settlementDate;



	
	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	

	public String getGuarLoanContractAmount() {
		return guarLoanContractAmount;
	}

	public void setGuarLoanContractAmount(String guarLoanContractAmount) {
		this.guarLoanContractAmount = guarLoanContractAmount;
	}

	public String getGuarLoanIssueDate() {
		return guarLoanIssueDate;
	}

	public void setGuarLoanIssueDate(String guarLoanIssueDate) {
		this.guarLoanIssueDate = guarLoanIssueDate;
	}

	public String getGuarLoanDueDate() {
		return guarLoanDueDate;
	}

	public void setGuarLoanDueDate(String guarLoanDueDate) {
		this.guarLoanDueDate = guarLoanDueDate;
	}

	public String getGuarAmount() {
		return guarAmount;
	}

	public void setGuarAmount(String guarAmount) {
		this.guarAmount = guarAmount;
	}

	public String getGuarLoanPrincipalAmount() {
		return guarLoanPrincipalAmount;
	}

	public void setGuarLoanPrincipalAmount(String guarLoanPrincipalAmount) {
		this.guarLoanPrincipalAmount = guarLoanPrincipalAmount;
	}

	public String getGuarLoanFiveclasscode() {
		return guarLoanFiveclasscode;
	}

	public void setGuarLoanFiveclasscode(String guarLoanFiveclasscode) {
		this.guarLoanFiveclasscode = guarLoanFiveclasscode;
	}

	public String getSettlementDate() {
		return settlementDate;
	}

	public void setSettlementDate(String settlementDate) {
		this.settlementDate = settlementDate;
	}

	public String getGuarLoanIssuingAgency() {
		return guarLoanIssuingAgency;
	}

	public void setGuarLoanIssuingAgency(String guarLoanIssuingAgency) {
		this.guarLoanIssuingAgency = guarLoanIssuingAgency;
	}



	
	
	
	
	

}
