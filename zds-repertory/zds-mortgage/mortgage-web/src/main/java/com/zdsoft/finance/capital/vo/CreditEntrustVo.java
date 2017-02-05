package com.zdsoft.finance.capital.vo;

import java.math.BigDecimal;

import com.zdsoft.finance.capital.entity.CreditEntrust;
import com.zdsoft.finance.common.base.BaseVo;
import com.zdsoft.finance.cooperator.entity.Capitalist;
import com.zdsoft.framework.core.common.util.ObjectHelper;

/**
 * 信托计划Vo
 * 
 * @createTime:2017年1月10日
 * @author liuwei
 * @version 1.0
 */
public class CreditEntrustVo extends BaseVo<CreditEntrust> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9058725306081238716L;

	/**
	 * 资方id
	 */
	private String capitalistId;

	/**
	 * 资方名称
	 */
	private String cooperatorName;

	/**
	 * 成立时间
	 */
	private Long establishmentDate;

	/**
	 * 是否主计划
	 */
	private Boolean isMasterPlan;

	/**
	 * 信托类型
	 */
	private String trustType;

	/**
	 * 合同费率
	 */
	private Double contractRate;

	/**
	 * 信托计划名称
	 */
	private String creditEntrustName;

	/**
	 * 本金规模
	 */
	private String principaScale;

	/**
	 * 转让状态
	 */
	private String assignmentState;

	/**
	 * 转让规模
	 */
	private String assignmentScale;

	/**
	 * 最低限额
	 */
	private BigDecimal minQuota;

	/**
	 * 服务费率
	 */
	private Double severRate;

	/**
	 * 保管费率
	 */
	private Double keepRate;

	/**
	 * 管理费率
	 */
	private Double managerRate;

	/**
	 * 待拨户开户行
	 */
	private String waitApprBank;

	/**
	 * 待拨户账户名
	 */
	private String waitApprName;

	/**
	 * 待拨户账号
	 */
	private String waitApprNo;

	/**
	 * 归集户开户行
	 */
	private String collectionAccountBank;

	/**
	 * 归集户账户名
	 */
	private String collectionAccountName;

	/**
	 * 归集户账号
	 */
	private String collectionAccountNo;

	/**
	 * 专户开户行
	 */
	private String specialAccountBank;

	/**
	 * 专户账户名
	 */
	private String specialAccountName;

	/**
	 * 专户账号
	 */
	private String specialAccountNo;

	/**
	 * 备付户开户行
	 */
	private String spareAccountBank;

	/**
	 * 备付户账户名
	 */
	private String spareAccountName;

	/**
	 * 备付户账号
	 */
	private String spareAccountNo;

	/**
	 * 是否开放申请额度
	 */
	private Boolean isOpenApply;

	/**
	 * 是否资金自动匹配
	 */
	private Boolean isAutoMatch;

	/**
	 * 截留额度
	 */
	private BigDecimal retain;

	/**
	 * 备注
	 */
	private String remark;

	/**
	 * 临时id
	 */
	private String tempUuid;

	/**
	 * 清分账号
	 */
	private String clearingAccount;

	/**
	 * 商户id
	 */
	private String merchantID;

	public String getTrustType() {
		return trustType;
	}

	public void setTrustType(String trustType) {
		this.trustType = trustType;
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

	public String getTempUuid() {
		return tempUuid;
	}

	public void setTempUuid(String tempUuid) {
		this.tempUuid = tempUuid;
	}

	public String getCapitalistId() {
		return capitalistId;
	}

	public void setCapitalistId(String capitalistId) {
		this.capitalistId = capitalistId;
	}

	public String getCooperatorName() {
		return cooperatorName;
	}

	public void setCooperatorName(String cooperatorName) {
		this.cooperatorName = cooperatorName;
	}

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

	public CreditEntrustVo() {
		super();
	}

	public CreditEntrustVo(CreditEntrust creditEntrust) {
		super(creditEntrust);

		Capitalist capitalist = creditEntrust.getCapitallist();
		if (ObjectHelper.isNotEmpty(capitalist)) {
			this.setCapitalistId(capitalist.getId());
			this.setCooperatorName(capitalist.getCooperatorName());
		}
	}

	public CreditEntrustVo(CreditEntrust creditEntrust, String[] args, String[] simpleArgs) {
		super(creditEntrust, args, simpleArgs);
	}

	public CreditEntrust toPo() {
		CreditEntrust creditEntrust = new CreditEntrust();
		return super.toPo(this, creditEntrust);
	}

}
