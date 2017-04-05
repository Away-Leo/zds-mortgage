package com.zdsoft.finance.businesssetting.service;

import com.zdsoft.finance.spi.businesssetting.QuestionRuleParamDto;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title: QuestionJudgeRuleService.java 
 * @ClassName: QuestionJudgeRuleService 
 * @Description: 命题解析接口
 * @author jincheng 
 * @date 2017年3月1日 下午5:35:06 
 * @version V1.0
 */
public interface QuestionJudgeRuleService {
	
	/**
	 * @Title: analysisJudgeRule 
	 * @Description: 解析是否命中
	 * @author jincheng 
	 * @param paramDto 命题解析参数
	 * @return
	 */
	public boolean analysisJudgeRule(QuestionRuleParamDto paramDto);
}
