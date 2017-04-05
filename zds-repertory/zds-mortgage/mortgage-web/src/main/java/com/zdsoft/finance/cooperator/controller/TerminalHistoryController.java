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
import com.zdsoft.framework.core.common.util.DateHelper;
import com.zdsoft.framework.core.common.util.ObjectHelper;
import com.zdsoft.framework.core.commweb.annotation.UriKey;
import com.zdsoft.framework.core.commweb.component.BaseController;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * 
 * @Title: TerminalHistoryController.java
 * @ClassName: TerminalHistoryController
 * @Description: 终端维护Controller
 * @author liuwei
 * @date 2017年3月8日 上午10:27:15
 * @version V1.0
 */
@Controller
@RequestMapping("/terminalHistory")
public class TerminalHistoryController extends BaseController {

	@Autowired
	TerminalHistoryService terminalHistoryService;

	/**
	 * 
	 * @Title: initTerminalHistory
	 * @Description: 合作方终端维护列表
	 * @author liuwei
	 * @param terminalId
	 *            终端id
	 * @return ModelAndView
	 */
	@RequestMapping("/initTerminalHistory")
	@UriKey(key = "com.zdsoft.finance.cooperator.initTerminalHistory")
	public ModelAndView initTerminalHistory(String terminalId) {
		Map<String, Object> obj = new HashMap<String, Object>();
		obj.put("terminalId", terminalId);
		return new ModelAndView("/cooperator/terminal_history_list", obj);
	}

	/**
	 * 
	 * @Title: getTerminalHistory
	 * @Description: 列表数据展示
	 * @author liuwei
	 * @param request
	 *            请求信息
	 * @param jsoncallback
	 * @param pageable
	 *            分页信息
	 * @return 处理消息msg json
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
			vo.setCreateDateLong(DateHelper.dateToString(history.getCreateTime(), DateHelper.DATE_WITHSECOND_FORMAT));
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
	 * 
	 * @Title: del
	 * @Description: 逻辑删除终端维护信息
	 * @author liuwei
	 * @param jsoncallback
	 * @param id
	 *            终端维护id
	 * @return 处理消息msg json
	 * @throws BusinessException
	 */
	@RequestMapping("/del")
	@UriKey(key = "com.zdsoft.finance.cooperator.terminalHistory.del")
	@ResponseBody
	public String del(String jsoncallback, String id) throws BusinessException {
		ResponseMsg msg = new ResponseMsg();
		try {
			terminalHistoryService.logicDelete(id);
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
	 * @Title: save
	 * @Description: 更新维护记录
	 * @author liuwei
	 * @param jsoncallback
	 * @param infoVo
	 *            维护信息
	 * @return 处理消息msg json
	 * @throws BusinessException
	 */
	@RequestMapping("/save")
	@UriKey(key = "com.zdsoft.finance.cooperator.terminalHistory.save")
	@ResponseBody
	public String save(String jsoncallback, TerminalHistoryVo infoVo) throws BusinessException {
		ResponseMsg msg = new ResponseMsg();
		if (!ObjectHelper.isEmpty(infoVo)) {
			if (ObjectHelper.isEmpty(infoVo.getId())) {
				terminalHistoryService.saveEntity(infoVo.toPO());
				msg.setMsg("保存成功！");
				msg.setResultStatus(ResponseMsg.SUCCESS);
			} else {
				logger.info("===============================ID:" + infoVo.getId());
				TerminalHistory info = terminalHistoryService.findOne(infoVo.getId());
				terminalHistoryService.updateEntity(info);
				msg.setMsg("更新成功！");
				msg.setResultStatus(ResponseMsg.SUCCESS);
			}
		} else {
			msg.setMsg("数据为空");
			msg.setResultStatus(ResponseMsg.APP_ERROR);
		}
		return ObjectHelper.objectToJson(msg, jsoncallback);
	}

	/**
	 * 
	 * @Title: contactsInfoEdit
	 * @Description: 终端维护记录更新
	 * @author liuwei
	 * @param vo
	 *            终端维护Vo
	 * @return ModelAndView
	 * @throws BusinessException
	 */
	@RequestMapping("/edit")
	@UriKey(key = "com.zdsoft.finance.cooperator.terminalHistory.edit")
	public ModelAndView contactsInfoEdit(TerminalHistoryVo vo) throws BusinessException {
		Map<String, Object> obj = new HashMap<String, Object>();
		TerminalHistory info = terminalHistoryService.findOne(vo.getId());
		TerminalHistoryVo infoVo = new TerminalHistoryVo();

		if (ObjectHelper.isNotEmpty(info))
			BeanUtils.copyProperties(info, infoVo);

		obj.put("infoVo", infoVo);
		return new ModelAndView("/cooperator/terminal_history_edit", obj);
	}

}
