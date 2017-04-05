package com.zdsoft.finance.specialapprove.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zdsoft.finance.base.service.impl.BaseServiceImpl;
import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.finance.specialapprove.entity.RiskRules;
import com.zdsoft.finance.specialapprove.entity.SpecialApproveManage;
import com.zdsoft.finance.specialapprove.repository.RiskRulesRepository;
import com.zdsoft.finance.specialapprove.service.RiskRulesService;
import com.zdsoft.framework.core.common.util.ObjectHelper;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: RiskRulesServiceImpl.java 
 * @ClassName: RiskRulesServiceImpl 
 * @Description: 风险规则明细实现
 * @author jingjy 
 * @date 2017年2月14日 下午5:51:57 
 * @version V1.0
 */
@Service("riskRulesService")
public class RiskRulesServiceImpl extends BaseServiceImpl<RiskRules, CustomRepository<RiskRules, String>>
	implements RiskRulesService {
	
	@Autowired
	private RiskRulesRepository riskRulesRepository;
	
	@Override
	public List<RiskRules> findByCaseApplyIdAndSpecialStatus(String caseApplyId,String specialStatus){
		return riskRulesRepository.findByCaseApplyIdAndSpecialStatus(caseApplyId, specialStatus);
	}
	
	/**
	 * 
	 * Title: findRiskRulesInfoByCaseApplyId  
	 * Description:  根据项目ID查询风险规则信息
	 * @author jingjiyan  
	 * @param caseApplyId
	 * 			案件ID
	 * @return
	 */
	public List<RiskRules> findRiskRulesInfoByCaseApplyId(String caseApplyId){
		return	riskRulesRepository.findRiskRulesInfoByCaseApplyId(caseApplyId);
	}

	@Override
	public boolean judgeComplianceReviewPassed(String caseApplyId) {
		List<RiskRules> riskRuless = riskRulesRepository.findByCaseApplyIdAndIsDeleted(caseApplyId, false);
		if(ObjectHelper.isEmpty(riskRuless)){
			return true;
		}
		for (RiskRules riskRules : riskRuless) {
			//存在审批未通过的
			if(!SpecialApproveManage.SPECIAL_APPROVE_STATUS_ADOPT.equals(riskRules.getSpecialStatus())){
				return false;
			}
		}
		return true;
	}

}
