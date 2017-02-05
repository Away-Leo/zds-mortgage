package com.zdsoft.finance.cooperator.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zdsoft.finance.common.base.QueryObj;
import com.zdsoft.finance.cooperator.entity.EvaluateCompanyRule;
import com.zdsoft.finance.cooperator.service.EvaluateCompanyRuleService;
import com.zdsoft.finance.cooperator.vo.EvaluateCompanyRuleVo;
import com.zdsoft.framework.core.common.dto.ResponseMsg;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.PageRequest;
import com.zdsoft.framework.core.common.util.ObjectHelper;
import com.zdsoft.framework.core.commweb.annotation.UriKey;
import com.zdsoft.framework.core.commweb.component.BaseController;

/**
 * 评估公司权重
 * @author zhangchao
 *
 */
@Controller
@RequestMapping("/evaluateCompanyRule")
public class EvaluateCompanyRuleController extends BaseController {

	@Autowired
	EvaluateCompanyRuleService evaluateCompanyRuleService;
	
	@Autowired
	com.zdsoft.essential.client.service.CED CED;
	
	/**
	 * 评估公司权重注册菜单
	 * @return 客户管理页面
	 */
	@RequestMapping("/initEvaluateCompanyRule")
	@UriKey(key = "com.zdsoft.finance.evaluateCompanyRule.initEvaluateCompanyRule")
	//@Menu(resource = "com.zdsoft.finance.evaluateCompanyRule.initEvaluateCompanyRule", label = "评估公司权重", group = "cooperator", order = 1)
	public ModelAndView initEvaluateCompanyRule() {
		return new ModelAndView("/cooperator/companyrule_list");
	}
	
	/**
	 * 评估公司权重列表
	 * @param request
	 * @param jsoncallback
	 * @param pageable
	 * @return
	 */
	@RequestMapping("/getEvaluateCompanyRule")
	@UriKey(key = "com.zdsoft.finance.evaluateCompanyRule.getEvaluateCompanyRule")
	@ResponseBody
	public String getEvaluateCompanyRule(HttpServletRequest request, String jsoncallback, PageRequest pageable) {

		// 获取页面封装的查询参数 
		@SuppressWarnings("unchecked")
		List<QueryObj> queryObjs = (List<QueryObj>) request.getAttribute("listObj");

		// 分页评估公司权重
		Page<EvaluateCompanyRule> evaluateCompanyRules = evaluateCompanyRuleService.findByHqlConditions(pageable, queryObjs);
		List<EvaluateCompanyRuleVo> evaluateCompanyRuleVos = new ArrayList<EvaluateCompanyRuleVo>();
		for (EvaluateCompanyRule evaluateCompanyRule : evaluateCompanyRules.getRecords()) {
			EvaluateCompanyRuleVo evaluateCompanyRuleVo = new EvaluateCompanyRuleVo(evaluateCompanyRule);
			//组装城市数据
			String city = evaluateCompanyRuleVo.getProvinceName()+","+evaluateCompanyRuleVo.getCityName()+","+evaluateCompanyRuleVo.getDistrictName();
			evaluateCompanyRuleVo.setCity(city);
			evaluateCompanyRuleVos.add(evaluateCompanyRuleVo);
		}
		
		//页面返回值组装
		ResponseMsg msg = new ResponseMsg();
		msg.setMsg("列表查询成功");
		msg.setResultStatus(ResponseMsg.SUCCESS);
		msg.setTotal(evaluateCompanyRules.getTotalRows());
		msg.setRows(evaluateCompanyRuleVos);

		return ObjectHelper.objectToJson(msg, jsoncallback);
	}
	
	/**
	 * 新增评估公司权重
	 * @return
	 */
	@RequestMapping("/addEvaluateCompanyRule")
	@UriKey(key = "com.zdsoft.finance.evaluateCompanyRule.addEvaluateCompanyRule")
	@ResponseBody
	public ModelAndView addEvaluateCompanyRule() {
		return new ModelAndView("/cooperator/companyrule_add");
	}
	
	/**
	 * 删除评估公司权重
	 * @param id 评估公司权重id
	 * @return
	 */
	@RequestMapping("/delEvaluateCompanyRule")
	@UriKey(key = "com.zdsoft.finance.evaluateCompanyRule.delEvaluateCompanyRule")
	@ResponseBody
	public ResponseMsg delEvaluateCompanyRule(String id) {
		ResponseMsg msg = new ResponseMsg();

		// 执行删除
		try {
			EvaluateCompanyRule evaluateCompanyRule = evaluateCompanyRuleService.findOne(id);
			evaluateCompanyRuleService.logicDelete(evaluateCompanyRule);
			msg.setResultStatus(ResponseMsg.SUCCESS);
			msg.setMsg("删除成功");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("evaluateCompanyRule删除失败", e);
			msg.setResultStatus(ResponseMsg.APP_ERROR);
			msg.setMsg("系统内部错误，请查看日志");
		}
		return msg;
	}
	
