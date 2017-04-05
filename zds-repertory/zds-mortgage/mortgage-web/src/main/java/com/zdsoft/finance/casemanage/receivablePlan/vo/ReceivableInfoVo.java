package com.zdsoft.finance.casemanage.receivablePlan.vo;

import java.math.BigDecimal;

import com.zdsoft.finance.casemanage.receivablePlan.entity.ReceivableInfo;
import com.zdsoft.finance.common.base.BaseVo;
import com.zdsoft.finance.common.utils.BigDecimalCalculateTwo;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: ReceivableInfoVo.java 
 * @ClassName: ReceivableInfoVo 
 * @Description: 还款计划信息Vo
 * @author zhoushichao 
 * @date 2017年3月16日 下午8:47:11 
 * @version V1.0
 */
public class ReceivableInfoVo extends BaseVo<ReceivableInfo> {

    private static final long serialVersionUID = 1L;

    /**
     * 案件Id
     */
    private String caseApplyId;
    
    /**
     * 贷款金额
     */
    private BigDecimal applyAmount;
    
    /**
     * 申请期限
     */
    private Integer applyTerm;
    /**
     * 申请期限单位
     */
    private String applyTermUnit;
    private String applyTermUnitName;
    
    /**
     * 还款方式
     */
    private String repaymentType;

    /**
     * 还款方式名称
     */
    private String repaymentTypeName;

    /**
     * 还款方式2
     */
    private String repaymentTypeTwo;

    /**
     * 还款方式2名称
     */
    private String repaymentTypeTwoName;

    /**
     * 逾期利率
     */
    private BigDecimal overdueDailyRate;

    /**
     * 逾期利率单位
     */
    private String overdueDailyRateUnit;

    /**
     * 逾期利率单位名称
     */
    private String overdueDailyRateUnitName;

    /**
     * 贷款利率
     */
    private BigDecimal loanMonthRate;

    /**
     * 贷款利率单位
     */
    private String loanMonthRateUnit;

    /**
     * 贷款利率单位名称
     */
    private String loanMonthRateUnitName;

    /**
     * 综合利率
     */
    private BigDecimal syntheticalRate;

    /**
     * 综合利率单位
     */
    private String syntheticalRateUnit;

    /**
     * 综合利率单位名称
     */
    private String syntheticalRateUnitName;

    /**
     * 动态转换利率
     */
    private BigDecimal internalRateReturn;

    /**
     * 动态转换利率单位
     */
    private String internalRateReturnUnit;

    /**
     * 动态转换利率单位名称
     */
    private String internalRateReturnUnitName;

    /**
     * 利率性质
     */
    private String rateNature;

    /**
     * 利率性质名称
     */
    private String rateNatureName;

    /**
     * 利率性质2
     */
    private String rateNatureTwo;

    /**
     * 利率性质2名称
     */
    private String rateNatureTwoName;

    /**
     * 还款日
     */
    private Integer repaymentDate;

    /**
     * 预计放款日期
     */
    private Long expectLoanDate;

    /**
     * 预计开始日期
     */
    private Long expectStartDate;

    /**
     * 预计结束日期
     */
    private Long expectEndDate;

    /**
     * 备注
     */
    private String remark;
    /**
	 * 机构审核(默认：0：未审核；1：已审核)
	 */
	private Integer institutionalAudit;
	
	/**
	 * 集团确认(默认：0：未确认；1：已确认)
	 */
	private Integer groupConfirmation;

    /**
     * 下一处理人
     */
    private String currentDealEmpName ;
    public ReceivableInfoVo() {
    }
    public ReceivableInfo toPO(){
    	ReceivableInfo po = new ReceivableInfo();
		return super.toPo(this, po);
	}

