package com.zdsoft.finance.specialapprove.service;

import java.util.List;

import com.zdsoft.finance.base.service.BaseService;
import com.zdsoft.finance.specialapprove.entity.ScoreCardRisk;


/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: ScoreCardRiskService.java 
 * @ClassName: ScoreCardRiskService 
 * @Description: 评分卡风险接口
 * @author jingjy 
 * @date 2017年2月14日 下午5:51:48 
 * @version V1.0
 */
public interface ScoreCardRiskService extends BaseService<ScoreCardRisk>{
	
	/**
	 * 
	 * @Title: findScoreCardRiskInfoByCaseApplyId 
	 * @Description: 根据项目ID查询评分卡风险信息
	 * @author jingjy 
	 * @param 
	 * 		caseApplyId 案件Id
	 * @return
	 */
	public List<ScoreCardRisk> findByCaseApplyId(String caseApplyId);

}
