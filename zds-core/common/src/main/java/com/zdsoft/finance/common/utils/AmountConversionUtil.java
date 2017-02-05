package com.zdsoft.finance.common.utils;

import com.zdsoft.framework.core.common.util.ObjectHelper;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * Created by liuwei on 2015/9/2.
 * 金额转换Util
 */
public class AmountConversionUtil {

    /**
     * 万元转元
     *
     * @param oldValue
     * @return
     */
    public static BigDecimal convertToYuan(BigDecimal oldValue) {
        if (ObjectHelper.isNotEmpty(oldValue)) {
            return oldValue.multiply(new BigDecimal(10000));
        } else {
            return null;
        }
    }

    public static String convertToYuan(String oldValue) {
        if (ObjectHelper.isNotEmpty(oldValue)) {
            return (new BigDecimal(oldValue).multiply(new BigDecimal(10000))).doubleValue() + "";
        } else {
            return null;
        }
    }

    public static Double convertToYuan(Double oldValue) {
        if (ObjectHelper.isNotEmpty(oldValue)) {
            return (new BigDecimal(oldValue).multiply(new BigDecimal(10000))).doubleValue();
        } else {
            return null;
        }
    }


    /**
     * 元转万元
     *
     * @param oldValue
     * @return
     */
    public static BigDecimal convertToWYuan(BigDecimal oldValue) {
        if (ObjectHelper.isNotEmpty(oldValue)) {
            return oldValue.divide(new BigDecimal(10000), 6, BigDecimal.ROUND_HALF_UP);
        } else {
            return null;
        }
    }
    
    /**
     * 根据传递的i值确定小数点后保留几位
     * @param oldValue 需要转换的金额
     * @param i 小数点后需要保留的位数
     * @return
     */
    public static BigDecimal convertToWYuan(BigDecimal oldValue, int i) {
        if (ObjectHelper.isNotEmpty(oldValue)) {
            return oldValue.divide(new BigDecimal(10000), i, BigDecimal.ROUND_HALF_UP);
        } else {
            return null;
        }
    }

    public static String convertToWYuan(String oldValue) {
        if (ObjectHelper.isNotEmpty(oldValue)) {
            return (new BigDecimal(oldValue).divide(new BigDecimal(10000), 6, BigDecimal.ROUND_HALF_UP)).doubleValue() + "";
        } else {
            return null;
        }
    }

    public static Double convertToWYuan(Double oldValue) {
        if (ObjectHelper.isNotEmpty(oldValue)) {
            return (new BigDecimal(oldValue).divide(new BigDecimal(10000), 6, BigDecimal.ROUND_HALF_UP)).doubleValue();
        } else {
            return null;
        }
    }
    
    /**
     * 格式化金额，千位分隔符
     * @param amount
     * @param formatStr
     * @return
     */
    public static String convertFormatAmount(BigDecimal amount,String formatStr)
    { 
    	String amountStr="";
    	if(ObjectHelper.isNotEmpty(amount)){
	        DecimalFormat nf = new DecimalFormat(formatStr);  
	        amountStr = nf.format(amount);
    	}
        return amountStr;
    }
    
    /**
     * 格式化金额，千位分隔符
     * @param amount 格式默认为 "#,###.00"
     * @return
     */
    public static String convertFormatAmount(BigDecimal amount)
    {
    	String amountStr="";
        DecimalFormat nf = new DecimalFormat("#,###.00"); 
        if(ObjectHelper.isNotEmpty(amount)){
        	amountStr= nf.format(amount);
        }
        return amountStr;
    }
    
    /**
     * 格式化金额，千位分隔符
     * @param amount
     * @param formatStr
     * @return
     */
    public static String convertFormatAmount(Double amount,String formatStr)
    { 
    	String amountStr="";
    	if(ObjectHelper.isNotEmpty(amount)){
	        DecimalFormat nf = new DecimalFormat(formatStr);  
	        amountStr = nf.format(amount);
    	}
        return amountStr;
    }
    
    /**
     * 格式化金额，千位分隔符
     * @param amount 格式默认为 "#,###.00"
     * @return
     */
    public static String convertFormatAmount(Double amount)
    {
    	String amountStr="";
        DecimalFormat nf = new DecimalFormat("#,###.00"); 
        if(ObjectHelper.isNotEmpty(amount)){
        	amountStr= nf.format(amount);
        }
        return amountStr;
    }
    
    
    public static void main(String[] args) {
		
//		String amountStr1=convertFormatAmount(new BigDecimal(123456789.2389));
//		String amountStr2=convertFormatAmount(123456789.2398);
//		System.out.print("amountStr1:"+amountStr1);
//		System.out.print("amountStr2:"+amountStr2);
		
	}
    
   
}
