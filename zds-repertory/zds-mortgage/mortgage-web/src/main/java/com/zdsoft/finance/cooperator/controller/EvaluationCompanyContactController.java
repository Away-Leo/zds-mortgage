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
import com.zdsoft.finance.cooperator.entity.EvaluationCompanyContact;
import com.zdsoft.finance.cooperator.service.EvaluationCompanyContactService;
import com.zdsoft.finance.cooperator.service.EvaluationCompanyService;
import com.zdsoft.finance.cooperator.vo.EvaluationCompanyContactVo;
import com.zdsoft.framework.core.common.dto.ResponseMsg;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.PageRequest;
import com.zdsoft.framework.core.common.util.ObjectHelper;
import com.zdsoft.framework.core.commweb.annotation.UriKey;
import com.zdsoft.framework.core.commweb.component.BaseController;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * 
 * @Title: EvaluationCompanyContactController.java
 * @ClassName: EvaluationCompanyContactController
 * @Description: 评估公司联系方式Controller
 * @author liuwei
 * @date 2017年3月9日 上午11:07:10
 * @version V1.0
 */
@Controller
@RequestMapping("/evaluationContact")
public class EvaluationCompanyContactController extends BaseController {

	@Autowired
	EvaluationCompanyContactService evaluationCompanyContactService;
	@Autowired
	EvaluationCompanyService evaluationCompanyService;

	/**
	 * 
	 * @Title: initContact
	 * @Description: 评估公司联系列表
	 * @author liuwei
	 * @param evaluationId
	 *            评估公司id
	 * @param operationType
	 *            操作类型
	 * @return ModelAndView
	 */
	@RequestMapping("/initContactsInfo")
	@UriKey(key = "com.zdsoft.finance.cooperator.evaluation.initContact")
	public ModelAndView initContact(String evaluationId, String operationType) {
		ModelAndView model = new ModelAndView("/cooperator/evaluation_contact_list");
		model.addObject("evaluationId", evaluationId);
		model.addObject("operationType", operationType);
		return model;
	}

