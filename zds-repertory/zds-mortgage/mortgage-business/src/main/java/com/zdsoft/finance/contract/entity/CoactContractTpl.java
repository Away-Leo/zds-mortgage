package com.zdsoft.finance.contract.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.zdsoft.finance.cooperator.entity.Capitalist;
import com.zdsoft.framework.core.common.domain.BaseEntity;

/**
 * 标准合同模板表
 * 
 * @author wangnengduo
 *
 * 
 *         )
 */
@Entity
@Table(name = "coact_contract_tpl")
public class CoactContractTpl extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * id
	 */
	@Column(length = 32)
	private String id;
	/**
	 * 合同编号
	 */
	@Column(length = 32)
	private String contractNo;
	/**
	 * 合同名称
	 */
	@Column(length = 128)
	private String contractName;
	/**
	 * 合同类型
	 */
	@Column(length = 20)
	private String contractType;
	/**
	 * 合同模板类型
	 */
	@Column(length = 20)
	private String contractTplType;
	/**
	 * 合同模板状态
	 */
	@Column(length = 20)
	private String contractTplState;
	/**
	 * 备注
	 */
	@Column(length = 512)
	private String remark;
	/**
	 * 模板申请单ID
	 */
	@Column(length = 32)
	private String tplApplyId;
	/**
	 * 资方ID
	 */
	// @Column(length = 20)
	// private String capitalId;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "capitalId", nullable = false)
	private Capitalist capitalist;

	/**
	 * 附件ID
	 */
	@Column(length = 32)
	private String attachmentId;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getContractNo() {
		return contractNo;
	}

	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}

	public String getContractName() {
		return contractName;
	}

	public void setContractName(String contractName) {
		this.contractName = contractName;
	}

	public String getContractType() {
		return contractType;
	}

	public void setContractType(String contractType) {
		this.contractType = contractType;
	}

	public String getContractTplType() {
		return contractTplType;
	}

	public void setContractTplType(String contractTplType) {
		this.contractTplType = contractTplType;
	}

	public String getContractTplState() {
		return contractTplState;
	}

	public void setContractTplState(String contractTplState) {
		this.contractTplState = contractTplState;
	}

	public String getTplApplyId() {
		return tplApplyId;
	}

	public void setTplApplyId(String tplApplyId) {
		this.tplApplyId = tplApplyId;
	}

	// public String getCapitalId() {
	// return capitalId;
	// }
	//
	// public void setCapitalId(String capitalId) {
	// this.capitalId = capitalId;
	// }

	public String getAttachmentId() {
		return attachmentId;
	}

	public Capitalist getCapitalist() {
		return capitalist;
	}

	public void setCapitalist(Capitalist capitalist) {
		this.capitalist = capitalist;
	}

	public void setAttachmentId(String attachmentId) {
		this.attachmentId = attachmentId;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}