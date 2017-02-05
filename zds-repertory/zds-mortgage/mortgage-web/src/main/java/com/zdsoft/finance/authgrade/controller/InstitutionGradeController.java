package com.zdsoft.finance.authgrade.controller;

import com.zdsoft.essential.client.service.CED;
import com.zdsoft.essential.dto.emp.EmpDto;
import com.zdsoft.finance.authgrade.entity.AuthGrade;
import com.zdsoft.finance.authgrade.entity.InstitutionGrade;
import com.zdsoft.finance.authgrade.entity.OrgAuthConn;
import com.zdsoft.finance.authgrade.entity.OrgIntermediate;
import com.zdsoft.finance.authgrade.service.AuthGradeService;
import com.zdsoft.finance.authgrade.service.InstitutionGradeService;
import com.zdsoft.finance.authgrade.service.OrgAuthConnService;
import com.zdsoft.finance.authgrade.vo.AuthGradeVo;
import com.zdsoft.finance.authgrade.vo.OrgAuthConnVo;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 机构评级controller
 *
 * @author LiaoGuoWei
 * @create 2017-01-09 9:52
 **/
@Controller
@RequestMapping("/institutionGrade")
public class InstitutionGradeController extends BaseController {

    @Autowired
    private InstitutionGradeService institutionGradeService;

    @Autowired
    private CED CED;

    @Autowired
    private OrgAuthConnService orgAuthConnService;

    @Autowired
    private AuthGradeService authGradeService;

    /**
     * 机构平级页面
     *
     * @param modelAndView
     * @return
     */
    @RequestMapping(value = "/institutionGradePage")
    @UriKey(key = "com.zdsoft.finance.institutionGrade.institutionGradePage")
    @Menu(resource = "com.zdsoft.finance.institutionGrade.institutionGradePage", label = "机构评级", group = "businessSetting", order = 2)
    public ModelAndView institutionGradePage(ModelAndView modelAndView) {
        modelAndView.setViewName("authgrade/business_institutiongrade_list");
        try {
            EmpDto empDto = CED.getLoginUser();
            modelAndView.addObject("empDto", empDto);
        } catch (Exception e) {
            logger.error("机构平级页面获取当前登录人失败！", e);
            e.printStackTrace();
        }
        return modelAndView;
    }

    /**
     * 机构评级列表数据
     *
     * @param institutionCode
     * @param jsoncallback
     * @return
     */
    @RequestMapping(value = "/institutionGradeList")
    @UriKey(key = "com.zdsoft.finance.institutionGradeList")
    @ResponseBody
    public String institutionGradeList(PageRequest pageRequest, String institutionCode, String jsoncallback) {
        Map<String, Object> returnData = new HashMap<String, Object>();
        try {
            //写入机构数据
            this.institutionGradeService.writeDataToOrg();
            Page<Map<String, Object>> sourceData = this.institutionGradeService.findPageByCondition(pageRequest, institutionCode);
            returnData.put("total", sourceData.getTotalRows());
            returnData.put("rows", sourceData.getRecords());
        } catch (Exception e) {
            logger.error("查询机构评级列表数据出错！", e);
            e.printStackTrace();
        }
        return ObjectHelper.objectToJson(returnData, jsoncallback);
    }

    /**
     * 转跳到编辑页面
     *
     * @param modelAndView
     * @param institutionCode
     * @param institutionName
     * @return
     */
    @RequestMapping(value = "/jumpEditPage")
    @UriKey(key = "com.zdsoft.finance.jumpEditPage")
    public ModelAndView jumpEditPage(ModelAndView modelAndView, String institutionCode, String institutionName, String type) {
        modelAndView.setViewName("authgrade/business_institutiongrade_edit");
        try {
            EmpDto empDto = CED.getLoginUser();
            modelAndView.addObject("empDto", empDto);

            OrgAuthConn sourceData = this.orgAuthConnService.findByOrgCode(institutionCode);
            List<AuthGrade> authGradeList=this.authGradeService.findByGradeCode(sourceData.getOriginalGradeCode());
            if(ObjectHelper.isEmpty(authGradeList)){
                modelAndView.addObject("isAuth","false");
            }
            modelAndView.addObject("orgAuthConn", sourceData);
        } catch (Exception e) {
            logger.error("转跳到编辑页面出错", e);
            e.printStackTrace();
        }
        modelAndView.addObject("institutionCode", institutionCode);
        modelAndView.addObject("institutionName", institutionName);
        modelAndView.addObject("type", type);
        return modelAndView;
    }

