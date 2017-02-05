package com.zdsoft.finance.casemanage.interview.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.zdsoft.framework.core.common.domain.BaseEntity;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title:Interview.java
 * @Package:com.zdsoft.finance.casemanage.interview.entity
 * @Description:面签信息
 * @author: xiongpan
 * @date:2017年1月20日 下午5:01:34
 * @version:v1.0
 */
@Entity
@Table(name = "case_interview")
public class Interview extends BaseEntity{

	private static final long serialVersionUID = -1502693547850742457L;
	
	/**
	 * 预计强制执行公证办理时间
	 */
	@Column
	private Long predictCompelNotarizationDate;
	
	/**
	 * 是否仲裁
	 */
	@Column(length=20)
	private String isArbitrate;
	
	/**
	 * 第三方还款授权
	 */
	@Column(length=20)
	private String isThirdPartyRepayAccredit;
	
	/**
	 * 第三方还款授权人姓名
	 */
	@Column(length=32)
	private String thirdPartyRepayAccreditName;

	public Long getPredictCompelNotarizationDate() {
		return predictCompelNotarizationDate;
	}

	public void setPredictCompelNotarizationDate(Long predictCompelNotarizationDate) {
		this.predictCompelNotarizationDate = predictCompelNotarizationDate;
	}

	public String getIsArbitrate() {
		return isArbitrate;
	}

	public void setIsArbitrate(String isArbitrate) {
		this.isArbitrate = isArbitrate;
	}

	public String getIsThirdPartyRepayAccredit() {
		return isThirdPartyRepayAccredit;
	}

	public void setIsThirdPartyRepayAccredit(String isThirdPartyRepayAccredit) {
		this.isThirdPartyRepayAccredit = isThirdPartyRepayAccredit;
	}

	public String getThirdPartyRepayAccreditName() {
		return thirdPartyRepayAccreditName;
	}

	public void setThirdPartyRepayAccreditName(String thirdPartyRepayAccreditName) {
		this.thirdPartyRepayAccreditName = thirdPartyRepayAccreditName;
	}
	
	
	
}
