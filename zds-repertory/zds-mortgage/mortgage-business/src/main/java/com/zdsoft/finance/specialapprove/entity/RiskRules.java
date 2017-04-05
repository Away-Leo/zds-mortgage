package com.zdsoft.finance.specialapprove.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.zdsoft.framework.core.common.domain.BaseEntity;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: RiskRules.java  
 * @ClassName: RiskRules 
 * @Description: 风险规则明细
 * @author jingjy 
 * @date 2017年2月14日 下午5:15:20 
 * @version V1.0
 */
@Entity
@Table(name="exp_risk_rules")
public class RiskRules extends BaseEntity{

	/**   
	 * @Fields serialVersionUID :     
	 */ 
	private static final long serialVersionUID = 1L;

	/**
     * 案件ID
     */
    @Column(length = 32)
    private String caseApplyId;
    
    /**
     * 节点来源
     */
    @Column(length = 64)
    private String nodeSource;
    
    /**
     * 编号
     */
    @Column(length = 32)
    private String rulesNo;
    
    /**
     * 大类
     */
    @Column(length = 32)
    private String rulesType;
    
    /**
     * 提示
     */
    @Column(length = 256)
    private String rulesPrompt;
    
    /**
     * 结果
     */
    @Column(length = 20)
    private String rulesResult;
    
    /**
     * 特批状态
     */
    @Column(length = 20)
    private String specialStatus;

	public String getCaseApplyId() {
		return caseApplyId;
	}

	public void setCaseApplyId(String caseApplyId) {
		this.caseApplyId = caseApplyId;
	}

	public String getNodeSource() {
		return nodeSource;
	}

	public void setNodeSource(String nodeSource) {
		this.nodeSource = nodeSource;
	}

	public String getRulesNo() {
		return rulesNo;
	}

	public void setRulesNo(String rulesNo) {
		this.rulesNo = rulesNo;
	}

	public String getRulesType() {
		return rulesType;
	}

	public void setRulesType(String rulesType) {
		this.rulesType = rulesType;
	}

	public String getRulesPrompt() {
		return rulesPrompt;
	}

	public void setRulesPrompt(String rulesPrompt) {
		this.rulesPrompt = rulesPrompt;
	}

	public String getRulesResult() {
		return rulesResult;
	}

	public void setRulesResult(String rulesResult) {
		this.rulesResult = rulesResult;
	}

	public String getSpecialStatus() {
		return specialStatus;
	}

	public void setSpecialStatus(String specialStatus) {
		this.specialStatus = specialStatus;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
    
}
