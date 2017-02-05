package com.zdsoft.finance.common.exception;
/**
 * 项目资料特有异常
 * @author Maple
 *
 */
public class IllegalBusinessFormException extends RuntimeException {

	private static final long serialVersionUID = 150783750411930984L;
	
	private String classNm;
	
	public IllegalBusinessFormException(String clzNm) {
		this.classNm = clzNm;
	}

	@Override
	public String getMessage() {
		return "无效的项目资料表单实体:" + classNm;
	}
}
