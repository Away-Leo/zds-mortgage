package com.zdsoft.finance.common.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.zdsoft.finance.common.annotation.AvoidDuplicateSubmission;
import com.zdsoft.framework.core.common.util.ObjectHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.UUID;

/**
 * 防重复提交过滤器
 * Created by Maple on 16/7/19.
 * @author Maple
 * @version 1.0
 */
public class AvoidDuplicateSubmissionInterceptor extends HandlerInterceptorAdapter {
    private static final Logger logger = LoggerFactory.getLogger(AvoidDuplicateSubmissionInterceptor.class);
    
    
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {
    	HandlerMethod handlerMethod = null;
    	Method method = null;
    	AvoidDuplicateSubmission annotation = null;
    	try{
    		handlerMethod= (HandlerMethod) handler;
    	    method = handlerMethod.getMethod();
    	    annotation= method.getAnnotation(AvoidDuplicateSubmission.class);
    	}catch (Exception e) {
    		logger.warn("===================获取handler出错,请求地址为:"+request.getServletPath()+"的请求");
		}
        
        //判断是否存在注解
        if (annotation != null) {
            //获取是否创建token
            boolean ceateToken = annotation.ceateToken();
            String sourceKey=annotation.sourceKey();
            
            //判断是否为空
            if(ObjectHelper.isEmpty(sourceKey)){
            	logger.warn("===================不存在资源key,请检查,请求地址为:"+request.getServletPath()+"的请求");
                return false;
            }
            
            //防重复体检支持多操作
            String[] sourceKeyArr=sourceKey.split(";");
            
            for(String sourceKeyItem:sourceKeyArr){
            	if (ceateToken) {
            		//TODO 暂时先放入到session 以后可能单独用内存 缓存等存放 涉及到定时清理等
            		String token=UUID.randomUUID().toString();
            		logger.info("方法:"+method.getName()+"创建token为:"+token+"操作:"+sourceKeyItem);
            		request.getSession(false).setAttribute(sourceKeyItem, token);
            	}
            	//获取是否删除token
            	boolean removeToken = annotation.removeToken();
            	//如果为删除token
            	if (removeToken) {
            		//校验是否重复
            		if (isRepeatSubmit(request,sourceKeyItem)) {
            			logger.warn("====防重复提交重要提醒,请求地址为:"+request.getServletPath()+"的请求 为重复请求");
            			return false;
            		}
            		//如果不为重复提交则删除
            		request.getSession(false).removeAttribute(sourceKeyItem);
            	}
            }
        }
        return true;
    }

    /**
     * 判断是否重复提交
     * @param request 请求内容
     * @param sourceKey 资源key
     * @return false为不重复 true为重复
     */
    private boolean isRepeatSubmit(HttpServletRequest request,String sourceKey) {
        //获取请求的token
        String serverToken = (String) request.getSession(false).getAttribute(sourceKey);
        //如果为空则默认为重复
        if (serverToken == null) {
            return true;
        }
        return false;
    }
}
