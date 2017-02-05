package com.zdsoft.finance.casemanage.appointment.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zdsoft.essential.client.service.CED;
import com.zdsoft.finance.casemanage.appointment.entity.Appointment;
import com.zdsoft.finance.casemanage.appointment.service.AppointmentService;
import com.zdsoft.finance.casemanage.appointment.vo.AppointmentVo;
import com.zdsoft.finance.common.base.QueryObj;
import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.finance.customer.entity.BeforePersonalCustomer;
import com.zdsoft.finance.customer.service.BeforePersonalCustomerService;
import com.zdsoft.finance.marketing.entity.CaseApply;
import com.zdsoft.finance.marketing.service.CaseApplyService;
import com.zdsoft.framework.core.common.dto.ResponseMsg;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.PageRequest;
import com.zdsoft.framework.core.common.util.DateHelper;
import com.zdsoft.framework.core.common.util.ObjectHelper;
import com.zdsoft.framework.core.commweb.annotation.UriKey;
import com.zdsoft.framework.core.commweb.component.BaseController;
import com.zdsoft.framework.cra.annotation.Menu;


/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title:AppointmentController.java
 * @Package:com.zdsoft.finance.casemanage.appointment.controller
 * @Description:案件预约信息控制器
 * @author: xiongpan
 * @date:2017年1月14日 下午7:58:59
 * @version:v1.0
 */
@Controller
@RequestMapping("appointment")
public class AppointmentController extends BaseController{
	
	@Autowired
	private AppointmentService appointmentService;
	
	@Autowired
	private CaseApplyService caseApplySerivce;
	
	@Autowired
	private CED CED;
	
