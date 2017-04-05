package com.zdsoft.finance.casemanage.receivablePlan;

import java.math.BigDecimal;
import java.util.List;

/**
 * 版权所有：重庆正大华日软件有限公司
 * 
 * @Title: IrrUtil.java
 * @ClassName: IrrUtil
 * @Description: IRR内部收益率计算工具类
 * @author jincheng
 * @date 2017年2月13日 下午9:30:09
 * @version V1.0
 */
public class IrrUtil {

	/**
	 * @param cashFlow
	 *            资金流
	 * @return IRR内部收益率
	 */
	public static BigDecimal getIrr(List<Double> cashFlow) {
		
		/** 最小差异 */
		final double MINDIF = 0.000000000001;
		
		/** 迭代次数 */
		int LOOPNUM = 1000;
		
		double flowOut = cashFlow.get(0);
		double minValue = 0d;
		double maxValue = 1d;
		double testValue = 0d;
		while (LOOPNUM > 0) {
			testValue = (minValue + maxValue) / 2;
			double npv = NPV(cashFlow, testValue);
			if (Math.abs(flowOut + npv) < MINDIF) {
				break;
			} else if (Math.abs(flowOut) > npv) {
				maxValue = testValue;
			} else {
				minValue = testValue;
			}
			LOOPNUM--;
		}
		return new   BigDecimal(testValue).setScale(12,   BigDecimal.ROUND_HALF_UP); 
	}

	public static double NPV(List<Double> flowInArr, double rate) {
		double npv = 0;
		for (int i = 1; i < flowInArr.size(); i++) {
			npv += flowInArr.get(i) / Math.pow(1 + rate, i);
		}
		return npv;
	}
}