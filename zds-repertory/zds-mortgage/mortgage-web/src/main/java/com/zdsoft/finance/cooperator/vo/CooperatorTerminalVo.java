package com.zdsoft.finance.cooperator.vo;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.zdsoft.finance.cooperator.entity.CooperatorTerminal;
import com.zdsoft.framework.core.common.util.ObjectHelper;

/**
 * 合作方终端信息
 * 
 * @author Hisa
 *
 */

//modify by liuhuan 2017-01-20  把extends父类Vo: 从BaseVo修改为定义的父类Vo   
public class CooperatorTerminalVo extends BaseCooperatorVo<CooperatorTerminal> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 编码
	 */
	private String autoCode;
	/**
	 * 终端全称
	 */
	private String terminalFullName;
	/**
	 * 终端类别
	 */
	private String terminalType;
	private String terminalTypeName;
	/**
	 * 终端编码
	 */
	private String terminalCode;

	/**
	 * 创建时间
	 */
	private Long nowDate;
	/**
	 * 公司电话
	 */
	private String companyTel;
	/**
	 * 终端月均产能总量预测
	 */
	private BigDecimal monthOutAvgPredict;
	/**
	 * 终端上月业务量
	 */
	private BigDecimal lastMonthBusiness;
	/**
	 * 终端平均每单贷款额度
	 */
	private BigDecimal oneBillAvgVaule;
	/**
	 * 终端状态
	 */
	private String terminalStatus;
	private String terminalStatusName;
	/**
	 * 终端等级
	 */
	private Integer grade;
	/**
	 * 终端归属
	 */
	private String belongType;

	/**
	 * 终端归属输入框
	 */
	private String belongTypeName;
	/**
	 * 是否机构公用
	 */
	private String isBelongOrg;
	/**
	 * 协议编号
	 */
	private String dealNumber;
	/**
	 * 返佣类型
	 */
	private String rebateType;
	/**
	 * 返佣比例
	 */
	private String returnRate;
	/**
	 * 返佣金额
	 */
	private String returnAmount;
	/**
	 * 是否允许发送手机短信
	 */
	private String isAllowPhoneMsg;
	/**
	 * 可接受的返佣方式
	 */
	private String acceptRebateType;
	/**
	 * 终端地址
	 */
	private String cooperatorAddress;
	/**
	 * 地区CODE
	 */
	private String regionCode;

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
	 * 最后来案时间
	 */
	private String lastinDate;

	/**
	 * 添加状态
	 */
	private String logicDelelte;

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
	 * 启用禁用
	 */
	private String isStop;
	private String isStopName;
	/**
	 * 返佣是否给员工集体提成
	 */
	private String isGiveDeductMoney;
	/**
	 * 发放工资日期
	 */
	private Long wageday;
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

	public String getIsStop() {
		return isStop;
	}

	public void setIsStop(String isStop) {
		this.isStop = isStop;
	}

	public String getIsStopName() {
		return isStopName;
	}

	public void setIsStopName(String isStopName) {
		this.isStopName = isStopName;
	}

	public String getAutoCode() {
		return autoCode;
	}

	public void setAutoCode(String autoCode) {
		this.autoCode = autoCode;
	}

	public Long getNowDate() {
		return nowDate;
	}

	public void setNowDate(Long nowDate) {
		this.nowDate = nowDate;
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

	public String getTerminalStatus() {
		return terminalStatus;
	}

	public void setTerminalStatus(String terminalStatus) {
		this.terminalStatus = terminalStatus;
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

	public String getBelongTypeName() {
		return belongTypeName;
	}

	public void setBelongTypeName(String belongTypeName) {
		this.belongTypeName = belongTypeName;
	}

	public String getIsBelongOrg() {
		return isBelongOrg;
	}

	public void setIsBelongOrg(String isBelongOrg) {
		this.isBelongOrg = isBelongOrg;
	}

	public String getDealNumber() {
		return dealNumber;
	}

	public void setDealNumber(String dealNumber) {
		this.dealNumber = dealNumber;
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

	public String getCooperatorAddress() {
		return cooperatorAddress;
	}

	public void setCooperatorAddress(String cooperatorAddress) {
		this.cooperatorAddress = cooperatorAddress;
	}

	public String getRegionCode() {
		return regionCode;
	}

	public void setRegionCode(String regionCode) {
		this.regionCode = regionCode;
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

	public String getLastinDate() {
		return lastinDate;
	}

	public void setLastinDate(String lastinDate) {
		this.lastinDate = lastinDate;
	}

	public String getLogicDelelte() {
		return logicDelelte;
	}

	public void setLogicDelelte(String logicDelelte) {
		this.logicDelelte = logicDelelte;
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

	public String getCreateTimeNm() {
		if (ObjectHelper.isNotEmpty(this.getCreateTime())) {
			Calendar c = Calendar.getInstance();
			c.setTime(this.getCreateTime());
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			return sdf.format(c.getTime());
		}
		return null;
	}

	public String getFoundDateNm() {
		Long found = this.getFoundDate();
		if (found != null && found > 2000) {
			String t = String.valueOf(found);
			return t.substring(0, 4) + "-" + t.substring(4, 6) + "-" + t.substring(6, 8);
		}
		return "";
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
