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
import com.zdsoft.finance.cooperator.entity.TerminalHistory;
import com.zdsoft.finance.cooperator.service.TerminalHistoryService;
import com.zdsoft.finance.cooperator.vo.TerminalHistoryVo;
import com.zdsoft.framework.core.common.dto.ResponseMsg;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.PageRequest;
import com.zdsoft.framework.core.common.util.ObjectHelper;
import com.zdsoft.framework.core.commweb.annotation.UriKey;
import com.zdsoft.framework.core.commweb.component.BaseController;
/**
 * 终端维护
 * @author Hisa
 *
 */
@Controller
@RequestMapping("/terminalHistory")
public class TerminalHistoryController extends BaseController {

	@Autowired
	TerminalHistoryService terminalHistoryService;
	
	/**
	 * 合作方终端维护列表
	 * @return
	 */
	@RequestMapping("/initTerminalHistory")
	@UriKey(key = "com.zdsoft.finance.cooperator.initTerminalHistory")
	public ModelAndView initTerminalHistory(String terminalId) {
		Map<String, Object> obj = new HashMap<String, Object>();
		obj.put("terminalId", terminalId);
		return new ModelAndView("/cooperator/terminal_history_list",obj);
	}
	
	
	/**
	 * 列表数据展示
	 * @param request
	 * @param jsoncallback
	 * @param pageable
	 * @return
	 */
	@RequestMapping("/getTerminalHistory")
	@UriKey(key = "com.zdsoft.finance.cooperator.getTerminalHistory")
	@ResponseBody
	public String getTerminalHistory(HttpServletRequest request, String jsoncallback, PageRequest pageable) {

		// 获取页面封装的查询参数
		@SuppressWarnings("unchecked")
		List<QueryObj> queryObjs = (List<QueryObj>) request.getAttribute("listObj");
		// 分页查询会议
		Page<TerminalHistory> historys = terminalHistoryService.findByHqlConditions(pageable, queryObjs);
		List<TerminalHistory> list = historys.getRecords();
		List<TerminalHistoryVo> listVo = new ArrayList<TerminalHistoryVo>();
		for (TerminalHistory history : list) {
			TerminalHistoryVo vo = new TerminalHistoryVo(history);	
			listVo.add(vo);
		}
		ResponseMsg msg = new ResponseMsg();
		msg.setMsg("列表查询成功");
		msg.setResultStatus(ResponseMsg.SUCCESS);
		msg.setTotal(0L);
		msg.setRows(listVo);
		return ObjectHelper.objectToJson(msg, jsoncallback);
	}
	
	/**
	 * 删除
	 * @param jsoncallback
	 * @return
	 * @throws BusinessException 
	 */
	@RequestMapping("/del")
	@UriKey(key = "com.zdsoft.finance.cooperator.terminalHistory.del")
	@ResponseBody
	public String del(String jsoncallback,String id) throws BusinessException {
		ResponseMsg msg = new ResponseMsg();
		try {
			terminalHistoryService.logicDelete(id);
			msg.setMsg("操作成功！");
			msg.setResultStatus(ResponseMsg.SUCCESS);
		} catch (Exception e) {
			msg.setMsg("操作失败！"+e.getMessage());
			msg.setResultStatus(ResponseMsg.SYS_ERROR);
		}
		return ObjectHelper.objectToJson(msg);
	}
	
	/**
	 * 更新维护记录
	 * @param jsoncallback
	 * @param infoVo
	 * @return
	 * @throws BusinessException
	 */
	@RequestMapping("/save")
	@UriKey(key = "com.zdsoft.finance.cooperator.terminalHistory.save")
	@ResponseBody
	public String save(String jsoncallback, TerminalHistoryVo infoVo ) throws BusinessException {
		ResponseMsg msg = new ResponseMsg();
		if(!ObjectHelper.isEmpty(infoVo)){
			if(ObjectHelper.isEmpty(infoVo.getId())){
				terminalHistoryService.saveEntity(infoVo.toPO());
				msg.setMsg("保存成功！");
				msg.setResultStatus(ResponseMsg.SUCCESS);
			}else{
				logger.info("===============================ID:"+infoVo.getId());
				TerminalHistory info =terminalHistoryService.findOne(infoVo.getId());
				terminalHistoryService.updateEntity(info);
				msg.setMsg("更新成功！");
				msg.setResultStatus(ResponseMsg.SUCCESS);
			}
		}else{
			msg.setMsg("数据为空");
			msg.setResultStatus(ResponseMsg.APP_ERROR);
		}
		return ObjectHelper.objectToJson(msg, jsoncallback);
	}
	/**
	 * 编辑
	 * @return
	 * @throws BusinessException 
	 */
	@RequestMapping("/edit")
	@UriKey(key = "com.zdsoft.finance.cooperator.terminalHistory.edit")
	public ModelAndView contactsInfoEdit(TerminalHistoryVo vo) throws BusinessException {
		Map<String, Object> obj = new HashMap<String, Object>();
		TerminalHistory info = terminalHistoryService.findOne(vo.getId());
		TerminalHistoryVo infoVo = new TerminalHistoryVo();
		
		if(ObjectHelper.isNotEmpty(info))
			BeanUtils.copyProperties(info, infoVo);
		
		obj.put("infoVo", infoVo);
		return new ModelAndView("/cooperator/terminal_history_edit",obj);
	}
	
}

