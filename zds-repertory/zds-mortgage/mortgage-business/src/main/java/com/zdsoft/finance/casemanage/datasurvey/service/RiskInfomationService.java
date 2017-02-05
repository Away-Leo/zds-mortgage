package com.zdsoft.finance.casemanage.datasurvey.service;

import com.zdsoft.finance.base.service.BaseService;
import com.zdsoft.finance.casemanage.datasurvey.entity.RiskInfomation;
import com.zdsoft.finance.marketing.entity.CaseApply;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * 
 * @Title:RiskInfomationService.java
 * @Package:com.zdsoft.finance.casemanage.datasurvey.service
 * @Description:风险信息
 * @author: laijun
 * @date:2017年1月10日 下午9:44:19
 * @version:v1.0
 */
public interface RiskInfomationService extends BaseService<RiskInfomation> {
	/**
	 * 
	 * 保存风险信息
	 *
	 * @author laijun
	 * @date:2017年1月13日 下午2:48:32
	 * @param caseApply
	 * @return
	 * @throws Exception
	 */
	CaseApply saveOrUpdateCaseApplyAndRiskInfo(CaseApply caseApply) throws Exception;
}
