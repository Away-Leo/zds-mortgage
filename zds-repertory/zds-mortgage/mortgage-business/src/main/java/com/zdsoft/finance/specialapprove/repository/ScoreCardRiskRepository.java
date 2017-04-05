package com.zdsoft.finance.specialapprove.repository;

import java.util.List;

import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.finance.specialapprove.entity.ScoreCardRisk;


/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: ScoreCardRiskRepository.java 
 * @ClassName: ScoreCardRiskRepository 
 * @Description: 评分卡风险Repository 
 * @author jingjy 
 * @date 2017年2月14日 下午5:54:59 
 * @version V1.0
 */
public interface ScoreCardRiskRepository extends CustomRepository<ScoreCardRisk, String>{
	/**
	 * 
	 * @Title: findScoreCardRiskByCaseApplyId 
	 * @Description: 根据案件ID查询评分卡信息
	 * @author jingjy 
	 * @param caseApplyId
	 * 		案件ID
	 * @return
	 */
	public List<ScoreCardRisk> findScoreCardRiskByCaseApplyId(String caseApplyId);

}
