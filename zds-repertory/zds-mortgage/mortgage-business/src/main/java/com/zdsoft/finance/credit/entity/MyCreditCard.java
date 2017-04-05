
package com.zdsoft.finance.credit.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.zdsoft.framework.core.common.domain.BaseEntity;

/** 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: MyCreditCard.java 
 * @ClassName: MyCreditCard 
 * @Description: 本人信用卡信息
 * @author dengyy 
 * @date 2017年2月22日 下午9:44:38 
 * @version V1.0 
 */
@Entity
@Table(name="cust_my_credit_card")
public class MyCreditCard extends BaseEntity{

    private static final long serialVersionUID = 1L;

    /**
     * 信用额度
     */
    @Column(precision=18,scale=6)
    private BigDecimal creditLimit  = BigDecimal.ZERO;
    
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
     * 当前逾期总额
     */
    @Column(precision=18,scale=6)
    private BigDecimal currentTotalOverdue  = BigDecimal.ZERO;
    
    /**
     * 当前透支额 
     */
    @Column(precision=18,scale=6)
    private BigDecimal currentOverdraft  = BigDecimal.ZERO;
    
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
     * 备注 
     */
    @Column(length=512)
    private String remark ;
    
    /**
     * 征信综合情况id
     */
    @Column(length=32)
    private String creditSituationId ;

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
    
}
