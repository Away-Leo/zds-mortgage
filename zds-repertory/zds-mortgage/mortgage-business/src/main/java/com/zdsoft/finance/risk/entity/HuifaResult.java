package com.zdsoft.finance.risk.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.zdsoft.framework.core.common.domain.BaseEntity;

/**
 * 版权所有：重庆正大华日软件有限公司
 * 
 * @Title: HuifaResult.java
 * @Package com.zdsoft.finance.risk.entity
 * @Description: 汇法网结果数据
 * @author panshm
 */
@Entity
@Table(name = "T_HUIFA_RESULT")
public class HuifaResult extends BaseEntity {

	private static final long serialVersionUID = 1L;
	
	/**
	 * 成功状态;s:成功
	 */
	@Column(length=10)
	private String success;

	/**
	 * 返回信息
	 */
	@Column(length=50)
	private String message;

	/**
	 * 总记录数
	 */
	@Column(name="totalnumber")
	private Integer totalNumber;

	/**
	 * 开始时间
	 */
	@Column
	private String startime;

	/**
	 * 结束时间
	 */
	@Column
	private String endtime;

	/**
	 * 结果
	 */
	@Lob 
	@Column(name="result_json")
	private String resultJson;

	/**
	 * 创建人ID
	 */
	@Column(name="creator_id", length=36)
	private String creatorId;

	/**
	 * 请求ID
	 */
	@Column(name="request_id", length=36)
	private String requestId;

	/**
	 * 创建时间
	 */
	@Column(name="create_time")
	@Temporal(TemporalType.TIMESTAMP)
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
	
}
