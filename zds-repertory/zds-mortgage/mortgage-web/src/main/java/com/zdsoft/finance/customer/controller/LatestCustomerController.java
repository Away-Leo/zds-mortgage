package com.zdsoft.finance.customer.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zdsoft.finance.common.base.QueryObj;
import com.zdsoft.finance.customer.entity.ContactType;
import com.zdsoft.finance.customer.entity.LatestCustomer;
import com.zdsoft.finance.customer.entity.RelationshipType;
import com.zdsoft.finance.customer.service.LatestCustomerService;
import com.zdsoft.finance.customer.vo.LatestCustomerVo;
import com.zdsoft.finance.customer.vo.RelationshipTypeVo;
import com.zdsoft.framework.core.common.dto.ResponseMsg;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.PageRequest;
import com.zdsoft.framework.core.common.util.ObjectHelper;
import com.zdsoft.framework.core.commweb.annotation.UriKey;
import com.zdsoft.framework.core.commweb.component.BaseController;
import com.zdsoft.framework.cra.annotation.Menu;

/**
 * 最新客户
 * @author zhangchao
 *	2016/12/22
 */
@Controller
@RequestMapping("latestCustomer")
public class LatestCustomerController extends BaseController {

	@Autowired
	private LatestCustomerService latestCustomerService;
	
	@Autowired
	private com.zdsoft.essential.client.service.CED CED;
	
	/**
	 * 客户管理注册菜单
	 * @return 
	 */
	@RequestMapping("/initCustomer")
	@UriKey(key = "com.zdsoft.finance.customer.initCustomer")
	//@Menu(resource = "com.zdsoft.finance.customer.initCustomer", label = "客户信息管理", group = "customer", order = 1)
	public ModelAndView initCreditEntrust() {
		return new ModelAndView("/customer/customer_list");
	}
	
	/**
	 * 客户新增
	 * @return
	 */
	@RequestMapping("/addCustomer")
	@UriKey(key = "com.zdsoft.finance.customer.addCustomer")
	@ResponseBody
	public ModelAndView addCustomer() {
		return new ModelAndView("/customer/customer_add");
	}
	
	/**
	 * 根据客户id查看客户信息
	 * @param id 客户id
	 * @param jsoncallback
	 * @return
	 */
	@RequestMapping("/findCustomerById")
	@UriKey(key = "com.zdsoft.finance.customer.findCustomerById")
	@ResponseBody
	public ModelAndView findCustomerById(String id, String jsoncallback) {
		Map<String, Object> optional = new HashMap<String, Object>();
		LatestCustomer latestCustomer = null;
		LatestCustomerVo latestCustomerVo = null;

		// 查询
		try {
			latestCustomer = latestCustomerService.findOne(id);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("latestCustomer查询失败", e);
		}
		
		//客户对象数据组装
		if(ObjectHelper.isNotEmpty(latestCustomer)){
			latestCustomerVo = new LatestCustomerVo(latestCustomer);
			latestCustomerVo = SimpleCodeToName(latestCustomerVo);
			String liveCode=latestCustomerVo.getLiveProvince()+","+latestCustomerVo.getLiveCity()+","+latestCustomerVo.getLiveDistrict();
			String domicileCode=latestCustomerVo.getDomicileProvince()+","+latestCustomerVo.getDomicileCity()+","+latestCustomerVo.getDomicileDistrict();
			latestCustomerVo.setLiveCode(liveCode);
			latestCustomerVo.setDomicileCode(domicileCode);
		}else{
			logger.error("latestCustomer查询结果为空");
		}
		
		optional.put("latestCustomerVo", latestCustomerVo);
		return new ModelAndView("/customer/customer_view", optional);
	}
	
