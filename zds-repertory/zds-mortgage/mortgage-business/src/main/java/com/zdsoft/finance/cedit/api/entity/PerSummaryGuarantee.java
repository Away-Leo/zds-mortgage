package com.zdsoft.finance.cedit.api.entity;

//import com.thoughtworks.xstream.annotations.XStreamAlias;

//	@XStreamAlias("PerSummaryGuarantee")
public class PerSummaryGuarantee {
	
	//	@XStreamAlias("PerGuarMount") // 担保笔数
	private String perGuarMount;
	
	//	@XStreamAlias("PerGuarAmount")  //担保金额
	private String perGuarAmount;
	
	//	@XStreamAlias("PerGuarPrincipalAmount")  //担保本金金额
	private String perGuarPrincipalAmount;

	public String getPerGuarMount() {
		return perGuarMount;
	}

	public void setPerGuarMount(String perGuarMount) {
		this.perGuarMount = perGuarMount;
	}

	public String getPerGuarAmount() {
		return perGuarAmount;
	}

	public void setPerGuarAmount(String perGuarAmount) {
		this.perGuarAmount = perGuarAmount;
	}

	public String getPerGuarPrincipalAmount() {
		return perGuarPrincipalAmount;
	}

	public void setPerGuarPrincipalAmount(String perGuarPrincipalAmount) {
		this.perGuarPrincipalAmount = perGuarPrincipalAmount;
	}

	
	
	
	
	
	

}
