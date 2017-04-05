package com.zdsoft.finance.filestore.controller;

import com.alibaba.fastjson.JSON;
import com.zdsoft.essential.client.service.CED;
import com.zdsoft.essential.dto.basedata.AttachmentDto;
import com.zdsoft.finance.common.enums.ENUM_USING_STATUS;
import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.finance.filestore.entity.DownHistory;
import com.zdsoft.finance.filestore.entity.FileStore;
import com.zdsoft.finance.filestore.service.DownHistoryService;
import com.zdsoft.finance.filestore.service.FileStoreService;
import com.zdsoft.finance.filestore.vo.DownHistoryVo;
import com.zdsoft.finance.filestore.vo.FileStoreVo;
import com.zdsoft.finance.product.entity.MaterialListType;
import com.zdsoft.framework.core.common.dto.ResponseMsg;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.PageRequest;
import com.zdsoft.framework.core.common.util.ObjectHelper;
import com.zdsoft.framework.core.commweb.annotation.UriKey;
import com.zdsoft.framework.core.commweb.component.BaseController;
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
 * 版权所有：重庆正大华日软件有限公司
 * @title FileStoreController.java
 * @className FileStoreController
 * @description
 * @create 2017-02-20 14:12
 * @author LiaoGuoWei
 * @version V1.0
 **/
@Controller
@RequestMapping(value = "/fileStore")
public class FileStoreController extends BaseController {

    @Autowired
    private FileStoreService fileStoreService;

    @Autowired
    private CED CED;

    @Autowired
    private DownHistoryService downHistoryService;

    /**
      * @Title: fileShowListPage
      * @Description: 列表显示页面
      * @author liaoguowei
      * @param modelAndView
      * @param fileStoreVo 文件库Vo,必须传入参数为
      *                    caseApplyId 案件号,businessId 业务表单ID,linkCode 环节编号,productId 产品ID
      * @return ModelAndView
      * @throws
    */
    @RequestMapping(value = "/fileShowListPage")
    @UriKey(key = "com.zdsoft.finance.fileStore.fileShowListPage")
    public ModelAndView fileShowListPage(ModelAndView modelAndView,FileStoreVo fileStoreVo){
        modelAndView.setViewName("filestore/filestore_showlist");
        modelAndView.addObject("fileStoreVo",fileStoreVo);
        return modelAndView;
    }

