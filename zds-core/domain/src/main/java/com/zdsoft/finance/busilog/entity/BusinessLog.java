package com.zdsoft.finance.busilog.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;

import com.zdsoft.framework.core.common.domain.BaseEntity;

/**
 * 业务操作日志
 * 在业务处理过程中，记录关键点操作详细信息。
 * @author liuwei
 * @version 1.0
 */
@Entity
@Table(name = "zf_business_log")
public class BusinessLog extends BaseEntity {
	private static final long serialVersionUID = 6736259262669745535L;

	/**
	 * 操作人
	 */
	@Column(length = 32)
	private String operatorNm;

	/**
	 * 操作时间
	 */
	@Column
	private Long operatorTime;

	/**
	 * 模块名
	 */
	@Column(length = 255)
	private String moduleNm;

	/**
	 * 动作
	 */
	@Column(length = 255)
	private String actionNm;

	/**
	 * 描述
	 */
	@Lob
	private String description;

	/**
	 * 日志类型
	 * //TODO 修改为枚举 by maple
	 */
	@Column(length = 32)
	private String logType;

	public String getOperatorNm() {
		return operatorNm;
	}

	public void setOperatorNm(String operatorNm) {
		this.operatorNm = operatorNm;
	}

	public Long getOperatorTime() {
		return operatorTime;
	}

	public void setOperatorTime(Long operatorTime) {
		this.operatorTime = operatorTime;
	}

	public String getModuleNm() {
		return moduleNm;
	}

	public void setModuleNm(String moduleNm) {
		this.moduleNm = moduleNm;
	}

	public String getActionNm() {
		return actionNm;
	}

	public void setActionNm(String actionNm) {
		this.actionNm = actionNm;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLogType() {
		return logType;
	}

	public void setLogType(String logType) {
		this.logType = logType;
	}

}
