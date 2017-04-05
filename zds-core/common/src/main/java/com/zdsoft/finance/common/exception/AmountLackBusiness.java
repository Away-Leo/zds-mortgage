package com.zdsoft.finance.common.exception;
/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: AmountLackBusiness.java 
 * @ClassName: AmountLackBusiness 
 * @Description: 金额不足异常
 * @author xj 
 * @date 2017年2月17日 下午4:07:47 
 * @version V1.0
 */
public class AmountLackBusiness extends Exception {

	private static final long serialVersionUID = 1L;

	public AmountLackBusiness() {
		super();
	}

	public AmountLackBusiness(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public AmountLackBusiness(String message, Throwable cause) {
		super(message, cause);
	}

	public AmountLackBusiness(String message) {
		super(message);
	}

	public AmountLackBusiness(Throwable cause) {
		super(cause);
	}

}
