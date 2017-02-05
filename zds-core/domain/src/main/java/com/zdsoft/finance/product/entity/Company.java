package com.zdsoft.finance.product.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.zdsoft.framework.core.common.domain.BaseEntity;

/**
 * 公司
 * @author longwei
 * @date 2016/12/22
 * @version 1.0
 */
@Entity
@Table(name="prct_company")
public class Company extends BaseEntity{

	private static final long serialVersionUID = 9062743836056894726L;

	/**
	 * 公司名称
	 */
	@Column(length=255)
	private String name;
	
	/**
	 * 机构代码
	 */
	@Column(length=64)
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
