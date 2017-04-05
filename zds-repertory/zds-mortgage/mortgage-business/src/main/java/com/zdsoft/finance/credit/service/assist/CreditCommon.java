package com.zdsoft.finance.credit.service.assist;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.framework.core.common.util.ObjectHelper;
import sun.misc.BASE64Decoder;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title: ReceiveCommon.java 
 * @ClassName: ReceiveCommon 
 * @Description: 征信工具类
 * @author gufeng 
 * @date 2017年2月27日 下午3:44:53 
 * @version V1.0
 */
@SuppressWarnings("restriction")
public class CreditCommon {

	/**
	 * @Title: getClassName 
	 * @Description: 根据名字得到类名
	 * @author gufeng 
	 * @param name 对象名字
	 * @return 类明以及类型
	 */
	public static Map<String,String> getClassName(String name){
		Map<String,String> map = new HashMap<>();
		String className = "Hm";
		//取第一个字母
		char[] a = name.substring(0, 1).toCharArray();
		//转大写
		a[0] -= 32;
		if(name.indexOf("List") != -1){
			map.put("TYPE", "LIST");
			map.put("NAME", className + a[0] + name.substring(1,name.indexOf("List")));
		}else{
			map.put("TYPE", "BEAN");
			map.put("NAME", className + a[0] + name.substring(1,name.length()));
		}
		return map;
	}
	
	/**
	 * @Title: populate 
	 * @Description: map转换为bean
	 * @author gufeng 
	 * @param obj 转换对象
	 * @param map 带转换数据
	 * @throws BusinessException 转换失败
	 */
	public static void populate(Object obj,Map<String, Object> map) throws BusinessException{
		try {
			BeanUtils.populate(obj, map);
		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
			throw new BusinessException("=====Map转换Bean类出错",e.getMessage());
		}
	}
	
	/**
	 * @Title: bigDecimalValue 
	 * @Description: 转换为bigDecimal
	 * @author gufeng 
	 * @param value 值
	 * @return 类型值
	 */
	public static BigDecimal bigDecimalValue(Object value){
		if(ObjectHelper.isEmpty(value) || ObjectHelper.isEmpty(value.toString())){
			return BigDecimal.ZERO;
		}
		String v = value.toString();
		if(v.equals("--")){
			return BigDecimal.ZERO;
		}
		v = v.replaceAll(",", "");
		return new BigDecimal(v);
	}
	
	/**
	 * @Title: integerValue 
	 * @Description: 转换为Integer
	 * @author gufeng 
	 * @param value 值
	 * @return 类型值
	 */
	public static Integer integerValue(Object value){
		if(ObjectHelper.isEmpty(value) || ObjectHelper.isEmpty(value.toString())){
			return 0;
		}
		String v = value.toString();
		if(v.equals("--")){
			return 0;
		}
		return Integer.parseInt(v);
	}
	
	/**
	 * @Title: stringValue 
	 * @Description: 转换为String
	 * @author gufeng 
	 * @param value 值
	 * @return 类型值
	 */
	public static String stringValue(Object value){
		if(ObjectHelper.isEmpty(value) || ObjectHelper.isEmpty(value.toString())){
			return "";
		}
		return value.toString();
	}
	
	/**
	 * @Title: base64ToInput 
	 * @Description: BASE64转inputStream
	 * @author gufeng 
	 * @param buf base64字符串
	 * @return 输入流
	 * @throws BusinessException 转换异常
	 */
	public static InputStream base64ToInput(String buf)throws BusinessException{
		BASE64Decoder decoder = new BASE64Decoder(); 
		try {
			byte[] bytes = decoder.decodeBuffer(buf);
			InputStream in = new ByteArrayInputStream(bytes);
			return in;
		} catch (IOException e) {
			e.printStackTrace();
			throw new BusinessException("=====InputString错误",e.getMessage());
		}
	}
	
	/**
	 * @Title: getCertificateType 
	 * @Description: 接口数据证件类型转换本地业务simpleCode
	 * @author gufeng 
	 * @param type 接口数据 类型
	 * @return 业务simplecode
	 */
	public static String getCertificateType(String type){
		//0 身份证 1 户口簿 2 护照 3 军官证 4 士兵证 5 港澳居民来往内地通行证 6 台湾同胞来往内地通行证 7 临时身份证 8 外国人居留证 9 警官证 A 香港身份证 B 澳门身份证
		String fullcode = "";
		switch (type) {
		case "0":
			fullcode = "YWDM002501";
			break;
		case "1":
			fullcode = "YWDM002502";
			break;
		case "2":
			fullcode = "YWDM002503";
			break;
		case "3":
			fullcode = "YWDM002504";
			break;
		case "4":
			fullcode = "YWDM002505";
			break;
		case "5":
			fullcode = "YWDM002506";
			break;
		case "6":
			fullcode = "YWDM002507";
			break;
		case "7":
			fullcode = "YWDM002508";
			break;
		case "8":
			fullcode = "YWDM002509";
			break;
		case "9":
			fullcode = "YWDM002510";
			break;
		case "A":
			fullcode = "YWDM002511";
			break;
		case "B":
			fullcode = "YWDM002512";
			break;
		case "C"://台湾身份证
			fullcode = "YWDM002513";
			break;
		case "D"://其他证件
			fullcode = "YWDM002515";
			break;
		case "E"://学生证
			fullcode = "YWDM002514";
			break;
		default:
			break;
		}
		return fullcode;
	}
	
	/**
	 * @Title: getCertificateTypeInterface 
	 * @Description: 本地业务simpleCode转换接口数据证件类型
	 * @author gufeng 
	 * @param type 本地业务simpleCode
	 * @return 接口数据证件类型
	 */
	public static String getCertificateTypeInterface(String type){
		//0 身份证 1 户口簿 2 护照 3 军官证 4 士兵证 5 港澳居民来往内地通行证 6 台湾同胞来往内地通行证 7 临时身份证 8 外国人居留证 9 警官证 A 香港身份证 B 澳门身份证
		String code = "";
		switch (type) {
		case "YWDM002501":
			code = "0";
			break;
		case "YWDM002502":
			code = "1";
			break;
		case "YWDM002503":
			code = "2";
			break;
		case "YWDM002504":
			code = "3";
			break;
		case "YWDM002505":
			code = "4";
			break;
		case "YWDM002506":
			code = "5";
			break;
		case "YWDM002507":
			code = "6";
			break;
		case "YWDM002508":
			code = "7";
			break;
		case "YWDM002509":
			code = "8";
			break;
		case "YWDM002510":
			code = "9";
			break;
		case "YWDM002511":
			code = "A";
			break;
		case "YWDM002512":
			code = "B";
			break;
		case "YWDM002513"://台湾身份证
			code = "C";
			break;
		case "YWDM002515"://其他证件
			code = "D";
			break;
		case "YWDM002514"://学生证
			code = "E";
			break;
		default:
			code = "0";
			break;
		}
		return code;
	}
	
}
