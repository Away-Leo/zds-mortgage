package com.zdsoft.finance.afterloan.repository;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.zdsoft.finance.afterloan.entity.AfterMonitor;
import com.zdsoft.finance.afterloan.entity.AfterMonitorRecord;
import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.finance.marketing.entity.CaseApplyBeforeCustomer;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.Pageable;
import com.zdsoft.framework.core.common.util.ObjectHelper;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @title AfterMonitorRepository.java
 * @className AfterMonitorRepository
 * @description 贷后监控
 * @author LiaoGuoWei
 * @create 2017/3/6 17:22
 * @version V1.0
 **/
public interface AfterMonitorRepository extends CustomRepository {

    /**
     * @Title: findMonitorByPage
     * @Description: 按照条件查找
     * @author liaoguowei
     * @param pageable
     * @param afterMonitor
     * @return com.zdsoft.framework.core.common.page.Page<com.zdsoft.finance.afterloan.entity.AfterMonitor>
     * @throws
     */
    public default Page<AfterMonitor> findMonitorByPage(Pageable pageable,AfterMonitor afterMonitor) throws Exception{
        Map<String,Object> qryCondition=new HashMap<String,Object>(8);
        StringBuffer sql=new StringBuffer("SELECT MA.ID                   \"id\", ");
                                     sql.append(" CAFC.ID                 \"customerId\", ");
                                     sql.append(" MA.APPLYAMOUNT          \"applyAmount\", ");
                                     sql.append(" MA.CASEAPPLYCODE        \"caseApplyCode\", ");
                                     sql.append(" MA.MECHANISMNAME        \"mechanismName\", ");
                                     sql.append(" CAFC.CUSTOMERNAME       \"mainBorrower\", ");
                                     sql.append(" NULL                    \"phoneNumber\", ");
                                     sql.append(" MA.PRODUCTTYPENAME      \"productTypeName\", ");
                                     sql.append(" MA.PRODUCTSUBTYPENAME   \"productSubtypeName\", ");
                                     sql.append(" pawn.floorage           \"floorAge\", ");
                                     sql.append(" FR.PLANPRINCIPALAMOUNT  \"overdueAmount\", ");
                                     sql.append(" FR.OVREDUESTARTDATE     \"overdueDate\", ");
                                     sql.append(" FR.DAYS                 \"overdueDay\", ");
                                     sql.append(" CONTRACT.CONTRACTAMOUNT \"contractAmount\", ");
                                     sql.append(" MR.MONITORDATE          \"lastMonitorDate\", ");
                                     sql.append(" MA.STAGE                \"stage\" ");
        sql.append(" FROM MKT_CASE_APPLY MA ");
        sql.append(" LEFT JOIN CON_CASE_CONTRACT CONTRACT ON MA.ID = CONTRACT.CASEAPPLYID ");
        sql.append(" LEFT JOIN CASE_AFTER_CUSTOMER AC ON AC.CASEAPPLYID = MA.ID AND AC.JOINTYPE =:joinType ");
        sql.append(" LEFT JOIN CUST_AFTER_CUSTOMER CAFC ON CAFC.ID = AC.CUSTOMERID ");
        sql.append(" LEFT JOIN ( SELECT HP.FLOORAGE,MC.CASEAPPLYID FROM MKT_COLLATERAL MC LEFT JOIN MKT_HOUSE_PROPERTY HP ON HP.ID=MC.ID WHERE MC.ISDELETED = 'F'  )PAWN  ON PAWN.CASEAPPLYID=MA.ID ");
        sql.append(" LEFT JOIN (SELECT RE.CASEAPPLYID, MAX(RE.MONITORDATE) MONITORDATE ");
        sql.append("           FROM AFTLOAN_MONITOR_RECORD RE ");
        sql.append("           WHERE  RE.CONTROLTYPE LIKE :controlType GROUP BY RE.CASEAPPLYID) MR ON MR.CASEAPPLYID = MA.ID ");
        sql.append(" LEFT JOIN FIN_CUSTOMER_RECEIVABLE FR ON FR.CASEAPPLYID = MA.ID AND FR.ISEFFECT = 'T' ");
        sql.append(" WHERE MA.LOANAPPLYANOUNT > 0 AND MA.ISDELETED='F'");

        qryCondition.put("joinType", CaseApplyBeforeCustomer.MAIN_BORROW);
        qryCondition.put("controlType", "%"+ AfterMonitorRecord.AFTER_MONITOR_RECORD+"%");
        //案件编号
        if(ObjectHelper.isNotEmpty(afterMonitor.getCaseApplyCode())){
            sql.append(" AND MA.CASEAPPLYCODE LIKE :caseApplyCode ");
            qryCondition.put("caseApplyCode","%"+afterMonitor.getCaseApplyCode()+"%");
        }
        //主借人
        if(ObjectHelper.isNotEmpty(afterMonitor.getMainBorrower())){
            sql.append(" AND CAFC.CUSTOMERNAME LIKE :customerName ");
            qryCondition.put("customerName","%"+afterMonitor.getMainBorrower()+"%");
        }
        //产品分类大类
        if(ObjectHelper.isNotEmpty(afterMonitor.getProductTypeId())){
            sql.append(" AND MA.PRODUCTTYPEID = :productTypeId ");
            qryCondition.put("productTypeId",afterMonitor.getProductTypeId());
        }
        //产品分类小类
        if(ObjectHelper.isNotEmpty(afterMonitor.getProductSubtypeId())){
            sql.append(" AND MA.PRODUCTSUBTYPEID = :productSubtypeId ");
            qryCondition.put("productSubtypeId",afterMonitor.getProductSubtypeId());
        }
        //机构
        if(ObjectHelper.isNotEmpty(afterMonitor.getMechanismName())){
            sql.append(" AND MA.MECHANISMNAME LIKE :mechanismName ");
            qryCondition.put("mechanismName","%"+afterMonitor.getMechanismName()+"%");
        }
        //案件状态
        if(ObjectHelper.isNotEmpty(afterMonitor.getStage())){
            sql.append(" AND MA.STAGE = :stage ");
            qryCondition.put("stage",afterMonitor.getStage());
        }
        //是否出险
        if(ObjectHelper.isNotEmpty(afterMonitor.getIsLoss())){
            sql.append(" AND MA.ISLOSS = :isLoss ");
            qryCondition.put("isLoss",afterMonitor.getIsLoss());
        }
        sql.append(" ORDER BY MA.createTime desc ");
        
        return this.findBySqlEntityPage(pageable,sql.toString(),qryCondition,AfterMonitor.class);
    }

