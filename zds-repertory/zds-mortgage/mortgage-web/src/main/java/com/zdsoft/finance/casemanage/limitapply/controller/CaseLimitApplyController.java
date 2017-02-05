package com.zdsoft.finance.casemanage.limitapply.controller;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zdsoft.essential.client.service.CED;
import com.zdsoft.finance.casemanage.limitapply.entity.CaseLimitApply;
import com.zdsoft.finance.casemanage.limitapply.entity.FundPlanInfo;
import com.zdsoft.finance.casemanage.limitapply.service.CaseLimitApplyService;
import com.zdsoft.finance.casemanage.limitapply.service.FundPlanInfoService;
import com.zdsoft.finance.casemanage.limitapply.vo.CaseLimitApplyVo;
import com.zdsoft.finance.casemanage.limitapply.vo.CaseLimitPlanInfoVo;
import com.zdsoft.finance.casemanage.limitapply.vo.FundPlanInfoVo;
import com.zdsoft.finance.common.base.QueryObj;
import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.finance.marketing.entity.CaseApply;
import com.zdsoft.finance.marketing.service.CaseApplyService;
import com.zdsoft.finance.marketing.vo.CaseApplyVo;
import com.zdsoft.framework.core.common.dto.ResponseMsg;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.PageRequest;
import com.zdsoft.framework.core.common.util.DateHelper;
import com.zdsoft.framework.core.common.util.ObjectHelper;
import com.zdsoft.framework.core.commweb.annotation.UriKey;
import com.zdsoft.framework.core.commweb.component.BaseController;
import com.zdsoft.framework.cra.annotation.Menu;

/**
 * 版权所有：重庆正大华日软件有限公司
 * 
 * @Title:CaseLimitApplyController.java
 * @Package:com.zdsoft.finance.casemanage.limitapply.controller
 * @Description:案件额度申请控制器
 * @author: xiongpan
 * @date:2017年1月7日 上午10:02:10
 * @version:v1.0
 */
@Controller
@RequestMapping("limitApply")
public class CaseLimitApplyController extends BaseController {

	@Autowired
	private CaseApplyService caseApplySerivce;

	@Autowired
	private CaseLimitApplyService caseLimitApplyService;
	
	@Autowired
	private FundPlanInfoService fundPlanInfoService;

	@Autowired
	private CED CED;

	/**
	 * 案件额度申请列表入口
	 * 
	 * @return 额度申请列表页面
	 */
	@RequestMapping("/initCaseLimitApply")
	@UriKey(key = "com.zdsoft.finance.casemanage.initCaseLimitApply")
	@Menu(resource = "com.zdsoft.finance.casemanage.initCaseLimitApply", label = "额度申请列表", group = "project", order = 1)
	public ModelAndView initCaseLimitApply() {
		return new ModelAndView("/casemanage/limitapply/case_limit_apply_list");
	}

	/**
	 * 案件额度申请查询列表
	 * 
	 * @param pageable
	 * @param jsoncallback
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/caseLimitApplyList")
	@UriKey(key = "com.zdsoft.finance.casemanage.limitApply.caseLimitApplyList")
	@ResponseBody
	public String caseLimitApplyList(HttpServletRequest request, PageRequest pageable, String jsoncallback) throws Exception {
		// 获取查询参数
		@SuppressWarnings("unchecked")
		List<QueryObj> queryObjs = (List<QueryObj>) request.getAttribute("listObj");
		// 获取案件申请配置信息
		Page<Map<String, Object>> caseLimitApply = caseApplySerivce.findPageCaseLimitApply(pageable, queryObjs);
		ResponseMsg msg = new ResponseMsg();
		msg.setMsg("列表查询成功");
		msg.setResultStatus(ResponseMsg.SUCCESS);
		msg.setTotal(caseLimitApply.getTotalRows());
		msg.setRows(caseLimitApply.getRecords());
		return ObjectHelper.objectToJson(msg, jsoncallback);
	}

	/**
	 * 跳转到额度申请页面
	 * 
	 * @return
	 * @throws BusinessException
	 */
	@RequestMapping("/addCaseLimitApply")
	@UriKey(key = "com.zdsoft.finance.casemanage.limitApply.addCaseLimitApply")
	@ResponseBody
	public ModelAndView addCaseLimitApply(String id, String customerName, String pledgeType) throws Exception {
		Map<String, Object> applyLimitModel = new HashMap<String, Object>();
		CaseApplyVo caseApplyVo = new CaseApplyVo();
		CaseApply caseApply = caseApplySerivce.findOne(id);
		BeanUtils.copyProperties(caseApply, caseApplyVo);
		caseApplyVo.setCustomerName(customerName);
		caseApplyVo.setPledgeType(pledgeType);
		// 调用接口,返回资金计划以及是专户资金还是备付资金,贷款发放账户和贷款回款账户信息,若是备付金还得返回可匹配的额度.
		// FundPlanInfo fundPlanInfo =
		// caseApplySerivce.getFundPlanInfo(caseApply.getProductSubtype(),
		// caseApply.getAssessedPriceMortgage(), caseApply.getApplyAmount());
		FundPlanInfo fundPlanInfo = caseApplySerivce.findOne(id).getFundPlanInfo();
		if (ObjectHelper.isNotEmpty(fundPlanInfo)) {
			FundPlanInfoVo fundPlanInfoVo = new FundPlanInfoVo();
			BeanUtils.copyProperties(fundPlanInfo, fundPlanInfoVo);
			applyLimitModel.put("fundPlanInfoVo", fundPlanInfoVo);
		}
		caseApplyVo.setCurrentApplierId(CED.getLoginUser().getId());
		caseApplyVo.setCurrentApplierName(CED.getLoginUser().getEmpNm());

		applyLimitModel.put("caseApplyVo", caseApplyVo);
		return new ModelAndView("/casemanage/limitapply/case_limit_apply_add", applyLimitModel);
	}

