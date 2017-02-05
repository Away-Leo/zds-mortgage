package com.zdsoft.finance.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 防重复提交注解
 * 用于在保存 更新 删除等操作时解决重复提交的问题
 * Created by ww1516123 on 16/7/19.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AvoidDuplicateSubmission {
    /**
     *  创建唯一标示
     * @return
     */
    boolean ceateToken() default false;
    /**
     * 资源唯一key 
     * 创建与删除一致
     * @return
     */
    String sourceKey();
    /**
     * 删除唯一标示
     * @return
     */
    boolean removeToken() default false;
}
