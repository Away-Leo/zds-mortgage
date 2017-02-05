package com.zdsoft.finance.common.utils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import java.util.ArrayList;
import java.util.List;

/**
 * 还款计划处理工具
 * @author pan
 *
 */
public class RepaymentPlanUtil {

	/**
	 * 将还款计划json字符串转List集合
	 * @param jsonString
	 * @return
	 */
	public static List<RepaymentPlanDTO> paymentPlanJSONStringToObject(String jsonString){
		jsonString = jsonString.replace("\"","");
		jsonString = jsonString.replace(":", "\":\"").replace(",", "\",\"");
		jsonString = jsonString.replace("{", "{\"").replace("}", "\"}").replace("}\",\"{", "},{");
		
		JsonConfig jsonConfig = new JsonConfig();  
		jsonConfig.registerJsonValueProcessor(String.class,new DoubleJsonValueProcessor());  
		  
		List<RepaymentPlanDTO> repaymentPlans = new ArrayList<RepaymentPlanDTO>();
		JSONArray array = JSONArray.fromObject(jsonString);
		for (int i = 0; i < array.size(); i++) {
			JSONObject jsonObject = array.getJSONObject(i);
			RepaymentPlanDTO repaymentPlan  = (RepaymentPlanDTO) JSONObject.toBean(jsonObject,
					RepaymentPlanDTO.class);
			
			repaymentPlans.add(repaymentPlan);
		}
		return repaymentPlans;
	}
}
