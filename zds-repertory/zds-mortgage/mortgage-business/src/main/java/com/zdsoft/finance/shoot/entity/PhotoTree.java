package com.zdsoft.finance.shoot.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.zdsoft.framework.core.common.domain.BaseEntity;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title: PhotoTree.java 
 * @ClassName: PhotoTree 
 * @Description: 用于存储资调过程的照片拍摄数据
 * <br>照片本身是树形结构分类，按照扁平化存储
 * @author panshm 
 * @date 2017年3月4日 下午4:37:53 
 * @version V1.0
 */
@Entity
@Table(name = "photo_tree")
public class PhotoTree extends BaseEntity {

	private static final long serialVersionUID = -1375514909640140430L;
	
	/**
	 * 固定一级分类：抵押物
	 */
	public static final String TYPE_1_NAME_GUARANTEE = "guarantee";
	
	/**
	 * 固定一级分类：公司
	 */
	public static final String TYPE_1_NAME_COMPANY = "company";
	
	/**
	 * 固定一级分类：地址
	 */
	public static final String TYPE_1_NAME_ADDRESS = "address";

    /**
     * 案件号
     */
    @Column(length = 32)
    private String caseApplyCode;

    /**
     * 环节编号
     */
    @Column(length = 32)
    private String linkCode;
    
    /**
     * 地址
     */
    @Column(length = 256)
    private String address;

    /**
     * 经度
     */
    @Column(precision = 13,scale = 10)
    private Double longitude;

    /**
     * 纬度
     */
    @Column(precision = 13,scale = 10)
    private Double latitude;

    /**
     * 1级分类
     */
	@Column(name="type_1_name", length = 32)
    private String type1Name;

    /**
     * 2级分类
     */
	@Column(name="type_2_name", length = 32)
    private String type2Name;

    /**
     * 3级分类
     */
	@Column(name="type_3_name", length = 32)
    private String type3Name;

    /**
     * 4级分类
     */
	@Column(name="type_4_name", length = 32)
    private String type4Name;

    /**
     * 5级分类
     */
	@Column(name="type_5_name", length = 32)
    private String type5Name;

    /**
     * 照片服务器物理地址
     */
	@Column(name="photo_path", length = 256)
    private String photoPath;

    /**
     * 照片服务器url
     */
	@Column(name="photo_url", length = 256)
    private String photoUrl;
    
	/**
	 * 全分类节点数组 <br>
	 * 通过英文中划线分隔 <br>
	 * 示例：抵押物-外部环境-周边路面情况
	 * 示例：抵押物-内部环境-房间-房间1
	 */
	@Column(length = 128)
	private String allNodeStr;
    
	/**
	 * 全分类节点名称数组 <br>
	 * 通过英文中划线分隔 <br>
	 * 示例：抵押物-外部环境-周边路面情况
	 * 示例：抵押物-内部环境-房间-房间1
	 */
	@Column(length = 128)
	private String allNodeNameStr;
	
	/**
	 * 客户端文件名，使用时间戳命名
	 */
	@Column(name="file_name", length=64)
	private String fileName;
	
	
	
	
	
	public String getCaseApplyCode() {
		return caseApplyCode;
	}

	public void setCaseApplyCode(String caseApplyCode) {
		this.caseApplyCode = caseApplyCode;
	}

	public String getLinkCode() {
		return linkCode;
	}

	public void setLinkCode(String linkCode) {
		this.linkCode = linkCode;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public String getType1Name() {
		return type1Name;
	}

	public void setType1Name(String type1Name) {
		this.type1Name = type1Name;
	}

	public String getType2Name() {
		return type2Name;
	}

	public void setType2Name(String type2Name) {
		this.type2Name = type2Name;
	}

	public String getType3Name() {
		return type3Name;
	}

	public void setType3Name(String type3Name) {
		this.type3Name = type3Name;
	}

	public String getType4Name() {
		return type4Name;
	}

	public void setType4Name(String type4Name) {
		this.type4Name = type4Name;
	}

	public String getType5Name() {
		return type5Name;
	}

	public void setType5Name(String type5Name) {
		this.type5Name = type5Name;
	}

	public String getPhotoPath() {
		return photoPath;
	}

	public void setPhotoPath(String photoPath) {
		this.photoPath = photoPath;
	}

	public String getPhotoUrl() {
		return photoUrl;
	}

	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
	}

	public String getAllNodeStr() {
		return allNodeStr;
	}

	public void setAllNodeStr(String allNodeStr) {
		this.allNodeStr = allNodeStr;
	}

	public String getAllNodeNameStr() {
		return allNodeNameStr;
	}

	public void setAllNodeNameStr(String allNodeNameStr) {
		this.allNodeNameStr = allNodeNameStr;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	

}
