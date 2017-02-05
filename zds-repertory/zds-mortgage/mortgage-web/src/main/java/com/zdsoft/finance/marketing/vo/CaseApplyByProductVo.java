package com.zdsoft.finance.marketing.vo;
/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title:CaseApplyByProductVo.java
 * @Package:com.zdsoft.finance.marketing.vo
 * @Description:APP案件申请列表VO
 * @author: jingjy
 * @date:2017年1月17日 上午10:50:50
 * @version:v1.0
 */
public class CaseApplyByProductVo {
    
    /**
     * 产品类别名称
     */
    private String category;
    

    /**
     * 产品名称
     */
    private String name;

    /**
     * 产品ID
     */
    private String productId;

    /**
     * 利率
     */
    private String rate;

    /**
     * 利率单位
     */
    private String rateUnit;
    
    /**
     * 利率单位名称
     */
    private String rateUnitName;

    /**
     * 最小贷款期限
     */
    private String minPeriod;

    /**
     * 最大贷款期限
     */
    private String maxPeriod;

    /**
     * 贷款期限单位
     */
    private String endDateUnit;
    
    /**
     * 贷款期限单位
     */
    private String startDateUnit;
    
    /**
     * 贷款期限单位名称
     */
    private String periodUnitName;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getRateUnit() {
        return rateUnit;
    }

    public void setRateUnit(String rateUnit) {
        this.rateUnit = rateUnit;
    }

    public String getMinPeriod() {
        return minPeriod;
    }

    public void setMinPeriod(String minPeriod) {
        this.minPeriod = minPeriod;
    }

    public String getMaxPeriod() {
        return maxPeriod;
    }

    public void setMaxPeriod(String maxPeriod) {
        this.maxPeriod = maxPeriod;
    }


    public String getEndDateUnit() {
        return endDateUnit;
    }

    public void setEndDateUnit(String endDateUnit) {
        this.endDateUnit = endDateUnit;
    }

    public String getStartDateUnit() {
        return startDateUnit;
    }

    public void setStartDateUnit(String startDateUnit) {
        this.startDateUnit = startDateUnit;
    }

    public String getRateUnitName() {
        return rateUnitName;
    }

    public void setRateUnitName(String rateUnitName) {
        this.rateUnitName = rateUnitName;
    }

    public String getPeriodUnitName() {
        return periodUnitName;
    }

    public void setPeriodUnitName(String periodUnitName) {
        this.periodUnitName = periodUnitName;
    }
    
}
