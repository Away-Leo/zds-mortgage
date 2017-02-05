package com.zdsoft.finance.casemanage.datasurvey.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.finance.marketing.entity.HouseAssessment;
import com.zdsoft.finance.marketing.entity.HouseProperty;
import com.zdsoft.finance.marketing.entity.PledgeInfo;
import com.zdsoft.finance.marketing.entity.PropertyOwner;
import com.zdsoft.finance.marketing.entity.Search;
import com.zdsoft.finance.marketing.service.HousePropertyService;
import com.zdsoft.finance.marketing.vo.HouseAssessmentVo;
import com.zdsoft.finance.marketing.vo.HousePropertyVo;
import com.zdsoft.finance.marketing.vo.PledgeInfoVo;
import com.zdsoft.finance.marketing.vo.SearchVo;
import com.zdsoft.framework.core.common.dto.ResponseMsg;
import com.zdsoft.framework.core.common.util.ObjectHelper;
import com.zdsoft.framework.core.commweb.annotation.UriKey;
import com.zdsoft.framework.core.commweb.component.BaseController;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * 
 * @Title:HouseInfomationController.java
 * @Package:com.zdsoft.finance.casemanage.datasurvey.controller
 * @Description:
 * @author: laijun
 * @date:2017年1月14日 下午9:05:01
 * @version:v1.0
 */
@Controller
@RequestMapping("/casemanage/datasurvey/houseinfomation")
public class HouseInfomationController extends BaseController {
	
	@Autowired
	private HousePropertyService housePropertyService;
	
	/**
	 * 
	 * 押品信息
	 *
	 * @author laijun
	 * @date:2017年1月14日 下午9:06:43
	 * @return
	 */
	@RequestMapping(value="/edit")
	@UriKey(key = "com.zdsoft.finance.casemanage.datasurvey.pledgeInfoMain")
	public ModelAndView pledgeInfoMain(String caseApplyId,ModelMap model) {
		logger.info("查询押品信息，案件ID为：" + caseApplyId);
		try {
			List<HouseProperty> list = housePropertyService.findByCaseApplyId(caseApplyId);
			String[] houseIds = new String[list.size()];
			String[] index = new String[list.size()];
			int i = 0;
			for (HouseProperty houseProperty : list) {
				houseIds[i] = houseProperty.getId();
				index[i] = i + "";
				i++;
			}
			model.put("houseIds", houseIds);
			model.put("index", index);
			model.put("caseApplyId", caseApplyId);
		} catch (Exception e) {
			logger.error("查询押品信息出错");
			e.printStackTrace();
		}
		return new ModelAndView("casemanage/datasurvey/housePledgeInfoModules/pledge_info_main",model);
	}
	
