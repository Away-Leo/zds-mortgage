package com.zdsoft.finance.common.utils;

import java.util.Random;

public class RandomNumberUtil{
	/**
	 * 随机生成min到max之间的随机整型数
	 * @param max 产生的最大值
	 * @param min 产生的最小值
	 * @return
	 */
	public static int getInt(int max,int min){
		Random random = new Random();
		int result = random.nextInt(max+1);//取小于或等于max的一个随机数
		if(result < min){	
			result = getInt(max,min);//如果随机数小于min，则递归
		}
		return result;
	}
}