package com.zdsoft.finance.businesssetting.repository;

import com.zdsoft.finance.businesssetting.entity.QuestionScene;
import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.Pageable;
import com.zdsoft.framework.core.common.util.ObjectHelper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title: QuestionSceneRepository.java 
 * @ClassName: QuestionSceneRepository 
 * @Description: 场景设置操作仓库
 * @author longwei 
 * @date 2017年2月6日 上午11:09:27 
 * @version V1.0
 */
public interface QuestionSceneRepository extends CustomRepository<QuestionScene, String>{

	/**
	 * @Title: findQuestionScenceByCondition
	 * @Description: 按照条件查找
	 * @author liaoguowei
	 * @param pageable 分页参数
	 * @param questionScene 查询条件
	 * @return com.zdsoft.framework.core.common.page.Page<com.zdsoft.finance.businesssetting.entity.QuestionScene>
	 * @throws
	 */
	public default Page<QuestionScene> findQuestionScenceByCondition(Pageable pageable,QuestionScene questionScene) throws Exception{
		Map<String,Object> qryCondition=new HashMap<String,Object>(3);
		StringBuffer sql=new StringBuffer(" select ");
									sql.append(" s.id as \"id\", ");
									sql.append(" s.sceneTypeCode as \"sceneTypeCode\", ");
									sql.append(" s.questionId as \"questionId\", ");
									sql.append(" s.sceneQuestionType as \"sceneQuestionType\", ");
									sql.append(" q.questionContent as \"questionContent\",");
									sql.append(" q.questionTypeCode as \"questionTypeCode\", ");
									sql.append(" q.paraValue as \"paraValue\", ");
									sql.append(" q.judgeRule as \"judgeRule\" ");
								sql.append(" from buss_questionscene s left join buss_question q on q.id=s.questionId ");
								sql.append(" where s.isDeleted = 'F' and q.isDeleted = 'F' ");
		//问题
		if(ObjectHelper.isNotEmpty(questionScene.getQuestionContent())){
			sql.append(" and q.questionContent like :questionContent ");
			qryCondition.put("questionContent","%"+questionScene.getQuestionContent()+"%");
		}
		//字段类型
		if(ObjectHelper.isNotEmpty(questionScene.getQuestionTypeCode())){
			sql.append(" and q.questionTypeCode = :questionTypeCode ");
			qryCondition.put("questionTypeCode",questionScene.getQuestionTypeCode());
		}
		//类型
		if(ObjectHelper.isNotEmpty(questionScene.getSceneTypeCode())){
			sql.append(" and s.sceneTypeCode = :sceneTypeCode ");
			qryCondition.put("sceneTypeCode",questionScene.getSceneTypeCode());
		}
		//规则类型
		if(ObjectHelper.isNotEmpty(questionScene.getSceneQuestionType())){
			sql.append(" and s.sceneQuestionType = :sceneQuestionType ");
			qryCondition.put("sceneQuestionType",questionScene.getSceneQuestionType());
		}
		sql.append(" order by s.createTime desc,s.updateTime desc ");

		return this.findBySqlEntityPage(pageable, sql.toString(), qryCondition, QuestionScene.class);
	}

	/**
	 * @Title: findBySceneTypeCodeAndSceneQuestionType 
	 * @Description: 通过场景类型与场景问题类型获取命题
	 * @author jincheng 
	 * @param sceneTypeCode 场景问题类型编号
	 * @param sceneQuestionType 场景问题类型
	 * @return
	 */
	@Query(" from QuestionScene where sceneTypeCode=?1 and sceneQuestionType=?2 and isDeleted = 'F' ")
	public List<QuestionScene> findBySceneTypeCodeAndSceneQuestionType(String sceneTypeCode, String sceneQuestionType);

	/**
	 * @Title: findBySceneTypeCode 
	 * @Description: 通过场景类型获取命题
	 * @author jincheng 
	 * @param sceneTypeCodeList
	 * @param isDeleted
	 * @return
	 */
	public List<QuestionScene> findBySceneTypeCodeInAndIsDeleted(List<String> sceneTypeCodeList,boolean isDeleted);


	/**
	 * @Title: findByQuestionIdAndSceneTypeCode
	 * @Description: 查找集合中id的有效数据
	 * @author liaoguowei
	 * @param questionIds 问题ID集合
	 * @param sceneTypeCode 场景问题类型
	 * @return java.util.List<com.zdsoft.finance.businesssetting.entity.QuestionScene>
	 * @throws
	 */
	@Query(" from QuestionScene q where q.isDeleted = false and q.questionId in (:questionId) and q.sceneTypeCode = :sceneTypeCode ")
	public List<QuestionScene> findByQuestionIdAndSceneTypeCode(@Param("questionId")List<String> questionIds,@Param("sceneTypeCode")String sceneTypeCode);



}

