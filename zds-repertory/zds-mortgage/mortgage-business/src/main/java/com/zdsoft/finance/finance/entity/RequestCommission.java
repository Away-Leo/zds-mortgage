package com.zdsoft.finance.finance.entity;

import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.zdsoft.finance.busiform.entity.BusiForm;
import com.zdsoft.framework.core.common.domain.BaseEntity;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title:RequestCommission.java
 * @Package:com.zdsoft.finance.finance.entity
 * @Description:支佣请款
 * @author: xiangjx
 * @date:2017年3月2日 上午10:44:56
 * @version:v1.0
 */
@Entity
@Table(name = "fin_request_commission")
public class RequestCommission extends BaseEntity {

	private static final long serialVersionUID = 1L;
	
	/**
	 * 流程代码
	 */
	public static final String proceessCode = "YWDM0012605"; 

	/**
	 * 单据号
	 */
	@Column(length = 32)
	private String billCode;

	/**
	 * 请款日期
	 */
	@Column()
	private Long reqFundsDate;
	
	/**
	 * 请款金额
	 */
	@Column(precision = 18, scale = 6)
	private  BigDecimal reqFundsAmount=BigDecimal.ZERO;

	/**
	 * 收款银行账户名称
	 */
	@Column(length = 100)
	private String accountName;

	/**
	 * 收款开户行
	 */
	@Column(length = 100)
	private String bankName;

	/**
	 * 收款账号
	 */
	@Column(length = 30)
	private String bankAccount;

	/**
	 * 往来单位
	 */
	@Column(length = 30)
	private String contactCompany;

	/**
	 * 请款项目
	 */
	@Column(length = 30)
	private String requestFundsProject;

	/**
	 * 是否拨款
	 */
	@Column(name = "isGrant")
	@org.hibernate.annotations.Type(type="true_false")
	private Boolean isGrant=false;
	
	/**
	 * 实际拨款日期
	 */
	@Column()
	private Long grantDate;
	
	/**
	 * 是否付款
	 */
	@Column(name = "isPayment")
	@org.hibernate.annotations.Type(type="true_false")
	private Boolean isPayment=false;
	
	/**
	 * 实际付款日期
	 */
	@Column()
	private Long paymentDate;

	/**
	 * 摘要
	 */
	@Column(length = 200)
	private String summary;

	/**
	 * 备注
	 */
	@Column(length = 200)
	private String remark;
	
    /**
     * 业务表单id
     */
    @OneToOne(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
    @JoinColumn(name = "busiFromId")
    private BusiForm busiForm;
    
    /**
	 * 数据状态 0:草稿1:流程中 2:通过 3：不通过
	 */
	@Column(length = 2)
	private Integer applyState=0;
	
	/**
	 * 机构id
	 */
	@Column(length = 32)
	private String orgId;
	
	/**
	 * 机构名称
	 */
	@Column(length = 60)
	private String orgName;

	@Transient
	private String msg;

	/**
	 * 是否提交
	 */
	@Transient
	private Boolean isSubmit = false;

	public String getBillCode() {
		return billCode;
	}

	public void setBillCode(String billCode) {
		this.billCode = billCode;
	}

	public Long getReqFundsDate() {
		return reqFundsDate;
	}

	public void setReqFundsDate(Long reqFundsDate) {
		this.reqFundsDate = reqFundsDate;
	}

	public BigDecimal getReqFundsAmount() {
		return reqFundsAmount;
	}

	public void setReqFundsAmount(BigDecimal reqFundsAmount) {
		this.reqFundsAmount = reqFundsAmount;
	}

	public Boolean getIsGrant() {
		return isGrant;
	}

	public void setIsGrant(Boolean isGrant) {
		this.isGrant = isGrant;
	}

	public Boolean getIsPayment() {
		return isPayment;
	}

	public void setIsPayment(Boolean isPayment) {
		this.isPayment = isPayment;
	}

	public Integer getApplyState() {
		return applyState;
	}

	public void setApplyState(Integer applyState) {
		this.applyState = applyState;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getBankAccount() {
		return bankAccount;
	}

	public void setBankAccount(String bankAccount) {
		this.bankAccount = bankAccount;
	}

	public String getContactCompany() {
		return contactCompany;
	}

	public void setContactCompany(String contactCompany) {
		this.contactCompany = contactCompany;
	}

	public String getRequestFundsProject() {
		return requestFundsProject;
	}

	public void setRequestFundsProject(String requestFundsProject) {
		this.requestFundsProject = requestFundsProject;
	}

	public Long getGrantDate() {
		return grantDate;
	}

	public void setGrantDate(Long grantDate) {
		this.grantDate = grantDate;
	}

	public Long getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(Long paymentDate) {
		this.paymentDate = paymentDate;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Boolean getIsSubmit() {
		return isSubmit;
	}

	public void setIsSubmit(Boolean isSubmit) {
		this.isSubmit = isSubmit;
	}

	public BusiForm getBusiForm() {
		return busiForm;
	}

	public void setBusiForm(BusiForm busiForm) {
		this.busiForm = busiForm;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
