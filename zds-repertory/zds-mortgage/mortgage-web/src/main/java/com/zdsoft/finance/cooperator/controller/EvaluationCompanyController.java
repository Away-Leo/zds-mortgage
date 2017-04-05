package com.zdsoft.finance.cooperator.controller;

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
import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.finance.cooperator.entity.EvaluationCompany;
import com.zdsoft.finance.cooperator.service.EvaluationCompanyService;
import com.zdsoft.finance.cooperator.vo.EvaluationCompanyVo;
import com.zdsoft.framework.core.common.dto.ResponseMsg;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.PageRequest;
import com.zdsoft.framework.core.common.util.DateHelper;
import com.zdsoft.framework.core.common.util.ObjectHelper;
import com.zdsoft.framework.core.commweb.annotation.UriKey;
import com.zdsoft.framework.core.commweb.component.BaseController;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * 
 * @Title: EvaluationCompanyController.java
 * @ClassName: EvaluationCompanyController
 * @Description: 评估公司Controller
 * @author liuwei
 * @date 2017年3月9日 上午11:12:40
 * @version V1.0
 */
@Controller
@RequestMapping("/evaluation")
public class EvaluationCompanyController extends BaseController {

	@Autowired
	EvaluationCompanyService evaluationCompanyService;

	/**
	 * 
	 * @Title: initEvaluationCompany
	 * @Description: 评估公司列表
	 * @author liuwei
	 * @param terminalId
	 *            评估公司id
	 * @return ModelAndView
	 */
	@RequestMapping("/initEvaluationCompany")
	@UriKey(key = "com.zdsoft.finance.cooperator.evaluation.initEvaluationCompany")
	public ModelAndView initEvaluationCompany(String terminalId) {
		return new ModelAndView("/cooperator/evaluation_company_list");
	}

	/**
	 * 
	 * @Title: getEvaluationCompany
	 * @Description: 评估公司列表数据展示
	 * @author liuwei
	 * @param request
	 * @param jsoncallback
	 * @param pageable
	 * @return 处理消息msg json
	 */
	@RequestMapping("/getContactsInfo")
	@UriKey(key = "com.zdsoft.finance.cooperator.evaluation.getEvaluationCompany")
	@ResponseBody
	public String getEvaluationCompany(HttpServletRequest request, String jsoncallback, PageRequest pageable) {

		// 获取页面封装的查询参数
		@SuppressWarnings("unchecked")
		List<QueryObj> queryObjs = (List<QueryObj>) request.getAttribute("listObj");
		QueryObj obj = new QueryObj();
		obj.setElement("String");
		obj.setObj("logicDelelte");
		obj.setOperator("E");
		obj.setValue("1");
		queryObjs.add(obj);
		// 分页查询会议
		Page<EvaluationCompany> infos = evaluationCompanyService.findByHqlConditions(pageable, queryObjs);
		List<EvaluationCompany> list = infos.getRecords();
		List<EvaluationCompanyVo> listVo = new ArrayList<EvaluationCompanyVo>();
		for (EvaluationCompany info : list) {
			EvaluationCompanyVo vo = new EvaluationCompanyVo(info, new String[] {},
					new String[] { "isStop", "evaluateType" });
			listVo.add(vo);
		}
		ResponseMsg msg = new ResponseMsg();
		msg.setMsg("列表查询成功");
		msg.setResultStatus(ResponseMsg.SUCCESS);
		msg.setTotal(0L);
		msg.setRows(listVo);
		return ObjectHelper.objectToJson(msg, jsoncallback);
	}

	/**
	 * 
	 * @Title: tab
	 * @Description: 新增页Tab
	 * @author liuwei
	 * @param evaluationId
	 * @param operationType
	 * @return
	 */
	@RequestMapping("/tab")
	@UriKey(key = "com.zdsoft.finance.cooperator.evaluation.tab")
	public ModelAndView tab(String evaluationId, String operationType) {
		ModelAndView modelAndView = new ModelAndView("/cooperator/evaluation_company_tab");
		if (!ObjectHelper.isEmpty(evaluationId)) {
			modelAndView.addObject("evaluationId", evaluationId);
		} else {
			EvaluationCompany info = new EvaluationCompany();
			info.setLogicDelelte("0");
			EvaluationCompany eva;
			try {
				eva = evaluationCompanyService.saveEntity(info);
				modelAndView.addObject("evaluationId", eva.getId());
			} catch (BusinessException e) {
				e.printStackTrace();
				logger.error("保存评估公司信息失败", e);
			}

		}
		modelAndView.addObject("operationType", operationType);
		return modelAndView;
	}

	/**
	 * 
	 * @Title: add
	 * @Description: 新增评估公司
	 * @author liuwei
	 * @param evaluationId
	 *            评估公司id
	 * @param operationType
	 *            操作类型
	 * @return ModelAndView
	 */
	@RequestMapping("/add")
	@UriKey(key = "com.zdsoft.finance.cooperator.evaluation.add")
	public ModelAndView add(String evaluationId, String operationType) {
		ModelAndView modelAndView = new ModelAndView("/cooperator/evaluation_company_edit");
		if ("mod".equals(operationType) || "view".equals(operationType)) {

			try {
				EvaluationCompany info = evaluationCompanyService.findOne(evaluationId);
				EvaluationCompanyVo infoVo = new EvaluationCompanyVo(info);
				infoVo.setFoundDate(DateHelper.longToDate(info.getFoundDate(), DateHelper.DATE_SHORT_FORMAT));
				modelAndView.addObject("infoVo", infoVo);

			} catch (BusinessException e) {
				e.printStackTrace();
				logger.error("查询评估公司失败", e);
			}
		}
		modelAndView.addObject("evaluationId", evaluationId);
		modelAndView.addObject("operationType", operationType);
		return modelAndView;
	}

