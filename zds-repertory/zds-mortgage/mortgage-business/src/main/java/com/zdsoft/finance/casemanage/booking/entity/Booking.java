package com.zdsoft.finance.casemanage.booking.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.zdsoft.framework.core.common.domain.BaseEntity;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: Booking.java 
 * @ClassName: Booking 
 * @Description: 预约信息
 * @author dengyy 
 * @date 2017年2月13日 下午7:27:56 
 * @version V1.0
 */
@Entity
@Table(name="case_Booking")
public class Booking extends BaseEntity{
    
	private static final long serialVersionUID = -4465924566650539716L;
	
	/**
	 * 未预约(默认)
	 */
	public static final String  BOOKINGTYPE_ONE = "YWDM0051061" ;
	/**
	 * 已预约
	 */
	public static final String  BOOKINGTYPE_TWO = "YWDM0051062" ;
	
	/**
     * 提醒方式 YWDM0051063.短信;
     */
    public static final String REMINDWAY_ONE = "YWDM0051063";
	/**
	 * 提醒方式 YWDM0051064.邮件
	 */
    public static final String REMINDWAY_TWO = "YWDM0051064";
    /**
     * 提醒方式 YWDM0051065.短信和邮件
     */
    public static final String REMINDWAY_YWDM = "YWDM0051065";
    
    /**
     * 上午
     */
    public static final String MORNING = "YWDM0051059";
    
    /**
     * 下午
     */
    public static final String AFTERNOON = "YWDM0051060";
    
	/**
	 * 预约面签时间(精确到天)
	 */
	@Column(length=16)
	private Long interviewDate;
	
	/**
	 * 预约面签上下午
	 */
	@Column(length=20)
	private String interviewAmOrPm;
	
	/**
	 * 预约办抵押时间(精确到天)
	 */
	@Column(length=16)
	private Long mortgageDate;
	
	/**
	 * 预约办抵押上下午
	 */
	@Column(length=20)
	private String mortgageDateAmOrPm;
	
	/**
	 * 预约公证时间(精确到天)
	 */
	@Column(length=16)
	private Long notarizationDate;
	
	/**
	 * 预约公证时间上下午
	 */
	@Column(length=20)
	private String notarizationAmOrPm;
	
	/**
	 * 预约委托时间(精确到天)
	 */
	@Column(length=16)
	private Long entrustDate;
	
	/**
	 * 预约委托上下午
	 */
	@Column(length=20)
	private String entrustAmOrPm;
	
	/**
	 * 备注
	 */
	@Column(length=512)
	private String remark;
	
	/**
	 * 收件人
	 */
	@Column(length=64)
	private String recipients;
	
	
	/**
	 * 发送内容
	 */
	@Column(length=512)
	private String sendtContent;
	
	/**
	 * 提醒方式(YWDM0051063.短信;YWDM0051064.邮件;YWDM0051065.短信和邮件) simplecode维护
	 */
	@Column(length=20)
	private String remindWay;
	
	/**
	 * 发送短信手机号码
	 */
	@Column(length=11)
	private String phoneNumber;
	
	/**
	 * 邮件地址
	 */
	@Column(length=64)
	private String emailAddress;
	
	/**
	 * 案件id
	 */
	@Column(length=32)
	private String caseApplyId ;
	
	 /**
     * 预约状态( YWDM0051061.未预约(默认);YWDM0051062.已预约) simplecode维护
     */
    @Column(length = 20)
    private String bookingType = "YWDM0051061";

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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getCaseApplyId() {
        return caseApplyId;
    }

    public void setCaseApplyId(String caseApplyId) {
        this.caseApplyId = caseApplyId;
    }

    public String getBookingType() {
        return bookingType;
    }

    public void setBookingType(String bookingType) {
        this.bookingType = bookingType;
    }

	
	
}
