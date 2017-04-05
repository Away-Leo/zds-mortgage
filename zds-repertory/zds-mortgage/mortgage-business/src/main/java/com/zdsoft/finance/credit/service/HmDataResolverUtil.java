package com.zdsoft.finance.credit.service;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

import com.zdsoft.finance.credit.entity.MyCredit;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title: HmRepayStatusResolverUtil.java 
 * @ClassName: HmRepayStatusResolverUtil 
 * @Description: 解析外部征信信息数据中的还款记录，供贷款记录等征信内容调用
 * @author panshm 
 * @date 2017年3月3日 下午3:10:20 
 * @version V1.0
 */
public class HmDataResolverUtil {
	
	/**
	 * 类型1：解析 累计逾期期数
	 */
	public static final int TYPE_CUMULATIVEOVERDUE = 1;

	/**
	 * 类型2：解析 最高逾期期数
	 */
	public static final int TYPE_MAXIMUMOVERDUE = 2;

	/**
	 * 类型3：解析 最12个月累计逾期次数
	 */
	public static final int TYPE_CUMULATIVEOVERDUE12 = 3;

	/**
	 * 类型4：解析 最12个月最高逾期期数
	 */
	public static final int TYPE_MAXIMUMOVERDUE12 = 4;
	
	/**
	 * @Title: resolveRepayStatus
	 * @Description: 解析外部征信信息数据中的还款记录，供贷款记录等征信内容调用
	 * @author panshm 
	 * @param repayStatusStr 外部征信信息数据中的还款记录
	 * @return MyCredit
	 */
	public static MyCredit resolveRepayStatus(String repayStatusStr) {
		MyCredit returnObj = new MyCredit();
		if (StringUtils.isEmpty(repayStatusStr)) {
			returnObj.setCumulativeOverdue(0);
			returnObj.setMaximumOverdue(0);
			returnObj.setCumulativeOverdue12(0);
			returnObj.setMaximumOverdue12(0);
			return returnObj;
		}
		String str = repayStatusStr.replaceAll(" ", "");
		Integer overdueCount = 0;
		Integer maxOverduePeriod = 0;
		Integer overdueCountOneYear = 0;
		Integer maxOverduePeriodOneYear = 0;
		for (int i = 0; i < str.length(); i++){
			if (Character.isDigit(str.charAt(i))) {
				overdueCount++;
				Integer thisPeriod = Character.getNumericValue(str.charAt(i));
				if (thisPeriod > maxOverduePeriod) {
					maxOverduePeriod = thisPeriod;
				}
			}
		}
		if (repayStatusStr.length()>12) {
			String shortStr = str.substring(str.length()-12, str.length());
			for (int i = 0; i < shortStr.length(); i++){
				if (Character.isDigit(shortStr.charAt(i))) {
					overdueCountOneYear++;
					Integer thisPeriod = Character.getNumericValue(shortStr.charAt(i));
					if (thisPeriod > maxOverduePeriodOneYear) {
						maxOverduePeriodOneYear = thisPeriod;
					}
				}
			}
		} else {
			overdueCountOneYear = overdueCount;
			maxOverduePeriodOneYear = maxOverduePeriod;
		}
		
		returnObj.setCumulativeOverdue(overdueCount);
		returnObj.setMaximumOverdue(maxOverduePeriod);
		returnObj.setCumulativeOverdue12(overdueCountOneYear);
		returnObj.setMaximumOverdue12(maxOverduePeriodOneYear);
		return returnObj;
	}
	
	/**
	 * @Title: resolveHmData
	 * @Description: 解析外部征信信息数据中的标题内容，供贷款记录等征信页面显示用
	 * @author panshm 
	 * @param hmData 外部征信信息数据中的标题
	 * @return MyCredit
	 */
	public static MyCredit resolveHmData(String hmData) {
		MyCredit returnObj = new MyCredit();
		// 解析 贷款发放日期和结清日期
		Pattern pattern =Pattern.compile("\\d{4}年\\d{1,2}月\\d{1,2}日");
	    Matcher matcher=pattern.matcher(hmData);
	    String[] dateStr = new String[3];
	    int i = 0;
	    while(matcher.find()){
	    	dateStr[i] = matcher.group();
	    	i++;
	    }
	    returnObj.setLoanDate(dateStringToLong(dateStr[0]));
	    returnObj.setClosingDate(dateStringToLong(dateStr[1]));
	    
	    // 解析 贷款金额
	    pattern = Pattern.compile("发放的\\d*,*\\d*");
	    matcher=pattern.matcher(hmData);
	    while(matcher.find()){
	    	String loanAmount = matcher.group();
	    	loanAmount = loanAmount.replaceAll("发放的", "");
	    	loanAmount = loanAmount.replaceAll(",", "");
	    	returnObj.setLoanAmount(new BigDecimal(loanAmount));
	    }
	    
	    // 解析 贷款种类
	    pattern = Pattern.compile("\\）([\u3007\u3400-\u4DB5\u4E00-\u9FCB\uE815-\uE864]|[\uD840-\uD87F][\uDC00-\uDFFF])*");
	    matcher=pattern.matcher(hmData);
	    while(matcher.find()){
	    	String loanType = matcher.group();
	    	loanType = loanType.substring(1, loanType.length());
	    	returnObj.setLoanTypesName(loanType);
	    }
	    
	    // 解析 贷款年限
	    pattern = Pattern.compile("\\d{1,}期");
	    matcher=pattern.matcher(hmData);
	    while(matcher.find()){
	    	String loanTerm = matcher.group();
	    	loanTerm = loanTerm.substring(0, loanTerm.length()-1);
	    	returnObj.setLoanTerm(Integer.valueOf(loanTerm)/12);
	    }
	    return returnObj;
	}
	
	/**
	 * @Title: dateStringToLong 
	 * @Description: 将String格式的日期转换为Long型
	 * @author panshm 
	 * @param dateStr 字符型日期数据
	 * @return Long型日期
	 */
	private static Long dateStringToLong(String dateStr) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
 		try {
			Date date = formatter.parse(dateStr);
			return date.getTime();
		} catch (ParseException e) {
			return 0L;
		}
	}
	
	/**
	 * @Title: main 
	 * @Description: 测试本工具类用main方法
	 * @author panshm 
	 * @param args
	 */
	public static void main(String[] args) {
		String aaa = "N N N N N N N N N 1 1 N 1 N 1 2 N 1 N N N N N N";
		resolveRepayStatus(aaa);
		
		String bbb = "1.2009年08月12日商业银行“DN”发放的200,000元（人民币）个人住房贷款，业务号X，抵押担保，240期，按月归还，2029年08月11日到期。截至2016年12月12日，";
		resolveHmData(bbb);
	}

}
