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

import org.hibernate.annotations.Type;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: HouseProperty.java 
 * @ClassName: HouseProperty 
 * @Description: 房产实体类
 * @author zhoushichao 
 * @date 2017年3月14日 下午5:45:38 
 * @version V1.0
 */
@Entity
@Table(name = "mkt_house_property")
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
     * 抵押权人ID
     */
    @Column(length = 32)
    private String mortgageeId;
    /**
     * 抵押权人名称
     */
    @Column(length = 128)
    private String mortgageeName;
    /**
     * 是否仲裁
     */
    @Column
    @Type(type = "true_false")
    Boolean isArbitration = Boolean.valueOf(false);
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
    private BigDecimal intermediaryInquiry=BigDecimal.ZERO;
    /**
     * 网络询价
     */
    @Column(precision = 18, scale = 2)
    private BigDecimal networkInquiry=BigDecimal.ZERO;
    /**
     * 风控核定价
     */
    @Column(precision = 18, scale = 2)
    private BigDecimal controlPrice=BigDecimal.ZERO;
    /**
     * 风控复议价  model by liuhuan 2017-2-16
     */
    @Column(precision = 18, scale = 2)
    private BigDecimal controlReviewPrice=BigDecimal.ZERO;
    /**
     * 居住状态  model by liuhuan 2017-2-16
     */
    @Column(length = 20)
    private String livingState;
    /**
     * 评估价
     */
    @Column(precision = 18, scale = 2)
    private BigDecimal evaluatingPrice=BigDecimal.ZERO;
    /**
     * 综合评估价
     */
    @Column(precision = 18, scale = 2)
    private BigDecimal synthesizePrice=BigDecimal.ZERO;
    /**
     * 是否有装修
     */
    @Column(length=20)
    private String isRenovation;
    /**
     * 是否有电梯
     */
    @Column(length=20)
    private String isElevator;
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
    @Column(length = 20)
    private String mortgageSituation;
    /**
     * 居住状态
     */
    @Column(length = 32)
    private String liveStuts;
    
    /**
     * (合同补充)债权数额
     */
    @Column(precision = 18, scale = 6)
    private BigDecimal creditorsAmount=BigDecimal.ZERO;
    
	/**
     * (合同补充)土地使用权面积
     */
    @Column(length = 32)
    private String landUseArea;
    
    /**
     * (合同补充)土地证编号
     */
    @Column(length = 32)
    private String landCertificateNo;
    
    /**
     * (合同补充)抵押物价值(苏州)/估价(中山)/剩余价值(江阴二抵)
     */
    @Column(precision = 18, scale = 6)
    private BigDecimal mortgageAmount1=BigDecimal.ZERO;
    
    /**
     * (合同补充)抵押金额(无锡)
     */
    @Column(precision = 18, scale = 6)
    private BigDecimal mortgageAmount2=BigDecimal.ZERO;
    
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
    
    public String getLivingState() {
		return livingState;
	}

	public void setLivingState(String livingState) {
		this.livingState = livingState;
	}

	public BigDecimal getControlReviewPrice() {
		return controlReviewPrice;
	}

	public void setControlReviewPrice(BigDecimal controlReviewPrice) {
		this.controlReviewPrice = controlReviewPrice;
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

    public String getMortgageeId() {
		return mortgageeId;
	}

	public void setMortgageeId(String mortgageeId) {
		this.mortgageeId = mortgageeId;
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


    public void setIsRenovation(String isRenovation) {
		this.isRenovation = isRenovation;
	}

	public void setIsElevator(String isElevator) {
		this.isElevator = isElevator;
	}

	public String getIsRenovation() {
		return isRenovation;
	}

	public String getIsElevator() {
		return isElevator;
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

	public String getLiveStuts() {
		return liveStuts;
	}

	public void setLiveStuts(String liveStuts) {
		this.liveStuts = liveStuts;
	}
	
	public BigDecimal getCreditorsAmount() {
		return creditorsAmount;
	}

	public void setCreditorsAmount(BigDecimal creditorsAmount) {
		this.creditorsAmount = creditorsAmount;
	}

	public String getLandUseArea() {
		return landUseArea;
	}

	public void setLandUseArea(String landUseArea) {
		this.landUseArea = landUseArea;
	}

	public String getLandCertificateNo() {
		return landCertificateNo;
	}

	public void setLandCertificateNo(String landCertificateNo) {
		this.landCertificateNo = landCertificateNo;
	}

	public BigDecimal getMortgageAmount1() {
		return mortgageAmount1;
	}

	public void setMortgageAmount1(BigDecimal mortgageAmount1) {
		this.mortgageAmount1 = mortgageAmount1;
	}

	public BigDecimal getMortgageAmount2() {
		return mortgageAmount2;
	}

	public void setMortgageAmount2(BigDecimal mortgageAmount2) {
		this.mortgageAmount2 = mortgageAmount2;
	}

    
}
