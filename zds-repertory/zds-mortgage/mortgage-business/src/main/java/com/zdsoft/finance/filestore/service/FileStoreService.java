package com.zdsoft.finance.filestore.service;

import com.zdsoft.essential.dto.basedata.AttachmentDto;
import com.zdsoft.finance.base.service.BaseService;
import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.finance.filestore.entity.FileStore;
import com.zdsoft.finance.product.entity.MaterialListType;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.Pageable;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.util.List;
import java.util.Map;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @title FileStoreService.java
 * @className FileStoreService
 * @description 文件库（公共上传下载）service
 * @author LiaoGuoWei
 * @create 2017/2/27 10:56
 * @version V1.0
 **/
public interface FileStoreService extends BaseService<FileStore> {

    /**
      * @Title: findByCondition
      * @Description: 按照条件查询
      * @author liaoguowei
      * @param fileStore 文件仓库
      * @return List<FileStore>
      * @throws Exception
    */
    public List<FileStore> findByCondition(FileStore fileStore)throws Exception;

    /**
     * @Title: findByConditionForApp
     * @Description: 按照条件查询(APP用接口)
     * @author liaoguowei
     * @param fileStore 文件仓库
     * @return List<FileStore>
     * @throws Exception
     */
    public List<Map<String,Object>> findByConditionForApp(FileStore fileStore)throws Exception;

    /**
     * @Title: findMapByConditionForApp
     * @Description: 按照条件查找（APP用）
     * @author liaoguowei
     * @param fileStore 文件库参数
     * @return java.util.Map<java.lang.String,java.util.List<com.zdsoft.finance.filestore.entity.FileStore>>
     * @throws
     */
    public Map<String,List<FileStore>> findMapByConditionForApp(FileStore fileStore) throws Exception;

    /**
     * @Title: findPageByCondition
     * @Description: 按照条件查询分页数据
     * @author liaoguowei
     * @param fileStore 文件仓库
     * @param pageable 分页参数
     * @return Page<FileStore>
     * @throws Exception
     */
    public Page<FileStore> findPageByCondition(Pageable pageable,FileStore fileStore,String isSearch) throws Exception;

    /**
      * @Title:  findById
      * @Description: 通过ID查找
      * @author liaoguowei
      * @param id 文件仓库ID
      * @return FileStore
      * @throws BusinessException
    */
    public FileStore findById(String id) throws BusinessException;

    /**
     * @Title:  logicDeleteFileStore
     * @Description: 逻辑删除数据
     * @author liaoguowei
     * @param id 文件仓库ID
     * @return FileStore
     * @throws Exception
     */
    public FileStore logicDeleteFileStore(String id) throws Exception;

    /**
     * @Title: batchLogicDeleteFileStore
     * @Description: 批量逻辑删除文件库数据
     * @author liaoguowei
     * @param fileStoreList 需要删除的数据
     * @return void
     * @throws Exception
     */
    public void batchLogicDeleteFileStore(List<FileStore> fileStoreList) throws Exception;


    /**
     * @Title:  updateFileStore
     * @Description: 更新
     * @author liaoguowei
     * @param fileStore 文件仓库
     * @return FileStore
     * @throws Exception
     */
    public FileStore updateFileStore(FileStore fileStore) throws Exception;

    /**
     * @Title:  updateFileStore
     * @Description: 保存
     * @author liaoguowei
     * @param fileStore 文件仓库
     * @return FileStore
     * @throws Exception
     */
    public FileStore saveFileStore(FileStore fileStore) throws Exception;

    /**
     * @Title:  updateFileStore
     * @Description: 更新或保存
     * @author liaoguowei
     * @param fileStore 文件仓库
     * @return FileStore
     * @throws Exception
     */
    public FileStore saveOrUpdateFileStore(FileStore fileStore) throws Exception;

    /**
     * @Title:  saveAllDraft
     * @Description: 将相关案件的所有草稿附件改为生效附件
     * @author liaoguowei
     * @param caseApplayCode 案件ID
     * @param linkCode 环节编号
     * @return void
     * @throws Exception
     */
    public void saveAllDraft(String caseApplayCode,String linkCode,String businessId) throws Exception;

