package com.zdsoft.finance.app.filestore.controller;

import com.zdsoft.essential.client.service.CED;
import com.zdsoft.finance.common.utils.AppStatus;
import com.zdsoft.finance.common.utils.app.AppServerUtil;
import com.zdsoft.finance.filestore.entity.FileStore;
import com.zdsoft.finance.filestore.service.FileStoreService;
import com.zdsoft.finance.filestore.vo.FileStoreVo;
import com.zdsoft.finance.product.service.MateriaListService;
import com.zdsoft.framework.core.commweb.component.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @title AppFileStoreController.java
 * @className AppFileStoreController
 * @description APP文件仓库controller
 * @author LiaoGuoWei
 * @create 2017/3/2 11:02
 * @version V1.0
 **/
@Controller
@RequestMapping(value = "/server/fileStore")
public class AppFileStoreController extends BaseController {

    @Autowired
    private CED CED;

    @Autowired
    private FileStoreService fileStoreService;

    @Autowired
    private MateriaListService materiaListService;


    /**
     * @Title: uploadFile
     * @Description: APP接口上传文件
     * @author liaoguowei
     * @param multipartfiles
     * @param fileStoreVo
     * @param token
     * @return java.lang.String
     * @throws
     */
    @RequestMapping(value = "/upload",produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String uploadFileForApp(@RequestParam("fileDatas")CommonsMultipartFile[] multipartfiles,FileStoreVo fileStoreVo,String token){
        String result="";
        try{
            List<FileStore> sourceData=this.fileStoreService.saveAndUploadForApp(multipartfiles,fileStoreVo.toPo(),token);
            Map<String,Object> returnData=new HashMap<String,Object>();
            returnData.put("caseApplyId",fileStoreVo.getCaseApplyId());
            List<Map<String,Object>> listDada=new ArrayList<Map<String,Object>>();
            for(FileStore temp:sourceData){
                Map<String,Object> listRowData=new HashMap<String,Object>();
                listRowData.put("attachmentId",temp.getAttachmentId());
                listRowData.put("fileName",temp.getFileName());
                listRowData.put("fileType",temp.getFileType());
                listRowData.put("fileStoreId",temp.getId());
                listDada.add(listRowData);
            }
            returnData.put("list",listDada);
            result=AppServerUtil.buildJsonObject(AppStatus.Succeed, returnData);
        }catch (Exception e){
            e.printStackTrace();
            logger.error("APP上传文件并保存数据出错",e);
            result = AppServerUtil.buildJsonObject(AppStatus.SystemError, e.getMessage());
        }
        return result;
    }

    /**
     * @Title: getMateriaListForApp
     * @Description: APP用 查询资料列表接口
     * @author liaoguowei
     * @param fileStoreVo
     * @return java.lang.String
     * @throws
     */
    @RequestMapping(value = "/getMateriaListForApp",produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String getMateriaListForApp(FileStoreVo fileStoreVo){
        String result="";
        try{
            List<Map<String,Object>> sourceData=this.fileStoreService.findByConditionForApp(fileStoreVo.toPo());
            result=AppServerUtil.buildJsonObject(AppStatus.Succeed,sourceData);
        }catch (Exception e){
            e.printStackTrace();
            logger.error("APP查询资料列表出错",e);
            result=AppServerUtil.buildJsonObject(AppStatus.SystemError);
        }
        return result;
    }

}
