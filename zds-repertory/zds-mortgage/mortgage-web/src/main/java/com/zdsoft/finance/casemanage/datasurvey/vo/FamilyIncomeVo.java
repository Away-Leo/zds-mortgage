package com.zdsoft.finance.casemanage.datasurvey.vo;

import java.math.BigDecimal;

import com.zdsoft.finance.casemanage.datasurvey.entity.FamilyIncome;
import com.zdsoft.finance.common.base.BaseVo;

/** 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: FamilyIncomeVo.java 
 * @ClassName: FamilyIncomeVo 
 * @Description: 家庭收入
 * @author liuhuan 
 * @date 2017年2月20日 下午9:03:14 
 * @version V1.0 
 */ 
public class FamilyIncomeVo extends BaseVo<FamilyIncome>{

	private static final long serialVersionUID = 3473364007213491954L;

	/**
	 * 案件Id
	 */
	private String caseApplyId;
	/**
	 * 户主名
	 */
	private String houseHolder;
	
	/**
	 * 是否为实际用款人流水code
	 */
	private String isMoney;
	/**
	 * 是否为实际用款人流水Name
	 */
	private String isMoneyName;
	
	/**
	 * 月均收入
	 */
	private BigDecimal monthAmount;
	
	/**
	 * 半年合计收入
	 */
	private BigDecimal totalAmount;
	
	/**
	 * 客户ID
	 */
	private String customerId;

	public FamilyIncomeVo() {
		super();
	}
	
	public FamilyIncomeVo(FamilyIncome po){
		super(po,null,new String[]{"isMoney"});
	}
	
	public FamilyIncome toPo(){
		FamilyIncome po = new FamilyIncome();
		return super.toPo(this, po);
	}
	
	public String getCaseApplyId() {
		return caseApplyId;
	}

	public void setCaseApplyId(String caseApplyId) {
		this.caseApplyId = caseApplyId;
	}

	public String getHouseHolder() {
		return houseHolder;
	}

	public void setHouseHolder(String houseHolder) {
		this.houseHolder = houseHolder;
	}

	public String getIsMoney() {
		return isMoney;
	}

	public void setIsMoney(String isMoney) {
		this.isMoney = isMoney;
	}

	public BigDecimal getMonthAmount() {
		return monthAmount;
	}

	public void setMonthAmount(BigDecimal monthAmount) {
		this.monthAmount = monthAmount;
	}

	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getIsMoneyName() {
		return isMoneyName;
	}

	public void setIsMoneyName(String isMoneyName) {
		this.isMoneyName = isMoneyName;
	}
}