	/**
	 * 客户信息编辑
	 * @param id 客户id
	 * @param jsoncallback
	 * @return
	 */
	@RequestMapping("/editCustomer")
	@UriKey(key = "com.zdsoft.finance.customer.editCustomer")
	@ResponseBody
	public ModelAndView editCustomer(String id, String jsoncallback) {
		Map<String, Object> optional = new HashMap<String, Object>();
		LatestCustomer latestCustomer = null;
		LatestCustomerVo latestCustomerVo = null;

		// 查询
		try {
			latestCustomer = latestCustomerService.findOne(id);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("latestCustomer查询失败", e);
		}
		
		//客户对象数据组装
		if(ObjectHelper.isNotEmpty(latestCustomer)){
			latestCustomerVo = new LatestCustomerVo(latestCustomer);
			String liveCode=latestCustomerVo.getLiveProvince()+","+latestCustomerVo.getLiveCity()+","+latestCustomerVo.getLiveDistrict();
			String domicileCode=latestCustomerVo.getDomicileProvince()+","+latestCustomerVo.getDomicileCity()+","+latestCustomerVo.getDomicileDistrict();
			latestCustomerVo.setLiveCode(liveCode);
			latestCustomerVo.setDomicileCode(domicileCode);
		}else{
			logger.error("latestCustomer查询结果为空");
		}
		
		optional.put("latestCustomerVo", latestCustomerVo);
		return new ModelAndView("/customer/customer_edit", optional);
	}
	
	/**
	 * 客户管理查询列表
	 * @param request 
	 * @param jsoncallback
	 * @param pageable
	 * @return
	 */
	@RequestMapping("/getlatestCustomers")
	@UriKey(key = "com.zdsoft.finance.customer.getlatestCustomers")
	@ResponseBody
	public String getlatestCustomers(HttpServletRequest request, String jsoncallback, PageRequest pageable) {

		// 获取页面封装的查询参数 
		@SuppressWarnings("unchecked")
		List<QueryObj> queryObjs = (List<QueryObj>) request.getAttribute("listObj");

		// 分页客户信息管理
		Page<LatestCustomer> latestCustomerPage = latestCustomerService.findByHqlConditions(pageable, queryObjs);
		List<LatestCustomerVo> LatestCustomerVos = new ArrayList<LatestCustomerVo>();
		for (LatestCustomer latestCustomer : latestCustomerPage.getRecords()) {
			LatestCustomerVo LatestCustomerVo = new LatestCustomerVo(latestCustomer);
			LatestCustomerVo = SimpleCodeToName(LatestCustomerVo);
			LatestCustomerVos.add(LatestCustomerVo);
		}
		ResponseMsg msg = new ResponseMsg();
		msg.setMsg("列表查询成功");
		msg.setResultStatus(ResponseMsg.SUCCESS);
		msg.setTotal(latestCustomerPage.getTotalRows());
		msg.setRows(LatestCustomerVos);

		return ObjectHelper.objectToJson(msg, jsoncallback);
	}
	
	/**
	 * 客户信息保存
	 * @param latestCustomerVo 客户信息
	 * @return
	 */
	@RequestMapping("/saveCustomer")
	@UriKey(key = "com.zdsoft.finance.customer.saveCustomer")
	@ResponseBody
	public ResponseMsg saveCustomer(LatestCustomerVo latestCustomerVo) {
		LatestCustomer latestCustomer = null;
		ResponseMsg msg = new ResponseMsg();
		Map<String, Object> latestCustomerMap = new HashMap<String, Object>();
		
		//给家庭地址和户籍地址赋值
		String[]  liveCode=latestCustomerVo.getLiveCode().split(",");
		String[]  domicileCode=latestCustomerVo.getDomicileCode().split(",");
		latestCustomerVo.setLiveProvince(liveCode[0]);
		latestCustomerVo.setLiveCity(liveCode[1]);
		latestCustomerVo.setLiveDistrict(liveCode[2]);
		latestCustomerVo.setDomicileProvince(domicileCode[0]);
		latestCustomerVo.setDomicileCity(domicileCode[1]);
		latestCustomerVo.setDomicileDistrict(domicileCode[2]);

		// 将Vo转成Po
		latestCustomer = latestCustomerVo.toPO();
		
		LatestCustomerVo newLatestCustomerVo = null;
		LatestCustomer newLatestCustomer = new LatestCustomer();

		// 执行保存
		try {
			newLatestCustomer = latestCustomerService.saveOrUpdateEntity(latestCustomer);
			newLatestCustomerVo = new LatestCustomerVo(newLatestCustomer);
			latestCustomerMap.put("newLatestCustomerVo", newLatestCustomerVo);
			msg.setResultStatus(ResponseMsg.SUCCESS);
			msg.setOptional(latestCustomerMap);
			msg.setMsg("保存成功");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("latestCustomer保存失败", e);
			msg.setResultStatus(ResponseMsg.APP_ERROR);
			msg.setMsg("系统内部错误，请查看日志");
		}
		return msg;
	}
	
