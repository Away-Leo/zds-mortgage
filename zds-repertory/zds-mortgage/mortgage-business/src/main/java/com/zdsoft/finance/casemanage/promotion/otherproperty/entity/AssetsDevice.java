package com.zdsoft.finance.casemanage.promotion.otherproperty.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.zdsoft.framework.core.common.domain.BaseEntity;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title:AssetsDevice.java
 * @Package:com.zdsoft.finance.casemanage.promotion.otherproperty.entity
 * @Description:其他资产之设备请款
 * @author: xiongpan
 * @date:2017年2月22日 下午2:02:05
 * @version:v1.0
 */
@Entity
@Table(name = "case_other_assets_device")
public class AssetsDevice extends BaseEntity{

	private static final long serialVersionUID = 7611478421654070175L;
	
	/**
	 * 设备名称
	 */
	@Column(length=50)
	private String deviceName;
	
	/**
	 * 设备发票出具日期
	 */
	@Column
	private Long invoiceDate;
	
	/**
	 * 权属人
	 */
	@Column(length=32)
	private String ownerName;
	
	/**
	 * 是否在押
	 */
	@Column(length=20)
	private String isPledge;
	
	/**
	 * 抵押金额(元)
	 */
	@Column(precision = 18, scale = 2)
	private BigDecimal pledgeAmount = BigDecimal.ZERO;
	
	/**
	 * 设备内部估价
	 */
	@Column(precision = 18, scale = 2)
	private BigDecimal evaluationAmount = BigDecimal.ZERO;
	
	/**
	 * 地址(省)
	 */
	@Column(length=30)
	private String deviceProvince;
	
	/**
	 * 地址(市)
	 */
	@Column(length=30)
	private String deviceCity;
	
	/**
	 * 地址(区)
	 */
	@Column(length=30)
	private String deviceDistrict;
	
	/**
	 * 详细地址
	 */
	@Column(length=128)
	private String detailAddress;
	
	/**
	 * 对应的案件ID
	 */
	@Column(length=32)
	private String caseApplyId;

	public String getDeviceName() {
		return deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	public Long getInvoiceDate() {
		return invoiceDate;
	}

	public void setInvoiceDate(Long invoiceDate) {
		this.invoiceDate = invoiceDate;
	}

	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	public String getIsPledge() {
		return isPledge;
	}

	public void setIsPledge(String isPledge) {
		this.isPledge = isPledge;
	}

	public BigDecimal getPledgeAmount() {
		return pledgeAmount;
	}

	public void setPledgeAmount(BigDecimal pledgeAmount) {
		this.pledgeAmount = pledgeAmount;
	}

	public BigDecimal getEvaluationAmount() {
		return evaluationAmount;
	}

	public void setEvaluationAmount(BigDecimal evaluationAmount) {
		this.evaluationAmount = evaluationAmount;
	}

	public String getDeviceProvince() {
		return deviceProvince;
	}

	public void setDeviceProvince(String deviceProvince) {
		this.deviceProvince = deviceProvince;
	}

	public String getDeviceCity() {
		return deviceCity;
	}

	public void setDeviceCity(String deviceCity) {
		this.deviceCity = deviceCity;
	}

	public String getDeviceDistrict() {
		return deviceDistrict;
	}

	public void setDeviceDistrict(String deviceDistrict) {
		this.deviceDistrict = deviceDistrict;
	}

	public String getDetailAddress() {
		return detailAddress;
	}

	public void setDetailAddress(String detailAddress) {
		this.detailAddress = detailAddress;
	}

	public String getCaseApplyId() {
		return caseApplyId;
	}

	public void setCaseApplyId(String caseApplyId) {
		this.caseApplyId = caseApplyId;
	}
	
	
	

}
