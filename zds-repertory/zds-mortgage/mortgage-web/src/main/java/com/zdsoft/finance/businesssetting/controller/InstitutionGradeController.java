package com.zdsoft.finance.businesssetting.controller;

import com.zdsoft.essential.client.service.CED;
import com.zdsoft.essential.dto.OrgTreeDto;
import com.zdsoft.essential.dto.emp.EmpDto;
import com.zdsoft.essential.dto.org.OrgDto;
import com.zdsoft.finance.businesssetting.entity.AuthGrade;
import com.zdsoft.finance.businesssetting.entity.InstitutionGrade;
import com.zdsoft.finance.businesssetting.entity.OrgAuthConn;
import com.zdsoft.finance.businesssetting.entity.OrgIntermediate;
import com.zdsoft.finance.businesssetting.service.AuthGradeService;
import com.zdsoft.finance.businesssetting.service.InstitutionGradeService;
import com.zdsoft.finance.businesssetting.service.OrgAuthConnService;
import com.zdsoft.finance.businesssetting.vo.AuthGradeVo;
import com.zdsoft.finance.businesssetting.vo.OrgAuthConnVo;
import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.framework.core.common.dto.ResponseMsg;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.PageRequest;
import com.zdsoft.framework.core.common.util.ObjectHelper;
import com.zdsoft.framework.core.commweb.annotation.UriKey;
import com.zdsoft.framework.core.commweb.component.BaseController;
import com.zdsoft.framework.cra.annotation.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 版权所有：重庆正大华日软件有限公司
 * 
 * @title InstitutionGradeController.java
 * @className InstitutionGradeController
 * @description 机构评级controller
 * @author LiaoGuoWei
 * @create 2017/2/27 15:09
 * @version V1.0
 **/
@Controller
@RequestMapping("/institutionGrade")
public class InstitutionGradeController extends BaseController {

	@Autowired
	private InstitutionGradeService institutionGradeService;

	@Autowired
	private CED CED;

	@Autowired
	private OrgAuthConnService orgAuthConnService;

	@Autowired
	private AuthGradeService authGradeService;

	/**
	 * @Title: institutionGradePage
	 * @Description: 机构平级页面
	 * @author liaoguowei
	 * @param modelAndView
	 * @return org.springframework.web.servlet.ModelAndView
	 */
	@RequestMapping(value = "/institutionGradePage")
	@UriKey(key = "com.zdsoft.finance.institutionGrade.institutionGradePage")
	@Menu(resource = "com.zdsoft.finance.institutionGrade.institutionGradePage", label = "机构评级", group = "businessSetting", order = 2)
	public ModelAndView institutionGradePage(ModelAndView modelAndView) {
		modelAndView.setViewName("businesssetting/business_institutiongrade_list");
		try {
			EmpDto empDto = CED.getLoginUser();
			modelAndView.addObject("empDto", empDto);
		} catch (Exception e) {
			logger.error("机构平级页面获取当前登录人失败！", e);
			e.printStackTrace();
		}
		return modelAndView;
	}

