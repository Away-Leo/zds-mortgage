package com.zdsoft.finance.filestore.service.impl;

import com.zdsoft.essential.client.service.CED;
import com.zdsoft.essential.dto.basedata.AttachmentDto;
import com.zdsoft.essential.dto.emp.EmpDto;
import com.zdsoft.finance.app.attachment.service.AppAttachmentService;
import com.zdsoft.finance.base.service.impl.BaseServiceImpl;
import com.zdsoft.finance.common.enums.ENUM_MEDIA_TYPE;
import com.zdsoft.finance.common.enums.ENUM_USING_STATUS;
import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.finance.common.utils.ObjectProperUtil;
import com.zdsoft.finance.filestore.entity.FileStore;
import com.zdsoft.finance.filestore.repository.FileStoreRepository;
import com.zdsoft.finance.filestore.service.FileStoreService;
import com.zdsoft.finance.marketing.entity.CaseApply;
import com.zdsoft.finance.marketing.service.CaseApplyService;
import com.zdsoft.finance.product.entity.MaterialList;
import com.zdsoft.finance.product.entity.MaterialListType;
import com.zdsoft.finance.product.repository.MaterialListTypeRepository;
import com.zdsoft.finance.product.service.MateriaListLimitsService;
import com.zdsoft.finance.product.service.MateriaListService;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.Pageable;
import com.zdsoft.framework.core.common.util.ObjectHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.util.*;

/**
 * 版权所有：重庆正大华日软件有限公司
 *
 * @author LiaoGuoWei
 * @version V1.0
 * @title FileStoreServiceImpl.java
 * @className FileStoreServiceImpl
 * @description 文件仓库service实现类
 * @create 2017/2/27 11:09
 **/
@Service(value = "fileStoreService")
public class FileStoreServiceImpl extends BaseServiceImpl<FileStore, FileStoreRepository> implements FileStoreService {

    @Autowired
    private FileStoreRepository fileStoreRepository;

    @Autowired
    private CED CED;

    @Autowired
    private MateriaListService materiaListService;

    @Autowired
    private MateriaListLimitsService materiaListLimitsService;

    @Autowired
    private CaseApplyService caseApplyService;

    @Autowired
    private AppAttachmentService appAttachmentService;

    @Autowired
    private MaterialListTypeRepository materialListTypeRepository;


    @Override
    public List<FileStore> findByCondition(FileStore fileStore) throws Exception {
        if (ObjectHelper.isNotEmpty(fileStore)) {
            return this.fileStoreRepository.findByCondition(fileStore);
        } else {
            throw new BusinessException("10010004", "未传入相关参数");
        }
    }

