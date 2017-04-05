package com.zdsoft.finance.app.marketing.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zdsoft.finance.common.utils.AppStatus;
import com.zdsoft.finance.common.utils.app.AppServerUtil;
import com.zdsoft.finance.marketing.entity.HouseProperty;
import com.zdsoft.finance.marketing.entity.Search;
import com.zdsoft.finance.marketing.service.HousePropertyService;
import com.zdsoft.finance.marketing.service.SearchService;
import com.zdsoft.finance.marketing.vo.SearchVo;
import com.zdsoft.framework.core.common.dto.ResponseMsg;
import com.zdsoft.framework.core.common.util.ObjectHelper;
import com.zdsoft.framework.core.commweb.component.BaseController;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: AppSearchController.java 
 * @ClassName: AppSearchController 
 * @Description: 房产产权状态App接口Controller
 * @author zhoushichao 
 * @date 2017年3月2日 下午2:41:41 
 * @version V1.0
 */
@Controller
@RequestMapping("/server/bizCenter/houseProperty")
public class AppSearchController extends BaseController {

	@Autowired
	private SearchService searchService;
	@Autowired
	private HousePropertyService housePropertyService;
	
	
	/**
	 * 
	 * @Title: saveSearch 
	 * @Description: 保存产权状态信息
	 * @author zhoushichao 
	 * @param searchVo 房产产权状态
	 * @param token 当前会话token值
	 * @return
	 */
	@RequestMapping(value="/addPropertyRightInfo",produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String saveSearch(SearchVo searchVo,String token) {
		
		Map<String, Object> searchMap = new HashMap<String, Object>();
		
		if (ObjectHelper.isEmpty(token)||ObjectHelper.isEmpty(searchVo.getHousePropertyId())
				||ObjectHelper.isEmpty(searchVo.getSearchStatus())||ObjectHelper.isEmpty(searchVo.getIsSearched())) {
			return AppServerUtil.buildError(AppStatus.ArgsError);
		}
		
		Search search = null;
		try {
			if (ObjectHelper.isNotEmpty(searchVo.getId())) {
				search = searchService.findOne(searchVo.getId());
				search.setUpdateTime(new Date());
			}else {
				search = new Search();
				search.setCreateTime(new Date());
			}
			BeanUtils.copyProperties(searchVo, search, new String[]{"id","createTime","updateTime"});
			HouseProperty houseProperty = housePropertyService.findOne(searchVo.getHousePropertyId());
			houseProperty.setSearch(search);
			// 执行保存
			houseProperty = housePropertyService.saveOrUpdateHouseProperty(houseProperty);
			searchMap.put("id", houseProperty.getSearch().getId());
			searchMap.put("status", ResponseMsg.SUCCESS);
			if(ObjectHelper.isNotEmpty(searchVo.getId())){
				searchMap.put("message", "修改产权状态信息成功！");
			}else {
				searchMap.put("message", "保存产权状态信息成功！");
			}
		} catch (Exception e) {
			searchMap.put("status", ResponseMsg.APP_ERROR);
			searchMap.put("message", "保存产权状态信息失败！");
			e.printStackTrace();
			logger.error("产权状态信息保存失败", e);
		}
		return ObjectHelper.objectToJson(searchMap);
	}
}
