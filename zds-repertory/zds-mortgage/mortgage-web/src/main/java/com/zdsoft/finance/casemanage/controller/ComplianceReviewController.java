package com.zdsoft.finance.casemanage.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zdsoft.finance.casemanage.vo.ReviewInformationVo;
import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.finance.customer.entity.BeforePersonalCustomer;
import com.zdsoft.finance.customer.vo.BeforePersonalCustomerVo;
import com.zdsoft.finance.loan.entity.ReviewInformation;
import com.zdsoft.finance.loan.service.ReviewInformationService;
import com.zdsoft.finance.marketing.entity.CaseApply;
import com.zdsoft.finance.marketing.entity.CaseApplyBeforeCustomer;
import com.zdsoft.finance.marketing.entity.CaseMaterialPromise;
import com.zdsoft.finance.marketing.service.CaseApplyBeforeCustomerService;
import com.zdsoft.finance.marketing.service.CaseApplyService;
import com.zdsoft.finance.marketing.service.CaseMaterialPromiseService;
import com.zdsoft.finance.marketing.vo.CaseApplyVo;
import com.zdsoft.finance.marketing.vo.CaseMaterialPromiseVo;
import com.zdsoft.framework.core.common.dto.ResponseMsg;
import com.zdsoft.framework.core.common.util.ObjectHelper;
import com.zdsoft.framework.core.commweb.annotation.UriKey;
import com.zdsoft.framework.core.commweb.component.BaseController;
import com.zdsoft.framework.cra.annotation.Menu;

/** 	
* 版权所有：重庆正大华日软件有限公司	
* @Title: DataDispatchingController.java 	
* @Package com.zdsoft.finance.casemanage.controller 	
* @Description: 合规复核Controller	
* @author liuhuan 	
* @date 2017年1月13日 上午10:46:21 	
* @version V1.0 	
*/
@Controller
@RequestMapping("/complianceReview")
public class ComplianceReviewController extends BaseController{
	
	@Autowired
	private CaseApplyService caseApplyService;
	
	@Autowired
	private CaseApplyBeforeCustomerService caseApplyBeforeCustomerService;
	
	@Autowired
	private CaseMaterialPromiseService caseMaterialPromiseService;
	
	@Autowired
	private ReviewInformationService reviewInformationService;
	
