package com.zdsoft.finance.credit.service.assist;

import com.zdsoft.framework.core.common.annotation.PropertySet;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: CreditParameter.java 
 * @ClassName: CreditParameter 
 * @Description: 征信常量
 * @author gufeng 
 * @date 2017年2月28日 下午3:53:00 
 * @version V1.0
 */
public class CreditParameter {

	/**
	 * 征信请求URL
	 */
	private static String creditRequestUrl;
	/**
	 * 征信请求 用户名
	 */
	private static String creditRequestUserName;
	/**
	 * 征信请求 密码
	 */
	private static String creditRequestPassword;
	/**
	 * 征信请求 密钥
	 */
	private static String creditRequestKey;
	
	public static String getCreditRequestUrl() {
		return creditRequestUrl;
	}
	@PropertySet
	public static void setCreditRequestUrl(String creditRequestUrl) {
		CreditParameter.creditRequestUrl = creditRequestUrl;
	}
	public static String getCreditRequestUserName() {
		return creditRequestUserName;
	}
	@PropertySet
	public static void setCreditRequestUserName(String creditRequestUserName) {
		CreditParameter.creditRequestUserName = creditRequestUserName;
	}
	public static String getCreditRequestPassword() {
		return creditRequestPassword;
	}
	@PropertySet
	public static void setCreditRequestPassword(String creditRequestPassword) {
		CreditParameter.creditRequestPassword = creditRequestPassword;
	}
	public static String getCreditRequestKey() {
		return creditRequestKey;
	}
	@PropertySet
	public static void setCreditRequestKey(String creditRequestKey) {
		CreditParameter.creditRequestKey = creditRequestKey;
	}
	
}
