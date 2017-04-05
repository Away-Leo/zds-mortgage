package com.zdsoft.finance.customer.entity;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 贷后个人客户信息
 * 版权所有：重庆正大华日软件有限公司
 * @Title: AfterPersonalCustomer.java 
 * @ClassName: AfterPersonalCustomer 
 * @Description: 
 * @author zhangchao 
 * @date 2017年2月6日 上午10:08:23 
 * @version V1.0
 */
@Entity
@Table(name = "cust_after_personal")
@DiscriminatorValue("PERS")
public class AfterPersonalCustomer extends AfterCustomer{

	/**
	 * 序列化
	 */
	private static final long serialVersionUID = 1L;

	/**
     * 曾用名
     */
    @Column(length = 128)
	private String formerName;
    
    /**
     * 出生日期(yyyyMMdd格式的long型)
     */
    @Column
	private Long birthdayDate;
    
    /**
     * 性别(303001：男。303002：女)
     */
    @Column(length = 20)
	private String gender;
    
    /**
     * 个人年收入
     */
    @Column(precision = 18, scale = 6)
	private String annualIncomeAmmount;
    
    /**
     * 婚姻状况(m0541;已婚；m0542;未婚；m0543;离异；)
     */
    @Column(length = 20)
	private String maritalStatus;
    
    /**
     * 职业类型(c01561:企业主；c01562：上班族；c01563：无业；)
     */
    @Column(length = 20)
	private String careerType;
    
    /**
     * 受教育程度(d01581：博士及博士后；d01582：硕士；d01583：本科；d01584：大专；d01585：高中/中专；d01586：初中；d01587：小学；)
     */
    @Column(length = 20)
	private String degree;
    
    /**
     * 居住年限(l0101:1年；l0101:1年；l0102:2年；l0103:3年；l0104:4年；l0105:5年；l0106:6年；l0107:7年；......l0111:10年以上；)
     */
    @Column(length = 20)
	private String liveAge;
    
    /**
     * 邮箱地址
     */
    @Column(length = 50)
	private String email;
    
    /**
     * 是否是实际用款人(YWDM0049002:是;YWDM0049001:否;)
     */
    @Column(length = 20)
	private String actualUsePerson;
    
    /**
     * 头像Id
     */
    @Column(length = 32)
	private String attachmentId;
    
    /**
     * 证件id
     */
    @Column(length = 32)
	private String credentialAttachmentId;
    
	public String getFormerName() {
		return formerName;
	}

	public void setFormerName(String formerName) {
		this.formerName = formerName;
	}

	public Long getBirthdayDate() {
		return birthdayDate;
	}

	public void setBirthdayDate(Long birthdayDate) {
		this.birthdayDate = birthdayDate;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getAnnualIncomeAmmount() {
		return annualIncomeAmmount;
	}

	public void setAnnualIncomeAmmount(String annualIncomeAmmount) {
		this.annualIncomeAmmount = annualIncomeAmmount;
	}

	public String getMaritalStatus() {
		return maritalStatus;
	}

	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	public String getCareerType() {
		return careerType;
	}

	public void setCareerType(String careerType) {
		this.careerType = careerType;
	}

	public String getDegree() {
		return degree;
	}

	public void setDegree(String degree) {
		this.degree = degree;
	}

	public String getLiveAge() {
		return liveAge;
	}

	public void setLiveAge(String liveAge) {
		this.liveAge = liveAge;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getActualUsePerson() {
		return actualUsePerson;
	}

	public void setActualUsePerson(String actualUsePerson) {
		this.actualUsePerson = actualUsePerson;
	}

	public String getAttachmentId() {
		return attachmentId;
	}

	public void setAttachmentId(String attachmentId) {
		this.attachmentId = attachmentId;
	}

	public String getCredentialAttachmentId() {
		return credentialAttachmentId;
	}

	public void setCredentialAttachmentId(String credentialAttachmentId) {
		this.credentialAttachmentId = credentialAttachmentId;
	}


	

	
    
}
