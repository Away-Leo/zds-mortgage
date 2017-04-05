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
 * 版权所有：重庆正大华日软件有限公司
 * @Title: ConFormatContractApply.java 
 * @ClassName: ConFormatContractApply 
 * @Description: 格式化合同申领
 * @author zhongyong 
 * @date 2017年3月7日 下午4:14:53 
 * @version V1.0
 */
@Entity
@Table(name = "con_format_contract_apply")
public class ConFormatContractApply extends BaseEntity {
	
	/**
	 * 未申请
	 */
	public static final String NOT_APPLY = "1";
	/**
	 * 已申请
	 */
	public static final String HAS_APPLIED = "2";
	/**
	 * 可领用
	 */
	public static final String CAN_RECEIVE = "3";
	/**
	 * 已领用
	 */
	public static final String HAS_RECEIVED = "4";

	
	private static final long serialVersionUID = 8161588229943880598L;

	/**
	 * 申请状态
	 */
	@Column(length = 20)
	private String applyStatus;
	
	/**
	 * 驻点寄出快递单号
	 */
	@Column(length = 32)
	private String trackingNoSend;
	
	/**
	 * 合同退回信托快递单号
	 */
	@Column(length = 32)
	private String trackingNoReceive;
	
	/**
	 * 备注
	 */
	@Column(length = 512)
	private String remark;
	
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
     * 申请编号
     */
    private transient String applyNo;
    
    /**
     * 申请时间
     */
    private transient Long applyDate;

	public String getApplyStatus() {
		return applyStatus;
	}

	public void setApplyStatus(String applyStatus) {
		this.applyStatus = applyStatus;
	}

	public String getTrackingNoSend() {
		return trackingNoSend;
	}

	public void setTrackingNoSend(String trackingNoSend) {
		this.trackingNoSend = trackingNoSend;
	}

	public String getTrackingNoReceive() {
		return trackingNoReceive;
	}

	public void setTrackingNoReceive(String trackingNoReceive) {
		this.trackingNoReceive = trackingNoReceive;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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

	public String getApplyNo() {
		return applyNo;
	}

	public void setApplyNo(String applyNo) {
		this.applyNo = applyNo;
	}

	public Long getApplyDate() {
		return applyDate;
	}

	public void setApplyDate(Long applyDate) {
		this.applyDate = applyDate;
	}
    

}
