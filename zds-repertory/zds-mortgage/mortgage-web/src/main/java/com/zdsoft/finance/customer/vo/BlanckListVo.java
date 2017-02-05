package com.zdsoft.finance.customer.vo;

import com.zdsoft.finance.common.base.BaseVo;
import com.zdsoft.finance.customer.entity.BlanckList;

/**
 * 黑名单
 * @author zhangchao
 * 2016/12/21
 */
public class BlanckListVo extends BaseVo<BlanckList> {

	
	/**
     * 姓名
     */
    private String blackName;
    
    /**
     * 证件类型
     */
    private String credentiaType;
    
    /**
     * 证件号
     */
    private String credentialNo;
    
    /**
     * 原因类型
     */
    private String reasonType;
    
    /**
     * 创建人id
     */
    private String creatorId;
    
    /**
     * 公司代码
     */
    private String companyCode;
    
    /**
     * 来源
     */
    private String source;
    
    /**
     * 开始日期
     */
    private Long startDate;
    
    /**
     * 结束日期
     */
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

	public BlanckListVo(){}
	
	public BlanckListVo(BlanckList po){
		super(po);
	}
	
	public BlanckList toPO(){
		BlanckList po = new BlanckList();
		return super.toPo(this, po);
	}
}
