package com.zdsoft.finance.marketing.controller;

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
import com.zdsoft.finance.marketing.entity.BeforehandApply;
import com.zdsoft.finance.marketing.entity.CaseApply;
import com.zdsoft.finance.marketing.service.BeforehandApplyService;
import com.zdsoft.finance.marketing.service.CaseApplyService;
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
 * @Title:AppCaseApplyController.java
 * @Package:com.zdsoft.finance.marketing.controller
 * @Description:案件申请App接口Controller
 * @author: zhoushichao
 * @date:2017年1月13日 下午8:42:55
 * @version:v1.0
 */
@Controller
@RequestMapping("/server/bizCenter/loanApplication")
public class AppCaseApplyController extends BaseController {

	@Autowired
	private CaseApplyService caseApplyService;
	@Autowired
	private BeforehandApplyService beforehandApplyService;
	@Autowired
	private CRA CRA;
	@Autowired
	private CED CED;
	@Autowired
    private ProductRateService productRateService;
	@Autowired
	private ProductService productService;
	/**
	 * 
	 * 贷款申请保存
	 *
	 * @author zhoushichao
	 * @param beforehandApplyVo
	 * @param token
	 * @return
	 */
	@RequestMapping(value = "/addLoanInfo",produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String saveCaseApply(BeforehandApplyVo beforehandApplyVo,String token){
		Map<String, Object> caseApplyMap = new HashMap<String, Object>();
		
		if (ObjectHelper.isEmpty(token)) {
			return AppServerUtil.buildError(AppStatus.ArgsError);
		}
		BeforehandApply beforehandApply =beforehandApplyVo.toPO();
		BeforehandApply beforehandApplys = new BeforehandApply();
		
		CaseApply caseApply=new CaseApply();
		
		try {
			if(ObjectHelper.isNotEmpty(beforehandApply.getId())){
				beforehandApplys=beforehandApplyService.findOne(beforehandApply.getId());
				
			}
			//获取产品信息
			if(ObjectHelper.isNotEmpty(beforehandApply.getProductSubtypeId())){
				Product product = productService.findOne(beforehandApply.getProductSubtypeId());
				beforehandApplys.setProductTypeId(product.getCategory().getId());
				beforehandApplys.setProductTypeName(product.getCategory().getName());
				beforehandApplys.setProductSubtypeId(product.getId());
				beforehandApplys.setProductSubtypeName(product.getProductName());
			}
			beforehandApplys.setTerminalId(beforehandApply.getTerminalId());
			beforehandApplys.setApplyAmount(beforehandApply.getApplyAmount());
			beforehandApplys.setCapitalSource(beforehandApply.getCapitalSource());
			beforehandApplys.setCapitalUseFor(beforehandApply.getCapitalUseFor());
			beforehandApplys.setApplyDeadline(beforehandApply.getApplyDeadline());
			beforehandApplys.setApplyDeadlineUnit(beforehandApply.getApplyDeadlineUnit());
			//初始保存案件状态为正常
	 		if(ObjectHelper.isEmpty(beforehandApplys.getCaseApplyStatus())){
				beforehandApplys.setCaseApplyStatus(BeforehandApply.NORMAL);
			}
			
			if(ObjectHelper.isNotEmpty(beforehandApplys.getCaseApplyId())){
				caseApply = caseApplyService.findOne(beforehandApplys.getCaseApplyId());
			}
			caseApply.setProductTypeId(beforehandApplys.getProductTypeId());
			caseApply.setProductTypeName(beforehandApplys.getProductTypeName());
			caseApply.setProductSubtypeId(beforehandApplys.getProductSubtypeId());
			caseApply.setProductSubtypeName(beforehandApplys.getProductSubtypeName());
			caseApply.setTerminalId(beforehandApplys.getTerminalId());
			caseApply.setApplyAmount(beforehandApplys.getApplyAmount());
			caseApply.setCapitalSource(beforehandApplys.getCapitalSource());
			caseApply.setCapitalUseFor(beforehandApplys.getCapitalUseFor());
			caseApply.setApplyDeadline(beforehandApplys.getApplyDeadline());
			caseApply.setApplyDeadlineUnit(beforehandApplys.getApplyDeadlineUnit());
			
			//保存案件申请信息
			caseApply = caseApplyService.saveOrUpdateCaseApply(caseApply);
			
			//保存案件预申请信息
			beforehandApplys.setCaseApplyId(caseApply.getId());
			beforehandApplys = beforehandApplyService.saveOrUpdateEntity(beforehandApplys);
		
			//获取登录人信息
			AccountDTO dto = CRA.getAccount(token);
			EmpDto emp = CED.getLoginUser(dto.getId());
			
			//设置登录人信息
			caseApply.setDevelopmentManagerCode(emp.getEmpCd());
			caseApply.setDevelopmentManagerName(emp.getEmpNm());
			caseApply.setDevelopmentDepartmentCode(emp.getDepartmentCd());
			caseApply.setDevelopmentDepartmentName(emp.getDepartmentName());
			caseApply.setMechanismCode(emp.getOrgCd());
			caseApply.setMechanismName(emp.getOrgNm());
			
			caseApply = caseApplyService.saveOrUpdateCaseApply(caseApply);
			if(ObjectHelper.isNotEmpty(beforehandApplyVo.toPO().getId())){
				caseApplyMap.put("message", "修改贷款申请成功！");
			}else {
				caseApplyMap.put("message", "保存贷款申请成功！");
			}
			caseApplyMap.put("id", beforehandApplys.getId());
			caseApplyMap.put("status", ResponseMsg.SUCCESS);
		} catch (Exception e) {
			caseApplyMap.put("status", ResponseMsg.APP_ERROR);
			caseApplyMap.put("message", "保存贷款申请失败！");
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
            
           /* 数据权限筛选，后期添加
              QueryObj obj = new QueryObj();
              AccountDTO dto;
              dto = CRA.getAccount(token);
       
        EmpDto emp = CED.getLoginUser(dto.getId());
        
        obj.setElement("String");
        obj.setObj("org");
        obj.setOperator("IN");
        queryObjs.add(obj);
        
        QueryObj obj2 = new QueryObj();
        obj2.setElement("String");
        obj2.setObj("logicDelelte");
        obj2.setOperator("E");
        obj2.setValue("1");
        queryObjs.add(obj2);*/
            
        List<CaseApplyByProductVo> voList = new ArrayList<CaseApplyByProductVo>();
        Page<Map<String, Object>> page =  productRateService.findBySqlProductRate(pageable, queryObjs);
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
            //封装vo
            vo.setRate(RateUtil.percentRate( Double.valueOf(rate+""))+"");
            vo.setRateUnit(CED.loadSimpleCodeNameByFullCode(rateUnit+""));
            vo.setName(productName+"");
            vo.setMinPeriod(startDate+"");
            vo.setMaxPeriod(endDate+"");
            vo.setCategory(categoryName+"");
            vo.setProductId(productId+"");
            vo.setStartDateUnit(CED.loadSimpleCodeNameByFullCode(startDateUnit+""));
            vo.setEndDateUnit(CED.loadSimpleCodeNameByFullCode(endDateUnit+""));
            voList.add(vo);
        }
            return  AppServerUtil.buildJsonObject(AppStatus.Succeed,voList);
        } catch (Exception e) {
            e.printStackTrace();
            AppServerUtil.buildJsonMessage(AppStatus.SystemError);  
        }
        return   AppServerUtil.buildJsonMessage(AppStatus.SystemError);  
        
    }
}