package com.zdsoft.finance.contract.vo;

import com.zdsoft.finance.common.base.BaseVo;
import com.zdsoft.finance.contract.entity.ConContractTpl;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title: ConContractTplVo.java 
 * @ClassName: ConContractTplVo 
 * @Description: 标准合同模板vo类
 * @author zhongyong 
 * @date 2017年2月28日 下午3:31:41 
 * @version V1.0
 */
public class ConContractTplVo extends BaseVo<ConContractTpl>{

	private static final long serialVersionUID = -6746637712211164952L;
	/**
	 * 资方类型
	 */
	private String capitalistType;
	/**
	 * 资方类别名称
	 */
	private String capitalistTypeNm;
	/**
	 * 资方ID
	 */
	private String capitalId;
	/**
	 * 资方名称
	 */
	private String capitalNm;
	/**
	 * 合同类型
	 */
	private String contractType;
	/**
	 * 合同类型名称
	 */
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
	
	
	public ConContractTplVo() {
		super();
	}
	
	public ConContractTplVo(ConContractTpl ConContractTpl, String[] args, String[] simpleArgs) {
		super(ConContractTpl, args, simpleArgs);
	}
	
	public ConContractTpl toPo() {
		ConContractTpl conContractTpl = new ConContractTpl();
		return (ConContractTpl)super.toPo(this, conContractTpl);
	}

	public ConContractTplVo(ConContractTpl conContractTpl) {
		super(conContractTpl);
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
	
}