	/**
	 * 评估公司权重查看
	 * @param id 评估公司权重ID
	 * @param jsoncallback
	 * @return
	 */
	@RequestMapping("/findEvaluateCompanyRuleById")
	@UriKey(key = "com.zdsoft.finance.evaluateCompanyRule.findEvaluateCompanyRuleById")
	@ResponseBody
	public ModelAndView findEvaluateCompanyRuleById(String id, String jsoncallback) {
		Map<String, Object> optional = new HashMap<String, Object>();
		EvaluateCompanyRule evaluateCompanyRule = null;
		EvaluateCompanyRuleVo evaluateCompanyRuleVo = null;

		// 查询
		try {
			evaluateCompanyRule = evaluateCompanyRuleService.findOne(id);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("evaluateCompanyRule查询失败", e);
		}
		
		//评估公司权重数据组装
		if(ObjectHelper.isNotEmpty(evaluateCompanyRule)){
			evaluateCompanyRuleVo = new EvaluateCompanyRuleVo(evaluateCompanyRule);
			String city = evaluateCompanyRuleVo.getProvinceName()+","+evaluateCompanyRuleVo.getCityName()+","+evaluateCompanyRuleVo.getDistrictName();
			evaluateCompanyRuleVo.setCity(city);
		}else{
			logger.error("evaluateCompanyRule查询结果为空");
		}
		
		optional.put("evaluateCompanyRuleVo", evaluateCompanyRuleVo);
		return new ModelAndView("/cooperator/companyrule_view", optional);
	}
	
	/**
	 * 评估公司权重编辑
	 * @param id 评估公司权重ID
	 * @param jsoncallback
	 * @return
	 */
	@RequestMapping("/editEvaluateCompanyRule")
	@UriKey(key = "com.zdsoft.finance.evaluateCompanyRule.editEvaluateCompanyRule")
	@ResponseBody
	public ModelAndView editEvaluateCompanyRule(String id, String jsoncallback) {
		Map<String, Object> optional = new HashMap<String, Object>();
		EvaluateCompanyRule evaluateCompanyRule = null;
		EvaluateCompanyRuleVo evaluateCompanyRuleVo = null;

		// 查询
		try {
			evaluateCompanyRule = evaluateCompanyRuleService.findOne(id);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("latestCustomer查询失败", e);
		}
		
		//评估公司权重数据组装
		if(ObjectHelper.isNotEmpty(evaluateCompanyRule)){
			evaluateCompanyRuleVo = new EvaluateCompanyRuleVo(evaluateCompanyRule);
			String city = evaluateCompanyRuleVo.getProvinceName()+","+evaluateCompanyRuleVo.getCityName()+","+evaluateCompanyRuleVo.getDistrictName();
			evaluateCompanyRuleVo.setCity(city);
		}else{
			logger.error("latestCustomer查询结果为空");
		}
		
		optional.put("evaluateCompanyRuleVo", evaluateCompanyRuleVo);
		return new ModelAndView("/cooperator/companyrule_edit", optional);
	}
	
