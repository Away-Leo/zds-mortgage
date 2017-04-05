package com.zdsoft.finance.credit.vo;

import java.math.BigDecimal;

import com.zdsoft.finance.common.base.BaseVo;
import com.zdsoft.finance.credit.entity.MyCreditCard;

public class MyCreditCardVo extends BaseVo<MyCreditCard>{

	private static final long serialVersionUID = -6433352667806705170L;
	
    /**
     * 信用额度
     */
    private BigDecimal creditLimit ;
    
    /**
     * 账户状态Code
     */
    private String accountStatusCode ;
    
    /**
     * 账户状态name
     */
    private String accountStatusName ;
    
    /**
     * 当前逾期总额
     */
    private BigDecimal currentTotalOverdue ;
    
    /**
     * 当前透支额 
     */
    private BigDecimal currentOverdraft ;
    
    /**
     * 最12个月累计逾期次数
     */
    private Integer cumulativeOverdue12 ;
    
    /**
     * 最12个月最高逾期次数 
     */
    private Integer maximumOverdue12 ;
    
    /**
     * 备注 
     */
    private String remark ;
    
    /**
     * 征信综合情况id
     */
    private String creditSituationId ;
    
    public MyCreditCardVo(){
    	super();
    }
    
    public MyCreditCardVo(MyCreditCard entity){
    	super(entity);
    }

	public BigDecimal getCreditLimit() {
		return creditLimit;
	}

	public void setCreditLimit(BigDecimal creditLimit) {
		this.creditLimit = creditLimit;
	}

	public String getAccountStatusCode() {
		return accountStatusCode;
	}

	public void setAccountStatusCode(String accountStatusCode) {
		this.accountStatusCode = accountStatusCode;
	}

	public String getAccountStatusName() {
		return accountStatusName;
	}

	public void setAccountStatusName(String accountStatusName) {
		this.accountStatusName = accountStatusName;
	}

	public BigDecimal getCurrentTotalOverdue() {
		return currentTotalOverdue;
	}

	public void setCurrentTotalOverdue(BigDecimal currentTotalOverdue) {
		this.currentTotalOverdue = currentTotalOverdue;
	}

	public BigDecimal getCurrentOverdraft() {
		return currentOverdraft;
	}

	public void setCurrentOverdraft(BigDecimal currentOverdraft) {
		this.currentOverdraft = currentOverdraft;
	}

	public Integer getCumulativeOverdue12() {
		return cumulativeOverdue12;
	}

	public void setCumulativeOverdue12(Integer cumulativeOverdue12) {
		this.cumulativeOverdue12 = cumulativeOverdue12;
	}

	public Integer getMaximumOverdue12() {
		return maximumOverdue12;
	}

	public void setMaximumOverdue12(Integer maximumOverdue12) {
		this.maximumOverdue12 = maximumOverdue12;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getCreditSituationId() {
		return creditSituationId;
	}

	public void setCreditSituationId(String creditSituationId) {
		this.creditSituationId = creditSituationId;
	}
    
	public MyCreditCard toPO() {
		MyCreditCard po = new MyCreditCard();
		return super.toPo(this, po);
	}

}
