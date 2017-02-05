package com.zdsoft.finance.marketing.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zdsoft.finance.common.utils.AppStatus;
import com.zdsoft.finance.common.utils.app.AppServerUtil;
import com.zdsoft.finance.marketing.entity.Search;
import com.zdsoft.finance.marketing.service.SearchService;
import com.zdsoft.finance.marketing.vo.SearchVo;
import com.zdsoft.framework.core.common.dto.ResponseMsg;
import com.zdsoft.framework.core.common.util.ObjectHelper;
import com.zdsoft.framework.core.commweb.component.BaseController;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title:AppSearchController.java
 * @Package:com.zdsoft.finance.marketing.controller
 * @Description:房产产权App接口Controller
 * @author: zhoushichao
 * @date:2017年1月13日 下午10:20:40
 * @version:v1.0
 */
@Controller
@RequestMapping("/server/bizCenter/houseProperty")
public class AppSearchController extends BaseController {

	@Autowired
	private SearchService searchService;
	
	/**
	 * 保存产权信息
	 * @param searchVo 产权
	 * @return
	 */
	@RequestMapping(value="/addPropertyRightInfo",produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String saveSearch(SearchVo searchVo,String token) {
		
		Map<String, Object> searchMap = new HashMap<String, Object>();
		
		Search search = null;

		// 将Vo转成Po
		search = searchVo.toPO();
		if (ObjectHelper.isEmpty(token)) {
			return AppServerUtil.buildError(AppStatus.ArgsError);
		}
		
		// 执行保存
		try {
			search = searchService.saveOrUpdateEntity(search);
			searchMap.put("id", search.getId());
			searchMap.put("status", ResponseMsg.SUCCESS);
			if(ObjectHelper.isNotEmpty(searchVo.toPO().getId())){
				searchMap.put("message", "修改产权信息成功！");
			}else {
				searchMap.put("message", "保存产权信息成功！");
			}
		} catch (Exception e) {
			searchMap.put("status", ResponseMsg.APP_ERROR);
			searchMap.put("message", "保存产权信息失败！");
			e.printStackTrace();
			logger.error("产权信息保存失败", e);
		}
		return ObjectHelper.objectToJson(searchMap);
	}
}
