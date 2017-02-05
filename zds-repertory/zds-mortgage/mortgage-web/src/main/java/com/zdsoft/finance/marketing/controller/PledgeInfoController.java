package com.zdsoft.finance.marketing.controller;

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
import com.zdsoft.framework.core.common.page.PageRequest;
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
@RequestMapping("/pledgeInfo")
public class PledgeInfoController extends BaseController{
	
	@Autowired
	private PledgeInfoService pledgeInfoService;
	
	/**
	 * 保存抵押信息
	 * @param pledgeInfoVo 抵押信息
	 * @return
	 */
	@RequestMapping("/savePledgeInfo")
	@UriKey(key = "com.zdsoft.finance.marketing.savePledgeInfo")
	@ResponseBody
	public ResponseMsg savePledgeInfo(PledgeInfoVo pledgeInfoVo) {
		
		PledgeInfo pledgeInfo = null;
		ResponseMsg msg = new ResponseMsg();

		// 将Vo转成Po
		pledgeInfo = pledgeInfoVo.toPO();
		
		// 执行保存
		try {
			pledgeInfoService.saveOrUpdateEntity(pledgeInfo);
			
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
	 * 抵押信息查询列表
	 * @param request 
	 * @param jsoncallback
	 * @param pageable
	 * @return
	 */
	@RequestMapping("/getPledgeInfoList")
	@UriKey(key = "com.zdsoft.finance.marketing.getPledgeInfoList")
	@ResponseBody
	public String getPledgeInfoList(String  housePropertyId, String jsoncallback, PageRequest pageable) {

		ResponseMsg msg = new ResponseMsg();
		// 分页抵押信息
		List<PledgeInfo> pledgeInfoList=null;
		try {
			if (ObjectHelper.isNotEmpty(housePropertyId)) {
				pledgeInfoList = pledgeInfoService.findByHouseId(housePropertyId);
			msg.setTotal(new Long(pledgeInfoList.size()));
			msg.setRows(pledgeInfoList);
			msg.setMsg("抵押信息列表查询成功！");
			}else{
				msg.setMsg("抵押信息列表查询无数据！");
			}
			msg.setResultStatus(ResponseMsg.SUCCESS);
		} catch (BusinessException e) {
			msg.setMsg("抵押信息列表查询失败");
			e.printStackTrace();
		}
		

		return ObjectHelper.objectToJson(msg, jsoncallback);
	}
	
	/**
	 * 逻辑删除抵押信息
	 * @param jsoncallback
	 * @return
	 * @throws BusinessException 
	 */
	@RequestMapping("/delete")
	@UriKey(key = "com.zdsoft.finance.marketing.deletePledgeInfo")
	@ResponseBody
	public String deletePledgeInfo(String jsoncallback,String id) throws BusinessException {
		ResponseMsg msg = new ResponseMsg();
		try {
			pledgeInfoService.logicDelete(id);
			msg.setMsg("删除成功！");
			msg.setResultStatus(ResponseMsg.SUCCESS);
		} catch (Exception e) {
			msg.setMsg("删除失败！"+e.getMessage());
			msg.setResultStatus(ResponseMsg.SYS_ERROR);
		}
		return ObjectHelper.objectToJson(msg);
	}

}
