package com.zdsoft.finance.cooperator.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zdsoft.finance.common.base.QueryObj;
import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.finance.cooperator.entity.Capitalist;
import com.zdsoft.finance.cooperator.entity.ContactsInfo;
import com.zdsoft.finance.cooperator.service.CapitalistService;
import com.zdsoft.finance.cooperator.service.ContactsInfoService;
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
 * @Title: CapitalistContactController.java
 * @ClassName: CapitalistContactController
 * @Description: 资方联系方式Controller
 * @author liuwei
 * @date 2017年3月8日 上午9:57:11
 * @version V1.0
 */
@Controller
@RequestMapping("/capitalistContact")
public class CapitalistContactController extends BaseController {

	@Autowired
	ContactsInfoService contactsInfoService;

	@Autowired
	CapitalistService capitalistService;

	/**
	 * 
	 * @Title: initCapitalistContact
	 * @Description: 初始化资方联系方式
	 * @author liuwei
	 * @param capitalistId
	 *            资方id
	 * @param operationType
	 *            操作类型
	 * @return ModelAndView
	 */
	@RequestMapping("/initCapitalistContact")
	@UriKey(key = "com.zdsoft.finance.cooperator.capitalist.initCapitalistContact")
	public ModelAndView initCapitalistContact(String capitalistId, String operationType) {
		ModelAndView model = new ModelAndView("/cooperator/capitalist_contact_list");
		model.addObject("capitalistId", capitalistId);
		model.addObject("operationType", operationType);
		return model;
	}

	/**
	 * 
	 * @Title: getCapitalistContact
	 * @Description: 获取资方联系方式集合
	 * @author liuwei
	 * @param request
	 *            请求
	 * @param jsoncallback
	 * @param pageable
	 *            分页信息
	 * @return 处理消息msgJson
	 */
	@RequestMapping("/getCapitalistContact")
	@UriKey(key = "com.zdsoft.finance.cooperator.capitalist.getCapitalistContact")
	@ResponseBody
	public String getCapitalistContact(HttpServletRequest request, String jsoncallback, PageRequest pageable) {
		// 获取页面封装的查询参数
		@SuppressWarnings("unchecked")
		List<QueryObj> queryObjs = (List<QueryObj>) request.getAttribute("listObj");
		// 分页查询会议
		Page<ContactsInfo> infos = contactsInfoService.findByHqlConditions(pageable, queryObjs);
		List<ContactsInfo> list = infos.getRecords();
		List<ContactsInfoVo> listVo = new ArrayList<ContactsInfoVo>();
		for (ContactsInfo info : list) {
			ContactsInfoVo vo = new ContactsInfoVo(info, null, new String[] { "contactType" });
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
	 * @Title: dialog
	 * @Description: 联系人编辑
	 * @author liuwei
	 * @param capitalistId
	 *            资方id
	 * @param id
	 *            联系方式id
	 * @param operationType
	 *            操作类型
	 * @return ModelAndView
	 */
	@RequestMapping("/dialog")
	@UriKey(key = "com.zdsoft.finance.cooperator.capitalist.contact.dialog")
	public ModelAndView dialog(String capitalistId, String id, String operationType) {
		ModelAndView modelAndView = new ModelAndView("/cooperator/capitalist_contact_dialog");
		if ("mod".equals(operationType) || "view".equals(operationType)) {

			try {
				// 查询联系人资料信息
				ContactsInfo info = contactsInfoService.findOne(id);
				ContactsInfoVo infoVo = new ContactsInfoVo(info);
				modelAndView.addObject("infoVo", infoVo);
			} catch (BusinessException e) {
				e.printStackTrace();
				logger.error("根据联系方式id查询资方联系人资料失败", e);
			}

		}
		modelAndView.addObject("capitalistId", capitalistId);
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
	 * @return 处理消息msgjson
	 */
	@RequestMapping("/save")
	@UriKey(key = "com.zdsoft.finance.cooperator.capitalist.contact.save")
	@ResponseBody
	public String save(ContactsInfoVo infoVo) {
		ResponseMsg msg = new ResponseMsg();

		// 判断联系人vo是否存在
		if (!ObjectHelper.isEmpty(infoVo)) {
			try {
				// 判断是否传入id
				if (ObjectHelper.isEmpty(infoVo.getId())) {
					// id不存在，则新增
					Capitalist ter = capitalistService.findOne(infoVo.getPartnerId());
					ContactsInfo info = infoVo.toPO();
					info.setPartnerId(ter.getId());
					contactsInfoService.saveEntity(info);
					msg.setMsg("保存成功！");
					msg.setResultStatus(ResponseMsg.SUCCESS);
				} else {
					// id存在，则修改
					ContactsInfo info = contactsInfoService.findOne(infoVo.getId());
					info.setLinkman(infoVo.getLinkman());
					info.setContactNumber(infoVo.getContactNumber());
					info.setContactType(infoVo.getContactType());
					Capitalist ter = capitalistService.findOne(infoVo.getPartnerId());
					info.setPartnerId(ter.getId());
					contactsInfoService.updateEntity(info);
					msg.setMsg("更新成功！");
					msg.setResultStatus(ResponseMsg.SUCCESS);
				}
			} catch (Exception e) {
				e.printStackTrace();
				logger.error("根据id查询资方信息失败", e);
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
	@UriKey(key = "com.zdsoft.finance.cooperator.capitalist.contact.del")
	@ResponseBody
	public String del(String jsoncallback, String id) {
		ResponseMsg msg = new ResponseMsg();
		try {

			// 执行逻辑删除信息
			contactsInfoService.logicDelete(id);
			msg.setMsg("操作成功！");
			msg.setResultStatus(ResponseMsg.SUCCESS);
		} catch (Exception e) {
			logger.error("逻辑删除联系方式失败", e);
			msg.setMsg("操作失败！" + e.getMessage());
			msg.setResultStatus(ResponseMsg.SYS_ERROR);
		}
		return ObjectHelper.objectToJson(msg);
	}
}
