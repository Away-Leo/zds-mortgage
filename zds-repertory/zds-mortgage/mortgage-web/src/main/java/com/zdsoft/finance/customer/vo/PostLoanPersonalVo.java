package com.zdsoft.finance.customer.vo;

import com.zdsoft.finance.customer.entity.PostLoanPersonal;

/**
 * 贷后个人客户信息
 * @author <a href="mailto:502549751@qq.com">zhangchao</a>
 * @createTime: 2017-01-10 14::41
 * @version: 1.0
 *
 */
public class PostLoanPersonalVo extends PostLoanCustomerVo{


	/**
	 * 序列化
	 */
	private static final long serialVersionUID = 1L;

	/**
     * 曾用名
     */
	private String formerName;
    
    /**
     * 出生日期(yyyyMMdd格式的long型)
     */
	private Long birthdayDate;
    
    /**
     * 性别(g01561：男。g01562：女)
     */
	private String gender;
    
    /**
     * 个人年收入
     */
	private String annualIncomeAmmount;
    
    /**
     * 婚姻状况(simplecode维护，保存的是fullcode，m0541;已婚；m0542;未婚；m0543;离异；)
     */
	private String maritalStatus;
    
    /**
     * 职业类型(simplecode维护，保存的是fullcode，c01561:企业主；c01562：上班族；c01563：无业；)
     */
	private String careerType;
    
    /**
     * 受教育程度(simplecode维护，保存的是fullcode，d01581：博士及博士后；d01582：硕士；d01583：本科；d01584：大专；d01585：高中/中专；d01586：初中；d01587：小学；)
     */
	private String degree;
    
    /**
     * 居住年限(simplecode维护，保存的是fullcode，l0101:1年；l0101:1年；l0102:2年；l0103:3年；l0104:4年；l0105:5年；l0106:6年；l0107:7年；......l0111:10年以上；)
     */
	private String liveAge;
    
    /**
     * 邮箱地址
     */
	private String email;
    
    /**
     * 是否是实际用款人(通过simplecode维护，保存fullcode，t01021:是;t01022:否;)
     */
	private String actualUsePerson;
    
    /**
     * 头像Id
     */
	private String attachmentId;
    
    /**
     * 证件id
     */
	private String credentialAttachmentId;
	
	/**
	 * 家庭地址
	 */
	private String address;

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
    
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public PostLoanPersonalVo(){}
	
	public PostLoanPersonalVo(PostLoanPersonal po){
		super(po);
	}
	
	public PostLoanPersonal toPO(){
		PostLoanPersonal po = new PostLoanPersonal();
		return (PostLoanPersonal) super.toPo(this, po);
	}

}
