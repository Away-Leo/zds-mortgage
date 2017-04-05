package com.zdsoft.finance.finance.service.process;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.zdsoft.bpm.annotation.client.AutoJob;
import com.zdsoft.essential.dto.emp.EmpDto;
import com.zdsoft.finance.casemanage.datasurvey.entity.FeeInfomation;
import com.zdsoft.finance.casemanage.datasurvey.service.FeeInfomationService;
import com.zdsoft.finance.finance.entity.FinIncome;
import com.zdsoft.finance.finance.entity.FinIncomeDetail;
import com.zdsoft.finance.finance.service.FinIncomeDetailService;
import com.zdsoft.finance.finance.service.FinIncomeService;
import com.zdsoft.finance.marketing.entity.CaseApply;
import com.zdsoft.finance.marketing.service.CaseApplyService;
import com.zdsoft.framework.core.common.dto.DelegateExecution;
import com.zdsoft.framework.core.common.service.JavaDelegate;
import com.zdsoft.framework.core.common.util.ObjectHelper;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title: FeeInfomationReviewJob.java 
 * @ClassName: FeeInfomationReviewJob 
 * @Description: 收费确认通过后置事项
 * @author xiangjx 
 * @date 2017年2月28日 下午2:58:19 
 * @version V1.0
 */
@Service
@AutoJob(label = "收费确认通过后置事项", resource = "com.zdsoft.finance.feeInfomation.autojob")
@Lazy(false)
public class FeeInfomationReviewJob implements JavaDelegate{

	@Autowired
	private FeeInfomationService feeInfomationService;
	
	@Autowired
	private FinIncomeDetailService finIncomeDetailService;
	
	@Autowired
	private CaseApplyService caseApplyService;
	
	@Autowired
	private FinIncomeService finIncomeService;
	
	@Autowired
	private  com.zdsoft.essential.client.service.CED  CED;
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor = Exception.class)
	public void execute(DelegateExecution delegateExecution) throws Exception {
		
		String businessId = (String) delegateExecution.getVariable("businessKey");// 流程业务id 
		
		FinIncome finIncome = finIncomeService.findOne(businessId);//收据信息
		
		if(ObjectHelper.isNotEmpty(finIncome) &&"2".equals(finIncome.getStatus())){
			List<FinIncomeDetail> detailList = finIncomeDetailService.findByFinIncomeId(businessId);
			
			for(FinIncomeDetail detail:detailList){
				if(ObjectHelper.isNotEmpty(detail.getFeeId())){
					FeeInfomation fee=feeInfomationService.findOne(detail.getFeeId());
					if(ObjectHelper.isNotEmpty(fee)){
						fee.setReceivedAmount(detail.getPaidAmount().add(ObjectHelper.isNotEmpty(fee.getReceivedAmount())?fee.getReceivedAmount():BigDecimal.ZERO));//累计实收
						fee.setBalanceAmount(fee.getReceivedAmount().subtract(ObjectHelper.isNotEmpty(fee.getPaidAmount())?fee.getPaidAmount():BigDecimal.ZERO));//实收-实付=结余
						feeInfomationService.updateEntity(fee);
					}
				}else{
					//把FinIncomeDetail中存在，FeeInfomation不存在的数据 保存到FeeInfomation
					CaseApply apply = caseApplyService.findOne(finIncome.getCaseApplyId());//案件信息
					FeeInfomation info = new FeeInfomation();
					info.setCaseApply(apply);
					info.setExpectedAmount(detail.getPaidAmount());
					info.setReceivedAmount(detail.getPaidAmount());
					
					EmpDto emp=CED.loadEmployeeByCode(finIncome.getCreateBy());
					info.setFeeObjectType(FeeInfomationService.JOIN_TYPE_OTHER_FULLCODE);
					info.setFeeObjectId(finIncome.getCreateBy());
					info.setFeeeObjectName(emp.getEmpNm());
					
					info.setPayObjectType(FeeInfomationService.JOIN_TYPE_OTHER_FULLCODE);
					info.setPayObjectId(finIncome.getCreateBy());
					info.setPayObjectName(emp.getEmpNm());
					
					info.setFeeItem(detail.getFeeItem());
					info.setFeeItemName(detail.getFeeItemName());
					info.setFeeType(detail.getFeeType());
					info.setFeeTypeName(detail.getFeeTypeName());
					info.setBalanceAmount(BigDecimal.ZERO);
					feeInfomationService.saveEntity(info);
					detail.setFeeId(info.getId());
					finIncomeDetailService.updateEntity(detail);
				}
			}
		}
	}
}
