package com.zdsoft.finance.marketing.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.zdsoft.finance.customer.entity.AfterCustomer;
import com.zdsoft.framework.core.common.domain.BaseEntity;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: CaseApplyAfterCustomer.java 
 * @ClassName: CaseApplyAfterCustomer 
 * @Description: 贷后案件业务关联人表
 * @author xj 
 * @date 2017年2月14日 上午10:38:16 
 * @version V1.0
 */
@Entity
@Table(name = "case_after_customer")
public class CaseApplyAfterCustomer extends BaseEntity {

    /**
     * 主借人
     */
    public static String MAIN_BORROW = "YWDM0051036";
    /**
     * 担保人
     */
    public static String GUARANTOR = "YWDM0051038";
    /**
     * 序列化
     */
    private static final long serialVersionUID = 7571013779413761584L;
    /**
     * 案件
     */
    @ManyToOne
    @JoinColumn(name = "caseApplyId")
    private CaseApply caseApply;
    /**
     * 贷前客户
     */
    @ManyToOne
    @JoinColumn(name = "customerId")
    private AfterCustomer afterCustomer;
    /**
     * 参与类型
     */
    @Column(length = 20)
    private String joinType;

    public CaseApply getCaseApply() {
        return caseApply;
    }

    public void setCaseApply(CaseApply caseApply) {
        this.caseApply = caseApply;
    }

	public AfterCustomer getAfterCustomer() {
		return afterCustomer;
	}

	public void setAfterCustomer(AfterCustomer afterCustomer) {
		this.afterCustomer = afterCustomer;
	}

	public String getJoinType() {
        return joinType;
    }

    public void setJoinType(String joinType) {
        this.joinType = joinType;
    }

}
