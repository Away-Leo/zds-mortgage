package com.zdsoft.finance.cooperator.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.zdsoft.framework.core.common.domain.BaseEntity;
/**
 * 权重计算规则表
 * @author Hisa
 *
 */
@Entity
@Table(name="zf_weight_rule")
public class WeightRule extends BaseEntity{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 评估公司组合
	 */
	private String evaluateCompanyMap;
	/**
	 * 对外比例
	 */
	@Column(precision=16,scale=4)
	private BigDecimal outProportion;
	/**
	 * 计算规则
	 */
	@Column(length=200)
	private String calculateRule;
	
	
	
	
	public String getEvaluateCompanyMap() {
		return evaluateCompanyMap;
	}
	public void setEvaluateCompanyMap(String evaluateCompanyMap) {
		this.evaluateCompanyMap = evaluateCompanyMap;
	}
	public BigDecimal getOutProportion() {
		return outProportion;
	}
	public void setOutProportion(BigDecimal outProportion) {
		this.outProportion = outProportion;
	}
	public String getCalculateRule() {
		return calculateRule;
	}
	public void setCalculateRule(String calculateRule) {
		this.calculateRule = calculateRule;
	}
	
	
}
