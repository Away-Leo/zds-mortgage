//package com.zdsoft.finance.projectatta.controller;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import javax.servlet.http.HttpServletRequest;
//
//import org.slf4j.Logger;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.ModelMap;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.servlet.ModelAndView;
//
//import com.zdsoft.finance.common.base.QueryObj;
//import com.zdsoft.finance.common.exception.BusinessException;
//import com.zdsoft.finance.common.utils.ParameterUtil;
//import com.zdsoft.finance.project.entity.Project;
//import com.zdsoft.finance.project.service.ProjectService;
//import com.zdsoft.finance.projectatta.entity.ProjectAtta;
//import com.zdsoft.finance.projectatta.service.ProjectAttaService;
//import com.zdsoft.finance.projectatta.vo.ProjectAttaVo;
//import com.zdsoft.framework.core.common.annotation.Log;
//import com.zdsoft.framework.core.common.dto.ResponseMsg;
//import com.zdsoft.framework.core.common.page.Page;
//import com.zdsoft.framework.core.common.page.PageRequest;
//import com.zdsoft.framework.core.common.util.ObjectHelper;
//import com.zdsoft.framework.core.commweb.annotation.UriKey;
//import com.zdsoft.framework.core.commweb.component.BaseController;
//
///** 
//* 版权所有：重庆正大华日软件有限公司
//* @Title: ProjectAttaController.java 
//* @Package com.zdsoft.finance.project.controller 
//* @Description: 项目附件Controller层
//* @author jingyh 
//* @date 2016年12月2日 上午11:01:48 
//* @version V1.0 
//*/
//@Controller
//@RequestMapping("/projectAtta")
//public class ProjectAttaController extends BaseController {
//	
//	@Log
//	private Logger log;
//
//	@Autowired
//	private ProjectAttaService projectAttaService;
//	
//	@Autowired
//	private ProjectService projectService;
//	
//	/**
//	 * 
//	 * @Title: projectAttachmentListPage 
//	 * @Description: 线下项目附件列表页面
//	 * @author jingyh 
//	 * @date 2016年12月2日 下午2:45:48
//	 * @param req
//	 * @param projectId
//	 * @return 
//	 * ModelAndView 
//	 * @throws
//	 */
//	@RequestMapping("/projectAttaListPage")
//	@UriKey(key = "com.zdsoft.finance.projectatta.projectAttaListPage")
//	public ModelAndView projectAttaListPage(HttpServletRequest req, String projectId) {
//		log.info("进入项目的附件管理列表页面");
//		log.debug("项目Id为：{}", projectId);
//		String viewName = "projectAtta/project_attachment_list";
//		ModelMap model = new ModelMap();
//		if (ObjectHelper.isNotEmpty(projectId)) {
//			try {
//				Project project = projectService.findOne(projectId);
//				model.put("projectInfo", project);
//			} catch (BusinessException e) {
//				log.error("查询项目信息出错", e);
//				e.printStackTrace();
//			}
//		}
//		model.put("projectId", projectId);
//		return new ModelAndView(viewName, model);
//	}
//	
//	/**
//	 * 
//	 * @Title: pageProjectAttaInfos 
//	 * @Description: 分页查询项目附件集合信息
//	 * @author jingyh 
//	 * @date 2016年12月2日 下午3:05:44
//	 * @param req
//	 * @param pageRequest
//	 * @return 
//	 * ResponseMsg 
//	 * @throws
//	 */
//	@RequestMapping(value="/pageProjectAttaInfos")
//    @UriKey(key = "com.zdsoft.finance.projectatta.pageProjectAttaInfos")
//    @ResponseBody
//    public ResponseMsg pageProjectAttaInfos(HttpServletRequest req,PageRequest pageRequest){
//		log.info("分页查询项目附件信息");
//		ResponseMsg msg = new ResponseMsg();
//        List<QueryObj> li = ParameterUtil.getQueryObjByParameter(req, new String[]{ "proAtta"});
//        Page<ProjectAtta> page = projectAttaService.findByHqlConditions(pageRequest, li);
//        List<ProjectAttaVo> resultList = new ArrayList<ProjectAttaVo>();
//        for (ProjectAtta atta : page.getRecords()) {
//        	resultList.add(new ProjectAttaVo(atta, null, null));
//        }
//        log.debug("总计数据集大小为：{}", page.getTotalRows());
//        msg.setResultStatus(ResponseMsg.SUCCESS);
//        msg.setRows(resultList);
//        msg.setTotal(page.getTotalRows());
//        return msg;
//    }
//	
//	/**
//	 * 
//	 * @Title: saveOrUpdateProjectAtta 
//	 * @Description: 保存或更新项目附件
//	 * @author jingyh 
//	 * @date 2016年12月2日 下午6:15:23
//	 * @param req
//	 * @param info
//	 * @return 
//	 * ResponseMsg 
//	 * @throws
//	 */
//	@RequestMapping("/saveOrUpdateProjectAtta")
//	@UriKey(key = "com.zdsoft.finance.projectatta.saveOrUpdateProjectAtta")
//	@ResponseBody
//	public ResponseMsg saveOrUpdateProjectAtta(HttpServletRequest req, ProjectAttaVo info) {
//		log.info("保存或更新项目附件信息");
//		ResponseMsg msg = new ResponseMsg();
//		try {
//			// 获取流程Id
//			String processInstanceId = req.getParameter("processInstanceId");
//			if (ObjectHelper.isNotEmpty(processInstanceId)) {
//				// TODO 如果流程Id不为空，则获取流程业务Id和类型
//				// TODO 获取流程表单
//			}
//			ProjectAtta projectAtta = this.projectAttaService.saveOrUpdateProjectAttaInfo(info.toPo());
//			msg.setResultStatus(ResponseMsg.SUCCESS);
//			msg.setId(projectAtta.getId());
//			log.debug("保存成功:{}", projectAtta.getId());
//			msg.setMsg("保存成功！");
//		} catch (Exception e) {
//			log.error("保存项目附件信息失败！");
//			msg.setResultStatus(ResponseMsg.APP_ERROR);
//			msg.setMsg(e.getMessage());
//			e.printStackTrace();
//		}
//		return msg;
//	}
//	
//	/**
//	 * 
//	 * @Title: deleteProjectAttaByIds 
//	 * @Description: 删除项目附件
//	 * @author jingyh 
//	 * @date 2016年12月5日 上午11:22:40
//	 * @param req
//	 * @param id
//	 * @return 
//	 * ResponseMsg 
//	 * @throws
//	 */
//	@RequestMapping("/deleteProjectAtta")
//	@UriKey(key = "com.zdsoft.finance.projectatta.deleteProjectAttaByIds")
//	@ResponseBody
//	public ResponseMsg deleteProjectAttaByIds(HttpServletRequest req, String ids) {
//		log.info("删除项目附件");
//		log.debug("项目附件的ids为：{}", ids);
//		ResponseMsg msg = new ResponseMsg();
//		try {
//			this.projectAttaService.deleteProjectAttaByIds(ids);
//		} catch (Exception e) {
//			log.error("删除项目附件出现错误！", e);
//			msg.setResultStatus(ResponseMsg.APP_ERROR);
//			msg.setMsg(e.getMessage());
//			e.printStackTrace();
//		}
//		return msg;
//	}
//	
//}
