package com.zdsoft.finance.job.service;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.framework.core.common.component.SpringContextHolder;
import com.zdsoft.framework.core.common.exception.AppException;
import com.zdsoft.framework.core.common.protocol.RPCRequest;
import com.zdsoft.framework.core.common.protocol.RPCResponse;
import com.zdsoft.framework.core.common.service.CheckTask;
import com.zdsoft.framework.core.common.service.JavaDelegate;
import com.zdsoft.framework.core.common.service.Subprocess;
import com.zdsoft.framework.core.common.util.AopTargetUtils;
import com.zdsoft.framework.core.common.util.ObjectHelper;
import com.zdsoft.framework.core.server.RPCAbstractService;
import com.zdsoft.framework.cra.annotation.InterfaceService;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title: IsImplService.java 
 * @ClassName: IsImplService 
 * @Description: 判断接口类型
 * @author gufeng 
 * @date 2017年3月13日 下午4:44:01 
 * @version V1.0
 */
@Service
@InterfaceService(resource = "zds.workflow.job.isImplService", label = "判断接口类型服务")
@Transactional
public class IsImplService extends RPCAbstractService {

	private Logger logger = LoggerFactory.getLogger(IsImplService.class);

	@Override
	@Transactional
	public RPCResponse remoteCall(RPCRequest request) throws AppException {

		logger.info("开始调用zds.workflow.job.isImplService服务：" + request.getBodys().toString());

		RPCResponse response = new RPCResponse();
		
		@SuppressWarnings("unchecked")
		Map<String, Object> mapParam = (Map<String, Object>) request.getBodys().get("params");
		String beanName = (String)mapParam.get("beanName");
		Map<String,Object> bodys = new HashMap<String, Object>();
		try {
			Object bean = SpringContextHolder.getBean(beanName);
			if (bean != null) {
				Object original = AopTargetUtils.getTarget(bean);
				if (ObjectHelper.isImplement(original, CheckTask.class)) {
					bodys.put("result", "CheckTask");
				}else if(ObjectHelper.isImplement(original, JavaDelegate.class)){
					bodys.put("result", "JavaDelegate");
				}else if(ObjectHelper.isImplement(original, Subprocess.class)){
					bodys.put("result", "Subprocess");
				}
				response.setBodys(bodys);
			}
			response.setStatus(RPCResponse.SUCCESS);
		} catch (Exception e) {
			response.setStatus(RPCResponse.FAILURE);
			response.setStatusDesc(e.getMessage());
			e.printStackTrace();
			throw new BusinessException(e.getMessage());
		}
		return response;
	}
}
