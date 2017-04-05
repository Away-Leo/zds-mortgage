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
import com.zdsoft.finance.cooperator.entity.BrokerageAccount;
import com.zdsoft.finance.cooperator.service.CooperatorTerminalService;
import com.zdsoft.finance.cooperator.service.BrokerageAccountService;
import com.zdsoft.finance.cooperator.vo.BrokerageAccountVo;
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
 * @Title: ReceiveAccountController.java
 * @ClassName: BrokerageAccountController
 * @Description: 返佣账户Controller
 * @author liuwei
 * @date 2017年3月1日 下午6:12:09
 * @version V1.0
 */
@Controller
@RequestMapping("/brokerageAccount")
public class BrokerageAccountController extends BaseController {

	@Autowired
	BrokerageAccountService brokerageAccountService;

	@Autowired
	CooperatorTerminalService cooperatorTerminalService;

	/**
	 * 合作方联系人信息列表
	 * 
	 * @return
	 */
	@RequestMapping("/initBrokerageAccount")
	@UriKey(key = "com.zdsoft.finance.cooperator.initBrokerageAccount")
	public ModelAndView initBrokerageAccount(String terminalId) {
		Map<String, Object> obj = new HashMap<String, Object>();
		obj.put("terminalId", terminalId);
		return new ModelAndView("/cooperator/receive_account_list", obj);
	}

	/**
	 * 
	 * @Title: getManagemegetBrokerageAccountntInfo
	 * @Description: 列表数据展示
	 * @author liuwei
	 * @param request
	 *            请求
	 * @param jsoncallback
	 * @param pageable
	 *            分页信息
	 * @return 处理消息msg json
	 */
	@RequestMapping("/getBrokerageAccount")
	@UriKey(key = "com.zdsoft.finance.cooperator.getBrokerageAccount")
	@ResponseBody
	public String getManagemegetBrokerageAccountntInfo(HttpServletRequest request, String jsoncallback,
			PageRequest pageable) {

		// 获取页面封装的查询参数
		@SuppressWarnings("unchecked")
		List<QueryObj> queryObjs = (List<QueryObj>) request.getAttribute("listObj");
		// 分页查询会议
		Page<BrokerageAccount> accounts = brokerageAccountService.findByHqlConditions(pageable, queryObjs);
		List<BrokerageAccount> list = accounts.getRecords();
		List<BrokerageAccountVo> listVo = new ArrayList<BrokerageAccountVo>();
		for (BrokerageAccount info : list) {
			BrokerageAccountVo vo = new BrokerageAccountVo(info, new String[] {}, new String[] { "approveType" });
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
	 * 
	 * @Title: del
	 * @Description: 删除返佣账户信息
	 * @author liuwei
	 * @param jsoncallback
	 * @param id
	 *            返佣账户id
	 * @return 处理消息msg json
	 */
	@RequestMapping("/del")
	@UriKey(key = "com.zdsoft.finance.cooperator.brokerageAccount.del")
	@ResponseBody
	public String del(String jsoncallback, String id) {
		ResponseMsg msg = new ResponseMsg();
		try {
			brokerageAccountService.logicDelete(id);
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
	 * @Title: brokerageAccountEdit
	 * @Description: 返佣账户编辑
	 * @author liuwei
	 * @param terminalId
	 *            终端id
	 * @param id
	 *            返佣账户id
	 * @param operationType
	 *            操作类型
	 * @return ModelAndView
	 */
	@RequestMapping("/edit")
	@UriKey(key = "com.zdsoft.finance.cooperator.brokerageAccount.dialog")
	public ModelAndView brokerageAccountEdit(String terminalId, String id, String operationType) {
		ModelAndView modelAndView = new ModelAndView("/cooperator/receive_account_dialog");
		if (!"add".equals(operationType)) {
			BrokerageAccount info;
			try {
				// 根据
				info = brokerageAccountService.findOne(id);
				BrokerageAccountVo infoVo = new BrokerageAccountVo(info);
				modelAndView.addObject("infoVo", infoVo);
			} catch (BusinessException e) {
				e.printStackTrace();
				logger.error("查询返佣账户信息失败", e);
			}

		}
		modelAndView.addObject("terminalId", terminalId);
		modelAndView.addObject("operationType", operationType);
		return modelAndView;
	}

	/**
	 * 
	 * @Title: save
	 * @Description: 更新返佣账户信息
	 * @author liuwei
	 * @param infoVo
	 *            返佣账户信息
	 * @return 处理消息msg json
	 */
	@RequestMapping("/save")
	@UriKey(key = "com.zdsoft.finance.cooperator.brokerageAccount.save")
	@ResponseBody
	public String save(BrokerageAccountVo infoVo) {
		ResponseMsg msg = new ResponseMsg();

		// 判断传递参数是否为空
		if (!ObjectHelper.isEmpty(infoVo)) {
			BrokerageAccount account = infoVo.toPo();
			try {
				account = brokerageAccountService.saveOrUpdateBrokerageAccount(account);
				msg.setMsg("新增/修改返佣信息成功");
				msg.setResultStatus(ResponseMsg.SUCCESS);
			} catch (Exception e) {
				e.printStackTrace();
				msg.setMsg("新增/修改返佣信息失败");
				msg.setResultStatus(ResponseMsg.APP_ERROR);
			}
		} else {
			msg.setMsg("数据为空");
			msg.setResultStatus(ResponseMsg.APP_ERROR);
		}
		return ObjectHelper.objectToJson(msg);
	}
}