    public ReceivableInfoVo(ReceivableInfo po) {
        super(po, null, new String[] { "rateNatureTwo", "rateNature", "internalRateReturnUnit", "syntheticalRateUnit", "loanMonthRateUnit",
                "overdueDailyRateUnit", "repaymentTypeTwo", "repaymentType","applyTermUnit" });
      //更改小数位数
        this.setLoanMonthRate(BigDecimalCalculateTwo.mul(this.getLoanMonthRate(),
				ReceivableInfo.RECEIVABLEINFO_DAY.equals(this.getLoanMonthRateUnit())?BigDecimal.valueOf(1000):BigDecimal.valueOf(100)).setScale(2,BigDecimal.ROUND_HALF_UP));
        this.setOverdueDailyRate(BigDecimalCalculateTwo.mul(this.getOverdueDailyRate(),
				ReceivableInfo.RECEIVABLEINFO_DAY.equals(this.getOverdueDailyRateUnit())?BigDecimal.valueOf(1000):BigDecimal.valueOf(100)).setScale(2,BigDecimal.ROUND_HALF_UP));
        this.setSyntheticalRate(BigDecimalCalculateTwo.mul(this.getSyntheticalRate(),
				ReceivableInfo.RECEIVABLEINFO_DAY.equals(this.getSyntheticalRateUnit())?BigDecimal.valueOf(1000):BigDecimal.valueOf(100)).setScale(2,BigDecimal.ROUND_HALF_UP));
        this.setInternalRateReturn(BigDecimalCalculateTwo.mul(this.getInternalRateReturn(),
				ReceivableInfo.RECEIVABLEINFO_DAY.equals(this.getInternalRateReturnUnit())?BigDecimal.valueOf(1000):BigDecimal.valueOf(100)).setScale(2,BigDecimal.ROUND_HALF_UP));
    }

    public String getCaseApplyId() {
        return caseApplyId;
    }

    public void setCaseApplyId(String caseApplyId) {
        this.caseApplyId = caseApplyId;
    }

    public String getRepaymentType() {
        return repaymentType;
    }

    public void setRepaymentType(String repaymentType) {
        this.repaymentType = repaymentType;
    }

    public String getRepaymentTypeTwo() {
        return repaymentTypeTwo;
    }

    public void setRepaymentTypeTwo(String repaymentTypeTwo) {
        this.repaymentTypeTwo = repaymentTypeTwo;
    }

    public BigDecimal getOverdueDailyRate() {
        return overdueDailyRate;
    }

    public void setOverdueDailyRate(BigDecimal overdueDailyRate) {
        this.overdueDailyRate = overdueDailyRate;
    }

    public BigDecimal getLoanMonthRate() {
            return loanMonthRate;
    }

    public void setLoanMonthRate(BigDecimal loanMonthRate) {
        this.loanMonthRate = loanMonthRate;
    }

    public String getRateNature() {
        return rateNature;
    }

    public void setRateNature(String rateNature) {
        this.rateNature = rateNature;
    }

    public Integer getRepaymentDate() {
        return repaymentDate;
    }

    public void setRepaymentDate(Integer repaymentDate) {
        this.repaymentDate = repaymentDate;
    }

    public Long getExpectLoanDate() {
        return expectLoanDate;
    }

    public void setExpectLoanDate(Long expectLoanDate) {
        this.expectLoanDate = expectLoanDate;
    }

    public Long getExpectStartDate() {
        return expectStartDate;
    }

    public void setExpectStartDate(Long expectStartDate) {
        this.expectStartDate = expectStartDate;
    }

    public String getOverdueDailyRateUnit() {
        return overdueDailyRateUnit;
    }

    public void setOverdueDailyRateUnit(String overdueDailyRateUnit) {
        this.overdueDailyRateUnit = overdueDailyRateUnit;
    }

    public String getLoanMonthRateUnit() {
        return loanMonthRateUnit;
    }

    public void setLoanMonthRateUnit(String loanMonthRateUnit) {
        this.loanMonthRateUnit = loanMonthRateUnit;
    }

    public BigDecimal getSyntheticalRate() {
        return syntheticalRate;
    }

    public void setSyntheticalRate(BigDecimal syntheticalRate) {
        this.syntheticalRate = syntheticalRate;
    }

    public String getSyntheticalRateUnit() {
        return syntheticalRateUnit;
    }

    public void setSyntheticalRateUnit(String syntheticalRateUnit) {
        this.syntheticalRateUnit = syntheticalRateUnit;
    }

