package com.zdsoft.finance.problemmanage.service;

import java.util.List;

import com.zdsoft.finance.base.service.BaseService;
import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.finance.problemmanage.entity.Question;
import com.zdsoft.finance.problemmanage.entity.QuestionScene;
import com.zdsoft.finance.problemmanage.entity.QuestionSceneQuery;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.Pageable;

/**
 * 场景设置操作接口
 * @author longwei
 * @date 2017/01/03
 * @version 1.0
 */
public interface QuestionSceneService extends BaseService<QuestionScene>{

	/**
	 * 查询场景设置列表并分页
	 * @param questionSceneQuery
	 * @return
	 * @throws BusinessException
	 */
	public Page<QuestionSceneQuery> findByPage(QuestionSceneQuery questionSceneQuery,Pageable pageable) throws BusinessException;
	
	/**
	 * 保存场景设置
	 * @param questionScene
	 * @param questions
	 * @throws BusinessException
	 */
	public void saveOrUpdate(QuestionScene questionScene,List<Question> questions) throws BusinessException;
}
