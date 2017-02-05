package com.zdsoft.finance.cooperator.controller;

import java.util.ArrayList;
import java.util.Date;
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
import com.zdsoft.finance.cooperator.entity.OtherCooperater;
import com.zdsoft.finance.cooperator.service.OtherCooperaterService;
import com.zdsoft.finance.cooperator.vo.OtherCooperaterVo;
import com.zdsoft.framework.core.common.dto.ResponseMsg;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.PageRequest;
import com.zdsoft.framework.core.common.util.ObjectHelper;
import com.zdsoft.framework.core.commweb.annotation.UriKey;
import com.zdsoft.framework.core.commweb.component.BaseController;
import com.zdsoft.framework.cra.annotation.Menu;

/**
 * 其他合作方
 * @author zhangchao
 *
 */
@Controller
@RequestMapping("/otherCooperater")
public class OtherCooperaterController  extends BaseController{
	
	@Autowired
	private OtherCooperaterService otherCooperaterService;
	
	@Autowired
	private com.zdsoft.essential.client.service.CED CED;
	
	/**
	 * 其他合作单位注册菜单
	 * @return 客户管理页面
	 */
	@RequestMapping("/initOtherCooperater")
	@UriKey(key = "com.zdsoft.finance.otherCooperater.initOtherCooperater")
	@Menu(resource = "com.zdsoft.finance.otherCooperater.initOtherCooperater", label = "其他合作单位", group = "cooperator", order = 1)
	public ModelAndView initOtherCooperater() {
		String EmpCd = null;
		try {
			EmpCd = CED.getLoginUser().getEmpCd();
		} catch (Exception e) {
			logger.error("获取当前登录人失败", e);
			e.printStackTrace();
		}
		Map<String, Object> optional = new HashMap<String, Object>();
		optional.put("EmpCd", EmpCd);
		return new ModelAndView("/cooperator/othercooperater_list" , optional);
	}
	
	/**
	 * 其他合作单位列表
	 * @param request
	 * @param jsoncallback
	 * @param pageable
	 * @return
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
			OtherCooperaterVo otherCooperaterVo = new OtherCooperaterVo(otherCooperater);
			otherCooperaterVo = SimpleCodeToName(otherCooperaterVo);
			if(otherCooperaterVo.getIsStop().equals("1")){
				otherCooperaterVo.setIsStop("否");
			}
			if(otherCooperaterVo.getIsStop().equals("0")){
				otherCooperaterVo.setIsStop("是");
			}
			otherCooperaterVos.add(otherCooperaterVo);
		}
		
		//页面返回信息组装
		ResponseMsg msg = new ResponseMsg();
		msg.setMsg("列表查询成功");
		msg.setResultStatus(ResponseMsg.SUCCESS);
		msg.setTotal(otherCooperaterPage.getTotalRows());
		msg.setRows(otherCooperaterVos);

		return ObjectHelper.objectToJson(msg, jsoncallback);
	}
	
	/**
	 * 新增合作单位
	 * @return
	 */
	@RequestMapping("/addOtherCooperater")
	@UriKey(key = "com.zdsoft.finance.otherCooperater.addOtherCooperater")
	@ResponseBody
	public ModelAndView addOtherCooperater() {
		return new ModelAndView("/cooperator/othercooperater_add");
	}
	
