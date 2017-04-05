package com.zdsoft.finance.casemanage.receivablePlan.vo;

import java.util.List;

import com.zdsoft.finance.finance.vo.RepaymentAmountAllotVo;

/** 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: ReceivablePlanPackageVo.java 
 * @ClassName: ReceivablePlanPackageVo 
 * @Description: 还款计划封装Vo
 * @author zhoushichao 
 * @date 2017年2月27日 上午11:25:48 
 * @version V1.0 
 */ 
public class ReceivablePlanPackageVo {

	/**
	 * 还款计划Vo
	 */
	private RepaymentAmountAllotVo repaymentAmountAllotVo;
	/**
	 * 实收明细Vo
	 */
	private List<RepaymentAmountAllotVo> repaymentAmountAllotVoList;
	public RepaymentAmountAllotVo getRepaymentAmountAllotVo() {
		return repaymentAmountAllotVo;
	}
	public void setRepaymentAmountAllotVo(RepaymentAmountAllotVo repaymentAmountAllotVo) {
		this.repaymentAmountAllotVo = repaymentAmountAllotVo;
	}
	public List<RepaymentAmountAllotVo> getRepaymentAmountAllotVoList() {
		return repaymentAmountAllotVoList;
	}
	public void setRepaymentAmountAllotVoList(List<RepaymentAmountAllotVo> repaymentAmountAllotVoList) {
		this.repaymentAmountAllotVoList = repaymentAmountAllotVoList;
	}
	
}
