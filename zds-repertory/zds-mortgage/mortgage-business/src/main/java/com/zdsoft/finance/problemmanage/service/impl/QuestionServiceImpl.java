package com.zdsoft.finance.problemmanage.service.impl;

import org.slf4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zdsoft.finance.base.service.impl.BaseServiceImpl;
import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.finance.problemmanage.entity.Question;
import com.zdsoft.finance.problemmanage.repository.QuestionRepository;
import com.zdsoft.finance.problemmanage.service.QuestionService;
import com.zdsoft.framework.core.common.annotation.Log;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.Pageable;
import com.zdsoft.framework.core.common.util.ObjectHelper;

/**
 * 问题定义操作接口实现
 * @author longwei
 * @date 2017/01/03
 * @version 1.0
 */
@Service("questionService")
public class QuestionServiceImpl extends BaseServiceImpl<Question, CustomRepository<Question,String>> implements QuestionService{

	@Autowired
	private QuestionRepository questionRepository;
	@Log
	private Logger logger;
	
	@Override
	public Page<Question> findByPage(Question question, Pageable pageable) throws BusinessException {
		if(ObjectHelper.isEmpty(question) || ObjectHelper.isEmpty(pageable)){
			logger.error("参数不合法");
			throw new BusinessException("参数不合法");
		}
		
		return questionRepository.findByPage(question, pageable);
	}

	@Override
	@Transactional
	public Question saveOrUpdate(Question question) throws BusinessException {
		if(ObjectHelper.isEmpty(question)){
			logger.error("对象不能为空");
			throw new BusinessException("对象不能为空");
		}
		
		if(ObjectHelper.isEmpty(question.getId())){
			question=this.saveEntity(question);
		}else {
			Question old=this.findOne(question.getId());
			if(ObjectHelper.isEmpty(old)){
				logger.error("原对象不存在");
				throw new BusinessException("原对象不存在");
			}
			BeanUtils.copyProperties(question, old, new String[]{"id","isDeleted","createTime","createBy","createOrgCd"});
			question=this.updateEntity(old);
		}
		return question;
	}

	@Override
	@Transactional
	public void delete(String questionId) throws BusinessException {
		if(ObjectHelper.isEmpty(questionId)){
			logger.error("参数为空");
			throw new BusinessException("参数为空");
		}
		
		Question question=this.findOne(questionId);
		if(ObjectHelper.isEmpty(question)){
			logger.error("对象不存在");
			throw new BusinessException("对象不存在");
		}
		
		this.logicDelete(question);
		
	}

}
