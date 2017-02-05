package com.zdsoft.finance.authgrade.repository;

import com.zdsoft.finance.authgrade.entity.AuthGrade;
import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.framework.core.common.domain.BaseEntity;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.Pageable;
import com.zdsoft.framework.core.common.util.ObjectHelper;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 授权等级设定操作库
 * @author LiaoGuoWei
 * @create 2017-01-04 15:53
 **/
public interface AuthGradeRepository extends CustomRepository<AuthGrade,String> {

    /**
     * 自定义分页查询
     * @param pageable
     * @param productParentId
     * @param productChildId
     * @param gradeCode
     * @return
     */
    public default Page<AuthGrade> qryConditions(Pageable pageable, String productParentId, String productChildId, String gradeCode){
        StringBuffer hql=new StringBuffer(" from AuthGrade g where g.isDeleted = :isDeleted ");
        Map<String,Object> conditions=new HashMap<String,Object>();
        conditions.put("isDeleted",!BaseEntity.DELETED);
        if(ObjectHelper.isNotEmpty(productParentId)){
            hql.append(" and g.productParentId = :productParentId ");
            conditions.put("productParentId",productParentId);
        }
        if(ObjectHelper.isNotEmpty(productChildId)){
            hql.append(" and g.productChildId = :productChildId ");
            conditions.put("productChildId",productChildId);
        }
        if(ObjectHelper.isNotEmpty(gradeCode)){
            hql.append(" and g.gradeCode = :gradeCode ");
            conditions.put("gradeCode",gradeCode);
        }
        hql.append(" order by g.createTime desc,g.productParentId  ");
        Page<AuthGrade> returnData=this.findByHqlPage(pageable,hql.toString(), conditions);
        return returnData;
    }

    /**
     * 按照等级编号查找
     * @param gradeCode
     * @return
     */
    @Query(" select a from AuthGrade a where a.isDeleted=false and a.gradeCode=:gradeCode")
    public List<AuthGrade> findByGradeCode(@Param("gradeCode")String gradeCode);

    /**
     * 机构授权查询
     * @param authGrade
     * @return
     */
    public default Page<AuthGrade> findInstitutionAuth(Pageable pageable, AuthGrade authGrade) throws Exception{
        Map<String, Object> qryCondition = new HashMap<String, Object>();
        StringBuffer sql = new StringBuffer(" select g.id as id, " +
                "  g.version as version, " +
                "  g.isDeleted as isDeleted, " +
                "  g.createTime as createTime, " +
                "  g.updateTime as updateTime, " +
                "  g.deleteTime as deleteTime, " +
                "  g.createBy as createBy, " +
                "  g.createOrgCd as createOrgCd, " +
                "  g.updateBy as updateBy, " +
                "  g.updateOrgCd as updateOrgCd, " +
                "  g.finalGradeCode as finalGradeCode, " +
                "  g.finalGradeName as finalGradeName, " +
                "  g.originalGradeCode as originalGradeCode, " +
                "  g.originalGradeName as originalGradeName, " +
                "  g.handerCode as handerCode, " +
                "  g.handerName as handerName, " +
                "  g.handelTime as handelTime, " +
                "  a.productParentId as productParentId, " +
                "  a.productParentName as productParentName, " +
                "  a.productChildId as productChildId, " +
                "  a.productChildName as productChildName, " +
                "  o.orgCd as institutionCode, " +
                "  o.orgNm as institutionName " +
                " from busi_orgintermediate o " +
                " LEFT JOIN busi_institutiongrade g on g.institutionCode=o.orgCd and g.isDeleted=0 "+
                " LEFT JOIN busi_authgrade a on a.id=g.authGrade_id and a.isDeleted=0 where 1=1 ");
        //机构唯一编号
        if (ObjectHelper.isNotEmpty(authGrade.getInstitutionCode())) {
            sql.append(" and institutionCode=:institutionCode ");
            qryCondition.put("institutionCode", authGrade.getInstitutionCode());
        }
        //终评
        if(ObjectHelper.isNotEmpty(authGrade.getFinalGradeCode())){
            sql.append(" and finalGradeCode = :finalGradeCode ");
            qryCondition.put("finalGradeCode",authGrade.getFinalGradeCode());
        }
        //父产品
        if(ObjectHelper.isNotEmpty(authGrade.getProductParentId())){
            sql.append(" and productParentId = :productParentId ");
            qryCondition.put("productParentId",authGrade.getProductParentId());
        }
        //子产品
        if(ObjectHelper.isNotEmpty(authGrade.getProductChildId())){
            sql.append(" and productChildId = :productChildId ");
            qryCondition.put("productChildId",authGrade.getProductChildId());
        }
        //评级人
        if(ObjectHelper.isNotEmpty(authGrade.getHanderCode())){
            sql.append(" and handerCode = :handerCode ");
            qryCondition.put("handerCode",authGrade.getHanderCode());
        }
        sql.append(" order by g.updateTime desc,o.orgCd ");
        //得到数据
        Page<AuthGrade> sourceData = this.findBySqlEntityPage(pageable, sql.toString(), qryCondition,AuthGrade.class);
        return sourceData;
    }
}
