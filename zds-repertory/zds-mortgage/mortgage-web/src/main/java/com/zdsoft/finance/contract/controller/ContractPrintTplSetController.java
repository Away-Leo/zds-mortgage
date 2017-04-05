package com.zdsoft.finance.contract.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.Response;

import org.hibernate.type.descriptor.java.DataHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.zdsoft.essential.client.service.CED;
import com.zdsoft.essential.dto.OrgTreeDto;
import com.zdsoft.essential.dto.basedata.AttachmentDto;
import com.zdsoft.finance.common.base.QueryObj;
import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.finance.common.service.CommonService;
import com.zdsoft.finance.common.utils.ParameterUtil;
import com.zdsoft.finance.contract.entity.BussPrintTplOrg;
import com.zdsoft.finance.contract.entity.BussPrintTplPage;
import com.zdsoft.finance.contract.entity.BussPrintTplProduct;
import com.zdsoft.finance.contract.entity.BussPrintTplSet;
import com.zdsoft.finance.contract.service.BussPrintTplOrgService;
import com.zdsoft.finance.contract.service.BussPrintTplPageService;
import com.zdsoft.finance.contract.service.BussPrintTplProductService;
import com.zdsoft.finance.contract.service.BussPrintTplSetService;
import com.zdsoft.finance.contract.service.ZfPrintTemplateInnerpageService;
import com.zdsoft.finance.contract.vo.BussPrintTplPageVo;

import com.zdsoft.finance.contract.vo.BussPrintTplSetVo;
import com.zdsoft.finance.product.entity.Product;
import com.zdsoft.finance.product.service.ProductService;
import com.zdsoft.framework.core.common.dto.ResponseMsg;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.PageRequest;
import com.zdsoft.framework.core.common.util.ObjectHelper;
import com.zdsoft.framework.core.commweb.annotation.UriKey;
import com.zdsoft.framework.core.commweb.component.BaseController;
import com.zdsoft.framework.cra.annotation.Menu;

import net.sf.ehcache.hibernate.strategy.TransactionalEhcacheEntityRegionAccessStrategy;
import oracle.net.aso.e;

/**
 * 版权所有：重庆正大华日软件有限公司
 * 
 * @Title:ContractPrintTplSetController.java
 * @Package:com.zdsoft.finance.contract.controller
 * @Description:用一句话描述该文件做什么
 * @author: huangdongkui
 * @date:Feb 28, 2017 10:42:21 AM
 * @version:v1.0
 */
@Controller
@RequestMapping("/contract")
public class ContractPrintTplSetController extends BaseController {
    @Autowired
    private HttpServletRequest request;

    @Autowired
    private CommonService commonService;

    @Autowired
    private BussPrintTplSetService bussPrintTplSetService;

    @Autowired
    private ZfPrintTemplateInnerpageService zfPrintTemplateInnerpageService;

    @Autowired
    private CED ced;

    @Autowired
    private BussPrintTplOrgService bussPrintTplOrgService;

    @Autowired
    private BussPrintTplPageService bussPrintTplPageService;

    @Autowired
    private BussPrintTplProductService bussPrintTplProductService;
    
    @Autowired
    private ProductService productService;

    private BussPrintTplSet logicDelete;

  
    /**
     * 套打模板配置
     * 
     * @return 套打模板配置列表
     */
    @RequestMapping("/initContractPrintTplSet")
    @UriKey(key = "com.zdsoft.finance.contract.initContractPrintTplSet")
    @Menu(resource = "com.zdsoft.finance.contract.initContractPrintTplSet", label = "套打模板配置", group = "businessSetting", order = 15)
    public ModelAndView initContractPrintTplSet() {

        return new ModelAndView("contract/ContractPrintTplSet_list");
    }

