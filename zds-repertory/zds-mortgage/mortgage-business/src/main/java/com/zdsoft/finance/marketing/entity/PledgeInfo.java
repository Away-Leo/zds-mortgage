package com.zdsoft.finance.marketing.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.zdsoft.framework.core.common.domain.BaseEntity;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: PledgeInfo.java 
 * @ClassName: PledgeInfo 
 * @Description: 抵押实体类
 * @author zhoushichao 
 * @date 2017年3月14日 下午5:45:54 
 * @version V1.0
 */
@Entity
@Table(name = "mkt_pledge_info")
public class PledgeInfo extends BaseEntity{

	private static final long serialVersionUID = 1L;
	
	/**
     * 1抵
     */
    public static final String PLEDGETYPE_ONE = "YWDM0051011" ;
    
    /**
     * 2抵
     */
    public static final String PLEDGETYPE_TWO = "YWDM0051012" ;
    
    /**
     * 3抵
     */
    public static final String PLEDGETYPE_ST = "YWDM0051013" ;
    
    /**
     * 其他
     */
    public static final String PLEDGETYPE_OTH = "YWDM0051014" ;
    
    /**
     * 普通抵押   model by liuhuan 2017-2-16
     */
    public static final String TYPE_BASE = "YWDM0051039" ;
    /**
     * 最高额抵押   model by liuhuan 2017-2-16
     */
    public static final String TYPE_HEIGHEST = "YWDM0051040" ;
    /**
     * 可循环支用   model by liuhuan 2017-2-16
     */
    public static final String TYPE_LOOP = "YWDM0051041" ;
    
  
	/**
	 * 房产
	 */
	@Column(length=32)
	private String housePropertyId;
	
	/**
	 * 抵押类型
	 */
	@Column(length=20)
	private String pledgeType;
	/**
	 * 贷款银行
	 */
	@Column(length=128)
	private String loanBank;
	/**
	 * 抵押期限
	 */
	@Column
    private Integer deadline;
	/**
     * 抵押期限单位
     */
    @Column(length = 20)
    private String deadlineUnit;
	/**
	 * 类型
	 */
	@Column(length=20)
	private String type;
	/**
	 * 贷款余额
	 */
	@Column(precision = 18, scale = 2)
    private BigDecimal loanBalance=BigDecimal.ZERO;
	/**
	 * 抵押金额
	 */
	@Column(precision = 18, scale = 2)
    private BigDecimal pledgeAmout=BigDecimal.ZERO;
	/**
	 * 成数
	 */
	@Column(precision = 18, scale = 6)
	private BigDecimal percentage=BigDecimal.ZERO;
	/**
	 * 前抵押贷款余额
	 */
	@Column(precision = 18, scale = 2)
	private BigDecimal frontLoanBalance=BigDecimal.ZERO;
	
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
}
