package com.zdsoft.finance.afterloan.repository;

import com.zdsoft.finance.afterloan.entity.AfterLoanReview;
import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.finance.marketing.entity.CaseApplyAfterCustomer;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.Pageable;
import com.zdsoft.framework.core.common.util.ObjectHelper;

import java.util.HashMap;
import java.util.Map;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @title AfterLoanReviewRepository.java
 * @className AfterLoanReviewRepository
 * @description 贷后监控查询自定义操作库
 * @author LiaoGuoWei
 * @create 2017/3/9 16:54
 * @version V1.0
 **/
@SuppressWarnings("rawtypes")
public interface AfterLoanReviewRepository extends CustomRepository {

    /**
     * @Title: findAfterLoanReviewByCondition
     * @Description: 根据条件查找贷后监控查询
     * @author liaoguowei
     * @param page 分页参数
     * @param afterLoanReview 查询条件
     * @return com.zdsoft.framework.core.common.page.Page<com.zdsoft.finance.afterloan.entity.AfterLoanReview>
     * @throws
     */
    @SuppressWarnings("unchecked")
	public default Page<AfterLoanReview> findAfterLoanReviewByCondition(Pageable page,AfterLoanReview afterLoanReview) throws Exception{
        Map<String,Object> qryCondition=new HashMap<String,Object>();
        StringBuffer sql=new StringBuffer("SELECT    C.ID                    AS \"id\", " );
                                        sql.append(" C.CASEAPPLYCODE         AS \"caseApplyCode\", " );
                                        sql.append(" C.APPLYAMOUNT           AS \"applyAmount\", " );
                                        sql.append(" C.PRODUCTTYPENAME       AS \"productTypeName\", " );
                                        sql.append(" C.PRODUCTSUBTYPENAME    AS \"productSubtypeName\", " );
                                        sql.append(" C.MECHANISMNAME         AS \"mechanismName\", " );
                                        sql.append(" C.STAGE                 AS \"stage\", " );
                                        sql.append(" C.CASEAPPLYSTATUS       AS \"caseApplyStatus\", " );
                                        sql.append(" TEMP.\"MONITORDATE\"    AS \"monitorDate\", " );
                                        sql.append(" CUS.CUSTOMERNAME        AS \"customerName\", " );
                                        sql.append(" CUS.CUSTOMERTYPE        AS \"customerType\", " );
                                        sql.append(" CUS.CREDENTIALTYPE      AS \"credentialType\", " );
                                        sql.append(" CUS.CREDENTIALNO        AS \"credentialNo\", " );
                                        sql.append(" CUS.ID                  AS \"customerId\", " );
                                        sql.append(" H.PROVINCE              AS \"province\", " );
                                        sql.append(" H.CITY                  AS \"city\", " );
                                        sql.append(" H.DISTRICT              AS \"district\" , " );
                                        sql.append(" H.MAILINGADDRESS        AS \"mailingAddress\" " );
                            sql.append(" FROM MKT_CASE_APPLY C " );
                            sql.append(" LEFT JOIN CASE_BEFORE_CUSTOMER CBC ON CBC.CASEAPPLYID = C.ID AND CBC.JOINTYPE = :joinType " );
                            sql.append(" LEFT JOIN CUST_BEFORE_CUSTOMER CUS ON CUS.ID = CBC.CUSTOMERID " );
                            sql.append(" LEFT JOIN MKT_COLLATERAL CO ON CO.CASEAPPLYID = C.ID " );
                            sql.append(" LEFT JOIN MKT_HOUSE_PROPERTY H ON H.ID = CO.ID " );
                            sql.append(" LEFT JOIN (SELECT MAX(R.MONITORDATE) AS \"MONITORDATE\" ,R.CASEAPPLYID AS \"CASEAPPLYID\" FROM AFTLOAN_MONITOR_RECORD R  GROUP BY R.CASEAPPLYID) TEMP ON TEMP.\"CASEAPPLYID\"=C.ID " );
                            
                            sql.append(" WHERE C.ISDELETED = 'F' and C.STAGE='YWDM009240'" );
        //参与类型
        qryCondition.put("joinType", CaseApplyAfterCustomer.MAIN_BORROW);
        //案件编号
        if(ObjectHelper.isNotEmpty(afterLoanReview.getCaseApplyCode())){
        	 sql.append(" AND C.CASEAPPLYCODE LIKE :caseApplyCode " );
             qryCondition.put("caseApplyCode","%"+afterLoanReview.getCaseApplyCode()+"%");
        }
        //主借人
        if(ObjectHelper.isNotEmpty(afterLoanReview.getCustomerName())){
            sql.append(" AND CUS.CUSTOMERNAME LIKE :customerName " );
            qryCondition.put("customerName","%"+afterLoanReview.getCustomerName()+"%");
        }
        //产品小类
        if(ObjectHelper.isNotEmpty(afterLoanReview.getProductSubtypeId())){
            sql.append(" AND C.PRODUCTSUBTYPEID= :productSubtypeId " );
            qryCondition.put("productSubtypeId",afterLoanReview.getProductSubtypeId());
        }
        //产品大类
        if(ObjectHelper.isNotEmpty(afterLoanReview.getProductTypeId())){
            sql.append(" AND C.PRODUCTTYPEID= :productTypeId " );
            qryCondition.put("productTypeId",afterLoanReview.getProductTypeId());
        }
        //机构
        if(ObjectHelper.isNotEmpty(afterLoanReview.getMechanismName())){
            sql.append(" AND C.MECHANISMNAME LIKE :mechanismName ");
            qryCondition.put("mechanismName","%"+afterLoanReview.getMechanismName()+"%");
        }
        //汇法信息
        if(ObjectHelper.isNotEmpty(afterLoanReview.getHuifa())){
        	
        }
        //工商
        if(ObjectHelper.isNotEmpty(afterLoanReview.getBusinessStatus())){
        	
        }
        sql.append("  ORDER BY C.CREATETIME DESC,C.UPDATETIME DESC ");
        return this.findBySqlEntityPage(page,sql.toString(),qryCondition,AfterLoanReview.class);
    }
}
