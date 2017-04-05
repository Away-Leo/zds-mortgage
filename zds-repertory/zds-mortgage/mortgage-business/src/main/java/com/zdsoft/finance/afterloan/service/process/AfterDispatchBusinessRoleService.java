package com.zdsoft.finance.afterloan.service.process;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zdsoft.bpm.annotation.client.BusinessRole;
import com.zdsoft.essential.dto.emp.EmpDto;
import com.zdsoft.finance.afterloan.entity.AfterDispatch;
import com.zdsoft.finance.afterloan.service.AfterDispatchService;
import com.zdsoft.framework.core.common.exception.AppException;
import com.zdsoft.framework.core.common.service.BusinessRoleResolver;
import com.zdsoft.framework.core.common.util.ObjectHelper;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: AfterDispatchBusinessRoleService.java 
 * @ClassName: AfterDispatchBusinessRoleService 
 * @Description: 贷后督办 工作流 指定派工人
 * @author xj 
 * @date 2017年3月7日 下午2:48:29 
 * @version V1.0
 */
@Service
@BusinessRole("派工人员")
public class AfterDispatchBusinessRoleService implements BusinessRoleResolver {
    
    private Logger logger = LoggerFactory.getLogger(AfterDispatchBusinessRoleService.class);
    
    @Autowired
    private AfterDispatchService afterDispatchService ;
    
    @SuppressWarnings("unchecked")
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Set<Map<String, String>> getEmployees(Map<String, Object> args) throws AppException {
        //案件id 项目id
    	logger.info("--------流程中指派派工人员处理人start-------------");
        String projectId = (String) args.get("projectId");
        logger.info("案件id："+projectId);
        String afterSuperviseId = (String) args.get("afterSuperviseId");
        logger.info("督办id："+afterSuperviseId);
        //查询督办的派工人员
        List<AfterDispatch> afterDispatchs = afterDispatchService.findByAfterSuperviseId(afterSuperviseId);
        Set<Map<String, String>> haMaps = new HashSet<Map<String, String>>();
        if(ObjectHelper.isNotEmpty(afterDispatchs)){
        	for (AfterDispatch afterDispatch : afterDispatchs) {
						Map<String, String> map = new HashMap<String, String>();
						EmpDto empDto = new EmpDto();
						empDto.setEmpCd(afterDispatch.getDispatchCode());
						logger.info("dispatchCode:"+afterDispatch.getDispatchCode());
						logger.info("dispatchName:"+afterDispatch.getDispatchName());
						empDto.setEmpNm(afterDispatch.getDispatchName());
					try {
						map = BeanUtils.describe(empDto);
						haMaps.add(map);
					} catch (Exception e) {
						e.printStackTrace();
						logger.error("指定派工人员异常：", e);
					}
			}
        }
        logger.info("--------流程中指派派工人员处理人end-------------");
        return haMaps;
    }

}