package com.zdsoft.finance.finance.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.finance.customer.entity.BeforeCustomer;
import com.zdsoft.finance.marketing.entity.CaseApply;
import com.zdsoft.finance.marketing.entity.CaseApplyBeforeCustomer;
import com.zdsoft.finance.marketing.service.CaseApplyBeforeCustomerService;
import com.zdsoft.finance.marketing.service.CaseApplyService;
import com.zdsoft.finance.marketing.vo.CaseApplyVo;
import com.zdsoft.framework.core.common.dto.ResponseMsg;
import com.zdsoft.framework.core.common.util.ObjectHelper;
import com.zdsoft.framework.core.commweb.annotation.UriKey;
import com.zdsoft.framework.core.commweb.component.BaseController;
import com.zdsoft.framework.cra.annotation.Menu;
/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title:CostSettlementController.java
 * @Package:com.zdsoft.finance.finance.controller
 * @Description:费用结算
 * @author: xiangjx
 * @date:2017年2月22日 下午5:45:30
 * @version:v1.0
 */
@Controller
@RequestMapping("/cost")
public class CostSettlementController extends BaseController{

	
	@Autowired
	private CaseApplyService caseApplyService;
	
	@Autowired
	private CaseApplyBeforeCustomerService  caseApplyBeforeCustomerService;
	
	/**
	 * 
	 * @Title: caseApplyList 
	 * @Description: 案件列表
	 * @author xiangjx 
	 * @return
	 */
	@RequestMapping("/caseReceiptList")
    @UriKey(key = "com.zdsoft.finance.finance.case.caseReceiptList")
    @Menu(resource = "com.zdsoft.finance.finance.case.caseReceiptList", label = "费用结算管理", group = "finance", order = 11)
	public ModelAndView caseApplyList(){
		return new ModelAndView("finance/finIncome/caseReceipt_list");
	}
	
	/**
	 * 
	 * @Title: costSettlemenet 
	 * @Description: 案件结算
	 * @author xiangjx 
	 * @param caseApplyId
	 * @return
	 * @throws BusinessException
	 */
	@RequestMapping("/costSettlemenet")
    @UriKey(key = "com.zdsoft.finance.finance.case.costSettlemenet")
	public ModelAndView costSettlemenet(String caseApplyId) {
		ModelMap  model = new ModelMap();
		try {
			CaseApply caseApply = caseApplyService.findOne(caseApplyId);
			CaseApplyVo caseApplyVo = new CaseApplyVo(caseApply);
			
			//获取主借人
			List<BeforeCustomer> customerList=caseApplyBeforeCustomerService.findCustomerByCaseApplyIdAndJoinType(caseApply.getId(), CaseApplyBeforeCustomer.MAIN_BORROW);
			for(BeforeCustomer cust:customerList){
				caseApplyVo.setCustomerName(cust.getCustomerName());
			}
			
			model.addAttribute("caseApply", caseApplyVo);
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		return new ModelAndView("finance/finIncome/cost_settlement_view",model);
	}
	/**
	 * 
	 * @Title: finIncomeFirmSave 
	 * @Description: 案件结算确认
	 * @author xiangjx 
	 * @param caseApplyId
	 * @param status
	 * @return
	 * @throws BusinessException
	 */
	@RequestMapping("/costSettlemenetSave")
	@UriKey(key = "com.zdsoft.finance.finance.case.costSettlemenetSave")
	@ResponseBody
	public String finIncomeFirmSave(String caseApplyId,String status) {
		ResponseMsg msg = new ResponseMsg();
		try {
			//更新状态
			CaseApply fic = caseApplyService.findOne(caseApplyId);
			fic.setSettlementStatus(status);
			caseApplyService.updateEntity(fic);
			msg.setMsg("收费确认成功");
	        msg.setResultStatus(ResponseMsg.SUCCESS);
		} catch (Exception e) {
			logger.error("保存收费确认数据失败",e);
			msg.setMsg("收费确认失败");
			msg.setResultStatus(ResponseMsg.APP_ERROR);
		}
		return  ObjectHelper.objectToJson(msg);
	}
}
