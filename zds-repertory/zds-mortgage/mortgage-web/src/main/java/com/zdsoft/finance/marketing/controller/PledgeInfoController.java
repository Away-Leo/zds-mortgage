package com.zdsoft.finance.marketing.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.finance.marketing.entity.PledgeInfo;
import com.zdsoft.finance.marketing.service.PledgeInfoService;
import com.zdsoft.finance.marketing.vo.PledgeInfoVo;
import com.zdsoft.framework.core.common.dto.ResponseMsg;
import com.zdsoft.framework.core.common.util.ObjectHelper;
import com.zdsoft.framework.core.commweb.annotation.UriKey;
import com.zdsoft.framework.core.commweb.component.BaseController;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title:PledgeInfoController.java
 * @Package:com.zdsoft.finance.marketing.controller
 * @Description:抵押信息Controller
 * @author: zhoushichao
 * @date:2017年1月13日 下午10:14:19
 * @version:v1.0
 */
@Controller
@RequestMapping("marketing/pledgeInfo")
public class PledgeInfoController extends BaseController{
	
	@Autowired
	private PledgeInfoService pledgeInfoService;
	
	/**
	 * 
	 * @Title: getPledgeInfoList 
	 * @Description: 根据房产Id查询抵押信息列表
	 * @author zhoushichao 
	 * @param housePropertyId 房产Id
	 * @param jsoncallback
	 * @return
	 */
	@RequestMapping("/getPledgeInfoList")
	@UriKey(key = "com.zdsoft.finance.marketing.pledgeInfo.getPledgeInfoList")
	@ResponseBody
	public String getPledgeInfoList(String  housePropertyId,String jsoncallback) {

		ResponseMsg msg = new ResponseMsg();
		// 查询抵押信息
		List<PledgeInfo> pledgeInfoList=null;
		List<PledgeInfoVo> voList = new ArrayList<PledgeInfoVo>();
		try {
			if (ObjectHelper.isNotEmpty(housePropertyId)) {
				pledgeInfoList = pledgeInfoService.findByHouseId(housePropertyId);
				for(PledgeInfo info : pledgeInfoList){
					PledgeInfoVo vo = new PledgeInfoVo(info);
					voList.add(vo);
				}
				
			msg.setTotal(new Long(voList.size()));
			msg.setRows(voList);
			msg.setMsg("抵押信息列表查询成功！");
			}else{
				msg.setMsg("抵押信息列表查询无数据！");
			}
			msg.setResultStatus(ResponseMsg.SUCCESS);
		} catch (BusinessException e) {
			msg.setMsg("抵押信息列表查询失败");
			e.printStackTrace();
			logger.error("抵押信息列表查询失败",e);
		}
		return ObjectHelper.objectToJson(msg, jsoncallback);
	}
	
	/**
	 * 、
	 * @Title: deletePledgeInfo 
	 * @Description: 根据id逻辑删除抵押信息
	 * @author zhoushichao 
	 * @param id 抵押id
	 * @return
	 */
	@RequestMapping("/deletePledgeInfo")
	@UriKey(key = "com.zdsoft.finance.marketing.pledgeInfo.deletePledgeInfo")
	@ResponseBody
	public String deletePledgeInfo(String id){
		ResponseMsg msg = new ResponseMsg();
		try {
			pledgeInfoService.logicDelete(id);
			msg.setMsg("删除抵押信息成功！");
			msg.setResultStatus(ResponseMsg.SUCCESS);
		} catch (BusinessException e) {
			msg.setMsg("删除抵押信息失败！"+e.getMessage());
			msg.setResultStatus(ResponseMsg.SYS_ERROR);
			logger.error("删除抵押信息失败！",e);
		}
		return ObjectHelper.objectToJson(msg);
	}

}
