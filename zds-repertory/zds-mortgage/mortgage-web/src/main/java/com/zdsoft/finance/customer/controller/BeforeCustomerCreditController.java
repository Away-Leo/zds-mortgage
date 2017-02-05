package com.zdsoft.finance.customer.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zdsoft.finance.casemanage.material.entity.CaseMaterialListAtta;
import com.zdsoft.finance.casemanage.material.vo.CaseMaterialListAttaVo;
import com.zdsoft.finance.common.base.QueryObj;
import com.zdsoft.finance.customer.entity.Credit;
import com.zdsoft.finance.customer.service.CreditService;
import com.zdsoft.finance.customer.vo.CreditVo;
import com.zdsoft.framework.core.common.dto.ResponseMsg;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.PageRequest;
import com.zdsoft.framework.core.commweb.annotation.UriKey;
import com.zdsoft.framework.core.commweb.component.BaseController;

/**
 * 版权所有：重庆正大华日软件有限公司
 * 
 * @Title:BeforeCustomerCreditController.java
 * @Package:com.zdsoft.finance.customer.controller
 * @Description:案件客户征信记录控制器
 * @author: gonght
 * @date:2017年1月13日 下午6:23:33
 * @version:v1.0
 */
@Controller
@RequestMapping("/beforeCustomerCredit")
public class BeforeCustomerCreditController extends BaseController {

	@Autowired
	private CreditService creditService;

	/**
	 * 案件相关客户征信列表入口
	 *
	 * @author gonght
	 * @param caseApplyId
	 *            案件Id
	 * @return
	 */
	@RequestMapping(value = "/initBeforeCustomerCreditListPage")
	@UriKey(key = "com.zdsoft.finance.customer.credit.initBeforeCustomerCreditListPage")
	public ModelAndView initBeforeCustomerCreditListPage(String caseApplyId) {
		logger.info("-----------进入案件相关客户征信列表入口页面----------------");
		logger.info("参数如下-->caseApplyId:{}", caseApplyId);
		ModelMap model = new ModelMap();
		model.put("caseApplyId", caseApplyId);
		return new ModelAndView("customer/credit/customer_credit_list", model);
	}

	/**
	 * 案件相关客户征信列表数据查询
	 *
	 * @author gonght
	 * @param request
	 * @param pageable
	 * @return
	 */
	@RequestMapping("/queryBeforeCustomerCreditList")
	@UriKey(key = "com.zdsoft.finance.customer.credit.queryBeforeCustomerCreditList")
	@ResponseBody
	public ResponseMsg queryBeforeCustomerCreditList(HttpServletRequest request, PageRequest pageable) {
		ResponseMsg msg = new ResponseMsg();

		try {
			// 获取页面封装的查询参数
			@SuppressWarnings("unchecked")
			List<QueryObj> queryObjs = (List<QueryObj>) request.getAttribute("listObj");

			for (QueryObj queryObj : queryObjs) {
				logger.info(queryObj.getElement());
				logger.info(queryObj.getObj());
				logger.info(queryObj.getOperator());
				logger.info(queryObj.getValue().toString());
			}

			// 分页查询
			Page<Credit> creditPage = creditService.findByHqlConditions(pageable, queryObjs);
			// Po转换Vo
			List<CreditVo> creditVos = new ArrayList<CreditVo>();
			for (Credit credit : creditPage.getRecords()) {
				CreditVo creditVo = new CreditVo(credit, new String[] {}, new String[] {});
				// 特殊处理
				creditVos.add(creditVo);
			}
			msg.setResultStatus(ResponseMsg.SUCCESS);
			msg.setMsg("案件相关客户征信列表查询成功");
			msg.setRows(creditVos);
			msg.setTotal(creditPage.getTotalRows());
		} catch (Exception e) {
			msg.setResultStatus(ResponseMsg.APP_ERROR);
			msg.setMsg("案件相关客户征信列表查询失败");
			e.printStackTrace();
		}
		return msg;
	}

	/**
	 * 保存客户征信附件信息
	 * 
	 * @author gonght
	 * @param caseMaterialListAttaVo 征信附件
	 * @return
	 */
	@RequestMapping("/saveCustomerCreditAtta")
	@UriKey(key = "com.zdsoft.finance.customer.credit.saveCustomerCreditAtta")
	@ResponseBody
	public ResponseMsg saveCustomerCreditAtta(CaseMaterialListAttaVo caseMaterialListAttaVo) {
		logger.info("保存客户征信附件信息");
		ResponseMsg msg = new ResponseMsg();
		try {
			logger.info("客户征信记录id={}", caseMaterialListAttaVo.getBusinessId());
			logger.info("上传附件id={}", caseMaterialListAttaVo.getAttachmentId());
			logger.info("附件类型(产品资料清单)代码={}", caseMaterialListAttaVo.getMateriaCode());
			logger.info("附件类型(产品资料清单)名称={}", caseMaterialListAttaVo.getMateriaName());
			CaseMaterialListAtta caseMaterialListAtta = caseMaterialListAttaVo.toPo();
			//处理客户附件的集合保存，更新状态
			creditService.saveCustomerCreditAtta(caseMaterialListAtta);
			msg.setResultStatus(ResponseMsg.SUCCESS);
			msg.setMsg("保存客户征信附件信息成功！");
		} catch (Exception e) {
			logger.error("保存客户征信附件信息！");
			msg.setResultStatus(ResponseMsg.APP_ERROR);
			msg.setMsg(e.getMessage());
			e.printStackTrace();
		}
		return msg;
	}

	/**
	 * 客户征信记录查看
	 *
	 * @author gonght
	 * @param customerCreditId
	 *            客户信息记录id
	 * @return
	 */
	@RequestMapping("/initBeforeCustomerCreditViewPage")
	@UriKey(key = "com.zdsoft.finance.customer.credit.initBeforeCustomerCreditViewPage")
	public ModelAndView initBeforeCustomerCreditViewPage(String creditId) {
		logger.info("-----------进入客户征信记录查看页面----------------");
		logger.info("参数如下-->creditId:{}", creditId);
		// TODO:获取后台数据
		ModelMap model = new ModelMap();
		model.put("creditVo", null);
		return new ModelAndView("customer/credit/customer_credit_view", model);
	}
}