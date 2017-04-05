package com.zdsoft.finance.credit.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;

import com.zdsoft.framework.core.common.domain.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 版权所有：重庆正大华日软件有限公司
 * 
 * @Title: HmAssetsDisposalInfo.java
 * @ClassName: HmAssetsDisposalInfo
 * @Description: 资产处置信息
 * @author gufeng
 * @date 2017年2月23日 上午9:38:18
 * @version V1.0
 */
@Entity
@Table(name = "hm_assets_disposal_info")
public class HmAssetsDisposalInfo extends BaseEntity {
	private static final long serialVersionUID = 4057827950051823634L;
	/**
	 * 征信查询id
	 */
	@Column(name = "query_id")
	private String queryId;
	/**
	 * 证件号
	 */
	@Column(name = "id_card")
	private String idCard;
	/**
	 * 编号
	 */
	@Column(name = "No")
	private int No;
	/**
	 * 资产管理公司
	 */
	@Column(name = "Asset_Management_Co")
	private String AssetManagementCo;
	/**
	 * 债务接收日
	 */
	@Column(name = "Debt_Receive_Date")
	private String DebtReceiveDate;
	/**
	 * 接收的债权金额
	 */
	@Column(name = "Receive_Rights_Amount")
	private BigDecimal ReceiveRightsAmount = BigDecimal.ZERO;
	/**
	 * 最近一次还款日期
	 */
	@Column(name = "Last_Pay_Off_Date")
	private String LastPayOffDate;
	/**
	 * 资产处置余额
	 */
	@Column(name = "Asset_Disposal_Overage")
	private BigDecimal AssetDisposalOverage = BigDecimal.ZERO;
	/**
	 * 创建时间
	 */
	@Column(name = "create_time")
	private Date createTime;

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

	public int getNo() {
		return No;
	}

	public void setNo(int No) {
		this.No = No;
	}

	public String getAssetManagementCo() {
		return AssetManagementCo;
	}

	public void setAssetManagementCo(String AssetManagementCo) {
		this.AssetManagementCo = AssetManagementCo;
	}

	public String getDebtReceiveDate() {
		return DebtReceiveDate;
	}

	public void setDebtReceiveDate(String DebtReceiveDate) {
		this.DebtReceiveDate = DebtReceiveDate;
	}

	public BigDecimal getReceiveRightsAmount() {
		return ReceiveRightsAmount;
	}

	public void setReceiveRightsAmount(BigDecimal ReceiveRightsAmount) {
		this.ReceiveRightsAmount = ReceiveRightsAmount;
	}

	public String getLastPayOffDate() {
		return LastPayOffDate;
	}

	public void setLastPayOffDate(String LastPayOffDate) {
		this.LastPayOffDate = LastPayOffDate;
	}

	public BigDecimal getAssetDisposalOverage() {
		return AssetDisposalOverage;
	}

	public void setAssetDisposalOverage(BigDecimal AssetDisposalOverage) {
		this.AssetDisposalOverage = AssetDisposalOverage;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
}
