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
    
    /**
     * 
     * 我的待办
     *
     * @author dengyy
     * @param request
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
            //暂时使用假数据  BPM 查询有问题
            //获取待办 
            Page<TaskInstanceQueryDto> findMyPendingTasks = BPM.findMyPendingTasks(page);
            List<TaskInstanceQueryDto> list = findMyPendingTasks.getRecords();
            List<Map<String, Object>> lists = new  ArrayList<>();
           for (TaskInstanceQueryDto taskInstanceQueryDto : list) {
                Map<String, Object> map = new HashMap<>();
                //标题
                map.put("subject", taskInstanceQueryDto.getSubject());
                //申请类型
                map.put("processNm", taskInstanceQueryDto.getProcessNm());
                //申请时间
                map.put("applyTime", DateHelper.dateToString(taskInstanceQueryDto.getApplyTime(), DateHelper.DATE_SHORT_FORMAT));
                //当前任务
                map.put("taskName", taskInstanceQueryDto.getTaskName());
                //当前处理人
                map.put("assigneeNm", taskInstanceQueryDto.getAssigneeNm());
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
