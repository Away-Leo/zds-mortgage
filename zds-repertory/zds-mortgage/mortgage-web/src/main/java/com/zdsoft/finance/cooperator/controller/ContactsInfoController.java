package com.zdsoft.finance.cooperator.controller;

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
import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.finance.cooperator.entity.ContactsInfo;
import com.zdsoft.finance.cooperator.entity.CooperatorTerminal;
import com.zdsoft.finance.cooperator.service.ContactsInfoService;
import com.zdsoft.finance.cooperator.service.CooperatorTerminalService;
import com.zdsoft.finance.cooperator.vo.ContactsInfoVo;
import com.zdsoft.framework.core.common.dto.ResponseMsg;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.PageRequest;
import com.zdsoft.framework.core.common.util.ObjectHelper;
import com.zdsoft.framework.core.commweb.annotation.UriKey;
import com.zdsoft.framework.core.commweb.component.BaseController;
/**
 * 联系人信息
 * @author Hisa
 *
 */
@Controller
@RequestMapping("/contactsInfo")
public class ContactsInfoController extends BaseController {

	@Autowired
	ContactsInfoService contactsInfoService;
	
	@Autowired
	CooperatorTerminalService cooperatorTerminalService;
	
	/**
	 * 合作方联系人信息列表
	 * @return
	 */
	@RequestMapping("/initContactsInfo")
	@UriKey(key = "com.zdsoft.finance.cooperator.initContactsInfo")
	public ModelAndView initContactsInfo(String terminalId) {
		Map<String, Object> obj = new HashMap<String, Object>();
		obj.put("terminalId", terminalId);
		return new ModelAndView("/cooperator/contacts_info_list",obj);
	}
	/**
	 * 列表数据展示
	 * @param request
	 * @param jsoncallback
	 * @param pageable
	 * @return
	 */
	@RequestMapping("/getContactsInfo")
	@UriKey(key = "com.zdsoft.finance.cooperator.getContactsInfo")
	@ResponseBody
	public String getContactsInfo(HttpServletRequest request, String jsoncallback, PageRequest pageable) {

		// 获取页面封装的查询参数
		@SuppressWarnings("unchecked")
		List<QueryObj> queryObjs = (List<QueryObj>) request.getAttribute("listObj");
		// 分页查询会议
		Page<ContactsInfo> infos = contactsInfoService.findByHqlConditions(pageable, queryObjs);
		List<ContactsInfo> list = infos.getRecords();
		List<ContactsInfoVo> listVo = new ArrayList<ContactsInfoVo>();
		for (ContactsInfo info : list) {
			ContactsInfoVo vo = new ContactsInfoVo(info);
			listVo.add(vo);
		}
		ResponseMsg msg = new ResponseMsg();
		msg.setMsg("列表查询成功");
		msg.setResultStatus(ResponseMsg.SUCCESS);
		msg.setTotal(infos.getTotalRows());
		msg.setRows(listVo);
		return ObjectHelper.objectToJson(msg, jsoncallback);
	}

	/**
	 * 编辑
	 * 
	 * @return
	 * @throws BusinessException
	 */
	@RequestMapping("/edit")
	@UriKey(key = "com.zdsoft.finance.cooperator.contactsInfo.dialog")
	public ModelAndView contactsInfoEdit(String terminalId, String id, String operationType)
			throws BusinessException {
		ModelAndView modelAndView = new ModelAndView("/cooperator/contacts_edit_dialog");
		if (!"add".equals(operationType)) {
			ContactsInfo info = contactsInfoService.findOne(id);
			ContactsInfoVo infoVo = new ContactsInfoVo(info);
			modelAndView.addObject("infoVo", infoVo);
		}
		modelAndView.addObject("terminalId", terminalId);
		modelAndView.addObject("operationType", operationType);
		return modelAndView;
	}
	/**
	 * 更新联系人
	 * @param jsoncallback
	 * @param infoVo
	 * @return
	 * @throws BusinessException
	 */
	@RequestMapping("/save")
	@UriKey(key = "com.zdsoft.finance.cooperator.contactsInfo.save")
	@ResponseBody
	public String save( ContactsInfoVo infoVo ) throws BusinessException {
		ResponseMsg msg = new ResponseMsg();
		if(!ObjectHelper.isEmpty(infoVo)){
			if(ObjectHelper.isEmpty(infoVo.getId())){
				 ContactsInfo info = infoVo.toPO();
				 CooperatorTerminal ter = cooperatorTerminalService.findOne(infoVo.getTerminalId());
				 info.setCooperatorTerminal(ter);
				contactsInfoService.saveEntity(info);
				msg.setMsg("保存成功！");
				msg.setResultStatus(ResponseMsg.SUCCESS);
			}else{
				ContactsInfo info =contactsInfoService.findOne(infoVo.getId());
				info.setContactName(infoVo.getContactName());
				info.setContactTelNumber(infoVo.getContactTelNumber());
				info.setRole(infoVo.getRole());
				CooperatorTerminal ter = cooperatorTerminalService.findOne(infoVo.getTerminalId());
				info.setCooperatorTerminal(ter);
				contactsInfoService.updateEntity(info);
				msg.setMsg("更新成功！");
				msg.setResultStatus(ResponseMsg.SUCCESS);
			}
		}else{
			msg.setMsg("数据为空");
			msg.setResultStatus(ResponseMsg.APP_ERROR);
		}
		return ObjectHelper.objectToJson(msg);
	}
	/**
	 * 删除
	 * @param jsoncallback
	 * @return
	 * @throws BusinessException 
	 */
	@RequestMapping("/del")
	@UriKey(key = "com.zdsoft.finance.cooperator.contactsInfo.del")
	@ResponseBody
	public String del(String jsoncallback,String id) throws BusinessException {
		ResponseMsg msg = new ResponseMsg();
		try {
			contactsInfoService.logicDelete(id);
			msg.setMsg("操作成功！");
			msg.setResultStatus(ResponseMsg.SUCCESS);
		} catch (Exception e) {
			msg.setMsg("操作失败！"+e.getMessage());
			msg.setResultStatus(ResponseMsg.SYS_ERROR);
		}
		return ObjectHelper.objectToJson(msg);
	}
}