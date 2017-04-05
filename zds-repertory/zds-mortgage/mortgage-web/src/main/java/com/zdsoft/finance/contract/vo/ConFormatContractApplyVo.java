package com.zdsoft.finance.contract.vo;

import com.zdsoft.finance.common.base.BaseVo;
import com.zdsoft.finance.contract.entity.ConFormatContractApply;
import com.zdsoft.framework.core.common.util.ObjectHelper;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title: ConFormatContractApplyVo.java 
 * @ClassName: ConFormatContractApplyVo 
 * @Description: 格式化合同申领
 * @author zhongyong 
 * @date 2017年3月8日 上午10:21:48 
 * @version V1.0
 */

public class ConFormatContractApplyVo extends BaseVo<ConFormatContractApply> {
	
	private static final long serialVersionUID = 8161588229943880598L;

	/**
	 * 申请状态
	 */
	private String applyStatus;
	
	/**
	 * 申请状态名称
	 */
	private String applyStatusNm;
	
	/**
	 * 驻点寄出快递单号
	 */
	private String trackingNoSend;
	
	/**
	 * 合同退回信托快递单号
	 */
	private String trackingNoReceive;
	
	/**
	 * 备注
	 */
	private String remark;
	
	/**
	 * 流程基础信息
	 */
	private String busiFormId;
	
    /**
     * 是否启动流程
     */
    private Boolean isSubmit;
    
    /**
     * 下一处理人
     */
    private String currentDealEmpNm ;

	/**
	 * 申请人【基表】
	 */
	private String applyEmpCode;
	
	/**
	 * 申请时间
	 */
	private Long applyDate;
	
	/**
	 * 申请编号【基表】
	 */
	private String applyNo;

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

	public String getApplyNo() {
		return applyNo;
	}

	public void setApplyNo(String applyNo) {
		this.applyNo = applyNo;
	}
    
	public String getApplyStatusNm() {
		return applyStatusNm;
	}

	public void setApplyStatusNm(String applyStatusNm) {
		this.applyStatusNm = applyStatusNm;
	}

	
	public ConFormatContractApplyVo() {
		super();
	}

	public ConFormatContractApplyVo(ConFormatContractApply entity) {
		super(entity);
		if (ObjectHelper.isNotEmpty(entity.getBusiForm())) {
			this.setBusiFormId(entity.getBusiForm().getId());
			this.setApplyEmpCode(entity.getBusiForm().getLaunchEmpCode());
			this.setApplyNo(entity.getBusiForm().getBusinessCode());
			this.setApplyDate(entity.getBusiForm().getApplyDate());
			if (ConFormatContractApply.NOT_APPLY.equals(entity.getApplyStatus())) {
				this.setApplyStatusNm("未申请");
			} else if (ConFormatContractApply.HAS_APPLIED.equals(entity.getApplyStatus())) {
				this.setApplyStatusNm("已申请");
			} else if (ConFormatContractApply.CAN_RECEIVE.equals(entity.getApplyStatus())) {
				this.setApplyStatusNm("可领用");
			} else if (ConFormatContractApply.HAS_RECEIVED.equals(entity.getApplyStatus())) {
				this.setApplyStatusNm("已领用");
			}
		}
	}

	public ConFormatContractApply toPo() {
		ConFormatContractApply entity = new ConFormatContractApply();
		super.toPo(this, entity);
		return entity;
	}


}
