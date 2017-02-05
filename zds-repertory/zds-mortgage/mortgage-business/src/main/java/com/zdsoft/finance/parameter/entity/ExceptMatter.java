package com.zdsoft.finance.parameter.entity;

import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.zdsoft.framework.core.common.domain.BaseEntity;
/**
 * 特批事项
 * @author hw
 *
 */
@Entity
@Table(name="zf_except_matter")
public class ExceptMatter extends BaseEntity{
	private static final long serialVersionUID = -3868459068064415309L;
	/**
	 * 特批事项编码
	 */
	@Column(length=32)
	private String code;
	/**
	 * 特批事项类型
	 */
	@Column(length=50)
	private String type;
	/**
	 * 特批事项名称
	 */
	@Column(length=50)
	private String name;
	/**
	 * 状态
	 */
	@Column
	private int status;
	/**
	 * 备注
	 */
	@Column(length=200)
	private String remark;
	private String empName;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
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
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	
}
