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
 * @Title: SpecialApproveFee.java 
 * @ClassName: SpecialApproveFee 
 * @Description: 费用特批事项 
 * @author wangrongwei
 * @date 2017年2月21日 下午3:47:18 
 * @version V1.0 
 */ 
@Entity
@Table(name = "exp_fee_risk_things")
public class SpecialApproveFee extends BaseEntity {

    private static final long serialVersionUID = 2268499051441977064L;
    
    /**   
     * @Fields receiveFeeId : 收费ID   
     */ 
    @Column(length=32)
    private String receiveFeeId;
    
    /**   
     * @Fields specialApproveManage : 特批管理  
     */ 
    @ManyToOne(targetEntity=SpecialApproveManage.class)
    @JoinColumn(name="specialApproveId",referencedColumnName="id")
    private SpecialApproveManage specialApproveManage;
    
    /**   
     * @Fields expFeeRulesId : 费用规则ID   
     */ 
    @Column(length=32)
    private String expFeeRulesId;
    
    /**   
     * @Fields standardAmount : 标准应收金额   
     */ 
    @Column(precision = 18, scale = 2)
    private BigDecimal standardAmount = BigDecimal.ZERO;
    
    public SpecialApproveFee() {
	}

	public String getReceiveFeeId() {
		return receiveFeeId;
	}

	public void setReceiveFeeId(String receiveFeeId) {
		this.receiveFeeId = receiveFeeId;
	}

	public SpecialApproveManage getSpecialApproveManage() {
		return specialApproveManage;
	}

	public void setSpecialApproveManage(SpecialApproveManage specialApproveManage) {
		this.specialApproveManage = specialApproveManage;
	}

	public String getExpFeeRulesId() {
		return expFeeRulesId;
	}

	public void setExpFeeRulesId(String expFeeRulesId) {
		this.expFeeRulesId = expFeeRulesId;
	}

	public BigDecimal getStandardAmount() {
		return standardAmount;
	}

	public void setStandardAmount(BigDecimal standardAmount) {
		this.standardAmount = standardAmount;
	}
	
}
