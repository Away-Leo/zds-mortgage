package com.zdsoft.finance.businesssetting.service;

import com.zdsoft.finance.base.service.BaseService;
import com.zdsoft.finance.businesssetting.entity.Question;
import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.Pageable;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title: QuestionService.java 
 * @ClassName: QuestionService 
 * @Description: 问题定义操作接口
 * @author longwei 
 * @date 2017年2月6日 上午11:10:14 
 * @version V1.0
 */
public interface QuestionService extends BaseService<Question> {


	/**
	  * @Title: findById
	  * @Description: 按照ID查找数据
	  * @author liaoguowei 
	  * @param id 问题ID
	  * @return Question
	  * @throws BusinessException
	*/
	public Question findById(String id) throws BusinessException;

	/**
	 * @Title: findByPage
	 * @Description: 分页查询
	 * @author liaoguowei
	 * @param question 问题
	 * @param pageable 分页参数
	 * @return Page<Question>
	 * @throws Exception
	 */
	public Page<Question> findByPage(Question question, Pageable pageable) throws BusinessException;

	/**
	 * @Title: delete
	 * @Description: 保存
	 * @author liaoguowei
	 * @param questionId 问题ID
	 * @return void
	 * @throws Exception
	 */
	public void delete(String questionId) throws BusinessException ;

	/**
	  * @Title: saveQuestion
	  * @Description: 保存
	  * @author liaoguowei
	  * @param question 问题
	  * @return Question
	  * @throws Exception
	*/
	public Question saveQuestion(Question question) throws Exception;

	/**
	  * @Title: updateQuestion
	  * @Description: 更新
	  * @author liaoguowei
	  * @param question 问题
	  * @return Question
	  * @throws Exception
	*/
	public Question updateQuestion(Question question) throws Exception;

	/**
	  * @Title: saveOrUpdateQuestion
	  * @Description: 更新或保存
	  * @author liaoguowei
	  * @param question 问题
	  * @return Question
	  * @throws Exception
	*/
	public Question saveOrUpdateQuestion(Question question) throws Exception;

	/**
	 * @Title: findQuestionById 
	 * @Description: 根据id 获取问题定义
	 * @author jincheng 
	 * @param questionId 问题ID
	 * @return
	 */
	public Question findQuestionById(String questionId);

}