	@RequestMapping("/initComplianceReview")
	@UriKey(key = "com.zdsoft.finance.casemanage.initComplianceReview")
	@Menu(resource = "com.zdsoft.finance.casemanage.initComplianceReview", label = "合规复核", group = "project", order = 4)
	public ModelAndView initComplianceReview(String caseApplyId, String processInstanceId, String businessKey){
		
		caseApplyId = "402892c459afaf700159b04e8fbe0007";
		ModelMap model = new ModelMap();
		CaseApply caseApply = null;
		List<CaseApplyBeforeCustomer> caseApplyBeforeCustomerList = null;
		CaseApplyBeforeCustomer caseApplyBeforeCustomer = null;
		BeforePersonalCustomerVo beforePersonalCustomerVo = null;
		List<CaseMaterialPromiseVo> caseMaterialPromiseVos = new ArrayList<CaseMaterialPromiseVo>();
		List<CaseMaterialPromise> caseMaterialPromiseList = null;
		try {
			//案件号
			caseApply = caseApplyService.findOne(caseApplyId);
			CaseApplyVo caseApplyVo = new CaseApplyVo(caseApply);
			model.put("caseApplyVo", caseApplyVo);
			//主借人
			caseApplyBeforeCustomerList = caseApplyBeforeCustomerService.queryByCaseApplyIdAndJoinType(caseApplyId, CaseApplyBeforeCustomer.MAIN_BORROW);
			if(ObjectHelper.isNotEmpty(caseApplyBeforeCustomerList) && caseApplyBeforeCustomerList.size() > 0){
				caseApplyBeforeCustomer = caseApplyBeforeCustomerList.get(0);
				beforePersonalCustomerVo = new BeforePersonalCustomerVo((BeforePersonalCustomer) caseApplyBeforeCustomer.getBeforeCustomer());
				model.put("beforePersonalCustomerVo", beforePersonalCustomerVo);
			}
			caseApplyBeforeCustomerList = null;
			//放款金额 
			//放款期限
			//已放款资金
			//待垫出
			//已收回金额
			//待回收金额
			
			//获取后补资料
			caseMaterialPromiseList = caseMaterialPromiseService.queryByCaseApplyId(caseApplyId);
			for(CaseMaterialPromise caseMaterialPromise : caseMaterialPromiseList){
				CaseMaterialPromiseVo caseMaterialPromiseVo = new CaseMaterialPromiseVo(caseMaterialPromise);
				caseMaterialPromiseVos.add(caseMaterialPromiseVo);
			}
			model.put("afterData", caseMaterialPromiseVos);
			//后补资料总数
			model.put("afterDataNumber", caseMaterialPromiseVos.size());
		} catch (BusinessException e) {
			logger.error("查询数据失败",e);
		} catch (Exception e) {
			logger.error("查询数据失败",e);
		}
		
		return new ModelAndView("/casemanage/complianceReview_view", model);
	}
	
	
	/**
	 * 获取复核信息
	 * @return
	 */
	@RequestMapping("/getReviewInformation")
	@UriKey(key="com.zdsoft.finance.casemanage.getReviewInformation")
	@ResponseBody
	public String getReviewInformation(String firstMark, String secondMark, String thirdMark, String jsoncallback){
		List<ReviewInformation> reviewInformationList = null;
		List<ReviewInformationVo> reviewInformationVos = new ArrayList<ReviewInformationVo>();
		ResponseMsg msg = new ResponseMsg();
		msg.setRows(reviewInformationVos);
		if(ObjectHelper.isEmpty(firstMark) && ObjectHelper.isEmpty(secondMark) && ObjectHelper.isEmpty(thirdMark)){
			try {
				reviewInformationList = reviewInformationService.findAllInfo();
				for(ReviewInformation reviewInformation : reviewInformationList){
					ReviewInformationVo reviewInformationVo = new ReviewInformationVo(reviewInformation);
					reviewInformationVos.add(reviewInformationVo);
				}
				msg.setMsg("抵押情况列表查询成功");
				msg.setResultStatus(ResponseMsg.SUCCESS);
				msg.setRows(reviewInformationVos); 
				return ObjectHelper.objectToJson(msg, jsoncallback);
			} catch (Exception e) {
				msg.setMsg("抵押情况列表查询失败");
				msg.setResultStatus(ResponseMsg.APP_ERROR);
				logger.error("查询数据失败", e);
			}
		}
		try {
			reviewInformationList = reviewInformationService.findAll(firstMark, secondMark, thirdMark);
			for(ReviewInformation reviewInformation : reviewInformationList){
				ReviewInformationVo reviewInformationVo = new ReviewInformationVo(reviewInformation);
				reviewInformationVos.add(reviewInformationVo);
			}
		} catch (Exception e) {
			msg.setMsg("抵押情况列表查询失败");
			msg.setResultStatus(ResponseMsg.APP_ERROR);
			logger.error("查询数据失败", e);
		}
		msg.setMsg("抵押情况列表查询成功");
		msg.setResultStatus(ResponseMsg.SUCCESS);
		msg.setRows(reviewInformationVos); 
		return ObjectHelper.objectToJson(msg, jsoncallback);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/saveComplianceReview")
	@UriKey(key="com.zdsoft.finance.casemanage.saveComplianceReview")
	@ResponseBody
	public void saveComplianceReview(HttpServletRequest request){
		//后补资料
		String number = request.getParameter("afterDataNumber");
		for(int i=0; i<Integer.parseInt(number); i++){
			String afterDataId = request.getParameter("afterData["+i+"].id");
			try {
				CaseMaterialPromise caseMaterialPromise = caseMaterialPromiseService.findOne(afterDataId);
				Long predictDate = Long.parseLong(request.getParameter("afterData["+i+"].predictDate"));
				caseMaterialPromise.setPredictDate(predictDate);
				caseMaterialPromise.setMo(request.getParameter("afterData["+i+"].mo"));
				caseMaterialPromiseService.updateEntity(caseMaterialPromise);
			} catch (BusinessException e) {
				logger.error("后补资料数据查询失败", e);
			} catch (Exception e){
				logger.error("后补资料数据保存失败", e);
			}
		}
	}
	
	
}
