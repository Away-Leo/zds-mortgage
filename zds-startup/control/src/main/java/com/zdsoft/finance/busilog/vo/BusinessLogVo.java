package com.zdsoft.finance.busilog.vo;

import com.zdsoft.finance.busilog.entity.BusinessLog;
import com.zdsoft.finance.busilog.entity.LogType;
import com.zdsoft.finance.common.base.BaseVo;
import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.finance.spi.common.dto.ActionNm;
import com.zdsoft.finance.spi.common.dto.ModuleNm;

public class BusinessLogVo extends BaseVo<BusinessLog> {

	/**
	 * 操作人
	 */
	private String operatorNm;

	/**
	 * 操作时间
	 */
	private Long operatorTime;

	/**
	 * 模块名
	 */
	private String moduleNm;

	/**
	 * 动作
	 */
	private String actionNm;

	/**
	 * 描述
	 */
	private String description;

	/**
	 * 日志记录类型
	 */
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

	public BusinessLogVo() {
		super();
	}

	public BusinessLogVo(BusinessLog t, String[] args, String[] simpleArgs) throws BusinessException {
		super(t, args, simpleArgs);
		this.setLogType(LogType.getName(t.getLogType()));
	}

	public BusinessLogVo(BusinessLog t) throws BusinessException {
		super(t);
		this.setLogType(LogType.getName(t.getLogType()));
		this.setModuleNm(ModuleNm.getName(t.getModuleNm()));
		this.setActionNm(ActionNm.getName(t.getActionNm()));
	}

	public BusinessLog toPo() {
		BusinessLog businessLog = new BusinessLog();
		super.toPo(this, businessLog);
		return businessLog;
	}

	public BusinessLog toPo(String[] args, String[] simpleArgs) {
		BusinessLog businessLog = new BusinessLog();
		super.toPo(this, businessLog, args, simpleArgs);
		return businessLog;
	}

}
