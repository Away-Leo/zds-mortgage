package com.zdsoft.finance.workbench.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.zdsoft.bpm.dto.TaskInstanceQueryDto;
import com.zdsoft.bpm.service.client.BPM;
import com.zdsoft.bpm.util.client.ProcessStoreHelper;
import com.zdsoft.finance.workbench.entity.OverdueCase;
import com.zdsoft.finance.workbench.entity.RemindPool;
import com.zdsoft.finance.workbench.service.OverdueCaseService;
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
 * 版权所有：重庆正大华日软件有限公司
 * @Title: HomePageController.java 
 * @ClassName: HomePageController 
 * @Description: 首页
 * @author gufeng 
 * @date 2017年3月13日 下午5:10:11 
 * @version V1.0
 */
@Controller
@RequestMapping("homePage")
public class HomePageController extends BaseController {

	@Autowired
	private RemindPoolService remindPoolService;

	@Autowired
	private BPM BPM;
	@Autowired
	private com.zdsoft.essential.client.service.CED CED;

	@Autowired
	private OverdueCaseService overdueCaseService;

	/**
	 * 
	 * @Title: init 
	 * @Description: 首页入口
	 * @author yangjia 
	 * @param request
	 * @return
	 */
	@RequestMapping("/init")
	@UriKey(key = "com.zdsoft.finance.homePage.init")
	@Menu(resource = "com.zdsoft.finance.homePage.init", label = "首页", group = "workbench", order = 1)
	public ModelAndView init(HttpServletRequest request) {
		// 资源路径
		ModelMap map = new ModelMap();
		String localWebServer = AppParameter.getLocalWebServer();
		localWebServer += request.getContextPath();
		map.put("localWebServer", localWebServer);
		String application = ProcessStoreHelper.getWorkflowAppCd();
		map.put("application", application);
		// 代办
		PageRequest pageable = new PageRequest(1, 6);

		// 查询逾期信息
		try {
			Map<String, Object> overdueCasemap = new HashMap<String, Object>();
			// 逾期60天以上
			overdueCasemap.put("overdueDays", OverdueCase.DAYS_60);
			Page<Map<String, Object>> page_60 = overdueCaseService
					.findPageOverdueCase(overdueCasemap, pageable);
			map.put("overdueCase60list", page_60.getRecords());
			List<Map<String, Object>> sumOverdueCase60list = overdueCaseService
					.findSumOverdueCase(overdueCasemap);
			map.put("sumOverdueCase60", sumOverdueCase60list.get(0));
		

			// 逾期30-59天
			overdueCasemap.put("overdueDays", OverdueCase.DAYS_59);
			Page<Map<String, Object>> page_59 = overdueCaseService
					.findPageOverdueCase(overdueCasemap, pageable);
			map.put("overdueCase59list", page_59.getRecords());
			List<Map<String, Object>> sumOverdueCase59list = overdueCaseService
					.findSumOverdueCase(overdueCasemap);
			map.put("sumOverdueCase59", sumOverdueCase59list.get(0));

			// 逾期20-29天
			overdueCasemap.put("overdueDays", OverdueCase.DAYS_30);
			Page<Map<String, Object>> page_30 = overdueCaseService
					.findPageOverdueCase(overdueCasemap, pageable);
			map.put("overdueCase30list", page_30.getRecords());
			List<Map<String, Object>> sumOverdueCase30list = overdueCaseService
					.findSumOverdueCase(overdueCasemap);
			map.put("sumOverdueCase30", sumOverdueCase30list.get(0));
		

			// 逾期1-19天
			overdueCasemap.put("overdueDays", OverdueCase.DAYS_19);
			Page<Map<String, Object>> page_19 = overdueCaseService
					.findPageOverdueCase(overdueCasemap, pageable);
			map.put("overdueCase19list", page_19.getRecords());
			List<Map<String, Object>> sumOverdueCase19list = overdueCaseService
					.findSumOverdueCase(overdueCasemap);
			map.put("sumOverdueCase19", sumOverdueCase19list.get(0));
			overdueCasemap.put("overdueDays", OverdueCase.DAYS_0);
			List<Map<String, Object>> list = overdueCaseService
					.findSumOverdueCase(overdueCasemap);
			map.put("sumOverdueCase", list.get(0));

		} catch (Exception e) {
			logger.error("查询案件逾期信息失败", e);
		}

		return new ModelAndView("/workbench/home_page", map);
	}

	/**
	 * 新任务
	 * 
	 * @param request
	 * @return
	 * @author LZM 2014-01-08 下午2:03
	 */

