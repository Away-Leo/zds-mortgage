package com.zdsoft.finance.contract.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.zdsoft.framework.core.common.domain.BaseEntity;
/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title: ConFormatContractMaterial.java 
 * @ClassName: ConFormatContractMaterial 
 * @Description: 格式化合同实物
 * @author zhongyong 
 * @date 2017年3月7日 下午4:44:01 
 * @version V1.0
 */
@Entity
@Table(name = "con_format_contract_material")
public class ConFormatContractMaterial extends BaseEntity{
	
	/**
	 * 申领
	 */
	public static final String FILE_STATUS_APPLY = "0";
	/**
	 * 可使用
	 */
	public static final String FILE_STATUS_CANUSE = "1";
	/**
	 * 已使用
	 */
	public static final String FILE_STATUS_HASUSED = "2";
	/**
	 * 作废
	 */
	public static final String FILE_STATUS_CANCEL = "3";

	private static final long serialVersionUID = 6955977623802433230L;
	
	/**
	 * 文件号(每个实物不同或者是一批)
	 */
	@Column(length = 32)
	private String fileNo;
	
	/**
	 * 状态(0申领/1使用/2作废)
	 */
	@Column(length = 32)
	private String fileStatus;
	
	/**
	 * 作废人
	 */
	@Column(length = 32)
	private String cancellationEmpCode;
	
	/**
	 * 作废时间
	 */
	@Column
	private Long cancellationDate;
	
	/**
	 * 使用人
	 */
	@Column(length = 32)
	private String useEmpCode;
	
	/**
	 * 使用时间
	 */
	@Column
	private Long useDate;
	
	/**
	 * 所属申领批次ID
	 */
	@Column(length = 32)
	private String formatContractDetailId;

	public String getFileNo() {
		return fileNo;
	}

	public void setFileNo(String fileNo) {
		this.fileNo = fileNo;
	}

	public String getFileStatus() {
		return fileStatus;
	}

	public void setFileStatus(String fileStatus) {
		this.fileStatus = fileStatus;
	}

	public String getCancellationEmpCode() {
		return cancellationEmpCode;
	}

	public void setCancellationEmpCode(String cancellationEmpCode) {
		this.cancellationEmpCode = cancellationEmpCode;
	}

	public Long getCancellationDate() {
		return cancellationDate;
	}

	public void setCancellationDate(Long cancellationDate) {
		this.cancellationDate = cancellationDate;
	}

	public String getUseEmpCode() {
		return useEmpCode;
	}

	public void setUseEmpCode(String useEmpCode) {
		this.useEmpCode = useEmpCode;
	}

	public Long getUseDate() {
		return useDate;
	}

	public void setUseDate(Long useDate) {
		this.useDate = useDate;
	}

	public String getFormatContractDetailId() {
		return formatContractDetailId;
	}

	public void setFormatContractDetailId(String formatContractDetailId) {
		this.formatContractDetailId = formatContractDetailId;
	}

}
