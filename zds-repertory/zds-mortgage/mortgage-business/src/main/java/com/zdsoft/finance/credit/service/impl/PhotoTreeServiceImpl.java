package com.zdsoft.finance.credit.service.impl;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.zdsoft.essential.client.controller.UploaderController;
import com.zdsoft.essential.client.service.CED;
import com.zdsoft.essential.dto.basedata.AttachmentDto;
import com.zdsoft.essential.dto.emp.EmpDto;
import com.zdsoft.finance.base.service.impl.BaseServiceImpl;
import com.zdsoft.finance.common.base.ConstantParameter;
import com.zdsoft.finance.common.enums.ENUM_MEDIA_TYPE;
import com.zdsoft.finance.common.enums.ENUM_USING_STATUS;
import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.finance.common.utils.ZUIPdfUtil;
import com.zdsoft.finance.credit.service.PhotoTreeService;
import com.zdsoft.finance.filestore.entity.FileStore;
import com.zdsoft.finance.filestore.service.FileStoreService;
import com.zdsoft.finance.marketing.entity.CaseApply;
import com.zdsoft.finance.marketing.service.CaseApplyService;
import com.zdsoft.finance.product.entity.MaterialList;
import com.zdsoft.finance.product.service.MateriaListService;
import com.zdsoft.finance.shoot.entity.PhotoTree;
import com.zdsoft.finance.shoot.repository.PhotoTreeRepository;
import com.zdsoft.framework.core.common.util.ObjectHelper;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * 版权所有：重庆正大华日软件有限公司
 *
 * @author panshm
 * @version V1.0
 * @Title: PhotoTreeServiceImpl.java
 * @ClassName: PhotoTreeServiceImpl
 * @Description: 资调摄像照片管理 Service实现类
 * @date 2017年3月6日 下午2:41:21
 */
@Service
public class PhotoTreeServiceImpl extends BaseServiceImpl<PhotoTree, PhotoTreeRepository> implements PhotoTreeService {

    @Autowired
    private CED CED;

    @Autowired
    private FileStoreService fileStoreService;

    @Autowired
    private CaseApplyService caseApplyService;

    @Autowired
    private MateriaListService materiaListService;

    @Override
    public List<PhotoTree> findShootPhotoInfo(String caseApplyCode, String linkCode) {
        return this.customReposity.findByCaseApplyCodeAndLinkCode(caseApplyCode, linkCode);
    }

    @Override
    public List<PhotoTree> findShootPhotoDetail(String caseApplyCode, String linkCode, String allNodeStr) {
        return this.customReposity.findByCaseApplyCodeAndLinkCodeAndAllNodeStrLike(caseApplyCode, linkCode, allNodeStr);
    }

    @Override
    @Transactional
    public void saveShootPhotoDatas(List<PhotoTree> addList, String isOver) throws Exception {
        // 保存PhotoTree数据
        EmpDto loginUser = CED.getLoginUser();
        for (PhotoTree entity : addList) {
            String typeNodeStr = entity.getAllNodeStr();
            String[] typeList = typeNodeStr.split("-");
            int i = 0;
            for (String type : typeList) {
                if (ObjectHelper.isEmpty(type))
                    break;
                if (i == 0) {
                    entity.setType1Name(type);
                    i++;
                    continue;
                }
                if (i == 1) {
                    entity.setType2Name(type);
                    i++;
                    continue;
                }
                if (i == 2) {
                    entity.setType3Name(type);
                    i++;
                    continue;
                }
                if (i == 3) {
                    entity.setType4Name(type);
                    i++;
                    continue;
                }
                if (i == 4) {
                    entity.setType5Name(type);
                    i++;
                    continue;
                }
            }
            entity.setCreateBy(loginUser.getAccount());
            entity.setCreateOrgCd(loginUser.getOrgCd());
            // 文件生成位置：/mnt/upload/tempShoot/aaaaa.jpg
            String savePath = FilenameUtils.concat(ConstantParameter.getShootPhotoServerFolderPath(),
                    entity.getFileName());
            entity.setPhotoPath(savePath);
            //查询是否已存在此文件的记录
            int count = customReposity.countByCaseApplyCodeAndAllNodeStrAndPhotoPath(entity.getCaseApplyCode(), entity.getAllNodeStr(), entity.getPhotoPath());
            entity.setPhotoUrl(ConstantParameter.getShootPhotoRelativePath() + File.separator + entity.getFileName());
            if (count == 0) {
                this.saveOrUpdateEntity(entity);
            }
        }
    }

    @Override
    @Transactional
    public void deleteShootPhotos(List<PhotoTree> delList) throws BusinessException {
        for (PhotoTree photoTree : delList) {
            photoTree = this.customReposity.findOne(photoTree.getId());
            this.customReposity.delete(photoTree);
        }
    }

