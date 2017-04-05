package com.zdsoft.finance.app.casemanage.handleMortgage.vo;

import java.util.List;

/** 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: AppInChargeVo.java 
 * @ClassName: AppInChargeVo 
 * @Description: App入押信息显示封装Vo
 * @author zhoushichao 
 * @date 2017年3月2日 下午5:29:54 
 * @version V1.0 
 */ 
public class AppInChargeVo {
	/**
	 * 房产信息Vo
	 */
	private AppHousePropertyVo appHousePropertyVo;
	/**
	 * 抵押Vo
	 */
	private List<AppPledgeInfoVo> appPledgeInfoVoList;
	/**
	 * 产权状态Vo
	 */
	private AppSearchVo AppsearchVo;
	/**
	 * 产权人Vo
	 */
	private List<AppPropertyOwnerVo> appPropertyOwnerVoList;
	/**
	 * 查册入押Vo
	 */
	private AppDetainVo appDetainVo;
	public AppHousePropertyVo getAppHousePropertyVo() {
		return appHousePropertyVo;
	}
	public void setAppHousePropertyVo(AppHousePropertyVo appHousePropertyVo) {
		this.appHousePropertyVo = appHousePropertyVo;
	}
	public List<AppPledgeInfoVo> getAppPledgeInfoVoList() {
		return appPledgeInfoVoList;
	}
	public void setAppPledgeInfoVoList(List<AppPledgeInfoVo> appPledgeInfoVoList) {
		this.appPledgeInfoVoList = appPledgeInfoVoList;
	}
	public AppSearchVo getAppsearchVo() {
		return AppsearchVo;
	}
	public void setAppsearchVo(AppSearchVo appsearchVo) {
		AppsearchVo = appsearchVo;
	}
	public List<AppPropertyOwnerVo> getAppPropertyOwnerVoList() {
		return appPropertyOwnerVoList;
	}
	public void setAppPropertyOwnerVoList(List<AppPropertyOwnerVo> appPropertyOwnerVoList) {
		this.appPropertyOwnerVoList = appPropertyOwnerVoList;
	}
	public AppDetainVo getAppDetainVo() {
		return appDetainVo;
	}
	public void setAppDetainVo(AppDetainVo appDetainVo) {
		this.appDetainVo = appDetainVo;
	}
}