	/**
	 * 保存评估公司权重
	 * @param otherCooperaterVo 评估公司权重对象
	 * @return
	 */
	@RequestMapping("/saveEvaluateCompanyRule")
	@UriKey(key = "com.zdsoft.finance.evaluateCompanyRule.saveEvaluateCompanyRule")
	@ResponseBody
	public ResponseMsg saveEvaluateCompanyRule(EvaluateCompanyRuleVo evaluateCompanyRuleVo) {
		EvaluateCompanyRule evaluateCompanyRule = null;
		ResponseMsg msg = new ResponseMsg();
		Map<String, Object> evaluateCompanyRuleMap = new HashMap<String, Object>();
		String rule = "";
		BigDecimal calculateRule;
		Boolean isTrue = false;
		//评估公司权重规则数据组装
		if(ObjectHelper.isNotEmpty(evaluateCompanyRuleVo.getEvaluateCompanyOne()) && ObjectHelper.isNotEmpty(evaluateCompanyRuleVo.getCalculateRuleOne())){
			calculateRule = evaluateCompanyRuleVo.getCalculateRuleOne().divide(new BigDecimal(100),4,BigDecimal.ROUND_HALF_UP);
			rule = evaluateCompanyRuleVo.getEvaluateCompanyOne() + "*" + calculateRule;
			isTrue = true;
		}
		
		if(ObjectHelper.isNotEmpty(evaluateCompanyRuleVo.getEvaluateCompanyTwo()) && ObjectHelper.isNotEmpty(evaluateCompanyRuleVo.getCalculateRuleTwo())){
			calculateRule = evaluateCompanyRuleVo.getCalculateRuleTwo().divide(new BigDecimal(100),4,BigDecimal.ROUND_HALF_UP);
			if(ObjectHelper.isNotEmpty(rule)){
				rule = rule + "+" + evaluateCompanyRuleVo.getEvaluateCompanyTwo() + "*" + calculateRule;
			}else{
				rule = evaluateCompanyRuleVo.getEvaluateCompanyTwo() + "*" + calculateRule;
			}
			isTrue = true;
		}
		
		if(ObjectHelper.isNotEmpty(evaluateCompanyRuleVo.getEvaluateCompanyThree()) && ObjectHelper.isNotEmpty(evaluateCompanyRuleVo.getCalculateRuleThree())){
			calculateRule = evaluateCompanyRuleVo.getCalculateRuleThree().divide(new BigDecimal(100),4,BigDecimal.ROUND_HALF_UP);
			if(ObjectHelper.isNotEmpty(rule)){
				rule = rule + "+" + evaluateCompanyRuleVo.getEvaluateCompanyThree() + "*" + calculateRule;
			}else{
				rule = evaluateCompanyRuleVo.getEvaluateCompanyThree() + "*" + calculateRule;
			}
			isTrue = true;
		}
		
		if(ObjectHelper.isNotEmpty(evaluateCompanyRuleVo.getEvaluateCompanyFour()) && ObjectHelper.isNotEmpty(evaluateCompanyRuleVo.getCalculateRuleFour())){
			calculateRule = evaluateCompanyRuleVo.getCalculateRuleFour().divide(new BigDecimal(100),4,BigDecimal.ROUND_HALF_UP);
			if(ObjectHelper.isNotEmpty(rule)){
				rule = rule + "+" + evaluateCompanyRuleVo.getEvaluateCompanyFour() + "*" + calculateRule;
			}else{
				rule = evaluateCompanyRuleVo.getEvaluateCompanyFour() + "*" + calculateRule;
			}
			isTrue = true;
		}
		
		if(ObjectHelper.isNotEmpty(evaluateCompanyRuleVo.getEvaluateCompanyFive()) && ObjectHelper.isNotEmpty(evaluateCompanyRuleVo.getCalculateRuleFive())){
			calculateRule = evaluateCompanyRuleVo.getCalculateRuleFive().divide(new BigDecimal(100),4,BigDecimal.ROUND_HALF_UP);
			if(ObjectHelper.isNotEmpty(rule)){
				rule = rule + "+" + evaluateCompanyRuleVo.getEvaluateCompanyFive() + "*" + calculateRule;
			}else{
				rule = evaluateCompanyRuleVo.getEvaluateCompanyFive() + "*" + calculateRule;
			}
			isTrue = true;
		}
		
		evaluateCompanyRuleVo.setRule(rule);
		
		//评估公司权重城市数据组装
		String[] city = evaluateCompanyRuleVo.getCity().split(",");
		evaluateCompanyRuleVo.setProvinceName(city[0]);
		evaluateCompanyRuleVo.setCityName(city[1]);
		evaluateCompanyRuleVo.setDistrictName(city[2]);

		// 将Vo转成Po
		evaluateCompanyRule = evaluateCompanyRuleVo.toPO();
		
		EvaluateCompanyRuleVo newevaluateCompanyRuleVo = null;
		EvaluateCompanyRule newevaluateCompanyRule = new EvaluateCompanyRule();
		
		if(isTrue){
			// 执行保存
			try {
				newevaluateCompanyRule = evaluateCompanyRuleService.saveOrUpdateEntity(evaluateCompanyRule);
				newevaluateCompanyRuleVo = new EvaluateCompanyRuleVo(newevaluateCompanyRule);
				evaluateCompanyRuleMap.put("newevaluateCompanyRuleVo", newevaluateCompanyRuleVo);
				msg.setResultStatus(ResponseMsg.SUCCESS);
				msg.setOptional(evaluateCompanyRuleMap);
				msg.setMsg("保存成功");
			} catch (Exception e) {
				e.printStackTrace();
				logger.error("evaluateCompanyRule保存失败", e);
				msg.setResultStatus(ResponseMsg.APP_ERROR);
				msg.setMsg("系统内部错误，请查看日志");
			}
		}else{
			logger.error("评估公司为空");
			msg.setResultStatus(ResponseMsg.APP_ERROR);
			msg.setMsg("评估公司不能为空");
		}
		return msg;
	}
	
	/**
	 * SimpleCode值转换
	 * @param latestCustomerVo
	 * @return
	 */
//	public EvaluateCompanyRuleVo SimpleCodeToName(EvaluateCompanyRuleVo evaluateCompanyRuleVosVo){
//		try {
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return evaluateCompanyRuleVosVo;
//	}
//	
//	public String SimpleCodeTurnOn(String fullCode){
//		String fullName = "";
//		try {
//			fullName = CED.loadSimpleCodeNameByFullCode(fullCode);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return fullName;
//	}
}
