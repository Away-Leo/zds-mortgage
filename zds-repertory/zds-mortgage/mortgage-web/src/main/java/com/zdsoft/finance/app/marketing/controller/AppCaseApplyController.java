package com.zdsoft.finance.app.marketing.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zdsoft.essential.client.service.CED;
import com.zdsoft.essential.dto.emp.EmpDto;
import com.zdsoft.finance.common.base.QueryObj;
import com.zdsoft.finance.common.utils.AppStatus;
import com.zdsoft.finance.common.utils.app.AppServerUtil;
import com.zdsoft.finance.cooperator.entity.CooperatorTerminal;
import com.zdsoft.finance.cooperator.service.CapitalistService;
import com.zdsoft.finance.cooperator.service.CooperatorTerminalService;
import com.zdsoft.finance.cooperator.vo.CooperatorTerminalVo;
import com.zdsoft.finance.marketing.entity.BeforehandApply;
import com.zdsoft.finance.marketing.service.BeforehandApplyService;
import com.zdsoft.finance.marketing.vo.BeforehandApplyVo;
import com.zdsoft.finance.marketing.vo.CaseApplyByProductVo;
import com.zdsoft.finance.product.entity.Product;
import com.zdsoft.finance.product.service.ProductRateService;
import com.zdsoft.finance.product.service.ProductService;
import com.zdsoft.framework.core.common.dto.ResponseMsg;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.PageRequest;
import com.zdsoft.framework.core.common.util.ObjectHelper;
import com.zdsoft.framework.core.common.util.RateUtil;
import com.zdsoft.framework.core.commweb.component.BaseController;
import com.zdsoft.framework.cra.client.service.CRA;
import com.zdsoft.framework.cra.dto.AccountDTO;

/** 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: AppCaseApplyController.java 
 * @ClassName: AppCaseApplyController 
 * @Description: 案件申请App接口Controller
 * @author zhoushichao 
 * @date 2017年3月2日 下午1:57:17 
 * @version V1.0 
 */ 
@Controller
@RequestMapping("/server/bizCenter/loanApplication")
public class AppCaseApplyController extends BaseController {

