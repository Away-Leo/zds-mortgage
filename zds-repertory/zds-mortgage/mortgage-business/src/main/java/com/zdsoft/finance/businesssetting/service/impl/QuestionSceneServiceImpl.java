package com.zdsoft.finance.businesssetting.service.impl;

import com.zdsoft.finance.base.service.impl.BaseServiceImpl;
import com.zdsoft.finance.businesssetting.entity.Question;
import com.zdsoft.finance.businesssetting.entity.QuestionScene;
import com.zdsoft.finance.businesssetting.repository.QuestionRepository;
import com.zdsoft.finance.businesssetting.repository.QuestionSceneRepository;
import com.zdsoft.finance.businesssetting.service.QuestionSceneService;
import com.zdsoft.finance.businesssetting.service.QuestionService;
import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.Pageable;
import com.zdsoft.framework.core.common.util.ObjectHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title: QuestionSceneServiceImpl.java 
 * @ClassName: QuestionSceneServiceImpl 
 * @Description: 场景设置操作实现
 * @author longwei 
 * @date 2017年2月6日 上午11:09:51 
 * @version V1.0
 */
@Service("questionSceneService")
public class QuestionSceneServiceImpl extends BaseServiceImpl<QuestionScene, CustomRepository<QuestionScene,String>> implements QuestionSceneService {

	@Autowired
	private QuestionSceneRepository questionSceneRepository;

	@Autowired
	private QuestionService questionService;

	@Autowired
	private QuestionRepository questionRepository;

	@Override
	public Page<QuestionScene> findQuestionSceneByPage(QuestionScene questionScene, Pageable pageable) throws Exception {
		return this.questionSceneRepository.findQuestionScenceByCondition(pageable, questionScene);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public List<QuestionScene> saveQuestionScene(String questionIds, String sceneTypeCode,String sceneQuestionType) throws Exception {
		if(ObjectHelper.isNotEmpty(questionIds)&&ObjectHelper.isNotEmpty(sceneTypeCode)){
			//问题库id数组
			String[] allQuestionIds=questionIds.split(",");
			//定义批量保存的临时数据
			List<QuestionScene> saveTempList=new ArrayList<QuestionScene>();
			for(int i=0;i<allQuestionIds.length;i++){
				try{
					Question question=this.questionService.findById(allQuestionIds[i]);
					QuestionScene questionScene=new QuestionScene();
					questionScene.setQuestionId(question.getId());
					questionScene.setSceneTypeCode(sceneTypeCode.trim());
					questionScene.setSceneQuestionType(sceneQuestionType);
					//将数据存入临时集合中
					saveTempList.add(questionScene);
				}catch (BusinessException e){
					logger.error("场景问题设置保存时查找相应问题数据出错",e);
				}
			}
			return this.questionSceneRepository.batchSave(saveTempList);
		}else{
			throw new BusinessException("10010004","未传入相关参数，保存场景问题设置出错");
		}
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void logicDeleteQuestionScene(String id) throws BusinessException {
		if(ObjectHelper.isNotEmpty(id)){
			QuestionScene questionScene=this.findQuestionSceneById(id);
			this.questionSceneRepository.logicDelete(questionScene);
		}else{
			throw new BusinessException("10010004","未传入相关参数，删除场景设置出错");
		}
	}

	@Override
	public QuestionScene findQuestionSceneById(String id) throws BusinessException {
		if(ObjectHelper.isNotEmpty(id)){
			QuestionScene questionScene=this.questionSceneRepository.findOne(id);
			if(ObjectHelper.isNotEmpty(questionScene)){
				return questionScene;
			}else{
				throw new BusinessException("10010002","根据参数未找到相应数据，按照ID查找场景设置出错");
			}
		}else{
			throw new BusinessException("10010004","未传入相关参数，按照ID查找场景问题设置出错");
		}
	}

	@Override
	public List<QuestionScene> findBySceneTypeCodeAndSceneQuestionType(String sceneTypeCode, String sceneQuestionType) {
		return questionSceneRepository.findBySceneTypeCodeAndSceneQuestionType( sceneTypeCode,  sceneQuestionType);
	}

	@Override
	public List<QuestionScene> findBySceneTypeCodeIn(List<String> sceneTypeCodeList) {
		return questionSceneRepository.findBySceneTypeCodeInAndIsDeleted(sceneTypeCodeList,false);
	}

	@Override
	public List<Question> findByQuestionIdAndSceneTypeCode(String ids, String sceneTypeCode) throws BusinessException{
		if(ObjectHelper.isNotEmpty(ids)&&ObjectHelper.isNotEmpty(sceneTypeCode)){
			//问题库id数组
			String[] allQuestionIds=ids.split(",");
			//定义返回数据集
			List<Question> returnData=new ArrayList<Question>();
			List<QuestionScene> sourceData=this.questionSceneRepository.findByQuestionIdAndSceneTypeCode(Arrays.asList(allQuestionIds),sceneTypeCode);
			for(QuestionScene temp:sourceData){
				Question question=this.questionRepository.findOne(temp.getQuestionId());
				if(ObjectHelper.isNotEmpty(question)){
					returnData.add(question);
				}
			}
			return returnData;
		}else{
			throw new BusinessException("10010004","未传入相关参数，添加场景问题检查数据出错");
		}
	}
}
