package com.zdsoft.finance.casemanage.appointment.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.zdsoft.framework.core.common.domain.BaseEntity;


/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title:Appointment.java
 * @Package:com.zdsoft.finance.casemanage.appointment.entity
 * @Description:案件预约信息
 * @author: xiongpan
 * @date:2017年1月14日 下午6:02:06
 * @version:v1.0
 */
@Entity
@Table(name="case_appointment")
public class Appointment extends BaseEntity{

	private static final long serialVersionUID = -4465924566650539716L;
	
	/**
	 * 预约面签时间(精确到天)
	 */
	@Column
	private Long interviewDate;
	
	/**
	 * 预约面签上下午
	 */
	@Column(length=20)
	private String interviewAmOrPm;
	
	/**
	 * 预约办抵押时间(精确到天)
	 */
	@Column
	private Long mortgageDate;
	
	/**
	 * 预约办抵押上下午
	 */
	@Column(length=20)
	private String mortgageDateAmOrPm;
	
	/**
	 * 预约公证时间(精确到天)
	 */
	@Column
	private Long notarizationDate;
	
	/**
	 * 预约公证时间上下午
	 */
	@Column(length=20)
	private String notarizationAmOrPm;
	
	/**
	 * 预约委托时间(精确到天)
	 */
	@Column
	private Long entrustDate;
	
	/**
	 * 预约委托上下午
	 */
	@Column(length=20)
	private String entrustAmOrPm;
	
	/**
	 * 备注
	 */
	@Column(length=500)
	private String mo;
	
	/**
	 * 收件人
	 */
	@Column(length=32)
	private String recipients;
	
	
	/**
	 * 发送内容
	 */
	@Column(length=500)
	private String sendtContent;
	
	/**
	 * 提醒方式(0.短信;1.邮件;2.短信和邮件)
	 */
	@Column(length=20)
	private String remindWay;
	
	/**
	 * 发送短信手机号码
	 */
	@Column(length=32)
	private String phoneNumber;
	
	/**
	 * 邮件地址
	 */
	@Column(length=64)
	private String emailAddress;
	


	public Long getInterviewDate() {
		return interviewDate;
	}

	public void setInterviewDate(Long interviewDate) {
		this.interviewDate = interviewDate;
	}

	public String getInterviewAmOrPm() {
		return interviewAmOrPm;
	}

	public void setInterviewAmOrPm(String interviewAmOrPm) {
		this.interviewAmOrPm = interviewAmOrPm;
	}

	public Long getMortgageDate() {
		return mortgageDate;
	}

	public void setMortgageDate(Long mortgageDate) {
		this.mortgageDate = mortgageDate;
	}

	public String getMortgageDateAmOrPm() {
		return mortgageDateAmOrPm;
	}

	public void setMortgageDateAmOrPm(String mortgageDateAmOrPm) {
		this.mortgageDateAmOrPm = mortgageDateAmOrPm;
	}

	public Long getNotarizationDate() {
		return notarizationDate;
	}

	public void setNotarizationDate(Long notarizationDate) {
		this.notarizationDate = notarizationDate;
	}

	public String getNotarizationAmOrPm() {
		return notarizationAmOrPm;
	}

	public void setNotarizationAmOrPm(String notarizationAmOrPm) {
		this.notarizationAmOrPm = notarizationAmOrPm;
	}

	public Long getEntrustDate() {
		return entrustDate;
	}

	public void setEntrustDate(Long entrustDate) {
		this.entrustDate = entrustDate;
	}

	public String getEntrustAmOrPm() {
		return entrustAmOrPm;
	}

	public void setEntrustAmOrPm(String entrustAmOrPm) {
		this.entrustAmOrPm = entrustAmOrPm;
	}

	public String getMo() {
		return mo;
	}

	public void setMo(String mo) {
		this.mo = mo;
	}

	public String getRecipients() {
		return recipients;
	}

	public void setRecipients(String recipients) {
		this.recipients = recipients;
	}

	public String getSendtContent() {
		return sendtContent;
	}

	public void setSendtContent(String sendtContent) {
		this.sendtContent = sendtContent;
	}

	public String getRemindWay() {
		return remindWay;
	}

	public void setRemindWay(String remindWay) {
		this.remindWay = remindWay;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	
	
}
