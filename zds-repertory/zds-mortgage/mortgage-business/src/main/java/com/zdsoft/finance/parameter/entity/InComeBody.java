package com.zdsoft.finance.parameter.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.zdsoft.framework.core.common.domain.BaseEntity;
/**
 * 收款主体表
 * @author hw
 *
 */
@Entity
@Table(name="zf_income_body")
public class InComeBody extends BaseEntity{

	private static final long serialVersionUID = 1395509210159133836L;
	/**
	 * 机构Id
	 */
	@Column(length=100)
	private String orgId;
	/**
	 * 收款主体
	 */
	@Column(length=50)
	private String inBody;
	/**
	 * 备注
	 */
	@Column(length=200)
	private String remark;
	private String orgName;
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	public String getInBody() {
		return inBody;
	}
	public void setInBody(String inBody) {
		this.inBody = inBody;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	
	
}
