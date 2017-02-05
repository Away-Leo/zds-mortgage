package com.zdsoft.finance.cooperator.entity;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * 合作方终端信息
 * 
 * @author Hisa
 *
 */
@Entity
@Table(name = "cpt_terminal")
public class CooperatorTerminal extends BaseCooperator {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 编码
	 */
	@Column(length = 64)
	private String autoCode;
	/**
	 * 终端全称
	 */
	@Column(length = 64)
	private String terminalFullName;
	/**
	 * 终端类别
	 */
	@Column(length = 64)
	private String terminalType;
	/**
	 * 终端编码
	 */
	@Column(length = 64)
	private String terminalCode;

	/**
	 * 公司电话
	 */
	@Column(length = 15)
	private String companyTel;
	/**
	 * 终端月均产能总量预测
	 */
	@Column(precision = 16, scale = 4)
	private BigDecimal monthOutAvgPredict;
	/**
	 * 终端上月业务量
	 */
	@Column(precision = 16, scale = 4)
	private BigDecimal lastMonthBusiness;
	/**
	 * 终端平均每单贷款额度
	 */
	@Column(precision = 16, scale = 4)
	private BigDecimal oneBillAvgVaule;
	/**
	 * 终端状态
	 */
	@Column(length = 20)
	private String terminalStatus;
	/**
	 * 终端等级
	 */
	private Integer grade;
	/**
	 * 创建时间
	 */
	private Long nowDate;
	/**
	 * 终端归属
	 */
	@Column(length = 8)
	private String belongType;

	/**
	 * 终端归属输入框
	 */
	@Column(length = 64)
	private String belongTypeName;
	/**
	 * 是否机构公用
	 */
	@Column(length = 15)
	private String isBelongOrg;
	/**
	 * 协议编号
	 */
	@Column(length = 32)
	private String dealNumber;
	/**
	 * 返佣类型
	 */
	@Column(length = 15)
	private String rebateType;
	/**
	 * 返佣比例
	 */
	@Column(precision = 16, scale = 4)
	private String returnRate;
	/**
	 * 返佣金额
	 */
	@Column(precision = 16, scale = 4)
	private String returnAmount;
	/**
	 * 是否允许发送手机短信
	 */
	@Column(length = 14)
	private String isAllowPhoneMsg;
	/**
	 * 可接受的返佣方式
	 */
	@Column(length = 128)
	private String acceptRebateType;
	/**
	 * 涉及业务
	 */
	@Column(length = 128)
	private String businessScope;
	/**
	 * 合作的要求和建议
	 */
	@Column(length = 500)
	private String cooperateSuggest;
	/**
	 * 没有继续合作原因
	 */
	@Column(length = 500)
	private String onContinueReason;
	/**
	 * 该终端的特殊说明
	 */
	@Column(length = 500)
	private String specialInstruction;
	/**
	 * 最后来案时间
	 */
	private Long lastinDate;

	/**
	 * 添加状态
	 */
	@Column(length = 1)
	private String logicDelelte;
	/**
	 * 启用禁用状态
	 */
	@Column(length = 32)
	private String status;

	/**
	 * 主要出资人及股东
	 */
	@Column(length = 128)
	private String mainShareholder;
	/**
	 * 员工数
	 */
	private Integer staffNumber;
	/**
	 * 员工流动性
	 */
	@Column(length = 20)
	private String staffTurnover;
	/**
	 * 返佣是否给员工集体提成
	 */
	@Column(length = 20)
	private String isGiveDeductMoney;
	/**
	 * 发放工资日期
	 */
	private Long wageday;
	/**
	 * 月均经营成本
	 */
	@Column(precision = 16, scale = 4)
	private BigDecimal monthManageCost;
	/**
	 * 注册资本
	 */
	@Column(precision = 16, scale = 4)
	private BigDecimal registeredCapital;
	/**
	 * 办公场所面积
	 */
	@Column(precision = 16, scale = 4)
	private BigDecimal workArea;
	/**
	 * 返佣账户
	 */
	@OneToMany(mappedBy = "cooperatorTerminal")
	private List<ReceiveAccount> receiveAccount;
	/**
	 * 终端维护记录
	 */
	@OneToMany(mappedBy = "cooperatorTerminal")
	private List<TerminalHistory> terminalHistory;
	/**
	 * 联系人资料
	 */
	@OneToMany(mappedBy = "cooperatorTerminal")
	private List<ContactsInfo> contactsInfo;
	/**
	 * 合作银行
	 */
	@OneToMany(mappedBy = "cooperatorTerminal")
	private List<CooperatorBank> cooperatorBank;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getBelongTypeName() {
		return belongTypeName;
	}

	public Long getNowDate() {
		return nowDate;
	}

	public void setNowDate(Long nowDate) {
		this.nowDate = nowDate;
	}

	public void setBelongTypeName(String belongTypeName) {
		this.belongTypeName = belongTypeName;
	}

	public String getAutoCode() {
		return autoCode;
	}

	public void setAutoCode(String autoCode) {
		this.autoCode = autoCode;
	}

	public String getTerminalFullName() {
		return terminalFullName;
	}

	public void setTerminalFullName(String terminalFullName) {
		this.terminalFullName = terminalFullName;
	}

	public String getTerminalType() {
		return terminalType;
	}

	public void setTerminalType(String terminalType) {
		this.terminalType = terminalType;
	}

	public String getTerminalCode() {
		return terminalCode;
	}

	public void setTerminalCode(String terminalCode) {
		this.terminalCode = terminalCode;
	}

	public String getCompanyTel() {
		return companyTel;
	}

	public void setCompanyTel(String companyTel) {
		this.companyTel = companyTel;
	}

	public BigDecimal getMonthOutAvgPredict() {
		return monthOutAvgPredict;
	}

