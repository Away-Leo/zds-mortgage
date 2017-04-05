package com.zdsoft.finance.customer.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

/**
 * 版权所有：重庆正大华日软件有限公司
 * 
 * @Title:BeforePersonalCusomer.java
 * @Package:com.zdsoft.finance.customer.entity
 * @Description:贷前个人客户信息
 * @author: xj
 * @date:2017年1月11日 上午10:23:37
 * @version:v1.0
 */
@Entity
@Table(name = "cust_before_personal")
@DiscriminatorValue("PERS")
public class BeforePersonalCustomer extends BeforeCustomer {
	
    /**
     * 序列化
     */
    private static final long serialVersionUID = 42199098559332684L;
    
    /**
     * 曾用名
     */
    @Column(length = 128)
    private String formerName;
    
    /**
     * 出生日期
     */
    @Column
    private Long birthdayDate;
    
    /**
     * 性别
     */
    @Column(length = 20)
    private String gender;
    

    /**
     * 个人年收入
     */
    @Column(precision = 18, scale = 2)
    private BigDecimal annualIncomeAmmount = BigDecimal.ZERO;
    
    /**
     * 婚姻状况
     */
    @Column(length = 20)
    private String maritalStatus;
    
    /**
     * 职业类型
     */
    @Column(length = 20)
    private String careerType;
    
    /**
     * 受教育程度
     */
    @Column(length = 20)
    private String degree;
    
    /**
     * 居住年限
     */
    @Column(length = 20)
    private String liveAge;
    
    /**
     * 邮箱地址
     */
    @Column(length = 50)
    private String email;
    
    /**
     * 是否是实际用款人
     */
    @Column(length = 50)
    private String actualUsePerson;
    
    /**
     * 头像id
     */
    @Column(length = 32)
    private String attachmentId;
    
    /**
     * app上传的扫描证件图片(附件id)
     */
    @Column(length = 32)
    private String credentialAttachmentId;
    
    /**
     * 客户关联关系
     */
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "customerId")
    @LazyCollection(LazyCollectionOption.TRUE)
    private List<BeforePersonalAssociation> beforePersonalAssociations = new ArrayList<BeforePersonalAssociation>();
    
    /**
     * 单位
     */
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "customerId")
    @LazyCollection(LazyCollectionOption.TRUE)
    private List<BeforeWorkUnit> beforeWorkUnits = new ArrayList<BeforeWorkUnit>();
    
    /**
     * 与主借人关系（亲戚朋友同事）
     */
    @Transient
    private String relationship;

    /**
     * 与主借人关系名称 add by liyb
     */
    @Transient
    private String relationshipName;
    
    /**
     * 配偶id 用于app
     */
    @Transient
    private String spouseId;
    
    /**
     * 参与类型 共借人、担保人
     */
    @Transient
    private String joinType;

    /**
     * 参与类型 共借人、担保人名称 modify by liyb
     */
    @Transient
    private String joinTypeName;

    /**
     * 配偶
     */
    @Transient
    private BeforePersonalCustomer spouse;
    
    /**
     * 地址
     */
    @Transient
    private List<BeforeAddress> homeAddress;

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

    public BigDecimal getAnnualIncomeAmmount() {
        return annualIncomeAmmount;
    }

    public void setAnnualIncomeAmmount(BigDecimal annualIncomeAmmount) {
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

    public List<BeforePersonalAssociation> getBeforePersonalAssociations() {
        return beforePersonalAssociations;
    }

    public void setBeforePersonalAssociations(List<BeforePersonalAssociation> beforePersonalAssociations) {
        this.beforePersonalAssociations = beforePersonalAssociations;
    }

    public List<BeforeWorkUnit> getBeforeWorkUnits() {
        return beforeWorkUnits;
    }

    public void setBeforeWorkUnits(List<BeforeWorkUnit> beforeWorkUnits) {
        this.beforeWorkUnits = beforeWorkUnits;
    }

    public String getRelationship() {
        return relationship;
    }

    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }

    public String getRelationshipName() {
        return relationshipName;
    }

    public void setRelationshipName(String relationshipName) {
        this.relationshipName = relationshipName;
    }

    public BeforePersonalCustomer getSpouse() {
        return spouse;
    }

    public void setSpouse(BeforePersonalCustomer spouse) {
        this.spouse = spouse;
    }

    public String getJoinType() {
        return joinType;
    }

    public void setJoinType(String joinType) {
        this.joinType = joinType;
    }

    public String getJoinTypeName() {
        return joinTypeName;
    }

    public void setJoinTypeName(String joinTypeName) {
        this.joinTypeName = joinTypeName;
    }

    public String getSpouseId() {
        return spouseId;
    }

    public void setSpouseId(String spouseId) {
        this.spouseId = spouseId;
    }

    public List<BeforeAddress> getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(List<BeforeAddress> homeAddress) {
        this.homeAddress = homeAddress;
    }

}