    /**
     * 添加模版
     * 
     * @return
     */
    @RequestMapping("/ContractPrintTplSet_edit")
    @UriKey(key = "com.zdsoft.finance.contract.ContractPrintTplSet_edit")
    @ResponseBody
    public ModelAndView ContractPrintTplSet_edit(String ContractPrintTplSet_id) {
        ModelMap modelMap = new ModelMap();

        try {
            BussPrintTplSet bussPrintTplSet;
            if (ContractPrintTplSet_id==null) {
                bussPrintTplSet=new BussPrintTplSet(); 
            }
            else{
                bussPrintTplSet = bussPrintTplSetService.findOne(ContractPrintTplSet_id);   
            }
            
            BussPrintTplSetVo vBussPrintTplSetVo = new BussPrintTplSetVo(bussPrintTplSet);
            
            vBussPrintTplSetVo.setOrglist(GetOrgidsStr(bussPrintTplSet.getId()));
            vBussPrintTplSetVo.setProductNames(GetProductStr(bussPrintTplSet.getId()));
            vBussPrintTplSetVo.setProductids(GetProductidsStr(bussPrintTplSet.getId()));
            modelMap.put("bussPrintTplSetVo", vBussPrintTplSetVo);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("跳转到检查页面出错", e);
        }

        return new ModelAndView("/contract/ContractPrintTplSet_edit", modelMap);
    }
    /**
     * 模版保存
     *
     * @author huangdongkui
     * @param bussPrintTplSetVo
     * @return
     */
    @RequestMapping("/contractPrintTplSetSave")
    @UriKey(key = "com.zdsoft.finance.contract.contractPrintTplSetSave")
    @ResponseBody
    public ResponseMsg contractPrintTplSetSave(BussPrintTplSetVo bussPrintTplSetVo) {
        ResponseMsg msg = new ResponseMsg();
        Map<String, Object> afterCheckMap = new HashMap<String, Object>();

        // // 将Vo转成Po
        BussPrintTplSet bussPrintTplSet = bussPrintTplSetVo.toPO();
        try {
            if (bussPrintTplSetVo.getId()==null) {
                msg.setMsg("新增模板成功");
            }
            else
            {      
                msg.setMsg("编辑模板成功");
            }
            
            bussPrintTplSet = bussPrintTplSetService.saveOrUpdateTran(bussPrintTplSet, bussPrintTplSetVo.getOrglist(),bussPrintTplSetVo.getProductids());
            bussPrintTplSetVo.setId(bussPrintTplSet.getId());
            afterCheckMap.put("afterCheck", bussPrintTplSetVo);
            msg.setResultStatus(ResponseMsg.SUCCESS);
            msg.setOptional(afterCheckMap);

        } catch (Exception e) {
            msg.setMsg("保存失败！");
            e.printStackTrace();
        }

        return msg;
    }

    
   /*
    * 删除模版
    *
    * @author huangdongkui
    * @param id
    * @return
    */

   @RequestMapping("/ContractPrintTplSet_del")
   @UriKey(key = "com.zdsoft.finance.contract.ContractPrintTplSet_del")
   @ResponseBody
   public ResponseMsg ContractPrintTplSet_del(String id) {
       
       ResponseMsg msg = new ResponseMsg();
       try {
           BussPrintTplSet logicDelete = bussPrintTplSetService.logicDelete(id);

           msg.setResultStatus(ResponseMsg.SUCCESS);
       } catch (Exception e) {
           logger.error("删除模版出错", e);
           e.printStackTrace();
           msg.setResultStatus(ResponseMsg.APP_ERROR);
           msg.setMsg(e.getMessage());
       }

       return msg;

   }
    /**
     * 添加模版
     * 
     * @return
     */
    @RequestMapping("/ContractPrintTplSet_Add")
    @UriKey(key = "com.zdsoft.finance.contract.ContractPrintTplSet_Add")
    @ResponseBody
    public ModelAndView ContractPrintTplSet_Add() {
        return new ModelAndView("/contract/ContractPrintTplSet_Add");
    }

    /**
     * 页列表
     * 
     * @return
     */
    @RequestMapping("/contractPrintTplSet_PageList")
    @UriKey(key = "com.zdsoft.finance.contract.contractPrintTplSet_PageList")
    @ResponseBody
    public String contractPrintTplSet_PageList(PageRequest pageable, String jsoncallback,String printTemplateId) {
        ResponseMsg msg = new ResponseMsg();

        @SuppressWarnings("unchecked")
        
        Page<BussPrintTplPage> page = null;
        try {
            page = bussPrintTplPageService.findByPageByCondition(pageable, printTemplateId);
            List<BussPrintTplPageVo> listVo = new ArrayList<BussPrintTplPageVo>();

            for (BussPrintTplPage queryObj : page.getRecords()) {

                BussPrintTplPageVo tempBussPrintTplPageVo = new BussPrintTplPageVo(queryObj);
                String aid = tempBussPrintTplPageVo.getAttachmentid();

                if (ObjectHelper.isNotEmpty(aid)) {
                    AttachmentDto findAttachmentById = ced.findAttachmentById(aid);
                    if (findAttachmentById!=null) {
                        String filePath = ced.findAttachmentById(aid).getFilePath();
                        
                        tempBussPrintTplPageVo.setCreditAttachment(filePath);
                    }
               
                }

                listVo.add(tempBussPrintTplPageVo);
            }
            msg.setResultStatus(ResponseMsg.SUCCESS);
            msg.setMsg("列表查询成功");
            msg.setRows(listVo);
            msg.setTotal(page.getTotalRows());
        } catch (Exception e) {
            e.printStackTrace();
            msg.setResultStatus(ResponseMsg.APP_ERROR);
            msg.setMsg("列表查询失败");
        }

        return ObjectHelper.objectToJson(msg, jsoncallback);
    }

