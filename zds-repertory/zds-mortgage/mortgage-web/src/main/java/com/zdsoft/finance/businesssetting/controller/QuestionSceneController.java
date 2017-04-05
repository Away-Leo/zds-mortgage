package com.zdsoft.finance.businesssetting.controller;

import com.zdsoft.finance.businesssetting.entity.Question;
import com.zdsoft.finance.businesssetting.entity.QuestionScene;
import com.zdsoft.finance.businesssetting.service.QuestionSceneService;
import com.zdsoft.finance.businesssetting.vo.QuestionSceneVo;
import com.zdsoft.framework.core.common.dto.ResponseMsg;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.PageRequest;
import com.zdsoft.framework.core.common.util.ObjectHelper;
import com.zdsoft.framework.core.commweb.annotation.UriKey;
import com.zdsoft.framework.core.commweb.component.BaseController;
import com.zdsoft.framework.cra.annotation.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title: QuestionSceneController.java 
 * @ClassName: QuestionSceneController 
 * @Description: 场景设置控制器
 * @author longwei 
 * @date 2017年2月6日 上午11:12:30 
 * @version V1.0
 */
@Controller
@RequestMapping("/questionScene")
public class QuestionSceneController extends BaseController{

	@Autowired
	private QuestionSceneService questionSceneService;

	/**
	 * @Title: list
	 * @Description: 场景设置入口
	 * @author liaoguowei
	 * @param
	 * @return org.springframework.web.servlet.ModelAndView
	 * @throws
	 */
	@Menu(resource = "com.zdsoft.finance.questionScene.list", label = "场景问题设置", group = "businessSetting", order = 2)
	@RequestMapping("/list")
	@UriKey(key = "com.zdsoft.finance.questionScene.list") 
	public ModelAndView list(){
		ModelAndView modelAndView=new ModelAndView("businesssetting/question_scene_list");
		return modelAndView;
	}

	/**
	 * @Title: add
	 * @Description: 场景设置添加
	 * @author liaoguowei
	 * @param
	 * @return org.springframework.web.servlet.ModelAndView
	 * @throws
	 */
	@RequestMapping("/add")
	@UriKey(key = "com.zdsoft.finance.questionScene.add") 
	public ModelAndView add(){
		ModelAndView modelAndView=new ModelAndView("businesssetting/question_scene_add");
		return modelAndView;
	}

	/**
	 * @Title: dialog
	 * @Description: 场景设置对话框
	 * @author liaoguowei
	 * @param jsonStr
	 * @return org.springframework.web.servlet.ModelAndView
	 * @throws
	 */
	@RequestMapping("/dialog")
	@UriKey(key = "com.zdsoft.finance.questionScene.dialog") 
	public ModelAndView dialog(String jsonStr) {
		ModelAndView modelAndView=new ModelAndView("businesssetting/question_scene_dialog");
		if(ObjectHelper.isEmpty(jsonStr)){
			logger.error("场景设置对话框参数为空");
		}
		modelAndView.addObject("jsonStr", jsonStr);
		return modelAndView;
	}

	/**
	 * @Title: getList
	 * @Description: 场景设置列表
	 * @author liaoguowei
	 * @param questionSceneVo
	 * @param pageable
	 * @return com.zdsoft.framework.core.common.dto.ResponseMsg
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(value = "/getList",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@UriKey(key = "com.zdsoft.finance.questionScene.getList") 
	public ResponseMsg getList(QuestionSceneVo questionSceneVo,PageRequest pageable){
		ResponseMsg msg=new ResponseMsg();
		try {
			Page<QuestionScene> page=questionSceneService.findQuestionSceneByPage(questionSceneVo.toPo(), pageable);
			List<QuestionSceneVo> list=new ArrayList<QuestionSceneVo>();
			for(QuestionScene temp:page.getRecords()){
				QuestionSceneVo vo=new QuestionSceneVo(temp);
				list.add(vo);
			}
			msg.setRows(list);
			msg.setTotal(page.getTotalRows());
		} catch (Exception e) {
			logger.error("查询分页失败",e);
			e.printStackTrace();
			msg.setMsg(e.getMessage());
			msg.setResultStatus(ResponseMsg.APP_ERROR);
		}
		return msg;
	}

	/**
	 * @Title: saveOrUpdate
	 * @Description: 保存
	 * @author liaoguowei
	 * @param ids
	 * @param sceneTypeCode
	 * @return com.zdsoft.framework.core.common.dto.ResponseMsg
	 * @throws
	 */
	@ResponseBody
	@RequestMapping("/saveOrUpdate")
	@UriKey(key = "com.zdsoft.finance.questionScene.saveOrUpdate") 
	public ResponseMsg saveOrUpdate(String ids,String sceneTypeCode,String sceneQuestionType){
		ResponseMsg msg=new ResponseMsg();
		try{
			List<Question> exsitData=this.questionSceneService.findByQuestionIdAndSceneTypeCode(ids, sceneTypeCode);
			if(ObjectHelper.isNotEmpty(exsitData)&&exsitData.size()>0){
				msg.setResultStatus(ResponseMsg.APP_ERROR);
				msg.setRows(exsitData);
			}else{
				this.questionSceneService.saveQuestionScene(ids, sceneTypeCode,sceneQuestionType);
				msg.setResultStatus(ResponseMsg.SUCCESS);
			}
		}catch (Exception e){
			logger.error("保存场景问题设置出错",e);
			e.printStackTrace();
			msg.setMsg(e.getMessage());
			msg.setResultStatus(ResponseMsg.SYS_ERROR);
		}
		return msg;
	}

	/**
	 * @Title: delete
	 * @Description: 删除
	 * @author liaoguowei
	 * @param questionSceneId
	 * @return com.zdsoft.framework.core.common.dto.ResponseMsg
	 * @throws
	 */
	@ResponseBody
	@RequestMapping("/delete")
	@UriKey(key = "com.zdsoft.finance.questionScene.delete") 
	public ResponseMsg delete(String questionSceneId){
		ResponseMsg msg=new ResponseMsg();
		try{
			this.questionSceneService.logicDeleteQuestionScene(questionSceneId);
			msg.setResultStatus(ResponseMsg.SUCCESS);
		}catch (Exception e){
			logger.error("删除场景问题设置出错",e);
			e.printStackTrace();
			msg.setMsg(e.getMessage());
			msg.setResultStatus(ResponseMsg.APP_ERROR);
		}
		return msg;
	}
	
}
