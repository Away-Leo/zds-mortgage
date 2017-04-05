package com.zdsoft.finance.credit.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.zdsoft.framework.core.common.domain.BaseEntity;

/**
 * 版权所有：重庆正大华日软件有限公司
 * 
 * @Title: HmIdentityInfo.java
 * @ClassName: HmIdentityInfo
 * @Description: 个人基本信息
 * @author gufeng
 * @date 2017年2月23日 上午9:39:37
 * @version V1.0
 */
@Entity
@Table(name = "HM_Identity_Info")
public class HmIdentityInfo extends BaseEntity {

	private static final long serialVersionUID = 5934271110244042674L;
	/**
	 * 查询表ID
	 */
	@Column(name = "query_Id")
	private String queryId;
	/**
	 * 身份证号码
	 */
	@Column(name = "id_card")
	private String idCard;
	/**
	 * 创建时间
	 */
	@Column(name = "create_time")
	private Timestamp createTime;

	/**
	 * 性别
	 */
	@Column(name = "Gender")
	private String gender;
	/**
	 * 生日
	 */
	@Column(name = "Date_Of_Birth")
	private String dateOfBirth;
	/**
	 * 婚姻状况
	 */
	@Column(name = "Marital_Status")
	private String maritalStatus;
	/**
	 * 手机号码
	 */
	@Column(name = "Mobile_Phone")
	private String mobilePhone;
	/**
	 * 单位电话
	 */
	@Column(name = "Company_Phone")
	private String companyPhone;
	/**
	 * 住宅电话
	 */
	@Column(name = "Home_Phone")
	private String homePhone;
	/**
	 * 学历
	 */
	@Column(name = "Education")
	private String education;
	/**
	 * 学位
	 */
	@Column(name = "Degree")
	private String degree;
	/**
	 * 通讯地址
	 */
	@Column(name = "Contact_Address")
	private String contactAddress;
	/**
	 * 户籍地址
	 */
	@Column(name = "Residence_Address")
	private String residenceAddress;

	public String getQueryId() {
		return queryId;
	}

	public void setQueryId(String queryId) {
		this.queryId = queryId;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getMaritalStatus() {
		return maritalStatus;
	}

	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public String getCompanyPhone() {
		return companyPhone;
	}

	public void setCompanyPhone(String companyPhone) {
		this.companyPhone = companyPhone;
	}

	public String getHomePhone() {
		return homePhone;
	}

	public void setHomePhone(String homePhone) {
		this.homePhone = homePhone;
	}

	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	public String getDegree() {
		return degree;
	}

	public void setDegree(String degree) {
		this.degree = degree;
	}

	public String getContactAddress() {
		return contactAddress;
	}

	public void setContactAddress(String contactAddress) {
		this.contactAddress = contactAddress;
	}

	public String getResidenceAddress() {
		return residenceAddress;
	}

	public void setResidenceAddress(String residenceAddress) {
		this.residenceAddress = residenceAddress;
	}

}
