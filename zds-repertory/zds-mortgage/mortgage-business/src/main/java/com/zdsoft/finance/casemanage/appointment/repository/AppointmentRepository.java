package com.zdsoft.finance.casemanage.appointment.repository;

import java.util.List;
import java.util.Map;

import com.zdsoft.finance.casemanage.appointment.entity.Appointment;
import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.framework.core.common.util.ObjectHelper;

/**
 * 版权所有：重庆正大华日软件有限公司
 * 
 * @Title:AppointmentRepository.java
 * @Package:com.zdsoft.finance.casemanage.appointment.repository
 * @Description:案件预约
 * @author: xiongpan
 * @date:2017年1月14日 下午7:42:22
 * @version:v1.0
 */
public interface AppointmentRepository extends CustomRepository<Appointment, String> {

	/**
	 * 查询目前的案件预约情况
	 * 
	 * @return
	 */

	public default List<Map<String, Object>> queryCaseCount(Long nowDate) {

		StringBuffer ssql = new StringBuffer(
				" SELECT tempT.date AS date,COUNT(DISTINCT CASE WHEN tempT.timePart = 'YWDM0051059' THEN tempT.id END) AS morning, ");
		ssql.append(" COUNT(DISTINCT CASE WHEN tempT.timePart = 'YWDM0051060' THEN tempT.id END) AS afternoon ");
		ssql.append(
				" FROM ( SELECT interviewDate AS date,interviewAmOrPm AS timePart,id FROM case_appointment "
				+ " UNION SELECT mortgageDate,mortgageDateAmOrPm,id FROM case_appointment "
				+ " UNION SELECT notarizationDate,notarizationAmOrPm,id FROM case_appointment "
				+ " UNION SELECT entrustDate,entrustAmOrPm,id FROM case_appointment ) tempT  ");
		ssql.append(" where  tempT.date >= '"+nowDate+"'");
		ssql.append(" GROUP BY tempT.date ");
		String sql = ssql.toString();
		List<Map<String, Object>> qcc;
		try {
			qcc = this.findListMapByCondition(sql, null);
			if(ObjectHelper.isNotEmpty(qcc)){
				return  this.findListMapByCondition(sql, null);
			}else{
				return null;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	};

}
