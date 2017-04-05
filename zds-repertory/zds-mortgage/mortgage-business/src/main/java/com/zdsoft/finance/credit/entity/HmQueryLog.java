package com.zdsoft.finance.credit.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.zdsoft.framework.core.common.domain.BaseEntity;

/**
 * 版权所有：重庆正大华日软件有限公司
 * 
 * @Title: HmQueryLog.java
 * @ClassName: HmQueryLog
 * @Description: 人行查询日志
 * @author gufeng
 * @date 2017年2月23日 上午9:43:41
 * @version V1.0
 */
@Entity
@Table(name = "hm_query_log")
public class HmQueryLog extends BaseEntity {

	private static final long serialVersionUID = 3098102897317612026L;
	/**
	 * 用户ID
	 */
	@Column(name = "user_id")
	private String userId;
	/**
	 * 查询ID
	 */
	@Column(name = "query_id")
	private String queryId;
	/**
	 * 批次任务ID
	 */
	@Column(name = "task_id")
	private String taskId;
	/**
	 * 被查人姓名
	 */
	@Column(name = "query_name")
	private String queryName;
	/**
	 * 被查人身份证号
	 */
	@Column(name = "query_Cred_Num")
	private String queryCredNum;
	/**
	 * 运行日志名称
	 */
	@Column(name = "log_code")
	private String logCode;
	/**
	 * 任务开始时间
	 */
	@Column(name = "start_time")
	private Timestamp startTime;
	/**
	 * 任务结束时间
	 */
	@Column(name = "end_time")
	private Timestamp endTime;
	/**
	 * 是否成功 1 是 0 否
	 */
	@Column(name = "status")
	private Integer status;
	/**
	 * 失败或成功原因
	 */
	@Column(name = "reason")
	private String reason;

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getQueryId() {
		return queryId;
	}

	public void setQueryId(String queryId) {
		this.queryId = queryId;
	}

	public String getQueryName() {
		return queryName;
	}

	public void setQueryName(String queryName) {
		this.queryName = queryName;
	}

	public String getQueryCredNum() {
		return queryCredNum;
	}

	public void setQueryCredNum(String queryCredNum) {
		this.queryCredNum = queryCredNum;
	}

	public String getLogCode() {
		return logCode;
	}

	public void setLogCode(String logCode) {
		this.logCode = logCode;
	}

	public Timestamp getStartTime() {
		return startTime;
	}

	public void setStartTime(Timestamp startTime) {
		this.startTime = startTime;
	}

	public Timestamp getEndTime() {
		return endTime;
	}

	public void setEndTime(Timestamp endTime) {
		this.endTime = endTime;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

}