	/**
	 * 案件预约列表入口
	 * 
	 * @return 案件预约列表页面
	 */
	@RequestMapping("/appointmentList")
	@UriKey(key = "com.zdsoft.finance.casemanage.appointmentList")
	@Menu(resource = "com.zdsoft.finance.casemanage.appointmentList", label = "预约列表", group = "project", order = 2)
	public ModelAndView getAppointmentList() {
		return new ModelAndView("/casemanage/appointment/case_appointment_list");
	}
	
	
	/**
	 * 案件预约分页查询列表
	 * 
	 * @param pageable
	 * @param jsoncallback
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/getAppointmentList")
	@UriKey(key = "com.zdsoft.finance.casemanage.appointment.getAppointmentList")
	@ResponseBody
	public String getAppointmentList(HttpServletRequest request, PageRequest pageable, String jsoncallback) throws Exception {
		// 获取查询参数
		@SuppressWarnings("unchecked")
		List<QueryObj> queryObjs = (List<QueryObj>) request.getAttribute("listObj");
		// 获取案件申请配置信息
		Page<Map<String, Object>> caseLimitApply = caseApplySerivce.findPageAppointment(pageable, queryObjs);
		ResponseMsg msg = new ResponseMsg();
		msg.setMsg("列表查询成功");
		msg.setResultStatus(ResponseMsg.SUCCESS);
		msg.setTotal(caseLimitApply.getTotalRows());
		msg.setRows(caseLimitApply.getRecords());
		return ObjectHelper.objectToJson(msg, jsoncallback);
	}
	
	
	/**
	 * 跳转到预约界面
	 * @param id
	 * @return
	 */
	@RequestMapping("/addAppointment")
	@UriKey(key="com.zdsoft.finance.casemanage.appointment.addAppointment")
	public ModelAndView addAppointment(String id, String customerName,String phoneNumber, String email){
		Map<String,Object> appointmentModel = new HashMap<String,Object>();
		CaseApply caseApply;
		try {
			caseApply = caseApplySerivce.findOne(id);
			if(ObjectHelper.isNotEmpty(caseApply.getAppointment())){
				Appointment appointment = caseApply.getAppointment();
				AppointmentVo appointmentVo = new AppointmentVo();
				BeanUtils.copyProperties(appointment, appointmentVo);
				appointmentModel.put("appointmentVo", appointmentVo);
			}
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		appointmentModel.put("caseApplyId", id);
		appointmentModel.put("customerName", customerName);
		appointmentModel.put("phoneNumber", phoneNumber);
		appointmentModel.put("email", email);
		return new ModelAndView("/casemanage/appointment/case_appointment_add", appointmentModel);
				
	}
	/**
	 * 跳转到预约详情界面
	 * @param id
	 * @return
	 * @throws BusinessException 
	 */
	@RequestMapping("/detailsAppointment")
	@UriKey(key="com.zdsoft.finance.casemanage.appointment.detailsAppointment")
	public ModelAndView detailsAppointment(String id) throws BusinessException{
		Map<String,Object> appointmentModel = new HashMap<String,Object>();
		CaseApply caseApply;
		try {
			caseApply = caseApplySerivce.findOne(id);
			if(ObjectHelper.isNotEmpty(caseApply.getAppointment())){
				Appointment appointment = caseApply.getAppointment();
				AppointmentVo appointmentVo = new AppointmentVo();
				BeanUtils.copyProperties(appointment, appointmentVo);
				appointmentModel.put("appointmentVo", appointmentVo);
			}
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		appointmentModel.put("caseApplyId", id);
		return new ModelAndView("/casemanage/appointment/case_appointment_view", appointmentModel);
		
	}
	
	
	/**
	 * 获取接下来的预约案件数(包括今天)
	 * @param jsoncallback
	 * @return
	 */
	@RequestMapping("/getAppointmentPersonCount")
	@UriKey(key = "com.zdsoft.finance.casemanage.appointment.getAppointmentPersonCount")
	@ResponseBody
	public String getAppointmentPersonCount(String jsoncallback){
		ResponseMsg msg = new ResponseMsg();
		Long nowDate = DateHelper.dateToLong(new Date(), "yyyyMMdd");
		List<Map<String,Object>> appointmentPersonCount = appointmentService.queryCaseCount(nowDate);
		if(ObjectHelper.isNotEmpty(appointmentPersonCount)){
			msg.setMsg("列表查询成功");
			msg.setResultStatus(ResponseMsg.SUCCESS);
			msg.setTotal((long) appointmentPersonCount.size());
			msg.setRows(appointmentPersonCount); 
		}else{
			msg.setMsg("目前暂无预约");
		}
		return ObjectHelper.objectToJson(msg, jsoncallback);
	}
	
	
	/**
	 * 获取案件预约客户(即案件的主借人,所有担保人)
	 * @param jsoncallback
	 * @return
	 */
	@RequestMapping("/appointmentClient")
	@UriKey(key = "com.zdsoft.finance.casemanage.appointment.appointmentClient")
	@ResponseBody
	public String appointmentClient(String id,String jsoncallback){
		ResponseMsg msg = new ResponseMsg();
		List<Map<String,Object>> appointmentClients = caseApplySerivce.appointmentClient(id);
		if(ObjectHelper.isNotEmpty(appointmentClients)){
			msg.setMsg("列表查询成功");
			msg.setResultStatus(ResponseMsg.SUCCESS);
		}else{
			msg.setMsg("没查询出客户,请查找原因");
		}
		msg.setRows(appointmentClients);
		return ObjectHelper.objectToJson(msg, jsoncallback);
	}
	
	/**
	 * 保存预约
	 * @param id
	 * @param appointmentVo
	 * @return
	 * @throws BusinessException 
	 */
	@RequestMapping("/saveAppointment")
	@UriKey(key = "com.zdsoft.finance.casemanage.appointment.saveAppointment")
	@ResponseBody
	public ResponseMsg saveAppointment(String id,AppointmentVo appointmentVo) throws BusinessException{
		ResponseMsg msg = new ResponseMsg();
		CaseApply caseApply= caseApplySerivce.findOne(id);
		Appointment appointment = caseApply.getAppointment();
			if(ObjectHelper.isNotEmpty(appointment)){
				BeanUtils.copyProperties(appointmentVo, appointment,new String[]{"id"});
				appointmentService.updateEntity(appointment);
			}else{
				appointment = new Appointment();
				BeanUtils.copyProperties(appointmentVo, appointment,new String[]{"id"});
				appointmentService.saveEntity(appointment);
				caseApply.setAppointmentType("YWDM0051062");
				caseApply.setAppointment(appointment);
			}
			caseApplySerivce.updateEntity(caseApply);
			msg.setResultStatus(ResponseMsg.SUCCESS);
			msg.setMsg("保存成功");
			return msg;
	
	}
	
	

}
