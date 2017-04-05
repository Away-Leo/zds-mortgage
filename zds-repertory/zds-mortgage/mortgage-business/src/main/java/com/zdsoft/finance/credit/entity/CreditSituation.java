
package com.zdsoft.finance.credit.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.zdsoft.framework.core.common.domain.BaseEntity;

/** 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: CreditSituation.java 
 * @ClassName: CreditSituation 
 * @Description: 征信综合情况
 * @author dengyy 
 * @date 2017年2月22日 下午9:25:42 
 * @version V1.0 
 */
@Entity
@Table(name="cust_credit_situation")
public class CreditSituation extends BaseEntity{

    private static final long serialVersionUID = 1L;
    
    /**
     * 人行征信报告打印时间
     */
    @Column(length=16)
    private Long reportPrintingDate ;
    
    /**
     * 人行报告1个月内查询次数
     */
    @Column(length=3)
    private Integer queryNumber ;
    
    /**
     * 征信情况综合评价 
     */
    @Column(length=512)
    private String creditEvaluation ;
    
    /**
     * 案件id
     */
    @Column(length=32)
    private String caseApplyId ;
    
    /**
     * 客户id 
     */
    @Column(length=32)
    private String customerId ;
    
    /**
     * 客户姓名 
     */
    @Column(length=64)
    private String customerName ;
    
    /**
     * 案件客户征信id
     */
    @Column(length=32)
    private String creditId ;
    
    /**
     * 本人征信信息
     */
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "creditSituationId")
    private List<MyCredit> myCredit = new ArrayList<MyCredit>();
    
    /**
     * 本人信用卡信息
     */
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "creditSituationId")
    private List<MyCreditCard> myCreditCard = new ArrayList<MyCreditCard>();
    
    /**
     * 贷款信息
     */
    private transient String  credit ;
    
    /**
     * 信用卡信息
     */
    private transient String  creditCard ;

    public Long getReportPrintingDate() {
        return reportPrintingDate;
    }

    public void setReportPrintingDate(Long reportPrintingDate) {
        this.reportPrintingDate = reportPrintingDate;
    }

    public Integer getQueryNumber() {
        return queryNumber;
    }

    public void setQueryNumber(Integer queryNumber) {
        this.queryNumber = queryNumber;
    }

    public String getCreditEvaluation() {
        return creditEvaluation;
    }

    public void setCreditEvaluation(String creditEvaluation) {
        this.creditEvaluation = creditEvaluation;
    }

    public String getCaseApplyId() {
        return caseApplyId;
    }

    public void setCaseApplyId(String caseApplyId) {
        this.caseApplyId = caseApplyId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCreditId() {
        return creditId;
    }

    public void setCreditId(String creditId) {
        this.creditId = creditId;
    }

    public List<MyCredit> getMyCredit() {
        return myCredit;
    }

    public void setMyCredit(List<MyCredit> myCredit) {
        this.myCredit = myCredit;
    }

    public List<MyCreditCard> getMyCreditCard() {
        return myCreditCard;
    }

    public void setMyCreditCard(List<MyCreditCard> myCreditCard) {
        this.myCreditCard = myCreditCard;
    }

    public String getCredit() {
        return credit;
    }

    public void setCredit(String credit) {
        this.credit = credit;
    }

    public String getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(String creditCard) {
        this.creditCard = creditCard;
    }
    
}
