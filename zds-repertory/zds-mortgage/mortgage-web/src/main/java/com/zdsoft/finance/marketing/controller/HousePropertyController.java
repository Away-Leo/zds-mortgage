package com.zdsoft.finance.marketing.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.finance.marketing.entity.CaseApply;
import com.zdsoft.finance.marketing.entity.HouseAssessment;
import com.zdsoft.finance.marketing.entity.HouseProperty;
import com.zdsoft.finance.marketing.entity.PledgeInfo;
import com.zdsoft.finance.marketing.entity.PropertyOwner;
import com.zdsoft.finance.marketing.entity.Search;
import com.zdsoft.finance.marketing.service.CaseApplyService;
import com.zdsoft.finance.marketing.service.HousePropertyService;
import com.zdsoft.finance.marketing.service.PledgeInfoService;
import com.zdsoft.finance.marketing.service.PropertyOwnerService;
import com.zdsoft.finance.marketing.service.SearchService;
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
 * 版权所有：重庆正大华日软件有限公司
 * @Title: HousePropertyController.java 
 * @ClassName: HousePropertyController 
 * @Description: 房产公共Controller 
 * @author zhoushichao 
 * @date 2017年2月18日 上午10:42:43 
 * @version V1.0 
 */ 
@Controller
@RequestMapping("marketing/houseProperty")
public class HousePropertyController extends BaseController {
	
	@Autowired
	private HousePropertyService housePropertyService;
	@Autowired
	private CaseApplyService caseApplyService;
	@Autowired
	private PledgeInfoService pledgeInfoService;
	@Autowired
	private PropertyOwnerService propertyOwnerService;	
	@Autowired
	private SearchService searchService;	

	/**
     * 
     * @Title: getHousePropertyList 
     * @Description: 根据案件ID查询房产信息
     * @author zhoushichao 
     * @param caseApplyId 房产ID
     * @param jsoncallback
     * @return
     */
	@RequestMapping("/getHousePropertyList")
	@UriKey(key = "com.cnfh.rms.marketing.houseProperty.getHousePropertyList")
	@ResponseBody
	public String getHousePropertyList(String caseApplyId, String jsoncallback) {

		ResponseMsg msg = new ResponseMsg();
		// 分页抵押信息
		List<HouseProperty> housePropertyList = null;
		List<HousePropertyVo> housePropertyVoList = new ArrayList<HousePropertyVo>();
		try {
			housePropertyList = housePropertyService.findByCaseApplyId(caseApplyId);
			
			for (HouseProperty houseProperty : housePropertyList) {
				HousePropertyVo housePropertyVo = new HousePropertyVo(houseProperty);
				housePropertyVoList.add(housePropertyVo);			
			}
				
			msg.setTotal(new Long(housePropertyVoList.size()));
			msg.setRows(housePropertyVoList);
			msg.setMsg("房产信息列表查询成功！");
			msg.setResultStatus(ResponseMsg.SUCCESS);
		} catch (Exception e) {
			msg.setResultStatus(ResponseMsg.SYS_ERROR);
			msg.setMsg("房产信息列表查询失败");
			e.printStackTrace();
			logger.error("房产信息列表查询失败:", e);
		}
		return ObjectHelper.objectToJson(msg, jsoncallback);
	}
	
	/**
	 * 
	 * @Title: listSingleHouse 
	 * @Description: 根据房产ID查询房产信息（单个押品列表）
	 * @author zhoushichao 
	 * @param housePropertyId 房产ID
	 * @return
	 */
    @RequestMapping("/listSingleHouseProperty")
    @UriKey(key = "com.cnfh.rms.marketing.houseProperty.listSingleHouseProperty")
    @ResponseBody
    public String listSingleHouseProperty(String housePropertyId, String jsoncallback){
        ResponseMsg msg = new ResponseMsg();
        List<HousePropertyVo> housePropertyVoList = new ArrayList<HousePropertyVo>();
        try {
            HouseProperty houseProperty = housePropertyService.findOne(housePropertyId);
            HousePropertyVo housePropertyVo = new HousePropertyVo(houseProperty);
            
            housePropertyVoList.add(housePropertyVo);
            msg.setTotal(new Long(housePropertyVoList.size()));
            msg.setRows(housePropertyVoList);
            msg.setMsg("查找单个押品信息成功");
            msg.setResultStatus(ResponseMsg.SUCCESS);
        } catch (BusinessException e) {
            e.printStackTrace();
            msg.setMsg("查找单个押品信息失败");
            msg.setResultStatus(ResponseMsg.APP_ERROR);
            logger.error("查找单个押品信息失败:"+e);
        }
        return ObjectHelper.objectToJson(msg, jsoncallback);
    }
    
