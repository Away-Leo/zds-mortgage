package com.zdsoft.finance.filestore.repository;

import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.finance.filestore.entity.FileStore;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.Pageable;
import com.zdsoft.framework.core.common.util.ObjectHelper;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @title FileStoreRepository.java
 * @className FileStoreRepository
 * @description 文件库自定义操作库
 * @author LiaoGuoWei
 * @create 2017/2/27 10:09
 * @version V1.0
 **/
public interface FileStoreRepository extends CustomRepository<FileStore,String> {

    /**
     * 按照条件查询分页数据
     * @param pageable 分页参数
     * @param fileStore 文件库
     * @return Page<FileStore>
     * @author liaoguowei
     * @throws Exception
     */
    public default Page<FileStore> findByCondition(Pageable pageable,FileStore fileStore) throws Exception{
        Map<String,Object> qryCondition=new HashMap<String,Object>();
        StringBuffer sql=new StringBuffer(" SELECT TEMP.*,ROWNUM AS \"disOrder\" FROM (SELECT M.MATERIATYPECODE    AS \"materiaTypeCode\",");
                                        sql.append(" M.MATERIATYPENAME AS \"materiaTypeCodeName\",");
                                        sql.append(" F.ID              AS \"id\",");
                                        sql.append(" M.MATERIACODE     AS \"materiaCode\",");
                                        sql.append(" M.MATERIANAME     AS \"materiaCodeName\",");
                                        sql.append(" M.ID              AS \"materiaId\",");
                                        sql.append(" F.CASEAPPLYID     AS \"caseApplyId\",");
                                        sql.append(" F.FILETYPE        AS \"fileType\",");
                                        sql.append(" F.PRODUCTID       AS \"productId\",");
                                        sql.append(" F.FILENAME        AS \"fileName\",");
                                        sql.append(" F.UPDATEBY        AS \"updateBy\",");
                                        sql.append(" F.UPDATETIME      AS \"updateTime\",");
                                        sql.append(" F.DOCUMENTNAME    AS \"documentName\",");
                                        sql.append(" F.SOURCECODE      AS \"sourceCode\",");
                                        sql.append(" F.ATTACHMENTID    AS \"attachmentId\",");
                                        sql.append(" F.LONGITUDE       AS \"longitude\",");
                                        sql.append(" F.LATITUDE        AS \"latitude\",");
                                        sql.append(" F.STATUS          AS \"status\",");
                                        sql.append(" F.BUSINESSID      AS \"businessId\",");
                                        sql.append(" F.LINKCODE        AS \"linkCode\"");
        sql.append(" FROM PRD_MATERIALLIST M ");
        sql.append(" LEFT JOIN COMMON_FILESTORE F ON F.MATERIAID = M.ID AND F.ISDELETED = 'F' AND F.STATUS <> '2'  ");
        //业务表单
        if(ObjectHelper.isNotEmpty(fileStore.getBusinessId())){
            sql.append(" AND F.BUSINESSID = :businessId ");
            qryCondition.put("businessId",fileStore.getBusinessId());
        }
        //案件号
        if(ObjectHelper.isNotEmpty(fileStore.getCaseApplyId())){
            sql.append(" AND F.CASEAPPLYID = :caseApplyId ");
            qryCondition.put("caseApplyId",fileStore.getCaseApplyId());
        }
        sql.append(" WHERE M.ISDELETED = 'F' ");
        //节点编号
        if(ObjectHelper.isNotEmpty(fileStore.getLinkCode())){
            sql.append(" AND M.MATERIATYPECODE IN (SELECT A.MATERIATYPECODE FROM PRD_MATERIALLIST_LIMITS A WHERE SUBSTR(A.MATERIALIMIT, LENGTH(A.MATERIALIMIT) - 1, LENGTH(A.MATERIALIMIT)) = :linkCode AND A.PRODUCTID = :productId AND A.ISDELETED = 'F') ");
            qryCondition.put("linkCode",fileStore.getLinkCode());
        }
        //产品ID
        if(ObjectHelper.isNotEmpty(fileStore.getProductId())){
            sql.append(" AND M.PRODUCTID = :productId ");
            qryCondition.put("productId",fileStore.getProductId());
        }
        //类别
        if(ObjectHelper.isNotEmpty(fileStore.getMateriaCode())){
            sql.append(" AND M.MATERIACODE =:materiaCode ");
            qryCondition.put("materiaCode",fileStore.getMateriaCode());
        }
        //大类编号
        if(ObjectHelper.isNotEmpty(fileStore.getMateriaTypeCode())){
            sql.append("AND M.MATERIATYPECODE = :materiaTypeCode ");
            qryCondition.put("materiaTypeCode",fileStore.getMateriaTypeCode());
        }
        //按照大类和显示顺序聚合
        sql.append("  ORDER BY M.MATERIATYPECODE) TEMP");

        return this.findBySqlEntityPage(pageable, sql.toString(), qryCondition, FileStore.class);
    }

