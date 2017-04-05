package com.zdsoft.finance.cooperator.vo;

import java.math.BigDecimal;
import java.util.Map;

import com.zdsoft.finance.common.base.BaseVo;
import com.zdsoft.finance.cooperator.entity.WeightRule;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: WeightRuleVo.java 
 * @ClassName: WeightRuleVo 
 * @Description: 权重计算规则Vo
 * @author liuwei
 * @date 2017年3月9日 上午11:28:11 
 * @version V1.0
 */
public class WeightRuleVo extends BaseVo<WeightRule> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 评估公司组合
	 */
	private Map<?, ?> evaluateCompanyMap;
	/**
	 * 对外比例
	 */
	private BigDecimal outProportion;
	/**
	 * 计算规则
	 */
	private String calculateRule;

	public Map<?, ?> getEvaluateCompanyMap() {
		return evaluateCompanyMap;
	}

	public void setEvaluateCompanyMap(Map<?, ?> evaluateCompanyMap) {
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

	public WeightRule toPO() {
		WeightRule po = new WeightRule();
		return super.toPo(this, po);
	}

}
