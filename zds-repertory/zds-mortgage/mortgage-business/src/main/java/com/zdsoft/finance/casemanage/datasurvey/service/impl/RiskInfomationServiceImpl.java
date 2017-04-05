package com.zdsoft.finance.casemanage.datasurvey.service.impl;

import java.util.Date;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zdsoft.essential.client.service.CED;
import com.zdsoft.finance.base.service.impl.BaseServiceImpl;
import com.zdsoft.finance.casemanage.datasurvey.entity.RiskInfomation;
import com.zdsoft.finance.casemanage.datasurvey.repository.RiskInfomationRepository;
import com.zdsoft.finance.casemanage.datasurvey.service.RiskInfomationService;
import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.finance.marketing.entity.CaseApply;
import com.zdsoft.finance.marketing.repository.CaseApplyRepository;
import com.zdsoft.framework.core.common.util.ObjectHelper;
/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title:RiskInfomationServiceImpl.java
 * @Package:com.zdsoft.finance.casemanage.datasurvey.service.impl
 * @Description:风险信息
 * @author: laijun
 * @date:2017年1月10日 下午9:44:50
 * @version:v1.0
 */
@Service
public class RiskInfomationServiceImpl extends BaseServiceImpl<RiskInfomation, CustomRepository<RiskInfomation, String>>
		implements RiskInfomationService {
	@Autowired
	RiskInfomationRepository riskInfomationRepository;
	@Autowired
	CaseApplyRepository caseApplyRepository;
	@Autowired
	CED CED;
	
	
	@Override
	public CaseApply saveOrUpdateCaseApplyAndRiskInfo(CaseApply apply) throws Exception {
		if (ObjectHelper.isNotEmpty(apply.getId())) {
			CaseApply oldCaseApply = caseApplyRepository.findOne(apply.getId());
			// 更新风险信息
			RiskInfomation newRiskInfomation = apply.getRiskInfo();
			if (ObjectHelper.isNotEmpty(newRiskInfomation)) {
				if (ObjectHelper.isNotEmpty(newRiskInfomation.getId())) {
					RiskInfomation oldRiskInfomation = riskInfomationRepository.findOne(newRiskInfomation.getId());
					BeanUtils.copyProperties(newRiskInfomation, oldRiskInfomation);
					oldRiskInfomation.setUpdateBy(CED.getLoginUser().getEmpNm());
					oldRiskInfomation.setUpdateTime(new Date());
					oldRiskInfomation.setUpdateOrgCd(CED.getLoginUser().getOrgCd());
					riskInfomationRepository.updateEntity(oldRiskInfomation);
				} else {
					newRiskInfomation.setCreateBy(CED.getLoginUser().getEmpNm());
					newRiskInfomation.setCreateOrgCd(CED.getLoginUser().getOrgCd());
					RiskInfomation riskInfo = riskInfomationRepository.saveEntity(newRiskInfomation);
					oldCaseApply.setRiskInfo(riskInfo);
				}
			}
			if(ObjectHelper.isNotEmpty(apply.getCapitalUseFor())){
				oldCaseApply.setCapitalUseFor(apply.getCapitalUseFor());
			}
			if(ObjectHelper.isNotEmpty(apply.getTerminalId())){
				oldCaseApply.setTerminalId(apply.getTerminalId());
			}

			apply = caseApplyRepository.updateEntity(oldCaseApply);
		}
		return apply;
	}
	
}
