package com.zdsoft.finance.casemanage.promotion.otherproperty.controller;

import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.zdsoft.finance.casemanage.promotion.otherproperty.entity.CustomerHouse;
import com.zdsoft.finance.casemanage.promotion.otherproperty.service.CustomerHouseService;
import com.zdsoft.finance.casemanage.promotion.otherproperty.vo.CustomerHouseVo;
import com.zdsoft.framework.core.common.dto.ResponseMsg;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.PageRequest;
import com.zdsoft.framework.core.common.util.ObjectHelper;
import com.zdsoft.framework.core.commweb.annotation.UriKey;
import com.zdsoft.framework.core.commweb.component.BaseController;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title:CustomerHouseController.java
 * @Package:com.zdsoft.finance.casemanage.promotion.otherproperty.controller
 * @Description:其他资产中房产信息控制器
 * @author: xiongpan
 * @date:2017年2月14日 下午4:31:53
 * @version:v1.0
 */
@Controller
@RequestMapping("/customerHouse")
public class CustomerHouseController extends BaseController{
	
	@Autowired
	private CustomerHouseService customerHouseService;

	/**
	 * 
	 * @Title: getCustomerHouseList 
	 * @Description: 根据案件id查询其他资产中所有房产情况
	 * @author xiongpan
	 * @param jsoncallback 返回json数据
	 * @param pageable 分页信息
	 * @param caseApplyId 案件id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/getCustomerHouseList")
	@UriKey(key = "com.zdsoft.finance.casemanage.promotion.getCustomerHouseList")
	@ResponseBody
	public String getCustomerHouseList( String jsoncallback, PageRequest pageable, String caseApplyId) throws Exception {
		Page<Map<String, Object>> customerHousePage = customerHouseService.findPageCustomerHouse(pageable, caseApplyId);

		ResponseMsg msg = new ResponseMsg();
		msg.setResultStatus(ResponseMsg.SUCCESS);
		msg.setTotal(customerHousePage.getTotalRows());
		msg.setRows(customerHousePage.getRecords());

		return ObjectHelper.objectToJson(msg, jsoncallback);
		
	}
	

	/**
	 * 
	 * @Title: saveCustomerHouse 
	 * @Description: 保存房产
	 * @author xiongpan
	 * @param customerHouseVo 前端传过来的房产对象Vo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/saveCustomerHouse")
	@UriKey(key = "com.zdsoft.finance.casemanage.promotion.otherproperty.saveCustomerHouse")
	@ResponseBody
	public ResponseMsg saveCustomerHouse(CustomerHouseVo customerHouseVo) throws Exception {
		ResponseMsg msg = new ResponseMsg();
		if(ObjectHelper.isNotEmpty(customerHouseVo)){
			CustomerHouse customerHouse = new CustomerHouse();
			if(ObjectHelper.isNotEmpty(customerHouseVo.getId())){
				customerHouse = customerHouseService.findOne(customerHouseVo.getId());
			}
			BeanUtils.copyProperties(customerHouseVo, customerHouse,new String[]{"id","createTime","isDeleted","createBy","createOrgCd"});
			customerHouseService.saveOrUpdateEntity(customerHouse);
			msg.setResultStatus(ResponseMsg.SUCCESS);
			msg.setMsg("保存成功");
		}else{
			msg.setMsg("前端传过来的为空");
		}
		return msg;
	}
	
	
	/**
	 * 
	 * @Title: deleteCustomerHouse 
	 * @Description: 删除房产
	 * @author xiongpan
	 * @param customerHouseId 要删除的房产id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/deleteCustomerHouse")
	@UriKey(key = "com.zdsoft.finance.casemanage.promotion.otherproperty.deleteCustomerHouse")
	@ResponseBody
	public ResponseMsg  deleteCustomerHouse(String customerHouseId) throws Exception {
		ResponseMsg msg = new ResponseMsg();
		if(ObjectHelper.isNotEmpty(customerHouseId)){
			customerHouseService.delete(customerHouseId);
			msg.setResultStatus(ResponseMsg.SUCCESS);
			msg.setMsg("删除成功");
		}else{
			msg.setMsg("前端传过来的id为空");
		}
		return msg;
	}
	
}
