package com.zdsoft.finance.common.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

/**
 * 
* 版权所有：重庆正大华日软件有限公司
* @Title:TimeUtil.java
* @Package:com.zdsoft.guarantee.util
* @Description: 时间处理相关工具方法(系统时间类型统一为12位long型数字)
* @author:jincheng
* @date:2016年8月2日 下午2:03:48
* @version: V1.0
 */
public class TimeUtil {

	/**
	 * 将时间类型转换为Long型
	 * 
	 * @param date
	 *            时间
	 * @return Long
	 */
	public static Long getDateToLong(Date date) {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String result = sdf.format(date);
		return Long.parseLong(result);

	}

	/**
	 * 将时间类型转换为int型
	 * 
	 * @param date
	 * @return
	 */
	public static int getDateToInt(Date date) {
		if(date ==null){
			return 0;
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String result = sdf.format(date);
		return Integer.parseInt(result);

	}

	/**
	 * 将一字符串14位格式日期时间转为日期格式
	 * 
	 * @param dt
	 * @return
	 */
	public static Date getCalendar(String dt) {
		Calendar date = new GregorianCalendar(Integer.parseInt(dt.substring(0,
				4)), Integer.parseInt(dt.substring(4, 6)), Integer.parseInt(dt
				.substring(6, 8)), Integer.parseInt(dt.substring(8, 10)),
				Integer.parseInt(dt.substring(10, 12)), Integer.parseInt(dt
						.substring(12, 14)));
		return date.getTime();
	}
	/**
	 * 将一字符串14位或8位格式日期时间转为日期格式yyyy-MM-dd HH:mm:ss
	 * 
	 * @param dt
	 * @return
	 */
	public static String getCalendar14_8(String dt) {
		String formatStyle =dt.length() >8 ? "yyyyMMddHHmmss" : "yyyyMMdd";
		String formatStyle2 =dt.length() >8 ? "yyyy-MM-dd HH:mm:ss" : "yyyy-MM-dd";
		
		SimpleDateFormat sdf=new SimpleDateFormat(formatStyle);
		SimpleDateFormat sdf2=new SimpleDateFormat(formatStyle2);
		 long timeStart = 0;
			try {
				timeStart = sdf.parse(dt).getTime();
			} catch (Exception e) {
			}
			Date date = new Date(timeStart);
		return sdf2.format(date);
	}
	/**
	 * 将一字符串14位格式日期时间转为日期格式yyyy-MM-dd HH:mm
	 * 
	 * @param dt
	 * @return
	 */
	public static String getCalendarTime14(String dt) {
		String date=dt.substring(0, 12);
		String formatStyle ="yyyyMMddHHmm";
		String formatStyle2 ="yyyy-MM-dd HH:mm";
		
		SimpleDateFormat sdf=new SimpleDateFormat(formatStyle);
		SimpleDateFormat sdf2=new SimpleDateFormat(formatStyle2);
		long timeStart = 0;
		try {
			timeStart = sdf.parse(date).getTime();
		} catch (Exception e) {
		}
		Date dates = new Date(timeStart);
		return sdf2.format(dates);
	}
	/**
	 * 将一字符串12位格式日期时间(yyyy-MM-dd HH:mm)转为日期格式yyyyMMddHHmm
	 * 
	 * @param dt
	 * @return
	 */
	public static String getCalendarTime12(String dt) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm"); 
        
        Long lo = null;
		try {
			lo = sdf.parse(dt).getTime();
		} catch (ParseException e) {
			e.printStackTrace();
		}
        SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmm");
       
        return sf.format(lo);
	}
	
	   /**
     * 将一字符串14位格式日期时间转为日期格式
     * 
     * @param dt
     * @return
     */
    public static String getCalendar_14(String dt) {
        String _date = addDate(dt, EnumTimeUnit.Month, -1)+"000000";
        Calendar date = new GregorianCalendar(Integer.parseInt(_date.substring(0,
                4)), Integer.parseInt(_date.substring(4, 6)), Integer.parseInt(_date
                .substring(6, 8)), Integer.parseInt(_date.substring(8, 10)),
                Integer.parseInt(_date.substring(10, 12)), Integer.parseInt(_date
                        .substring(12, 14)));
        Date date__ = date.getTime();
        
        SimpleDateFormat simple = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return  simple.format(date__);
    }
    
    /**
     * 将一字符串8位格式日期时间转为日期格式 
     * 
     * @param dt
     * @return
     */
    public static String  getCalendar_8(String dt) {
        String _date = addDate(dt, EnumTimeUnit.Month, -1);
        Calendar date = new GregorianCalendar(Integer.parseInt(_date.substring(0,
                4)), Integer.parseInt(_date.substring(4, 6)), Integer.parseInt(_date
                .substring(6, 8)));
        Date date__ = date.getTime();
        SimpleDateFormat simple = new SimpleDateFormat("yyyy-MM-dd");
        return simple.format(date__);
    }

