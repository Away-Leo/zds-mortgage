package com.zdsoft.finance.credit.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.zdsoft.framework.core.common.domain.BaseEntity;

/**
 * 版权所有：重庆正大华日软件有限公司
 * 
 * @Title: HmResideInfo.java
 * @ClassName: HmResideInfo
 * @Description: 居住信息列表
 * @author gufeng
 * @date 2017年2月23日 上午9:44:01
 * @version V1.0
 */
@Entity
@Table(name = "HM_Reside_Info")
public class HmResideInfo extends BaseEntity {

	private static final long serialVersionUID = -7664193823668236760L;
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
	 * 顺序
	 */
	@Column(name = "No")
	private String no;
	/**
	 * 居住地址
	 */
	@Column(name = "Reside_Addr")
	private String resideAddr;
	/**
	 * 居住状况
	 */
	@Column(name = "Reside_Status")
	private String resideStatus;
	/**
	 * 信息更新日期
	 */
	@Column(name = "Info_Update_Date")
	private String infoUpdateDate;

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

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public String getResideAddr() {
		return resideAddr;
	}

	public void setResideAddr(String resideAddr) {
		this.resideAddr = resideAddr;
	}

	public String getResideStatus() {
		return resideStatus;
	}

	public void setResideStatus(String resideStatus) {
		this.resideStatus = resideStatus;
	}

	public String getInfoUpdateDate() {
		return infoUpdateDate;
	}

	public void setInfoUpdateDate(String infoUpdateDate) {
		this.infoUpdateDate = infoUpdateDate;
	}

}
