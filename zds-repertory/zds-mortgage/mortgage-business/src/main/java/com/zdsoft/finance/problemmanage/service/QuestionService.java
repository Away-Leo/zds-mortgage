package com.zdsoft.finance.problemmanage.service;

import com.zdsoft.finance.base.service.BaseService;
import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.finance.problemmanage.entity.Question;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.Pageable;

/**
 * 问题定义操作接口
 * 
 * @author longwei
 * @date 2017/01/03
 * @version 1.0
 */
public interface QuestionService extends BaseService<Question> {

	/**
	 * 查询问题定义列表并分页
	 * 
	 * @param question
	 * @param pageable
	 * @return
	 * @throws BusinessException
	 */
	public Page<Question> findByPage(Question question, Pageable pageable) throws BusinessException;
	
	/**
	 * 修改或保存
	 * @param question
	 * @return
	 * @throws BusinessException
	 */
	public Question saveOrUpdate(Question question) throws BusinessException ;
	
	/**
	 * 删除
	 * @param questionId
	 * @throws BusinessException
	 */
	public void delete(String questionId) throws BusinessException ;
}
