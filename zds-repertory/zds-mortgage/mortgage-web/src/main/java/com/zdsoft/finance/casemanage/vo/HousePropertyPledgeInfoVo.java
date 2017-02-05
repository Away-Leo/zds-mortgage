package com.zdsoft.finance.casemanage.vo;

import java.math.BigDecimal;

/**
 * 版权所有：重庆正大华日软件有限公司
 * 
 * @Title: HousePropertyPledgeInfoVo.java
 * @Package com.zdsoft.finance.casemanage.vo
 * @Description: 抵押物抵押情况综合VO（包含一抵，二抵）
 * @author Liyb
 * @date 2017年1月15日 上午11:11:35
 * @version V1.0
 */
public class HousePropertyPledgeInfoVo {

    /**
     * 抵押物ID
     */
    private String housePropertyId;

    /**
     * 一抵银行
     */
    private String firstLoanBank;

    /**
     * 一抵贷款余额
     */
    private BigDecimal firstLoanBalance;

    /**
     * 二抵银行
     */
    private String secondLoanBank;

    /**
     * 二抵贷款余额
     */
    private BigDecimal secondLoanBalance;

    public String getHousePropertyId() {
        return housePropertyId;
    }

    public void setHousePropertyId(String housePropertyId) {
        this.housePropertyId = housePropertyId;
    }


    public String getFirstLoanBank() {
        return firstLoanBank;
    }

    public void setFirstLoanBank(String firstLoanBank) {
        this.firstLoanBank = firstLoanBank;
    }

    public BigDecimal getFirstLoanBalance() {
        return firstLoanBalance;
    }

    public void setFirstLoanBalance(BigDecimal firstLoanBalance) {
        this.firstLoanBalance = firstLoanBalance;
    }

    public String getSecondLoanBank() {
        return secondLoanBank;
    }

    public void setSecondLoanBank(String secondLoanBank) {
        this.secondLoanBank = secondLoanBank;
    }

    public BigDecimal getSecondLoanBalance() {
        return secondLoanBalance;
    }

    public void setSecondLoanBalance(BigDecimal secondLoanBalance) {
        this.secondLoanBalance = secondLoanBalance;
    }

}
