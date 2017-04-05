package com.zdsoft.finance.casemanage.promotion.otherproperty.controller;

import java.util.Map;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zdsoft.finance.casemanage.promotion.otherproperty.entity.AssetsSecurities;
import com.zdsoft.finance.casemanage.promotion.otherproperty.service.AssetsSecuritiesService;
import com.zdsoft.finance.casemanage.promotion.otherproperty.vo.AssetsSecuritiesVo;
import com.zdsoft.framework.core.common.dto.ResponseMsg;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.PageRequest;
import com.zdsoft.framework.core.common.util.ObjectHelper;
import com.zdsoft.framework.core.commweb.annotation.UriKey;
import com.zdsoft.framework.core.commweb.component.BaseController;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title:AssetsSecuritiesController.java
 * @Package:com.zdsoft.finance.casemanage.promotion.otherproperty.controller
 * @Description:其他资产之有价证券信息控制器
 * @author: xiongpan
 * @date:2017年2月20日 下午2:10:51
 * @version:v1.0
 */
@Controller
@RequestMapping("/assetsSecurities")
public class AssetsSecuritiesController extends BaseController{
	
	@Autowired
	private AssetsSecuritiesService assetsSecuritiesService;

	/**
	 * 
	 * @Title: getAssetsSecuritiesList 
	 * @Description: 根据对应的案件id查询出所有其他资产之有价证券分页信息
	 * @author xiongpan
	 * @param jsoncallback 返回json数据
	 * @param pageable 分页信息
	 * @param caseApplyId 案件id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/getAssetsSecuritiesList")
	@UriKey(key = "com.zdsoft.finance.casemanage.promotion.getAssetsSecuritiesList")
	@ResponseBody
	public String getAssetsSecuritiesList( String jsoncallback, PageRequest pageable, String caseApplyId) throws Exception {
		Page<Map<String, Object>> assetsSecuritiesListPage = assetsSecuritiesService.findPageAssetsSecurities(pageable, caseApplyId);

		ResponseMsg msg = new ResponseMsg();
		msg.setResultStatus(ResponseMsg.SUCCESS);
		msg.setTotal(assetsSecuritiesListPage.getTotalRows());
		msg.setRows(assetsSecuritiesListPage.getRecords());

		return ObjectHelper.objectToJson(msg, jsoncallback);
		
	}
	

	/**
	 * 
	 * @Title: saveAssetsSecurities 
	 * @Description: 保存有价证券
	 * @author xiongpan
	 * @param assetsSecuritiesVo 前端传过来的有价证券对象
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/saveAssetsSecurities")
	@UriKey(key = "com.zdsoft.finance.casemanage.promotion.otherproperty.saveAssetsSecurities")
	@ResponseBody
	public ResponseMsg saveAssetsSecurities(AssetsSecuritiesVo assetsSecuritiesVo) throws Exception {
		ResponseMsg msg = new ResponseMsg();
		if(ObjectHelper.isNotEmpty(assetsSecuritiesVo)){
			AssetsSecurities assetsSecurities = new AssetsSecurities();
			if(ObjectHelper.isNotEmpty(assetsSecuritiesVo.getId())){
				assetsSecurities = assetsSecuritiesService.findOne(assetsSecuritiesVo.getId());
			}
			BeanUtils.copyProperties(assetsSecuritiesVo, assetsSecurities,new String[]{"id","createTime","isDeleted","createBy","createOrgCd"});
			assetsSecuritiesService.saveOrUpdateEntity(assetsSecurities);
			msg.setResultStatus(ResponseMsg.SUCCESS);
			msg.setMsg("保存成功");
		}else{
			msg.setMsg("前端传过来的为空");
		}
		return msg;
	}
	
	
	/**
	 * 
	 * @Title: deleteAssetsSecurities 
	 * @Description: 删除有价证券
	 * @author xiongpan
	 * @param assetsSecuritiesId 要删除的有价证券id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/deleteAssetsSecuritiesr")
	@UriKey(key = "com.zdsoft.finance.casemanage.promotion.otherproperty.deleteAssetsSecurities")
	@ResponseBody
	public ResponseMsg  deleteAssetsSecurities(String assetsSecuritiesId) throws Exception {
		ResponseMsg msg = new ResponseMsg();
		if(ObjectHelper.isNotEmpty(assetsSecuritiesId)){
			assetsSecuritiesService.delete(assetsSecuritiesId);
			msg.setResultStatus(ResponseMsg.SUCCESS);
			msg.setMsg("删除成功");
		}else{
			msg.setMsg("前端传过来的id为空");
		}
		return msg;
	}
	
	

}
