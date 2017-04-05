package com.zdsoft.finance.app.marketing.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zdsoft.finance.common.utils.AppStatus;
import com.zdsoft.finance.common.utils.app.AppServerUtil;
import com.zdsoft.finance.marketing.entity.PropertyOwner;
import com.zdsoft.finance.marketing.service.PropertyOwnerService;
import com.zdsoft.finance.marketing.vo.PropertyOwnerVo;
import com.zdsoft.framework.core.common.dto.ResponseMsg;
import com.zdsoft.framework.core.common.util.ObjectHelper;
import com.zdsoft.framework.core.commweb.component.BaseController;

/** 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: AppPropertyOwnerController.java 
 * @ClassName: AppPropertyOwnerController 
 * @Description: 产权人App接口Controller
 * @author zhoushichao 
 * @date 2017年3月2日 下午2:24:18 
 * @version V1.0 
 */ 
@Controller
@RequestMapping("/server/bizCenter/houseProperty")
public class AppPropertyOwnerController extends BaseController {

	@Autowired
	private PropertyOwnerService propertyOwnerService;
	
	/**
	 * 
	 * @Title: savePropertyOwner 
	 * @Description: 保存产权人信息
	 * @author zhoushichao 
	 * @param propertyOwnerVo  产权人信息
	 * @param token  当前会话token值
	 * @return
	 */
	@RequestMapping(value="/addPropertyOwner",produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String savePropertyOwner(PropertyOwnerVo propertyOwnerVo,String token) {
		
		Map<String, Object> propertyOwnerMap = new HashMap<String, Object>();
		

		if (ObjectHelper.isEmpty(token)||ObjectHelper.isEmpty(propertyOwnerVo.getHousePropertyId())
				||ObjectHelper.isEmpty(propertyOwnerVo.getOwnerName())||ObjectHelper.isEmpty(propertyOwnerVo.getCredentialNo())
				||ObjectHelper.isEmpty(propertyOwnerVo.getIdentityStartDate())||ObjectHelper.isEmpty(propertyOwnerVo.getIdentityEndDate())
				||ObjectHelper.isEmpty(propertyOwnerVo.getPhoneNumber())||ObjectHelper.isEmpty(propertyOwnerVo.getEmailAddress())
				||ObjectHelper.isEmpty(propertyOwnerVo.getProvince())||ObjectHelper.isEmpty(propertyOwnerVo.getCity())
				||ObjectHelper.isEmpty(propertyOwnerVo.getDistrict())||ObjectHelper.isEmpty(propertyOwnerVo.getMailingAddress())) {
			return AppServerUtil.buildError(AppStatus.ArgsError);
		}
		PropertyOwner propertyOwner = new PropertyOwner();
		
		try {
			if (ObjectHelper.isNotEmpty(propertyOwnerVo.getId())) {
				propertyOwner = propertyOwnerService.findOne(propertyOwnerVo.getId());
			}
			propertyOwner.setHousePropertyId(propertyOwnerVo.getHousePropertyId());
			propertyOwner.setOwnerName(propertyOwnerVo.getOwnerName());
			propertyOwner.setCredentialNo(propertyOwnerVo.getCredentialNo());
			propertyOwner.setIdentityStartDate(propertyOwnerVo.getIdentityStartDate());
			propertyOwner.setIdentityEndDate(propertyOwnerVo.getIdentityEndDate());
			propertyOwner.setPhoneNumber(propertyOwnerVo.getPhoneNumber());
			propertyOwner.setEmailAddress(propertyOwnerVo.getEmailAddress());
			propertyOwner.setProvince(propertyOwnerVo.getProvince());
			propertyOwner.setCity(propertyOwnerVo.getCity());
			propertyOwner.setDistrict(propertyOwnerVo.getDistrict());
			propertyOwner.setMailingAddress(propertyOwnerVo.getMailingAddress());
			// 执行保存
			propertyOwner = propertyOwnerService.saveOrUpdateEntity(propertyOwner);
			propertyOwnerMap.put("id", propertyOwner.getId());
			propertyOwnerMap.put("status", ResponseMsg.SUCCESS);
			if(ObjectHelper.isNotEmpty(propertyOwnerVo.getId())){
				propertyOwnerMap.put("message", "修改产权人信息成功！");
			}else {
				propertyOwnerMap.put("message", "保存产权人信息成功！");
			}
		} catch (Exception e) {
			propertyOwnerMap.put("status", ResponseMsg.APP_ERROR);
			propertyOwnerMap.put("message", "保存产权人信息失败！");
			e.printStackTrace();
			logger.error("产权人信息保存失败", e);
		}
		return ObjectHelper.objectToJson(propertyOwnerMap);
	}
}
