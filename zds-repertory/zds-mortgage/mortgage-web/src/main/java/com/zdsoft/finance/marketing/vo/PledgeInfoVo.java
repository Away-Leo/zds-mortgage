package com.zdsoft.finance.marketing.vo;

import java.math.BigDecimal;

import com.zdsoft.finance.common.base.BaseVo;
import com.zdsoft.finance.marketing.entity.PledgeInfo;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: PledgeInfoVo.java 
 * @ClassName: PledgeInfoVo 
 * @Description: 房产抵押Vo
 * @author zhoushichao 
 * @date 2017年3月14日 下午7:49:53 
 * @version V1.0
 */
public class PledgeInfoVo extends BaseVo<PledgeInfo>{

	private static final long serialVersionUID = 1L;

	/**
	 * 房产Id
	 */
	private String housePropertyId;
	/**
	 * 抵押类型Code
	 */
	private String pledgeType;
	/**
	 * 抵押类型名称
	 */
	private String pledgeTypeName;
	/**
	 * 贷款银行
	 */
	private String loanBank;
	/**
	 * 抵押期限
	 */
    private Integer deadline;
	/**
     * 抵押期限单位
     */
    private String deadlineUnit;
	/**
	 * 类型Code
	 */
	private String type;
	/**
	 * 类型名称
	 */
	private String typeName;
	/**
	 * 贷款余额
	 */
    private BigDecimal loanBalance;
	/**
	 * 抵押金额
	 */
    private BigDecimal pledgeAmout;
	/**
	 * 成数
	 */
	private BigDecimal percentage;
	/**
	 * 前抵押贷款余额
	 */
	private BigDecimal frontLoanBalance;
	
	public String getHousePropertyId() {
		return housePropertyId;
	}
	public void setHousePropertyId(String housePropertyId) {
		this.housePropertyId = housePropertyId;
	}
	public String getPledgeType() {
		return pledgeType;
	}
	public void setPledgeType(String pledgeType) {
		this.pledgeType = pledgeType;
	}
	public String getLoanBank() {
		return loanBank;
	}
	public void setLoanBank(String loanBank) {
		this.loanBank = loanBank;
	}
	public Integer getDeadline() {
		return deadline;
	}
	public void setDeadline(Integer deadline) {
		this.deadline = deadline;
	}
	public String getDeadlineUnit() {
		return deadlineUnit;
	}
	public void setDeadlineUnit(String deadlineUnit) {
		this.deadlineUnit = deadlineUnit;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public BigDecimal getLoanBalance() {
		return loanBalance;
	}
	public void setLoanBalance(BigDecimal loanBalance) {
		this.loanBalance = loanBalance;
	}
	public BigDecimal getPledgeAmout() {
		return pledgeAmout;
	}
	public void setPledgeAmout(BigDecimal pledgeAmout) {
		this.pledgeAmout = pledgeAmout;
	}
	public BigDecimal getPercentage() {
		return percentage;
	}
	public void setPercentage(BigDecimal percentage) {
		this.percentage = percentage;
	}
	public BigDecimal getFrontLoanBalance() {
		return frontLoanBalance;
	}
	public void setFrontLoanBalance(BigDecimal frontLoanBalance) {
		this.frontLoanBalance = frontLoanBalance;
	}
	public String getPledgeTypeName() {
		return pledgeTypeName;
	}
	public void setPledgeTypeName(String pledgeTypeName) {
		this.pledgeTypeName = pledgeTypeName;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	
	public PledgeInfoVo() {
		super();
	}
	public PledgeInfoVo(PledgeInfo po){
		super(po,null,new String[]{"pledgeType","type"});
	}
	public PledgeInfo toPo(){
		PledgeInfo po = new PledgeInfo();
		return super.toPo(this, po);
	}
}
