package com.zdsoft.finance.common.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Ajax响应客户端工具类
 * 
 * @author zt
 * @version 1.0.0
 * @since 1.0.0
 */
public class AjaxRespUtil {
	
	private static final String MSG_KEY = "msg";
	private static final String STATUS_KEY = "status";
	private static final String DATA_KEY = "rows";
	private static final String OBJ_KEY = "obj";
	
	/**
	 * 响应客户端此操作成功
	 * @return
	 */
	public static Map<String, Object> setAjaxSuccess() {
		return setAjaxStatus(true);
	}
	
	/**
	 * 响应客户端此操作失败
	 * @return
	 */
	public static Map<String, Object> setAjaxError() {
		return setAjaxStatus(false);
	}
	
	/**
	 * 响应客户端消息
	 * @param status 响应状态,true为成功,反之为失败
	 * @return 消息列表
	 */
	public static Map<String, Object> setAjaxStatus(final boolean status) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put(STATUS_KEY, status);
		return result;
	}
	
	/**
	 * 响应客户端此操作失败
	 * @param message 消息内容
	 * @return 消息列表
	 */
	public static Map<String, Object> setAjaxMsg(final String message) {
		return setAjaxMsg(message, true);
	}
	
	/**
	 * 响应客户端消息
	 * @param message 消息内容
	 * @param status 响应状态,true为成功,反之为失败
	 * @return 消息列表
	 */
	public static Map<String, Object> setAjaxMsg(final String message, final boolean status) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put(MSG_KEY, message);
		result.put(STATUS_KEY, status);
		return result;
	}
	
	/**
	 * 响应客户端数据集
	 * @param datas 数据记录
	 * @return 数据记录
	 */
	public static Map<String, Object> setAjaxData(final List<?> datas) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put(DATA_KEY, datas);
		return result;
	}
	
	/**
	 * 响应客户端单个对象
	 * @param data 对象
	 * @return 数据对象
	 */
	public static Map<String, Object> setAjaxObj(final Object data) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put(OBJ_KEY, data);
		return result;
	}
	
}
