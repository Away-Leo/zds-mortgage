package com.zdsoft.finance.aop.service;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterNameDiscoverer;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import com.zdsoft.finance.common.annotation.InitExpandFiled;
import com.zdsoft.finance.common.auto.entity.ExpandFiled;
import com.zdsoft.finance.common.auto.listener.ExpandFiledAutoService;
import com.zdsoft.framework.core.common.exception.InvalidArgumentException;

@Aspect
@Component
public class InitExpandFiledAdvisor {

	@Autowired
	ParameterNameDiscoverer parameterNameDiscoverer;

	@Autowired
	ExpandFiledAutoService expandFiledAutoService;

	/**
	 * 注解有@InitExpandFiled方法的切点
	 */
	public static final String INIT_EXPAND_FILED = "@annotation(com.zdsoft.finance.common.annotation.InitExpandFiled)";

	/**
	 * 添加域对象实列切面
	 * 
	 * @param joinPoint
	 * @return
	 * @throws Throwable
	 */
	@AfterReturning(pointcut = INIT_EXPAND_FILED, returning = "retVal")
	public void createInitExpandFiledAfter(JoinPoint joinPoint, Object retVal)
			throws Exception, InvalidArgumentException {

		// 获取参数表及其对应的值
		Map<String, Object> argMap = new HashMap<String, Object>();
		Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
		// 方法名字
		String[] names = parameterNameDiscoverer.getParameterNames(method);
		// 取得传入参数值
		Object[] args_ = joinPoint.getArgs();
		int i = 0;
		for (String n : names) {
			argMap.put(n, args_[i]);
			i++;
		}

		// 取得创建标签
		InitExpandFiled initExpandFiled = (InitExpandFiled) method.getAnnotation(InitExpandFiled.class);
		// 取得clazzNm的取值
		String clazzNm = initExpandFiled.clazzNm();
		String clazzVoNm = initExpandFiled.voNm();

		ModelAndView modelAndView = (ModelAndView) retVal;
		Map<String, Object> modelMap = modelAndView.getModel();

		List<ExpandFiled> expandFileds = expandFiledAutoService.findByClassNm(clazzNm);
		modelMap.put("expandFileds", expandFileds);
		modelMap.put("clazzNm", modelMap.get(clazzVoNm));
	}

}
