package com.zdsoft.finance.authgrade.repository;

import com.zdsoft.finance.authgrade.entity.InstitutionGrade;
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
 * 机构评级自定义操作库
 *
 * @author LiaoGuoWei
 * @create 2017-01-06 15:05
 **/
public interface InstitutionGradeRepository extends CustomRepository<InstitutionGrade, String> {

    /**
     * 根据条件查询
     *
     * @param institutionCode
     * @return
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
     * 根据条件查询
     *
     * @param institutionCode
     * @return
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
     * 按照条件查找授权等级设定数据
     *
     * @param institutionCode
     * @return
     */
    public default Page<Map<String, Object>> findPageWithCondition(Pageable pageable, String institutionCode) throws Exception {
        Map<String, Object> qryCondition = new HashMap<String, Object>();
        StringBuffer sql = new StringBuffer(" select  c.id as \"id\", " +
                " c.createTime as \"createTime\", " +
                " c.authGradeCode as \"finalGradeCode\", " +
                " c.authGradeName as \"finalGradeName\", " +
                " c.originalGradeCode as \"originalGradeCode\", " +
                " c.originalGradeName as \"originalGradeName\", " +
                " c.handelerCode as \"handerCode\", " +
                " c.handelerName as \"handerName\", " +
                " c.handelTime as \"handelTime\", " +
                " a.productParentId as \"productParentId\",  " +
                " a.productParentName as \"productParentName\",  " +
                " a.productChildId as \"productChildId\",  " +
                " a.productChildName as \"productChildName\",  " +
                " a.authAmount as \"authAmount\",  " +
                " o.orgId as \"institutionCode\",  " +
                " o.orgNm as \"institutionName\"  " +
                "from busi_orgintermediate o " +
                "left join busi_orgauthconn c on o.orgId=c.orgIntermediateId " +
                "left join busi_authgrade a on a.gradeCode=c.authGradeCode and a.isDeleted='F' where 1=1 ");
        //机构唯一编号
        if (ObjectHelper.isNotEmpty(institutionCode)) {
            sql.append(" and o.orgId=:institutionCode ");
            qryCondition.put("institutionCode", institutionCode);
        }
        sql.append(" order by c.handelTime desc,o.orgCd,c.authGradeCode ");
        //得到数据
        Page<Map<String, Object>> sourceData = this.findBySqlMapPage(pageable, sql.toString(), qryCondition);
        return sourceData;
    }

    public default List<Map<String, Object>> findHander() throws Exception{
        List<Map<String, Object>> returnData = new ArrayList<Map<String, Object>>();
        StringBuffer sql = new StringBuffer(" SELECT DISTINCT " +
                                                " i.handerCode AS handerCode,  " +
                                                " i.handerName AS handerName  " +
                                            "FROM  " +
                                            " busi_institutiongrade i  " +
                                            "WHERE  " +
                                            " i.isDeleted = 0  ");
        List<Map<String,Object>> sourceData=this.findListMapByCondition(sql.toString(),null);
        return sourceData;
    }

}
