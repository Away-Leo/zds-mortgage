package com.zdsoft.finance.businesssetting.controller;

import com.zdsoft.essential.client.service.CED;
import com.zdsoft.essential.dto.org.OrgDto;
import com.zdsoft.finance.businesssetting.vo.MortOwnerVo;
import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.finance.businesssetting.entity.MortOwner;
import com.zdsoft.finance.businesssetting.service.MortOwnerService;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @title MortOwnerController.java
 * @className MortOwnerController
 * @description 抵押权人管理controller
 * @author LiaoGuoWei
 * @create 2017/3/3 15:18
 * @version V1.0
 **/
@RequestMapping("/mortOwner")
@Controller
public class MortOwnerController extends BaseController {

    @Autowired
    private CED CED;

    @Autowired
    private MortOwnerService mortOwnerService;


    /**
     * @Title: initPerson
     * @Description: 抵押权人页面
     * @author liaoguowei
     * @param modelAndView
     * @return org.springframework.web.servlet.ModelAndView
     * @throws
     */
    @RequestMapping("/mortOwnerPage")
    @UriKey(key = "com.zdsoft.finance.mortOwner.mortOwnerPage")
    @Menu(resource = "com.zdsoft.finance.mortOwner.mortOwnerPage", label = "抵押权人管理", group = "businessSetting", order = 1)
    public ModelAndView mortOwnerPage(ModelAndView modelAndView) {
        modelAndView.setViewName("businesssetting/business_mortowner_list");
        return modelAndView;
    }
    /**
     * @Title: mortOwnerListData
     * @Description: 抵押权人列表数据
     * @author liaoguowei
     * @param beOrgCode
     * @param ownerName
     * @param jsoncallback
     * @param pageRequest
     * @return java.lang.String
     * @throws
     */
    @RequestMapping("/mortOwnerListData")
    @UriKey(key = "com.zdsoft.finance.mortOwner.mortOwnerListData")
    @ResponseBody
    public String mortOwnerListData(String beOrgCode,String ownerName,String jsoncallback,PageRequest pageRequest){
        Map<String,Object> returnData=new HashMap<String,Object>();
        Page<MortOwner> sourceData=this.mortOwnerService.findByCondition(ownerName,beOrgCode,pageRequest);
        List<MortOwnerVo> returnListData=new ArrayList<MortOwnerVo>();
        if(ObjectHelper.isNotEmpty(sourceData.getRecords())){
            for(MortOwner temp:sourceData.getRecords()){
                MortOwnerVo vo=new MortOwnerVo(temp);
                try{
                    String beOrgName=this.CED.loadOrganizationByCode(temp.getBeOrgCode()).getOrgNm();
                    vo.setBeOrgName(beOrgName);
                }catch (Exception e){
                    logger.error("根据ID查找单位出错",e);
                    e.printStackTrace();
                }
                returnListData.add(vo);
            }
            returnData.put("total",sourceData.getTotalRows());
            returnData.put("rows",returnListData);
        }
        return ObjectHelper.objectToJson(returnData,jsoncallback);
    }

    /**
     * @Title: orgSimpleCode
     * @Description: 机构自定义下拉框数据
     * @author liaoguowei
     * @param jsoncallback
     * @return java.lang.String
     * @throws
     */
    @RequestMapping("/orgSimpleCode")
    @UriKey(key = "com.zdsoft.finance.mortOwner.orgSimpleCode")
    @ResponseBody
    public String orgSimpleCode(String jsoncallback){
        String returnData="";
        try{
            List<OrgDto> orgDtoList=this.CED.queryAllCompany();
            List<Map<String,Object>> returnDataList=new ArrayList<Map<String, Object>>();
            for(OrgDto temp:orgDtoList){
                Map<String,Object>rowData=new HashMap<String,Object>();
                rowData.put("id",temp.getOrgCd());
                rowData.put("text",temp.getOrgNm());
                returnDataList.add(rowData);
            }
            returnData=ObjectHelper.objectToJson(returnDataList,jsoncallback);
        }catch (Exception e){
            e.printStackTrace();
            logger.error("查询所有机构出错",e);
        }
        return returnData;
    }

    /**
     * @Title: saveOrUpdataMortOwner
     * @Description: 保存或修改抵押权人
     * @author liaoguowei
     * @param mortOwner
     * @return com.zdsoft.framework.core.common.dto.ResponseMsg
     * @throws
     */
    @RequestMapping("/saveOrUpdataMortOwner")
    @UriKey(key = "com.zdsoft.finance.mortOwner.saveOrUpdataMortOwner")
    @ResponseBody
    public ResponseMsg saveOrUpdataMortOwner(MortOwner mortOwner){
        ResponseMsg msg=new ResponseMsg();
        try{
            List<MortOwner> sourceData=this.mortOwnerService.findBeOrgCodeAndOwnerTypeCodeAndOwnerName(mortOwner);
            if(ObjectHelper.isNotEmpty(sourceData)&&sourceData.size()>0){
                if(ObjectHelper.isNotEmpty(mortOwner.getId())&&sourceData.get(0).getId().equalsIgnoreCase(mortOwner.getId())){
                    this.mortOwnerService.saveOrUpdateMortOwner(mortOwner);
                    msg.setResultStatus(ResponseMsg.SUCCESS);
                }else{
                    msg.setResultStatus(ResponseMsg.APP_ERROR);
                }
            }else{
                this.mortOwnerService.saveOrUpdateMortOwner(mortOwner);
                msg.setResultStatus(ResponseMsg.SUCCESS);
            }
        }catch (BusinessException e){
            e.printStackTrace();
            logger.error("保存或更新抵押权人出错",e);
            msg.setResultStatus(ResponseMsg.SYS_ERROR);
            msg.setMsg(e.getMessage());
        }
        return msg;
    }

    /**
     * @Title: deleteMortOwner
     * @Description: 逻辑删除
     * @author liaoguowei
     * @param id
     * @return com.zdsoft.framework.core.common.dto.ResponseMsg
     * @throws
     */
    @RequestMapping("/deleteMortOwner")
    @UriKey(key = "com.zdsoft.finance.mortOwner.deleteMortOwner")
    @ResponseBody
    public ResponseMsg deleteMortOwner(String id){
        ResponseMsg msg=new ResponseMsg();
        try{
            MortOwner mortOwner=this.mortOwnerService.deleteMortOwner(id);
            msg.setResultStatus(ResponseMsg.SUCCESS);
        }catch (BusinessException e){
            logger.error("删除抵押权人出错",e);
            e.printStackTrace();
            msg.setResultStatus(ResponseMsg.APP_ERROR);
            msg.setMsg(e.getMessage());
        }
        return msg;
    }
}
