package com.zdsoft.finance.businesssetting.controller;

import com.zdsoft.finance.businesssetting.entity.SysLabel;
import com.zdsoft.finance.businesssetting.service.SysLabelService;
import com.zdsoft.finance.businesssetting.vo.SysLabelVo;
import com.zdsoft.finance.common.base.QueryObj;
import com.zdsoft.framework.core.common.dto.ResponseMsg;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.PageRequest;
import com.zdsoft.framework.core.common.util.ObjectHelper;
import com.zdsoft.framework.core.commweb.annotation.UriKey;
import com.zdsoft.framework.core.commweb.component.BaseController;
import com.zdsoft.framework.cra.annotation.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * 版权所有：重庆正大华日软件有限公司
 *
 * @author LiaoGuoWei
 * @version V1.0
 * @title SysLabelController.java
 * @className SysLabelController
 * @description 标签设置controller
 * @create 2017/2/27 20:35
 **/
@RequestMapping("/sysLabel")
@Controller
public class SysLabelController extends BaseController {

    @Autowired
    private SysLabelService sysLabelService;

    /**
     * @param
     * @return org.springframework.web.servlet.ModelAndView
     * @throws
     * @Title: initSysLabel
     * @Description: 标签设置初始化
     * @author liaoguowei
     */
    @RequestMapping("/initSysLabel")
    @UriKey(key = "com.zdsoft.finance.parameter.initSysLabel")
    @Menu(resource = "com.zdsoft.finance.parameter.initSysLabel", label = "标签设置", group = "businessSetting", order = 1)
    public ModelAndView initSysLabel() {
        return new ModelAndView("businesssetting/syslabel_list");
    }

    /**
     * @param request
     * @param jsoncallback
     * @param pageable
     * @return java.lang.String
     * @throws
     * @Title: getSysLabel
     * @Description: 获取标签列表
     * @author liaoguowei
     */
    @RequestMapping("/sysLabel")
    @UriKey(key = "com.zdsoft.finance.parameter.getSysLabel")
    @ResponseBody
    public String getSysLabel(HttpServletRequest request, String jsoncallback, PageRequest pageable) {
        ResponseMsg msg = new ResponseMsg();
        try {
            // 获取页面封装的查询参数
            List<QueryObj> queryObjs = (List<QueryObj>) request.getAttribute("listObj");
            QueryObj obj = new QueryObj();
            obj.setObj("updateTime");
            obj.setElement("OB");
            obj.setOperator("OB");
            obj.setValue("DESC");
            queryObjs.add(obj);
            // 分页抵押权人
            Page<SysLabel> sysLabelPage = sysLabelService.findByHqlConditions(pageable, queryObjs);
            List<SysLabelVo> returnList=new ArrayList<SysLabelVo>();
            if(ObjectHelper.isNotEmpty(sysLabelPage.getRecords())){
                for(SysLabel temp:sysLabelPage.getRecords()){
                    SysLabelVo vo=new SysLabelVo(temp);
                    returnList.add(vo);
                }
            }
            msg.setResultStatus(ResponseMsg.SUCCESS);
            msg.setTotal(sysLabelPage.getTotalRows());
            msg.setRows(returnList);
        } catch (Exception e) {
            logger.error("获取标签设置列表数据出错",e);
            e.printStackTrace();
            msg.setMsg(e.getMessage());
            msg.setResultStatus(ResponseMsg.APP_ERROR);
        }

        return ObjectHelper.objectToJson(msg, jsoncallback);
    }

    /**
     * @param label
     * @param jsoncallback
     * @param pageable
     * @return java.lang.String
     * @throws
     * @Title: updateSysLabel
     * @Description: 更新标签
     * @author liaoguowei
     */
    @RequestMapping(value = "/updateSysLabel",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @UriKey(key = "com.zdsoft.finance.parameter.updateSysLabel")
    @ResponseBody
    public String updateSysLabel(SysLabelVo label, String jsoncallback, PageRequest pageable) {
        ResponseMsg msg = new ResponseMsg();
        try {
            sysLabelService.saveOrUpdateSysLabel(label.toPo());
            msg.setResultStatus(ResponseMsg.SUCCESS);
        } catch (Exception e) {
            logger.error("更新标签出错",e);
            e.printStackTrace();
            msg.setMsg(e.getMessage());
            msg.setResultStatus(ResponseMsg.APP_ERROR);
        }
        return ObjectHelper.objectToJson(msg, jsoncallback);
    }

    /**
     * @Title: deleteSysLabel
     * @Description: 删除标签设置
     * @author liaoguowei
     * @param id
     * @param jsoncallback
     * @return java.lang.String
     * @throws
     */
    @RequestMapping("/deleteSysLabel")
    @UriKey(key = "com.zdsoft.finance.parameter.deleteSysLabel")
    @ResponseBody
    public String deleteSysLabel(String id, String jsoncallback) {
        ResponseMsg msg = new ResponseMsg();
        try {
            sysLabelService.deleteLabel(id);
            msg.setResultStatus(ResponseMsg.SUCCESS);
        } catch (Exception e) {
            logger.error("删除标签出错",e);
            e.printStackTrace();
            msg.setMsg(e.getMessage());
            msg.setResultStatus(ResponseMsg.APP_ERROR);
        }
        return ObjectHelper.objectToJson(msg, jsoncallback);
    }

}
