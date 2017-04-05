package com.zdsoft.finance.contract.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.zdsoft.framework.core.common.domain.BaseEntity;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title: ConFormatContractDetail.java 
 * @ClassName: ConFormatContractDetail 
 * @Description: 格式化合同明细清单
 * @author zhongyong 
 * @date 2017年3月7日 下午4:40:20 
 * @version V1.0
 */
@Entity
@Table(name = "con_format_contract_detail")
public class ConFormatContractDetail extends BaseEntity {

	private static final long serialVersionUID = 8788572917875084191L;
	
	/**
	 * 资方
	 */
	@Column(length = 32)
	private String capitalistId;
	
	/**
	 * 合同类别
	 */
	@Column(length = 32)
	private String contractType;
	
	/**
	 * 合同名称
	 */
	@Column(length = 32)
	private String contractName;
	
	/**
	 * 份数
	 */
	@Column
	private Integer contractCopies;
	
	/**
	 * 是否收到
	 */
	@Column(length = 32)
	private String isReceive;
	
	/**
	 * 已使用合同份数
	 */
	@Column
	private Integer useNum;
	
	/**
	 * 已作废合同份数
	 */
	@Column
	private Integer cancellationNum;
	
	/**
	 * 标准合同模板ID
	 */
	@Column(length = 32)
	private String contractTemplateId;
	
	/**
	 * 格式化合同申领ID
	 */
	@Column(length = 32)
	private String formatContractApplyId;

	public String getCapitalistId() {
		return capitalistId;
	}

	public void setCapitalistId(String capitalistId) {
		this.capitalistId = capitalistId;
	}

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

	public Integer getContractCopies() {
		return contractCopies;
	}

	public void setContractCopies(Integer contractCopies) {
		this.contractCopies = contractCopies;
	}

	public String getIsReceive() {
		return isReceive;
	}

	public void setIsReceive(String isReceive) {
		this.isReceive = isReceive;
	}

	public Integer getUseNum() {
		return useNum;
	}

	public void setUseNum(Integer useNum) {
		this.useNum = useNum;
	}

	public Integer getCancellationNum() {
		return cancellationNum;
	}

	public void setCancellationNum(Integer cancellationNum) {
		this.cancellationNum = cancellationNum;
	}

	public String getContractTemplateId() {
		return contractTemplateId;
	}

	public void setContractTemplateId(String contractTemplateId) {
		this.contractTemplateId = contractTemplateId;
	}

	public String getFormatContractApplyId() {
		return formatContractApplyId;
	}

	public void setFormatContractApplyId(String formatContractApplyId) {
		this.formatContractApplyId = formatContractApplyId;
	}
	

}
