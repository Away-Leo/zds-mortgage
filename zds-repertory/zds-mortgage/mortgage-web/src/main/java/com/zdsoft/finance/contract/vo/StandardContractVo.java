package com.zdsoft.finance.contract.vo;

import com.zdsoft.finance.common.base.BaseVo;
import com.zdsoft.finance.contract.entity.CoactContractTpl;

public class StandardContractVo extends BaseVo<CoactContractTpl>{

	/**
	 * id
	 */
	private String id;

	/**
	 * 合同编号
	 */
	private String contractNo;
	/**
	 * 合同名称
	 */
	private String contractName;
	/**
	 * 合同类型
	 */
	private String contractType;
	/**
	 * 合同模板类型
	 */
	private String contractTplType;
	/**
	 * 合同模板状态
	 */
	private String contractTplState;
	/**
	 * 备注
	 */
	private String remark;
	/**
	 * 模板申请单ID
	 */
	private String tplApplyId;
	/**
	 * 资方ID
	 */
	private String capitalId;
	/**
	 * 附件ID
	 */
	private String attachmentId;
	
	public StandardContractVo() {
		super();
	}

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


	public String getRemark() {
		return remark;
	}


	public void setRemark(String remark) {
		this.remark = remark;
	}


	public String getTplApplyId() {
		return tplApplyId;
	}


	public void setTplApplyId(String tplApplyId) {
		this.tplApplyId = tplApplyId;
	}


	public String getCapitalId() {
		return capitalId;
	}


	public void setCapitalId(String capitalId) {
		this.capitalId = capitalId;
	}


	public String getAttachmentId() {
		return attachmentId;
	}


	public void setAttachmentId(String attachmentId) {
		this.attachmentId = attachmentId;
	}

	
	
	public StandardContractVo(CoactContractTpl CoactContractTpl, String[] args, String[] simpleArgs) {
		super(CoactContractTpl, args, simpleArgs);
	}
	
	public CoactContractTpl toPo() {
		CoactContractTpl coactContractTpl = new CoactContractTpl();
		return (CoactContractTpl)super.toPo(this, coactContractTpl);
	}

	public StandardContractVo(CoactContractTpl coactContractTpl) {
		super(coactContractTpl);
	}
	
}