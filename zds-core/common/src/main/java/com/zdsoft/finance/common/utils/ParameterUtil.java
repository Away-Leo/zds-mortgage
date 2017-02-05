package com.zdsoft.finance.common.utils;

import com.zdsoft.finance.common.base.QueryObj;
import com.zdsoft.framework.core.common.util.ObjectHelper;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

/**
 * 用于解析页面传入的模糊查询参数
 * 
 * @author liuwei 详细使用方式参见parameter.poperties
 */

public class ParameterUtil {

	/**
	 * 返回查询条件结果集
	 * 
	 * @param request
	 * @param clazz
	 *            需要查询的对象名
	 * @return
	 * @throws ServletRequestBindingException
	 */
	public static List<QueryObj> getQueryObjByParameter(HttpServletRequest request, String[] clazz) {
		Enumeration<String> en = request.getParameterNames();
		List<QueryObj> listObj = new ArrayList<QueryObj>();
		while (en.hasMoreElements()) {
			QueryObj obj = toQueryObj(request, en.nextElement(), clazz, listObj);
			if (null != obj)
				listObj.add(obj);
		}
		return listObj;
	}

	/**
	 * 
	 * @param request
	 * @param str
	 *            页面请求名组件名 类名,属性名,操作符,类型
	 * @param clazz
	 *            需要查询的对象名
	 * @return
	 * @throws ServletRequestBindingException
	 */
	private static QueryObj toQueryObj(HttpServletRequest request, String str, String[] clazz, List<QueryObj> list) {
		String[] qu = str.trim().split("\\|");
		if (qu.length != 4 || !validationQueryString(str))
			return null;

		QueryObj obj = new QueryObj();

		for (String s : clazz) {
			if (qu[0].equals(s) || s.equals("*")) {
				if (request.getParameter(str) == null || request.getParameter(str) == "") {
					continue;
				} else if (qu[2].equals("R")) {
					obj.setOperator(">");
				} else if (qu[2].equals("RE")) {
					obj.setOperator(">=");
				} else if (qu[2].equals("L")) {
					obj.setOperator("<");
				} else if (qu[2].equals("LE")) {
					obj.setOperator("<=");
				} else if (qu[2].equals("E")) {
					obj.setOperator("=");
				} else if (qu[2].equals("NE")) {
					obj.setOperator("!=");
				} else if (qu[2].equals("LK")) {
					obj.setOperator("like");
				} else if (qu[2].equals("IN")) {
					Indo(obj, qu[0], qu[1], "=", ServletRequestUtils.getStringParameter(request, str, null));
				} else if (qu[2].equals("NN")) {
					Indo(obj, qu[0], qu[1], "!=", ServletRequestUtils.getStringParameter(request, str, null));
				} else if (qu[2].equals("OL")) {
					IndoOL(obj, qu[0], qu[1], "like", ServletRequestUtils.getStringParameter(request, str, null));
				}
				if (qu[3].equals("I"))
					obj.setValue(ServletRequestUtils.getIntParameter(request, str, 0));
				else if (qu[3].equals("L"))
					obj.setValue(ServletRequestUtils.getLongParameter(request, str, 0));
				else if (qu[3].equals("D"))
					obj.setValue(ServletRequestUtils.getDoubleParameter(request, str, 0.0));
				else if (qu[3].equals("S")) {
					obj.setValue(ServletRequestUtils.getStringParameter(request, str, null).trim());
					if (qu[2].equals("LK"))
						obj.setValue("%" + ServletRequestUtils.getStringParameter(request, str, null).trim() + "%");
				} else if (qu[3].equals("T"))
					obj.setValue(ServletRequestUtils.getIntParameter(request, str, 0));
				obj.setObj(qu[0]);
				obj.setElement(qu[1]);
				break;
			}
		}
		return obj.getValue() != null ? obj : null;
	}

	/**
	 * 验证参数是否合规
	 * 
	 * @return 合规true 不合规false
	 */
	// delete、add、update、truncate、alter、grant、drop
	private static boolean validationQueryString(String queryString) {
		boolean state = true;
		if (ObjectHelper.isEmpty(queryString)) {// 为空 直接退出
			return false;
		}
		String querystr = queryString.toUpperCase();// 转大写 用于判断
		if (querystr.contains("DELETE") || querystr.contains("ADD") || querystr.contains("UPDATE")
				|| querystr.contains("TRUNCATE") || querystr.contains("ALTER") || querystr.contains("GRANT")
				|| querystr.contains("DROP")) {
			state = false;
		}
		return state;
	}

	public static void main(String[] args) {
		System.out.println("abc张".toUpperCase());
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