	/**
	 * @param date1   大于15天为一期
	 *            需要比较的时间 不能为空(null),需要正确的日期格式
	 * @param date2
	 *            被比较的时间 为空(null)则为当前时间
	 * @param stype
	 *            返回值类型 EnumTimeUnit 指定返回数据日期类型
	 * @return
	 */
	public static int compareDate(String date1, String date2, EnumTimeUnit type) {
		date1 = date1.length() >= 8 ? date1.substring(0, 8) : date1;
		date2 = date2.length() >= 8 ? date2.substring(0, 8) : date2;
		int n = 0;
		String formatStyle = "yyyyMMdd";

		date2 = date2 == null ? TimeUtil.getCurrentDate() : date2;

		DateFormat df = new SimpleDateFormat(formatStyle);

		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();

		try {
			c1.setTime(df.parse(date1));
			c2.setTime(df.parse(date2));
		} catch (Exception e3) {
		}
		if(type.equals(EnumTimeUnit.Day)){
			while (c2.compareTo(c1) > 0) { // 循环对比，直到相等，n 就是所要的结果
				n++;
				c1.add(Calendar.DATE, 1); // 比较天数，日期+1
			}
		}else if(type.equals(EnumTimeUnit.Month))
			n = getDateLength(date1,date2);
		return n;
	}
	
	//TODO 获取月份差
	public static Map<String,Object> getMonthCha(String date1, String date2) {
		int month=0;
		int day=0;
		int last_month_day=Integer.valueOf(date1);
		int datex=Integer.valueOf(date2);
		Map<String,Object> map=new HashMap<String, Object>();
		while (true) {
			Integer calc_M_D=Integer.valueOf(TimeUtil.addDate(last_month_day+"", EnumTimeUnit.Month, 1));
			if(calc_M_D>datex){
				for(int i=last_month_day;i<datex;i=Integer.parseInt(TimeUtil.addDate(i+"", EnumTimeUnit.Day, 1))){
					day+=1;
				}
				break;
			}
			last_month_day=calc_M_D;
			month+=1;
			if(calc_M_D.equals(Integer.valueOf(date2))){break;}
		}
		map.put("month", month);
		map.put("day", day);
		return map;
	}
	
	public static Map<String,Object> getMonthChaForSuanwei(String date1, String date2) {
		int month=0;
		int day=0;
		int last_month_day=Integer.valueOf(date1);
		int datex=Integer.valueOf(TimeUtil.addDate(date2, EnumTimeUnit.Day, 1));
		Map<String,Object> map=new HashMap<String, Object>();
		while (true) {
			Integer calc_M_D=Integer.valueOf(TimeUtil.addDate(last_month_day+"", EnumTimeUnit.Month, 1));
			if(calc_M_D>datex){
				for(int i=last_month_day;i<datex;i=Integer.parseInt(TimeUtil.addDate(i+"", EnumTimeUnit.Day, 1))){
					day+=1;
				}
				break;
			}
			last_month_day=calc_M_D;
			month+=1;
			if(calc_M_D.equals(Integer.valueOf(date2))){break;}
		}
		map.put("month", month);
		map.put("day", day);
		return map;
	}
	
	
	/**
	 * @param date1   还款计划专用 到期还本与到等额还款多一天则为一期
	 *            需要比较的时间 不能为空(null),需要正确的日期格式
	 * @param date2
	 *            被比较的时间 为空(null)则为当前时间
	 * @param stype
	 *            返回值类型 EnumTimeUnit 指定返回数据日期类型
	 * @return
	 */
	public static int compareDate(String date1, String date2, EnumTimeUnit type,String repayMethod) {
		date1 = date1.length() >= 8 ? date1.substring(0, 8) : date1;
		date2 = date2.length() >= 8 ? date2.substring(0, 8) : date2;
		int n = 0;
		String formatStyle = "yyyyMMdd";

		date2 = date2 == null ? TimeUtil.getCurrentDate() : date2;

		DateFormat df = new SimpleDateFormat(formatStyle);

		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();

		try {
			c1.setTime(df.parse(date1));
			c2.setTime(df.parse(date2));
		} catch (Exception e3) {
		}
		if(type.equals(EnumTimeUnit.Day)){
			while (c2.compareTo(c1) > 0) { // 循环对比，直到相等，n 就是所要的结果
				n++;
				c1.add(Calendar.DATE, 1); // 比较天数，日期+1
			}
			if((c2.getTimeInMillis()-c1.getTimeInMillis())/86400000 > 15)  // 如果超出时间大于15天多算一个月
				n = getDateLength(date1,date2);
				n++;
		}else if(repayMethod != null && (c2.getTimeInMillis()-c1.getTimeInMillis())/86400000 > 0 ){
			n = getDateLength(date1,date2);
			n++;
		}else if(type.equals(EnumTimeUnit.Month)){
			n = getDateLength(date1,date2);
		}
		return n;
	}

