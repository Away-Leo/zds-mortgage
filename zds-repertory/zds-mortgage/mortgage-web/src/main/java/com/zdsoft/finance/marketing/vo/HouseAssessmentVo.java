package com.zdsoft.finance.marketing.vo;

import java.math.BigDecimal;


import com.zdsoft.finance.common.base.BaseVo;
import com.zdsoft.finance.marketing.entity.HouseAssessment;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: HouseAssessmentVo.java 
 * @ClassName: HouseAssessmentVo 
 * @Description: 房产评估信息Vo
 * @author zhoushichao 
 * @date 2017年3月14日 下午7:49:06 
 * @version V1.0
 */
public class HouseAssessmentVo extends BaseVo<HouseAssessment>{

	private static final long serialVersionUID = 1L;

	/**
	 * 评估公司
	 */
	private String evaluationCompany;
	/**
	 * 评估价格
	 */
	private BigDecimal evaluationPrice;
	/**
	 * 评估时间
	 */
	private Long evaluationDate;
	
	public HouseAssessmentVo() {
		super();
	}
	public HouseAssessmentVo(HouseAssessment po) {
		super(po);
	}
	public HouseAssessment toPo(){
		HouseAssessment po = new HouseAssessment();
		return super.toPo(this, po);
	}
	
	public String getEvaluationCompany() {
		return evaluationCompany;
	}
	public void setEvaluationCompany(String evaluationCompany) {
		this.evaluationCompany = evaluationCompany;
	}
	public BigDecimal getEvaluationPrice() {
		return evaluationPrice;
	}
	public void setEvaluationPrice(BigDecimal evaluationPrice) {
		this.evaluationPrice = evaluationPrice;
	}
	public Long getEvaluationDate() {
		return evaluationDate;
	}
	public void setEvaluationDate(Long evaluationDate) {
		this.evaluationDate = evaluationDate;
	}
}
