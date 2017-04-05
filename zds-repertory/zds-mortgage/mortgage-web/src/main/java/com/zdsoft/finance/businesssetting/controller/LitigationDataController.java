package com.zdsoft.finance.businesssetting.controller;

import com.zdsoft.essential.client.service.CED;
import com.zdsoft.essential.dto.basedata.AttachmentDto;
import com.zdsoft.finance.businesssetting.entity.LitigationData;
import com.zdsoft.finance.businesssetting.service.LitigationDataService;
import com.zdsoft.finance.businesssetting.vo.LitigationDataVo;
import com.zdsoft.framework.core.common.dto.ResponseMsg;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.PageRequest;
import com.zdsoft.framework.core.common.util.ObjectHelper;
import com.zdsoft.framework.core.commweb.annotation.UriKey;
import com.zdsoft.framework.core.commweb.component.BaseController;
import com.zdsoft.framework.cra.annotation.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @title LitigationDataController.java
 * @className LitigationDataController
 * @description 诉讼资料配置controller
 * @author LiaoGuoWei
 * @create 2017/3/3 15:16
 * @version V1.0
 **/
@RequestMapping("/paramter")
@Controller
public class LitigationDataController extends BaseController {
    @Autowired
    private LitigationDataService litigationDataService;

    @Autowired
    private CED CED;

    /**
     * @Title: initLitigationData
     * @Description: 诉讼资料配置页面
     * @author liaoguowei
     * @param
     * @return org.springframework.web.servlet.ModelAndView
     * @throws
     */
    @RequestMapping("/initLitigationData")
    @UriKey(key = "com.zdsoft.finance.parameter.initLitigationData")
    @Menu(resource = "com.zdsoft.finance.parameter.initLitigationData", label = "诉讼资料配置", group = "businessSetting", order = 1)
    public ModelAndView initLitigationData() {
        return new ModelAndView("businesssetting/litigationdata_list");
    }

    /**
     * @Title: getLitigationData
     * @Description: 获取诉讼资料列表
     * @author liaoguowei
     * @param data 查询条件
     * @param jsoncallback json回调
     * @param pageable 分页参数
     * @return java.lang.String
     * @throws
     */
    @RequestMapping("/litigationData")
    @UriKey(key = "com.zdsoft.finance.parameter.getLitigationData")
    @ResponseBody
    public String getLitigationData(LitigationDataVo data, String jsoncallback, PageRequest pageable) {
        ResponseMsg msg = new ResponseMsg();
        try {
            // 分页抵押权人
            Page<LitigationData> LitigationDataPage = litigationDataService.findLitigationDataByCondition(pageable, data.toPo());
            List<LitigationDataVo> returnList = new ArrayList<LitigationDataVo>();
            if (ObjectHelper.isNotEmpty(LitigationDataPage.getRecords())) {
                for (LitigationData temp : LitigationDataPage.getRecords()) {
                    LitigationDataVo vo = new LitigationDataVo(temp);
                    AttachmentDto attachmentDto = this.CED.findAttachmentById(temp.getAttachmentId());
                    if(ObjectHelper.isNotEmpty(attachmentDto)){
                        vo.setFilePath(ObjectHelper.isNotEmpty(attachmentDto.getFilePath())?attachmentDto.getFilePath():"");
                    }
                    returnList.add(vo);
                }
            }
            msg.setResultStatus(ResponseMsg.SUCCESS);
            msg.setTotal(LitigationDataPage.getTotalRows());
            msg.setRows(returnList);
        } catch (Exception e) {
            logger.error("获取诉讼资料列表出错",e);
            e.printStackTrace();
            msg.setResultStatus(ResponseMsg.APP_ERROR);
        }

        return ObjectHelper.objectToJson(msg, jsoncallback);
    }

    /**
     * @Title: updateLitigationData
     * @Description: 更新标签
     * @author liaoguowei
     * @param label
     * @param jsoncallback
     * @param pageable
     * @return java.lang.String
     * @throws
     */
    @RequestMapping("/updateLitigationData")
    @UriKey(key = "com.zdsoft.finance.parameter.updateLitigationData")
    @ResponseBody
    public String updateLitigationData(LitigationData label, String jsoncallback, PageRequest pageable) {
        ResponseMsg msg = new ResponseMsg();
        try {
            LitigationData litigationData = litigationDataService.saveOrUpdateLitiData(label);
            msg.setResultStatus(ResponseMsg.SUCCESS);

        } catch (Exception e) {
            e.printStackTrace();
            msg.setMsg(e.getMessage());
            msg.setResultStatus(ResponseMsg.APP_ERROR);
            logger.error("保存诉讼资料配置出错",e);
        }
        return ObjectHelper.objectToJson(msg, jsoncallback);
    }

    /**
     * @Title: deleteLitigationData
     * @Description: 删除
     * @author liaoguowei
     * @param label
     * @param jsoncallback
     * @param pageable
     * @return java.lang.String
     * @throws
     */
    @RequestMapping("/deleteLitigationData")
    @UriKey(key = "com.zdsoft.finance.parameter.deleteLitigationData")
    @ResponseBody
    public String deleteLitigationData(LitigationData label, String jsoncallback, PageRequest pageable) {
        ResponseMsg msg = new ResponseMsg();
        try {
            litigationDataService.deleteLitigationData(label.getId());
            msg.setMsg("操作成功！");
            msg.setResultStatus(ResponseMsg.SUCCESS);

        } catch (Exception e) {
            logger.error("删除诉讼资料出错",e);
            msg.setMsg("操作失败！");
            msg.setResultStatus(ResponseMsg.APP_ERROR);
        }
        return ObjectHelper.objectToJson(msg, jsoncallback);
    }

}
