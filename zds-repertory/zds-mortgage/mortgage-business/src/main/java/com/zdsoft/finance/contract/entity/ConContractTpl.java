package com.zdsoft.finance.contract.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.zdsoft.framework.core.common.domain.BaseEntity;
/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title: ConContractTpl.java 
 * @ClassName: ConContractTpl 
 * @Description: 标准合同模板
 * @author zhongyong 
 * @date 2017年2月27日 上午11:14:25 
 * @version V1.0
 */
@Entity
@Table(name = "con_contract_tpl")
public class ConContractTpl  extends BaseEntity{
	
	private static final long serialVersionUID = -3318842712986445194L;
	/**
	 * 资方类型
	 */
	@Column(length = 20)
	private String capitalistType;
	/**
	 * 资方ID
	 */
	@Column(length = 32)
	private String capitalId;
	/**
	 * 合同类型
	 */
	@Column(length = 32)
	private String contractType;
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
	 * 合同状态
	 */
	@Column(length = 20)
	private String contractTplState;
	/**
	 * 备注
	 */
	@Column(length = 512)
	private String remark;
	/**
	 * 合同模板附件ID
	 */
	@Column(length = 32)
	private String attachmentId;
	/**
	 * 机构合同报备ID
	 */
	@Column(length = 32)
	private String orgCantractApplyId;
	
	public String getCapitalistType() {
		return capitalistType;
	}
	public void setCapitalistType(String capitalistType) {
		this.capitalistType = capitalistType;
	}
	public String getCapitalId() {
		return capitalId;
	}
	public void setCapitalId(String capitalId) {
		this.capitalId = capitalId;
	}
	public String getContractType() {
		return contractType;
	}
	public void setContractType(String contractType) {
		this.contractType = contractType;
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
	public String getOrgCantractApplyId() {
		return orgCantractApplyId;
	}
	public void setOrgCantractApplyId(String orgCantractApplyId) {
		this.orgCantractApplyId = orgCantractApplyId;
	}

}
