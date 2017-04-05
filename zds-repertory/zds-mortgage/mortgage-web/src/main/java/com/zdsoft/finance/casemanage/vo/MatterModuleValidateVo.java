package com.zdsoft.finance.casemanage.vo;

/** 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: MatterModuleValidateVo.java 
 * @ClassName: MatterModuleValidateVo 
 * @Description: 事项模块验证Vo
 * @author zhoushichao 
 * @date 2017年3月6日 下午12:26:42 
 * @version V1.0 
 */ 
public class MatterModuleValidateVo {

	/**
	 * 页签名  TabName.java
	 */
	private String tabName;
	/**
	 * 是否执行(默认：0：未执行；1：已执行)
	 */
	private Integer executeTag;
	public String getTabName() {
		return tabName;
	}
	public void setTabName(String tabName) {
		this.tabName = tabName;
	}
	public Integer getExecuteTag() {
		return executeTag;
	}
	public void setExecuteTag(Integer executeTag) {
		this.executeTag = executeTag;
	}
}