    /**
     * 
     * @Title: editHousePropertyTab 
     * @Description: 获取押品信息tab页面
     * @author zhoushichao 
     * @param caseApplyId 案件Id
     * @return
     */
	@RequestMapping(value="/editHousePropertyTab")
	@UriKey(key = "com.cnfh.rms.marketing.houseProperty.editHousePropertyTab")
	public ModelAndView editHousePropertyTab(String caseApplyId) {
		logger.info("查询押品信息，案件ID为：" + caseApplyId);
		ModelMap model = new ModelMap();
		try {
			model=intoHousePropertyTabData(caseApplyId);
		} catch (Exception e) {
			logger.error("查询押品信息出错", e);
			e.printStackTrace();
		}
		return new ModelAndView("casemanage/datasurvey/housePledgeInfoModules/pledge_info_main_edit",model);
	}
	
	/** 
	 * @Title: viewHousePropertyTab 
	 * @Description: 获取押品查看信息tab页面
	 * @author zjx 
	 * @param caseApplyId案件ID
	 * @return  
	 */ 
	@RequestMapping(value="/viewHousePropertyTab")
	@UriKey(key = "com.cnfh.rms.marketing.houseProperty.viewHousePropertyTab")
	public ModelAndView viewHousePropertyTab(String caseApplyId) {
		logger.info("查询押品信息，案件ID为：" + caseApplyId);
		ModelMap model = new ModelMap();
		try {
			model=intoHousePropertyTabData(caseApplyId);
		} catch (Exception e) {
			logger.error("查询押品信息出错", e);
			e.printStackTrace();
		}
		return new ModelAndView("casemanage/datasurvey/housePledgeInfoModules/pledge_info_main_view",model);
	}
	
	/** 
	 * @Title: intoHousePropertyTabData 
	 * @Description: 组装押品信息tab页面数据
	 * @author zjx 
	 * @param caseApplyId案件Id
	 * @return  
	 */ 
	private ModelMap intoHousePropertyTabData(String caseApplyId) {
		ModelMap model = new ModelMap();
		List<HousePropertyVo> voList = new ArrayList<HousePropertyVo>();
		List<HouseProperty> list = housePropertyService.findByCaseApplyId(caseApplyId);
		for (HouseProperty houseProperty : list) {
			HousePropertyVo housePropertyVo = new HousePropertyVo(houseProperty);
			voList.add(housePropertyVo);
		}
		model.put("voList", voList);
		return model;
	}

	/**
	 * 
	 * @Title: editHousePropertyById 
	 * @Description: 编辑押品信息
	 * @author zhoushichao 
	 * @param housePropertyId  房产Id
	 * @return
	 */
	@RequestMapping(value="/editHousePropertyById")
	@UriKey(key="com.cnfh.rms.marketing.houseProperty.editHousePropertyById")
	public ModelAndView editHousePropertyById(String housePropertyId){
		ModelMap model = new ModelMap();
		try {
			model = initHousePropertyData(housePropertyId);
		} catch (BusinessException e) {
			logger.error("查询房产信息出错" + e.getMessage());
			e.printStackTrace();
		}
		return new ModelAndView("casemanage/datasurvey/housePledgeInfoModules/houseinfomation_edit",model);
	}
	
	/** 
	 * @Title: viewHousePropertyById 
	 * @Description: 查看押品信息
	 * @author zjx 
	 * @param housePropertyId 房产Id
	 * @return  
	 */ 
	@RequestMapping(value="/viewHousePropertyById")
	@UriKey(key="com.cnfh.rms.marketing.houseProperty.viewHousePropertyById")
	public ModelAndView viewHousePropertyById(String housePropertyId){
		ModelMap model = new ModelMap();
		try {
			model = initHousePropertyData(housePropertyId);
		} catch (BusinessException e) {
			logger.error("查询房产信息出错" + e.getMessage());
			e.printStackTrace();
		}
		return new ModelAndView("casemanage/datasurvey/housePledgeInfoModules/houseinfomation_view",model);
	}
	
	/** 
	 * @Title: initHousePropertyData 
	 * @Description: 组装房产信息页面数据
	 * @author zjx 
	 * @param housePropertyId
	 * @return
	 * @throws BusinessException  
	 */ 
	private ModelMap initHousePropertyData(String housePropertyId) throws BusinessException {
		ModelMap model = new ModelMap();
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
		return model;
	}

	/**
	 * 
	 * @Title: deleteHousePropertyById 
	 * @Description: 根据房产Id删除押品信息
	 * @author zhoushichao 
	 * @param housePropertyId 房产Id
	 * @return
	 */
	@RequestMapping(value="/deleteHousePropertyById")
	@UriKey(key="com.cnfh.rms.marketing.houseProperty.deleteHousePropertyById")
	@ResponseBody
	public ResponseMsg deleteHousePropertyById(String housePropertyId){
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
		return responseMsg;
	}
	
