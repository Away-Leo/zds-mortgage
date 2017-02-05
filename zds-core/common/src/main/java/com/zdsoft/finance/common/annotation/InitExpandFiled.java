package com.zdsoft.finance.common.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 扩展字段注解
 * 
 * @author liuwei
 *
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface InitExpandFiled {
	/**
	 * 全类名
	 * 
	 * @return
	 * @description
	 */
	public String clazzNm();

	/**
	 * vo类名
	 * 
	 * @return
	 */
	public String voNm();

}
