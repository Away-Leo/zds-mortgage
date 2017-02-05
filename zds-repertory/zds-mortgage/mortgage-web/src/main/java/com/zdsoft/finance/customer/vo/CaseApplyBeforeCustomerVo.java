package com.zdsoft.finance.customer.vo;

import com.zdsoft.finance.common.base.BaseVo;
import com.zdsoft.finance.customer.entity.BeforeCustomer;
import com.zdsoft.finance.marketing.entity.CaseApplyBeforeCustomer;
import com.zdsoft.finance.marketing.vo.CaseApplyVo;

/**
 * 版权所有：重庆正大华日软件有限公司
 * 
 * @Title: CaseApplyBeforeCustomerVo.java
 * @Package com.zdsoft.finance.customer.vo
 * @Description: 案件客户关系表VO
 * @author Liyb
 * @date 2017年1月20日 下午4:40:43
 * @version V1.0
 */
public class CaseApplyBeforeCustomerVo extends BaseVo<CaseApplyBeforeCustomer> {

    /**
     * 
     */
    private static final long serialVersionUID = -8325117165521769369L;

    private CaseApplyVo caseApply;

    private BeforeCustomer beforeCustomer;
    /**
     * 参与类型
     */
    private String joinType;

    private String joinTypeName;

    public CaseApplyVo getCaseApply() {
        return caseApply;
    }

    public void setCaseApply(CaseApplyVo caseApply) {
        this.caseApply = caseApply;
    }

    public BeforeCustomer getBeforeCustomer() {
        return beforeCustomer;
    }

    public void setBeforeCustomer(BeforeCustomer beforeCustomer) {
        this.beforeCustomer = beforeCustomer;
    }

    public String getJoinType() {
        return joinType;
    }

    public void setJoinType(String joinType) {
        this.joinType = joinType;
    }

    public String getJoinTypeName() {
        return joinTypeName;
    }

    public void setJoinTypeName(String joinTypeName) {
        this.joinTypeName = joinTypeName;
    }

	public CaseApplyBeforeCustomer toPo() {
		CaseApplyBeforeCustomer po = new CaseApplyBeforeCustomer();
		return po;
	}
	public CaseApplyBeforeCustomerVo(CaseApplyBeforeCustomer po) {
		super(po,null,new String[]{"joinType"});
	}


}
