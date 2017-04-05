package com.zdsoft.finance.specialapprove.vo;

import java.math.BigDecimal;


/** 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: FeeItemVo.java 
 * @ClassName: FeeItemVo 
 * @Description: 费用特批Vo
 * @author wangrongwei
 * @date 2017年2月23日 上午9:42:36 
 * @version V1.0 
 */ 
public class FeeSpecialApproveItemVo {
	
	/**   
	 * 序号 
	 */ 
	private String index;
	
	/**
	 * 预计应收
	 */
	private BigDecimal expectedAmount;
	
	/**   
	 * 标准应收 
	 */ 
	private BigDecimal standardAmount;
	
	/**
	 * 费用类型名称
	 */
	private String feeTypeName;
	
	public FeeSpecialApproveItemVo() {
	}

	public FeeSpecialApproveItemVo(String index, BigDecimal expectedAmount, BigDecimal standardAmount, String feeTypeName) {
		super();
		this.index = index;
		this.expectedAmount = expectedAmount;
		this.standardAmount = standardAmount;
		this.feeTypeName = feeTypeName;
	}

	public String getIndex() {
		return index;
	}

	public void setIndex(String index) {
		this.index = index;
	}

	public BigDecimal getExpectedAmount() {
		return expectedAmount;
	}

	public void setExpectedAmount(BigDecimal expectedAmount) {
		this.expectedAmount = expectedAmount;
	}

	public BigDecimal getStandardAmount() {
		return standardAmount;
	}

	public void setStandardAmount(BigDecimal standardAmount) {
		this.standardAmount = standardAmount;
	}

	public String getFeeTypeName() {
		return feeTypeName;
	}

	public void setFeeTypeName(String feeTypeName) {
		this.feeTypeName = feeTypeName;
	}
}
