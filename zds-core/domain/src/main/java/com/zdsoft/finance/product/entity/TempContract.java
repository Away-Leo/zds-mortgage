package com.zdsoft.finance.product.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.zdsoft.framework.core.common.domain.BaseEntity;


/**
 * TODO 合同临时域对象，用作产品合同模版关联
 * @createTime 2017年1月10日 下午3:23:41
 * @author <a href="mailto:gufeng@zdsoft.cn">gufeng</a>
 * @version 1.0
 */
@Entity
@Table(name="prct_temp_contract")
public class TempContract extends BaseEntity {

	private static final long serialVersionUID = 7577976424508280581L;

	/**
	 * 合同名称
	 */
	@Column(length = 32)
	private String contractNm;
	
	/**
	 * 合同附件名称
	 */
	@Column(length = 255)
	private String attrNm;
	
	/**
	 * 合同类型
	 */
	@Column(length = 32)
	private String contractType;

	public String getContractNm() {
		return contractNm;
	}

	public void setContractNm(String contractNm) {
		this.contractNm = contractNm;
	}

	public String getAttrNm() {
		return attrNm;
	}

	public void setAttrNm(String attrNm) {
		this.attrNm = attrNm;
	}

	public String getContractType() {
		return contractType;
	}

	public void setContractType(String contractType) {
		this.contractType = contractType;
	}
	
}
