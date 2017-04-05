package com.zdsoft.finance.credit.service.assist;

import java.security.*;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.crypto.*;
import javax.crypto.spec.*;

import sun.misc.*;

/**
 * 版权所有：重庆正大华日软件有限公司
 * 
 * @Title: DesUtil.java
 * @ClassName: DesUtil
 * @Description: 泛华那边给的加密代码
 * @author gufeng
 * @date 2017年2月27日 下午6:03:47
 * @version V1.0
 */
@SuppressWarnings("restriction")
public class DesUtil {
	private static final byte[] iv = { 0x12, 0x34, 0x56, 0x78, (byte) 0x90, (byte) 0xab, (byte) 0xcd, (byte) 0xef };// des
																													// 向量
	private static BASE64Encoder enc = new BASE64Encoder();// 将byte[]转换成String
	private static BASE64Decoder dec = new BASE64Decoder(); // 将String转换成byte[]
	private final static SimpleDateFormat formate = new SimpleDateFormat("yyyyMMddHHmmss");

	/**
	 * @Title: encrypt 
	 * @Description: 加密字节数组
	 * @author gufeng 
	 * @param arrB 需加密的字节数组
	 * @param key 密钥
	 * @return 加密后的字节数组
	 * @throws Exception 加密异常
	 */
	public static byte[] encrypt(byte[] arrB, String key) throws Exception {
		DESKeySpec desKeySpec = new DESKeySpec(key.getBytes("UTF-8"));
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
		SecretKey secretKey = keyFactory.generateSecret(desKeySpec);
		IvParameterSpec ivp = new IvParameterSpec(DesUtil.iv);
		Cipher encryptCipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
		encryptCipher.init(Cipher.ENCRYPT_MODE, secretKey, ivp);

		return encryptCipher.doFinal(arrB);
	}
	
	/**
	 * @Title: makeSecKey 
	 * @Description: 加密
	 * @author gufeng 
	 * @param userName 用户名
	 * @param password 密码
	 * @param key 密钥
	 * @return 加密后的字符串
	 * @throws Exception 加密异常
	 */
	public static String makeSecKey(String userName, String password, String key) throws Exception {
		String str = userName + "|" + password + "|" + formate.format(new Date());
		String sec_key = encrypt(str, key);
		System.out.println(sec_key);
		return sec_key;

	}

	/**
	 * @Title: checkSecKey 
	 * @Description: 检查加密
	 * @author gufeng 
	 * @param secStr 加密字符串
	 * @param userName 用户名
	 * @param password 密码
	 * @param key 密钥
	 * @return 是否通过
	 * @throws Exception 解密异常
	 */
	public static boolean checkSecKey(String secStr, String userName, String password, String key)
			throws Exception {
		boolean b = false;
		String str = decrypt(secStr, key);
		String strs[] = str.split("\\|");
		if (userName.equals(strs[0]) || password.equals(strs[1])) {
			Date sendTime = formate.parse(strs[2]);
			if (new Date().getTime() - sendTime.getTime() < 1000 * 60 * 10) // 十分钟之内就可以
				b = true;
		}
		return b;
	}

	/**
	 * @Title: desEncrypt 
	 * @Description: 备注加密
	 * @author gufeng 
	 * @param message 信息
	 * @param key 密钥
	 * @return 加密后字节
	 * @throws Exception 加密异常
	 */
	public static byte[] desEncrypt(String message, String key) throws Exception {
		Cipher cipher = Cipher.getInstance("DES");

		DESKeySpec desKeySpec = new DESKeySpec(key.getBytes("UTF-8"));
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
		SecretKey secretKey = keyFactory.generateSecret(desKeySpec);
		cipher.init(Cipher.ENCRYPT_MODE, secretKey);

		return cipher.doFinal(message.getBytes("UTF-8"));
	}

	/**
	 * @Title: encrypt 
	 * @Description: 加密字符串
	 * @author gufeng 
	 * @param xml 需加密的字符串
	 * @param key 密钥
	 * @return 加密后字符串
	 * @throws Exception 加密异常
	 */
	public static String encrypt(String xml, String key) throws Exception {
		return DesUtil.enc.encode(encrypt(xml.getBytes(), key));
	}

	/**
	 * @Title: decrypt 
	 * @Description: 解密字节数组
	 * @author gufeng 
	 * @param arrB 需解密的字节数组
	 * @param key 密钥
	 * @return 解密后的字节数组
	 * @throws Exception 解密异常
	 */
	public static byte[] decrypt(byte[] arrB, String key) throws Exception {
		DESKeySpec desKeySpec = new DESKeySpec(key.getBytes("UTF-8"));

		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
		SecretKey secretKey = keyFactory.generateSecret(desKeySpec);
		IvParameterSpec ivp = new IvParameterSpec(DesUtil.iv);

		Cipher decryptCipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
		decryptCipher.init(Cipher.DECRYPT_MODE, secretKey, ivp);

		return decryptCipher.doFinal(arrB);
	}

	/**
	 * @Title: decrypt 
	 * @Description: 解密字符串
	 * @author gufeng 
	 * @param xml 需解密的字符串
	 * @param key 密钥
	 * @return 解密后字符串
	 * @throws Exception 解密异常
	 */
	public static String decrypt(String xml, String key) throws Exception {
		return new String(decrypt(DesUtil.dec.decodeBuffer(xml), key));
	}

	/**
	 * @Title: decrypt 
	 * @Description: 解密字符串
	 * @author gufeng 
	 * @param xml 需解密的字符串
	 * @param key 密钥
	 * @param encoding 编码格式
	 * @return 解密后的字符串
	 * @throws Exception 解密异常
	 */
	public static String decrypt(String xml, String key, String encoding) throws Exception {
		return new String(decrypt(DesUtil.dec.decodeBuffer(xml), key), encoding);
	}

	/**
	 * @Title: getKey 
	 * @Description: 从指定字符串生成密钥，密钥所需的字节数组长度为8位 不足8位时后面补0，超出8位只取前8位
	 * @author gufeng 
	 * @param arrBTmp 构成该字符串的字节数组
	 * @return 生成的密钥
	 * @throws Exception 获取密钥异常
	 */
	@SuppressWarnings("unused")
	private Key getKey(byte[] arrBTmp) throws Exception {
		// 创建一个空的8位字节数组（默认值为0）
		byte[] arrB = new byte[8];

		// 将原始字节数组转换为8位
		for (int i = 0; i < arrBTmp.length && i < arrB.length; i++) {
			arrB[i] = arrBTmp[i];
		}

		// 生成密钥
		Key key = new javax.crypto.spec.SecretKeySpec(arrB, "DES");

		return key;
	}

	/**
	 * @Title: main 
	 * @Description: 测试方法
	 * @author gufeng 
	 * @param args 字符串数据
	 * @throws Exception 测试异常
	 */
	public static void main(String[] args) throws Exception {

		String key = makeSecKey("ttjp8899", "cy20551155", "xbychina");
		System.out.println(key);
		String con = decrypt(key, "xbychina");
		System.out.println(con);
	}
}
