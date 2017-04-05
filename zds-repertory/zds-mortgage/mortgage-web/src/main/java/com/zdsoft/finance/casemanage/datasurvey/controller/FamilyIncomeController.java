package com.zdsoft.finance.casemanage.datasurvey.controller;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zdsoft.finance.casemanage.datasurvey.entity.FamilyIncome;
import com.zdsoft.finance.casemanage.datasurvey.entity.FamilyIncomeDetail;
import com.zdsoft.finance.casemanage.datasurvey.service.FamilyIncomeDetailService;
import com.zdsoft.finance.casemanage.datasurvey.service.FamilyIncomeService;
import com.zdsoft.finance.casemanage.datasurvey.vo.FamilyIncomeDetailVo;
import com.zdsoft.finance.casemanage.datasurvey.vo.FamilyIncomeVo;
import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.finance.customer.entity.BeforePersonalCustomer;
import com.zdsoft.finance.customer.service.BeforePersonalCustomerService;
import com.zdsoft.finance.customer.vo.BeforePersonalCustomerVo;
import com.zdsoft.finance.marketing.entity.CaseApplyBeforeCustomer;
import com.zdsoft.finance.marketing.service.CaseApplyBeforeCustomerService;
import com.zdsoft.framework.core.common.dto.ResponseMsg;
import com.zdsoft.framework.core.common.util.DateHelper;
import com.zdsoft.framework.core.common.util.ObjectHelper;
import com.zdsoft.framework.core.commweb.annotation.UriKey;
import com.zdsoft.framework.core.commweb.component.BaseController;

/** 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: FamilyIncomeController.java 
 * @ClassName: FamilyIncomeController 
 * @Description: 家庭收入
 * @author liuhuan 
 * @date 2017年2月20日 下午5:19:29 
 * @version V1.0 
 */ 
@Controller
@RequestMapping("/casemanage/datasurvey/familyIncome")
public class FamilyIncomeController extends BaseController{
	
	@Autowired
	private BeforePersonalCustomerService beforePersonalCustomerService;
	@Autowired
	private CaseApplyBeforeCustomerService caseApplyBeforeCustomerService;
	
	@Autowired
	private FamilyIncomeService familyIncomeService;
	
	@Autowired
	private FamilyIncomeDetailService familyIncomeDetailService;
	
	/**
	 * 
	 * @Title: initFamilyIncome 
	 * @Description: 家庭收入编辑加载
	 * @author liuhuan 
	 * @param caseApplyId	案件id
	 * @return
	 */
	@RequestMapping("/loadFamilyIncome")
	@UriKey(key = "com.zdsoft.finance.casemanage.datasurvey.familyincomeinfo")
	public ModelAndView loadFamilyIncome(String caseApplyId){
		logger.info("-----------进入家庭收入录入页面----------------");
		ModelMap model = new ModelMap();
		try {
			model=initFamilyincomeinfo(caseApplyId);
		} catch (Exception e) {
			model.put("errorMsg", e.getMessage());
			e.printStackTrace();
			logger.error("家庭收入信息失败",e);
		} 
		return new ModelAndView("casemanage/datasurvey/familyIncome/familyIncome_main_edit", model);
	}
	
	/**
	 * 
	 * @Title: loadViewFamilyIncome 
	 * @Description: 家庭收入查看加载
	 * @author zhoushichao 
	 * @param caseApplyId  案件Id
	 * @return
	 */
	@RequestMapping("/loadViewFamilyIncome")
	@UriKey(key = "com.zdsoft.finance.casemanage.datasurvey.viewFamilyincomeinfo")
	public ModelAndView loadViewFamilyIncome(String caseApplyId){
		logger.info("-----------进入家庭收入查看页面----------------");
		ModelMap model = new ModelMap();
		try {
			model=initFamilyincomeinfo(caseApplyId);
		} catch (Exception e) {
			model.put("errorMsg", e.getMessage());
			e.printStackTrace();
			logger.error("家庭收入信息失败",e);
		} 
		return new ModelAndView("casemanage/datasurvey/familyIncome/familyIncome_main_view", model);
	}
	
