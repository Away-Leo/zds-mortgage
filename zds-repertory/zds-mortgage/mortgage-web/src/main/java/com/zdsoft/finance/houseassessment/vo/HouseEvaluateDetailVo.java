package com.zdsoft.finance.houseassessment.vo;

import java.util.Date;

import com.zdsoft.finance.common.base.BaseVo;
import com.zdsoft.finance.houseassessment.entity.HouseEvaluateDetail;
import com.zdsoft.framework.core.common.util.DateHelper;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: HouseEvaluateDetailVo.java 
 * @ClassName: HouseEvaluateDetailVo 
 * @Description: HouseEvaluateDetailVo对象
 * @author caixiekang 
 * @date 2017年2月27日 下午6:03:49 
 * @version V1.0
 */
public class HouseEvaluateDetailVo extends BaseVo<HouseEvaluateDetail>{
	
	
	/**   
	 */ 
	private static final long serialVersionUID = -2746665518802639485L;
	
	public static final long ON_LINE = 1L;
	
	public static final long OFF_LINE = 0L;
	
	/**
     * 小区ID
     */
	private String houseId;
    
    /**
     * 小区名
     */
	private String houseName;
    
    /**
     * 楼栋ID
     */
	private String buildingId;
    
    /**
     * 楼栋名
     */
	private String buildingName;
    
    /**
     * 房号ID
     */
	private String roomId;
    
    /**
     * 房号
     */
	private String roomNo;
    
    /**
     * 房子所在楼层
     */
	private String roomFloor;
    
    /**
     * 总楼层
     */
	private String totalFloor;
    
    /**
     * 评估总价
     */
	private String evaluatePrice;
    
    /**
     * 单价
     */
	private String unitPrice;
    
    /**
     * 创建人ID
     */
	private String creatorId;
    
    /**
     * 创建人
     */
	private String creator;
    
    /**
     * 创建时间
     */
	private Date createTime;
    
    /**
     * 评估ID
     */
	private String evaluateId;
    
    /**
     * 评估公司
     */
	private String evaluateCompany;
    
    /**
     * 权重
     */
	private Long weight;
    
    /**
     *  0:线下,1:线上
     */
	private Long onLine;
    
    /**
     * 返回时间
     */
	private Date resultTime;
	/**
	 * 评估时间(VO特有)
	 */
	private String evaluatedTime;
	
	public String getHouseId() {
		return houseId;
	}
	public void setHouseId(String houseId) {
		this.houseId = houseId;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
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
	public String getEvaluatedTime() {
		return evaluatedTime;
	}
	public void setEvaluatedTime(String evaluatedTime) {
		this.evaluatedTime = evaluatedTime;
	}
	
	
	public HouseEvaluateDetailVo(){
		super();
	}
	public HouseEvaluateDetailVo(HouseEvaluateDetail houseEvaluateDetail){
		super(houseEvaluateDetail, null, null);
		if(ON_LINE == onLine){
			this.setEvaluatedTime(DateHelper.dateToString(this.getCreateTime(), DateHelper.DATE_WITHMINUTE_FORMAT ));
		}
		if(OFF_LINE == onLine){
			this.setEvaluatedTime(DateHelper.dateToString(this.getResultTime(), DateHelper.DATE_WITHMINUTE_FORMAT ));
		}
		
	}
	
	public HouseEvaluateDetail toPo(){
		HouseEvaluateDetail houseEvaluateDetail = new HouseEvaluateDetail();
		return super.toPo(this, houseEvaluateDetail);
	}
}
