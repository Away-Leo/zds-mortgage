package com.zdsoft.finance.businesssetting.controller;

import com.zdsoft.essential.client.service.CED;
import com.zdsoft.essential.dto.emp.EmpDto;
import com.zdsoft.finance.businesssetting.entity.ExceptMatter;
import com.zdsoft.finance.businesssetting.service.ExceptMatterService;
import com.zdsoft.finance.businesssetting.vo.ExceptMatterVo;
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

import java.util.ArrayList;
import java.util.List;


/**
 * 版权所有：重庆正大华日软件有限公司
 * @title ExceptMatterController.java
 * @className ExceptMatterController
 * @description 特批事项管理controller
 * @author LiaoGuoWei
 * @create 2017/2/16 18:33
 * @version V1.0
 **/
@RequestMapping("/exceptMatter")
@Controller
public class ExceptMatterController extends BaseController {
    @Autowired
    private ExceptMatterService exceptMatterService;
    @Autowired
    private CED CED;

    /**
     * @Title: initExceptMatter
     * @Description: 特批事项管理初始化
     * @author liaoguowei
     * @return org.springframework.web.servlet.ModelAndView
     */
    @RequestMapping("/initExceptMatter")
    @UriKey(key = "com.zdsoft.finance.parameter.initExceptMatter")
    @Menu(resource = "com.zdsoft.finance.parameter.initExceptMatter", label = "特批事项管理", group = "businessSetting", order = 1)
    public ModelAndView initExceptMatter() {
        return new ModelAndView("businesssetting/exceptmatter_list");
    }

    /**
     * @Title: getExceptMatter
     * @Description: 特批事项列表
     * @author liaoguowei
     * @param exceptMatterVo 查询条件
     * @param jsoncallback json回调
     * @param pageable 分页参数
     * @return java.lang.String
     * @throws
     */
    @RequestMapping("/exceptMatter")
    @UriKey(key = "com.zdsoft.finance.parameter.getExceptMatter")
    @ResponseBody
    public String getExceptMatter(ExceptMatterVo  exceptMatterVo, String jsoncallback, PageRequest pageable) {
        ResponseMsg msg = new ResponseMsg();
        try {
            // 分页特批事项
            Page<ExceptMatter> exceptMatterPage = exceptMatterService.findExceptMatterByCondition(pageable, exceptMatterVo.toPo());
            List<ExceptMatterVo> exceptMatterVoList = new ArrayList<ExceptMatterVo>();
            //将数据转换为VO
            if (ObjectHelper.isNotEmpty(exceptMatterPage) && ObjectHelper.isNotEmpty(exceptMatterPage.getRecords())) {
                for (ExceptMatter temp : exceptMatterPage.getRecords()) {
                    ExceptMatterVo vo = new ExceptMatterVo(temp);
                    try {
                        //定义操作人 通过接口查询操作人并组装返回数据
                        EmpDto handelr = CED.loadEmployeeByCode(temp.getUpdateBy());
                        if (ObjectHelper.isNotEmpty(handelr)) {
                            vo.setHandelrName(ObjectHelper.isNotEmpty(handelr.getEmpNm()) ? handelr.getEmpNm() : "");
                        }
                    } catch (Exception e) {
                        logger.error("特批事项查询列表组装操作人出错", e);
                    }
                    exceptMatterVoList.add(vo);
                }
            }
            msg.setResultStatus(ResponseMsg.SUCCESS);
            msg.setTotal(exceptMatterPage.getTotalRows());
            msg.setRows(exceptMatterVoList);
        } catch (Exception e) {
            msg.setMsg(e.getMessage());
            msg.setResultStatus(ResponseMsg.APP_ERROR);
            e.printStackTrace();
            logger.error("查询特批事项出错！",e);
        }
        return ObjectHelper.objectToJson(msg, jsoncallback);
    }

    /**
     * @Title: updateExceptMatter
     * @Description: 更新
     * @author liaoguowei
     * @param exceptMatter
     * @param jsoncallback
     * @param pageable
     * @return java.lang.String
     * @throws
     */
    @RequestMapping(value = "/updateExceptMatter",produces = MediaType.APPLICATION_JSON_UTF8_VALUE )
    @UriKey(key = "com.zdsoft.finance.parameter.updateExceptMatter")
    @ResponseBody
    public String updateExceptMatter(ExceptMatter exceptMatter, String jsoncallback, PageRequest pageable) {
        ResponseMsg msg = new ResponseMsg();
        try {
            List<ExceptMatter> sourceData=this.exceptMatterService.findByTypeAndName(exceptMatter);
            if(ObjectHelper.isNotEmpty(sourceData)){
                if(exceptMatter.getId().equalsIgnoreCase(sourceData.get(0).getId())){
                    exceptMatterService.saveOrUpdateExceptMatter(exceptMatter);
                    msg.setResultStatus(ResponseMsg.SUCCESS);
                }else{
                    msg.setResultStatus(ResponseMsg.APP_ERROR);
                }
            }else{
                exceptMatterService.saveOrUpdateExceptMatter(exceptMatter);
                msg.setResultStatus(ResponseMsg.SUCCESS);
            }
        } catch (Exception e) {
            e.printStackTrace();
            msg.setMsg(e.getMessage());
            msg.setResultStatus(ResponseMsg.SYS_ERROR);
            logger.error("更新特批事项出错",e);
        }
        return ObjectHelper.objectToJson(msg, jsoncallback);
    }

        /**
         * @param jsoncallback
         * @return java.lang.String
         * @throws
         * @Title: buildingCode
         * @Description: 生成特批事项编号
         * @author liaoguowei
         */
        @RequestMapping(value = "/buildingCode")
        @UriKey(key = "com.zdsoft.finance.parameter.buildingCode")
        @ResponseBody
        public String buildingCode(String jsoncallback) {
            ResponseMsg msg = new ResponseMsg();
            try {
                String sourceData = this.exceptMatterService.buildingCode();
                msg.setMsg(sourceData);
                msg.setResultStatus(ResponseMsg.SUCCESS);
            } catch (Exception e) {
                logger.error("特批事项生成编号出错", e);
                e.printStackTrace();
                msg.setResultStatus(ResponseMsg.APP_ERROR);
                msg.setMsg(e.getMessage());
            }
            return ObjectHelper.objectToJson(msg, jsoncallback);
        }
    /**
     * @Title: deleteExceptMatter
     * @Description: 删除特批事项
     * @author liaoguowei
     * @param exceptMatterId
     * @param jsoncallback
     * @param pageable
     * @return java.lang.String
     * @throws
     */
    @RequestMapping("/deleteExceptMatter")
    @UriKey(key = "com.zdsoft.finance.parameter.deleteExceptMatter")
    @ResponseBody
    public String deleteExceptMatter(String exceptMatterId, String jsoncallback, PageRequest pageable) {
        ResponseMsg msg = new ResponseMsg();
        try {
            exceptMatterService.deleteExceptMatter(exceptMatterId);
            msg.setResultStatus(ResponseMsg.SUCCESS);
        } catch (Exception e) {
            msg.setMsg(e.getMessage());
            msg.setResultStatus(ResponseMsg.APP_ERROR);
            e.printStackTrace();
            logger.error("删除特批事项出错",e);
        }
        return ObjectHelper.objectToJson(msg, jsoncallback);
    }

}
