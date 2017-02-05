package com.zdsoft.finance.marketing.entity;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * 版权所有：重庆正大华日软件有限公司
 * 
 * @Title:HouseProperty.java
 * @Package:com.zdsoft.finance.marketing.entity
 * @Description:房产实体类
 * @author: zhoushichao
 * @date:2017年1月10日 下午9:45:49
 * @version:v1.0
 */
@Entity
@Table(name = "mark_house_property")
public class HouseProperty extends Collateral {

    private static final long serialVersionUID = 1L;

    /**
     * 房产证编号
     */
    @Column(length = 32)
    private String houseNo;
    /**
     * 预计入押时间
     */
    @Column
    private Long expectedDate;
    /**
     * 预计强制执行公证办理时间
     */
    @Column
    private Long transactDate;
    /**
     * 抵押权人名称
     */
    @Column(length = 128)
    private String mortgageeName;
    /**
     * 是否仲裁
     */
    @Column(length = 10)
    private Boolean isArbitration = false;
    /**
     * 小区名称
     */
    @Column(length = 128)
    private String communityName;
    /**
     * 所在楼层
     */
    @Column(length = 32)
    private String placeFloor;
    /**
     * 总楼层
     */
    @Column(length = 32)
    private String sumFloor;
    /**
     * 面积
     */
    @Column(length = 32)
    private String area;
    /**
     * 房产性质
     */
    @Column(length = 32)
    private String estateProperties;

    /**
     * 房产性质（其它）
     */
    @Column(length = 32)
    private String estatePropertiessOther;
    /**
     * 房产权属
     */
    @Column(length = 32)
    private String estateOwnership;
    /**
     * 中介询价
     */
    @Column(precision = 18, scale = 2)
    private BigDecimal intermediaryInquiry;
    /**
     * 网络询价
     */
    @Column(precision = 18, scale = 2)
    private BigDecimal networkInquiry;
    /**
     * 风控核定价
     */
    @Column(precision = 18, scale = 2)
    private BigDecimal controlPrice;
    /**
     * 评估价
     */
    @Column(precision = 18, scale = 2)
    private BigDecimal evaluatingPrice;
    /**
     * 综合评估价
     */
    @Column(precision = 18, scale = 2)
    private BigDecimal synthesizePrice;
    /**
     * 是否有装修
     */
    @Column(length = 10)
    private Boolean isRenovation = false;
    /**
     * 是否有电梯
     */
    @Column(length = 10)
    private Boolean isElevator = false;
    /**
     * 楼龄
     */
    @Column(length = 32)
    private String floorAge;
    /**
     * 省
     */
    @Column(length = 32)
    private String province;

    /**
     * 市
     */
    @Column(length = 32)
    private String city;

    /**
     * 区
     */
    @Column(length = 32)
    private String district;

