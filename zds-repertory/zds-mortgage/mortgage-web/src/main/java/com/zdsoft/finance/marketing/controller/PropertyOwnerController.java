package com.zdsoft.finance.marketing.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.finance.marketing.entity.PropertyOwner;
import com.zdsoft.finance.marketing.service.PropertyOwnerService;
import com.zdsoft.finance.marketing.vo.PropertyOwnerVo;
import com.zdsoft.framework.core.common.dto.ResponseMsg;
import com.zdsoft.framework.core.common.util.ObjectHelper;
import com.zdsoft.framework.core.commweb.annotation.UriKey;
import com.zdsoft.framework.core.commweb.component.BaseController;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title:PropertyOwnerController.java
 * @Package:com.zdsoft.finance.marketing.controller
 * @Description:产权人Controller
 * @author: zhoushichao
 * @date:2017年1月13日 下午10:14:32
 * @version:v1.0
 */
@Controller
@RequestMapping("marketing/propertyOwner")
public class PropertyOwnerController extends BaseController{
	
	@Autowired
	private PropertyOwnerService propertyOwnerService;
	
	/**
	 * 
	 * @Title: getPropertyOwnerList 
	 * @Description: 根据房产Id查询产权人信息列表
	 * @author zhoushichao 
	 * @param housePropertyId 房产Id
	 * @param jsoncallback
	 * @return
	 */
	@RequestMapping("/getPropertyOwnerList")
	@UriKey(key = "com.zdsoft.finance.marketing.propertyOwner.getPropertyOwnerList")
	@ResponseBody
	public String getPropertyOwnerList(String housePropertyId, String jsoncallback) {
		
		ResponseMsg msg = new ResponseMsg();
		// 产权人信息
		List<PropertyOwner> propertyOwnerList=null;
		List<PropertyOwnerVo> voList=new ArrayList<PropertyOwnerVo>();
		try {
			if (ObjectHelper.isNotEmpty(housePropertyId)) {
				propertyOwnerList = propertyOwnerService.findByHousePropertyId(housePropertyId);
				for(PropertyOwner owner:propertyOwnerList){
					PropertyOwnerVo vo = new PropertyOwnerVo(owner);
					voList.add(vo);
				}
				
				msg.setTotal(new Long(voList.size()));
				msg.setRows(voList);
				msg.setMsg("抵押信息列表查询成功！");
			}else{
				msg.setMsg("抵押信息列表查询无数据！");
				
			}
			msg.setResultStatus(ResponseMsg.SUCCESS);
		} catch (Exception e) {
			msg.setMsg("抵押信息列表查询失败");
			e.printStackTrace();
			logger.error("抵押信息列表查询失败",e);
		}
		
		return ObjectHelper.objectToJson(msg, jsoncallback);
	}
	
	/**
	 * 
	 * @Title: deletePropertyOwner 
	 * @Description: 根据产权人id逻辑删除产权人信息
	 * @author zhoushichao 
	 * @param id 产权人id
	 * @return
	 */
	@RequestMapping("/deletePropertyOwner")
	@UriKey(key = "com.zdsoft.finance.marketing.propertyOwner.deletePropertyOwner")
	@ResponseBody
	public String deletePropertyOwner(String id){
		ResponseMsg msg = new ResponseMsg();
		try {
			propertyOwnerService.logicDelete(id);
			msg.setMsg("删除产权人信息成功！");
			msg.setResultStatus(ResponseMsg.SUCCESS);
		} catch (BusinessException e) {
			msg.setMsg("删除产权人信息失败！"+e.getMessage());
			msg.setResultStatus(ResponseMsg.SYS_ERROR);
			logger.error("删除产权人信息失败",e);
			
		}
		return ObjectHelper.objectToJson(msg);
	}
}
