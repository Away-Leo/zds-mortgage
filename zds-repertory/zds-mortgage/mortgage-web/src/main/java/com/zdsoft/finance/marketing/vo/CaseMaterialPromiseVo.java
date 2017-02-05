package com.zdsoft.finance.marketing.vo;

import com.zdsoft.finance.common.base.BaseVo;
import com.zdsoft.finance.marketing.entity.CaseApply;
import com.zdsoft.finance.marketing.entity.CaseMaterialPromise;

/** 	
* 版权所有：重庆正大华日软件有限公司	
* @Title: CaseMaterialPromiseVo.java 	
* @Package com.zdsoft.finance.marketing.vo 	
* @Description: 后补资料vo	
* @author liuhuan 	
* @date 2017年1月17日 下午4:14:29 	
* @version V1.0 	
*/
public class CaseMaterialPromiseVo extends BaseVo<CaseMaterialPromise>{

	private static final long serialVersionUID = -7378502754154392692L;
	
	/**
	 * 关联案件
	 */
	private CaseApply caseApply;
	
	/**
	 * 后补资料类型
	 */
	private String materialType;
	
	/**
	 * 预计后补时间
	 */
	private Long predictDate;
	
	/**
	 * 操作人Id
	 */
	private String operatorId;
	
	/**
	 * 承诺时间
	 */
	private Long promiseDate;
	
	/**
	 * 备注
	 */
	private String mo;

	public String getMo() {
		return mo;
	}

	public void setMo(String mo) {
		this.mo = mo;
	}

	public CaseMaterialPromiseVo() {
		super();
	}
	
	public CaseMaterialPromiseVo(CaseMaterialPromise po) {
		super(po);
	}
	
	public CaseMaterialPromise toPo(){
		CaseMaterialPromise po = new CaseMaterialPromise();
		return super.toPo(this, po);
	}
	
	public CaseApply getCaseApply() {
		return caseApply;
	}

	public void setCaseApply(CaseApply caseApply) {
		this.caseApply = caseApply;
	}

	public String getMaterialType() {
		return materialType;
	}

	public void setMaterialType(String materialType) {
		this.materialType = materialType;
	}

	public Long getPredictDate() {
		return predictDate;
	}

	public void setPredictDate(Long predictDate) {
		this.predictDate = predictDate;
	}

	public String getOperatorId() {
		return operatorId;
	}

	public void setOperatorId(String operatorId) {
		this.operatorId = operatorId;
	}

	public Long getPromiseDate() {
		return promiseDate;
	}

	public void setPromiseDate(Long promiseDate) {
		this.promiseDate = promiseDate;
	}
	
}
