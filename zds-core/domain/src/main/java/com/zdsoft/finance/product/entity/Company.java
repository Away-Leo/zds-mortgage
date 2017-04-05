package com.zdsoft.finance.product.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.zdsoft.framework.core.common.domain.BaseEntity;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title: Company.java 
 * @ClassName: Company 
 * @Description: 公司
 * @author gufeng 
 * @date 2017年3月14日 下午4:30:13 
 * @version V1.0
 */
@Entity
@Table(name="prd_company")
public class Company extends BaseEntity{

	private static final long serialVersionUID = 9062743836056894726L;

	/**
	 * 公司名称
	 */
	@Column(length = 64)
	private String name;
	
	/**
	 * 机构代码
	 */
	@Column(length = 64)
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