    /**
     * @Title: findProductMateriaWithLinkCode
     * @Description: 在当前产品和案件状态下查找具有的资料
     * @author liaoguowei
     * @param fileStore
     * @return java.util.List<com.zdsoft.finance.filestore.entity.FileStore>
     * @throws
     */
    public default List<FileStore> findProductMateriaWithLinkCode(FileStore fileStore) throws Exception{
        Map<String,Object> qryCondition=new HashMap<String,Object>();
        StringBuffer sql=new StringBuffer("SELECT         L.MATERIATYPECODE    AS \"materiaTypeCode\", ");
                                             sql.append(" L.MATERIATYPENAME    AS \"materiaTypeCodeName\", ");
                                             sql.append(" L.MATERIACODE        AS \"materiaCode\", ");
                                             sql.append(" L.MATERIANAME        AS \"materiaCodeName\" ");
                            sql.append(" FROM PRD_MATERIALLIST L ");
                            sql.append(" WHERE L.ISDELETED = 'F' ");
        if(ObjectHelper.isNotEmpty(fileStore.getProductId())&&ObjectHelper.isNotEmpty(fileStore.getLinkCode())){
            sql.append(" AND L.PRODUCTID = :productId AND L.MATERIATYPECODE IN ");
            sql.append(" (SELECT A.MATERIATYPECODE FROM PRD_MATERIALLIST_LIMITS A WHERE SUBSTR(A.MATERIALIMIT, LENGTH(A.MATERIALIMIT) - 1, LENGTH(A.MATERIALIMIT)) = :linkCode ");
            sql.append(" AND A.PRODUCTID = :productId AND A.ISDELETED = 'F') ");
            qryCondition.put("productId",fileStore.getProductId());
            qryCondition.put("linkCode",fileStore.getLinkCode());
        }
        sql.append(" ORDER BY L.MATERIATYPECODE ");

        return this.findBySql(sql.toString(),qryCondition,FileStore.class);
    }

    /**
     * 按照条件数据
     * @param fileStore
     * @return
     * @author liaoguowei
     * @throws Exception
     */
    public default List<FileStore> findByCondition(FileStore fileStore) throws Exception{
        Map<String,Object> qryCondition=new HashMap<String,Object>();
        StringBuffer sql=new StringBuffer("SELECT M.MATERIATYPECODE AS \"materiaTypeCode\", ");
                                        sql.append( " M.MATERIATYPENAME     AS \"materiaTypeCodeName\", ");
                                        sql.append( " M.MATERIACODE     AS \"materiaCode\", ");
                                        sql.append( " M.MATERIANAME     AS \"materiaCodeName\", ");
                                        sql.append(" M.ID              AS \"materiaId\", ");
                                        sql.append(" F.ID              AS \"id\", ");
                                        sql.append(" F.PRODUCTID       AS \"productId\",");
                                        sql.append(" F.CASEAPPLYID   AS \"caseApplyId\", ");
                                        sql.append(" ROWNUM            AS \"disOrder\", ");
                                        sql.append(" F.FILETYPE        AS \"fileType\", ");
                                        sql.append(" F.FILENAME        AS \"fileName\", ");
                                        sql.append(" F.UPDATEBY        AS \"updateBy\",");
                                        sql.append(" F.UPDATETIME      AS \"updateTime\",");
                                        sql.append(" F.DOCUMENTNAME    AS \"documentName\", ");
                                        sql.append(" F.SOURCECODE      AS \"sourceCode\", ");
                                        sql.append(" F.ATTACHMENTID    AS \"attachmentId\", ");
                                        sql.append(" F.LONGITUDE       AS \"longitude\", ");
                                        sql.append(" F.LATITUDE        AS \"latitude\", ");
                                        sql.append(" F.STATUS          AS \"status\", ");
                                        sql.append(" F.BUSINESSID      AS \"businessId\", ");
                                        sql.append(" F.LINKCODE        AS \"linkCode\" ");
        sql.append(" FROM COMMON_FILESTORE F ");
        sql.append(" LEFT JOIN PRD_MATERIALLIST M ON M.ID = F.MATERIAID AND M.ISDELETED = 'F'  ");
        sql.append(" WHERE F.ISDELETED = 'F' ");
        //环节编号
        if(ObjectHelper.isNotEmpty(fileStore.getLinkCode())){
            sql.append(" AND F.LINKCODE = :linkCode ");
            qryCondition.put("linkCode",fileStore.getLinkCode());
        }
        //案件编号
        if(ObjectHelper.isNotEmpty(fileStore.getCaseApplyId())){
            sql.append(" AND F.CASEAPPLYID = :caseApplyId ");
            qryCondition.put("caseApplyId",fileStore.getCaseApplyId());
        }
        //资料编号
        if(ObjectHelper.isNotEmpty(fileStore.getMateriaCode())){
            sql.append(" AND M.MATERIACODE = :materiaCode ");
            qryCondition.put("materiaCode",fileStore.getMateriaCode());
        }
        //状态
        if(ObjectHelper.isNotEmpty(fileStore.getStatus())){
            sql.append("AND F.STATUS = :status ");
            qryCondition.put("status",fileStore.getStatus());
        }
        //表单ID
        if(ObjectHelper.isNotEmpty(fileStore.getBusinessId())){
            sql.append(" AND F.BUSINESSID = :businessId ");
            qryCondition.put("businessId",fileStore.getBusinessId());
        }
        //附件类型
        if(ObjectHelper.isNotEmpty(fileStore.getMateriaCodes())){
            sql.append(" AND M.MATERIACODE IN :materiaCodes ");
            qryCondition.put("materiaCodes","("+fileStore.getMateriaCodes()+")");
        }
        //资料ID
        if(ObjectHelper.isNotEmpty(fileStore.getMateriaId())){
        	sql.append(" AND F.MATERIAID = :materiaId ");
        	qryCondition.put("materiaId",fileStore.getMateriaId());
        }
        //产品ID
        if(ObjectHelper.isNotEmpty(fileStore.getProductId())){
            sql.append(" AND F.productId = :productId ");
            qryCondition.put("productId",fileStore.getProductId());
        }
        //文件名
        if(ObjectHelper.isNotEmpty(fileStore.getFileName())){
            sql.append(" AND F.FILENAME = :fileName ");
            qryCondition.put("fileName",fileStore.getFileName());
        }
        sql.append(" ORDER BY M.MATERIATYPECODE, F.DISORDER ");
        return this.findBySql(sql.toString(),qryCondition,FileStore.class);
    }

