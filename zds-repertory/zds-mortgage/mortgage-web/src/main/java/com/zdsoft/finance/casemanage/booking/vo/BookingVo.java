package com.zdsoft.finance.casemanage.booking.vo;

import com.zdsoft.finance.casemanage.booking.entity.Booking;
import com.zdsoft.finance.common.base.BaseVo;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: BookingVo.java 
 * @ClassName: BookingVo 
 * @Description: 案件预约Vo
 * @author dengyy 
 * @date 2017年2月13日 下午7:51:11 
 * @version V1.0
 */
public class BookingVo extends BaseVo<Booking>{

	private static final long serialVersionUID = -1977321054382047776L;
	
	/**
     * 预约面签时间(精确到天)
     */
    private Long interviewDate;
    
    /**
     * 预约面签上下午
     */
    private String interviewAmOrPm;
    
    /**
     * 预约面签上下午名称
     */
    private String interviewAmOrPmName;
    
    /**
     * 预约办抵押时间(精确到天)
     */
    private Long mortgageDate;
    
    /**
     * 预约办抵押上下午
     */
    private String mortgageDateAmOrPm;
    
    /**
     * 预约办抵押上下午名称
     */
    private String mortgageDateAmOrPmName;
    
    /**
     * 预约公证时间(精确到天)
     */
    private Long notarizationDate;
    
    /**
     * 预约公证时间上下午
     */
    private String notarizationAmOrPm;
    
    /**
     * 预约公证时间上下午名称
     */
    private String notarizationAmOrPmName;
    
    /**
     * 预约委托时间(精确到天)
     */
    private Long entrustDate;
    
    /**
     * 预约委托上下午
     */
    private String entrustAmOrPm;
    
    /**
     * 预约委托上下午名称
     */
    private String entrustAmOrPmName;
    
    /**
     * 备注
     */
    private String remark;
    
    /**
     * 收件人
     */
    private String recipients;
    
    
    /**
     * 发送内容
     */
    private String sendtContent;
    
    /**
     * 提醒方式(0.短信;1.邮件;2.短信和邮件)
     */
    private String remindWay;
    
    /**
     * 提醒方式(0.短信;1.邮件;2.短信和邮件)
     */
    private String remindWayName;
    
    /**
     * 发送短信手机号码
     */
    private String phoneNumber;
    
    /**
     * 邮件地址
     */
    private String emailAddress;
    
    /**
     * 案件id
     */
    private String caseApplyId ;
    
     /**
     * 预约状态(0.未预约(默认);1.已预约)
     */
    private String bookingType ;

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
    
    public String getInterviewAmOrPmName() {
        return interviewAmOrPmName;
    }

    public void setInterviewAmOrPmName(String interviewAmOrPmName) {
        this.interviewAmOrPmName = interviewAmOrPmName;
    }

    public String getMortgageDateAmOrPmName() {
        return mortgageDateAmOrPmName;
    }

    public void setMortgageDateAmOrPmName(String mortgageDateAmOrPmName) {
        this.mortgageDateAmOrPmName = mortgageDateAmOrPmName;
    }

    public String getNotarizationAmOrPmName() {
        return notarizationAmOrPmName;
    }

    public void setNotarizationAmOrPmName(String notarizationAmOrPmName) {
        this.notarizationAmOrPmName = notarizationAmOrPmName;
    }
    
    public String getEntrustAmOrPmName() {
        return entrustAmOrPmName;
    }

    public void setEntrustAmOrPmName(String entrustAmOrPmName) {
        this.entrustAmOrPmName = entrustAmOrPmName;
    }
    
    public String getRemindWayName() {
        return remindWayName;
    }

    public void setRemindWayName(String remindWayName) {
        this.remindWayName = remindWayName;
    }
	
	public BookingVo(){
	    super();
	}
	
	public BookingVo(Booking entity){
	    super(entity, null, new String[]{"entrustAmOrPm","notarizationAmOrPm","mortgageDateAmOrPm","interviewAmOrPm","remindWay"});
	}

	/**
	 * 
	 * @Title: toPo 
	 * @Description: 转换为实体
	 * @author dengyy 
	 * @return
	 */
	public Booking toPo(){
	    Booking entity = new Booking() ;
	    this.toPo(this, entity);
	    return entity ;
	}
  
}
