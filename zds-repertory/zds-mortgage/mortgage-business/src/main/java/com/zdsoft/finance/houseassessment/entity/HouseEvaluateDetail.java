package com.zdsoft.finance.houseassessment.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.zdsoft.framework.core.common.domain.BaseEntity;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: HouseEvaluateDetail.java 
 * @ClassName: HouseEvaluateDetail 
 * @Description: 房子评估详细信息实体
 * @author yangjia 
 * @date 2017年2月18日 下午4:20:42 
 * @version V1.0
 */
@Entity
@Table(name = "t_house_evaluate_detail")
public class HouseEvaluateDetail extends BaseEntity{

	private static final long serialVersionUID = 5286308911985026045L;
	
	/**
     * 小区ID
     */
    @Column(name="house_id",length = 36)
	private String houseId;
    
    /**
     * 小区名
     */
    @Column(name="house_name",length = 50)
	private String houseName;
    
    /**
     * 楼栋ID
     */
    @Column(name="building_id",length = 36)
	private String buildingId;
    
    /**
     * 楼栋名
     */
    @Column(name="building_name",length = 50)
	private String buildingName;
    
    /**
     * 房号ID
     */
    @Column(name="room_id",length = 36)
	private String roomId;
    
    /**
     * 房号
     */
    @Column(name="room_no",length = 50)
	private String roomNo;
    
    /**
     * 房子所在楼层
     */
    @Column(name="room_floor",length = 10)
	private String roomFloor;
    
    /**
     * 总楼层
     */
    @Column(name="total_floor",length = 10)
	private String totalFloor;
    
    /**
     * 评估总价
     */
    @Column(name="evaluate_price",length = 20)
	private String evaluatePrice;
    
    /**
     * 单价
     */
    @Column(name="unit_price",length = 20)
	private String unitPrice;
    
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
     * 评估ID
     */
    @Column(name="evaluate_id",length = 36)
	private String evaluateId;
    
    /**
     * 评估公司
     */
    @Column(name="evaluate_company",length = 10)
	private String evaluateCompany;
    
    /**
     * 权重
     */
    @Column(name="weight")
	private Long weight;
    
    /**
     *  0:线下,1:线上
     */
    @Column(name="on_line")
	private Long onLine;
    
    /**
     * 返回时间
     */
    @Column(name="result_time")
	private Date resultTime;
    
    

	public Long getOnLine() {
		return onLine;
	}

	public void setOnLine(Long onLine) {
		this.onLine = onLine;
	}

	public Date getResultTime() {
		return resultTime;
	}

	public void setResultTime(Date resultTime) {
		this.resultTime = resultTime;
	}

	public String getHouseId() {
		return houseId;
	}

	public void setHouseId(String houseId) {
		this.houseId = houseId;
	}

	public String getHouseName() {
		return houseName;
	}

	public void setHouseName(String houseName) {
		this.houseName = houseName;
	}

	public String getBuildingId() {
		return buildingId;
	}

	public void setBuildingId(String buildingId) {
		this.buildingId = buildingId;
	}

	public String getBuildingName() {
		return buildingName;
	}

	public void setBuildingName(String buildingName) {
		this.buildingName = buildingName;
	}

	public String getRoomId() {
		return roomId;
	}

	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}

	public String getRoomNo() {
		return roomNo;
	}

	public void setRoomNo(String roomNo) {
		this.roomNo = roomNo;
	}

	public String getRoomFloor() {
		return roomFloor;
	}

	public void setRoomFloor(String roomFloor) {
		this.roomFloor = roomFloor;
	}

	public String getTotalFloor() {
		return totalFloor;
	}

	public void setTotalFloor(String totalFloor) {
		this.totalFloor = totalFloor;
	}

	public String getEvaluatePrice() {
		return evaluatePrice;
	}

	public void setEvaluatePrice(String evaluatePrice) {
		this.evaluatePrice = evaluatePrice;
	}

	public String getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(String unitPrice) {
		this.unitPrice = unitPrice;
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

	public String getEvaluateId() {
		return evaluateId;
	}

	public void setEvaluateId(String evaluateId) {
		this.evaluateId = evaluateId;
	}

	public String getEvaluateCompany() {
		return evaluateCompany;
	}

	public void setEvaluateCompany(String evaluateCompany) {
		this.evaluateCompany = evaluateCompany;
	}

	public Long getWeight() {
		return weight;
	}

	public void setWeight(Long weight) {
		this.weight = weight;
	}

}
