package com.zdsoft.finance.finance.vo;

import java.math.BigDecimal;

import com.zdsoft.finance.common.base.BaseVo;
import com.zdsoft.finance.common.utils.EnumTimeUnit;
import com.zdsoft.finance.common.utils.TimeUtil;
import com.zdsoft.finance.finance.entity.RepaymentAmountAllot;
import com.zdsoft.framework.core.common.util.ObjectHelper;

/**
 * 版权所有：重庆正大华日软件有限公司
 * 
 * @Title: RepaymentAmountAllotVo.java
 * @ClassName: RepaymentAmountAllotVo
 * @Description: 还款-收款单-金额分配Vo
 * @author jincheng
 * @date 2017年2月24日 上午11:17:10
 * @version V1.0
 */
public class RepaymentAmountAllotVo extends BaseVo<RepaymentAmountAllot> {

	private static final long serialVersionUID = 1L;

	/**
	 * 案件id
	 */
	private String caseApplyId;

	/**
	 * 收款单id
	 */
	private String receiptId;

	/**
	 * 还款计划id
	 */
	private String planId;

	/**
	 * 还款类型 1:正常 2:减免
	 */
	private Integer paidType;

	/**
	 * 是否复核
	 */
	private Boolean isReview = false;

	// ---------------------------还款计划--------------------------------
	/**
	 * 期数
	 */
	private Integer periods;

	/**
	 * 还款日期
	 */
	private Long planRepayDate;

	/**
	 * 当前还款日期
	 */
	private Long paidRepayDate;
	
	/**
	 * 逾期天数
	 */
	private Long overdueDays;

	/**
	 * 当期本金
	 */

	private BigDecimal planPrincipalAmount;

	/**
	 * 当期利息
	 */

	private BigDecimal planInterestAmount;

	/**
	 * 当期服务费
	 */

	private BigDecimal planServiceFee;

	/**
	 * 当期剩余本金
	 */

	private BigDecimal remainPrincipal;

	/**
	 * 罚息
	 */

	private BigDecimal planPenalty;

	/**
	 * 当期罚息
	 */

	private BigDecimal currentPlanPenalty;

	/**
	 * 违约金
	 */

	private BigDecimal planDamages;

	// -----------------------------待还---------------------------------

	/**
	 * 当期本金
	 */

	private BigDecimal dplanPrincipalAmount;

	/**
	 * 当期利息
	 */

	private BigDecimal dplanInterestAmount;

	/**
	 * 当期服务费
	 */

	private BigDecimal dplanServiceFee;

	/**
	 * 罚息
	 */

	private BigDecimal dplanPenalty;

	/**
	 * 当期罚息
	 */

	private BigDecimal dcurrentPlanPenalty;

	/**
	 * 违约金
	 */

	private BigDecimal dplanDamages;

	// -----------------------------本次实收(可操作)---------------------------
	/**
	 * 当期本金
	 */

	private BigDecimal paidPrincipalAmount;

	/**
	 * 当期利息
	 */

	private BigDecimal paidInterestAmount;

	/**
	 * 当期服务费
	 */

	private BigDecimal paidServiceFee;

	/**
	 * 罚息
	 */

	private BigDecimal paidPenalty;

	/**
	 * 当期罚息
	 */

	private BigDecimal currentPaidPenalty;

	/**
	 * 违约金
	 */

	private BigDecimal paidDamages;
	
	/**
	 * 实付本金
	 */
	private BigDecimal paidTotalPrincipalAmount = BigDecimal.ZERO;
	
	/**
	 * 实付利息
	 */
	private BigDecimal paidTotalInterestAmount = BigDecimal.ZERO;
	
	/**
	 * 实付服务费
	 */
	private BigDecimal paidTotalServiceFee = BigDecimal.ZERO;
	
	/**
	 * 实付罚息
	 */
	private BigDecimal paidTotalPenalty = BigDecimal.ZERO;
	
	/**
	 * 实付当前罚息
	 */
	private BigDecimal currentPaidTotalPenalty = BigDecimal.ZERO;
	
	/**
	 * 实付违约金
	 */
	private BigDecimal paidTotalDamages = BigDecimal.ZERO;

	/**
	 * 减免当期罚息
	 */
	private BigDecimal currentReductionPenalty = BigDecimal.ZERO;

	/**
	 * 减免罚息
	 */
	private BigDecimal reductionPenalty = BigDecimal.ZERO;

	/**
	 * 减免违约金
	 */
	private BigDecimal reductionDamages = BigDecimal.ZERO;
	
	/**
	 * 本息结清日
	 */
	private Long  piSettlementDate;

	public String getCaseApplyId() {
		return caseApplyId;
	}

