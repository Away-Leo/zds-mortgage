package com.zdsoft.finance.problemmanage.repository;

import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.finance.problemmanage.entity.Question;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.Pageable;

/**
 * 问题定义操作仓库
 * @author longwei
 * @date 2016/01/03
 * @version 1.0
 */
public interface QuestionRepository extends CustomRepository<Question, String>{

	/**
	 * 查询问题定义列表并分页
	 * @param question
	 * @param pageable
	 * @return
	 * @throws BusinessException
	 */
	public Page<Question> findByPage(Question question,Pageable pageable) throws BusinessException ;

}
