package com.zdsoft.finance.app.question.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zdsoft.finance.businesssetting.entity.Question;
import com.zdsoft.finance.businesssetting.entity.QuestionScene;
import com.zdsoft.finance.businesssetting.entity.Questionnaire;
import com.zdsoft.finance.businesssetting.service.QuestionSceneService;
import com.zdsoft.finance.businesssetting.service.QuestionService;
import com.zdsoft.finance.businesssetting.service.QuestionnaireService;
import com.zdsoft.finance.businesssetting.vo.QuestionnaireVo;
import com.zdsoft.finance.common.utils.AppStatus;
import com.zdsoft.finance.common.utils.app.AppServerUtil;
import com.zdsoft.finance.customer.entity.BeforeCustomer;
import com.zdsoft.finance.customer.entity.BeforePersonalAssociation;
import com.zdsoft.finance.customer.entity.BeforePersonalCustomer;
import com.zdsoft.finance.customer.service.BeforePersonalAssociationService;
import com.zdsoft.finance.customer.service.BeforePersonalCustomerService;
import com.zdsoft.finance.marketing.entity.CaseApplyBeforeCustomer;
import com.zdsoft.finance.marketing.service.CaseApplyBeforeCustomerService;
import com.zdsoft.finance.spi.businesssetting.QuestionnaireListDto;
import com.zdsoft.framework.core.common.util.ObjectHelper;
import com.zdsoft.framework.core.commweb.annotation.UriKey;
import com.zdsoft.framework.core.commweb.component.BaseController;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title: IntelligenceQuestionController.java 
 * @ClassName: IntelligenceQuestionController 
 * @Description: APP智能问卷Controller
 * @author jincheng 
 * @date 2017年3月3日 上午9:57:33 
 * @version V1.0
 */
@Controller
@RequestMapping("/server/question")
public class IntelligenceQuestionController extends BaseController {
	
	@Autowired
	private QuestionnaireService questionnaireService;
	
	@Autowired
	private QuestionSceneService questionSceneService;
	
	@Autowired
	private QuestionService  questionService;
	
	@Autowired
	private CaseApplyBeforeCustomerService caseApplyBeforeCustomerService;
	
	@Autowired
	private BeforePersonalCustomerService  beforePersonalCustomerService;
	
	@Autowired
	private BeforePersonalAssociationService  beforePersonalAssociationService;

	/**
	 * @Title: findIntelligenceQuestion 
	 * @Description: 获取智能问卷题库
	 * @author jincheng  
	 * @param caseApplyId  案件id
	 * @param sceneTypeCode 问题场景类型(资调YWDM0010202、面签YWDM0010203)
	 * @param token
	 * @param phoneType 手机类型
	 * @return
	 */
	@RequestMapping(value = "/findIntelligenceQuestion")
//	@UriKey(key = "com.zdsoft.finance.app.findIntelligenceQuestion")
	@ResponseBody
	public ModelAndView findIntelligenceQuestion(String caseApplyId,String sceneTypeCode, String token,String phoneType) {
		ModelMap model = new ModelMap(); 
		String viewPage="/businesssetting/questionnaire_app_edit";
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
			
			QuestionnaireListDto dto  = questionnaireService.getQuestionnaireList(sceneTypeCode,caseApplyId);
			
//		    AccountDTO account = CRA.getAccount(token);
//			EmpDto loginUser = CED.getLoginUser(account.getId());
			model.put("token", token);
			model.put("phoneType", phoneType);
			model.put("caseApplyId", caseApplyId);
			model.put("sceneTypeCode", sceneTypeCode);
			model.put("question", ObjectHelper.objectToJson(dto));
		 
		} catch (Exception e) {
			e.printStackTrace();
		}
//           return AppServerUtil.buildJsonObject(AppStatus.Succeed, rtMap);
		   return new ModelAndView(viewPage, model);
	}
	
	
	/**
	 * @Title: saveQuestionnaire 
	 * @Description: 保存智能问卷答案
	 * @author jincheng 
	 * @param caseApplyId
	 * @param sceneTypeCode
	 * @param questionList
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/saveQuestionnaire")
	@UriKey(key = "com.zdsoft.finance.app.saveQuestionnaire")
	public String saveQuestionnaire(String caseApplyId,String sceneTypeCode,String questionList, String token) {
		try{
			questionnaireService.saveOrUpdateAppQuestionnaire(caseApplyId,sceneTypeCode,questionList);
			return AppServerUtil.buildError(AppStatus.Succeed, "保存成功");
		}catch (Exception e) {
			e.printStackTrace();
			return AppServerUtil.buildError(AppStatus.SystemError, "保存失败");
		}
	}
	
	/**
	 * @Title: questionnaireView 
	 * @Description: 获取智能问卷资调、面签答案对比
	 * @author jincheng 
	 * @param caseApplyId
	 * @param sceneTypeCode
	 * @return
	 */
	@RequestMapping(value = "/questionnaireView")
	@UriKey(key = "com.zdsoft.finance.app.questionnaireView")
	@ResponseBody
	public String questionnaireView(String caseApplyId,String sceneTypeCode) {
			List<String> paramList=new ArrayList<String>();
			paramList.add("YWDM0010202");
			paramList.add("YWDM0010203");
			List<QuestionScene> qssList=questionSceneService.findBySceneTypeCodeIn(paramList); 
			List<QuestionnaireVo> voList=new  ArrayList<QuestionnaireVo>();
			for(QuestionScene qss:qssList){
				Question qs=questionService.findQuestionById(qss.getQuestionId()); 
				QuestionnaireVo vo=new QuestionnaireVo();
				BeanUtils.copyProperties(qss, vo);
				BeanUtils.copyProperties(qs, vo);
				Questionnaire qn1=questionnaireService.findByCaseApplyIdAndQuestionIdAndSceneTypeCode(caseApplyId, qss.getQuestionId(),"YWDM0010202");//资调
				if(ObjectHelper.isNotEmpty(qn1)){
					vo.setCapitalResult(qn1.getQuestionValue());
				}
				Questionnaire qn2=questionnaireService.findByCaseApplyIdAndQuestionIdAndSceneTypeCode(caseApplyId, qss.getQuestionId(),"YWDM0010203");//面签
				if(ObjectHelper.isNotEmpty(qn2)){
					vo.setSignResult(qn2.getQuestionValue());
				}
				if(ObjectHelper.isNotEmpty(vo.getCapitalResult())&&ObjectHelper.isNotEmpty(vo.getSignResult())&&vo.getCapitalResult().equals(vo.getSignResult())){
					vo.setSame(true);
				}
				voList.add(vo);
			}
		return AppServerUtil.buildJsonList(voList);
	}
	
}
