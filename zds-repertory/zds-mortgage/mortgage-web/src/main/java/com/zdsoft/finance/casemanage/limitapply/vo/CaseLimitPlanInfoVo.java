package com.zdsoft.finance.casemanage.limitapply.vo;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title:CaseLimitPlanInfoVo.java
 * @Package:com.zdsoft.finance.casemanage.limitapply.vo
 * @Description:额度申请需保存数据的Vo封装
 * @author: xiongpan
 * @date:2017年1月17日 上午11:09:04
 * @version:v1.0
 */
public class CaseLimitPlanInfoVo {
	
	private CaseLimitApplyVo caseLimitApplyVo;
	
	private FundPlanInfoVo fundPlanInfoVo;

	public CaseLimitApplyVo getCaseLimitApplyVo() {
		return caseLimitApplyVo;
	}

	public void setCaseLimitApplyVo(CaseLimitApplyVo caseLimitApplyVo) {
		this.caseLimitApplyVo = caseLimitApplyVo;
	}

	public FundPlanInfoVo getFundPlanInfoVo() {
		return fundPlanInfoVo;
	}

	public void setFundPlanInfoVo(FundPlanInfoVo fundPlanInfoVo) {
		this.fundPlanInfoVo = fundPlanInfoVo;
	}
	
	

}
