package com.zdsoft.finance.marketing.entity;

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
 * @Title: Chargeback.java 
 * @ClassName: Chargeback 
 * @Description: 退单函
 * @author caixiekang 
 * @date 2017年3月6日 下午2:06:24 
 * @version V1.0
 */
@Entity
@Table(name ="mkt_chargeback")
public class Chargeback extends BaseEntity{

	/**   
	 * @Fields serialVersionUID   
	 */ 
	private static final long serialVersionUID = -7438632998758725746L;
	/**
	 * 案件Id
	 */
	@Column(length=32)
	private String caseApplyId;
	/**
	 * 案件号
	 */
	@Column(length=32)
	private String caseApplyCode;
	/**
	 * 案件当时状态
	 */
	@Column(length=20)
	private String caseApplyStatus;
	/**
	 * 退单原因
	 */
	@Column(length=20)
	private String chargebackCause;
	/**
	 * 备注
	 */
	@Column(length=512)
	private String remark;
	/**
     * 业务表单id
     */
    @OneToOne(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
    @JoinColumn(name = "busiFromId")
    private BusiForm busiForm;

    /**
     * 消息
     */
    @Transient
    private String msg;
	public String getMsg() {
		return msg;
	}
	/**
	 * 是否提交
	 */
	@Transient
	private Boolean submitted;
	

	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getCaseApplyId() {
		return caseApplyId;
	}
	public void setCaseApplyId(String caseApplyId) {
		this.caseApplyId = caseApplyId;
	}
	public String getCaseApplyStatus() {
		return caseApplyStatus;
	}
	public void setCaseApplyStatus(String caseApplyStatus) {
		this.caseApplyStatus = caseApplyStatus;
	}
	public String getChargebackCause() {
		return chargebackCause;
	}
	public void setChargebackCause(String chargebackCause) {
		this.chargebackCause = chargebackCause;
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
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getCaseApplyCode() {
		return caseApplyCode;
	}
	public void setCaseApplyCode(String caseApplyCode) {
		this.caseApplyCode = caseApplyCode;
	}
	public Boolean getSubmitted() {
		return submitted;
	}
	public void setSubmitted(Boolean submitted) {
		this.submitted = submitted;
	}
    
    

}
