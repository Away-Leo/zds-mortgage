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
import com.zdsoft.finance.cooperator.entity.OtherCooperater;
import com.zdsoft.finance.cooperator.service.OtherCooperaterService;
import com.zdsoft.finance.cooperator.vo.OtherCooperaterVo;
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
 * @Title: OtherCooperaterController.java
 * @ClassName: OtherCooperaterController
 * @Description: 其他合作方式Controller
 * @author liuwei
 * @date 2017年3月8日 上午10:25:50
 * @version V1.0
 */
@Controller
@RequestMapping("/otherCooperater")
public class OtherCooperaterController extends BaseController {

	@Autowired
	private OtherCooperaterService otherCooperaterService;

	@Autowired
	CED CED;

	/**
	 * 
	 * @Title: initOtherCooperater
	 * @Description: 其他合作单位注册菜单
	 * @author liuwei
	 * @return ModelAndView
	 */
	@RequestMapping("/initOtherCooperater")
	@UriKey(key = "com.zdsoft.finance.otherCooperater.initOtherCooperater")
	@Menu(resource = "com.zdsoft.finance.otherCooperater.initOtherCooperater", label = "其他合作单位", group = "cooperator", order = 1)
	public ModelAndView initOtherCooperater() {
		String empCd = null;
		try {
			empCd = CED.getLoginUser().getEmpCd();
		} catch (Exception e) {
			logger.error("获取当前登录人失败", e);
			e.printStackTrace();
		}
		Map<String, Object> optional = new HashMap<String, Object>();
		optional.put("empCd", empCd);
		return new ModelAndView("/cooperator/othercooperater_list", optional);
	}

	/**
	 * 其他合作单位列表
	 * 
	 * @Title: getOtherCooperater
	 * @Description:
	 * @author zhangchao
	 * @param request
	 * @param jsoncallback
	 * @param pageable
	 * @return 处理消息msg json
	 */
	@RequestMapping("/getOtherCooperater")
	@UriKey(key = "com.zdsoft.finance.otherCooperater.getOtherCooperater")
	@ResponseBody
	public String getOtherCooperater(HttpServletRequest request, String jsoncallback, PageRequest pageable) {

		// 获取页面封装的查询参数
		@SuppressWarnings("unchecked")
		List<QueryObj> queryObjs = (List<QueryObj>) request.getAttribute("listObj");

		// 分页其他合作单位
		Page<OtherCooperater> otherCooperaterPage = otherCooperaterService.findByHqlConditions(pageable, queryObjs);
		List<OtherCooperaterVo> otherCooperaterVos = new ArrayList<OtherCooperaterVo>();
		for (OtherCooperater otherCooperater : otherCooperaterPage.getRecords()) {
			OtherCooperaterVo otherCooperaterVo = new OtherCooperaterVo(otherCooperater, null,
					new String[] { "companyType", "isStop" });
			otherCooperaterVos.add(otherCooperaterVo);
		}

		// 页面返回信息组装
		ResponseMsg msg = new ResponseMsg();
		msg.setMsg("列表查询成功");
		msg.setResultStatus(ResponseMsg.SUCCESS);
		msg.setTotal(otherCooperaterPage.getTotalRows());
		msg.setRows(otherCooperaterVos);

		return ObjectHelper.objectToJson(msg, jsoncallback);
	}

	/**
	 * 新增合作单位
	 * 
	 * @Title: addOtherCooperater
	 * @Description:
	 * @author zhangchao
	 * @return 新增合作单位页面
	 */
	@RequestMapping("/addOtherCooperater")
	@UriKey(key = "com.zdsoft.finance.otherCooperater.addOtherCooperater")
	@ResponseBody
	public ModelAndView addOtherCooperater() {

		ModelMap modelMap = new ModelMap();

		// TODO .. 终端归属权限重新处理
		/*
		 * // 获取部门信息 List<Map<String, Object>> returnValue = new
		 * ArrayList<Map<String, Object>>();
		 * 
		 * try { List<OrgTreeDto> orgTreeDtos = CED.getOrgTree(); returnValue =
		 * recursionValue(orgTreeDtos, returnValue, true); } catch (Exception e)
		 * { e.printStackTrace(); } modelMap.put("data",
		 * ObjectHelper.objectToJson(returnValue));
		 * 
		 * // 人员数据 List<Map<String, Object>> returnValue2 = new
		 * ArrayList<Map<String, Object>>(); try { List<OrgTreeDto> orgTreeDtos
		 * = CED.getOrgTree(); returnValue2 = recursionValue(orgTreeDtos,
		 * returnValue, false); } catch (Exception e) { e.printStackTrace(); }
		 * modelMap.put("data2", ObjectHelper.objectToJson(returnValue2));
		 */

		modelMap.put("maxDate", DateHelper.dateToString(new Date(), DateHelper.DATE_SHORT_FORMAT));
		return new ModelAndView("/cooperator/othercooperater_add", modelMap);
	}

