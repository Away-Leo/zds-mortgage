package com.zdsoft.finance.afterloan.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zdsoft.essential.client.service.CED;
import com.zdsoft.finance.afterloan.entity.AfterDispatch;
import com.zdsoft.finance.afterloan.entity.AfterMonitorRecord;
import com.zdsoft.finance.afterloan.entity.AfterSupervise;
import com.zdsoft.finance.afterloan.entity.FollowInfo;
import com.zdsoft.finance.afterloan.service.AfterDispatchService;
import com.zdsoft.finance.afterloan.service.AfterMonitorRecordService;
import com.zdsoft.finance.afterloan.service.AfterSuperviseService;
import com.zdsoft.finance.afterloan.service.FollowInfoService;
import com.zdsoft.finance.afterloan.vo.AfterDispatchVo;
import com.zdsoft.finance.afterloan.vo.AfterSuperviseVo;
import com.zdsoft.finance.afterloan.vo.FollowInfoVo;
import com.zdsoft.finance.busiform.entity.BusiFormStatus;
import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.finance.marketing.entity.CaseApply;
import com.zdsoft.finance.marketing.service.CaseApplyService;
import com.zdsoft.framework.core.common.dto.ResponseMsg;
import com.zdsoft.framework.core.common.util.ObjectHelper;
import com.zdsoft.framework.core.commweb.annotation.UriKey;
import com.zdsoft.framework.core.commweb.component.BaseController;
import com.zdsoft.framework.cra.annotation.Reference;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * 
 * @Title: LoanAfterBasePageController.java
 * @ClassName: LoanAfterBasePageController
 * @Description: 贷后基础信息Controller(主要提供公共页面信息)
 * @author liuwei
 * @date 2017年3月6日 上午10:02:44
 * @version V1.0
 */
@Controller
@RequestMapping("/loanAfterBasePage")
public class LoanAfterBasePageController extends BaseController {

	@Autowired
	AfterMonitorRecordService afterMonitorRecordService;

	@Autowired
	AfterSuperviseService afterSuperviseService;

	@Autowired
	FollowInfoService followInfoService;

	@Autowired
	AfterDispatchService afterDispatchService;

	@Autowired
	CaseApplyService caseApplyService;

	@Autowired
	CED CED;

	@RequestMapping("/initCaseApplyDetail")
	@UriKey(key = "com.zdsoft.finance.afterloan.base.initCaseApplyDetail")
	@Reference(resource = "com.cnfh.rms.initCaseApplyDetail", label = "查看案件详情")
	public ModelAndView initCaseApplyDetail(String caseApplyId, String controlType) {
		// sql查询案件相关信息
		Map<String, Object> returnMap = afterMonitorRecordService.findMonitorRecordByCaseApplyId(caseApplyId,
				controlType);
		returnMap.put("caseApplyId", caseApplyId);

		try {
			// 查询案件信息
			CaseApply caseApply = caseApplyService.findOne(caseApplyId);

			// 查询集团监控部门的督办信息

			// TODO ..liuwei 获取监控部门部门编号/贷后部门部门编号/法务部门部门编号
			Map<String, FollowInfo> followInfos = followInfoService.findByCaseApplyIdAndOrgCds("", "", "",
					caseApply.getMechanismCode(), caseApplyId);
			Map<String, AfterSupervise> afterSupervises = afterSuperviseService.findByCaseApplyIdAndOrgCds("", "", "",
					caseApply.getMechanismCode(), caseApplyId);

			FollowInfo monitorFollowInfo = followInfos.get("monitorFollowInfo");
			FollowInfo afterLoanFollowInfo = followInfos.get("afterLoanFollowInfo");
			FollowInfo justiceFollowInfo = followInfos.get("justiceFollowInfo");
			FollowInfo mechanismFollowInfo = followInfos.get("mechanismFollowInfo");

			AfterSupervise monitorFollow = afterSupervises.get("monitorFollow");
			AfterSupervise afterLoanFollow = afterSupervises.get("afterLoanFollow");
			AfterSupervise justiceFollow = afterSupervises.get("justiceFollow");
			AfterSupervise mechanismFollow = afterSupervises.get("mechanismFollow");

			// 集团监控部门的督办/跟催信息
			if (ObjectHelper.isNotEmpty(monitorFollow)) {
				AfterSuperviseVo monitorVo = new AfterSuperviseVo(monitorFollow);
				returnMap.put("monitorVo", monitorVo);
			}
			if (ObjectHelper.isNotEmpty(monitorFollowInfo)) {
				FollowInfoVo monitorInfoVo = new FollowInfoVo(monitorFollowInfo);
				returnMap.put("monitorInfoVo", monitorInfoVo);
			}

			// 集团贷后部门的督办/跟催信息
			if (ObjectHelper.isNotEmpty(afterLoanFollow)) {
				AfterSuperviseVo afterLoanVo = new AfterSuperviseVo(afterLoanFollow);
				returnMap.put("afterLoanVo", afterLoanVo);
			}
			if (ObjectHelper.isNotEmpty(afterLoanFollowInfo)) {
				FollowInfoVo afterLoanInfoVo = new FollowInfoVo(afterLoanFollowInfo);
				returnMap.put("afterLoanInfoVo", afterLoanInfoVo);
			}

			// 集团法务部门的督办/跟催信息
			if (ObjectHelper.isNotEmpty(justiceFollow)) {
				AfterSuperviseVo justiceVo = new AfterSuperviseVo(justiceFollow);
				returnMap.put("justiceVo", justiceVo);
			}
			if (ObjectHelper.isNotEmpty(justiceFollowInfo)) {
				FollowInfoVo justiceInfoVo = new FollowInfoVo(justiceFollowInfo);
				returnMap.put("justiceInfoVo", justiceInfoVo);
			}

			// 本机构的督办/跟催信息
			if (ObjectHelper.isNotEmpty(mechanismFollow)) {
				AfterSuperviseVo mechanismVo = new AfterSuperviseVo(mechanismFollow);
				returnMap.put("mechanismVo", mechanismVo);
			}

			if (ObjectHelper.isNotEmpty(mechanismFollowInfo)) {
				FollowInfoVo mechanismInfoVo = new FollowInfoVo(mechanismFollowInfo);
				returnMap.put("mechanismInfoVo", mechanismInfoVo);
			}
		} catch (BusinessException e) {
			e.printStackTrace();
			logger.error("通过案件id查询案件信息失败", e);
		}

		return new ModelAndView("/afterloan/basePage/case_apply_detail", returnMap);
	}

