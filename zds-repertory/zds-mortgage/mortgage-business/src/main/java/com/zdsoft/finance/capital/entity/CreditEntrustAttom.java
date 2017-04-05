package com.zdsoft.finance.capital.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import com.zdsoft.framework.core.common.domain.BaseEntity;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: CreditEntrustAttom.java 
 * @ClassName: CreditEntrustAttom 
 * @Description: 信托计划转让
 * @author liuwei 
 * @date 2017年2月8日 上午10:23:09 
 * @version V1.0
 */
@Entity
@Table(name = "cptl_credit_entrust_attorm")
public class CreditEntrustAttom extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2966233671607795083L;

	/**
	 * 受让方类型
	 */
	@Column(length = 32)
	private String acceptType;

	/**
	 * 受让方名称
	 */
	@Column(length = 64)
	private String acceptName;

	/**
	 * 转让状态
	 */
	@Column(length = 32)
	private String attomState;

	/**
	 * 身份证号码
	 */
	@Column(length = 18)
	private String cardNo;

	/**
	 * 联系地址
	 */
	@Column(length = 256)
	private String address;

	/**
	 * 移动电话
	 */
	@Column(length = 32)
	private String mobile;

	/**
	 * 固定费用
	 */
	@Column(precision = 18, scale = 6)
	private BigDecimal fixedFee = BigDecimal.ZERO;

	/**
	 * 财务费用
	 */
	@Column(precision = 18, scale = 6)
	private BigDecimal financeFee = BigDecimal.ZERO;

	/**
	 * 受让金额
	 */
	@Column(precision = 18, scale = 6)
	private BigDecimal acceptAmount = BigDecimal.ZERO;

	/**
	 * 合同收益率
	 */
	@Column(precision = 18, scale = 6)
	private Double contractProfitRate;

	/**
	 * 转让生效时间
	 */
	@Column
	private Long attomEffect;

	/**
	 * 单一合同编号
	 */
	@Column(length = 32)
	private String contractNo;

	/**
	 * 转让合同编号
	 */
	@Column(length = 32)
	private String attomContractNo;

	/**
	 * 转让截止日期
	 */
	@Column
	private Long attomEndDate;

	/**
	 * 受让人开户行
	 */
	@Column(length = 32)
	private String assigneeAccBank;

	/**
	 * 受让人账户名
	 */
	@Column(length = 64)
	private String assigneeAccName;

	/**
	 * 受让人账号
	 */
	@Column(length = 32)
	private String assigneeAccNo;

	/**
	 * 派息周期
	 */
	@Column(length = 32)
	private String payoutPeriod;

	/**
	 * 付息日
	 */
	@Column
	private Integer termDay;

	/**
	 * 提交日期
	 */
	@Column
	private Long completeDate;

	/**
	 * 备注
	 */
	@Column(length = 256)
	private String remark;

	/**
	 * 提交状态
	 */
	@Column
	private Integer status;

	/**
	 * 转让状态
	 */
	@Column
	private Integer attomStatus;

	/**
	 * 提交人
	 */
	@Column(length = 32)
	private String completeEmpCd;
	@Column(length = 64)
	private String completeEmpName;

	/**
	 * 组织机构代码
	 */
	@Column(length = 32)
	private String orgCd;

	/**
	 * 联系人名称
	 */
	@Column(length = 32)
	private String contactName;

	/**
	 * 固定电话
	 */
	@Column(length = 32)
	private String phone;

	@ManyToOne(fetch = FetchType.LAZY)
	@Cascade({ CascadeType.ALL })
	@JoinColumn(name = "creditEntrustId")
	private CreditEntrust creditEntrust;

	@Column(length = 32)
	private String tempUuid;

	public String getAcceptType() {
		return acceptType;
	}

	public void setAcceptType(String acceptType) {
		this.acceptType = acceptType;
	}

	public String getAcceptName() {
		return acceptName;
	}

	public void setAcceptName(String acceptName) {
		this.acceptName = acceptName;
	}

	public String getAttomState() {
		return attomState;
	}

	public void setAttomState(String attomState) {
		this.attomState = attomState;
	}

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public BigDecimal getFixedFee() {
		return fixedFee;
	}

	public void setFixedFee(BigDecimal fixedFee) {
		this.fixedFee = fixedFee;
	}

	public BigDecimal getFinanceFee() {
		return financeFee;
	}

	public void setFinanceFee(BigDecimal financeFee) {
		this.financeFee = financeFee;
	}

	public BigDecimal getAcceptAmount() {
		return acceptAmount;
	}

	public void setAcceptAmount(BigDecimal acceptAmount) {
		this.acceptAmount = acceptAmount;
	}

	public Double getContractProfitRate() {
		return contractProfitRate;
	}

	public void setContractProfitRate(Double contractProfitRate) {
		this.contractProfitRate = contractProfitRate;
	}

	public Long getAttomEffect() {
		return attomEffect;
	}

	public void setAttomEffect(Long attomEffect) {
		this.attomEffect = attomEffect;
	}

	public String getContractNo() {
		return contractNo;
	}

	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}

	public String getAttomContractNo() {
		return attomContractNo;
	}

	public void setAttomContractNo(String attomContractNo) {
		this.attomContractNo = attomContractNo;
	}

	public Long getAttomEndDate() {
		return attomEndDate;
	}

	public void setAttomEndDate(Long attomEndDate) {
		this.attomEndDate = attomEndDate;
	}

	public String getAssigneeAccBank() {
		return assigneeAccBank;
	}

	public void setAssigneeAccBank(String assigneeAccBank) {
		this.assigneeAccBank = assigneeAccBank;
	}

	public String getAssigneeAccName() {
		return assigneeAccName;
	}

	public void setAssigneeAccName(String assigneeAccName) {
		this.assigneeAccName = assigneeAccName;
	}

	public String getAssigneeAccNo() {
		return assigneeAccNo;
	}

	public void setAssigneeAccNo(String assigneeAccNo) {
		this.assigneeAccNo = assigneeAccNo;
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

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public CreditEntrust getCreditEntrust() {
		return creditEntrust;
	}

	public void setCreditEntrust(CreditEntrust creditEntrust) {
		this.creditEntrust = creditEntrust;
	}

	public Long getCompleteDate() {
		return completeDate;
	}

	public void setCompleteDate(Long completeDate) {
		this.completeDate = completeDate;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
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

	public Integer getAttomStatus() {
		return attomStatus;
	}

	public void setAttomStatus(Integer attomStatus) {
		this.attomStatus = attomStatus;
	}

	public String getOrgCd() {
		return orgCd;
	}

	public void setOrgCd(String orgCd) {
		this.orgCd = orgCd;
	}

	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getTempUuid() {
		return tempUuid;
	}

	public void setTempUuid(String tempUuid) {
		this.tempUuid = tempUuid;
	}

}
