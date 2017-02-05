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
 * 评估公司表
 * Evaluation
 * @author Hisa
 *
 */
@Controller
@RequestMapping("/evaluation")
public class EvaluationCompanyController extends BaseController {

	@Autowired
	EvaluationCompanyService evaluationCompanyService;
	
	/**
	 * 评估公司列表
	 * @return
	 */
	@RequestMapping("/initEvaluationCompany")
	@UriKey(key = "com.zdsoft.finance.cooperator.evaluation.initEvaluationCompany")
	//@Menu(resource = "com.zdsoft.finance.cooperator.evaluation.initEvaluationCompany", label = "评估公司", group = "cooperator", order = 2)
	public ModelAndView initEvaluationCompany(String terminalId) {
		return new ModelAndView("/cooperator/evaluation_company_list");
	}
	/**
	 * 评估公司列表数据展示
	 * @param request
	 * @param jsoncallback
	 * @param pageable
	 * @return
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
			EvaluationCompanyVo vo = new EvaluationCompanyVo(info, new String[] {}, new String[] { "isStop","evaluateType" });
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
	 * 新增页Tab
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/tab")
	@UriKey(key = "com.zdsoft.finance.cooperator.evaluation.tab")
	public ModelAndView tab(String evaluationId,String operationType) throws Exception {
		ModelAndView modelAndView=new ModelAndView("/cooperator/evaluation_company_tab");
		if(!ObjectHelper.isEmpty(evaluationId)){
			modelAndView.addObject("evaluationId", evaluationId);
		}else{
			EvaluationCompany info = new EvaluationCompany();
			info.setLogicDelelte("0");
			EvaluationCompany eva = evaluationCompanyService.saveEntity(info);
			modelAndView.addObject("evaluationId", eva.getId());
		}
		modelAndView.addObject("operationType", operationType);
		return modelAndView;
	}
	/**
	 * add
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/add")
	@UriKey(key = "com.zdsoft.finance.cooperator.evaluation.add")
	public ModelAndView add(String evaluationId,String operationType) throws Exception {
		ModelAndView modelAndView=new ModelAndView("/cooperator/evaluation_company_edit");
		if("mod".equals(operationType) || "view".equals(operationType)){
			EvaluationCompany info = evaluationCompanyService.findOne(evaluationId);
			EvaluationCompanyVo infoVo = new EvaluationCompanyVo(info);
			infoVo.setFoundDate(DateHelper.longToDate(info.getFoundDate(), DateHelper.DATE_SHORT_FORMAT));
			modelAndView.addObject("infoVo", infoVo);
			
		}
		modelAndView.addObject("evaluationId", evaluationId);
		modelAndView.addObject("operationType", operationType);
		return modelAndView;
	}
	
	/**
	 * 更新评审公司
	 * @param jsoncallback
	 * @param infoVo
	 * @return
	 * @throws BusinessException
	 */
	@RequestMapping("/save")
	@UriKey(key = "com.zdsoft.finance.cooperator.evaluation.company.save")
	@ResponseBody
	public String save(EvaluationCompanyVo infoVo ) throws BusinessException {
		ResponseMsg msg = new ResponseMsg();
		if(!ObjectHelper.isEmpty(infoVo)){
			if(ObjectHelper.isEmpty(infoVo.getId())){
				EvaluationCompany info = infoVo.toPO();
				EvaluationCompany eva = evaluationCompanyService.saveEntity(info);
				msg.setMsg("保存成功！");
				msg.setId(eva.getId());
				msg.setResultStatus(ResponseMsg.SUCCESS);
			}else{
				EvaluationCompany info =evaluationCompanyService.findOne(infoVo.getId());
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
		}else{
			msg.setMsg("数据为空");
			msg.setResultStatus(ResponseMsg.APP_ERROR);
		}
		return ObjectHelper.objectToJson(msg);
	}
	/**
	 * 删除
	 * @param jsoncallback
	 * @return
	 * @throws BusinessException 
	 */
	@RequestMapping("/del")
	@UriKey(key = "com.zdsoft.finance.cooperator.evaluation.company.del")
	@ResponseBody
	public String del(String jsoncallback,String id) throws BusinessException {
		ResponseMsg msg = new ResponseMsg();
		try {
			evaluationCompanyService.logicDelete(id);
			msg.setMsg("操作成功！");
			msg.setResultStatus(ResponseMsg.SUCCESS);
		} catch (Exception e) {
			msg.setMsg("操作失败！"+e.getMessage());
			msg.setResultStatus(ResponseMsg.SYS_ERROR);
		}
		return ObjectHelper.objectToJson(msg);
	}
	
	/**
	 * 查询所有的评估公司名称（评估公司权重下拉框使用）
	 * @param jsoncallback
	 * @return
	 */
	@RequestMapping("/findAllCompanyName")
	@UriKey(key = "com.zdsoft.finance.cooperator.evaluation.company.findAllCompanyName")
	@ResponseBody
	public String findClientNameByClientId(String jsoncallback) {
		List<EvaluationCompany> evaluationCompanys = null;
		List<Map<String, String>> simpleCode = new ArrayList<Map<String, String>>();
		evaluationCompanys = evaluationCompanyService.findAll();
		if(ObjectHelper.isNotEmpty(evaluationCompanys)&&evaluationCompanys.size()>0){
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
