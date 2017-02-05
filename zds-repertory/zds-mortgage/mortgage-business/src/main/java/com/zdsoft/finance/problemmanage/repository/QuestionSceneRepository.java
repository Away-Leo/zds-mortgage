package com.zdsoft.finance.problemmanage.repository;

import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.finance.problemmanage.entity.QuestionScene;
import com.zdsoft.finance.problemmanage.entity.QuestionSceneQuery;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.Pageable;

/**
 * 场景设置操作仓库
 * @author longwei
 * @date 2016/01/03
 * @version 1.0
 */
public interface QuestionSceneRepository extends CustomRepository<QuestionScene, String>{

	/**
	 * 查询场景设置列表并分页
	 * @param questionSceneQuery
	 * @param pageable
	 * @return
	 * @throws BusinessException
	 */
	public Page<QuestionSceneQuery> findByPage(QuestionSceneQuery questionSceneQuery ,Pageable pageable) throws BusinessException ;
}

