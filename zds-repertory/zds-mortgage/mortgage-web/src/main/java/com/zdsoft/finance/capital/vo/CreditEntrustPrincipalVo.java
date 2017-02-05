package com.zdsoft.finance.capital.vo;

import java.math.BigDecimal;

import com.zdsoft.finance.capital.entity.CreditEntrustPrincipal;
import com.zdsoft.finance.common.base.BaseVo;
import com.zdsoft.finance.spi.common.dto.StatusNm;

/**
 * 信托计划本金投入
 * 
 * @createTime:2017年1月10日
 * @author liuwei
 * @version 1.0
 */
public class CreditEntrustPrincipalVo extends BaseVo<CreditEntrustPrincipal> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8165472379922050492L;

	/**
	 * 出资方
	 */
	private String contribution;

	/**
	 * 出资方类型
	 */
	private String contributionType;

	/**
	 * 出资方类型名称
	 */
	private String contributionTypeName;

	/**
	 * 初始本金
	 */
	private BigDecimal initialPrincipal;

	/**
	 * 本金金额
	 */
	private BigDecimal principalAmount;

	/**
	 * 合同收益率
	 */
	private Double profitRate;

	/**
	 * 追加日期
	 */
	private Long addDate;

	/**
	 * 使用比率
	 */
	private Double useProp;

	/**
	 * 预计到账日期
	 */
	private Long expectDate;

	/**
	 * 提交日期
	 */
	private Long completeDate;

	/**
	 * 实际到账日期
	 */
	private Long actualDate;

	/**
	 * 使用额度
	 */
	private BigDecimal usedQuota;

	/**
	 * 状态
	 */
	private Integer status;
	private String statusName;

	/**
	 * 备注
	 */
	private String remark;

	/**
	 * 提交人
	 */
	private String completeEmpCd;
	private String completeEmpName;

	/**
	 * 派息周期
	 */
	private String payoutPeriod;

	/**
	 * 派息周期Nm
	 */
	private String payoutPeriodName;

	/**
	 * 付息日
	 */
	private Integer termDay;

	/**
	 * 到期日期
	 */
	private Long maturityDate;

	/**
	 * 临时id
	 */
	private String tempUuid;

	/**
	 * 信托计划id
	 */
	private String creditEntrustId;

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

	public BigDecimal getPrincipalAmount() {
		return principalAmount;
	}

	public void setPrincipalAmount(BigDecimal principalAmount) {
		this.principalAmount = principalAmount;
	}

	public Double getProfitRate() {
		return profitRate;
	}

	public void setProfitRate(Double profitRate) {
		this.profitRate = profitRate;
	}

	public Long getAddDate() {
		return addDate;
	}

	public void setAddDate(Long addDate) {
		this.addDate = addDate;
	}

	public Double getUseProp() {
		return useProp;
	}

	public void setUseProp(Double useProp) {
		this.useProp = useProp;
	}

	public Long getExpectDate() {
		return expectDate;
	}

	public void setExpectDate(Long expectDate) {
		this.expectDate = expectDate;
	}

	public Long getActualDate() {
		return actualDate;
	}

	public void setActualDate(Long actualDate) {
		this.actualDate = actualDate;
	}

	public BigDecimal getUsedQuota() {
		return usedQuota;
	}

	public void setUsedQuota(BigDecimal usedQuota) {
		this.usedQuota = usedQuota;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public BigDecimal getInitialPrincipal() {
		return initialPrincipal;
	}

	public void setInitialPrincipal(BigDecimal initialPrincipal) {
		this.initialPrincipal = initialPrincipal;
	}

	public String getContributionTypeName() {
		return contributionTypeName;
	}

	public void setContributionTypeName(String contributionTypeName) {
		this.contributionTypeName = contributionTypeName;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Long getCompleteDate() {
		return completeDate;
	}

	public void setCompleteDate(Long completeDate) {
		this.completeDate = completeDate;
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

	public String getPayoutPeriod() {
		return payoutPeriod;
	}

	public void setPayoutPeriod(String payoutPeriod) {
		this.payoutPeriod = payoutPeriod;
	}

	public Integer getTermDay() {
		return termDay;
	}

	public void setTermDay(Integer termDay) {
		this.termDay = termDay;
	}

	public Long getMaturityDate() {
		return maturityDate;
	}

	public void setMaturityDate(Long maturityDate) {
		this.maturityDate = maturityDate;
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

	public String getPayoutPeriodName() {
		return payoutPeriodName;
	}

	public void setPayoutPeriodName(String payoutPeriodName) {
		this.payoutPeriodName = payoutPeriodName;
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public CreditEntrustPrincipalVo() {
		super();
	}

	public CreditEntrustPrincipalVo(CreditEntrustPrincipal creditEntrustPrincipal) {
		super(creditEntrustPrincipal);
		this.setStatusName(StatusNm.getName(creditEntrustPrincipal.getStatus()));
	}

	public CreditEntrustPrincipalVo(CreditEntrustPrincipal creditEntrustPrincipal, String[] args, String[] simpArgs) {
		super(creditEntrustPrincipal, args, simpArgs);
		this.setStatusName(StatusNm.getName(creditEntrustPrincipal.getStatus()));
	}

	public CreditEntrustPrincipal toPo() {
		CreditEntrustPrincipal principal = new CreditEntrustPrincipal();
		return super.toPo(this, principal);
	}
}
