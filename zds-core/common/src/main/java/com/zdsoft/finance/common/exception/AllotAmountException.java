package com.zdsoft.finance.common.exception;
/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: AllotAmountException.java 
 * @ClassName: AllotAmountException 
 * @Description: 分配额度异常
 * @author xj 
 * @date 2017年2月18日 下午2:36:44 
 * @version V1.0
 */
public class AllotAmountException extends Exception {

	private static final long serialVersionUID = 2614202883953585102L;

	public AllotAmountException() {
		super();
	}

	public AllotAmountException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public AllotAmountException(String message, Throwable cause) {
		super(message, cause);
	}

	public AllotAmountException(String message) {
		super(message);
	}

	public AllotAmountException(Throwable cause) {
		super(cause);
	}

}
