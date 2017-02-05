package com.zdsoft.finance.common.utils;

import com.zdsoft.framework.core.common.util.ObjectHelper;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 该类用于处理json返回
 * 
 * @author zhangpan
 * 
 */
public class PageDto {
	// 页
	private int page = 0;
	// 行
	private int rows = 10;
	// 总行
	private Long total = 0L;
	// 状态 0:失败 1:正常
	private int state = 0;
	//footer
	@SuppressWarnings("rawtypes")
	private List footer = new ArrayList();
	// 信息
	private String message;
	// 结果集
	@SuppressWarnings("rawtypes")
	private List results = new ArrayList();
	// 过虑元素
	private String[] filter;
	private String jsoncallback;

	public String[] getFilter() {
		return filter;
	}

	public void setFilter(String[] filter) {
		this.filter = filter;
	}

	public List<?> getFooter() {
		return footer;
	}

	public void setFooter(List<?> footer) {
		this.footer = footer;
	}

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}

	@Override
	public String toString() {
		Map<String, Object> m = new HashMap<String, Object>();
		if (ObjectHelper.isEmpty(results)&&state==0) {
			state = 0;
		}else{
			state = 1;
		}
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setExcludes(filter);
		JSONArray Array = JSONArray.fromObject(results, jsonConfig);
		m.put("rows", Array);
		m.put("page", page);
		m.put("row", rows);
		m.put("total", total);
		m.put("state", state);
		m.put("message", message);
		if(ObjectHelper.isNotEmpty(footer)){
			m.put("footer", footer);
		}
		return jsoncallback + "(" + JSONObject.fromObject(m).toString() + ")";
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		if (page > 0){
			this.page = page;
		}
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		if (rows > 0)
			this.rows = rows;
	}

	@SuppressWarnings("rawtypes")
	public List getResults() {
		return results;
	}

	public void setResults(@SuppressWarnings("rawtypes") List results) {
		this.results = results;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getJsoncallback() {
		return jsoncallback;
	}

	public void setJsoncallback(String jsoncallback) {
		this.jsoncallback = jsoncallback;
	}

}
