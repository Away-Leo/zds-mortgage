package com.zdsoft.finance.app.notificationscenter.controller;

import java.util.Date;
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
 * @Title: NotificationsCenterController.java 
 * @ClassName: NotificationsCenterController 
 * @Description: app 消息中心
 * @author dengyy 
 * @date 2017年3月3日 上午10:04:34 
 * @version V1.0
 */
@RequestMapping("/server/notificationsCenter")
@Controller
public class NotificationsCenterController extends BaseController {

    @Autowired
    private BPM BPM ;
    @Autowired
    private CED CED ;

    /**
     * 
     * @Title: NotificationsCenterIndex 
     * @Description: app 消息中心
     * @author dengyy 
     * @param request
     * @return
     */
    @RequestMapping("/index")
    @ResponseBody
    public String NotificationsCenterIndex(HttpServletRequest request){
        //查询待办任务 
        Pageable pageable = new PageRequest(1, 1000);
        try {
            Map<String, Object> rtMap = new HashMap<>();
            //待办任务
            Map<String, Object> params = new HashMap<String, Object>();
			params.put("empDto", CED.getLoginUser());
			Page<TaskInstanceQueryDto> page = BPM.findMyPendingTasks(params,pageable,true);
            Map<String, Object> myJobsMap = new HashMap<>();
            if(ObjectHelper.isNotEmpty(page.getRecords())){
                List<TaskInstanceQueryDto> records = page.getRecords();
                myJobsMap.put("latestMsgTile", records.get(0).getSubject());
                myJobsMap.put("latestMsgTime", DateHelper.dateToString(new Date(), DateHelper.DATE_WITHSECOND_FORMAT));
                myJobsMap.put("msgNum", records.size()+"");
            }else{
                myJobsMap.put("latestMsgTile", "");
                myJobsMap.put("latestMsgTime", DateHelper.dateToString(new Date(), DateHelper.DATE_WITHSECOND_FORMAT));
                myJobsMap.put("msgNum", "0");
            }
            rtMap.put("myJobs", myJobsMap);
            //消息提醒
            Map<String, Object> myReminderMap = new HashMap<>();
            myReminderMap.put("latestMsgTile", "");
            myReminderMap.put("latestMsgTime", DateHelper.dateToString(new Date(), DateHelper.DATE_WITHSECOND_FORMAT));
            myReminderMap.put("msgNum", "0");
            rtMap.put("myReminder", myReminderMap);
            
            //经营日报
            Map<String, Object> myDailyMap = new HashMap<>();
            myDailyMap.put("latestMsgTile", "");
            myDailyMap.put("latestMsgTime", DateHelper.dateToString(new Date(), DateHelper.DATE_WITHSECOND_FORMAT));
            myDailyMap.put("msgNum", "0");
            rtMap.put("myDaily", myDailyMap);
            
            //后补资料提醒
            Map<String, Object> mySupplementMap = new HashMap<>();
            mySupplementMap.put("latestMsgTile", "");
            mySupplementMap.put("latestMsgTime", DateHelper.dateToString(new Date(), DateHelper.DATE_WITHSECOND_FORMAT));
            mySupplementMap.put("msgNum", "0");
            rtMap.put("mySupplement", mySupplementMap);
            return AppServerUtil.buildJsonObject(AppStatus.Succeed, rtMap);
        } catch (Exception e) {
            logger.error("app消息中心信息失败", e);
            e.printStackTrace();
            return AppServerUtil.buildError(AppStatus.ArgsError);
        }
    }
}
