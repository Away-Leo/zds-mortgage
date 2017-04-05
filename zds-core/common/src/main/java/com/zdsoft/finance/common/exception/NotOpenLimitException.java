package com.zdsoft.finance.common.exception;
/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: NotOpenLimitException.java 
 * @ClassName: NotOpenLimitException 
 * @Description: 额度申请，未开放申请额度
 * @author xj 
 * @date 2017年2月18日 下午2:32:41 
 * @version V1.0
 */
public class NotOpenLimitException extends Exception {

	
	private static final long serialVersionUID = 2614202883953585102L;

	public NotOpenLimitException() {
		super();
	}

	public NotOpenLimitException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public NotOpenLimitException(String message, Throwable cause) {
		super(message, cause);
	}

	public NotOpenLimitException(String message) {
		super(message);
	}

	public NotOpenLimitException(Throwable cause) {
		super(cause);
	}

}
