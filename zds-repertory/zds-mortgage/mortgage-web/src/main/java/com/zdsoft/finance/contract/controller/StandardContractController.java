package com.zdsoft.finance.contract.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zdsoft.finance.common.base.QueryObj;
import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.finance.contract.entity.CoactContractTpl;
import com.zdsoft.finance.contract.service.StandardContractService;
import com.zdsoft.finance.contract.vo.StandardContractVo;
import com.zdsoft.finance.cooperator.entity.Capitalist;
import com.zdsoft.finance.cooperator.service.CapitalistService;
import com.zdsoft.framework.core.common.dto.ResponseMsg;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.PageRequest;
import com.zdsoft.framework.core.common.util.ObjectHelper;
import com.zdsoft.framework.core.commweb.annotation.UriKey;
import com.zdsoft.framework.core.commweb.component.BaseController;
import com.zdsoft.framework.cra.annotation.Menu;
import com.zdsoft.framework.cra.annotation.Reference;

@Controller
@RequestMapping("/standardContract")
public class StandardContractController extends BaseController {

	@Autowired
	private StandardContractService standardContractService;
	@Autowired
	private CapitalistService capitalistService;
	@Autowired
	private HttpServletRequest request;

	/**
	 * 标准合同设置初始化
	 * 
	 * @return 标准合同设置页面
	 */
	@RequestMapping("/initStandardContract")
	@UriKey(key = "com.zdsoft.finance.contract.initStandardContract")
	@Menu(resource = "com.zdsoft.finance.contract.initStandardContract", label = "标准合同设置", group = "contract", order = 1)
	public ModelAndView getStandardContract() {
		return new ModelAndView("/contract/standardContract_list");
	}

	/**
	 * 标准合同查询
	 * 
	 * @param pageable
	 * @param jsoncallback
	 * @return
	 */
	@RequestMapping("/standardContractList")
	@UriKey(key = "com.zdsoft.finance.contract.standardContractList")
	@ResponseBody

	public String standardContractList(HttpServletRequest request, PageRequest pageable, String jsoncallback) {
		ResponseMsg msg = new ResponseMsg();
		// TODO 条件
		@SuppressWarnings("unchecked")
		List<QueryObj> queryObjs = (List<QueryObj>) request.getAttribute("listObj");

		Page<CoactContractTpl> infos = standardContractService.findByHqlConditions(pageable, queryObjs);
		List<CoactContractTpl> list = infos.getRecords();
		List<StandardContractVo> listVo = new ArrayList<StandardContractVo>();
		for (CoactContractTpl info : list) {
			StandardContractVo vo = new StandardContractVo(info, new String[] {}, new String[] { "capitalistType" });
			listVo.add(vo);
		}

		/*
		 * Page<StandardContractVo> page = null; //查询数据模拟
		 * List<StandardContractVo> list = new ArrayList<>();
		 * 
		 * for (int i = 0; i < 6; i++) { StandardContractVo temp = new
		 * StandardContractVo(); temp.setid(String.valueOf(i));
		 * 
		 * temp.setAttachmentName(temp.getAttachmentName()+String.valueOf(i));
		 * temp.setCapitalName(temp.getCapitalName()+String.valueOf(i));
		 * list.add(temp); }
		 */

		msg.setResultStatus(ResponseMsg.SUCCESS);
		msg.setMsg("列表查询成功");
		msg.setRows(list);
		msg.setTotal(infos.getTotalRows());
		return ObjectHelper.objectToJson(msg, jsoncallback);
	}

	/**
	 * 标准合同编辑
	 * 
	 * @return
	 * @throws BusinessException
	 */
	@RequestMapping("/dialog")
	@UriKey(key = "com.zdsoft.finance.contract.standardContract.dialog")
	public ModelAndView dialog(String capitalistId, String id, String operationType) throws BusinessException {

		ModelAndView modelAndView = new ModelAndView("/contract/standardContract_list_add");
		if ("mod".equals(operationType) || "view".equals(operationType)) {
			CoactContractTpl info = standardContractService.findOne(id);
			StandardContractVo infoVo = new StandardContractVo(info);
			modelAndView.addObject("infoVo", infoVo);
		}
		modelAndView.addObject("capitalistId", capitalistId);
		modelAndView.addObject("operationType", operationType);
		return modelAndView;
	}