	/**
	 * 
	 * @Title: initFamilyincomeinfo 
	 * @Description: 家庭收入数据组装
	 * @author zhoushichao 
	 * @param caseApplyId
	 * @return
	 */
	private ModelMap initFamilyincomeinfo(String caseApplyId) {
		ModelMap model = new ModelMap();
		List<FamilyIncome> listVo = new ArrayList<FamilyIncome>();
		listVo = familyIncomeService.findByCaseApplyId(caseApplyId);
		model.put("vo", listVo);
		model.put("caseApplyId", caseApplyId);
		return model;
	}

	/**
	 * 
	 * @Title: editFamilyIncome 
	 * @Description: 家庭收入编辑页面
	 * @author zhoushichao 
	 * @param caseApplyId 案件id
	 * @param familyIncomeId 家庭收入Id
	 * @return
	 */
	@RequestMapping("/editFamilyIncome")
	@UriKey(key = "com.zdsoft.finance.casemanage.datasurvey.familyincomeinfo.edit")
	public ModelAndView editFamilyIncome(String caseApplyId, String familyIncomeId){
		ModelMap model = new ModelMap();
		try {
			model=initFamilyIncomeData(caseApplyId,familyIncomeId);
		} catch (Exception e) {
			logger.error("查询数据失败",e.getMessage());
			e.printStackTrace();
		}
		return new ModelAndView("casemanage/datasurvey/familyIncome/familyIncome_edit", model);
	}
	
	/**
	 * 
	 * @Title: viewFamilyIncome 
	 * @Description: 家庭收入查看页面
	 * @author zhoushichao 
	 * @param caseApplyId 案件id
	 * @param familyIncomeId 家庭收入Id
	 * @return
	 */
	@RequestMapping("/viewFamilyIncome")
	@UriKey(key = "com.zdsoft.finance.casemanage.datasurvey.familyincomeinfo.view")
	public ModelAndView viewFamilyIncome(String caseApplyId, String familyIncomeId){
		ModelMap model = new ModelMap();
		try {
			model=initFamilyIncomeData(caseApplyId,familyIncomeId);
		} catch (Exception e) {
			logger.error("查询数据失败",e.getMessage());
			e.printStackTrace();
		}
		return new ModelAndView("casemanage/datasurvey/familyIncome/familyIncome_view", model);
	}
	/** 
	 * @Title: initFamilyIncomeData 
	 * @Description: 组装具体某个页签的数据
	 * @author zjx 
	 * @param caseApplyId 案件id
	 * @param customerId 客户id
	 * @return
	 * @throws BusinessException  
	 */ 
	private ModelMap initFamilyIncomeData(String caseApplyId, String familyIncomeId) throws BusinessException {
		ModelMap model = new ModelMap();
		
		List<FamilyIncomeDetail> familyIncomeDetailList = null;
		List<FamilyIncomeDetailVo> detailListVo = new ArrayList<FamilyIncomeDetailVo>();
		
		FamilyIncome familyIncome = new FamilyIncome();
		if(ObjectHelper.isNotEmpty(familyIncomeId)&&familyIncomeId.length()==32){
			familyIncome = familyIncomeService.findOne(familyIncomeId);
			
			familyIncomeDetailList = familyIncomeDetailService.findByFamilyIncomeId(familyIncome.getId());
			for(FamilyIncomeDetail familyIncomeDetail : familyIncomeDetailList){
				FamilyIncomeDetailVo vo = new FamilyIncomeDetailVo(familyIncomeDetail);
				vo.setRealMonthStr(DateHelper.longToDate(vo.getRealMonth()*100, "yyyy年MM月"));
				detailListVo.add(vo);
			}
		} else{
			SimpleDateFormat matter=new SimpleDateFormat("yyyyMM");
			SimpleDateFormat matterStr=new SimpleDateFormat("yyyy年MM月");
			Calendar calendar = Calendar.getInstance();
			for(int i=0; i<6; i++){
				FamilyIncomeDetailVo vo = new FamilyIncomeDetailVo();
				calendar.add(Calendar.MONTH, -1);
				Date date = calendar.getTime();
				String monthStr = matterStr.format(date);
				String month = matter.format(date);
				vo.setRealMonth(Long.parseLong(month));
				vo.setRealMonthStr(monthStr);
				detailListVo.add(vo);
			}
		}
		model.put("familyIncomeVo", new FamilyIncomeVo(familyIncome));
		model.put("detailListVo", detailListVo);
		model.put("caseApplyId", caseApplyId);
		return model;
	}

