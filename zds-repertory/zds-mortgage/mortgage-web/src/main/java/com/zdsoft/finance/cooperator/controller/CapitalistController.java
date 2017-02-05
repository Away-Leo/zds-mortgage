package com.zdsoft.finance.cooperator.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zdsoft.essential.client.service.CED;
import com.zdsoft.finance.common.base.QueryObj;
import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.finance.cooperator.entity.Capitalist;
import com.zdsoft.finance.cooperator.entity.CapitalistTrust;
import com.zdsoft.finance.cooperator.entity.CooperatorBank;
import com.zdsoft.finance.cooperator.service.CapitalistService;
import com.zdsoft.finance.cooperator.service.CapitalistTrustService;
import com.zdsoft.finance.cooperator.service.CooperatorBankService;
import com.zdsoft.finance.cooperator.vo.CapitalistTrustVo;
import com.zdsoft.finance.cooperator.vo.CapitalistVo;
import com.zdsoft.framework.core.common.dto.ResponseMsg;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.PageRequest;
import com.zdsoft.framework.core.common.util.ObjectHelper;
import com.zdsoft.framework.core.commweb.annotation.UriKey;
import com.zdsoft.framework.core.commweb.component.BaseController;
import com.zdsoft.framework.cra.annotation.Menu;

/**
 * 资方
 * 
 * @author Hisa
 *
 */
@Controller
@RequestMapping("/capitalist")
public class CapitalistController extends BaseController {

	@Autowired
	CapitalistService capitalistService;
	@Autowired
	CapitalistTrustService capitalistTrustService;
	@Autowired
	CooperatorBankService cooperatorBankService;
	@Autowired
	CED CED;

	/**
	 * 合作方联系人信息列表
	 * 
	 * @return
	 */
	@RequestMapping("/initCapitalist")
	@UriKey(key = "com.zdsoft.finance.cooperator.capitalist.initCapitalist")
	@Menu(resource = "com.zdsoft.finance.cooperator.capitalist.initCapitalist", label = "资方管理", group = "cooperator", order = 3)
	public ModelAndView initContactsInfo() {
		ModelAndView model = new ModelAndView("/cooperator/capitalist_list");
		return model;
	}

	/**
	 * 返回资方list
	 * 
	 * @param jsoncallback
	 * @return
	 */
	@RequestMapping("/capitalistSimpleCode")
	@UriKey(key = "com.zdsoft.finance.cooperator.capitalist.capitalistSimpleCode")
	@ResponseBody
	public String capitalistSimpleCode(String jsoncallback) {
		List<Capitalist> list = capitalistService.findLogicList("1");
		List<CapitalistVo> listVo = new ArrayList<CapitalistVo>();
		for (Capitalist info : list) {
			CapitalistVo vo = new CapitalistVo(info);
			listVo.add(vo);
		}
		return ObjectHelper.objectToJson(listVo, jsoncallback);
	}
	/**
	 * 返回根据部门的资方list
	 * 
	 * @param jsoncallback
	 * @return
	 */
	@RequestMapping("/capitalistOrgSimpleCode")
	@UriKey(key = "com.zdsoft.finance.cooperator.capitalist.capitalistOrgSimpleCode")
	@ResponseBody
	public String capitalistOrgSimpleCode(String jsoncallback,String createOrgCd) {
		List<Capitalist> list = capitalistService.findLogicOrgList(createOrgCd, "0");
		List<CapitalistVo> listVo = new ArrayList<CapitalistVo>();
		for (Capitalist info : list) {
			CapitalistVo vo = new CapitalistVo(info);
			listVo.add(vo);
		}
		return ObjectHelper.objectToJson(listVo, jsoncallback);
	}
	/**
	 * 新增页Tab
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/tab")
	@UriKey(key = "com.zdsoft.finance.cooperator.capitalist.tab")
	public ModelAndView tab(String capitalistId, String operationType, String capitalistType, String cooperatorName)
			throws Exception {
		ModelAndView modelAndView = new ModelAndView("/cooperator/capitalist_edit_tab");
		Capitalist ca = new Capitalist();
		if ("add".equals(operationType)) {
			ca.setCooperatorName(cooperatorName);
			ca.setCapitalistType(capitalistType);
			CooperatorBank bank = new CooperatorBank();
			CooperatorBank banks = cooperatorBankService.saveEntity(bank);
			CapitalistTrust trust = new CapitalistTrust();
			CapitalistTrust captrust = capitalistTrustService.saveEntity(trust);
			ca.setCapitalistTrust(captrust);// 进入页面初始化数据
			ca.setCooperatorBank(banks);
			ca.setCreateOrgCd(CED.getLoginUser().getOrgCd());
			ca.setLogicDelelte("1");
			Capitalist cap = capitalistService.saveEntity(ca);
			modelAndView.addObject("capitalist", cap);
		} else {
			ca.setId(capitalistId);
			modelAndView.addObject("capitalist", ca);
		}
		modelAndView.addObject("operationType", operationType);
		return modelAndView;
	}

	/**
	 * 列表数据展示
	 * 
	 * @param request
	 * @param jsoncallback
	 * @param pageable
	 * @return
	 */
	@RequestMapping("/getCapitalist")
	@UriKey(key = "com.zdsoft.finance.cooperator.capitalist.getCapitalist")
	@ResponseBody
	public String getCapitalist(HttpServletRequest request, String jsoncallback, PageRequest pageable) {

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
		Page<Capitalist> infos = capitalistService.findByHqlConditions(pageable, queryObjs);
		List<Capitalist> list = infos.getRecords();
		List<CapitalistVo> listVo = new ArrayList<CapitalistVo>();
		for (Capitalist info : list) {
			CapitalistVo vo = new CapitalistVo(info, null, new String[] { "capitalistType" });
			listVo.add(vo);
		}
		ResponseMsg msg = new ResponseMsg();
		msg.setMsg("列表查询成功");
		msg.setResultStatus(ResponseMsg.SUCCESS);
		msg.setTotal(infos.getTotalRows());
		msg.setRows(listVo);
		return ObjectHelper.objectToJson(msg, jsoncallback);
	}

