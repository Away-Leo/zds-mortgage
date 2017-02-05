package com.zdsoft.finance.capital.entity;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.Type;

import com.zdsoft.finance.cooperator.entity.Capitalist;
import com.zdsoft.framework.core.common.domain.BaseEntity;

/**
 * 信托计划
 * 
 * @createTime:2017年1月10日
 * @author liuwei
 * @version 1.0
 */
@Entity
@Table(name = "caal_credit_entrust")
public class CreditEntrust extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2006175870147776982L;

	/**
	 * 资方
	 */
	@ManyToOne
	@JoinColumn(name="capitalistId", nullable=false)
	private Capitalist capitallist;

	/**
	 * 成立时间
	 */
	@Column
	private Long establishmentDate;

	/**
	 * 是否主计划
	 */
	@Column
	@Type(type = "true_false")
	private Boolean isMasterPlan = Boolean.valueOf(false);

	/**
	 * 信托类型
	 */
	@Column(length = 32)
	private String trustType;

	/**
	 * 合同费率
	 */
	@Column(precision = 30, scale = 12)
	private Double contractRate;

	/**
	 * 信托计划名称
	 */
	@Column(length = 64)
	private String creditEntrustName;

	/**
	 * 本金规模
	 */
	@Column(length = 16)
	private String principaScale;

	/**
	 * 转让状态
	 */
	@Column(length = 32)
	private String assignmentState;

	/**
	 * 转让规模
	 */
	@Column(length = 16)
	private String assignmentScale;

	/**
	 * 最低限额
	 */
	@Column(precision = 16, scale = 4)
	private BigDecimal minQuota;

	/**
	 * 服务费率
	 */
	@Column(precision = 30, scale = 12)
	private Double severRate;

	/**
	 * 保管费率
	 */
	@Column(precision = 30, scale = 12)
	private Double keepRate;

	/**
	 * 管理费率
	 */
	@Column(precision = 30, scale = 12)
	private Double managerRate;

	/**
	 * 待拨户开户行
	 */
	@Column(length = 64)
	private String waitApprBank;

	/**
	 * 待拨户账户名
	 */
	@Column(length = 32)
	private String waitApprName;

	/**
	 * 待拨户账号
	 */
	@Column(length = 32)
	private String waitApprNo;

	/**
	 * 归集户开户行
	 */
	@Column(length = 64)
	private String collectionAccountBank;

	/**
	 * 归集户账户名
	 */
	@Column(length = 32)
	private String collectionAccountName;

	/**
	 * 归集户账号
	 */
	@Column(length = 32)
	private String collectionAccountNo;

	/**
	 * 专户开户行
	 */
	@Column(length = 64)
	private String specialAccountBank;

	/**
	 * 专户账户名
	 */
	@Column(length = 32)
	private String specialAccountName;

	/**
	 * 专户账号
	 */
	@Column(length = 32)
	private String specialAccountNo;

	/**
	 * 备付户开户行
	 */
	@Column(length = 64)
	private String spareAccountBank;

	/**
	 * 备付户账户名
	 */
	@Column(length = 32)
	private String spareAccountName;

	/**
	 * 备付户账号
	 */
	@Column(length = 32)
	private String spareAccountNo;

	/**
	 * 是否开放申请额度
	 */
	@Column
	@Type(type = "true_false")
	private Boolean isOpenApply = Boolean.valueOf(false);

	/**
	 * 是否资金自动匹配
	 */
	@Column
	@Type(type = "true_false")
	private Boolean isAutoMatch = Boolean.valueOf(false);

	/**
	 * 截留额度
	 */
	@Column(precision = 16, scale = 4)
	private BigDecimal retain;

	/**
	 * 备注
	 */
	@Column(length = 256)
	private String remark;

	/**
	 * 临时id
	 */
	private transient String tempUuid;

	/**
	 * 备付金余额
	 */
	@Column(precision = 30, scale = 12)
	private BigDecimal paymentBalance;

	/**
	 * 收入余额
	 */
	@Column(precision = 30, scale = 12)
	private BigDecimal incomeBalance;

	/**
	 * 信托计划转让
	 */
	@OneToMany(mappedBy = "creditEntrust")
	@LazyCollection(LazyCollectionOption.TRUE)
	@Cascade({ CascadeType.ALL })
	private List<CreditEntrustAttom> creditEntrustAttoms;

	/**
	 * 信托计划本金投入
	 */
	@OneToMany(mappedBy = "creditEntrust")
	@LazyCollection(LazyCollectionOption.TRUE)
	@Cascade({ CascadeType.ALL })
	private List<CreditEntrustPrincipal> creditEntrustPrincipals;

	/**
	 * 备付资金跟踪
	 */
	@OneToMany(mappedBy = "creditEntrust")
	@LazyCollection(LazyCollectionOption.TRUE)
	@Cascade({ CascadeType.ALL })
	private List<SpareCapital> spareCapitals;

	/**
	 * 信托计划借方资金
	 */
	@OneToMany(mappedBy = "creditEntrust")
	@LazyCollection(LazyCollectionOption.TRUE)
	@Cascade({ CascadeType.ALL })
	private List<CreditEntrustDebit> creditEntrustDebits;

	/**
	 * 专户贷方资金跟踪
	 */
	@OneToMany(mappedBy = "creditEntrust")
	@LazyCollection(LazyCollectionOption.TRUE)
	@Cascade({ CascadeType.ALL })
	private List<LoanCapital> loanCapitals;

	/**
	 * 应付资金跟踪
	 */
	@OneToMany(mappedBy = "creditEntrust")
	@LazyCollection(LazyCollectionOption.TRUE)
	@Cascade({ CascadeType.ALL })
	private List<CreditCostTracking> creditCostTrackings;

	/**
	 * 本金赎回
	 */
	@OneToMany(mappedBy = "creditEntrust")
	@LazyCollection(LazyCollectionOption.TRUE)
	@Cascade({ CascadeType.ALL })
	private List<CreditEntrustRedemPrinci> creditEntrustRedemPrincis;

	/**
	 * 清分账号
	 */
	@Column(length = 32)
	private String clearingAccount;

	/**
	 * 商户id
	 */
	@Column(length = 32)
	private String merchantID;

	public Long getEstablishmentDate() {
		return establishmentDate;
	}

	public void setEstablishmentDate(Long establishmentDate) {
		this.establishmentDate = establishmentDate;
	}

	public Boolean getIsMasterPlan() {
		return isMasterPlan;
	}

	public void setIsMasterPlan(Boolean isMasterPlan) {
		this.isMasterPlan = isMasterPlan;
	}

	public Double getContractRate() {
		return contractRate;
	}

	public void setContractRate(Double contractRate) {
		this.contractRate = contractRate;
	}

	public String getCreditEntrustName() {
		return creditEntrustName;
	}

	public void setCreditEntrustName(String creditEntrustName) {
		this.creditEntrustName = creditEntrustName;
	}

	public String getPrincipaScale() {
		return principaScale;
	}

	public void setPrincipaScale(String principaScale) {
		this.principaScale = principaScale;
	}

	public String getAssignmentState() {
		return assignmentState;
	}

	public void setAssignmentState(String assignmentState) {
		this.assignmentState = assignmentState;
	}

	public String getAssignmentScale() {
		return assignmentScale;
	}

	public void setAssignmentScale(String assignmentScale) {
		this.assignmentScale = assignmentScale;
	}

	public BigDecimal getMinQuota() {
		return minQuota;
	}

	public void setMinQuota(BigDecimal minQuota) {
		this.minQuota = minQuota;
	}

	public Double getSeverRate() {
		return severRate;
	}

	public void setSeverRate(Double severRate) {
		this.severRate = severRate;
	}

	public Double getKeepRate() {
		return keepRate;
	}

	public void setKeepRate(Double keepRate) {
		this.keepRate = keepRate;
	}

	public Double getManagerRate() {
		return managerRate;
	}

	public void setManagerRate(Double managerRate) {
		this.managerRate = managerRate;
	}

	public String getWaitApprBank() {
		return waitApprBank;
	}

	public void setWaitApprBank(String waitApprBank) {
		this.waitApprBank = waitApprBank;
	}

	public String getWaitApprName() {
		return waitApprName;
	}

	public void setWaitApprName(String waitApprName) {
		this.waitApprName = waitApprName;
	}

	public String getWaitApprNo() {
		return waitApprNo;
	}

	public void setWaitApprNo(String waitApprNo) {
		this.waitApprNo = waitApprNo;
	}

	public String getCollectionAccountBank() {
		return collectionAccountBank;
	}

	public void setCollectionAccountBank(String collectionAccountBank) {
		this.collectionAccountBank = collectionAccountBank;
	}

	public String getCollectionAccountName() {
		return collectionAccountName;
	}

	public void setCollectionAccountName(String collectionAccountName) {
		this.collectionAccountName = collectionAccountName;
	}

	public String getCollectionAccountNo() {
		return collectionAccountNo;
	}

	public void setCollectionAccountNo(String collectionAccountNo) {
		this.collectionAccountNo = collectionAccountNo;
	}

	public String getSpecialAccountBank() {
		return specialAccountBank;
	}

	public void setSpecialAccountBank(String specialAccountBank) {
		this.specialAccountBank = specialAccountBank;
	}

	public String getSpecialAccountName() {
		return specialAccountName;
	}

	public void setSpecialAccountName(String specialAccountName) {
		this.specialAccountName = specialAccountName;
	}

	public String getSpecialAccountNo() {
		return specialAccountNo;
	}

	public void setSpecialAccountNo(String specialAccountNo) {
		this.specialAccountNo = specialAccountNo;
	}

	public String getSpareAccountBank() {
		return spareAccountBank;
	}

	public void setSpareAccountBank(String spareAccountBank) {
		this.spareAccountBank = spareAccountBank;
	}

	public String getSpareAccountName() {
		return spareAccountName;
	}

	public void setSpareAccountName(String spareAccountName) {
		this.spareAccountName = spareAccountName;
	}

	public String getSpareAccountNo() {
		return spareAccountNo;
	}

	public void setSpareAccountNo(String spareAccountNo) {
		this.spareAccountNo = spareAccountNo;
	}

	public Boolean getIsOpenApply() {
		return isOpenApply;
	}

	public void setIsOpenApply(Boolean isOpenApply) {
		this.isOpenApply = isOpenApply;
	}

	public Boolean getIsAutoMatch() {
		return isAutoMatch;
	}

	public void setIsAutoMatch(Boolean isAutoMatch) {
		this.isAutoMatch = isAutoMatch;
	}

	public BigDecimal getRetain() {
		return retain;
	}

	public void setRetain(BigDecimal retain) {
		this.retain = retain;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public List<CreditEntrustAttom> getCreditEntrustAttoms() {
		return creditEntrustAttoms;
	}

	public void setCreditEntrustAttoms(List<CreditEntrustAttom> creditEntrustAttoms) {
		this.creditEntrustAttoms = creditEntrustAttoms;
	}

	public List<CreditEntrustPrincipal> getCreditEntrustPrincipals() {
		return creditEntrustPrincipals;
	}

	public void setCreditEntrustPrincipals(List<CreditEntrustPrincipal> creditEntrustPrincipals) {
		this.creditEntrustPrincipals = creditEntrustPrincipals;
	}

	public List<SpareCapital> getSpareCapitals() {
		return spareCapitals;
	}

	public void setSpareCapitals(List<SpareCapital> spareCapitals) {
		this.spareCapitals = spareCapitals;
	}

	public List<CreditEntrustDebit> getCreditEntrustDebits() {
		return creditEntrustDebits;
	}

	public void setCreditEntrustDebits(List<CreditEntrustDebit> creditEntrustDebits) {
		this.creditEntrustDebits = creditEntrustDebits;
	}

	public List<LoanCapital> getLoanCapitals() {
		return loanCapitals;
	}

	public void setLoanCapitals(List<LoanCapital> loanCapitals) {
		this.loanCapitals = loanCapitals;
	}

	public List<CreditCostTracking> getCreditCostTrackings() {
		return creditCostTrackings;
	}

	public void setCreditCostTrackings(List<CreditCostTracking> creditCostTrackings) {
		this.creditCostTrackings = creditCostTrackings;
	}

	public String getTrustType() {
		return trustType;
	}

	public void setTrustType(String trustType) {
		this.trustType = trustType;
	}

	public String getTempUuid() {
		return tempUuid;
	}

	public void setTempUuid(String tempUuid) {
		this.tempUuid = tempUuid;
	}

	public Capitalist getCapitallist() {
		return capitallist;
	}

	public void setCapitallist(Capitalist capitallist) {
		this.capitallist = capitallist;
	}

	public BigDecimal getPaymentBalance() {
		return paymentBalance;
	}

	public void setPaymentBalance(BigDecimal paymentBalance) {
		this.paymentBalance = paymentBalance;
	}

	public BigDecimal getIncomeBalance() {
		return incomeBalance;
	}

	public void setIncomeBalance(BigDecimal incomeBalance) {
		this.incomeBalance = incomeBalance;
	}

	public String getClearingAccount() {
		return clearingAccount;
	}

	public void setClearingAccount(String clearingAccount) {
		this.clearingAccount = clearingAccount;
	}

	public String getMerchantID() {
		return merchantID;
	}

	public void setMerchantID(String merchantID) {
		this.merchantID = merchantID;
	}

	public List<CreditEntrustRedemPrinci> getCreditEntrustRedemPrincis() {
		return creditEntrustRedemPrincis;
	}

	public void setCreditEntrustRedemPrincis(List<CreditEntrustRedemPrinci> creditEntrustRedemPrincis) {
		this.creditEntrustRedemPrincis = creditEntrustRedemPrincis;
	}

}
