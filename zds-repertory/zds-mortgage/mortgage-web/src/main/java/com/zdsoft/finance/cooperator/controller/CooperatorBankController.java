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
import com.zdsoft.finance.common.exception.BusinessException;
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
 * 合作银行
 * @author Hisa
 *
 */
@Controller
@RequestMapping("/cooperatorBank")
public class CooperatorBankController extends BaseController {
	
	@Autowired
	CooperatorBankService cooperatorBankService;
	
	@Autowired
	CooperatorTerminalService cooperatorTerminalService;
	/**
	 * 合作方联系人信息列表
	 * @return
	 */
	@RequestMapping("/initCooperatorBank")
	@UriKey(key = "com.zdsoft.finance.cooperator.initCooperatorBank")
	public ModelAndView initCooperatorBank(String terminalId) {
		Map<String, Object> obj = new HashMap<String, Object>();
		obj.put("terminalId", terminalId);
		return new ModelAndView("/cooperator/cooperator_bank_list",obj);
	}
	/**
	 * 列表数据展示
	 * @param request
	 * @param jsoncallback
	 * @param pageable
	 * @return
	 */
	@RequestMapping("/getCooperatorBank")
	@UriKey(key = "com.zdsoft.finance.cooperator.getCooperatorBank")
	@ResponseBody
	public String getCooperatorBank(HttpServletRequest request, String jsoncallback, PageRequest pageable) {

		// 获取页面封装的查询参数
		@SuppressWarnings("unchecked")
		List<QueryObj> queryObjs = (List<QueryObj>) request.getAttribute("listObj");
		// 分页查询会议
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
	 * 保存数据
	 * @param jsoncallback
	 * @param cooperatorBank
	 * @return
	 * @throws BusinessException
	 */
	@RequestMapping("/save")
	@UriKey(key = "com.zdsoft.finance.cooperator.cooperatorBank.save")
	@ResponseBody
	public String save(CooperatorBankVo cooperatorBank ) throws BusinessException {
		ResponseMsg msg = new ResponseMsg();
		if(!ObjectHelper.isEmpty(cooperatorBank)){
			if(ObjectHelper.isEmpty(cooperatorBank.getId())){
				CooperatorTerminal ter = cooperatorTerminalService.findOne(cooperatorBank.getTerminalId());
				CooperatorBank bank = cooperatorBank.toPO();
				bank.setCooperatorTerminal(ter);
				cooperatorBankService.saveEntity(bank);
				msg.setMsg("保存成功！");
				msg.setResultStatus(ResponseMsg.SUCCESS);
			}else{
				logger.info("===============================ID:"+cooperatorBank.getId());
				CooperatorBank terminal =cooperatorBankService.findOne(cooperatorBank.getId());
				terminal.setBankName(cooperatorBank.getBankName());
				CooperatorTerminal ter = cooperatorTerminalService.findOne(cooperatorBank.getTerminalId());
				terminal.setCooperatorTerminal(ter);
				cooperatorBankService.updateEntity(terminal);
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
	@UriKey(key = "com.zdsoft.finance.cooperator.cooperatorBank.del")
	@ResponseBody
	public String del(String jsoncallback,String id) throws BusinessException {
		ResponseMsg msg = new ResponseMsg();
		try {
			cooperatorBankService.logicDelete(id);
			msg.setMsg("操作成功！");
			msg.setResultStatus(ResponseMsg.SUCCESS);
		} catch (Exception e) {
			msg.setMsg("操作失败！"+e.getMessage());
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
	@UriKey(key = "com.zdsoft.finance.cooperator.cooperatorBank.dialog")
	public ModelAndView contactsInfoEdit(String terminalId, String id, String operationType)
			throws BusinessException {
		ModelAndView modelAndView = new ModelAndView("/cooperator/cooperator_bank_dialog");
		if (!"add".equals(operationType)) {
			CooperatorBank bank = cooperatorBankService.findOne(id);
			CooperatorBankVo infoVo = new CooperatorBankVo(bank);
			modelAndView.addObject("infoVo", infoVo);
		}
		modelAndView.addObject("terminalId", terminalId);
		modelAndView.addObject("operationType", operationType);
		return modelAndView;
	}
}