	/**
	 * 删除合作单位
	 * @param id 合作单位id
	 * @return
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
	 * @param id 合作单位ID
	 * @param jsoncallback
	 * @return
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
		
		//返回页面对象值组装
		if(ObjectHelper.isNotEmpty(otherCooperater)){
			otherCooperaterVo = new OtherCooperaterVo(otherCooperater);
			if(ObjectHelper.isNotEmpty(otherCooperater.getDetailedProvince())){
				String code = otherCooperater.getDetailedProvince() + "," + otherCooperater.getDetailedCity() + "," + otherCooperater.getDetailedDistrict();
				otherCooperaterVo.setDetailedCode(code);
			}
			otherCooperaterVo = SimpleCodeToName(otherCooperaterVo);
			if(otherCooperaterVo.getIsStop().equals("1")){
				otherCooperaterVo.setIsStop("否");
			}
			if(otherCooperaterVo.getIsStop().equals("0")){
				otherCooperaterVo.setIsStop("是");
			}
		}else{
			logger.error("latestCustomer查询结果为空");
		}
		
		optional.put("otherCooperaterVo", otherCooperaterVo);
		return new ModelAndView("/cooperator/othercooperater_view", optional);
	}
	
	/**
	 * 合作单位编辑
	 * @param id 合作单位ID
	 * @param jsoncallback
	 * @return
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
			logger.error("latestCustomer查询失败", e);
		}
		
		if(ObjectHelper.isNotEmpty(otherCooperater)){
			otherCooperaterVo = new OtherCooperaterVo(otherCooperater);
			if(ObjectHelper.isNotEmpty(otherCooperater.getDetailedProvince())){
				String code = otherCooperater.getDetailedProvince() + "," + otherCooperater.getDetailedCity() + "," + otherCooperater.getDetailedDistrict();
				otherCooperaterVo.setDetailedCode(code);
			}
		}else{
			logger.error("latestCustomer查询结果为空");
		}
		
		optional.put("otherCooperaterVo", otherCooperaterVo);
		return new ModelAndView("/cooperator/othercooperater_edit", optional);
	}
	
	/**
	 * 保存合作单位
	 * @param otherCooperaterVo 合作单位对象
	 * @return
	 */
	@RequestMapping("/saveOtherCooperater")
	@UriKey(key = "com.zdsoft.finance.otherCooperater.saveOtherCooperater")
	@ResponseBody
	public ResponseMsg saveOtherCooperater(OtherCooperaterVo otherCooperaterVo) {
		OtherCooperater otherCooperater = null;
		ResponseMsg msg = new ResponseMsg();
		Map<String, Object> otherCooperaterMap = new HashMap<String, Object>();
		
//		if(ObjectHelper.isNotEmpty(otherCooperaterVo.getDetailedCode())){
//			String[] code = otherCooperaterVo.getDetailedCode().split(",");
//			otherCooperaterVo.setDetailedProvince(code[0]);
//			otherCooperaterVo.setDetailedCity(code[1]);
//			otherCooperaterVo.setDetailedDistrict(code[2]);
//		}

		// 将Vo转成Po
		otherCooperater = otherCooperaterVo.toPO();
		
		OtherCooperaterVo newotherCooperaterVo = null;
		OtherCooperater newotherCooperater = new OtherCooperater();

		// 执行保存
		try {
			if(ObjectHelper.isNotEmpty(otherCooperater.getId())){
				newotherCooperater = otherCooperaterService.findOne(otherCooperater.getId());
				Date date = new Date();
				otherCooperater.setUpdateTime(date);
				otherCooperater.setCreateTime(newotherCooperater.getCreateTime());
				otherCooperater.setCreateBy(CED.getLoginUser().getEmpCd());
				otherCooperater.setCreateOrgCd(CED.getLoginUser().getOrgCd());
				newotherCooperater = otherCooperaterService.updateEntity(otherCooperater);
			}else{
				Date date = new Date();
				otherCooperater.setCreateTime(date);
				otherCooperater.setCreateBy(CED.getLoginUser().getEmpCd());
				otherCooperater.setCreateOrgCd(CED.getLoginUser().getOrgCd());
				newotherCooperater = otherCooperaterService.saveEntity(otherCooperater);
			}
			newotherCooperaterVo = new OtherCooperaterVo(newotherCooperater);
			otherCooperaterMap.put("newotherCooperaterVo", newotherCooperaterVo);
			msg.setResultStatus(ResponseMsg.SUCCESS);
			msg.setOptional(otherCooperaterMap);
			msg.setMsg("保存成功");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("otherCooperater保存失败", e);
			msg.setResultStatus(ResponseMsg.APP_ERROR);
			msg.setMsg(e.getMessage());
		}
		return msg;
	}
	
