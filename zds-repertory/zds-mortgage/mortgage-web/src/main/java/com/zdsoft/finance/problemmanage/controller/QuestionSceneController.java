package com.zdsoft.finance.problemmanage.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zdsoft.essential.client.service.CED;
import com.zdsoft.essential.dto.emp.EmpDto;
import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.finance.problemmanage.entity.Question;
import com.zdsoft.finance.problemmanage.entity.QuestionScene;
import com.zdsoft.finance.problemmanage.entity.QuestionSceneQuery;
import com.zdsoft.finance.problemmanage.service.QuestionSceneService;
import com.zdsoft.finance.problemmanage.vo.QuestionSceneQueryVo;
import com.zdsoft.finance.problemmanage.vo.QuestionSceneVo;
import com.zdsoft.framework.core.common.dto.ResponseMsg;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.PageRequest;
import com.zdsoft.framework.core.common.util.ObjectHelper;
import com.zdsoft.framework.core.commweb.annotation.UriKey;
import com.zdsoft.framework.core.commweb.component.BaseController;
import com.zdsoft.framework.cra.annotation.Menu;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 场景设置控制器
 * @author longwei
 * @date 2017/01/03
 * @version 1.0
 */
@Controller
@RequestMapping("/questionScene")
public class QuestionSceneController extends BaseController{

	@Autowired
	private QuestionSceneService questionSceneService;
	@Autowired
	private CED CED;
	
	/**
	 * 场景设置入口
	 */
	@Menu(resource = "com.zdsoft.finance.questionScene.list", label = "场景问题设置", group = "questionManage", order = 2)
	@RequestMapping("/list")
	@UriKey(key = "com.zdsoft.finance.questionScene.list") 
	public ModelAndView list(){
		ModelAndView modelAndView=new ModelAndView("question/question_scene_list");
		return modelAndView;
	}
	
	/**
	 * 场景设置添加
	 */
	@RequestMapping("/add")
	@UriKey(key = "com.zdsoft.finance.questionScene.add") 
	public ModelAndView add(){
		ModelAndView modelAndView=new ModelAndView("question/question_scene_add");
		return modelAndView;
	}
	
	/**
	 * 场景设置对话框
	 * @throws BusinessException 
	 */
	@RequestMapping("/dialog")
	@UriKey(key = "com.zdsoft.finance.questionScene.dialog") 
	public ModelAndView dialog(String jsonStr) throws BusinessException {
		ModelAndView modelAndView=new ModelAndView("question/question_scene_dialog");
		if(ObjectHelper.isEmpty(jsonStr)){
			logger.error("参数为空");
			throw new BusinessException("参数为空");
		}
		modelAndView.addObject("jsonStr", jsonStr);
		return modelAndView;
	}
	
