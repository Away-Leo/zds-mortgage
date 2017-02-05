package com.zdsoft.finance.casemanage.datasurvey.vo;

import java.io.Serializable;
import java.util.List;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title:FeeInfomationListVo.java
 * @Package:com.zdsoft.finance.casemanage.datasurvey.vo
 * @Description:费用集合Vo
 * @author: jingyh
 * @date:2017年1月16日 下午8:34:24
 * @version:v1.0
 */
public class FeeInfomationListVo implements Serializable {

	
	private static final long serialVersionUID = -5557813129722243819L;

	/**
	 * 案件Id
	 */
	private String caseApplyId;
	
	/**
	 * 费用信息
	 */
    private List<FeeInfomationVo> feeInfos;

	public String getCaseApplyId() {
		return caseApplyId;
	}

	public void setCaseApplyId(String caseApplyId) {
		this.caseApplyId = caseApplyId;
	}

	public List<FeeInfomationVo> getFeeInfos() {
		return feeInfos;
	}

	public void setFeeInfos(List<FeeInfomationVo> feeInfos) {
		this.feeInfos = feeInfos;
	}
}
