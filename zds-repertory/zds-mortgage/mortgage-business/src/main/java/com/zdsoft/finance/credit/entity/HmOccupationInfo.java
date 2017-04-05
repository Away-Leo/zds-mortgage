package com.zdsoft.finance.credit.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.zdsoft.framework.core.common.domain.BaseEntity;

/**
 * 版权所有：重庆正大华日软件有限公司
 * 
 * @Title: HmOccupationInfo.java
 * @ClassName: HmOccupationInfo
 * @Description: 职业信息
 * @author gufeng
 * @date 2017年2月23日 上午9:40:25
 * @version V1.0
 */
@Entity
@Table(name = "hm_Occupation_Info")
public class HmOccupationInfo extends BaseEntity {

	private static final long serialVersionUID = 2458324161628962607L;
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
	 * 序号
	 */
	@Column(name = "no")
	private String no;
	/**
	 * 工作单位
	 */
	@Column(name = "Company_Name")
	private String companyName;
	/**
	 * 公司地址
	 */
	@Column(name = "Company_Address")
	private String companyAddress;
	/**
	 * 职业
	 */
	@Column(name = "Occupation")
	private String occupation;
	/**
	 * 行业
	 */
	@Column(name = "Company_Type")
	private String companyType;
	/**
	 * 职位
	 */
	@Column(name = "Position")
	private String position;
	/**
	 * 职称
	 */
	@Column(name = "Post_Title")
	private String postTitle;
	/**
	 * 进入单位年份
	 */
	@Column(name = "Employe_dYear")
	private String employeDYear;
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

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getCompanyAddress() {
		return companyAddress;
	}

	public void setCompanyAddress(String companyAddress) {
		this.companyAddress = companyAddress;
	}

	public String getOccupation() {
		return occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

	public String getCompanyType() {
		return companyType;
	}

	public void setCompanyType(String companyType) {
		this.companyType = companyType;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getPostTitle() {
		return postTitle;
	}

	public void setPostTitle(String postTitle) {
		this.postTitle = postTitle;
	}

	public String getEmployeDYear() {
		return employeDYear;
	}

	public void setEmployeDYear(String employeDYear) {
		this.employeDYear = employeDYear;
	}

	public String getInfoUpdateDate() {
		return infoUpdateDate;
	}

	public void setInfoUpdateDate(String infoUpdateDate) {
		this.infoUpdateDate = infoUpdateDate;
	}

}
