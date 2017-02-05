package com.zdsoft.finance.common.utils;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.zdsoft.essential.client.service.CED;
import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.framework.core.common.annotation.Log;
import com.zdsoft.framework.core.common.util.DateHelper;
import com.zdsoft.framework.core.common.util.ObjectHelper;

@Component
public class VoUtil {

	@Autowired
	private CED CED;

	@Log
	private Logger logger;

	private static VoUtil voUtil;

	public void setCED(CED CED, Logger logger) {
		this.CED = CED;
		this.logger = logger;
	}

	@PostConstruct // 关键二 通过@PostConstruct 和 @PreDestroy 方法 实现初始化和销毁bean之前进行的操作
	public void init() {
		voUtil = this;
		voUtil.CED = this.CED; // 初使化时将已静态化的testService实例化
		voUtil.logger = this.logger;
	}

	/**
	 * 全复制
	 * 
	 * @param source
	 *            复制源
	 * @param target
	 *            目标源
	 * @param direction
	 *            true为Vo->Po,false为Po->Vo
	 * @throws BusinessException
	 *             如果复制源或者目标源为null，则抛出异常10010004;如果复制出错，则抛出异常10010005
	 */
	public static void copyPoperties(Object source, Object target, Boolean direction) {
		if (ObjectHelper.isEmpty(source) || ObjectHelper.isEmpty(target)) {
			voUtil.logger.error("传入参数为空，10010004：" + source + "," + target);
		}
		try {
			copy(source, target, direction, null, null, null);
		} catch (Exception e) {
			e.printStackTrace();
			voUtil.logger.error("voUtil 复制出错", e);
		}
	}

	/**
	 * 选择性复制，剔除不需要复制的
	 * 
	 * @param source
	 *            数据源
	 * @param target
	 *            目标源
	 * @param direction
	 *            true为Vo->Po,false为Po->Vo
	 * @param args
	 *            剔除的属性名
	 * @throws BusinessException
	 *             如果复制源或者目标源为null，则抛出异常10010004;如果复制出错，则抛出异常10010005
	 */
	public static void copyPoperties(Object source, Object target, Boolean direction, String[] args) {
		if (ObjectHelper.isEmpty(source) || ObjectHelper.isEmpty(target)) {
			voUtil.logger.error("传入参数为空，10010004：" + source + "," + target);
		}
		try {
			copy(source, target, direction, args, null, null);
		} catch (Exception e) {
			e.printStackTrace();
			voUtil.logger.error("voUtil 复制出错", e);
		}
	}

	/**
	 * 选择性复制，剔除不复制的，以及需要转换simpleCode
	 * 
	 * @param source
	 *            数据源
	 * @param target
	 *            目标源
	 * @param direction
	 *            true为Vo->Po,false为Po->Vo
	 * @param args
	 *            剔除的属性名
	 * @param simpleArgs
	 *            转换的simpleCode名
	 * @throws BusinessException
	 *             如果复制源或者目标源为null，则抛出异常10010004;如果复制出错，则抛出异常10010005;
	 */
	public static void copyPoperties(Object source, Object target, Boolean direction, String[] args,
			String[] simpleArgs) {
		if (ObjectHelper.isEmpty(source) || ObjectHelper.isEmpty(target)) {
			voUtil.logger.error("传入参数为空，10010004：" + source + "," + target);
		}
		try {
			copy(source, target, direction, args, simpleArgs, null);
		} catch (Exception e) {
			e.printStackTrace();
			voUtil.logger.error("VoUtil复制出错", e);
		}
	}

