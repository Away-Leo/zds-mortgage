package com.zdsoft.finance.app.marketing.controller;

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
 * @Title: AppPledgeInfoController.java 
 * @ClassName: AppPledgeInfoController 
 * @Description: 抵押信息App接口Controller
 * @author zhoushichao 
 * @date 2017年3月2日 下午1:57:38 
 * @version V1.0 
 */ 
@Controller
@RequestMapping(" /server/bizCenter/houseProperty")
public class AppPledgeInfoController extends BaseController{

	@Autowired
	private PledgeInfoService pledgeInfoService;
	
	/**
	 * 
	 * @Title: savePledgeInfo 
	 * @Description: 保存抵押信息
	 * @author zhoushichao 
	 * @param pledgeInfoVo  抵押信息
	 * @param token  当前会话token值
	 * @return
	 */
	@RequestMapping(value="/addMortgageInfo",produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String savePledgeInfo(PledgeInfoVo pledgeInfoVo,String token) {
		
		Map<String, Object> pledgeInfoMap = new HashMap<String, Object>();

		if (ObjectHelper.isEmpty(token)||ObjectHelper.isEmpty(pledgeInfoVo.getHousePropertyId())
				||ObjectHelper.isEmpty(pledgeInfoVo.getPledgeType())||ObjectHelper.isEmpty(pledgeInfoVo.getType())
				||ObjectHelper.isEmpty(pledgeInfoVo.getLoanBank())||ObjectHelper.isEmpty(pledgeInfoVo.getDeadline())
				||ObjectHelper.isEmpty(pledgeInfoVo.getDeadlineUnit())||ObjectHelper.isEmpty(pledgeInfoVo.getLoanBalance())
				||ObjectHelper.isEmpty(pledgeInfoVo.getPledgeAmout())) {
			return AppServerUtil.buildError(AppStatus.ArgsError);
		}
		PledgeInfo pledgeInfo = null;
		
		try {
			if (ObjectHelper.isNotEmpty(pledgeInfoVo.getId())) {
				pledgeInfo = pledgeInfoService.findOne(pledgeInfoVo.getId());
			}else {
				pledgeInfo = new PledgeInfo();
			}
			pledgeInfo.setHousePropertyId(pledgeInfoVo.getHousePropertyId());
			pledgeInfo.setPledgeType(pledgeInfoVo.getPledgeType());
			pledgeInfo.setType(pledgeInfoVo.getType());
			pledgeInfo.setLoanBank(pledgeInfoVo.getLoanBank());
			pledgeInfo.setDeadline(pledgeInfoVo.getDeadline());
			pledgeInfo.setDeadlineUnit(pledgeInfoVo.getDeadlineUnit());
			pledgeInfo.setLoanBalance(pledgeInfoVo.getLoanBalance());
			pledgeInfo.setPledgeAmout(pledgeInfoVo.getPledgeAmout());
			if (ObjectHelper.isNotEmpty(pledgeInfoVo.getPercentage())) {
				pledgeInfo.setPercentage(pledgeInfoVo.getPercentage());
			}
			// 执行保存
			pledgeInfo = pledgeInfoService.saveOrUpdateEntity(pledgeInfo);
			
			pledgeInfoMap.put("id", pledgeInfo.getId());
			pledgeInfoMap.put("status", ResponseMsg.SUCCESS);
			pledgeInfoMap.put("message", "保存抵押信息成功！");
			if(ObjectHelper.isNotEmpty(pledgeInfoVo.getId())){
				pledgeInfoMap.put("message", "修改抵押信息成功！");
			}else {
				pledgeInfoMap.put("message", "保存抵押信息成功！");
			}
		} catch (Exception e) {
			pledgeInfoMap.put("status", ResponseMsg.SYS_ERROR);
			pledgeInfoMap.put("message", "保存抵押信息失败！");
			e.printStackTrace();
			logger.error("保存抵押信息失败！", e);
		}
		return ObjectHelper.objectToJson(pledgeInfoMap);
	}
}
