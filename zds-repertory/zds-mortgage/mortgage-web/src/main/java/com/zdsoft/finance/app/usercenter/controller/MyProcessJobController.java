package com.zdsoft.finance.app.usercenter.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zdsoft.bpm.dto.TaskInstanceQueryDto;
import com.zdsoft.bpm.service.client.BPM;
import com.zdsoft.essential.client.service.CED;
import com.zdsoft.finance.common.utils.AppStatus;
import com.zdsoft.finance.common.utils.app.AppServerUtil;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.PageRequest;
import com.zdsoft.framework.core.common.page.Pageable;
import com.zdsoft.framework.core.common.util.DateHelper;
import com.zdsoft.framework.core.common.util.ObjectHelper;
import com.zdsoft.framework.core.commweb.component.BaseController;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title:MyProcessJobController.java
 * @Package:com.zdsoft.finance.app.usercenter.controller
 * @Description:我的待办任务
 * @author: dengyy
 * @date:2017年1月17日 上午10:56:57
 * @version:v1.0
 */
@RequestMapping("/server/userCenter")
@Controller
public class MyProcessJobController extends BaseController {

    @Autowired
    private BPM BPM ;
    @Autowired
    private CED CED ;

    /**
     * 
     * @Title: myJobs 
     * @Description: 我的待办
     * @author dengyy 
     * @param request 请求信息
     * @return
     */
    @RequestMapping("/myJobs")
    @ResponseBody
    public  String myJobs(HttpServletRequest request){
        try {
            //分页信息
            Integer pageIndex = AppServerUtil.DefaultPageIndex;
            Integer pageSize = AppServerUtil.DefaultPageSize;
            if (ObjectHelper.isNotEmpty(request.getParameter("pageIndex"))) {
                pageIndex = new Integer(request.getParameter("pageIndex"));
            }
            if (ObjectHelper.isNotEmpty(request.getParameter("pageSize"))) {
                pageSize = new Integer(request.getParameter("pageSize"));
            }
            Pageable page = new PageRequest(pageIndex, pageSize);
            
            String type = request.getParameter("type");
            String keyword = request.getParameter("keyword");
            
            //获取待办 
            Map<String, Object> params = new HashMap<String, Object>();
			params.put("empDto", CED.getLoginUser());
			if("1".equals(type)){
			    params.put("subject", keyword);
			}
			if("2".equals(type)){
                params.put("applierNm", keyword);
            }
			if("3".equals(type)){
                params.put("processNm", keyword);
            }
			Page<TaskInstanceQueryDto> findMyPendingTasks = BPM.findMyPendingTasks(params,page,true);
            List<TaskInstanceQueryDto> list = findMyPendingTasks.getRecords();
            List<Map<String, Object>> lists = new  ArrayList<>();
           for (TaskInstanceQueryDto taskInstanceQueryDto : list) {
                Map<String, Object> map = new HashMap<>();
                //标题
                map.put("subject", taskInstanceQueryDto.getSubject());
                //申请人
                map.put("applyName", taskInstanceQueryDto.getApplyNm());
                //申请时间
                map.put("applyDate", DateHelper.dateToString(taskInstanceQueryDto.getApplyTime(), DateHelper.DATE_SHORT_FORMAT));
                //流程名称 
                map.put("processNm",  taskInstanceQueryDto.getProcessNm());
                //项目id
                map.put("projectId",  taskInstanceQueryDto.getProjectId());
                //业务id
                map.put("businessKey",  taskInstanceQueryDto.getBusinessKey());
                //流程id
                map.put("procInstanceId",   taskInstanceQueryDto.getProcInstanceId());
                //任务节点 id
                map.put("taskInstanceId",   taskInstanceQueryDto.getTaskInstanceId());
                //主事项的key
                String homeJobRes = taskInstanceQueryDto.getHomeJobRes();
                //资调编辑的key
                if("com.zdsoft.finance.casemanage.datasurvey.editJob".equals(homeJobRes)){
                    map.put("type",   "1");
                } 
                //办理抵押
                if("com.cnfh.rms.casemanage.handleMortgage.editHandleMortgage".equals(homeJobRes)){
                    map.put("type",   "2");
                } 
                lists.add(map);
            }
            return AppServerUtil.buildJsonList(AppStatus.Succeed, lists);
        } catch (Exception e) {
            logger.error("获取我的待办信息失败");
            e.printStackTrace();
            return AppServerUtil.buildError(AppStatus.SystemError, e);
        }
        
    }
    
    /**
     * 
     * @Title: myDoneJobs 
     * @Description: 我的已办信息
     * @author jingyh 
     * @param request
     * @return
     */
    @RequestMapping("/myDoneJobs")
    @ResponseBody
    public String myDoneJobs(HttpServletRequest request) {
        try {
            //分页信息
            Integer pageIndex = AppServerUtil.DefaultPageIndex;
            Integer pageSize = AppServerUtil.DefaultPageSize;
            if (ObjectHelper.isNotEmpty(request.getParameter("pageIndex"))) {
                pageIndex = new Integer(request.getParameter("pageIndex"));
            }
            if (ObjectHelper.isNotEmpty(request.getParameter("pageSize"))) {
                pageSize = new Integer(request.getParameter("pageSize"));
            }
            Pageable page = new PageRequest(pageIndex, pageSize);
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("subject", request.getParameter("keyword"));
            params.put("taskNm", request.getParameter("keyword"));
           
			Page<TaskInstanceQueryDto> findMyPendingTasks = BPM.findMyPendingTasks(params,page,false);
            List<TaskInstanceQueryDto> list = findMyPendingTasks.getRecords();
            List<Map<String, Object>> lists = new  ArrayList<>();
           for (TaskInstanceQueryDto taskInstanceQueryDto : list) {
                Map<String, Object> map = new HashMap<>();
            	// 任务实例ID
                map.put("taskInstanceId", taskInstanceQueryDto.getTaskInstanceId());
                // 项目ID
                map.put("projectId", taskInstanceQueryDto.getProjectId());
                // 业务表单主键ID
                map.put("businessKey", taskInstanceQueryDto.getBusinessKey());
                // 流程实例ID
                map.put("procInstanceId", taskInstanceQueryDto.getProcInstanceId());
                // 任务名称
                map.put("taskName", taskInstanceQueryDto.getTaskName());
                //申请时间
                map.put("applyTime", DateHelper.dateToString(taskInstanceQueryDto.getApplyTime(), DateHelper.DATE_SHORT_FORMAT));
                // 申请人
                map.put("applyName", taskInstanceQueryDto.getApplyNm());
                // 流程名称
                map.put("processName", taskInstanceQueryDto.getProcessNm());
                lists.add(map);
            }
            return AppServerUtil.buildJsonList(AppStatus.Succeed, lists);
        } catch (Exception e) {
            logger.error("获取我的待办信息失败");
            e.printStackTrace();
            return AppServerUtil.buildError(AppStatus.SystemError, e);
        }
        
    }
}
