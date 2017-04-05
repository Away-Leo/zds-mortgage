package com.zdsoft.finance.businesssetting.controller;

import com.zdsoft.essential.client.service.CED;
import com.zdsoft.essential.dto.org.OrgDto;
import com.zdsoft.finance.businesssetting.entity.InComeBody;
import com.zdsoft.finance.businesssetting.service.InComeBodyService;
import com.zdsoft.finance.businesssetting.vo.InComeBodyVo;
import com.zdsoft.framework.core.common.dto.ResponseMsg;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.PageRequest;
import com.zdsoft.framework.core.common.util.DateHelper;
import com.zdsoft.framework.core.common.util.ObjectHelper;
import com.zdsoft.framework.core.commweb.annotation.UriKey;
import com.zdsoft.framework.core.commweb.component.BaseController;
import com.zdsoft.framework.cra.annotation.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * 版权所有：重庆正大华日软件有限公司
 * 
 * @title InComeBodyController.java
 * @className InComeBodyController
 * @description 收款主体controller
 * @author LiaoGuoWei
 * @create 2017/2/27 15:06
 * @version V1.0
 **/
@RequestMapping("/inComeBody")
@Controller
public class InComeBodyController extends BaseController {
	@Autowired
	private InComeBodyService inComeBodyService;
	@Autowired
	private CED CED;

	/**
	 * @Title: initInComeBody
	 * @Description: 收款主体初始化
	 * @author liaoguowei
	 * @param
	 * @return org.springframework.web.servlet.ModelAndView @throws
	 */
	@RequestMapping("/initInComeBody")
	@UriKey(key = "com.zdsoft.finance.parameter.initInComeBody")
	@Menu(resource = "com.zdsoft.finance.parameter.initInComeBody", label = "收款主体", group = "businessSetting", order = 1)
	public ModelAndView initInComeBody() {
		ModelMap modelMap=new ModelMap();
		try{
			String empOrgCd=CED.getLoginUser().getOrgCd();
			modelMap.put("empOrgCd",empOrgCd);
		}catch (Exception e){
			logger.error("收款主体页面获取当前登陆人信息失败",e);
			e.printStackTrace();
		}
		return new ModelAndView("businesssetting/incomebody_list",modelMap);
	}

