package com.zdsoft.finance.marketing.controller;

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
 * @Title:AppHousePropertyController.java
 * @Package:com.zdsoft.finance.marketing.controller
 * @Description:房产App接口Controller
 * @author: zhoushichao
 * @date:2017年1月13日 下午8:42:18
 * @version:v1.0
 */
@Controller
@RequestMapping("/server/bizCenter/houseProperty")
public class AppHousePropertyController extends BaseController {

	@Autowired
	private HousePropertyService housePropertyService;
	@Autowired
	private CaseApplyService caseApplyService;
	
	@RequestMapping(value = "/addBaseInfo",produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String saveHouseProperty(HousePropertyVo housePropertyVo,String token){
		Map<String, Object> houseMap = new HashMap<String, Object>();
		
		HouseProperty houseProperty = housePropertyVo.toPO();
		if (ObjectHelper.isEmpty(token)) {
			return AppServerUtil.buildError(AppStatus.ArgsError);
		}
		
		try {
			if(ObjectHelper.isNotEmpty(housePropertyVo.getCaseApplyId())){
				CaseApply caseApply = caseApplyService.findOne(housePropertyVo.getCaseApplyId());
				houseProperty.setCaseApply(caseApply);
			}
			houseProperty = housePropertyService.saveOrUpdateHouseProperty(houseProperty);
			houseMap.put("id", houseProperty.getId());
			houseMap.put("status", ResponseMsg.SUCCESS);
			if(ObjectHelper.isNotEmpty(housePropertyVo.toPO().getId())){
				houseMap.put("message", "修改房产信息成功！");
			}else {
				houseMap.put("message", "保存房产信息成功！");
			}
		} catch (Exception e) {
			houseMap.put("status", ResponseMsg.APP_ERROR);
			houseMap.put("message", "保存房产信息失败！");
			e.printStackTrace();
		}
		return ObjectHelper.objectToJson(houseMap);
	}
}
