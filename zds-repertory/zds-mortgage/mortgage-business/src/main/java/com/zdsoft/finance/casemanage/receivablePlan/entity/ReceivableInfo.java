package com.zdsoft.finance.casemanage.receivablePlan.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.zdsoft.finance.busiform.entity.BusiForm;
import com.zdsoft.framework.core.common.domain.BaseEntity;

/**
* 版权所有：重庆正大华日软件有限公司   
* @Title: ReceivableInfo.java 
* @Package com.zdsoft.finance.casemanage.receivablePlan.entity 
* @Description: 还款计划基本信息 
* @author zjx  
* @date 2017年2月22日 下午8:38:25 
* @version V1.0   
*/
@Entity
@Table(name = "case_receivable_info")
public class ReceivableInfo extends BaseEntity {

	private static final long serialVersionUID = 1L;
	/**
	 * 流程代码
	 */
	public static final String PROCEESS_CODE = "YWDM0012606";
	/**
	 * %年
	 */
	public static final String RECEIVABLEINFO_YEAR = "YWDM0011901"; 
	/**
	 * %月
	 */
	public static final String RECEIVABLEINFO_MONTH = "YWDM0011902"; 
	/**
	 * ‰日（千分号）
	 */
	public static final String RECEIVABLEINFO_DAY = "YWDM0011903"; 
	/**
	 * 放款前
	 */
	public static final Integer BEFORE_LOAN = 0;
	/**
	 * 放款后
	 */
	public static final Integer AFTER_LOAN = 1;
	/**
	 * 未审核
	 */
	public static final Integer UNAUDITED = 0;
	/**
	 * 已审核
	 */
	public static final Integer AUDITED = 1;
	/**
	 * 未确认
	 */
	public static final Integer NOT_CONFIRMED = 0;
	/**
	 * 已确认
	 */
	public static final Integer ALREADY_CONFIRMED = 1;

	/**
	 * 案件Id
	 */
	@Column(length = 32, nullable = false)
	private String caseApplyId;

	/**
	 * 还款方式
	 */
	@Column(length = 20)
	private String repaymentType;

	/**
	 * 还款方式2
	 */
	@Column(length = 20)
	private String repaymentTypeTwo;

	/**
	 * 逾期利率
	 */
	@Column(precision = 18, scale = 6)
	private BigDecimal overdueDailyRate = BigDecimal.ZERO;
	
	/**
	 * 逾期利率单位
	 */
	@Column(length=20)
	private String overdueDailyRateUnit;

	/**
	 * 贷款利率
	 */
	@Column(precision = 18, scale = 6)
	private BigDecimal loanMonthRate = BigDecimal.ZERO;
	
	/**
	 * 贷款利率单位
	 */
	@Column(length=20)
	private String loanMonthRateUnit;
	
	/**
	 * 综合利率
	 */
	@Column(precision = 18, scale = 6)
	private BigDecimal syntheticalRate = BigDecimal.ZERO;
	
	/**
	 * 综合利率单位
	 */
	@Column(length=20)
	private String syntheticalRateUnit;
	
	/**
	 * 动态转换利率
	 */
	@Column(precision = 18, scale = 6)
	private BigDecimal internalRateReturn = BigDecimal.ZERO;
	/**
	 * 动态转换利率单位
	 */
	@Column(length=20)
	private String internalRateReturnUnit;

	/**
	 * 利率性质
	 */
	@Column(length = 20)
	private String rateNature;
	
	/**
	 * 利率性质2
	 */
	@Column(length = 20)
	private String rateNatureTwo;

	/**
	 * 还款日
	 */
	@Column
	private Integer repaymentDate=0;

	/**
	 * 预计放款日期
	 */
	@Column
	private Long expectLoanDate;

	/**
	 * 预计开始日期
	 */
	@Column
	private Long expectStartDate;

	/**
	 * 预计结束日期
	 */
	@Column
	private Long expectEndDate;

	/**
	 * 备注
	 */
	@Column(length = 500)
	private String remark;
	
	 /**
     * 业务表单id
     */
    @OneToOne
    @JoinColumn(name = "busiFromId")
    private BusiForm busiForm;

    /**
	 * 放款前后标识(默认放款前：0：放款前标识；1放款后标识)
	 */
	@Column
	private Integer receivableFlag = 0;
	
	/**
	 * 机构审核(默认：0：未审核；1：已审核)
	 */
	@Column
	private Integer institutionalAudit = 0;
	
	/**
	 * 集团确认(默认：0：未确认；1：已确认)
	 */
	@Column
	private Integer groupConfirmation = 0;
	 /**
     * 下一处理人
     */
    private transient String currentDealEmpName ;
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

	public Integer getRepaymentDate() {
		return repaymentDate;
	}

	public void setRepaymentDate(Integer repaymentDate) {
		this.repaymentDate = repaymentDate;
	}

	public Long getExpectEndDate() {
		return expectEndDate;
	}

	public void setExpectEndDate(Long expectEndDate) {
		this.expectEndDate = expectEndDate;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

	public BusiForm getBusiForm() {
		return busiForm;
	}

	public void setBusiForm(BusiForm busiForm) {
		this.busiForm = busiForm;
	}

	public Integer getReceivableFlag() {
		return receivableFlag;
	}

	public void setReceivableFlag(Integer receivableFlag) {
		this.receivableFlag = receivableFlag;
	}

	public String getCurrentDealEmpName() {
		return currentDealEmpName;
	}

	public void setCurrentDealEmpName(String currentDealEmpName) {
		this.currentDealEmpName = currentDealEmpName;
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