    /**
     * 机构自定义下拉框
     *
     * @param jsoncallback
     * @return
     */
    @RequestMapping(value = "/findAllOrgSimpleCode")
    @UriKey(key = "com.zdsoft.finance.findAllOrgSimpleCode")
    @ResponseBody
    public String findAllOrgSimpleCode(String jsoncallback) {
        List<Map<String, Object>> returnData = new ArrayList<Map<String, Object>>();
        try {
            //写入机构数据
            this.institutionGradeService.writeDataToOrg();
            List<OrgIntermediate> sourceData = this.institutionGradeService.findAllOrg();
            for (OrgIntermediate temp : sourceData) {
                Map<String, Object> rowData = new HashMap<String, Object>();
                rowData.put("id", temp.getOrgId());
                rowData.put("text", temp.getOrgNm());
                returnData.add(rowData);
            }
        } catch (Exception e) {
            logger.error("获取机构下拉框出错", e);
            e.printStackTrace();
        }

        return ObjectHelper.objectToJson(returnData, jsoncallback);
    }

    @RequestMapping(value = "/findHanderSimpleCode")
    @UriKey(key = "com.zdsoft.finance.findHanderSimpleCode")
    @ResponseBody
    public String findHanderSimpleCode(String jsoncallback) {
        List<Map<String, Object>> returnData = new ArrayList<Map<String, Object>>();
        try {
            List<Map<String, Object>> sourceData = this.institutionGradeService.findAllHander();
            for (Map<String, Object> temp : sourceData) {
                Map<String, Object> rowData = new HashMap<String, Object>();
                rowData.put("id", temp.get("handerCode"));
                rowData.put("text", temp.get("handerName"));
                returnData.add(rowData);
            }
        } catch (Exception e) {
            logger.error("查询所有操作人出错", e);
            e.printStackTrace();
        }
        return ObjectHelper.objectToJson(returnData, jsoncallback);
    }

    /**
     * 更改历史数据
     *
     * @param jsoncallback
     * @return
     */
    @RequestMapping(value = "/findHistoryData")
    @UriKey(key = "com.zdsoft.finance.findHistoryData")
    @ResponseBody
    public String findHistoryData(PageRequest pageRequest, String jsoncallback,String institutionCode) {
        Map<String, Object> returnData = new HashMap<String, Object>();
        try {
            Page<InstitutionGrade> institutionGradePage = this.institutionGradeService.findPageList(pageRequest, institutionCode);
            returnData.put("total", institutionGradePage.getTotalRows());
            returnData.put("rows", institutionGradePage.getRecords());
        } catch (BusinessException e) {
            logger.error("查找历史列表数据出错", e);
            e.printStackTrace();
        }
        return ObjectHelper.objectToJson(returnData, jsoncallback);
    }

    /**
     * 保存或更新机构平级
     *
     * @param orgAuthConnVo
     * @return
     */
    @RequestMapping(value = "/saveOrUpdateInstitution", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @UriKey(key = "com.zdsoft.finance.saveOrUpdateInstitution")
    @ResponseBody
    public ResponseMsg saveOrUpdateInstitution(OrgAuthConnVo orgAuthConnVo) {
        ResponseMsg msg = new ResponseMsg();
        try {
            OrgAuthConn sourceData = this.orgAuthConnService.saveOrUpdateOrgAuthConn(orgAuthConnVo.toPo());
            msg.setResultStatus(ResponseMsg.SUCCESS);
        } catch (Exception e) {
            msg.setResultStatus(ResponseMsg.APP_ERROR);
            msg.setMsg(e.getMessage());
            logger.error("机构评级保存或更新出错", e);
            e.printStackTrace();
        }
        return msg;
    }

    /**
     * 机构授权查询页面
     *
     * @param modelAndView
     * @return
     */
    @RequestMapping(value = "/institutionAuthPage")
    @UriKey(key = "com.zdsoft.finance.institutionAuthPage")
    @Menu(resource = "com.zdsoft.finance.institutionGrade.institutionAuthPage", label = "机构授权查询", group = "businessSetting", order = 3)
    public ModelAndView institutionAuthPage(ModelAndView modelAndView) {
        modelAndView.setViewName("authgrade/business_institutionauth_list");
        return modelAndView;
    }


    /**
     * 机构授权查询列表数据
     *
     * @param jsoncallback
     * @return
     */
    @RequestMapping(value = "/institutionAuthList")
    @UriKey(key = "com.zdsoft.finance.institutionAuthList")
    @ResponseBody
    public String institutionAuthList(PageRequest pageRequest, AuthGradeVo authGradeVo, String jsoncallback) {
        Map<String, Object> returnData = new HashMap<String, Object>();
        try {
            Page<AuthGrade> sourceData = this.institutionGradeService.findInstitutionAuthPage(pageRequest, authGradeVo.toPo());
            returnData.put("total", sourceData.getTotalRows());
            returnData.put("rows", sourceData.getRecords());
        } catch (Exception e) {
            logger.error("机构授权查询出错", e);
            e.printStackTrace();
        }
        return ObjectHelper.objectToJson(returnData, jsoncallback);
    }
}