	/**
	 * 
	 * @Title: getCustomerName 
	 * @Description: 根据案件查询客户，  家庭收入中的户主名下拉数据
	 * @author liuhuan 
	 * @param jsoncallback	
	 * @param caseApplyId	案件id
	 * @return	
	 */
	@RequestMapping("/getCustomerName")
	@UriKey(key="com.zdsoft.finance.customer.getCustomerName")
	@ResponseBody
	public String getCustomerName(String jsoncallback, String caseApplyId) {
	    List<Map<String, Object>> returnData = new ArrayList<Map<String, Object>>();
	    //查询树结构数据
	    List<BeforePersonalCustomer> beforePersonalCustomers = null;
	    try {
	    	beforePersonalCustomers = beforePersonalCustomerService.queryByCaseApplyId(caseApplyId);
	    	for (BeforePersonalCustomer customer : beforePersonalCustomers) {
	    		Map<String, Object> rowData = new HashMap<String, Object>();
	    		rowData.put("id", customer.getId());
	    		rowData.put("text", customer.getCustomerName());
	    		returnData.add(rowData);
	    	}
		} catch (Exception e) {
			logger.error("查询客户失败",e);
			e.printStackTrace();
		}
	    return ObjectHelper.objectToJson(returnData, jsoncallback);
	}
	
	/**
	 * 
	 * @Title: findLikeCustomerName 
	 * @Description: 根据案件Id和户主名模糊查询数据
	 * @author zhoushichao 
	 * @param id  户主名
	 * @param caseApplyId  案件Id
	 * @return 
	 */
	@RequestMapping("/findLikeCustomerName")
	@UriKey(key="com.zdsoft.finance.customer.findLikeCustomerName")
	@ResponseBody
	public String findLikeCustomerName(String id,String caseApplyId){
	    logger.info("进入户主名模糊查询数据！");
	    List<Map<String, String>> mapList = new ArrayList<Map<String, String>>();
	    Map<String, Object> mapRt = new HashMap<String, Object>();
	    if(ObjectHelper.isEmpty(id)){
            id = "" ;
        }
        List<CaseApplyBeforeCustomer> list = null ;
        try{
             list = caseApplyBeforeCustomerService.findCustomerNameByLike(caseApplyId, id);
        }catch (BusinessException e) {
            Map<String, String> map = new HashMap<String, String>();
            map.put("customerId", "");
            map.put("customerName", "");
            mapList.add(map);
            mapRt.put("data", mapList);
            return ObjectHelper.objectToJson(mapRt);
        }
        if(ObjectHelper.isNotEmpty(list)){
            for (CaseApplyBeforeCustomer customer : list) {
                Map<String, String> map = new HashMap<String, String>();
                map.put("customerId", customer.getBeforeCustomer().getId());
                map.put("customerName", customer.getBeforeCustomer().getCustomerName());
                mapList.add(map);
            }
        }
        mapRt.put("data", mapList);
        return ObjectHelper.objectToJson(mapRt);
	    
	}
	
