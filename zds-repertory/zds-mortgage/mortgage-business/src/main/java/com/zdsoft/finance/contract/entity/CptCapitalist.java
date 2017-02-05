package com.zdsoft.finance.contract.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.zdsoft.framework.core.common.domain.BaseEntity;

public class CptCapitalist  extends BaseEntity{

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
	@Column(length = 20)
	private String capitalId;
	/**
	 * 附件ID
	 */
	@Column(length = 32)
	private String attachmentId;
}