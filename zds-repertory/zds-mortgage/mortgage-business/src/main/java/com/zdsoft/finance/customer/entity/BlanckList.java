package com.zdsoft.finance.customer.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.IndexColumn;

import com.zdsoft.framework.core.common.domain.BaseEntity;



/**
 * 黑名单
 * 版权所有：重庆正大华日软件有限公司
 * @Title: BlanckList.java 
 * @ClassName: BlanckList 
 * @Description: 
 * @author zhangchao 
 * @date 2017年2月6日 上午10:16:59 
 * @version V1.0
 */
@Entity
@Table(name = "cust_blacklist")
public class BlanckList extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
     * 姓名
     */
    @Column(length = 128)
    private String blackName;
    
    /**
     * 证件类型
     */
    @Column(length = 20)
    private String credentiaType;
    
    /**
     * 证件号
     */
    @Column(length = 64)
    private String credentialNo;
    
    /**
     * 原因类型
     */
    @Column(length = 20)
    private String reasonType;
    
    /**
     * 公司代码
     */
    @Column(length = 32)
    private String companyCode;
    
    /**
     * 来源
     */
    @Column(length = 20)
    private String source;
    
    /**
     * 开始日期
     */
    @Column
    private Long startDate;
    
    /**
     * 结束日期
     */
    @Column
    private Long endDate;
    
    /**
     * 工作单位
     */
    @Column(length=128)
    private String workUnit;
    
    /**
     * 工作电话
     */
    @Column(length=11)
    private String workContact;
    
    /**
     * 家庭电话
     */
    @Column(length=11)
    private String familyContact;
    
    /**
     * 备注
     */
    @Column(length=500)
    private String remark;
    
    /**
     * 关联客户
     */
    @Column(length=32)
    private String relationCustomerId;

	public String getBlackName() {
		return blackName;
	}

	public void setBlackName(String blackName) {
		this.blackName = blackName;
	}

	public String getCredentiaType() {
		return credentiaType;
	}

	public void setCredentiaType(String credentiaType) {
		this.credentiaType = credentiaType;
	}

	public String getCredentialNo() {
		return credentialNo;
	}

	public void setCredentialNo(String credentialNo) {
		this.credentialNo = credentialNo;
	}

	public String getReasonType() {
		return reasonType;
	}

	public void setReasonType(String reasonType) {
		this.reasonType = reasonType;
	}

	public String getCompanyCode() {
		return companyCode;
	}

	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public Long getStartDate() {
		return startDate;
	}

	public void setStartDate(Long startDate) {
		this.startDate = startDate;
	}

	public Long getEndDate() {
		return endDate;
	}

	public void setEndDate(Long endDate) {
		this.endDate = endDate;
	}

	public String getWorkUnit() {
		return workUnit;
	}

	public void setWorkUnit(String workUnit) {
		this.workUnit = workUnit;
	}

	public String getWorkContact() {
		return workContact;
	}

	public void setWorkContact(String workContact) {
		this.workContact = workContact;
	}

	public String getFamilyContact() {
		return familyContact;
	}

	public void setFamilyContact(String familyContact) {
		this.familyContact = familyContact;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getRelationCustomerId() {
		return relationCustomerId;
	}

	public void setRelationCustomerId(String relationCustomerId) {
		this.relationCustomerId = relationCustomerId;
	}

}
