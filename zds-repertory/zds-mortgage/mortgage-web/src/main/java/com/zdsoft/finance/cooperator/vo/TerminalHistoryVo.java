package com.zdsoft.finance.cooperator.vo;

import com.zdsoft.finance.common.base.BaseVo;
import com.zdsoft.finance.cooperator.entity.TerminalHistory;

/**
 * 合作方维护表
 * 
 * @author Hisa
 *
 */
public class TerminalHistoryVo extends BaseVo<TerminalHistory> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 计划时段
	 */
	private String planTimeFrame;
	/**
	 * 维护人
	 */
	private String maintainer;
	/**
	 * 审批人
	 */
	private String approver;
	/**
	 * 计划维护方式
	 */
	private String planApproveType;
	/**
	 * 计划终端状态
	 */
	private String planterminalState;
	/**
	 * 计划具体维护事项
	 */
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
	private String terminalId;
	
	

	public String getIsDoneName() {
		if(isDone != null  && isDone){
			return "完成";
		}else{
			return "未完成";
		}
		
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

	public String getTerminalId() {
		return terminalId;
	}

	public void setTerminalId(String terminalId) {
		this.terminalId = terminalId;
	}

	public TerminalHistoryVo() {
		super();
	}

	public TerminalHistoryVo(TerminalHistory terminalHistory) {
		super(terminalHistory);
	}

	public TerminalHistoryVo(TerminalHistory terminalHistory, String[] args, String[] simpleArgs) {
		super(terminalHistory, args, simpleArgs);
	}

	public TerminalHistory toPO() {
		TerminalHistory po = new TerminalHistory();
		return super.toPo(this, po);
	}
}
