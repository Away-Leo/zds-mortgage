package com.zdsoft.finance.credit.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.zdsoft.framework.core.common.domain.BaseEntity;

/**
 * 版权所有：重庆正大华日软件有限公司
 * 
 * @Title: HmSpouseInfo.java
 * @ClassName: HmSpouseInfo
 * @Description: 配偶信息表
 * @author gufeng
 * @date 2017年2月23日 上午9:44:21
 * @version V1.0
 */
@Entity
@Table(name = "HM_Spouse_Info")
public class HmSpouseInfo extends BaseEntity {

	private static final long serialVersionUID = 4651308172443633517L;
	/**
	 * 查询表Id
	 */
	@Column(name = "query_Id")
	private String queryId;
	/**
	 * 身份证号码
	 */
	@Column(name = "id_card")
	private String idCard;
	/**
	 * 创建时间
	 */
	@Column(name = "create_time")
	private Timestamp createTime;

	/**
	 * 配偶姓名
	 */
	@Column(name = "Spouse_Name")
	private String spouseName;
	/**
	 * 证件类型
	 */
	@Column(name = "Spouse_Cert_Type")
	private String spouseCertType;
	/**
	 * 证件号码
	 */
	@Column(name = "Spouse_Cred_Num")
	private String spouseCredNum;
	/**
	 * 工作单位
	 */
	@Column(name = "Spouse_Company")
	private String spouseCompany;
	/**
	 * 单位号码
	 */
	@Column(name = "Spouse_Phone")
	private String spousePhone;

	public String getQueryId() {
		return queryId;
	}

	public void setQueryId(String queryId) {
		this.queryId = queryId;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public String getSpouseName() {
		return spouseName;
	}

	public void setSpouseName(String spouseName) {
		this.spouseName = spouseName;
	}

	public String getSpouseCertType() {
		return spouseCertType;
	}

	public void setSpouseCertType(String spouseCertType) {
		this.spouseCertType = spouseCertType;
	}

	public String getSpouseCredNum() {
		return spouseCredNum;
	}

	public void setSpouseCredNum(String spouseCredNum) {
		this.spouseCredNum = spouseCredNum;
	}

	public String getSpouseCompany() {
		return spouseCompany;
	}

	public void setSpouseCompany(String spouseCompany) {
		this.spouseCompany = spouseCompany;
	}

	public String getSpousePhone() {
		return spousePhone;
	}

	public void setSpousePhone(String spousePhone) {
		this.spousePhone = spousePhone;
	}

}
