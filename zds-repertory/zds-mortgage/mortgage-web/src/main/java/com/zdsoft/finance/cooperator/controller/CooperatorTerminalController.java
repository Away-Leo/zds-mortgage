package com.zdsoft.finance.cooperator.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zdsoft.essential.client.service.CED;
import com.zdsoft.finance.common.base.QueryObj;
import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.finance.cooperator.entity.CooperatorTerminal;
import com.zdsoft.finance.cooperator.service.CooperatorTerminalService;
import com.zdsoft.finance.cooperator.vo.CooperatorTerminalVo;
import com.zdsoft.framework.core.common.dto.ResponseMsg;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.PageRequest;
import com.zdsoft.framework.core.common.util.DateHelper;
import com.zdsoft.framework.core.common.util.ObjectHelper;
import com.zdsoft.framework.core.commweb.annotation.UriKey;
import com.zdsoft.framework.core.commweb.component.BaseController;
import com.zdsoft.framework.cra.annotation.Menu;

/**
 * 合作方终端
 * 
 * @author Hisa
 *
 */
@Controller
@RequestMapping("/cooperator")
public class CooperatorTerminalController extends BaseController {

	@Autowired
	CooperatorTerminalService cooperatorTerminalService;

	@Autowired
	CED CED;

	/**
	 * 合作方管理列表
	 * 
	 * @return
	 */
	@RequestMapping("/initCooperator")
	@UriKey(key = "com.zdsoft.finance.cooperator.initCooperator")
	@Menu(resource = "com.zdsoft.finance.cooperator.initCooperator", label = "终端管理", group = "cooperator", order = 1)
	public ModelAndView initCooperator() {
		return new ModelAndView("/cooperator/cooperator_list");
	}

	/**
	 * 返回终端list
	 * 
	 * @param jsoncallback
	 * @return
	 */
	@RequestMapping("/cooperatorSimpleCode")
	@UriKey(key = "com.zdsoft.finance.cooperator.cooperatorSimpleCode")
	@ResponseBody
	public String cooperatorSimpleCode(String jsoncallback,String createOrgCd) {
		List<CooperatorTerminal> list = cooperatorTerminalService.getLogicAndOrgList(createOrgCd,"0");
		List<CooperatorTerminalVo> listVo = new ArrayList<CooperatorTerminalVo>();
		for (CooperatorTerminal info : list) {
			CooperatorTerminalVo vo = new CooperatorTerminalVo(info);
			listVo.add(vo);
		}
		return ObjectHelper.objectToJson(listVo, jsoncallback);
	}
	/**
	 * 列表数据展示
	 * 
	 * @param request
	 * @param jsoncallback
	 * @param pageable
	 * @return
	 */
	@RequestMapping("/getCooperator")
	@UriKey(key = "com.zdsoft.finance.cooperator.getCooperator")
	@ResponseBody
	public String getCooperator(HttpServletRequest request, String jsoncallback, PageRequest pageable) {

		// 获取页面封装的查询参数
		@SuppressWarnings("unchecked")
		List<QueryObj> queryObjs = (List<QueryObj>) request.getAttribute("listObj");
		QueryObj obj = new QueryObj();
		obj.setElement("String");
		obj.setObj("logicDelelte");
		obj.setOperator("E");
		obj.setValue("0");
		queryObjs.add(obj);
		// 分页查询会议
		Page<CooperatorTerminal> terminal = cooperatorTerminalService.findByHqlConditions(pageable, queryObjs);
		List<CooperatorTerminal> list = terminal.getRecords();
		List<CooperatorTerminalVo> listVo = new ArrayList<CooperatorTerminalVo>();
		for (CooperatorTerminal cooperatorTerminal : list) {
			CooperatorTerminalVo vo = new CooperatorTerminalVo(cooperatorTerminal,null,new String[]{"terminalType","terminalStatus","isStop"});
			listVo.add(vo);
		}
		ResponseMsg msg = new ResponseMsg();
		msg.setMsg("列表查询成功");
		msg.setResultStatus(ResponseMsg.SUCCESS);
		msg.setTotal(terminal.getTotalRows());
		msg.setRows(listVo);
		return ObjectHelper.objectToJson(msg, jsoncallback);
	}
	/**
	 * 模糊查询
	 * @param request
	 * @param jsoncallback
	 * @param terminalFullName
	 * @param belongTypeName
	 * @return
	 */
	@RequestMapping("/terminalFullName")
	@UriKey(key = "com.zdsoft.finance.cooperator.blurry.terminalFullName")
	@ResponseBody
	public String blurryTerminalFullName(HttpServletRequest request, String jsoncallback, String id) {
		// 分页查询会议
		List<CooperatorTerminal> terminal = cooperatorTerminalService.getBlurry("%"+id+"%",null);
		List<Map<String,Object>>  data = new ArrayList<>();
		if(terminal.size() > 0 ){
			for (CooperatorTerminal cooperatorTerminal : terminal) {
				Map<String,Object> map = new HashMap<>();
				map.put("id", cooperatorTerminal.getId());
				map.put("terminalFullName", cooperatorTerminal.getTerminalFullName());
				data.add(map);
			}
		}
		Map<String,Object> m = new HashMap<>();
		m.put("data", data);
		return ObjectHelper.objectToJson(m, jsoncallback);
	}
	/**
	 * 模糊查询
	 * @param request
	 * @param jsoncallback
	 * @param id
	 * @return
	 */
	@RequestMapping("/belongTypeName")
	@UriKey(key = "com.zdsoft.finance.cooperator.blurry.belongTypeName")
	@ResponseBody
	public String blurryBelongTypeName(HttpServletRequest request, String jsoncallback, String id) {
		// 分页查询会议
		List<CooperatorTerminal> terminal = cooperatorTerminalService.getBlurry(null,"%"+id+"%");
		List<Map<String,Object>>  data = new ArrayList<>();
		if(terminal.size() > 0){
			for (CooperatorTerminal cooperatorTerminal : terminal) {
				Map<String,Object> map = new HashMap<>();
				map.put("id", cooperatorTerminal.getId());
				map.put("belongTypeName", cooperatorTerminal.getBelongTypeName());
				data.add(map);
			}
		}
		Map<String,Object> m = new HashMap<>();
		m.put("data", data);
		return ObjectHelper.objectToJson(m, jsoncallback);
	}

