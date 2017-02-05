package com.zdsoft.finance.seal.vo;

public class PrintDetailsVo {		
	//资料名称	
	//原件（份数）	
	//复印件（份数）	
	//申请公章	
	//其他说明
	
	private String detailsName="营业执照（4-1或4-4）";
	public String getDetailsName() {
		return detailsName;
	}
	public void setDetailsName(String detailsName) {
		this.detailsName = detailsName;
	}
	public int getOriginalCount() {
		return originalCount;
	}
	public void setOriginalCount(int originalCount) {
		this.originalCount = originalCount;
	}
	public int getCopyCount() {
		return copyCount;
	}
	public void setCopyCount(int copyCount) {
		this.copyCount = copyCount;
	}
	public String getAppSeal() {
		return appSeal;
	}
	public void setAppSeal(String appSeal) {
		this.appSeal = appSeal;
	}
	public String getOtherDesc() {
		return otherDesc;
	}
	public void setOtherDesc(String otherDesc) {
		this.otherDesc = otherDesc;
	}
	private int originalCount=5;
	private int copyCount=6;
	private String appSeal="";
	private String otherDesc="其他说明";
}