    @Override
    public List<Map<String,Object>> findByConditionForApp(FileStore fileStore) throws Exception {
        if (ObjectHelper.isNotEmpty(fileStore)) {
            if (ObjectHelper.isEmpty(fileStore.getLinkCode())) {
                throw new BusinessException("10010004", "未传入相关参数，APP查询资料列表未传入环节编号");
            }
            if (ObjectHelper.isEmpty(fileStore.getCaseApplyId())) {
                throw new BusinessException("10010004", "未传入相关参数，APP查询资料列表未传入案件编号");
            }
            if (ObjectHelper.isEmpty(fileStore.getMateriaCodes())) {
                throw new BusinessException("10010004", "未传入相关参数，APP查询资料列表未传入资料编号集");
            }
            //定义返回数据
            List<Map<String,Object>> returnData=new ArrayList<Map<String, Object>>();
            //从文件仓库中查找数据
            Map<String,List<FileStore>> sourceData=this.findMapByConditionForApp(fileStore);
            for(String temp:sourceData.keySet()){
                //定义返回行数据
                Map<String,Object> rowData=null;
                if(ObjectHelper.isEmpty(sourceData.get(temp))){
                    CaseApply caseApply=this.caseApplyService.findOne(fileStore.getCaseApplyId());
                    if(ObjectHelper.isNotEmpty(caseApply)&&ObjectHelper.isNotEmpty(caseApply.getProductSubtypeId())){
                        try{
                            MaterialList materialList=this.materiaListService.findByProductIdAndMateriaCode(caseApply.getProductSubtypeId(),temp);
                            rowData=new HashMap<String,Object>();
                            rowData.put("materiaCode",temp);
                            rowData.put("materiaId",materialList.getId());
                            rowData.put("caseApplyId",fileStore.getCaseApplyId());
                            rowData.put("productId",caseApply.getProductSubtypeId());
                            rowData.put("linkCode",fileStore.getLinkCode());
                            rowData.put("list",new ArrayList<>());
                        }catch (Exception e){
                            logger.error("APP查询资料出错",e);
                            e.printStackTrace();
                        }
                    }
                }else{
                    List<FileStore> oneRowFileStore=sourceData.get(temp);
                    List<Map<String,Object>> returnListData=new ArrayList<Map<String, Object>>();
                    rowData=new HashMap<String,Object>();
                    rowData.put("materiaCode",temp);
                    rowData.put("materiaId",oneRowFileStore.get(0).getMateriaId());
                    rowData.put("caseApplyId",oneRowFileStore.get(0).getCaseApplyId());
                    rowData.put("productId",ObjectHelper.isNotEmpty(oneRowFileStore.get(0).getProductId())?oneRowFileStore.get(0).getProductId():"");
                    rowData.put("linkCode",fileStore.getLinkCode());
                    for(FileStore tempFileStore:oneRowFileStore){
                        Map<String,Object> returnListRowData=new HashMap<String,Object>();
                        returnListRowData.put("attachmentId",tempFileStore.getAttachmentId());
                        returnListRowData.put("fileName",tempFileStore.getFileName());
                        returnListRowData.put("fileType",tempFileStore.getFileType());
                        returnListRowData.put("id",tempFileStore.getId());
                        returnListData.add(returnListRowData);
                    }
                    rowData.put("list",returnListData);
                }
                if(ObjectHelper.isNotEmpty(rowData)){
                    //申明资料名称
                    String materiaCodeName="";
                    materiaCodeName=CED.loadSimpleCodeById(rowData.get("materiaCode").toString()).getName();
                    rowData.put("materiaCodeName",materiaCodeName);
                    returnData.add(rowData);
                }
            }
            return returnData;
        } else {
            throw new BusinessException("10010004", "未传入相关参数，APP查找资料列表出错");
        }
    }

    @Override
    public Map<String, List<FileStore>> findMapByConditionForApp(FileStore fileStore) throws Exception {
        if(ObjectHelper.isNotEmpty(fileStore)){
            if (ObjectHelper.isEmpty(fileStore.getLinkCode())) {
                throw new BusinessException("10010004", "未传入相关参数，APP查询资料列表未传入环节编号");
            }
            if (ObjectHelper.isEmpty(fileStore.getCaseApplyId())) {
                throw new BusinessException("10010004", "未传入相关参数，APP查询资料列表未传入案件编号");
            }
            if (ObjectHelper.isEmpty(fileStore.getMateriaCodes())) {
                throw new BusinessException("10010004", "未传入相关参数，APP查询资料列表未传入资料编号集");
            }
            Map<String,List<FileStore>> returnData=new LinkedHashMap<String,List<FileStore>>();
            //定义资料类别集
            String[] materiaCodes=fileStore.getMateriaCodes().split(",");
            for(int i=0;i<materiaCodes.length;i++){
                fileStore.setMateriaCode(materiaCodes[i]);
                fileStore.setMateriaCodes(null);
                List<FileStore> sourceData = this.fileStoreRepository.findByCondition(fileStore);
                returnData.put(materiaCodes[i],sourceData);
            }
            return returnData;
        }else{
            throw new BusinessException("10010004","未传入相关参数，APP按照条件查找文件库数据出错");
        }
    }

