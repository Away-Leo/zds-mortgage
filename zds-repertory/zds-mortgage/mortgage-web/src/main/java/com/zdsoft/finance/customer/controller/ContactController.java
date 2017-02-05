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

import com.zdsoft.finance.common.base.QueryObj;
import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.finance.customer.entity.Contact;
import com.zdsoft.finance.customer.entity.ContactType;
import com.zdsoft.finance.customer.entity.LatestCustomer;
import com.zdsoft.finance.customer.entity.RelationshipType;
import com.zdsoft.finance.customer.service.ContactService;
import com.zdsoft.finance.customer.service.LatestCustomerService;
import com.zdsoft.finance.customer.vo.ContactVo;
import com.zdsoft.framework.core.common.dto.ResponseMsg;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.PageRequest;
import com.zdsoft.framework.core.common.util.ObjectHelper;
import com.zdsoft.framework.core.commweb.annotation.UriKey;
import com.zdsoft.framework.core.commweb.component.BaseController;

/**
 * 联系方式
 * @author zhangchao
 *	2016/12/22
 */
@Controller
@RequestMapping("contact")
public class ContactController extends BaseController {
	
	@Autowired
	private ContactService contactService;
	
	@Autowired
	private LatestCustomerService latestCustomerService;
	
	@Autowired
	private com.zdsoft.essential.client.service.CED CED;
	
	/**
	 * 根据客户id查询相关联系方式
	 * @param request
	 * @param clientId 客户id
	 * @param jsoncallback
	 * @param pageable
	 * @return
	 */
	@RequestMapping("/findByClientId")
	@UriKey(key = "com.zdsoft.finance.contact.findByClientId")
	@ResponseBody
	public String findByClientId(HttpServletRequest request, String clientId ,String jsoncallback, PageRequest pageable){
		// 获取页面封装的查询参数
		@SuppressWarnings("unchecked")
		List<QueryObj> queryObjs = (List<QueryObj>) request.getAttribute("listObj");
		
		ResponseMsg msg = new ResponseMsg();
		if(ObjectHelper.isNotEmpty(queryObjs)&&queryObjs.size()>0){
			// 分页客户联系方式
			Page<Contact> latestCustomerPage = contactService.findByHqlConditions(pageable, queryObjs);
			List<ContactVo> LatestCustomerVos = new ArrayList<ContactVo>();
			for (Contact latestCustomer : latestCustomerPage.getRecords()) {
				ContactVo LatestCustomerVo = new ContactVo(latestCustomer);
				LatestCustomerVo = SimpleCodeToName(LatestCustomerVo);
				LatestCustomerVos.add(LatestCustomerVo);
			}
			msg.setMsg("列表查询成功");
			msg.setResultStatus(ResponseMsg.SUCCESS);
			msg.setTotal(latestCustomerPage.getTotalRows());
			msg.setRows(LatestCustomerVos);
		}else{
			msg.setMsg("列表查询成功");
			msg.setResultStatus(ResponseMsg.SUCCESS);
			msg.setTotal(0L);
			msg.setRows(new ArrayList<ContactVo>());
		}
		return ObjectHelper.objectToJson(msg, jsoncallback);
	}
	
	/**
	 * 保存客户联系方式
	 * @param contactVo 联系方式对象
	 * @param clientId 客户id
	 * @param jsoncallback
	 * @return
	 */
	@RequestMapping("/saveContact")
	@UriKey(key = "com.zdsoft.finance.contact.saveContact")
	@ResponseBody
	public ResponseMsg saveContact(ContactVo contactVo, String clientId, String jsoncallback) {
		Contact contact = null;
		ResponseMsg msg = new ResponseMsg();
		Map<String, Object> workUnitsMap = new HashMap<String, Object>();

		// 将Vo转成Po
		contact = contactVo.toPO();
		
		ContactVo newContactVo = null;
		Contact newContact = new Contact();

		LatestCustomer latestCustomer = null;
		
		//查询
		try {
			latestCustomer = latestCustomerService.findOne(clientId);
		} catch (BusinessException e1) {
			e1.printStackTrace();
			
		}
		contact.setCustomer(latestCustomer);
		// 执行保存
		try {
			newContact = contactService.saveOrUpdateEntity(contact);
			newContactVo = new ContactVo(newContact);
			workUnitsMap.put("newContactVo", newContactVo);
			msg.setResultStatus(ResponseMsg.SUCCESS);
			msg.setOptional(workUnitsMap);
			msg.setMsg("保存成功");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("contact保存失败", e);
			msg.setResultStatus(ResponseMsg.APP_ERROR);
			msg.setMsg("系统内部错误，请查看日志");
		}
		return msg;
	}
	
	/**
	 * 根据id删除联系方式
	 * @param id 联系方式id
	 * @param jsoncallback
	 * @return
	 */
	@RequestMapping("/delContact")
	@UriKey(key = "com.zdsoft.finance.workUnits.delContact")
	@ResponseBody
	public ResponseMsg delContact(String id, String jsoncallback) {
		Contact contact = null;
		ResponseMsg msg = new ResponseMsg();

		//查询
		try {
			contact = contactService.findOne(id);
		} catch (BusinessException e1) {
			e1.printStackTrace();
			logger.error("latestCustomer查询失败", e1);
		}
		
		// 删除
		try {
			contactService.logicDelete(contact);
			msg.setResultStatus(ResponseMsg.SUCCESS);
			msg.setMsg("删除成功");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("contact删除失败", e);
			msg.setResultStatus(ResponseMsg.APP_ERROR);
			msg.setMsg("系统内部错误，请查看日志");
		}
		return msg;
	}

	/**
	 * SimpleCode值转换
	 * @param latestCustomerVo
	 * @return
	 */
	public ContactVo SimpleCodeToName(ContactVo latestCustomerVo){
		if(ObjectHelper.isNotEmpty(latestCustomerVo.getRelationshipType()))
			latestCustomerVo.setRelationshipType(RelationshipType.getName(latestCustomerVo.getRelationshipType()));
		if(ObjectHelper.isNotEmpty(latestCustomerVo.getContactType()))
			latestCustomerVo.setContactType(ContactType.getName(latestCustomerVo.getContactType()));
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
}
