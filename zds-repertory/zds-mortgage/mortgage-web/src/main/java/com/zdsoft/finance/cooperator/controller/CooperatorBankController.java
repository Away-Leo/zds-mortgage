package com.zdsoft.finance.cooperator.controller;

import java.util.ArrayList;
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

import com.zdsoft.finance.common.base.QueryObj;
import com.zdsoft.finance.cooperator.entity.CooperatorBank;
import com.zdsoft.finance.cooperator.entity.CooperatorTerminal;
import com.zdsoft.finance.cooperator.service.CooperatorBankService;
import com.zdsoft.finance.cooperator.service.CooperatorTerminalService;
import com.zdsoft.finance.cooperator.vo.CooperatorBankVo;
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
 * @Title: CooperatorBankController.java
 * @ClassName: CooperatorBankController
 * @Description: 合作方银行controller
 * @author liuwei
 * @date 2017年3月8日 上午10:18:47
 * @version V1.0
 */
@Controller
@RequestMapping("/cooperatorBank")
public class CooperatorBankController extends BaseController {

	@Autowired
	CooperatorBankService cooperatorBankService;

	@Autowired
	CooperatorTerminalService cooperatorTerminalService;

	/**
	 * 
	 * @Title: initCooperatorBank
	 * @Description: 合作方银行列表
	 * @author liuwei
	 * @param terminalId
	 *            终端id
	 * @return ModelAndView
	 */
	@RequestMapping("/initCooperatorBank")
	@UriKey(key = "com.zdsoft.finance.cooperator.initCooperatorBank")
	public ModelAndView initCooperatorBank(String terminalId) {
		Map<String, Object> obj = new HashMap<String, Object>();
		obj.put("terminalId", terminalId);
		return new ModelAndView("/cooperator/cooperator_bank_list", obj);
	}

	/**
	 * 
	 * @Title: getCooperatorBank
	 * @Description: 列表数据展示
	 * @author liuwei
	 * @param request
	 *            请求
	 * @param jsoncallback
	 * @param pageable
	 *            分页信息
	 * @return 处理消息msg
	 */
	@RequestMapping("/getCooperatorBank")
	@UriKey(key = "com.zdsoft.finance.cooperator.getCooperatorBank")
	@ResponseBody
	public String getCooperatorBank(HttpServletRequest request, String jsoncallback, PageRequest pageable) {

		// 获取页面封装的查询参数
		@SuppressWarnings("unchecked")
		List<QueryObj> queryObjs = (List<QueryObj>) request.getAttribute("listObj");
		// 分页查询合作方银行信息
		Page<CooperatorBank> banks = cooperatorBankService.findByHqlConditions(pageable, queryObjs);
		List<CooperatorBank> list = banks.getRecords();
		List<CooperatorBankVo> listVo = new ArrayList<CooperatorBankVo>();
		for (CooperatorBank bank : list) {
			CooperatorBankVo vo = new CooperatorBankVo();
			BeanUtils.copyProperties(bank, vo);
			listVo.add(vo);
		}
		ResponseMsg msg = new ResponseMsg();
		msg.setMsg("列表查询成功");
		msg.setResultStatus(ResponseMsg.SUCCESS);
		msg.setTotal(banks.getTotalRows());
		msg.setRows(listVo);
		return ObjectHelper.objectToJson(msg, jsoncallback);
	}

	/**
	 * 
	 * @Title: save
	 * @Description: 保存数据
	 * @author liuwei
	 * @param cooperatorBank
	 *            合作方银行信息
	 * @return 处理消息msg json
	 */
	@RequestMapping("/save")
	@UriKey(key = "com.zdsoft.finance.cooperator.cooperatorBank.save")
	@ResponseBody
	public String save(CooperatorBankVo cooperatorBank) {
		ResponseMsg msg = new ResponseMsg();
		try {
			if (!ObjectHelper.isEmpty(cooperatorBank)) {
				if (ObjectHelper.isEmpty(cooperatorBank.getId())) { // 判断id是否存在
					// 不存在,则新增
					CooperatorTerminal ter = cooperatorTerminalService.findOne(cooperatorBank.getTerminalId());
					CooperatorBank bank = cooperatorBank.toPO();
					bank.setCooperatorTerminal(ter);
					cooperatorBankService.saveEntity(bank);
					msg.setMsg("保存成功！");
					msg.setResultStatus(ResponseMsg.SUCCESS);
				} else {
					// 存在则修改
					logger.info("===============================ID:" + cooperatorBank.getId());
					CooperatorBank terminal = cooperatorBankService.findOne(cooperatorBank.getId());
					terminal.setBankName(cooperatorBank.getBankName());
					CooperatorTerminal ter = cooperatorTerminalService.findOne(cooperatorBank.getTerminalId());
					terminal.setCooperatorTerminal(ter);
					cooperatorBankService.updateEntity(terminal);
					msg.setMsg("更新成功！");
					msg.setResultStatus(ResponseMsg.SUCCESS);
				}
			} else {
				msg.setMsg("数据为空");
				msg.setResultStatus(ResponseMsg.APP_ERROR);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("保存合作方银行失败", e);
			msg.setMsg("保存合作方银行失败");
			msg.setResultStatus(ResponseMsg.APP_ERROR);
		}
		return ObjectHelper.objectToJson(msg);
	}

	/**
	 * 
	 * @Title: del
	 * @Description: 逻辑删除合作方银行信息
	 * @author liuwei
	 * @param jsoncallback
	 * @param id
	 *            合作方银行信息id
	 * @return 处理消息msg json
	 */
	@RequestMapping("/del")
	@UriKey(key = "com.zdsoft.finance.cooperator.cooperatorBank.del")
	@ResponseBody
	public String del(String jsoncallback, String id) {
		ResponseMsg msg = new ResponseMsg();
		try {
			cooperatorBankService.logicDelete(id);
			msg.setMsg("操作成功！");
			msg.setResultStatus(ResponseMsg.SUCCESS);
		} catch (Exception e) {
			msg.setMsg("操作失败！" + e.getMessage());
			msg.setResultStatus(ResponseMsg.SYS_ERROR);
		}
		return ObjectHelper.objectToJson(msg);
	}

	/**
	 * 
	 * @Title: contactsInfoEdit
	 * @Description: 合作方银行dialog
	 * @author liuwei
	 * @param terminalId
	 *            终端id
	 * @param id
	 *            合作方银行id
	 * @param operationType
	 *            操作方式
	 * @return ModelAndView
	 */
	@RequestMapping("/edit")
	@UriKey(key = "com.zdsoft.finance.cooperator.cooperatorBank.dialog")
	public ModelAndView contactsInfoEdit(String terminalId, String id, String operationType) {
		ModelAndView modelAndView = new ModelAndView("/cooperator/cooperator_bank_dialog");
		try {
			if (!"add".equals(operationType)) {
				CooperatorBank bank = cooperatorBankService.findOne(id);
				CooperatorBankVo infoVo = new CooperatorBankVo(bank);
				modelAndView.addObject("infoVo", infoVo);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("查询合作方银行失败", e);
		}
		modelAndView.addObject("terminalId", terminalId);
		modelAndView.addObject("operationType", operationType);
		return modelAndView;
	}
}
