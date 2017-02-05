package com.zdsoft.finance.aop.service;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.zdsoft.finance.common.annotation.AvoidDuplicateSubmission;
import com.zdsoft.framework.core.common.dto.ResponseMsg;
import com.zdsoft.framework.core.common.util.ObjectHelper;

/**
 * 防重复提交封装返回responseMsg参数
 * @author longwei
 * @date 2016/12/09
 * @version 1.0
 */
@Component
@Aspect
public class AvoidDuplicateSubmissionAdvisor {

	private static final Logger logger = LoggerFactory.getLogger(AvoidDuplicateSubmissionAdvisor.class);
	
	/**
	 * 注解有@AvoidDuplicateSubmission方法的切点
	 */
	public static final String AVOID_DUPLICATE_SUBMISSION = "@annotation(com.zdsoft.finance.common.annotation.AvoidDuplicateSubmission)";

	/**
     * 执行方法后封装返回值
     * @param joinPoint
     * @param retVal
     * @throws Exception
     * @throws InvalidArgumentException
     */
    @AfterReturning(pointcut = AVOID_DUPLICATE_SUBMISSION, returning = "retVal")
	public void buildResponseMsg(JoinPoint joinPoint, Object retVal) {
    	if(retVal instanceof ResponseMsg){
    		ResponseMsg msg=(ResponseMsg) retVal;
    		
    		//获取注解sourceKey
    		Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
    		AvoidDuplicateSubmission avoidDuplicateSubmission=method.getAnnotation(AvoidDuplicateSubmission.class);
    		String sourceKey=avoidDuplicateSubmission.sourceKey();
    		
    		//判断是否为空
            if(ObjectHelper.isEmpty(sourceKey)){
            	logger.warn("===================不存在资源key,请检查");
                return ;
            }
            
            //判断是否存在多个
            if(ObjectHelper.isEmpty(sourceKey.split(";").length>1)){
            	logger.warn("===================配置了多个key,请检查");
                return ;
            }
            
    		//封装返回值
    		Map<String, Object> optional = new HashMap<String, Object>();
			optional.put("sourceKey", sourceKey);
			optional.put("repeatToken", true);
			msg.setOptional(optional);
    	}
    }
}