	/**
	 * @Title: institutionGradeList
	 * @Description: 机构评级列表数据
	 * @author liaoguowei
	 * @param pageRequest
	 * @param institutionCode
	 * @param jsoncallback
	 * @return java.lang.String
	 */
	@RequestMapping(value = "/institutionGradeList")
	@UriKey(key = "com.zdsoft.finance.institutionGrade.institutionGradeList")
	@ResponseBody
	public String institutionGradeList(PageRequest pageRequest, String institutionCode, String jsoncallback) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		try {
			// 写入机构数据
			this.institutionGradeService.writeDataToOrg();
			Page<Map<String, Object>> sourceData = this.institutionGradeService.findPageByCondition(pageRequest,
					institutionCode);
			returnData.put("total", sourceData.getTotalRows());
			returnData.put("rows", sourceData.getRecords());
		} catch (Exception e) {
			logger.error("查询机构评级列表数据出错！", e);
			returnData.put("msg", e.getMessage());
			e.printStackTrace();
		}
		return ObjectHelper.objectToJson(returnData, jsoncallback);
	}

	/**
	 * @Title: jumpEditPage
	 * @Description: 转跳到编辑页面
	 * @author liaoguowei
	 * @param modelAndView
	 * @param institutionCode
	 * @param institutionName
	 * @param type
	 * @return org.springframework.web.servlet.ModelAndView
	 */
	@RequestMapping(value = "/jumpEditPage")
	@UriKey(key = "com.zdsoft.finance.institutionGrade.jumpEditPage")
	public ModelAndView jumpEditPage(ModelAndView modelAndView, String institutionCode, String institutionName,
			String type,String productParentId) {
		modelAndView.setViewName("businesssetting/business_institutiongrade_edit");
		try {
			EmpDto empDto = CED.getLoginUser();
			modelAndView.addObject("empDto", empDto);

			OrgAuthConn sourceData = this.orgAuthConnService.findByOrgCode(institutionCode);
			modelAndView.addObject("orgAuthConn", sourceData);
		} catch (Exception e) {
			logger.error("转跳到编辑页面出错", e);
			e.printStackTrace();
		}
		modelAndView.addObject("institutionCode", institutionCode);
		modelAndView.addObject("institutionName", institutionName);
		modelAndView.addObject("productParentId", productParentId);
		modelAndView.addObject("type", type);
		return modelAndView;
	}

	/**
	 * @Title: findAllOrgSimpleCode
	 * @Description: 机构自定义下拉框
	 * @author liaoguowei
	 * @param jsoncallback
	 */
	@RequestMapping(value = "/findAllOrgSimpleCode")
	@UriKey(key = "com.zdsoft.finance.institutionGrade.findAllOrgSimpleCode")
	@ResponseBody
	public String findAllOrgSimpleCode(String jsoncallback) {
		List<Map<String, Object>> returnData = new ArrayList<Map<String, Object>>();
		try {
			// 写入机构数据
			this.institutionGradeService.writeDataToOrg();
			List<OrgIntermediate> sourceData = this.institutionGradeService.findAllOrg();
			for (OrgIntermediate temp : sourceData) {
				Map<String, Object> rowData = new HashMap<String, Object>();
				rowData.put("id", temp.getOrgCd());
				rowData.put("text", temp.getOrgNm());
				returnData.add(rowData);
			}
		} catch (Exception e) {
			logger.error("获取机构下拉框出错", e);
			e.printStackTrace();
		}

		return ObjectHelper.objectToJson(returnData, jsoncallback);
	}

	/**
	 * @Title: findHanderSimpleCode
	 * @Description: 查找操作人
	 * @author liaoguowei
	 * @param jsoncallback
	 * @return java.lang.String
	 */
	@RequestMapping(value = "/findHanderSimpleCode")
	@UriKey(key = "com.zdsoft.finance.institutionGrade.findHanderSimpleCode")
	@ResponseBody
	public String findHanderSimpleCode(String jsoncallback) {
		List<Map<String, Object>> returnData = new ArrayList<Map<String, Object>>();
		try {
			List<Map<String, Object>> sourceData = this.institutionGradeService.findAllHander();
			for (Map<String, Object> temp : sourceData) {
				Map<String, Object> rowData = new HashMap<String, Object>();
				rowData.put("id", temp.get("handerCode"));
				rowData.put("text", temp.get("handerName"));
				returnData.add(rowData);
			}
		} catch (Exception e) {
			logger.error("查询所有操作人出错", e);
			e.printStackTrace();
		}
		return ObjectHelper.objectToJson(returnData, jsoncallback);
	}

	/**
	 * @Title: findHistoryData
	 * @Description: 更改历史数据
	 * @author liaoguowei
	 * @param pageRequest
	 * @param jsoncallback
	 * @param institutionCode
	 * @return java.lang.String
	 */
	@RequestMapping(value = "/findHistoryData")
	@UriKey(key = "com.zdsoft.finance.institutionGrade.findHistoryData")
	@ResponseBody
	public String findHistoryData(PageRequest pageRequest, String jsoncallback, String institutionCode) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		try {
			Page<InstitutionGrade> institutionGradePage = this.institutionGradeService.findPageList(pageRequest,
					institutionCode);
			returnData.put("total", institutionGradePage.getTotalRows());
			returnData.put("rows", institutionGradePage.getRecords());
		} catch (BusinessException e) {
			logger.error("查找历史列表数据出错", e);
			e.printStackTrace();
		}
		return ObjectHelper.objectToJson(returnData, jsoncallback);
	}

	/**
	 * @Title: saveOrUpdateInstitution
	 * @Description: 保存或更新机构平级
	 * @author liaoguowei
	 * @param orgAuthConnVo
	 * @return com.zdsoft.framework.core.common.dto.ResponseMsg
	 */
	@RequestMapping(value = "/saveOrUpdateInstitution", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@UriKey(key = "com.zdsoft.finance.institutionGrade.saveOrUpdateInstitution")
	@ResponseBody
	public ResponseMsg saveOrUpdateInstitution(OrgAuthConnVo orgAuthConnVo) {
		ResponseMsg msg = new ResponseMsg();
		try {
			OrgAuthConn sourceData = this.orgAuthConnService.saveOrUpdateOrgAuthConn(orgAuthConnVo.toPo());
			msg.setResultStatus(ResponseMsg.SUCCESS);
		} catch (Exception e) {
			msg.setMsg("机构评级保存失败,请检查是否配置授权等级");
			msg.setResultStatus(ResponseMsg.APP_ERROR);

			logger.error("机构评级保存或更新出错", e);
			e.printStackTrace();
		}
		return msg;
	}

	/**
	 * @Title: institutionAuthPage
	 * @Description: 机构授权查询页面
	 * @author liaoguowei
	 * @param modelAndView
	 * @return org.springframework.web.servlet.ModelAndView
	 */
	@RequestMapping(value = "/institutionAuthPage")
	@UriKey(key = "com.zdsoft.finance.institutionAuthPage")
	@Menu(resource = "com.zdsoft.finance.institutionGrade.institutionAuthPage", label = "机构授权查询", group = "businessSetting", order = 3)
	public ModelAndView institutionAuthPage(ModelAndView modelAndView) {
		modelAndView.setViewName("businesssetting/business_institutionauth_list");
		return modelAndView;
	}

	/**
	 * @Title: institutionAuthList
	 * @Description: 机构授权查询列表数据
	 * @author liaoguowei
	 * @param pageRequest
	 * @param authGradeVo
	 * @param jsoncallback
	 * @return java.lang.String
	 */
	@RequestMapping(value = "/institutionAuthList")
	@UriKey(key = "com.zdsoft.finance.institutionGrade.institutionAuthList")
	@ResponseBody
	public String institutionAuthList(PageRequest pageRequest, AuthGradeVo authGradeVo, String jsoncallback) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		try {
			Page<AuthGrade> sourceData = this.institutionGradeService.findInstitutionAuthPage(pageRequest,
					authGradeVo.toPo());
			returnData.put("total", sourceData.getTotalRows());
			returnData.put("rows", sourceData.getRecords());
		} catch (Exception e) {
			logger.error("机构授权查询出错", e);
			e.printStackTrace();
		}
		return ObjectHelper.objectToJson(returnData, jsoncallback);
	}

	/**
	 * 
	 * @Title: getAllEmps
	 * @Description: 获取所有人
	 * @author liuwei
	 * @param jsoncallback
	 * @return 所有人Json
	 */
	@RequestMapping("/getAllEmps")
	@UriKey(key = "com.zdsoft.finance.institutionGrade.getAllEmps")
	@ResponseBody
	public String getAllEmps(String jsoncallback) {
		List<Map<String, Object>> returnValue = new ArrayList<Map<String, Object>>();

		try {
			List<OrgTreeDto> orgTreeDtos = CED.getOrgTree();
			returnValue = recursionValue(orgTreeDtos, returnValue);
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
	public List<Map<String, Object>> recursionValue(List<OrgTreeDto> orgTreeDtos, List<Map<String, Object>> returnValue)
			throws Exception {

		if (ObjectHelper.isNotEmpty(orgTreeDtos)) {
			for (int i = 0; i < orgTreeDtos.size(); i++) {
				// 获取部门
				OrgDto orgDto = CED.getOrgAndSubOrgsById(orgTreeDtos.get(i).getId());

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
				recursionValue(childOrgTreeDtos, returnValue);
			}
		}

		return returnValue;
	}
}
