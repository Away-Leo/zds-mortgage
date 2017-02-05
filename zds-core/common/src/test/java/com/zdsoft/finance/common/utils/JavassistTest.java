package com.zdsoft.finance.common.utils;

import java.lang.reflect.Method;

import org.junit.Test;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtField;
import javassist.CtMethod;
import javassist.CtNewMethod;

/**
 * Javassist 测试类
 * @author Maple
 *
 */
public class JavassistTest {
	
	/*@Test
	public void testInvoke() {
		try {
			   System.out.println("=======================");
			ClassPool pool = ClassPool.getDefault();
			//获取要修改的类的所有信息
			CtClass ct = pool.get("com.zdsoft.finance.common.auto.entity.ExpandFiled"); 	
			CtField f= new CtField(pool.get(String.class.getName()),"money", ct);
			ct.addField(f);
			CtMethod setMethod=null;
			setMethod=CtNewMethod.setter("setMoney", f);
			ct.addMethod(setMethod);
			CtMethod getMethod=null;
			getMethod=CtNewMethod.getter("getMoney", f);
			ct.addMethod(getMethod);
			ct.toClass();
			 Class<?> cls = Class.forName("com.zdsoft.finance.common.auto.entity.ExpandFiled");
			// 反射来执行它
	         Method method = cls.getMethod("setMoney", String.class);
	         Method getmethod = cls.getMethod("getMoney");
	         Object[] paramObjs = new Object[1];
	         paramObjs[0]="123";
	         Object obj = cls.newInstance();
	         method.invoke(obj,paramObjs);
	         String money=(String) getmethod.invoke(obj);
	         System.out.println("====="+money);
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
         
	}*/
	
}
