package com.zdsoft.finance.cooperator.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.zdsoft.framework.core.common.domain.BaseEntity;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: EvaluateCompanyRule.java 
 * @ClassName: EvaluateCompanyRule 
 * @Description: 评估公司权重表
 * @author liuwei
 * @date 2017年3月8日 上午9:56:07 
 * @version V1.0
 */
@Entity
@Table(name="cpt_evaluate_company_rule")
public class EvaluateCompanyRule extends BaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 机构ID
	 */
	@Column(precision=32)
	private BigDecimal orgId = BigDecimal.ZERO;
	
	/**
	 * 机构名
	 */
	@Column(length=64)
	private String orgName;
	
	/**
	 * 城市名(省)
	 */
	@Column(length=64)
	private String provinceName;
	
	/**
	 * 城市名(市)
	 */
	@Column(length=64)
	private String cityName;
	
	/**
	 * 城市名（区）
	 */
	@Column(length=64)
	private String districtName;
	
	/**
	 * 规则
	 */
	@Column(length=200)
	private String rule;
	
	/**
	 * 评估公司(1)
	 */
	@Column(length=64)
	private String evaluateCompanyOne;
	
	/**
	 * 对外展示比例（%）(1)
	 */
	@Column(precision=30,scale=12)
	private BigDecimal outProportionOne = BigDecimal.ZERO;
	
	/**
	 * 计算规则(1)
	 */
	@Column(precision=30,scale=12)
	private BigDecimal calculateRuleOne = BigDecimal.ZERO;
	
	/**
	 * 评估公司(2)
	 */
	@Column(length=64)
	private String evaluateCompanyTwo;
	
	/**
	 * 对外展示比例（%）(2)
	 */
	@Column(precision=30,scale=12)
	private BigDecimal outProportionTwo = BigDecimal.ZERO;
	
	/**
	 * 计算规则(2)
	 */
	@Column(precision=30,scale=12)
	private BigDecimal calculateRuleTwo = BigDecimal.ZERO;
	
	/**
	 * 评估公司(3)
	 */
	@Column(length=64)
	private String evaluateCompanyThree;
	
	/**
	 * 对外展示比例（%）(3)
	 */
	@Column(precision=30,scale=12)
	private BigDecimal outProportionThree = BigDecimal.ZERO;
	
	/**
	 * 计算规则(3)
	 */
	@Column(precision=30,scale=12)
	private BigDecimal calculateRuleThree = BigDecimal.ZERO;
	
	/**
	 * 评估公司(4)
	 */
	@Column(length=64)
	private String evaluateCompanyFour;
	
	/**
	 * 对外展示比例（%）(4)
	 */
	@Column(precision=30,scale=12)
	private BigDecimal outProportionFour = BigDecimal.ZERO;
	
	/**
	 * 计算规则(4)
	 */
	@Column(precision=30,scale=12)
	private BigDecimal calculateRuleFour = BigDecimal.ZERO;
	
	/**
	 * 评估公司(5)
	 */
	@Column(length=64)
	private String evaluateCompanyFive;
	
	/**
	 * 对外展示比例（%）(5)
	 */
	@Column(precision=30,scale=12)
	private BigDecimal outProportionFive = BigDecimal.ZERO;
	
	/**
	 * 计算规则(5)
	 */
	@Column(precision=30,scale=12)
	private BigDecimal calculateRuleFive = BigDecimal.ZERO;

	public BigDecimal getOrgId() {
		return orgId;
	}

	public void setOrgId(BigDecimal orgId) {
		this.orgId = orgId;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getProvinceName() {
		return provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getRule() {
		return rule;
	}

	public void setRule(String rule) {
		this.rule = rule;
	}

	public String getEvaluateCompanyOne() {
		return evaluateCompanyOne;
	}

	public void setEvaluateCompanyOne(String evaluateCompanyOne) {
		this.evaluateCompanyOne = evaluateCompanyOne;
	}

	public BigDecimal getOutProportionOne() {
		return outProportionOne;
	}

	public void setOutProportionOne(BigDecimal outProportionOne) {
		this.outProportionOne = outProportionOne;
	}

	public BigDecimal getCalculateRuleOne() {
		return calculateRuleOne;
	}

	public void setCalculateRuleOne(BigDecimal calculateRuleOne) {
		this.calculateRuleOne = calculateRuleOne;
	}

	public String getEvaluateCompanyTwo() {
		return evaluateCompanyTwo;
	}

	public void setEvaluateCompanyTwo(String evaluateCompanyTwo) {
		this.evaluateCompanyTwo = evaluateCompanyTwo;
	}

	public BigDecimal getOutProportionTwo() {
		return outProportionTwo;
	}

	public void setOutProportionTwo(BigDecimal outProportionTwo) {
		this.outProportionTwo = outProportionTwo;
	}

	public BigDecimal getCalculateRuleTwo() {
		return calculateRuleTwo;
	}

	public void setCalculateRuleTwo(BigDecimal calculateRuleTwo) {
		this.calculateRuleTwo = calculateRuleTwo;
	}

	public String getEvaluateCompanyThree() {
		return evaluateCompanyThree;
	}

	public void setEvaluateCompanyThree(String evaluateCompanyThree) {
		this.evaluateCompanyThree = evaluateCompanyThree;
	}

	public BigDecimal getOutProportionThree() {
		return outProportionThree;
	}

	public void setOutProportionThree(BigDecimal outProportionThree) {
		this.outProportionThree = outProportionThree;
	}

	public BigDecimal getCalculateRuleThree() {
		return calculateRuleThree;
	}

	public void setCalculateRuleThree(BigDecimal calculateRuleThree) {
		this.calculateRuleThree = calculateRuleThree;
	}

	public String getEvaluateCompanyFour() {
		return evaluateCompanyFour;
	}

	public void setEvaluateCompanyFour(String evaluateCompanyFour) {
		this.evaluateCompanyFour = evaluateCompanyFour;
	}

	public BigDecimal getOutProportionFour() {
		return outProportionFour;
	}

	public void setOutProportionFour(BigDecimal outProportionFour) {
		this.outProportionFour = outProportionFour;
	}

	public BigDecimal getCalculateRuleFour() {
		return calculateRuleFour;
	}

	public void setCalculateRuleFour(BigDecimal calculateRuleFour) {
		this.calculateRuleFour = calculateRuleFour;
	}

	public String getEvaluateCompanyFive() {
		return evaluateCompanyFive;
	}

	public void setEvaluateCompanyFive(String evaluateCompanyFive) {
		this.evaluateCompanyFive = evaluateCompanyFive;
	}

	public BigDecimal getOutProportionFive() {
		return outProportionFive;
	}

	public void setOutProportionFive(BigDecimal outProportionFive) {
		this.outProportionFive = outProportionFive;
	}

	public BigDecimal getCalculateRuleFive() {
		return calculateRuleFive;
	}

	public void setCalculateRuleFive(BigDecimal calculateRuleFive) {
		this.calculateRuleFive = calculateRuleFive;
	}

	public String getDistrictName() {
		return districtName;
	}

	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}
	
}
