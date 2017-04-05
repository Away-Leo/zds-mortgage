package com.zdsoft.finance.risk.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * 版权所有：重庆正大华日软件有限公司
 * 
 * @Title: HuifaResultVo.java
 * @Package com.zdsoft.finance.risk.vo
 * @Description: 汇法网结果数据
 * @author panshm
 */
public class HuifaResultVo implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * 数据主键
	 */
	private String id;
	
	/**
	 * 成功状态;s:成功
	 */
	private String success;

	/**
	 * 返回信息
	 */
	private String message;

	/**
	 * 总记录数
	 */
	private Integer totalNumber;

	/**
	 * 开始时间
	 */
	private String startime;

	/**
	 * 结束时间
	 */
	private String endtime;

	/**
	 * 结果
	 */
	private String resultJson;

	/**
	 * 创建人ID
	 */
	private String creatorId;

	/**
	 * 请求ID
	 */
	private String requestId;

	/**
	 * 创建时间
	 */
	private Date createTime;

	public String getSuccess() {
		return success;
	}

	public void setSuccess(String success) {
		this.success = success;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Integer getTotalNumber() {
		return totalNumber;
	}

	public void setTotalNumber(Integer totalNumber) {
		this.totalNumber = totalNumber;
	}

	public String getStartime() {
		return startime;
	}

	public void setStartime(String startime) {
		this.startime = startime;
	}

	public String getEndtime() {
		return endtime;
	}

	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}

	public String getResultJson() {
		return resultJson;
	}

	public void setResultJson(String resultJson) {
		this.resultJson = resultJson;
	}

	public String getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(String creatorId) {
		this.creatorId = creatorId;
	}

	public String getRequestId() {
		return requestId;
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
}
