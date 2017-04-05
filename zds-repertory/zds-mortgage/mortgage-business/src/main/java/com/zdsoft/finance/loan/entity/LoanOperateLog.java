package com.zdsoft.finance.loan.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.zdsoft.framework.core.common.domain.BaseEntity;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title: LoanOperateLog.java 
 * @ClassName: LoanOperateLog 
 * @Description: 放款日志实体类
 * @author huangwei 
 * @date 2017年2月22日 上午11:08:03 
 * @version V1.0
 */
@Entity
@Table(name = "loan_operation_log")
public class LoanOperateLog  extends BaseEntity{
	/**   
	 * @Fields serialVersionUID : TODO   
	 */ 
	private static final long serialVersionUID = -3474623493260186087L;
	/**
	 * 操作类型(1.准放款，2取消准放款)
	 */
	@Column(nullable = false,length=32)
	private String dealType;
	/**
	 * 处理金额（元）
	 */
	@Column(nullable = false, precision = 18, scale = 6)
	private BigDecimal dealAmount = BigDecimal.ZERO;
	/**
	 * 操作时间
	 */
	@Column
	private long dealDate;
	/**
	 * 实际发生时间
	 */
	@Column
	private long dealRealDate;
	/**
	 * 备注
	 */
	@Column(length=512)
	private String remark;
	/**
	 * 放款申请Id
	 */
	@Column(nullable = false,length=32)
	private String loanApplyId;
	/**
	 * 处理人
	 */
	@Column(length=32)
	private String handlerCode;
	public String getDealType() {
		return dealType;
	}
	public void setDealType(String dealType) {
		this.dealType = dealType;
	}
	public long getDealRealDate() {
		return dealRealDate;
	}
	public void setDealRealDate(long dealRealDate) {
		this.dealRealDate = dealRealDate;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getLoanApplyId() {
		return loanApplyId;
	}
	public void setLoanApplyId(String loanApplyId) {
		this.loanApplyId = loanApplyId;
	}
	public BigDecimal getDealAmount() {
		return dealAmount;
	}
	public void setDealAmount(BigDecimal dealAmount) {
		this.dealAmount = dealAmount;
	}
	public long getDealDate() {
		return dealDate;
	}
	public void setDealDate(long dealDate) {
		this.dealDate = dealDate;
	}
	public String getHandlerCode() {
		return handlerCode;
	}
	public void setHandlerCode(String handlerCode) {
		this.handlerCode = handlerCode;
	}
	
	
}
