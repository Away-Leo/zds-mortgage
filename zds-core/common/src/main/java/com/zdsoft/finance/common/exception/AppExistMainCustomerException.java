package com.zdsoft.finance.common.exception;
/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: AppExistMainCustomerException.java 
 * @ClassName: AppExistMainCustomerException 
 * @Description: app不存在主借人
 * @author xj 
 * @date 2017年3月29日 下午4:23:33 
 * @version V1.0
 */
public class AppExistMainCustomerException extends Exception {

	private static final long serialVersionUID = 2614202883953585102L;

	public AppExistMainCustomerException() {
		super();
	}

	public AppExistMainCustomerException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public AppExistMainCustomerException(String message, Throwable cause) {
		super(message, cause);
	}

	public AppExistMainCustomerException(String message) {
		super(message);
	}

	public AppExistMainCustomerException(Throwable cause) {
		super(cause);
	}

}
