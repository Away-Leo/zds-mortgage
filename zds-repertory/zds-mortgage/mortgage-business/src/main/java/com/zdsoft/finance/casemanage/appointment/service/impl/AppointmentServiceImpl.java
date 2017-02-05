package com.zdsoft.finance.casemanage.appointment.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zdsoft.finance.base.service.impl.BaseServiceImpl;
import com.zdsoft.finance.casemanage.appointment.entity.Appointment;
import com.zdsoft.finance.casemanage.appointment.repository.AppointmentRepository;
import com.zdsoft.finance.casemanage.appointment.service.AppointmentService;
import com.zdsoft.finance.common.base.CustomRepository;


/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title:AppointmentServiceImpl.java
 * @Package:com.zdsoft.finance.casemanage.appointment.service.impl
 * @Description:案件预约信息服务接口实现
 * @author: xiongpan
 * @date:2017年1月14日 下午7:50:40
 * @version:v1.0
 */
@Service
public class AppointmentServiceImpl extends BaseServiceImpl<Appointment, CustomRepository<Appointment, String>>
		implements AppointmentService {
	
	@Autowired
	private AppointmentRepository appointmentRepository;

	/**
	 * 查询目前的案件预约情况
	 */
	@Override
	public List<Map<String, Object>> queryCaseCount(Long nowDate) {
		return appointmentRepository.queryCaseCount(nowDate);
	}

}
