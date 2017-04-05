package com.zdsoft.finance.finance.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zdsoft.bpm.annotation.client.FinishJob;
import com.zdsoft.bpm.annotation.client.ManualJob;
import com.zdsoft.finance.businesssetting.entity.OrgBankAccount;
import com.zdsoft.finance.businesssetting.service.OrgBankAccountService;
import com.zdsoft.finance.casemanage.datasurvey.entity.FeeInfomation;
import com.zdsoft.finance.casemanage.datasurvey.service.FeeInfomationService;
import com.zdsoft.finance.common.base.QueryObj;
import com.zdsoft.finance.common.utils.TimeUtil;
import com.zdsoft.finance.finance.entity.RequestFunds;
import com.zdsoft.finance.finance.service.RequestCommissionService;
import com.zdsoft.finance.finance.vo.RequestFundsDetailVo;
import com.zdsoft.finance.finance.vo.RequestFundsVo;
import com.zdsoft.framework.core.common.dto.ResponseMsg;
import com.zdsoft.framework.core.common.dto.ResultDto;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.PageRequest;
import com.zdsoft.framework.core.common.util.ObjectHelper;
import com.zdsoft.framework.core.commweb.annotation.UriKey;
import com.zdsoft.framework.core.commweb.component.BaseController;
import com.zdsoft.framework.cra.annotation.Menu;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * 
 * @Title:RequestCommissionController.java
 * @Package:com.zdsoft.finance.finance.controller
 * @Description:请佣管理
 * @author: xiangjx
 * @date:2017年3月2日 上午10:29:24
 * @version:v1.0
 */
@Controller
@RequestMapping("requestCommission")
public class RequestCommissionController extends BaseController {

	@Autowired
	private RequestCommissionService requestCommissionService;
	
    @Autowired
	private FeeInfomationService feeInfomationService;
    
    
    @Autowired
    private OrgBankAccountService orgBankAccountService;
    
    @Autowired
    private com.zdsoft.essential.client.service.CED CED;

	/**
	 * 
	 * @Title: caseList 
	 * @Description: 支佣管理
	 * @author xiangjx 
	 * @return
	 */
	@RequestMapping("/caseList")
	@UriKey(key = "com.zdsoft.finance.requestCommission.caseList")
	@Menu(resource = "com.zdsoft.finance.requestCommission.caseList", label = "支佣管理", group = "finance", order = 13)
	public ModelAndView caseList() {
		return new ModelAndView("/finance/requestCommission/requestCommission_case_list");
	}