    @Override
    @Transactional
    public void saveShootPhotoFiles(CommonsMultipartFile[] multipartfiles) throws BusinessException, IOException {
        for (MultipartFile file : multipartfiles) {
            if (file != null && !file.isEmpty()) {
                String savePath = FilenameUtils.concat(ConstantParameter.getShootPhotoServerFolderPath(), file.getOriginalFilename());
                logger.info("savePath:", savePath);
                File outFile = new File(savePath);
                if (outFile.isFile() && outFile.exists()) {
                    outFile.delete();
                    outFile.createNewFile();
                }
                FileUtils.copyInputStreamToFile(file.getInputStream(), outFile);
            }
        }
    }

    @Override
    @Transactional
    public void saveShootPDF(String caseApplyCode, String linkCode, String preNodeStr, String pdfName) throws Exception {
        List<PhotoTree> photoList = this.customReposity.findByCaseApplyCodeAndLinkCodeAndAllNodeStrStartingWith(caseApplyCode, linkCode, preNodeStr);
        if (photoList == null || photoList.size() == 0) {
            throw new BusinessException("10010002", "未找到可供提交的图片资源，请先上传图片！");
        }
        // 三个固定1级分类项为： 1：抵押物（guarantee），2：公司（company），3：住址（address）
        // 将本案件的全部拍摄文件划分为3组，分别生成pdf文件
        List<PhotoTree> guaranteeList = new ArrayList<PhotoTree>();
        List<PhotoTree> companyList = new ArrayList<PhotoTree>();
        List<PhotoTree> addressList = new ArrayList<PhotoTree>();
        for (PhotoTree entity : photoList) {
            if (PhotoTree.TYPE_1_NAME_GUARANTEE.equals(entity.getType1Name())) {
                guaranteeList.add(entity);
            }
            if (PhotoTree.TYPE_1_NAME_COMPANY.equals(entity.getType1Name())) {
                companyList.add(entity);
            }
            if (PhotoTree.TYPE_1_NAME_ADDRESS.equals(entity.getType1Name())) {
                addressList.add(entity);
            }
        }
        // materiaCode在本功能内是固定项，暂时写成HardCode
        generateShootPDF(guaranteeList, "YWDM001500104", pdfName);//抵押物照片
        generateShootPDF(companyList, "YWDM001500107", pdfName);//公司照片
        generateShootPDF(addressList, "YWDM001500106", pdfName);//家访照片
    }

    @Transactional
    @Override
    public int deleteByCaseApplyCodeAndAllNodeStrStartingWith(String caseApplyCode, String allNodeStr) {
        return this.customReposity.deleteByCaseApplyCodeAndAllNodeStrStartingWith(caseApplyCode, allNodeStr);
    }

