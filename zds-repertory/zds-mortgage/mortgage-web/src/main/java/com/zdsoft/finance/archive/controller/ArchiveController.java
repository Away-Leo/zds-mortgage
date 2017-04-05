package com.zdsoft.finance.archive.controller;

import java.util.ArrayList;
import java.util.List;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zdsoft.framework.core.common.dto.ResponseMsg;
import com.zdsoft.framework.core.common.page.PageRequest;
import com.zdsoft.framework.core.common.util.ObjectHelper;
import com.zdsoft.framework.core.commweb.annotation.UriKey;
import com.zdsoft.framework.core.commweb.component.BaseController;
import com.zdsoft.framework.cra.annotation.Menu;


/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title: ArchiveController.java 
 * @ClassName: ArchiveController 
 * @Description: 档案管理
 * @author gufeng 
 * @date 2017年3月13日 下午5:08:41 
 * @version V1.0
 */
@Controller
@RequestMapping("archive")
public class ArchiveController extends BaseController {

	/**
	 * 档案管理入口
	 * @return 档案管理页面
	 */
	@RequestMapping("/initArchive")
	@UriKey(key = "com.zdsoft.finance.archive.initArchive")
	@Menu(resource = "com.zdsoft.finance.archive.initArchive", label = "档案管理列表", group = "archive", order = 1)
	public ModelAndView initArchive() {
		return new ModelAndView("/archive/archive_list");
	}
	
	/**
	 * 案件查询
	 * @param pageable
	 * @param jsoncallback
	 * @return
	 */
	@RequestMapping("/projectList")
	@UriKey(key = "com.zdsoft.finance.archive.projectList")
	@ResponseBody
	public String projectList(PageRequest pageable, String jsoncallback) {
		ResponseMsg msg = new ResponseMsg();
		//查询数据模拟
		List<TempProject> list = new ArrayList<>();
		TempProject temp = new TempProject();
		list.add(temp);
		list.add(temp);
		list.add(temp);
		list.add(temp);
		list.add(temp);
		list.add(temp);
		list.add(temp);
		list.add(temp);
		msg.setResultStatus(ResponseMsg.SUCCESS);
		msg.setMsg("列表查询成功");
		msg.setRows(list);
		msg.setTotal(16L);
		return ObjectHelper.objectToJson(msg, jsoncallback);
	}
	
	
}
