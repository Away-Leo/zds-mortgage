package com.zdsoft.finance.cooperator.vo;

import com.zdsoft.finance.common.base.BaseVo;
import com.zdsoft.finance.cooperator.entity.TerminalHistory;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * 
 * @Title: TerminalHistoryVo.java
 * @ClassName: TerminalHistoryVo
 * @Description: 合作方维护Vo
 * @author liuwei
 * @date 2017年3月9日 上午11:28:03
 * @version V1.0
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
	 * 维护人Code
	 */
	private String maintainerCd;
	/**
	 * 维护人姓名
	 */
	private String maintainerName;
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

	/**
	 * 创建时间long
	 */
	private String createDateLong;

	public String getIsDoneName() {
		if (isDone != null && isDone) {
			return "完成";
		} else {
			return "未完成";
		}

	}

	public String getPlanTimeFrame() {
		return planTimeFrame;
	}

	public void setPlanTimeFrame(String planTimeFrame) {
		this.planTimeFrame = planTimeFrame;
	}

	public String getMaintainerCd() {
		return maintainerCd;
	}

	public void setMaintainerCd(String maintainerCd) {
		this.maintainerCd = maintainerCd;
	}

	public String getMaintainerName() {
		return maintainerName;
	}

	public void setMaintainerName(String maintainerName) {
		this.maintainerName = maintainerName;
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

	public String getCreateDateLong() {
		return createDateLong;
	}

	public void setCreateDateLong(String createDateLong) {
		this.createDateLong = createDateLong;
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
