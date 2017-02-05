package com.zdsoft.finance.common.utils;

/**
 * 表达式括号匹配校验辅助类
 *
 * @author GongHaitao
 * @since 1.0
 * @see ExtStack
 */
public class ExpressionUtil {
	
	/**
	 * 匹配成功
	 */
	public static final int CheckStateCode_1 = 0;
	/**
	 * 左右括号匹配次序不正确
	 */
	public static final int CheckStateCode_2 = 1;
	/**
	 * 左右方括号匹配次序不正确
	 */
	public static final int CheckStateCode_3 = 2;
	/**
	 * 左右花括号匹配次序不正确
	 */
	public static final int CheckStateCode_4 = 3;
	/**
	 * 右括号多于左括号
	 */
	public static final int CheckStateCode_5 = 4;
	/**
	 * 左括号多于右括号
	 */
	public static final int CheckStateCode_6 = 5;

	/**
	 * 表达式是否正确
	 * @param exp　表达式生成的字符串数组
	 * @param n	表达式字符串长度
	 * @return 检验结果码
	 * @throws Exception
	 */
	public static int expIsCorrect(String[] exp, int n) throws Exception {
		ExtStack myStack = new ExtStack();
		for (int i = 0; i < n; i++) {
			if ((exp[i].equals(new String("("))) || (exp[i].equals(new String("[")) || (exp[i].equals(new String("{")))))
				myStack.epush(exp[i]);
			else if ((exp[i].equals(new String(")"))) && myStack.notEmpty()	&& myStack.getTop().equals(new String("(")))
				myStack.epop();
			else if ((exp[i].equals(new String(")"))) && myStack.notEmpty()) {
				System.out.println("左右括号匹配次序不正确！");
				return CheckStateCode_2;
			} else if ((exp[i].equals(new String("]"))) && myStack.notEmpty() && myStack.getTop().equals(new String("[")))
				myStack.epop();
			else if ((exp[i].equals(new String("]"))) && myStack.notEmpty() && !myStack.getTop().equals(new String("["))) {
				System.out.println("左右方括号匹配次序不正确！");
				return CheckStateCode_3;
			} else if ((exp[i].equals(new String("}"))) && myStack.notEmpty() && myStack.getTop().equals(new String("{")))
				myStack.epop();
			else if ((exp[i].equals(new String("}"))) && myStack.notEmpty() && !myStack.getTop().equals(new String("{"))) {
				System.out.println("左右花括号匹配次序不正确");
				return CheckStateCode_4;
			} else if ((exp[i].equals(new String(")")))
					|| (exp[i].equals(new String("]")))
					|| (exp[i].equals(new String("]")))
					|| (exp[i].equals(new String("}")))) {
				System.out.println("右括号多于左括号！");
				return CheckStateCode_5;
			}
		}
		if (myStack.notEmpty()){
			System.out.println("左括号多于右括号！");
			return CheckStateCode_6;
		}
		else {
			System.out.println("匹配正确！");
			return CheckStateCode_1;
		}
	}

	public static String[] strToString(String str) {
		int n = str.length();
		String[] a;
		a = new String[n];
		for (int i = 0; i < n; i++) {
			a[i] = str.substring(i, i + 1);
		}
		return a;
	}
	
	public static void main(String[] args) {
		String str;
		int n;
		try {
			str = "(())abc{[}()}"; // 左右括号匹配次序不正确
			n = str.length();
			String[] a;
			a = strToString(str); // 转换成String类型数组
			expIsCorrect(a, n);

			str = "(()))abc{[]}"; // 右括号多于左括号
			n = str.length();
			String[] b;
			b = strToString(str);
			expIsCorrect(b, n);

			str = "(()()abc{[]}"; // 左括号多于右括号
			n = str.length();
			String[] c;
			c = strToString(str);
			expIsCorrect(c, n);

			str = "(())abc{[]}"; // 括号匹配正确
			n = str.length();
			String[] c1;
			c1 = strToString(str);
			expIsCorrect(c1, n);

			str = "${}-${{}}"; // 括号匹配正确
			n = str.length();
			String[] s;
			s = strToString(str);
			expIsCorrect(s, n);
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}