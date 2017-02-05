package com.zdsoft.finance.parameter.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.zdsoft.framework.core.common.domain.BaseEntity;
/**
 * 提前还款规则
 * @author hw
 *
 */
@Entity
@Table(name="zf_prepay_rule")
public class PrePayRule extends BaseEntity{
	private static final long serialVersionUID = 2613416688308040131L;
	/**
	 * 规则编码
	 */
	@Column(length=50)
	private String code;
	/**
	 * 规则名
	 */
	@Column(length=50)
	private String name;
	/**
	 * 规则描述
	 */
	@Column(length=500)
	private String remark;
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
	
}
