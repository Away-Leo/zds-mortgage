package com.zdsoft.finance.common.utils;
/**
 * APP返回状态值
 * @author XJ
 *
 */
public enum AppStatus {

	/**
	 * 操作成功
	 */
	Succeed(0),
	/**
	 * 数据库操作错误
	 */
	DaoError(-1),
	/**
	 * 系统错误 服务端异常
	 */
	SystemError(-2),
	/**
	 * 参数错误或不完整
	 */
	ArgsError(-3),
	/**
	 * 权限错误
	 */
	PermissionError(-4),
	/**
	 * 业务逻辑执行错误
	 */
	LogicError(-5),
	/**
	 * token验证超时或者失效!
	 */
	TokenValidateError(-6),
	/**
	 * 存在主借人
	 */
	ExistMainCustomer(-7),
	/**
	 * 不存在主借人
	 */
	OntExistMainCustomer(-8),
	
	/**
	 * 黑名单异常
	 */
	BlackListError(-9);
	private int value;

	public int value() {
		return value;
	}

	public static AppStatus valueOf(int value) { // 手写的从int到enum的转换函数
		switch (value) {
		case 0:
			return Succeed;
		case -1:
			return DaoError;
		case -2:
			return SystemError;
		case -3:
			return ArgsError;
		case -4:
			return PermissionError;
		case -5:
			return LogicError;
		case -6:
			return TokenValidateError;
		case -7:
			return ExistMainCustomer;
		case -8:
			return OntExistMainCustomer;
		
		default:
			return null;
		}
	}

	private AppStatus(int status) {
		this.value = status;
	}
}