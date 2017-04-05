package com.zdsoft.finance.businesssetting.service;

import com.zdsoft.finance.base.service.BaseService;
import com.zdsoft.finance.businesssetting.entity.Question;
import com.zdsoft.finance.businesssetting.entity.QuestionScene;
import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.Pageable;

import java.util.List;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title: QuestionSceneService.java 
 * @ClassName: QuestionSceneService 
 * @Description: 场景设置操作接口
 * @author liaoguowei
 * @date 2017年2月6日 上午11:10:06 
 * @version V1.0
 */
public interface QuestionSceneService extends BaseService<QuestionScene>{

	/**
	  * @Title: findQuestionSceneByPage
	  * @Description: 分页查询场景问题设置
	  * @author liaoguowei 
	  * @param questionScene 场景问题
	  * @param pageable 分页参数
	  * @return Page<QuestionScene>
	  * @throws BusinessException
	*/
	public Page<QuestionScene> findQuestionSceneByPage(QuestionScene questionScene, Pageable pageable) throws Exception;

	/**
	  * @Title: saveQuestionScene
	  * @Description: 保存场景问题设置
	  * @author liaoguowei 
	  * @param questionIds 问题ID
	  * @param sceneTypeCode 场景问题类型编号
	  * @return List<QuestionScene>
	  * @throws Exception
	*/
	public List<QuestionScene> saveQuestionScene(String questionIds,String sceneTypeCode,String sceneQuestionType) throws Exception;

	/**
	  * @Title: logicDeleteQuestionScene
	  * @Description: 逻辑删除
	  * @author liaoguowei
	  * @param id 场景问题ID
	  * @return void
	  * @throws BusinessException
	*/
	public void logicDeleteQuestionScene(String id) throws BusinessException;

	/**
	  * @Title: findQuestionSceneById
	  * @Description: 按照ID查找
	  * @author liaoguowei
	  * @param id 场景问题ID
	  * @return QuestionScene
	  * @throws BusinessException
	*/
	public QuestionScene findQuestionSceneById(String id) throws BusinessException;

	/**
	 * @Title: findBySceneTypeCodeAndSceneQuestionType 
	 * @Description: 通过场景类型与场景问题类型获取命题
	 * @author jincheng 
	 * @param sceneTypeCode 场景问题类型编号
	 * @param sceneQuestionType 场景问题类型
	 * @return
	 */
	public List<QuestionScene> findBySceneTypeCodeAndSceneQuestionType(String sceneTypeCode, String sceneQuestionType);
	
	/**
	 * @Title: findBySceneTypeCode 
	 * @Description: 通过场景类型获取命题
	 * @author jincheng 
	 * @param sceneTypeCodeList 场景问题类型集合
	 * @return
	 */
	public List<QuestionScene> findBySceneTypeCodeIn(List<String> sceneTypeCodeList);

	/**
	 * @Title: findByQuestionIdAndSceneTypeCode
	 * @Description: 在当前场景下和多个ID中查找数据
	 * @author liaoguowei
	 * @param ids 多个问题ID
	 * @param sceneTypeCode 场景类型编号
	 * @return java.util.List<com.zdsoft.finance.businesssetting.entity.QuestionScene>
	 * @throws
	 */
	public List<Question> findByQuestionIdAndSceneTypeCode(String ids,String sceneTypeCode) throws BusinessException;


}
