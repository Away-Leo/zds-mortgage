package com.zdsoft.finance.marketing.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zdsoft.finance.common.utils.AppStatus;
import com.zdsoft.finance.common.utils.app.AppServerUtil;
import com.zdsoft.finance.marketing.entity.PledgeInfo;
import com.zdsoft.finance.marketing.service.PledgeInfoService;
import com.zdsoft.finance.marketing.vo.PledgeInfoVo;
import com.zdsoft.framework.core.common.dto.ResponseMsg;
import com.zdsoft.framework.core.common.util.ObjectHelper;
import com.zdsoft.framework.core.commweb.component.BaseController;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title:AppPledgeInfoController.java
 * @Package:com.zdsoft.finance.marketing.controller
 * @Description:抵押信息App接口Controller
 * @author: zhoushichao
 * @date:2017年1月13日 下午9:05:49
 * @version:v1.0
 */
@Controller
@RequestMapping(" /server/bizCenter/houseProperty")
public class AppPledgeInfoController extends BaseController{

	@Autowired
	private PledgeInfoService pledgeInfoService;
	
	/**
	 * 保存抵押信息
	 * @param pledgeInfoVo 抵押信息
	 * @return
	 */
	@RequestMapping(value="/addMortgageInfo",produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String savePledgeInfo(PledgeInfoVo pledgeInfoVo,String token) {
		
		Map<String, Object> pledgeInfoMap = new HashMap<String, Object>();

		PledgeInfo pledgeInfo = null;
		// 将Vo转成Po
		pledgeInfo = pledgeInfoVo.toPO();
		if (ObjectHelper.isEmpty(token)) {
			return AppServerUtil.buildError(AppStatus.ArgsError);
		}
		
		// 执行保存
		try {
			pledgeInfo = pledgeInfoService.saveOrUpdateEntity(pledgeInfo);
			
			pledgeInfoMap.put("id", pledgeInfo.getId());
			pledgeInfoMap.put("status", ResponseMsg.SUCCESS);
			pledgeInfoMap.put("message", "保存抵押信息成功！");
			if(ObjectHelper.isNotEmpty(pledgeInfoVo.toPO().getId())){
				pledgeInfoMap.put("message", "修改抵押信息成功！");
			}else {
				pledgeInfoMap.put("message", "保存抵押信息成功！");
			}
		} catch (Exception e) {
			pledgeInfoMap.put("status", ResponseMsg.SYS_ERROR);
			pledgeInfoMap.put("message", "保存抵押信息失败！");
			e.printStackTrace();
		}
		return ObjectHelper.objectToJson(pledgeInfoMap);
	}
}
