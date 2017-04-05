package com.zdsoft.finance.common.utils;

import java.math.BigDecimal;

import com.zdsoft.framework.core.common.util.ObjectHelper;

/**
 * 根据周期转换利率
 * 
 * @author liuwei
 *
 */
public class RateConvertUtil {

	/**
	 * 日利率
	 */
	public final static String RATE_DAY = "3001001";
	/**
	 * 月利率
	 */
	public final static String RATE_MONTH = "3001002";
	/**
	 * 年利率
	 */
	public final static String RATE_YEAR = "3001003";

	/**
	 * 转换为百分位或者千分位
	 * 
	 * @param oldValue
	 * @param cycle
	 * @return oldValue*100或者oldValue*1000
	 */
	public static Double convertToZS(Double oldValue, String cycle) {
		if (ObjectHelper.isNotEmpty(oldValue)) {
			if (ObjectHelper.isNotEmpty(cycle)
					&& (cycle.equals(RateConvertUtil.RATE_YEAR) || cycle.equals(RateConvertUtil.RATE_MONTH))) {
				return (new BigDecimal(oldValue).multiply(new BigDecimal(100)).setScale(4, BigDecimal.ROUND_HALF_DOWN))
						.doubleValue();
			} else if (ObjectHelper.isNotEmpty(cycle) && cycle.equals(RateConvertUtil.RATE_DAY)) {
				return (new BigDecimal(oldValue).multiply(new BigDecimal(1000)).setScale(4, BigDecimal.ROUND_HALF_DOWN))
						.doubleValue();
			} else {
				return oldValue;
			}
		} else {
			return null;
		}
	}

	/**
	 * 转换为百分位或者千分位
	 * 
	 * @param oldValue
	 * @param cycle
	 * @return oldValue*100或者oldValue*1000
	 */
	public static BigDecimal convertToZS(BigDecimal oldValue, String cycle) {
		if (ObjectHelper.isNotEmpty(oldValue)) {
			if (ObjectHelper.isNotEmpty(cycle)
					&& (cycle.equals(RateConvertUtil.RATE_YEAR) || cycle.equals(RateConvertUtil.RATE_MONTH))) {
				return (oldValue).multiply(new BigDecimal(100)).setScale(4, BigDecimal.ROUND_HALF_DOWN);
			} else if (ObjectHelper.isNotEmpty(cycle) && cycle.equals(RateConvertUtil.RATE_DAY)) {
				return (oldValue).multiply(new BigDecimal(1000)).setScale(4, BigDecimal.ROUND_HALF_DOWN);
			} else {
				return oldValue;
			}
		} else {
			return null;
		}
	}

	/**
	 * 
	 * @param oldValue
	 * @param cycle
	 * @return oldValue/100 或者 oldValue/1000
	 */
	public static Double convertToXS(Double oldValue, String cycle) {
		if (ObjectHelper.isNotEmpty(oldValue)) {
			if (ObjectHelper.isNotEmpty(cycle)
					&& (cycle.equals(RateConvertUtil.RATE_YEAR) || cycle.equals(RateConvertUtil.RATE_MONTH))) {
				return (new BigDecimal(oldValue).divide(new BigDecimal(100)).setScale(6, BigDecimal.ROUND_HALF_DOWN))
						.doubleValue();
			} else if (ObjectHelper.isNotEmpty(cycle) && cycle.equals(RateConvertUtil.RATE_DAY)) {
				return (new BigDecimal(oldValue).divide(new BigDecimal(1000)).setScale(6, BigDecimal.ROUND_HALF_DOWN))
						.doubleValue();
			} else {
				return oldValue;
			}
		} else {
			return null;
		}
	}

	public static BigDecimal convertToXS(BigDecimal oldValue, String cycle) {
		if (ObjectHelper.isNotEmpty(oldValue)) {
			if (ObjectHelper.isNotEmpty(cycle)
					&& (cycle.equals(RateConvertUtil.RATE_YEAR) || cycle.equals(RateConvertUtil.RATE_MONTH))) {
				return ((oldValue).divide(new BigDecimal(100)).setScale(4, BigDecimal.ROUND_HALF_DOWN));
			} else if (ObjectHelper.isNotEmpty(cycle) && cycle.equals(RateConvertUtil.RATE_DAY)) {
				return ((oldValue).divide(new BigDecimal(1000)).setScale(4, BigDecimal.ROUND_HALF_DOWN));
			} else {
				return oldValue;
			}
		} else {
			return null;
		}
	}

	/**
	 * 转换为年利率
	 * 
	 * @param oldValue
	 *            小数
	 * @param cycle
	 *            周期
	 * @return oldValue*100 或者oldValue*100*12 或者或者oldValue*100*360
	 */
	public static Double convertToYFeeRate(Double oldValue, String cycle) {
		if (ObjectHelper.isNotEmpty(oldValue)) {
			if (ObjectHelper.isNotEmpty(cycle) && cycle.equals(RateConvertUtil.RATE_YEAR)) {
				return (new BigDecimal(oldValue).multiply(new BigDecimal(100)).setScale(2, BigDecimal.ROUND_HALF_DOWN))
						.doubleValue();
			} else if (ObjectHelper.isNotEmpty(cycle) && cycle.equals(RateConvertUtil.RATE_MONTH)) {
				return (new BigDecimal(oldValue).multiply(new BigDecimal(100)).multiply(new BigDecimal(12)).setScale(2,
						BigDecimal.ROUND_HALF_DOWN)).doubleValue();
			} else if (ObjectHelper.isNotEmpty(cycle) && cycle.equals(RateConvertUtil.RATE_DAY)) {
				return (new BigDecimal(oldValue).multiply(new BigDecimal(1000)).multiply(new BigDecimal(360)).setScale(2,
						BigDecimal.ROUND_HALF_DOWN)).doubleValue();
			} else {
				return oldValue;
			}
		} else {
			return null;
		}
	}

	public static String converFeeRateCycle(String cycle) {

		if (ObjectHelper.isNotEmpty(cycle) && cycle.equals(RateConvertUtil.RATE_YEAR)) {
			return "%/年";
		} else if (ObjectHelper.isNotEmpty(cycle) && cycle.equals(RateConvertUtil.RATE_MONTH)) {
			return "%/月";
		} else if (ObjectHelper.isNotEmpty(cycle) && cycle.equals(RateConvertUtil.RATE_DAY)) {
			return "‰/日";
		}
		return "";
	}

}
