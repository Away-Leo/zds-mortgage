package com.zdsoft.finance.common.exception;

import com.zdsoft.framework.core.common.exception.AppException;
import com.zdsoft.framework.core.common.util.ObjectHelper;

/**
 * 业务异常类
 * 业务处理过程中已知业务错误异常信息
 * @author liuwei
 * @date 2016/10/17
 * @version 1.0
 */
public class BusinessException extends AppException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6993794710240541321L;

	private String exceptionCode;// 异常码

	private String exceptionMessage;// 异常信息

	public String getExceptionCode() {
		return exceptionCode;
	}

	public void setExceptionCode(String exceptionCode) {
		this.exceptionCode = exceptionCode;
	}

	public String getExceptionMessage() {
		return exceptionMessage;
	}

	public void setExceptionMessage(String exceptionMessage) {
		this.exceptionMessage = exceptionMessage;
	}

	public BusinessException() {
		super();
	}

	/**
	 * @param msg
	 */
	public BusinessException(String msg) {
		super(msg);
	}

	/**
	 * 泛金融验证类异常
	 * 
	 * @param exceptionCode
	 *            异常码
	 * @param exceptionMessage
	 *            异常信息
	 */
	public BusinessException(String exceptionCode, String exceptionMessage) {
		super();
		this.exceptionCode = exceptionCode;
		this.exceptionMessage = exceptionMessage;
	}

	@Override
	public String getErrCode() {
		return null;
	}

	@Override
	public Object[] getArguments() {
		return null;
	}

	public String getMessage() {
		return "exceptionCode=" + exceptionCode + "\t exceptionMessage="
				+ (ObjectHelper.isNotEmpty(exceptionMessage) ? exceptionMessage : super.getMessage());
	}
}
