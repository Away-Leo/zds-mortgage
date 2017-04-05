package com.zdsoft.finance.businesssetting.controller;

import com.zdsoft.essential.client.service.CED;
import com.zdsoft.finance.businesssetting.entity.Question;
import com.zdsoft.finance.businesssetting.service.QuestionService;
import com.zdsoft.finance.businesssetting.vo.QuestionVo;
import com.zdsoft.finance.common.exception.BusinessException;
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
 * @Title: QuestionController.java 
 * @ClassName: QuestionController 
 * @Description: 问题定义控制器
 * @author longwei 
 * @date 2017年2月6日 上午11:10:32 
 * @version V1.0
 */
@Controller
@RequestMapping("/question")
public class QuestionController extends BaseController{
                                     
	@Autowired
	private QuestionService questionService;
	
	@Autowired
	private CED CED;


	/**
	 * @Title: list
	 * @Description: 问题库定义页面
	 * @author liaoguowei
	 * @param modelAndView
	 * @return org.springframework.web.servlet.ModelAndView
	 * @throws
	 */
	@Menu(resource = "com.zdsoft.finance.question.list", label = "问题库定义", group = "businessSetting", order = 1)
	@RequestMapping("/list")
	@UriKey(key = "com.zdsoft.finance.question.list")
	public ModelAndView list(ModelAndView modelAndView){
		modelAndView.setViewName("businesssetting/question_list");
		return modelAndView;
	}


	/**
	 * @Title: getList
	 * @Description: 问题库列表数据
	 * @author liaoguowei
	 * @param questionVo
	 * @param pageRequest
	 * @return com.zdsoft.framework.core.common.dto.ResponseMsg
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(value = "/getList",produces = MediaType.APPLICATION_JSON_UTF8_VALUE/*防止中文乱码*/)
	@UriKey(key="com.zdsoft.finance.question.getList")
	public ResponseMsg getList(QuestionVo questionVo,PageRequest pageRequest){
		ResponseMsg msg=new ResponseMsg();
		try {
			Page<Question> page = questionService.findByPage(questionVo.toPo(), pageRequest);
			List<QuestionVo> list=new ArrayList<QuestionVo>();
			for(Question questionQuery:page.getRecords()){
				QuestionVo vo=new QuestionVo(questionQuery);
				if(ObjectHelper.isNotEmpty(questionQuery.getQuestionTypeCode())){
					String questionTypeCodeName=ObjectHelper.isNotEmpty(CED.loadSimpleCodeById(questionQuery.getQuestionTypeCode()))?CED.loadSimpleCodeById(questionQuery.getQuestionTypeCode()).getName():"";
					vo.setQuestionTypeCodeName(questionTypeCodeName);
				}
				list.add(vo);
			}
			msg.setRows(list);
			msg.setTotal(page.getTotalRows());
		} catch (Exception e) {
			logger.error("查询问题定义列表失败",e);
			e.printStackTrace();
			msg.setResultStatus(ResponseMsg.APP_ERROR);
			msg.setMsg(e.getMessage());
		}
		return msg;
	}

	/**
	 * @Title: delete
	 * @Description: 删除问题库设置
	 * @author liaoguowei
	 * @param questionId
	 * @return com.zdsoft.framework.core.common.dto.ResponseMsg
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(value = "/delete",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@UriKey(key="com.zdsoft.finance.question.delete")
	public ResponseMsg delete(String questionId){
		ResponseMsg msg=new ResponseMsg();
		try {
			questionService.delete(questionId);
			msg.setResultStatus(ResponseMsg.SUCCESS);
		} catch (BusinessException e) {
			logger.error("删除失败",e);
			e.printStackTrace();
			msg.setResultStatus(ResponseMsg.APP_ERROR);
			msg.setMsg(e.getMessage());
		}
		return msg;
	}

	/**
	 * @Title: saveOrUpdate
	 * @Description: 保存以及修改
	 * @author liaoguowei
	 * @param questionVo
	 * @return com.zdsoft.framework.core.common.dto.ResponseMsg
	 * @throws
	 */
	@ResponseBody
	@RequestMapping("/saveOrUpdate")
	@UriKey(key="com.zdsoft.finance.question.saveOrUpdate")
	public ResponseMsg saveOrUpdate(QuestionVo questionVo){
		ResponseMsg msg=new ResponseMsg();
		try {
			this.questionService.saveOrUpdateQuestion(questionVo.toPo());
			msg.setResultStatus(ResponseMsg.SUCCESS);
		} catch (Exception e) {
			logger.error("保存失败",e);
			e.printStackTrace();
			msg.setResultStatus(ResponseMsg.APP_ERROR);
			msg.setMsg(e.getMessage());
		}
		return msg;
	}
	
}
