package com.zdsoft.finance.businesssetting.entity;

import com.zdsoft.framework.core.common.domain.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @title CostItem.java
 * @className CostItem
 * @description 费用项域对象
 * @author LiaoGuoWei
 * @create 2017/3/3 10:38
 * @version V1.0
 **/
@Entity
@Table(name="buss_costitem")
public class CostItem extends BaseEntity{
	private static final long serialVersionUID = -877666659004970564L;
	/**
	 * 费用项编码
	 */
	@Column(length=50)
	private String costItemCode;
	/**
	 * 费用项名称
	 */
	@Column(length=50)
	private String costItemName;
	/**
	 * 备注
	 */
	@Column(length=500)
	private String remark;

	/**
	 * 操作人名称
	 */
	@Column(length=50)
	private String empName;

	public String getCostItemCode() {
		return costItemCode;
	}

	public void setCostItemCode(String costItemCode) {
		this.costItemCode = costItemCode;
	}

	public String getCostItemName() {
		return costItemName;
	}

	public void setCostItemName(String costItemName) {
		this.costItemName = costItemName;
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
