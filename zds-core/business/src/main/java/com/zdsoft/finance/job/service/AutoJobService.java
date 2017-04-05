package com.zdsoft.finance.job.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.zdsoft.bpm.dto.ProcessInstanceDto;
import com.zdsoft.framework.core.common.component.SpringContextHolder;
import com.zdsoft.framework.core.common.dto.DelegateExecution;
import com.zdsoft.framework.core.common.exception.AppException;
import com.zdsoft.framework.core.common.protocol.RPCRequest;
import com.zdsoft.framework.core.common.protocol.RPCResponse;
import com.zdsoft.framework.core.common.service.CheckTask;
import com.zdsoft.framework.core.common.service.JavaDelegate;
import com.zdsoft.framework.core.common.service.Subprocess;
import com.zdsoft.framework.core.common.service.BusinessRoleResolver;
import com.zdsoft.framework.core.common.util.AopTargetUtils;
import com.zdsoft.framework.core.common.util.ObjectHelper;
import com.zdsoft.framework.core.server.RPCAbstractService;
import com.zdsoft.framework.cra.annotation.InterfaceService;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title: AutoJobService.java 
 * @ClassName: AutoJobService 
 * @Description: 业务系统自动事项服务，工作流系统调用
 * @author gufeng 
 * @date 2017年3月13日 下午4:42:46 
 * @version V1.0
 */
@Service
@InterfaceService(resource = "zds.workflow.job.AutoJobService", label = "业务系统自动事项服务，工作流系统调用")
@Transactional
public class AutoJobService extends RPCAbstractService {

	private Logger logger = LoggerFactory.getLogger(AutoJobService.class);
	
	@SuppressWarnings({ "unchecked", "unused" })
	@Override
	@Transactional
	public RPCResponse remoteCall(RPCRequest request) throws AppException {

		logger.info("开始调用zds.workflow.job.AutoJobService服务：" + request.getBodys().toString());

		RPCResponse response = new RPCResponse();
		Map<String, Object> bodys = new HashMap<String, Object>();
		Map<String, Object> mapParam = (Map<String, Object>) request.getBodys().get("params");
		String beanName = (String)mapParam.get("beanName");
		String parentTaskInstId = (String) mapParam.get("parentTaskInstId");
		String argsString = (String) mapParam.get("args");
		Map<String, Object> args = JSONObject.parseObject(argsString,Map.class);
		String deString = (String)mapParam.get("de");
		DelegateExecution de = JSONObject.parseObject(deString,DelegateExecution.class);
		try {
			Object bean = SpringContextHolder.getBean(beanName);
			logger.info("开始调用zds.workflow.job.AutoJobService服务 参数说明："+mapParam.toString());
			Boolean checkRtn =false;
			if (bean != null) {
				Object original = AopTargetUtils.getTarget(bean);
				if (ObjectHelper.isImplement(original, Subprocess.class)) {
					// 启动子流程
					Set<ProcessInstanceDto> set = ((Subprocess) bean).startSubprocess(parentTaskInstId,de);
					checkRtn = true;
				}else if(ObjectHelper.isImplement(original, JavaDelegate.class)){
					//执行自动事项
					((JavaDelegate) bean).execute(de);
					checkRtn = true;
				}else if(ObjectHelper.isImplement(original, CheckTask.class)){
					// 执行检查任务
					checkRtn = ((CheckTask) bean).executeCheck(de);
				}else if(ObjectHelper.isImplement(original, BusinessRoleResolver.class)){
					// 执行自定义的角色解析
					Set<Map<String, String>> empDtoSet = ((BusinessRoleResolver) bean).getEmployees(args);
					bodys.put("result", empDtoSet);
					response.setBodys(bodys);
					checkRtn = true;
				}
			}
			
			if(checkRtn){
				response.setStatus(RPCResponse.SUCCESS);
			}else{
				response.setStatus(RPCResponse.FAILURE);
			}
		} catch (Exception e) {
			response.setStatus(RPCResponse.FAILURE);
			response.setStatusDesc(e.getMessage());
			e.printStackTrace();
			logger.info("工作流调用业务错误信息：",e);
		}
		return response;
	}
}
