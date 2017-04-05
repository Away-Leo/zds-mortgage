package com.zdsoft.finance.casemanage.promotion.otherproperty.controller;

import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zdsoft.finance.casemanage.promotion.otherproperty.entity.AssetsCar;
import com.zdsoft.finance.casemanage.promotion.otherproperty.service.AssetsCarService;
import com.zdsoft.finance.casemanage.promotion.otherproperty.vo.AssetsCarVo;
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
 * @date:2017年2月17日 下午4:31:53
 * @version:v1.0
 */
@Controller
@RequestMapping("/assetsCar")
public class AssetsCarController extends BaseController{
	
	@Autowired
	private AssetsCarService assetsCarService;
	
	/**
	 * 
	 * @Title: getAssetsCarList 
	 * @Description: 根据案件id查询出所有其他资产中的汽车情况
	 * @author xiongpan
	 * @param jsoncallback 返回json数据
	 * @param pageable 分页信息
	 * @param caseApplyId 案件id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/getAssetsCarList")
	@UriKey(key = "com.zdsoft.finance.casemanage.promotion.getAssetsCarList")
	@ResponseBody
	public String getAssetsCarList( String jsoncallback, PageRequest pageable, String caseApplyId) throws Exception {
		Page<Map<String, Object>> assetsCarListPage = assetsCarService.findPageAssetsCar(pageable, caseApplyId);

		ResponseMsg msg = new ResponseMsg();
		msg.setResultStatus(ResponseMsg.SUCCESS);
		msg.setTotal(assetsCarListPage.getTotalRows());
		msg.setRows(assetsCarListPage.getRecords());

		return ObjectHelper.objectToJson(msg, jsoncallback);
		
	}
	
	/**
	 * 
	 * @Title: saveAssetsCar 
	 * @Description: 保存汽车
	 * @author xiongpan
	 * @param assetsCarVo 汽车情况Vo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/saveAssetsCar")
	@UriKey(key = "com.zdsoft.finance.casemanage.promotion.otherproperty.saveAssetsCar")
	@ResponseBody
	public ResponseMsg saveAssetsCar(AssetsCarVo assetsCarVo) throws Exception {
		ResponseMsg msg = new ResponseMsg();
		if(ObjectHelper.isNotEmpty(assetsCarVo)){
			AssetsCar assetsCar = new AssetsCar();
			if(ObjectHelper.isNotEmpty(assetsCarVo.getId())){
				assetsCar = assetsCarService.findOne(assetsCarVo.getId());
			}
			BeanUtils.copyProperties(assetsCarVo, assetsCar,new String[]{"id","createTime","isDeleted","createBy","createOrgCd"});
			assetsCarService.saveOrUpdateEntity(assetsCar);
			msg.setResultStatus(ResponseMsg.SUCCESS);
			msg.setMsg("保存成功");
		}else{
			msg.setMsg("前端传过来的为空");
		}
		return msg;
	}
	
	
	/**
	 * 
	 * @Title: deleteAssetsCar 
	 * @Description: 删除汽车
	 * @author xiongpan
	 * @param assetsCarId 汽车id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/deleteCustomerHouse")
	@UriKey(key = "com.zdsoft.finance.casemanage.promotion.otherproperty.deleteAssetsCar")
	@ResponseBody
	public ResponseMsg  deleteAssetsCar(String assetsCarId) throws Exception {
		ResponseMsg msg = new ResponseMsg();
		if(ObjectHelper.isNotEmpty(assetsCarId)){
			assetsCarService.delete(assetsCarId);
			msg.setResultStatus(ResponseMsg.SUCCESS);
			msg.setMsg("删除成功");
		}else{
			msg.setMsg("前端传过来的id为空");
		}
		return msg;
	}
	
	
	

}