    /**
     * 将相关案件的所有草稿数据修改为正式数据
     * @param caseApplayCode
     * @param linkCode
     * @author liaoguowei
     */
    @Query(" update FileStore f set f.status='0' where f.caseApplyId =:caseApplyId and f.linkCode = :linkCode and f.isDeleted = 'F' and f.status='2' ")
    @Modifying
    public void saveAllDraftWithCase(@Param("caseApplyId")String caseApplayCode,@Param("linkCode")String linkCode);

    /**
     * 将相关表单的所有草稿数据修改为正式数据
     * @param businessId
     * @param linkCode
     * @author liaoguowei
     */
    @Query(" update FileStore f set f.status='0' where f.businessId =:businessId and f.linkCode = :linkCode and f.isDeleted = 'F' and f.status='2' ")
    @Modifying
    public void saveAllDraftWithBusi(@Param("businessId")String businessId,@Param("linkCode")String linkCode);

    /**
     * 将相关表单和案件的所有草稿数据修改为正式数据
     * @param businessId
     * @param linkCode
     * @author liaoguowei
     */
    @Query(" update FileStore f set f.status='0' where f.businessId =:businessId and f.caseApplyId =:caseApplyId and f.linkCode = :linkCode and f.isDeleted = 'F' and f.status='2' ")
    @Modifying
    public void saveAllDraftWithBusiAndCase(@Param("businessId")String businessId,@Param("caseApplyId")String caseApplyId,@Param("linkCode")String linkCode);

    /**
     * 通过业务表单ID和节点编号查找
     * @param businessId
     * @param linkCode
     * @return
     */
    public List<FileStore> findByBusinessIdAndLinkCode(String businessId,String linkCode);

    /**
      * @Title: findByCaseApplyIdAndMateriaIdAndBusinessId
      * @Description: 通过资料ID、案件编号、业务表单ID查找
      * @author liaoguowei
      * @param caseApplyId
      * @param materiaId
      * @param businessId
      * @return
      * @throws
      * model by liuhuan -添加OrderByCreateTimeDesc
    */

    public List<FileStore> findByCaseApplyIdAndMateriaIdAndBusinessIdOrderByCreateTimeDesc(String caseApplyId,String materiaId,String businessId);
    
    /** 
     * @Title: findByCaseApplyCodeAndBusinessIdAndLinkCode 
     * @Description: 通过案件id、业务id、环节code查询附件
     * @author liuhuan 
     * @param caseApplyId 案件id
     * @param businessId 业务id
     * @param linkCode 环节code
     * @return  
     */ 
    @Query(" from FileStore m where m.isDeleted = false and m.caseApplyId = :caseApplyId and m.businessId = :businessId and m.linkCode = :linkCode ")
    public List<FileStore> findByCaseApplyIdAndBusinessIdAndLinkCode(@Param("caseApplyId")String caseApplyId, @Param("businessId")String businessId,@Param("linkCode")String linkCode);
    
}
