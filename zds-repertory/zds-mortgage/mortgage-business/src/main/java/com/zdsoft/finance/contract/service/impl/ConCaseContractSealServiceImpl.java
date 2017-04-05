package com.zdsoft.finance.contract.service.impl;

import com.zdsoft.essential.client.controller.UploaderController;
import com.zdsoft.essential.client.service.CED;
import com.zdsoft.essential.dto.basedata.AttachmentDto;
import com.zdsoft.essential.dto.emp.EmpDto;
import com.zdsoft.finance.base.service.impl.BaseServiceImpl;
import com.zdsoft.finance.busiform.entity.BusiForm;
import com.zdsoft.finance.busiform.entity.BusiFormStatus;
import com.zdsoft.finance.busiform.service.BusiFormService;
import com.zdsoft.finance.common.base.ConstantParameter;
import com.zdsoft.finance.common.base.QueryObj;
import com.zdsoft.finance.common.enums.ENUM_MEDIA_TYPE;
import com.zdsoft.finance.common.enums.ENUM_USING_STATUS;
import com.zdsoft.finance.common.enums.busiform.ApplyModelTypeEnum;
import com.zdsoft.finance.contract.entity.ConCaseContract;
import com.zdsoft.finance.contract.entity.ConCaseContractSeal;
import com.zdsoft.finance.contract.entity.ConCaseContractSealDetail;
import com.zdsoft.finance.contract.repository.ConCaseContractRepository;
import com.zdsoft.finance.contract.repository.ConCaseContractSealDetailRepository;
import com.zdsoft.finance.contract.repository.ConCaseContractSealRepository;
import com.zdsoft.finance.contract.service.ConCaseContractSealService;
import com.zdsoft.finance.contract.service.ConCaseContractService;
import com.zdsoft.finance.filestore.entity.FileStore;
import com.zdsoft.finance.filestore.service.FileStoreService;
import com.zdsoft.finance.marketing.entity.CaseApply;
import com.zdsoft.finance.marketing.repository.CaseApplyRepository;
import com.zdsoft.finance.product.entity.MaterialList;
import com.zdsoft.finance.product.service.MateriaListService;
import com.zdsoft.finance.product.service.MaterialListTypeService;
import com.zdsoft.framework.core.common.configure.AppParameter;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.PageRequest;
import com.zdsoft.framework.core.common.util.ObjectHelper;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.tools.zip.ZipUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.zip.ZipOutputStream;

/**
 * 版权所有：重庆正大华日软件有限公司
 *
 * @author denglw
 * @version V1.0
 * @Title: ConCaseContractSealServiceImpl.java
 * @ClassName: ConCaseContractSealServiceImpl
 * @Description: 案件合同盖章
 * @date 2017年3月15日 下午4:14:53
 */
