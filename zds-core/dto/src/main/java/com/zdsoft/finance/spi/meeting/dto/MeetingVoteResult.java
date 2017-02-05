package com.zdsoft.finance.spi.meeting.dto;

public enum MeetingVoteResult {

	/**
	 * 同意
	 */
	VOTE_AGREE("00"),

	/**
	 * 不同意
	 */
	VOTE_NOT_AGREE("01"),

	/**
	 * 复议
	 */
	VOTE_REVIEW("02"),

	/**
	 * 弃权
	 */
	VOTE_WAIVER("03");

	public final String value;

	private MeetingVoteResult(String value) {
		this.value = value;
	}

	public static String getName(String value) {
		switch (value) {
		case "00":
			return "同意";
		case "01":
			return "不同意";
		case "02":
			return "复议";
		case "03":
			return "弃权";
		default:
			return "";
		}
	}

	public static MeetingVoteResult getValue(String value) {
		for (MeetingVoteResult e : MeetingVoteResult.values()) {
			if (e.value.equals(value)) {
				return e;
			}
		}
		return null;
	}
}