	/**
	 * 获取本案件的申请记录
	 * 
	 * @param id
	 * @param jsoncallback
	 * @return
	 */
	@RequestMapping("/getTheCaseLimitApplyHistory")
	@UriKey(key = "com.zdsoft.finance.casemanage.limitApply.getTheCaseLimitApplyHistory")
	@ResponseBody
	public String getTheCaseLimitApplyHistory(String id, String jsoncallback) {
		List<CaseLimitApply> caseLimitApplys = caseLimitApplyService.findByCaseApplyId(id);
		ResponseMsg msg = new ResponseMsg();
		msg.setMsg("列表查询成功");
		msg.setResultStatus(ResponseMsg.SUCCESS);
		msg.setTotal((long) caseLimitApplys.size());
		msg.setRows(caseLimitApplys);
		return ObjectHelper.objectToJson(msg, jsoncallback);
	}

	/**
	 * 跳转到详情页面
	 * 
	 * @return
	 * @throws BusinessException
	 */
	@RequestMapping("/detailsCaseLimitApply")
	@UriKey(key = "com.zdsoft.finance.casemanage.limitApply.detailsCaseLimitApply")
	@ResponseBody
	public ModelAndView detailsCaseLimitApply(String id, String customerName, String pledgeType)
			throws BusinessException {
		Map<String, Object> applyLimitModel = new HashMap<>();
		CaseApply caseApply = caseApplySerivce.findOne(id);
		CaseApplyVo caseApplyVo = new CaseApplyVo();
		BeanUtils.copyProperties(caseApply, caseApplyVo);
		FundPlanInfo fundPlanInfo = caseApply.getFundPlanInfo();
		if(ObjectHelper.isNotEmpty(fundPlanInfo)){
			FundPlanInfoVo fundPlanInfoVo = new FundPlanInfoVo();
			BeanUtils.copyProperties(fundPlanInfo, fundPlanInfoVo);
			applyLimitModel.put("fundPlanInfoVo", fundPlanInfoVo);
		}
		caseApplyVo.setCustomerName(customerName);
		caseApplyVo.setPledgeType(pledgeType);

		CaseLimitApply caseLimitApply = caseLimitApplyService.findByCaseApplyId(id).get(0);
		if (ObjectHelper.isNotEmpty(caseLimitApply)) {
			CaseLimitApplyVo caseLimitApplyVo = new CaseLimitApplyVo();
			BeanUtils.copyProperties(caseLimitApply, caseLimitApplyVo);
			applyLimitModel.put("caseLimitApplyVo", caseLimitApplyVo);
		}
		applyLimitModel.put("caseApplyVo", caseApplyVo);
		return new ModelAndView("/casemanage/limitapply/case_limit_apply_details", applyLimitModel);
	}

