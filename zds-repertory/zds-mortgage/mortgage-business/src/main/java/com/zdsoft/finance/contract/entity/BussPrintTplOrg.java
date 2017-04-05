package com.zdsoft.finance.contract.entity;

import com.zdsoft.framework.core.common.domain.BaseEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "buss_print_tpl_org")
public class BussPrintTplOrg extends BaseEntity {
	/**
	 *
	 */
	private static final long serialVersionUID = -3307895559384707655L;

	@Column(length = 32)
	private String orgCode;

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public String getPrintTemplateId() {
		return printTemplateId;
	}

	public void setPrintTemplateId(String printTemplateId) {
		this.printTemplateId = printTemplateId;
	}

	@Column(length = 32)
	private String printTemplateId;
}
