package com.zdsoft.finance.casemanage.material.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zdsoft.finance.casemanage.material.service.CaseMaterialListAttaLogService;
import com.zdsoft.finance.casemanage.material.vo.CaseMaterialListAttaLogVo;
import com.zdsoft.finance.common.base.QueryObj;
import com.zdsoft.finance.common.utils.ParameterUtil;
import com.zdsoft.framework.core.common.dto.ResponseMsg;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.PageRequest;
import com.zdsoft.framework.core.commweb.annotation.UriKey;
import com.zdsoft.framework.core.commweb.component.BaseController;

/**
 * 版权所有：重庆正大华日软件有限公司
 * 
 * @Title:CaseMaterialListAttaLogController.java
 * @Package:com.zdsoft.finance.casemanage.material.controller
 * @Description:案件资料清单附件日志控制器
 * @author: gonght
 * @date:2017年1月16日 上午9:53:00
 * @version:v1.0
 */
@Controller
@RequestMapping("/caseMaterialAttaLog")
public class CaseMaterialListAttaLogController extends BaseController {

    @Autowired
    private CaseMaterialListAttaLogService caseMaterialListAttaLogService;
    
    /**
     * 
     * 案件资料清单附件日志分页列表入口
     *
     * @author gonght
     * @param request
     * @param caseApplyId
     * @param processLink
     * @return
     */
    @RequestMapping(value = "/initCaseMaterialAttaLogListPage")
    @UriKey(key = "com.zdsoft.finance.caseMaterial.initCaseMaterialAttaLogListPage")
    public ModelAndView initCaseMaterialAttaLogListPage(HttpServletRequest request, String caseApplyId, String processLink) {
        logger.info("-----------进入案件资料清单附件日志分页列表入口页面----------------");
        logger.info("参数如下-->caseApplyId={},processLink={}", caseApplyId, processLink);
        ModelMap model = new ModelMap();
        model.put("caseApplyId", caseApplyId);//那个案件
        model.put("processLink", processLink);//那个环节
        return new ModelAndView("casemanage/material/case_material_attalog_list", model);
    }
    
    /**
     * 案件资料清单附件日志分页列表分页查询
     *
     * @author gonght
     * @param request
     *            请求数据对象
     * @param pageRequest
     *            分页对象
     * @return
     */
    @RequestMapping("/queryCaseMaterialAttaLogList")
    @UriKey(key = "com.zdsoft.finance.caseMaterial.queryCaseMaterialAttaLogList")
    @ResponseBody
    public Map<String, Object> queryCaseMaterialAttaLogList(HttpServletRequest request, PageRequest pageRequest) {

        // 获取页面封装的查询参数
        List<QueryObj> queryObjs = ParameterUtil.getQueryObjByParameter(request, new String[] { "cml", "cmla","cmlal" });

        logger.info("分页请求的参数如下：");
        for (QueryObj queryObj : queryObjs) {
            logger.info(queryObj.getElement());
            logger.info(queryObj.getObj());
            logger.info(queryObj.getOperator());
            logger.info(queryObj.getValue().toString());
        }

        Map<String, Object> mapObj = new HashMap<String, Object>();
        try {
            Page<Map<String, Object>> page = caseMaterialListAttaLogService.findCaseMaterialAttaLogList(pageRequest, queryObjs);
            // for(Map<String,Object> map:page.getRecords()){
            // //有特殊处理的再这里处理，没有直接返回
            // Object amount=map.get("amount");
            // map.put("amount", AmountConversionUtil.convertToWYuan(BigDecimal.valueOf(Double.valueOf(amount+""))));
            // }
            mapObj.put("total", page.getTotalRows());
            mapObj.put("rows", page.getRecords());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mapObj;
    }
    
    /**
     * 
     * 保存案件资料清单附件日志
     *
     * @author gonght
     * @param request
     * @param caseMaterialListAttaLogVo 案件资料清单附件日志vo
     * @return
     */
    @RequestMapping("/saveCaseMaterialListAttaLog")
    @UriKey(key = "com.zdsoft.finance.caseMaterial.saveCaseMaterialListAttaLog")
    @ResponseBody
    public ResponseMsg saveCaseMaterialListAttaLog(HttpServletRequest request, CaseMaterialListAttaLogVo caseMaterialListAttaLogVo) {
        logger.info("保存案件资料清单附件日志");
        ResponseMsg msg = new ResponseMsg();
        try {
            logger.info("资料清单附件id={}", caseMaterialListAttaLogVo.getCaseMaterialListAttaId());
            //TODO:下载日志保存

            msg.setResultStatus(ResponseMsg.SUCCESS);
            msg.setMsg("保存案件资料清单附件日志成功！");
        } catch (Exception e) {
            logger.error("保存案件资料清单附件日志失败！");
            msg.setResultStatus(ResponseMsg.APP_ERROR);
            msg.setMsg(e.getMessage());
            e.printStackTrace();
        }
        return msg;
    }
}
