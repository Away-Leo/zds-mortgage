package com.zdsoft.finance.casemanage.datasurvey.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.finance.marketing.entity.CaseApply;
import com.zdsoft.finance.marketing.service.CaseApplyService;
import com.zdsoft.framework.core.commweb.annotation.UriKey;
import com.zdsoft.framework.core.commweb.component.BaseController;

/** 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: AttachmentController.java 
 * @ClassName: AttachmentController 
 * @Description: 资调 - 附件
 * @author liuhuan 
 * @date 2017年2月27日 下午3:52:10 
 * @version V1.0 
 */ 
@RequestMapping("/casemanage/datasurvey/attachment")
@Controller
public class AttachmentInfoController extends BaseController{
	
	@Autowired
	private CaseApplyService caseApplyService;
	
	/** 
	 * @Title: loadCreditInfo 
	 * @Description: 加载附件列表
	 * @author liuhuan 
	 * @param caseApplyId 案件id
	 * @return  
	 */ 
	@RequestMapping("/load")
	@UriKey(key="com.zdsoft.finance.casemanage.datasurvey.attachment.load")
	public ModelAndView loadCreditInfo(String caseApplyId, String businessKey){
		ModelMap model = new ModelMap();
		model.put("caseApplyId", caseApplyId);
		try {
			CaseApply caseApply = caseApplyService.findOne(caseApplyId);
			model.put("productId", caseApply.getProductSubtypeId());
			model.put("linkCode", CaseApply.CASE_STATUS_DATASURVEY.substring(CaseApply.CASE_STATUS_DATASURVEY.length()-2, CaseApply.CASE_STATUS_DATASURVEY.length()));
//			model.put("businessId", businessKey); 不传businessId 显示整个资调阶段的附件
		} catch (BusinessException e) {
			logger.error("案件不存在",e.getMessage());
			model.put("errorMsg", "案件异常,请联系管理员");
			e.printStackTrace();
		}
		return new ModelAndView("casemanage/datasurvey/attachment_list",model);
	}
	
}	
