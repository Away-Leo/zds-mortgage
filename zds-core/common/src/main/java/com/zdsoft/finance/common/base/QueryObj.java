package com.zdsoft.finance.common.base;

import java.util.List;

public class QueryObj {
	// 对象
	private String obj;

	// 属性
	private String element;

	// 操作符
	private String operator;

	// 值
	private Object value;

	private List<QueryObj> queryList;

	public String getObj() {
		return obj;
	}

	public void setObj(String obj) {
		this.obj = obj;
	}

	public String getElement() {
		return element;
	}

	public void setElement(String element) {
		this.element = element;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	public List<QueryObj> getQueryList() {
		return queryList;
	}

	public void setQueryList(List<QueryObj> queryList) {
		this.queryList = queryList;
	}
}
