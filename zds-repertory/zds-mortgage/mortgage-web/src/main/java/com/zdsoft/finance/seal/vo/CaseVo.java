package com.zdsoft.finance.seal.vo;

public class CaseVo {
	public String getCaseNo() {
		return caseNo;
	}
	public void setCaseNo(String caseNo) {
		this.caseNo = caseNo;
	}
	public String getJiedanDate() {
		return jiedanDate;
	}
	public void setJiedanDate(String jiedanDate) {
		this.jiedanDate = jiedanDate;
	}
	public String getSubProduct() {
		return subProduct;
	}
	public void setSubProduct(String subProduct) {
		this.subProduct = subProduct;
	}
	public String getEntendMNG() {
		return entendMNG;
	}
	public void setEntendMNG(String entendMNG) {
		this.entendMNG = entendMNG;
	}
	public String getEntendDept() {
		return entendDept;
	}
	public void setEntendDept(String entendDept) {
		this.entendDept = entendDept;
	}
	public String getJigou() {
		return jigou;
	}
	public void setJigou(String jigou) {
		this.jigou = jigou;
	}
	public String getApplyMoney() {
		return applyMoney;
	}
	public void setApplyMoney(String applyMoney) {
		this.applyMoney = applyMoney;
	}
	public String getLoanLmit() {
		return loanLmit;
	}
	public void setLoanLmit(String loanLmit) {
		this.loanLmit = loanLmit;
	}
	public String getPayBackType() {
		return payBackType;
	}
	public void setPayBackType(String payBackType) {
		this.payBackType = payBackType;
	}
	public String getLoanRate() {
		return loanRate;
	}
	public void setLoanRate(String loanRate) {
		this.loanRate = loanRate;
	}
	public String getOverRate() {
		return overRate;
	}
	public void setOverRate(String overRate) {
		this.overRate = overRate;
	}
	public String getTerminal() {
		return terminal;
	}
	public void setTerminal(String terminal) {
		this.terminal = terminal;
	}
	public String getMoneyFrom() {
		return moneyFrom;
	}
	public void setMoneyFrom(String moneyFrom) {
		this.moneyFrom = moneyFrom;
	}
	private String caseNo = "vdvdvdvd";// 案件号
	private String jiedanDate = "";// 接单日期
	private String subProduct = "";// 子产品
	private String entendMNG = "";// 拓展经理
	private String entendDept = "";// 拓展部门
	private String jigou = "";// 机构
	private String applyMoney = "";// 申请金额（元）
	private String loanLmit = "";// 贷款期限
	private String payBackType = ""; // 还款方式
	private String loanRate = "";// 贷款利率
	private String overRate = "";// 逾期利率
	private String terminal = "";// 终端
	private String moneyFrom = "";// 资金来源
}
