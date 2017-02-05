package com.zdsoft.finance.prcostitem.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.zdsoft.framework.core.common.domain.BaseEntity;

/**
 * 贷后监控(业务监控临时需要)
 * @createTime 2017-01-11
 * @author <a href="mailto:gufeng@zdsoft.cn">gufeng</a>
 * @version 1.0
 */
@Entity
@Table(name = "zf_test_post_loan")
public class TestPostloan extends BaseEntity{

	private static final long serialVersionUID = 1L;
	
	/**
	 * 机构
	 */
	@Column
	private String organization;
	/**
	 * 案件号
	 */
	@Column(length = 32)
	private String projectCd;
	/**
	 * 主借人
	 */
	@Column
	private String creditMember;
	
	/**
	 * 联系方式
	 */
	@Column(length = 32)
	private String phone;
	/**
	 * 产品分类
	 */
	@Column
	private String parentNm;
	/**
	 * 子产品
	 */
	@Column
	private String productNm;
	/**
	 * 楼龄
	 */
	@Column
	private Integer floorAge;
	
	/**
	 * 贷款金额
	 */
	@Column(precision = 30,scale = 12)
	private BigDecimal amount;
	/**
	 * 逾期金额
	 */
	@Column(precision = 30,scale = 12)
	private BigDecimal overdueAmount;
	/**
	 * 逾期日期
	 */
	@Column
	private Long overdueDate;
	/**
	 * 逾期天数
	 */
	@Column
	private Integer overdueDays;
	/**
	 * 上次重估时间
	 */
	@Column
	private Long lastDate;
	
	/**
	 * 是否出险
	 */
	@Column(columnDefinition = "boolean")
	private Boolean isDanger;
	/**
	 * 案件状态
	 */
	@Column
	private Integer projectStatus;
	public String getOrganization() {
		return organization;
	}
	public void setOrganization(String organization) {
		this.organization = organization;
	}
	public String getProjectCd() {
		return projectCd;
	}
	public void setProjectCd(String projectCd) {
		this.projectCd = projectCd;
	}
	
	public String getCreditMember() {
		return creditMember;
	}
	public void setCreditMember(String creditMember) {
		this.creditMember = creditMember;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getParentNm() {
		return parentNm;
	}
	public void setParentNm(String parentNm) {
		this.parentNm = parentNm;
	}
	public String getProductNm() {
		return productNm;
	}
	public void setProductNm(String productNm) {
		this.productNm = productNm;
	}
	public Integer getFloorAge() {
		return floorAge;
	}
	public void setFloorAge(Integer floorAge) {
		this.floorAge = floorAge;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public BigDecimal getOverdueAmount() {
		return overdueAmount;
	}
	public void setOverdueAmount(BigDecimal overdueAmount) {
		this.overdueAmount = overdueAmount;
	}
	public Long getOverdueDate() {
		return overdueDate;
	}
	public void setOverdueDate(Long overdueDate) {
		this.overdueDate = overdueDate;
	}
	public Integer getOverdueDays() {
		return overdueDays;
	}
	public void setOverdueDays(Integer overdueDays) {
		this.overdueDays = overdueDays;
	}
	public Long getLastDate() {
		return lastDate;
	}
	public void setLastDate(Long lastDate) {
		this.lastDate = lastDate;
	}
	public Boolean getIsDanger() {
		return isDanger;
	}
	public void setIsDanger(Boolean isDanger) {
		this.isDanger = isDanger;
	}
	public Integer getProjectStatus() {
		return projectStatus;
	}
	public void setProjectStatus(Integer projectStatus) {
		this.projectStatus = projectStatus;
	}
	
}
