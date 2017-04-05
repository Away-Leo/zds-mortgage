package com.zdsoft.finance.businesssetting.repository;

import java.util.List;

import com.zdsoft.finance.businesssetting.entity.Questionnaire;
import com.zdsoft.finance.common.base.CustomRepository;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title: QuestionnaireRepository.java 
 * @ClassName: QuestionnaireRepository 
 * @Description: 智能问卷答题结果Repository 
 * @author jincheng 
 * @date 2017年2月22日 下午4:53:12 
 * @version V1.0
 */
public interface QuestionnaireRepository extends CustomRepository<Questionnaire, String>{

	/**
	 * @Title: findByCaseApplyIdAndSceneTypeCode 
	 * @Description: 获取智能问卷答题结果
	 * @author jincheng 
	 * @param caseApplyId
	 * @param sceneTypeCode
	 * @return
	 */
	List<Questionnaire> findByCaseApplyIdAndSceneTypeCode(String caseApplyId, String sceneTypeCode);

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
	 * @Title: findByCaseApplyIdAndQuestionIdAndSceneTypeCode 
	 * @Description: 获取智能问卷答题
	 * @author jincheng 
	 * @param caseApplyId
	 * @param questionId
	 * @param sceneTypeCode
	 * @return
	 */
	Questionnaire findByCaseApplyIdAndQuestionIdAndSceneTypeCode(String caseApplyId, String questionId,String sceneTypeCode);

	/**
	 * @Title: findByCaseApplyId 
	 * @Description: 获取智能问卷答题
	 * @author jincheng 
	 * @param caseApplyId
	 * @return
	 */
	List<Questionnaire> findByCaseApplyId(String caseApplyId);


}