	/**
	 * 
	 * @Title: getContactsInfo
	 * @Description: 评估公司联系人列表数据展示
	 * @author liuwei
	 * @param request
	 *            请求
	 * @param jsoncallback
	 * @param pageable
	 *            分页信息
	 * @return 处理消息msg json
	 */
	@RequestMapping("/getContactsInfo")
	@UriKey(key = "com.zdsoft.finance.cooperator.evaluation.getContact")
	@ResponseBody
	public String getContactsInfo(HttpServletRequest request, String jsoncallback, PageRequest pageable) {

		// 获取页面封装的查询参数
		@SuppressWarnings("unchecked")
		List<QueryObj> queryObjs = (List<QueryObj>) request.getAttribute("listObj");
		// 分页查询会议
		Page<EvaluationCompanyContact> infos = evaluationCompanyContactService.findByHqlConditions(pageable, queryObjs);
		List<EvaluationCompanyContact> list = infos.getRecords();
		List<EvaluationCompanyContactVo> listVo = new ArrayList<EvaluationCompanyContactVo>();
		for (EvaluationCompanyContact info : list) {
			EvaluationCompanyContactVo vo = new EvaluationCompanyContactVo(info);
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
	 * @Title: save
	 * @Description: 更新联系人
	 * @author liuwei
	 * @param infoVo
	 *            联系人Vo
	 * @return 处理消息msg json
	 * @throws BusinessException
	 */
	@RequestMapping("/save")
	@UriKey(key = "com.zdsoft.finance.cooperator.evaluation.contact.save")
	@ResponseBody
	public String save(EvaluationCompanyContactVo infoVo) throws BusinessException {
		ResponseMsg msg = new ResponseMsg();
		if (!ObjectHelper.isEmpty(infoVo)) {
			if (ObjectHelper.isEmpty(infoVo.getId())) {
				EvaluationCompanyContact info = infoVo.toPO();
				EvaluationCompany eval = evaluationCompanyService.findOne(infoVo.getEvaluationId());
				info.setEvaluationCompany(eval);
				evaluationCompanyContactService.saveEntity(info);
				msg.setMsg("保存成功！");
				msg.setResultStatus(ResponseMsg.SUCCESS);
			} else {

				// 查询评估公司联系信息
				EvaluationCompanyContact info = evaluationCompanyContactService.findOne(infoVo.getId());

				// 设置新值
				info.setContactName(infoVo.getContactName());
				info.setContactTelNumber(infoVo.getContactTelNumber());
				info.setContactType(infoVo.getContactType());
				EvaluationCompany ter = evaluationCompanyService.findOne(infoVo.getEvaluationId());
				info.setEvaluationCompany(ter);
				evaluationCompanyContactService.updateEntity(info);
				msg.setMsg("更新成功！");
				msg.setResultStatus(ResponseMsg.SUCCESS);
			}
		} else {
			msg.setMsg("数据为空");
			msg.setResultStatus(ResponseMsg.APP_ERROR);
		}
		return ObjectHelper.objectToJson(msg);
	}

	/**
	 * 
	 * @Title: dialog
	 * @Description: 评估公司联系方式dialog
	 * @author liuwei
	 * @param evaluationId
	 *            评估公司id
	 * @param id
	 *            评估公司联系方式id
	 * @param operationType
	 *            操作类型
	 * @return ModelAndView
	 */
	@RequestMapping("/dialog")
	@UriKey(key = "com.zdsoft.finance.cooperator.evaluation.contact.dialog")
	public ModelAndView dialog(String evaluationId, String id, String operationType) {
		ModelAndView modelAndView = new ModelAndView("/cooperator/evaluation_contact_dialog");
		if ("mod".equals(operationType)) {

			try {
				EvaluationCompanyContact info = evaluationCompanyContactService.findOne(id);
				EvaluationCompanyContactVo infoVo = new EvaluationCompanyContactVo(info);
				modelAndView.addObject("infoVo", infoVo);
			} catch (BusinessException e) {
				e.printStackTrace();
				logger.error("查询评估公司联系方式失败", e);
			}

		}
		modelAndView.addObject("evaluationId", evaluationId);
		return modelAndView;
	}

	/**
	 * 
	 * @Title: del
	 * @Description: 删除评估公司联系方式
	 * @author liuwei
	 * @param jsoncallback
	 * @param id
	 *            评估公司联系方式id
	 * @return 处理消息msg json
	 */
	@RequestMapping("/del")
	@UriKey(key = "com.zdsoft.finance.cooperator.evaluation.contact.del")
	@ResponseBody
	public String del(String jsoncallback, String id) {
		ResponseMsg msg = new ResponseMsg();
		try {
			evaluationCompanyContactService.logicDelete(id);
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
	 * @Title: companyEdit
	 * @Description: 联系人编辑
	 * @author liuwei
	 * @param id
	 *            联系人id
	 * @return 处理消息msg json
	 */
	@RequestMapping("/edit")
	@UriKey(key = "com.zdsoft.finance.cooperator.evaluation.contact.edit")
	@ResponseBody
	public String companyEdit(String id) {

		Map<String, Object> obj = new HashMap<String, Object>();
		ResponseMsg msg = new ResponseMsg();
		try {
			// 查询评估公司联系方式信息
			EvaluationCompanyContact info = evaluationCompanyContactService.findOne(id);

			// 转换Vo
			EvaluationCompanyContactVo infoVo = new EvaluationCompanyContactVo(info);
			obj.put("infoVo", infoVo);
			msg.setMsg("操作成功");
			msg.setResultStatus(ResponseMsg.SUCCESS);
			msg.setTotal(0L);
			msg.setOptional(obj);
		} catch (BusinessException e) {
			e.printStackTrace();
			logger.error("查询评估公司联系方式失败", e);
			msg.setMsg("查询评估公司联系方式失败");
			msg.setResultStatus(ResponseMsg.APP_ERROR);
		}

		return ObjectHelper.objectToJson(msg);
	}
}