    /**
     * @Title: findMonitorByPage
     * @Description: 按照条件查找
     * @author liaoguowei
     * @param afterMonitor 查询条件
     * @return com.zdsoft.framework.core.common.page.Page<com.zdsoft.finance.afterloan.entity.AfterMonitor>
     * @throws
     */
    public default List<AfterMonitor> findMonitorInitiActiveList(AfterMonitor afterMonitor) throws Exception{
        Map<String, Object> qryCondition = new HashMap<String, Object>(8);
        StringBuffer sql = new StringBuffer("SELECT MA.ID                   \"id\", ");
                                        sql.append(" CAFC.ID                 \"customerId\", ");
                                        sql.append(" MA.APPLYAMOUNT          \"applyAmount\", ");
                                        sql.append(" MA.CASEAPPLYCODE        \"caseApplyCode\", ");
                                        sql.append(" MA.MECHANISMNAME        \"mechanismName\", ");
                                        sql.append(" CAFC.CUSTOMERNAME       \"mainBorrower\", ");
                                        sql.append(" NULL                    \"phoneNumber\", ");
                                        sql.append(" MA.PRODUCTTYPENAME      \"productTypeName\", ");
                                        sql.append(" MA.PRODUCTSUBTYPENAME   \"productSubtypeName\", ");
                                        sql.append(" FR.PLANPRINCIPALAMOUNT  \"overdueAmount\", ");
                                        sql.append(" FR.OVREDUESTARTDATE     \"overdueDate\", ");
                                        sql.append(" FR.DAYS                 \"overdueDay\" ");
        sql.append(" FROM MKT_CASE_APPLY MA ");
        sql.append(" LEFT JOIN CON_CASE_CONTRACT CONTRACT ON MA.ID = CONTRACT.CASEAPPLYID ");
        sql.append(" LEFT JOIN CASE_AFTER_CUSTOMER AC ON AC.CASEAPPLYID = MA.ID AND AC.JOINTYPE =:joinType ");
        sql.append(" LEFT JOIN CUST_AFTER_CUSTOMER CAFC ON CAFC.ID = AC.CUSTOMERID ");
        sql.append(" LEFT JOIN ( SELECT HP.FLOORAGE,MC.CASEAPPLYID FROM MKT_COLLATERAL MC LEFT JOIN MKT_HOUSE_PROPERTY HP ON HP.ID=MC.ID WHERE MC.ISDELETED = 'F'  )PAWN  ON PAWN.CASEAPPLYID=MA.ID ");
        sql.append(" LEFT JOIN (SELECT RE.CASEAPPLYID, MAX(RE.MONITORDATE) MONITORDATE ");
        sql.append("           FROM AFTLOAN_MONITOR_RECORD RE ");
        sql.append("           WHERE  RE.CONTROLTYPE LIKE :controlType GROUP BY RE.CASEAPPLYID) MR ON MR.CASEAPPLYID = MA.ID ");
        sql.append(" LEFT JOIN FIN_CUSTOMER_RECEIVABLE FR ON FR.CASEAPPLYID = MA.ID AND FR.ISEFFECT = 'T' ");
        sql.append(" WHERE MA.LOANAPPLYANOUNT > 0 AND MA.ISDELETED='F'");

        qryCondition.put("joinType", CaseApplyBeforeCustomer.MAIN_BORROW);
        qryCondition.put("controlType", "%" + AfterMonitorRecord.AFTER_MONITOR_RECORD + "%");
        if(ObjectHelper.isNotEmpty(afterMonitor.getId())){
            sql.append(" AND MA.ID IN (:ids) ");
            qryCondition.put("ids", Arrays.asList(afterMonitor.getId().split(",")));
        }
        return this.findBySql(sql.toString(),qryCondition,AfterMonitor.class);
    }

}
