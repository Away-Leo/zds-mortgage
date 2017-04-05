package com.zdsoft.finance.specialapprove.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zdsoft.finance.base.service.impl.BaseServiceImpl;
import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.finance.specialapprove.entity.ScoreCardRisk;
import com.zdsoft.finance.specialapprove.repository.ScoreCardRiskRepository;
import com.zdsoft.finance.specialapprove.service.ScoreCardRiskService;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: ScoreCardRiskServiceImpl.java 
 * @ClassName: ScoreCardRiskServiceImpl 
 * @Description: 评分卡风险实现
 * @author jingjy 
 * @date 2017年2月14日 下午5:51:26 
 * @version V1.0
 */
@Service("scoreCardRiskService")
public class ScoreCardRiskServiceImpl extends BaseServiceImpl<ScoreCardRisk, CustomRepository<ScoreCardRisk, String>>
	implements ScoreCardRiskService {
	
	@Autowired
	private ScoreCardRiskRepository scoreCardRiskRepository;
	
	/**
	 * 
	 * Title: findByCaseApplyId  
	 * Description: 根据项目ID查询评分卡风险信息
	 * @author jingjiyan  
	 * @param caseApplyId
	 * 			案件ID
	 * @return
	 */
	public List<ScoreCardRisk> findByCaseApplyId(String caseApplyId){
		return scoreCardRiskRepository.findScoreCardRiskByCaseApplyId(caseApplyId);
	}
	
}
