package com.zdsoft.finance.businesssetting.repository;

import com.zdsoft.finance.businesssetting.entity.InstitutionGrade;
import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.framework.core.common.domain.BaseEntity;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.Pageable;
import com.zdsoft.framework.core.common.util.ObjectHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @title InstitutionGradeRepository.java
 * @className InstitutionGradeRepository
 * @description 机构评级自定义操作库
 * @author LiaoGuoWei
 * @create 2017/3/3 14:38
 * @version V1.0
 **/
public interface InstitutionGradeRepository extends CustomRepository<InstitutionGrade, String> {

    /**
     * @Title: queryWithCondition
     * @Description: 根据条件查询
     * @author liaoguowei
     * @param institutionCode 机构编码
     * @return java.util.List<com.zdsoft.finance.businesssetting.entity.InstitutionGrade>
     * @throws
     */
    public default List<InstitutionGrade> queryWithCondition(String institutionCode) {
        Map<String, Object> qryCondition = new HashMap<String, Object>();
        StringBuffer hql = new StringBuffer(" from InstitutionGrade i where i.isDeleted = :isDeleted ");
        qryCondition.put("isDeleted", !BaseEntity.DELETED);

        //机构唯一编号
        if (ObjectHelper.isNotEmpty(institutionCode)) {
            hql.append(" and i.institutionCode = :institutionCode ");
            qryCondition.put("institutionCode", institutionCode);
        }

        hql.append(" order by i.updateTime desc,i.institutionCode ");
        return this.findByHql(hql.toString(), qryCondition);
    }

    /**
     * @Title: queryPageWithCondition
     * @Description: 根据条件查询
     * @author liaoguowei
     * @param pageable 分页参数
     * @param institutionCode 机构ID
     * @return com.zdsoft.framework.core.common.page.Page<com.zdsoft.finance.businesssetting.entity.InstitutionGrade>
     * @throws
     */
    public default Page<InstitutionGrade> queryPageWithCondition(Pageable pageable, String institutionCode) {
        Map<String, Object> qryCondition = new HashMap<String, Object>();
        StringBuffer hql = new StringBuffer(" from InstitutionGrade i where i.isDeleted = :isDeleted ");
        qryCondition.put("isDeleted", !BaseEntity.DELETED);

        //机构唯一编号
        if (ObjectHelper.isNotEmpty(institutionCode)) {
            hql.append(" and i.institutionCode = :institutionCode ");
            qryCondition.put("institutionCode", institutionCode);
        }

        hql.append(" order by i.updateTime desc,i.institutionCode ");
        return this.findByHqlPage(pageable, hql.toString(), qryCondition);
    }


    /**
     * @Title: findPageWithCondition
     * @Description: 按照条件查找授权等级设定数据
     * @author liaoguowei
     * @param pageable 分页参数
     * @param institutionCode 机构ID
     * @return com.zdsoft.framework.core.common.page.Page<java.util.Map<java.lang.String,java.lang.Object>>
     * @throws
     */
    public default Page<Map<String, Object>> findPageWithCondition(Pageable pageable, String institutionCode) throws Exception {
        Map<String, Object> qryCondition = new HashMap<String, Object>();
        StringBuffer sql = new StringBuffer(" select     c.id                as \"id\", ");
                                            sql.append(" c.createTime        as \"createTime\", ");
                                            sql.append(" c.authGradeCode     as \"finalGradeCode\", ");
                                            sql.append(" c.authGradeName     as \"finalGradeName\", ");
                                            sql.append(" c.originalGradeCode as \"originalGradeCode\", ");
                                            sql.append(" c.originalGradeName as \"originalGradeName\", ");
                                            sql.append(" c.handelerCode      as \"handerCode\", ");
                                            sql.append(" c.handelerName      as \"handerName\", ");
                                            sql.append(" c.handelTime        as \"handelTime\", ");
                                            sql.append(" a.productParentId   as \"productParentId\",  ");
                                            sql.append(" a.productParentName as \"productParentName\",  ");
                                            sql.append(" a.productChildId    as \"productChildId\",  ");
                                            sql.append(" a.productChildName  as \"productChildName\",  ");
                                            sql.append(" a.authAmount        as \"authAmount\",  ");
                                            sql.append(" o.orgCd             as \"institutionCode\",  ");
                                            sql.append(" o.orgNm             as \"institutionName\"  ");
                                sql.append("from buss_orgintermediate o ");
                                sql.append("left join buss_orgauthconn c on o.orgCd=c.orgIntermediateId ");
                                sql.append("left join buss_authgrade a on a.gradeCode=c.authGradeCode and a.isDeleted='F' where 1=1 ");
        //机构唯一编号
        if (ObjectHelper.isNotEmpty(institutionCode)) {
            sql.append(" and o.orgCd=:institutionCode ");
            qryCondition.put("institutionCode", institutionCode);
        }
        sql.append(" order by c.handelTime desc,o.orgCd,c.authGradeCode ");
        //得到数据
        Page<Map<String, Object>> sourceData = this.findBySqlMapPage(pageable, sql.toString(), qryCondition);
        return sourceData;
    }
    /**
     * @Title: findHander
     * @Description: 查找操作人
     * @author liaoguowei
     * @param
     * @return java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     * @throws
     */
    public default List<Map<String, Object>> findHander() throws Exception{
        List<Map<String, Object>> returnData = new ArrayList<Map<String, Object>>();
        StringBuffer sql = new StringBuffer(" SELECT DISTINCT ");
        sql.append(" i.handerCode AS handerCode,  ");
        sql.append(" i.handerName AS handerName  ");
        sql.append("FROM  ");
        sql.append(" buss_institutiongrade i  ");
        sql.append("WHERE  ");
        sql.append(" i.isDeleted = 'F'  ");
        List<Map<String,Object>> sourceData=this.findListMapByCondition(sql.toString(),null);
        return sourceData;
    }

}
