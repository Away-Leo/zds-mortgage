package com.zdsoft.finance.cedit.api.entity;

//import com.thoughtworks.xstream.annotations.XStreamAlias;

//@XStreamAlias("AssetsDisposalInfo") // 资产处置信息【
public class AssetsDisposalInfo {
//	@XStreamAlias("No") //编号
	private String no;
	
//	@XStreamAlias("AssetManagementCo") //资产管理公司
	private String assetManagementCo;
	
//	@XStreamAlias("DebtReceiveDate") //债务接收日期
	private String debtReceiveDate;
	
//	@XStreamAlias("ReceiveRightsAmount") //接收的债权金额 
	private String receiveRightsAmount;
	
//	@XStreamAlias("LastPayOffDate") //最近一次还款日期
	private String lastPayOffDate;
	
//	@XStreamAlias("AssetDisposalOverage")  //资产处置余额
	private String assetDisposalOverage;

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public String getAssetManagementCo() {
		return assetManagementCo;
	}

	public void setAssetManagementCo(String assetManagementCo) {
		this.assetManagementCo = assetManagementCo;
	}

	public String getDebtReceiveDate() {
		return debtReceiveDate;
	}

	public void setDebtReceiveDate(String debtReceiveDate) {
		this.debtReceiveDate = debtReceiveDate;
	}

	public String getReceiveRightsAmount() {
		return receiveRightsAmount;
	}

	public void setReceiveRightsAmount(String receiveRightsAmount) {
		this.receiveRightsAmount = receiveRightsAmount;
	}

	public String getLastPayOffDate() {
		return lastPayOffDate;
	}

	public void setLastPayOffDate(String lastPayOffDate) {
		this.lastPayOffDate = lastPayOffDate;
	}

	public String getAssetDisposalOverage() {
		return assetDisposalOverage;
	}

	public void setAssetDisposalOverage(String assetDisposalOverage) {
		this.assetDisposalOverage = assetDisposalOverage;
	}
	
	

	

	
	
	
	
}
