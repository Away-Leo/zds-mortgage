package com.zdsoft.finance.credit.vo;

import com.zdsoft.finance.common.base.BaseVo;
import com.zdsoft.finance.credit.entity.CreditSituation;

/** 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: CreditSituationVo.java 
 * @ClassName: CreditSituationVo 
 * @Description: 征信综合情况 vo
 * @author dengyy 
 * @date 2017年3月14日 下午9:22:59 
 * @version V1.0 
 */
public class CreditSituationVo extends BaseVo<CreditSituation> {

    private static final long serialVersionUID = -936202509801774381L;
    
    /**
     * 人行征信报告打印时间
     */
    private Long reportPrintingDate ;
    
    /**
     * 人行报告1个月内查询次数
     */
    private Integer queryNumber ;
    
    /**
     * 征信情况综合评价 
     */
    private String creditEvaluation ;
    
    /**
     * 案件id
     */
    private String caseApplyId ;
    
    /**
     * 客户id 
     */
    private String customerId ;
    
    /**
     * 客户姓名 
     */
    private String customerName ;
    
    /**
     * 案件客户征信id
     */
    private String creditId ;
  
    /**
     * 本人信用卡信息
     */
    private String creditCard ;
    
    /**
     * 本人贷款信息
     */
    private String credit ;

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
    
    public CreditSituationVo(){
        super();
    }
    
    public CreditSituationVo(CreditSituation entity){
        super(entity);
    }
    
    public CreditSituation toPo(){
        CreditSituation entity = new CreditSituation();
        entity = super.toPo(this, entity);
        return entity;
    }

    public String getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(String creditCard) {
        this.creditCard = creditCard;
    }

    public String getCredit() {
        return credit;
    }

    public void setCredit(String credit) {
        this.credit = credit;
    }

}
