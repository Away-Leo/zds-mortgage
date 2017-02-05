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

import com.zdsoft.finance.casemanage.material.entity.CaseMaterialListAtta;
import com.zdsoft.finance.casemanage.material.service.CaseMaterialListAttaLogService;
import com.zdsoft.finance.casemanage.material.service.CaseMaterialListAttaService;
import com.zdsoft.finance.casemanage.material.service.CaseMaterialListService;
import com.zdsoft.finance.casemanage.material.vo.CaseMaterialListAttaVo;
import com.zdsoft.finance.casemanage.material.vo.CaseMaterialListPageVo;
import com.zdsoft.finance.common.base.QueryObj;
import com.zdsoft.finance.common.base.impl.CustomCommon;
import com.zdsoft.finance.common.utils.ParameterUtil;
import com.zdsoft.framework.core.common.dto.ResponseMsg;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.PageRequest;
import com.zdsoft.framework.core.commweb.annotation.UriKey;
import com.zdsoft.framework.core.commweb.component.BaseController;

/**
 * 版权所有：重庆正大华日软件有限公司
 * 
 * @Title:CaseAttachmentController.java
 * @Package:com.zdsoft.finance.attachment
 * @Description:案件资料清单控制器
 * @author: gonght
 * @date:2017年1月15日 上午10:59:55
 * @version:v1.0
 */
@Controller
@RequestMapping("/caseMaterial")
public class CaseMaterialListController extends BaseController {

    @Autowired
    private CaseMaterialListService caseMaterialListService;

    @Autowired
    private CaseMaterialListAttaService caseMaterialListAttaService;

    @Autowired
    private CaseMaterialListAttaLogService caseMaterialListAttaLogService;

    /**
     * 案件资料清单测试入口
     *
     * @author gonght
     * @param caseApplyId
     *            案件Id
     * @return
     */
    @RequestMapping(value = "/test")
    @UriKey(key = "com.zdsoft.finance.caseMaterial.test")
    public ModelAndView test(String caseApplyId) {
        ModelMap model = new ModelMap();
        return new ModelAndView("casemanage/material/test", model);
    }

    /**
     * 案件资料清单附件列表(流程中用)
     *
     * @author gonght
     * @param request
     * @param caseApplyId
     *            案件Id
     * @param processLink
     *            流程环节(不同的环节显示的资料清单不一样)
     * @return
     */
    @RequestMapping(value = "/initCaseMaterialProcessListPage")
    @UriKey(key = "com.zdsoft.finance.caseMaterial.initCaseMaterialProcessListPage")
    public ModelAndView initCaseMaterialProcessListPage(HttpServletRequest request, String caseApplyId, String processLink) {
        logger.info("-----------进入 案件资料清单附件列表入口页面----------------");
        logger.info("参数如下-->caseApplyId={},processLink={}", caseApplyId, processLink);
        ModelMap model = new ModelMap();
        model.put("caseApplyId", caseApplyId);
        model.put("processLink", processLink);
        return new ModelAndView("casemanage/material/case_material_list", model);
    }

    /**
     * 案件资料清单附件列表分页查询(流程中用)
     *
     * @author gonght
     * @param request
     *            请求数据对象
     * @param pageRequest
     *            分页对象
     * @return
     */
    @RequestMapping("/queryCaseMaterialProcessList")
    @UriKey(key = "com.zdsoft.finance.caseMaterial.queryCaseMaterialProcessList")
    @ResponseBody
    public Map<String, Object> queryCaseMaterialProcessList(HttpServletRequest request, PageRequest pageRequest) {

        // 获取页面封装的查询参数
        List<QueryObj> queryObjs = ParameterUtil.getQueryObjByParameter(request, new String[] { "cml", "cmla" });

        logger.info("分页请求的参数如下：");
        for (QueryObj queryObj : queryObjs) {
            logger.info(queryObj.getElement());
            logger.info(queryObj.getObj());
            logger.info(queryObj.getOperator());
            logger.info(queryObj.getValue().toString());
        }

        Map<String, Object> mapObj = new HashMap<String, Object>();
        try {
            Page<Map<String, Object>> page = caseMaterialListService.findCaseMaterialList(pageRequest, queryObjs);
            // for(Map<String,Object> map:page.getRecords()){
            // //有特殊处理的再这里处理，没有直接返回
            // Object amount=map.get("amount");
            // map.put("amount", AmountConversionUtil.convertToWYuan(BigDecimal.valueOf(Double.valueOf(amount+""))));
            // }
            mapObj.put("total", page.getTotalRows());
            mapObj.put("rows",  CustomCommon.convert(CaseMaterialListPageVo.class, page.getRecords()));//由于oracle大写的问题，转成vo对象返回
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mapObj;
    }

    /**
     * 保存案件资料清单附件
     *
     * @author gonght
     * @param request
     * @param caseMaterialListAttaVo
     *            案件资料清单附件vo
     * @return
     */
    @RequestMapping("/saveCaseMaterialListAtta")
    @UriKey(key = "com.zdsoft.finance.caseMaterial.saveCaseMaterialListAtta")
    @ResponseBody
    public ResponseMsg saveCaseMaterialListAtta(HttpServletRequest request, CaseMaterialListAttaVo caseMaterialListAttaVo) {
        logger.info("保存案件资料清单附件");
        ResponseMsg msg = new ResponseMsg();
        try {
            logger.info("上传附件ids={}", caseMaterialListAttaVo.getAttachmentId());
            logger.info("附件类型代码={}", caseMaterialListAttaVo.getCaseMaterialListId());
            logger.info("关联资料清单id={}",caseMaterialListAttaVo.getCaseMaterialListId());
            
            CaseMaterialListAtta caseMaterialListAtta = caseMaterialListAttaVo.toPo();
            
            caseMaterialListAttaService.save(caseMaterialListAtta );

            msg.setResultStatus(ResponseMsg.SUCCESS);
            msg.setMsg("保存案件资料清单附件成功！");
        } catch (Exception e) {
            logger.error("保存案件资料清单附件失败！");
            msg.setResultStatus(ResponseMsg.APP_ERROR);
            msg.setMsg(e.getMessage());
            e.printStackTrace();
        }
        return msg;
    }
    
    //TODO 预览页面跳转
    
    //TODO 批量下载处理
    
    //TODO 查看下载日志页面跳转
    
    //TODO 下载日志分页查询
}