    /**
     * @Title:  findAttachmentsByIds
     * @Description: 按照系统附件ID查找系统附件
     * @author liaoguowei
     * @param ids id
     * @return List<AttachmentDto>
     * @throws Exception
     */
    public List<AttachmentDto> findAttachmentsByIds(String ids) throws Exception;

    /**
     * @Title:  findByBusinessIdAndLinkCode
     * @Description: 通过表单ID和环节编号进行查找
     * @author liaoguowei
     * @param businessId 业务ID
     * @param linkCode 环节编号
     * @return List<AttachmentDto>
     * @throws BusinessException
     */
    public List<FileStore> findByBusinessIdAndLinkCode(String businessId,String linkCode) throws BusinessException;

    /**
     * @Title:  findByMateriaCode
     * @param caseApplyId 案件ID
     * @param businessId 业务编号
     * @param materiaCode 资料编号
     * @return
     * @throws BusinessException
     */
    public List<FileStore> findByMateriaCode(String caseApplyId,String businessId,String materiaCode) throws Exception;
    /**
     * @Title:  findByMateriaIdAndCaseApplyIdAndBusinessId
     * @param caseApplyId 案件ID
     * @param businessId 业务编号
     * @param materiaId 资料ID
     * @return
     * @throws BusinessException
     */
    public List<FileStore> findByMateriaIdAndCaseApplyIdAndBusinessId(String caseApplyId,String businessId,String materiaId) throws BusinessException;

    /**
     * @Title:  findWithMateriaIdAndCaseApplyIdAndBusinessId
     * @param caseApplyId 案件ID
     * @param businessId 业务编号ID
     * @param materiaId 资料ID
     * @return
     * @throws BusinessException
     */
    public List<FileStore> findWithMateriaIdAndCaseApplyIdAndBusinessId(String caseApplyId,String businessId,String materiaId);

    /**
      * @Title: saveForCredit
      * @Description: 保存征信接口专用
      * @author liaoguowei 
      * @param caseApplyId 案件ID
      * @param fileName 文件名称
      * @param businessId 业务ID
      * @return FileStore
      * @throws Exception
    */
    public FileStore saveForCredit(String caseApplyId,String fileName,String businessId,String attachmentId,String linkCode) throws Exception;

    /**
      * @Title: saveAndUploadForApp
      * @Description: 上传文件和保存数据（APP应用）
      * @author liaoguowei
      * @param multipartfiles 文件流
      * @param fileStore 文件仓库
      * @param token 令牌
      * @return List<FileStore>
      * @throws Exception
    */
    public List<FileStore> saveAndUploadForApp(CommonsMultipartFile[] multipartfiles,FileStore fileStore,String token) throws Exception;


    /**
     * @Title: findCurrentProductMateriaWithLinkCode
     * @Description: 在当前产品和案件状态下查找具有的资料
     * @author liaoguowei
     * @param fileStore 查询条件
     * @return java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     * @throws Exception
     */
    public List<Map<String,Object>> findProductMateriaWithLinkCode(FileStore fileStore) throws Exception;

    /** 
     * @Title: findByCaseApplyIdAndBusinessIdAndLinkCode 
     * @Description: 根据案件id-业务id-环节code查询附件
     * @author liuhuan 
     * @param caseApplyId 案件id
     * @param businessId 业务id
     * @param linkCode 环节code
     * @return
     * @throws Exception  
     */ 
    public List<FileStore> findByCaseApplyIdAndBusinessIdAndLinkCode(String caseApplyId, String businessId, String linkCode) throws Exception;

    /**
     * @Title: findParentMaterial
     * @Description: 从资料清单中查找所有资料大类
     * @author liaoguowei
     * @param
     * @return java.util.List<com.zdsoft.finance.product.entity.MaterialListType>
     * @throws
     */
    public List<MaterialListType> findParentMaterial();

}
