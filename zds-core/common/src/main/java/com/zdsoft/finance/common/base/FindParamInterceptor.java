package com.zdsoft.finance.common.base;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.zdsoft.finance.common.utils.AmountConversionUtil;
import com.zdsoft.framework.core.common.util.ObjectHelper;

/**
 * 过滤查询参数,并自动封装
 * 
 * @author liuwei
 *
 */
public class FindParamInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		// 获取所有的参数
		Enumeration<String> en = request.getParameterNames();
		List<QueryObj> listObj = new ArrayList<QueryObj>();
		while (en.hasMoreElements()) {
			// 组装查询dto
			QueryObj obj = toQueryObj(request, en.nextElement());
			if (null != obj)
				listObj.add(obj);
		}

		// 将组装的数据放入request中
		request.setAttribute("listObj", listObj);

		return super.preHandle(request, response, handler);
	}

	/**
	 * 根据参数组装成查询对象
	 * 
	 * @param request
	 *            请求
	 * @param str
	 *            参数值
	 * @return 查询对象
	 * @throws ServletRequestBindingException
	 */
	private static QueryObj toQueryObj(HttpServletRequest request, String str) throws ServletRequestBindingException {

		// 过滤掉需要处理的请求参数
		String[] qu = str.trim().split("\\|");
		if (qu.length == 1) {
			qu = str.trim().split("%7C");
		}
		QueryObj obj = null;
		if (qu.length == 3) {
			obj = getQueryObjLength3(qu, request, str);
		} else if (qu.length == 4) {
			obj = getQueryObjLength4(qu, request, str);
		} else {
			return null;
		}

		if (ObjectHelper.isNotEmpty(obj) && ObjectHelper.isNotEmpty(obj.getValue())) {
			return obj;
		} else {
			return null;
		}
	}

	/**
	 * 4个参数使用
	 * 
	 * @param qu
	 * @param request
	 * @param str
	 * @return
	 * @throws ServletRequestBindingException
	 */
	private static QueryObj getQueryObjLength4(String[] qu, HttpServletRequest request, String str)
			throws ServletRequestBindingException {
		QueryObj obj = new QueryObj();
		switch (qu[2]) {
		case "R":
			obj.setOperator(">");
			break;
		case "RE":
			obj.setOperator(">=");
			break;
		case "L":
			obj.setOperator("<");
			break;
		case "LE":
			obj.setOperator("<=");
			break;
		case "E":
			obj.setOperator("=");
			break;
		case "NE":
			obj.setOperator("<>");
			break;
		case "LK":
			obj.setOperator("like");
			break;
		case "IN":
			Indo(obj, qu[0], qu[1], "=", ServletRequestUtils.getStringParameter(request, str, null));
			break;
		case "NN":
			Indo(obj, qu[0], qu[1], "!=", ServletRequestUtils.getStringParameter(request, str, null));
			break;
		case "OL":
			IndoOL(obj, qu[0], qu[1], "like", ServletRequestUtils.getStringParameter(request, str, null));
			break;
		default:
			break;
		}
		switch (qu[3]) {
		case "I":
			obj.setValue(ServletRequestUtils.getIntParameter(request, str, 0));
			break;
		case "L":
			obj.setValue(ServletRequestUtils.getLongParameter(request, str, 0));
			break;
		case "D":
			obj.setValue(ServletRequestUtils.getDoubleParameter(request, str, 0.0)/100);
			break;
		case "S":
			obj.setValue(ServletRequestUtils.getStringParameter(request, str, null).trim());
			if (qu[2].equals("LK"))
				obj.setValue("%" + ServletRequestUtils.getStringParameter(request, str, null).trim() + "%");
			break;
		case "T":
			obj.setValue(ServletRequestUtils.getIntParameter(request, str, 0));
			break;
		case "BD":
			String temp = ServletRequestUtils.getStringParameter(request, str, "").trim();
			obj.setValue(AmountConversionUtil.convertToWYuan(new BigDecimal(temp)));
			break;
		default:
			break;
		}
		obj.setObj(qu[0]);
		obj.setElement(qu[1]);
		return obj;
	}

	/**
	 * 3个参数使用
	 * 
	 * @param qu
	 * @param request
	 * @param str
	 * @return
	 * @throws ServletRequestBindingException
	 */
	private static QueryObj getQueryObjLength3(String[] qu, HttpServletRequest request, String str)
			throws ServletRequestBindingException {
		QueryObj obj = new QueryObj();
		// 判断类型,根据规则,组装queryObj
		if (qu[1].equals("R"))
			obj.setOperator("R");
		else if (qu[1].equals("RE"))
			obj.setOperator("RE");
		else if (qu[1].equals("L"))
			obj.setOperator("L");
		else if (qu[1].equals("LE"))
			obj.setOperator("LE");
		else if (qu[1].equals("E"))
			obj.setOperator("E");
		else if (qu[1].equals("NE"))
			obj.setOperator("NE");
		else if (qu[1].equals("LK"))
			obj.setOperator("LK");
		else if (qu[1].equals("IN")) {
			obj.setOperator("IN");
			obj.setValue(Arrays.asList(ServletRequestUtils.getStringParameter(request, str).split(",")));
		} else if (qu[1].equals("NN")) {
			obj.setOperator("NN");
			obj.setValue(Arrays.asList(ServletRequestUtils.getStringParameter(request, str).split(",")));
		} else if (qu[1].equals("OL")) {
			obj.setOperator("OL");
			obj.setValue(Arrays.asList(ServletRequestUtils.getStringParameter(request, str).split(",")));
		} else if (qu[1].equals("BT")) {
			obj.setOperator("BT");
		}

		if ("I".equals(qu[2])) {// Integer类型
			String tempValue = ServletRequestUtils.getStringParameter(request, str);
			if (ObjectHelper.isNotEmpty(tempValue)) {
				obj.setValue(ServletRequestUtils.getIntParameter(request, str, 0));
			}
		} else if ("L".equals(qu[2])) {// Long类型
			String tempValue = ServletRequestUtils.getStringParameter(request, str);
			if (ObjectHelper.isNotEmpty(tempValue)) {
				obj.setValue(ServletRequestUtils.getLongParameter(request, str, 0L));
			}
		} else if ("D".equals(qu[2])) {// Double类型
			String tempValue = ServletRequestUtils.getStringParameter(request, str);
			if (ObjectHelper.isNotEmpty(tempValue)) {
				obj.setValue(ServletRequestUtils.getDoubleParameter(request, str, 0D)/100);
			}
		} else if ("S".equals(qu[2]) && (!"IN".equals(qu[1]) && !"NN".equals(qu[1]) && !"OL".equals(qu[1]))) { // String类型
			obj.setValue(ServletRequestUtils.getStringParameter(request, str, null).trim());
		} else if ("BD".equals(qu[2])) {// BigDecimal类型
			String amountTemp = ServletRequestUtils.getStringParameter(request, str, null).trim();
			obj.setValue(AmountConversionUtil.convertToYuan(amountTemp));
		} else if ("F".equals(qu[2])) {// Float类型
			String tempValue = ServletRequestUtils.getStringParameter(request, str);
			if (ObjectHelper.isNotEmpty(tempValue)) {
				obj.setValue(ServletRequestUtils.getFloatParameter(request, str, 0F));
			}
		} else if ("B".equals(qu[2])) {// Boolean类型
			obj.setValue(ServletRequestUtils.getBooleanParameter(request, str));
		} else if ("BT".equals(qu[2])) {
			obj.setValue(ServletRequestUtils.getStringParameter(request, str));
		}
		obj.setElement(changeElement(qu[2]));
		obj.setObj(qu[0]);
		return obj;
	}

	// 转换类型
	private static String changeElement(String element) {

		String ele = "";
		switch (element) {
		case "BD":
			ele = "BigDecimal";
			break;
		case "D":
			ele = "Double";
			break;
		case "F":
			ele = "Float";
			break;
		case "L":
			ele = "Long";
			break;
		case "I":
			ele = "Integer";
			break;
		case "S":
			ele = "String";
			break;
		case "T":
			ele = "Date";
			break;
		case "B":
			ele = "Boolean";
			break;
		default:
			break;
		}
		return ele;
	}

	/**
	 * 复选处理
	 * 
	 * @param value
	 * @return
	 */
	private static void Indo(QueryObj QueryObj, String obj, String ele, String Operator, String value) {
		List<QueryObj> listObj = new ArrayList<QueryObj>();
		String[] strs = value.trim().split(",");
		for (String g : strs) {
			QueryObj query = new QueryObj();
			query.setObj(obj);
			query.setElement(ele);
			query.setOperator(Operator);
			query.setValue(g.trim());
			listObj.add(query.getValue() != null ? query : null);
		}
		QueryObj.setQueryList(listObj);
	}

	/**
	 * 多关键字模糊查询
	 * 
	 * @param QueryObj
	 *            查询参数对象
	 * @param obj
	 *            查询对象名(SQL语句中的表别名)
	 * @param ele
	 *            查询参数
	 * @param Operator
	 *            操作符
	 * @param value
	 *            查询值
	 */
	private static void IndoOL(QueryObj QueryObj, String obj, String ele, String Operator, String value) {
		List<QueryObj> listObj = new ArrayList<QueryObj>();
		String[] strs = value.trim().split(",");
		for (String g : strs) {
			QueryObj query = new QueryObj();
			query.setObj(obj);
			query.setElement(ele);
			query.setOperator(Operator);
			query.setValue("%" + g.trim() + "%");
			listObj.add(query.getValue() != null ? query : null);
		}
		QueryObj.setQueryList(listObj);
	}
}