    /**
     * 详细地址
     */
    @Column(length = 128)
    private String mailingAddress;
    /**
     * 抵押情况
     */
    @Column(length = 3000)
    private String mortgageSituation;
    /**
     * 产权状态情况
     */
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "searchId", nullable = true, unique = true)
    private Search search;
    /**
     * 房产评估信息
     */
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "housePropertyId")
    private List<HouseAssessment> houseAssessmentList;

    /**
     * 产权人
     */
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "housePropertyId")
    private List<PropertyOwner> propertyOwnerList;

    /**
     * 抵押情况
     */
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "housePropertyId")
    private List<PledgeInfo> pledgeInfoList;

    public String getHouseNo() {
        return houseNo;
    }

    public void setHouseNo(String houseNo) {
        this.houseNo = houseNo;
    }

    public Long getExpectedDate() {
        return expectedDate;
    }

    public void setExpectedDate(Long expectedDate) {
        this.expectedDate = expectedDate;
    }

    public Long getTransactDate() {
        return transactDate;
    }

    public void setTransactDate(Long transactDate) {
        this.transactDate = transactDate;
    }

    public String getMortgageeName() {
        return mortgageeName;
    }

    public void setMortgageeName(String mortgageeName) {
        this.mortgageeName = mortgageeName;
    }

    public Boolean getIsArbitration() {
        return isArbitration;
    }

    public void setIsArbitration(Boolean isArbitration) {
        this.isArbitration = isArbitration;
    }

    public BigDecimal getIntermediaryInquiry() {
        return intermediaryInquiry;
    }

    public void setIntermediaryInquiry(BigDecimal intermediaryInquiry) {
        this.intermediaryInquiry = intermediaryInquiry;
    }

    public BigDecimal getNetworkInquiry() {
        return networkInquiry;
    }

    public void setNetworkInquiry(BigDecimal networkInquiry) {
        this.networkInquiry = networkInquiry;
    }

    public BigDecimal getControlPrice() {
        return controlPrice;
    }

    public void setControlPrice(BigDecimal controlPrice) {
        this.controlPrice = controlPrice;
    }

    public BigDecimal getSynthesizePrice() {
        return synthesizePrice;
    }

    public void setSynthesizePrice(BigDecimal synthesizePrice) {
        this.synthesizePrice = synthesizePrice;
    }

    public Boolean getIsRenovation() {
        return isRenovation;
    }

    public void setIsRenovation(Boolean isRenovation) {
        this.isRenovation = isRenovation;
    }

    public Boolean getIsElevator() {
        return isElevator;
    }

    public void setIsElevator(Boolean isElevator) {
        this.isElevator = isElevator;
    }

    public String getCommunityName() {
        return communityName;
    }

    public void setCommunityName(String communityName) {
        this.communityName = communityName;
    }

    public String getPlaceFloor() {
        return placeFloor;
    }

    public void setPlaceFloor(String placeFloor) {
        this.placeFloor = placeFloor;
    }

    public String getSumFloor() {
        return sumFloor;
    }

    public void setSumFloor(String sumFloor) {
        this.sumFloor = sumFloor;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getEstateProperties() {
        return estateProperties;
    }

    public void setEstateProperties(String estateProperties) {
        this.estateProperties = estateProperties;
    }

    public String getEstatePropertiessOther() {
        return estatePropertiessOther;
    }

    public void setEstatePropertiessOther(String estatePropertiessOther) {
        this.estatePropertiessOther = estatePropertiessOther;
    }

    public String getEstateOwnership() {
        return estateOwnership;
    }

    public void setEstateOwnership(String estateOwnership) {
        this.estateOwnership = estateOwnership;
    }

    public String getFloorAge() {
        return floorAge;
    }

    public void setFloorAge(String floorAge) {
        this.floorAge = floorAge;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getMailingAddress() {
        return mailingAddress;
    }

    public void setMailingAddress(String mailingAddress) {
        this.mailingAddress = mailingAddress;
    }

    public String getMortgageSituation() {
        return mortgageSituation;
    }

    public void setMortgageSituation(String mortgageSituation) {
        this.mortgageSituation = mortgageSituation;
    }

    public BigDecimal getEvaluatingPrice() {
        return evaluatingPrice;
    }

    public void setEvaluatingPrice(BigDecimal evaluatingPrice) {
        this.evaluatingPrice = evaluatingPrice;
    }

    public Search getSearch() {
        return search;
    }

    public void setSearch(Search search) {
        this.search = search;
    }

    public List<HouseAssessment> getHouseAssessmentList() {
        return houseAssessmentList;
    }

    public void setHouseAssessmentList(List<HouseAssessment> houseAssessmentList) {
        this.houseAssessmentList = houseAssessmentList;
    }

    public List<PropertyOwner> getPropertyOwnerList() {
        return propertyOwnerList;
    }

    public void setPropertyOwnerList(List<PropertyOwner> propertyOwnerList) {
        this.propertyOwnerList = propertyOwnerList;
    }

    public List<PledgeInfo> getPledgeInfoList() {
        return pledgeInfoList;
    }

    public void setPledgeInfoList(List<PledgeInfo> pledgeInfoList) {
        this.pledgeInfoList = pledgeInfoList;
    }
}
