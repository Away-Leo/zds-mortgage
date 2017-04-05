package com.zdsoft.finance.marketing.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.zdsoft.framework.core.common.domain.BaseEntity;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: HouseAssessment.java 
 * @ClassName: HouseAssessment 
 * @Description: 房产评估信息实体
 * @author zhoushichao 
 * @date 2017年3月14日 下午5:45:20 
 * @version V1.0
 */
@Entity
@Table(name="mkt_house_assessment")
public class HouseAssessment extends BaseEntity {

	private static final long serialVersionUID = 1L;
	
	/**
	 * 房产
	 */
	@Column(length=20)
	private String housePropertyId;
	/**
	 * 评估公司
	 */
	@Column(length=128)
	private String evaluationCompany;
	/**
	 * 评估价格
	 */
	@Column(precision = 18, scale = 2)
	private BigDecimal evaluationPrice = BigDecimal.ZERO;
	/**
	 * 评估时间
	 */
	@Column
	private Long evaluationDate;
	
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
	public String getHousePropertyId() {
		return housePropertyId;
	}
	public void setHousePropertyId(String housePropertyId) {
		this.housePropertyId = housePropertyId;
	}
}
