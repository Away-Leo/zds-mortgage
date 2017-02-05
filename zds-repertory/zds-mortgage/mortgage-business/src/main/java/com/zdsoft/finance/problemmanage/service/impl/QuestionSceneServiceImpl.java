package com.zdsoft.finance.problemmanage.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zdsoft.finance.base.service.impl.BaseServiceImpl;
import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.finance.problemmanage.entity.Question;
import com.zdsoft.finance.problemmanage.entity.QuestionScene;
import com.zdsoft.finance.problemmanage.entity.QuestionSceneQuery;
import com.zdsoft.finance.problemmanage.repository.QuestionSceneRepository;
import com.zdsoft.finance.problemmanage.service.QuestionSceneService;
import com.zdsoft.finance.problemmanage.service.QuestionService;
import com.zdsoft.framework.core.common.annotation.Log;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.Pageable;
import com.zdsoft.framework.core.common.util.ObjectHelper;

/**
 * 场景设置操作实现
 * @author longwei
 * @date 2017/01/03
 * @version 1.0
 */
@Service("questionSceneService")
public class QuestionSceneServiceImpl extends BaseServiceImpl<QuestionScene, CustomRepository<QuestionScene,String>> implements QuestionSceneService{

	@Log
	private Logger logger;
	
	@Autowired
	private QuestionSceneRepository questionSceneRepository;
	@Autowired
	private QuestionService questionService;
	
	@Override
	public Page<QuestionSceneQuery> findByPage(QuestionSceneQuery questionSceneQuery, Pageable pageable) throws BusinessException {
		if(ObjectHelper.isEmpty(questionSceneQuery) || ObjectHelper.isEmpty(pageable)){
			logger.error("参数不合法");
			throw new BusinessException("参数不合法");
		}
		return questionSceneRepository.findByPage(questionSceneQuery, pageable);
	}

	@Override
	public void saveOrUpdate(QuestionScene questionScene, List<Question> questions) throws BusinessException {
		if(ObjectHelper.isEmpty(questionScene) || ObjectHelper.isEmpty(questions)){
			logger.error("参数为空");
			throw new BusinessException("参数为空");
		}
		
		for(Question question:questions){
			Question query=questionService.findOne(question.getId());
			QuestionScene saveObj=new QuestionScene();
			BeanUtils.copyProperties(questionScene, saveObj);
			if(ObjectHelper.isEmpty(query)){
				logger.error("对象不存在");
				throw new BusinessException("对象不存在");
			}
			saveObj.setQuestion(query);
			this.saveEntity(saveObj);
		}
	}

}
