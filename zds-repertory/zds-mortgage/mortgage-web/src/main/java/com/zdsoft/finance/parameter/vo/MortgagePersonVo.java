package com.zdsoft.finance.parameter.vo;

import com.zdsoft.finance.common.base.BaseVo;
import com.zdsoft.finance.parameter.entity.MortgagePerson;

public class MortgagePersonVo extends BaseVo<MortgagePerson>{
	/**
	 * id
	 */
	private String id;
	/**
	 * 机构名称
	 */
	private String orgName;
	/**
	 * 机构
	 */
	private String orgId;
	/**
	 * 类型
	 */
	private String type;
	/**
	 * 名称
	 */
	private String name;
	/**
	 * 状态
	 */
	private int status;
	/**
	 * 创建日期
	 */
	private long createDate;
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public long getCreateDate() {
		return createDate;
	}
	public void setCreateDate(long createDate) {
		this.createDate = createDate;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	
	
}