	/**
	 * 场景设置列表
	 */
	@ResponseBody
	@RequestMapping("/getList")
	@UriKey(key = "com.zdsoft.finance.questionScene.getList") 
	public ResponseMsg getList(QuestionSceneQueryVo questionSceneQueryVo,PageRequest pageable){
		ResponseMsg msg=new ResponseMsg();
		QuestionSceneQuery questionSceneQuery = questionSceneQueryVo.toPo();
		try {
			Page<QuestionSceneQuery> page=questionSceneService.findByPage(questionSceneQuery, pageable);
			List<QuestionSceneQueryVo> list=new ArrayList<QuestionSceneQueryVo>();
			for(QuestionSceneQuery query:page.getRecords()){
				list.add(new QuestionSceneQueryVo(query));
			}
			msg.setRows(list);
			msg.setTotal(page.getTotalRows());
		} catch (BusinessException e) {
			logger.error("查询分页失败",e);
			e.printStackTrace();
			msg.setMsg("查询分页失败");
			msg.setResultStatus(ResponseMsg.APP_ERROR);
		}
		return msg;
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@RequestMapping("/saveOrUpdate")
	@UriKey(key = "com.zdsoft.finance.questionScene.saveOrUpdate") 
	public ResponseMsg saveOrUpdate(QuestionSceneVo questionSceneVo,String jsonStr){
		ResponseMsg msg=new ResponseMsg();
		if(ObjectHelper.isEmpty(questionSceneVo) || ObjectHelper.isEmpty(jsonStr)){
			logger.error("参数为空");
			msg.setMsg("参数为空");
			msg.setResultStatus(ResponseMsg.APP_ERROR);
			return msg;
		}
		QuestionScene questionScene=questionSceneVo.toPo();
		List<Question> list=null;
		try {
			buildCommonField(questionScene);
			list=expressionJson(jsonStr);
		} catch (Exception e) {
			logger.error("构建对象失败",e);
			e.printStackTrace();
			msg.setMsg("构建对象失败");
			msg.setResultStatus(ResponseMsg.APP_ERROR);
			return msg;
		}
		try {
			questionSceneService.saveOrUpdate(questionScene,list);
		} catch (BusinessException e) {
			logger.error("保存失败",e);
			e.printStackTrace();
			msg.setMsg("保存失败");
			msg.setResultStatus(ResponseMsg.APP_ERROR);
		}
		return msg;
	}
	
	/**
	 * 删除
	 */
	@ResponseBody
	@RequestMapping("/delete")
	@UriKey(key = "com.zdsoft.finance.questionScene.delete") 
	public ResponseMsg delete(String questionSceneId){
		ResponseMsg msg=new ResponseMsg();
		if(ObjectHelper.isEmpty(questionSceneId)){
			logger.error("参数为空");
			msg.setMsg("参数为空");
			msg.setResultStatus(ResponseMsg.APP_ERROR);
			return msg;
		}
		try {
			QuestionScene questionScene=questionSceneService.findOne(questionSceneId);
			if(ObjectHelper.isEmpty(questionScene)){
				logger.error("对象不存在");
				msg.setMsg("对象不存在");
				msg.setResultStatus(ResponseMsg.APP_ERROR);
				return msg;
			}
			questionSceneService.logicDelete(questionScene);
		} catch (BusinessException e) {
			logger.error("删除失败");
			e.printStackTrace();
			msg.setMsg("删除失败");
			msg.setResultStatus(ResponseMsg.APP_ERROR);
		}
		return msg;
	}
	
	private List<Question> expressionJson(String jsonStr) throws BusinessException {
		List<Question> list=new ArrayList<Question>();
		JSONArray json=JSONArray.fromObject(jsonStr);
		for(int i=0;i<json.size();i++){
			JSONObject jsonObject=json.getJSONObject(i);
			Object id=jsonObject.get("id");
			if(ObjectHelper.isEmpty(id)){
				logger.error("id为空");
				throw new BusinessException("id为空");
			}
			Question question=new Question();
			question.setId(id.toString());
			list.add(question);
		}
		
		return list;
	}
	
	private void buildCommonField(QuestionScene questionScene) throws Exception{
		
		EmpDto empDto=CED.getLoginUser();
		if(ObjectHelper.isEmpty(empDto)){
			logger.error("获取平台资源失败，未获取到当前登录人员");
			throw new BusinessException("获取平台资源失败，未获取到当前登录人员");
		}
		if(ObjectHelper.isEmpty(questionScene.getId())){
			questionScene.setCreateBy(empDto.getEmpCd());
			questionScene.setCreateOrgCd(empDto.getOrgCd());
			questionScene.setCreateTime(new Date());
		}else{
			questionScene.setUpdateBy(empDto.getEmpCd());
			questionScene.setUpdateOrgCd(empDto.getOrgCd());
			questionScene.setUpdateTime(new Date());
		}
		
		if(ObjectHelper.isNotEmpty(questionScene.getSceneCd())){
			String sceneNm=CED.loadSimpleCodeNameByFullCode(questionScene.getSceneCd());
			if(ObjectHelper.isEmpty(sceneNm)){
				logger.error("获取平台资源失败，解析simpcode失败");
				throw new BusinessException("获取平台资源失败，解析simpcode失败");
			}
			questionScene.setSceneNm(sceneNm);
		}
	}
}
