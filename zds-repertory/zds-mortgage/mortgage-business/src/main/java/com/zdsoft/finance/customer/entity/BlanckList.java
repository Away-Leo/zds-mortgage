package com.zdsoft.finance.customer.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.zdsoft.framework.core.common.domain.BaseEntity;



/**
 * 黑名单
 * @author zhangchao
 * 2016/12/21
 */
@Entity
@Table(name = "zf_blancklist")
public class BlanckList extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
     * 姓名
     */
    @Column(length = 64)
    private String blackName;
    
    /**
     * 证件类型
     */
    @Column(length = 15)
    private String credentiaType;
    
    /**
     * 证件号
     */
    @Column(length = 18)
    private String credentialNo;
    
    /**
     * 原因类型
     */
    @Column(length = 15)
    private String reasonType;
    
    /**
     * 创建人id
     */
    @Column(length = 32)
    private String creatorId;
    
    /**
     * 公司代码
     */
    @Column(length = 32)
    private String companyCode;
    
    /**
     * 来源
     */
    @Column(length = 15)
    private String source;
    
    /**
     * 开始日期
     */
    @Column
    private Long startDate;
    
    /**
     * 结束日期
     */
    @Column
    private Long endDate;

	public String getBlackName() {
		return blackName;
	}

	public void setBlackName(String blackName) {
		this.blackName = blackName;
	}

	public String getCredentiaType() {
		return credentiaType;
	}

	public void setCredentiaType(String credentiaType) {
		this.credentiaType = credentiaType;
	}

	public String getCredentialNo() {
		return credentialNo;
	}

	public void setCredentialNo(String credentialNo) {
		this.credentialNo = credentialNo;
	}

	public String getReasonType() {
		return reasonType;
	}

	public void setReasonType(String reasonType) {
		this.reasonType = reasonType;
	}

	public String getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(String creatorId) {
		this.creatorId = creatorId;
	}

	public String getCompanyCode() {
		return companyCode;
	}

	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public Long getStartDate() {
		return startDate;
	}

	public void setStartDate(Long startDate) {
		this.startDate = startDate;
	}

	public Long getEndDate() {
		return endDate;
	}

	public void setEndDate(Long endDate) {
		this.endDate = endDate;
	}

}
