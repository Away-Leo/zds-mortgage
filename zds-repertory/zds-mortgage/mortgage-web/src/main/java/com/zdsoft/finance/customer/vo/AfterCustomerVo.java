package com.zdsoft.finance.customer.vo;

import com.zdsoft.finance.common.base.BaseVo;
import com.zdsoft.finance.common.utils.VoUtil;
import com.zdsoft.framework.core.common.domain.BaseEntity;

/**
 * 贷后客户
 * 版权所有：重庆正大华日软件有限公司
 * @Title: PostLoanCustomerVo.java 
 * @ClassName: PostLoanCustomerVo 
 * @Description: 
 * @author zhangchao 
 * @date 2017年2月6日 上午11:12:38 
 * @version V1.0
 */

public class AfterCustomerVo <T extends BaseEntity> extends BaseVo<T> {

	/**
	 * 序列化
	 */
	private static final long serialVersionUID = -3888348610863554757L;
	/**
	 * 姓名
	 */
	private String customerName;
	/**
	 * 证件类型
	 */
	private String credentialType;
	private String credentialTypeName;//modify by liuhuan 2017-1-20    证件类型名称 
	/**
	 * 证件号码
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
	 * 部门代码
	 */
	private String departmentName;
	/**
	 * 客户创建类型
	 */
	private Integer createType;
	
	public String getCredentialTypeName() {
		return credentialTypeName;
	}
	public void setCredentialTypeName(String credentialTypeName) {
		this.credentialTypeName = credentialTypeName;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	
	public String getCredentialType() {
		return credentialType;
	}
	public void setCredentialType(String credentialType) {
		this.credentialType = credentialType;
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
	public Integer getCreateType() {
		return createType;
	}
	public void setCreateType(Integer createType) {
		this.createType = createType;
	}
	public AfterCustomerVo() {
		super();
	}

	/**
	 * 构造器
	 * 
	 * @param t
	 *            对象
	 * @param args
	 *            不复制的参数
	 * @param simpleArgs
	 *            需要转换simpleCode的属性
	 */
	public AfterCustomerVo(T t, String[] args, String[] simpleArgs) {
		VoUtil.copyPoperties(t, this, false, args, simpleArgs);
	}

	/**
	 * 构造器
	 * 
	 * @param t
	 *            对象
	 * @param args
	 *            不复制的参数
	 * @param simpleArgs
	 *            需要转换simpleCode的属性
	 */
	public AfterCustomerVo(T t, String[] args, String[] simpleArgs, String[] dateArgs) {
		VoUtil.copyPoperties(t, this, false, args, simpleArgs, dateArgs);
	}

	/**
	 * 构造器
	 * 
	 * @param t
	 *            对象
	 * @param args
	 *            不复制的参数
	 */
	public AfterCustomerVo(T t, String[] args) {
		VoUtil.copyPoperties(t, this, false, args);
	}

	/**
	 * 构造器
	 * 
	 * @param t
	 *            对象
	 */
	public AfterCustomerVo(T t) {
		VoUtil.copyPoperties(t, this, false);
	}
}
