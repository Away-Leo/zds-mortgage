package com.zdsoft.finance.specialapprove.vo;

import com.zdsoft.finance.common.base.BaseVo;
import com.zdsoft.finance.specialapprove.entity.SpecialApproveManage;

/** 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: SpecialApproveManageVo.java 
 * @ClassName: SpecialApproveManageVo 
 * @Description: 特批管理VO
 * @author wangrongwei
 * @date 2017年2月15日 下午3:55:38 
 * @version V1.0 
 */ 
public class SpecialApproveManageVo extends BaseVo<SpecialApproveManage> {

	/**   
	 * @Fields serialVersionUID 
	 */ 
	private static final long serialVersionUID = 1L;

	/**   
	 * @Fields caseApplyId : 案件ID   
	 */ 
	private String caseApplyId;
	
	/**   
	 * @Fields specialApproveType : 特批类型（1、风险特批/2、自由还款特批/3、费用特批/4、减免特批/5、大特批）
	 */ 
	private Integer specialApproveType;
	
	/**   
	 * @Fields specialApproveStatus : 特批状态   （通过  / 未通过）
	 */ 
	private String specialApproveStatus;
	
	/**   
	 * @Fields remark : 备注
	 */ 
	private String remark;
	
	public SpecialApproveManageVo() {
	}
	
	public SpecialApproveManageVo(String caseApplyId, Integer specialApproveType, String specialApproveStatus,
			String remark) {
		super();
		this.caseApplyId = caseApplyId;
		this.specialApproveType = specialApproveType;
		this.specialApproveStatus = specialApproveStatus;
		this.remark = remark;
	}

	public String getCaseApplyId() {
		return caseApplyId;
	}

	public void setCaseApplyId(String caseApplyId) {
		this.caseApplyId = caseApplyId;
	}

	public Integer getSpecialApproveType() {
		return specialApproveType;
	}

	public void setSpecialApproveType(Integer specialApproveType) {
		this.specialApproveType = specialApproveType;
	}

	public String getSpecialApproveStatus() {
		return specialApproveStatus;
	}

	public void setSpecialApproveStatus(String specialApproveStatus) {
		this.specialApproveStatus = specialApproveStatus;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	public SpecialApproveManage toPo(SpecialApproveManage specialApproveManage){
		return super.toPo(this, specialApproveManage);
	}
}