@Service("conCaseContractSealService")
public class ConCaseContractSealServiceImpl extends BaseServiceImpl<ConCaseContractSeal, ConCaseContractSealRepository>
        implements ConCaseContractSealService {
    @Autowired
    private ConCaseContractSealDetailRepository sealDetailRepository;

    @Autowired
    private CaseApplyRepository caseApplyRepository;

    @Autowired
    private ConCaseContractService contractService;
    @Autowired
    private BusiFormService busiFormService;
    @Autowired
    private FileStoreService fileStoreService;
    @Autowired
    private MateriaListService materiaListService;
    @Autowired
    private CED CED;

    @Override
    public Page<Map<String, Object>> getContractList(PageRequest pageable, List<QueryObj> queryObjs) throws Exception {
        return customReposity.getContractList(pageable, queryObjs);
    }

    @Override
    @Transactional
    public ConCaseContractSeal saveOrUpdateEntity(ConCaseContractSeal contractSeal, List<ConCaseContractSealDetail> sealDetails, String fileUuid) throws Exception {
        String sealId = contractSeal.getId();
        EmpDto empDto = CED.getLoginUser();
        if (ObjectHelper.isEmpty(sealId)) {
            contractSeal.setCreateBy(empDto.getEmpCd());
            contractSeal.setCreateOrgCd(empDto.getOrgCd());
            //盖章申请信息
            contractSeal = this.saveEntity(contractSeal);
            //盖章申请明细
            for (ConCaseContractSealDetail sealDetail : sealDetails) {
                sealDetail.setCaseContractSealId(contractSeal.getId());
                sealDetailRepository.saveEntity(sealDetail);
            }
            //合同
            ConCaseContract contract = contractService.findOne(contractSeal.getCaseContractId());
            //案件
            CaseApply caseApply = caseApplyRepository.getOne(contract.getCaseApplyId());
            //得到附件Linkcode
            String stage = caseApply.getStage();
            if (stage != null && !"".equals(stage)) {
                stage = stage.substring(stage.length() - 2, stage.length());
            }
            //新增时，固定businessID,保存时修改附件businessId
            List<FileStore> fileStores = fileStoreService.findByCaseApplyIdAndBusinessIdAndLinkCode(caseApply.getId(), fileUuid, stage);
            for (FileStore fileStore : fileStores) {
                fileStore.setBusinessId(contractSeal.getId());
                fileStoreService.updateEntity(fileStore);
            }
        } else {
            ConCaseContractSeal dbContractSeal = customReposity.findOne(sealId);
            BeanUtils.copyProperties(contractSeal, dbContractSeal,
                    new String[]{"version",
                            "isDeleted", "createTime", "deleteTime"});
            contractSeal = this.updateEntity(dbContractSeal);
            //页面未记录操作信息，直接删除原记录重新保存
            sealDetailRepository.deleteByCaseContractSealId(contractSeal.getId());
            for (ConCaseContractSealDetail sealDetail : sealDetails) {
                sealDetail.setCaseContractSealId(contractSeal.getId());
                sealDetailRepository.saveEntity(sealDetail);
            }
        }
        return contractSeal;
    }

    @Override
    @Transactional
    public ConCaseContractSeal saveOrUpdateBill(ConCaseContractSeal contractSeal, List<ConCaseContractSealDetail> sealDetails, boolean submitStatus, String fileUuid) throws Exception {
        //保存盖章信息
        contractSeal = this.saveOrUpdateEntity(contractSeal, sealDetails, fileUuid);
        //合同
        ConCaseContract contract = contractService.findOne(contractSeal.getCaseContractId());
        //案件
        CaseApply caseApply = caseApplyRepository.getOne(contract.getCaseApplyId());
        EmpDto empDto = CED.getLoginUser();
        //工作流基础信息
        BusiForm busiForm = contractSeal.getBusiForm();
        if (ObjectHelper.isEmpty(busiForm)) {
            busiForm = new BusiForm();
            // 状态
            busiForm.setFormStatus(BusiFormStatus.DRAFT.value);
            busiForm.setLaunchEmpCode(empDto.getEmpCd());
            busiForm.setLaunchEmpName(empDto.getEmpNm());
            // 还款计划：标题 案件号+产品 业务编号：案件号
            busiForm.setApplyTitle("督办-" + caseApply.getCaseApplyCode());
            busiForm.setBusinessCode(caseApply.getCaseApplyCode());
            // 关联业务表单id
            busiForm.setBusinessEntityId(contractSeal.getId());
            // 关联表单域对象类名 例如表单为Project时 此字段存入的值为Project
            busiForm.setBusinessEntityName(ConCaseContractSeal.class.getSimpleName());
            // 关联组件数据ID 例如 项目表ID 合同ID
            busiForm.setComponentsEntityId(contractSeal.getCaseContractId());
            // 关联组件域对象类名 例如表单为Project时 此字段存入的值为Project
            busiForm.setComponentsEntityName(ConCaseContract.class.getSimpleName());
            // 所属模块
            busiForm.setModelType(ApplyModelTypeEnum.SEAL_CONTRACT_APPLY.value);
            busiForm.setApplyTitle(contract.getContractNo() + "_" + contractSeal.getApplyType());
            busiForm = busiFormService.saveBusiForm(busiForm);
            contractSeal.setBusiForm(busiForm);
            contractSeal = this.saveOrUpdateEntity(contractSeal);
        }
        if (submitStatus) {
            // 启动流程
            Map<String, String> businessVarible = new HashMap<String, String>();
            businessVarible.put("contracSealId", contractSeal.getId());
            // 功能代码
            busiForm.setProcessKey("合同盖章流程");
            // 产品id
            busiForm.setProductId(caseApply.getProductSubtypeId());
            // 启动流程
            busiForm = busiFormService.startProcess(busiForm, businessVarible, null);
        }
        return contractSeal;
    }

    @Override
    public ConCaseContractSeal saveTrackingNoSend(String id, String trackingNoSend) throws Exception {
        ConCaseContractSeal contractSeal = customReposity.findOne(id);
        if (contractSeal != null) {
            contractSeal.setTrackingNoSend(trackingNoSend);
            customReposity.updateEntity(contractSeal);
        }
        return contractSeal;
    }

    @Override
    public ConCaseContractSeal saveTrackingNoReceive(String id, String trackingNoReceive) throws Exception {
        ConCaseContractSeal contractSeal = customReposity.findOne(id);
        if (contractSeal != null) {
            contractSeal.setTrackingNoReceive(trackingNoReceive);
            customReposity.updateEntity(contractSeal);
        }
        return contractSeal;
    }

    @Override
    public synchronized void importContractFile(String contractDetailIds, String businessId, String caseApplyId, String linkCode, String productId) throws Exception {
        if (ObjectHelper.isNotEmpty(contractDetailIds)) {
            String[] ids = contractDetailIds.split(",");
            List<String> attIds = new ArrayList<>();
            for(String detailId:ids){
                Map<String,Object> detailMap =  contractService.getConCaseContractDetailById(detailId);
                attIds.add(detailMap.get("ATTACHMENTID").toString());
            }
            List<AttachmentDto> attas = CED.findAttachmentByIds(attIds);
            String zipDirPath = AppParameter.getUploadFolder() + "tempZip" + File.separator + UUID.randomUUID().toString().replace("-", "");
            File zipDir = new File(zipDirPath);
            if (zipDir.isDirectory() && zipDir.exists()) {
                FileUtils.deleteDirectory(zipDir);
            }
            String fileNameStr = "合同其它类资料.zip";
            zipDir.mkdirs();
            //拷贝文件到指定目录
            for (AttachmentDto atta : attas) {
                String filePath = atta.getFilePath();
                String label = atta.getFileLabel();
                FileUtils.copyFile(new File(filePath), new File(zipDirPath + File.separator + label));
            }
            String zipFileName = AppParameter.getUploadFolder() + "tempZip" + File.separator + fileNameStr;
            File zipFile = new File(zipFileName);
            if (zipFile.isFile() && zipFile.exists()) {
                zipFile.delete();
            }
            //压缩文件
            this.zip(zipFileName, zipDir);
            File curZipFile = new File(zipFileName);
            try {
                EmpDto loginUser = CED.getLoginUser();
                //上传文件到平台
                AttachmentDto dto = this.saveAttachment(curZipFile, fileNameStr, loginUser);
                MaterialList materialList = materiaListService.findByProductIdAndMateriaCode(productId, "YWDM001500601");//其它
                //上传到公共附件
                FileStore fileStore = new FileStore();
                fileStore.setAttachmentId(dto.getId());
                fileStore.setFileName(fileNameStr);
                fileStore.setCaseApplyId(caseApplyId);
                fileStore.setBusinessId(caseApplyId);
                fileStore.setLinkCode(linkCode);
                fileStore.setCreateBy(loginUser.getEmpCd());
                fileStore.setFileType(ENUM_MEDIA_TYPE.getClassType("pdf"));
                fileStore.setProductId(productId);
                fileStore.setCreateOrgCd(loginUser.getOrgCd());
                fileStore.setDocumentName("");
                fileStore.setStatus(ENUM_USING_STATUS.USING.value);
                fileStore.setMateriaId(materialList.getId());
                fileStore.setUpdateTime(new Date());
                fileStore.setUpdateBy(loginUser.getEmpCd());
                fileStoreService.saveOrUpdateEntity(fileStore);
            } catch (Exception e) {
                e.printStackTrace();
                logger.error("上传文件失败");
            } finally {
                if (curZipFile != null && curZipFile.exists()) {
                    curZipFile.delete();
                }
                if (zipDir != null && zipDir.exists()) {
                    FileUtils.deleteDirectory(zipDir);
                }
            }

        }
    }

    /**
     * @param file
     * @throws Exception
     * @Title: saveAttachment
     * @Description: 上传到平台
     * @author denglw
     */
    @Transactional
    private AttachmentDto saveAttachment(File file, String fileName, EmpDto loginUser) throws Exception {
        if (ObjectHelper.isNotEmpty(file) && file.exists()) {
            AttachmentDto dto = new AttachmentDto();
            dto.setFileLabel(fileName);
            dto.setFileNm(UUID.randomUUID().toString() + ".zip");
            dto.setFileType("zip");
            //创建人
            dto.setOwner(loginUser.getEmpCd());
            String savePath = UploaderController.getUploadFolder();
            logger.debug("uploadFolder:[{}]", savePath);
            if (savePath == null) {
                UploaderController.setUploadFolder(AppParameter.getUploadFolder());
                logger.warn("Attention!!!!!!,uploadFolder is not be setted,reset with the default path:[{}]",
                        AppParameter.getUploadFolder());
            }
            DateFormat format = new SimpleDateFormat("yyyy");
            savePath = FilenameUtils.concat(UploaderController.getUploadFolder() + File.separator + format.format(new Date()),
                    dto.getFileNm());
            dto.setFilePath(savePath);
            dto.setIsTemp(false);
            File outFile = new File(dto.getFilePath());
            FileUtils.copyInputStreamToFile(new FileInputStream(file), outFile);
            dto = CED.saveAttachment(dto);
            dto.setFilePath(ConstantParameter.getAppDownloadUrl() + dto.getFilePath());
            return dto;
        }
        return null;
    }

    /**
     * @param zipFileName
     * @param inputFile
     * @throws Exception
     * @Title: zip
     * @Description: 压缩文件或文件夹
     * @author denglw
     */
    private void zip(String zipFileName, File inputFile) throws Exception {
        ZipOutputStream out = null;
        try {
            out = new ZipOutputStream(new FileOutputStream(
                    new String(zipFileName.getBytes("gb2312"))));
            zip(out, inputFile, "");
        } catch (Exception e) {
            logger.error("压缩文件失败!");
        } finally {
            if (out != null) {
                out.close();
            }
        }

    }

    /**
     * @param out
     * @param f
     * @param base
     * @throws Exception
     * @Title: zip
     * @Description: 压缩文件或文件夹
     * @author denglw
     */
    private void zip(ZipOutputStream out, File f, String base) throws Exception {
        if (f.isDirectory()) {
            File[] fl = f.listFiles();
            base = base.length() == 0 ? "" : base + "/";
            for (int i = 0; i < fl.length; i++) {
                zip(out, fl[i], base + fl[i].getName());
            }
        } else {
            out.putNextEntry(new org.apache.tools.zip.ZipEntry(base));
            FileInputStream in = new FileInputStream(f);
            int b;
            while ((b = in.read()) != -1)
                out.write(b);
            in.close();
        }
    }
}
