package com.zdsoft.finance.casemanage.interview.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import com.zdsoft.finance.busiform.entity.BusiForm;
import com.zdsoft.framework.core.common.domain.BaseEntity;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: Interview.java 
 * @ClassName: Interview 
 * @Description: 案件面签信息实体
 * @author dengyy 
 * @date 2017年2月15日 上午9:42:31 
 * @version V1.0
 */
@Entity
@Table(name = "case_interview")
public class Interview extends BaseEntity{

	private static final long serialVersionUID = -1502693547850742457L;
	
	/**
	 * 流程代码
	 */
	public static final String proceessCode = "YWDM0012604"; 
	
	/**
	 * 未面签
	 */
	public static final String SIMPLECODE_ONE = "YWDM0051077"; 
	
	/**
	 * 已面签
	 */
	public static final String PROCEESSCODE_TWO = "YWDM0051078"; 
	
	/**
	 * 案件id
	 */
	@Column(length=32)
	private String caseApplyId ;
	
	/**
	 * 预计强制执行公证办理时间 
	 */
	@Column(length=16)
	private String compulsoryNotaryDate ;
	
	/**
	 * 是否仲裁 
	 */
	@Column(length=1)
	@Type(type="true_false")
	private Boolean isArbitrate ;
	
	/**
	 * 第三方还款授权
	 */
	@Column(length=1)
    @Type(type="true_false")
	private Boolean isThirdPartyRepayAccredit ;
	
	
	/**
	 * 第三方还款授权人id 保前的客户id
	 */
	@Column(length=32)
	private String thirdPartyRepayAccreditId ;
	
	/**
	 * 第三方还款授权人姓名 
	 */
	@Column(length=64)
	private String thirdPartyRepayAccreditName ;
	
	/**
	 * 流程基础信息
	 */
	@OneToOne(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	@JoinColumn(name = "busiFormId")
	private BusiForm busiForm ;
	
	/**
     * 面签状态(0.未面签(默认);1.已面签)
     */
    @Column(length = 20)
    private String interviewStatus;
    
    /**
     * 面签时间
     */
    @Column(length=16)
    private Long actualInterviewDate ;
    
    /**
     * 案件房产信息 数据 json
     */
    private transient String houseData ;
    
    /**
     * 是否启动流程
     */
    private transient Boolean isSubmit ;
    
    /**
     * 下一处理人
     */
    private transient String currentDealEmpNm ;

    public String getCaseApplyId() {
        return caseApplyId;
    }

    public void setCaseApplyId(String caseApplyId) {
        this.caseApplyId = caseApplyId;
    }

    public String getCompulsoryNotaryDate() {
        return compulsoryNotaryDate;
    }

    public void setCompulsoryNotaryDate(String compulsoryNotaryDate) {
        this.compulsoryNotaryDate = compulsoryNotaryDate;
    }

    public Boolean getIsArbitrate() {
        return isArbitrate;
    }

    public void setIsArbitrate(Boolean isArbitrate) {
        this.isArbitrate = isArbitrate;
    }

    public Boolean getIsThirdPartyRepayAccredit() {
        return isThirdPartyRepayAccredit;
    }

    public void setIsThirdPartyRepayAccredit(Boolean isThirdPartyRepayAccredit) {
        this.isThirdPartyRepayAccredit = isThirdPartyRepayAccredit;
    }

    public String getThirdPartyRepayAccreditName() {
        return thirdPartyRepayAccreditName;
    }

    public void setThirdPartyRepayAccreditName(String thirdPartyRepayAccreditName) {
        this.thirdPartyRepayAccreditName = thirdPartyRepayAccreditName;
    }

    public BusiForm getBusiForm() {
        return busiForm;
    }

    public void setBusiForm(BusiForm busiForm) {
        this.busiForm = busiForm;
    }

    public String getInterviewStatus() {
        return interviewStatus;
    }

    public void setInterviewStatus(String interviewStatus) {
        this.interviewStatus = interviewStatus;
    }

    public String getHouseData() {
        return houseData;
    }

    public void setHouseData(String houseData) {
        this.houseData = houseData;
    }

    public Boolean getIsSubmit() {
        return isSubmit;
    }

    public void setIsSubmit(Boolean isSubmit) {
        this.isSubmit = isSubmit;
    }

    public Long getActualInterviewDate() {
        return actualInterviewDate;
    }

    public void setActualInterviewDate(Long actualInterviewDate) {
        this.actualInterviewDate = actualInterviewDate;
    }

    public String getCurrentDealEmpNm() {
        return currentDealEmpNm;
    }

    public void setCurrentDealEmpNm(String currentDealEmpNm) {
        this.currentDealEmpNm = currentDealEmpNm;
    }

    public String getThirdPartyRepayAccreditId() {
        return thirdPartyRepayAccreditId;
    }

    public void setThirdPartyRepayAccreditId(String thirdPartyRepayAccreditId) {
        this.thirdPartyRepayAccreditId = thirdPartyRepayAccreditId;
    }
	
}
