package com.zdsoft.finance.marketing.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.zdsoft.framework.core.common.domain.BaseEntity;

/** 	
* 版权所有：重庆正大华日软件有限公司	
* @Title: CaseMaterialPromise.java 	
* @Package com.zdsoft.finance.marketing.entity 	
* @Description: 后补资料	
* @author liuhuan 	
* @date 2017年1月17日 下午4:01:31 	
* @version V1.0 	
*/
@Entity
@Table(name="case_material_promise")
public class CaseMaterialPromise extends BaseEntity{

	private static final long serialVersionUID = 1184424305847731800L;
	
	/**
	 * 关联案件
	 */
	@ManyToOne
	@JoinColumn(name="caseApplyId")
	private CaseApply caseApply;
	
	/**
	 * 后补资料类型
	 */
	@Column(length=32)
	private String materialType;
	
	/**
	 * 预计后补时间
	 */
	@Column
	private Long predictDate;
	
	/**
	 * 操作人Id
	 */
	@Column(length=32)
	private String operatorId;
	
	/**
	 * 承诺时间
	 */
	@Column
	private Long promiseDate;
	
	/**
	 * 备注
	 */
	@Column(length=500)
	private String mo;
	
	public String getMo() {
		return mo;
	}

	public void setMo(String mo) {
		this.mo = mo;
	}

	public CaseApply getCaseApply() {
		return caseApply;
	}

	public void setCaseApply(CaseApply caseApply) {
		this.caseApply = caseApply;
	}

	public String getMaterialType() {
		return materialType;
	}

	public void setMaterialType(String materialType) {
		this.materialType = materialType;
	}

	public Long getPredictDate() {
		return predictDate;
	}

	public void setPredictDate(Long predictDate) {
		this.predictDate = predictDate;
	}

	public String getOperatorId() {
		return operatorId;
	}

	public void setOperatorId(String operatorId) {
		this.operatorId = operatorId;
	}

	public Long getPromiseDate() {
		return promiseDate;
	}

	public void setPromiseDate(Long promiseDate) {
		this.promiseDate = promiseDate;
	}
	
	
	
}
