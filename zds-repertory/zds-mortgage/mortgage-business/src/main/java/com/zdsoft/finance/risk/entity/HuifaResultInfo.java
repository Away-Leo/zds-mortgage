package com.zdsoft.finance.risk.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.zdsoft.framework.core.common.domain.BaseEntity;

/**
 * 版权所有：重庆正大华日软件有限公司
 * 
 * @Title: HuifaResultInfo.java
 * @Package com.zdsoft.finance.risk.entity
 * @Description: 汇法网结果详细数据
 * @author panshm
 */
@Entity
@Table(name = "T_HUIFA_RESULT_INFO")
public class HuifaResultInfo extends BaseEntity {

    private static final long serialVersionUID = 1L;
    
    public static final String TYPE_PUBLIC = "执行公开信息";
    
    public static final String TYPE_DISHONEST = "失信老赖名单";
    
    public static final String TYPE_RESTRICTED_HIGH_CONSUME = "限制高消费名单";
    
    public static final String TYPE_RESTRICTED_ENTRY_AND_EXIT = "限制出入境名单";
    
    public static final String TYPE_CIVIL_JUDGMENT = "民商事裁判文书";
    
    public static final String TYPE_CIVIL_APPROVAL_PROCESS = "民商事审判流程";
    
    public static final String TYPE_CRIMINAL = "罪犯及嫌疑人名单";
    
    public static final String TYPE_ILLEGALITY = "行政违法记录";
    
    public static final String TYPE_OWE_TAXES = "欠税名单";
    
    public static final String TYPE_ABNORMAL_TAX = "纳税非正常户";
    
    public static final String TYPE_ARREARAGE = "欠款欠费名单";

	/**
	 * key的id
	 */
	@Column(length=10)
	private Long keyid;

	/**
	 * 名称
	 */
	@Column(length=100)
	private String name;

	/**
	 * cid
	 */
	@Column(length=50)
	private String cid;

	/**
	 * 类型
	 */
	@Column(name="type",length=50)
	private String typen;

	/**
	 * 类型码
	 */
	@Column(length=5)
	private String typet;

	/**
	 * url
	 */
	@Column(length=100)
	private String url;

	/**
	 * 标题
	 */
	@Column(length=200)
	private String title;

	/**
	 * 说明
	 */
	@Column(length=500)
	private String description;

	/**
	 * 日期
	 */
	@Column(length=20)
	private String datetime;

	/**
	 * 汇法网结果数据id
	 */
	@Column(name="result_id", length=36)
	private String resultId;

	/**
	 * 姓名、名称
	 */
	@Column(name="msg_name", length=100)
	private String msgName;

	/**
	 * 证件号、识别号
	 */
	@Column(name="msg_id_num", length=50)
	private String msgIdNum;

	/**
	 * 法院、机关
	 */
	@Column(name="msg_court", length=100)
	private String msgCourt;

	/**
	 * 案号、借据编号
	 */
	@Column(name="msg_number", length=50)
	private String msgNumber;

	/**
	 * 内容、事由、欠税属期
	 */
	@Column(name="msg_content", length=100)
	private String msgContent;

	/**
	 * 日期类别、税种、币种
	 */
	@Column(name="msg_type", length=50)
	private String msgType;

	/**
	 * 立案时间、具体日期、结案时间、处理时间、认定日期、欠税发生时间，拖欠日期
	 */
	@Column(name="msg_time", length=60)
	private String msgTime;

	/**
	 * 金额、欠税余额
	 */
	@Column(name="msg_amount", length=50)
	private String msgAmount;

	/**
	 * 执行状态、诉讼地位、行政执法结果
	 */
	@Column(name="msg_status", length=50)
	private String msgStatus;

	/**
	 * 处理结果、法院审理结果
	 */
	@Column(name="msg_result", length=150)
	private String msgResult;

	/**
	 * 备注
	 */
	@Column(name="msg_remark", length=255)
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

	public String getTypen() {
		return typen;
	}

	public void setTypen(String typen) {
		this.typen = typen;
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