	/**
	 * 配偶信息保存
	 * @param latestCustomerVo 客户信息
	 * @param customerId 主借人客户id
	 * @return
	 */
	@RequestMapping("/saveMarital")
	@UriKey(key = "com.zdsoft.finance.customer.saveMarital")
	@ResponseBody
	public ResponseMsg saveMarital(LatestCustomerVo latestCustomerVo, String customerId) {
		LatestCustomer latestCustomer = null;
		ResponseMsg msg = new ResponseMsg();
		Map<String, Object> latestCustomerMap = new HashMap<String, Object>();

		// 将Vo转成Po
		latestCustomer = latestCustomerVo.toPO();
		
		//返回页面的值
		LatestCustomerVo newLatestCustomerVo = null;
		LatestCustomer newLatestCustomer = new LatestCustomer();

		// 执行保存
		try {
			//查询主借人信息
			LatestCustomer customer = latestCustomerService.findOne(customerId);
			
			//将主借人的id赋值给配偶的配偶id字段
			latestCustomer.setClientId(customerId);
			newLatestCustomer = latestCustomerService.saveOrUpdateEntity(latestCustomer);
			
			//将配偶id赋值给主借人的配偶id字段，然后保存
			customer.setClientId(newLatestCustomer.getId());
			latestCustomerService.saveOrUpdateEntity(customer);
			
			//返回页面值设置
			newLatestCustomerVo = new LatestCustomerVo(newLatestCustomer);
			latestCustomerMap.put("newLatestCustomerVo", newLatestCustomerVo);
			msg.setResultStatus(ResponseMsg.SUCCESS);
			msg.setOptional(latestCustomerMap);
			msg.setMsg("保存成功");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("latestCustomer保存失败", e);
			msg.setResultStatus(ResponseMsg.APP_ERROR);
			msg.setMsg("系统内部错误，请查看日志");
		}
		return msg;
	}
	
	/**
	 * 更新客户信息
	 * @param latestCustomerVo 客户信息
	 * @param jsoncallback
	 * @return
	 */
	@RequestMapping("/updateCustomer")
	@UriKey(key = "com.zdsoft.finance.customer.updateCustomer")
	@ResponseBody
	public ResponseMsg updateCustomer(LatestCustomerVo latestCustomerVo, String jsoncallback) {
		LatestCustomer latestCustomer = null;
		ResponseMsg msg = new ResponseMsg();
		
		//给家庭地址和户籍地址赋值
		String[]  liveCode=latestCustomerVo.getLiveCode().split(",");
		String[]  domicileCode=latestCustomerVo.getDomicileCode().split(",");
		latestCustomerVo.setLiveProvince(liveCode[0]);
		latestCustomerVo.setLiveCity(liveCode[1]);
		latestCustomerVo.setLiveDistrict(liveCode[2]);
		latestCustomerVo.setDomicileProvince(domicileCode[0]);
		latestCustomerVo.setDomicileCity(domicileCode[1]);
		latestCustomerVo.setDomicileDistrict(domicileCode[2]);

		// 将Vo转成Po
		latestCustomer = latestCustomerVo.toPO();

		// 执行保存
		try {
			latestCustomerService.saveOrUpdateEntity(latestCustomer);
			msg.setResultStatus(ResponseMsg.SUCCESS);
			msg.setMsg("保存成功");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("latestCustomer保存失败", e);
			msg.setResultStatus(ResponseMsg.APP_ERROR);
			msg.setMsg("系统内部错误，请查看日志");
		}
		return msg;
	}
	
