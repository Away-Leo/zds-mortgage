package com.zdsoft.finance.common.utils;



import com.zdsoft.framework.core.common.util.DateHelper;
import com.zdsoft.framework.core.common.util.ObjectHelper;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 时间工具类
 * @author pan
 *
 */
public class DateUtil {
	
	/**
	 * 返回endDate和startDate两个日期中间的天数值
	 * 
	 * @param startDate,格式：yyyy-MM-dd
	 * @param endDate,格式：yyyy-MM-dd
	 * @return
	 */
	public static long getDifferenceDay(Date startDate,Date endDate){
		long beginTime = startDate.getTime();
		long endTime = endDate.getTime();
		long iDay = (long) ((endTime - beginTime) / (1000 * 60 * 60 * 24));
		return iDay;
	}
	
	/**
	 * 得到当前14位时间
	 * @return
	 */
	public static Long getCurrentDate(){
		DateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
		return new Long(format.format(new Date()));
	}
	
	/**
	 * 将8位带线字符串格式转换为8位Long格式
	 * @param date
	 * 				"yyyy-MM-dd"
	 * @return
	 */
	public static Long formatDateLong(String date){
		if(ObjectHelper.isEmpty(date)){
			return 0L;
		}
		Date d1 = DateHelper.stringToDate(date, "yyyy-MM-dd");
		DateFormat format = new SimpleDateFormat("yyyyMMdd");
		return new Long(format.format(d1));
	}
	
	/**
	 * 给一个日期加上几个月或几天
	 * 
	 * @param date
	 *            传入的字符格式日期
	 * @param no
	 *            加上的数量
	 * @param type
	 *            类型
	 * @return
	 */
	public static String addDate(String date, EnumTimeUnit type, int no) {
		date = date.length() == 14 ? date.substring(0, 8) : date;
		DateFormat df = new SimpleDateFormat("yyyyMMdd");
		Calendar c1 = Calendar.getInstance();
		try {
			c1.setTime(df.parse(date));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		if (type.equals(EnumTimeUnit.Month))
			c1.add(Calendar.MONTH, no);
		else if (type.equals(EnumTimeUnit.Day))
			c1.add(Calendar.DATE, no);
		SimpleDateFormat simple = new SimpleDateFormat("yyyyMMdd");
		return simple.format(c1.getTime());
	}
	
	/**
	 * 传入yyyymmdd格式的Long 转为yyyy-mm-dd格式的string
	 * @param date
	 * @return
	 */
	public static String formatDateToStr(Long date)
	{
		String dateStr="";
		SimpleDateFormat f = new SimpleDateFormat("yyyyMMdd");
	    try{
	    	if(ObjectHelper.isNotEmpty(date)){
		        Date startDate = f.parse(date.toString());
		        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");  
		        dateStr=sdf.format(startDate);  
	    	}
	      }catch(Exception e)
	      {
	        	
	      }
		
		return dateStr;
	}
	
    /**
     * 累计月
     * @param deadLineY
     * @param deadLineM
     * @param deadLineD
     * @return
     */
    public static Integer sumLineM(Integer deadLineY,Integer deadLineM,Integer deadLineD)
    {
    	
    	if(ObjectHelper.isEmpty(deadLineY))
    	{
    		deadLineY=0;
    	}
    	if(ObjectHelper.isEmpty(deadLineM))
    	{
    		deadLineM=0;
    	}
    	if(ObjectHelper.isEmpty(deadLineD))
    	{
    		deadLineD=0;
    	}
    	
    	Integer sumM= deadLineY*12+deadLineM+deadLineD /30;
    	return sumM;
    }
    
    /**
     * 根据开始时间 期限 是否算尾 计算结束日期
     * @param startDate
     * @param deadlineY
     * @param deadlineM
     * @param deadlineD
     * @param isIncludeProjTail
     * @return map  endDate:全是数字(如:20160101)   endDateStr:yyyy-MM-dd格式(如:2016-01-01)
     */
    public static Map<String,Object > calEndDate( String startDate,int deadlineY,int deadlineM,int deadlineD , String isIncludeProjTail)
    {
    Map<String,Object > optional=new HashMap<String, Object>();
   	 SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd"); 
	 SimpleDateFormat formatterymd = new SimpleDateFormat("yyyy-MM-dd"); 
	 try { 
	     Date myDate = formatter.parse(startDate); 
	     Calendar c1 = Calendar.getInstance(); 
	     c1.setTime(myDate); 
	     c1.add(Calendar.YEAR, deadlineY);
	     c1.add(Calendar.MONTH, deadlineM);
	     c1.add(Calendar.DATE, deadlineD);
	     myDate=c1.getTime();
	     
	     String newDate_1=formatter.format(myDate);
	     
	     Date endDate = formatter.parse(newDate_1); 
	     //默认为空 保持现状
	     if(ObjectHelper.isEmpty(isIncludeProjTail) || isIncludeProjTail.equals("1")){ 
    	     Calendar c = Calendar.getInstance(); 
    	     c.setTime(endDate); 
    	     c.add(Calendar.DATE, -1);
    	     endDate = c.getTime(); 
	     }
	     
	     String endDateL=formatter.format(endDate); 
	     String endDateStr=formatterymd.format(endDate); 
	     optional.put("endDate", endDateL);//全是数字
	     optional.put("endDateStr", endDateStr);//yyyy-MM-dd
	 } catch (ParseException e1) { 
	     e1.printStackTrace(); 
	 } 
    	return optional;
    	
    }
    
    /**
	 * 获取某个月最后一天
	 * @param timex
	 * @return
	 */
	public static Date lastDayOfMonth(Integer timex) {
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
		Date date=new Date();
		try {
			date = df.parse(String.valueOf(timex));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		cal.roll(Calendar.DAY_OF_MONTH, -1);
		return cal.getTime();
		}
	/**
	 * 将时间类型转换为int型
	 * 
	 * @param date
	 * @return
	 */
	public static int getDateToInt(Date date) {
		if(ObjectHelper.isEmpty(date)){
			return 0;
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String result = sdf.format(date);
		return Integer.parseInt(result);

	}
}