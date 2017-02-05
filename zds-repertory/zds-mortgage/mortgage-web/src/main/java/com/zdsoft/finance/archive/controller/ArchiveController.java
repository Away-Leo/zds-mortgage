package com.zdsoft.finance.archive.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zdsoft.finance.archive.service.ArchiveService;
import com.zdsoft.finance.common.base.QueryObj;
import com.zdsoft.framework.core.common.dto.ResponseMsg;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.PageRequest;
import com.zdsoft.framework.core.common.util.ObjectHelper;
import com.zdsoft.framework.core.commweb.annotation.UriKey;
import com.zdsoft.framework.core.commweb.component.BaseController;
import com.zdsoft.framework.cra.annotation.Menu;

/**
 * 档案管理
 * @author <a href="mailto:gufeng@zdsoft.cn">gufeng</a>
 * @date 2016-12-21
 */
@Controller
@RequestMapping("archive")
public class ArchiveController extends BaseController {

	@Autowired
	private ArchiveService archiveService;
	@Autowired
	private HttpServletRequest request;
	
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
		//TODO 条件
		@SuppressWarnings("unchecked")
		List<QueryObj> queryObjs = (List<QueryObj>) request.getAttribute("listObj");
		Page<TempProject> page = null;
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
