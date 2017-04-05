package com.zdsoft.finance.casemanage.limitApply.vo;

import java.math.BigDecimal;

import com.zdsoft.finance.casemanage.limitApply.entity.CaseLimitApply;
import com.zdsoft.finance.common.base.BaseVo;
import com.zdsoft.finance.common.utils.VoUtil;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: CaseLimitApplyVo.java 
 * @ClassName: CaseLimitApplyVo 
 * @Description: 案件额度申请Vo
 * @author xj 
 * @date 2017年2月17日 下午2:28:12 
 * @version V1.0
 */
public class CaseLimitApplyVo extends BaseVo<CaseLimitApply>{
	
	private static final long serialVersionUID = -2361725760132354363L;
	
	/**
	 * 案件id
	 */
	private String caseApplyId;
	
	/**
	 * 资金计划id
	 */
	private String fundPlanId;
	
	/**
	 * 资金计划名称
	 */
	private String fundPlanName;
	
	/**
	 * 贷款发放账户机构名称
	 */
	private String loanOutAccountBranchName;
	
	/**
	 * 贷款发放账户账户名
	 */
	private String loanOutAccountName;
	
	/**
	 * 贷款发放账户
	 */
	private String loanOutAccount;
	
	/**
	 * 贷款回款账户机构名称
	 */
	private String loanBackAccountBranchName;
	
	/**
	 * 贷款回款账户账户名
	 */
	private String loanBackAccountName;
	
	/**
	 * 贷款回款账户
	 */
	private String loanBackAccount;
	
	/**
	 * 状态code
	 */
	private String effectiveStatus;
	
	/**
	 * 状态name
	 */
	private String effectiveStatusName;
	
	/**
	 * 申请额度
	 */
	private BigDecimal applyLimitAmount;
	
	/**
	 * 申请人code
	 */
	private String applyEmpCode;
	
	/**
	 * 申请人姓名
	 */
	private String applyEmpName;
	
	/**
	 * 分配日期
	 */
	private Long allotDate;
	
	/**
	 * 申请时间
	 */
	private Long applyDate;
	
	/**
	 * 申请时间
	 */
	private String applyDateStr;
	
	/**
	 * 取消人code
	 */
	private String cancelEmpCode;
	
	/**
	 * 取消人姓名
	 */
	private String cancelEmpName;
	
	/**
	 * 取消时间
	 */
	private Long cancelDate;
	
	public CaseLimitApplyVo() {
    }

    public CaseLimitApplyVo(CaseLimitApply po) {
    	super(po,null,new String[]{"effectiveStatus"},new String[]{"applyDate|yyyy-MM-dd HH:mm:ss",});
    }
    
    public CaseLimitApplyVo(CaseLimitApply po, String[] args, String[] simpleArgs) throws Exception {
        VoUtil.copyPoperties(po, this, false, args, simpleArgs);
    }

    public CaseLimitApply toPO() {
        CaseLimitApply po = new CaseLimitApply();
        return super.toPo(this, po);
    }
	public String getCaseApplyId() {
		return caseApplyId;
	}

	public void setCaseApplyId(String caseApplyId) {
		this.caseApplyId = caseApplyId;
	}

	public String getFundPlanId() {
		return fundPlanId;
	}

	public void setFundPlanId(String fundPlanId) {
		this.fundPlanId = fundPlanId;
	}

	public String getFundPlanName() {
		return fundPlanName;
	}

	public void setFundPlanName(String fundPlanName) {
		this.fundPlanName = fundPlanName;
	}

	public String getLoanOutAccountBranchName() {
		return loanOutAccountBranchName;
	}

	public void setLoanOutAccountBranchName(String loanOutAccountBranchName) {
		this.loanOutAccountBranchName = loanOutAccountBranchName;
	}

	public String getLoanOutAccountName() {
		return loanOutAccountName;
	}

	public void setLoanOutAccountName(String loanOutAccountName) {
		this.loanOutAccountName = loanOutAccountName;
	}

	public String getLoanOutAccount() {
		return loanOutAccount;
	}

	public void setLoanOutAccount(String loanOutAccount) {
		this.loanOutAccount = loanOutAccount;
	}

	public String getLoanBackAccountBranchName() {
		return loanBackAccountBranchName;
	}

	public void setLoanBackAccountBranchName(String loanBackAccountBranchName) {
		this.loanBackAccountBranchName = loanBackAccountBranchName;
	}

	public String getLoanBackAccountName() {
		return loanBackAccountName;
	}

	public void setLoanBackAccountName(String loanBackAccountName) {
		this.loanBackAccountName = loanBackAccountName;
	}

	public String getLoanBackAccount() {
		return loanBackAccount;
	}

	public void setLoanBackAccount(String loanBackAccount) {
		this.loanBackAccount = loanBackAccount;
	}

	public String getEffectiveStatus() {
		return effectiveStatus;
	}

	public void setEffectiveStatus(String effectiveStatus) {
		this.effectiveStatus = effectiveStatus;
	}

/*	public Boolean getIsGetPrepareLimit() {
		return isGetPrepareLimit;
	}

	public void setIsGetPrepareLimit(Boolean isGetPrepareLimit) {
		this.isGetPrepareLimit = isGetPrepareLimit;
	}*/

	public BigDecimal getApplyLimitAmount() {
		return applyLimitAmount;
	}

	public void setApplyLimitAmount(BigDecimal applyLimitAmount) {
		this.applyLimitAmount = applyLimitAmount;
	}

	public String getApplyEmpCode() {
		return applyEmpCode;
	}

	public void setApplyEmpCode(String applyEmpCode) {
		this.applyEmpCode = applyEmpCode;
	}

	public String getApplyEmpName() {
		return applyEmpName;
	}

	public void setApplyEmpName(String applyEmpName) {
		this.applyEmpName = applyEmpName;
	}

	public Long getAllotDate() {
		return allotDate;
	}

	public void setAllotDate(Long allotDate) {
		this.allotDate = allotDate;
	}

	public Long getApplyDate() {
		return applyDate;
	}

	public void setApplyDate(Long applyDate) {
		this.applyDate = applyDate;
	}

	public String getCancelEmpCode() {
		return cancelEmpCode;
	}

	public void setCancelEmpCode(String cancelEmpCode) {
		this.cancelEmpCode = cancelEmpCode;
	}

	public String getCancelEmpName() {
		return cancelEmpName;
	}

	public void setCancelEmpName(String cancelEmpName) {
		this.cancelEmpName = cancelEmpName;
	}

	public Long getCancelDate() {
		return cancelDate;
	}

	public void setCancelDate(Long cancelDate) {
		this.cancelDate = cancelDate;
	}

	public String getApplyDateStr() {
		return applyDateStr;
	}

	public void setApplyDateStr(String applyDateStr) {
		this.applyDateStr = applyDateStr;
	}

	public String getEffectiveStatusName() {
		return effectiveStatusName;
	}

	public void setEffectiveStatusName(String effectiveStatusName) {
		this.effectiveStatusName = effectiveStatusName;
	}
	
	

	
	
	
	
	

}