	public void setMonthOutAvgPredict(BigDecimal monthOutAvgPredict) {
		this.monthOutAvgPredict = monthOutAvgPredict;
	}

	public BigDecimal getLastMonthBusiness() {
		return lastMonthBusiness;
	}

	public void setLastMonthBusiness(BigDecimal lastMonthBusiness) {
		this.lastMonthBusiness = lastMonthBusiness;
	}

	public BigDecimal getOneBillAvgVaule() {
		return oneBillAvgVaule;
	}

	public void setOneBillAvgVaule(BigDecimal oneBillAvgVaule) {
		this.oneBillAvgVaule = oneBillAvgVaule;
	}

	public Integer getGrade() {
		return grade;
	}

	public void setGrade(Integer grade) {
		this.grade = grade;
	}

	public String getBelongType() {
		return belongType;
	}

	public void setBelongType(String belongType) {
		this.belongType = belongType;
	}

	public String getDealNumber() {
		return dealNumber;
	}

	public void setDealNumber(String dealNumber) {
		this.dealNumber = dealNumber;
	}

	public String getLogicDelelte() {
		return logicDelelte;
	}

	public void setLogicDelelte(String logicDelelte) {
		this.logicDelelte = logicDelelte;
	}

	public String getIsAllowPhoneMsg() {
		return isAllowPhoneMsg;
	}

	public void setIsAllowPhoneMsg(String isAllowPhoneMsg) {
		this.isAllowPhoneMsg = isAllowPhoneMsg;
	}

	public String getAcceptRebateType() {
		return acceptRebateType;
	}

	public void setAcceptRebateType(String acceptRebateType) {
		this.acceptRebateType = acceptRebateType;
	}

	public String getBusinessScope() {
		return businessScope;
	}

	public void setBusinessScope(String businessScope) {
		this.businessScope = businessScope;
	}

	public String getCooperateSuggest() {
		return cooperateSuggest;
	}

	public void setCooperateSuggest(String cooperateSuggest) {
		this.cooperateSuggest = cooperateSuggest;
	}

	public String getOnContinueReason() {
		return onContinueReason;
	}

	public void setOnContinueReason(String onContinueReason) {
		this.onContinueReason = onContinueReason;
	}

	public String getSpecialInstruction() {
		return specialInstruction;
	}

	public void setSpecialInstruction(String specialInstruction) {
		this.specialInstruction = specialInstruction;
	}

	public String getIsBelongOrg() {
		return isBelongOrg;
	}

	public void setIsBelongOrg(String isBelongOrg) {
		this.isBelongOrg = isBelongOrg;
	}

	public Long getLastinDate() {
		return lastinDate;
	}

	public void setLastinDate(Long lastinDate) {
		this.lastinDate = lastinDate;
	}

	public List<ReceiveAccount> getReceiveAccount() {
		return receiveAccount;
	}

	public void setReceiveAccount(List<ReceiveAccount> receiveAccount) {
		this.receiveAccount = receiveAccount;
	}

	public List<TerminalHistory> getTerminalHistory() {
		return terminalHistory;
	}

	public void setTerminalHistory(List<TerminalHistory> terminalHistory) {
		this.terminalHistory = terminalHistory;
	}

	public List<ContactsInfo> getContactsInfo() {
		return contactsInfo;
	}

	public void setContactsInfo(List<ContactsInfo> contactsInfo) {
		this.contactsInfo = contactsInfo;
	}

	public List<CooperatorBank> getCooperatorBank() {
		return cooperatorBank;
	}

	public void setCooperatorBank(List<CooperatorBank> cooperatorBank) {
		this.cooperatorBank = cooperatorBank;
	}

	public String getMainShareholder() {
		return mainShareholder;
	}

	public void setMainShareholder(String mainShareholder) {
		this.mainShareholder = mainShareholder;
	}

	public Integer getStaffNumber() {
		return staffNumber;
	}

	public void setStaffNumber(Integer staffNumber) {
		this.staffNumber = staffNumber;
	}

	public String getStaffTurnover() {
		return staffTurnover;
	}

	public void setStaffTurnover(String staffTurnover) {
		this.staffTurnover = staffTurnover;
	}

	public String getIsGiveDeductMoney() {
		return isGiveDeductMoney;
	}

	public void setIsGiveDeductMoney(String isGiveDeductMoney) {
		this.isGiveDeductMoney = isGiveDeductMoney;
	}

	public Long getWageday() {
		return wageday;
	}

	public void setWageday(Long wageday) {
		this.wageday = wageday;
	}

	public BigDecimal getMonthManageCost() {
		return monthManageCost;
	}

	public void setMonthManageCost(BigDecimal monthManageCost) {
		this.monthManageCost = monthManageCost;
	}

	public BigDecimal getRegisteredCapital() {
		return registeredCapital;
	}

	public void setRegisteredCapital(BigDecimal registeredCapital) {
		this.registeredCapital = registeredCapital;
	}

	public BigDecimal getWorkArea() {
		return workArea;
	}

	public void setWorkArea(BigDecimal workArea) {
		this.workArea = workArea;
	}

	public String getTerminalStatus() {
		return terminalStatus;
	}

	public void setTerminalStatus(String terminalStatus) {
		this.terminalStatus = terminalStatus;
	}

	public String getRebateType() {
		return rebateType;
	}

	public void setRebateType(String rebateType) {
		this.rebateType = rebateType;
	}

	public String getReturnRate() {
		return returnRate;
	}

	public void setReturnRate(String returnRate) {
		this.returnRate = returnRate;
	}

	public String getReturnAmount() {
		return returnAmount;
	}

	public void setReturnAmount(String returnAmount) {
		this.returnAmount = returnAmount;
	}

}
