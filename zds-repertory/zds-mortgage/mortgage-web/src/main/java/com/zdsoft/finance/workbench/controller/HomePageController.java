package com.zdsoft.finance.workbench.controller;


import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.zdsoft.bpm.dto.TaskInstanceQueryDto;
import com.zdsoft.bpm.service.client.BPM;
import com.zdsoft.bpm.util.client.ProcessStoreHelper;
import com.zdsoft.finance.workbench.entity.RemindPool;
import com.zdsoft.finance.workbench.service.RemindPoolService;
import com.zdsoft.finance.workbench.vo.RemindPoolVo;
import com.zdsoft.framework.core.common.configure.AppParameter;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.PageRequest;
import com.zdsoft.framework.core.common.util.ObjectHelper;
import com.zdsoft.framework.core.commweb.annotation.UriKey;
import com.zdsoft.framework.core.commweb.component.BaseController;
import com.zdsoft.framework.cra.annotation.Menu;

/**
 * 首页
 * @author <a href="mailto:gufeng@zdsoft.cn">gufeng</a>
 * @date  2017-01-06
 */
@Controller
@RequestMapping("homePage")
public class HomePageController extends BaseController {

	@Autowired
	private RemindPoolService remindPoolService;
	
	@Autowired
	private BPM BPM;
	
	/**
	 * 档案管理入口
	 * @return 档案管理页面
	 */
	@RequestMapping("/init")
	@UriKey(key = "com.zdsoft.finance.homePage.init")
	@Menu(resource = "com.zdsoft.finance.homePage.init", label = "首页", group = "workbench", order = 1)
	public ModelAndView init(HttpServletRequest request) {
		//资源路径
		ModelMap map = new ModelMap();
		String localWebServer = AppParameter.getLocalWebServer();
		localWebServer += request.getContextPath();
		map.put("localWebServer", localWebServer);
		String application = ProcessStoreHelper.getWorkflowAppCd();
		map.put("application", application);
		//代办
		PageRequest pageable = new PageRequest(1, 6);
		try {
			Page<TaskInstanceQueryDto> page = BPM.findMyPendingTasks(pageable);
			map.put("pendingTasks", page);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("BPM查询出错",e.getMessage());
		}
		
		//借款借据
		List<RemindPool> borrows = remindPoolService.findByCategory("borrow");
		List<RemindPoolVo> borrowVos = new ArrayList<>();
		if(ObjectHelper.isNotEmpty(borrows) && borrows.size() > 0){
			for (RemindPool po : borrows) {
				RemindPoolVo vo = new RemindPoolVo(po);
				borrowVos.add(vo);
			}
		}
		map.put("borrowVos", borrowVos);
		//补充合同
		List<RemindPool> contracts = remindPoolService.findByCategory("contract");
		List<RemindPoolVo> contractVos = new ArrayList<>();
		if(ObjectHelper.isNotEmpty(contracts) && contracts.size() > 0){
			for (RemindPool po : contracts) {
				RemindPoolVo vo = new RemindPoolVo(po);
				contractVos.add(vo);
			}
		}
		map.put("contractVos", contractVos);
		//他项权证
		List<RemindPool> encumbrances = remindPoolService.findByCategory("encumbrance");
		List<RemindPoolVo> encumbranceVos = new ArrayList<>();
		if(ObjectHelper.isNotEmpty(encumbrances) && encumbrances.size() > 0){
			for (RemindPool po : encumbrances) {
				RemindPoolVo vo = new RemindPoolVo(po);
				encumbranceVos.add(vo);
			}
		}
		map.put("encumbranceVos", encumbranceVos);
		//委托公证
		List<RemindPool> entrusts = remindPoolService.findByCategory("entrust");
		List<RemindPoolVo> entrustVos = new ArrayList<>();
		if(ObjectHelper.isNotEmpty(entrusts) && entrusts.size() > 0){
			for (RemindPool po : entrusts) {
				RemindPoolVo vo = new RemindPoolVo(po);
				entrustVos.add(vo);
			}
		}
		map.put("entrustVos", entrustVos);
		//权证公证
		List<RemindPool> warrants = remindPoolService.findByCategory("warrant");
		List<RemindPoolVo> warrantVos = new ArrayList<>();
		if(ObjectHelper.isNotEmpty(warrants) && warrants.size() > 0){
			for (RemindPool po : warrants) {
				RemindPoolVo vo = new RemindPoolVo(po);
				warrantVos.add(vo);
			}
		}
		map.put("warrantVos", warrantVos);
		//其他
		List<RemindPool> others = remindPoolService.findByCategory("other");
		List<RemindPoolVo> otherVos = new ArrayList<>();
		if(ObjectHelper.isNotEmpty(others) && others.size() > 0){
			for (RemindPool po : others) {
				RemindPoolVo vo = new RemindPoolVo(po);
				otherVos.add(vo);
			}
		}
		map.put("otherVos", otherVos);
		return new ModelAndView("/workbench/home_page",map);
	}
	
	
	
}
