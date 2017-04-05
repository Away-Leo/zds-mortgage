package com.zdsoft.finance.common.service.process;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.HashSet;
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
import com.zdsoft.finance.marketing.entity.CaseTask;
import com.zdsoft.finance.marketing.service.CaseTaskService;
import com.zdsoft.framework.core.common.exception.AppException;
import com.zdsoft.framework.core.common.service.BusinessRoleResolver;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: CreditMemberResolverService.java 
 * @ClassName: CreditMemberResolverService 
 * @Description: 工作流 指定资调员
 * @author dengyy 
 * @date 2017年3月3日 下午4:28:08 
 * @version V1.0
 */
@Service
@BusinessRole("资调员")
public class CreditMemberResolverService implements BusinessRoleResolver {
    
    private Logger logger = LoggerFactory.getLogger(CreditMemberResolverService.class);
    
    @Autowired
    private CaseTaskService caseTaskService ;
    
    @SuppressWarnings("unchecked")
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Set<Map<String, String>> getEmployees(Map<String, Object> args) throws AppException {
        //案件id 项目id
        String projectId = (String) args.get("projectId");
        logger.info("案件id："+projectId);
        //案件派单
        CaseTask caseTask = caseTaskService.findByCaseApplyId(projectId);
        Set<Map<String, String>> haMaps = new HashSet<Map<String, String>>();
        Map<String, String> map = new HashMap<String, String>();
        EmpDto empDto = new EmpDto() ;
        empDto.setEmpCd(caseTask.getTaskPersonnelCode());
        empDto.setEmpNm(caseTask.getTaskPersonnelName());
        try {
            //数据转换
            map= BeanUtils.describe(empDto);
            logger.info("指定人："+map);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
            logger.error("设置指定人失败：",e);
        }
        //设置处理人
        haMaps.add(map);
        return haMaps;
    }

}