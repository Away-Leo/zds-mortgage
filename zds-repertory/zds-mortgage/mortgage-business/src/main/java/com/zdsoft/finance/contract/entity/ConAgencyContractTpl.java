package com.zdsoft.finance.contract.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.zdsoft.finance.busiform.entity.BusiForm;
import com.zdsoft.framework.core.common.domain.BaseEntity;
/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: ConAgencyContractTpl.java 
 * @ClassName: ConAgencyContractTpl 
 * @Description: 机构合同报备
 * @author zhongyong 
 * @date 2017年2月27日 上午10:59:25 
 * @version V1.0
 */
@Entity
@Table(name = "con_agency_contract_tpl")
public class ConAgencyContractTpl extends BaseEntity{

	private static final long serialVersionUID = 2350759829699886703L;
	
	/**
	 * 申请机构
	 */
	@Column(length = 32)
	private String applyOrgCode;
	
	/**
	 * 申请人部门
	 */
	@Column(length = 32)
	private String applyDepartment;
	
	/**
	 * 申请类别
	 */
	@Column(length = 32)
	private String applyType;
	
	/**
	 * 原因说明
	 */
	@Column(length = 512)
	private String applyReason;
	
	/**
	 * 流程基础信息
	 */
	@OneToOne(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	@JoinColumn(name = "busiFormId")
	private BusiForm busiForm ;
	
    /**
     * 是否启动流程
     */
    private transient Boolean isSubmit ;
    
    /**
     * 下一处理人
     */
    private transient String currentDealEmpNm ;
    
	/**
	 * 申请人【基表】
	 */
	private transient String applyEmpCode;
	
	/**
	 * 申请时间
	 */
	private transient Long applyDate;
	
	/**
	 * 标题【基表】
	 */
	private transient String applyTitle;
	
	/**
	 * 申请编号【基表】
	 */
	private transient String applyNo;

	public String getApplyOrgCode() {
		return applyOrgCode;
	}

	public void setApplyOrgCode(String applyOrgCode) {
		this.applyOrgCode = applyOrgCode;
	}

	public String getApplyDepartment() {
		return applyDepartment;
	}

	public void setApplyDepartment(String applyDepartment) {
		this.applyDepartment = applyDepartment;
	}

	public String getApplyType() {
		return applyType;
	}

	public void setApplyType(String applyType) {
		this.applyType = applyType;
	}

	public String getApplyReason() {
		return applyReason;
	}

	public void setApplyReason(String applyReason) {
		this.applyReason = applyReason;
	}

	public BusiForm getBusiForm() {
		return busiForm;
	}

	public void setBusiForm(BusiForm busiForm) {
		this.busiForm = busiForm;
	}

	public Boolean getIsSubmit() {
		return isSubmit;
	}

	public void setIsSubmit(Boolean isSubmit) {
		this.isSubmit = isSubmit;
	}

	public String getCurrentDealEmpNm() {
		return currentDealEmpNm;
	}

	public void setCurrentDealEmpNm(String currentDealEmpNm) {
		this.currentDealEmpNm = currentDealEmpNm;
	}

	public String getApplyEmpCode() {
		return applyEmpCode;
	}

	public void setApplyEmpCode(String applyEmpCode) {
		this.applyEmpCode = applyEmpCode;
	}

	public Long getApplyDate() {
		return applyDate;
	}

	public void setApplyDate(Long applyDate) {
		this.applyDate = applyDate;
	}

	public String getApplyTitle() {
		return applyTitle;
	}

	public void setApplyTitle(String applyTitle) {
		this.applyTitle = applyTitle;
	}

	public String getApplyNo() {
		return applyNo;
	}

	public void setApplyNo(String applyNo) {
		this.applyNo = applyNo;
	}
	
}
