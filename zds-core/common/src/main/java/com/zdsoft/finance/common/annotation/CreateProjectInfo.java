package com.zdsoft.finance.common.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.zdsoft.framework.core.common.domain.BaseEntity;
/**
 * 新增项目资料信息
 * @author Maple
 * @version 1.0
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface CreateProjectInfo {
	/**
	 * 项目编号
	 * 
	 * @return
	 * @description
	 */
	public String projectCd();

	/**
	 * 信息所对应的相关实体类
	 * 
	 * @return
	 * @description
	 */
	public Class<? extends BaseEntity> className();
//	public Class<? extends BaseEntity> refClass() default BaseEntity.class;

}
