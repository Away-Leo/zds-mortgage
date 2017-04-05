package com.zdsoft.finance.houseassessment.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.zdsoft.framework.core.common.domain.BaseEntity;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: HouseEvaluate.java 
 * @ClassName: HouseEvaluate 
 * @Description: 房子评估实体
 * @author yangjia 
 * @date 2017年2月18日 下午3:36:10 
 * @version V1.0
 */
@Entity
@Table(name = "t_house_evaluate")
public class HouseEvaluate extends BaseEntity{

	private static final long serialVersionUID = -1038843626220854210L;
	
	/**
     * 省ID
     */
    @Column(name="province_id",length = 36)
	private String provinceId;
    
    /**
     * 省
     */
    @Column(name="province_name",length = 30)
	private String provinceName;
    
    /**
     * 市ID
     */
    @Column(name="city_id",length = 36)
	private String cityId;
    
    /**
     * 市
     */
    @Column(name="city_name",length = 30)
	private String cityName;
    
    /**
     * 区ID
     */
    @Column(name="district_id",length = 36)
	private String districtId;
    
    /**
     * 区
     */
    @Column(name="district_name",length = 30)
	private String districtName;
    
    /**
     * 楼盘关键字
     */
    @Column(name="house_key",length = 50)
    private String houseKey;
    
    /**
	 * 面积
	 */
	@Column(name="house_area", precision = 10, scale = 2)
	private BigDecimal houseArea = BigDecimal.ZERO;
	
	/**
     * 物业地址
     */
    @Column(name="house_address",length = 150)
	private String houseAddress;
    
    /**
     * 计算后总价
     */
    @Column(name="sum_price",length = 20)
	private String sumPrice;
    
    /**
     * 创建人ID
     */
    @Column(name="creator_id",length = 36)
	private String creatorId;
    
    /**
     * 创建人
     */
    @Column(name="creator",length = 30)
	private String creator;
    
    /**
     * 创建时间
     */
    @Column(name="create_time")
	private Date createTime;
    
    /**
     * 权重
     */
    @Column(name="weight")
	private Long weight;
    
    
    /**
     * 业务ID
     */
    @Column(name="biz_id",length = 36)
	private String bizId;
    
    /**
     * '0:手机/1:web
     */
    @Column(name="source")
	private String source;

	public String getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(String provinceId) {
		this.provinceId = provinceId;
	}

	public String getProvinceName() {
		return provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}

	public String getCityId() {
		return cityId;
	}

	public void setCityId(String cityId) {
		this.cityId = cityId;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getDistrictId() {
		return districtId;
	}

	public void setDistrictId(String districtId) {
		this.districtId = districtId;
	}

	public String getDistrictName() {
		return districtName;
	}

	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}

	public String getHouseKey() {
		return houseKey;
	}

	public void setHouseKey(String houseKey) {
		this.houseKey = houseKey;
	}

	public BigDecimal getHouseArea() {
		return houseArea;
	}

	public void setHouseArea(BigDecimal houseArea) {
		this.houseArea = houseArea;
	}

	public String getHouseAddress() {
		return houseAddress;
	}

	public void setHouseAddress(String houseAddress) {
		this.houseAddress = houseAddress;
	}

	public String getSumPrice() {
		return sumPrice;
	}

	public void setSumPrice(String sumPrice) {
		this.sumPrice = sumPrice;
	}

	public String getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(String creatorId) {
		this.creatorId = creatorId;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Long getWeight() {
		return weight;
	}

	public void setWeight(Long weight) {
		this.weight = weight;
	}

	public String getBizId() {
		return bizId;
	}

	public void setBizId(String bizId) {
		this.bizId = bizId;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}
    
    
    
    
    

}
