package com.zdsoft.finance.spi.meeting.dto;

public enum MeetingStatus {

	/**
	 * 草稿状态
	 */
	MEET_DRAFT_STATE("00"),

	/**
	 * 待投票
	 */
	MEET_STATE_PEND("01"),

	/**
	 * 投票中
	 */
	MEET_STATE_DOING("02"),

	/**
	 * 会议结束
	 */
	MEET_STATE_FINAL_END("03");

	public final String value;

	private MeetingStatus(String value) {
		this.value = value;
	}

	public static String getName(String value) {
		switch (value) {
		case "00":
			return "草稿状态";
		case "01":
			return "待投票";
		case "02":
			return "投票中";
		case "03":
			return "会议结束";
		default:
			return "";
		}
	}

	public static MeetingStatus getValue(String value) {
		for (MeetingStatus e : MeetingStatus.values()) {
			if (e.value.equals(value)) {
				return e;
			}
		}
		return null;
	}
}
