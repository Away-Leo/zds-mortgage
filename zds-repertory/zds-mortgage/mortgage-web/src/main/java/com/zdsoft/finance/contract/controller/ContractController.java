package com.zdsoft.finance.contract.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zdsoft.finance.archive.controller.TempProject;
import com.zdsoft.finance.archive.service.ArchiveService;
import com.zdsoft.finance.common.base.QueryObj;
import com.zdsoft.finance.common.service.CommonService;
import com.zdsoft.framework.core.common.dto.ResponseMsg;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.PageRequest;
import com.zdsoft.framework.core.common.util.ObjectHelper;
import com.zdsoft.framework.core.commweb.annotation.UriKey;
import com.zdsoft.framework.core.commweb.component.BaseController;
import com.zdsoft.framework.cra.annotation.Menu;
/**
 * 合同管理
 * @author huangdk
 * @date 2016-1-6
 */
@Controller
@RequestMapping("/contract")
public class ContractController extends BaseController {
	
	@Autowired
	private HttpServletRequest request;
	@Autowired
	private CommonService _CommonService;
	
	/**
	 * 机构合同报备入口
	 * @return 机构合同报备列表
	 */
	@RequestMapping("/initContract")
	@UriKey(key = "com.zdsoft.finance.contract.initContract")
	@Menu(resource = "com.zdsoft.finance.contract.initContract", label = "机构合同报备", group = "contract", order = 1)
	public ModelAndView initContract() {
		return new ModelAndView("/contract/contract_list");
	}
	
	
	/**
	 * 案件查询
	 * @param pageable
	 * @param jsoncallback
	 * @return
	 */
	@RequestMapping("/contractList")
	@UriKey(key = "com.zdsoft.finance.contract.contractList")
	@ResponseBody
	public String contractList(PageRequest pageable, String jsoncallback) {
		ResponseMsg msg = new ResponseMsg();
		//TODO 条件
		@SuppressWarnings("unchecked")
		List<QueryObj> queryObjs = (List<QueryObj>) request.getAttribute("listObj");
		Page<ContractModel> page = null;
		//查询数据模拟
		List<ContractModel> list = new ArrayList<>();
	
		for (int i = 0; i < 50; i++) {
			ContractModel temp = new ContractModel();
			temp.setid(i);
			
			temp.setapplyName(temp.getapplyName()+String.valueOf(i));
			list.add(temp);
		}
	
		msg.setResultStatus(ResponseMsg.SUCCESS);
		msg.setMsg("列表查询成功");
		msg.setRows(list);
		msg.setTotal(16L);
		return ObjectHelper.objectToJson(msg, jsoncallback);
	}
	
	
	/**
	 * 申请
	 * @return
	 */
	@RequestMapping("/addContractApply")
	@UriKey(key = "com.zdsoft.finance.contract.addContractApply")
	@ResponseBody
	public ModelAndView addContractApply() {
		return new ModelAndView("/contract/contract_add");
	}
	
	
	/**
	 * 案件合同管理列表
	 * @return 案件合同管理列表
	 */
	@RequestMapping("/caseContractList")
	@UriKey(key = "com.zdsoft.finance.contract.caseContractList")
	@Menu(resource = "com.zdsoft.finance.contract.caseContractList", label = "案件合同管理", group = "contract", order = 4)
	public ModelAndView caseContractList() {
		return new ModelAndView("/contract/caseContract_List");
	}
	
	/**
	 * 案件合同查询
	 * @param pageable
	 * @param jsoncallback
	 * @return
	 */
	@RequestMapping("/getCaseContractList")
	@UriKey(key = "com.zdsoft.finance.contract.getCaseContractList")
	@ResponseBody
	public String getCaseContractList(PageRequest pageable, String jsoncallback) {
		ResponseMsg msg = new ResponseMsg();
		//TODO 条件
		@SuppressWarnings("unchecked")
		List<QueryObj> queryObjs = (List<QueryObj>) request.getAttribute("listObj");
		
		Page<Map<String, Object>> pms = null;
		
		try {
			pms = _CommonService.GetCaseContractPages(pageable,queryObjs);
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
	 * 案件合同管理列表
	 * @return 案件合同管理列表
	 */
	@RequestMapping("/caseContractPrint")
	@UriKey(key = "com.zdsoft.finance.contract.caseContractPrint")
	@ResponseBody
	public ModelAndView caseContractPrint() {
		return new ModelAndView("/contract/caseContract_print");
	}
	
	/**
	 * 合同套打预览
	 * @return 合同套打预览
	 */
	@RequestMapping("/caseContractView")
	@UriKey(key = "com.zdsoft.finance.contract.caseContractView")
	@ResponseBody
	public ModelAndView caseContractView() {
		return new ModelAndView("/contract/caseContract_view");
	}
}
