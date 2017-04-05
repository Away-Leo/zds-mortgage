package com.zdsoft.finance.cooperator.vo;

import java.math.BigDecimal;

import com.zdsoft.finance.common.base.BaseVo;
import com.zdsoft.finance.cooperator.entity.CooperatorTerminal;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * 
 * @Title: CooperatorTerminalVo.java
 * @ClassName: CooperatorTerminalVo
 * @Description: 终端Vo
 * @author liuwei
 * @date 2017年3月1日 上午11:33:05
 * @version V1.0
 */
public class CooperatorTerminalVo extends BaseVo<CooperatorTerminal> {

	/**
	 * @Fields serialVersionUID
	 */
	private static final long serialVersionUID = 8285382630789584501L;

	/**
	 * 终端编码
	 */
	private String terminalCode;

	/**
	 * 终端全称
	 */
	private String terminalFullName;

	/**
	 * 终端类别
	 */
	private String terminalType;

	/**
	 * 终端类别名称
	 */
	private String terminalTypeName;

	/**
	 * 协议编号
	 */
	private String dealNumber;

	/**
	 * 公司电话
	 */
	private String companyTel;

	/**
	 * 终端月均产能总量预测
	 */
	private BigDecimal monthOutAvgPredict;

	/**
	 * 终端状态
	 */
	private String terminalStatus;

	/**
	 * 终端状态名称
	 */
	private String terminalStatusName;

	/**
	 * 终端等级
	 */
	private String grade;

	/**
	 * 终端归属类型
	 */
	private String belongType;

	/**
	 * 终端归属关联
	 */
	private String belongRelevanceCode;

	/**
	 * 终端归属关联Name
	 */
	private String belongRelevanceName;

	/**
	 * 主要联系人名称
	 */
	private String linkman;

	/**
	 * 是否机构公用
	 */
	private String isBelongOrg;

	/**
	 * 返佣类型
	 */
	private String rebateType;

	/**
	 * 返佣类型值
	 */
	private String returnRate;

	/**
	 * 是否允许发送手机短信
	 */
	private String isAllowPhoneMsg;

	/**
	 * 可接受的返佣方式
	 */
	private String acceptRebateType;

	/**
	 * 终端地址省
	 */
	private String addProvince;

	/**
	 * 终端地址市
	 */
	private String addCity;

	/**
	 * 终端地址区
	 */
	private String addCounty;

	/**
	 * 终端地址
	 */
	private String address;

	/**
	 * 涉及业务
	 */
	private String businessScope;

	/**
	 * 合作的要求和建议
	 */
	private String cooperateSuggest;

	/**
	 * 没有继续合作原因
	 */
	private String onContinueReason;

	/**
	 * 该终端的特殊说明
	 */
	private String specialInstruction;

	/**
	 * 成立时间
	 */
	private Long foundDate;

	/**
	 * 主要出资人及股东
	 */
	private String mainShareholder;

	/**
	 * 员工数
	 */
	private Integer staffNumber;

	/**
	 * 员工流动性
	 */
	private String staffTurnover;

	/**
	 * 返佣是否给员工集体提成
	 */
	private String isGiveDeductMoney;

	/**
	 * 发放工资日期每月几日
	 */
	private Integer wageday;

	/**
	 * 月均经营成本
	 */
	private BigDecimal monthManageCost;

	/**
	 * 注册资本
	 */
	private BigDecimal registeredCapital;

	/**
	 * 办公场所面积
	 */
	private BigDecimal workArea;

	/**
	 * 上级机构
	 */
	private String parentOrg;

	/**
	 * 维护次数
	 */
	private Integer maintenanceTimes;

	/**
	 * 创建时间显示
	 */
	private String createTimeName;

	/**
	 * 共用机构Code
	 */
	private String shareOrgCode;

	public String getTerminalCode() {
		return terminalCode;
	}

