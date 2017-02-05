package com.zdsoft.finance.cooperator.vo;

import java.math.BigDecimal;

import com.zdsoft.finance.common.base.BaseVo;
import com.zdsoft.finance.cooperator.entity.EvaluateCompanyRule;
/**
 * 评估公司权重表
 * @author Hisa
 *
 */
public class EvaluateCompanyRuleVo extends BaseVo<EvaluateCompanyRule>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 机构ID
	 */
	private BigDecimal orgId;
	
	/**
	 * 机构名
	 */
	private String orgName;
	
	/**
	 * 城市名(省)
	 */
	private String provinceName;
	
	/**
	 * 城市名(市)
	 */
	private String cityName;
	
	/**
	 * 城市名（区）
	 */
	private String districtName;
	
	/**
	 * 城市
	 */
	private String city;
	
	/**
	 * 规则
	 */
	private String rule;
	
	/**
	 * 评估公司(1)
	 */
	private String evaluateCompanyOne;
	
	/**
	 * 对外展示比例（%）(1)
	 */
	private BigDecimal outProportionOne;
	
	/**
	 * 计算规则(1)
	 */
	private BigDecimal calculateRuleOne;
	
	/**
	 * 评估公司(2)
	 */
	private String evaluateCompanyTwo;
	
	/**
	 * 对外展示比例（%）(2)
	 */
	private BigDecimal outProportionTwo;
	
	/**
	 * 计算规则(2)
	 */
	private BigDecimal calculateRuleTwo;
	
	/**
	 * 评估公司(3)
	 */
	private String evaluateCompanyThree;
	
	/**
	 * 对外展示比例（%）(3)
	 */
	private BigDecimal outProportionThree;
	
	/**
	 * 计算规则(3)
	 */
	private BigDecimal calculateRuleThree;
	
	/**
	 * 评估公司(4)
	 */
	private String evaluateCompanyFour;
	
	/**
	 * 对外展示比例（%）(4)
	 */
	private BigDecimal outProportionFour;
	
	/**
	 * 计算规则(4)
	 */
	private BigDecimal calculateRuleFour;
	
	/**
	 * 评估公司(5)
	 */
	private String evaluateCompanyFive;
	
	/**
	 * 对外展示比例（%）(5)
	 */
	private BigDecimal outProportionFive;
	
	/**
	 * 计算规则(5)
	 */
	private BigDecimal calculateRuleFive;

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
	
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getDistrictName() {
		return districtName;
	}

	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}

	public EvaluateCompanyRule toPO(){
		EvaluateCompanyRule po = new EvaluateCompanyRule();
		return super.toPo(this, po);
	}
	
	public EvaluateCompanyRuleVo(){}
	
	public EvaluateCompanyRuleVo(EvaluateCompanyRule po){
		super(po);
	}
}
