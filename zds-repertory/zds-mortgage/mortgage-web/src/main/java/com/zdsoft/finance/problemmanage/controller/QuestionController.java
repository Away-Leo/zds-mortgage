package com.zdsoft.finance.problemmanage.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zdsoft.essential.client.service.CED;
import com.zdsoft.essential.dto.basedata.SimpleCodeDto;
import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.finance.problemmanage.entity.Question;
import com.zdsoft.finance.problemmanage.service.QuestionService;
import com.zdsoft.finance.problemmanage.vo.QuestionVo;
import com.zdsoft.framework.core.common.dto.ResponseMsg;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.PageRequest;
import com.zdsoft.framework.core.common.util.ObjectHelper;
import com.zdsoft.framework.core.commweb.annotation.UriKey;
import com.zdsoft.framework.core.commweb.component.BaseController;
import com.zdsoft.framework.cra.annotation.Menu;

/**
 * 问题定义控制器
 * @author longwei
 * @date 2017/01/03
 * @version 1.0
 */
@Controller
@RequestMapping("/question")
public class QuestionController extends BaseController{
                                     
	@Autowired
	private QuestionService questionService;
	
	@Autowired
	private CED CED;
	/**
	 * 问题库定义入口
	 */
	@Menu(resource = "com.zdsoft.finance.question.list", label = "问题库定义", group = "questionManage", order = 1)
	@RequestMapping("/list")
	@UriKey(key = "com.zdsoft.finance.question.list")
	public ModelAndView list(){
		ModelAndView modelAndView=new ModelAndView("question/question_list");
		return modelAndView;
	}
	
	/**
	 * 问题对话框
	 * @throws BusinessException 
	 */
	@RequestMapping("/dialog")
	@UriKey(key = "com.zdsoft.finance.question.dialog")
	public ModelAndView dialog(String questionId) throws BusinessException{
		ModelAndView modelAndView=new ModelAndView("question/question_list_dialog");
		
		if(ObjectHelper.isNotEmpty(questionId)){
			Question question=questionService.findOne(questionId);
			modelAndView.addObject("question", new QuestionVo(question));
		}
		return modelAndView;
	}
	
	/**
	 * 问题库列表
	 */
	@ResponseBody
	@RequestMapping("/paramSimpleCode")
	@UriKey(key="com.zdsoft.finance.question.paramSimpleCode")
	public ResponseMsg paramSimpleCode(String paramCd,PageRequest pageRequest){
		ResponseMsg msg=new ResponseMsg();
		SimpleCodeDto simpleCodeDto=new SimpleCodeDto();
		simpleCodeDto.setCodeCategoryCd("questionParamCd");
		simpleCodeDto.setFullCode(paramCd);
		try {
			List<SimpleCodeDto> list=CED.querySimpleCodeByCondition(simpleCodeDto);
			msg.setRows(list);
			msg.setTotal(Long.valueOf(list.size()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return msg;
	}
	
	/**
	 * 问题库列表
	 */
	@ResponseBody
	@RequestMapping("/getList")
	@UriKey(key="com.zdsoft.finance.question.getList")
	public ResponseMsg getList(QuestionVo questionVo,PageRequest pageRequest){
		ResponseMsg msg=new ResponseMsg();
		Question question=questionVo.toPo();
		try {
			Page<Question> page = questionService.findByPage(question, pageRequest);
			List<QuestionVo> list=new ArrayList<QuestionVo>();
			for(Question questionQuery:page.getRecords()){
				list.add(new QuestionVo(questionQuery));
			}
			msg.setRows(list);
			msg.setTotal(page.getTotalRows());
		} catch (BusinessException e) {
			logger.error("查询问题定义列表失败");
			e.printStackTrace();
			msg.setResultStatus(ResponseMsg.APP_ERROR);
			msg.setMsg("查询失败");
		}
		return msg;
	}
	
	/**
	 * 删除
	 */
	@ResponseBody
	@RequestMapping("/delete")
	@UriKey(key="com.zdsoft.finance.question.delete")
	public ResponseMsg delete(String questionId){
		ResponseMsg msg=new ResponseMsg();
		
		if(ObjectHelper.isEmpty(questionId)){
			logger.error("参数为空");
			msg.setResultStatus(ResponseMsg.APP_ERROR);
			msg.setMsg("参数为空");
			return msg;
		}
		
		try {
			questionService.delete(questionId);
		} catch (BusinessException e) {
			logger.error("删除失败",e);
			e.printStackTrace();
			msg.setResultStatus(ResponseMsg.APP_ERROR);
			msg.setMsg("删除失败");
		}
		return msg;
	}
	
	/**
	 * 保存、修改问题
	 */
	@ResponseBody
	@RequestMapping("/saveOrUpdate")
	@UriKey(key="com.zdsoft.finance.question.saveOrUpdate")
	public ResponseMsg saveOrUpdate(QuestionVo questionVo){
		ResponseMsg msg=new ResponseMsg();
		
		Question question=questionVo.toPo();
		try {
			buildCommonField(question);
			questionService.saveOrUpdate(question);
		} catch (Exception e) {
			logger.error("保存失败",e);
			e.printStackTrace();
			msg.setResultStatus(ResponseMsg.APP_ERROR);
			msg.setMsg("保存失败");
		}
		
		return msg;
	}
	
	/**
	 * 组装对象
	 * @throws Exception 
	 */
	private void buildCommonField(Question question) throws Exception {
		
		if(ObjectHelper.isEmpty(question)){
			logger.error("参数不合法");
			throw new BusinessException("参数不合法");
		}
		
		//ced取得人员
		/*EmpDto empDto=CED.getLoginUser();
		if(ObjectHelper.isEmpty(empDto)){
			logger.error("获取平台资源失败，登录人员为空");
			throw new BusinessException("获取平台资源失败，登录人员为空");
		}
		if(ObjectHelper.isNotEmpty(question.getId())){
			question.setUpdateBy(empDto.getEmpCd());
			question.setUpdateOrgCd(empDto.getOrgCd());
			question.setUpdateTime(new Date());
		}else {
			question.setCreateBy(empDto.getEmpCd());
			question.setCreateOrgCd(empDto.getOrgCd());
			question.setCreateTime(new Date());
		}*/
		
		//组装simpcode
		if(ObjectHelper.isNotEmpty(question.getParamCd())){
			String[] arrParamCd=question.getParamCd().split(",");
			StringBuilder paramNm=new StringBuilder();
			for(int i=0;i<arrParamCd.length;i++){
				String param=CED.loadSimpleCodeNameByFullCode(arrParamCd[i]);
				if(ObjectHelper.isEmpty(param)){
					logger.error("获取平台资源失败，未获取simplecode");
					throw new BusinessException("获取平台资源失败，未获取simplecode");
				}
				if(i==arrParamCd.length-1){
					paramNm.append(param);
				}else{
					paramNm.append(param+",");
				}
			}
			question.setParamNm(paramNm.toString());
		}
		if(ObjectHelper.isNotEmpty(question.getTypeCd())){
			String typeNm=CED.loadSimpleCodeNameByFullCode(question.getTypeCd());
			if(ObjectHelper.isEmpty(typeNm)){
				logger.error("获取平台资源失败，未获取simplecode");
				throw new BusinessException("获取平台资源失败，未获取simplecode");
			}
			question.setTypeNm(typeNm);
		}
		
	}
}
