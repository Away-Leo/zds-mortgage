package com.zdsoft.finance.capital.vo;

import java.math.BigDecimal;

import com.zdsoft.finance.capital.entity.CreditEntrustRedemPrinci;
import com.zdsoft.finance.common.base.BaseVo;
import com.zdsoft.finance.spi.common.dto.StatusNm;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: CreditEntrustRedemPrinciVo.java 
 * @ClassName: CreditEntrustRedemPrinciVo 
 * @Description: 本金赎回Vo
 * @author liuwei
 * @date 2017年3月6日 下午4:20:22 
 * @version V1.0
 */
public class CreditEntrustRedemPrinciVo extends BaseVo<CreditEntrustRedemPrinci> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2214152220098183559L;

	/**
	 * 出资方
	 */
	private String contribution;

	/**
	 * 出资方类型
	 */
	private String contributionType;

	/**
	 * 出资方类型
	 */
	private String contributionTypeName;

	/**
	 * 赎回金额
	 */
	private BigDecimal redemptionAmount;

	/**
	 * 备注
	 */
	private String remark;

	/**
	 * 提交状态
	 */
	private Integer status;

	private String statusName;

	/**
	 * 临时id
	 */
	private String tempUuid;

	/**
	 * 信托计划id
	 */
	private String creditEntrustId;

	/**
	 * 提交人
	 */
	private String completeEmpCd;
	private String completeEmpName;

	/**
	 * 提交日期
	 */
	private Long completeDate;

	public String getContributionTypeName() {
		return contributionTypeName;
	}

	public void setContributionTypeName(String contributionTypeName) {
		this.contributionTypeName = contributionTypeName;
	}

	public String getContribution() {
		return contribution;
	}

	public void setContribution(String contribution) {
		this.contribution = contribution;
	}

	public String getContributionType() {
		return contributionType;
	}

	public void setContributionType(String contributionType) {
		this.contributionType = contributionType;
	}

	public BigDecimal getRedemptionAmount() {
		return redemptionAmount;
	}

	public void setRedemptionAmount(BigDecimal redemptionAmount) {
		this.redemptionAmount = redemptionAmount;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getTempUuid() {
		return tempUuid;
	}

	public void setTempUuid(String tempUuid) {
		this.tempUuid = tempUuid;
	}

	public String getCreditEntrustId() {
		return creditEntrustId;
	}

	public void setCreditEntrustId(String creditEntrustId) {
		this.creditEntrustId = creditEntrustId;
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public String getCompleteEmpCd() {
		return completeEmpCd;
	}

	public void setCompleteEmpCd(String completeEmpCd) {
		this.completeEmpCd = completeEmpCd;
	}

	public String getCompleteEmpName() {
		return completeEmpName;
	}

	public void setCompleteEmpName(String completeEmpName) {
		this.completeEmpName = completeEmpName;
	}

	public Long getCompleteDate() {
		return completeDate;
	}

	public void setCompleteDate(Long completeDate) {
		this.completeDate = completeDate;
	}

	public CreditEntrustRedemPrinciVo() {
		super();
	}

	public CreditEntrustRedemPrinciVo(CreditEntrustRedemPrinci creditEntrustRedemPrinci) {
		super(creditEntrustRedemPrinci);
		this.setStatusName(StatusNm.getName(creditEntrustRedemPrinci.getStatus()));
	}

	public CreditEntrustRedemPrinciVo(CreditEntrustRedemPrinci creditEntrustRedemPrinci, String[] args,
			String[] simpleArgs) {
		super(creditEntrustRedemPrinci, args, simpleArgs);
		this.setStatusName(StatusNm.getName(creditEntrustRedemPrinci.getStatus()));
	}

	public CreditEntrustRedemPrinci toPo() {
		CreditEntrustRedemPrinci creditEntrustRedemPrinci = new CreditEntrustRedemPrinci();
		return super.toPo(this, creditEntrustRedemPrinci);
	}
}
