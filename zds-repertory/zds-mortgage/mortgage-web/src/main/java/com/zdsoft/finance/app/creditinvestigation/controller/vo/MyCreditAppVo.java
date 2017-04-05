package com.zdsoft.finance.app.creditinvestigation.controller.vo;

import java.io.Serializable;
import java.math.BigDecimal;

import com.zdsoft.finance.credit.entity.MyCredit;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title: MyCreditAppVo.java 
 * @ClassName: MyCreditAppVo 
 * @Description: 基于MyCredit实体封装app用vo对象
 * @author panshm 
 * @date 2017年3月4日 下午1:54:19 
 * @version V1.0
 */
public class MyCreditAppVo implements Serializable {

	private static final long serialVersionUID = 1L;
	
    /**
     * 贷款种类name
     */
    private String loanTypesName ;
    
    /**
     * 账户状态name
     */
    private String accountStatusName ;
    
    /**
     * 贷款发放日期 
     */
    private Long loanDate ;
    
    /**
     * 贷款年限 
     */
    private Integer loanTerm ;
    
    /**
     * 贷款金额  
     */
    private BigDecimal loanAmount ;
    
    /**
     * 贷款余额  
     */
    private BigDecimal loanBalance ;
    
    /**
     * 当前逾期期数
     */
    private Integer currentOverdue ;
    
    /**
     * 当前逾期金额 
     */
    private BigDecimal currentOverdueAmount ;
    
    /**
     * 累计逾期期数
     */
    private Integer cumulativeOverdue ;
    
    /**
     * 最高逾期期数  maximumOverdue
     */
    private Integer maximumOverdue ;
    
    /**
     * 最12个月累计逾期次数
     */
    private Integer cumulativeOverdue12 ;
    
    /**
     * 最12个月最高逾期次数
     */
    private Integer maximumOverdue12 ;
    
    /**
     * 结清日期
     */
    private Long closingDate ;
    
    /**
     * 将实体转成本构造函数
     * @param entity MyCredit实体
     */
    public MyCreditAppVo(MyCredit entity){
    	this.loanTypesName = entity.getLoanTypesName();
    	this.accountStatusName = entity.getAccountStatusName();
    	this.loanDate = entity.getLoanDate();
    	this.loanTerm = entity.getLoanTerm();
    	this.loanAmount = entity.getLoanAmount();
    	this.loanBalance = entity.getLoanBalance();
    	this.currentOverdue = entity.getCurrentOverdue();
    	this.currentOverdueAmount = entity.getCurrentOverdueAmount();
    	this.cumulativeOverdue = entity.getCumulativeOverdue();
    	this.maximumOverdue = entity.getMaximumOverdue();
    	this.cumulativeOverdue12 = entity.getCumulativeOverdue12();
    	this.maximumOverdue12 = entity.getMaximumOverdue12();
    	this.closingDate = entity.getClosingDate();
    }

	public String getLoanTypesName() {
		return loanTypesName;
	}

	public void setLoanTypesName(String loanTypesName) {
		this.loanTypesName = loanTypesName;
	}

	public String getAccountStatusName() {
		return accountStatusName;
	}

	public void setAccountStatusName(String accountStatusName) {
		this.accountStatusName = accountStatusName;
	}

	public Long getLoanDate() {
		return loanDate;
	}

	public void setLoanDate(Long loanDate) {
		this.loanDate = loanDate;
	}

	public Integer getLoanTerm() {
		return loanTerm;
	}

	public void setLoanTerm(Integer loanTerm) {
		this.loanTerm = loanTerm;
	}

	public BigDecimal getLoanAmount() {
		return loanAmount;
	}

	public void setLoanAmount(BigDecimal loanAmount) {
		this.loanAmount = loanAmount;
	}

	public BigDecimal getLoanBalance() {
		return loanBalance;
	}

	public void setLoanBalance(BigDecimal loanBalance) {
		this.loanBalance = loanBalance;
	}

	public Integer getCurrentOverdue() {
		return currentOverdue;
	}

	public void setCurrentOverdue(Integer currentOverdue) {
		this.currentOverdue = currentOverdue;
	}

	public BigDecimal getCurrentOverdueAmount() {
		return currentOverdueAmount;
	}

	public void setCurrentOverdueAmount(BigDecimal currentOverdueAmount) {
		this.currentOverdueAmount = currentOverdueAmount;
	}

	public Integer getCumulativeOverdue() {
		return cumulativeOverdue;
	}

	public void setCumulativeOverdue(Integer cumulativeOverdue) {
		this.cumulativeOverdue = cumulativeOverdue;
	}

	public Integer getMaximumOverdue() {
		return maximumOverdue;
	}

	public void setMaximumOverdue(Integer maximumOverdue) {
		this.maximumOverdue = maximumOverdue;
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

	public Long getClosingDate() {
		return closingDate;
	}

	public void setClosingDate(Long closingDate) {
		this.closingDate = closingDate;
	}

}
