package com.zdsoft.finance.common.utils;

import java.text.DecimalFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *  将2012-11-02格式的日期转换为“二〇一二年十一月二日”这种
 * @author user
 *
 */
public class DateToUpperChinese {
	private static final String[] NUMBERS = { "〇", "一", "二", "三", "四", "五",
			"六", "七", "八", "九" };

	public static synchronized String toChinese(String str) {
		StringBuffer sb = new StringBuffer();
		sb.append(getSplitDateStr(str, 0)).append("年")
				.append(getSplitDateStr(str, 1)).append("月")
				.append(getSplitDateStr(str, 2)).append("日");
		return sb.toString();
	}

	public static String getSplitDateStr(String str, int unit) {
		// unit是单位 0=年 1=月 2日
		String[] DateStr = str.split("-");
		if (unit > DateStr.length)
			unit = 0;
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < DateStr[unit].length(); i++) {

			if ((unit == 1 || unit == 2) && Integer.valueOf(DateStr[unit]) > 9) {
				sb.append(convertNum(DateStr[unit].substring(0, 1)))
						.append("十")
						.append(convertNum(DateStr[unit].substring(1, 2)));
				break;
			} else {
				sb.append(convertNum(DateStr[unit].substring(i, i + 1)));
			}
		}
		if (unit == 1 || unit == 2) {
			return sb.toString().replaceAll("^一", "").replace("〇", "");
		}
		return sb.toString();

	}

	private static String convertNum(String str) {
		return NUMBERS[Integer.valueOf(str)];
	}

	public static boolean isNumeric(String str) {
		Pattern pattern = Pattern.compile("[0-9]*");
		Matcher isNum = pattern.matcher(str);
		if (!isNum.matches()) {
			return false;
		}
		return true;
	}
	public static String noE(double d){
		DecimalFormat a = new DecimalFormat("#,##0.00");
		return a.format(d);
	}
	
	public static String toRMB(double money) {
		char[] s1 = { '零', '壹', '贰', '叁', '肆', '伍', '陆', '柒', '捌', '玖' };
		char[] s4 = { '分', '角', '元', '拾', '佰', '仟', '万', '拾', '佰', '仟', '亿',
				'拾', '佰', '仟', '万' };
		String str = String.valueOf(Math.round(money * 100 + 0.00001));
		String result = "";
		for (int i = 0; i < str.length(); i++) {
			int n = str.charAt(str.length() - 1 - i) - '0';
			result = s1[n] + "" + s4[i] + result;
		}
		result = result.replaceAll("零仟", "零");
		result = result.replaceAll("零佰", "零");
		result = result.replaceAll("零拾", "零");
		result = result.replaceAll("零亿", "亿");
		result = result.replaceAll("零万", "万");
		result = result.replaceAll("零元", "元");
		result = result.replaceAll("零角", "零");
		result = result.replaceAll("零分", "零");
		result = result.replaceAll("零零", "零");
		result = result.replaceAll("零亿", "亿");
		result = result.replaceAll("零零", "零");
		result = result.replaceAll("零万", "万");
		result = result.replaceAll("零零", "零");
		result = result.replaceAll("零元", "元");
		result = result.replaceAll("亿万", "亿");
		result = result.replaceAll("零$", "");
		result = result.replaceAll("元$", "");
		result = result.replaceAll("角$", "");
		return result;
	}
	public static void main(String[] args) {
		System.out.println(DateToUpperChinese.toChinese("2014-05-06"));
	}
}
