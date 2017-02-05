package com.zdsoft.finance.repayplantool.vo;

public class CalcProidVo {

private Integer startDt;
	
	private Integer endDt;
	
	private Integer days=0;
	
	private boolean isKuanYue;//是否垮月
	
	private Integer actRepayDt=0;//每期实际还款日

	public Integer getStartDt() {
		return startDt;
	}

	public void setStartDt(Integer startDt) {
		this.startDt = startDt;
	}

	public Integer getEndDt() {
		return endDt;
	}

	public void setEndDt(Integer endDt) {
		this.endDt = endDt;
	}

	public Integer getDays() {
		return days;
	}

	public void setDays(Integer days) {
		this.days = days;
	}

	public boolean isKuanYue() {
		return isKuanYue;
	}

	public void setKuanYue(boolean isKuanYue) {
		this.isKuanYue = isKuanYue;
	}

	public Integer getActRepayDt() {
		return actRepayDt;
	}

	public void setActRepayDt(Integer actRepayDt) {
		this.actRepayDt = actRepayDt;
	}
	
}