	public void setTerminalCode(String terminalCode) {
		this.terminalCode = terminalCode;
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

	public String getDealNumber() {
		return dealNumber;
	}

	public void setDealNumber(String dealNumber) {
		this.dealNumber = dealNumber;
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

	public String getTerminalStatus() {
		return terminalStatus;
	}

	public void setTerminalStatus(String terminalStatus) {
		this.terminalStatus = terminalStatus;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getBelongType() {
		return belongType;
	}

	public void setBelongType(String belongType) {
		this.belongType = belongType;
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

	public String getAcceptRebateType() {
		return acceptRebateType;
	}

	public void setAcceptRebateType(String acceptRebateType) {
		this.acceptRebateType = acceptRebateType;
	}

	public String getAddProvince() {
		return addProvince;
	}

	public void setAddProvince(String addProvince) {
		this.addProvince = addProvince;
	}

	public String getAddCity() {
		return addCity;
	}

	public void setAddCity(String addCity) {
		this.addCity = addCity;
	}

	public String getAddCounty() {
		return addCounty;
	}

	public void setAddCounty(String addCounty) {
		this.addCounty = addCounty;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
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

	public Long getFoundDate() {
		return foundDate;
	}

	public void setFoundDate(Long foundDate) {
		this.foundDate = foundDate;
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

	public Integer getWageday() {
		return wageday;
	}

	public void setWageday(Integer wageday) {
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

	public String getParentOrg() {
		return parentOrg;
	}

	public void setParentOrg(String parentOrg) {
		this.parentOrg = parentOrg;
	}

	public Integer getMaintenanceTimes() {
		return maintenanceTimes;
	}

	public void setMaintenanceTimes(Integer maintenanceTimes) {
		this.maintenanceTimes = maintenanceTimes;
	}

	public String getIsBelongOrg() {
		return isBelongOrg;
	}

	public void setIsBelongOrg(String isBelongOrg) {
		this.isBelongOrg = isBelongOrg;
	}

	public String getIsAllowPhoneMsg() {
		return isAllowPhoneMsg;
	}

	public void setIsAllowPhoneMsg(String isAllowPhoneMsg) {
		this.isAllowPhoneMsg = isAllowPhoneMsg;
	}

	public String getIsGiveDeductMoney() {
		return isGiveDeductMoney;
	}

	public void setIsGiveDeductMoney(String isGiveDeductMoney) {
		this.isGiveDeductMoney = isGiveDeductMoney;
	}

	public String getTerminalTypeName() {
		return terminalTypeName;
	}

	public void setTerminalTypeName(String terminalTypeName) {
		this.terminalTypeName = terminalTypeName;
	}

	public String getTerminalStatusName() {
		return terminalStatusName;
	}

	public void setTerminalStatusName(String terminalStatusName) {
		this.terminalStatusName = terminalStatusName;
	}

	public String getCreateTimeName() {
		return createTimeName;
	}

	public void setCreateTimeName(String createTimeName) {
		this.createTimeName = createTimeName;
	}

	public String getBelongRelevanceCode() {
		return belongRelevanceCode;
	}

	public void setBelongRelevanceCode(String belongRelevanceCode) {
		this.belongRelevanceCode = belongRelevanceCode;
	}

	public String getBelongRelevanceName() {
		return belongRelevanceName;
	}

	public void setBelongRelevanceName(String belongRelevanceName) {
		this.belongRelevanceName = belongRelevanceName;
	}

	public String getLinkman() {
		return linkman;
	}

	public void setLinkman(String linkman) {
		this.linkman = linkman;
	}

	public String getShareOrgCode() {
		return shareOrgCode;
	}

	public void setShareOrgCode(String shareOrgCode) {
		this.shareOrgCode = shareOrgCode;
	}

	public CooperatorTerminalVo() {
		super();
	}

	public CooperatorTerminalVo(CooperatorTerminal cooperatorTerminal) {
		super(cooperatorTerminal);
	}

	public CooperatorTerminalVo(CooperatorTerminal cooperatorTerminal, String[] args, String[] simpleArgs) {
		super(cooperatorTerminal, args, simpleArgs);
	}

	public CooperatorTerminal toPO() {
		CooperatorTerminal po = new CooperatorTerminal();
		return super.toPo(this, po);
	}
}
