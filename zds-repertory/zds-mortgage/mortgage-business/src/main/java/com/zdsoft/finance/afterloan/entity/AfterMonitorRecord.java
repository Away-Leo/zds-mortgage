package com.zdsoft.finance.afterloan.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.zdsoft.finance.marketing.entity.CaseApply;
import com.zdsoft.framework.core.common.domain.BaseEntity;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: MiddleMonitorRecord.java 
 * @ClassName: MiddleMonitorRecord 
 * @Description: 贷中监控
 * @author xj 
 * @date 2017年2月14日 上午11:16:25 
 * @version V1.0
 */
@Entity
@Table(name = "aftloan_monitor_record")
public class AfterMonitorRecord extends BaseEntity{
	private static final long serialVersionUID = -7874705150651777028L;
	
	/**
	 * 贷中监控
	 */
	public static String MIDDLE_MONITOR_RECORD="middle";
	
	/**
	 * 贷后监控
	 */
	public static String AFTER_MONITOR_RECORD="after";
	
	/**
	 * 监控状态-保存
	 */
	public static String STATUS_SAVE="save";
	
	/**
	 * 监控状态-提交
	 */
	public static String STATUS_SUBMIT="submit";
	
	/**
	 * 监控状态-完成
	 */
	public static String STATUS_FINISH="finish";
	
	/**
	 * 接口类型(工商、汇法、房产评估)-工商
	 */
	public static String INTERFACE_INDUSTRY_COMMERCE="0";
	
	/**
	 * 接口类型(工商、汇法、房产评估)-汇法
	 */
	public static String INTERFACE_CONNECTLAND="1";
	
	/**
	 * 接口类型(工商、汇法、房产评估)-房产评估
	 */
	public static String INTERFACE_HOUSE="2";
	
	 /**
     * 案件
     */
    @ManyToOne
    @JoinColumn(name = "caseApplyId")
    private CaseApply caseApply;
    
    /**
     * 监控时间
     */
    @Column
    private Long monitorDate;
   
    /**
     * 调用结果(成功、失败)
     */
    @Column(length=10) 
    private String callResult;
    
    /**
     * 接口类型(工商、汇法、房产评估)
     */
    @Column(length=10)
    private String interfaceType;
    
    /**
     * 监控类型(贷中、贷后)
     */
    @Column(length=10)
    private String controlType;
	public CaseApply getCaseApply() {
		return caseApply;
	}
	public void setCaseApply(CaseApply caseApply) {
		this.caseApply = caseApply;
	}
	public Long getMonitorDate() {
		return monitorDate;
	}
	public void setMonitorDate(Long monitorDate) {
		this.monitorDate = monitorDate;
	}
	
	public String getCallResult() {
		return callResult;
	}
	public void setCallResult(String callResult) {
		this.callResult = callResult;
	}
	public String getInterfaceType() {
		return interfaceType;
	}
	public void setInterfaceType(String interfaceType) {
		this.interfaceType = interfaceType;
	}
	public String getControlType() {
		return controlType;
	}
	public void setControlType(String controlType) {
		this.controlType = controlType;
	}
    

}