	public void setCaseApplyId(String caseApplyId) {
		this.caseApplyId = caseApplyId;
	}

	public String getReceiptId() {
		return receiptId;
	}

	public void setReceiptId(String receiptId) {
		this.receiptId = receiptId;
	}

	public String getPlanId() {
		return planId;
	}

	public void setPlanId(String planId) {
		this.planId = planId;
	}

	public Integer getPaidType() {
		return paidType;
	}

	public void setPaidType(Integer paidType) {
		this.paidType = paidType;
	}

	public Boolean getIsReview() {
		return isReview;
	}

	public void setIsReview(Boolean isReview) {
		this.isReview = isReview;
	}

	public Integer getPeriods() {
		return periods;
	}

	public void setPeriods(Integer periods) {
		this.periods = periods;
	}

	public Long getPlanRepayDate() {
		return planRepayDate;
	}

	public void setPlanRepayDate(Long planRepayDate) {
		this.planRepayDate = planRepayDate;
	}

	public Long getPaidRepayDate() {
		return paidRepayDate;
	}

	public void setPaidRepayDate(Long paidRepayDate) {
		this.paidRepayDate = paidRepayDate;
	}

	public BigDecimal getPlanPrincipalAmount() {
		return planPrincipalAmount;
	}

	public void setPlanPrincipalAmount(BigDecimal planPrincipalAmount) {
		this.planPrincipalAmount = planPrincipalAmount;
	}

	public BigDecimal getPlanInterestAmount() {
		return planInterestAmount;
	}

	public void setPlanInterestAmount(BigDecimal planInterestAmount) {
		this.planInterestAmount = planInterestAmount;
	}

	public BigDecimal getPlanServiceFee() {
		return planServiceFee;
	}

	public void setPlanServiceFee(BigDecimal planServiceFee) {
		this.planServiceFee = planServiceFee;
	}

	public BigDecimal getRemainPrincipal() {
		return remainPrincipal;
	}

	public void setRemainPrincipal(BigDecimal remainPrincipal) {
		this.remainPrincipal = remainPrincipal;
	}

	public BigDecimal getPlanPenalty() {
		return planPenalty;
	}

	public void setPlanPenalty(BigDecimal planPenalty) {
		this.planPenalty = planPenalty;
	}

	public BigDecimal getCurrentPlanPenalty() {
		return currentPlanPenalty;
	}

	public void setCurrentPlanPenalty(BigDecimal currentPlanPenalty) {
		this.currentPlanPenalty = currentPlanPenalty;
	}

	public BigDecimal getPlanDamages() {
		return planDamages;
	}

	public void setPlanDamages(BigDecimal planDamages) {
		this.planDamages = planDamages;
	}

	public BigDecimal getDplanPrincipalAmount() {
		return dplanPrincipalAmount;
	}

	public void setDplanPrincipalAmount(BigDecimal dplanPrincipalAmount) {
		this.dplanPrincipalAmount = dplanPrincipalAmount;
	}

	public BigDecimal getDplanInterestAmount() {
		return dplanInterestAmount;
	}

	public void setDplanInterestAmount(BigDecimal dplanInterestAmount) {
		this.dplanInterestAmount = dplanInterestAmount;
	}

	public BigDecimal getDplanServiceFee() {
		return dplanServiceFee;
	}

	public void setDplanServiceFee(BigDecimal dplanServiceFee) {
		this.dplanServiceFee = dplanServiceFee;
	}

	public BigDecimal getDplanPenalty() {
		return dplanPenalty;
	}

	public void setDplanPenalty(BigDecimal dplanPenalty) {
		this.dplanPenalty = dplanPenalty;
	}

	public BigDecimal getDcurrentPlanPenalty() {
		return dcurrentPlanPenalty;
	}

	public void setDcurrentPlanPenalty(BigDecimal dcurrentPlanPenalty) {
		this.dcurrentPlanPenalty = dcurrentPlanPenalty;
	}

	public BigDecimal getDplanDamages() {
		return dplanDamages;
	}

	public void setDplanDamages(BigDecimal dplanDamages) {
		this.dplanDamages = dplanDamages;
	}

	public BigDecimal getPaidPrincipalAmount() {
		return paidPrincipalAmount;
	}

	public void setPaidPrincipalAmount(BigDecimal paidPrincipalAmount) {
		this.paidPrincipalAmount = paidPrincipalAmount;
	}

	public BigDecimal getPaidInterestAmount() {
		return paidInterestAmount;
	}

	public void setPaidInterestAmount(BigDecimal paidInterestAmount) {
		this.paidInterestAmount = paidInterestAmount;
	}