	/**
	 * 根据客户姓名，证件类型和证件编号判断客户是否已存在，如果存在则页面赋值
	 * @param clientNm 客户姓名
	 * @param credentiaType 证件类型
	 * @param credentialNo 证件编号
	 * @return
	 */
	@RequestMapping("/findByCredentialNo")
	@UriKey(key = "com.zdsoft.finance.customer.findByCredentialNo")
	@ResponseBody
	public ResponseMsg findByCredentialNo(String clientNm, String credentiaType, String credentialNo) {
		LatestCustomer latestCustomer = null;
		LatestCustomerVo latestCustomerVo = null;
		ResponseMsg msg = new ResponseMsg();
		Map<String, Object> latestCustomerMap = new HashMap<String, Object>();

		//根据条件查询
		try {
			latestCustomer = latestCustomerService.findByCredentialNo(clientNm, credentiaType, credentialNo);
		} catch (Exception e) {
			e.printStackTrace();
			msg.setResultStatus(ResponseMsg.APP_ERROR);
			msg.setMsg("查询出错，请看日志");
			logger.error("latestCustomer查询失败", e);
		}
		
		//客户数据组装
		if(ObjectHelper.isNotEmpty(latestCustomer)){
			latestCustomerVo = new LatestCustomerVo(latestCustomer);
			latestCustomerMap.put("latestCustomerVo", latestCustomerVo);
			String liveCode=latestCustomerVo.getLiveProvince()+","+latestCustomerVo.getLiveCity()+","+latestCustomerVo.getLiveDistrict();
			String domicileCode=latestCustomerVo.getDomicileProvince()+","+latestCustomerVo.getDomicileCity()+","+latestCustomerVo.getDomicileDistrict();
			latestCustomerVo.setLiveCode(liveCode);
			latestCustomerVo.setDomicileCode(domicileCode);
			msg.setResultStatus(ResponseMsg.SUCCESS);
			msg.setMsg("查询成功");
			msg.setOptional(latestCustomerMap);
		}else{
			msg.setResultStatus(ResponseMsg.APP_ERROR);
			msg.setMsg("查询结果为空");
			logger.error("latestCustomer查询结果为空");
		}
		return msg;
	}
	
	/**
	 * 根据证件类型和证件编号判断客户是否存在，如果存在则页面赋值
	 * @param credentiaType 证件类型
	 * @param credentialNo 证件编号
	 * @return
	 */
	@RequestMapping("/findByCredentiaTypeAndCredentialNo")
	@UriKey(key = "com.zdsoft.finance.customer.findByCredentiaTypeAndCredentialNo")
	@ResponseBody
	public ResponseMsg findByCredentiaTypeAndCredentialNo(String credentiaType, String credentialNo) {
		LatestCustomer latestCustomer = null;
		LatestCustomerVo latestCustomerVo = null;
		ResponseMsg msg = new ResponseMsg();
		Map<String, Object> latestCustomerMap = new HashMap<String, Object>();

		//根据条件查询
		try {
			latestCustomer = latestCustomerService.findByCredentiaTypeAndCredentialNo(credentiaType, credentialNo);
		} catch (Exception e) {
			e.printStackTrace();
			msg.setResultStatus(ResponseMsg.APP_ERROR);
			msg.setMsg("查询出错，请看日志");
			logger.error("latestCustomer查询失败", e);
		}
		if(ObjectHelper.isNotEmpty(latestCustomer)){
			latestCustomerVo = new LatestCustomerVo(latestCustomer);
			String liveCode=latestCustomerVo.getLiveProvince()+","+latestCustomerVo.getLiveCity()+","+latestCustomerVo.getLiveDistrict();
			String domicileCode=latestCustomerVo.getDomicileProvince()+","+latestCustomerVo.getDomicileCity()+","+latestCustomerVo.getDomicileDistrict();
			latestCustomerVo.setLiveCode(liveCode);
			latestCustomerVo.setDomicileCode(domicileCode);
			latestCustomerMap.put("latestCustomerVo", latestCustomerVo);
			msg.setResultStatus(ResponseMsg.SUCCESS);
			msg.setMsg("该证件号已存在，页面赋值");
			msg.setOptional(latestCustomerMap);
		}else{
			msg.setResultStatus(ResponseMsg.APP_ERROR);
			msg.setMsg("证件号不存在，可新增");
		}
		return msg;
	}
	
