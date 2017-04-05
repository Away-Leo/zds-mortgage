package com.zdsoft.finance.businesssetting.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zdsoft.finance.businesssetting.entity.Questionnaire;
import com.zdsoft.finance.businesssetting.service.QuestionnaireService;
import com.zdsoft.finance.businesssetting.vo.QuestionnaireVo;
import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.finance.customer.entity.BeforeCustomer;
import com.zdsoft.finance.customer.entity.BeforePersonalAssociation;
import com.zdsoft.finance.customer.entity.BeforePersonalCustomer;
import com.zdsoft.finance.customer.service.BeforePersonalAssociationService;
import com.zdsoft.finance.customer.service.BeforePersonalCustomerService;
import com.zdsoft.finance.marketing.entity.CaseApplyBeforeCustomer;
import com.zdsoft.finance.marketing.service.CaseApplyBeforeCustomerService;
import com.zdsoft.finance.spi.businesssetting.QuestionnaireListDto;
import com.zdsoft.framework.core.common.dto.ResponseMsg;
import com.zdsoft.framework.core.common.dto.ResultDto;
import com.zdsoft.framework.core.common.page.PageRequest;
import com.zdsoft.framework.core.common.util.ObjectHelper;
import com.zdsoft.framework.core.commweb.annotation.UriKey;
import com.zdsoft.framework.core.commweb.component.BaseController;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title: QuestionnaireController.java 
 * @ClassName: QuestionnaireController 
 * @Description: 智能问卷答题结果Controller 
 * @author jincheng 
 * @date 2017年2月22日 下午4:56:56 
 * @version V1.0
 */
@Controller
@RequestMapping("/questionnaire")
public class QuestionnaireController extends BaseController{
	
	@Autowired
	private QuestionnaireService questionnaireService;
	
	@Autowired
	private CaseApplyBeforeCustomerService caseApplyBeforeCustomerService;
	
	@Autowired
	private BeforePersonalCustomerService  beforePersonalCustomerService;
	
	@Autowired
	private BeforePersonalAssociationService  beforePersonalAssociationService;
	
