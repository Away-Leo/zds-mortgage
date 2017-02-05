package com.zdsoft.finance.marketing.controller;

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
import com.zdsoft.framework.core.common.page.PageRequest;
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
@RequestMapping("/propertyOwner")
public class PropertyOwnerController extends BaseController{
	
	@Autowired
	private PropertyOwnerService propertyOwnerService;
	
	/**
	 * 保存产权人信息
	 * @param propertyOwnerVo 产权人
	 * @return
	 */
	@RequestMapping("/savePropertyOwner")
	@UriKey(key = "com.zdsoft.finance.marketing.savePropertyOwner")
	@ResponseBody
	public ResponseMsg savePropertyOwner(PropertyOwnerVo propertyOwnerVo) {
		
		PropertyOwner propertyOwner = null;
		ResponseMsg msg = new ResponseMsg();

		// 将Vo转成Po
		propertyOwner = propertyOwnerVo.toPO();
		
		// 执行保存
		try {
			propertyOwnerService.saveOrUpdateEntity(propertyOwner);
			
			msg.setResultStatus(ResponseMsg.SUCCESS);
			msg.setMsg("保存抵押信息成功");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("beforehandApply保存失败", e);
			msg.setResultStatus(ResponseMsg.APP_ERROR);
			msg.setMsg("系统内部错误，请查看日志");
		}
		return msg;
	}
	
	/**
	 * 产权人信息查询列表
	 * @param request 
	 * @param jsoncallback
	 * @param pageable
	 * @return
	 */
	@RequestMapping("/getPropertyOwnerList")
	@UriKey(key = "com.zdsoft.finance.marketing.getPropertyOwnerList")
	@ResponseBody
	public String getPropertyOwnerList(String housePropertyId, String jsoncallback, PageRequest pageable) {
		
		ResponseMsg msg = new ResponseMsg();
		// 分页抵押信息
		List<PropertyOwner> propertyOwnerList=null;
		try {
			if (ObjectHelper.isNotEmpty(housePropertyId)) {
				propertyOwnerList = propertyOwnerService.findByHousePropertyId(housePropertyId);
				msg.setTotal(new Long(propertyOwnerList.size()));
				msg.setRows(propertyOwnerList);
				msg.setMsg("抵押信息列表查询成功！");
			}else{
				msg.setMsg("抵押信息列表查询无数据！");
				
			}
			msg.setResultStatus(ResponseMsg.SUCCESS);
		} catch (Exception e) {
			msg.setMsg("抵押信息列表查询失败");
			e.printStackTrace();
		}
		
		return ObjectHelper.objectToJson(msg, jsoncallback);
	}
	
	/**
	 * 逻辑删除产权人信息
	 * @param jsoncallback
	 * @return
	 * @throws BusinessException 
	 */
	@RequestMapping("/delete")
	@UriKey(key = "com.zdsoft.finance.marketing.deletePropertyOwner")
	@ResponseBody
	public String deletePropertyOwner(String jsoncallback,String id) throws BusinessException {
		ResponseMsg msg = new ResponseMsg();
		try {
			propertyOwnerService.logicDelete(id);
			msg.setMsg("删除成功！");
			msg.setResultStatus(ResponseMsg.SUCCESS);
		} catch (Exception e) {
			msg.setMsg("删除失败！"+e.getMessage());
			msg.setResultStatus(ResponseMsg.SYS_ERROR);
		}
		return ObjectHelper.objectToJson(msg);
	}

}
