package com.zdsoft.finance.casemanage.interview.service.process;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zdsoft.bpm.annotation.client.AutoJob;
import com.zdsoft.bpm.service.client.BPM;
import com.zdsoft.finance.common.utils.AmountConversionUtil;
import com.zdsoft.finance.marketing.entity.CaseApply;
import com.zdsoft.finance.marketing.service.CaseApplyService;
import com.zdsoft.framework.core.common.dto.DelegateExecution;
import com.zdsoft.framework.core.common.exception.BusinessException;
import com.zdsoft.framework.core.common.service.JavaDelegate;
import com.zdsoft.framework.core.common.util.ObjectHelper;

/** 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: ApplyAmountJudgmentAuto.java 
 * @ClassName: ApplyAmountJudgmentAuto 
 * @Description: 申请金额判断
 * @author dengyy 
 * @date 2017年3月20日 下午10:10:59 
 * @version V1.0 
 */

@Service
@AutoJob(label = "申请金额判断", resource = "com.zdsoft.finance.casemanage.interview.service.process.applyAmountJudgmentAuto")
public class ApplyAmountJudgmentAuto implements JavaDelegate {
    
    private Logger logger = LoggerFactory.getLogger(ApplyAmountJudgmentAuto.class);
    
    @Autowired
    private BPM bpm;
    
    @Autowired
    private CaseApplyService caseApplyService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void execute(DelegateExecution exec) throws Exception {
        logger.info("开始执行申请金额判断自动事项...");
        String projectId = (String) exec.getVariable("projectId");
        String procInstId = (String) exec.getVariable("processInstanceId");
        logger.info("案件ID为 ：" + projectId);
        logger.info("processInstansId为 ：" + procInstId);
        CaseApply caseApply = caseApplyService.findOne(projectId);
        if (ObjectHelper.isNotEmpty(caseApply)) {
            BigDecimal actualApplyAmount = caseApply.getActualApplyAmount();
            actualApplyAmount = AmountConversionUtil.convertToWYuan(actualApplyAmount);
            Map<String, String> vars = new HashMap<>();
            vars.put("apply_amount", actualApplyAmount.intValue()+"");
            bpm.setEngineVariable(procInstId, vars);
        }else{
            throw new BusinessException("获取案件信息出错!");
        }
    }

}
