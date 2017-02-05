package com.zdsoft.finance.customer.entity;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.zdsoft.framework.core.common.domain.BaseEntity;


/**
 * 客户基类
 * @author zhangchao
 * 2016/12/21
 */
@Entity
@Table(name = "zf_customer")
public class Customer extends BaseEntity {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * 姓名
     */
    @Column(length = 64)
    private String clientNm;
    
    /**
     * 曾用名
     */
    @Column(length = 64)
    private String formerNm;
    
    /**
     * 证件类型
     */
    @Column(length = 15)
    private String credentiaType;
    
    /**
     * 身份证号码
     */
    @Column(length = 18)
    private String credentialNo;
    
    /**
     * 参与类型
     */
    @Column(length = 15)
    private String attendType;
    
    /**
     * 生日（yyyy-MM-dd）
     */
    @Column
    private long birthday;
    
    /**
     * 性别
     */
    @Column(length = 15)
    private String gender;
    
    /**
     * 婚姻状况
     */
    @Column(length = 15)
    private String marital;
    
    /**
     * 配偶id
     */
    @Column(length = 32)
    private String clientId;
    
    /**
     * 职业类型
     */
    @Column(length = 15)
    private String careerType;
    
    /**
     * 个人年收入
     */
    @Column(precision = 30, scale = 12)
    private BigDecimal annualIncome;
    
    /**
     * 受教育程度
     */
    @Column(length = 15)
    private String degree;
    
    /**
     * 居住年限
     */
    @Column(length = 15)
    private String liveAge;
    
    /**
     * 邮箱地址
     */
    @Column(length = 32)
    private String email;
    
    /**
     * 是否实际用款人
     */
    @Column
    private Boolean isActualUsePerson;
    
    /**
     * 省（家庭地址）
     */
    @Column(length = 15)
    private String liveProvince;
    
    /**
     * 市（家庭地址）
     */
    @Column(length = 15)
    private String liveCity;
    
    /**
     * 区（家庭地址）
     */
    @Column(length = 15)
    private String liveDistrict;
    
    /**
     * 详细地址（家庭地址）
     */
    @Column(length = 200)
    private String liveAddress;
    
    /**
     * 省（户籍地址）
     */
    @Column(length = 15)
    private String domicileProvince;
    
    /**
     * 市（户籍地址）
     */
    @Column(length = 15)
    private String domicileCity;
    
    /**
     * 区（户籍地址）
     */
    @Column(length = 15)
    private String domicileDistrict;
    
    /**
     * 详细地址（户籍地址）
     */
    @Column(length = 200)
    private String domicileAddress;
    
    /**
     * 创建人姓名
     */
    @Column(length = 64)
    private String creatorName;
    
    /**
     * 公司代码
     */
    @Column(length = 32)
    private String companyCode;
    
    /**
     * 公司名称
     */
    @Column(length = 64)
    private String companynName;
    
    /**
     * 业务部门代码
     */
    @Column(length = 32)
    private String businessDeptCode;
    
    /**
     * 业务部门名称
     */
    @Column(length = 64)
    private String businessDeptName;
    
    /**
     * 客户创建类型（如与项目相关联、配偶、最新客户信息）客户创建类型（如与项目相关联、配偶、最新客户信息）
     */
    @Column
    private int createType;
    
    /**
     * 客户状态(不用于逻辑判断，只用于历史显示)
     */
    @Column
    private int customerStatus;
    
    /**
     * 客户头像
     */
    @Column(length = 255)
    private String imgId;
    
    /**
     * 联系方式
     */
    @OneToMany(mappedBy = "customer")
    private List<Contact> contacts;
    
    /**
     * 工作单位
     */
    @OneToMany(mappedBy = "customer")
    private List<WorkUnits> workUnitss;
    
    /**
     * 客户关联关系
     */
    @ManyToMany
    @JoinTable(name = "cust_Association")
    private List<CrmAssociation> crmAssociations;

	public String getClientNm() {
		return clientNm;
	}

	public void setClientNm(String clientNm) {
		this.clientNm = clientNm;
	}

