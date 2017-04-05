package com.zdsoft.finance.casemanage.vo;

import java.math.BigDecimal;

/**
 * 版权所有：重庆正大华日软件有限公司
 * 
 * @Title: HousePropertyPledgeInfoVo.java
 * @Package com.zdsoft.finance.casemanage.vo
 * @Description: 抵押物抵押情况综合VO（包含一抵，二抵）
 * @author Liyb
 * @date 2017年1月15日 上午11:11:35
 * @version V1.0
 */
public class HousePropertyPledgeInfoVo {

    /**
     * 抵押物ID
     */
    private String housePropertyId;

    /**
     * 一抵银行
     */
    private String firstLoanBank;

    /**
     * 一抵贷款余额
     */
    private BigDecimal firstLoanBalance;

    /**
     * 二抵银行
     */
    private String secondLoanBank;

    /**
     * 二抵贷款余额
     */
    private BigDecimal secondLoanBalance;
    
    /**
	 * 成数
	 */
    private BigDecimal percentage;
    /**
     * 楼龄
     */
    private String floorAge;
    /**
     * 风控核定价
     */
    private BigDecimal controlPrice;
    /**
     * 居住状态
     */
    private String livingState;
    private String livingStateName;
    /**
     * 实际金额
     */
    private BigDecimal applyAmount;

    public String getHousePropertyId() {
        return housePropertyId;
    }

    public void setHousePropertyId(String housePropertyId) {
        this.housePropertyId = housePropertyId;
    }


    public String getFirstLoanBank() {
        return firstLoanBank;
    }

    public void setFirstLoanBank(String firstLoanBank) {
        this.firstLoanBank = firstLoanBank;
    }

    public BigDecimal getFirstLoanBalance() {
        return firstLoanBalance;
    }

    public void setFirstLoanBalance(BigDecimal firstLoanBalance) {
        this.firstLoanBalance = firstLoanBalance;
    }

    public String getSecondLoanBank() {
        return secondLoanBank;
    }

    public void setSecondLoanBank(String secondLoanBank) {
        this.secondLoanBank = secondLoanBank;
    }

    public BigDecimal getSecondLoanBalance() {
        return secondLoanBalance;
    }

    public void setSecondLoanBalance(BigDecimal secondLoanBalance) {
        this.secondLoanBalance = secondLoanBalance;
    }

	public BigDecimal getPercentage() {
		return percentage;
	}

	public void setPercentage(BigDecimal percentage) {
		this.percentage = percentage;
	}

	public String getFloorAge() {
		return floorAge;
	}

	public void setFloorAge(String floorAge) {
		this.floorAge = floorAge;
	}

	public BigDecimal getControlPrice() {
		return controlPrice;
	}

	public void setControlPrice(BigDecimal controlPrice) {
		this.controlPrice = controlPrice;
	}

	public String getLivingState() {
		return livingState;
	}

	public void setLivingState(String livingState) {
		this.livingState = livingState;
	}

	public String getLivingStateName() {
		return livingStateName;
	}

	public void setLivingStateName(String livingStateName) {
		this.livingStateName = livingStateName;
	}

	public BigDecimal getApplyAmount() {
		return applyAmount;
	}

	public void setApplyAmount(BigDecimal applyAmount) {
		this.applyAmount = applyAmount;
	}
}