	/**
	 * 获取资方并转换为json
	 * 
	 * @param attribution
	 * @param id
	 * @param jsoncallback
	 * @return
	 */
	@RequestMapping("/getCapitalistCategorys")
	@UriKey(key = "com.zdsoft.finance.contract.getCapitalistCategory")
	@ResponseBody
	public String getCapitalistCategorys(String capitalistType) {

		/*
		 * List<Capitalist> capitalists =
		 * capitalistService.findByCapitalistType(capitalistType);
		 * List<CapitalistVo> capitalistVos = new ArrayList<CapitalistVo>(); for
		 * (Capitalist capitalist : capitalists) { CapitalistVo capitalistVo =
		 * new CapitalistVo(capitalist); capitalistVos.add(capitalistVo); }
		 * return ObjectHelper.objectToJson(capitalistVos);
		 */
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		try {
			if (ObjectHelper.isNotEmpty(capitalistType)) {
				List<Capitalist> capitalists = capitalistService.findByCapitalistType(capitalistType);
				if (ObjectHelper.isNotEmpty(capitalists)) {
					for (Capitalist capitalist : capitalists) {
						Map<String, Object> returnData = new HashMap<String, Object>();
						returnData.put("id", capitalist.getId());
						returnData.put("value", capitalist.getCooperatorName());
						list.add(returnData);
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("通过产品类型查询对应机构的产品", e);
		}
		return ObjectHelper.objectToJson(list);

	}

	/**
	 * 标准合同弹框
	 * 
	 * @return
	 */
	@RequestMapping("/initCasePlanDistribution")
	@UriKey(key = "com.zdsoft.finance.capital.initCasePlanDistribution")
	@Reference(resource = "com.zdsoft.finance.capital.initCasePlanDistribution", label = "标准合同编辑")
	public ModelAndView initCasePlanDistribution(String id) {
		ModelMap modelMap = new ModelMap();

		/*
		 * try { StandardContractVo institutionFunds =
		 * StandardContractVo.findOne(id); StandardContractVo fundsVo = new
		 * StandardContractVo(StandardContractVo);
		 * 
		 * modelMap.put("fundsVo", fundsVo); modelMap.put("updateTime",
		 * DateHelper.dateToString(new Date(), "yyyy/MM/dd")); } catch
		 * (BusinessException e) { e.printStackTrace();
		 * logger.error("查询标准合同出现错误", e); }
		 */

		return new ModelAndView("/contract/standardContract_list_add", modelMap);
	}

	/**
	 * 新增或编辑标准合同
	 * 
	 * @param notarizeVo
	 *            合同VO对象
	 * @return
	 */
	@RequestMapping("/addStandardContract")
	@UriKey(key = "com.zdsoft.finance.contract.standardContract.save")
	@ResponseBody
	public ResponseMsg saveOrUpdatestandardContract(StandardContractVo standardContractVo) {
		ResponseMsg msg = new ResponseMsg();

		// 增加
		try {
			CoactContractTpl coactContractTpl = standardContractVo.toPo();
			standardContractService.saveOrUpdateEntity(coactContractTpl);
			msg.setResultStatus(ResponseMsg.SUCCESS);
			msg.setMsg("保存成功");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("保存失败", e);
			msg.setResultStatus(ResponseMsg.APP_ERROR);
			msg.setMsg("系统内部错误，请查看日志");
		}
		return msg;
	}

	/**
	 * 删除标准合同
	 * 
	 * @param notarizeVo
	 *            标准合同VO对象
	 * @return
	 */
	@RequestMapping("/deleteStandardContract")
	@UriKey(key = "com.zdsoft.finance.contract.standardContract.deleteStandardContract")
	@ResponseBody
	public ResponseMsg deleteStandardContract(String id, String jsoncallback) {
		ResponseMsg msg = new ResponseMsg();

		try {
			standardContractService.logicDelete(id);
			msg.setResultStatus(ResponseMsg.SUCCESS);
			msg.setMsg("删除成功");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("删除失败", e);
			msg.setResultStatus(ResponseMsg.APP_ERROR);
			msg.setMsg("系统内部错误，请查看日志");
		}
		return msg;
	}
}