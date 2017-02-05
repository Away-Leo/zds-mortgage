package com.zdsoft.finance.casemanage.casetracking.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.zdsoft.finance.casemanage.casetracking.entity.ManualNodeRecord;
import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.finance.marketing.entity.CaseApplyBeforeCustomer;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title:CaseTrackingRepository.java
 * @Package:com.zdsoft.finance.casemanage.casetracking.repository
 * @Description:CaseTrackingRepository层
 * @author: caixiekang
 * @date:2017年1月14日 上午10:44:38
 * @version:v1.0
 */
public interface CaseTrackingRepository extends CustomRepository<ManualNodeRecord, String> {

    /**
     * @Description:案件管理的案件跟踪表的SQL语句
     * @author: caixiekang
     */
    public StringBuffer SQL = new StringBuffer("SELECT" + " ca.caseApplyCode  \"caseApplyCode\", " 
            + " cu.customerName \"customerName\", "
            + " cu.credentialNo \"credentialNo\", "
            + " prc.name  \"categoryName\", "
            + " prp.productName \"childName\", " 
            + " ca.applyAmount \"applyAmount\", "
            + " ca.creditMember \"creditMember\", " 
            + " rec.nodeName \"nodeName\", "
            + " rec.operatorName \"operatorName\", "
            + " ca.caseApplyStatus \"caseApplyStatus\", "
            + " ca.developmentManagerName \"developmentManagerName\", "
            + " ca.applyDate \"applyDate\", "
            + " ca.id \"id\" "
            + " FROM mark_case_apply ca "
            + " LEFT JOIN prct_category prc ON ca.productTypeId = prc.id "
            + " LEFT JOIN prct_product prp ON  ca.productSubtypeId = prp.id "
            + " LEFT JOIN case_before_customer casea ON ca.id = casea.caseApplyId "
            + " LEFT JOIN cus_before_customer cu ON casea.customerId = cu.id "
            + " LEFT JOIN case_manual_node_record_before  rec ON ca.id = rec.caseApplyId  "
            + " WHERE 1 = 1 AND casea.joinType = '" + CaseApplyBeforeCustomer.MAIN_BORROW + "' ");
                
    /**
     * @Description:案件管理的案件跟踪表的SQL语句
     * @author:caixiekang
     */
    public StringBuffer EXTEND_SQL = new StringBuffer(" ORDER BY applyDate DESC");
}
