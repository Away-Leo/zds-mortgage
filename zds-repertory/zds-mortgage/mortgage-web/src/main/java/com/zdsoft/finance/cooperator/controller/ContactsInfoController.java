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
 * 
 * 版权所有：重庆正大华日软件有限公司
 * 
 * @Title: ContactsInfoController.java
 * @ClassName: ContactsInfoController
 * @Description: 联系人信息Controller
 * @author liuwei
 * @date 2017年3月8日 上午10:12:15
 * @version V1.0
 */
@Controller
@RequestMapping("/contactsInfo")
public class ContactsInfoController extends BaseController {

	@Autowired
	ContactsInfoService contactsInfoService;

	@Autowired
	CooperatorTerminalService cooperatorTerminalService;

	/**
	 * 
	 * @Title: initContactsInfo
	 * @Description: 合作方联系人信息列表
	 * @author liuwei
	 * @param terminalId
	 *            终端id
	 * @return
	 */
	@RequestMapping("/initContactsInfo")
	@UriKey(key = "com.zdsoft.finance.cooperator.initContactsInfo")
	public ModelAndView initContactsInfo(String terminalId) {
		Map<String, Object> obj = new HashMap<String, Object>();
		obj.put("partnerId", terminalId);
		return new ModelAndView("/cooperator/contacts_info_list", obj);
	}

	/**
	 * 
	 * @Title: getContactsInfo
	 * @Description: 列表数据展示
	 * @author liuwei
	 * @param request
	 *            请求
	 * @param jsoncallback
	 * @param pageable
	 *            分页信息
	 * @return 处理信息json
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
	 * 
	 * @Title: contactsInfoEdit
	 * @Description: 资方联系人dialog
	 * @author liuwei
	 * @param terminalId
	 *            终端id
	 * @param id
	 *            联系人id
	 * @param operationType
	 *            操作类型
	 * @return ModelAndView
	 */
	@RequestMapping("/edit")
	@UriKey(key = "com.zdsoft.finance.cooperator.contactsInfo.dialog")
	public ModelAndView contactsInfoEdit(String terminalId, String id, String operationType) {
		ModelAndView modelAndView = new ModelAndView("/cooperator/contacts_edit_dialog");
		if (!"add".equals(operationType)) {
			try {
				ContactsInfo info = contactsInfoService.findOne(id);
				ContactsInfoVo infoVo = new ContactsInfoVo(info);
				modelAndView.addObject("infoVo", infoVo);
			} catch (BusinessException e) {
				e.printStackTrace();
				logger.error("查询联系方式失败", e);
			}
		}
		modelAndView.addObject("terminalId", terminalId);
		modelAndView.addObject("operationType", operationType);
		return modelAndView;
	}

	/**
	 * 
	 * @Title: save
	 * @Description: 更新联系人
	 * @author liuwei
	 * @param infoVo
	 *            联系人信息
	 * @return 处理消息msg json
	 */
	@RequestMapping("/save")
	@UriKey(key = "com.zdsoft.finance.cooperator.contactsInfo.save")
	@ResponseBody
	public String save(ContactsInfoVo infoVo) {
		ResponseMsg msg = new ResponseMsg();
		if (!ObjectHelper.isEmpty(infoVo)) {
			// 查询终端信息
			try {
				CooperatorTerminal ter = cooperatorTerminalService.findOne(infoVo.getPartnerId());
				ContactsInfo info = infoVo.toPO();
				info.setPartnerId(ter.getId());
				try {
					// 如果主要联系人为null，则直接将第一个联系人保存为主要联系人
					if (ObjectHelper.isEmpty(ter.getLinkman())) {
						info.setMainContacts(true);
					}
					contactsInfoService.saveOrUpdateContactsInfo(info);
					msg.setMsg("保存成功");
					msg.setResultStatus(ResponseMsg.SUCCESS);
				} catch (Exception e) {
					e.printStackTrace();
					msg.setMsg("保存失败");
					msg.setResultStatus(ResponseMsg.APP_ERROR);
				}
			} catch (BusinessException e) {
				e.printStackTrace();
				logger.error("查询终端信息失败", e);
			}
		} else {
			msg.setMsg("数据为空");
			msg.setResultStatus(ResponseMsg.APP_ERROR);
		}
		return ObjectHelper.objectToJson(msg);
	}

	/**
	 * 
	 * @Title: del
	 * @Description: 逻辑删除联系人信息
	 * @author liuwei
	 * @param jsoncallback
	 * @param id
	 *            联系人id
	 * @return 处理消息msg json
	 */
	@RequestMapping("/del")
	@UriKey(key = "com.zdsoft.finance.cooperator.contactsInfo.del")
	@ResponseBody
	public String del(String jsoncallback, String id) {
		ResponseMsg msg = new ResponseMsg();
		try {
			contactsInfoService.logicDelete(id);
			msg.setMsg("操作成功！");
			msg.setResultStatus(ResponseMsg.SUCCESS);
		} catch (Exception e) {
			msg.setMsg("操作失败！" + e.getMessage());
			msg.setResultStatus(ResponseMsg.SYS_ERROR);
		}
		return ObjectHelper.objectToJson(msg);
	}
}
