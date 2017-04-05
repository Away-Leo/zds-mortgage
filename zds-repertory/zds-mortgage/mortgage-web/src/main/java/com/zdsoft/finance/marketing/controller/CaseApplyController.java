package com.zdsoft.finance.marketing.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zdsoft.essential.client.aop.annotation.DataAuthResource;
import com.zdsoft.essential.client.service.CED;
import com.zdsoft.essential.dto.basedata.SimpleCodeDto;
import com.zdsoft.essential.dto.permission.DataOperPermDto;
import com.zdsoft.finance.common.base.QueryObj;
import com.zdsoft.finance.common.base.impl.CustomCommon;
import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.finance.common.utils.ParameterUtil;
import com.zdsoft.finance.marketing.service.CaseApplyService;
import com.zdsoft.finance.marketing.vo.RelationCaseInfoVo;
import com.zdsoft.framework.core.common.dto.ResponseMsg;
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
 * @Title: CaseApplyController.java 
 * @ClassName: CaseApplyController 
 * @Description: 案件申请Controller
 * @author zhoushichao 
 * @date 2017年3月14日 下午7:45:44 
 * @version V1.0
 */
@Controller
@RequestMapping("/caseApply")
public class CaseApplyController extends BaseController {
	
	/**
	 * 房产属性基础数据类型Id值
	 */
	public static final String HOUSE_PROPERTY_CATEGORY_ID = "YWDM0057";
	
	/**
	 * 抵押情况基础数据类型Id值
	 */
	public static final String PLEDGE_TYPE_CATEGORY_ID = "YWDM0052";

	@Autowired
	private CaseApplyService caseApplyService;
	
	@Autowired
	private CED CED;
	
	/**
	 * 
	 * @Title: caseApplyList 
	 * @Description: 案件查询列表菜单注册入口
	 * @author zhoushichao 
	 * @return
	 */
	@RequestMapping("/caseApplyList")
	@UriKey(key = "com.zdsoft.finance.marketing.caseApplyList")
	@Menu(resource = "com.zdsoft.finance.marketing.caseApplyList", label = "案件查询", group = "marketing", order = 14)
	@DataAuthResource(resource="com.zdsoft.finance.marketing.caseApplyList.dataAuth",label="案件查询",group="com.zdsoft.finance.marketing.caseApplyList")
	public ModelAndView caseApplyList() {
		return new ModelAndView("marketing/case_apply_list");
	}
	
	/**
	 * 
	 * @Title: getCaseApplyList 
	 * @Description: 案件查询查询列表
	 * @author zhoushichao 
	 * @param request 请求参数
	 * @param pageable 分页信息
	 * @return
	 */
	@RequestMapping("/getCaseApplyList")
	@UriKey(key = "com.zdsoft.finance.marketing.getCaseApplyList")
	@ResponseBody
	public String getCaseApplyList(HttpServletRequest request,  PageRequest pageable) {

		ResponseMsg msg = new ResponseMsg();
		// 获取页面封装的查询参数
		@SuppressWarnings("unchecked")
		List<QueryObj> queryObjs = (List<QueryObj>) request.getAttribute("listObj");
		try {
			 //数据权限
            DataOperPermDto dtOperPermDto=CED.findDataPerms(StoreHelper.getApplication(), "com.zdsoft.finance.marketing.caseApplyList.dataAuth");
			Page<Map<String, Object>> caseApplyPage = caseApplyService.findPageCaseApply(pageable, queryObjs,dtOperPermDto);
		
			msg.setMsg("列表查询成功!");
			msg.setResultStatus(ResponseMsg.SUCCESS);
			msg.setTotal(caseApplyPage.getTotalRows());
			msg.setRows(caseApplyPage.getRecords());
		} catch (Exception e) {
			msg.setMsg("列表查询成功失败!");
			msg.setResultStatus(ResponseMsg.SYS_ERROR);
			e.printStackTrace();
			logger.error("列表查询成功失败",e);
		}
		return ObjectHelper.objectToJson(msg);
	}
	
	/**
	 * @Title: getCaseApplyCustomerReceivableList 
	 * @Description: 获取案件信息（包括逾期信息）
	 * @author jincheng 
	 * @param request
	 * @param pageable
	 * @return
	 */
	@RequestMapping("/getCaseApplyCustomerReceivableList")
	@UriKey(key = "com.zdsoft.finance.marketing.getCaseApplyCustomerReceivableList")
	@ResponseBody
	public String getCaseApplyCustomerReceivableList(HttpServletRequest request, PageRequest pageable){
		ResponseMsg msg = new ResponseMsg();
		 try {
			DataOperPermDto dataOperPermDto = CED.findDataPerms(StoreHelper.getApplication(), "com.zdsoft.finance.repayment.caseList.dataAuth");
			List<QueryObj> queryObjs =ParameterUtil.getQueryObjByParameter(request, new String[]{"mca","cus","cla","loan","pp"});
			Page<Map<String, Object>> caseApplyPage = caseApplyService.getCaseApplyCustomerReceivableList(pageable, queryObjs,dataOperPermDto);
			msg.setResultStatus(ResponseMsg.SUCCESS);
			msg.setTotal(caseApplyPage.getTotalRows());
			msg.setRows(caseApplyPage.getRecords());
		} catch (AppException e) {
			e.printStackTrace();
		}
		return ObjectHelper.objectToJson(msg);
	}
	
