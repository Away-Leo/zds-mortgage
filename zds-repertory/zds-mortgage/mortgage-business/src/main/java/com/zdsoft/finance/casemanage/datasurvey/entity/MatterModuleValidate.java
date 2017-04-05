package com.zdsoft.finance.casemanage.datasurvey.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.zdsoft.framework.core.common.domain.BaseEntity;

/** 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: MatterModuleValidate.java 
 * @ClassName: MatterModuleValidate 
 * @Description: 事项模块验证实体
 * @author zhoushichao 
 * @date 2017年3月3日 下午2:31:03 
 * @version V1.0 
 */ 
@Entity
@Table(name = "case_matter_module_validate")
public class MatterModuleValidate extends BaseEntity{

	private static final long serialVersionUID = 1L;
	/**
	 * 未执行
	 */
	public static final Integer NO = 0;
	/**
	 * 已执行
	 */
	public static final Integer YES = 1;

	/**
	 * 主体Id(推荐使用案件Id)
	 */
	@Column(length = 32)
	private String businessKey;
	/**
	 * 事项名  MatterName.java
	 */
	@Column(length = 64)
	private String matterName;
	/**
	 * 页签名  TabName.java
	 */
	@Column(length = 64)
	private String tabName;
	/**
	 * 是否执行(默认：0：未执行；1：已执行)
	 */
	@Column
	private Integer executeTag = 0;
	
	public String getBusinessKey() {
		return businessKey;
	}
	public void setBusinessKey(String businessKey) {
		this.businessKey = businessKey;
	}
	public String getMatterName() {
		return matterName;
	}
	public void setMatterName(String matterName) {
		this.matterName = matterName;
	}
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
