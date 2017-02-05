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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterNameDiscoverer;
import org.springframework.stereotype.Component;

import com.zdsoft.finance.common.annotation.CreateProjectInfo;
import com.zdsoft.finance.projectfolder.entity.ProjectInfo;
import com.zdsoft.finance.projectfolder.entity.ProjectInfoType;
import com.zdsoft.finance.projectfolder.repository.ProjectInfoTypeRepository;
import com.zdsoft.framework.core.common.exception.InvalidArgumentException;
import com.zdsoft.framework.core.common.util.ObjectHelper;
import com.zdsoft.framework.core.common.util.StoreHelper;

/**
 * Dao层中对注解有@CreateProjectInfo、@DeleteProjectInfo的方法，系统在创建、删除域对象实例时
 * 自动向系统注册、删除该实例
 * 
 * @author 钟平
 * @version 1.0.0
 * @since 1.0.0
 */
@Aspect
@Component
public class ProjInfoAdvisor {
	/**
	 * 日志
	 */
	Logger logger = LoggerFactory.getLogger(ProjInfoAdvisor.class);
	/**
	 * 注解有@CreateProjectInfo方法的切点
	 */
	public static final String CREATE_PORJECTINFO = "@annotation(com.zdsoft.finance.common.annotation.CreateProjectInfo)";
	//public static final String CREATE_PORJECTINFO = "@within(org.springframework.stereotype.Service)&&@annotation(com.zdsoft.finance.aop.annotation.CreateProjectInfo)";

	/**
	 * 注解有@DeleteProjectInfo方法的切点
	 */
	public static final String DELETE_PORJECTINFO = "@annotation(com.zdsoft.finance.common.annotation.DeleteProjectInfo)";
	//public static final String DELETE_PORJECTINFO = "@within(org.springframework.stereotype.Service)&&@annotation(com.zdsoft.finance.aop.annotation.DeleteProjectInfo)";

	
	@Autowired
	ProjectInfoTypeRepository projectInfoTypeRepository;

	@Autowired
	PorjInfoAdvisorService porjInfoAdvisorService;

	@Autowired
	ParameterNameDiscoverer parameterNameDiscoverer;
	

	/**
	 * 添加域对象实列切面
	 * 
	 * @param joinPoint
	 * @return
	 * @throws Throwable
	 */
	@AfterReturning(pointcut = CREATE_PORJECTINFO, returning = "retVal")
	public void createProjectInfoAfter(JoinPoint joinPoint,Object retVal) throws Exception,InvalidArgumentException{
		// 判断返回值是否为空
		if (ObjectHelper.isEmpty(retVal)) {
			logger.info("返回值为空----------------->"+retVal);
			throw new InvalidArgumentException("retVal","返回值不能为空");
		}
		// 判断返回参数是否为对像
		Class<?> objClass = retVal.getClass();
		
		// 获取参数表及其对应的值
		Map<String, Object> argMap = new HashMap<String, Object>();
		Method method = ((MethodSignature) joinPoint.getSignature())
				.getMethod();
		// 方法名字
		String[] names = parameterNameDiscoverer.getParameterNames(method);
		// 取得传入参数值
		Object[] args_ = joinPoint.getArgs();
		int i = 0;
		for (String n : names) {
			argMap.put(n, args_[i]);
			i++;
		}
		
		//取得创建标签
		CreateProjectInfo createProjectInfo = (CreateProjectInfo) method.getAnnotation(CreateProjectInfo.class);
		//取得project的取值
		String projectCd = createProjectInfo.projectCd();
		
		/*String resProjectCd = "";*/
		String resProjectCd=projectCd;
		//判断porjectCd是否指定了取值
		/*if(ObjectHelper.isEmpty(projectCd)){
			throw new InvalidArgumentException("projectCd","项目编号不能为空!");
		}
		if(projectCd.indexOf("rtn.")==0){
			projectCd = projectCd.substring(4);
			resProjectCd = (String)ObjectHelper.getFieldValue(retVal, projectCd);
		}else{
			resProjectCd = (String)ObjectHelper.getFieldValue(argMap, projectCd);
		}*/
		//取得返回的id
		String resId = "";
		try {
			// 取得创建对像的id
			Method id = objClass.getMethod("getId", new Class[] {});
			resId = (String) id.invoke(retVal, new Object[] {});
		} catch (NoSuchMethodException e) {
			throw new InvalidArgumentException("id"," 无效的项目资料信息Id为空");
		}

		// 取得资料实体类名
		String classNm = createProjectInfo.className().getName();

		// 根据资料实体类名取得项目资料类别
		ProjectInfoType projectInfoType =  
				projectInfoTypeRepository
				.findByClassNmAndAppCd(classNm,StoreHelper.getApplication());

			
		if (ObjectHelper.isEmpty(projectInfoType)) {
			throw new InvalidArgumentException("projectInfoType","项目资料类别为空");
		}
		
						
		ProjectInfo proR = new ProjectInfo();		
		proR.setBusinessId(resId);
		proR.setProjectCd(resProjectCd);
		proR.setProjectInfoType(projectInfoType);
		porjInfoAdvisorService.saveProjectInfo(proR);
		logger.info("添加项目资料信息成功:id="+proR.getId()+",resId="+resId + ",resProjectCd="+resProjectCd);
	}

	/**
	 * 删除域对象实列切面
	 * 
	 * @param joinPoint
	 * @return
	 * @throws Throwable
	 */
	@AfterReturning(pointcut = DELETE_PORJECTINFO, returning = "retVal")
	public void deleteProjectInfoAfter(JoinPoint joinPoint,Object retVal) throws InvalidArgumentException{
		// 获取域对象参数
		Object[] args = joinPoint.getArgs();
		Object businessId;
		// 执行方法
		String businessId_="";
		
		if(ObjectHelper.isEmpty(args)){
			logger.info("未取得传入参数");
			throw new InvalidArgumentException("retVal","返回值不能为空");
		}else{
			businessId = args[0];
		}
		
		if(businessId instanceof String){
			businessId_ = (String)businessId;
		}
		porjInfoAdvisorService.deleteProjectInfo(businessId_);
	}
}
