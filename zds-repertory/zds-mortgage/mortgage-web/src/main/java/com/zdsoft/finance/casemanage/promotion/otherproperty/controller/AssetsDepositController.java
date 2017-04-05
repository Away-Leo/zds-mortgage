package com.zdsoft.finance.casemanage.promotion.otherproperty.controller;

import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zdsoft.finance.casemanage.promotion.otherproperty.entity.AssetsDeposit;
import com.zdsoft.finance.casemanage.promotion.otherproperty.service.AssetsDepositService;
import com.zdsoft.framework.core.common.dto.ResponseMsg;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.PageRequest;
import com.zdsoft.framework.core.common.util.ObjectHelper;
import com.zdsoft.framework.core.commweb.annotation.UriKey;
import com.zdsoft.framework.core.commweb.component.BaseController;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title:AssetsDepositController.java
 * @Package:com.zdsoft.finance.casemanage.promotion.otherproperty.controller
 * @Description:其他资产之存款情况控制器
 * @author: xiongpan
 * @date:2017年2月21日 下午2:46:59
 * @version:v1.0
 */
@Controller
@RequestMapping("/assetsDeposit")
public class AssetsDepositController extends BaseController{

	@Autowired
	private AssetsDepositService assetsDepositService;
	
	/**
	 * 
	 * @Title: getAssetsDepositList 
	 * @Description: 根据案件id查询出其他资产之存款的所有分页信息
	 * @author xiongpan
	 * @param jsoncallback 返回json数据
	 * @param pageable 分页信息
	 * @param caseApplyId 案件id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/getAssetsDepositList")
	@UriKey(key = "com.zdsoft.finance.casemanage.promotion.getAssetsDepositList")
	@ResponseBody
	public String getAssetsDepositList( String jsoncallback, PageRequest pageable, String caseApplyId) throws Exception {
		Page<Map<String, Object>> assetsDepositPage = assetsDepositService.findPageAssetsDeposit(pageable, caseApplyId);

		ResponseMsg msg = new ResponseMsg();
		msg.setResultStatus(ResponseMsg.SUCCESS);
		msg.setTotal(assetsDepositPage.getTotalRows());
		msg.setRows(assetsDepositPage.getRecords());

		return ObjectHelper.objectToJson(msg, jsoncallback);
		
	}
	
	
	/**
	 * 
	 * @Title: saveAssetsDeposit 
	 * @Description: 保存存款
	 * @author xiongpan
	 * @param assetsDeposit 前端传过来的存款对象
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/saveAssetsDeposit")
	@UriKey(key = "com.zdsoft.finance.casemanage.promotion.otherproperty.saveAssetsDeposit")
	@ResponseBody
	public ResponseMsg saveAssetsDeposit(AssetsDeposit assetsDeposit) throws Exception {
		ResponseMsg msg = new ResponseMsg();
		if(ObjectHelper.isNotEmpty(assetsDeposit)){
			if(ObjectHelper.isNotEmpty(assetsDeposit.getId())){
				AssetsDeposit entity = assetsDepositService.findOne(assetsDeposit.getId());
				BeanUtils.copyProperties(assetsDeposit, entity,new String[]{"id","createTime","isDeleted","createBy","createOrgCd"});
				assetsDepositService.updateEntity(entity);
			}else{
				assetsDepositService.saveEntity(assetsDeposit);
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
	 * @Title: deleteAssetsDeposit 
	 * @Description: 删除存款
	 * @author xiongpan
	 * @param assetsDepositId 存款id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/deleteAssetsDeposit")
	@UriKey(key = "com.zdsoft.finance.casemanage.promotion.otherproperty.deleteAssetsDeposit")
	@ResponseBody
	public ResponseMsg  deleteAssetsDeposit(String assetsDepositId) throws Exception {
		ResponseMsg msg = new ResponseMsg();
		if(ObjectHelper.isNotEmpty(assetsDepositId)){
			assetsDepositService.delete(assetsDepositId);
			msg.setResultStatus(ResponseMsg.SUCCESS);
			msg.setMsg("删除成功");
		}else{
			msg.setMsg("前端传过来的id为空");
		}
		return msg;
	}
	
	
	
}
