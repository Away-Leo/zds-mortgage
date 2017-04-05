package com.zdsoft.finance.marketing.vo;

import java.math.BigDecimal;

import com.zdsoft.finance.common.base.BaseVo;
import com.zdsoft.finance.common.utils.VoUtil;
import com.zdsoft.finance.marketing.entity.HouseProperty;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: HousePropertyVo.java 
 * @ClassName: HousePropertyVo 
 * @Description: 房产Vo类
 * @author zhoushichao 
 * @date 2017年3月14日 下午7:49:27 
 * @version V1.0
 */
public class HousePropertyVo extends BaseVo<HouseProperty> {

    private static final long serialVersionUID = 1L;

    /**
     * 案件ID
     */
    private String caseApplyId;
    /**
     * 房产证编号
     */
    private String houseNo;
    /**
     * 预计入押时间
     */
    private Long expectedDate;
    /**
     * 预计强制执行公证办理时间
     */
    private Long transactDate;
    /**
     * 抵押权人名称
     */
    private String mortgageeName;
    /**
     * 是否仲裁
     */
    private Boolean isArbitration;
    /**
     * 小区名称
     */
    private String communityName;
    /**
     * 所在楼层
     */
    private String placeFloor;
    /**
     * 总楼层
     */
    private String sumFloor;
    /**
     * 面积
     */
    private String area;
    /**
     * 房产性质Code
     */
    private String estateProperties;
    /**
     * 房产性质名称
     */
    private String estatePropertiesName;
    /**
     * 房产性质（其它）
     */
    private String estatePropertiessOther;
    /**
     * 房产权属Code
     */
    private String estateOwnership;
    /**
     * 房产权属名称
     */
    private String estateOwnershipName;
    /**
     * 中介询价
     */
    private BigDecimal intermediaryInquiry;
    /**
     * 网络询价
     */
    private BigDecimal networkInquiry;
    /**
     * 风控核定价
     */
    private BigDecimal controlPrice;
    /**
     * 风控复议价  model by liuhuan 2017-2-16
     */
    private BigDecimal controlReviewPrice;
    /**
     * 居住状态  model by liuhuan 2017-2-16
     */
    private String livingState;
    private String livingStateName;
    /**
     * 评估价
     */
    private BigDecimal evaluatingPrice;
    /**
     * 综合评估价
     */
    private BigDecimal synthesizePrice;
    /**
     * 是否有装修
     * model by liuhuan 2017-2-16 类型转换为String
     */
    private String isRenovation;
    private String isRenovationName;
    /**
     * 是否有电梯
     * model by liuhuan 2017-2-16 类型转换为String
     */
    private String isElevator;
    private String isElevatorName;
    /**
     * 楼龄
     */
    private String floorAge;
    /**
     * 省Code
     */
    private String province;
    /**
     * 省名称
     */
    private String provinceName;
    /**
     * 市Code
     */
    private String city;
    /**
     * 市名称
     */
    private String cityName;
    /**
     * 区Code
     */
    private String district;
    /**
     * 区名称
     */
    private String districtName;

    /**
     * 详细地址
     */
    private String mailingAddress;

    /**
     * 综合详细地址
     */
    private String totalMailingAddress;
    /**
     * 抵押情况Code
     */
    private String mortgageSituation;
    /**
     * 抵押情况名称
     */
    private String mortgageSituationName;
    /**
     * 产权情况
     */
    private SearchVo searchVo;
    
    
    /**
     * (合同补充)债权数额
     */
    private BigDecimal creditorsAmount;
	/**
     * (合同补充)土地使用权面积
     */
    private String landUseArea;
    
    /**
     * (合同补充)土地证编号
     */
    private String landCertificateNo;
    
    /**
     * (合同补充)抵押物价值(苏州)/估价(中山)/剩余价值(江阴二抵)
     */
    private BigDecimal mortgageAmount1;
    
    /**
     * (合同补充)抵押金额(无锡)
     */
    private BigDecimal mortgageAmount2;

    public String getCaseApplyId() {
        return caseApplyId;
    }

    public void setCaseApplyId(String caseApplyId) {
        this.caseApplyId = caseApplyId;
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

    public String getIsRenovation() {
		return isRenovation;
	}

	public void setIsRenovation(String isRenovation) {
		this.isRenovation = isRenovation;
	}

	public String getIsElevator() {
		return isElevator;
	}

	public void setIsElevator(String isElevator) {
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

    public String getEstatePropertiesName() {
        return estatePropertiesName;
    }

    public void setEstatePropertiesName(String estatePropertiesName) {
        this.estatePropertiesName = estatePropertiesName;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    public SearchVo getSearchVo() {
        return searchVo;
    }

    public void setSearchVo(SearchVo searchVo) {
        this.searchVo = searchVo;
    }

    public String getEstateOwnershipName() {
		return estateOwnershipName;
	}

	public void setEstateOwnershipName(String estateOwnershipName) {
		this.estateOwnershipName = estateOwnershipName;
	}
	
	public String getMortgageSituationName() {
		return mortgageSituationName;
	}

	public void setMortgageSituationName(String mortgageSituationName) {
		this.mortgageSituationName = mortgageSituationName;
	}
    public String getTotalMailingAddress() {
		return totalMailingAddress;
	}

	public void setTotalMailingAddress(String totalMailingAddress) {
		this.totalMailingAddress = totalMailingAddress;
	}

	public String getIsRenovationName() {
		return isRenovationName;
	}

	public void setIsRenovationName(String isRenovationName) {
		this.isRenovationName = isRenovationName;
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

	public HousePropertyVo() {
        super();
    }

    public HousePropertyVo(HouseProperty po) {
        super(po,null,new String[]{"livingState","isElevator","isRenovation","estateProperties","estateOwnership","province","city","district","mortgageSituation"});
        this.caseApplyId = po.getCaseApply().getId();
        this.totalMailingAddress = this.provinceName+"/"+this.cityName+"/"+this.districtName+"/"+this.mailingAddress;
    }

    public HousePropertyVo(HouseProperty po, String[] args, String[] simpleArgs) throws Exception {
        VoUtil.copyPoperties(po, this, false, args, simpleArgs);
    }

    public HouseProperty toPo() {
        HouseProperty po = new HouseProperty();
        return super.toPo(this, po);
    }

	public String getIsElevatorName() {
		return isElevatorName;
	}

	public void setIsElevatorName(String isElevatorName) {
		this.isElevatorName = isElevatorName;
	}

	public String getLivingStateName() {
		return livingStateName;
	}

	public void setLivingStateName(String livingStateName) {
		this.livingStateName = livingStateName;
	}

}