	/**
	 * 
	 * @Title: savePledgeInfo 
	 * @Description: 保存押品信息
	 * @author zhoushichao 
	 * @param propertyList  产权人信息
	 * @param pledgeList 抵押
	 * @param search 产权状态
	 * @param houseProperty 房产
	 * @param caseApplyId 案件Id
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/saveHouseProperty")
	@UriKey(key="com.cnfh.rms.marketing.houseProperty.saveHouseProperty")
	@ResponseBody
	public ResponseMsg savePledgeInfo(String propertyList,String pledgeList,HousePropertyVo housePropertyVo,String caseApplyId){
		ResponseMsg responseMsg = new ResponseMsg();
		Map<String, Object> map = new HashMap<String, Object>();
		//转换产权人信息
		JSONArray propertyJson = JSONArray.fromObject(propertyList);
		List<PropertyOwner> propertyOwnerList = JSONArray.toList(propertyJson, new PropertyOwner(),new JsonConfig());
		//转换抵押信息
		JSONArray pledgeJson = JSONArray.fromObject(pledgeList);
		List<PledgeInfo> pledgeInfoList = JSONArray.toList(pledgeJson, new PledgeInfo(),new JsonConfig());
		
		HouseProperty houseProperty = housePropertyVo.toPo();
		Search search = housePropertyVo.getSearchVo().toPo();
		
		//设置产权状态
		if (ObjectHelper.isNotEmpty(search)) {
			Search searchPo = new Search();
			if (ObjectHelper.isNotEmpty(search.getId())) {
				try {
					searchPo = searchService.findOne(search.getId());
					searchPo.setUpdateTime(new Date());
				} catch (BusinessException e) {
					e.printStackTrace();
				}
			}else{
				searchPo.setCreateTime(new Date());
			}
			BeanUtils.copyProperties(search, searchPo , new String[]{"isDeleted","createTime","updateTime"});
			houseProperty.setSearch(searchPo);
		}
		
		//抵押情况
		List<PledgeInfo> pledgeInfoPoList = new ArrayList<PledgeInfo>();
		if(ObjectHelper.isNotEmpty(pledgeInfoList)){
			for (PledgeInfo pledgeInfo : pledgeInfoList) {
				PledgeInfo pledge = new PledgeInfo();
				if(ObjectHelper.isNotEmpty(pledgeInfo.getId())){
					try {
						pledge = pledgeInfoService.findOne(pledgeInfo.getId());
						pledge.setUpdateTime(new Date());
					} catch (BusinessException e) {
						e.printStackTrace();
					}
				}else{
					pledge.setCreateTime(new Date());
				}
				BeanUtils.copyProperties(pledgeInfo, pledge , new String[]{"isDeleted","createTime","updateTime"});
				pledgeInfoPoList.add(pledge);
			}
		}
		houseProperty.setPledgeInfoList(pledgeInfoPoList);
		
		//产权人信息
		List<PropertyOwner> propertyOwnerPoList = new ArrayList<PropertyOwner>();
		if(ObjectHelper.isNotEmpty(propertyOwnerList)){
			for (PropertyOwner propertyOwner : propertyOwnerList) {
				PropertyOwner property = new PropertyOwner();
				if(ObjectHelper.isNotEmpty(propertyOwner.getId())){
					try {
						property = propertyOwnerService.findOne(propertyOwner.getId());
						property.setUpdateTime(new Date());
					} catch (BusinessException e) {
						e.printStackTrace();
					}
				}else{
					property.setCreateTime(new Date());
				}
				BeanUtils.copyProperties(propertyOwner, property , new String[]{"isDeleted","createTime","updateTime"});
				propertyOwnerPoList.add(property);
			}
		}
		//房产设置产权人信息
		houseProperty.setPropertyOwnerList(propertyOwnerPoList);
		
		try {
			CaseApply caseApply = caseApplyService.findOne(caseApplyId);
			houseProperty.setCaseApply(caseApply);
			//保存押品所有信息
			houseProperty = housePropertyService.saveOrUpdateHouseProperty(houseProperty);
			
			map.put("housePropertyId", houseProperty.getId());
			map.put("searchId", houseProperty.getSearch().getId());
			map.put("communityName", houseProperty.getCommunityName());
			responseMsg.setOptional(map);
			responseMsg.setMsg("保存押品信息成功");
			responseMsg.setResultStatus(ResponseMsg.SUCCESS);
		} catch (Exception e) {
			responseMsg.setResultStatus(ResponseMsg.SYS_ERROR);
			responseMsg.setMsg("保存押品所有信息出错");
			logger.error("保存押品所有信息出错：" + e.getMessage());
			e.printStackTrace();
		}
		return responseMsg;
	}
}