	@RequestMapping("/initFollowList")
	@UriKey(key = "com.zdsoft.finance.afterloan.base.initFollowList")
	@Reference(resource = "com.zdsoft.finance.afterloan.base.initFollowList", label = "查看跟催列表")
	public ModelAndView initFollowList(String followUpId, String afterSuperviseId) {
		ModelMap modelMap = new ModelMap();
		String caseApplyId = null;
		try {
			if (ObjectHelper.isNotEmpty(followUpId) && !"undefined".equals(followUpId)) {
				FollowInfo followInfo = followInfoService.findOne(followUpId);
				caseApplyId = followInfo.getCaseApplyId();
			} else if (ObjectHelper.isNotEmpty(afterSuperviseId) && !"undefined".equals(afterSuperviseId)) {
				AfterSupervise afterSupervise = afterSuperviseService.findOne(afterSuperviseId);
				caseApplyId = afterSupervise.getCaseApplyId();
			}
		} catch (BusinessException e) {
			e.printStackTrace();
			logger.error("查询跟催督办信息失败", e);
		}
		modelMap.put("caseApplyId", caseApplyId);
		return new ModelAndView("/afterloan/basePage/case_follow_list", modelMap);
	}

	/**
	 * 
	 * @Title: afterSuperviseList
	 * @Description: 查询该案件下审批通过的督办信息
	 * @author liuwei
	 * @param caseApplyId
	 *            案件id
	 * @param jsoncallback
	 * @return 督办集合JSON
	 */
	@RequestMapping("/afterSuperviseList")
	@UriKey(key = "com.zdsoft.finance.afterloan.base.afterSuperviseList")
	@ResponseBody
	public String afterSuperviseList(String caseApplyId, String jsoncallback) {
		ResponseMsg msg = new ResponseMsg();

		try {
			List<AfterSuperviseVo> afterSuperviseVos = new ArrayList<AfterSuperviseVo>();
			List<AfterSupervise> afterSupervises = afterSuperviseService.findByCaseApplyIdAndFormStatus(caseApplyId,
					BusiFormStatus.THROUAPPROVAL);
			for (int i = 0; i < afterSupervises.size(); i++) {
				AfterSuperviseVo superviseVo = new AfterSuperviseVo(afterSupervises.get(i));
				afterSuperviseVos.add(superviseVo);
			}

			msg.setMsg("查询督办信息成功");
			msg.setResultStatus(ResponseMsg.SUCCESS);
			msg.setTotal(Long.valueOf(afterSuperviseVos.size()));
			msg.setRows(afterSuperviseVos);
		} catch (Exception e) {
			e.printStackTrace();
			msg.setMsg("查询督办信息失败");
			msg.setResultStatus(ResponseMsg.APP_ERROR);
		}
		return ObjectHelper.objectToJson(msg, jsoncallback);
	}

