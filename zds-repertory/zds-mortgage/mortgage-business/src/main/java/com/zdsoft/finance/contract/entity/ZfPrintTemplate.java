package com.zdsoft.finance.contract.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.zdsoft.framework.core.common.domain.BaseEntity;

@Entity
@Table(name = "zf_print_template")
public class ZfPrintTemplate  extends BaseEntity {
	private static final long serialVersionUID = 1L;
	
	/*套打模板Id*/
	@Column(length = 32)
	private String tid;
	
	/*（所属）机构Id*/
	@Column(length = 32)
	private String toSid;
	
	/*资金Id*/
	@Column(length = 32)
	private String fid;
	
	/*模板名称*/
	@Column(length = 50)
	private String ptName;
	
	/*适用业务类型*/
	@Column
	private int fitType;
	
	/*（可用）机构Id*/
	@Column(length = 32)
	private String fitSid;
	
	/*状态*/
	@Column
	private int status;
	
	public String getTid() {
		return tid;
	}

	public void setTid(String tid) {
		this.tid = tid;
	}

	public String getToSid() {
		return toSid;
	}

	public void setToSid(String toSid) {
		this.toSid = toSid;
	}

	public String getFid() {
		return fid;
	}

	public void setFid(String fid) {
		this.fid = fid;
	}

	public String getPtName() {
		return ptName;
	}

	public void setPtName(String ptName) {
		this.ptName = ptName;
	}

	public int getFitType() {
		return fitType;
	}

	public void setFitType(int fitType) {
		this.fitType = fitType;
	}

	public String getFitSid() {
		return fitSid;
	}

	public void setFitSid(String fitSid) {
		this.fitSid = fitSid;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
	
	
}
