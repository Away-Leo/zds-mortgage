package com.zdsoft.finance.cooperator.controller;

import java.util.ArrayList;
import java.util.Date;
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

import com.zdsoft.essential.client.service.CED;
import com.zdsoft.essential.dto.OrgTreeDto;
import com.zdsoft.essential.dto.emp.EmpDto;
import com.zdsoft.essential.dto.org.OrgDto;
import com.zdsoft.finance.common.base.QueryObj;
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
 * 
 * 版权所有：重庆正大华日软件有限公司
 * 
 * @Title: CooperatorTerminalController.java
 * @ClassName: CooperatorTerminalController
 * @Description: 终端维护Controller
 * @author liuwei
 * @date 2017年2月28日 下午8:49:43
 * @version V1.0
 */
@Controller
@RequestMapping("/cooperator")
public class CooperatorTerminalController extends BaseController {

	@Autowired
	CooperatorTerminalService cooperatorTerminalService;

	@Autowired
	CED CED;

	/**
	 * 
	 * @Title: initCooperator
	 * @Description: 终端管理列表入口
	 * @author liuwei
	 * @return ModelAndView
	 */
	@RequestMapping("/initCooperator")
	@UriKey(key = "com.zdsoft.finance.cooperator.initCooperator")
	@Menu(resource = "com.zdsoft.finance.cooperator.initCooperator", label = "终端管理", group = "cooperator", order = 1)
	public ModelAndView initCooperator() {

		ModelMap modelMap = new ModelMap();

		// TODO .. 终端权限重新处理
		/*
		 * // 初始化初始数据 List<Map<String, Object>> returnValue = new
		 * ArrayList<Map<String, Object>>(); try { List<OrgTreeDto> orgTreeDtos
		 * = CED.getOrgTree(); returnValue = recursionValue(orgTreeDtos,
		 * returnValue, null); } catch (Exception e) { e.printStackTrace(); }
		 * modelMap.put("data", ObjectHelper.objectToJson(returnValue));
		 */

		try {
			String shareOrgCode = CED.getLoginUser().getCompanyCd();
			modelMap.put("shareOrgCode", shareOrgCode);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("获取当前登录人失败", e);
		}

		return new ModelAndView("/cooperator/cooperator_list", modelMap);
	}