	/**
	 * 根据客户id查询配偶信息（显示页面）
	 * @param clientId 主借人客户id
	 * @return
	 */
	@RequestMapping("/findMaritalCustomerByClientId")
	@UriKey(key = "com.zdsoft.finance.customer.findMaritalCustomerByClientId")
	@ResponseBody
	public ResponseMsg findMaritalCustomerByClientId(String clientId) {
		LatestCustomer latestCustomer = null;
		LatestCustomerVo latestCustomerVo = null;
		ResponseMsg msg = new ResponseMsg();
		Map<String, Object> latestCustomerMap = new HashMap<String, Object>();

		//根据条件查询
		try {
			latestCustomer = latestCustomerService.findByfindByClientId(clientId);
		} catch (Exception e) {
			e.printStackTrace();
			msg.setResultStatus(ResponseMsg.APP_ERROR);
			msg.setMsg("查询出错，请看日志");
			logger.error("latestCustomer查询失败", e);
		}
		if(ObjectHelper.isNotEmpty(latestCustomer)){
			latestCustomerVo = new LatestCustomerVo(latestCustomer);
			//值转换
			latestCustomerVo = SimpleCodeToName(latestCustomerVo);
			latestCustomerMap.put("maritalCustomerVo", latestCustomerVo);
			msg.setResultStatus(ResponseMsg.SUCCESS);
			msg.setMsg("查询配偶信息成功");
			msg.setOptional(latestCustomerMap);
		}else{
			msg.setResultStatus(ResponseMsg.APP_ERROR);
			msg.setMsg("配偶信息不存在");
		}
		return msg;
	}
	
	/**
	 * 根据客户id查询配偶信息（编辑页面）
	 * @param clientId 主借人客户id
	 * @return
	 */
	@RequestMapping("/findMaritalByClientId")
	@UriKey(key = "com.zdsoft.finance.customer.findMaritalByClientId")
	@ResponseBody
	public ResponseMsg findMaritalByClientId(String clientId) {
		LatestCustomer latestCustomer = null;
		LatestCustomerVo latestCustomerVo = null;
		ResponseMsg msg = new ResponseMsg();
		Map<String, Object> latestCustomerMap = new HashMap<String, Object>();

		//根据条件查询
		try {
			latestCustomer = latestCustomerService.findByfindByClientId(clientId);
		} catch (Exception e) {
			e.printStackTrace();
			msg.setResultStatus(ResponseMsg.APP_ERROR);
			msg.setMsg("查询出错，请看日志");
			logger.error("latestCustomer查询失败", e);
		}
		if(ObjectHelper.isNotEmpty(latestCustomer)){
			latestCustomerVo = new LatestCustomerVo(latestCustomer);
			latestCustomerMap.put("maritalCustomerVo", latestCustomerVo);
			msg.setResultStatus(ResponseMsg.SUCCESS);
			msg.setMsg("查询配偶信息成功");
			msg.setOptional(latestCustomerMap);
		}else{
			msg.setResultStatus(ResponseMsg.APP_ERROR);
			msg.setMsg("配偶信息不存在");
		}
		return msg;
	}
	
	/**
	 * 根据客户id查询相关客户的姓名（用于联系方式和工作单位下拉框）
	 * @param clientId 客户id
	 * @param jsoncallback
	 * @return
	 */
	@RequestMapping("/findClientNameByClientId")
	@UriKey(key = "com.zdsoft.finance.customer.findClientNameByClientId")
	@ResponseBody
	public String findClientNameByClientId(String clientId, String jsoncallback) {
		List<LatestCustomer> latestCustomers = null;
		List<Map<String, String>> simpleCode = new ArrayList<Map<String, String>>();

		//根据条件查询
		try {
			latestCustomers = latestCustomerService.findClientNameByClientId(clientId);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("latestCustomer查询失败", e);
		}
		for (LatestCustomer latestCustomer : latestCustomers) {
			Map<String, String> latestCustomerMap = new HashMap<String, String>();
			LatestCustomerVo latestCustomerVo = new LatestCustomerVo(latestCustomer);
			latestCustomerMap.put("fullcode", latestCustomerVo.getClientNm());
			latestCustomerMap.put("name", latestCustomerVo.getClientNm());
			
			simpleCode.add(latestCustomerMap);
		}
		return ObjectHelper.objectToJson(simpleCode, jsoncallback);
	}
	