	@Autowired
	private BeforehandApplyService beforehandApplyService;
	@Autowired
	private CED CED;
    @Autowired
    private CRA CRA;
	@Autowired
    private ProductRateService productRateService;
	@Autowired
	private ProductService productService;
	@Autowired
	CapitalistService capitalistService;
	@Autowired
	CooperatorTerminalService cooperatorTerminalService;
	/**
	 * 
	 * @Title: saveCaseApply 
	 * @Description: 贷款申请保存
	 * @author zhoushichao 
	 * @param beforehandApplyVo   贷款信息
	 * @param token  当前会话token值
	 * @return
	 */
	@RequestMapping(value = "/addLoanInfo",produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String saveCaseApply(BeforehandApplyVo beforehandApplyVo,String token){
		Map<String, Object> caseApplyMap = new HashMap<String, Object>();
		
		if (ObjectHelper.isEmpty(token)||ObjectHelper.isEmpty(beforehandApplyVo.getProductSubtypeId())||ObjectHelper.isEmpty(beforehandApplyVo.getTerminalId())
				||ObjectHelper.isEmpty(beforehandApplyVo.getApplyAmount())||ObjectHelper.isEmpty(beforehandApplyVo.getCapitalUseFor())
				||ObjectHelper.isEmpty(beforehandApplyVo.getApplyTerm())||ObjectHelper.isEmpty(beforehandApplyVo.getApplyTermUnit())) {
			return AppServerUtil.buildError(AppStatus.ArgsError);
		}
		BeforehandApply beforehandApply =beforehandApplyVo.toPO();
		BeforehandApply beforehand = new BeforehandApply();
		
		try {
			if(ObjectHelper.isNotEmpty(beforehandApply.getId())){
				beforehand=beforehandApplyService.findOne(beforehandApply.getId());
			}
			//获取产品信息
			if(ObjectHelper.isNotEmpty(beforehandApply.getProductSubtypeId())){
				Product product = productService.findOne(beforehandApply.getProductSubtypeId());
				beforehand.setProductTypeId(product.getCategory().getId());
				beforehand.setProductTypeName(product.getCategory().getName());
				beforehand.setProductSubtypeId(product.getId());
				beforehand.setProductSubtypeName(product.getProductName());
			}
			beforehand.setTerminalId(beforehandApply.getTerminalId());
			beforehand.setApplyAmount(beforehandApply.getApplyAmount());
			beforehand.setCapitalSource(beforehandApply.getCapitalSource());
			beforehand.setCapitalUseFor(beforehandApply.getCapitalUseFor());
			beforehand.setApplyTerm(beforehandApply.getApplyTerm());
			beforehand.setApplyTermUnit(beforehandApply.getApplyTermUnit());
			
			//保存贷款申请
			beforehand = beforehandApplyService.saveOrUpdateAppBeforehandApply(beforehand);
			if(ObjectHelper.isNotEmpty(beforehandApplyVo.getId())){
				caseApplyMap.put("message", "修改贷款申请成功！");
			}else {
				caseApplyMap.put("message", "保存贷款申请成功！");
			}
			caseApplyMap.put("id", beforehand.getId());
			caseApplyMap.put("caseApplyId", beforehand.getCaseApplyId());
			caseApplyMap.put("status", ResponseMsg.SUCCESS);
		} catch (Exception e) {
			caseApplyMap.put("status", ResponseMsg.APP_ERROR);
			caseApplyMap.put("message", "保存贷款申请失败！");
			logger.error("保存贷款申请失败！", e);
			e.printStackTrace();
		}
		return ObjectHelper.objectToJson(caseApplyMap);
	}
	
	  /**
     * 
     * APP获取产品类型
     *
     * @author jingjy
     * @param caseApplyByProductVo
     * @param token
     * @param request
     * @param pageable
     * @return
     */
    @RequestMapping(value = "/index",produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String caseApplyAndProductList(CaseApplyByProductVo caseApplyByProductVo,String token,HttpServletRequest request,PageRequest pageable){
        String pageIndex=request.getParameter("pageIndex");
        String pageSize=request.getParameter("pageSize");
        pageable.setPage(Integer.valueOf(pageIndex));
        pageable.setRows(Integer.valueOf(pageSize));
        // 获取页面封装的查询参数 
        @SuppressWarnings("unchecked")
        List<QueryObj> queryObjs = (List<QueryObj>) request.getAttribute("listObj");
       
        try {
            
        	 AccountDTO dto2 = CRA.getAccount(token);
             EmpDto emp = CED.getLoginUser(dto2.getId());
             List<CaseApplyByProductVo> voList = new ArrayList<CaseApplyByProductVo>();
             Page<Map<String, Object>> page =  productRateService.findBySqlProductRate(pageable, queryObjs,emp.getOrgCd(),emp.getCompanyCd());
             logger.info("----------------> 获取产品列表成功");
             for(Map<String, Object> map : page.getRecords()){
                 CaseApplyByProductVo vo = new CaseApplyByProductVo();
                 Object productId = map.get("ID");
                 Object productName = map.get("PRODUCTNAME");
                 Object endDate = map.get("ENDDATE");
                 Object startDate = map.get("STARTDATE");
                 Object startDateUnit = map.get("STARTDATEUNIT");
                 Object endDateUnit = map.get("ENDDATEUNIT");
                 Object rate = map.get("RATE");
                 Object rateUnit = map.get("RATEUNIT");
                 Object categoryName = map.get("CATEGORYNAME");
                 Object capitalistId = map.get("CAPITALISTID");
                 Object capitalistName = map.get("CAPITALISTNAME");
                 //封装vo
                 
                 if("YWDM0011903".equals(rateUnit)){
                 	vo.setRate(RateUtil.percentRate2(Double.valueOf(rate+""),false)+"");
                 }else{
                 	vo.setRate(RateUtil.percentRate( Double.valueOf(rate+""))+"");
                 }
                 vo.setRateUnit(CED.loadSimpleCodeNameByFullCode(rateUnit+""));
                 vo.setName(productName+"");
                 vo.setMinPeriod(startDate+"");
                 vo.setMaxPeriod(endDate+"");
                 vo.setCategory(categoryName+"");
                 vo.setProductId(productId+"");
                 vo.setCapitalistId(capitalistId+"");
                 vo.setCapitalistName(capitalistName+"");
                 vo.setStartDateUnit(CED.loadSimpleCodeNameByFullCode(startDateUnit+""));
                 vo.setEndDateUnit(CED.loadSimpleCodeNameByFullCode(endDateUnit+""));
                 logger.info("----------------> 产品列表："+ ObjectHelper.objectToJson(vo));
                 voList.add(vo);
                 
             }
                 return  AppServerUtil.buildJsonObject(AppStatus.Succeed,voList);
             } catch (Exception e) {
             	logger.info("获取产品列表失败，失败原因:" + e.getMessage());
                 e.printStackTrace();
                 AppServerUtil.buildJsonMessage(AppStatus.SystemError);  
             }
             return   AppServerUtil.buildJsonMessage(AppStatus.SystemError);  
        
    }
    

	/**
	 * 
	 * @Title: cooperatorSimpleCode
	 * @Description: 终端列表
	 * @author jingjiyan
	 * @param jsoncallback
	 * @param createOrgCd
	 *            部门编号
	 * @return 终端列表信息
	 */
	@RequestMapping("/appCooperatorSimpleCode")
	@ResponseBody
	public String cooperatorSimpleCode(String token) {
		List<Map<String,Object>> list_new = new ArrayList<Map<String,Object>>();
		if (ObjectHelper.isEmpty(token)) {
			return AppServerUtil.buildError(AppStatus.ArgsError);
		}
		try{
        EmpDto emp = CED.getLoginUser();
        logger.info("------------------->comanyCd="+emp.getCompanyCd());
		List<CooperatorTerminal> list = cooperatorTerminalService.findByShareOrgCdAndTerminalStatus(emp.getCompanyCd(),
				"YWDM0016301");
		for (CooperatorTerminal info : list) {
			CooperatorTerminalVo vo = new CooperatorTerminalVo(info);
			Map<String,Object>	returnData = new HashMap<String,Object>();
			returnData.put("id", vo.getId());
			returnData.put("terminalFullName", vo.getTerminalFullName());
			list_new.add(returnData);
		}
		}catch(Exception e){
			e.printStackTrace();
			logger.error("获取的终端列表集合", e);
			return	AppServerUtil.buildJsonMessage(AppStatus.SystemError,"获取的终端列表集合");  
		}
		return AppServerUtil.buildJsonObject(AppStatus.Succeed,list_new);
	}
}