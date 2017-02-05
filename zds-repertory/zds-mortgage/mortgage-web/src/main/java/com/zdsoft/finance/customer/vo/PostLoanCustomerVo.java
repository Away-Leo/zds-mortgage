package com.zdsoft.finance.customer.vo;

import com.zdsoft.finance.common.base.BaseVo;
import com.zdsoft.finance.customer.entity.PostLoanCustomer;

/**
 * 贷后客户
 * @author <a href="mailto:502549751@qq.com">zhangchao</a>
 * @createTime: 2017-01-10 14::41
 * @version: 1.0
 *
 */
public class PostLoanCustomerVo extends BaseVo<PostLoanCustomer> {
	
	/**
	 * 序列化
	 */
	private static final long serialVersionUID = 1L;

	/**
     * 姓名
     */
	private String customerName;
	
    /**
     * 证件类型
     */
	private String credentiaType;
	
    /**
     * 证件号
     */
	private String credentialNo;
	
    /**
     * 创建人姓名
     */
	private String creatorName;
	
    /**
     * 公司代码
     */
	private String companyCode;
	
    /**
     * 公司名称
     */
	private String companyName;
    
    /**
     * 部门代码
     */
	private String departmentCode;
	
    /**
     * 部门名称
     */
	private String departmentName;
	
    /**
     * 客户id
     */
	private String customerId;
	
    /**
     * 姓名
     */
	private Integer createType;

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
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

	public String getCreatorName() {
		return creatorName;
	}

	public void setCreatorName(String creatorName) {
		this.creatorName = creatorName;
	}

	public String getCompanyCode() {
		return companyCode;
	}

	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getDepartmentCode() {
		return departmentCode;
	}

	public void setDepartmentCode(String departmentCode) {
		this.departmentCode = departmentCode;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public Integer getCreateType() {
		return createType;
	}

	public void setCreateType(Integer createType) {
		this.createType = createType;
	}
	
	public PostLoanCustomerVo(){}
	
	public PostLoanCustomerVo(PostLoanCustomer po){
		super(po);
	}
	
	public PostLoanCustomer toPO(){
		PostLoanCustomer po = new PostLoanCustomer();
		return super.toPo(this, po);
	}
	
}
