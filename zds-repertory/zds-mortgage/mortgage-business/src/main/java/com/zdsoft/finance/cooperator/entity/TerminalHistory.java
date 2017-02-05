package com.zdsoft.finance.cooperator.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.zdsoft.framework.core.common.domain.BaseEntity;

/**
 * 合作方维护表
 * @author Hisa
 *
 */
@Entity
@Table(name="cpt_terminal_history")
public class TerminalHistory extends BaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 计划时段
	 */
	@Column(length=64)
	private String planTimeFrame;
	/**
	 * 维护人
	 */
	@Column(length=64)
	private String maintainer;
	/**
	 * 审批人
	 */
	@Column(length=64)
	private String approver;
	/**
	 * 计划维护方式
	 */
	@Column(length=64)
	private String planApproveType;
	/**
	 * 计划终端状态
	 */
	private String planterminalState;
	/**
	 * 计划具体维护事项
	 */
	@Column(length=64)
	private String planMaintainThing;
	/**
	 * 完成是否
	 */
	private Boolean isDone;
	/**
	 * 具体落实执行情况
	 */
	private int howThingCondition;
	
	/**
	 * 关联的终端
	 */
	 @ManyToOne
	 @JoinColumn(name="terminalId")
	 private CooperatorTerminal cooperatorTerminal;
	 
	 
	public CooperatorTerminal getCooperatorTerminal() {
		return cooperatorTerminal;
	}
	public void setCooperatorTerminal(CooperatorTerminal cooperatorTerminal) {
		this.cooperatorTerminal = cooperatorTerminal;
	}
	
	public String getPlanTimeFrame() {
		return planTimeFrame;
	}
	public void setPlanTimeFrame(String planTimeFrame) {
		this.planTimeFrame = planTimeFrame;
	}
	public String getMaintainer() {
		return maintainer;
	}
	public void setMaintainer(String maintainer) {
		this.maintainer = maintainer;
	}
	public String getApprover() {
		return approver;
	}
	public void setApprover(String approver) {
		this.approver = approver;
	}
	public String getPlanApproveType() {
		return planApproveType;
	}
	public void setPlanApproveType(String planApproveType) {
		this.planApproveType = planApproveType;
	}
	public String getPlanterminalState() {
		return planterminalState;
	}
	public void setPlanterminalState(String planterminalState) {
		this.planterminalState = planterminalState;
	}
	public String getPlanMaintainThing() {
		return planMaintainThing;
	}
	public void setPlanMaintainThing(String planMaintainThing) {
		this.planMaintainThing = planMaintainThing;
	}
	public Boolean getIsDone() {
		return isDone;
	}
	public void setIsDone(Boolean isDone) {
		this.isDone = isDone;
	}
	public int getHowThingCondition() {
		return howThingCondition;
	}
	public void setHowThingCondition(int howThingCondition) {
		this.howThingCondition = howThingCondition;
	}
	
	
}