	/**
	 * 联系人编辑
	 * 
	 * @return
	 * @throws BusinessException
	 */
	@RequestMapping("/edit")
	@UriKey(key = "com.zdsoft.finance.cooperator.capitalist.edit")
	public ModelAndView capitalistEdit(String capitalistId, String operationType) throws BusinessException {
		ModelAndView modelAndView = null;
		try {
			Capitalist info = capitalistService.findOne(capitalistId);
			if (info.getCapitalistType().equals("bank")) {
				// 银行
				modelAndView = new ModelAndView("/cooperator/capitalist_edit_bank");
			} else {
				// 非银
				modelAndView = new ModelAndView("/cooperator/capitalist_edit_nonsilver");
			}
			if (!"add".equals(operationType)) {
				CapitalistTrust trust = capitalistTrustService.findOne(info.getCapitalistTrust().getId());
				modelAndView.addObject("trust", trust);
			}
			CapitalistVo capitalistVo = new CapitalistVo(info);
			modelAndView.addObject("capitalist", capitalistVo);
			modelAndView.addObject("capitalistId", capitalistId);
			modelAndView.addObject("operationType", operationType);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return modelAndView;
	}

	/**
	 * 资方新增弹窗
	 * 
	 * @return
	 * @throws BusinessException
	 */
	@RequestMapping("/dialog")
	@UriKey(key = "com.zdsoft.finance.cooperator.capitalist.dialog")
	public ModelAndView dialog() throws BusinessException {
		ModelAndView modelAndView = new ModelAndView("/cooperator/capitalist_dialog");
		return modelAndView;
	}

	/**
	 * 保存
	 * 
	 * @param jsoncallback
	 * @param infoVo
	 * @return
	 * @throws BusinessException
	 */
	@RequestMapping("/save")
	@UriKey(key = "com.zdsoft.finance.cooperator.capitalist.save")
	@ResponseBody
	public String save(CapitalistVo infoVo, CapitalistTrustVo trustVo) throws BusinessException {
		ResponseMsg msg = new ResponseMsg();
		Capitalist cap = capitalistService.findOne(infoVo.getId());
		cap.setLogicDelelte("0");
		cap.setCooperatorShortName(infoVo.getCooperatorShortName());// 资方简称
		cap.setIsStop(infoVo.getIsStop());// 是否停用
		cap.setCooperatorType(infoVo.getCooperatorType());// 资方类别
		cap.setCooperatorAddress(infoVo.getCooperatorAddress());
		cap.setRegionCode(infoVo.getRegionCode());
		cap.setFoundDate(infoVo.getFoundDate());
		cap.setLegalPerson(infoVo.getLegalPerson());
		cap.setDutyParagraph(infoVo.getDutyParagraph());
		cap.setIndustry(infoVo.getIndustry());
		cap.setIsStop(infoVo.getIsStop());
		cap.setCapitalistCategory(infoVo.getCapitalistCategory());
		cap.setRemark(infoVo.getRemark());
		cap.setContactTelNumber(infoVo.getContactTelNumber());
		cap.setIsHasBankAgreement(infoVo.getIsHasBankAgreement());
		capitalistService.updateEntity(cap);
		// 更新银行字段
		if (StringUtils.isNotEmpty(infoVo.getBankAccountShow())) {
			CooperatorBank bank = cooperatorBankService.findOne(cap.getCooperatorBank().getId());
			bank.setBankAccount(infoVo.getBankAccountShow());
			cooperatorBankService.updateEntity(bank);
		}
		// 更新信托字段
		if (trustVo != null) {
			CapitalistTrust trust = capitalistTrustService.findOne(cap.getCapitalistTrust().getId());
			trust.setAppointBorrowRate(trustVo.getAppointBorrowRate());
			trust.setAppointRepayDate(trustVo.getAppointRepayDate());
			trust.setPlanInputCost(trustVo.getPlanInputCost());
			capitalistTrustService.saveEntity(trust);
		}
		msg.setMsg("更新成功！");
		msg.setResultStatus(ResponseMsg.SUCCESS);
		return ObjectHelper.objectToJson(msg);
	}

	/**
	 * 删除
	 * 
	 * @param jsoncallback
	 * @return
	 * @throws BusinessException
	 */
	@RequestMapping("/del")
	@UriKey(key = "com.zdsoft.finance.cooperator.capitalist.del")
	@ResponseBody
	public String del(String jsoncallback, String id) throws BusinessException {
		ResponseMsg msg = new ResponseMsg();
		try {
			capitalistService.logicDelete(id);
			msg.setMsg("操作成功！");
			msg.setResultStatus(ResponseMsg.SUCCESS);
		} catch (Exception e) {
			msg.setMsg("操作失败！" + e.getMessage());
			msg.setResultStatus(ResponseMsg.SYS_ERROR);
		}
		return ObjectHelper.objectToJson(msg);
	}
}
