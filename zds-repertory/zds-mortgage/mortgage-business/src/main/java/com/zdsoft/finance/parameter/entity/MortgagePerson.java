package com.zdsoft.finance.parameter.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.zdsoft.framework.core.common.domain.BaseEntity;
/**
 * 抵押权人
 * @author hw
 *
 */
@Entity
@Table(name="zf_mortgage_person")
public class MortgagePerson extends BaseEntity{
	private static final long serialVersionUID = -365166626952899462L;
	/**
	 * 抵押权人名称
	 */
	@Column(name="name",length=50)
	private String name;
	/**
	 * 类别
	 */
	@Column(name="type",length=50)
	private String type;
	/**
	 * 机构Id
	 */
	@Column(name="orgId",length=100)
	private String orgId;
	/**
	 * 状态
	 */
	@Column(name="status")
	private int status;
	private String orgName;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	
}
