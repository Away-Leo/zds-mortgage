
package com.zdsoft.finance.casemanage.receivablePlan.service.impl;

import java.math.BigDecimal;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zdsoft.finance.base.service.impl.BaseServiceImpl;
import com.zdsoft.finance.busiform.entity.BusiForm;
import com.zdsoft.finance.busiform.entity.BusiFormStatus;
import com.zdsoft.finance.busiform.service.BusiFormService;
import com.zdsoft.finance.casemanage.datasurvey.entity.MatterModuleValidate;
import com.zdsoft.finance.casemanage.datasurvey.service.MatterModuleValidateService;
import com.zdsoft.finance.casemanage.receivablePlan.entity.ReceivableInfo;
import com.zdsoft.finance.casemanage.receivablePlan.repository.ReceivableInfoRepository;
import com.zdsoft.finance.casemanage.receivablePlan.service.ReceivableInfoService;
import com.zdsoft.finance.casemanage.receivablePlan.service.ReceivablePlanTempService;
import com.zdsoft.finance.common.enums.busiform.ApplyModelTypeEnum;
import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.finance.common.utils.BigDecimalCalculateTwo;
import com.zdsoft.finance.marketing.entity.CaseApply;
import com.zdsoft.finance.marketing.service.CaseApplyService;
import com.zdsoft.framework.core.common.util.ObjectHelper;

/**
 * 版权所有：重庆正大华日软件有限公司
 * 
 * @Title: ReceivableInfoServiceImpl.java
 * @ClassName: ReceivableInfoServiceImpl
 * @Description: 还款计划基本信息ServiceImpl
 * @author dengyy
 * @date 2017年2月20日 下午5:49:13
 * @version V1.0
 */
@Service("receivableInfoService")
public class ReceivableInfoServiceImpl extends BaseServiceImpl<ReceivableInfo, ReceivableInfoRepository> implements ReceivableInfoService {

	@Autowired
	private BusiFormService busiFormService;
	@Autowired
    private ReceivablePlanTempService receivablePlanTempService;
	@Autowired
	private CaseApplyService caseApplyService ;
	@Autowired
	private MatterModuleValidateService matterModuleValidateService;
	
    @Override
    public ReceivableInfo findByCaseApplyId(String caseApplyId) throws BusinessException {
        if(ObjectHelper.isEmpty(caseApplyId)){
            throw new BusinessException("10010004", "案件id不能为空！");
        }
        return this.customReposity.findByCaseApplyId(caseApplyId);
    }
    
    /**
     * 
     * Title: saveOrUpdateReceivableInfo   
     * Description:更新、保存还款计划基本信息 
     * @param receivableInfo
     * 			还款计划基本信息
     * @param applyTerm
     * 			期限
     * @param applyTermUnit
     * 			期限单位
     * @return   
     *
     */
    @Override
	@Transactional(rollbackFor=Exception.class)
    public ReceivableInfo saveOrUpdateReceivableInfo(ReceivableInfo receivableInfo,Integer applyTerm,String applyTermUnit){
    	try {
            	if(ObjectHelper.isNotEmpty(receivableInfo.getId())){
            		receivableInfo = this.customReposity.updateEntity(receivableInfo);
            	}else{
            		receivableInfo = this.customReposity.save(receivableInfo);
            	}
            	MatterModuleValidate validate = new MatterModuleValidate();
            	validate.setBusinessKey(receivableInfo.getCaseApplyId());
				validate.setMatterName("dataSurvey");
				validate.setTabName("receivablePlan");
				validate.setExecuteTag(1);   	
				matterModuleValidateService.saveOrUpdateEntity(validate);
    	CaseApply caseApply = caseApplyService.findOne(receivableInfo.getCaseApplyId());
    	caseApply.setReceivableInfo(receivableInfo);
		caseApply.setRepayMethod(receivableInfo.getRepaymentType());
		caseApply.setApplyRate(receivableInfo.getLoanMonthRate());
		caseApply.setApplyRateUnit(receivableInfo.getLoanMonthRateUnit());
		caseApply.setOverdueRate(receivableInfo.getOverdueDailyRate());
		caseApply.setOverdueRateUnit(receivableInfo.getOverdueDailyRateUnit());
		caseApply.setSynthesizeRate(receivableInfo.getSyntheticalRate());
		caseApply.setSynthesizeRateUnit(receivableInfo.getSyntheticalRateUnit());
		caseApply.setDynamicRate(receivableInfo.getInternalRateReturn());
		caseApply.setDynamicRateUnit(receivableInfo.getInternalRateReturnUnit());
		caseApply.setApplyTerm(applyTerm);
		caseApply.setApplyTermUnit(applyTermUnit);
		caseApplyService.updateEntity(caseApply);
		
    	} catch (BusinessException e) {
			e.printStackTrace();
		}
    	return receivableInfo;
    }
    
    