	/**
	 * 选择性复制，剔除不复制的，以及需要转换simpleCode
	 * 
	 * @param source
	 *            数据源
	 * @param target
	 *            目标源
	 * @param direction
	 *            true为Vo->Po,false为Po->Vo
	 * @param args
	 *            剔除的属性名
	 * @param simpleArgs
	 *            转换的simpleCode名
	 * @param dateArgs
	 *            需要转换的时间类型 属性|转换后的格式
	 * @throws BusinessException
	 *             如果复制源或者目标源为null，则抛出异常10010004;如果复制出错，则抛出异常10010005;
	 */
	public static void copyPoperties(Object source, Object target, Boolean direction, String[] args,
			String[] simpleArgs, String[] dateArgs) {
		if (ObjectHelper.isEmpty(source) || ObjectHelper.isEmpty(target)) {
			voUtil.logger.error("传入参数为空，10010004：" + source + "," + target);
		}
		try {
			copy(source, target, direction, args, simpleArgs, dateArgs);
		} catch (Exception e) {
			e.printStackTrace();
			voUtil.logger.error("VoUtil复制出错", e);
		}
	}

	private static void copy(Object source, Object target, Boolean direction, String[] args, String[] simpleArgs,
			String[] dateArgs) throws Exception {

		if (source == null || target == null) {
		}

		BeanUtils.copyProperties(source, target, args);
		// 是否向上转换（元=>万元,0.01=>1%）
		Class<?> objClass = target.getClass();
		Method[] methods = objClass.getMethods();
		List<String> ingoreHandleList;
		if (ObjectHelper.isEmpty(args)) {
			ingoreHandleList = new ArrayList<String>();
		} else {
			ingoreHandleList = Arrays.asList(args);
		}
		List<String> simplecodeArgs;
		if (ObjectHelper.isEmpty(simpleArgs)) {
			simplecodeArgs = new ArrayList<String>();
		} else {
			simplecodeArgs = Arrays.asList(simpleArgs);
		}
		List<String> dateArgsArray;
		if (ObjectHelper.isEmpty(dateArgs)) {
			dateArgsArray = new ArrayList<String>();
		} else {
			dateArgsArray = Arrays.asList(dateArgs);
		}

		for (Method method : methods) {
			if (method.getName().startsWith("set") && method.getParameterCount() == 1
					&& void.class.equals(method.getReturnType()) && method.getModifiers() == Modifier.PUBLIC) {
				// set方法
				// 字段名称
				String fieldNm = StringUtils.uncapitalize(method.getName().substring(3));
				if (ingoreHandleList.contains(fieldNm)) {
					// 忽略处理
					continue;
				}
				// 字段类型
				Class<?> type = method.getParameterTypes()[0];
				Method getM = objClass.getMethod("get" + method.getName().substring(3));
				Method setM = method;
				if (getM != null && setM != null) {
					// get、set方法存在
					Object val = getM.invoke(target);

					if (simplecodeArgs.contains(fieldNm)) {

						Method setNmM = objClass.getMethod("set" + method.getName().substring(3) + "Name", String.class);
						String newVal = "";
						if (ObjectHelper.isNotEmpty(val)) {
							newVal = voUtil.CED.loadSimpleCodeNameByFullCode((String) val);
						}
						setM.invoke(target, val);
						setNmM.invoke(target, newVal);
					}

					if (val != null && val instanceof Long) {
						for (String dateArg : dateArgsArray) {
							String[] dateArgL = dateArg.split("\\|");
							if (dateArgL[0].equals(fieldNm)) {
								Method setNmM = objClass.getMethod("set" + method.getName().substring(3) + "Str",
										String.class);
								String newVal = DateHelper.longToDate((Long)val, dateArgL[1]);
								setM.invoke(target, val);
								setNmM.invoke(target, newVal);
							}
						}
					}
					

					if (val != null) {
						// 数据不为空
						if (type.equals(BigDecimal.class)) {
							// BigDecimal 类型
							if (direction) {
								setM.invoke(target, ((BigDecimal) val));
							} else {
								setM.invoke(target, ((BigDecimal) val));
							}
						} else if (type.equals(Double.class)) {
							// Double类型
							if (direction) {
								setM.invoke(target,
										RateConvertUtil.convertToXS((Double) val, RateConvertUtil.RATE_YEAR));
							} else {
								setM.invoke(target,
										RateConvertUtil.convertToZS((Double) val, RateConvertUtil.RATE_YEAR));
							}
						}
					}
				}
			}
		}
	}

}
