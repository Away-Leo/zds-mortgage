package com.zdsoft.finance.casemanage.vo;

import java.math.BigDecimal;

import com.zdsoft.finance.common.base.BaseVo;
import com.zdsoft.finance.common.utils.VoUtil;
import com.zdsoft.finance.specialapprove.entity.FeeRules;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: FeeRulesVo.java 
 * @ClassName: FeeRulesVo 
 * @Description: TODO
 * @author jingjy 
 * @date 2017年2月14日 下午7:55:43 
 * @version V1.0
 */
public class FeeRulesVo extends BaseVo<FeeRules>{

	/**   
	 * @Fields serialVersionUID : TODO   
	 */ 
	private static final long serialVersionUID = 1L;
	
	
	/**
     * 案件ID
     */
    private String caseApplyId;
     
    /**
     * 收费对象
     */
    private String feePerson;
    
    /**
     * 收费项目
     */
    private String feeItem;
    private String feeItemName;
    
    /**
     * 应收金额
     */
    private BigDecimal expectedAmount;
    
    /**
     * 标准应收金额
     */
    private BigDecimal standardAmount;

    /**
     * 实收金额
     */
    private BigDecimal receivedAmount;
    
    /**
     * 特批状态
     */
    private String specialStatus;
    private String specialStatusName;

	public String getCaseApplyId() {
		return caseApplyId;
	}

	public void setCaseApplyId(String caseApplyId) {
		this.caseApplyId = caseApplyId;
	}

	public String getFeePerson() {
		return feePerson;
	}

	public void setFeePerson(String feePerson) {
		this.feePerson = feePerson;
	}

	public String getFeeItem() {
		return feeItem;
	}

	public void setFeeItem(String feeItem) {
		this.feeItem = feeItem;
	}

	public BigDecimal getExpectedAmount() {
		return expectedAmount;
	}

	public void setExpectedAmount(BigDecimal expectedAmount) {
		this.expectedAmount = expectedAmount;
	}

	public BigDecimal getReceivedAmount() {
		return receivedAmount;
	}

	public void setReceivedAmount(BigDecimal receivedAmount) {
		this.receivedAmount = receivedAmount;
	}

	public String getSpecialStatus() {
		return specialStatus;
	}

	public void setSpecialStatus(String specialStatus) {
		this.specialStatus = specialStatus;
	}

	public String getFeeItemName() {
		return feeItemName;
	}

	public void setFeeItemName(String feeItemName) {
		this.feeItemName = feeItemName;
	}

	public String getSpecialStatusName() {
		return specialStatusName;
	}

	public void setSpecialStatusName(String specialStatusName) {
		this.specialStatusName = specialStatusName;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
    public FeeRulesVo(FeeRules rules){
    	VoUtil.copyPoperties(rules, this, false,new String[]{""}, new String[]{"specialStatus","feeItem"});
    }
    
	public FeeRulesVo() {
		super();
	}

	public BigDecimal getStandardAmount() {
		return standardAmount;
	}

	public void setStandardAmount(BigDecimal standardAmount) {
		this.standardAmount = standardAmount;
	}
	
}
