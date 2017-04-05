package com.zdsoft.finance.contract.vo;

import com.zdsoft.finance.busiform.entity.BusiFormStatus;
import com.zdsoft.finance.common.base.BaseVo;
import com.zdsoft.finance.contract.entity.ConAgencyContractTpl;
import com.zdsoft.framework.core.common.util.DateHelper;
import com.zdsoft.framework.core.common.util.ObjectHelper;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title: ConAgencyContractTplVo.java 
 * @ClassName: ConAgencyContractTplVo 
 * @Description: 机构合同报备vo类
 * @author zhongyong 
 * @date 2017年2月28日 下午3:32:12 
 * @version V1.0
 */
public class ConAgencyContractTplVo extends BaseVo<ConAgencyContractTpl>{

	private static final long serialVersionUID = -8308621839080535788L;
	
	/**
	 * 申请人【基表】
	 */
	private String applyEmpCode;
	private String applyEmpNm;
	
	/**
	 * 申请时间
	 */
	private Long applyDate;
	
	/**
	 * 申请时间字符串
	 */
	private String applyDateStr;
	
	/**
	 * 状态【基表】
	 */
	private String applyStatus;
	
	/**
	 * 标题【基表】
	 */
	private String applyTitle;
	
	/**
	 * 申请编号【基表】
	 */
	private String applyNo;
	
	/**
	 * 申请机构
	 */
	private String applyOrgCode;
	
	/**
	 * 申请人部门
	 */
	private String applyDepartment;
	
	/**
	 * 申请类别
	 */
	private String applyType;
	private String applyTypeNm;
	
	/**
	 * 原因说明
	 */
	private String applyReason;
	
	/**
	 * 流程基础信息
	 */
	private String busiFormId ;
	
    /**
     * 是否启动流程
     */
    private Boolean isSubmit ;
    
    /**
     * 下一处理人
     */
    private String currentDealEmpNm ;

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

	public String getApplyStatus() {
		return applyStatus;
	}

	public void setApplyStatus(String applyStatus) {
		this.applyStatus = applyStatus;
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

	public String getBusiFormId() {
		return busiFormId;
	}

	public void setBusiFormId(String busiFormId) {
		this.busiFormId = busiFormId;
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
    
	public String getApplyEmpNm() {
		return applyEmpNm;
	}

	public void setApplyEmpNm(String applyEmpNm) {
		this.applyEmpNm = applyEmpNm;
	}

	public String getApplyTypeNm() {
		return applyTypeNm;
	}

	public void setApplyTypeNm(String applyTypeNm) {
		this.applyTypeNm = applyTypeNm;
	}
	
	public String getApplyDateStr() {
		return applyDateStr;
	}

	public void setApplyDateStr(String applyDateStr) {
		this.applyDateStr = applyDateStr;
	}

	public ConAgencyContractTplVo() {
		super();
	}

	public ConAgencyContractTplVo(ConAgencyContractTpl entity) {
		super(entity);
		if (ObjectHelper.isNotEmpty(entity.getBusiForm())) {
			this.setBusiFormId(entity.getBusiForm().getId());
			this.setApplyEmpCode(entity.getBusiForm().getLaunchEmpCode());
			this.setApplyStatus(BusiFormStatus.getName(entity.getBusiForm().getFormStatus()));
			this.setApplyTitle(entity.getBusiForm().getApplyTitle());
			this.setApplyNo(entity.getBusiForm().getBusinessCode());
			this.setApplyEmpNm(entity.getBusiForm().getLaunchEmpName());
			this.setApplyDate(entity.getBusiForm().getApplyDate());
			this.setApplyDateStr(DateHelper.longToDate(this.getApplyDate(), DateHelper.DATE_WITHMINUTE_FORMAT));
		}
	}

	public ConAgencyContractTpl toPo() {
		ConAgencyContractTpl entity = new ConAgencyContractTpl();
		super.toPo(this, entity);
		return entity;
	}



}
