package com.zdsoft.finance.contract.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import com.zdsoft.framework.core.common.domain.BaseEntity;  
/** 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: ConCaseContract.java 
 * @ClassName: ConCaseContract 
 * @Description: 案件合同
 * @author wangnengduo
 * @date 2017年2月28日 上午10:57:44 
 * @version V1.0 
 */ 

@Entity
@Table(name = "con_case_contract")
public class ConCaseContract  extends BaseEntity{
    
    /**
     * 合同编号 表达式 id
     */
    public static final String EXPRESSION_ID = "YW000000000001" ;
    
	/**
	 * id
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 案件ID
	 */
	@Column(length = 32)
	private String caseApplyId;
	/**
	 * 合同编号
	 */
	@Column(length = 32)
	private String contractNo;

	/**
	 * 合同金额
	 */
	@Column(precision = 18, scale = 6)
	private BigDecimal contractAmount = BigDecimal.ZERO;
	/**
	 * 利率
	 */
	@Column(precision = 18, scale = 6)
	private BigDecimal contractRate = BigDecimal.ZERO;
	
	/**
	 * 2017/3/1 dengyy
	 * 利率单位
	 */
	@Column(length=32)
	private String contractRateUnit ;
	
	/**
	 * 还款方式
	 */
	@Column(length = 32)
	private String repaymentMethod;
	/**
	 * 合同开始日期
	 */
	@Column
	private Long contractStartDate;
	/**
	 * 合同结束日期
	 */
	@Column
	private Long contractEndDate;
	/**
	 * 备注
	 */
	@Column(length = 3000)
	private String remark;
	
	public String getCaseApplyId() {
		return caseApplyId;
	}
	public void setCaseApplyId(String caseApplyId) {
		this.caseApplyId = caseApplyId;
	}
	public String getContractNo() {
		return contractNo;
	}
	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}
	
	public BigDecimal getContractAmount() {
		return contractAmount;
	}
	public void setContractAmount(BigDecimal contractAmount) {
		this.contractAmount = contractAmount;
	}
	public BigDecimal getContractRate() {
		return contractRate;
	}
	public void setContractRate(BigDecimal contractRate) {
		this.contractRate = contractRate;
	}
	public String getRepaymentMethod() {
		return repaymentMethod;
	}
	public void setRepaymentMethod(String repaymentMethod) {
		this.repaymentMethod = repaymentMethod;
	}
	public Long getContractStartDate() {
		return contractStartDate;
	}
	public void setContractStartDate(Long contractStartDate) {
		this.contractStartDate = contractStartDate;
	}
	public Long getContractEndDate() {
		return contractEndDate;
	}
	public void setContractEndDate(Long contractEndDate) {
		this.contractEndDate = contractEndDate;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
    public String getContractRateUnit() {
        return contractRateUnit;
    }
    public void setContractRateUnit(String contractRateUnit) {
        this.contractRateUnit = contractRateUnit;
    }

}
