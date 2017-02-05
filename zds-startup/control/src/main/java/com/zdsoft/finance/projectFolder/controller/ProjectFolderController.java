//package com.zdsoft.finance.projectFolder.controller;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import javax.servlet.http.HttpServletRequest;
//
//import com.zdsoft.framework.core.common.dto.ResponseMsg;
//import com.zdsoft.framework.core.common.util.ObjectHelper;
//import com.zdsoft.framework.core.commweb.annotation.UriKey;
//import com.zdsoft.framework.core.commweb.component.BaseController;
//import org.hibernate.hql.spi.id.MultiTableBulkIdStrategy.DeleteHandler;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.servlet.ModelAndView;
//
//import com.zdsoft.finance.common.exception.BusinessException;
//import com.zdsoft.finance.projectFolder.service.ProjectFolderService;
//import com.zdsoft.finance.projectFolder.vo.ProjectFolderInfoVo;
//import com.zdsoft.finance.projectfolder.entity.ProjectInfo;
//import com.zdsoft.finance.projectfolder.entity.ProjectInfoType;
//import com.zdsoft.framework.core.common.dto.ResponseMsg;
//import com.zdsoft.framework.core.common.util.ObjectHelper;
//import com.zdsoft.framework.core.common.util.StoreHelper;
//import com.zdsoft.framework.core.commweb.annotation.UriKey;
//import com.zdsoft.framework.core.commweb.component.BaseController;
//import com.zdsoft.framework.cra.client.service.CRA;
//
//@Controller
//@RequestMapping("/projectFolder")
//public class ProjectFolderController extends BaseController {
//
//	@Autowired
//	private ProjectFolderService projectFolderService;
//
//    /*@Autowired
//    private ProjectService projectService;*/
//
//    @Autowired
//    private CRA CRA;
//
//
//
//	/**
//	 * 加载项目文件夹
//	 * @param request
//	 * @param projectCd
//	 * @return
//	 * @throws BusinessException
//	 */
//	@RequestMapping("/showProjectFolderGroup")
//	@UriKey(key = "zf.projectfolder.showProjectFolderGroup")
//	@ResponseBody
//	public String showProjectFolderGroup(HttpServletRequest request, String projectCd,String jsoncallback) {
//		ResponseMsg msg = new ResponseMsg();
//
//		if (ObjectHelper.isEmpty(projectCd)) {
//			msg.setResultStatus(ResponseMsg.APP_ERROR);
//			msg.setMsg("项目编号projectCd不能为空!");
//		} else {
//			msg.setResultStatus(ResponseMsg.SUCCESS);
//			//这里ID值存放projectCd值
//			msg.setId(projectCd);
//			List<ProjectFolderInfoVo> infoVos = new ArrayList<ProjectFolderInfoVo>();
//			//获取固定项目资料类别
//			List<ProjectInfoType> fixGroups = projectFolderService.showFixProjectFolderGroup(null);
//			List<String> groupNames = new ArrayList<String>();
//			for (ProjectInfoType group : fixGroups) {
//				groupNames.add(group.getGroupNm());
//				ProjectFolderInfoVo infoVo = new ProjectFolderInfoVo();
//				infoVo.setGroupNm(group.getGroupNm());
//				infoVo.setInfoLabel(group.getInfoLabel());
//				infoVos.add(infoVo);
//			}
//			//获取非固定项目资料类别
//			List<ProjectInfoType> groups = projectFolderService.showProjectFolderGroup(projectCd);
//			for (ProjectInfoType group : groups) {
//				if(groupNames.contains(group.getGroupNm())){
//					continue;
//				}
//				ProjectFolderInfoVo infoVo = new ProjectFolderInfoVo();
//				infoVo.setGroupNm(group.getGroupNm());
//				infoVo.setInfoLabel(group.getInfoLabel());
//				infoVos.add(infoVo);
//			}
//			msg.setRows(infoVos);
//		}
//		return ObjectHelper.objectToJson(msg, jsoncallback);
//	}
//
//	/**
//	 * 加载项目资料信息
//	 * @param request
//	 * @param projectCd
//	 * @return
//	 * @throws BusinessException
//	 */
//	@RequestMapping("/showProjectInfos")
//	@UriKey(key = "zf.projectfolder.showProjectInfos")
//	@ResponseBody
//	public String showProjectInfos(HttpServletRequest request, String projectCd, String groupNm,String jsoncallback) {
//		ResponseMsg msg = new ResponseMsg();
//		if (ObjectHelper.isEmpty(projectCd) || ObjectHelper.isEmpty(groupNm)) {
//			msg.setResultStatus(ResponseMsg.APP_ERROR);
//			msg.setMsg("项目编号projectCd和分组名称不能为空!");
//		} else {
//			msg.setResultStatus(ResponseMsg.SUCCESS);
//			//这里ID值存放projectCd值
//			msg.setId(projectCd);
//			//显示固定文件夹
//			List<ProjectInfoType> infoTypes = projectFolderService.showFixProjectFolderGroup(groupNm);
//			List<ProjectFolderInfoVo> infoVos = new ArrayList<ProjectFolderInfoVo>();
//			for(ProjectInfoType infoType:infoTypes){
//				ProjectFolderInfoVo infoVo = new ProjectFolderInfoVo();
//				infoVo.setAppCd(infoType.getAppCd());
//				infoVo.setBusinessId("");
//				infoVo.setClassNm(infoType.getClassNm());
//				infoVo.setDisplayOrder(infoType.getDisplayOrder());
//				infoVo.setGroupNm(infoType.getGroupNm());
//				infoVo.setId("");
//				infoVo.setInfoLabel(infoType.getInfoLabel());
//				infoVo.setIsFix(infoType.getIsFix());
//				infoVo.setIsFlow(infoType.getIsFlow());
//				infoVo.setProjectCd(projectCd);
//				infoVo.setTypeId(infoType.getId());
//				infoVo.setViewResource(infoType.getViewResource());
//				infoVos.add(infoVo);
//			}
//			//显示项目资料
//			List<ProjectInfo> infos = projectFolderService.showProjectInfos(projectCd, groupNm);
//			for (ProjectInfo info : infos) {
//				if(info.getProjectInfoType().getIsFix()){
//					continue;
//				}
//				ProjectFolderInfoVo infoVo = new ProjectFolderInfoVo();
//				infoVo.setAppCd(info.getProjectInfoType().getAppCd());
//				infoVo.setBusinessId(info.getBusinessId());
//				infoVo.setClassNm(info.getProjectInfoType().getClassNm());
//				infoVo.setDisplayOrder(info.getProjectInfoType().getDisplayOrder());
//				infoVo.setGroupNm(info.getProjectInfoType().getGroupNm());
//				infoVo.setId(info.getId());
//				infoVo.setInfoLabel(info.getProjectInfoType().getInfoLabel());
//				infoVo.setIsFix(info.getProjectInfoType().getIsFix());
//				infoVo.setIsFlow(info.getProjectInfoType().getIsFlow());
//				infoVo.setProjectCd(info.getProjectCd());
//				infoVo.setTypeId(info.getProjectInfoType().getId());
//				infoVo.setViewResource(info.getProjectInfoType().getViewResource());
//				infoVos.add(infoVo);
//			}
//			//相同文件加上序号区分 by xgyin 20160418
//			int infoVosSize = infoVos.size();
//			if(infoVosSize>1){
//				for(int i=0;i<infoVosSize;i++){
//					infoVos.get(i).setInfoLabel(infoVos.get(i).getInfoLabel() + String.valueOf(i));
//				}
//			}
//
//			msg.setRows(infoVos);
//		}
//		return ObjectHelper.objectToJson(msg, jsoncallback);
//	}
//
//	/**
//	 * 获取项资料信息url
//	 * @param request
//	 * @param id
//	 * @return
//	 */
//	@RequestMapping("/findProjectFolderInfoInnerUrl")
//	@UriKey(key = "zf.projectfolder.findProjectFolderInfoInnerUrl")
//	public String findProjectFolderInfoInnerUrl(HttpServletRequest request, String projectCd,String groupNm,String id ,String jsoncallback) {
//		ResponseMsg msg = new ResponseMsg();
//
//		if (ObjectHelper.isEmpty(id)) {
//			ProjectInfoType infoType = this.projectFolderService.findProjectInfoTypeByGroupNm(groupNm);
//			if (infoType == null) {
//				msg.setResultStatus(ResponseMsg.APP_ERROR);
//				msg.setMsg("项目类别中分组编号:" + groupNm + "无效!");
//			}
//			try {
//				StringBuffer url = new StringBuffer(CRA.getMenu(infoType.getAppCd(), infoType.getViewResource()));
//				url.append("?projectCd=" + projectCd + "&businessId=");
//				msg.setId(url.toString());
//				msg.setResultStatus(ResponseMsg.SUCCESS);
//				return "redirect:"+url.toString();
//			} catch (Exception e) {
//				msg.setResultStatus(ResponseMsg.APP_ERROR);
//				msg.setMsg("获取项目资料信息URL发生异常:" + e.getMessage() + "!");
//			}
//		} else {
//			ProjectInfo info = this.projectFolderService.findProjectInfoById(id);
//			if (info == null) {
//				msg.setResultStatus(ResponseMsg.APP_ERROR);
//				msg.setMsg("项目资料信息ID:" + id + "无效!");
//			}
//			try {
//				StringBuffer url = new StringBuffer(CRA.getMenu(info.getProjectInfoType().getAppCd(), info
//						.getProjectInfoType().getViewResource()));
//				url.append("?projectCd=" + info.getProjectCd() + "&businessId=" + info.getBusinessId());
//				msg.setId(url.toString());
//				return "redirect:"+url.toString();
//				/*msg.setId("floatingLoanEvaluate/flowLoanWorkFlowView");
//				msg.setResultStatus(ResponseMsg.SUCCESS);
//				return "redirect:"+"/admin/floatingLoanEvaluate/flowLoanWorkFlowViewFolder?projectCd=" + info.getProjectCd() + "&businessId=" + info.getBusinessId();
//*/
//			} catch (Exception e) {
//				msg.setResultStatus(ResponseMsg.APP_ERROR);
//				msg.setMsg("获取项目资料信息URL发生异常:" + e.getMessage() + "!");
//				e.printStackTrace();
//				logger.error("获取项目资料信息URL发生异常:",e);
//			}
//		}
//
//		return null;
//	}
//	/**
//	 * 获取审批单审批意见信息
//	 * @param request
//	 * @param businessId 审批单编号
//	 * @return
//	 * @throws BusinessException
//	 */
//	@RequestMapping("/showProjectFolderTaskOpinions")
//	@UriKey(key = "zf.projectfolder.showProjectFolderTaskOpinions")
//	@ResponseBody
//	public String showProjectFolderTaskOpinions(HttpServletRequest request, String id,String jsoncallback)  {
//		ResponseMsg msg = new ResponseMsg();
//		/*try{
//			if(ObjectHelper.isEmpty(id)){
//				throw new BusinessException("审批单编号不能为空!");
//			}else{
//				ProjectInfo info = this.projectFolderService.findProjectInfoById(id);
//					if (info == null) {
//					throw new BusinessException("项目资料信息ID:" + id + "无效!");
//
//				}
//				BusiForm busiForm =busiFormService.findById(info.getBusinessId());
//				if(busiForm==null){
//					msg.setResultStatus(ResponseMsg.APP_ERROR);
//					msg.setMsg("当前审批单编号["+info.getBusinessId() +"]无效!");
//				}else{
//					//该审批单的流程实列ID
//					String processInstanceId = busiForm.getProcessInstanceKey();
//						//获取审批意见
//					List<TaskOpinionDto> taskOpinions = BPM.findTaskOpinions(processInstanceId, busiForm.getBusiFormNo());
//					if(ObjectHelper.isNotEmpty(taskOpinions)){
//						//获取流程审批单描述
//						BpmProcessInstance processIns = processInstanceService.findProcessInstance(processInstanceId);
//							if(processIns!=null){
//							String insDesc = "流程名称【"+processIns.getProcessNm() + "】；";
//							String preTaskAndAssignees  = "任务名称【";
//							String taskAndAssignees ="";
//
//							for (BpmTaskInstance taskInst : processIns.getActivityTaskInstances()) {
//								taskAndAssignees += taskInst.getTaskName() + "："+taskInst.getAssigneeNames() + ",";
//							}
//
//							if (taskAndAssignees.length() > 0) {
//								taskAndAssignees = taskAndAssignees.substring(0, taskAndAssignees.length() - 1);
//								insDesc = insDesc + preTaskAndAssignees + taskAndAssignees + "】";
//							} else{
//								insDesc = "流程名称【"+processIns.getProcessNm() + "】已完成";
//							}
//
//							msg.setId(insDesc);
//
//						}
//						msg.setResultStatus(ResponseMsg.SUCCESS);
//						msg.setMsg("当前审批单具有审批意见!");
//						msg.setRows(taskOpinions);
//					}
//
//				}
//			}
//		}catch(AppException e){
//			msg.setResultStatus(ResponseMsg.SYS_ERROR);
//			msg.setMsg(e.getMessage());
//		}*/
//		return ObjectHelper.objectToJson(msg, jsoncallback);
//	}
//
//	/**
//	 * 项目文件夹打印
//	 * @throws Exception
//	 */
//	@RequestMapping("/projectInfoPrint")
//	@UriKey(key = "zf.projectfolder.projectInfoPrint")
//	public ModelAndView projectInfoPrint(String folderId,String groupNm,String projectCd) throws Exception  {
//		if(ObjectHelper.isEmpty(folderId) && ObjectHelper.isEmpty(groupNm)){
//			logger.error("文件夹id或groupNm为空");
//			throw new BusinessException("文件夹id或groupNm为空");
//		}
//		ModelAndView modelAndView=new ModelAndView("process/project_info_print");
//		StringBuffer url=new StringBuffer();
//		if (ObjectHelper.isEmpty(folderId)) {
//			ProjectInfoType infoType = this.projectFolderService.findProjectInfoTypeByGroupNm(groupNm);
//			url.append(CRA.getMenu(infoType.getAppCd(), infoType.getViewResource()));
//			url.append("?projectCd=" + projectCd + "&businessId=");
//		} else {
//			ProjectInfo info = this.projectFolderService.findProjectInfoById(folderId);
//			url.append(CRA.getMenu(info.getProjectInfoType().getAppCd(), info
//					.getProjectInfoType().getViewResource()));
//			url.append("?projectCd=" + info.getProjectCd() + "&businessId=" + info.getBusinessId());
//		}
//
//		modelAndView.addObject("businessUrl", url.toString());
//		return modelAndView;
//	}
//
//}