	/**
	 * 获取押品信息
	 *
	 * @author wrw
	 * @param model
	 * @param housePropertyId
	 * @return
	 */
	@RequestMapping(value="/queryHousePropertyById")
	@UriKey(key="com.zdsoft.finance.caseManager.queryHousePropertyById")
	public ModelAndView queryHousePropertyById(ModelMap model,String housePropertyId,String caseApplyId){
		try {
			if (ObjectHelper.isEmpty(housePropertyId)) {
				HousePropertyVo housePropertyVo=new HousePropertyVo();
				housePropertyVo.setCaseApplyId(caseApplyId);
				model.put("housePropertyVo", housePropertyVo);
				return new ModelAndView("casemanage/datasurvey/housePledgeInfoModules/houseinfomation_edit",model);
			}
			HouseProperty houseProperty = housePropertyService.findOne(housePropertyId);
			if (ObjectHelper.isNotEmpty(houseProperty)) {
				model.put("housePropertyVo", new HousePropertyVo(houseProperty));
			}else{
				logger.error("房产信息ID不存在，ID:" + housePropertyId);
			}
			//获取抵押情况
			List<PledgeInfo> pledgeInfoList = houseProperty.getPledgeInfoList();
			if (ObjectHelper.isNotEmpty(pledgeInfoList)) {
				List<PledgeInfoVo> pledgeInfoVoList = new ArrayList<>();
				for (PledgeInfo pledgeInfo : pledgeInfoList) {
					pledgeInfoVoList.add(new PledgeInfoVo(pledgeInfo));
				}
				model.put("pledgeInfoVoList", pledgeInfoVoList);
			}
			//获取产权状态
			Search search = houseProperty.getSearch();
			if (ObjectHelper.isNotEmpty(search)) {
				model.put("searchVo", new SearchVo(search));
			}
			//获取产权人信息
			List<HouseAssessment> houseAssessmentList = houseProperty.getHouseAssessmentList();
			if (ObjectHelper.isNotEmpty(houseAssessmentList)) {
				List<HouseAssessmentVo> houseAssessmentVoList = new ArrayList<>();
				for (HouseAssessment houseAssessment : houseAssessmentList) {
					houseAssessmentVoList.add(new HouseAssessmentVo(houseAssessment));
				}
				model.put("houseAssessmentVoList", houseAssessmentVoList);
			}
		} catch (BusinessException e) {
			logger.error("查询房产信息出错" + e.getMessage());
			e.printStackTrace();
		}
		model.put("housePropertyId", housePropertyId);
		return new ModelAndView("casemanage/datasurvey/housePledgeInfoModules/houseinfomation_edit",model);
	}
	
	
	/**
	 * 删除押品信息
	 *
	 * @author wrw
	 * @param housePropertyId
	 * @return
	 */
	@RequestMapping(value="/deleteHousePropertyById")
	@UriKey(key="com.zdsoft.finance.caseManager.deleteHousePropertyById")
	@ResponseBody
	public String deleteHousePropertyById(String housePropertyId,String jsoncallback){
		ResponseMsg responseMsg = new ResponseMsg();
		logger.info("删除押品信息 押品ID为：" + housePropertyId);
		try {
			housePropertyService.deleteHousePropertyById(housePropertyId);
			responseMsg.setResultStatus(ResponseMsg.SUCCESS);
			responseMsg.setMsg("删除成功");
		} catch (BusinessException e) {
			logger.error("删除出错" + e.getMessage());
			responseMsg.setResultStatus(ResponseMsg.SYS_ERROR);
			e.printStackTrace();
		}
		return ObjectHelper.objectToJson(responseMsg,jsoncallback);
	}
	
	
	/**
	 * 保存押品信息
	 *
	 * @author wrw
	 * @param propertyList
	 * @param pledgeList
	 * @param searchVo
	 * @param housePropertyVo
	 * @param model
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/savePledgeInfo")
	@ResponseBody
	@UriKey(key="com.zdsoft.finance.caseManager.savePledgeInfo")
	public String savePledgeInfo(String propertyList,String pledgeList,Search search,HouseProperty houseProperty,ModelMap model,String searchId,String jsoncallback){
		logger.info("保存押品所有信息开始： 案件ID为：" + houseProperty.getCaseApply().getId());
		ResponseMsg responseMsg = new ResponseMsg();
		//转换抵押信息
		JSONArray json = JSONArray.fromObject(propertyList);
		List<PropertyOwner> propertyOwnerList = JSONArray.toList(json, new PropertyOwner(),new JsonConfig());
		//转换产权人信息
		json = JSONArray.fromObject(pledgeList);
		List<PledgeInfo> pledgeInfoList = JSONArray.toList(json, new PledgeInfo(),new JsonConfig());
		//设置产权状态ID
		search.setId(searchId);
		
		try {
			//保存押品所有信息
			String houseId = housePropertyService.savePledgeInfo(search, houseProperty, propertyOwnerList, pledgeInfoList);
			responseMsg.setSourceKey(houseId);
			responseMsg.setMsg("保存押品信息成功");
			responseMsg.setResultStatus(ResponseMsg.SUCCESS);
		} catch (Exception e) {
			responseMsg.setResultStatus(ResponseMsg.SYS_ERROR);
			logger.error("保存押品所有信息出错：" + e.getMessage());
			e.printStackTrace();
		}
		return ObjectHelper.objectToJson(responseMsg,jsoncallback);
	}
}
