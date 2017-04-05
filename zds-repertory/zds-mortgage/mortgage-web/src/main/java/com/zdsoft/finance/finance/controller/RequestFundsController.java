package com.zdsoft.finance.finance.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.zdsoft.essential.client.aop.annotation.DataAuthResource;
import com.zdsoft.essential.dto.permission.DataOperPermDto;
import com.zdsoft.finance.businesssetting.entity.OrgBankAccount;
import com.zdsoft.finance.businesssetting.service.OrgBankAccountService;
import com.zdsoft.finance.casemanage.datasurvey.entity.FeeInfomation;
import com.zdsoft.finance.casemanage.datasurvey.service.FeeInfomationService;
import com.zdsoft.finance.casemanage.datasurvey.vo.FeeInfomationVo;
import com.zdsoft.finance.common.base.QueryObj;
import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.finance.common.utils.ParameterUtil;
import com.zdsoft.finance.common.utils.TimeUtil;
import com.zdsoft.finance.finance.entity.RequestFunds;
import com.zdsoft.finance.finance.entity.RequestFundsDetail;
import com.zdsoft.finance.finance.service.RequestFundsDetailService;
import com.zdsoft.finance.finance.service.RequestFundsService;
import com.zdsoft.finance.finance.vo.RequestFundsDetailVo;
import com.zdsoft.finance.finance.vo.RequestFundsVo;
import com.zdsoft.finance.marketing.entity.CaseApply;
import com.zdsoft.finance.marketing.service.CaseApplyService;
import com.zdsoft.finance.marketing.vo.CaseApplyVo;
import com.zdsoft.framework.core.common.dto.ResponseMsg;
import com.zdsoft.framework.core.common.dto.ResultDto;
import com.zdsoft.framework.core.common.exception.AppException;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.PageRequest;
import com.zdsoft.framework.core.common.util.ObjectHelper;
import com.zdsoft.framework.core.common.util.StoreHelper;
import com.zdsoft.framework.core.commweb.annotation.UriKey;
import com.zdsoft.framework.core.commweb.component.BaseController;
import com.zdsoft.framework.cra.annotation.Menu;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: RequestFundsController.java 
 * @ClassName: RequestFundsController 
 * @Description: 费用请款Controller
 * @author jincheng 
 * @date 2017年2月8日 下午4:54:31 
 * @version V1.0
 */
@Controller
@RequestMapping("requestFunds")
public class RequestFundsController extends BaseController {
	 
    @Autowired
    private RequestFundsService requestFundsService;
    
    @Autowired
    private RequestFundsDetailService requestFundsDetailService;
    
    @Autowired
	private FeeInfomationService feeInfomationService;
    
    @Autowired
    private CaseApplyService caseApplyService;
    
    @Autowired
    private OrgBankAccountService orgBankAccountService;
    
    @Autowired
    private com.zdsoft.essential.client.service.CED CED;
    
 
    //TODO -----------------------------------------------------------------请款管理-------------------------------------------------------------------------------------
    /**
     * @Title: interviewList 
     * @Description: 费用请款管理
     * @author jincheng 
     * @return
     */
    @RequestMapping("/caseList")
	@UriKey(key = "com.zdsoft.finance.requestFunds.caseList")
	@Menu(resource = "com.zdsoft.finance.requestFunds.caseList", label = "费用请款管理", group = "finance", order =4)
	public ModelAndView caseList() {
		return new ModelAndView("/finance/requestFunds/requestFunds_case_list");
	}
    
    /**
     * @Title: feeRequestFundsList 
     * @Description: 费用请款单查询
     * @author jincheng 
     * @return
     */
    @RequestMapping("/feeRequestFundsList")
   	@UriKey(key = "com.zdsoft.finance.requestFunds.feeRequestFundsList")
   	@Menu(resource = "com.zdsoft.finance.requestFunds.feeRequestFundsList", label = "费用请款单查询", group = "finance", order =5)
   	public ModelAndView feeRequestFundsList() {
   		return new ModelAndView("/finance/requestFunds/requestFunds_list");
   	}
    
