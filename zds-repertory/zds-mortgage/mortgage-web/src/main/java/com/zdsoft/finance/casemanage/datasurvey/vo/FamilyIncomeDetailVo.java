package com.zdsoft.finance.casemanage.datasurvey.vo;

import java.math.BigDecimal;

import com.zdsoft.finance.casemanage.datasurvey.entity.FamilyIncomeDetail;
import com.zdsoft.finance.common.base.BaseVo;

/** 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: FamilyIncomeDetailVo.java 
 * @ClassName: FamilyIncomeDetailVo 
 * @Description: 家庭收入详情
 * @author liuhuan 
 * @date 2017年2月20日 下午8:17:15 
 * @version V1.0 
 */ 
public class FamilyIncomeDetailVo extends BaseVo<FamilyIncomeDetail>{

	private static final long serialVersionUID = -5445277220449628274L;

	/**
	 * 家庭收入id
	 */
	private String familyIncomeId;
	
	/**
	 * 月份
	 */
	private Long realMonth;
	
	/**   
	 * 转化格式的月份   
	 */ 
	private String realMonthStr;
	
	/**
	 * 贷方发生金额
	 */
	private BigDecimal realAmount;
	
	public FamilyIncomeDetailVo() {
		super();
	}
	public FamilyIncomeDetailVo(FamilyIncomeDetail po){
		super(po,null,null,new String[]{"realMonth|yyyy年MM月"});
	}
	
	public FamilyIncomeDetail toPo(){
		FamilyIncomeDetail po = new FamilyIncomeDetail();
		return super.toPo(this, po);
	}
	
	public String getFamilyIncomeId() {
		return familyIncomeId;
	}

	public void setFamilyIncomeId(String familyIncomeId) {
		this.familyIncomeId = familyIncomeId;
	}

	public String getRealMonthStr() {
		return realMonthStr;
	}

	public void setRealMonthStr(String realMonthStr) {
		this.realMonthStr = realMonthStr;
	}

	public Long getRealMonth() {
		return realMonth;
	}

	public void setRealMonth(Long realMonth) {
		this.realMonth = realMonth;
	}

	public BigDecimal getRealAmount() {
		return realAmount;
	}

	public void setRealAmount(BigDecimal realAmount) {
		this.realAmount = realAmount;
	}
	
}