	/**
	 * SimpleCode值转换
	 * @param latestCustomerVo
	 * @return
	 */
	public LatestCustomerVo SimpleCodeToName(LatestCustomerVo latestCustomerVo){
		try {
			if(ObjectHelper.isNotEmpty(latestCustomerVo.getCredentiaType())){
				latestCustomerVo.setCredentiaType(SimpleCodeTurnOn(latestCustomerVo.getCredentiaType()));
			}
			
			if(ObjectHelper.isNotEmpty(latestCustomerVo.getMarital())){
				latestCustomerVo.setMarital(SimpleCodeTurnOn(latestCustomerVo.getMarital()));
			}
			
			if(ObjectHelper.isNotEmpty(latestCustomerVo.getCareerType())){
				latestCustomerVo.setCareerType(SimpleCodeTurnOn(latestCustomerVo.getCareerType()));
			}
			
			if(ObjectHelper.isNotEmpty(latestCustomerVo.getDegree())){
				latestCustomerVo.setDegree(SimpleCodeTurnOn(latestCustomerVo.getDegree()));
			}
			
			if(ObjectHelper.isNotEmpty(latestCustomerVo.getGender())){
				latestCustomerVo.setGender(SimpleCodeTurnOn(latestCustomerVo.getGender()));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return latestCustomerVo;
	}
	
	public String SimpleCodeTurnOn(String fullCode){
		String fullName = "";
		try {
			fullName = CED.loadSimpleCodeNameByFullCode(fullCode);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return fullName;
	}
	
	/**
	 * 联系类型下拉框值
	 * @param jsoncallback
	 * @return
	 */
	@RequestMapping(value="/findRelationshipType")
	@UriKey(key="com.zdsoft.finance.customer.findRelationshipType")
	@ResponseBody
	public String findRelationshipType(String jsoncallback){
		RelationshipType[] events = RelationshipType.values();
		String event = null;
		String resultStr = null;
		Map<String, Object> map = new HashMap<String, Object>();
		try{
			List<RelationshipTypeVo> eventsVos = new ArrayList<RelationshipTypeVo>();
			for(int i = 0 , ele = RelationshipType.values().length ; i < ele ; i ++){
				RelationshipTypeVo eventVo = new RelationshipTypeVo();
				event = events[i].value;
				eventVo.setEventsCd(event);
				eventVo.setEventsName(RelationshipType.getName(event));
				eventsVos.add(eventVo);
			}
			resultStr = ObjectHelper.objectToJson(eventsVos, jsoncallback);
			map.put("success", true);
		}catch(Exception e){
			map.put("false", false);
		}
		return resultStr;
	}
	
	/**
	 * 关系下拉框值
	 * @param jsoncallback
	 * @return
	 */
	@RequestMapping(value="/findContactType")
	@UriKey(key="com.zdsoft.finance.customer.findContactType")
	@ResponseBody
	public String findContactType(String jsoncallback){
		ContactType[] events = ContactType.values();
		String event = null;
		String resultStr = null;
		Map<String, Object> map = new HashMap<String, Object>();
		try{
			List<RelationshipTypeVo> eventsVos = new ArrayList<RelationshipTypeVo>();
			for(int i = 0 , ele = ContactType.values().length ; i < ele ; i ++){
				RelationshipTypeVo eventVo = new RelationshipTypeVo();
				event = events[i].value;
				eventVo.setEventsCd(event);
				eventVo.setEventsName(ContactType.getName(event));
				eventsVos.add(eventVo);
			}
			resultStr = ObjectHelper.objectToJson(eventsVos, jsoncallback);
			map.put("success", true);
		}catch(Exception e){
			map.put("false", false);
		}
		return resultStr;
	}
	
}