	/**
	 * 更新数据
	 * 
	 * @param jsoncallback
	 * @return
	 * @throws BusinessException
	 */
	@RequestMapping("/update")
	@UriKey(key = "com.zdsoft.finance.cooperator.update")
	@ResponseBody
	public String update(String id, String state) throws BusinessException {
		ResponseMsg msg = new ResponseMsg();
		try {
			String[] ids = null;
			if (StringUtils.isNotEmpty(id)) {
				id = id.substring(0, id.length() - 1);
				ids = id.split(",");
				for (String str : ids) {
					CooperatorTerminal terminal = cooperatorTerminalService.findOne(str);
					terminal.setIsStop(state);
					cooperatorTerminalService.updateEntity(terminal);
				}
			}
			msg.setMsg("更新成功！");
			msg.setResultStatus(ResponseMsg.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			msg.setMsg("操作异常！" + e.getMessage());
			msg.setResultStatus(ResponseMsg.APP_ERROR);
		}

		return ObjectHelper.objectToJson(msg);
	}

	/**
	 * 新增页面
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/add")
	@UriKey(key = "com.zdsoft.finance.cooperator.add")
	public ModelAndView add() throws Exception {
		Map<String, Object> obj = new HashMap<String, Object>();
		CooperatorTerminalVo terminalVo = new CooperatorTerminalVo();
		terminalVo.setAutoCode(CED.resolveExpression("100000000000100002", null));
		CooperatorTerminal t = terminalVo.toPO();
		t.setLogicDelelte("1");
		t.setCreateOrgCd(CED.getLoginUser().getOrgCd());
		t.setNowDate(DateHelper.dateToLong(new Date(), DateHelper.DATE_SHORT_SIMPLE_FORMAT));
		t.setIsStop("zhuangtai001");
		CooperatorTerminal terminal = cooperatorTerminalService.saveEntity(t);
		terminalVo.setId(terminal.getId());
		obj.put("terminal", terminalVo);
		return new ModelAndView("/cooperator/cooperator_edit", obj);
	}

	/**
	 * 保存
	 * 
	 * @param jsoncallback
	 * @param cooperatorTerminal
	 * @return
	 * @throws BusinessException
	 */
	@RequestMapping("/save")
	@UriKey(key = "com.zdsoft.finance.cooperator.save")
	@ResponseBody
	public String save(CooperatorTerminalVo terminalVo) throws BusinessException {
		ResponseMsg msg = new ResponseMsg();
		if (!ObjectHelper.isEmpty(terminalVo) ) {
			if (ObjectHelper.isEmpty(terminalVo.getId())) {
				CooperatorTerminal terminal = terminalVo.toPO();
				terminal.setFoundDate(terminalVo.getFoundDate());
				cooperatorTerminalService.saveEntity(terminal);
				msg.setMsg("保存成功！");
				msg.setResultStatus(ResponseMsg.SUCCESS);
			} else {
				CooperatorTerminal terminal = cooperatorTerminalService.findOne(terminalVo.getId());
				terminal.setTerminalFullName(terminalVo.getTerminalFullName());
				terminal.setTerminalType(terminalVo.getTerminalType());
				terminal.setTerminalCode(terminalVo.getTerminalCode());
				terminal.setCompanyTel(terminalVo.getCompanyTel());
				terminal.setMonthOutAvgPredict(terminalVo.getMonthOutAvgPredict());
				terminal.setLastMonthBusiness(terminalVo.getLastMonthBusiness());
				terminal.setOneBillAvgVaule(terminalVo.getOneBillAvgVaule());
				terminal.setTerminalStatus(terminalVo.getTerminalStatus());
				terminal.setGrade(terminalVo.getGrade());
				terminal.setBelongType(terminalVo.getBelongType());
				terminal.setBelongTypeName(terminalVo.getBelongTypeName());
				terminal.setIsBelongOrg(terminalVo.getIsBelongOrg());
				terminal.setDealNumber(terminalVo.getDealNumber());
				terminal.setRebateType(terminalVo.getRebateType());
				terminal.setReturnRate(terminalVo.getReturnRate());
				terminal.setReturnAmount(terminalVo.getReturnAmount());
				terminal.setIsAllowPhoneMsg(terminalVo.getIsAllowPhoneMsg());
				terminal.setAcceptRebateType(terminalVo.getAcceptRebateType());
				terminal.setCooperatorAddress(terminalVo.getCooperatorAddress());
				terminal.setRegionCode(terminalVo.getRegionCode());
				terminal.setBusinessScope(terminalVo.getBusinessScope());
				terminal.setCooperateSuggest(terminalVo.getCooperateSuggest());
				terminal.setOnContinueReason(terminalVo.getOnContinueReason());
				terminal.setSpecialInstruction(terminalVo.getSpecialInstruction());
				terminal.setLogicDelelte("0");//有效数据
				terminal.setFoundDate(terminalVo.getFoundDate());
				terminal.setMainShareholder(terminalVo.getMainShareholder());
				terminal.setStaffNumber(terminalVo.getStaffNumber());
				terminal.setStaffTurnover(terminalVo.getStaffTurnover());
				terminal.setIsGiveDeductMoney(terminalVo.getIsGiveDeductMoney());
				terminal.setWageday(terminalVo.getWageday());
				terminal.setMonthManageCost(terminalVo.getMonthManageCost());
				terminal.setRegisteredCapital(terminalVo.getRegisteredCapital());
				terminal.setWorkArea(terminalVo.getWorkArea());

				cooperatorTerminalService.updateEntity(terminal);
				msg.setMsg("更新成功！");
				msg.setResultStatus(ResponseMsg.SUCCESS);
			}
		} else {
			msg.setMsg("数据为空");
			msg.setResultStatus(ResponseMsg.APP_ERROR);
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
	@UriKey(key = "com.zdsoft.finance.cooperator.edit")
	public ModelAndView edit(CooperatorTerminalVo vo) throws BusinessException {
		Map<String, Object> obj = new HashMap<String, Object>();
		CooperatorTerminal terminal = cooperatorTerminalService.findOne(vo.getId());
		CooperatorTerminalVo terminalVo = new CooperatorTerminalVo(terminal);
		obj.put("terminal", terminalVo);
		return new ModelAndView("/cooperator/cooperator_edit", obj);
	}

	/**
	 * 删除
	 * 
	 * @param jsoncallback
	 * @return
	 * @throws BusinessException
	 */
	@RequestMapping("/del")
	@UriKey(key = "com.zdsoft.finance.cooperator.del")
	@ResponseBody
	public String del(String jsoncallback, String id) throws BusinessException {
		ResponseMsg msg = new ResponseMsg();
		try {
			cooperatorTerminalService.logicDelete(id);
			msg.setMsg("操作成功！");
			msg.setResultStatus(ResponseMsg.SUCCESS);
		} catch (Exception e) {
			msg.setMsg("操作失败！" + e.getMessage());
			msg.setResultStatus(ResponseMsg.SYS_ERROR);
		}
		return ObjectHelper.objectToJson(msg);
	}

}
