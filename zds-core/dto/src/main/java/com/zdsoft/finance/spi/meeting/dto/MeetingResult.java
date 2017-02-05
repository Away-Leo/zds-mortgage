package com.zdsoft.finance.spi.meeting.dto;

public enum MeetingResult {
	/**
	 * 通过
	 */
	REVIEW_THROUGH(0),

	/**
	 * 不通过
	 */
	REVIEW_NOT_THROUGH(-1),

	/**
	 * 复议
	 */
	REVIEW_AGAIN(1),

	/**
	 * 有条件同意
	 */
	REVIEW_CONDTITION(2);

	public final Integer value;

	private MeetingResult(Integer value) {
		this.value = value;
	}

	public static String getName(Integer value) {
		switch (value) {
		case 0:
			return "通过";
		case -1:
			return "不通过";
		case 1:
			return "复议";
		case 2:
			return "有条件同意";
		default:
			return "";
		}
	}

	public static MeetingResult getValue(Integer value) {
		for (MeetingResult e : MeetingResult.values()) {
			if (e.value == value) {
				return e;
			}
		}
		return null;
	}
}
