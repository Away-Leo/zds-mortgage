package com.zdsoft.finance.capital.vo;

import java.math.BigDecimal;

import com.zdsoft.finance.capital.entity.CreditEntrustAttom;
import com.zdsoft.finance.common.base.BaseVo;
import com.zdsoft.finance.spi.common.dto.StatusNm;

/**
 * 信托计划转让Vo
 * 
 * @createTime:2017年1月10日
 * @author liuwei
 * @version 1.0
 */
public class CreditEntrustAttomVo extends BaseVo<CreditEntrustAttom> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4490518684999495973L;

	/**
	 * 受让方类型
	 */
	private String acceptType;

	/**
	 * 受让方类型名称
	 */
	private String acceptTypeName;

	/**
	 * 受让方名称
	 */
	private String acceptName;

	/**
	 * 转让状态
	 */
	private String attomState;

	/**
	 * 转让状态中文
	 */
	private String attomStateName;

	/**
	 * 身份证号码
	 */
	private String cardNo;

	/**
	 * 联系地址
	 */
	private String address;

	/**
	 * 移动电话
	 */
	private String mobile;

	/**
	 * 固定费用
	 */
	private BigDecimal fixedFee;

	/**
	 * 财务费用
	 */
	private BigDecimal financeFee;

	/**
	 * 受让金额
	 */
	private BigDecimal acceptAmount;

	/**
	 * 合同收益率
	 */
	private Double contractProfitRate;

	/**
	 * 转让生效时间
	 */
	private Long attomEffect;

	/**
	 * 单一合同编号
	 */
	private String contractNo;

	/**
	 * 转让合同编号
	 */
	private String attomContractNo;

	/**
	 * 转让截止日期
	 */
	private Long attomEndDate;

	/**
	 * 转让状态
	 */
	private Integer attomStatus;

	/**
	 * 提交日期
	 */
	private Long completeDate;

	/**
	 * 受让人开户行
	 */
	private String assigneeAccBank;

	/**
	 * 受让人账户名
	 */
	private String assigneeAccName;

	/**
	 * 受让人账号
	 */
	private String assigneeAccNo;

	/**
	 * 派息周期
	 */
	private String payoutPeriod;

	/**
	 * 付息日
	 */
	private Integer termDay;

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
	 * 提交人
	 */
	private String completeEmpCd;
	private String completeEmpName;

	/**
	 * 组织机构代码
	 */
	private String orgCd;

	/**
	 * 联系人名称
	 */
	private String contactName;

	/**
	 * 固定电话
	 */
	private String phone;

	/**
	 * 临时id
	 */
	private String tempUuid;

	/**
	 * 信托计划id
	 */
	private String creditEntrustId;

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

	public String getAcceptTypeName() {
		return acceptTypeName;
	}

	public void setAcceptTypeName(String acceptTypeName) {
		this.acceptTypeName = acceptTypeName;
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

	public String getCreditEntrustId() {
		return creditEntrustId;
	}

	public void setCreditEntrustId(String creditEntrustId) {
		this.creditEntrustId = creditEntrustId;
	}

	public String getAttomStateName() {
		return attomStateName;
	}

	public void setAttomStateName(String attomStateName) {
		this.attomStateName = attomStateName;
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public CreditEntrustAttomVo() {
		super();
	}

	public CreditEntrustAttomVo(CreditEntrustAttom creditEntrustAttom) {
		super(creditEntrustAttom);
		this.setStatusName(StatusNm.getName(creditEntrustAttom.getStatus()));
	}

	public CreditEntrustAttomVo(CreditEntrustAttom creditEntrustAttom, String[] args, String[] simpleArgs) {
		super(creditEntrustAttom, args, simpleArgs);
		this.setStatusName(StatusNm.getName(creditEntrustAttom.getStatus()));
	}

	public CreditEntrustAttom toPo() {
		CreditEntrustAttom attom = new CreditEntrustAttom();
		return super.toPo(this, attom);
	}

}