    @Override
    public Page<FileStore> findPageByCondition(Pageable pageable, FileStore fileStore,String isSearch) throws Exception {
        if (ObjectHelper.isNotEmpty(fileStore)) {
            if (ObjectHelper.isEmpty(fileStore.getProductId())) {
                throw new BusinessException("10010003", "传入参数有误！产品ID为空");
            }
            if(ObjectHelper.isEmpty(fileStore.getCaseApplyId())){
                throw new BusinessException("10010003","传入参数有误！案件编号为空");
            }
            //如果是搜索 则屏蔽字段
            if(ObjectHelper.isNotEmpty(isSearch)){
                fileStore.setLinkCode("");
                //fileStore.setCaseApplyId("");
                fileStore.setBusinessId("");
            }
            return fileStoreRepository.findByCondition(pageable, fileStore);
        } else {
            throw new BusinessException("10010004", "未传入相关参数");
        }
    }

    @Override
    public FileStore findById(String id) throws BusinessException {
        if (ObjectHelper.isNotEmpty(id)) {
            FileStore fileStore = this.fileStoreRepository.findOne(id);
            if (ObjectHelper.isNotEmpty(fileStore)) {
                return fileStore;
            } else {
                throw new BusinessException("10010002", "根据参数未找到相应数据");
            }
        } else {
            throw new BusinessException("10010004", "未传入相关参数");
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public FileStore logicDeleteFileStore(String id) throws Exception {
        if (ObjectHelper.isNotEmpty(id)) {
            FileStore fileStore = this.findById(id);
            return this.fileStoreRepository.logicDelete(fileStore);
        } else {
            throw new BusinessException("10010004", "未传入相关参数");
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void batchLogicDeleteFileStore(List<FileStore> fileStoreList) throws Exception {
        if(ObjectHelper.isNotEmpty(fileStoreList)){
            //待删除数据
            List<FileStore> sourceData=new ArrayList<FileStore>();
            for(FileStore temp:fileStoreList){
                try{
                    FileStore fileStore=this.findById(temp.getId());
                    sourceData.add(fileStore);
                }catch (BusinessException e){
                    logger.error("批量删除文件库数据出错，根据ID未找到相应数据",e);
                    e.printStackTrace();
                }
            }
            this.fileStoreRepository.batchLogicDelete(sourceData);
        }else{
            throw new BusinessException("10010004","未传入相关参数，批量删除文件库数据出错");
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public FileStore updateFileStore(FileStore fileStore) throws Exception {
        if (ObjectHelper.isNotEmpty(fileStore)) {
            if (ObjectHelper.isNotEmpty(fileStore.getId())) {
                FileStore oldData = this.findById(fileStore.getId());
                //将更改过后的值复制到旧数据中
                oldData = (FileStore) ObjectProperUtil.compareAndValue(fileStore, oldData, false, null);
                oldData.setUpdateBy(CED.getLoginUser().getEmpCd());
                oldData.setUpdateOrgCd(CED.getLoginUser().getOrgCd());
                //当前产品中当前资料清单配置是否已经配置查看权限
                checkAuth(oldData);
                return this.fileStoreRepository.updateEntity(oldData);
            } else {
                throw new BusinessException("10010003", "传入参数有误");
            }
        } else {
            throw new BusinessException("10010004", "未传入相关参数");
        }
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public FileStore saveFileStore(FileStore fileStore) throws Exception {
        if (ObjectHelper.isNotEmpty(fileStore)) {
            fileStore = this.checkAuth(fileStore);
            fileStore.setCreateBy(CED.getLoginUser().getEmpCd());
            fileStore.setCreateOrgCd(CED.getLoginUser().getOrgCd());
            fileStore.setUpdateBy(CED.getLoginUser().getEmpCd());
            fileStore.setUpdateOrgCd(CED.getLoginUser().getOrgCd());
            fileStore.setUpdateTime(new Date());
            fileStore.setFileType(ENUM_MEDIA_TYPE.getClassType(fileStore.getFileName().substring(fileStore.getFileName().lastIndexOf(".")) + ","));
            fileStore.setDocumentName(fileStore.getFileName().substring(0, fileStore.getFileName().lastIndexOf(".")));
            return this.saveEntity(fileStore);
        } else {
            throw new BusinessException("10010004", "未传入相关参数");
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public FileStore saveOrUpdateFileStore(FileStore fileStore) throws Exception {
        if (ObjectHelper.isNotEmpty(fileStore)) {
            if (ObjectHelper.isNotEmpty(fileStore.getId())) {
                return this.updateFileStore(fileStore);
            } else {
                return this.saveFileStore(fileStore);
            }
        } else {
            throw new BusinessException("10010004", "未传入相关参数");
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveAllDraft(String caseApplayCode, String linkCode, String businessId) throws Exception {
        if (ObjectHelper.isNotEmpty(linkCode)) {
            if (ObjectHelper.isNotEmpty(caseApplayCode) && ObjectHelper.isEmpty(businessId)) {
                this.fileStoreRepository.saveAllDraftWithCase(caseApplayCode, linkCode);
            }
            else if (ObjectHelper.isNotEmpty(businessId) && ObjectHelper.isEmpty(caseApplayCode)) {
                this.fileStoreRepository.saveAllDraftWithBusi(businessId, linkCode);
            }
            else if (ObjectHelper.isNotEmpty(businessId) && ObjectHelper.isNotEmpty(caseApplayCode)) {
                this.fileStoreRepository.saveAllDraftWithBusiAndCase(businessId, caseApplayCode, linkCode);
            } else {
                throw new BusinessException("10010003", "传入参数有误");
            }
        } else {
            throw new BusinessException("10010004", "未传入相关数据");
        }
    }

    @Override
    public List<AttachmentDto> findAttachmentsByIds(String ids) throws Exception {
        if (ObjectHelper.isNotEmpty(ids)) {
            List<AttachmentDto> attachments = new ArrayList<AttachmentDto>(20);
            String[] idArra = ids.split(",");
            for (int i = 0; i < idArra.length; i++) {
                AttachmentDto atta = this.CED.findAttachmentById(idArra[i]);
                attachments.add(atta);
            }
            return attachments;
        } else {
            throw new BusinessException("10010004", "未传入相关参数");
        }
    }

    @Override
    public List<FileStore> findByBusinessIdAndLinkCode(String businessId, String linkCode) throws BusinessException {
        if (ObjectHelper.isNotEmpty(businessId) && ObjectHelper.isNotEmpty(linkCode)) {
            List<FileStore> sourceData = this.fileStoreRepository.findByBusinessIdAndLinkCode(businessId, linkCode);
            if (ObjectHelper.isNotEmpty(sourceData)) {
                return sourceData;
            } else {
                throw new BusinessException("10010002", "根据参数未找到相应数据");
            }
        } else {
            throw new BusinessException("10010004", "未传入相关参数");
        }
    }

    @Override
    public List<FileStore> findByMateriaCode(String caseApplyId, String businessId, String materiaCode) throws Exception {
        if (ObjectHelper.isNotEmpty(caseApplyId) && ObjectHelper.isNotEmpty(businessId) && ObjectHelper.isNotEmpty(materiaCode)) {
            //查找案件 通过案件得到产品ID
            CaseApply caseApply = this.caseApplyService.findOne(caseApplyId);
            if (ObjectHelper.isEmpty(caseApply.getProductSubtypeId())) {
                throw new BusinessException("10010002", "根据参数未找到相应数据，根据案件未找到相应产品");
            }
            //得到产品ID
            String productId = caseApply.getProductSubtypeId();
            //根据产品ID和资料编号（materiaCode）得到资料清单
            MaterialList materialList = this.materiaListService.findByProductIdAndMateriaCode(productId, materiaCode);
            //查找资料库
            List<FileStore> sourceData = this.findByMateriaIdAndCaseApplyIdAndBusinessId(caseApply.getId(), businessId, materialList.getId());
            return sourceData;
        } else {
            throw new BusinessException("10010004", "未传入相关参数，按照资料清单资料编号查找附件出错");
        }
    }

    @Override
    public List<FileStore> findByMateriaIdAndCaseApplyIdAndBusinessId(String caseApplyId, String businessId, String materiaId) throws BusinessException {
        if (ObjectHelper.isNotEmpty(caseApplyId) && ObjectHelper.isNotEmpty(businessId) && ObjectHelper.isNotEmpty(materiaId)) {
            List<FileStore> sourceData = this.fileStoreRepository.findByCaseApplyIdAndMateriaIdAndBusinessIdOrderByCreateTimeDesc(caseApplyId, materiaId, businessId);
            if (ObjectHelper.isNotEmpty(sourceData) && sourceData.size() > 0) {
                return sourceData;
            } else {
                throw new BusinessException("10010003", "根据参数未找到相应数据，根据参数未找到资料库数据");
            }
        } else {
            throw new BusinessException("10010004", "未传入相关参数，通过资料ID、案件编号、业务表单ID查找资料库出错");
        }
    }

    @Override
    public List<FileStore> findWithMateriaIdAndCaseApplyIdAndBusinessId(String caseApplyId, String businessId, String materiaId) {
        if (ObjectHelper.isNotEmpty(caseApplyId) && ObjectHelper.isNotEmpty(businessId) && ObjectHelper.isNotEmpty(materiaId)) {
            return this.fileStoreRepository.findByCaseApplyIdAndMateriaIdAndBusinessIdOrderByCreateTimeDesc(caseApplyId, materiaId, businessId);
        }
        return null;
    }

    @Override
    public FileStore saveForCredit(String caseApplyId, String fileName, String businessId, String attachmentId, String linkCode) throws Exception {
        //查找案件 通过案件得到产品ID
        CaseApply caseApply = this.caseApplyService.findOne(caseApplyId);
        if (ObjectHelper.isEmpty(caseApply.getProductSubtypeId())) {
            throw new BusinessException("10010002", "根据参数未找到相应数据，根据案件未找到相应产品");
        }
        if (ObjectHelper.isEmpty(attachmentId)) {
            throw new BusinessException("10010004", "未传入相关参数，文件库保存征信报告出错");
        }
        if (ObjectHelper.isEmpty(fileName)) {
            throw new BusinessException("10010004", "未传入相关参数，文件库保存征信信息出错，文件名为空");
        }
        //得到产品ID
        String productId = caseApply.getProductSubtypeId();
        FileStore fileStore = new FileStore();
        fileStore.setCaseApplyId(caseApply.getId());
        fileStore.setBusinessId(businessId);
        fileStore.setFileName(fileName);
        fileStore.setProductId(productId);
        fileStore.setLinkCode(linkCode);
        //获得清单ID
        fileStore = this.checkAuthWithoutLinkCode(fileStore);
        EmpDto empDto = null;
        try {
            //接口进来获取不到登录人 直接跳过
            empDto = CED.getLoginUser();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (ObjectHelper.isNotEmpty(empDto)) {
            fileStore.setCreateBy(empDto.getEmpCd());
            fileStore.setCreateOrgCd(empDto.getOrgCd());
            fileStore.setUpdateBy(empDto.getEmpCd());
            fileStore.setUpdateOrgCd(empDto.getOrgCd());
        }
        fileStore.setUpdateTime(new Date());
        fileStore.setFileType(ENUM_MEDIA_TYPE.getClassType(fileStore.getFileName().substring(fileStore.getFileName().lastIndexOf(".")) + ","));
        fileStore.setDocumentName(fileStore.getFileName().substring(0, fileStore.getFileName().lastIndexOf(".")));
        fileStore.setAttachmentId(attachmentId);
        return this.saveEntity(fileStore);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<FileStore> saveAndUploadForApp(CommonsMultipartFile[] multipartfiles, FileStore fileStore, String token) throws Exception {
        //验证参数的有效性
        this.checkParams(multipartfiles, fileStore);
        //上传数据
        List<AttachmentDto> attachmentDtos = appAttachmentService.saveAttachment(multipartfiles, token);
        //查找资料清单数据
        MaterialList materiaList = this.materiaListService.findMateriaListById(fileStore.getMateriaId());
        //删除已经上传的文件
        List<FileStore> oldDatas=this.findByCondition(fileStore);
        try{
            this.batchLogicDeleteFileStore(oldDatas);
        }catch (BusinessException e){
            logger.error("批量删除已上传数据出错",e);
        }
        //查找案件信息
        CaseApply caseApply = this.caseApplyService.findOne(fileStore.getCaseApplyId());
        //定义批量保存临时集合
        List<FileStore> fileStoreTempList = new ArrayList<FileStore>();
        //保存数据到文件库
        for (AttachmentDto temp : attachmentDtos) {
            FileStore fileStoreSave = new FileStore();
            fileStoreSave.setLinkCode(ObjectHelper.isNotEmpty(fileStore.getLinkCode()) ? fileStore.getLinkCode() : "");
            fileStoreSave.setAttachmentId(temp.getId());
            fileStoreSave.setProductId(ObjectHelper.isNotEmpty(caseApply.getProductSubtypeId()) ? caseApply.getProductSubtypeId() : "");
            fileStoreSave.setFileName(materiaList.getMateriaName() + temp.getFileLabel().substring(temp.getFileLabel().lastIndexOf(".")));
            fileStoreSave.setBusinessId(ObjectHelper.isNotEmpty(fileStore.getBusinessId()) ? fileStore.getBusinessId() : "");
            fileStoreSave.setCaseApplyId(fileStore.getCaseApplyId());
            fileStoreSave.setDocumentName(materiaList.getMateriaName());
            fileStoreSave.setFileType(ENUM_MEDIA_TYPE.getClassType(fileStoreSave.getFileName().substring(fileStoreSave.getFileName().lastIndexOf(".")) + ","));
            fileStoreSave.setLatitude(ObjectHelper.isNotEmpty(fileStore.getLatitude()) ? fileStore.getLatitude() : null);
            fileStoreSave.setLongitude(ObjectHelper.isNotEmpty(fileStore.getLongitude()) ? fileStore.getLongitude() : null);
            fileStoreSave.setMateriaId(materiaList.getId());
            fileStoreSave.setSourceCode("");
            fileStoreSave.setStatus(ENUM_USING_STATUS.USING.value);
            try {
                fileStoreSave.setCreateOrgCd(CED.getLoginUser().getOrgCd());
                fileStoreSave.setCreateBy(CED.getLoginUser().getEmpCd());
            } catch (Exception e) {
                logger.error("APP上传文件并保存数据出错", e);
                e.printStackTrace();
            }
            fileStoreTempList.add(fileStoreSave);
        }
        return this.fileStoreRepository.batchSave(fileStoreTempList);
    }

    @Override
    public List<Map<String, Object>> findProductMateriaWithLinkCode(FileStore fileStore) throws Exception {
        List<Map<String,Object>> returnData=new ArrayList<Map<String, Object>>();
        if(ObjectHelper.isNotEmpty(fileStore)&&ObjectHelper.isNotEmpty(fileStore.getLinkCode())&&ObjectHelper.isNotEmpty(fileStore.getProductId())){
            List<FileStore> sourceData=this.fileStoreRepository.findProductMateriaWithLinkCode(fileStore);
            if(ObjectHelper.isNotEmpty(sourceData)&&sourceData.size()>0){
                //先按照大类分类
                Map<String,List<FileStore>> parentAndChild=new HashMap<String,List<FileStore>>();
                for(FileStore temp:sourceData){
                    if(!parentAndChild.containsKey(temp.getMateriaTypeCode())){
                        List<FileStore> child=new ArrayList<FileStore>();
                        child.add(temp);
                        parentAndChild.put(temp.getMateriaTypeCode(),child);
                    }else{
                        parentAndChild.get(temp.getMateriaTypeCode()).add(temp);
                    }
                }
                //组装成返回结果
                for(String temp:parentAndChild.keySet()){
                    Map<String,Object> rowData = new HashMap<String,Object>();
                    List<Map<String,Object>> rowChildData = new ArrayList<Map<String, Object>>();
                    //先组装子元素
                    for(FileStore tempChild:parentAndChild.get(temp)){
                        Map<String,Object> childData=new HashMap<String,Object>();
                        childData.put("id",tempChild.getMateriaCode());
                        childData.put("text",tempChild.getMateriaCodeName());

                        rowChildData.add(childData);
                    }
                    rowData.put("id",parentAndChild.get(temp).get(0).getMateriaTypeCode());
                    rowData.put("text",parentAndChild.get(temp).get(0).getMateriaTypeCodeName());
                    rowData.put("child",rowChildData);

                    returnData.add(rowData);
                }
            }
            return returnData;
        }else{
            throw new BusinessException("10010004","未传入相关参数，资料库查找资料配置数据出错");
        }
    }

    /**
     * @param fileStore
     * @return com.zdsoft.finance.filestore.entity.FileStore
     * @throws
     * @Title: checkAuth
     * @Description: 检查查看权限 若有权限则将资料清单ID赋值给资料库
     * @author liaoguowei
     */
    private FileStore checkAuth(FileStore fileStore) throws Exception {
        if (ObjectHelper.isNotEmpty(fileStore) && ObjectHelper.isNotEmpty(fileStore.getLinkCode())) {
            //查找相应产品的资料清单设置
            MaterialList materialList = null;
            //当为保存时 根据文件名来进行查找
            if (ObjectHelper.isEmpty(fileStore.getId())) {
                materialList = this.analyzeFileName(fileStore.getProductId(), fileStore.getFileName());
            } else {//当为修改时 按类别编号进行查找
                materialList = this.analyzeMateriaCode(fileStore);
            }
            if(ObjectHelper.isNotEmpty(materialList)){
                //查找权限配置
                this.materiaListLimitsService.findWithMateriaLimitLike(fileStore.getProductId(),materialList.getMateriaTypeCode(),fileStore.getLinkCode());
                fileStore.setMateriaId(materialList.getId());
            }
            return fileStore;
        } else {
            throw new BusinessException("10010004", "未传入相关数据，检查产品查看权限出错");
        }

    }

    /**
     * @param fileStore
     * @return com.zdsoft.finance.filestore.entity.FileStore
     * @throws
     * @Title: checkAuth
     * @Description: 检查查看权限 若有权限则将资料清单ID赋值给资料库
     * @author liaoguowei
     */
    private FileStore checkAuthWithoutLinkCode(FileStore fileStore) throws Exception {
        if (ObjectHelper.isNotEmpty(fileStore)) {
            //查找相应产品的资料清单设置
            MaterialList materialList = null;
            //当为保存时 根据文件名来进行查找
            if (ObjectHelper.isEmpty(fileStore.getId())) {
                materialList = this.analyzeFileName(fileStore.getProductId(), fileStore.getFileName());
            } else {//当为修改时 按类别编号进行查找
                materialList = this.analyzeMateriaCode(fileStore);
            }
            if (ObjectHelper.isEmpty(materialList)) {
                throw new BusinessException("10010002", "根据参数未找到相应配置！在当前产品上未找到相应资料配置");
            }
            fileStore.setMateriaId(materialList.getId());
            return fileStore;
        } else {
            throw new BusinessException("10010004", "未传入相关配置");
        }

    }

    /**
     * @param fileStore
     * @return void
     * @throws BusinessException
     * @Title: checkParams
     * @Description: APP文件上传参数检查
     * @author liaoguowei
     */
    private void checkParams(CommonsMultipartFile[] multipartfiles, FileStore fileStore) throws BusinessException {
        //文件流
        if (ObjectHelper.isEmpty(multipartfiles)) {
            throw new BusinessException("10010004", "未传入相关参数，APP文件库上传文件并保存出错");
        }
        //环节编号
        if (ObjectHelper.isEmpty(fileStore.getLinkCode())) {
            throw new BusinessException("10010004", "未传入相关参数，APP文件库上传文件并保存未传入环节编号");
        }
        //案件号
        if (ObjectHelper.isEmpty(fileStore.getCaseApplyId())) {
            throw new BusinessException("10010004", "未传入相关参数，APP文件库上传文件并保存未传入案件号");
        }
        //资料ID
        if (ObjectHelper.isEmpty(fileStore.getMateriaId())) {
            throw new BusinessException("10010004", "未传入相关参数，APP文件库上传并保存未传入资料ID");
        }
    }

    /**
     * 解析文件名 并返回与之相应的资料
     *
     * @param fileOriName
     * @return
     */
    private MaterialList analyzeFileName(String productId, String fileOriName) throws BusinessException {
        if (ObjectHelper.isNotEmpty(fileOriName) && ObjectHelper.isNotEmpty(productId)) {
            try{
                //fileTypeName包含中文、助记码、数字助记码
                String fileTypeName = fileOriName.substring(0, fileOriName.indexOf("+"));
                try{
                    logger.info("--------------------------------解析文件名传递参数：productId="+productId+"                类型名称="+fileTypeName);
                    MaterialList sourceData=this.materiaListService.findByProductAndClass(productId, fileTypeName);
                    return sourceData;
                }catch (java.lang.IndexOutOfBoundsException e){
                    e.printStackTrace();
                    throw new BusinessException("10010003","传入参数有误！请检查文件命名");
                }
            }catch (StringIndexOutOfBoundsException e){
                logger.error("解析文件名出错",e);
                return null;
            }
        } else {
            throw new BusinessException("10010004", "未传入相关参数");
        }
    }

    /**
     * 解析类别
     *
     * @param fileStore
     * @return
     */
    private MaterialList analyzeMateriaCode(FileStore fileStore) throws Exception {
        if (ObjectHelper.isNotEmpty(fileStore) && ObjectHelper.isNotEmpty(fileStore.getProductId())) {
            MaterialList materialList = null;
            if (ObjectHelper.isNotEmpty(fileStore.getMateriaCode()) && ObjectHelper.isEmpty(fileStore.getMateriaTypeCode())) {
                materialList = this.materiaListService.findByProductIdAndMateriaCode(fileStore.getProductId(), fileStore.getMateriaCode());
            } else if (ObjectHelper.isNotEmpty(fileStore.getMateriaTypeCode()) && ObjectHelper.isNotEmpty(fileStore.getMateriaCode())) {
                materialList = this.materiaListService.findByMateriaTypeCdAndProductIdAndMateriaCd(fileStore.getMateriaTypeCode(), fileStore.getProductId(), fileStore.getMateriaCode());
            } else {
                throw new BusinessException("10010003", "传入参数有误");
            }
            if (ObjectHelper.isNotEmpty(materialList)) {
                return materialList;
            } else {
                throw new BusinessException("10010002", "根据参数未找到相应配置,当前产品在此节点无权限");
            }
        } else {
            throw new BusinessException("10010004", "未传入相关参数！");
        }
    }

	@Override
	public List<FileStore> findByCaseApplyIdAndBusinessIdAndLinkCode(String caseApplyId, String businessId,
			String linkCode) throws Exception {
		if(ObjectHelper.isEmpty(caseApplyId) || ObjectHelper.isEmpty(businessId) || ObjectHelper.isEmpty(linkCode)){
			throw new BusinessException("10010003", "传入参数为空");
		}
		return this.customReposity.findByCaseApplyIdAndBusinessIdAndLinkCode(caseApplyId, businessId, linkCode);
	}

    @Override
    public List<MaterialListType> findParentMaterial() {
        return this.materialListTypeRepository.findParentMateria();
    }
}
