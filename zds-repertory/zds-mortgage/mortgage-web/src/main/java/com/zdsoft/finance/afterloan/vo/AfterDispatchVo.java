package com.zdsoft.finance.afterloan.vo;

import com.zdsoft.finance.afterloan.entity.AfterDispatch;
import com.zdsoft.finance.common.base.BaseVo;
/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: AfterDispatchVo.java 
 * @ClassName: AfterDispatch 
 * @Description: 派工人员vo
 * @author xj 
 * @date 2017年3月7日 下午12:52:27 
 * @version V1.0
 */
public class AfterDispatchVo extends BaseVo<AfterDispatch> {

	
	private static final long serialVersionUID = 6194956633644802395L;

	/**
	 * 督办人code
	 */
	private String superviserCode;
	
	/**
	 * 督办人name
	 */
	private String superviserName;
	
	/**
	 * 派工员工code
	 */
	private String dispatchCode;
	
	/**
	 * 派工人姓名
	 */
	private String dispatchName;
	
	/**
	 * 派工所属部门code
	 */
	private String dispatchDepartmentCode;
	
	/**
	 * 派工所属部门name
	 */
	private String dispatchDepartmentName;
	
	/**
	 * 反馈时间
	 */
	private Long feedbackDate;
	
	/**
	 * 反馈结果
	 */
	private String feedbackRresults;
	
	public String getSuperviserCode() {
		return superviserCode;
	}

	public void setSuperviserCode(String superviserCode) {
		this.superviserCode = superviserCode;
	}

	public String getSuperviserName() {
		return superviserName;
	}

	public void setSuperviserName(String superviserName) {
		this.superviserName = superviserName;
	}

	public String getDispatchCode() {
		return dispatchCode;
	}

	public void setDispatchCode(String dispatchCode) {
		this.dispatchCode = dispatchCode;
	}

	public String getDispatchName() {
		return dispatchName;
	}

	public void setDispatchName(String dispatchName) {
		this.dispatchName = dispatchName;
	}

	public String getDispatchDepartmentCode() {
		return dispatchDepartmentCode;
	}

	public void setDispatchDepartmentCode(String dispatchDepartmentCode) {
		this.dispatchDepartmentCode = dispatchDepartmentCode;
	}

	public String getDispatchDepartmentName() {
		return dispatchDepartmentName;
	}

	public void setDispatchDepartmentName(String dispatchDepartmentName) {
		this.dispatchDepartmentName = dispatchDepartmentName;
	}

	public Long getFeedbackDate() {
		return feedbackDate;
	}

	public void setFeedbackDate(Long feedbackDate) {
		this.feedbackDate = feedbackDate;
	}

	public String getFeedbackRresults() {
		return feedbackRresults;
	}

	public void setFeedbackRresults(String feedbackRresults) {
		this.feedbackRresults = feedbackRresults;
	}
	public AfterDispatchVo() {
		super();
	}
	public AfterDispatchVo(AfterDispatch po){
		super(po,null,null);
	}
	
	public AfterDispatch toPO(){
		AfterDispatch po = new AfterDispatch();
		return super.toPo(this, po);
	}


	
}
