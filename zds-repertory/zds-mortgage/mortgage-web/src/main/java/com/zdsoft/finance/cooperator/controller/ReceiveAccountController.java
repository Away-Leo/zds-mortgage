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
import com.zdsoft.finance.cooperator.entity.CooperatorTerminal;
import com.zdsoft.finance.cooperator.entity.ReceiveAccount;
import com.zdsoft.finance.cooperator.service.CooperatorTerminalService;
import com.zdsoft.finance.cooperator.service.ReceiveAccountService;
import com.zdsoft.finance.cooperator.vo.ReceiveAccountVo;
import com.zdsoft.framework.core.common.dto.ResponseMsg;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.PageRequest;
import com.zdsoft.framework.core.common.util.ObjectHelper;
import com.zdsoft.framework.core.commweb.annotation.UriKey;
import com.zdsoft.framework.core.commweb.component.BaseController;

/**
 * 返佣账户
 * 
 * @author Hisa
 *
 */
@Controller
@RequestMapping("/receiveAccount")
public class ReceiveAccountController extends BaseController {

	@Autowired
	ReceiveAccountService receiveAccountService;

	@Autowired
	CooperatorTerminalService cooperatorTerminalService;

	/**
	 * 合作方联系人信息列表
	 * 
	 * @return
	 */
	@RequestMapping("/initReceiveAccount")
	@UriKey(key = "com.zdsoft.finance.cooperator.initReceiveAccount")
	public ModelAndView initReceiveAccount(String terminalId) {
		Map<String, Object> obj = new HashMap<String, Object>();
		obj.put("terminalId", terminalId);
		return new ModelAndView("/cooperator/receive_account_list", obj);
	}

	/**
	 * 列表数据展示
	 * 
	 * @param request
	 * @param jsoncallback
	 * @param pageable
	 * @return
	 */
	@RequestMapping("/getReceiveAccount")
	@UriKey(key = "com.zdsoft.finance.cooperator.getReceiveAccount")
	@ResponseBody
	public String getManagemegetReceiveAccountntInfo(HttpServletRequest request, String jsoncallback,
			PageRequest pageable) {

		// 获取页面封装的查询参数
		@SuppressWarnings("unchecked")
		List<QueryObj> queryObjs = (List<QueryObj>) request.getAttribute("listObj");
		// 分页查询会议
		Page<ReceiveAccount> accounts = receiveAccountService.findByHqlConditions(pageable, queryObjs);
		List<ReceiveAccount> list = accounts.getRecords();
		List<ReceiveAccountVo> listVo = new ArrayList<ReceiveAccountVo>();
		for (ReceiveAccount info : list) {
			ReceiveAccountVo vo = new ReceiveAccountVo(info,null,new String[]{"approveState"});
			listVo.add(vo);
		}
		ResponseMsg msg = new ResponseMsg();
		msg.setMsg("列表查询成功");
		msg.setResultStatus(ResponseMsg.SUCCESS);
		msg.setTotal(accounts.getTotalRows());
		msg.setRows(listVo);
		return ObjectHelper.objectToJson(msg, jsoncallback);
	}

	/**
	 * 删除
	 * 
	 * @param jsoncallback
	 * @return
	 * @throws BusinessException
	 */
	@RequestMapping("/del")
	@UriKey(key = "com.zdsoft.finance.cooperator.receiveAccount.del")
	@ResponseBody
	public String del(String jsoncallback, String id) throws BusinessException {
		ResponseMsg msg = new ResponseMsg();
		try {
			receiveAccountService.logicDelete(id);
			msg.setMsg("操作成功！");
			msg.setResultStatus(ResponseMsg.SUCCESS);
		} catch (Exception e) {
			msg.setMsg("操作失败！" + e.getMessage());
			msg.setResultStatus(ResponseMsg.SYS_ERROR);
		}
		return ObjectHelper.objectToJson(msg);
	}

	/**
	 * 编辑
	 * 
	 * @return
	 * @throws BusinessException
	 */
	@RequestMapping("/edit")
	@UriKey(key = "com.zdsoft.finance.cooperator.receiveAccount.dialog")
	public ModelAndView contactsInfoEdit(String terminalId, String id, String operationType)
			throws BusinessException {
		ModelAndView modelAndView = new ModelAndView("/cooperator/receive_account_dialog");
		if (!"add".equals(operationType)) {
			ReceiveAccount info = receiveAccountService.findOne(id);
			ReceiveAccountVo infoVo = new ReceiveAccountVo(info);
			modelAndView.addObject("infoVo", infoVo);
		}
		modelAndView.addObject("terminalId", terminalId);
		modelAndView.addObject("operationType", operationType);
		return modelAndView;
	}

	/**
	 * 更新返佣账户
	 * 
	 * @param jsoncallback
	 * @param infoVo
	 * @return
	 * @throws BusinessException
	 */
	@RequestMapping("/save")
	@UriKey(key = "com.zdsoft.finance.cooperator.receiveAccount.save")
	@ResponseBody
	public String save(ReceiveAccountVo infoVo) throws BusinessException {
		ResponseMsg msg = new ResponseMsg();
		if (!ObjectHelper.isEmpty(infoVo)) {
			if (ObjectHelper.isEmpty(infoVo.getId())) {
				CooperatorTerminal ter = cooperatorTerminalService.findOne(infoVo.getTerminalId());
				ReceiveAccount info = infoVo.toPO();
				info.setCooperatorTerminal(ter);
				receiveAccountService.saveEntity(info);
				msg.setMsg("保存成功！");
				msg.setResultStatus(ResponseMsg.SUCCESS);
			} else {
				logger.info("===============================ID:" + infoVo.getId());
				ReceiveAccount info = receiveAccountService.findOne(infoVo.getId());
				info.setBankName(infoVo.getBankName());
				info.setAccountDescribe(infoVo.getAccountDescribe());
				info.setAccountName(infoVo.getAccountName());
				info.setAccountNumber(infoVo.getAccountNumber());
				info.setApproveState(infoVo.getApproveState());
				info.setReceiverName(infoVo.getReceiverName());
				info.setAliveAccount(infoVo.getAliveAccount());
				CooperatorTerminal ter = cooperatorTerminalService.findOne(infoVo.getTerminalId());
				info.setCooperatorTerminal(ter);
				receiveAccountService.updateEntity(info);
				msg.setMsg("更新成功！");
				msg.setResultStatus(ResponseMsg.SUCCESS);
			}
		} else {
			msg.setMsg("数据为空");
			msg.setResultStatus(ResponseMsg.APP_ERROR);
		}
		return ObjectHelper.objectToJson(msg);
	}
}
