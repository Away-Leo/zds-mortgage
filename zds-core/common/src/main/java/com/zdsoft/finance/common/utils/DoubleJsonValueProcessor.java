package com.zdsoft.finance.common.utils;


import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 *JsonConfig数据类型处理
 * @author pan
 *
 */
public class DoubleJsonValueProcessor implements JsonValueProcessor {

	/**
	 * 处理数组类型
	 */
	public Object processArrayValue(Object value, JsonConfig jsonConfig) {
		
		if (value instanceof String[]) {

			Double [] obj = {};

			String [] nums = (String[]) value;

			for (int i = 0; i < nums.length; i++) {  //仅处理Double型
				if (isIntNumeric(value.toString())) {//优先判断整型
					return value;
				}else if (isDouNumeric(nums[i])) {
					obj[i] = roundHalfUp(new Double(nums[i]), 3);
				}else{
					return value;
				}
			}

			return obj;
		}

		return value;
	}

	/**
	 * 处理单个对象
	 */
	public Object processObjectValue(String key, Object value,
			JsonConfig jsonConfig) {
		
		if (isIntNumeric(value.toString())) {//优先判断整型
			return value.toString().replace("\"", "");
		}

		if (isDouNumeric(value.toString())) {//判断浮点型
			return roundHalfUp(new Double(value.toString()), 3);
		}

		return value;
	}
	
	
	/**
	 * 四舍五入。
	 * 
	 * @param number
	 *            数值
	 * @return 舍入后的数值
	 * @see java.text.RoundingMode.HALF_UP
	 */
	public Double roundHalfUp(double number, int frac) {  
       return BigDecimalCalculate.round(number, frac);
    }  
	
	
	/**
	 * 判断字符是否是整型
	 * @param str
	 * @return
	 */
	public boolean isIntNumeric(String str) {
		Pattern pattern = Pattern.compile("[0-9]*");
		Matcher isNum = pattern.matcher(str);
		if (!isNum.matches()) {
			return false;
		}
		return true;
	}
	
	/**
	 * 判断字符是否是浮点型
	 * @param str
	 * @return
	 */
	public boolean isDouNumeric(String str) {
		Pattern pattern = Pattern.compile("[0-9]*.[0-9]*");
		Matcher isNum = pattern.matcher(str);
		if (!isNum.matches()) {
			return false;
		}
		return true;
	}
}