	/**
	 * @Title: questionnairePage 
	 * @Description: 智能问卷
	 * @author jincheng 
	 * @param caseApplyId
	 * @param sceneTypeCode  --YWDM0010202
	 * @return
	 */
	@RequestMapping(value = "/questionnairePage")
	@UriKey(key = "com.zdsoft.finance.question.questionnairePage")
	public ModelAndView questionnairePage(@RequestParam("caseApplyId")String caseApplyId,@RequestParam("sceneTypeCode")String sceneTypeCode) {
		ModelMap model = new ModelMap();
		String viewPage="/businesssetting/questionnaire_edit";
		try {
			String main_picture_id="";
			String spouse_picture_id="";
			List<BeforeCustomer> bcList=caseApplyBeforeCustomerService.findCustomerByCaseApplyIdAndJoinType(caseApplyId, CaseApplyBeforeCustomer.MAIN_BORROW);
			if(ObjectHelper.isNotEmpty(bcList)){
				BeforePersonalCustomer bpc=beforePersonalCustomerService.findById(bcList.get(0).getId());
				main_picture_id=bpc.getAttachmentId();
				List<BeforePersonalAssociation> bpaList=beforePersonalAssociationService.findByRelationshipAndCustomerId(BeforePersonalAssociation.SPOUSE, bpc.getId());
				if(ObjectHelper.isNotEmpty(bpaList)){
					BeforePersonalCustomer bpcx=beforePersonalCustomerService.findById(bpaList.get(0).getId());
					if(ObjectHelper.isNotEmpty(bpcx)){
						spouse_picture_id=bpcx.getAttachmentId();
					}
				}
			}
			model.put("main_picture_id", main_picture_id);
			model.put("spouse_picture_id", spouse_picture_id);
			
			List<Questionnaire> list=questionnaireService.findByCaseApplyIdAndSceneTypeCode(caseApplyId, sceneTypeCode);
			if(ObjectHelper.isNotEmpty(list)&&list.size()>0){
				viewPage="/businesssetting/questionnaire_view";
				model.put("caseApplyId", caseApplyId);
				model.put("sceneTypeCode", sceneTypeCode);
			}else{
				QuestionnaireListDto dto = questionnaireService.getQuestionnaireList(sceneTypeCode,caseApplyId);
				model.put("caseApplyId", caseApplyId);
				model.put("sceneTypeCode", sceneTypeCode);
				model.put("question", ObjectHelper.objectToJson(dto));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView(viewPage, model);
	}
	
	/**
	 * @Title: editQuestionnaire 
	 * @Description: 流程中现场问卷
	 * @author zhoushichao 
	 * @param caseApplyId  案件Id
	 * @param sceneTypeCode  --YWDM0010202
	 * @return
	 */
	@RequestMapping(value = "/editQuestionnaire")
	@UriKey(key = "com.zdsoft.finance.question.editQuestionnaire")
	public ModelAndView editQuestionnaire(@RequestParam("caseApplyId")String caseApplyId,@RequestParam("sceneTypeCode")String sceneTypeCode) {
		ModelMap model = new ModelMap();
		String viewPage="casemanage/datasurvey/questionnaire_process_edit";
		try {
			
			List<Questionnaire> list=questionnaireService.findByCaseApplyIdAndSceneTypeCode(caseApplyId, sceneTypeCode);
			if(ObjectHelper.isNotEmpty(list)&&list.size()>0){
				viewPage="/businesssetting/questionnaire_view";
				model.put("sceneTypeCode", sceneTypeCode);
				model.put("caseApplyId", caseApplyId);
			}else{
				QuestionnaireListDto dto = questionnaireService.getQuestionnaireList(sceneTypeCode,caseApplyId);
				model.put("question", ObjectHelper.objectToJson(dto));
				model.put("sceneTypeCode", sceneTypeCode);
				model.put("caseApplyId", caseApplyId);
				logger.error("QuestionnaireController问卷题库{}",ObjectHelper.objectToJson(dto));
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("编辑智能问卷出错",e);
		}
		return new ModelAndView(viewPage, model);
	}
	
	/**
	 * @Title: saveQuestionnaire 
	 * @Description: 保存智能问卷
	 * @author jincheng 
	 * @param caseApplyId
	 * @param sceneTypeCode
	 * @param questionList
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/saveQuestionnaire")
	@UriKey(key = "com.zdsoft.finance.question.saveQuestionnaire")
	public ResponseMsg saveQuestionnaire(String caseApplyId,String sceneTypeCode,String questionList) {
		ResponseMsg msg = new ResponseMsg();
		try{
			questionnaireService.saveOrUpdateQuestionnaire(caseApplyId,sceneTypeCode,questionList);
			msg.setResultStatus(ResultDto.SUCCESS);
			msg.setMsg("保存成功");
		}catch (Exception e) {
			e.printStackTrace();
			msg.setResultStatus(ResultDto.APP_ERROR);
			msg.setMsg("保存失败");
		}
		return msg;
	}
	
	/**
	 * @Title: questionnaireView 
	 * @Description: 智能问卷结果页面
	 * @author jincheng 
	 * @param caseApplyId
	 * @param sceneTypeCode
	 * @param isSame
	 * @return
	 */
	@RequestMapping(value = "/questionnaireView")
	@UriKey(key = "com.zdsoft.finance.question.questionnaireView")
	public ModelAndView questionnaireView(@RequestParam("caseApplyId")String caseApplyId,@RequestParam("sceneTypeCode")String sceneTypeCode,boolean isSame) {
		ModelMap model = new ModelMap();
		try {
			String main_picture_id="";
			String spouse_picture_id="";
			List<BeforeCustomer> bcList=caseApplyBeforeCustomerService.findCustomerByCaseApplyIdAndJoinType(caseApplyId, CaseApplyBeforeCustomer.MAIN_BORROW);
			if(ObjectHelper.isNotEmpty(bcList)){
				BeforePersonalCustomer bpc=beforePersonalCustomerService.findById(bcList.get(0).getId());
				main_picture_id=bpc.getAttachmentId();
				List<BeforePersonalAssociation> bpaList=beforePersonalAssociationService.findByRelationshipAndCustomerId(BeforePersonalAssociation.SPOUSE, bpc.getId());
				if(ObjectHelper.isNotEmpty(bpaList)){
					BeforePersonalCustomer bpcx=beforePersonalCustomerService.findById(bpaList.get(0).getId());
					if(ObjectHelper.isNotEmpty(bpcx)){
						spouse_picture_id=bpcx.getAttachmentId();
					}
				}
			}
			model.put("main_picture_id", main_picture_id);
			model.put("spouse_picture_id", spouse_picture_id);
			
			List<QuestionnaireVo> voList = this.warpperQuestionnarie(caseApplyId,isSame);
			model.put("questionList", voList);
			model.put("sceneTypeCode", sceneTypeCode);
			model.put("caseApplyId", caseApplyId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("/businesssetting/questionnaire_view", model);
	}
	
	/**
	 * @Title: getQuestionnaireList 
	 * @Description: 获取问卷答题答案
	 * @author jincheng 
	 * @param request
	 * @param pageRequest
	 * @param caseApplyId
	 * @param sceneTypeCode
	 * @param isSame
	 * @return
	 */
	@RequestMapping(value = "/getQuestionnaireList")
	@UriKey(key = "com.zdsoft.finance.question.getQuestionnaireList")
	@ResponseBody
	public ResponseMsg getQuestionnaireList(HttpServletRequest request,PageRequest pageRequest,String caseApplyId,String sceneTypeCode,boolean isSame) {
		ResponseMsg msg = new ResponseMsg();
		try {
			if("YWDM0010203".equals(sceneTypeCode)){
				List<QuestionnaireVo> voList = this.warpperQuestionnarie(caseApplyId,isSame);
				msg.setRows(voList);
				msg.setTotal(Long.parseLong(""+voList.size()));
			}else{
				List<Questionnaire>	voList=questionnaireService.findByCaseApplyIdAndSceneTypeCode(caseApplyId,sceneTypeCode);
				msg.setRows(voList);
				msg.setTotal(Long.parseLong(""+voList.size()));
			}
			msg.setResultStatus(ResponseMsg.SUCCESS);
		} catch (BusinessException e) {
			e.printStackTrace();
		}
    return msg;
	}
	
	/**
	 * @Title: warpperQuestionnarie 
	 * @Description: 封装智能问卷答案
	 * @author jincheng 
	 * @param caseApplyId
	 * @return
	 * @throws BusinessException
	 */
	private List<QuestionnaireVo> warpperQuestionnarie(String caseApplyId,boolean isSame) throws BusinessException {
			List<QuestionnaireVo> voList=new  ArrayList<QuestionnaireVo>();
			List<Questionnaire> qnList1=questionnaireService.findByCaseApplyIdAndSceneTypeCode(caseApplyId,"YWDM0010202");//资调
			List<Questionnaire> qnList2=questionnaireService.findByCaseApplyIdAndSceneTypeCode(caseApplyId,"YWDM0010203");//面签
			
			for(Questionnaire qn1:qnList1){//资调答题
				QuestionnaireVo vo1=new QuestionnaireVo();
				BeanUtils.copyProperties(qn1, vo1,new String[]{"isSame","capitalResult","signResult"});
				vo1.setCapitalResult(qn1.getQuestionValue());
				voList.add(vo1);
			}
			
			for(Questionnaire qn2:qnList2){//面签答题
				QuestionnaireVo vo2=new QuestionnaireVo();
				BeanUtils.copyProperties(qn2, vo2,new String[]{"isSame","capitalResult","signResult"});
				vo2.setSignResult(qn2.getQuestionValue());
				if(!this.judegeSame(qn2,voList)){
					voList.add(vo2);
				}
			}
			
			if(isSame){//相同问题
				List<QuestionnaireVo> voList2=new  ArrayList<QuestionnaireVo>();
				for(QuestionnaireVo vo:voList){
					if(vo.isSame()){
						voList2.add(vo);
					}
				}
				voList.clear();
				voList.addAll(voList2);
			}
		return voList;
	}
	
	/**
	 * @Title: judegeSame 
	 * @Description: 判断问题是否在其他问题中答过
	 * @author jincheng 
	 * @param questionId
	 * @param qnList
	 * @return
	 */
	private boolean judegeSame(Questionnaire qn, List<QuestionnaireVo> qnVoList) {
		boolean bool=false;
		for(QuestionnaireVo qnVo:qnVoList){
			if(qn.getQuestionId().equals(qnVo.getQuestionId())){
				qnVo.setSame(true);
				if(!qn.getQuestionValue().equals(qnVo.getCapitalResult())){
					qnVo.setResult(false);
				}
				qnVo.setSignResult(qn.getQuestionValue());
				bool=true;
			}
		}
		return bool;
	}
}
