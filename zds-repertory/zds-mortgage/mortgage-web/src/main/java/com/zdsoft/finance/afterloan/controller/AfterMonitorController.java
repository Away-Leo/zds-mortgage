package com.zdsoft.finance.afterloan.controller;

import com.zdsoft.essential.client.service.CED;
import com.zdsoft.essential.dto.emp.EmpDto;
import com.zdsoft.finance.afterloan.entity.AfterMonitor;
import com.zdsoft.finance.afterloan.service.AfterMonitorService;
import com.zdsoft.finance.afterloan.vo.AfterMonitorVo;
import com.zdsoft.framework.core.common.dto.ResponseMsg;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.PageRequest;
import com.zdsoft.framework.core.common.util.DateHelper;
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
import java.util.Date;
import java.util.List;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @title AfterMonitorController.java
 * @className AfterMonitorController
 * @description 贷后监控controller
 * @author LiaoGuoWei
 * @create 2017/3/6 17:44
 * @version V1.0
 **/
@Controller
@RequestMapping(value = "/afterMonitor",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class AfterMonitorController extends BaseController {

    @Autowired
    private AfterMonitorService afterMonitorService;

    @Autowired
    private CED CED;



    /**
     * @Title: afterMonitorPage
     * @Description: 贷后监控页面
     * @author liaoguowei
     * @param modelAndView
     * @return org.springframework.web.servlet.ModelAndView
     * @throws
     */
    @RequestMapping("/afterMonitorPage")
    @UriKey(key = "com.zdsoft.finance.afterloan.afterMonitor.afterMonitorPage")
    @Menu(resource = "com.zdsoft.finance.afterloan.afterMonitor.afterMonitorPage", label = "贷后监控", group = "afterloan", order = 1)
    public ModelAndView afterMonitorPage(ModelAndView modelAndView) {
        modelAndView.setViewName("afterloan/afterMonitor/afterMonitor_list");
        return modelAndView;
    }

    /**
     * @Title: afterMonitorListData
     * @Description: 贷后监控列表
     * @author liaoguowei
     * @param jsoncallback json回调
     * @param afterMonitorVo 贷后监控VO
     * @param page 分页参数
     * @return java.lang.String
     * @throws
     */
    @RequestMapping("/afterMonitorListData")
    @UriKey(key = "com.zdsoft.finance.afterLoan.afterMonitor.afterMonitorListData")
    @ResponseBody
    public String afterMonitorListData(PageRequest page,AfterMonitorVo afterMonitorVo,String jsoncallback){
        ResponseMsg msg=new ResponseMsg();
        try{
            Page<AfterMonitor> sourceData=this.afterMonitorService.findMonitorByCondition(page,afterMonitorVo.toPo());
            List<AfterMonitorVo> returnList=new ArrayList<AfterMonitorVo>();
            if(ObjectHelper.isNotEmpty(sourceData)&&ObjectHelper.isNotEmpty(sourceData.getRecords())&&sourceData.getRecords().size()>0){
                for(AfterMonitor temp:sourceData.getRecords()){
                    AfterMonitorVo vo=new AfterMonitorVo(temp);
                    returnList.add(vo);
                }
            }
            msg.setRows(returnList);
            msg.setTotal(sourceData.getTotalRows());
        }catch (Exception e){
            logger.error("贷后监控列表数据查询出错",e);
            msg.setMsg(e.getMessage());
            e.printStackTrace();
        }
        return ObjectHelper.objectToJson(msg,jsoncallback);
    }

    /**
     * @Title: initiativeSearchPage
     * @Description: 贷后监控主动查询页面
     * @author liaoguowei
     * @param ids 多个案件ID
     * @param modelAndView 视图
     * @param overAmount 逾期金额
     * @param overCount 逾期条数
     * @return org.springframework.web.servlet.ModelAndView
     * @throws
     */
    @RequestMapping(value = "/initiativeSearchPage")
    @UriKey(key = "com.zdsoft.finance.afterMonitor.initiativeSearchPage")
    public ModelAndView initiativeSearchPage(ModelAndView modelAndView,String ids,String overAmount,String overCount){
        modelAndView.setViewName("afterloan/afterMonitor/initiativeSearch_list");
        try{
            EmpDto emp=CED.getLoginUser();
            modelAndView.addObject("emp",emp);
            modelAndView.addObject("ids",ids);
            modelAndView.addObject("overAmount",overAmount);
            modelAndView.addObject("overCount",overCount);
            modelAndView.addObject("searchDate", DateHelper.dateToString(new Date(),DateHelper.DATE_SHORT_FORMAT));
        }catch (Exception e){
            logger.error("贷后监控主动查询出错",e);
            e.printStackTrace();
        }
        return modelAndView;
    }

    /**
     * @Title: findMonitorInitiActiveList
     * @Description: 贷后监控主动查询列表数据
     * @author liaoguowei
     * @param afterVo 查询条件
     * @param jsoncallback josn回调
     * @return java.lang.String
     * @throws
     */
    @RequestMapping(value = "/initiActiveList")
    @UriKey(key = "com.zdsoft.finance.afterMonitor.initiActiveList")
    @ResponseBody
    public String findMonitorInitiActiveList(AfterMonitorVo afterVo,String jsoncallback){
        ResponseMsg msg=new ResponseMsg();
        try{
            List<AfterMonitor> sourceData=this.afterMonitorService.findMonitorInitiActiveList(afterVo.toPo());
            List<AfterMonitorVo> returnList=new ArrayList<AfterMonitorVo>();
            if(ObjectHelper.isNotEmpty(sourceData)&&sourceData.size()>0){
                for(AfterMonitor temp:sourceData){
                    AfterMonitorVo vo=new AfterMonitorVo(temp);
                    returnList.add(vo);
                }
            }
            msg.setRows(returnList);
            msg.setResultStatus(ResponseMsg.SUCCESS);
        }catch (Exception e){
            logger.error("贷后监控主动查询，列表数据查询出错",e);
            e.printStackTrace();
            msg.setMsg(e.getMessage());
            msg.setResultStatus(ResponseMsg.APP_ERROR);
        }
        return ObjectHelper.objectToJson(msg,jsoncallback);
    }

    /**
     * @Title: doInitiativeSearch
     * @Description: 贷后监控主动查询
     * @author liaoguowei
     * @param ids 待查询案件数据
     * @return com.zdsoft.framework.core.common.dto.ResponseMsg
     * @throws
     */
    @RequestMapping(value = "/doInitiativeSearch")
    @UriKey(key = "com.zdsoft.finance.afterMonitor.doInitiativeSearch")
    @ResponseBody
    public ResponseMsg doInitiativeSearch(String ids,String impls){
        ResponseMsg msg=new ResponseMsg();
        try{
            this.afterMonitorService.doInitiActive(ids,impls);
            msg.setResultStatus(ResponseMsg.SUCCESS);
        }catch (Exception e){
            logger.error("贷后监控主动查询出错",e);
            e.printStackTrace();
            msg.setResultStatus(ResponseMsg.APP_ERROR);
            msg.setMsg(e.getMessage());
        }
        return msg;
    }

}