	/**
	 * 删除合作单位
	 * 
	 * @Title: delOtherCooperater
	 * @Description:
	 * @author zhangchao
	 * @param id
	 *            合作单位id
	 * @return 处理消息msg
	 */
	@RequestMapping("/delOtherCooperater")
	@UriKey(key = "com.zdsoft.finance.otherCooperater.delOtherCooperater")
	@ResponseBody
	public ResponseMsg delOtherCooperater(String id) {
		ResponseMsg msg = new ResponseMsg();

		// 执行删除操作
		try {
			OtherCooperater otherCooperater = otherCooperaterService.findOne(id);
			otherCooperaterService.logicDelete(otherCooperater);
			msg.setResultStatus(ResponseMsg.SUCCESS);
			msg.setMsg("删除成功");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("otherCooperater删除失败", e);
			msg.setResultStatus(ResponseMsg.APP_ERROR);
			msg.setMsg(e.getMessage());
		}
		return msg;
	}

	/**
	 * 合作单位查看
	 * 
	 * @Title: findOtherCooperaterById
	 * @Description:
	 * @author zhangchao
	 * @param id
	 *            合作单位ID
	 * @param jsoncallback
	 * @return ModelAndView
	 */
	@RequestMapping("/findOtherCooperaterById")
	@UriKey(key = "com.zdsoft.finance.otherCooperater.findOtherCooperaterById")
	@ResponseBody
	public ModelAndView findOtherCooperaterById(String id, String jsoncallback) {
		Map<String, Object> optional = new HashMap<String, Object>();
		OtherCooperater otherCooperater = null;
		OtherCooperaterVo otherCooperaterVo = null;

		// 执行查询操作
		try {
			otherCooperater = otherCooperaterService.findOne(id);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("latestCustomer查询失败", e);
		}

		// 返回页面对象值组装
		if (ObjectHelper.isNotEmpty(otherCooperater)) {
			otherCooperaterVo = new OtherCooperaterVo(otherCooperater, null,
					new String[] { "enterpriseType", "companyType", "companyBelong", "isStop", "industry" });
		} else {
			logger.error("latestCustomer查询结果为空");
		}

		optional.put("otherCooperaterVo", otherCooperaterVo);
		return new ModelAndView("/cooperator/othercooperater_view", optional);
	}

	/**
	 * 合作单位编辑
	 * 
	 * @Title: editOthercooperater
	 * @Description:
	 * @author zhangchao
	 * @param id
	 *            合作单位ID
	 * @param jsoncallback
	 * @return 合作单位编辑页面
	 */
	@RequestMapping("/editOthercooperater")
	@UriKey(key = "com.zdsoft.finance.otherCooperater.editOthercooperater")
	@ResponseBody
	public ModelAndView editOthercooperater(String id, String jsoncallback) {
		Map<String, Object> optional = new HashMap<String, Object>();
		OtherCooperater otherCooperater = null;
		OtherCooperaterVo otherCooperaterVo = null;

		// 执行查询
		try {
			otherCooperater = otherCooperaterService.findOne(id);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("其他合作单位查询失败", e);
		}

		otherCooperaterVo = new OtherCooperaterVo(otherCooperater);
		optional.put("otherCooperaterVo", otherCooperaterVo);

		// TODO ..终端归属权限重新处理
		/*
		 * // 获取部门信息 List<Map<String, Object>> returnValue = new
		 * ArrayList<Map<String, Object>>();
		 * 
		 * try { List<OrgTreeDto> orgTreeDtos = CED.getOrgTree(); returnValue =
		 * recursionValue(orgTreeDtos, returnValue, true); } catch (Exception e)
		 * { e.printStackTrace(); } optional.put("data",
		 * ObjectHelper.objectToJson(returnValue));
		 * 
		 * // 人员数据 List<Map<String, Object>> returnValue2 = new
		 * ArrayList<Map<String, Object>>(); try { List<OrgTreeDto> orgTreeDtos
		 * = CED.getOrgTree(); returnValue2 = recursionValue(orgTreeDtos,
		 * returnValue, false); } catch (Exception e) { e.printStackTrace(); }
		 * optional.put("data2", ObjectHelper.objectToJson(returnValue2));
		 */
		optional.put("maxDate", DateHelper.dateToString(new Date(), DateHelper.DATE_SHORT_FORMAT));
		return new ModelAndView("/cooperator/othercooperater_edit", optional);
	}

	/**
	 * 保存合作单位
	 * 
	 * @Title: saveOtherCooperater
	 * @Description:
	 * @author zhangchao
	 * @param otherCooperaterVo
	 *            合作单位对象
	 * @return ResponseMsg
	 */
	@RequestMapping("/saveOtherCooperater")
	@UriKey(key = "com.zdsoft.finance.otherCooperater.saveOtherCooperater")
	@ResponseBody
	public ResponseMsg saveOtherCooperater(OtherCooperaterVo otherCooperaterVo) {

		ResponseMsg msg = new ResponseMsg();

		// 转换为Vo
		OtherCooperater otherCooperater = otherCooperaterVo.toPO();
		try {
			otherCooperaterService.saveOrUpdateOtherCooperater(otherCooperater);
			msg.setMsg("保存成功");
			msg.setResultStatus(ResponseMsg.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			msg.setMsg("保存失败");
			msg.setResultStatus(ResponseMsg.APP_ERROR);
		}
		return msg;
	}

	/**
	 * 
	 * @Title: SimpleCodeTurnOn
	 * @Description: 转换simpleCode
	 * @author liuwei
	 * @param fullCode
	 *            编号
	 * @return 转换后的name
	 */
	public String SimpleCodeTurnOn(String fullCode) {
		String fullName = "";
		try {
			fullName = CED.loadSimpleCodeNameByFullCode(fullCode);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return fullName;
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