	/**
	 * 
	 * @Title: scanRequestCommissionPrcoessView 
	 * @Description: 支佣请款审批页面
	 * @author xiangjx 
	 * @param projectId
	 * @param processInstanceId
	 * @param businessKey
	 * @return
	 */
	@RequestMapping(value = "/scanRequestCommissionPrcoessView")
	@UriKey(key = "com.zdsoft.finance.requestCommission.scanRequestCommissionPrcoessView")
	@ManualJob(resource = "com.zdsoft.finance.requestCommission.scanRequestCommissionPrcoessView", label = "支佣请款审批页面")
	@FinishJob(resource = "com.zdsoft.finance.requestCommission.scanRequestCommissionPrcoessView", businessId = "businessKey", projectId = "projectId")
	public ModelAndView scanRequestCommissionPrcoessView(String projectId, String processInstanceId,String businessKey) {
		ModelMap model = new ModelMap();
		model.put("projectId", projectId);
		model.put("processInstanceId", processInstanceId);
		model.put("businessKey", businessKey);
		try {
			RequestFunds funds = requestCommissionService.findOne(businessKey);
			RequestFundsVo fundsVo = new RequestFundsVo(funds);
			model.put("funds", fundsVo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("/finance/requestCommission/requestCommission_prcoess_view", model);
	}
	
	
	/**
	 * 
	 * @Title: scanRequestCommissionView 
	 * @Description: 支佣查看
	 * @author xiangjx 
	 * @param projectId
	 * @param processInstanceId
	 * @param businessKey
	 * @return
	 */
	@RequestMapping(value = "/scanRequestCommissionView")
	@UriKey(key = "com.zdsoft.finance.requestCommission.scanRequestCommissionView")
	public ModelAndView scanRequestCommissionView(String projectId, String processInstanceId,String businessKey) {
		ModelMap model = new ModelMap();
		model.put("projectId", projectId);
		model.put("processInstanceId", processInstanceId);
		model.put("businessKey", businessKey);
		try {
			RequestFunds funds = requestCommissionService.findOne(businessKey);
			RequestFundsVo fundsVo = new RequestFundsVo(funds);
			model.put("funds", fundsVo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("/finance/requestCommission/requestCommission_view", model);
	}

	/**
	 * 
	 * @Title: caseFeeList 
	 * @Description: 案件请款列表
	 * @author xiangjx 
	 * @param caseApplyId
	 * @param request
	 * @param pageRequest
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/caseFeeList")
	@UriKey(key = "com.zdsoft.finance.requestCommission.caseFeeList")
	@ResponseBody
	public ResponseMsg caseFeeList(HttpServletRequest request, PageRequest pageRequest) {
		ResponseMsg msg = new ResponseMsg();
		List<RequestFundsDetailVo> feeList=new ArrayList<RequestFundsDetailVo>();
		List<QueryObj> li = (List<QueryObj>) request.getAttribute("listObj");
		pageRequest.setRows(100);
		Page<FeeInfomation> pageList= feeInfomationService.findByHqlConditions(pageRequest, li);
		for(FeeInfomation fee:pageList.getRecords()){
			RequestFundsDetailVo feeVo=new RequestFundsDetailVo();
			BeanUtils.copyProperties(fee, feeVo);
			feeVo.setFeeId(fee.getId());
//			feeVo.setReqBigAmount(reqBigAmount.subtract(ObjectHelper.isEmpty(feeVo.getPaidAmount())?BigDecimal.ZERO:feeVo.getPaidAmount()));
			feeVo.setReqFundsAmount(feeVo.getReceivedAmount());
			feeVo.setCaseApplyCode(fee.getCaseApply().getCaseApplyCode());
			feeVo.setCaseApplyId(fee.getCaseApply().getId());
			feeList.add(feeVo);
		}
		msg.setResultStatus(ResponseMsg.SUCCESS);
        msg.setRows(feeList);
        msg.setTotal(Long.parseLong(""+feeList.size()));
        return msg;
	}

	/**
	 * 
	 * @Title: saveOrSubmitRequestCommission 
	 * @Description: 支佣请款保存
	 * @author xiangjx 
	 * @param commissionVo
	 * @param feeList
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/saveOrSubmitRequestCommission")
	@UriKey(key = "com.zdsoft.finance.requestCommission.saveOrSubmitRequestCommission")
	public ResponseMsg saveOrSubmitRequestCommission(RequestFundsVo commissionVo, String feeList) {
		ResponseMsg msg = new ResponseMsg();
		try {
			RequestFunds com = commissionVo.toPo();
			com = requestCommissionService.saveOrSubmitRequestCommission(com, feeList);
			msg.setId(com.getId());
			msg.setMsg(com.getMsg());
			msg.setResultStatus(ResultDto.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			msg.setResultStatus(ResultDto.APP_ERROR);
		}
		return msg;
	}

	/**
	 * 
	 * @Title: requestCommissionEditPage 
	 * @Description: 进入支佣请款页面
	 * @author xiangjx 
	 * @param caseApplyId
	 * @param reqFundsId
	 * @return
	 */
	@RequestMapping("/requestCommissionEditPage")
	@UriKey(key = "com.zdsoft.finance.requestCommission.requestCommissionEditPage")
	public ModelAndView requestCommissionEditPage(String[] caseApplyId,String businessKey,String busiFormId,String reqType) {
		String viewName = "/finance/requestCommission/requestCommission_edit";
		ModelMap model = new ModelMap();
    	try {
    		RequestFundsVo fundsVo=new RequestFundsVo();
    		StringBuilder sb=new StringBuilder();//案件id
    		boolean bool=false;
    		if(ObjectHelper.isEmpty(businessKey)){
    			for(String caseId:caseApplyId){
        			if(bool){
        				sb.append(",");
        			}
        			sb.append(caseId);
        			bool=true;
        		}
    			model.put("caseApplyId", sb.toString());
    			fundsVo.setBillCode("FH"+TimeUtil.getCurrentDate());
    			fundsVo.setReqFundsDate(TimeUtil.getCurrentDateInteger().longValue());
    			String orgId=CED.getLoginUser().getOrgId();
    			OrgBankAccount account=orgBankAccountService.findByOrgId(orgId);
    			if(ObjectHelper.isNotEmpty(account)){
    				fundsVo.setAccountName(account.getProceedsAccount());
    				fundsVo.setBankName(account.getBankName());
    				fundsVo.setBankAccount(account.getProceedsNumber());
    			}
    			fundsVo.setReqType(reqType);
    		}else{
    			RequestFunds funds=requestCommissionService.findOne(businessKey);
    			fundsVo=new RequestFundsVo(funds);
    		}
    		model.put("funds", fundsVo);
    	} catch (Exception e) {
			e.printStackTrace();
		}
    	return new ModelAndView(viewName, model);
	}

}