    /**
      * @Title: fileShowListDada
      * @Description: 列表显示页面列表数据
      * @author liaoguowei
      * @param fileStoreVo 文件库VO
      * @param jsoncallback json回调函数
      * @return String
      * @throws
    */
    @RequestMapping(value = "/fileShowListDada",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @UriKey(key = "com.zdsoft.finance.fileStore.fileShowListDada")
    @ResponseBody
    public String fileShowListDada(PageRequest pageRequest,FileStoreVo fileStoreVo,String jsoncallback,String isSearch){
        ResponseMsg msg=new ResponseMsg();
        try{
            Page<FileStore> sourceData=this.fileStoreService.findPageByCondition(pageRequest,fileStoreVo.toPo(),isSearch);
            List<FileStoreVo> returnList=new ArrayList<FileStoreVo>();
            if(ObjectHelper.isNotEmpty(sourceData)&&ObjectHelper.isNotEmpty(sourceData.getRecords())){
                for(FileStore temp:sourceData.getRecords()){
                    FileStoreVo vo=new FileStoreVo(temp);
                    //申明操作人
                    String updateEmpName="";
                    updateEmpName=ObjectHelper.isNotEmpty(temp.getUpdateBy())?CED.loadEmployeeByCode(temp.getUpdateBy()).getEmpNm():"";
                    vo.setUpdateEmpName(updateEmpName);
                    //申明类别
                    returnList.add(vo);
                }
            }
            msg.setRows(returnList);
            msg.setTotal(sourceData.getTotalRows());
        }catch (Exception e){
            e.printStackTrace();
            msg.setMsg(e.getMessage());
            logger.error("分页查询列表文件库列表数据出错",e);
        }
        return ObjectHelper.objectToJson(msg,jsoncallback);
    }
    /**
     * @Title: saveAllDraft
     * @Description:  将相关案件的所有草稿状态的附件改为有效附件
     * @author liaoguowei
     * @param caseApplyId
     * @param linkCode
     * @param businessId
     * @return com.zdsoft.framework.core.common.dto.ResponseMsg
     * @throws
     */
    @RequestMapping(value = "/saveAllDraft",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @UriKey(key = "com.zdsoft.finance.fileStore.saveAllDraft")
    @ResponseBody
    public ResponseMsg saveAllDraft(String caseApplyId,String linkCode,String businessId){
        ResponseMsg msg=new ResponseMsg();
        try {
            this.fileStoreService.saveAllDraft(caseApplyId, linkCode,businessId);
            msg.setResultStatus(ResponseMsg.SUCCESS);
        }catch (Exception e){
            msg.setResultStatus(ResponseMsg.APP_ERROR);
            msg.setMsg(e.getMessage());
            logger.error("保存附件出错",e);
            e.printStackTrace();
        }
        return msg;
    }

    /**
     * @Title: fileUploadPage
     * @Description:  上传页面
     * @author liaoguowei
     * @param modelAndView
     * @param fileStoreVo
     * @return org.springframework.web.servlet.ModelAndView
     * @throws
     */
    @RequestMapping(value = "/fileUploadPage")
    @UriKey(key = "com.zdsoft.finance.fileStore.fileUploadPage")
    public ModelAndView fileUploadPage(ModelAndView modelAndView,FileStoreVo fileStoreVo){
        modelAndView.setViewName("filestore/filestore_uploadlist");
        try{
            //设页面的所有数据初始状态为草稿状态
            fileStoreVo.setStatus(ENUM_USING_STATUS.DRAFT.value);
            String allSimple=this.getAllCode(fileStoreVo);
            modelAndView.addObject("allSimple",allSimple);
            modelAndView.addObject("fileStoreVo",fileStoreVo);
            modelAndView.addObject("fileStoreJson", JSON.toJSONString(fileStoreVo));
        }catch (Exception e){
            logger.error("文件库转跳到上传页面出错",e);
            e.printStackTrace();
        }
        return modelAndView;
    }

    /**
     * @Title: fileUploadTempData
     * @Description: 上传临时数据列表
     * @author liaoguowei
     * @param fileStoreVo
     * @param jsoncallback
     * @return java.lang.String
     * @throws
     */
    @RequestMapping(value = "/fileUploadTempData",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @UriKey(key = "com.zdsoft.finance.fileStore.fileUploadTempData")
    @ResponseBody
    public String fileUploadTempData(FileStoreVo fileStoreVo,String jsoncallback){
        ResponseMsg msg=new ResponseMsg();
        try{
            List<FileStore> sourceData=this.fileStoreService.findByCondition(fileStoreVo.toPo());
            List<FileStoreVo> returnList=new ArrayList<FileStoreVo>();
            if(ObjectHelper.isNotEmpty(sourceData)){
                for(FileStore temp:sourceData){
                    FileStoreVo vo=new FileStoreVo(temp);
                    //申明操作人
                    String updateEmpName="";
                    updateEmpName=ObjectHelper.isNotEmpty(temp.getUpdateBy())?CED.loadEmployeeByCode(temp.getUpdateBy()).getEmpNm():"";
                    vo.setUpdateEmpName(updateEmpName);
                    //申明所属分类
                    String materiaTypeCodeName="";
//                    //申明类别
//                    String materiaCodeName="";
//                    materiaCodeName=ObjectHelper.isNotEmpty(temp.getMateriaCode())?CED.loadSimpleCodeById(temp.getMateriaCode()).getName():"";
//                    materiaTypeCodeName=ObjectHelper.isNotEmpty(temp.getMateriaTypeCode())?CED.loadSimpleCodeById(temp.getMateriaTypeCode()).getName():"";
//                    vo.setMateriaCodeName(materiaCodeName);
//                    vo.setMateriaTypeCodeName(materiaTypeCodeName);
                    returnList.add(vo);
                }
            }
            msg.setRows(returnList);
        }catch (Exception e){
            msg.setMsg(e.getMessage());
            logger.error("查询上传临时数据出错",e);
            e.printStackTrace();
        }
        return ObjectHelper.objectToJson(msg,jsoncallback);
    }

    /**
     * @Title: deleteFileStore
     * @Description: 删除
     * @author liaoguowei
     * @param id
     * @return com.zdsoft.framework.core.common.dto.ResponseMsg
     * @throws
     */
    @RequestMapping(value = "/deleteFileStore",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @UriKey(key = "com.zdsoft.finance.fileStore.deleteFileStore")
    @ResponseBody
    public ResponseMsg deleteFileStore(String id){
        ResponseMsg msg=new ResponseMsg();
        try{
            this.fileStoreService.logicDeleteFileStore(id);
            msg.setResultStatus(ResponseMsg.SUCCESS);
        }catch (Exception e){
            msg.setMsg(e.getMessage());
            msg.setResultStatus(ResponseMsg.APP_ERROR);
            logger.error("删除出错",e);
            e.printStackTrace();
        }
        return msg;
    }

    /**
     * @Title: uploadFileStore
     * @Description: 上传附件
     * @author liaoguowei
     * @param fileStoreJson
     * @param fileName
     * @param attachmentId
     * @return com.zdsoft.framework.core.common.dto.ResponseMsg
     * @throws
     */
    @RequestMapping(value = "/uploadFileStore",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @UriKey(key = "com.zdsoft.finance.fileStore.uploadFileStore")
    @ResponseBody
    public ResponseMsg uploadFileStore(String fileStoreJson,String fileName,String attachmentId){
        ResponseMsg msg=new ResponseMsg();
        try{
            FileStore fileStore=JSON.parseObject(fileStoreJson, FileStore.class);
            fileStore.setFileName(fileName);
            fileStore.setAttachmentId(attachmentId);
            this.fileStoreService.saveOrUpdateFileStore(fileStore);
            msg.setResultStatus(ResponseMsg.SUCCESS);
        }catch (Exception e){
            if(e.getClass().getName().equalsIgnoreCase("com.zdsoft.finance.common.exception.BusinessException")){
                BusinessException businessException=(BusinessException)e;
                msg.setMsg(businessException.getExceptionMessage());
            }else{
                msg.setMsg(e.getMessage());
            }
            msg.setResultStatus(ResponseMsg.APP_ERROR);
            logger.error("上传附件失败!",e);
            e.printStackTrace();
        }
        return msg;
    }

    /**
     * @Title: editFileStore
     * @Description: 编辑
     * @author liaoguowei
     * @param fileStoreVo
     * @param jsoncallback
     * @return com.zdsoft.framework.core.common.dto.ResponseMsg
     * @throws
     */
    @RequestMapping(value = "/editFileStore")
    @UriKey(key = "com.zdsoft.finance.fileStore.editFileStore")
    @ResponseBody
    public ResponseMsg editFileStore(FileStoreVo fileStoreVo,String jsoncallback){
        ResponseMsg msg=new ResponseMsg();
        try{
            this.fileStoreService.saveOrUpdateFileStore(fileStoreVo.toPo());
            msg.setResultStatus(ResponseMsg.SUCCESS);
        }catch (Exception e){
            msg.setResultStatus(ResponseMsg.APP_ERROR);
            msg.setMsg(e.getMessage());
            logger.error("编辑附件出错！",e);
            e.printStackTrace();
        }
        return msg;
    }

    /**
     * @Title: downloadFile
     * @Description: 下载附件前保存下载历史记录
     * @author liaoguowei
     * @param ids
     * @return com.zdsoft.framework.core.common.dto.ResponseMsg
     * @throws
     */
    @RequestMapping(value = "/downloadFile",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @UriKey(key = "com.zdsoft.finance.fileStore.downloadFile")
    @ResponseBody
    public ResponseMsg downloadFile(String ids){
        ResponseMsg msg=new ResponseMsg();
        try{
            this.downHistoryService.saveDownHisByFileStoreIds(ids);
            msg.setResultStatus(ResponseMsg.SUCCESS);
        }catch (Exception e){
            logger.error("下载附件出错！",e);
            msg.setResultStatus(ResponseMsg.APP_ERROR);
            msg.setMsg(e.getMessage());
            e.printStackTrace();
        }
        return msg;
    }

    /**
     * @Title: downHisPage
     * @Description: 历史纪录页面
     * @author liaoguowei
     * @param modelAndView
     * @return org.springframework.web.servlet.ModelAndView
     * @throws
     */
    @RequestMapping(value = "/downHisPage")
    @UriKey(key = "com.zdsoft.finance.fileStore.downHisPage")
    public ModelAndView downHisPage(FileStoreVo fileStoreVo,ModelAndView modelAndView){
        modelAndView.setViewName("filestore/filestore_downhistory_list");
        modelAndView.addObject("fileStoreVo",fileStoreVo);
        return modelAndView;
    }

    /**
     * @Title: downHisListData
     * @Description: 历史记录列表数据
     * @author liaoguowei
     * @param pageRequest
     * @param downHistoryVo
     * @param jsoncallback
     * @return java.lang.String
     * @throws
     */
    @RequestMapping(value = "/downHisListData",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @UriKey(key = "com.zdsoft.finance.fileStore.downHisListData")
    @ResponseBody
    public String downHisListData(PageRequest pageRequest,DownHistoryVo downHistoryVo,String jsoncallback){
        ResponseMsg msg=new ResponseMsg();
        try{
            Page<DownHistory> sourceData=this.downHistoryService.findPageByCondition(pageRequest,downHistoryVo.toPo());
            List<DownHistoryVo> returnListData=new ArrayList<DownHistoryVo>();
            if(ObjectHelper.isNotEmpty(sourceData)&&ObjectHelper.isNotEmpty(sourceData.getRecords())){
                for(DownHistory temp: sourceData.getRecords()){
                    DownHistoryVo vo=new DownHistoryVo(temp);
                    //所属分类
                    String materiaTypeName=ObjectHelper.isNotEmpty(temp.getMateriaTypeCode())?CED.loadSimpleCodeById(temp.getMateriaTypeCode()).getName():"";
                    //类别名称
                    String mateiraName=ObjectHelper.isNotEmpty(temp.getMateriaCode())?CED.loadSimpleCodeById(temp.getMateriaCode()).getName():"";

                    vo.setMateriaTypeName(materiaTypeName);
                    vo.setMateriaName(mateiraName);

                    returnListData.add(vo);
                }
            }
            msg.setRows(returnListData);
            msg.setTotal(sourceData.getTotalRows());
        }catch (Exception e){
            msg.setMsg(e.getMessage());
            logger.error("历史纪录数据查询出错",e);
            e.printStackTrace();
        }
        return ObjectHelper.objectToJson(msg,jsoncallback);
    }

    /**
     * @Title: imgViewPage
     * @Description: 图片预览页面
     * @author liaoguowei
     * @param modelAndView
     * @param attachmentIds
     * @return org.springframework.web.servlet.ModelAndView
     * @throws
     */
    @RequestMapping(value = "/imgViewPage")
    @UriKey(key = "com.zdsoft.finance.fileStore.imgViewPage")
    public ModelAndView imgViewPage(ModelAndView modelAndView,String attachmentIds){
        modelAndView.setViewName("filestore/filestore_img_view");
        try{
            List<AttachmentDto> sourceData=this.fileStoreService.findAttachmentsByIds(attachmentIds);
            modelAndView.addObject("attas",sourceData);
        }catch (Exception e){
            logger.error("图片预览出错",e);
            e.printStackTrace();
        }
        return modelAndView;
    }

    /**
     * @Title: findProductMateriaWithLinkCode
     * @Description: 通过当前产品和环节编号查找资料清单中已经配置好的资料类型数据
     * @author liaoguowei
     * @param fileStoreVo 查询条件
     * @return java.lang.String
     * @throws
     */
    @RequestMapping(value = "/findProductMateriaWithLinkCode")
    @UriKey(key = "com.zdsoft.finance.fileStore.findProductMateriaWithLinkCode")
    @ResponseBody
    public String findProductMateriaWithLinkCode(FileStoreVo fileStoreVo,String jsoncallback){
        List<Map<String,Object>> sourceData=null;
        try{
            sourceData=this.fileStoreService.findProductMateriaWithLinkCode(fileStoreVo.toPo());
        }catch (Exception e){
            logger.error("资料库查找资料下拉框出错",e);
            e.printStackTrace();
        }
        return ObjectHelper.objectToJson(sourceData,jsoncallback);
    }

    /**
     * @Title: findParentMateria
     * @Description: 查找父级资料
     * @author liaoguowei
     * @param jsoncallback json回调
     * @return java.lang.String
     * @throws
     */
    @RequestMapping(value = "/findParentMateria")
    @UriKey(key = "com.zdsoft.finance.fileStore.findParentMateria")
    @ResponseBody
    public String findParentMateria(String jsoncallback){
        List<MaterialListType> sourceData=this.fileStoreService.findParentMaterial();
        return ObjectHelper.objectToJson(sourceData,jsoncallback);
    }

    /**
     * @Title: getAllCode
     * @Description: 得到所有资料清单小类simpleCode
     * @author liaoguowei
     * @param
     * @return java.lang.String
     * @throws
     */
    private String getAllCode(FileStoreVo vo) {
        String returnData="";
        try {
            List<Map<String,Object>> sourceData=this.fileStoreService.findProductMateriaWithLinkCode(vo.toPo());
            List<Map<String, Object>> allSimples = new ArrayList<Map<String, Object>>();
            for (Map<String,Object> temp : sourceData) {
                List<Map<String,Object>> childs=(List<Map<String,Object>>)temp.get("child");
                for(Map<String,Object> row:childs){
                    Map<String, Object> clientSimMap = new HashMap<String, Object>();
                    clientSimMap.put("id", row.get("id"));
                    clientSimMap.put("name", row.get("text"));
                    allSimples.add(clientSimMap);
                }
            }
            returnData=ObjectHelper.objectToJson(allSimples);
        } catch (Exception e) {
            logger.error("资料清单查询simpleCode出错", e);
            e.printStackTrace();
        }
        return  returnData;
    }

}
