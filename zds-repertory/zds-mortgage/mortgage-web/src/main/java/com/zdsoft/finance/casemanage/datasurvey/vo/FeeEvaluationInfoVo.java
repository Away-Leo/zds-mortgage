package com.zdsoft.finance.casemanage.datasurvey.vo;

import java.io.Serializable;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title:FeeEvaluationInfoVo.java
 * @Package:com.zdsoft.finance.casemanage.datasurvey.vo
 * @Description:费用信息中，评估公司信息Vo
 * @author: jingyh
 * @date:2017年1月18日 上午11:02:28
 * @version:v1.0
 */
public class FeeEvaluationInfoVo implements Serializable {

	private static final long serialVersionUID = -5357110577655645151L;

	/**
	 * Id
	 */
	private String id;
	
	/**
	 * 公司名称
	 */
	private String companyName;
	
	/**
	 * 公司类型
	 */
	private Integer companyType;
	
	/**
	 * 公司类型名称
	 */
	private String companyTypeName;
	
	/**
	 * 公司代码
	 */
	private String companyCode;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public Integer getCompanyType() {
		return companyType;
	}

	public void setCompanyType(Integer companyType) {
		this.companyType = companyType;
	}

	public String getCompanyTypeName() {
		return companyTypeName;
	}

	public void setCompanyTypeName(String companyTypeName) {
		this.companyTypeName = companyTypeName;
	}

	public String getCompanyCode() {
		return companyCode;
	}

	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}
}