    public BigDecimal getInternalRateReturn() {
        return internalRateReturn;
    }

    public void setInternalRateReturn(BigDecimal internalRateReturn) {
        this.internalRateReturn = internalRateReturn;
    }

    public String getInternalRateReturnUnit() {
        return internalRateReturnUnit;
    }

    public void setInternalRateReturnUnit(String internalRateReturnUnit) {
        this.internalRateReturnUnit = internalRateReturnUnit;
    }

    public String getRateNatureTwo() {
        return rateNatureTwo;
    }

    public void setRateNatureTwo(String rateNatureTwo) {
        this.rateNatureTwo = rateNatureTwo;
    }

    public Long getExpectEndDate() {
        return expectEndDate;
    }

    public void setExpectEndDate(Long expectEndDate) {
        this.expectEndDate = expectEndDate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getRepaymentTypeName() {
        return repaymentTypeName;
    }

    public void setRepaymentTypeName(String repaymentTypeName) {
        this.repaymentTypeName = repaymentTypeName;
    }

    public String getRepaymentTypeTwoName() {
        return repaymentTypeTwoName;
    }

    public void setRepaymentTypeTwoName(String repaymentTypeTwoName) {
        this.repaymentTypeTwoName = repaymentTypeTwoName;
    }

    public String getOverdueDailyRateUnitName() {
        return overdueDailyRateUnitName;
    }

    public void setOverdueDailyRateUnitName(String overdueDailyRateUnitName) {
        this.overdueDailyRateUnitName = overdueDailyRateUnitName;
    }

    public String getLoanMonthRateUnitName() {
        return loanMonthRateUnitName;
    }

    public void setLoanMonthRateUnitName(String loanMonthRateUnitName) {
        this.loanMonthRateUnitName = loanMonthRateUnitName;
    }

    public String getSyntheticalRateUnitName() {
        return syntheticalRateUnitName;
    }

    public void setSyntheticalRateUnitName(String syntheticalRateUnitName) {
        this.syntheticalRateUnitName = syntheticalRateUnitName;
    }

    public String getInternalRateReturnUnitName() {
        return internalRateReturnUnitName;
    }

    public void setInternalRateReturnUnitName(String internalRateReturnUnitName) {
        this.internalRateReturnUnitName = internalRateReturnUnitName;
    }

    public String getRateNatureName() {
        return rateNatureName;
    }

    public void setRateNatureName(String rateNatureName) {
        this.rateNatureName = rateNatureName;
    }

    public String getRateNatureTwoName() {
        return rateNatureTwoName;
    }

    public void setRateNatureTwoName(String rateNatureTwoName) {
        this.rateNatureTwoName = rateNatureTwoName;
    }
	public String getCurrentDealEmpName() {
		return currentDealEmpName;
	}
	public void setCurrentDealEmpName(String currentDealEmpName) {
		this.currentDealEmpName = currentDealEmpName;
	}
	public BigDecimal getApplyAmount() {
		return applyAmount;
	}
	public void setApplyAmount(BigDecimal applyAmount) {
		this.applyAmount = applyAmount;
	}
	public Integer getApplyTerm() {
		return applyTerm;
	}
	public void setApplyTerm(Integer applyTerm) {
		this.applyTerm = applyTerm;
	}
	public String getApplyTermUnit() {
		return applyTermUnit;
	}
	public void setApplyTermUnit(String applyTermUnit) {
		this.applyTermUnit = applyTermUnit;
	}
	public String getApplyTermUnitName() {
		return applyTermUnitName;
	}
	public void setApplyTermUnitName(String applyTermUnitName) {
		this.applyTermUnitName = applyTermUnitName;
	}
	public Integer getInstitutionalAudit() {
		return institutionalAudit;
	}
	public void setInstitutionalAudit(Integer institutionalAudit) {
		this.institutionalAudit = institutionalAudit;
	}
	public Integer getGroupConfirmation() {
		return groupConfirmation;
	}
	public void setGroupConfirmation(Integer groupConfirmation) {
		this.groupConfirmation = groupConfirmation;
	}
	
}
