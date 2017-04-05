package com.zdsoft.finance.businesssetting.controller;

import com.zdsoft.essential.client.service.CED;
import com.zdsoft.essential.dto.emp.EmpDto;
import com.zdsoft.finance.businesssetting.entity.CostItem;
import com.zdsoft.finance.businesssetting.service.CostItemService;
import com.zdsoft.finance.businesssetting.vo.CostItemVo;
import com.zdsoft.finance.common.exception.BusinessException;
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
import java.sql.Timestamp;
import java.util.*;


/**
 * 版权所有：重庆正大华日软件有限公司
 *
 * @author LiaoGuoWei
 * @version V1.0
 * @title CostItemController.java
 * @className CostItemController
 * @description 费用项管理controller
 * @create 2017/2/27 14:49
 **/
@RequestMapping("/paramter")
@Controller
public class CostItemController extends BaseController {
    @Autowired
    private CostItemService costItemService;
    @Autowired
    private CED ced;

    /**
     * @param
     * @return org.springframework.web.servlet.ModelAndView
     * @throws
     * @Title: initCostItem
     * @Description: 费用项管理初始化
     * @author liaoguowei
     */
    @RequestMapping("/initCostItem")
    @UriKey(key = "com.zdsoft.finance.parameter.initCostItem")
    @Menu(resource = "com.zdsoft.finance.parameter.initCostItem", label = "费用项管理", group = "businessSetting", order = 1)
    public ModelAndView initCostItem() {
        return new ModelAndView("businesssetting/costitem_list");
    }


    /**
     * @Title: getCostItem
     * @Description: 分页查询数据
     * @author liaoguowei
     * @param costItemVo 查询条件
     * @param jsoncallback json回调
     * @param pageable 分页参数
     * @return java.lang.String
     * @throws
     */
    @RequestMapping(value = "/costItem",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @UriKey(key = "com.zdsoft.finance.parameter.getCostItem")
    @ResponseBody
    public String getCostItem(CostItemVo costItemVo ,String jsoncallback, PageRequest pageable) {
        ResponseMsg msg = new ResponseMsg();
        try {
            // 分页抵押权人
            Page<CostItem> CostItemPage = costItemService.findCostItemByCondition(pageable, costItemVo.toPo());
            msg.setResultStatus(ResponseMsg.SUCCESS);
            msg.setTotal(CostItemPage.getTotalRows());
            msg.setRows(CostItemPage.getRecords());
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("查询费用项管理列表出错",e);
            msg.setMsg(e.getMessage());
            msg.setResultStatus(ResponseMsg.APP_ERROR);
        }

        return ObjectHelper.objectToJson(msg, jsoncallback);
    }

    /**
     * @param costItem
     * @param jsoncallback
     * @param pageable
     * @return java.lang.String
     * @throws
     * @Title: updateCostItem
     * @Description: 更新或添加费用项
     * @author liaoguowei
     */
    @RequestMapping("/updateCostItem")
    @UriKey(key = "com.zdsoft.finance.parameter.updateCostItem")
    @ResponseBody
    public String updateCostItem(CostItem costItem, String jsoncallback, PageRequest pageable) {
        ResponseMsg msg = new ResponseMsg();
        try {
            List<CostItem> costItems = costItemService.findByName(costItem.getCostItemName());
            if (costItems != null && costItems.size() > 0 && !costItems.get(0).getId().equals(costItem.getId())) {
                msg.setMsg("操作失败！费用项名称不能重复.");
                msg.setResultStatus(ResponseMsg.APP_ERROR);
            } else {
                costItem.setUpdateBy(ced.getLoginUser().getEmpCd());
                List<EmpDto> emp = ced.findEmployeesByCodes(new String[]{costItem.getUpdateBy()});
                costItem.setEmpName(emp.get(0).getEmpNm());
                costItem.setUpdateTime(new Timestamp(new Date().getTime()));
                costItemService.saveOrUpdateEntity(costItem);
                msg.setMsg("操作成功！");
                msg.setResultStatus(ResponseMsg.SUCCESS);
            }

        } catch (Exception e) {
            logger.error("更新或保存费用项出错", e);
            e.printStackTrace();
            msg.setMsg("操作失败！");
            msg.setResultStatus(ResponseMsg.APP_ERROR);
        }
        return ObjectHelper.objectToJson(msg, jsoncallback);
    }

    /**
     * @param costItem
     * @param jsoncallback
     * @param pageable
     * @return java.lang.String
     * @throws
     * @Title: deleteCostItem
     * @Description: 删除费用项
     * @author liaoguowei
     */
    @RequestMapping("/deleteCostItem")
    @UriKey(key = "com.zdsoft.finance.parameter.deleteCostItem")
    @ResponseBody
    public String deleteCostItem(CostItem costItem, String jsoncallback, PageRequest pageable) {
        ResponseMsg msg = new ResponseMsg();
        try {
            costItemService.deleteCostItem(costItem.getId());
            msg.setMsg("操作成功！");
            msg.setResultStatus(ResponseMsg.SUCCESS);

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("删除费用项出错", e);
            msg.setMsg("操作失败！");
            msg.setResultStatus(ResponseMsg.APP_ERROR);
        }
        return ObjectHelper.objectToJson(msg, jsoncallback);
    }


    /**
     * @param jsoncallback
     * @return java.lang.String
     * @throws
     * @Title: findAllEffectiveItemSimpleCode
     * @Description: 查找所有有效的费用项自定义simpleCode
     * @author liaoguowei
     */
    @RequestMapping(value = "/findAllEffectiveItemSimpleCode")
    @UriKey(key = "com.zdsoft.finance.parameter.findAllEffectiveItemSimpleCode")
    @ResponseBody
    public String findAllEffectiveItemSimpleCode(String jsoncallback) {
        List<Map<String, Object>> returnData = new ArrayList<Map<String, Object>>();
        try {
            List<CostItem> sourceData = this.costItemService.findAllEffectiveItem();
            for (CostItem temp : sourceData) {
                Map<String, Object> rowData = new HashMap<String, Object>();
                rowData.put("code", temp.getCostItemCode());
                rowData.put("text", temp.getCostItemName());
                returnData.add(rowData);
            }
        } catch (BusinessException e) {
            logger.error("查找所有有效费用项出错！", e);
            e.printStackTrace();
        }
        return ObjectHelper.objectToJson(returnData, jsoncallback);
    }

    /**
     * @param request
     * @param jsoncallback
     * @param pageable
     * @return java.lang.String
     * @throws
     * @Title: getBasicMessage
     * @Description: 获取一个费用项的基本信息
     * @author liaoguowei
     */
    @RequestMapping("/getCostitemBasicMessage")
    @UriKey(key = "com.zdsoft.finance.parameter.getCostitemBasicMessage")
    @ResponseBody
    public String getBasicMessage(HttpServletRequest request, String jsoncallback, PageRequest pageable) {
        ResponseMsg msg = new ResponseMsg();
        try {
            Map<String, Object> result = new HashMap<String, Object>();
            String costItemCode=this.costItemService.buildingCostItemCode();
            result.put("code", costItemCode);
            result.put("empName", ced.getLoginUser().getEmpNm());
            msg.setResultStatus(ResponseMsg.SUCCESS);
            msg.setOptional(result);
        } catch (Exception e) {
            logger.error("获取费用项基本信息出错", e);
            e.printStackTrace();
            msg.setMsg(e.getMessage());
            msg.setResultStatus(ResponseMsg.APP_ERROR);
        }
        return ObjectHelper.objectToJson(msg, jsoncallback);
    }

}
