package com.zdsoft.finance.businesssetting.service;

import java.util.List;

import com.zdsoft.finance.base.service.BaseService;
import com.zdsoft.finance.businesssetting.entity.Questionnaire;
import com.zdsoft.finance.spi.businesssetting.QuestionnaireListDto;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title: QuestionnaireService.java 
 * @ClassName: QuestionnaireService 
 * @Description: 智能问卷答题结果接口
 * @author jincheng 
 * @date 2017年2月22日 下午4:53:55 
 * @version V1.0
 */
public interface QuestionnaireService extends BaseService<Questionnaire> {

	/**
	 * @Title: getQuestionnaireList 
	 * @Description: 获取智能问卷答题
	 * @author jincheng 
	 * @param linkTypeCode
	 * @param caseApplyId 案件id
	 * @return
	 */
	QuestionnaireListDto getQuestionnaireList(String linkTypeCode, String caseApplyId)throws Exception;
	
	/**
	 * @Title: findByCaseApplyIdAndQuestionId 
	 * @Description: 获取智能问卷答题
	 * @author jincheng 
	 * @param caseApplyId
	 * @param questionId
	 * @return
	 */
	Questionnaire findByCaseApplyIdAndQuestionId(String caseApplyId, String questionId);

	/**
	 * @Title: findByCaseApplyIdAndSceneTypeCode 
	 * @Description: 获取智能问卷答题结果
	 * @author jincheng 
	 * @param caseApplyId 案件id
	 * @param sceneTypeCode 场景类型
	 * @return
	 */
	List<Questionnaire> findByCaseApplyIdAndSceneTypeCode(String caseApplyId, String sceneTypeCode);
	
	/**
	 * @Title: findByCaseApplyId 
	 * @Description: 获取智能问卷答题结果
	 * @author jincheng 
	 * @param caseApplyId 案件id
	 * @return
	 */
	List<Questionnaire> findByCaseApplyId(String caseApplyId);

	/**
	 * @Title: saveOrUpdateQuestionnaire 
	 * @Description: 保存智能问卷答题结果
	 * @author jincheng 
	 * @param caseApplyId
	 * @param sceneTypeCode
	 * @param questionList
	 * @throws Exception
	 */
	void saveOrUpdateQuestionnaire(String caseApplyId,String sceneTypeCode,String questionList) throws Exception;

	/**
	 * @Title: findByCaseApplyIdAndQuestionIdAndSceneTypeCode 
	 * @Description: 获取智能问卷答题结果
	 * @author jincheng 
	 * @param caseApplyId
	 * @param questionId
	 * @param string
	 * @return
	 */
	Questionnaire findByCaseApplyIdAndQuestionIdAndSceneTypeCode(String caseApplyId, String questionId, String sceneTypeCode);
	
	/**
	 * @Title: saveOrUpdateAppQuestionnaire 
	 * @Description: APP保存智能问卷答题结果
	 * @author jingjiyan 
	 * @param caseApplyId
	 * 			案件ID
	 * @param sceneTypeCode
	 * 			场景类型
	 * @param questionList
	 * 			答题答案
	 * @throws Exception
	 */
	void saveOrUpdateAppQuestionnaire(String caseApplyId, String sceneTypeCode, String questionList) throws Exception;

	
}
