package com.zdsoft.finance.customer.vo;

import com.zdsoft.finance.common.base.BaseVo;
import com.zdsoft.finance.customer.entity.BlanckList;

/**
 * 黑名单
 * 版权所有：重庆正大华日软件有限公司
 * @Title: BlanckListVo.java 
 * @ClassName: BlanckListVo 
 * @Description: 
 * @author zhangchao 
 * @date 2017年2月6日 上午11:12:05 
 * @version V1.0
 */
public class BlanckListVo extends BaseVo<BlanckList> {

	
	/**   
	 * @Fields serialVersionUID : TODO   
	 */ 
	private static final long serialVersionUID = 1L;

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
    
    /**
     * 工作单位
     */
    private String workUnit;
    
    /**
     * 工作电话
     */
    private String workContact;
    
    /**
     * 家庭电话
     */
    private String familyContact;
    
    /**
     * 备注
     */
    private String remark;
    
    /**
     * 关联客户
     */
    private String relationCustomerId;

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

	public String getWorkUnit() {
		return workUnit;
	}

	public void setWorkUnit(String workUnit) {
		this.workUnit = workUnit;
	}

	public String getWorkContact() {
		return workContact;
	}

	public void setWorkContact(String workContact) {
		this.workContact = workContact;
	}

	public String getFamilyContact() {
		return familyContact;
	}

	public void setFamilyContact(String familyContact) {
		this.familyContact = familyContact;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getRelationCustomerId() {
		return relationCustomerId;
	}

	public void setRelationCustomerId(String relationCustomerId) {
		this.relationCustomerId = relationCustomerId;
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
