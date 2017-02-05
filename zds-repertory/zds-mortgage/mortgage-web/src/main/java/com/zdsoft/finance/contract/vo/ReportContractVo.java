package com.zdsoft.finance.contract.vo;

import com.zdsoft.finance.common.base.BaseVo;
import com.zdsoft.finance.contract.entity.CoactAgencyContractTplApply;

public class ReportContractVo  extends BaseVo<CoactAgencyContractTplApply>{

	/**
	 * id
	 */
	private String id;

	/**
	 * 申请编号
	 */
	private String applyNo;
	/**
	 * 申请类型
	 */
	private String applyType;
	/**
	 * 申请单标题
	 */
	private String applyTitle;
	/**
	 * 申请状态
	 */
	private String applyState;
	/**
	 * 申请原因
	 */
	private String applyReason;
	/**
	 * 申请时间
	 */
	private String applyDate;
	/**
	 * 申请人ID
	 */
	private String applicantId;
	/**
	 * 所属机构
	 */
	private String agencyId;
	
	
	public ReportContractVo(CoactAgencyContractTplApply CoactAgencyContractTplApply, String[] args, String[] simpleArgs) {
		super(CoactAgencyContractTplApply, args, simpleArgs);
	}
}