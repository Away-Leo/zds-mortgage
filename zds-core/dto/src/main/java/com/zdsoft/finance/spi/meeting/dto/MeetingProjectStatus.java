package com.zdsoft.finance.spi.meeting.dto;


public enum MeetingProjectStatus {
	/**
	 * 待上会
	 */
	MEET_PROJECT_STATE_WAIT(0),

	/**
	 * 已安排
	 */
	MEET_PROJECT_STATE_FIXED(1),

	/**
	 * 待投票
	 */
	MEET_PROJECT_STATE_TOVOTE(2),

	/**
	 * 投票中
	 */
	MEET_PROJECT_STATE_VOTEING(3),

	/**
	 * 待出纪要
	 */
	MEET_PROJECT_STATE_TOACCR(4),

	/**
	 * 草拟纪要
	 */
	MEET_PROJECT_STATE_DRAFTACCR(5),

	/**
	 * 纪要审批中
	 */
	MEET_PROJECT_STATE_APPRACCR(6),

	/**
	 * 纪要审批通过
	 */
	MEET_PROJECT_STATE_OUTRESULT(7),

	/**
	 * 纪要审批不通过
	 */
	MEET_PROJECT_STATE_ACCR_ABANDON(8),

	/**
	 * 拒绝上会
	 */
	MEET_PROJECT_STATE_REFUSE(9);

	public final Integer value;

	private MeetingProjectStatus(Integer value) {
		this.value = value;
	}

	/**
	 * 获取对应描述
	 * 
	 * @param value
	 *            枚举值
	 * @return 描述
	 */
	public static String getName(Integer value) {
		switch (value) {
		case 0:
			return "待上会";
		case 1:
			return "已安排";
		case 2:
			return "待投票";
		case 3:
			return "投票中";
		case 4:
			return "待出纪要";
		case 5:
			return "草拟纪要";
		case 6:
			return "纪要审批中";
		case 7:
			return "纪要审批通过";
		case 8:
			return "纪要审批不通过";
		case 9:
			return "拒绝上会";
		default:
			return "";
		}
	}
	
	/**
	 * 
	 * @param value
	 * @return
	 */
	public static MeetingProjectStatus getValue(Integer value) {
		for (MeetingProjectStatus e : MeetingProjectStatus.values()) {
			if (e.value == value) {
				return e;
			}
		}
		return null;
	}
}
