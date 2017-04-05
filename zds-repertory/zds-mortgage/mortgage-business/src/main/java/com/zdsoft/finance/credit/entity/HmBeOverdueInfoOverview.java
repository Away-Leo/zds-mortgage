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
 * @Title: HmBeOverdueInfoOverview.java
 * @ClassName: HmBeOverdueInfoOverview
 * @Description: 逾期及违约信息概要
 * @author gufeng
 * @date 2017年2月23日 上午9:38:25
 * @version V1.0
 */
@Entity
@Table(name = "hm_be_over_due_info_over_view")
public class HmBeOverdueInfoOverview extends BaseEntity {

	private static final long serialVersionUID = 404750530518774574L;
	/**
	 * 查询表ID
	 */
	@Column(name = "query_Id")
	private String queryId;
	/**
	 * 身份证号
	 */
	@Column(name = "id_card")
	private String idCard;
	/**
	 * 创建时间
	 */
	@Column(name = "create_time")
	private Timestamp createTime;

	/**
	 * 呆账信息汇总笔数
	 */
	@Column(name = "bad_Debts_Info_Sum_Number")
	private Integer badDebtsInfoSumNumber;
	/**
	 * 呆账信息汇余额
	 */
	@Column(name = "Bad_Debts_Info_Sum_Amount")
	private BigDecimal badDebtsInfoSumAmount = BigDecimal.ZERO;

	/**
	 * 资产处置信息汇总笔数
	 */
	@Column(name = "Assets_Disposal_Info_Sum_Numb")
	private Integer assetsDisposalInfoSumNumber;
	/**
	 * 资产处置信息汇总余额
	 */
	@Column(name = "Assets_Disposal_Info_Sum_Amou")
	private BigDecimal assetsDisposalInfoSumAmount = BigDecimal.ZERO;

	/**
	 * 保证人代偿信息汇总笔数
	 */
	@Column(name = "Guarantor_Compen_Info_Sum_Numb")
	private Integer guarantorCompenInfoSumNumber;
	/**
	 * 保证人代偿信息汇总余额
	 */
	@Column(name = "Guarantor_Compen_Info_Sum_Amo")
	private BigDecimal guarantorCompenInfoSumAmount = BigDecimal.ZERO;

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

	public Integer getBadDebtsInfoSumNumber() {
		return badDebtsInfoSumNumber;
	}

	public void setBadDebtsInfoSumNumber(Integer badDebtsInfoSumNumber) {
		this.badDebtsInfoSumNumber = badDebtsInfoSumNumber;
	}

	public BigDecimal getBadDebtsInfoSumAmount() {
		return badDebtsInfoSumAmount;
	}

	public void setBadDebtsInfoSumAmount(BigDecimal badDebtsInfoSumAmount) {
		this.badDebtsInfoSumAmount = badDebtsInfoSumAmount;
	}

	public Integer getAssetsDisposalInfoSumNumber() {
		return assetsDisposalInfoSumNumber;
	}

	public void setAssetsDisposalInfoSumNumber(Integer assetsDisposalInfoSumNumber) {
		this.assetsDisposalInfoSumNumber = assetsDisposalInfoSumNumber;
	}

	public BigDecimal getAssetsDisposalInfoSumAmount() {
		return assetsDisposalInfoSumAmount;
	}

	public void setAssetsDisposalInfoSumAmount(BigDecimal assetsDisposalInfoSumAmount) {
		this.assetsDisposalInfoSumAmount = assetsDisposalInfoSumAmount;
	}

	public Integer getGuarantorCompenInfoSumNumber() {
		return guarantorCompenInfoSumNumber;
	}

	public void setGuarantorCompenInfoSumNumber(Integer guarantorCompenInfoSumNumber) {
		this.guarantorCompenInfoSumNumber = guarantorCompenInfoSumNumber;
	}

	public BigDecimal getGuarantorCompenInfoSumAmount() {
		return guarantorCompenInfoSumAmount;
	}

	public void setGuarantorCompenInfoSumAmount(BigDecimal guarantorCompenInfoSumAmount) {
		this.guarantorCompenInfoSumAmount = guarantorCompenInfoSumAmount;
	}

}
