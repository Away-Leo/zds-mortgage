package com.zdsoft.finance.cedit.api.entity;

//import com.thoughtworks.xstream.annotations.XStreamAlias;

//@XStreamAlias("BeOverdueInfoOverview") // 逾期及违约信息
public class BeOverdueInfoOverview {

//	2.5.2.7 n逾期及违约信息概要【BeOverdueInfoOverview】 
//	BadDebtsInfoSumNumber 呆账信息汇总笔数 S N 
//	BadDebtsInfoSumAmount 呆账信息汇总余额 S N  
//	AssetsDisposalInfoSumNumber 资产处置信息汇总笔数 S N 
//	AssetsDisposalInfoSumAmount 资产处置信息汇总余额 S N  
//	GuarantorCompenInfoSumNumber 保证人代偿信息汇总笔数 S N  
//	GuarantorCompenInfoSumAmount 保证人代偿信息汇总余额 S N  
	
//	@XStreamAlias("BadDebtsInfoSumNumber")
	private String badDebtsInfoSumNumber;
	
//	@XStreamAlias("BadDebtsInfoSumAmount")
	private String badDebtsInfoSumAmount;
	
//	@XStreamAlias("AssetsDisposalInfoSumNumber")
	private String assetsDisposalInfoSumNumber;
	
//	@XStreamAlias("AssetsDisposalInfoSumAmount")
	private String assetsDisposalInfoSumAmount;
	
//	@XStreamAlias("GuarantorCompenInfoSumNumber")
	private String guarantorCompenInfoSumNumber;
	
//	@XStreamAlias("GuarantorCompenInfoSumAmount")
	private String guarantorCompenInfoSumAmount;

	public String getBadDebtsInfoSumNumber() {
		return badDebtsInfoSumNumber;
	}

	public void setBadDebtsInfoSumNumber(String badDebtsInfoSumNumber) {
		this.badDebtsInfoSumNumber = badDebtsInfoSumNumber;
	}

	public String getBadDebtsInfoSumAmount() {
		return badDebtsInfoSumAmount;
	}

	public void setBadDebtsInfoSumAmount(String badDebtsInfoSumAmount) {
		this.badDebtsInfoSumAmount = badDebtsInfoSumAmount;
	}

	public String getAssetsDisposalInfoSumNumber() {
		return assetsDisposalInfoSumNumber;
	}

	public void setAssetsDisposalInfoSumNumber(String assetsDisposalInfoSumNumber) {
		this.assetsDisposalInfoSumNumber = assetsDisposalInfoSumNumber;
	}

	public String getAssetsDisposalInfoSumAmount() {
		return assetsDisposalInfoSumAmount;
	}

	public void setAssetsDisposalInfoSumAmount(String assetsDisposalInfoSumAmount) {
		this.assetsDisposalInfoSumAmount = assetsDisposalInfoSumAmount;
	}

	public String getGuarantorCompenInfoSumNumber() {
		return guarantorCompenInfoSumNumber;
	}

	public void setGuarantorCompenInfoSumNumber(String guarantorCompenInfoSumNumber) {
		this.guarantorCompenInfoSumNumber = guarantorCompenInfoSumNumber;
	}

	public String getGuarantorCompenInfoSumAmount() {
		return guarantorCompenInfoSumAmount;
	}

	public void setGuarantorCompenInfoSumAmount(String guarantorCompenInfoSumAmount) {
		this.guarantorCompenInfoSumAmount = guarantorCompenInfoSumAmount;
	}
	
	

}
