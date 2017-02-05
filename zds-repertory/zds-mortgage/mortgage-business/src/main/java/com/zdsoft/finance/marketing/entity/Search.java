package com.zdsoft.finance.marketing.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.zdsoft.framework.core.common.domain.BaseEntity;

/**
 * 版权所有：重庆正大华日软件有限公司
 * 
 * @Title:Search.java
 * @Package:com.zdsoft.finance.marketing.entity
 * @Description:产权状态实体
 * @author: zhoushichao
 * @date:2017年1月13日 下午9:32:35
 * @version:v1.0
 */
@Entity
@Table(name = "mark_search")
public class Search extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 查册状态
     */
    @Column(length = 32)
    private String searchStatus;

    /**
     * 查册单位
     */
    @Column(length = 128)
    private String searchUnit;

    /**
     * 是否查封
     */
    @Column(length = 10)
    private Boolean isSearched = false;

    /**
     * 是否有查封历史
     */
    @Column(length = 10)
    private Boolean isSearchHistory = false;

    /**
     * 查封时间
     */
    @Column
    private Long searchDate;

    /**
     * 查封期限
     */
    @Column
    private Integer searchTerm;

    /**
     * 查封金额
     */
    @Column(precision = 18, scale = 2)
    private BigDecimal searchAmount;

    /**
     * 查封者
     */
    @Column(length = 128)
    private String searchName;

    /**
     * 解封时间
     */
    @Column
    private Long unsealDate;

    /**
     * 查封事由
     */
    @Column(length = 500)
    private String searchReason;

    /**
     * 是否有抵押历史
     */
    @Column(length = 10)
    private Boolean isMortgageHistory = false;

    /**
     * 抵押时间
     */
    @Column
    private Long mortgageDate;

    /**
     * 抵押金额
     */
    @Column(precision = 18, scale = 2)
    private BigDecimal mortgageAmount;

    /**
     * 抵押权人
     */
    @Column(length = 128)
    private String mortgagee;

    /**
     * 解押时间
     */
    @Column
    private Long dischargeDate;

    /**
     * 抵押状态
     */
    @Column(length = 32)
    private String mortgageeStatus;

    /**
     * 预计日期
     */
    @Column
    private Long expectDate;

    /**
     * 添加字段by caixiekang
     */
    @Column(length = 32)
    private String housePropertyId;

    public String getHousePropertyId() {
        return housePropertyId;
    }

    public void setHousePropertyId(String housePropertyId) {
        this.housePropertyId = housePropertyId;
    }

    public String getSearchStatus() {
        return searchStatus;
    }

    public void setSearchStatus(String searchStatus) {
        this.searchStatus = searchStatus;
    }

    public String getSearchUnit() {
        return searchUnit;
    }

    public void setSearchUnit(String searchUnit) {
        this.searchUnit = searchUnit;
    }

    public Boolean getIsSearched() {
        return isSearched;
    }

    public void setIsSearched(Boolean isSearched) {
        this.isSearched = isSearched;
    }

    public Boolean getIsSearchHistory() {
        return isSearchHistory;
    }

    public void setIsSearchHistory(Boolean isSearchHistory) {
        this.isSearchHistory = isSearchHistory;
    }

    public Long getSearchDate() {
        return searchDate;
    }

    public void setSearchDate(Long searchDate) {
        this.searchDate = searchDate;
    }

    public Integer getSearchTerm() {
        return searchTerm;
    }

    public void setSearchTerm(Integer searchTerm) {
        this.searchTerm = searchTerm;
    }

    public BigDecimal getSearchAmount() {
        return searchAmount;
    }

    public void setSearchAmount(BigDecimal searchAmount) {
        this.searchAmount = searchAmount;
    }

    public String getSearchName() {
        return searchName;
    }

    public void setSearchName(String searchName) {
        this.searchName = searchName;
    }

    public Long getUnsealDate() {
        return unsealDate;
    }

    public void setUnsealDate(Long unsealDate) {
        this.unsealDate = unsealDate;
    }

    public String getSearchReason() {
        return searchReason;
    }

    public void setSearchReason(String searchReason) {
        this.searchReason = searchReason;
    }

    public Boolean getIsMortgageHistory() {
        return isMortgageHistory;
    }

    public void setIsMortgageHistory(Boolean isMortgageHistory) {
        this.isMortgageHistory = isMortgageHistory;
    }

    public Long getMortgageDate() {
        return mortgageDate;
    }

    public void setMortgageDate(Long mortgageDate) {
        this.mortgageDate = mortgageDate;
    }

    public BigDecimal getMortgageAmount() {
        return mortgageAmount;
    }

    public void setMortgageAmount(BigDecimal mortgageAmount) {
        this.mortgageAmount = mortgageAmount;
    }

    public String getMortgagee() {
        return mortgagee;
    }

    public void setMortgagee(String mortgagee) {
        this.mortgagee = mortgagee;
    }

    public Long getDischargeDate() {
        return dischargeDate;
    }

    public void setDischargeDate(Long dischargeDate) {
        this.dischargeDate = dischargeDate;
    }

    public String getMortgageeStatus() {
        return mortgageeStatus;
    }

    public void setMortgageeStatus(String mortgageeStatus) {
        this.mortgageeStatus = mortgageeStatus;
    }

    public Long getExpectDate() {
        return expectDate;
    }

    public void setExpectDate(Long expectDate) {
        this.expectDate = expectDate;
    }
}
