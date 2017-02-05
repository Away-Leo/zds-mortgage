package com.zdsoft.finance.seal.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.servlet.http.HttpServletRequest;

import org.omg.DynamicAny._DynAnyStub;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.dyuproject.protostuff.StringSerializer.STRING;
import com.zdsoft.finance.common.base.QueryObj;
import com.zdsoft.finance.contract.controller.ContractModel;
import com.zdsoft.finance.contract.service.SealService;
import com.zdsoft.finance.seal.vo.CaseVo;
import com.zdsoft.finance.seal.vo.FilesVo;
import com.zdsoft.finance.seal.vo.PrintDetailsVo;
import com.zdsoft.finance.seal.vo.SealListViewVo;
import com.zdsoft.finance.seal.vo.SecurityVo;
import com.zdsoft.framework.core.common.dto.ResponseMsg;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.PageRequest;
import com.zdsoft.framework.core.common.util.ObjectHelper;
import com.zdsoft.framework.core.commweb.annotation.UriKey;
import com.zdsoft.framework.core.commweb.component.BaseController;
import com.zdsoft.framework.cra.annotation.Menu;
import com.zdsoft.framework.cra.annotation.Reference;

/**
 * 合同管理
 * 
 * @author huangdk
 * @date 2016-1-6
 */
@Controller
@RequestMapping("seal")
public class SealController extends BaseController {
	@Autowired
	private HttpServletRequest request;

	@Autowired
	private SealService _SealService;
	/**
	 * 机构合同报备入口
	 * 
	 * @return 机构合同报备列表
	 */
	@RequestMapping("/initSeal")
	@UriKey(key = "com.zdsoft.finance.seal.initSeal")
	@Menu(resource = "com.zdsoft.finance.seal.initSeal", label = "案件合同盖章")
	public ModelAndView initContract() {
		return new ModelAndView("/seal/seal_list");
	}

	/**
	 * 驻点人员审核
	 * 
	 * @return 驻点人员审核
	 */
	@RequestMapping("/initZDSH")
	@UriKey(key = "com.zdsoft.finance.seal.initZDSH")
	public ModelAndView initZDSH() {
		return new ModelAndView("/seal/initZDSH");
	}

	/**
	 * 外贸信托审核
	 * 
	 * @return 外贸信托审核
	 */
	@RequestMapping("/initWMXTSH")
	@UriKey(key = "com.zdsoft.finance.seal.initWMXTSH")
	public ModelAndView sealcontrol() {
		return new ModelAndView("/seal/initWMXTSH");
	}

	/**
	 * 驻点盖章
	 * 
	 * @return 驻点盖章
	 */
	@RequestMapping("/initZDGZ")
	@UriKey(key = "com.zdsoft.finance.seal.initZDGZ")
	public ModelAndView initZDGZ() {
		return new ModelAndView("/seal/initZDGZ");
	}

	/**
	 * 驻点寄出
	 * 
	 * @return 驻点寄出
	 */
	@RequestMapping("/initZDJC")
	@UriKey(key = "com.zdsoft.finance.seal.initZDJC")
	public ModelAndView initZDJC() {
		return new ModelAndView("/seal/initZDJC");
	}

	/**
	 * 驻点合同退回
	 * 
	 * @return 驻点合同退回
	 */
	@RequestMapping("/initZDHERSendBack")
	@UriKey(key = "com.zdsoft.finance.seal.initZDHERSendBack")
	public ModelAndView initZDHERSendBack() {
		return new ModelAndView("/seal/initZDHERSendBack");
	}
	
	/**
	 * 信托合同收回
	 * 
	 * @return 信托合同收回
	 */
	@RequestMapping("/initXTHERSendBack")
	@UriKey(key = "com.zdsoft.finance.seal.initXTHERSendBack")
	public ModelAndView initXTHERSendBack() {
		return new ModelAndView("/seal/initXTHERSendBack");
	}
	