	@RequestMapping("/findContactCompanyBelongName")
	@UriKey(key = "com.zdsoft.finance.otherCooperater.findContactCompanyBelongName")
	@ResponseBody
	public ResponseMsg findContactCompanyBelongName(String contactCompanyBelong) {
		String contactCompanyBelongName = null;
		ResponseMsg msg = new ResponseMsg();
		Map<String, Object> otherCooperaterMap = new HashMap<String, Object>();
		
		try{
			if(contactCompanyBelong.equals("机构")){
				contactCompanyBelongName = CED.getLoginUser().getCompanyNm();
			}else if(contactCompanyBelong.equals("部门")){
				contactCompanyBelongName = CED.getLoginUser().getDepartmentName();
			}else if(contactCompanyBelong.equals("人员")){
				contactCompanyBelongName = CED.getLoginUser().getEmpNm();
			}
			OtherCooperaterVo otherCooperaterVo = new OtherCooperaterVo();
			otherCooperaterVo.setContactCompanyBelongName(contactCompanyBelongName);
			otherCooperaterMap.put("otherCooperaterVo", otherCooperaterVo);
			msg.setResultStatus(ResponseMsg.SUCCESS);
			msg.setOptional(otherCooperaterMap);
			msg.setMsg("查询成功");
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("查询失败", e);
			msg.setResultStatus(ResponseMsg.APP_ERROR);
			msg.setMsg(e.getMessage());
		}
		return msg;
	}
	
	/**
	 * SimpleCode值转换
	 * @param latestCustomerVo 合作单位对象
	 * @return
	 */
	public OtherCooperaterVo SimpleCodeToName(OtherCooperaterVo otherCooperaterVo){
		try {
			if(ObjectHelper.isNotEmpty(otherCooperaterVo.getCompanyType())){
				otherCooperaterVo.setCompanyType(SimpleCodeTurnOn(otherCooperaterVo.getCompanyType()));
			}
			if(ObjectHelper.isNotEmpty(otherCooperaterVo.getType())){
				otherCooperaterVo.setType(SimpleCodeTurnOn(otherCooperaterVo.getType()));
			}
			if(ObjectHelper.isNotEmpty(otherCooperaterVo.getContactCompanyBelong())){
				otherCooperaterVo.setContactCompanyBelong(SimpleCodeTurnOn(otherCooperaterVo.getContactCompanyBelong()));
			}
			
//			if(ObjectHelper.isNotEmpty(otherCooperaterVo.getDetailedProvince())){
//				otherCooperaterVo.setDetailedProvince(SimpleCodeTurnOn(otherCooperaterVo.getDetailedProvince()));
//			}
//			if(ObjectHelper.isNotEmpty(otherCooperaterVo.getDetailedCity())){
//				otherCooperaterVo.setDetailedCity(SimpleCodeTurnOn(otherCooperaterVo.getDetailedCity()));
//			}
//			if(ObjectHelper.isNotEmpty(otherCooperaterVo.getDetailedDistrict())){
//				otherCooperaterVo.setDetailedDistrict(SimpleCodeTurnOn(otherCooperaterVo.getDetailedDistrict()));
//			}
			if(ObjectHelper.isNotEmpty(otherCooperaterVo.getIndustry())){
				otherCooperaterVo.setIndustry(SimpleCodeTurnOn(otherCooperaterVo.getIndustry()));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return otherCooperaterVo;
	}
	
	public String SimpleCodeTurnOn(String fullCode){
		String fullName = "";
		try {
			fullName = CED.loadSimpleCodeNameByFullCode(fullCode);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return fullName;
	}
}