	/**
	 * 
	 * @Title: cooperatorSimpleCode
	 * @Description: 终端列表
	 * @author liuwei
	 * @param jsoncallback
	 * @param createOrgCd
	 *            部门编号
	 * @return 终端列表信息
	 */
	@RequestMapping("/cooperatorSimpleCode")
	@UriKey(key = "com.zdsoft.finance.cooperator.cooperatorSimpleCode")
	@ResponseBody
	public String cooperatorSimpleCode(String jsoncallback, String createOrgCd) {
		List<CooperatorTerminalVo> listVo = new ArrayList<CooperatorTerminalVo>();

		try {
			List<CooperatorTerminal> list = cooperatorTerminalService
					.findByShareOrgCdAndTerminalStatus(CED.getLoginUser().getCompanyCd(), "YWDM0016301");
			for (CooperatorTerminal info : list) {
				CooperatorTerminalVo vo = new CooperatorTerminalVo(info);
				listVo.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("获取当前登录人失败", e);
		}

		return ObjectHelper.objectToJson(listVo, jsoncallback);
	}

	/**
	 * 
	 * @Title: getCooperator
	 * @Description: 终端列表数据
	 * @author liuwei
	 * @param request
	 *            请求
	 * @param jsoncallback
	 *            jsoncallback
	 * @param pageable
	 *            分页信息
	 * @return 列表json
	 */
	@RequestMapping("/getCooperator")
	@UriKey(key = "com.zdsoft.finance.cooperator.getCooperator")
	@ResponseBody
	public String getCooperator(HttpServletRequest request, String jsoncallback, PageRequest pageable) {

		// 获取页面封装的查询参数
		@SuppressWarnings("unchecked")
		List<QueryObj> queryObjs = (List<QueryObj>) request.getAttribute("listObj");

		// 查询终端分页信息
		Page<CooperatorTerminal> terminal = cooperatorTerminalService.findByHqlConditions(pageable, queryObjs);
		List<CooperatorTerminal> list = terminal.getRecords();

		// 构建返回终端vo
		List<CooperatorTerminalVo> listVo = new ArrayList<CooperatorTerminalVo>();
		for (CooperatorTerminal cooperatorTerminal : list) {
			CooperatorTerminalVo vo = new CooperatorTerminalVo(cooperatorTerminal, null,
					new String[] { "terminalType", "terminalStatus" });
			vo.setCreateTimeName(
					DateHelper.dateToString(cooperatorTerminal.getCreateTime(), DateHelper.DATE_SHORT_FORMAT));
			listVo.add(vo);
		}

		// 处理Msg
		ResponseMsg msg = new ResponseMsg();
		msg.setMsg("列表查询成功");
		msg.setResultStatus(ResponseMsg.SUCCESS);
		msg.setTotal(terminal.getTotalRows());
		msg.setRows(listVo);

		// 返回数据
		return ObjectHelper.objectToJson(msg, jsoncallback);
	}

	/**
	 * 
	 * @Title: update
	 * @Description: 更新终端信息状态
	 * @author liuwei
	 * @param id
	 *            终端id
	 * @param state
	 *            状态
	 * @return 处理消息msg json
	 */
	@RequestMapping("/update")
	@UriKey(key = "com.zdsoft.finance.cooperator.update")
	@ResponseBody
	public String update(String id, String state) {
		ResponseMsg msg = new ResponseMsg();
		try {
			cooperatorTerminalService.updateTerminalStatusByIds(id, state);
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
	 * 
	 * @Title: cooperatorAdd
	 * @Description: 终端新增
	 * @author liuwei
	 * @return ModelAndView
	 */
	@RequestMapping("/terminalAdd")
	@UriKey(key = "com.zdsoft.finance.cooperator.terminalAdd")
	public ModelAndView terminalAdd() {
		Map<String, Object> obj = new HashMap<String, Object>();
		CooperatorTerminalVo terminalVo = new CooperatorTerminalVo();
		CooperatorTerminal t = null;
		// 设置终端编码
		try {
			terminalVo.setTerminalCode(CED.resolveExpression("YW000000000002", null));
			t = terminalVo.toPO();
			t.setCreateOrgCd(CED.getLoginUser().getOrgCd());
			// 保存终端
			CooperatorTerminal terminal;
			terminal = cooperatorTerminalService.saveTempTerminal(t);
			terminalVo.setId(terminal.getId());
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("调用平台接口出现错误", e);
		}
		// 转换Vo
		obj.put("terminal", terminalVo);

		// TODO .. 终端权限重新处理
		/*
		 * // 部门数据 List<Map<String, Object>> returnValue = new
		 * ArrayList<Map<String, Object>>(); try { List<OrgTreeDto> orgTreeDtos
		 * = CED.getOrgTree(); returnValue = recursionValue(orgTreeDtos,
		 * returnValue, true); } catch (Exception e) { e.printStackTrace(); }
		 * obj.put("data", ObjectHelper.objectToJson(returnValue));
		 * 
		 * // 人员数据 List<Map<String, Object>> returnValue2 = new
		 * ArrayList<Map<String, Object>>(); try { List<OrgTreeDto> orgTreeDtos
		 * = CED.getOrgTree(); returnValue2 = recursionValue(orgTreeDtos,
		 * returnValue, false); } catch (Exception e) { e.printStackTrace(); }
		 * obj.put("data2", ObjectHelper.objectToJson(returnValue2));
		 */

		obj.put("maxDate", DateHelper.dateToString(new Date(), DateHelper.DATE_SHORT_FORMAT));
		return new ModelAndView("/cooperator/cooperator_edit", obj);
	}

	/**
	 * 
	 * @Title: save
	 * @Description: 终端信息保存
	 * @author liuwei
	 * @param terminalVo
	 *            终端信息Vo
	 * @return msg处理消息
	 */
	@RequestMapping("/save")
	@UriKey(key = "com.zdsoft.finance.cooperator.save")
	@ResponseBody
	public String save(CooperatorTerminalVo terminalVo) {
		ResponseMsg msg = new ResponseMsg();
		CooperatorTerminal cooperatorTerminal = terminalVo.toPO();
		try {
			cooperatorTerminalService.saveOrUpdateTerminal(cooperatorTerminal);
			msg.setMsg("保存终端信息成功");
			msg.setResultStatus(ResponseMsg.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			msg.setMsg("保存终端信息失败");
			msg.setResultStatus(ResponseMsg.APP_ERROR);
		}
		return ObjectHelper.objectToJson(msg);
	}

	/**
	 * 
	 * @Title: edit
	 * @Description: 终端编辑
	 * @author liuwei
	 * @param vo
	 *            终端信息Vo
	 * @return ModelAndView
	 */
	@RequestMapping("/edit")
	@UriKey(key = "com.zdsoft.finance.cooperator.edit")
	public ModelAndView edit(CooperatorTerminalVo vo) {
		Map<String, Object> obj = new HashMap<String, Object>();
		try {
			CooperatorTerminal terminal = cooperatorTerminalService.findOne(vo.getId());
			CooperatorTerminalVo terminalVo = new CooperatorTerminalVo(terminal);
			obj.put("terminal", terminalVo);
			// TODO 终端归属重新处理
			/*
			 * List<Map<String, Object>> returnValue = new ArrayList<Map<String,
			 * Object>>(); List<Map<String, Object>> returnValue2 = new
			 * ArrayList<Map<String, Object>>(); returnValue =
			 * recursionValue(CED.getOrgTree(), returnValue, true); returnValue2
			 * = recursionValue(CED.getOrgTree(), returnValue2, true);
			 * 
			 * obj.put("orgDatas", returnValue); obj.put("empDatas",
			 * returnValue2);
			 */
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("查询终端信息失败", e);
		}
		return new ModelAndView("/cooperator/cooperator_edit", obj);
	}

	/**
	 * 
	 * @Title: del
	 * @Description: 逻辑删除终端信息
	 * @author liuwei
	 * @param jsoncallback
	 * @param id
	 *            终端id
	 * @return 处理消息msg json
	 */
	@RequestMapping("/del")
	@UriKey(key = "com.zdsoft.finance.cooperator.del")
	@ResponseBody
	public String del(String jsoncallback, String id) {
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

	/**
	 * 
	 * @Title: getAllEmps
	 * @Description: 终端归属模糊查询
	 * @author liuwei
	 * @param jsoncallback
	 * @return 模糊查询信息
	 */
	@RequestMapping("/getAllEmps")
	@UriKey(key = "com.zdsoft.finance.cooperator.getAllEmps")
	@ResponseBody
	public String getAllEmps(String jsoncallback) {
		List<Map<String, Object>> returnValue = new ArrayList<Map<String, Object>>();

		try {
			List<OrgTreeDto> orgTreeDtos = CED.getOrgTree();
			returnValue = recursionValue(orgTreeDtos, returnValue, false);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Map<String, Object> returnMap = new HashMap<String, Object>();
		returnMap.put("data", returnValue);
		return ObjectHelper.objectToJson(returnMap, jsoncallback);
	}

	/**
	 * 
	 * @Title: getOrgs
	 * @Description: 终端归属模糊查询
	 * @author liuwei
	 * @param jsoncallback
	 * @return 模糊查询信息
	 */
	@RequestMapping("/getOrgs")
	@UriKey(key = "com.zdsoft.finance.cooperator.getOrgs")
	@ResponseBody
	public String getOrgs(String jsoncallback) {
		List<Map<String, Object>> returnValue = new ArrayList<Map<String, Object>>();

		try {
			List<OrgTreeDto> orgTreeDtos = CED.getOrgTree();
			returnValue = recursionValue(orgTreeDtos, returnValue, true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Map<String, Object> returnMap = new HashMap<String, Object>();
		returnMap.put("data", returnValue);
		return ObjectHelper.objectToJson(returnMap, jsoncallback);
	}

	/**
	 * 
	 * @Title: getTerminalBelong
	 * @Description: 终端归属模糊查询
	 * @author liuwei
	 * @param jsoncallback
	 * @return 模糊查询信息
	 */
	@RequestMapping("/getTerminalBelong")
	@UriKey(key = "com.zdsoft.finance.cooperator.getTerminalBelong")
	@ResponseBody
	public String getTerminalBelong(String jsoncallback) {
		List<Map<String, Object>> returnValue = new ArrayList<Map<String, Object>>();

		try {
			List<OrgTreeDto> orgTreeDtos = CED.getOrgTree();
			returnValue = recursionValue(orgTreeDtos, returnValue, null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Map<String, Object> returnMap = new HashMap<String, Object>();
		returnMap.put("data", returnValue);
		return ObjectHelper.objectToJson(returnMap, jsoncallback);
	}

	/**
	 * 
	 * @Title: recursionValue
	 * @Description: 组装归属返回数据
	 * @author liuwei
	 * @param orgTreeDtos
	 *            组织机构树
	 * @param returnValue
	 *            返回数据
	 * @return 返回数据
	 * @throws Exception
	 */
	public List<Map<String, Object>> recursionValue(List<OrgTreeDto> orgTreeDtos, List<Map<String, Object>> returnValue,
			Boolean isOrg) throws Exception {

		if (ObjectHelper.isNotEmpty(orgTreeDtos)) {
			for (int i = 0; i < orgTreeDtos.size(); i++) {
				// 获取部门
				OrgDto orgDto = CED.getOrgAndSubOrgsById(orgTreeDtos.get(i).getId());

				if (ObjectHelper.isNotEmpty(isOrg)) {
					if (isOrg) {
						// 部门组装
						Map<String, Object> objMap = new HashMap<String, Object>();
						objMap.put("py", orgDto.getOrgNm());
						objMap.put("name", orgDto.getOrgNm());
						objMap.put("code", orgDto.getOrgCd());
						returnValue.add(objMap);
					} else {
						// 部门内人员组装
						List<EmpDto> empDtos = CED.getAllEmpDtoByOrgCd(orgDto.getOrgCd());
						if (ObjectHelper.isNotEmpty(empDtos)) {
							for (int j = 0; j < empDtos.size(); j++) {
								Map<String, Object> empMap = new HashMap<String, Object>();
								empMap.put("py", empDtos.get(j).getEmpNm());
								empMap.put("name", empDtos.get(j).getEmpNm());
								empMap.put("code", empDtos.get(j).getEmpCd());
								returnValue.add(empMap);
							}
						}
					}

					// 子集部门
					List<OrgTreeDto> childOrgTreeDtos = orgTreeDtos.get(i).getChildren();
					recursionValue(childOrgTreeDtos, returnValue, isOrg);
				} else {
					// 部门组装
					Map<String, Object> objMap = new HashMap<String, Object>();
					objMap.put("py", orgDto.getOrgNm());
					objMap.put("name", orgDto.getOrgNm());
					objMap.put("code", orgDto.getOrgCd());
					returnValue.add(objMap);

					// 部门内人员组装
					List<EmpDto> empDtos = CED.getAllEmpDtoByOrgCd(orgDto.getOrgCd());
					if (ObjectHelper.isNotEmpty(empDtos)) {
						for (int j = 0; j < empDtos.size(); j++) {
							Map<String, Object> empMap = new HashMap<String, Object>();
							empMap.put("py", empDtos.get(j).getEmpNm());
							empMap.put("name", empDtos.get(j).getEmpNm());
							empMap.put("code", empDtos.get(j).getEmpCd());
							returnValue.add(empMap);
						}
					}

					// 子集部门
					List<OrgTreeDto> childOrgTreeDtos = orgTreeDtos.get(i).getChildren();
					recursionValue(childOrgTreeDtos, returnValue, null);
				}
			}
		}

		return returnValue;
	}

}
