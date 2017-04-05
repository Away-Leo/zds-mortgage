package com.zdsoft.finance.common.exception;
/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: AppExistMainCustomerException.java 
 * @ClassName: AppExistMainCustomerException 
 * @Description: app已经存在主借人
 * @author xj 
 * @date 2017年3月29日 下午4:22:39 
 * @version V1.0
 */
public class AppOntExistMainCustomerException extends Exception {

	private static final long serialVersionUID = 2614202883953585102L;

	public AppOntExistMainCustomerException() {
		super();
	}

	public AppOntExistMainCustomerException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public AppOntExistMainCustomerException(String message, Throwable cause) {
		super(message, cause);
	}

	public AppOntExistMainCustomerException(String message) {
		super(message);
	}

	public AppOntExistMainCustomerException(Throwable cause) {
		super(cause);
	}

}
