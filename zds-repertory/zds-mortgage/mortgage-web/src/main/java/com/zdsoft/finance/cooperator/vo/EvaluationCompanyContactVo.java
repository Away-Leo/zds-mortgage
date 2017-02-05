package com.zdsoft.finance.cooperator.vo;

import com.zdsoft.finance.common.base.BaseVo;
import com.zdsoft.finance.cooperator.entity.EvaluationCompanyContact;

/**
 * 评估公司联系方式表
 * 
 * @author Hisa
 *
 */
public class EvaluationCompanyContactVo extends BaseVo<EvaluationCompanyContact> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 联系人姓名
	 */
	private String contactName;
	/**
	 * 联系类型
	 */
	private String contactType;
	private String contactTypeNm;
	/**
	 * 电话号码
	 */
	private String contactTelNumber;
	/**
	 * 对应的评估公司
	 */
	private String evaluationId;

	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	public String getContactTelNumber() {
		return contactTelNumber;
	}

	public void setContactTelNumber(String contactTelNumber) {
		this.contactTelNumber = contactTelNumber;
	}

	public String getEvaluationId() {
		return evaluationId;
	}

	public void setEvaluationId(String evaluationId) {
		this.evaluationId = evaluationId;
	}

	public String getContactType() {
		return contactType;
	}

	public void setContactType(String contactType) {
		this.contactType = contactType;
	}

	public String getContactTypeNm() {
		return contactTypeNm;
	}

	public void setContactTypeNm(String contactTypeNm) {
		this.contactTypeNm = contactTypeNm;
	}

	public EvaluationCompanyContactVo() {
		super();
	}

	public EvaluationCompanyContactVo(EvaluationCompanyContact evaluationCompanyContact) {
		super(evaluationCompanyContact, null, new String[] { "contactType" });
	}

	public EvaluationCompanyContact toPO() {
		EvaluationCompanyContact po = new EvaluationCompanyContact();
		return super.toPo(this, po);
	}
}
