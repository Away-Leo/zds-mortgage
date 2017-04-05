package com.zdsoft.finance.contract.vo;

import javax.persistence.Column;

import com.zdsoft.finance.common.base.BaseVo;
import com.zdsoft.finance.contract.entity.ConContractTpl;

public class StandardContractVo extends BaseVo<ConContractTpl>{

	/**
	 * id
	 */
	private String id;
	/**
	 * 资方类型
	 */
	private String capitalistType;
	/*
	 * 资方类别名称
	 */
	private String capitalistTypeNm;
	/**
	 * 资方ID
	 */
	private String capitalId;
	private String capitalNm;
	/**
	 * 合同类型
	 */
	private String contractType;
	private String contractTypeNm;
	/**
	 * 合同编号
	 */
	private String contractNo;
	/**
	 * 合同名称
	 */
	private String contractName;
	/**
	 * 合同状态
	 */
	private String contractTplState;
	/**
	 * 备注
	 */
	private String remark;
	/**
	 * 合同模板附件ID
	 */
	private String attachmentId;
	private String attachName;
	/**
	 * 机构合同报备ID
	 */
	private String orgCantractApplyId;
	
	
	public String getId() {
		return id;
	}




	public void setId(String id) {
		this.id = id;
	}




	public String getCapitalistType() {
		return capitalistType;
	}




	public void setCapitalistType(String capitalistType) {
		this.capitalistType = capitalistType;
	}




	public String getCapitalistTypeNm() {
		return capitalistTypeNm;
	}




	public void setCapitalistTypeNm(String capitalistTypeNm) {
		this.capitalistTypeNm = capitalistTypeNm;
	}




	public String getCapitalId() {
		return capitalId;
	}




	public void setCapitalId(String capitalId) {
		this.capitalId = capitalId;
	}




	public String getCapitalNm() {
		return capitalNm;
	}




	public void setCapitalNm(String capitalNm) {
		this.capitalNm = capitalNm;
	}




	public String getContractType() {
		return contractType;
	}




	public void setContractType(String contractType) {
		this.contractType = contractType;
	}




	public String getContractTypeNm() {
		return contractTypeNm;
	}




	public void setContractTypeNm(String contractTypeNm) {
		this.contractTypeNm = contractTypeNm;
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




	public String getAttachmentId() {
		return attachmentId;
	}




	public void setAttachmentId(String attachmentId) {
		this.attachmentId = attachmentId;
	}




	public String getAttachName() {
		return attachName;
	}




	public void setAttachName(String attachName) {
		this.attachName = attachName;
	}




	public String getOrgCantractApplyId() {
		return orgCantractApplyId;
	}




	public void setOrgCantractApplyId(String orgCantractApplyId) {
		this.orgCantractApplyId = orgCantractApplyId;
	}
	
	public StandardContractVo() {
		super();
	}

	
	public StandardContractVo(ConContractTpl ConContractTpl, String[] args, String[] simpleArgs) {
		super(ConContractTpl, args, simpleArgs);
	}
	
	public ConContractTpl toPo() {
		ConContractTpl conContractTpl = new ConContractTpl();
		return (ConContractTpl)super.toPo(this, conContractTpl);
	}

	public StandardContractVo(ConContractTpl conContractTpl) {
		super(conContractTpl);
	}
	
}