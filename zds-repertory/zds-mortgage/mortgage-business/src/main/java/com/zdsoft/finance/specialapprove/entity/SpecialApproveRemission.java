package com.zdsoft.finance.specialapprove.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.zdsoft.framework.core.common.domain.BaseEntity;

/** 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: SpecialApproveRemission.java 
 * @ClassName: SpecialApproveRemission 
 * @Description: 费用减免
 * @author wangrongwei
 * @date 2017年2月27日 下午4:10:06 
 * @version V1.0 
 */ 
@Entity
@Table(name = "exp_fee_remission_details")
public class SpecialApproveRemission extends BaseEntity {

    private static final long serialVersionUID = 2268499051441977064L;
    
    /**   
     * @Fields annulAmount : 减免金额   
     */ 
    @Column
    private BigDecimal annulAmount;
    
    /**   
     * @Fields categoryCode : 减免项目Code
     */ 
    @Column(length=20)
    private String beRemissionItemCode;
    
    /**   
     * @Fields beRemissionItemName : 减免项目名称
     */ 
    @Column(length=128)
    private String beRemissionItemName;
    
    /** 
     * @Fields receiveAmount : 应收金额   
     */ 
    @Column
    private BigDecimal receivedAmount;
    
    /**   
     * @Fields expectedAmount : 实收金额   
     */ 
    @Column
    private BigDecimal expectedAmount;
    
	/**   
	 * @Fields penaltyUseStandard : 罚息挂钩标准
	 */ 
	@Column(length=20)
	private String penaltyUseStandard;
	
	/**   
	 * @Fields repaymentAmountId : 实收表ID   
	 */ 
	@Column(length=32)
	private String repaymentAmountId;
    
    /** 
     * @Fields specialApproveManage : 特批管理  
     */ 
    @ManyToOne(targetEntity=SpecialApproveManage.class)
    @JoinColumn(name="specialApproveId",referencedColumnName="id")
    private SpecialApproveManage specialApproveManage;
    
    public SpecialApproveRemission() {
	}

	public BigDecimal getAnnulAmount() {
		return annulAmount;
	}

	public void setAnnulAmount(BigDecimal annulAmount) {
		this.annulAmount = annulAmount;
	}

	public String getBeRemissionItemCode() {
		return beRemissionItemCode;
	}

	public void setBeRemissionItemCode(String beRemissionItemCode) {
		this.beRemissionItemCode = beRemissionItemCode;
	}

	public String getBeRemissionItemName() {
		return beRemissionItemName;
	}

	public void setBeRemissionItemName(String beRemissionItemName) {
		this.beRemissionItemName = beRemissionItemName;
	}

	public BigDecimal getReceivedAmount() {
		return receivedAmount;
	}

	public void setReceivedAmount(BigDecimal receivedAmount) {
		this.receivedAmount = receivedAmount;
	}

	public BigDecimal getExpectedAmount() {
		return expectedAmount;
	}

	public void setExpectedAmount(BigDecimal expectedAmount) {
		this.expectedAmount = expectedAmount;
	}

	public String getPenaltyUseStandard() {
		return penaltyUseStandard;
	}

	public void setPenaltyUseStandard(String penaltyUseStandard) {
		this.penaltyUseStandard = penaltyUseStandard;
	}

	public SpecialApproveManage getSpecialApproveManage() {
		return specialApproveManage;
	}

	public void setSpecialApproveManage(SpecialApproveManage specialApproveManage) {
		this.specialApproveManage = specialApproveManage;
	}

	public String getRepaymentAmountId() {
		return repaymentAmountId;
	}

	public void setRepaymentAmountId(String repaymentAmountId) {
		this.repaymentAmountId = repaymentAmountId;
	}
	
}
