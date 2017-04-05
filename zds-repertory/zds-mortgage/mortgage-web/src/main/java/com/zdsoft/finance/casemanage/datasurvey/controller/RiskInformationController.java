package com.zdsoft.finance.casemanage.datasurvey.controller;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zdsoft.finance.businesssetting.entity.Questionnaire;
import com.zdsoft.finance.businesssetting.service.QuestionnaireService;
import com.zdsoft.finance.casemanage.datasurvey.entity.RiskInfomation;
import com.zdsoft.finance.casemanage.datasurvey.service.RiskInfomationService;
import com.zdsoft.finance.casemanage.datasurvey.vo.RiskInformationVo;
import com.zdsoft.finance.common.utils.BigDecimalCalculateTwo;
import com.zdsoft.finance.cooperator.entity.CooperatorTerminal;
import com.zdsoft.finance.cooperator.service.CooperatorTerminalService;
import com.zdsoft.finance.marketing.entity.CaseApply;
import com.zdsoft.finance.marketing.entity.HouseProperty;
import com.zdsoft.finance.marketing.entity.PledgeInfo;
import com.zdsoft.finance.marketing.service.CaseApplyService;
import com.zdsoft.finance.marketing.service.HousePropertyService;
import com.zdsoft.finance.marketing.service.PledgeInfoService;
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
	@Autowired
	private HousePropertyService housePropertyService;
	@Autowired
	private PledgeInfoService pledgeInfoService;
	@Autowired
	private QuestionnaireService questionnaireService;
	@Autowired
	private RiskInfoWithQuestion riskInfoWithQuestion;
	/** 
	 * @Title: getPercentage 
	 * @Description: 计算贷款成数 评估价抵押成数
	 * @author liuhuan 
	 * @param caseApplyId 案件id
	 * @param applyAmount 申请金额
	 * @return 	loanNumber为 贷款成数的key 
	 * 			assessedPriceMortgage为 评估价抵押成数的key
	 * @throws Exception  
	 */ 
	@SuppressWarnings("unused")
	private Map<String, Object> getPercentage(String caseApplyId, BigDecimal applyAmount) throws Exception{
		Map<String, Object> result = new HashMap<String, Object>();
		
		List<PledgeInfo> pledgeInfoList = null;
		List<HouseProperty> housePropertyList = null;
		HouseProperty houseProperty = null;
		PledgeInfo pledgeInfo = null;
		
		BigDecimal loanNumber = new BigDecimal(0.00);//贷款成数
		BigDecimal assessedPriceMortgage = new BigDecimal(0.00);//评估价抵押成数
		
		housePropertyList = housePropertyService.findByCaseApplyId(caseApplyId);
		if(ObjectHelper.isEmpty(housePropertyList) || housePropertyList.size() < 0){
			return result;
		}
		houseProperty = housePropertyList.get(0);
		pledgeInfoList = pledgeInfoService.findByHouseId(houseProperty.getId());
		if(ObjectHelper.isEmpty(pledgeInfoList) || pledgeInfoList.size() < 0){
			return result;
		}
		pledgeInfo = pledgeInfoList.get(0);
		
		BigDecimal synthesizePrice = houseProperty.getSynthesizePrice();//综合评估价
		BigDecimal controlPrice = houseProperty.getControlPrice();//风控核定价
		
		//普通抵押
		if(ObjectHelper.isEquals(pledgeInfo.getType(), PledgeInfo.TYPE_BASE)){
			//贷款成数
			if(ObjectHelper.isNotEmpty(controlPrice) && controlPrice.compareTo(BigDecimal.ZERO) == 1){
				loanNumber = BigDecimalCalculateTwo.add(applyAmount, pledgeInfo.getFrontLoanBalance());
				loanNumber = BigDecimalCalculateTwo.div(loanNumber, controlPrice, 2);
			}
			
			//评估价抵押成数
			if(ObjectHelper.isNotEmpty(synthesizePrice) && synthesizePrice.compareTo(BigDecimal.ZERO) == 1){
				assessedPriceMortgage = BigDecimalCalculateTwo.add(applyAmount, pledgeInfo.getFrontLoanBalance());
				assessedPriceMortgage = BigDecimalCalculateTwo.div(loanNumber, synthesizePrice, 2);
			}
		}
		//最高额抵押
		if(ObjectHelper.isEquals(pledgeInfo.getType(), PledgeInfo.TYPE_BASE)){
			//贷款成数
			if(ObjectHelper.isNotEmpty(controlPrice) && controlPrice.compareTo(BigDecimal.ZERO) == 1){
				loanNumber = BigDecimalCalculateTwo.add(applyAmount, pledgeInfo.getFrontLoanBalance());
				loanNumber = BigDecimalCalculateTwo.div(loanNumber, controlPrice, 2);
			}
			
			//评估价抵押成数
			if(ObjectHelper.isNotEmpty(synthesizePrice) && synthesizePrice.compareTo(BigDecimal.ZERO) == 1){
				assessedPriceMortgage = BigDecimalCalculateTwo.add(applyAmount, pledgeInfo.getFrontLoanBalance());
				assessedPriceMortgage = BigDecimalCalculateTwo.div(loanNumber, synthesizePrice, 2);
			}
		}
		
		result.put("loanNumber", loanNumber);
		result.put("assessedPriceMortgage", assessedPriceMortgage);
		return result;
	}
	

	/** 
	 * @Title: edit 
	 * @Description: 风险信息编辑
	 * @author liuhuan 
	 * @param caseApplyId 案件id
	 * @return  
	 */ 
	@RequestMapping(value = "/editRiskInformation")
	@UriKey(key = "com.zdsoft.finance.casemanage.datasurvey.riskinformation.editRiskInformation")
	public ModelAndView editRiskInformation(String caseApplyId) {
		ModelMap map = null;
		try {
			map = initRiskInformationData(caseApplyId);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("风险信息页面数据查询失败：", e);
		}
		return new ModelAndView("casemanage/datasurvey/riskinformation_edit",map);
	}

	/** 
	 * @Title: view 
	 * @Description: 风险信息查看
	 * @author zjx 
	 * @param caseApplyId 案件id
	 * @return  
	 */ 
	@RequestMapping(value = "/viewRiskInformation")
	@UriKey(key = "com.zdsoft.finance.casemanage.datasurvey.riskinformation.viewRiskInformation")
	public ModelAndView viewRiskInformation(String caseApplyId) {
		ModelMap map = new ModelMap();
			try {
				map = initRiskInformationData(caseApplyId);
			} catch(Exception e){
				map.put("errorMsg", e.getMessage());
				logger.error("dto错误",e.getMessage());
				e.printStackTrace();
			}
		return new ModelAndView("casemanage/datasurvey/riskinformation_view",map);

	}
	
	/** 
	 * @Title: initRiskInformationData 
	 * @Description: 根据传入参数查询出风险信息页面数据
	 * @author zjx 
	 * @param caseApplyId 案件ID
	 * @param vo 案件VO
	 * @param map modelMap
	 * @throws Exception  
	 */ 
	private ModelMap initRiskInformationData(String caseApplyId){
		ModelMap map = new ModelMap();
		try {
			CaseApply apply = caseApplyService.findOne(caseApplyId);
			if (ObjectHelper.isNotEmpty(apply)) {
				CaseApplyVo vo = new CaseApplyVo(apply);
				
				RiskInformationVo riskVo = new RiskInformationVo();
				// 风险信息
				RiskInfomation riskInfomation = apply.getRiskInfo();
				if (ObjectHelper.isNotEmpty(riskInfomation)) {
					riskVo = new RiskInformationVo(apply.getRiskInfo());
				}
				riskVo = getControlEvaluation(riskVo,caseApplyId);
				vo.setRiskInfoVo(riskVo);
				//查询终端名称
				if (ObjectHelper.isNotEmpty(apply.getTerminalId())) {
					CooperatorTerminal cooperatorTerminal = cooperatorTerminalService
							.findOne(apply.getTerminalId());
					if (ObjectHelper.isNotEmpty(cooperatorTerminal)) {
						vo.setTerminalIdName(cooperatorTerminal.getTerminalFullName());
					}
				}
				map.put("vo", vo);
			}else{
				map.put("errorMsg", "此案件不存在!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			map.put("errorMsg", "异常,请联系管理员！");
		}
		return map;
	}


	/** 
	 * @Title: getControlEvaluation 
	 * @Description: 获取风险评价
	 * @author liuhuan 
	 * @param vo 风险信息vo
	 * @param caseApplyId 案件id
	 * @return  
	 */ 
	private RiskInformationVo getControlEvaluation(RiskInformationVo vo, String caseApplyId){
		try {
			if(ObjectHelper.isEmpty(caseApplyId)){
				logger.error("系统异常");
				return vo;
			}
			//资调环节问卷的答卷
			List<Questionnaire> questionList = questionnaireService.findByCaseApplyIdAndSceneTypeCode(caseApplyId, "YWDM0010202");
			if(ObjectHelper.isEmpty(questionList) || questionList.size() <=0){
				logger.info("智能问卷为空");
				return vo;
			}
			//此处问题id固定,根据id查询出对应问题,然后判断问题答案.
			Map<String,String> map = riskInfoWithQuestion.getRiskControlEvaluation(questionList, caseApplyId);
			vo.setPledgeReview(map.get("pledgeReview"));
			vo.setBusinessAnalysis(map.get("businessAnalysis"));	
			vo.setWorkSituation(map.get("workSituation"));
			vo.setSpecialSituation(map.get("specialSituation"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return vo;
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
