package com.zdsoft.finance.common.utils;

/**
 * 期限转换
 * @author zhouz
 *
 */
public class DeallineConversionUtil {
		 
	/**
	 * 期限转换
	 * @param deallineY 期限年
	 * @param deallineM 期限月
	 * @param deallineD 期限日
	 * @return dealline 期限年+期限月+期限日
	 */
	public static String deadlineConversion(Integer deallineY,Integer deallineM,Integer deallineD)
	{
		String dealline="";
		deallineY=deallineY!=null?deallineY:0;
		deallineM=deallineM!=null?deallineM:0;
		deallineD=deallineD!=null?deallineD:0;
		dealline=deallineY+"年"+deallineM+"月"+	deallineD+"日";
		return dealline;
	}


    
}
