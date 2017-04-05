package com.zdsoft.finance.cooperator.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.zdsoft.framework.core.common.domain.BaseEntity;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: EvaluationCompanyContact.java 
 * @ClassName: EvaluationCompanyContact 
 * @Description: 评估公司联系方式表
 * @author liuwei
 * @date 2017年3月8日 上午9:56:27 
 * @version V1.0
 */
@Entity
@Table(name = "cpt_evaluation_company_contact")
public class EvaluationCompanyContact extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 联系人姓名
	 */
	@Column(length = 64)
	private String contactName;
	/**
	 * 联系类型
	 */
	@Column(length = 20)
	private String contactType;
	/**
	 * 电话号码
	 */
	@Column(length = 16)
	private String contactTelNumber;

	/**
	 * 关联的终端
	 */
	@ManyToOne
	@JoinColumn(name = "evaluationId")
	private EvaluationCompany evaluationCompany;

	public EvaluationCompany getEvaluationCompany() {
		return evaluationCompany;
	}

	public void setEvaluationCompany(EvaluationCompany evaluationCompany) {
		this.evaluationCompany = evaluationCompany;
	}

	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	public String getContactType() {
		return contactType;
	}

	public void setContactType(String contactType) {
		this.contactType = contactType;
	}

	public String getContactTelNumber() {
		return contactTelNumber;
	}

	public void setContactTelNumber(String contactTelNumber) {
		this.contactTelNumber = contactTelNumber;
	}

}