	/**
	 * 
	 * @Title: save
	 * @Description: 更新评估公司
	 * @author liuwei
	 * @param infoVo
	 *            评估公司信息
	 * @return 处理消息msg json
	 */
	@RequestMapping("/save")
	@UriKey(key = "com.zdsoft.finance.cooperator.evaluation.company.save")
	@ResponseBody
	public String save(EvaluationCompanyVo infoVo) {
		ResponseMsg msg = new ResponseMsg();
		try {
			if (!ObjectHelper.isEmpty(infoVo)) {
				if (ObjectHelper.isEmpty(infoVo.getId())) {
					EvaluationCompany info = infoVo.toPO();
					EvaluationCompany eva = evaluationCompanyService.saveEntity(info);
					msg.setMsg("保存成功！");
					msg.setId(eva.getId());
					msg.setResultStatus(ResponseMsg.SUCCESS);
				} else {
					EvaluationCompany info = evaluationCompanyService.findOne(infoVo.getId());
					info.setCompanyType(infoVo.getCompanyType());
					info.setCompanyName(infoVo.getCompanyName());
					info.setShortName(infoVo.getShortName());
					info.setFatherName(infoVo.getFatherName());
					info.setWebsite(infoVo.getWebsite());
					info.setPostalcode(infoVo.getPostalcode());
					info.setRegionCode(infoVo.getRegionCode());
					info.setIsStop(infoVo.getIsStop());
					info.setEvaluateType(infoVo.getEvaluateType());
					info.setAddress(infoVo.getAddress());
					info.setFoundDate(DateHelper.dateToLong(
							DateHelper.stringToDate(infoVo.getFoundDate(), DateHelper.DATE_SHORT_SIMPLE_FORMAT),
							DateHelper.DATE_SHORT_SIMPLE_FORMAT_WITHHOUR));
					info.setLegalPerson(infoVo.getLegalPerson());
					info.setDutyParagraph(infoVo.getDutyParagraph());
					info.setBankAccount(infoVo.getBankAccount());
					info.setIndustry(infoVo.getIndustry());
					info.setRemark(infoVo.getRemark());
					info.setLogicDelelte("1");
					evaluationCompanyService.updateEntity(info);
					msg.setMsg("更新成功！");
					msg.setResultStatus(ResponseMsg.SUCCESS);
				}
			} else {
				msg.setMsg("数据为空");
				msg.setResultStatus(ResponseMsg.APP_ERROR);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("保存评估公司信息失败", e);
		}
		return ObjectHelper.objectToJson(msg);
	}

	/**
	 * 
	 * @Title: del
	 * @Description: 删除评估公司
	 * @author liuwei
	 * @param jsoncallback
	 * @param id
	 *            评估公司id
	 * @return 处理消息msg json
	 */
	@RequestMapping("/del")
	@UriKey(key = "com.zdsoft.finance.cooperator.evaluation.company.del")
	@ResponseBody
	public String del(String jsoncallback, String id) {
		ResponseMsg msg = new ResponseMsg();
		try {
			evaluationCompanyService.logicDelete(id);
			msg.setMsg("操作成功！");
			msg.setResultStatus(ResponseMsg.SUCCESS);
		} catch (Exception e) {
			msg.setMsg("操作失败！" + e.getMessage());
			msg.setResultStatus(ResponseMsg.SYS_ERROR);
		}
		return ObjectHelper.objectToJson(msg);
	}

	/**
	 * 
	 * @Title: findClientNameByClientId
	 * @Description: 查询所有的评估公司名称（评估公司权重下拉框使用）
	 * @author liuwei
	 * @param jsoncallback
	 * @return 评估公司json
	 */
	@RequestMapping("/findAllCompanyName")
	@UriKey(key = "com.zdsoft.finance.cooperator.evaluation.company.findAllCompanyName")
	@ResponseBody
	public String findClientNameByClientId(String jsoncallback) {
		List<EvaluationCompany> evaluationCompanys = null;
		List<Map<String, String>> simpleCode = new ArrayList<Map<String, String>>();
		evaluationCompanys = evaluationCompanyService.findAll();
		if (ObjectHelper.isNotEmpty(evaluationCompanys) && evaluationCompanys.size() > 0) {
			for (EvaluationCompany evaluationCompany : evaluationCompanys) {
				Map<String, String> evaluationCompanyMap = new HashMap<String, String>();
				evaluationCompanyMap.put("fullcode", evaluationCompany.getCompanyName());
				evaluationCompanyMap.put("name", evaluationCompany.getCompanyName());
				simpleCode.add(evaluationCompanyMap);
			}
		}
		return ObjectHelper.objectToJson(simpleCode, jsoncallback);
	}
}