    /**
     * @Title: commonCooperSelector 
     * @Description: 选择合作方
     * @author jincheng 
     * @return
     */
    @RequestMapping("/commonCooperSelector")
	@UriKey(key = "com.zdsoft.finance.requestFunds.commonCooperSelector")
	public ModelAndView commonCooperSelector() {
		return new ModelAndView("/common/common_cooper_selector");
	}
    
    /**
     * @Title: caseFeeList 
     * @Description: 获取案件费用列表
     * @author jincheng 
     * @param caseApplyId
     * @return
     */
    @SuppressWarnings("unchecked")
	@RequestMapping(value="/caseFeeList")
	@UriKey(key = "com.zdsoft.finance.requestFunds.caseFeeList")
	@ResponseBody
	public ResponseMsg  caseFeeList(HttpServletRequest request,PageRequest pageRequest){
			ResponseMsg msg = new ResponseMsg();
			List<RequestFundsDetailVo> feeList=new ArrayList<RequestFundsDetailVo>();
			List<QueryObj> li = (List<QueryObj>) request.getAttribute("listObj");
			pageRequest.setRows(100);
			Page<FeeInfomation> pageList= feeInfomationService.findByHqlConditions(pageRequest, li);
			for(FeeInfomation fee:pageList.getRecords()){
				RequestFundsDetailVo feeVo=new RequestFundsDetailVo();
				BeanUtils.copyProperties(fee, feeVo);
				feeVo.setFeeId(fee.getId());
				BigDecimal reqBigAmount=ObjectHelper.isEmpty(feeVo.getReceivedAmount())?BigDecimal.ZERO:feeVo.getReceivedAmount();
				feeVo.setReqBigAmount(reqBigAmount.subtract(ObjectHelper.isEmpty(feeVo.getPaidAmount())?BigDecimal.ZERO:feeVo.getPaidAmount()));
				feeVo.setReqFundsAmount(BigDecimal.ZERO);
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
    * @Title: requestFundsEditPage 
    * @Description: 进入请款申请页面
    * @author jincheng 
    * @param caseApplyId
    * @param businessKey
    * @param busiFormId
    * @param reqType
    * @return
    */
	@RequestMapping("/requestFundsEditPage")
    @UriKey(key = "com.zdsoft.finance.requestFunds.requestFundsEditPage")
    public ModelAndView requestFundsEditPage(String[] caseApplyId,String businessKey,String busiFormId,String reqType){
    	String viewName = "/finance/requestFunds/requestFunds_edit";
    	ModelMap model = new ModelMap();
    	try {
    		RequestFundsVo fundsVo=new RequestFundsVo();
    		StringBuilder sb=new StringBuilder();//案件id
    		boolean bool=false;
    		List<FeeInfomationVo> mapList=new ArrayList<FeeInfomationVo>();
    		if(ObjectHelper.isEmpty(businessKey)){
    			for(String caseId:caseApplyId){
        			if(bool){
        				sb.append(",");
        			}
        			sb.append(caseId);
        			bool=true;
        			this.warrperPayObject(mapList, caseId);
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
    			RequestFunds funds=requestFundsService.findOne(businessKey);
    			fundsVo=new RequestFundsVo(funds);
    			List<RequestFundsDetail> detailList=requestFundsDetailService.findRequestFundsDetailByReqFundsId(funds.getId());
    			for(RequestFundsDetail detail:detailList){
    				this.warrperPayObject(mapList, detail.getCaseApplyId());
    			}
    		}
    		model.put("funds", fundsVo);
    		model.put("payObject", ObjectHelper.objectToJson(mapList));//付款方对象
    	} catch (Exception e) {
			e.printStackTrace();
		}
    	return new ModelAndView(viewName, model);
    }

	/**
	 * @Title: warrperPayObject 
	 * @Description: 封装案件费用付款方
	 * @author jincheng 
	 * @param mapList
	 * @param caseId
	 * @throws Exception
	 */
	private void warrperPayObject(List<FeeInfomationVo> mapList, String caseId) throws Exception {
		List<FeeInfomation> feeList=feeInfomationService.findByCaseApplyId(caseId);
		for(FeeInfomation fee:feeList){
			if(ObjectHelper.isNotEmpty(fee.getPayObjectId())){
				FeeInfomationVo vo=new FeeInfomationVo();
				BeanUtils.copyProperties(fee, vo);
				if(!this.judgePayObjectIdIsExit(mapList,fee.getPayObjectId())){//判断费用付款方是否存在
					mapList.add(vo);
				}
			}
		}
	}
	
	/**
	 * @Title: judgePayObjectIdIsExit 
	 * @Description: 判断费用付款方是否存在
	 * @author jincheng 
	 * @param mapList
	 * @param payObjectId
	 * @return
	 */
	private boolean judgePayObjectIdIsExit(List<FeeInfomationVo> mapList, String payObjectId) {
		boolean bool=false;
		for(FeeInfomationVo vo:mapList){
			if(vo.getPayObjectId().equals(payObjectId)){
				bool=true;
			}
		}
		return bool;
}

	/**
	 * @Title: saveOrSubmitRequestFunds 
	 * @Description: 新增并启动流程、修改收费请款信息
	 * @author jincheng 
	 * @param fundsVo
	 * @param feeParam 费用列表JSON格式
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/saveOrSubmitRequestFunds")
	@UriKey(key = "com.zdsoft.finance.requestFunds.saveOrSubmitRequestFunds")
	public ResponseMsg saveOrSubmitRequestFunds(RequestFundsVo fundsVo,String feeList) {
		ResponseMsg msg = new ResponseMsg();
		try{
			RequestFunds funds=fundsVo.toPo();
			funds=requestFundsService.saveOrSubmitRequestFunds(funds,feeList); 
			msg.setId(funds.getId());
			msg.setMsg(funds.getMsg());
			msg.setResultStatus(ResultDto.SUCCESS);
		}catch (Exception e) {
			e.printStackTrace();
			msg.setMsg(e.getMessage());
			msg.setResultStatus(ResultDto.APP_ERROR);
		}
		return msg;
	}
	
	/**
	 * @Title: scanRequestFundsView 
	 * @Description: 草稿中查看请款信息
	 * @author jincheng 
	 * @param projectId
	 * @param processInstanceId
	 * @param businessKey
	 * @param busiFormId
	 * @return
	 */
	@RequestMapping(value = "/scanRequestFundsView")
	@UriKey(key = "com.zdsoft.finance.requestFunds.scanRequestFundsView")
	public ModelAndView scanRequestFundsView(String projectId, String processInstanceId, String businessKey,String busiFormId) {
		ModelMap model = new ModelMap();
		model.put("projectId", projectId);
		model.put("processInstanceId", processInstanceId);
		model.put("businessKey", businessKey);
		try {
				RequestFunds funds=requestFundsService.findOne(businessKey);
				RequestFundsVo fundsVo=new RequestFundsVo(funds);
				model.put("funds", fundsVo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("/finance/requestFunds/requestFunds_view", model);
	}
	
	/**
	 * @Title: scanRequestFundsPrcoessView 
	 * @Description: 案件请款审批页面
	 * @author jincheng 
	 * @param caseApplyId
	 * @param processInstanceId
	 * @param businessKey
	 * @return
	 */
	@RequestMapping(value = "/scanRequestFundsPrcoessView")
	@UriKey(key = "com.zdsoft.finance.requestFunds.scanRequestFundsPrcoessView")
	@ManualJob(resource = "com.zdsoft.finance.requestFunds.scanRequestFundsPrcoessView", label = "案件请款审批页面")
	@FinishJob(resource = "com.zdsoft.finance.requestFunds.scanRequestFundsPrcoessView", businessId = "businessKey", projectId = "projectId")
	public ModelAndView scanRequestFundsPrcoessView(String projectId, String processInstanceId, String businessKey) {
		ModelMap model = new ModelMap();
		model.put("projectId", projectId);
		model.put("processInstanceId", processInstanceId);
		model.put("businessKey", businessKey);
		try {
				RequestFunds funds=requestFundsService.findOne(businessKey);
				RequestFundsVo fundsVo=new RequestFundsVo(funds);
				model.put("funds", fundsVo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("/finance/requestFunds/requestFunds_prcoess_view", model);
	}
	
	/**
	 * @Title: processRequestFundsEdit 
	 * @Description: 案件请款修改页面
	 * @author jincheng 
	 * @param projectId
	 * @param processInstanceId
	 * @param businessKey
	 * @return
	 */
	@RequestMapping(value = "/processRequestFundsEdit")
	@UriKey(key = "com.zdsoft.finance.requestFunds.processRequestFundsEdit")
	@ManualJob(resource = "com.zdsoft.finance.requestFunds.processRequestFundsEdit", label = "案件请款修改页面")
	public ModelAndView processRequestFundsEdit(String projectId, String processInstanceId, String businessKey) {
		ModelMap model = new ModelMap();
		model.put("projectId", projectId);
		model.put("processInstanceId", processInstanceId);
		model.put("businessKey", businessKey);
		try {
			RequestFunds funds=requestFundsService.findOne(businessKey);
			RequestFundsVo fundsVo=new RequestFundsVo(funds);
			List<RequestFundsDetail> detailList=requestFundsDetailService.findRequestFundsDetailByReqFundsId(funds.getId());
			List<FeeInfomationVo> mapList=new ArrayList<FeeInfomationVo>();
			for(RequestFundsDetail detail:detailList){
				this.warrperPayObject(mapList, detail.getCaseApplyId());
			}
			model.put("funds", fundsVo);
			model.put("payObject", ObjectHelper.objectToJson(mapList));//付款方对象
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("/finance/requestFunds/requestFunds_prcoess_edit", model);
	}
	
	/**
	 * @Title: processUpdateRequestFunds 
	 * @Description: 工作流中修改请款信息
	 * @author jincheng 
	 * @param projectId
	 * @param processInstanceId
	 * @param businessKey
	 * @param fundsVo
	 * @param feeList 费用列表JSON格式
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/processUpdateRequestFunds")
	@UriKey(key = "com.zdsoft.finance.requestFunds.processUpdateRequestFunds")
	@FinishJob(resource = "com.zdsoft.finance.requestFunds.processUpdateRequestFunds", businessId = "businessKey", projectId = "projectId")
	public ResponseMsg processUpdateRequestFunds(String projectId, String processInstanceId, String businessKey,RequestFundsVo fundsVo,String feeList) {
		ResponseMsg msg = new ResponseMsg();
		try{
			RequestFunds funds=fundsVo.toPo();
			requestFundsService. saveOrSubmitRequestFunds(funds,feeList);
			Map<String, Object>  map=new HashMap<String, Object>();
			msg.setOptional(map);
			msg.setResultStatus(ResultDto.SUCCESS);
		}catch (Exception e) {
			e.printStackTrace();
			msg.setResultStatus(ResultDto.APP_ERROR);
		}
		return msg;
	}
	
	/**
	 * @Title: printRequestFunds 
	 * @Description: 打印明细
	 * @author jincheng 
	 * @param caseApplyId
	 * @param feeArray
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/printRequestFunds")
	@UriKey(key = "com.zdsoft.finance.requestFunds.printRequestFunds")
	public ModelAndView printRequestFunds(String caseApplyId,String[] feeArray) {
    	ModelMap model = new ModelMap();
    	try {
			CaseApply caseApply=caseApplyService.findOne(caseApplyId);
			CaseApplyVo caseVo=new CaseApplyVo(caseApply);
			model.put("caseApply", caseVo);
			List<RequestFundsDetailVo> detailList=new ArrayList<RequestFundsDetailVo>();
			for(String id:feeArray){
				RequestFundsDetail fee=requestFundsDetailService.findOne(id);
				RequestFundsDetailVo feeVo=new RequestFundsDetailVo();
				BeanUtils.copyProperties(fee, feeVo);
				detailList.add(feeVo);
			}
			model.put("detailList", detailList);
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		return new ModelAndView("/finance/requestFunds/requestFunds_print",model);
	}
	
    //TODO -------------------------------------------------------------------------------------拨款管理---------------------------------------------------------------------------------------------------
    /**
     * @Title: grantList 
     * @Description: 拨款管理
     * @author jincheng 
     * @return
     */
    @RequestMapping("/grantList")
	@UriKey(key = "com.zdsoft.finance.requestFunds.grantList")
	@Menu(resource = "com.zdsoft.finance.requestFunds.grantList", label = "拨款管理", group = "finance", order = 5)
    @DataAuthResource(resource="com.zdsoft.finance.requestFunds.grantList.dataAuth",label="拨款管理",group="com.zdsoft.finance.requestFunds.grantList")
	public ModelAndView grantList() {
		return new ModelAndView("/finance/requestFunds/requestFunds_grant_list");
	}
    
    /**
     * @Title: getRequestFundsList 
     * @Description: 获取请款列表(拨款)
     * @author jincheng 
     * @param request
     * @param jsoncallback
     * @param pageRequest
     * @return
     * @throws Exception
     */
    @RequestMapping("/getRequestFundsList")
	@UriKey(key = "com.zdsoft.finance.requestFunds.getRequestFundsList")
	@ResponseBody
	public String getRequestFundsList(HttpServletRequest request, String jsoncallback, PageRequest pageRequest) {
    	ResponseMsg msg = new ResponseMsg();
    	  try {
			DataOperPermDto dataOperPermDto = CED.findDataPerms(StoreHelper.getApplication(), "com.zdsoft.finance.requestFunds.grantList.dataAuth");
			@SuppressWarnings("unchecked")
			List<QueryObj> queryObjs = (List<QueryObj>) request.getAttribute("listObj");
			Page<RequestFunds> page = requestFundsService.findByHqlConditions(pageRequest, queryObjs,dataOperPermDto);
			Page<RequestFundsVo> pageVo=new com.zdsoft.framework.core.common.page.PageImpl<RequestFundsVo>(pageRequest);
			List<RequestFundsVo> voList=new ArrayList<RequestFundsVo>();
			for(RequestFunds funds:page.getRecords()){
				RequestFundsVo fundsVo=new RequestFundsVo();
				BeanUtils.copyProperties(funds, fundsVo);
				voList.add(fundsVo);
			}
			pageVo.setRecords(voList);
			pageVo.setTotalRows(page.getTotalRows());
			msg.setResultStatus(ResponseMsg.SUCCESS);
			msg.setTotal(pageVo.getTotalRows());
			msg.setRows(pageVo.getRecords());
		} catch (AppException e) {
			e.printStackTrace();
		}
		return ObjectHelper.objectToJson(msg, jsoncallback);
	}
    
    /**
     * @Title: grantEditPage 
     * @Description: 批量拨款页面
     * @author jincheng 
     * @param reqFundsIds
     * @return
     */
    @RequestMapping("/grantEditPage")
	@UriKey(key = "com.zdsoft.finance.requestFunds.grantEditPage")
	public ModelAndView grantEditPage(String[] reqFundsIds) {
    	ModelMap model = new ModelMap();
    	StringBuilder sb=new StringBuilder();//请款id
		boolean bool=false;
		if(ObjectHelper.isNotEmpty(reqFundsIds)){
			for(String fundsId:reqFundsIds){
    			if(bool){
    				sb.append(",");
    			}
    			sb.append(fundsId);
    			bool=true;
    		}
		}
		model.put("reqFundsIds", sb.toString());
		return new ModelAndView("/finance/requestFunds/requestFunds_grant_edit",model);
	}
    
	/**
	 * @Title: grantRequestFunds 
	 * @Description: 拨款
	 * @author jincheng 
	 * @param grantDate 实际拨款日期
	 * @param reqFundsIds
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/grantRequestFunds")
	@UriKey(key = "com.zdsoft.finance.requestFunds.grantRequestFunds")
	public ResponseMsg grantRequestFunds(Long grantDate,String[] reqFundsIds) {
		ResponseMsg msg = new ResponseMsg();
		try{
			requestFundsService.grantRequestFunds(grantDate,reqFundsIds); 
			msg.setResultStatus(ResultDto.SUCCESS);
		}catch (Exception e) {
			e.printStackTrace();
			msg.setResultStatus(ResultDto.APP_ERROR);
		}
		return msg;
	}
	
	//TODO -------------------------------------------------------------------------------------付款管理---------------------------------------------------------------------------------------------------
    /**
     * @Title: paymentList 
     * @Description: 付款管理
     * @author jincheng 
     * @return
     */
    @RequestMapping("/paymentList")
	@UriKey(key = "com.zdsoft.finance.requestFunds.paymentList")
	@Menu(resource = "com.zdsoft.finance.requestFunds.paymentList", label = "付款管理", group = "finance", order = 6)
    @DataAuthResource(resource="com.zdsoft.finance.requestFunds.paymentList.dataAuth",label="付款管理",group="com.zdsoft.finance.requestFunds.paymentList")
	public ModelAndView paymentList() {
		return new ModelAndView("/finance/requestFunds/requestFunds_payment_list");
	}
    
    /**
     * @Title: getRequestFundsGroupPayObjectNameList 
     * @Description: 获取请款列表(付款):如果请款单中多个往来单位，就分拆成多个付款单
     * @author jincheng 
     * @param request
     * @param jsoncallback
     * @param pageRequest
     * @return
     * @throws Exception
     */
    @RequestMapping("/getRequestFundsGroupPayObjectNameList")
   	@UriKey(key = "com.zdsoft.finance.requestFunds.getRequestFundsGroupPayObjectNameList")
   	@ResponseBody
   	public String getRequestFundsGroupPayObjectNameList(HttpServletRequest request, String jsoncallback, PageRequest pageRequest){
    	ResponseMsg msg = new ResponseMsg();
    	try {
			DataOperPermDto dataOperPermDto = CED.findDataPerms(StoreHelper.getApplication(), "com.zdsoft.finance.requestFunds.paymentList.dataAuth");
			// 获取页面封装的查询参数
	   		List<QueryObj> queryObjs = ParameterUtil.getQueryObjByParameter(request, new String[]{"rq","rqd"});
	   		Page<Map<String,Object>> page = requestFundsDetailService.getRequestFundsDetailGroupList(pageRequest, queryObjs,dataOperPermDto);
	   		msg.setResultStatus(ResponseMsg.SUCCESS);
	   		msg.setTotal(page.getTotalRows());
	   		msg.setRows(page.getRecords());
		} catch (AppException e) {
			e.printStackTrace();
		}
   		return ObjectHelper.objectToJson(msg, jsoncallback);
   	}
    
   /**
    * @Title: paymentEditPage 
    * @Description: 付款单页面
    * @author jincheng 
    * @param reqFundsId
    * @param billCode
    * @param payObjectId
    * @return
    */
    @RequestMapping("/paymentEditPage")
	@UriKey(key = "com.zdsoft.finance.requestFunds.paymentEditPage")
	public ModelAndView paymentEditPage(String reqFundsId,String billCode,String payObjectId,String payObjectName) {
    	ModelMap model = new ModelMap();
		try {
			model.put("reqFundsId", reqFundsId);
			model.put("billCode", billCode);
			model.put("payObjectId", payObjectId);
			model.put("payObjectName", payObjectName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("/finance/requestFunds/requestFunds_payment_edit",model);
	}
    
    /**
     * @Title: paymentRequestFunds 
     * @Description: 付款
     * @author jincheng 
     * @param grantDate
     * @param reqFundsIds
     * @return
     */
    @ResponseBody
	@RequestMapping("/paymentRequestFunds")
	@UriKey(key = "com.zdsoft.finance.requestFunds.paymentRequestFunds")
	public ResponseMsg paymentRequestFunds(String reqFundsId,Long paymentDate,String payFeeList) {
		ResponseMsg msg = new ResponseMsg();
		try{
			requestFundsService.paymentRequestFunds(reqFundsId,paymentDate,payFeeList); 
			msg.setResultStatus(ResultDto.SUCCESS);
		}catch (Exception e) {
			e.printStackTrace();
			msg.setResultStatus(ResultDto.APP_ERROR);
		}
		return msg;
	}
   
}
