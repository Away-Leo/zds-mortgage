package com.zdsoft.finance.cooperator.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.zdsoft.framework.core.common.domain.BaseEntity;

/**
 * 资方附件
 * 
 * @author Hisa
 *
 */
@Entity
@Table(name = "cpt_attachment")
public class CooperateIdea extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 协议名称
	 */
	@Column(length = 64)
	private String agreementName;
	/**
	 * 附件
	 */
	@Column(length = 255)
	private String attachmentId;

	/**
	 * 关联的终端
	 */
	@ManyToOne
	@JoinColumn(name = "capitalistId")
	private Capitalist capitalist;

	public Capitalist getCapitalist() {
		return capitalist;
	}

	public void setCapitalist(Capitalist capitalist) {
		this.capitalist = capitalist;
	}

	public String getAgreementName() {
		return agreementName;
	}

	public void setAgreementName(String agreementName) {
		this.agreementName = agreementName;
	}

	public String getAttachmentId() {
		return attachmentId;
	}

	public void setAttachmentId(String attachmentId) {
		this.attachmentId = attachmentId;
	}

}