	/**
	 * 案件查询
	 * 
	 * @param pageable
	 * @param jsoncallback
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/sealList")
	@UriKey(key = "com.zdsoft.finance.seal.sealList")
	@ResponseBody
	public String sealList(PageRequest pageable, String jsoncallback) {
		ResponseMsg msg = new ResponseMsg();
		// TODO 条件
		// @SuppressWarnings("unchecked")
		List<QueryObj> queryObjs = (List<QueryObj>) request.getAttribute("listObj");

	
		Page<Map<String, Object>> pms = null;
		try {
			pms = _SealService.findCoactCaseList(pageable,queryObjs);
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		

		msg.setResultStatus(ResponseMsg.SUCCESS);
		msg.setMsg("列表查询成功");
		msg.setRows(pms.getRecords());
		msg.setTotal(16L);
		return ObjectHelper.objectToJson(msg, jsoncallback);
	}

	/**
	 * 盖章申请
	 * 
	 * @return
	 */
	@RequestMapping("/apply")
	@UriKey(key = "com.zdsoft.finance.seal.apply")
	@Reference(resource = "com.zdsoft.finance.seal.apply", label = "盖章申请")
	public ModelAndView apply() {

		ModelMap modelMap = new ModelMap();

		CaseVo vo = new CaseVo();
		vo.setApplyMoney("2211");
		vo.setCaseNo("1501-2015030003");
		vo.setJigou("广州分公司");
		vo.setEntendMNG("张三");
		vo.setSubProduct("子产品1");
		vo.setJiedanDate("2017-1-10");
		ModelAndView mav = new ModelAndView("/seal/seal_apply");
		modelMap.put("vo", vo);

		return new ModelAndView("/seal/seal_apply", modelMap);
	}

	/**
	 * 押品列表
	 * 
	 * @param pageable
	 * @param jsoncallback
	 * @return
	 */
	@RequestMapping("/securityList")
	@UriKey(key = "com.zdsoft.finance.seal.securityList")
	@ResponseBody
	public String securityList(PageRequest pageable, String jsoncallback) {

		ResponseMsg msg = new ResponseMsg();
		// TODO 条件
		@SuppressWarnings("unchecked")
		List<QueryObj> queryObjs = (List<QueryObj>) request.getAttribute("listObj");
		Page<SecurityVo> page = null;
		// 查询数据模拟
		List<SecurityVo> list = new ArrayList<SecurityVo>();

		for (int i = 0; i < 4; i++) {
			SecurityVo temp = new SecurityVo();

			list.add(temp);
		}

		msg.setResultStatus(ResponseMsg.SUCCESS);
		msg.setMsg("列表查询成功");
		msg.setRows(list);
		msg.setTotal(50L);
		return ObjectHelper.objectToJson(msg, jsoncallback);
	}

	/**
	 * 现场驻点人员打印资料明细
	 * 
	 * @param pageable
	 * @param jsoncallback
	 * @return
	 */
	@RequestMapping("/printDetailsList")
	@UriKey(key = "com.zdsoft.finance.seal.printDetailsList")
	@ResponseBody
	public String printDetailsList(PageRequest pageable, String jsoncallback) {

		ResponseMsg msg = new ResponseMsg();
		// TODO 条件
		@SuppressWarnings("unchecked")
		List<QueryObj> queryObjs = (List<QueryObj>) request.getAttribute("listObj");
		Page<PrintDetailsVo> page = null;
		// 查询数据模拟
		List<PrintDetailsVo> list = new ArrayList<PrintDetailsVo>();

		for (int i = 0; i < 4; i++) {
			PrintDetailsVo temp = new PrintDetailsVo();

			list.add(temp);
		}

		msg.setResultStatus(ResponseMsg.SUCCESS);
		msg.setMsg("列表查询成功");
		msg.setRows(list);
		msg.setTotal(16L);
		return ObjectHelper.objectToJson(msg, jsoncallback);
	}

	/**
	 * 附件
	 * 
	 * @param pageable
	 * @param jsoncallback
	 * @return
	 */
	@RequestMapping("/filesList")
	@UriKey(key = "com.zdsoft.finance.seal.filesList")
	@ResponseBody
	public String filesList(PageRequest pageable, String jsoncallback) {

		ResponseMsg msg = new ResponseMsg();
		// TODO 条件
		@SuppressWarnings("unchecked")
		List<QueryObj> queryObjs = (List<QueryObj>) request.getAttribute("listObj");
		Page<FilesVo> page = null;
		// 查询数据模拟
		List<FilesVo> list = new ArrayList<FilesVo>();

		for (int i = 0; i < 50; i++) {
			FilesVo temp = new FilesVo();

			list.add(temp);
		}

		msg.setResultStatus(ResponseMsg.SUCCESS);
		msg.setMsg("列表查询成功");
		msg.setRows(list);
		msg.setTotal(16L);
		return ObjectHelper.objectToJson(msg, jsoncallback);
	}

}
