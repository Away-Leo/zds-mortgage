
package com.zdsoft.finance.contract.service.process;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zdsoft.bpm.annotation.client.AutoJob;
import com.zdsoft.essential.client.service.CED;
import com.zdsoft.finance.common.utils.EnumTimeUnit;
import com.zdsoft.finance.common.utils.TimeUtil;
import com.zdsoft.finance.contract.entity.ConCaseContract;
import com.zdsoft.finance.contract.service.ConCaseContractService;
import com.zdsoft.finance.marketing.entity.CaseApply;
import com.zdsoft.finance.marketing.service.CaseApplyService;
import com.zdsoft.framework.core.common.annotation.Log;
import com.zdsoft.framework.core.common.dto.DelegateExecution;
import com.zdsoft.framework.core.common.exception.BusinessException;
import com.zdsoft.framework.core.common.service.JavaDelegate;
import com.zdsoft.framework.core.common.util.DateHelper;
import com.zdsoft.framework.core.common.util.ObjectHelper;

/** 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: ConCaseContractAutoJob.java 
 * @ClassName: ConCaseContractAutoJob 
 * @Description: 自动创建 合同基础信息
 * @author dengyy 
 * @date 2017年3月1日 上午11:47:31 
 * @version V1.0 
 */
@Service
@AutoJob(label = "自动生成合同", resource = "com.zdsoft.finance.contract.service.process.conCaseContract.autoJob")
public class ConCaseContractAutoJob implements JavaDelegate {

    @Autowired
    private ConCaseContractService conCaseContractService ;
    
    @Autowired
    private CED CED ;
    
    @Autowired
    private CaseApplyService  caseApplyService ;
    
    @Log
    private Logger log;
    
    @Override
    @Transactional
    public void execute(DelegateExecution execution) throws Exception {
        log.info("进入自动生成合同！");
        String projectId=(String)execution.getVariable("projectId");
        if(ObjectHelper.isEmpty(projectId)){
            throw new BusinessException("流程案件id不能为空！");
        }
        log.info("自动生成合同 流程项目id： {}"+projectId);
        //获取案件合同信息
        ConCaseContract entity = conCaseContractService.findByCaseApplyId(projectId);
        //没有合同信息就创建新的合同信息
        if(ObjectHelper.isEmpty(entity)){
            entity = new ConCaseContract();
        }
        CaseApply caseApply = caseApplyService.findOne(projectId);
        entity.setCaseApplyId(projectId);
        //合同表达式
        String expression = CED.resolveExpression(ConCaseContract.EXPRESSION_ID, null);
        if(ObjectHelper.isEmpty(expression)){
            throw new BusinessException("合同表达式生成失败！");
        }
        log.info("合同表达式： {}"+expression);
        entity.setContractNo(expression);
        /**
         * 利率
         */
        entity.setContractRate(caseApply.getApplyRate());
        entity.setContractRateUnit(caseApply.getApplyRateUnit());
        /**
         * 还款方式
         */
        entity.setRepaymentMethod(caseApply.getRepayMethod());
        /**
         * 合同开始日期
         */
        entity.setContractStartDate(caseApply.getApplyDate());
        /**
         * 合同结束日期
         */
        EnumTimeUnit type = null;
        if(CaseApply.DATEUNIT_YEAR.equals(caseApply.getApplyTermUnit()) ){
            type = EnumTimeUnit.Year;
        }else if(CaseApply.DATEUNIT_MONTH.equals(caseApply.getApplyTermUnit())){
            type = EnumTimeUnit.Month;
        }else if(CaseApply.DATEUNIT_DAY.equals(caseApply.getApplyTermUnit())){
            type = EnumTimeUnit.Day;
        }
        //根据开始日期 和期限计算结束日期
        String addDate = TimeUtil.addDate(caseApply.getApplyDate().toString(), type , caseApply.getApplyTerm());
        entity.setContractEndDate(DateHelper.stringDateToLong(addDate, DateHelper.DATE_SHORT_SIMPLE_FORMAT, DateHelper.DATE_SHORT_SIMPLE_FORMAT) );
        conCaseContractService.saveOrUpdateEntity(entity);
        log.info("自动生成合同结束！");
    }

}