    /**
     * @Title: generateShootPDF
     * @Description: 根据拍摄照片生成对应的资调pdf文件，并保存到FileStore和Attachment表中
     * @author denglw
     * @param list
     * @param materiaCode
     * @param preNodeNameStr allnodename前两个分类（抵押物-抵押物1）
     * @throws Exception
     */
    @Transactional
    private void generateShootPDF(List<PhotoTree> list, String materiaCode, String pdfName) throws Exception {
        logger.debug("开始合并生成PDF文件，list={}", list);
        if (list == null || list.size() == 0) {
            return;
        }
        String address = list.get(0).getAddress();
        String caseApplyCode = list.get(0).getCaseApplyCode();
        CaseApply caseApply = caseApplyService.findByCaseApplyCode(caseApplyCode);
        String caseApplyId = caseApply.getId();//附件对象要求传递案件id
        String productId = caseApply.getProductSubtypeId();
        MaterialList materialList = materiaListService.findByProductIdAndMateriaCode(productId, materiaCode);
//		String linkCode = list.get(0).getLinkCode();
        String savePath = UploaderController.getUploadFolder() + File.separator + UUID.randomUUID().toString() + ".pdf";

        // 根据照片列表生成pdf
        // step 1: 创建文档对象
        Document document = new Document();
        try {
            // step 2:
            // 我们创建一个文档
            // 并针对PDF格式的流文件
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(savePath));
            writer.setStrictImageSequence(true);
            // step 3: 我们打开文档
            document.open();
            // step 4:
            //表格两列
            PdfPTable table1 = new PdfPTable(2);
            //垂直居中
            table1.setHorizontalAlignment(Element.ALIGN_CENTER);
            //表格的宽度为100%
            table1.setWidthPercentage(100);
            //两列宽度的比例
            float[] wid1 = {0.5f, 0.5f};
            table1.setWidths(wid1);
            table1.getDefaultCell().setBorderWidth(0); //不显示边框
            //图片
            for (PhotoTree entity : list) {
                Image jpg = Image.getInstance(entity.getPhotoPath());
                table1.addCell(
                        ZUIPdfUtil.newPdfPCellByColspan(0, jpg, Element.ALIGN_CENTER, entity.getAllNodeNameStr()));
            }
            if (list.size() / 2 != 0) {
                table1.addCell(ZUIPdfUtil.newPdfPCellByColspan(0, new Paragraph(""), Element.ALIGN_CENTER, ""));
            }
            document.add(table1);
        } catch (DocumentException de) {
            throw de;
        } catch (IOException ioe) {
            throw ioe;
        }
        document.close();
        logger.debug("生成PDF文件完毕，savePath={}", savePath);
        // 将该pdf保存到Attachment表和FileStore表内
        AttachmentDto dto = new AttachmentDto();
        dto.setFileLabel(pdfName + ".pdf");
        dto.setFileNm(UUID.randomUUID().toString() + ".pdf");
        dto.setFileType(FilenameUtils.getExtension(dto.getFileLabel()));
        EmpDto loginUser = CED.getLoginUser();
        dto.setOwner(loginUser.getEmpCd());
        dto.setFilePath(savePath);
        dto.setIsTemp(true);
        dto = CED.saveAttachment(dto);
        FileStore fileStore = new FileStore();
        fileStore.setAttachmentId(dto.getId());
        fileStore.setFileName(pdfName + ".pdf");
        fileStore.setCaseApplyId(caseApplyId);
        fileStore.setBusinessId(caseApplyId);
        fileStore.setLinkCode("06"); // TODO 06=资调，未来该值会变更为枚举值
        fileStore.setCreateBy(loginUser.getEmpCd());
        fileStore.setFileType(ENUM_MEDIA_TYPE.getClassType("pdf"));
        fileStore.setProductId(productId);
        fileStore.setCreateOrgCd(loginUser.getOrgCd());
        fileStore.setDocumentName(pdfName);
        fileStore.setStatus(ENUM_USING_STATUS.USING.value);
        fileStore.setMateriaId(materialList.getId());
        fileStore.setUpdateTime(new Date());
        fileStore.setUpdateBy(loginUser.getEmpCd());
        fileStoreService.saveOrUpdateEntity(fileStore);
    }

    @Override
    @Transactional
    public void deleteShootPdf(String caseApplyCode, String linkCode, String pdfName) throws Exception {
        CaseApply caseApply = caseApplyService.findByCaseApplyCode(caseApplyCode);
        String caseApplyId = caseApply.getId();//附件对象要求传递案件id
        String productId = caseApply.getProductSubtypeId();
        FileStore fileStore = new FileStore();
        fileStore.setCaseApplyId(caseApplyId);
        fileStore.setBusinessId(caseApplyId);
        fileStore.setLinkCode(linkCode);
        fileStore.setProductId(productId);
        fileStore.setFileName(pdfName + ".pdf");
        fileStore.setDocumentName(pdfName);
        List<FileStore> deleteLs = fileStoreService.findByCondition(fileStore);
        for (FileStore delFileStore : deleteLs) {
            String attachmentId = delFileStore.getAttachmentId();
            CED.deleteAttachment(attachmentId);
        }
        if (ObjectHelper.isNotEmpty(deleteLs)) {
            fileStoreService.batchLogicDeleteFileStore(deleteLs);
        }
    }

    @Override
    @Transactional
    public void submitData(List<PhotoTree> addList, String caseApplyCode, String linkCode, String isOver) throws Exception {
        String allNodeStr = addList.get(0).getAllNodeStr();
        String allNodeNameStr = addList.get(0).getAllNodeNameStr();
        if (ObjectHelper.isNotEmpty(allNodeStr)) {
            String[] preNodes = allNodeStr.split("-");
            String[] preNodeNames = allNodeNameStr.split("-");
            if (preNodes.length >= 2) {
                String preNodeStr = preNodes[0] + "-" + preNodes[1];
                String preNodeNameStr = preNodeNames[0] + "-" + preNodeNames[1];
                //删除(抵押物-抵押物1)之前的记录
                this.deleteByCaseApplyCodeAndAllNodeStrStartingWith(caseApplyCode, preNodeStr);
                //删除后再保存记录，保证数据完整性(抵押物-抵押物1)
                this.saveShootPhotoDatas(addList, isOver);
                // 如果是最后一次上传，需要取得本次全部附件并生成pdf文件
                String pdfName  = preNodeNames[0]+"("+preNodeStr+")";
                if ("true".equals(isOver)) {
                    this.deleteShootPdf(caseApplyCode, linkCode,pdfName);
                    this.saveShootPDF(caseApplyCode, linkCode, preNodeStr,pdfName);
                }
            }
        }
    }
}
