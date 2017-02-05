package com.zdsoft.finance.casemanage.datasurvey.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zdsoft.finance.casemanage.datasurvey.vo.ApplicantInfomationVo;
import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.finance.customer.entity.BeforeAddress;
import com.zdsoft.finance.customer.entity.BeforeContact;
import com.zdsoft.finance.customer.entity.BeforePersonalAssociation;
import com.zdsoft.finance.customer.entity.BeforePersonalCustomer;
import com.zdsoft.finance.customer.entity.BeforeWorkUnit;
import com.zdsoft.finance.customer.entity.CustomerCreateType;
import com.zdsoft.finance.customer.repository.BeforePersonalAssociationRepository;
import com.zdsoft.finance.customer.service.BeforeAddressService;
import com.zdsoft.finance.customer.service.BeforePersonalCustomerService;
import com.zdsoft.finance.customer.vo.BeforeAddressVo;
import com.zdsoft.finance.customer.vo.BeforeContactVo;
import com.zdsoft.finance.customer.vo.BeforePersonalCustomerVo;
import com.zdsoft.finance.customer.vo.BeforeWorkUnitVo;
import com.zdsoft.finance.customer.vo.CaseApplyBeforeCustomerVo;
import com.zdsoft.finance.marketing.entity.CaseApplyBeforeCustomer;
import com.zdsoft.finance.marketing.service.CaseApplyBeforeCustomerService;
import com.zdsoft.framework.core.common.dto.ResponseMsg;
import com.zdsoft.framework.core.common.util.ObjectHelper;
import com.zdsoft.framework.core.commweb.annotation.UriKey;
import com.zdsoft.framework.core.commweb.component.BaseController;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * 
 * @Title:ApplicantInfomationController.java
 * @Package:com.zdsoft.finance.casemanage.datasurvey.controller
 * @Description:申请人信息
 * @author: laijun
 * @date:2017年1月10日 下午9:40:09
 * @version:v1.0
 */
@Controller
@RequestMapping("/casemanage/datasurvey/applicantinfo")
public class ApplicantInfomationController extends BaseController {

	@Autowired
	BeforePersonalCustomerService beforePersonalCustomerService;
	@Autowired
	CaseApplyBeforeCustomerService applyBeforeCustomerService;
	@Autowired
	private BeforeAddressService beforeAddressService;
	@Autowired
	BeforePersonalAssociationRepository beforePersonalAssociationRepository;
	@Autowired
	private CaseApplyBeforeCustomerService caseApplyBeforeCustomerService;

