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
 * @Title: HmPerSummaryGuarantee.java
 * @ClassName: HmPerSummaryGuarantee
 * @Description: 外担保信息汇总
 * @author gufeng
 * @date 2017年2月23日 上午9:41:04
 * @version V1.0
 */
@Entity
@Table(name = "hm_Per_Summary_Guarantee")
public class HmPerSummaryGuarantee extends BaseEntity {

	private static final long serialVersionUID = 3963242300589139233L;
	/**
	 * 信息表ID
	 */
	@Column(name = "query_Id")
	private String queryId;
	/**
	 * 证件号码
	 */
	@Column(name = "id_card")
	private String idCard;
	/**
	 * 创建时间
	 */
	@Column(name = "create_time")
	private Timestamp createTime;

	/**
	 * 担保笔数
	 */
	@Column(name = "Per_Guar_Mount")
	private Integer perGuarMount;

	/**
	 * 担保金额
	 */
	@Column(name = "Per_Guar_Amount")
	private BigDecimal perGuarAmount = BigDecimal.ZERO;

	/**
	 * 担保本金金额
	 */
	@Column(name = "Per_Guar_Principal_Amount")
	private BigDecimal perGuarPrincipalAmount = BigDecimal.ZERO;

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

	public Integer getPerGuarMount() {
		return perGuarMount;
	}

	public void setPerGuarMount(Integer perGuarMount) {
		this.perGuarMount = perGuarMount;
	}

	public BigDecimal getPerGuarAmount() {
		return perGuarAmount;
	}

	public void setPerGuarAmount(BigDecimal perGuarAmount) {
		this.perGuarAmount = perGuarAmount;
	}

	public BigDecimal getPerGuarPrincipalAmount() {
		return perGuarPrincipalAmount;
	}

	public void setPerGuarPrincipalAmount(BigDecimal perGuarPrincipalAmount) {
		this.perGuarPrincipalAmount = perGuarPrincipalAmount;
	}

}