	/**
	 * 
	 * @Title: getActualUsePersonName 
	 * @Description: 查询对应客户 是否是实际用款人
	 * @author liuhuan 
	 * @param jsoncallback
	 * @param caseApplyId 案件id
	 * @param customerId 客户id
	 * @return
	 */
	@RequestMapping("/getActualUsePersonName")
	@UriKey(key="com.zdsoft.finance.customer.getActualUsePersonName")
	@ResponseBody
	public String getActualUsePersonName(String jsoncallback, String caseApplyId, String customerId){
		Map<String, Object> rowData = new HashMap<String, Object>();
		List<BeforePersonalCustomer> beforePersonalCustomers = null;
	    try {
	    	beforePersonalCustomers = beforePersonalCustomerService.queryByCaseApplyId(caseApplyId);
	    	for (BeforePersonalCustomer customer : beforePersonalCustomers) {
	    		if(ObjectHelper.isEquals(customerId, customer.getId())){
	    			BeforePersonalCustomerVo beforePersonalCustomerVo = new BeforePersonalCustomerVo(customer);
	    			rowData.put("actualUsePersonName", beforePersonalCustomerVo.getActualUsePersonName());
	    			rowData.put("actualUsePerson", beforePersonalCustomerVo.getActualUsePerson());
	    		}
	    	}
		} catch (Exception e) {
			logger.error("查询客户失败",e);
			e.printStackTrace();
		}
	    return ObjectHelper.objectToJson(rowData, jsoncallback);
	}
	
	
	/** 
	 * @Title: deleteHousePropertyById 
	 * @Description: 删除家庭收入
	 * @author liuhuan 
	 * @param customerId	客户id
	 * @param caseApplyId	案件id
	 * @return  
	 */ 
	@RequestMapping(value="/deleteFamilyIncomeById")
	@UriKey(key="com.zdsoft.finance.casemanage.datasurvey.familyincomeinfo.deleteFamilyIncomeById")
	@ResponseBody
	public String deleteFamilyIncomeById(String familyIncomeId){
		ResponseMsg responseMsg = new ResponseMsg();
		logger.info("删除家庭收入 ID为：" + familyIncomeId);
		try {
			familyIncomeService.deleteByFamilyIncomeId(familyIncomeId);
			responseMsg.setResultStatus(ResponseMsg.SUCCESS);
			responseMsg.setMsg("删除家庭收入成功");
		} catch (Exception e) {
			responseMsg.setMsg("删除家庭收入失败！");
			logger.error("删除家庭收入失败！" + e.getMessage());
			responseMsg.setResultStatus(ResponseMsg.SYS_ERROR);
			e.printStackTrace();
		}
		return ObjectHelper.objectToJson(responseMsg);
	}
	
	
	/**
	 * 
	 * @Title: saveFamilyIncome 
	 * @Description: 保存家庭收入
	 * @author liuhuan 
	 * @param persCustomerVo 客户信息
	 * @param request	参数
	 * @param averageMonthAmount 月均金额
	 * @param total	半年总金额
	 * @param jsoncallback
	 * @return
	 */
	@RequestMapping("/saveFamilyIncome")
	@UriKey(key="com.zdsoft.finance.casemanage.datasurvey.familyincomeinfo.save")
	@ResponseBody
	public String saveFamilyIncome(FamilyIncomeVo camilyIncomeVo, HttpServletRequest request, String jsoncallback){
		logger.info("----保存家庭收入--");
		ResponseMsg responseMsg = new ResponseMsg();
		
		FamilyIncome familyIncome = camilyIncomeVo.toPo();
		
		List<FamilyIncomeDetail> detailList = new ArrayList<FamilyIncomeDetail>();
		
		//封装客户最近6个月份的收入金额,为6个月
		for(int i=0; i<6; i++){
			//月份
			String month = request.getParameter("month["+i+"]");
			//金额
			String amount = request.getParameter("amount["+i+"]");
			
			FamilyIncomeDetail familyIncomeDetail = new FamilyIncomeDetail();
			
			familyIncomeDetail.setRealMonth(Long.parseLong(month));
			familyIncomeDetail.setRealAmount(new BigDecimal(amount.trim()));
			detailList.add(familyIncomeDetail);
		}
		try {
			familyIncome = familyIncomeService.saveFamilyIncome(familyIncome, detailList);
			responseMsg.setId(familyIncome.getId());
			responseMsg.setSourceKey(familyIncome.getHouseHolder());
			responseMsg.setMsg("保存家庭收入成功");
			responseMsg.setResultStatus(ResponseMsg.SUCCESS);
		} catch (Exception e) {
			logger.error("保存家庭收入失败：",e);
			responseMsg.setResultStatus(ResponseMsg.SYS_ERROR);
			responseMsg.setMsg("保存家庭收入失败！");
			e.printStackTrace();
		}
		return ObjectHelper.objectToJson(responseMsg,jsoncallback);
	}
}