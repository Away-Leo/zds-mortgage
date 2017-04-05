package com.zdsoft.finance.customer.vo;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.zdsoft.finance.common.utils.TimeUtil;
import com.zdsoft.finance.common.utils.VoUtil;
import com.zdsoft.finance.customer.entity.BeforeAddress;
import com.zdsoft.finance.customer.entity.BeforePersonalCustomer;
import com.zdsoft.framework.core.common.util.ObjectHelper;

/**
 * 版权所有：重庆正大华日软件有限公司
 * 
 * @Title:BeforePersonalCustomerVo.java
 * @Package:com.zdsoft.finance.customer.vo
 * @Description:个人贷款贷款
 * @author: xj
 * @date:2017年1月12日 上午11:24:53
 * @version:v1.0
 */
public class BeforePersonalCustomerVo extends BeforeCustomerVo<BeforePersonalCustomer> {

    /**
     * 序列化
     */
    private static final long serialVersionUID = -3753965636594036636L;
    
    /**
     * 曾用名
     */
    private String formerName;
    
    /**
     * 出生日期
     */
    private Long birthdayDate;
    
    /**
     * 出生日期
     */
    private String birthdayDateStr;//modify by liuhuan 2017-1-20 出生日期显示格式

    /**
     * 性别
     */
    private String gender;

    /**
     * 性别名称 modify by liyb
     */
    private String genderName;
    
    /**
     * 个人年收入
     */
    private BigDecimal annualIncomeAmmount;
    
    /**
     * 婚姻状况
     */
    private String maritalStatus;

    /**
     * 婚姻状况名称 modify by liyb
     */
    private String maritalStatusName;
    
    /**
     * 职业类型code
     */
    private String careerType;
    
    /**
     * 职业类型name
     */
    private String careerTypeName;//modify by liuhuan 2017-1-20 职业类型名称
    
    /**
     * 受教育程度code
     */
    private String degree;
    
    /**
     * 受教育程度name
     */
    private String degreeName;//modify by liuhuan 2017-1-21 受教育程度名称
    
    /**
     * 居住年限code
     */
    private String liveAge;
    
    /**
     * 居住年限name
     */
    private String liveAgeName;//modify by liuhuan 2017-1-21
    
    /**
     * 邮箱地址
     */
    private String email;
    
    /**
     * 是否是实际用款人code
     */
    private String actualUsePerson;
    
    /**
     * 是否是实际用款人name
     */
    private String actualUsePersonName;//modify by liuhuan 2017-1-21
    
    /**
     * 头像id
     */
    private String attachmentId;
    
    /**
     * 头像url
     */

    private String headPortraitPath;
    
    /**
     * app上传的扫描证件图片(附件id)
     */
    private String credentialAttachmentId;

    /**
     * 与主借人关系（亲戚朋友同事配偶）
     */
    private String relationship;

    /**
     * 与主借人关系名称 add by liyb
     */
    private String relationshipName;
    
    /**
     * 参与类型 共借人、担保人
     */
    private String joinType;

    /**
     * 参与类型 共借人、担保人名称 modify by liyb
     */
    private String joinTypeName;

    /**
     * 配偶
     */
    private BeforePersonalCustomerVo spouseVo;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 家庭住址
     */
    private List<BeforeAddress> homeAddress;
    
    /**
     * 户籍地址 modify by jingjiyan
     */
    private String permanentAddress;

    public Integer getAge() {
        if (ObjectHelper.isNotEmpty(this.getBirthdayDate())) {
            Map<String, Object> map = TimeUtil.getMonthCha(this.getBirthdayDate().toString(),
                    TimeUtil.getCurrentDateInteger().toString());
            int month = Integer.parseInt(map.get("month").toString());
            age = month / 12;
        }
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public BeforePersonalCustomerVo() {
    }

    public BeforePersonalCustomerVo(BeforePersonalCustomer po) {
    	if (ObjectHelper.isNotEmpty(po)) {
    		VoUtil.copyPoperties(po, this, false, null, new String[]{"joinType","credentialType","gender","maritalStatus","careerType","degree","liveAge","actualUsePerson"}, new String[]{"birthdayDate|yyyy-MM-dd"});
    	}
    }
    
    public BeforePersonalCustomerVo(BeforePersonalCustomer po, String[] args, String[] simpleArgs) throws Exception {
        VoUtil.copyPoperties(po, this, false, args, simpleArgs);
    }

    public BeforePersonalCustomer toPO() {
        BeforePersonalCustomer po = new BeforePersonalCustomer();
        return super.toPo(this, po);
    }

    
    
    public String getActualUsePersonName() {
		return actualUsePersonName;
	}

	public void setActualUsePersonName(String actualUsePersonName) {
		this.actualUsePersonName = actualUsePersonName;
	}

	public String getLiveAgeName() {
		return liveAgeName;
	}

	public void setLiveAgeName(String liveAgeName) {
		this.liveAgeName = liveAgeName;
	}

	public String getDegreeName() {
		return degreeName;
	}

	public void setDegreeName(String degreeName) {
		this.degreeName = degreeName;
	}

	public String getCareerTypeName() {
		return careerTypeName;
	}

	public void setCareerTypeName(String careerTypeName) {
		this.careerTypeName = careerTypeName;
	}

	public String getBirthdayDateStr() {
		return birthdayDateStr;
	}

	public void setBirthdayDateStr(String birthdayDateStr) {
		this.birthdayDateStr = birthdayDateStr;
	}

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

    public String getRelationship() {
        return relationship;
    }

    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }

    public String getJoinType() {
        return joinType;
    }

    public void setJoinType(String joinType) {
        this.joinType = joinType;
    }

    public String getGenderName() {
        return genderName;
    }

    public void setGenderName(String genderName) {
        this.genderName = genderName;
    }

    public String getMaritalStatusName() {
        return maritalStatusName;
    }

    public void setMaritalStatusName(String maritalStatusName) {
        this.maritalStatusName = maritalStatusName;
    }

    public String getRelationshipName() {
        return relationshipName;
    }

    public void setRelationshipName(String relationshipName) {
        this.relationshipName = relationshipName;
    }

    public String getJoinTypeName() {
        return joinTypeName;
    }

    public void setJoinTypeName(String joinTypeName) {
        this.joinTypeName = joinTypeName;
    }

    public BeforePersonalCustomerVo getSpouseVo() {
        return spouseVo;
    }

    public void setSpouseVo(BeforePersonalCustomerVo spouseVo) {
        this.spouseVo = spouseVo;
    }

    public String getHeadPortraitPath() {
        return headPortraitPath;
    }

    public void setHeadPortraitPath(String headPortraitPath) {
        this.headPortraitPath = headPortraitPath;
    }

    public List<BeforeAddress> getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(List<BeforeAddress> homeAddress) {
        this.homeAddress = homeAddress;
    }

	public String getPermanentAddress() {
		return permanentAddress;
	}

	public void setPermanentAddress(String permanentAddress) {
		this.permanentAddress = permanentAddress;
	}

}