	public BigDecimal getPaidServiceFee() {
		return paidServiceFee;
	}

	public void setPaidServiceFee(BigDecimal paidServiceFee) {
		this.paidServiceFee = paidServiceFee;
	}

	public BigDecimal getPaidPenalty() {
		return paidPenalty;
	}

	public void setPaidPenalty(BigDecimal paidPenalty) {
		this.paidPenalty = paidPenalty;
	}

	public BigDecimal getCurrentPaidPenalty() {
		return currentPaidPenalty;
	}

	public void setCurrentPaidPenalty(BigDecimal currentPaidPenalty) {
		this.currentPaidPenalty = currentPaidPenalty;
	}

	public BigDecimal getPaidDamages() {
		return paidDamages;
	}

	public void setPaidDamages(BigDecimal paidDamages) {
		this.paidDamages = paidDamages;
	}

	/**
	 * 
	 * @Title: getOverdueDays 
	 * @Description: 计算逾期天数
	 * @author zhoushichao 
	 * @return
	 */
	public Long getOverdueDays() {
		if(ObjectHelper.isEmpty(this.paidRepayDate)){
			if( TimeUtil.getCurrentDateInteger().longValue()>this.planRepayDate){
				overdueDays=Long.valueOf(TimeUtil.compareDate(""+this.planRepayDate, ""+TimeUtil.getCurrentDateInteger().longValue(), EnumTimeUnit.Day));
			}else{
				overdueDays=0l;
			}
		}else{
			if(this.paidRepayDate>this.planRepayDate){
				overdueDays=Long.valueOf(TimeUtil.compareDate(""+this.planRepayDate, ""+this.paidRepayDate, EnumTimeUnit.Day));
			}
		}
		return overdueDays;
	}

	public void setOverdueDays(Long overdueDays) {
		this.overdueDays = overdueDays;
	}

	public BigDecimal getPaidTotalPrincipalAmount() {
		return paidTotalPrincipalAmount;
	}

	public void setPaidTotalPrincipalAmount(BigDecimal paidTotalPrincipalAmount) {
		this.paidTotalPrincipalAmount = paidTotalPrincipalAmount;
	}

	public BigDecimal getPaidTotalInterestAmount() {
		return paidTotalInterestAmount;
	}

	public void setPaidTotalInterestAmount(BigDecimal paidTotalInterestAmount) {
		this.paidTotalInterestAmount = paidTotalInterestAmount;
	}

	public BigDecimal getPaidTotalServiceFee() {
		return paidTotalServiceFee;
	}

	public void setPaidTotalServiceFee(BigDecimal paidTotalServiceFee) {
		this.paidTotalServiceFee = paidTotalServiceFee;
	}

	public BigDecimal getPaidTotalPenalty() {
		return paidTotalPenalty;
	}

	public void setPaidTotalPenalty(BigDecimal paidTotalPenalty) {
		this.paidTotalPenalty = paidTotalPenalty;
	}

	public BigDecimal getCurrentPaidTotalPenalty() {
		return currentPaidTotalPenalty;
	}

	public void setCurrentPaidTotalPenalty(BigDecimal currentPaidTotalPenalty) {
		this.currentPaidTotalPenalty = currentPaidTotalPenalty;
	}

	public BigDecimal getPaidTotalDamages() {
		return paidTotalDamages;
	}

	public void setPaidTotalDamages(BigDecimal paidTotalDamages) {
		this.paidTotalDamages = paidTotalDamages;
	}

	public BigDecimal getCurrentReductionPenalty() {
		return currentReductionPenalty;
	}

	public void setCurrentReductionPenalty(BigDecimal currentReductionPenalty) {
		this.currentReductionPenalty = currentReductionPenalty;
	}

	public BigDecimal getReductionPenalty() {
		return reductionPenalty;
	}

	public void setReductionPenalty(BigDecimal reductionPenalty) {
		this.reductionPenalty = reductionPenalty;
	}

	public BigDecimal getReductionDamages() {
		return reductionDamages;
	}

	public void setReductionDamages(BigDecimal reductionDamages) {
		this.reductionDamages = reductionDamages;
	}

	public Long getPiSettlementDate() {
		return piSettlementDate;
	}

	public void setPiSettlementDate(Long piSettlementDate) {
		this.piSettlementDate = piSettlementDate;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public RepaymentAmountAllotVo() {
		super();
	}

	public RepaymentAmountAllotVo(RepaymentAmountAllot entity){
		super(entity);
	}

	public RepaymentAmountAllot toPo(){
		RepaymentAmountAllot entity = new RepaymentAmountAllot();
		return super.toPo(this, entity);
	}

}
