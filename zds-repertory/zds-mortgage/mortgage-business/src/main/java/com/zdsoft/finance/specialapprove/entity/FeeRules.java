package com.zdsoft.finance.specialapprove.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.zdsoft.framework.core.common.domain.BaseEntity;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: FeeRules.java 
 * @ClassName: FeeRules 
 * @Description: 费用规则明细
 * @author jingjy 
 * @date 2017年2月14日 下午5:26:09 
 * @version V1.0
 */
@Entity
@Table(name="exp_fee_rules")
public class FeeRules extends BaseEntity{

	/**   
	 * @Fields serialVersionUID :     
	 */ 
	private static final long serialVersionUID = 1L;
	
	
	/**
     * 案件ID
     */
    @Column(length = 32)
    private String caseApplyId;
     
    /**
     * 收费ID
     */
    @Column(length = 128)
    private String receiveFeeId;
    
    /**
     * 应收金额
     */
    @Column(precision = 18, scale = 2)
    private BigDecimal expectedAmount=BigDecimal.ZERO;

    /**
     * 标准应收金额
     */
    @Column(precision = 18, scale = 2)
    private BigDecimal standardAmount=BigDecimal.ZERO;
    
    /**
     * 特批状态
     */
    @Column(length = 20)
    private String specialStatus;
    
    public FeeRules() {
	}

	public String getCaseApplyId() {
		return caseApplyId;
	}

	public void setCaseApplyId(String caseApplyId) {
		this.caseApplyId = caseApplyId;
	}

	public String getReceiveFeeId() {
		return receiveFeeId;
	}

	public void setReceiveFeeId(String receiveFeeId) {
		this.receiveFeeId = receiveFeeId;
	}

	public BigDecimal getExpectedAmount() {
		return expectedAmount;
	}

	public void setExpectedAmount(BigDecimal expectedAmount) {
		this.expectedAmount = expectedAmount;
	}

	public BigDecimal getStandardAmount() {
		return standardAmount;
	}

	public void setStandardAmount(BigDecimal standardAmount) {
		this.standardAmount = standardAmount;
	}

	public String getSpecialStatus() {
		return specialStatus;
	}

	public void setSpecialStatus(String specialStatus) {
		this.specialStatus = specialStatus;
	}
    
}
