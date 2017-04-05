package com.zdsoft.finance.cooperator.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.zdsoft.framework.core.common.domain.BaseEntity;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * 
 * @Title: TerminalHistory.java
 * @ClassName: TerminalHistory
 * @Description: 终端维护记录(刘伟二次维护)
 * @author liuwei
 * @date 2017年2月25日 下午2:22:41
 * @version V1.0
 */
@Entity
@Table(name = "cpt_terminal_history")
public class TerminalHistory extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 维护人
	 */
	@Column(length = 32)
	private String maintainerCd;

	/**
	 * 维护人姓名
	 */
	@Column(length = 64)
	private String maintainerName;

	/**
	 * 审批人
	 */
	@Column(length = 32)
	private String approverCd;

	/**
	 * 计划维护方式
	 */
	@Column(length = 20)
	private String planApproveType;

	/**
	 * 计划终端状态
	 */
	@Column(length = 20)
	private String planterminalState;

	/**
	 * 计划具体维护事项
	 */
	@Column(length = 500)
	private String planMaintainThing;

	/**
	 * 完成是否
	 */
	@Column(length = 32)
	private String isDone;

	/**
	 * 具体落实执行情况
	 */
	@Column(length = 500)
	private String howThingCondition;

	/**
	 * 关联的终端
	 */
	@ManyToOne
	@JoinColumn(name = "terminalId")
	private CooperatorTerminal cooperatorTerminal;

	public String getMaintainerCd() {
		return maintainerCd;
	}

	public void setMaintainerCd(String maintainerCd) {
		this.maintainerCd = maintainerCd;
	}

	public String getApproverCd() {
		return approverCd;
	}

	public void setApproverCd(String approverCd) {
		this.approverCd = approverCd;
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

	public String getHowThingCondition() {
		return howThingCondition;
	}

	public void setHowThingCondition(String howThingCondition) {
		this.howThingCondition = howThingCondition;
	}

	public CooperatorTerminal getCooperatorTerminal() {
		return cooperatorTerminal;
	}

	public void setCooperatorTerminal(CooperatorTerminal cooperatorTerminal) {
		this.cooperatorTerminal = cooperatorTerminal;
	}

	public String getIsDone() {
		return isDone;
	}

	public void setIsDone(String isDone) {
		this.isDone = isDone;
	}

	public String getMaintainerName() {
		return maintainerName;
	}

	public void setMaintainerName(String maintainerName) {
		this.maintainerName = maintainerName;
	}

}