	/**
	 * 
	 * 跟据案件id和客户id删除与案件的关系
	 *
	 * @author laijun
	 * @date:2017年1月17日 下午8:14:02
	 * @return
	 */
	@RequestMapping(value = "/delete")
	@UriKey(key = "com.zdsoft.finance.casemanage.datasurvey.applicantinfomation.delete")
	@ResponseBody
	public ResponseMsg delete(String caseApplyId, String customerId) {
		ResponseMsg msg = new ResponseMsg();
		try {
			if (ObjectHelper.isNotEmpty(customerId) && ObjectHelper.isNotEmpty(caseApplyId)) {

				applyBeforeCustomerService.deleteByCustomerIdAndCaseApplyId(customerId, caseApplyId);
				msg.setResultStatus(ResponseMsg.SUCCESS);
				msg.setMsg("删除申请人信息成功！");
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("保存客户信息失败", e);
			msg.setResultStatus(ResponseMsg.SYS_ERROR);
			msg.setMsg("系统内部错误，请查看日志");
		}
		return msg;

	}

	/**
	 * 
	 * 申请人信息修改
	 *
	 * @author laijun
	 * @date:2017年1月10日 下午9:40:22
	 * @param caseApplyId
	 * @return
	 */
	@RequestMapping(value = "/edit")
	@UriKey(key = "com.zdsoft.finance.casemanage.datasurvey.applicantinfomation.edit")
	public ModelAndView edit(String customerId, String caseApplyId) {
		ModelMap model = new ModelMap();
		BeforePersonalCustomerVo beforePersonalCustomerVo = null;
		try {

			// 获取客户信息
			BeforePersonalCustomer beforePersonalCustomer = beforePersonalCustomerService.findOne(customerId);
			//查询客户与案件的关系
			CaseApplyBeforeCustomer caseApplyBeforeCustomer= caseApplyBeforeCustomerService.findByCaseApplyIdAndBeforeCustomerId(caseApplyId,customerId);
			CaseApplyBeforeCustomerVo caseApplyBeforeCustomerVo=new CaseApplyBeforeCustomerVo(caseApplyBeforeCustomer);
			beforePersonalCustomer.setJoinType(caseApplyBeforeCustomerVo.getJoinType());
			beforePersonalCustomer.setJoinTypeName(caseApplyBeforeCustomerVo.getJoinTypeName());
			beforePersonalCustomerVo = new BeforePersonalCustomerVo(beforePersonalCustomer);
			// 家庭地址
			BeforeAddress homeAddressVo = beforeAddressService.loadAddresss(beforePersonalCustomer.getId(),
					BeforeAddress.HOME_ADDRESS);
			model.put("homeAddressVo", homeAddressVo);
			// 户籍地址
			BeforeAddress beforeAddressVo = beforeAddressService.loadAddresss(beforePersonalCustomer.getId(),
					BeforeAddress.HOUSEHOLD_REGISTRATION_ADDRESS);
			model.put("beforeAddressVo", beforeAddressVo);
			// 配偶
			List<BeforePersonalAssociation> spouses = beforePersonalAssociationRepository
					.findByRelationshipAndCustomerId(BeforePersonalAssociation.SPOUSE, customerId);
			if (ObjectHelper.isNotEmpty(spouses)) {
				BeforePersonalCustomer spouse=spouses.get(0).getBeforePersonalCusomer();
				//查询客户与案件的关系
				CaseApplyBeforeCustomer soupseCase= caseApplyBeforeCustomerService.findByCaseApplyIdAndBeforeCustomerId(caseApplyId,customerId);
				CaseApplyBeforeCustomerVo soupseCaseVo=new CaseApplyBeforeCustomerVo(soupseCase);
				spouse.setJoinType(soupseCaseVo.getJoinType());
				spouse.setJoinTypeName(soupseCaseVo.getJoinTypeName());
			
				BeforePersonalCustomerVo spouseVo = new BeforePersonalCustomerVo(spouse);
				beforePersonalCustomerVo.setSpouseVo(spouseVo);
			}
			model.put("persCustomerVo", beforePersonalCustomerVo);
			model.put("customerId", customerId);
			model.put("caseApplyId", caseApplyId);
		} catch (BusinessException e) {
			logger.error("根据客户ID查询客户信息失败", e);
			model.put("errorMsg", e.getMessage());
			e.printStackTrace();
		}
		return new ModelAndView("casemanage/datasurvey/applicantinfo_edit", model);

	}

	/**
	 * 
	 * 申请人信息查看
	 *
	 * @author laijun
	 * @date:2017年1月10日 下午9:40:22
	 * @param caseApplyId
	 * @return
	 */
	@RequestMapping(value = "/view")
	@UriKey(key = "com.zdsoft.finance.casemanage.datasurvey.applicantinfomation.view")
	public ModelAndView view(String customerId, String caseApplyId) {
		ModelMap model = new ModelMap();
		BeforePersonalCustomerVo beforePersonalCustomerVo = null;
		try {

			// 获取客户信息
			BeforePersonalCustomer beforePersonalCustomer = beforePersonalCustomerService.findOne(customerId);
			//查询客户与案件的关系
			CaseApplyBeforeCustomer caseApplyBeforeCustomer= caseApplyBeforeCustomerService.findByCaseApplyIdAndBeforeCustomerId(caseApplyId,customerId);
			CaseApplyBeforeCustomerVo caseApplyBeforeCustomerVo=new CaseApplyBeforeCustomerVo(caseApplyBeforeCustomer);
			beforePersonalCustomer.setJoinType(caseApplyBeforeCustomerVo.getJoinType());
			beforePersonalCustomer.setJoinTypeName(caseApplyBeforeCustomerVo.getJoinTypeName());
			beforePersonalCustomerVo = new BeforePersonalCustomerVo(beforePersonalCustomer);
			// 家庭地址
			BeforeAddress homeAddress = beforeAddressService.loadAddresss(beforePersonalCustomer.getId(),
					BeforeAddress.HOME_ADDRESS);
			BeforeAddressVo homeAddressVo=new BeforeAddressVo(homeAddress);
			model.put("homeAddressVo", homeAddressVo);
			// 户籍地址
			BeforeAddress beforeAddress = beforeAddressService.loadAddresss(beforePersonalCustomer.getId(),
					BeforeAddress.HOUSEHOLD_REGISTRATION_ADDRESS);
			BeforeAddressVo beforeAddressVo=new BeforeAddressVo(beforeAddress);
			model.put("beforeAddressVo", beforeAddressVo);
			// 配偶
			List<BeforePersonalAssociation> spouses = beforePersonalAssociationRepository
					.findByRelationshipAndCustomerId(BeforePersonalAssociation.SPOUSE, customerId);
			if (ObjectHelper.isNotEmpty(spouses)) {
				BeforePersonalCustomer spouse=spouses.get(0).getBeforePersonalCusomer();
				//查询客户与案件的关系
				CaseApplyBeforeCustomer soupseCase= caseApplyBeforeCustomerService.findByCaseApplyIdAndBeforeCustomerId(caseApplyId,customerId);
				CaseApplyBeforeCustomerVo soupseCaseVo=new CaseApplyBeforeCustomerVo(soupseCase);
				spouse.setJoinType(soupseCaseVo.getJoinType());
				spouse.setJoinTypeName(soupseCaseVo.getJoinTypeName());
			
				BeforePersonalCustomerVo spouseVo = new BeforePersonalCustomerVo(spouse);
				beforePersonalCustomerVo.setSpouseVo(spouseVo);
			}
			model.put("persCustomerVo", beforePersonalCustomerVo);
			model.put("customerId", customerId);
			model.put("caseApplyId", caseApplyId);
		} catch (BusinessException e) {
			logger.error("根据客户ID查询客户信息失败", e);
			model.put("errorMsg", e.getMessage());
			e.printStackTrace();
		}
		return new ModelAndView("casemanage/datasurvey/applicantinfo/applicantinfo_view", model);

	}

	/**
	 * 
	 * 申请人信息加载
	 *
	 * @author laijun
	 * @date:2017年1月10日 下午9:40:22
	 * @param caseApplyId
	 * @return
	 */
	@RequestMapping(value = "/load")
	@UriKey(key = "com.zdsoft.finance.casemanage.datasurvey.applicantinfomation.load")
	public ModelAndView load(String caseApplyId) {
		ModelMap model = new ModelMap();
		List<BeforePersonalCustomerVo> listVo = new ArrayList<BeforePersonalCustomerVo>();
		try {
			List<BeforePersonalCustomer> beforePersonalCustomers = beforePersonalCustomerService
					.queryByCaseApplyId(caseApplyId);
			if (beforePersonalCustomers.size() == 0) {
				model.put("errorMsg", "数据异常,客户信息为空!");
			} else {
				// PO转换
				for (BeforePersonalCustomer beforePersonalCustomer : beforePersonalCustomers) {
					listVo.add(new BeforePersonalCustomerVo(beforePersonalCustomer));
				}
			}
		} catch (Exception e) {
			model.put("errorMsg", e.getMessage());
		}
		model.put("vo", listVo);
		model.put("caseApplyId", caseApplyId);
		return new ModelAndView("casemanage/datasurvey/applicantinfo_load", model);

	}

	/**
	 * 
	 * 申请人信息加载(查看)
	 *
	 * @author laijun
	 * @date:2017年1月10日 下午9:40:22
	 * @param caseApplyId
	 * @return
	 */
	@RequestMapping(value = "/loadView")
	@UriKey(key = "com.zdsoft.finance.casemanage.datasurvey.applicantinfomation.load.view")
	public ModelAndView loadView(String caseApplyId) {
		ModelMap model = new ModelMap();
		List<BeforePersonalCustomerVo> listVo = new ArrayList<BeforePersonalCustomerVo>();
		try {
			List<BeforePersonalCustomer> beforePersonalCustomers = beforePersonalCustomerService
					.queryByCaseApplyId(caseApplyId);
			if (beforePersonalCustomers.size() == 0) {
				model.put("errorMsg", "数据异常,客户信息为空!");
			} else {
				// PO转换
				for (BeforePersonalCustomer beforePersonalCustomer : beforePersonalCustomers) {
					listVo.add(new BeforePersonalCustomerVo(beforePersonalCustomer));
				}
			}
		} catch (Exception e) {
			model.put("errorMsg", e.getMessage());
		}
		model.put("vo", listVo);
		model.put("caseApplyId", caseApplyId);
		return new ModelAndView("casemanage/datasurvey/applicantinfo/applicantinfo_load_view", model);

	}

	/**
	 * 
	 * 申请人信息保存
	 *
	 * @author laijun
	 * @date:2017年1月10日 下午9:40:38
	 * @param jsoncallBack
	 * @return
	 */
	@RequestMapping("/save")
	@UriKey(key = "com.zdsoft.finance.casemanage.datasurvey.applicantinfomation.save")
	@ResponseBody
	public ResponseMsg save(ApplicantInfomationVo vo, String jsoncallBack) {
		ResponseMsg msg = new ResponseMsg();
		// 户籍地址
		BeforeAddress beforeAddress = null;
		// 家庭地址
		BeforeAddress homeAddress = null;
		// 配偶
		BeforePersonalCustomerVo persCustomerVo = vo.getPersCustomerVo();
		BeforePersonalCustomerVo spouseVo = persCustomerVo.getSpouseVo();
		BeforePersonalCustomer spouse = null;
		if (ObjectHelper.isNotEmpty(spouseVo)) {
			spouse = spouseVo.toPO();
			spouse.setCreateType(CustomerCreateType.MAIN_CUSTOMER.value());
		}
		// 主借人
		BeforePersonalCustomer customer = persCustomerVo.toPO();
		customer.setCreateType(CustomerCreateType.MAIN_CUSTOMER.value());
		customer.setSpouse(spouse);
		// 户籍地址
		beforeAddress = vo.getBeforeAddressVo().toPO();
		beforeAddress.setAddressType(BeforeAddress.HOUSEHOLD_REGISTRATION_ADDRESS);
		// 家庭地址
		homeAddress = vo.getHomeAddressVo().toPO();
		homeAddress.setAddressType(BeforeAddress.HOME_ADDRESS);
		// 地址集合
		List<BeforeAddress> allHomeAddress = new ArrayList<BeforeAddress>();
		allHomeAddress.add(homeAddress);
		allHomeAddress.add(beforeAddress);

		// 联系方式
		List<BeforeContact> beforeContacts = new ArrayList<BeforeContact>();
		List<BeforeContactVo> beforeContactVos = vo.getBeforeContactVos();
		if (ObjectHelper.isNotEmpty(beforeContactVos)) {
			for (BeforeContactVo beforeContactVo : beforeContactVos) {
				BeforeContact po = beforeContactVo.toPO();
				beforeContacts.add(po);
			}
		}

		// 工作单位集合
		List<BeforeWorkUnit> beforeWorkUnits = new ArrayList<BeforeWorkUnit>();
		List<BeforeWorkUnitVo> beforeWorkUnitVos = vo.getBeforeWorkUnitVos();
		if (ObjectHelper.isNotEmpty(beforeWorkUnitVos)) {
			for (BeforeWorkUnitVo beforeWorkUnitVo : beforeWorkUnitVos) {
				BeforeWorkUnit po = beforeWorkUnitVo.toPO();
				beforeWorkUnits.add(po);
			}
		}
		try {
			// 工作单位
			customer.setBeforeWorkUnits(beforeWorkUnits);
			// 客户地址
			customer.setHomeAddress(allHomeAddress);
			// 联系方式
			customer.setBeforeContacts(beforeContacts);
			// 配偶
			customer.setSpouse(spouse);

			beforePersonalCustomerService.saveOrUpdateCustomer(customer, vo.getCaseApplyId());
			msg.setResultStatus(ResponseMsg.SUCCESS);
			msg.setMsg("保存客户信息成功！");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("保存客户信息失败", e);
			msg.setResultStatus(ResponseMsg.SYS_ERROR);
			msg.setMsg("系统内部错误，请查看日志");
		}
		return msg;
	}
}
