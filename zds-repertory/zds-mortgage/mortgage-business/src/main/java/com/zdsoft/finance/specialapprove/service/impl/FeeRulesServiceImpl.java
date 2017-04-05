package com.zdsoft.finance.specialapprove.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zdsoft.finance.base.service.impl.BaseServiceImpl;
import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.finance.specialapprove.entity.FeeRules;
import com.zdsoft.finance.specialapprove.entity.SpecialApproveManage;
import com.zdsoft.finance.specialapprove.repository.FeeRulesRepository;
import com.zdsoft.finance.specialapprove.service.FeeRulesService;
import com.zdsoft.framework.core.common.util.ObjectHelper;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: FeeRulesServiceImpl.java 
 * @ClassName: FeeRulesServiceImpl 
 * @Description: 费用规则明细实现
 * @author jingjy 
 * @date 2017年2月14日 下午5:37:21 
 * @version V1.0
 */
@Service("feeRulesService")
public class FeeRulesServiceImpl extends BaseServiceImpl<FeeRules, CustomRepository<FeeRules, String>> 
	implements FeeRulesService {
	
	@Autowired
	private FeeRulesRepository feeRulesRepository;
	
	@Override
	public List<FeeRules> findFeeRulesInfoByCaseApplyIdAndSpecialStatus(String caseApplyId,String specialStatus){
		return feeRulesRepository.findFeeRulesInfoByCaseApplyIdAndSpecialStatus(caseApplyId,specialStatus);
	}
	
	/**
	 * 
	 * Title: findFeeRulesInfoByCaseApplyId  
	 * Description: 根据案件ID查询费用规则信息
	 * @author jingjiyan  
	 * @param caseApplyId
	 * 			案件ID
	 * @return
	 */
	public List<FeeRules> findFeeRulesInfoByCaseApplyId(String caseApplyId){
		return feeRulesRepository.findFeeRulesInfoByCaseApplyId(caseApplyId);
	}

	@Override
	public boolean judgeComplianceReviewPassed(String caseApplyId) {

		List<FeeRules> feeRuless = feeRulesRepository.findByCaseApplyIdAndIsDeleted(caseApplyId,false);
		if(ObjectHelper.isEmpty(feeRuless)){
			return true;
		}
		for (FeeRules feeRules : feeRuless) {
			//存在审批未通过的
			if(!SpecialApproveManage.SPECIAL_APPROVE_STATUS_ADOPT.equals(feeRules.getSpecialStatus())){
				return false;
			}
		}
		return true;
	}
	
}
