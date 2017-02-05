package com.zdsoft.finance.parameter.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.zdsoft.framework.core.common.domain.BaseEntity;
/**
 * 标签
 * @author hw
 *
 */
@Entity
@Table(name="zf_sys_label")
public class SysLabel extends BaseEntity{
	private static final long serialVersionUID = -6075266942139101516L;
	/**
	 * 标签名称
	 */
	@Column(name="name",length=50)
	private String name;
	/**
	 * 标签类型
	 */
	@Column(name="type",length=50)
	private String type;
	/**
	 * 取值方式
	 */
	@Column(name="valueMethod",length=32)
	private String valueMethod;
	/**
	 * 最大显示字数
	 */
	@Column(name="maxNumber")
	private int maxNumber;
	/**
	 * 取值SQL
	 */
	@Column(name="sqls",length=500)
	private String sqls;
	/**
	 * 状态
	 */
	@Column(name="status")
	private int status;
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
	public String getValueMethod() {
		return valueMethod;
	}
	public void setValueMethod(String valueMethod) {
		this.valueMethod = valueMethod;
	}
	public int getMaxNumber() {
		return maxNumber;
	}
	public void setMaxNumber(int maxNumber) {
		this.maxNumber = maxNumber;
	}
	public String getSqls() {
		return sqls;
	}
	public void setSqls(String sqls) {
		this.sqls = sqls;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
}