	/**
	 * 获取月差
	 * 
	 * @return
	 */
	private static int getDateLength(String date1, String date2) {
		int leng = 0;
		if (null != date1 || null != date2) {
			int yea1 = Integer.parseInt(date1.substring(0, 4));
			int mon1 = Integer.parseInt(date1.substring(4, 6));
			int yea2 = Integer.parseInt(date2.substring(0, 4));
			int mon2 = Integer.parseInt(date2.substring(4, 6));
			leng = yea2 * 12 + mon2 - yea1 * 12 - mon1;
			
			
			String date0= TimeUtil.addDate(date1,EnumTimeUnit.Month,leng);
			String formatStyle = "yyyyMMdd";
			DateFormat df = new SimpleDateFormat(formatStyle);

			Calendar c1 = Calendar.getInstance();
			Calendar c2 = Calendar.getInstance();

			try {
				c1.setTime(df.parse(date0));
				c2.setTime(df.parse(date2));
			} catch (Exception e3) {
			}
		}
		return leng;
	}

	/**
	 * 得到当前系统日期
	 * 
	 * @return
	 */
	public static String getCurrentDate() {
		Calendar c = Calendar.getInstance();
		Date date = c.getTime();
		SimpleDateFormat simple = new SimpleDateFormat("yyyyMMddHHmmss");
		return simple.format(date);
	}
	/**
	 * 得到当前系统日期格式yyyy-MM-dd HH:mm:ss
	 * @return
	 */
	public static String getCurrentDateFormat() {
		Calendar c = Calendar.getInstance();
		Date date = c.getTime();
		SimpleDateFormat simple = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return simple.format(date);
	}
	
	/**
	 * 得到当前系统日期yyyyMMdd
	 * 
	 * @return
	 */
	public static Integer getCurrentDateInteger() {
		Date nowDate = new Date(); 
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");//设置日期格式
		return Integer.parseInt(dateFormat.format(nowDate));  //转换为数字日期
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
		else if (type.equals(EnumTimeUnit.Year))
			c1.add(Calendar.YEAR, no);
		SimpleDateFormat simple = new SimpleDateFormat("yyyyMMdd");
		return simple.format(c1.getTime());
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
	public static String addDateTwo(String date, EnumTimeUnit type, int no) {
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
		else if (type.equals(EnumTimeUnit.Year))
			c1.add(Calendar.YEAR, no);
		c1.add(Calendar.DATE, 1);
		SimpleDateFormat simple = new SimpleDateFormat("yyyyMMdd");
		return simple.format(c1.getTime());
	}


	/**
	 * 返回传入时间的当月最后一天
	 * 
	 * @param date
	 * @return
	 */
	public static String lastDayOfThisMonth(String date) {
		String _date = addDate(date, EnumTimeUnit.Month, 1);
		String firstDay = firstDayOfThisMonth(_date);
		return addDate(firstDay, EnumTimeUnit.Day, -1);
	}

	/**
	 * 返回传入时间当月的第一天.
	 * 
	 * @param date
	 * @return
	 */
	public static String firstDayOfThisMonth(String date) {
		StringBuffer _date = new StringBuffer();
		_date.append(date.substring(1, 4));
		_date.append("01");
		return _date.toString();
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
		 * 获得两个日期之前相差的月份 
		 * @param start
		 * @param end
		 * @return
		 */
	 public static int getMonth(Date start, Date end) {
	        if (start.after(end)) {
	            Date t = start;
	            start = end;
	            end = t;
	        }
	        Calendar startCalendar = Calendar.getInstance();
	        startCalendar.setTime(start);
	        Calendar endCalendar = Calendar.getInstance();
	        endCalendar.setTime(end);
	        Calendar temp = Calendar.getInstance();
	        temp.setTime(end);
	        temp.add(Calendar.DATE, 1);

	        int year = endCalendar.get(Calendar.YEAR)
	                - startCalendar.get(Calendar.YEAR);
	        int month = endCalendar.get(Calendar.MONTH)
	                - startCalendar.get(Calendar.MONTH);

	        if ((startCalendar.get(Calendar.DATE) == 1)
	                && (temp.get(Calendar.DATE) == 1)) {
	            return year * 12 + month + 1;
	        } else if ((startCalendar.get(Calendar.DATE) != 1)
	                && (temp.get(Calendar.DATE) == 1)) {
	            return year * 12 + month;
	        } else if ((startCalendar.get(Calendar.DATE) == 1)
	                && (temp.get(Calendar.DATE) != 1)) {
	            return year * 12 + month;
	        } else {
	            return (year * 12 + month - 1) < 0 ? 0 : (year * 12 + month);
	        }
	    }
}
