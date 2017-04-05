package com.zdsoft.finance.app.marketing.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zdsoft.finance.common.utils.AppStatus;
import com.zdsoft.finance.common.utils.app.AppServerUtil;
import com.zdsoft.finance.marketing.entity.CaseApply;
import com.zdsoft.finance.marketing.entity.HouseProperty;
import com.zdsoft.finance.marketing.service.CaseApplyService;
import com.zdsoft.finance.marketing.service.HousePropertyService;
import com.zdsoft.finance.marketing.vo.HousePropertyVo;
import com.zdsoft.framework.core.common.dto.ResponseMsg;
import com.zdsoft.framework.core.common.util.ObjectHelper;
import com.zdsoft.framework.core.commweb.component.BaseController;

/** 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: AppHousePropertyController.java 
 * @ClassName: AppHousePropertyController 
 * @Description: 房产App接口Controller
 * @author zhoushichao 
 * @date 2017年3月2日 下午1:56:16 
 * @version V1.0 
 */ 
@Controller
@RequestMapping("/server/bizCenter/houseProperty")
public class AppHousePropertyController extends BaseController {

	@Autowired
	private HousePropertyService housePropertyService;
	@Autowired
	private CaseApplyService caseApplyService;
	
	/**
	 * 
	 * @Title: saveHouseProperty 
	 * @Description: 保存房产信息
	 * @author zhoushichao 
	 * @param housePropertyVo  房产信息
	 * @param token  当前会话token值
	 * @return
	 */
	@RequestMapping(value = "/addBaseInfo",produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String saveHouseProperty(HousePropertyVo housePropertyVo,String token){
		Map<String, Object> houseMap = new HashMap<String, Object>();
		
		if (ObjectHelper.isEmpty(token)||ObjectHelper.isEmpty(housePropertyVo.getCaseApplyId())||ObjectHelper.isEmpty(housePropertyVo.getMortgageSituation())
				||ObjectHelper.isEmpty(housePropertyVo.getCommunityName())||ObjectHelper.isEmpty(housePropertyVo.getMailingAddress())
				||ObjectHelper.isEmpty(housePropertyVo.getArea())||ObjectHelper.isEmpty(housePropertyVo.getEstateOwnership())
				||ObjectHelper.isEmpty(housePropertyVo.getPlaceFloor())||ObjectHelper.isEmpty(housePropertyVo.getSumFloor())
				||ObjectHelper.isEmpty(housePropertyVo.getEstateProperties())||ObjectHelper.isEmpty(housePropertyVo.getDistrict())
				||ObjectHelper.isEmpty(housePropertyVo.getProvince())||ObjectHelper.isEmpty(housePropertyVo.getCity())) {
			return AppServerUtil.buildError(AppStatus.ArgsError);
		}
		
		try {
			HouseProperty houseProperty = null;
			if (ObjectHelper.isNotEmpty(housePropertyVo.getId())) {
				houseProperty = housePropertyService.findOne(housePropertyVo.getId());
			}else {
				houseProperty = new HouseProperty();
			}
			houseProperty.setCommunityName(housePropertyVo.getCommunityName());
			houseProperty.setMortgageSituation(housePropertyVo.getMortgageSituation());
			houseProperty.setMailingAddress(housePropertyVo.getMailingAddress());
			houseProperty.setFloorAge(housePropertyVo.getFloorAge());
			houseProperty.setArea(housePropertyVo.getArea());
			houseProperty.setEstateOwnership(housePropertyVo.getEstateOwnership());
			houseProperty.setPlaceFloor(housePropertyVo.getPlaceFloor());
			houseProperty.setSumFloor(housePropertyVo.getSumFloor());
			houseProperty.setEstateProperties(housePropertyVo.getEstateProperties());
			houseProperty.setDistrict(housePropertyVo.getDistrict());
			houseProperty.setProvince(housePropertyVo.getProvince());
			houseProperty.setCity(housePropertyVo.getCity());
			
			CaseApply caseApply = caseApplyService.findOne(housePropertyVo.getCaseApplyId());
			houseProperty.setCaseApply(caseApply);
			houseProperty = housePropertyService.saveOrUpdateHouseProperty(houseProperty);
			houseMap.put("id", houseProperty.getId());
			houseMap.put("status", ResponseMsg.SUCCESS);
			if(ObjectHelper.isNotEmpty(housePropertyVo.getId())){
				houseMap.put("message", "修改房产信息成功！");
			}else {
				houseMap.put("message", "保存房产信息成功！");
			}
		} catch (Exception e) {
			houseMap.put("status", ResponseMsg.APP_ERROR);
			houseMap.put("message", "保存房产信息失败！");
			e.printStackTrace();
			logger.error("保存房产信息失败！", e);
		}
		return ObjectHelper.objectToJson(houseMap);
	}
}