	/**
	 * 
	 * @Title: getSimpCodeAll
	 * @Description: 查询收款主体下拉列表数据
	 * @author xiangjx
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/getSimpCodeAll")
	@UriKey(key = "com.zdsoft.finance.parameter.getSimpCodeAll")
	@ResponseBody
	public String getSimpCodeAll(HttpServletRequest request) {
		List<InComeBody> list = null;
		List<InComeBodyVo> listVo = new ArrayList<>();
		logger.info("查询收款主体下拉列表数据  ");
		try {
			list = inComeBodyService.findAllInComeBodyBySimpCode();
			for (InComeBody inComeBody : list) {
				InComeBodyVo vo = new InComeBodyVo();
				vo.setId(inComeBody.getId());
				vo.setName(inComeBody.getInBody());
				listVo.add(vo);
			}
		} catch (Exception e) {
			logger.debug("查询收款主体下拉列表数据  出错了");
			e.printStackTrace();
		}
		return ObjectHelper.objectToJson(listVo);
	}
	
	/**
	 * 
	 * @Title: getInComeBodyOrgList 
	 * @Description: 查询当前机构收款主体
	 * @author jincheng 
	 * @param orgCode
	 * @return
	 */
	@RequestMapping(value = "/getInComeBodyOrgList")
	@UriKey(key = "com.zdsoft.finance.parameter.getInComeBodyOrgList")
	@ResponseBody
	public String getInComeBodyOrgList(String orgCode) {
		List<InComeBodyVo> listVo = new ArrayList<>();
		try {
			List<InComeBody>	list = inComeBodyService.findByOrgId(orgCode);
			for (InComeBody inComeBody : list) {
				InComeBodyVo vo = new InComeBodyVo();
				vo.setId(inComeBody.getId());
				vo.setName(inComeBody.getInBody());
				listVo.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ObjectHelper.objectToJson(listVo);
	}


	/**
	 * @Title: getInComeBody
	 * @Description: 收款主体列表
	 * @author liaoguowei
	 * @param vo 查询条件
	 * @param jsoncallback
	 * @param pageable
	 * @return java.lang.String @throws
	 */
	@RequestMapping("/inComeBody")
	@UriKey(key = "com.zdsoft.finance.parameter.getInComeBody")
	@ResponseBody
	public String getInComeBody(InComeBodyVo vo, String jsoncallback, PageRequest pageable) {
		ResponseMsg msg = new ResponseMsg();
		try {
			// 分页抵押权人
			Page<InComeBody> inComeBodyPage = inComeBodyService.findIncomBodyByCondition(pageable, vo.toPo());
			List<InComeBodyVo> inComeBodyVos = new ArrayList<InComeBodyVo>();
			if(ObjectHelper.isNotEmpty(inComeBodyPage)&&ObjectHelper.isNotEmpty(inComeBodyPage.getRecords())&&inComeBodyPage.getRecords().size()>0){
				for (InComeBody temp:inComeBodyPage.getRecords()) {
					InComeBodyVo inComeBodyVo = new InComeBodyVo(temp);
					inComeBodyVo.setCreateTimeName(DateHelper.dateToString(temp.getCreateTime(), DateHelper.DATE_SHORT_FORMAT));
					OrgDto orgDto = CED.loadOrganizationByCode(temp.getOrgId());
					if (ObjectHelper.isNotEmpty(orgDto) && ObjectHelper.isNotEmpty(orgDto.getOrgNm())) {
						inComeBodyVo.setOrgName(orgDto.getOrgNm());
					}
					inComeBodyVos.add(inComeBodyVo);
				}
			}
			msg.setMsg("列表查询成功");
			msg.setResultStatus(ResponseMsg.SUCCESS);
			msg.setTotal(inComeBodyPage.getTotalRows());
			msg.setRows(inComeBodyVos);
		} catch (Exception e) {
			msg.setMsg("列表查询失败");
			msg.setResultStatus(ResponseMsg.APP_ERROR);
			logger.error("列表查询失败", e);
		}

		return ObjectHelper.objectToJson(msg, jsoncallback);
	}

	/**
	 * @Title: updateInComeBody
	 * @Description: 更新收款主体
	 * @author liaoguowei
	 * @param inComeBodyVo
	 * @param jsoncallback
	 * @param pageable
	 * @return java.lang.String @throws
	 */
	@RequestMapping("/updateInComeBody")
	@UriKey(key = "com.zdsoft.finance.parameter.updateInComeBody")
	@ResponseBody
	public String updateInComeBody(InComeBodyVo inComeBodyVo, String jsoncallback, PageRequest pageable) {
		ResponseMsg msg = new ResponseMsg();
		try {
			InComeBody inComeBody = inComeBodyVo.toPo();
			inComeBodyService.saveOrUpdateInComeBody(inComeBody);
			msg.setMsg("操作成功！");
			msg.setResultStatus(ResponseMsg.SUCCESS);

		} catch (Exception e) {
			logger.error("更新收款主体失败", e);
			msg.setMsg("操作失败！");
			msg.setResultStatus(ResponseMsg.APP_ERROR);
			e.printStackTrace();
		}
		return ObjectHelper.objectToJson(msg, jsoncallback);
	}

	/**
	 * @Title: deleteInComeBody
	 * @Description: 删除收款主体
	 * @author liaoguowei
	 * @param inComeBody
	 * @param jsoncallback
	 * @param pageable
	 * @return java.lang.String @throws
	 */
	@RequestMapping("/deleteInComeBody")
	@UriKey(key = "com.zdsoft.finance.parameter.deleteInComeBody")
	@ResponseBody
	public String deleteInComeBody(InComeBody inComeBody, String jsoncallback, PageRequest pageable) {
		ResponseMsg msg = new ResponseMsg();
		try {
			inComeBodyService.deleteInComeBody(inComeBody.getId());
			msg.setMsg("操作成功！");
			msg.setResultStatus(ResponseMsg.SUCCESS);

		} catch (Exception e) {
			logger.error("删除收款主体失败", e);
			e.printStackTrace();
			msg.setMsg("操作失败！");
			msg.setResultStatus(ResponseMsg.APP_ERROR);
		}
		return ObjectHelper.objectToJson(msg, jsoncallback);
	}

}
