package com.zdsoft.finance.casemanage.datasurvey.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zdsoft.essential.client.service.CED;
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
import com.zdsoft.finance.customer.service.BeforePersonalAssociationService;
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

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: ApplicantInfomationController.java 
 * @ClassName: ApplicantInfomationController 
 * @Description: 申请人信息 Controller 
 * @author zhoushichao 
 * @date 2017年3月6日 下午6:09:07 
 * @version V1.0
 */
@Controller
@RequestMapping("/casemanage/datasurvey")
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
	@Autowired
	private BeforePersonalAssociationService associationService;
	@Autowired
	private CED CED;
	
	/**
	 * 
	 * @Title: deleteApplyBeforeCustomer 
	 * @Description: 跟据案件id和客户id删除与案件的关系
	 * @author zhoushichao 
	 * @param caseApplyId 案件id
	 * @param customerId 客户id
	 * @return
	 */
	@RequestMapping(value = "/deleteApplyBeforeCustomer")
	@UriKey(key = "com.zdsoft.finance.casemanage.datasurvey.applicantinfomation.deleteApplyBeforeCustomer")
	@ResponseBody
	public ResponseMsg deleteApplyBeforeCustomer(String caseApplyId, String customerId) {
		ResponseMsg msg = new ResponseMsg();
		try {
			applyBeforeCustomerService.deleteByCustomerIdAndCaseApplyId(customerId, caseApplyId);
			msg.setResultStatus(ResponseMsg.SUCCESS);
			msg.setMsg("删除客户信息成功！");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("删除客户信息失败", e);
			msg.setResultStatus(ResponseMsg.SYS_ERROR);
			msg.setMsg("系统内部错误，请查看日志");
		}
		return msg;

	}

	/**
	 * 
	 * @Title: editApplicantInfomation 
	 * @Description: 申请人信息编辑
	 * @author zhoushichao 
	 * @param customerId 客户id
	 * @param caseApplyId 案件id
	 * @return
	 */
	@RequestMapping(value = "/editApplicantInfomation")
	@UriKey(key = "com.zdsoft.finance.casemanage.datasurvey.applicantinfomation.editApplicantInfomation")
	public ModelAndView editApplicantInfomation(String customerId, String caseApplyId) {
		ModelMap model = new ModelMap();
		BeforePersonalCustomerVo beforePersonalCustomerVo = null;
		try {
			//判断新增还是编辑客户
			if(ObjectHelper.isNotEmpty(customerId)){
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
				BeforeAddressVo homeAddressVo = new BeforeAddressVo(homeAddress);
				model.put("homeAddressVo", homeAddressVo);
				// 户籍地址
				BeforeAddress beforeAddress = beforeAddressService.loadAddresss(beforePersonalCustomer.getId(),
						BeforeAddress.HOUSEHOLD_REGISTRATION_ADDRESS);
				BeforeAddressVo beforeAddressVo = new BeforeAddressVo(beforeAddress);
				model.put("beforeAddressVo", beforeAddressVo);
				// 配偶
				List<BeforePersonalAssociation> spouses = beforePersonalAssociationRepository
						.findByRelationshipAndCustomerId(BeforePersonalAssociation.SPOUSE, customerId);
				if (ObjectHelper.isNotEmpty(spouses)) {
					BeforePersonalCustomer spouse=spouses.get(0).getBeforePersonalCusomer();
					//查询客户的配偶与案件的关系
					CaseApplyBeforeCustomer soupseCase= caseApplyBeforeCustomerService.findByCaseApplyIdAndBeforeCustomerId(caseApplyId,spouse.getId());
					CaseApplyBeforeCustomerVo soupseCaseVo=new CaseApplyBeforeCustomerVo(soupseCase);
					spouse.setJoinType(soupseCaseVo.getJoinType());
					spouse.setJoinTypeName(soupseCaseVo.getJoinTypeName());
				
					BeforePersonalCustomerVo spouseVo = new BeforePersonalCustomerVo(spouse);
					beforePersonalCustomerVo.setSpouseVo(spouseVo);
				}
				model.put("persCustomerVo", beforePersonalCustomerVo);
				model.put("customerId", customerId);
			}
		} catch (BusinessException e) {
			logger.error("根据客户ID查询客户信息失败", e);
			model.put("errorMsg", e.getMessage());
			e.printStackTrace();
		}
		return new ModelAndView("casemanage/datasurvey/applicantinfo/applicantinfo_edit", model);

	}

	/**
	 * 
	 * @Title: viewApplicantInfomation 
	 * @Description: 申请人信息查看
	 * @author zhoushichao 
	 * @param customerId 客户id
	 * @param caseApplyId 案件id
	 * @return
	 */
	@RequestMapping(value = "/viewApplicantInfomation")
	@UriKey(key = "com.zdsoft.finance.casemanage.datasurvey.applicantinfomation.viewApplicantInfomation")
	public ModelAndView viewApplicantInfomation(String customerId, String caseApplyId) {
		ModelAndView modelAndView = this.editApplicantInfomation(customerId, caseApplyId);
		modelAndView.setViewName("casemanage/datasurvey/applicantinfo/applicantinfo_view");
		return modelAndView;
	}

	/**
	 * 
	 * @Title: loadApplicantInfomation 
	 * @Description: 初始化申请人信息加载
	 * @author zhoushichao 
	 * @param caseApplyId 案件Id
	 * @return
	 */
	@RequestMapping(value = "/loadApplicantInfomation")
	@UriKey(key = "com.zdsoft.finance.casemanage.datasurvey.applicantinfomation.loadApplicantInfomation")
	public ModelAndView loadApplicantInfomation(String caseApplyId) {
		ModelMap model = new ModelMap();
		List<BeforePersonalCustomerVo> listVo = new ArrayList<BeforePersonalCustomerVo>();
		try {
			List<BeforePersonalCustomer> beforePersonalCustomers = beforePersonalCustomerService.queryByCaseApplyId(caseApplyId);
			if (beforePersonalCustomers.size() == 0) {
				model.put("errorMsg", "客户信息为空!");
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
		return new ModelAndView("casemanage/datasurvey/applicantinfo/applicantinfo_load_edit", model);

	}

	/**
	 * 
	 * @Title: loadView 
	 * @Description: 申请人信息加载(查看)
	 * @author zhoushichao 
	 * @param caseApplyId
	 * @return
	 */
	@RequestMapping(value = "/loadView")
	@UriKey(key = "com.zdsoft.finance.casemanage.datasurvey.applicantinfomation.loadView")
	public ModelAndView loadView(String caseApplyId) {
		ModelMap model = new ModelMap();
		List<BeforePersonalCustomerVo> listVo = new ArrayList<BeforePersonalCustomerVo>();
		try {
			List<BeforePersonalCustomer> beforePersonalCustomers = beforePersonalCustomerService
					.queryByCaseApplyId(caseApplyId);
				// PO转换
				for (BeforePersonalCustomer beforePersonalCustomer : beforePersonalCustomers) {
					listVo.add(new BeforePersonalCustomerVo(beforePersonalCustomer));
				}
				model.put("listVo", listVo);
		} catch (Exception e) {
			logger.error("申请人信息加载失败：", e);
		}
		return new ModelAndView("casemanage/datasurvey/applicantinfo/applicantinfo_load_view", model);
	}

	/**
	 * 
	 * @Title: saveApplicantInfomation 
	 * @Description: 申请人信息保存
	 * @author zhoushichao 
	 * @param vo 申请人信息
	 * @return
	 */
	@RequestMapping("/saveApplicantInfomation")
	@UriKey(key = "com.zdsoft.finance.casemanage.datasurvey.applicantinfomation.saveApplicantInfomation")
	@ResponseBody
	public ResponseMsg saveApplicantInfomation(ApplicantInfomationVo vo) {
		ResponseMsg msg = new ResponseMsg();
		//客户信息
		BeforePersonalCustomerVo persCustomerVo = vo.getPersCustomerVo();
		BeforePersonalCustomer customer = persCustomerVo.toPO();
		customer.setCreateType(CustomerCreateType.MAIN_CUSTOMER.value());
		
		//判断是否存在主借人
		if(CaseApplyBeforeCustomer.MAIN_BORROW.equals(customer.getJoinType())&&ObjectHelper.isNotEmpty(vo.getCaseApplyId())){
			List<CaseApplyBeforeCustomer> caseApplyBeforeCustomerList = applyBeforeCustomerService.queryByCaseApplyIdAndJoinType(vo.getCaseApplyId(), customer.getJoinType());
			if (caseApplyBeforeCustomerList.size()>=1) {
				for (CaseApplyBeforeCustomer caseApplyBeforeCustomer : caseApplyBeforeCustomerList) {
					if (!customer.getId().equals(caseApplyBeforeCustomer.getBeforeCustomer().getId())) {
						msg.setResultStatus(ResponseMsg.SYS_ERROR);
						msg.setMsg("保存客户信息失败！该案件已有主借人，请重新选择参与类型！");
						return msg;
					}
				}
			}
		}
		
		//配偶信息
		BeforePersonalCustomerVo spouseVo = persCustomerVo.getSpouseVo();
		BeforePersonalCustomer spouse = null;
		if (ObjectHelper.isNotEmpty(spouseVo)) {
			spouse = spouseVo.toPO();
			spouse.setCreateType(CustomerCreateType.MAIN_CUSTOMER.value());
			customer.setSpouse(spouse);
		}
		// 户籍地址
		BeforeAddress beforeAddress = vo.getBeforeAddressVo().toPO();
		beforeAddress.setAddressType(BeforeAddress.HOUSEHOLD_REGISTRATION_ADDRESS);
		// 家庭地址
		BeforeAddress homeAddress = vo.getHomeAddressVo().toPO();
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

			BeforePersonalCustomer beforePersonalCustomer = beforePersonalCustomerService.saveOrUpdateCustomer(customer, vo.getCaseApplyId());
			Map<String, Object> map = new HashMap<String, Object>();
			beforePersonalCustomer.setJoinTypeName(CED.loadSimpleCodeNameByFullCode(beforePersonalCustomer.getJoinType()));
			map.put("joinTypeName", beforePersonalCustomer.getJoinTypeName());
			map.put("customerName", beforePersonalCustomer.getCustomerName());
			msg.setId(beforePersonalCustomer.getId());
			msg.setOptional(map);
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
	/**
	 * 
	 * @Title: loadRelationship 
	 * @Description: 选择关系弹窗初始化
	 * @author caixiekang 
	 * @param caseApplyId 案件id
	 * @return
	 */
	@RequestMapping("/loadRelationship")
	@UriKey(key = "com.zdsoft.finance.casemanage.datasurvey.applicantinfomation.loadRelationship")
	@ResponseBody
	public ModelAndView loadRelationship(String caseApplyId){
		ModelAndView mv = new ModelAndView("casemanage/datasurvey/applicantinfo/applicantinfo_relationship_edit");
		//返回主借人信息
		List<BeforePersonalCustomer> mainborrowList = 
				beforePersonalCustomerService.queryCustomerByCaseApplyIdAndJoinType(caseApplyId, CaseApplyBeforeCustomer.MAIN_BORROW);
		BeforePersonalCustomer mainborrow = mainborrowList.get(0);
		BeforePersonalCustomerVo mainborrowVo = new BeforePersonalCustomerVo(mainborrow);
		mv.addObject("mainborrowVo", mainborrowVo);
		return mv;
	}
	
	
	
	
	/**
	 * 
	 * @Title: findRelationship 
	 * @Description: 通过案件Id获取案件中所有参与类型
	 * @author caixiekang 
	 * @param caseApplyId 案件id
	 * @return
	 */
	@RequestMapping("/findRelationship")
	@UriKey(key = "com.zdsoft.finance.casemanage.datasurvey.applicantinfomation.findRelationship")
	@ResponseBody
	public ResponseMsg findRelationship(String caseApplyId){
		ResponseMsg msg = new ResponseMsg();
		BeforePersonalCustomer mainborrow = null;
		List<BeforePersonalCustomerVo> allCustomerVoList = new ArrayList<>();
		
		//查找该案件中的所有参与人
		List<BeforePersonalCustomer> beforePersonalCustomerList = beforePersonalCustomerService.queryByCaseApplyId(caseApplyId);
		
		
		//提取主借人,并在list中删除主借人,组装新List
		for (BeforePersonalCustomer beforePersonalCustomer : beforePersonalCustomerList) {
			if(beforePersonalCustomer.getJoinType().equals(CaseApplyBeforeCustomer.MAIN_BORROW)){
				mainborrow = beforePersonalCustomer;
				if(ObjectHelper.isNotEmpty(mainborrow.getSpouse())){
					BeforePersonalCustomerVo beforePersonalCustomerVo = new BeforePersonalCustomerVo(mainborrow.getSpouse());
					allCustomerVoList.add(beforePersonalCustomerVo);
				}
			}else{
				allCustomerVoList.add(new BeforePersonalCustomerVo(beforePersonalCustomer));
				if(ObjectHelper.isNotEmpty(beforePersonalCustomer.getSpouse())){
					allCustomerVoList.add(new BeforePersonalCustomerVo(beforePersonalCustomer.getSpouse()));
				}
			}
		}
		
		
		msg.setMsg("案件中所有人的参与类型查询成功");
        msg.setResultStatus(ResponseMsg.SUCCESS);
        //msg.setTotal(list.getTotalRows());
        msg.setRows(allCustomerVoList);
		return msg;
	}
	
	
	/**
	 * 
	 * @Title: saveRelationship 
	 * @Description: TODO
	 * @author caixiekang 
	 * @param mainborrowId 主借人Id
	 * @param relationshipList 页面传来的关系信息Json数据
	 * @return
	 */
	@SuppressWarnings({ "unchecked" })
	@RequestMapping("/saveRelationship")
	@UriKey(key = "com.zdsoft.finance.casemanage.datasurvey.applicantinfomation.saveRelationship")
	@ResponseBody
	public ResponseMsg saveRelationship(String mainborrowId, String relationshipList) throws Exception{
		//解析json数据
		ResponseMsg msg = new ResponseMsg();
		try{
			JSONArray jsonArray = JSONArray.fromObject(relationshipList);
			List<BeforePersonalCustomerVo> beforePersonalCustomerVos = 
					JSONArray.toList(jsonArray, new BeforePersonalCustomerVo(), new JsonConfig());
			//获取关系字段,组建新的List
			List<BeforePersonalCustomer> beforePersonalCustomers = new ArrayList<>();
			for (BeforePersonalCustomerVo beforePersonalCustomerVo : beforePersonalCustomerVos) {
				BeforePersonalCustomer bPC= beforePersonalCustomerService.findById(beforePersonalCustomerVo.getId());
				bPC.setRelationship(beforePersonalCustomerVo.getRelationship());
				beforePersonalCustomers.add(bPC);
			}
			
			//保存关系
			associationService.batchSaveRelationship(mainborrowId, beforePersonalCustomers);
			msg.setMsg("保存主借人关系信息成功");
			msg.setResultStatus(ResponseMsg.SUCCESS);
		}catch(Exception e){
			logger.error(e.getMessage());
			e.printStackTrace();
			msg.setMsg("保存主借人关系信息失败,请联系管理员");
			msg.setResultStatus(ResponseMsg.SYS_ERROR);
		}
		
		return msg;
	}
	
	/**
	 * 
	 * @Title: isShowRelationship 
	 * @Description: 判断是否显示弹窗
	 * @author caixiekang 
	 * @param caseApplyId 案件id
	 * @return
	 */
	@RequestMapping("/isShowRelationship")
	@UriKey(key = "com.zdsoft.finance.casemanage.datasurvey.applicantinfomation.isShowRelationship")
	@ResponseBody
	public ResponseMsg isShowRelationship(String caseApplyId){
		ResponseMsg msg = new ResponseMsg();
		logger.info("caseApplyId:", caseApplyId);
		Map<String, Object> map = new HashMap<>();
		try{
			//返回主借人信息
			List<BeforePersonalCustomer> mainborrowList = 
					beforePersonalCustomerService.queryCustomerByCaseApplyIdAndJoinType(caseApplyId, CaseApplyBeforeCustomer.MAIN_BORROW);
			if(ObjectHelper.isEmpty(mainborrowList)){
				msg.setResultStatus(ResponseMsg.SYS_ERROR);
				msg.setMsg("该案件没有主借人，请添加主借人");
				return msg;
			}
			List<CaseApplyBeforeCustomer> caseApplyBeforeCustomers =caseApplyBeforeCustomerService.queryByCaseApplyId(caseApplyId);
			if(caseApplyBeforeCustomers.size() <= 1){
				map.put("isShow", "0");
				
			}else{
				map.put("isShow", "1");
			}
			msg.setMsg("判断是否显示弹窗查询成功");
			msg.setResultStatus(ResponseMsg.SUCCESS);
			msg.setOptional(map);
		}catch(Exception e){
			e.printStackTrace();
			logger.error("判断是否显示弹窗查询出错",e);
			msg.setResultStatus(ResponseMsg.APP_ERROR);
			msg.setMsg("判断是否显示弹窗查询出错");
			
		}
		return msg;
	}
}
