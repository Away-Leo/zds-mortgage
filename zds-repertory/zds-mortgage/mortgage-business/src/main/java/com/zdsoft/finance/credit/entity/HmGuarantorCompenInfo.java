package com.zdsoft.finance.credit.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.zdsoft.framework.core.common.domain.BaseEntity;

/**
 * 版权所有：重庆正大华日软件有限公司
 * 
 * @Title: HmGuarantorCompenInfo.java
 * @ClassName: HmGuarantorCompenInfo
 * @Description: 保证人代偿信息
 * @author gufeng
 * @date 2017年2月23日 上午9:39:06
 * @version V1.0
 */
@Entity
@Table(name = "hm_Guarantor_Compen_Info")
public class HmGuarantorCompenInfo extends BaseEntity {
	private static final long serialVersionUID = -7849960839037460738L;
	/**
	 * 查询表Id
	 */
	@Column(name = "query_Id")
	private String queryId;
	/**
	 * 查询表Id
	 */
	@Column(name = "id_card")
	private String idCard;
	/**
	 * 创建时间
	 */
	@Column(name = "create_time")
	private Timestamp createTime;

	/**
	 * 编号
	 */
	@Column(name = "no")
	private Integer no;

	/**
	 * 编号
	 */
	@Column(name = "Compensation_Organization")
	private String compensationOrganization;

	/**
	 * 最近一次代偿日期
	 */
	@Column(name = "Last_Compensation_Date")
	private String lastCompensationDate;

	/**
	 * 最近一次代偿日期
	 */
	@Column(name = "Compensation_Sum")
	private BigDecimal compensationSum = BigDecimal.ZERO;

	/**
	 * 最近一次还款日期
	 */
	@Column(name = "Last_PayOff_Date")
	private String lastPayOffDate;

	/**
	 * 余额
	 */
	@Column(name = "Compensation_Overage")
	private BigDecimal compensationOverage = BigDecimal.ZERO;

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

	public Integer getNo() {
		return no;
	}

	public void setNo(Integer no) {
		this.no = no;
	}

	public String getCompensationOrganization() {
		return compensationOrganization;
	}

	public void setCompensationOrganization(String compensationOrganization) {
		this.compensationOrganization = compensationOrganization;
	}

	public String getLastCompensationDate() {
		return lastCompensationDate;
	}

	public void setLastCompensationDate(String lastCompensationDate) {
		this.lastCompensationDate = lastCompensationDate;
	}

	public BigDecimal getCompensationSum() {
		return compensationSum;
	}

	public void setCompensationSum(BigDecimal compensationSum) {
		this.compensationSum = compensationSum;
	}

	public String getLastPayOffDate() {
		return lastPayOffDate;
	}

	public void setLastPayOffDate(String lastPayOffDate) {
		this.lastPayOffDate = lastPayOffDate;
	}

	public BigDecimal getCompensationOverage() {
		return compensationOverage;
	}

	public void setCompensationOverage(BigDecimal compensationOverage) {
		this.compensationOverage = compensationOverage;
	}

}
