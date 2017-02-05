package com.zdsoft.finance.parameter.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.zdsoft.framework.core.common.domain.BaseEntity;
/**
 * 费用项表
 * @author hw
 *
 */
@Entity
@Table(name="zf_costitem")
public class CostItem extends BaseEntity{
	private static final long serialVersionUID = -877666659004970564L;
	/**
	 * 费用项编码
	 */
	@Column(length=50)
	private String code;
	/**
	 * 费用项名称
	 */
	@Column(length=50)
	private String name;
	/**
	 * 备注
	 */
	@Column(length=500)
	private String remark;
	@Column(length=50)
	private String empName;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
