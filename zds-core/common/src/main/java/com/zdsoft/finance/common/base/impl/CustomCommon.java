package com.zdsoft.finance.common.base.impl;

import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.ConvertUtils;

import com.zdsoft.finance.common.base.QueryObj;
import com.zdsoft.framework.core.common.util.ObjectHelper;


/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title: CustomCommon.java 
 * @ClassName: CustomCommon 
 * @Description: 组合查询工具类
 * @author gufeng 
 * @date 2017年3月13日 下午5:11:03 
 * @version V1.0
 */
public class CustomCommon {

	/**
	 * 查询拼接结果的sql/hql
	 */
	public static final String QUERY_SQL = "sql";
	
	/**
	 * 查询拼接结果的参数集合数组
	 */
	public static final String QUERY_PARAM = "obs";
	
	/**
	 * 构造SQL查询列表的查询SQL
	 * @param param 参数集合
	 * @param _sql 查询基础SQL
	 * @param _extendSql 查询扩展SQL
	 * @return 构造的查询SQL
	 */
	public static String getSearchSql(List<QueryObj> param, StringBuffer _sql, StringBuffer _extendSql){
		String conditionSql = generateSearchConditionSql(param).get(QUERY_SQL).toString();
		if(ObjectHelper.isNotEmpty(_extendSql)){
			return _sql.toString()+conditionSql+_extendSql.toString();
		}else{
			return _sql.toString()+conditionSql;
		}
	}
	
	/**
	 * 构造列表查询条件SQL/HQL
	 * @param list 参数对象列表
	 * @return map (key="sql":查询SQL,key="obs":参数集合)
	 */
	public static Map<String,Object> generateSearchConditionSql(List<QueryObj> list){
		Map<String,Object> map = new HashMap<>();
		StringBuffer hql = new StringBuffer(100);
		int len = list.size();
		for (QueryObj queryObj : list){
			if(null!=queryObj.getQueryList())
				len = len+queryObj.getQueryList().size()-1;
		}
		
		Object[] obs = new Object[len];
		Integer obssize = 0;
		for (QueryObj queryObj : list) {
			hql.append(" AND ");
			// 复选处理
			if(null!=queryObj.getQueryList()&&queryObj.getQueryList().size()>0){
					hql.append("(");
					Integer i = obssize;
					for (QueryObj queryo : queryObj.getQueryList()) {
						if (i-obssize>0){
						   hql.append(" OR ");
						}
						hql.append(queryo.getObj() + ".");
						hql.append(queryo.getElement()).append(" ");
						hql.append(queryo.getOperator());
						hql.append(" ? ");
						obs[i] = queryo.getValue();
						i++;
					}
					obssize = i;
					hql.append(")");
				continue;
			}
			hql.append(queryObj.getObj() + ".");
			hql.append(queryObj.getElement()).append(" ");
			hql.append(queryObj.getOperator());
			hql.append(" ? ");
			obs[obssize] = queryObj.getValue();
			obssize++;
		}
		
		map.put(QUERY_SQL, hql.toString());
		map.put(QUERY_PARAM, obs);
		return map;
	}

	/**
	 * 
	 * 从查询返回结果转换成具体类型集合
	 * 要求：别名和属性名相同
	 *
	 * @author jingyh
	 * @param clazz
	 * @param list
	 * @return
	 */
	public static List<Object> convert(Class<?> clazz, List<Map<String, Object>> list) {
		List<Object> result = null;
		if (ObjectHelper.isEmpty(list)) {
			return new ArrayList<Object>();
		}
		result = new ArrayList<Object>();
		try {
			PropertyDescriptor[] props = Introspector.getBeanInfo(clazz).getPropertyDescriptors();
			for (Map<String, Object> map : list) {
				Object obj = clazz.newInstance();
				for (String key:map.keySet()) {
					String attrName = key;
					for (PropertyDescriptor prop : props) {
						if (!attrName.equalsIgnoreCase(prop.getName())) {
							continue;
						}
						Method method = prop.getWriteMethod();
						Object value = map.get(key);
						if (value != null) {
							value = ConvertUtils.convert(value, prop.getPropertyType());
						}
						method.invoke(obj,value);
					}
				}
				result.add(obj);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("数据转换错误");
		}
		return result;
	}
}