	/**
	 * 保存额度申请信息
	 */
	@RequestMapping("/saveCaseLimitApply")
	@UriKey(key = "com.zdsoft.finance.casemanage.limitApply.saveCaseLimitApply")
	@ResponseBody
	public ResponseMsg saveCaseLimitApply(CaseLimitPlanInfoVo caseLimitPlanInfoVo, String fundPlanName, String caseApplyId,
			String isPrepareAccount, BigDecimal loanOutAccountAmount) {
		ResponseMsg msg = new ResponseMsg();
		try {
			CaseApply caseApply = caseApplySerivce.findOne(caseApplyId);
			CaseLimitApplyVo caseLimitApplyVo = caseLimitPlanInfoVo.getCaseLimitApplyVo();
			FundPlanInfoVo fundPlanInfoVo = caseLimitPlanInfoVo.getFundPlanInfoVo();
			caseApply.setActualApplyAmount(caseLimitApplyVo.getCurrentApplyLimit());
			caseLimitApplyVo.setApplyDate(DateHelper.dateToLong(new Date(), "yyyyMMddHHssmm"));
			if (ObjectHelper.isNotEmpty(fundPlanInfoVo.getLoanOutAccountAmount())
					&& fundPlanInfoVo.getLoanOutAccountAmount().compareTo(caseLimitApplyVo.getCurrentApplyLimit()) >= 0) {
				// 如果贷款发放账户是备付金账户
				if (CED.loadSimpleCodeNameByFullCode(fundPlanInfoVo.getIsPrepareAccount()).equals("YWDM0049002")) {
					fundPlanInfoVo.setIsGetPrepareLimit("YWDM0049002");
				}
				caseApply.setActualLimitStatus("YWDM0051058");
				caseLimitApplyVo.setAllotDate(DateHelper.dateToLong(new Date(), "yyyyMMddHHssmm"));
			} else {
				caseApply.setActualLimitStatus("YWDM0051057");
			}
			caseLimitApplyVo.setFundPlanName(fundPlanName);
			CaseLimitApply caseLimitApply = new CaseLimitApply();
			BeanUtils.copyProperties(caseLimitApplyVo, caseLimitApply);
			FundPlanInfo fundPlanInfo = new FundPlanInfo();
			BeanUtils.copyProperties(fundPlanInfoVo, fundPlanInfo);
			caseApply.setFundPlanInfo(fundPlanInfo);
			
			fundPlanInfoService.saveOrUpdateEntity(fundPlanInfo);
			caseLimitApplyService.saveEntity(caseLimitApply);
			caseApplySerivce.updateEntity(caseApply);
			
			msg.setResultStatus(ResponseMsg.SUCCESS);
			msg.setMsg("申请成功");
		} catch (Exception e) {
			logger.error("CED错误", e.getMessage());
			e.printStackTrace();
			logger.error("caseLimitApply保存失败", e);
			msg.setResultStatus(ResponseMsg.APP_ERROR);
			msg.setMsg("系统内部错误，请查看日志");
		}
		return msg;
	}

	/**
	 * 取消额度申请
	 */
	@RequestMapping("/cancelCaseLimitApply")
	@UriKey(key = "com.zdsoft.finance.casemanage.limitApply.cancelCaseLimitApply")
	@ResponseBody
	public ResponseMsg cancleCaseLimitApply(String id) {
		ResponseMsg msg = new ResponseMsg();
		try {
		CaseApply caseApply = caseApplySerivce.findOne(id);
		caseApply.setActualApplyAmount(null);
		caseApply.setActualLimitStatus("YWDM0051056");
		caseApply.setFundPlanInfo(null);
		caseApplySerivce.updateEntity(caseApply);
		
		CaseLimitApply caseLimitApply = caseLimitApplyService.findByCaseApplyId(id).get(0);
		if(ObjectHelper.isNotEmpty(caseLimitApply)){
			caseLimitApply.setCancelDate(DateHelper.dateToLong(new Date(), "yyyyMMddHHssmm"));
			caseLimitApply.setLimitCancelEmpName(CED.getLoginUser().getEmpNm());
			caseLimitApplyService.updateEntity(caseLimitApply);
		}
			msg.setResultStatus(ResponseMsg.SUCCESS);
			msg.setMsg("取消额度申请成功");
		} catch (Exception e) {
			logger.error("CED错误", e.getMessage());
			e.printStackTrace();
			logger.error("caseLimitApply或caseApply修改失败", e);
			msg.setResultStatus(ResponseMsg.APP_ERROR);
			msg.setMsg("系统内部错误，请查看日志");
		}
		return msg;
	}

}