	@Override
	@Transactional(rollbackFor=Exception.class)
	public ReceivableInfo updateReceivableInfo(ReceivableInfo receivableInfo, String receivablePlanJson, Boolean submitStatus)
			throws Exception {
		// 保存还款计划基本信息
		ReceivableInfo po = null;
		if (ObjectHelper.isNotEmpty(receivableInfo.getId())) {
			po = this.findOne(receivableInfo.getId());
			BeanUtils.copyProperties(receivableInfo, po, new String[] { "id","createTime","receivablePlan","busiForm","institutionalAudit","groupConfirmation" });
			po.setReceivableFlag(ReceivableInfo.AFTER_LOAN);
		} else {
			po = receivableInfo;
		}
		//更改小数位数
		po.setLoanMonthRate(BigDecimalCalculateTwo.div(po.getLoanMonthRate(),
				ReceivableInfo.RECEIVABLEINFO_DAY.equals(po.getLoanMonthRateUnit())?BigDecimal.valueOf(1000):BigDecimal.valueOf(100)));
		po.setOverdueDailyRate(BigDecimalCalculateTwo.div(po.getOverdueDailyRate(),
				ReceivableInfo.RECEIVABLEINFO_DAY.equals(po.getOverdueDailyRateUnit())?BigDecimal.valueOf(1000):BigDecimal.valueOf(100)));
		if(ObjectHelper.isNotEmpty(po.getSyntheticalRate())){
			po.setSyntheticalRate(BigDecimalCalculateTwo.div(po.getSyntheticalRate(),
					ReceivableInfo.RECEIVABLEINFO_DAY.equals(po.getSyntheticalRateUnit())?BigDecimal.valueOf(1000):BigDecimal.valueOf(100)));
		}
		if(ObjectHelper.isNotEmpty(po.getInternalRateReturn())){
			po.setInternalRateReturn(BigDecimalCalculateTwo.div(po.getInternalRateReturn(),
					ReceivableInfo.RECEIVABLEINFO_DAY.equals(po.getInternalRateReturnUnit())?BigDecimal.valueOf(1000):BigDecimal.valueOf(100)));
		}
		po = this.saveOrUpdateEntity(po);
		//获取案件信息
        CaseApply caseApply = caseApplyService.findOne(po.getCaseApplyId());
        caseApply.setRepayMethod(po.getRepaymentType());
		caseApply.setApplyRate(po.getLoanMonthRate());
		caseApply.setApplyRateUnit(po.getLoanMonthRateUnit());
		caseApply.setOverdueRate(po.getOverdueDailyRate());
		caseApply.setOverdueRateUnit(po.getOverdueDailyRateUnit());
		caseApply.setSynthesizeRate(po.getSyntheticalRate());
		caseApply.setSynthesizeRateUnit(po.getSyntheticalRateUnit());
		caseApply.setDynamicRate(po.getInternalRateReturn());
		caseApply.setDynamicRateUnit(po.getInternalRateReturnUnit());
        caseApply.setReceivableInfo(po);
        caseApplyService.saveOrUpdateCaseApply(caseApply);
        
		//保存还款计划
        receivablePlanTempService.deleteReceivablePlanTempByReceivableInfoId(po.getId());
		receivablePlanTempService.saveReceivablePlanTemp(po.getCaseApplyId(), po.getId(), receivablePlanJson);
		
		BusiForm busiForm = po.getBusiForm();
		
		if(ObjectHelper.isEmpty(busiForm)){
			busiForm = new BusiForm();
			//状态
			busiForm.setFormStatus(BusiFormStatus.DRAFT.value);
			//还款计划：标题 案件号+产品      业务编号：案件号
	        busiForm.setApplyTitle(caseApply.getCaseApplyCode()+caseApply.getProductSubtypeName());
	        busiForm.setBusinessCode(caseApply.getCaseApplyCode());
			//关联业务表单id
			busiForm.setBusinessEntityId(po.getId());
			//关联表单域对象类名 例如表单为Project时 此字段存入的值为Project
			busiForm.setBusinessEntityName(ReceivableInfo.class.getSimpleName());
			//关联组件数据ID 例如 项目表ID 合同ID
			busiForm.setComponentsEntityId(po.getCaseApplyId());
			//关联组件域对象类名 例如表单为Project时 此字段存入的值为Project
			busiForm.setComponentsEntityName(CaseApply.class.getSimpleName());
			//所属模块
	        busiForm.setModelType(ApplyModelTypeEnum.CASE_RECEIVABLE_INFO.value);
	        busiForm = busiFormService.saveOrUpdateBusiForm(busiForm);
	        po.setBusiForm(busiForm);
	        po = this.saveOrUpdateEntity(po);
		}
		
		//判断是否启动流程
        if(submitStatus){
            //功能代码
            busiForm.setFunctionCode(ReceivableInfo.PROCEESS_CODE);
            //产品id
            busiForm.setProductId(caseApply.getProductSubtypeId());
            //启动流程
            busiForm = busiFormService.startProcess(busiForm, null, null);
            //下一处理人
            po.setCurrentDealEmpName(busiForm.getCurrentDealEmpNm());
        }
		
		po.setBusiForm(busiForm);
		return po;
	}

    @Override
    public ReceivableInfo findByCaseApplyId(String caseApplyId, Integer receivableFlag) throws BusinessException {
        return this.customReposity.findByCaseApplyId(caseApplyId, receivableFlag);
    }

}
