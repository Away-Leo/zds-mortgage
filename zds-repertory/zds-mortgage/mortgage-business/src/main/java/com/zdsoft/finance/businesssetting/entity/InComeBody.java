package com.zdsoft.finance.businesssetting.entity;

import com.zdsoft.framework.core.common.domain.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @title InComeBody.java
 * @className InComeBody
 * @description 收款主体域对象
 * @author LiaoGuoWei
 * @create 2017/3/3 11:44
 * @version V1.0
 **/
@Entity
@Table(name="buss_incomebody")
public class InComeBody extends BaseEntity{

	private static final long serialVersionUID = 1395509210159133836L;
	/**
	 * 机构Id
	 */
	@Column(length=100)
	private String orgId;
	/**
	 * 父机构ID
	 */
	@Column(length=100)
	private String orgPid;
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
	/**
	 * 机构名称
	 */
	@Column(length = 64)
	private String orgName;

	/**
	 * 多个机构ID
	 */
	private transient String orgIds;

	public String getOrgIds() {
		return orgIds;
	}

	public void setOrgIds(String orgIds) {
		this.orgIds = orgIds;
	}

	public String getOrgPid() {
		return orgPid;
	}

	public void setOrgPid(String orgPid) {
		this.orgPid = orgPid;
	}

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
