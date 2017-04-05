package com.zdsoft.finance.businesssetting.entity;

import com.zdsoft.framework.core.common.domain.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @title ExceptMatter.java
 * @className ExceptMatter
 * @description 特批事项域对象
 * @author LiaoGuoWei
 * @create 2017/3/3 11:32
 * @version V1.0
 **/
@Entity
@Table(name="buss_exceptmatter")
public class ExceptMatter extends BaseEntity{
	private static final long serialVersionUID = -3868459068064415309L;
	/**
	 * 特批事项编码
	 */
	@Column(length=32)
	private String exceptMattercode;
	/**
	 * 特批事项类型
	 */
	@Column(length=32)
	private String exceptMatterType;
	/**
	 * 特批事项名称
	 */
	@Column(length=64)
	private String exceptMatterName;
	/**
	 * 状态
	 */
	@Column(length = 1)
	private String status;
	/**
	 * 备注
	 */
	@Column(length=512)
	private String remark;

	public String getExceptMattercode() {
		return exceptMattercode;
	}

	public void setExceptMattercode(String exceptMattercode) {
		this.exceptMattercode = exceptMattercode;
	}

	public String getExceptMatterType() {
		return exceptMatterType;
	}

	public void setExceptMatterType(String exceptMatterType) {
		this.exceptMatterType = exceptMatterType;
	}

	public String getExceptMatterName() {
		return exceptMatterName;
	}

	public void setExceptMatterName(String exceptMatterName) {
		this.exceptMatterName = exceptMatterName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
}
