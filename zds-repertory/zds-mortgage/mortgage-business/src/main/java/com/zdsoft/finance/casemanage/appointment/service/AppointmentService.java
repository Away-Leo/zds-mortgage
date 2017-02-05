package com.zdsoft.finance.casemanage.appointment.service;

import java.util.List;
import java.util.Map;

import com.zdsoft.finance.base.service.BaseService;
import com.zdsoft.finance.casemanage.appointment.entity.Appointment;


/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title:AppointmentService.java
 * @Package:com.zdsoft.finance.casemanage.appointment.service
 * @Description:案件预约信息服务接口
 * @author: xiongpan
 * @date:2017年1月14日 下午9:15:42
 * @version:v1.0
 */
public interface AppointmentService extends BaseService<Appointment>{

	/**
	 * 查询目前的案件预约情况
	 * @return
	 */
	List<Map<String, Object>> queryCaseCount(Long nowDate);

}
