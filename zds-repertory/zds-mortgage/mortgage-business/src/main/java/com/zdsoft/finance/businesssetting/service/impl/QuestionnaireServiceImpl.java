package com.zdsoft.finance.businesssetting.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.zdsoft.finance.base.service.impl.BaseServiceImpl;
import com.zdsoft.finance.businesssetting.entity.Question;
import com.zdsoft.finance.businesssetting.entity.QuestionScene;
import com.zdsoft.finance.businesssetting.entity.Questionnaire;
import com.zdsoft.finance.businesssetting.repository.QuestionnaireRepository;
import com.zdsoft.finance.businesssetting.service.QuestionJudgeRuleService;
import com.zdsoft.finance.businesssetting.service.QuestionSceneService;
import com.zdsoft.finance.businesssetting.service.QuestionService;
import com.zdsoft.finance.businesssetting.service.QuestionnaireService;
import com.zdsoft.finance.casemanage.datasurvey.entity.MatterModuleValidate;
import com.zdsoft.finance.casemanage.datasurvey.service.MatterModuleValidateService;
import com.zdsoft.finance.common.enums.ENUM_QESTION_SCENE_TYPE;
import com.zdsoft.finance.spi.businesssetting.QuestionRuleParamDto;
import com.zdsoft.finance.spi.businesssetting.QuestionnaireDto;
import com.zdsoft.finance.spi.businesssetting.QuestionnaireListDto;
import com.zdsoft.framework.core.common.component.SpringContextHolder;
import com.zdsoft.framework.core.common.util.ObjectHelper;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title: QuestionnaireServiceImpl.java 
 * @ClassName: QuestionnaireServiceImpl 
 * @Description: 智能问卷答题结果接口实现
 * @author jincheng 
 * @date 2017年2月22日 下午4:54:18 
 * @version V1.0
 */
@Service("questionnaireService")
public class QuestionnaireServiceImpl extends BaseServiceImpl<Questionnaire, QuestionnaireRepository> implements QuestionnaireService {

	@Autowired
	private QuestionSceneService questionSceneService;   
	
	@Autowired
	private QuestionService questionService;
	
	@Autowired
	private MatterModuleValidateService matterModuleValidateService;
	 
	@Override
	public QuestionnaireListDto getQuestionnaireList(String sceneTypeCode, String caseApplyId) throws Exception{ 
			QuestionnaireListDto dto=new QuestionnaireListDto();
			dto.setCaseApplyId(caseApplyId);
			dto.setSceneTypeCode(sceneTypeCode);
			List<QuestionnaireDto> questionList=new ArrayList<QuestionnaireDto>();
			
			//1、获取必填题库
			List<QuestionScene> sceneList1=questionSceneService.findBySceneTypeCodeAndSceneQuestionType(sceneTypeCode,ENUM_QESTION_SCENE_TYPE.DEFAULTS.getValue());
			for(QuestionScene scene1:sceneList1){
				this.wapperQuestion(questionList, scene1);
			}
			
			//2、获取命中题库
			List<QuestionScene> sceneList2=questionSceneService.findBySceneTypeCodeAndSceneQuestionType(sceneTypeCode,ENUM_QESTION_SCENE_TYPE.HITS.getValue());
			for(QuestionScene scene2:sceneList2){
				scene2.setCaseApplyId(caseApplyId);
				this.wapperQuestion(questionList, scene2);
			}
			
			//3、获取随机题库(面签触发或者资调中证件照是否与本人实际面貌相符(否)触发)
			if("YWDM0010203".equals(sceneTypeCode)){
				List<QuestionScene> sceneList3=questionSceneService.findBySceneTypeCodeAndSceneQuestionType(sceneTypeCode,ENUM_QESTION_SCENE_TYPE.RANDOMS.getValue());
				Random r=new Random();
				Long rox=Math.round(r.nextDouble()*3)+2;//2-5的随机数
				if(rox>sceneList3.size()){
					for(QuestionScene scene3:sceneList3){
						this.wapperQuestion(questionList, scene3);
					}
				}else{
						for(Long j = 0l;j<rox;j++){
							Random rand = new Random();
							int num = rand.nextInt(sceneList3.size());
							QuestionScene scene3=sceneList3.get(num);
							this.wapperQuestion(questionList, scene3);
							sceneList3.remove(scene3);
						}
				}
			}
			dto.setQuestionList(questionList);
			return dto;
	}

