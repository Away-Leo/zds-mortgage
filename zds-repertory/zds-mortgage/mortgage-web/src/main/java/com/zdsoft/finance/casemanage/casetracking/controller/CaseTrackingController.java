package com.zdsoft.finance.casemanage.casetracking.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zdsoft.finance.authgrade.entity.OrgIntermediate;
import com.zdsoft.finance.casemanage.casetracking.service.CaseTrackingService;
import com.zdsoft.finance.common.base.QueryObj;
import com.zdsoft.framework.core.common.dto.ResponseMsg;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.PageRequest;
import com.zdsoft.framework.core.common.util.ObjectHelper;
import com.zdsoft.framework.core.commweb.annotation.UriKey;
import com.zdsoft.framework.core.commweb.component.BaseController;
import com.zdsoft.framework.cra.annotation.Menu;


/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title:CaseTrackingController.java
 * @Package:com.zdsoft.finance.casemanage.controller
 * @Description:案件跟踪表
 * @author: caixiekang
 * @date:2017年1月13日 下午10:28:38
 * @version:v1.0
 */
@Controller
@RequestMapping("/casemanage")
public class CaseTrackingController extends BaseController {
    
    @Autowired
    private CaseTrackingService caseTrackingService;
    
    /**
     * 
     * 案件跟踪初始化
     *
     * @author caixiekang
     * @return
     */
    @RequestMapping("/listCaseTracking")
    @UriKey(key = "com.zdsoft.finance.casemanage.casetracking.listCaseTracking")
    @Menu(resource = "com.zdsoft.finance.casemanage.casetracking.listCaseTracking", label = "案件跟踪表", group = "project", order = 15)
    public ModelAndView listCaseTracking(){
        
        return new ModelAndView("/casemanage/casetracking/case_tracking_list");
        
    }
    
    /**
     * 
     * 获取案件跟踪列表
     *
     * @author caixiekang
     * @param request
     * @param pageable
     * @return
     */
    @SuppressWarnings("unchecked")
    @RequestMapping("/getCaseTrackingList")
    @UriKey(key = "com.zdsoft.finance.casemanage.casetracking.getCaseTracking")
    @ResponseBody
    public ResponseMsg getCaseTrackingList(HttpServletRequest request,PageRequest pageable){
        ResponseMsg msg = new ResponseMsg();
        try {
            List<QueryObj> queryObjs = (List<QueryObj>) request.getAttribute("listObj");
            Page<Map<String, Object>> caseTrackingList = caseTrackingService.queryAllCaseTracking(pageable, queryObjs);
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
    
    /**
     * 案件状态自定义下拉框
     * @param jsoncallback
     * @return
     */
    @RequestMapping(value = "/findAllCaseApplyStage")
    @UriKey(key = "com.zdsoft.finance.casemanage.casetracking.findAllCaseApplyStage")
    @ResponseBody
    public String findAllCaseApplyStage(String jsoncallback){
//       List<Map<String,Object>> returnData=new ArrayList<Map<String,Object>>();
//        List<OrgIntermediate> sourceData=this.institutionGradeService.findAllOrg();
//        for(OrgIntermediate temp:sourceData){
//            Map<String,Object> rowData=new HashMap<String,Object>();
//            rowData.put("id",temp.getOrgCd());
//            rowData.put("text",temp.getOrgNm());
//            returnData.add(rowData);
//        }
//        return ObjectHelper.objectToJson(returnData,jsoncallback);
        return null;
    }

}
