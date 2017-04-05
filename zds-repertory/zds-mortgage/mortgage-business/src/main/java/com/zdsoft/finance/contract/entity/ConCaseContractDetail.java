package com.zdsoft.finance.contract.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.zdsoft.framework.core.common.domain.BaseEntity;

/** 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: ConCaseContractDetail.java 
 * @ClassName: ConCaseContractDetail 
 * @Description: 合同明细表
 * @author yangzhiran
 * @date 2017年3月2日 上午09:24:44 
 * @version V1.0 
 */ 

@Entity
@Table(name = "con_case_contract_detail")
public class ConCaseContractDetail extends BaseEntity{
	/**
	 * id
	 */
	private static final long serialVersionUID = 1L;
	
	
	/**
	 * 合同分类(生成/实物)
	 */
	@Column(length = 32)
    private String contractType;
	/**
	 * 合同名称
	 */
	@Column(length = 32)
    private String contractName;
	/**
	 * 实例化附件Id(生成类型的才有，产品合同)
	 */
	@Column(length = 32)
    private String attachmentId;
	/**
	 * 格式化合同实物ID(实物的才有，机构合同)
	 */
	@Column(length = 32)
    private String formatContractMaterialId;
	/**
	 * 案件合同ID
	 */
	@Column(length = 32)
    private String caseContractId;
	public String getContractType() {
		return contractType;
	}
	public void setContractType(String contractType) {
		this.contractType = contractType;
	}
	public String getContractName() {
		return contractName;
	}
	public void setContractName(String contractName) {
		this.contractName = contractName;
	}
	public String getAttachmentId() {
		return attachmentId;
	}
	public void setAttachmentId(String attachmentId) {
		this.attachmentId = attachmentId;
	}
	public String getFormatContractMaterialId() {
		return formatContractMaterialId;
	}
	public void setFormatContractMaterialId(String formatContractMaterialId) {
		this.formatContractMaterialId = formatContractMaterialId;
	}
	public String getCaseContractId() {
		return caseContractId;
	}
	public void setCaseContractId(String caseContractId) {
		this.caseContractId = caseContractId;
	}
}
