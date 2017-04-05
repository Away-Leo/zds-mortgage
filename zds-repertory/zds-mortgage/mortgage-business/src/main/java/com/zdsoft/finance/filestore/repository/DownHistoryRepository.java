package com.zdsoft.finance.filestore.repository;

import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.finance.filestore.entity.DownHistory;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.Pageable;
import com.zdsoft.framework.core.common.util.ObjectHelper;

import java.util.HashMap;
import java.util.Map;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @title DownHistoryRepository.java
 * @className DownHistoryRepository
 * @description 下载历史自定义操作库
 * @author LiaoGuoWei
 * @create 2017/2/27 10:09
 * @version V1.0
 **/
public interface DownHistoryRepository extends CustomRepository<DownHistory,String>{

    /**
     * @Title: findPageByCondition
     * @Description: 分页查询
     * @author liaoguowei
     * @param pageable 分页参数
     * @param downHistory 下载历史
     * @return Page<DownHistory>
     * @throws Exception
     */
    public default Page<DownHistory> findPageByCondition(Pageable pageable,DownHistory downHistory) throws Exception{
        Map<String,Object> qryCondition=new HashMap<String,Object>();
        StringBuffer sql=new StringBuffer(" SELECT TEMP.* FROM (");
                                                    sql.append(" SELECT  T.MATERIATYPECODE AS \"materiaTypeCode\", ");
                                                            sql.append(" T.MATERIACODE AS \"materiaCode\", ");
                                                            sql.append(" F.FILENAME AS \"fileName\", ");
                                                            sql.append(" F.DOCUMENTNAME AS \"documentName\", ");
                                                            sql.append(" T.DOWNEMPNAME AS \"downEmpName\", ");
                                                            sql.append(" COUNT(T.ID) AS \"downFrequency\", ");
                                                            sql.append(" MAX(T.LATESTDOWNDATE) AS \"latestDownDate\", ");
                                                            sql.append(" F.LINKCODE AS \"sourceCode\" ");
                                                    sql.append(" FROM COMMON_DOWNHIS T ");
                                sql.append(" LEFT JOIN COMMON_FILESTORE F ON F.ID = T.FILESTORE_ID ");
                                sql.append(" WHERE 1 = 1 ");
        //所属分类
        if(ObjectHelper.isNotEmpty(downHistory.getMateriaTypeCode())){
            sql.append(" AND T.MATERIATYPECODE = :materiaTypeCode ");
            qryCondition.put("materiaTypeCode",downHistory.getMateriaTypeCode());
        }
        //类别名称
        if(ObjectHelper.isNotEmpty(downHistory.getMateriaCode())){
            sql.append(" AND F.MATERIACODE = :materiaCode ");
            qryCondition.put("materiaCode",downHistory.getMateriaCode());
        }
        //下载人
        if(ObjectHelper.isNotEmpty(downHistory.getDownEmpName())){
            sql.append(" AND T.DOWNEMPNAME LIKE :downEmpName ");
            qryCondition.put("downEmpName","%"+downHistory.getDownEmpName()+"%");
        }
        //案件ID
        if(ObjectHelper.isNotEmpty(downHistory.getFileStore().getCaseApplyId())){
            sql.append(" AND F.CASEAPPLYID = :caseApplyId ");
            qryCondition.put("caseApplyId",downHistory.getFileStore().getCaseApplyId());
        }
        //环节编号
        if(ObjectHelper.isNotEmpty(downHistory.getFileStore().getLinkCode())){
            sql.append(" AND F.LINKCODE = :linkCode ");
            qryCondition.put("linkCode",downHistory.getFileStore().getLinkCode());
        }
        //businessId
        if(ObjectHelper.isNotEmpty(downHistory.getFileStore().getBusinessId())){
            sql.append(" AND F.BUSINESSID = :businessId ");
            qryCondition.put("businessId",downHistory.getFileStore().getBusinessId());
        }
        //产品ID
        if(ObjectHelper.isNotEmpty(downHistory.getFileStore().getProductId())){
            sql.append(" AND F.PRODUCTID = :productId ");
            qryCondition.put("productId",downHistory.getFileStore().getProductId());
        }
        //文件名
        if(ObjectHelper.isNotEmpty(downHistory.getFileName())){
            sql.append(" AND F.FILENAME LIKE :fileName ESCAPE'/' ");
            qryCondition.put("fileName","%"+downHistory.getFileName().replaceAll("%","/%").replaceAll("_","/_")+"%");
        }
        sql.append(" GROUP BY T.FILESTORE_ID,T.MATERIATYPECODE,T.MATERIACODE,F.FILENAME,F.DOCUMENTNAME,T.DOWNEMPNAME,F.LINKCODE) TEMP ORDER BY TEMP.\"latestDownDate\" DESC ");
        return this.findBySqlEntityPage(pageable, sql.toString(), qryCondition, DownHistory.class);
    }
}