	public String getFormerNm() {
		return formerNm;
	}

	public void setFormerNm(String formerNm) {
		this.formerNm = formerNm;
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

	public String getAttendType() {
		return attendType;
	}

	public void setAttendType(String attendType) {
		this.attendType = attendType;
	}

	public long getBirthday() {
		return birthday;
	}

	public void setBirthday(long birthday) {
		this.birthday = birthday;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getMarital() {
		return marital;
	}

	public void setMarital(String marital) {
		this.marital = marital;
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getCareerType() {
		return careerType;
	}

	public void setCareerType(String careerType) {
		this.careerType = careerType;
	}

	public BigDecimal getAnnualIncome() {
		return annualIncome;
	}

	public void setAnnualIncome(BigDecimal annualIncome) {
		this.annualIncome = annualIncome;
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

	public Boolean getIsActualUsePerson() {
		return isActualUsePerson;
	}

	public void setIsActualUsePerson(Boolean isActualUsePerson) {
		this.isActualUsePerson = isActualUsePerson;
	}

	public String getLiveProvince() {
		return liveProvince;
	}

	public void setLiveProvince(String liveProvince) {
		this.liveProvince = liveProvince;
	}

	public String getLiveCity() {
		return liveCity;
	}

	public void setLiveCity(String liveCity) {
		this.liveCity = liveCity;
	}

	public String getLiveDistrict() {
		return liveDistrict;
	}

	public void setLiveDistrict(String liveDistrict) {
		this.liveDistrict = liveDistrict;
	}

	public String getLiveAddress() {
		return liveAddress;
	}

	public void setLiveAddress(String liveAddress) {
		this.liveAddress = liveAddress;
	}

	public String getDomicileProvince() {
		return domicileProvince;
	}

	public void setDomicileProvince(String domicileProvince) {
		this.domicileProvince = domicileProvince;
	}

	public String getDomicileCity() {
		return domicileCity;
	}

	public void setDomicileCity(String domicileCity) {
		this.domicileCity = domicileCity;
	}

	public String getDomicileDistrict() {
		return domicileDistrict;
	}

	public void setDomicileDistrict(String domicileDistrict) {
		this.domicileDistrict = domicileDistrict;
	}

	public String getDomicileAddress() {
		return domicileAddress;
	}

	public void setDomicileAddress(String domicileAddress) {
		this.domicileAddress = domicileAddress;
	}

	public String getCreatorName() {
		return creatorName;
	}

	public void setCreatorName(String creatorName) {
		this.creatorName = creatorName;
	}

	public String getCompanyCode() {
		return companyCode;
	}

	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}

	public String getCompanynName() {
		return companynName;
	}

	public void setCompanynName(String companynName) {
		this.companynName = companynName;
	}

	public String getBusinessDeptCode() {
		return businessDeptCode;
	}

	public void setBusinessDeptCode(String businessDeptCode) {
		this.businessDeptCode = businessDeptCode;
	}

	public String getBusinessDeptName() {
		return businessDeptName;
	}

	public void setBusinessDeptName(String businessDeptName) {
		this.businessDeptName = businessDeptName;
	}

	public int getCreateType() {
		return createType;
	}

	public void setCreateType(int createType) {
		this.createType = createType;
	}

	public int getCustomerStatus() {
		return customerStatus;
	}

	public void setCustomerStatus(int customerStatus) {
		this.customerStatus = customerStatus;
	}

	public String getImgId() {
		return imgId;
	}

	public void setImgId(String imgId) {
		this.imgId = imgId;
	}

	public List<Contact> getContacts() {
		return contacts;
	}

	public void setContacts(List<Contact> contacts) {
		this.contacts = contacts;
	}

	public List<WorkUnits> getWorkUnitss() {
		return workUnitss;
	}

	public void setWorkUnitss(List<WorkUnits> workUnitss) {
		this.workUnitss = workUnitss;
	}

	public List<CrmAssociation> getCrmAssociations() {
		return crmAssociations;
	}

	public void setCrmAssociations(List<CrmAssociation> crmAssociations) {
		this.crmAssociations = crmAssociations;
	}

}
