package com.zdsoft.finance.marketing.controller;

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
 * @Title:AppPropertyOwnerController.java
 * @Package:com.zdsoft.finance.marketing.controller
 * @Description:产权人App接口Controller
 * @author: zhoushichao
 * @date:2017年1月13日 下午9:13:29
 * @version:v1.0
 */
@Controller
@RequestMapping("/server/bizCenter/houseProperty")
public class AppPropertyOwnerController extends BaseController {

	@Autowired
	private PropertyOwnerService propertyOwnerService;
	
	/**
	 * 保存产权人信息
	 * @param propertyOwnerVo 产权人
	 * @return
	 */
	@RequestMapping(value="/addPropertyOwner",produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String savePropertyOwner(PropertyOwnerVo propertyOwnerVo,String token) {
		
		Map<String, Object> propertyOwnerMap = new HashMap<String, Object>();
		
		PropertyOwner propertyOwner = null;

		// 将Vo转成Po
		propertyOwner = propertyOwnerVo.toPO();
		if (ObjectHelper.isEmpty(token)) {
			return AppServerUtil.buildError(AppStatus.ArgsError);
		}
		
		// 执行保存
		try {
			propertyOwner = propertyOwnerService.saveOrUpdateEntity(propertyOwner);
			propertyOwnerMap.put("id", propertyOwner.getId());
			propertyOwnerMap.put("status", ResponseMsg.SUCCESS);
			if(ObjectHelper.isNotEmpty(propertyOwnerVo.toPO().getId())){
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