	/**
	 * 
	 * @Title: getAfterSuperviseById
	 * @Description: 获取督办反馈集合
	 * @author liuwei
	 * @param id
	 *            督办id
	 * @param jsoncallback
	 * @return 反馈集合json
	 */
	@RequestMapping("/getAfterDispatchBySuperviseId")
	@UriKey(key = "com.zdsoft.finance.afterloan.base.getAfterDispatchBySuperviseId")
	@ResponseBody
	public String getAfterDispatchBySuperviseId(String id, String jsoncallback) {
		ResponseMsg msg = new ResponseMsg();
		// 通过督办id查询督办反馈信息集合
		List<AfterDispatch> afterDispatchs = afterDispatchService.findByAfterSuperviseId(id);

		// 构建返回Vo
		List<AfterDispatchVo> afterDispatchVos = new ArrayList<AfterDispatchVo>();
		for (int i = 0; i < afterDispatchs.size(); i++) {
			AfterDispatchVo dispatchVo = new AfterDispatchVo(afterDispatchs.get(i));
			afterDispatchVos.add(dispatchVo);
		}

		msg.setMsg("督办反馈信息查询成功");
		msg.setResultStatus(ResponseMsg.SUCCESS);
		msg.setTotal(Long.valueOf(afterDispatchs.size()));
		msg.setRows(afterDispatchVos);
		return ObjectHelper.objectToJson(msg, jsoncallback);
	}

	/**
	 * 
	 * @Title: followInfoList
	 * @Description: 查询该案件下审批通过的跟催信息
	 * @author liuwei
	 * @param caseApplyId
	 *            案件id
	 * @param jsoncallback
	 * @return 跟催信息JSON
	 */
	@RequestMapping("/followInfoList")
	@UriKey(key = "com.zdsoft.finance.afterloan.base.followInfoList")
	@ResponseBody
	public String followInfoList(String caseApplyId, String jsoncallback) {

		ResponseMsg msg = new ResponseMsg();
		// 查询跟催信息
		List<FollowInfo> followInfos;
		try {
			followInfos = followInfoService.findByCaseApplyIdAndFormStatus(caseApplyId, BusiFormStatus.THROUAPPROVAL);
			// 组装返回数据
			List<FollowInfoVo> followInfoVos = new ArrayList<FollowInfoVo>();
			for (int i = 0; i < followInfos.size(); i++) {
				FollowInfoVo followInfoVo = new FollowInfoVo(followInfos.get(i));
				followInfoVos.add(followInfoVo);
			}

			// 返回跟催信息
			msg.setMsg("跟催信息查询成功");
			msg.setResultStatus(ResponseMsg.SUCCESS);
			msg.setTotal(Long.valueOf(followInfoVos.size()));
			msg.setRows(followInfoVos);
		} catch (BusinessException e) {
			e.printStackTrace();
			msg.setMsg("跟催信息查询失败");
			msg.setResultStatus(ResponseMsg.APP_ERROR);
		}
		return ObjectHelper.objectToJson(msg, jsoncallback);
	}

	@RequestMapping("/initFollowUpDetail")
	@UriKey(key = "com.zdsoft.finance.afterloan.base.initFollowUpDetail")
	@Reference(resource = "com.zdsoft.finance.afterloan.base.initFollowUpDetail", label = "跟催明细")
	public ModelAndView initFollowUpDetail(String followUpId, String afterSuperviseId) {

		ModelMap modelMap = new ModelMap();
		String caseApplyId = null;
		try {
			// 判断督办id
			if (ObjectHelper.isNotEmpty(afterSuperviseId)) {
				// 查询督办信息
				AfterSupervise afterSupervise = afterSuperviseService.findOne(afterSuperviseId);
				caseApplyId = afterSupervise.getCaseApplyId();

				// 转换督办Vo
				AfterSuperviseVo afterSuperviseVo = new AfterSuperviseVo(afterSupervise);
				modelMap.put("afterSuperviseVo", afterSuperviseVo);
			}

			// 判断跟催id
			if (ObjectHelper.isNotEmpty(followUpId)) {

				// 查询跟催信息
				FollowInfo followInfo = followInfoService.findOne(followUpId);
				caseApplyId = followInfo.getCaseApplyId();

				// 转换跟催Vo
				FollowInfoVo followInfoVo = new FollowInfoVo(followInfo);
				modelMap.put("followInfoVo", followInfoVo);

			}
		} catch (BusinessException e) {
			e.printStackTrace();
			logger.error("查询督办信息失败", e);
		}

		// 判断案件id是否存在
		if (ObjectHelper.isNotEmpty(caseApplyId)) {
			// sql查询案件相关信息
			Map<String, Object> returnMap = afterMonitorRecordService.findMonitorRecordByCaseApplyId(caseApplyId,
					AfterMonitorRecord.MIDDLE_MONITOR_RECORD);
			modelMap.putAll(returnMap);
			modelMap.put("caseApplyId", caseApplyId);
		}
		return new ModelAndView("/afterloan/basePage/case_follow_view", modelMap);
	}
}
