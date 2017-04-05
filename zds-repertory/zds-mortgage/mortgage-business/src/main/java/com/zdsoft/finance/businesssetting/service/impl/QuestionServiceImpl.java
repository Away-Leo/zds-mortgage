package com.zdsoft.finance.businesssetting.service.impl;

import com.zdsoft.essential.client.service.CED;
import com.zdsoft.finance.base.service.impl.BaseServiceImpl;
import com.zdsoft.finance.businesssetting.entity.Question;
import com.zdsoft.finance.businesssetting.repository.QuestionRepository;
import com.zdsoft.finance.businesssetting.service.QuestionService;
import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.finance.common.utils.ObjectProperUtil;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.Pageable;
import com.zdsoft.framework.core.common.util.ObjectHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @title QuestionServiceImpl.java
 * @className QuestionServiceImpl
 * @description 问题库定义service
 * @author LiaoGuoWei
 * @create 2017/2/28 15:49
 * @version V1.0
 **/
@Service("questionService")
public class QuestionServiceImpl extends BaseServiceImpl<Question, CustomRepository<Question, String>> implements QuestionService {

    @Autowired
    private QuestionRepository questionRepository; 

    @Autowired
    private CED CED;

    @Override
    public Question findById(String id) throws BusinessException {
        if(ObjectHelper.isNotEmpty(id)){
            Question question=this.questionRepository.findOne(id);
            if(ObjectHelper.isNotEmpty(question)){
                return question;
            }else{
                throw new BusinessException("10010002","根据参数未找到相应数据，根据ID查找问题库出错");
            }
        }else{
            throw new BusinessException("10010004","未传入相关参数，按照ID查找问题库出错");
        }
    }

    @Override
    @Transactional
    public void delete(String questionId) throws BusinessException {
        if (ObjectHelper.isNotEmpty(questionId)) {
            Question question = this.findById(questionId);
            this.logicDelete(question);
        }else{
            throw new BusinessException("10010004","未传入相关参数，删除问题库设置出错");
        }
    }

    @Override
    public Page<Question> findByPage(Question question, Pageable pageable) throws BusinessException {
        return this.questionRepository.findByPage(question, pageable);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Question saveQuestion(Question question) throws Exception {
        if(ObjectHelper.isNotEmpty(question)){
            if(ObjectHelper.isEmpty(question.getId())){
                question.setUpdateBy(CED.getLoginUser().getEmpCd());
                question.setUpdateOrgCd(CED.getLoginUser().getOrgCd());
                question.setCreateBy(CED.getLoginUser().getEmpCd());
                question.setCreateOrgCd(CED.getLoginUser().getOrgCd());
                return this.questionRepository.saveEntity(question);
            }else{
                throw new BusinessException("10010003","传入参数错误，保存问题库设置出错");
            }
        }else{
            throw new BusinessException("10010004","未传入相关参数，保存问题库设置出错");
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Question updateQuestion(Question question) throws Exception {
        if(ObjectHelper.isNotEmpty(question)){
            if(ObjectHelper.isNotEmpty(question.getId())){
                //查找旧值
                Question oldData=this.findById(question.getId());
                //将更改的值进行赋值
                oldData=(Question) ObjectProperUtil.compareAndValue(question,oldData,false,new String[]{"paraValue","judgeRule"});
                oldData.setUpdateBy(CED.getLoginUser().getEmpCd());
                oldData.setUpdateOrgCd(CED.getLoginUser().getOrgCd());

                return this.questionRepository.updateEntity(oldData);
            }else{
                throw new BusinessException("10010003","传入参数有误，更新问题库设置出错");
            }
        }else{
            throw new BusinessException("10010004","未传入相关参数，更新问题库设置出错");
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Question saveOrUpdateQuestion(Question question) throws Exception {
        if(ObjectHelper.isNotEmpty(question)){
            if(ObjectHelper.isNotEmpty(question.getId())){
                return this.updateQuestion(question);
            }else{
                return this.saveQuestion(question);
            }
        }else{
            throw new BusinessException("10010004","未传入相关参数，更新或保存问题库设置出错");
        }
    }

	@Override
	public Question findQuestionById(String questionId) {
		return this.customReposity.findOne(questionId);
	}
}
