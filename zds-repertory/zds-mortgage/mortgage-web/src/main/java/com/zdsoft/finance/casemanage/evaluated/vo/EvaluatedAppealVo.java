package com.zdsoft.finance.casemanage.evaluated.vo;

import java.math.BigDecimal;

import com.zdsoft.finance.casemanage.evaluated.entity.EvaluatedAppeal;
import com.zdsoft.finance.common.base.BaseVo;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: EvaluatedAppealVo.java 
 * @ClassName: EvaluatedAppealVo 
 * @Description: 评估价申诉VO
 * @author caixiekang 
 * @date 2017年2月20日 下午3:36:16 
 * @version V1.0
 */
public class EvaluatedAppealVo extends BaseVo<EvaluatedAppeal>{

	/**   
	 * @Fields serialVersionUID : 
	 */ 
	private static final long serialVersionUID = 1L;
	/**
	 * 保存
	 */
	public static final String SUBMITTED_NO = "0";
	/**
	 * 提交
	 */
	public static final String SUBMITTED_YES = "1";
	/**
	 * 房产id
	 */
	private String housePropertyId;
	/**
	 * 风控复议价
	 */
	private BigDecimal appealAprolAmount;
	/**
	 * 申诉人ID
	 */
	private String appealEmployeId;
	/**
	 * 申诉人姓名
	 */
	private String appealName;
	/**
	 * 申诉时间
	 */
	private Long appealDate;
	/**
	 * 有无特殊因素
	 */
	private String isSpecificFactor;
	/**
	 * 有无特殊因素Name
	 */
	private String isSpecificFactorName;
	/**
	 * 备注
	 */
	private String remark;
	/**
	 * 复议价处理人Id
	 */
	private String handleEmployeeId;
	/**
	 * 复议价处理人姓名
	 */
	private String handleEmployeeName;
	/**
	 * 复议处理时间
	 */
	private Long handleDate;
	/**
	 * 评估价申诉状态
	 */
	private String appealStatus;
	/**
	 * 保存:0
	 * 提交:1
	 */
	private String sumitted;
	
	public String getSumitted() {
		return sumitted;
	}
	public void setSumitted(String sumitted) {
		this.sumitted = sumitted;
	}
	public String getHousePropertyId() {
		return housePropertyId;
	}
	public void setHousePropertyId(String housePropertyId) {
		this.housePropertyId = housePropertyId;
	}

	
	public BigDecimal getAppealAprolAmount() {
		return appealAprolAmount;
	}
	public void setAppealAprolAmount(BigDecimal appealAprolAmount) {
		this.appealAprolAmount = appealAprolAmount;
	}
	public String getAppealEmployeId() {
		return appealEmployeId;
	}
	public void setAppealEmployeId(String appealEmployeId) {
		this.appealEmployeId = appealEmployeId;
	}
	public String getAppealName() {
		return appealName;
	}
	public void setAppealName(String appealName) {
		this.appealName = appealName;
	}
	public Long getAppealDate() {
		return appealDate;
	}
	public void setAppealDate(Long appealDate) {
		this.appealDate = appealDate;
	}
	public String getIsSpecificFactor() {
		return isSpecificFactor;
	}
	public void setIsSpecificFactor(String isSpecificFactor) {
		this.isSpecificFactor = isSpecificFactor;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getHandleEmployeeId() {
		return handleEmployeeId;
	}
	public void setHandleEmployeeId(String handleEmployeeId) {
		this.handleEmployeeId = handleEmployeeId;
	}
	public String getHandleEmployeeName() {
		return handleEmployeeName;
	}
	public void setHandleEmployeeName(String handleEmployeeName) {
		this.handleEmployeeName = handleEmployeeName;
	}
	public Long getHandleDate() {
		return handleDate;
	}
	public void setHandleDate(Long handleDate) {
		this.handleDate = handleDate;
	}
	public String getAppealStatus() {
		return appealStatus;
	}
	public void setAppealStatus(String appealStatus) {
		this.appealStatus = appealStatus;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getIsSpecificFactorName() {
		return isSpecificFactorName;
	}
	public void setIsSpecificFactorName(String isSpecificFactorName) {
		this.isSpecificFactorName = isSpecificFactorName;
	}
	public EvaluatedAppealVo(){
		super();
	}
	
	public EvaluatedAppealVo(EvaluatedAppeal evaluatedAppeal){
        super(evaluatedAppeal, null, new String[]{"isSpecificFactor"});
    }
	
	public EvaluatedAppeal toPo(){
		EvaluatedAppeal appeal = new EvaluatedAppeal();
		return super.toPo(this, appeal);
		
	}
}
