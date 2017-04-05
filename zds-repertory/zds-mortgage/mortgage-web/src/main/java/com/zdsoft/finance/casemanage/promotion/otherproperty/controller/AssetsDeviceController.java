package com.zdsoft.finance.casemanage.promotion.otherproperty.controller;

import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zdsoft.finance.casemanage.promotion.otherproperty.entity.AssetsDevice;
import com.zdsoft.finance.casemanage.promotion.otherproperty.service.AssetsDeviceService;
import com.zdsoft.framework.core.common.dto.ResponseMsg;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.PageRequest;
import com.zdsoft.framework.core.common.util.ObjectHelper;
import com.zdsoft.framework.core.commweb.annotation.UriKey;
import com.zdsoft.framework.core.commweb.component.BaseController;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title:AssetsDeviceController.java
 * @Package:com.zdsoft.finance.casemanage.promotion.otherproperty.controller
 * @Description:其他资产之设备信息控制器
 * @author: xiongpan
 * @date:2017年2月22日 下午2:43:56
 * @version:v1.0
 */
@Controller
@RequestMapping("/assetsDevice")
public class AssetsDeviceController extends BaseController{
	
	@Autowired
	private AssetsDeviceService assetsDeviceService;
	
	/**
	 * 
	 * @Title: getAssetsDeviceList 
	 * @Description: 根据案件id查询其他资产之设备情况分页信息
	 * @author xiongpan
	 * @param jsoncallback 返回json数据
	 * @param pageable 分页信息
	 * @param caseApplyId 案件id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/getAssetsDeviceList")
	@UriKey(key = "com.zdsoft.finance.casemanage.promotion.getAssetsDeviceList")
	@ResponseBody
	public String getAssetsDeviceList( String jsoncallback, PageRequest pageable, String caseApplyId) throws Exception {
		Page<Map<String, Object>> assetsDevicePage = assetsDeviceService.findPageAssetsDevice(pageable, caseApplyId);

		ResponseMsg msg = new ResponseMsg();
		msg.setResultStatus(ResponseMsg.SUCCESS);
		msg.setTotal(assetsDevicePage.getTotalRows());
		msg.setRows(assetsDevicePage.getRecords());

		return ObjectHelper.objectToJson(msg, jsoncallback);
		
	}
	
	
	/**
	 * 
	 * @Title: saveAssetsDevice 
	 * @Description: 保存设备
	 * @author xiongpan
	 * @param assetsDevice 前端出传过来的设备对象
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/saveAssetsDevice")
	@UriKey(key = "com.zdsoft.finance.casemanage.promotion.otherproperty.saveAssetsDevice")
	@ResponseBody
	public ResponseMsg saveAssetsDevice(AssetsDevice assetsDevice) throws Exception {
		ResponseMsg msg = new ResponseMsg();
		if(ObjectHelper.isNotEmpty(assetsDevice)){
			if(ObjectHelper.isNotEmpty(assetsDevice.getId())){
				AssetsDevice entity = assetsDeviceService.findOne(assetsDevice.getId());
				BeanUtils.copyProperties(assetsDevice, entity,new String[]{"id","createTime","isDeleted","createBy","createOrgCd"});
				assetsDeviceService.updateEntity(assetsDevice);
			}else{
				assetsDeviceService.saveEntity(assetsDevice);
			}
			msg.setResultStatus(ResponseMsg.SUCCESS);
			msg.setMsg("保存成功");
		}else{
			msg.setMsg("前端传过来的为空");
		}
		return msg;
	}
	
	
	/**
	 * 
	 * @Title: deleteAssetsDevice 
	 * @Description: 删除设备
	 * @author xiongpan
	 * @param assetsDeviceId 前端传过来要删除的设备id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/deleteAssetsDevice")
	@UriKey(key = "com.zdsoft.finance.casemanage.promotion.otherproperty.deleteAssetsDevice")
	@ResponseBody
	public ResponseMsg  deleteAssetsDevice(String assetsDeviceId) throws Exception {
		ResponseMsg msg = new ResponseMsg();
		if(ObjectHelper.isNotEmpty(assetsDeviceId)){
			assetsDeviceService.delete(assetsDeviceId);
			msg.setResultStatus(ResponseMsg.SUCCESS);
			msg.setMsg("删除成功");
		}else{
			msg.setMsg("前端传过来的id为空");
		}
		return msg;
	}
	
	
	

}
