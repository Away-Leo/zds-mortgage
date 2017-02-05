package com.zdsoft.finance.casemanage.datasurvey.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zdsoft.finance.casemanage.datasurvey.entity.RiskInfomation;
import com.zdsoft.finance.casemanage.datasurvey.service.RiskInfomationService;
import com.zdsoft.finance.casemanage.datasurvey.vo.RiskInformationVo;
import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.finance.cooperator.entity.CooperatorTerminal;
import com.zdsoft.finance.cooperator.service.CooperatorTerminalService;
import com.zdsoft.finance.marketing.entity.CaseApply;
import com.zdsoft.finance.marketing.service.CaseApplyService;
import com.zdsoft.finance.marketing.vo.CaseApplyVo;
import com.zdsoft.framework.core.common.dto.ResponseMsg;
import com.zdsoft.framework.core.common.util.ObjectHelper;
import com.zdsoft.framework.core.commweb.annotation.UriKey;
import com.zdsoft.framework.core.commweb.component.BaseController;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * 
 * @Title:RiskInformationController.java
 * @Package:com.zdsoft.finance.casemanage.datasurvey.controller
 * @Description:风险信息
 * @author: laijun
 * @date:2017年1月10日 下午9:41:46
 * @version:v1.0
 */
@Controller
@RequestMapping("/casemanage/datasurvey/riskinformation")
public class RiskInformationController extends BaseController {

	@Autowired
	private CaseApplyService caseApplyService;
	@Autowired
	private RiskInfomationService riskInfomationService;
	@Autowired
	private CooperatorTerminalService cooperatorTerminalService;

	/**
	 * 
	 * 风险信息编辑
	 *
	 * @author laijun
	 * @date:2017年1月10日 下午9:42:01
	 * @param caseApplyId
	 * @return
	 */
	@RequestMapping(value = "/edit")
	@UriKey(key = "com.zdsoft.finance.casemanage.datasurvey.riskinformation.edit")
	public ModelAndView edit(String caseApplyId) {
		ModelMap map = new ModelMap();
		CaseApplyVo vo = null;
		if (ObjectHelper.isEmpty(caseApplyId)) {
			map.put("errorMsg", "案件id为空!");
		} else {
			try {
				CaseApply apply = caseApplyService.findOne(caseApplyId);
				if (ObjectHelper.isNotEmpty(apply)) {
					vo = new CaseApplyVo(apply);
					// 风险信息
					RiskInfomation riskInfomation = apply.getRiskInfo();

					if (ObjectHelper.isNotEmpty(riskInfomation)) {
						vo.setRiskInfoVo(new RiskInformationVo(apply.getRiskInfo()));
					}
					//查询终端名称
					if (ObjectHelper.isNotEmpty(apply.getTerminalId())) {
						CooperatorTerminal cooperatorTerminal = cooperatorTerminalService
								.findOne(apply.getTerminalId());
						if (ObjectHelper.isNotEmpty(cooperatorTerminal)) {
							vo.setTerminalIdName(cooperatorTerminal.getTerminalFullName());
						}
					}
				}else{
					map.put("errorMsg", "此案件不存在!");
				}
			} catch (BusinessException e) {
				e.printStackTrace();
				map.put("errorMsg", e.getMessage());
				logger.error("查询出错", e.getMessage());
			}
		}
		map.put("vo", vo);
		ModelAndView model = new ModelAndView("casemanage/datasurvey/riskinformation_edit");
		model.addAllObjects(map);
		return model;

	}

	/**
	 * 
	 * 风险信息保存
	 *
	 * @author laijun
	 * @date:2017年1月10日 下午9:42:11
	 * @param vo
	 * @param jsoncallBack
	 * @return
	 */
	@RequestMapping("/save")
	@UriKey(key = "com.zdsoft.finance.casemanage.datasurvey.riskinformation.save")
	@ResponseBody
	public ResponseMsg save(CaseApplyVo vo, String jsoncallBack) {
		ResponseMsg msg = new ResponseMsg();
		try {
			CaseApply caseApply = vo.toPO();
			// 风险信息
			RiskInfomation riskInfomation = vo.getRiskInfoVo().toPO();
			caseApply.setRiskInfo(riskInfomation);
			// 保存风险信息和案件基本信息
			CaseApply apply = riskInfomationService.saveOrUpdateCaseApplyAndRiskInfo(caseApply);
			msg.setId(apply.getId());
			msg.setResultStatus(ResponseMsg.SUCCESS);
			msg.setMsg("保存风险信息成功!");
		} catch (Exception e) {
			msg.setResultStatus(ResponseMsg.APP_ERROR);
			msg.setMsg("保存风险信息失败");
			logger.error("保存失败", e);
			e.printStackTrace();
		}
		return msg;
	}
}
