package com.zdsoft.finance.common.utils;

import java.util.List;

public class ZUIComboboxUtil {

	/**
	 * 组装combobox所需的json
	 * 
	 * @param data
	 *            为list, key,value ,以逗号拼接起来。
	 * @return json数组
	 */
	public static String assComboJson(List<String> data) {
		StringBuffer returnJson = new StringBuffer("[");
		for (int i = 0; i < data.size(); i++) {
			if (i != data.size() - 1) {
				returnJson.append(
						"{'id':'" + data.get(i).split(",")[0] + "','text':'" + data.get(i).split(",")[1] + "'},");
			} else {
				returnJson.append(
						"{'id':'" + data.get(i).split(",")[0] + "','text':'" + data.get(i).split(",")[1] + "'}]");
			}
		}
		return returnJson.toString();
	}
}
