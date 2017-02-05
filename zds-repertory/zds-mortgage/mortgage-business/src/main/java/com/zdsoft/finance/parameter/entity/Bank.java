package com.zdsoft.finance.parameter.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.zdsoft.framework.core.common.domain.BaseEntity;
/**
 * 银行表
 * @author hw
 *
 */
@Entity
@Table(name="zf_bank")
public class Bank extends BaseEntity{
	private static final long serialVersionUID = 2741867178996966281L;
	/**
	 * 银行名称
	 */
	@Column(length=50)
	private String name;
	/**
	 * 银行代码
	 */
	@Column(length=50)
	private String code;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}

}
