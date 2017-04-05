package com.zdsoft.finance.casemanage.casetracking.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zdsoft.essential.client.aop.annotation.DataAuthResource;
import com.zdsoft.essential.client.service.CED;
import com.zdsoft.essential.dto.permission.DataOperPermDto;
import com.zdsoft.finance.common.base.QueryObj;
import com.zdsoft.finance.common.utils.ParameterUtil;
import com.zdsoft.finance.marketing.service.CaseApplyService;
import com.zdsoft.framework.core.common.dto.ResponseMsg;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.PageRequest;
import com.zdsoft.framework.core.common.util.StoreHelper;
import com.zdsoft.framework.core.commweb.annotation.UriKey;
import com.zdsoft.framework.core.commweb.component.BaseController;
import com.zdsoft.framework.cra.annotation.Menu;


/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: CaseTrackingController.java 
 * @ClassName: CaseTrackingController 
 * @Description: 案件跟踪表
 * @author xj 
 * @date 2017年2月23日 上午11:47:42 
 * @version V1.0
 */
@Controller
@RequestMapping("/casemanage")
public class CaseTrackingController extends BaseController {
    
    @Autowired
    private CaseApplyService caseApplyService;
    
    @Autowired
    private CED CED ;
    
    /**
     * 
     * @Title: listCaseTracking 
     * @Description: 案件跟踪初始化
     * @author xj 
     * @return
     */
    @RequestMapping("/listCaseTracking")
    @UriKey(key = "com.cnfh.rms.casemanage.casetracking.listCaseTracking")
    @Menu(resource = "com.cnfh.rms.casemanage.casetracking.listCaseTracking", label = "案件跟踪表", group = "project", order = 15)
    @DataAuthResource(resource="com.cnfh.rms.casemanage.casetracking.listCaseTracking.dataAuth",label="案件跟踪表",group="com.cnfh.rms.casemanage.casetracking.listCaseTracking")
    public ModelAndView listCaseTracking(){
        
        return new ModelAndView("/casemanage/casetracking/case_tracking_list");
        
    }
    
    /**
     * 
     * @Title: getCaseTrackingList 
     * @Description: 获取案件跟踪列表
     * @author xj 
     * @param request
     * @param pageable
     * @return
     */
    @RequestMapping("/getCaseTrackingList")
    @UriKey(key = "com.cnfh.rms.casemanage.casetracking.getCaseTracking")
    @ResponseBody
    public ResponseMsg getCaseTrackingList(HttpServletRequest request,PageRequest pageable){
        ResponseMsg msg = new ResponseMsg();
        try {
        	List<QueryObj> queryObjs = ParameterUtil.getQueryObjByParameter(request, new String[]{"ma","bcust"});
        	//数据权限
            DataOperPermDto dtOperPermDto=CED.findDataPerms(StoreHelper.getApplication(), "com.cnfh.rms.casemanage.casetracking.listCaseTracking.dataAuth");
            Page<Map<String, Object>> caseTrackingList = caseApplyService.findPageCaseApplyBasicInforList(pageable, queryObjs,dtOperPermDto);
            msg.setMsg("案件跟踪列表查询成功");
            msg.setResultStatus(ResponseMsg.SUCCESS);
            msg.setTotal(caseTrackingList.getTotalRows());
            msg.setRows(caseTrackingList.getRecords());
        } catch (Exception e) {
            e.printStackTrace();
            msg.setMsg("案件跟踪列表查询失败");
            msg.setResultStatus(ResponseMsg.APP_ERROR);
        }
        return msg;
    }
    
}
