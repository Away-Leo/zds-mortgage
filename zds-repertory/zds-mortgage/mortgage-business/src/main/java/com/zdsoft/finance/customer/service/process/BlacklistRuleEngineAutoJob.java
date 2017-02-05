package com.zdsoft.finance.customer.service.process;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zdsoft.bpm.annotation.client.AutoJob;
import com.zdsoft.finance.customer.service.BlanckListService;
import com.zdsoft.framework.core.common.dto.DelegateExecution;
import com.zdsoft.framework.core.common.exception.BusinessException;
import com.zdsoft.framework.core.common.service.JavaDelegate;

@Service
@AutoJob(label = "黑名单规则引擎接口", resource = "com.zdsoft.finance.projectatta.service.process.blacklistRuleEngine.autoJob")
public class BlacklistRuleEngineAutoJob implements JavaDelegate{
    
    @Autowired
    private BlanckListService blanckListService ;
    
    @Override
    @Transactional
    public void execute(DelegateExecution delegateexecution) throws Exception {
        //流程id
        //String processInstId = (String) delegateexecution.getVariable("processInstanceId");
        //项目id（案件id）
        String projectId = (String) delegateexecution.getVariable("projectId");
        Boolean boolean1 = blanckListService.checkBlankList(projectId);
        if(boolean1){
        	throw new BusinessException("您暂时不符合我司贷款要求！");
        }
    }
}
