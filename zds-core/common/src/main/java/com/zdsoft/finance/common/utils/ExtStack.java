package com.zdsoft.finance.common.utils;

import java.util.Stack;

/**
 * 对象堆栈
 *
 * @author GongHaitao
 * @since 1.0
 */
public class ExtStack extends Stack <String>{

	private static final long serialVersionUID = -1469399137879709396L;
	
	final int defaultSize = 20;
	int top;
	String[] stack;
	int maxStackSize;

	public ExtStack() {
		initiate(defaultSize);
	}

	private void initiate(int sz) {
		maxStackSize = sz;
		top = 0;
		stack = new String[sz];
	}

	/**
	 * 把项压入堆栈顶部
	 * @param obj
	 * @throws Exception
	 */
	public void epush(String obj) throws Exception{
		if (top == maxStackSize) {
			throw new Exception("堆配已满");
		}
		stack[top] = obj;
		top++;
	}

	/**
	 * 移除堆栈顶部的对象
	 * @throws Exception
	 */
	public String epop() throws Exception {
		if (top == 0) {
			throw new Exception("堆栈已空");
		}
		top--;
		return stack[top];
	}

	/**
	 * 取最顶端对象
	 * @return
	 * @throws Exception
	 */
	public String getTop() throws Exception {
		if (top == 0) {
			throw new Exception("堆栈已空");
		}
		return stack[top - 1];
	}

	public boolean notEmpty() {
		return (top > 0);
	}
}