package com.zdsoft.finance.contract.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zdsoft.finance.common.base.QueryObj;
import com.zdsoft.finance.contract.entity.CoactAgencyContractTplApply;
import com.zdsoft.finance.contract.service.ReportContractService;
import com.zdsoft.finance.contract.vo.ReportContractVo;
import com.zdsoft.framework.core.common.dto.ResponseMsg;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.PageRequest;
import com.zdsoft.framework.core.common.util.ObjectHelper;
import com.zdsoft.framework.core.commweb.annotation.UriKey;
import com.zdsoft.framework.core.commweb.component.BaseController;

/**
 * 合同管理
 * @author wangnengduo
 * @date 2016-1-6
 */
@Controller
@RequestMapping("reportContract")
public class ReportContractController extends BaseController{

	@Autowired
	private ReportContractService reportContractService;
	@Autowired
	private HttpServletRequest request;
	
	
    /**
     * 机构合同报备入口
     * @return 机构合同报备列表
     * 
     * 2017.3.11 功能重复注释 dengyy
     */
//  @RequestMapping("/initReportContract")
//  @UriKey(key = "com.zdsoft.finance.contract.initReportContract")
//  @Menu(resource = "com.zdsoft.finance.contract.initReportContract", label = "机构合同报备", group = "contract", order = 2)
//  public ModelAndView initContract() {
//      return new ModelAndView("/contract/reportContract_list");
//  }
	
	
	/**
	 * 案件查询
	 * @param pageable
	 * @param jsoncallback
	 * @return
	 */
	@RequestMapping("/reportContractList")
	@UriKey(key = "com.zdsoft.finance.contract.reportContractList")
	@ResponseBody
	public String reportContractList(PageRequest pageable, String jsoncallback) {
		/*ResponseMsg msg = new ResponseMsg();
		//TODO 条件
		@SuppressWarnings("unchecked")
		List<QueryObj> queryObjs = (List<QueryObj>) request.getAttribute("listObj");
		Page<ContractModel> page = null;
		//查询数据模拟
		List<ContractModel> list = new ArrayList<>();
	
		for (int i = 0; i < 6; i++) {
			ContractModel temp = new ContractModel();
			temp.setid(i);
			
			temp.setapplyName(temp.getapplyName()+String.valueOf(i));
			list.add(temp);
		}
	
		msg.setResultStatus(ResponseMsg.SUCCESS);
		msg.setMsg("列表查询成功");
		msg.setRows(list);
		msg.setTotal(16L);
		return ObjectHelper.objectToJson(msg, jsoncallback);*/
		ResponseMsg msg = new ResponseMsg();
		//TODO 条件
		@SuppressWarnings("unchecked")
		List<QueryObj> queryObjs = (List<QueryObj>) request.getAttribute("listObj");
		
		Page<CoactAgencyContractTplApply> infos = reportContractService.findByHqlConditions(pageable, queryObjs);
		List<CoactAgencyContractTplApply> list = infos.getRecords();
		List<ReportContractVo> listVo = new ArrayList<ReportContractVo>();
		for (CoactAgencyContractTplApply info : list) {
			ReportContractVo vo = new ReportContractVo(info, new String[] {}, new String[] { "contractName" });
			listVo.add(vo);
		}
		
	
		msg.setResultStatus(ResponseMsg.SUCCESS);
		msg.setMsg("列表查询成功");
		msg.setRows(list);
		msg.setTotal(infos.getTotalRows());
		return ObjectHelper.objectToJson(msg, jsoncallback);
	}
	
	
	/**
	 * 申请
	 * @return
	 */
	@RequestMapping("/addContractApply")
	@UriKey(key = "com.zdsoft.finance.contract.addReportContractApply")
	@ResponseBody
	public ModelAndView addContractApply() {
		return new ModelAndView("/contract/reportContract_add");
	}
}