	@RequestMapping(value = "/findMyPendingTasks", produces = "text/plain;charset=UTF-8")
	@UriKey(key = "com.zdsoft.finance.homePage.findMyPendingTasks")
	public ModelAndView findMyPendingTasks(HttpServletRequest request) {
		ModelMap model = new ModelMap();
		String localWebServer = AppParameter.getLocalWebServer();
		localWebServer += request.getContextPath();
		model.put("localWebServer", localWebServer);
		String application = ProcessStoreHelper.getWorkflowAppCd();
		model.put("application", application);
		try {
			// 代办
			PageRequest pageable = new PageRequest(1, 6);
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("empDto", CED.getLoginUser());
			Page<TaskInstanceQueryDto> page = BPM.findMyPendingTasks(params,
					pageable, false);
			// 4.1.4-SNAPSHOT 版本 END
			// Page<TaskInstanceQueryDto> page =
			// BPM.findMyPendingTasks(pageable,CED.getLoginUser());
			model.put("pendingTasks", page);

		} catch (Exception e) {
			logger.error("获取新任务信息失败", e);
		}
		return new ModelAndView("workbench/myPendingTasks_portal", model);
	}
	
	/**
	 * 
	 * @Title: findMyRemindCase 
	 * @Description: 提醒
	 * @author yangjia 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/findMyRemindCase", produces = "text/plain;charset=UTF-8")
	@UriKey(key = "com.zdsoft.finance.homePage.findMyRemindCase")
	public ModelAndView findMyRemindCase(HttpServletRequest request) {
		
		// 资源路径
		ModelMap map = new ModelMap();
		String localWebServer = AppParameter.getLocalWebServer();
		localWebServer += request.getContextPath();
		map.put("localWebServer", localWebServer);
		String application = ProcessStoreHelper.getWorkflowAppCd();
		map.put("application", application);

		// 借款借据
		List<RemindPool> borrows = remindPoolService.findByCategory("borrow");
		List<RemindPoolVo> borrowVos = new ArrayList<>();
		if (ObjectHelper.isNotEmpty(borrows) && borrows.size() > 0) {
			for (RemindPool po : borrows) {
				RemindPoolVo vo = new RemindPoolVo(po);
				borrowVos.add(vo);
			}
		}
		map.put("borrowVos", borrowVos);
		// 补充合同
		List<RemindPool> contracts = remindPoolService
				.findByCategory("contract");
		List<RemindPoolVo> contractVos = new ArrayList<>();
		if (ObjectHelper.isNotEmpty(contracts) && contracts.size() > 0) {
			for (RemindPool po : contracts) {
				RemindPoolVo vo = new RemindPoolVo(po);
				contractVos.add(vo);
			}
		}
		map.put("contractVos", contractVos);
		// 他项权证
		List<RemindPool> encumbrances = remindPoolService
				.findByCategory("encumbrance");
		List<RemindPoolVo> encumbranceVos = new ArrayList<>();
		if (ObjectHelper.isNotEmpty(encumbrances) && encumbrances.size() > 0) {
			for (RemindPool po : encumbrances) {
				RemindPoolVo vo = new RemindPoolVo(po);
				encumbranceVos.add(vo);
			}
		}
		map.put("encumbranceVos", encumbranceVos);
		// 委托公证
		List<RemindPool> entrusts = remindPoolService.findByCategory("entrust");
		List<RemindPoolVo> entrustVos = new ArrayList<>();
		if (ObjectHelper.isNotEmpty(entrusts) && entrusts.size() > 0) {
			for (RemindPool po : entrusts) {
				RemindPoolVo vo = new RemindPoolVo(po);
				entrustVos.add(vo);
			}
		}
		map.put("entrustVos", entrustVos);
		// 权证公证
		List<RemindPool> warrants = remindPoolService.findByCategory("warrant");
		List<RemindPoolVo> warrantVos = new ArrayList<>();
		if (ObjectHelper.isNotEmpty(warrants) && warrants.size() > 0) {
			for (RemindPool po : warrants) {
				RemindPoolVo vo = new RemindPoolVo(po);
				warrantVos.add(vo);
			}
		}
		map.put("warrantVos", warrantVos);
		// 其他
		List<RemindPool> others = remindPoolService.findByCategory("other");
		List<RemindPoolVo> otherVos = new ArrayList<>();
		if (ObjectHelper.isNotEmpty(others) && others.size() > 0) {
			for (RemindPool po : others) {
				RemindPoolVo vo = new RemindPoolVo(po);
				otherVos.add(vo);
			}
		}
		map.put("otherVos", otherVos);
				
		return new ModelAndView("workbench/myRemindCase_portal", map);
		
	}

}
