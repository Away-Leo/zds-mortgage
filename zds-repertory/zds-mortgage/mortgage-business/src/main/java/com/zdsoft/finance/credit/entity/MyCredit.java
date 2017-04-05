
package com.zdsoft.finance.credit.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.zdsoft.framework.core.common.domain.BaseEntity;

/** 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: MyCredit.java 
 * @ClassName: MyCredit 
 * @Description: 本人征信信息
 * @author dengyy 
 * @date 2017年2月22日 下午9:31:55 
 * @version V1.0 
 */
@Entity
@Table(name="cust_my_credit")
public class MyCredit extends BaseEntity{
    
    private static final long serialVersionUID = 1L;

    /**
     * 贷款种类code
     */
    @Column(length=20)
    private String loanTypesCode ;
    
    /**
     * 贷款种类name
     */
    @Column(length=64)
    private String loanTypesName ;
    
    /**
     * 账户状态Code
     */
    @Column(length=20)
    private String accountStatusCode ;
    
    /**
     * 账户状态name
     */
    @Column(length=64)
    private String accountStatusName ;
    
    /**
     * 贷款发放日期 
     */
    @Column(length=16)
    private Long loanDate ;
    
    /**
     * 贷款年限 
     */
    @Column(length=3)
    private Integer loanTerm ;
    
    /**
     * 贷款金额  
     */
    @Column(precision=18,scale=6)
    private BigDecimal loanAmount = BigDecimal.ZERO;
    
    /**
     * 贷款余额  
     */
    @Column(precision=18,scale=6)
    private BigDecimal loanBalance = BigDecimal.ZERO;
    
    /**
     * 当前逾期期数
     */
    @Column(length=3)
    private Integer currentOverdue ;
    
    /**
     * 当前逾期金额 
     */
    @Column(precision=18,scale=6)
    private BigDecimal currentOverdueAmount  = BigDecimal.ZERO;
    
    /**
     * 累计逾期期数
     */
    @Column(length=3)
    private Integer cumulativeOverdue ;
    
    /**
     * 最高逾期期数  maximumOverdue
     */
    @Column(length=3)
    private Integer maximumOverdue ;
    
    /**
     * 最12个月累计逾期次数
     */
    @Column(length=3)
    private Integer cumulativeOverdue12 ;
    
    /**
     * 最12个月最高逾期次数
     */
    @Column(length=3)
    private Integer maximumOverdue12 ;
    
    /**
     * 最近5年内几个月处于逾期状态 
     */
    @Column(length=3)
    private Integer monthsOverdue5 ;
    
    /**
     * 最近5年内90天以上逾期次数
     */
    @Column(length=3)
    private Integer lastYear90Count ;
    
    /**
     * 结清日期
     */
    @Column(length =16)
    private Long closingDate ;
    
    /**
     * 逾期情况及其他情况说明 
     */
    @Column(length=512)
    private String remark ;
    
    /**
     * 征信综合情况id
     */
    @Column(length=32)
    private String creditSituationId ;

    public String getLoanTypesCode() {
        return loanTypesCode;
    }

    public void setLoanTypesCode(String loanTypesCode) {
        this.loanTypesCode = loanTypesCode;
    }

    public String getLoanTypesName() {
        return loanTypesName;
    }

    public void setLoanTypesName(String loanTypesName) {
        this.loanTypesName = loanTypesName;
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

    public Integer getMonthsOverdue5() {
        return monthsOverdue5;
    }

    public void setMonthsOverdue5(Integer monthsOverdue5) {
        this.monthsOverdue5 = monthsOverdue5;
    }

    public Integer getLastYear90Count() {
        return lastYear90Count;
    }

    public void setLastYear90Count(Integer lastYear90Count) {
        this.lastYear90Count = lastYear90Count;
    }

    public Long getClosingDate() {
        return closingDate;
    }

    public void setClosingDate(Long closingDate) {
        this.closingDate = closingDate;
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
    
}
