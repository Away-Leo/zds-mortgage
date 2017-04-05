package com.zdsoft.finance.risk.vo;

import java.io.Serializable;

/**
 * 版权所有：重庆正大华日软件有限公司
 * 
 * @Title: HuifaResultInfoVo.java
 * @Package com.zdsoft.finance.risk.vo
 * @Description: 汇法网结果详细数据
 * @author panshm
 */
public class HuifaResultInfoVo implements Serializable {
	
	private static final long serialVersionUID = 1L;

	/**
	 * key的id
	 */
	private Long keyid;

	/**
	 * 名称
	 */
	private String name;

	/**
	 * cid
	 */
	private String cid;

	/**
	 * 类型
	 */
	private String type;

	/**
	 * 类型码
	 */
	private String typet;

	/**
	 * url
	 */
	private String url;

	/**
	 * 标题
	 */
	private String title;

	/**
	 * 说明
	 */
	private String description;

	/**
	 * 日期
	 */
	private String datetime;

	/**
	 * 汇法网结果数据id
	 */
	private String resultId;

	/**
	 * 姓名、名称
	 */
	private String msgName;

	/**
	 * 证件号、识别号
	 */
	private String msgIdNum;

	/**
	 * 法院、机关
	 */
	private String msgCourt;

	/**
	 * 案号、借据编号
	 */
	private String msgNumber;

	/**
	 * 内容、事由、欠税属期
	 */
	private String msgContent;

	/**
	 * 日期类别、税种、币种
	 */
	private String msgType;

	/**
	 * 立案时间、具体日期、结案时间、处理时间、认定日期、欠税发生时间，拖欠日期
	 */
	private String msgTime;

	/**
	 * 金额、欠税余额
	 */
	private String msgAmount;

	/**
	 * 执行状态、诉讼地位、行政执法结果
	 */
	private String msgStatus;

	/**
	 * 处理结果、法院审理结果
	 */
	private String msgResult;

	/**
	 * 备注
	 */
	private String msgRemark;

	public Long getKeyid() {
		return keyid;
	}

	public void setKeyid(Long keyid) {
		this.keyid = keyid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCid() {
		return cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTypet() {
		return typet;
	}

	public void setTypet(String typet) {
		this.typet = typet;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDatetime() {
		return datetime;
	}

	public void setDatetime(String datetime) {
		this.datetime = datetime;
	}

	public String getResultId() {
		return resultId;
	}

	public void setResultId(String resultId) {
		this.resultId = resultId;
	}

	public String getMsgName() {
		return msgName;
	}

	public void setMsgName(String msgName) {
		this.msgName = msgName;
	}

	public String getMsgIdNum() {
		return msgIdNum;
	}

	public void setMsgIdNum(String msgIdNum) {
		this.msgIdNum = msgIdNum;
	}

	public String getMsgCourt() {
		return msgCourt;
	}

	public void setMsgCourt(String msgCourt) {
		this.msgCourt = msgCourt;
	}

	public String getMsgNumber() {
		return msgNumber;
	}

	public void setMsgNumber(String msgNumber) {
		this.msgNumber = msgNumber;
	}

	public String getMsgContent() {
		return msgContent;
	}

	public void setMsgContent(String msgContent) {
		this.msgContent = msgContent;
	}

	public String getMsgType() {
		return msgType;
	}

	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}

	public String getMsgTime() {
		return msgTime;
	}

	public void setMsgTime(String msgTime) {
		this.msgTime = msgTime;
	}

	public String getMsgAmount() {
		return msgAmount;
	}

	public void setMsgAmount(String msgAmount) {
		this.msgAmount = msgAmount;
	}

	public String getMsgStatus() {
		return msgStatus;
	}

	public void setMsgStatus(String msgStatus) {
		this.msgStatus = msgStatus;
	}

	public String getMsgResult() {
		return msgResult;
	}

	public void setMsgResult(String msgResult) {
		this.msgResult = msgResult;
	}

	public String getMsgRemark() {
		return msgRemark;
	}

	public void setMsgRemark(String msgRemark) {
		this.msgRemark = msgRemark;
	}


}
