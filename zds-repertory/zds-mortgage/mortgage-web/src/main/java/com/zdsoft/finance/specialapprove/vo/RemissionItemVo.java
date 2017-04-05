package com.zdsoft.finance.specialapprove.vo;

import java.math.BigDecimal;

import com.zdsoft.finance.common.base.BaseVo;
import com.zdsoft.finance.specialapprove.entity.SpecialApproveRemission;

public class RemissionItemVo extends BaseVo<SpecialApproveRemission> {
	
	/**   
	 * @Fields serialVersionUID :
	 */ 
	private static final long serialVersionUID = 1L;

	/**   
     * @Fields annulAmount : 减免金额   
     */ 
    private BigDecimal annulAmount;
    
    /**   
     * @Fields categoryCode : 减免项目Code
     */ 
    private String beRemissionItemCode;
    
    /**   
     * @Fields beRemissionItemName : 减免项目名称
     */ 
    private String beRemissionItemName;
    
    /** 
     * @Fields receiveAmount : 应收金额   
     */ 
    private BigDecimal receivedAmount;
    
    /**   
     * @Fields expectedAmount : 实收金额   
     */ 
    private BigDecimal expectedAmount;
    
	/**   
	 * @Fields penaltyUseStandard : 罚息挂钩标准
	 */ 
	private String penaltyUseStandard;
	
	/**   
	 * @Fields repaymentAmountId : 实收表ID   
	 */ 
	private String repaymentAmountId;
	
	public RemissionItemVo() {
	}
	public RemissionItemVo(SpecialApproveRemission po){
		super(po);
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
	
	public String getRepaymentAmountId() {
		return repaymentAmountId;
	}
	
	public void setRepaymentAmountId(String repaymentAmountId) {
		this.repaymentAmountId = repaymentAmountId;
	}
	
}