	/**
	 * 
	 * @Title: getCaseApplyCommissionList 
	 * @Description: 案件支佣列表数据
	 * @author xiangjx 
	 * @param request
	 * @param pageable
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/getCaseApplyCommissionList")
	@UriKey(key = "com.zdsoft.finance.marketing.getCaseApplyCommissionList")
	@ResponseBody
	public String getCaseApplyCommissionList(HttpServletRequest request, PageRequest pageable) throws Exception {

		// 获取页面封装的查询参数
		@SuppressWarnings("unchecked")
		List<QueryObj> queryObjs = (List<QueryObj>) request.getAttribute("listObj");
		
		Page<Map<String, Object>> caseApplyPage = caseApplyService.findPageCaseApplyAndCommission(pageable, queryObjs);

		ResponseMsg msg = new ResponseMsg();
		msg.setMsg("列表查询成功!");
		msg.setResultStatus(ResponseMsg.SUCCESS);
		msg.setTotal(caseApplyPage.getTotalRows());
		msg.setRows(caseApplyPage.getRecords());
		return ObjectHelper.objectToJson(msg);
	}
	
	/**
	 * 
	 * @Title: initCustomerRelationCaseView 
	 * @Description: 进入关联案件的页面
	 * @author jingyh 
	 * @param req
	 * @param customerId
	 * @param exceptCaseIds
	 * @return
	 */
	@RequestMapping("/initCustomerRelationCaseView")
	@UriKey(key = "com.zdsoft.finance.marketing.initCustomerRelationCaseView")
	public ModelAndView initCustomerRelationCaseView(HttpServletRequest req, String customerId,String exceptCaseIds) {
		logger.info("进入关联案件页面");
		logger.debug("客户Id：{}", customerId);
		logger.debug("排除的案件Id：{}", exceptCaseIds);
		ModelMap model = new ModelMap();
		model.put("customerId", customerId);
		model.put("exceptCaseIds", exceptCaseIds);
		return new ModelAndView("casemanage/relation_case_infos_list",model);
	}
	
	/**
	 * 
	 * @Title: findCustomerRelationCaseInfos 
	 * @Description: 查询客户关联案件信息
	 * @author jingyh 
	 * @param req
	 * @param pageReq
	 * @param customerId
	 * @param exceptCaseIds
	 * @return
	 */
	@RequestMapping("/findCustomerRelationCaseInfos")
	@UriKey(key = "com.zdsoft.finance.marketing.findCustomerRelationCaseInfos")
	@ResponseBody
	public ResponseMsg findCustomerRelationCaseInfos(HttpServletRequest req, PageRequest pageReq, String customerId,String exceptCaseIds) {
		ResponseMsg msg = new ResponseMsg();
		try {
			if (ObjectHelper.isEmpty(customerId)) {
				throw new BusinessException("10010004", "传入客户Id为空！");
			}
			List<String> ids = new ArrayList<String>();
			if (ObjectHelper.isNotEmpty(exceptCaseIds)) {
				String[] tempVtc = exceptCaseIds.split(",");
				ids = Arrays.asList(tempVtc);
			}
			// SQL查询数据库
			Page<Map<String, Object>> queryPageInfo = this.caseApplyService.findRelationCaseInfos(pageReq, customerId, ids);
			List<RelationCaseInfoVo> returnList = new ArrayList<RelationCaseInfoVo>();
			if (ObjectHelper.isNotEmpty(queryPageInfo)) {
				List<SimpleCodeDto> housePropertyList = CED.querySimpleCodeByCategoryId(HOUSE_PROPERTY_CATEGORY_ID);
				List<SimpleCodeDto> pledgeTypeList = CED.querySimpleCodeByCategoryId(PLEDGE_TYPE_CATEGORY_ID);
				List<Object> objList = CustomCommon.convert(RelationCaseInfoVo.class, queryPageInfo.getRecords());
				for (Object obj : objList) {
					RelationCaseInfoVo voObj = (RelationCaseInfoVo)obj;
					// 基础上数据的转换
					// 房产属性
					if (ObjectHelper.isNotEmpty(voObj.getHouseProperty())) {
						for (SimpleCodeDto temp : housePropertyList) {
							if (voObj.getHouseProperty().equals(temp.getFullCode())) {
								voObj.setHousePropertyName(temp.getName());
								break;
							}
						}
					}
					// 抵押模式
					if (ObjectHelper.isNotEmpty(voObj.getPledgeType())) {
						for (SimpleCodeDto temp : pledgeTypeList) {
							if (voObj.getPledgeType().equals(temp.getFullCode())) {
								voObj.setPledgeTypeName(temp.getName());
								break;
							}
						}
					}
					returnList.add(voObj);
				}
			}
			msg.setRows(returnList);
			msg.setTotal(queryPageInfo.getTotalRows());
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("查询关联案件信息出现错误!");
			msg.setResultStatus(ResponseMsg.APP_ERROR);
			msg.setMsg(e.getMessage());
		}
		return msg;
	}
	
}