	/**
	 * @Title: wapperQuestion 
	 * @Description: 封装问题
	 * @author jincheng 
	 * @param questionList
	 * @param scene1
	 */
	private void wapperQuestion(List<QuestionnaireDto> questionList, QuestionScene scene1) {
		Question qu=questionService.findQuestionById(scene1.getQuestionId());
		if(ObjectHelper.isNotEmpty(qu)&&!qu.getIsDeleted()){
			qu.setCaseApplyId(scene1.getCaseApplyId());
			qu.setSceneTypeCode(scene1.getSceneTypeCode());
			QuestionnaireDto dto1=new QuestionnaireDto();
			BeanUtils.copyProperties(scene1, dto1,new String[]{"id"});
			if(ENUM_QESTION_SCENE_TYPE.HITS.getValue().equals(scene1.getSceneQuestionType())){//命中规则
				if(this.isSelectHit(qu)){
					BeanUtils.copyProperties(qu, dto1,new String[]{"id"});
					dto1.setQuestionItem(qu.getParaValue());
					questionList.add(dto1);
				}
			}else{
				BeanUtils.copyProperties(qu, dto1,new String[]{"id"});
				dto1.setQuestionItem(qu.getParaValue());
				questionList.add(dto1);
			}
		}
	}

	@Override
	public List<Questionnaire> findByCaseApplyIdAndSceneTypeCode(String caseApplyId, String sceneTypeCode) {
		return this.customReposity.findByCaseApplyIdAndSceneTypeCode(caseApplyId,sceneTypeCode);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	public void saveOrUpdateQuestionnaire(String caseApplyId,String sceneTypeCode,String questionList) throws Exception{
		//1、删除以前的问题库答题
		List<Questionnaire> list=this.customReposity.findByCaseApplyIdAndSceneTypeCode(caseApplyId, sceneTypeCode);
		for(Questionnaire qu:list){
			this.customReposity.delete(qu);
		}
		
		//2、记录最新的问题库答题
		List<Questionnaire> entityList=JSON.parseArray(questionList, Questionnaire.class);
		for(Questionnaire entity:entityList){
			entity.setCaseApplyId(caseApplyId);
			entity.setSceneTypeCode(sceneTypeCode);
			this.saveEntity(entity);
		}
	}
	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	public void saveOrUpdateAppQuestionnaire(String caseApplyId,String sceneTypeCode,String questionList) throws Exception{
		//1、删除以前的问题库答题
		List<Questionnaire> list=this.customReposity.findByCaseApplyIdAndSceneTypeCode(caseApplyId, sceneTypeCode);
		for(Questionnaire qu:list){
			this.customReposity.delete(qu);
		}
		
		//2、记录最新的问题库答题
		List<Questionnaire> entityList=JSON.parseArray(questionList, Questionnaire.class);
		for(Questionnaire entity:entityList){
			entity.setCaseApplyId(caseApplyId);
			entity.setSceneTypeCode(sceneTypeCode);
			this.saveEntity(entity);
		}
    	MatterModuleValidate validate = new MatterModuleValidate();
    	validate.setBusinessKey(caseApplyId);
		validate.setMatterName("dataSurvey");
		validate.setTabName("questionnaireInfo");
		validate.setExecuteTag(1);   	
		matterModuleValidateService.saveOrUpdateEntity(validate);
	}

	@Override
	public Questionnaire findByCaseApplyIdAndQuestionId(String caseApplyId, String questionId) {
		return this.customReposity.findByCaseApplyIdAndQuestionId(caseApplyId,questionId); 
	}
	
	/**
	 * @Title: isSelectHit 
	 * @Description: 解析命中规则
	 * @author jincheng 
	 * @param scene2
	 * @return
	 */
	private boolean isSelectHit(Question question) {
		boolean bool=false;
		if(ObjectHelper.isNotEmpty(question)&&ObjectHelper.isNotEmpty(question.getJudgeRule())){
			QuestionRuleParamDto dto=new QuestionRuleParamDto();
			dto.setCaseApplyId(question.getCaseApplyId());
			dto.setSceneTypeCode(question.getSceneTypeCode());
			dto.setQuestionContent(question.getQuestionContent());
			String judgeRule=question.getJudgeRule();//命中规则 sss:aa==>[sss,aa]
			String[] ruleArray=judgeRule.split(":");
			try {
				QuestionJudgeRuleService qjr=SpringContextHolder.getBean(ruleArray[0].trim());
				if(ruleArray.length>1){
					dto.setFlag(ruleArray[1].trim());
				}
				bool=qjr.analysisJudgeRule(dto);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return bool;
	}

	@Override
	public Questionnaire findByCaseApplyIdAndQuestionIdAndSceneTypeCode(String caseApplyId, String questionId,String sceneTypeCode) {
		return this.customReposity.findByCaseApplyIdAndQuestionIdAndSceneTypeCode(caseApplyId,questionId,sceneTypeCode);
	}

	@Override
	public List<Questionnaire> findByCaseApplyId(String caseApplyId) {
		return this.customReposity.findByCaseApplyId( caseApplyId);
	}
}