    /**
     * 案件查询
     * 
     * @param pageable
     * @param jsoncallback
     * @return
     */
    @RequestMapping("/contractPrintTplSetList")
    @UriKey(key = "com.zdsoft.finance.contract.contractPrintTplSetList")
    @ResponseBody
    public String contractPrintTplSetList(PageRequest pageable, String jsoncallback) {
        ResponseMsg msg = new ResponseMsg();

        @SuppressWarnings("unchecked")
        List<QueryObj> queryObjs = ParameterUtil.getQueryObjByParameter(request, new String[] { "ccc", "org" });

        Page<Map<String, Object>> page = null;

        List<BussPrintTplSetVo> listResults = new ArrayList<BussPrintTplSetVo>();
        List<OrgTreeDto> listorg = null;
        try {
            page = bussPrintTplSetService.GetBussPrintTplPages(pageable, queryObjs);

            for (Map<String, Object> map_BussPrintTplSet : page.getRecords()) {
                String beloworgcode = map_BussPrintTplSet.get("BELOWORGCODE").toString();

                map_BussPrintTplSet.put("ORGNAME", ced.loadOrganizationByCode(beloworgcode).getOrgNm());
                map_BussPrintTplSet.put("CANUSEORGNAME", GetOrgStr(map_BussPrintTplSet.get("ID").toString()));
               
                map_BussPrintTplSet.put("productNames", GetProductStr(map_BussPrintTplSet.get("ID").toString()));
            }

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        msg.setResultStatus(ResponseMsg.SUCCESS);
        msg.setMsg("列表查询成功");
        msg.setRows(page.getRecords());
        msg.setTotal(page.getTotalRows());
        return ObjectHelper.objectToJson(msg, jsoncallback);
    }

    /**
     * 
     * 机构代码转机构名称
     *
     * @author huangdongkui
     * @param printTemplateId 模版ID
     * @return
     */
    private String GetOrgStr(String printTemplateId) {
        String returnValue ="";
        try {
            // BussPrintTplSet_ID
            List<BussPrintTplOrg> listborg = bussPrintTplOrgService.findByPrintTemplateId(printTemplateId);
            for (BussPrintTplOrg iterable_element : listborg) {
                returnValue+=ced.loadOrganizationByCode(iterable_element.getOrgCode()).getOrgNm() + ",";
            }
            
            if (ObjectHelper.isNotEmpty(returnValue)) {
                returnValue=returnValue.substring(0, returnValue.length() - 1);
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        
        
       
        return returnValue;
    }

    /**
     * 
     * 机构代码转机构名称
     *
     * @author huangdongkui
     * @param printTemplateId 模版ID
     * @return
     */
    private String GetOrgidsStr(String printTemplateId) {
        String returnValue ="";
        try {
            // BussPrintTplSet_ID
            List<BussPrintTplOrg> listborg = bussPrintTplOrgService.findByPrintTemplateId(printTemplateId);
            for (BussPrintTplOrg iterable_element : listborg) {
                returnValue+=iterable_element.getOrgCode() + ",";
            }
            
            if (ObjectHelper.isNotEmpty(returnValue)) {
                returnValue=returnValue.substring(0, returnValue.length() - 1);
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        
        
       
        return returnValue;
    }
    /**
     * 
     * 获取产品表信息
     *
     * @author huangdongkui
     * @param printTemplateId 模版ID
     * @return
     */
    private String GetProductStr(String printTemplateId)
    {
        String returnValue ="";
        
        try {
            List<BussPrintTplProduct> listProduct = bussPrintTplProductService.findByPrintTemplateId(printTemplateId);
            for (BussPrintTplProduct bussPrintTplProductItem : listProduct) {

                Product findOne = productService.findOne(bussPrintTplProductItem.getProductcode());
                returnValue+=findOne.getProductName()+",";
            }
            
            if (ObjectHelper.isNotEmpty(returnValue)) {
                returnValue=returnValue.substring(0, returnValue.length() - 1);
            }
          
            
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        
        return returnValue;
    }
    
    
    /**
     * 
     * 获取产品表信息IDS
     *
     * @author huangdongkui
     * @param printTemplateId
     * @return
     */
    private String GetProductidsStr(String printTemplateId)
    {
        String returnValue ="";
        
        try {
            List<BussPrintTplProduct> listProduct = bussPrintTplProductService.findByPrintTemplateId(printTemplateId);
            for (BussPrintTplProduct bussPrintTplProductItem : listProduct) {
                returnValue+=bussPrintTplProductItem.getProductcode()+",";
            }
            
            if (ObjectHelper.isNotEmpty(returnValue)) {
                returnValue=returnValue.substring(0, returnValue.length() - 1);
            }
          
            
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        
        return returnValue;
    }
   
   /**
    * 
    * 编辑页
    *
    * @author huangdongkui
    * @param 页面主建id
    * @param 模版主建printTemplateId
    * @return
    */
    @RequestMapping("/ContractPrintTplPage_edit")
    @UriKey(key = "com.zdsoft.finance.contract.ContractPrintTplPage_edit")
    @ResponseBody
    public ModelAndView ContractPrintTplPage_edit(String id, String printTemplateId) {
        ModelMap modelMap = new ModelMap();

        try {
            BussPrintTplPage bussPrintTplPage;
            if (id == null) {// 添加
                bussPrintTplPage=new BussPrintTplPage();
                bussPrintTplPage.setPrinttemplateid(printTemplateId);
                
            } else// 编辑
            {
                bussPrintTplPage = bussPrintTplPageService.findOne(id);
            }
            BussPrintTplPageVo bussPrintTplPageVo = new BussPrintTplPageVo(bussPrintTplPage);

            String attachmentid = bussPrintTplPageVo.getAttachmentid();

            if (ObjectHelper.isNotEmpty(attachmentid)) {
                bussPrintTplPageVo.setCreditAttachment(ced.findAttachmentById(attachmentid).getFilePath());
            }
          
            modelMap.put("bussPrintTplPageVo", bussPrintTplPageVo);

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("跳转到检查页面出错", e);
        }

        return new ModelAndView("/contract/contractPrintTplPage_edit", modelMap);
    }

    /**
     * 页保存
     *
     * @author huangdongkui
     * @param bussPrintTplPageVo
     * @return
     */
    @RequestMapping("/contractPrintTplPage_Save")
    @UriKey(key = "com.zdsoft.finance.contract.contractPrintTplPage_Save")
    @ResponseBody

    public ResponseMsg contractPrintTplPage_Save(BussPrintTplPageVo bussPrintTplPageVo) {
        ResponseMsg msg = new ResponseMsg();
        Map<String, Object> afterCheckMap = new HashMap<String, Object>();
        BussPrintTplPage bussPrintTplPage = bussPrintTplPageVo.toPO();
        try {

            bussPrintTplPage = bussPrintTplPageService.saveOrUpdateEntity(bussPrintTplPage);

            afterCheckMap.put("afterCheck", bussPrintTplPageVo);
            msg.setResultStatus(ResponseMsg.SUCCESS);
            msg.setOptional(afterCheckMap);
            msg.setMsg("保存检查信息成功！");
        } catch (Exception e) {
            msg.setMsg("保存检查信息失败！");
            e.printStackTrace();
        }
        return msg;
    }

    /**
     * 页删除
     *
     * @author huangdongkui
     * @param id
     * @return
     */

    @RequestMapping("/contractPrintTplPageDelete")
    @UriKey(key = "com.zdsoft.finance.contract.contractPrintTplPageDelete")
    @ResponseBody
    public ResponseMsg contractPrintTplPageDelete(String id) {
        ResponseMsg msg = new ResponseMsg();
        try {
            BussPrintTplPage obj = bussPrintTplPageService.logicDelete(id);

            msg.setResultStatus(ResponseMsg.SUCCESS);
        } catch (Exception e) {
            logger.error("删除抵押权人出错", e);
            e.printStackTrace();
            msg.setResultStatus(ResponseMsg.APP_ERROR);
            msg.setMsg(e.getMessage());
        }

        return msg;

    }
    /**
     * 
     * 获取模版和页面信息
     *
     * @author huangdongkui
     * @param printTemplateId
     * @param pageid
     * @return
     * @throws BusinessException
     */
    
    @RequestMapping("/getTplAndPageByPrintTplId")
    @UriKey(key = "com.zdsoft.finance.contract.getTplAndPageByPrintTplId")
    @ResponseBody
  
    public String getTplAndPageByPrintTplId(String printTemplateId) throws BusinessException {

        List<BussPrintTplPage> resultObj = bussPrintTplPageService.findByTempId(printTemplateId);
        BussPrintTplSet m_bussPrintTplSet= bussPrintTplSetService.findOne(printTemplateId);
        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("templateName", m_bussPrintTplSet.getTemplatename());
        resultMap.put("ContractTemplates", resultObj);

        return ObjectHelper.objectToJson(resultMap);

    }
}
