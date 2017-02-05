package com.zdsoft.finance.marketing.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zdsoft.essential.client.service.CED;
import com.zdsoft.essential.dto.basedata.AttachmentDto;
import com.zdsoft.essential.dto.emp.EmpDto;
import com.zdsoft.finance.busiform.entity.BusiForm;
import com.zdsoft.finance.casemanage.material.entity.CaseMaterialListAtta;
import com.zdsoft.finance.casemanage.material.service.CaseMaterialListAttaService;
import com.zdsoft.finance.casemanage.material.vo.CaseMaterialListAttaVo;
import com.zdsoft.finance.common.base.ConstantParameter;
import com.zdsoft.finance.common.base.QueryObj;
import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.finance.cooperator.entity.Capitalist;
import com.zdsoft.finance.cooperator.entity.CooperatorTerminal;
import com.zdsoft.finance.cooperator.service.CapitalistService;
import com.zdsoft.finance.cooperator.service.CooperatorTerminalService;
import com.zdsoft.finance.customer.entity.BeforeAddress;
import com.zdsoft.finance.customer.entity.BeforeContact;
import com.zdsoft.finance.customer.entity.BeforePersonalCustomer;
import com.zdsoft.finance.customer.entity.BeforeWorkUnit;
import com.zdsoft.finance.customer.entity.CustomerCreateType;
import com.zdsoft.finance.customer.service.BeforeAddressService;
import com.zdsoft.finance.customer.service.BeforePersonalCustomerService;
import com.zdsoft.finance.customer.vo.BeforeAddressVo;
import com.zdsoft.finance.customer.vo.BeforeContactVo;
import com.zdsoft.finance.customer.vo.BeforePersonalCustomerVo;
import com.zdsoft.finance.customer.vo.BeforeWorkUnitVo;
import com.zdsoft.finance.marketing.entity.BeforehandApply;
import com.zdsoft.finance.marketing.entity.CaseApply;
import com.zdsoft.finance.marketing.entity.CaseApplyBeforeCustomer;
import com.zdsoft.finance.marketing.entity.HouseProperty;
import com.zdsoft.finance.marketing.entity.PledgeInfo;
import com.zdsoft.finance.marketing.entity.PropertyOwner;
import com.zdsoft.finance.marketing.service.BeforehandApplyService;
import com.zdsoft.finance.marketing.service.CaseApplyService;
import com.zdsoft.finance.marketing.service.HousePropertyService;
import com.zdsoft.finance.marketing.service.PledgeInfoService;
import com.zdsoft.finance.marketing.service.PropertyOwnerService;
import com.zdsoft.finance.marketing.vo.CaseApplyVo;
import com.zdsoft.finance.marketing.vo.HousePropertyVo;
import com.zdsoft.finance.marketing.vo.MarketingBeforehandVo;
import com.zdsoft.finance.marketing.vo.PledgeInfoVo;
import com.zdsoft.finance.marketing.vo.PropertyOwnerVo;
import com.zdsoft.framework.core.common.dto.ResponseMsg;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.PageRequest;
import com.zdsoft.framework.core.common.util.ObjectHelper;
import com.zdsoft.framework.core.commweb.annotation.UriKey;
import com.zdsoft.framework.core.commweb.component.BaseController;
import com.zdsoft.framework.cra.annotation.Menu;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title:BeforehandApplyController.java
 * @Package:com.zdsoft.finance.marketing.controller
 * @Description:案件预申请Controller
 * @author: zhoushichao
 * @date:2017年1月11日 上午10:29:12
 * @version:v1.0
 */
@Controller
@RequestMapping("/beforehandApply")
public class BeforehandApplyController extends BaseController {

	@Autowired
	private BeforehandApplyService beforehandApplyService;
	@Autowired
	private CaseApplyService caseApplyService;
	@Autowired
	private BeforePersonalCustomerService beforePersonalCustomerService;
	@Autowired
	private BeforeAddressService beforeAddressService;
	@Autowired
	private HousePropertyService housePropertyService;
	@Autowired
	private CaseMaterialListAttaService caseMaterialListAttaService;	
	@Autowired
	private CooperatorTerminalService cooperatorTerminalService;	
	@Autowired
	private CapitalistService capitalistService;	
	@Autowired
	private PledgeInfoService pledgeInfoService;	
	@Autowired
	private PropertyOwnerService propertyOwnerService;	
	@Autowired
	private CED CED;
	
	/**
	 * 营销登记列表菜单注册入口
	 */
	@RequestMapping("/beforehandApplyList")
	@UriKey(key = "com.zdsoft.finance.marketing.beforehandApplyList")
	@Menu(resource = "com.zdsoft.finance.marketing.beforehandApplyList", label = "营销登记", group = "marketing", order = 11)
	public ModelAndView beforehandApplyList() {
		ModelAndView modelAndView=new ModelAndView("marketing/beforehand_apply_list");
		return modelAndView;
	}
	/**
	 * 营销登记查询列表
	 * @param request 
	 * @param jsoncallback
	 * @param pageable
	 * @return
	 */
	@RequestMapping("/getBeforehandApplyList")
	@UriKey(key = "com.zdsoft.finance.marketing.getBeforehandApplyList")
	@ResponseBody
	public String getBeforehandApplyList(HttpServletRequest request,String productTypeId, String jsoncallback, PageRequest pageable) {

		// 获取页面封装的查询参数 
		@SuppressWarnings("unchecked")
		List<QueryObj> queryObjs = (List<QueryObj>) request.getAttribute("listObj");
		
		Map<String,Object> returnData=new HashMap<String,Object>();

		try {
			Page<Map<String, Object>> caseApplyPage = caseApplyService.findPageBeforehandApply(pageable, queryObjs);
			returnData.put("total",caseApplyPage.getTotalRows());
			returnData.put("rows",caseApplyPage.getRecords());
		} catch (Exception e) {
			 logger.error("营销登记列表数据出错！",e);
			e.printStackTrace();
		}
		
    return ObjectHelper.objectToJson(returnData,jsoncallback);
	}
	/**
	 * 
	 * 查看营销申请详情页面
	 *
	 * @author xj
	 * @param caseApplyId
	 * @return
	 */
	@RequestMapping("/viewBeforehandApply")
	@UriKey(key = "com.zdsoft.finance.marketing.viewBeforehandApply")
	public ModelAndView viewBeforehandApply(String caseApplyId) {
		ModelAndView modelAndView = this.addBeforehandApply(caseApplyId);
		try {
			ModelMap modelMap = modelAndView.getModelMap();
			//案件信息
			CaseApplyVo caseApplyVo = (CaseApplyVo) modelMap.get("beforehandApplyVo");
			this.caseApplyFullcodeTransform(caseApplyVo);
		
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("跳转到营销详情", e);
		}
		modelAndView.setViewName("/marketing/beforehand_apply_view");
		return modelAndView;
	}

	/**
	 * 新增或编辑贷款申请页面跳转
	 * @return
	 */
	@RequestMapping("/addBeforehandApply")
	@UriKey(key = "com.zdsoft.finance.marketing.addBeforehandApply")
	public ModelAndView addBeforehandApply(String caseApplyId) {
		ModelMap modelMap = new ModelMap();
		try {
			//获取登陆人信息
			EmpDto emp = CED.getLoginUser();
			modelMap.put("empDto",emp); 
			modelMap.put("developmentManagerName",emp.getEmpNm());
			modelMap.put("developmentDepartmentName",emp.getDepartmentName() );
			if(ObjectHelper.isNotEmpty(caseApplyId)){
				//获取征信附件信息
				this.pullCaseMaterial(caseApplyId,modelMap);
				
				//获取所有客户信息
				List<BeforePersonalCustomer> beforePersonalCustomers = beforePersonalCustomerService.queryByCaseApplyId(caseApplyId);
				BeforePersonalCustomer beforePersonalCustomer = null;
				for (BeforePersonalCustomer bcs : beforePersonalCustomers) {
					if(CaseApplyBeforeCustomer.MAIN_BORROW.equals(bcs.getJoinType())){
						beforePersonalCustomer = bcs;
						break;
					}
				}
				BeforePersonalCustomerVo beforePersonalCustomerVo = new BeforePersonalCustomerVo(beforePersonalCustomer);
				//头像
				String attachmentId = beforePersonalCustomerVo.getAttachmentId();
				if(ObjectHelper.isNotEmpty(attachmentId)){
					AttachmentDto attachment = CED.findAttachmentById(attachmentId);
					if(ObjectHelper.isNotEmpty(attachment)){
						//url地址
						String headPortraitPath = ConstantParameter.getAppDownloadUrl()+attachment.getFilePath();
						beforePersonalCustomerVo.setHeadPortraitPath(headPortraitPath);
					}
				}
				//地址
				//家庭地址
				BeforeAddress beforeAddress = beforeAddressService.loadAddresss(beforePersonalCustomer.getId(), BeforeAddress.HOME_ADDRESS);
				BeforeAddressVo homeAddressVo = new BeforeAddressVo(beforeAddress);
				modelMap.put("homeAddressVo", homeAddressVo);
				//户籍地址
				BeforeAddress registrationSddress = beforeAddressService.loadAddresss(beforePersonalCustomer.getId(), BeforeAddress.HOUSEHOLD_REGISTRATION_ADDRESS);
				BeforeAddressVo beforeAddressVo = new BeforeAddressVo(registrationSddress);
				modelMap.put("beforeAddressVo", beforeAddressVo);
				
				//配偶
				BeforePersonalCustomer spouse = beforePersonalCustomer.getSpouse();
				if(ObjectHelper.isNotEmpty(spouse)){
					BeforePersonalCustomerVo spouseVo = new BeforePersonalCustomerVo(spouse);
					beforePersonalCustomerVo.setSpouseVo(spouseVo);
					//头像
					String spouseAttachmentId = spouseVo.getAttachmentId();
					if(ObjectHelper.isNotEmpty(spouseAttachmentId)){
						AttachmentDto attachment = CED.findAttachmentById(spouseAttachmentId);
						if(ObjectHelper.isNotEmpty(attachment)){
							//url地址
							String headPortraitPath = ConstantParameter.getAppDownloadUrl()+attachment.getFilePath();
							spouseVo.setHeadPortraitPath(headPortraitPath);
						}
					}
				}
				//客户和其配偶信息
				modelMap.put("persCustomerVo", beforePersonalCustomerVo);
				
				//案件申请 caseApplyVo
				CaseApply caseApply = caseApplyService.findOne(caseApplyId);
				CaseApplyVo caseApplyVo = new CaseApplyVo(caseApply);
				modelMap.put("beforehandApplyVo", caseApplyVo);
				
				//查询营销预申请Id
				BeforehandApply beforehandApply = beforehandApplyService.findByCaseApplyId(caseApplyId);
				modelMap.put("beforehandApplyId", beforehandApply.getId());
				//房产
				HousePropertyVo housePropertyVo = this.pullHouseProperty(caseApplyId);
				modelMap.put("housePropertyVo", housePropertyVo);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("跳转到营销等级", e);
		}
		return new ModelAndView("/marketing/beforehand_apply_edit",modelMap);
	}
	//房产
	private HousePropertyVo pullHouseProperty(String caseApplyId) {
		List<HouseProperty> housePropertys = housePropertyService.findByCaseApplyId(caseApplyId);
		HouseProperty housePropertyPo = housePropertys.get(0);
		HousePropertyVo housePropertyVo = new HousePropertyVo(housePropertyPo);
		return housePropertyVo;
	}
	//获取征信附件信息
	private void pullCaseMaterial(String caseApplyId, ModelMap modelMap) throws Exception {
		List<CaseMaterialListAtta> caseMaterialListAttas = caseMaterialListAttaService.findByBusinessIdAndMateriaCode(caseApplyId, BeforehandApplyService.CREDIT_CLASS);
		
		if(ObjectHelper.isNotEmpty(caseMaterialListAttas)){
			List<CaseMaterialListAttaVo> caseMaterialListAttaVos = new ArrayList<CaseMaterialListAttaVo>();
			String attachmentIds="";
			for (CaseMaterialListAtta caseMaterialListAtta : caseMaterialListAttas) {
				CaseMaterialListAttaVo caseMaterialListAttaVo = new CaseMaterialListAttaVo();
				BeanUtils.copyProperties(caseMaterialListAtta, caseMaterialListAttaVo);
				String attachmentId = caseMaterialListAtta.getAttachmentId();
				attachmentIds +=","+attachmentId;
				caseMaterialListAttaVos.add(caseMaterialListAttaVo);
			}
			attachmentIds = attachmentIds.replaceFirst(",", "");
			modelMap.put("attachmentIds", attachmentIds);
			modelMap.put("caseMaterialListAttaVos", caseMaterialListAttaVos);
		}
		
	}
	/**
	 * 保存营销登记
	 * @param marketingBeforehandVo 营销登记
	 * @param submitStatus 提交状态
	 * @return
	 */
	@RequestMapping("/saveBeforehandApply")
	@UriKey(key = "com.zdsoft.finance.marketing.saveBeforehandApply")
	@ResponseBody
	public ResponseMsg saveBeforehandApply(MarketingBeforehandVo marketingBeforehandVo,Boolean submitStatus) {
		
		ResponseMsg msg = new ResponseMsg();
		Map<String, Object> beforehandApplyMap = new HashMap<String, Object>();
		
		//案件预申请
		BeforehandApply beforehandApply = null;
		//主借人
		BeforePersonalCustomer mainCustomer=null;
		//房产
		HouseProperty houseProperty = null;
		
		try {	
			// 将Vo转成Po
			Map<String, Object> voTurnPoMap = this.voTurnPo(marketingBeforehandVo);
			//获取数据
			beforehandApply = (BeforehandApply) voTurnPoMap.get("beforehandApply");
			mainCustomer = (BeforePersonalCustomer) voTurnPoMap.get("mainCustomer");
			houseProperty = (HouseProperty) voTurnPoMap.get("houseProperty");
			
			// 执行保存
			//保存案件预申请信息
			Map<String, Object> map = beforehandApplyService.saveOrUpdateBeforehandApply(beforehandApply,mainCustomer,houseProperty,marketingBeforehandVo.getCreditAttachmentIds(),submitStatus);
			
			//页面相应信息
			//案件
			CaseApply caseApply = (CaseApply) map.get("caseApply");
			BusiForm busiForm = caseApply.getBusiForm();
			
			//客户
			BeforePersonalCustomer resultCustomer = (BeforePersonalCustomer) map.get("customer");
			//配偶
			BeforePersonalCustomer resultSpouse = resultCustomer.getSpouse();
			//欲申请
			BeforehandApply resultBeforehandApply = (BeforehandApply) map.get("beforehandApply");
			//押品信息
			HouseProperty housePropertyPo = (HouseProperty) map.get("houseProperty");
			
			//下一处理人
			if(ObjectHelper.isNotEmpty(busiForm)){
				beforehandApplyMap.put("currentDealEmpNm", busiForm.getCurrentDealEmpNm());
			}
			//客户id
			beforehandApplyMap.put("customerId", resultCustomer.getId());
			//配偶id
			if(ObjectHelper.isNotEmpty(resultSpouse)){
				beforehandApplyMap.put("spouseId", resultSpouse.getId());
			}
			//押品信息
			beforehandApplyMap.put("housePropertyId", housePropertyPo.getId());
			//返回页面信息
			beforehandApplyMap.put("beforehandApplyId", resultBeforehandApply.getId());
			msg.setResultStatus(ResponseMsg.SUCCESS);
			msg.setOptional(beforehandApplyMap);
			msg.setMsg("保存营销申请成功！");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("营销申请保存失败", e);
			msg.setResultStatus(ResponseMsg.SYS_ERROR);
			msg.setMsg("系统内部错误，请查看日志");
		}
		return msg;
	}
	
	/**
	 * 
	 * 营销申请Vo转Po
	 *
	 * @author zhoushichao
	 * @param marketingBeforehandVo
	 * @return
	 */
	public Map<String, Object> voTurnPo(MarketingBeforehandVo marketingBeforehandVo){
		Map<String, Object> voTurnPoMap =new HashMap<String, Object>();
		
		//案件预申请
		BeforehandApply beforehandApply = null;
		//房产
		HouseProperty houseProperty = null;
		//户籍地址
		BeforeAddress beforeAddress = null;
		//家庭地址
		BeforeAddress homeAddress = null;
		
		// 将Vo转成Po
		//案件预申请
		beforehandApply = marketingBeforehandVo.getBeforehandApplyVo().toPO();
		
		//配偶
		BeforePersonalCustomerVo persCustomerVo = marketingBeforehandVo.getPersCustomerVo();
		BeforePersonalCustomerVo spouseVo = persCustomerVo.getSpouseVo();
		BeforePersonalCustomer spouse = null;
		if(ObjectHelper.isNotEmpty(spouseVo)){
			spouse = spouseVo.toPO();
			spouse.setCreateType(CustomerCreateType.MAIN_CUSTOMER.value());
		}
		//主借人
		BeforePersonalCustomer mainCustomer = persCustomerVo.toPO();
		mainCustomer.setCreateType(CustomerCreateType.MAIN_CUSTOMER.value());
		mainCustomer.setSpouse(spouse);
		
		//户籍地址
		beforeAddress = marketingBeforehandVo.getBeforeAddressVo().toPO();
		beforeAddress.setAddressType(BeforeAddress.HOUSEHOLD_REGISTRATION_ADDRESS);
		//家庭地址
		homeAddress = marketingBeforehandVo.getHomeAddressVo().toPO();
		homeAddress.setAddressType(BeforeAddress.HOME_ADDRESS);
		
		//地址集合
		List<BeforeAddress> allHomeAddress = new ArrayList<BeforeAddress>();
		allHomeAddress.add(homeAddress);
		allHomeAddress.add(beforeAddress);
		
		//联系方式
		List<BeforeContact>  beforeContacts = new ArrayList<BeforeContact>();
		List<BeforeContactVo> beforeContactVos = marketingBeforehandVo.getBeforeContactVos();
		if(ObjectHelper.isNotEmpty(beforeContactVos)){
			for (BeforeContactVo beforeContactVo : beforeContactVos) {
				BeforeContact po = beforeContactVo.toPO();
				beforeContacts.add(po);
			}
		}
		
		//工作单位集合
		List<BeforeWorkUnit> beforeWorkUnits = new ArrayList<BeforeWorkUnit>();
		List<BeforeWorkUnitVo> beforeWorkUnitVos = marketingBeforehandVo.getBeforeWorkUnitVos();
		if(ObjectHelper.isNotEmpty(beforeWorkUnitVos)){
			for (BeforeWorkUnitVo beforeWorkUnitVo : beforeWorkUnitVos) {
				BeforeWorkUnit po = beforeWorkUnitVo.toPO();
				beforeWorkUnits.add(po);
			}
		}
		
		//工作单位
		mainCustomer.setBeforeWorkUnits(beforeWorkUnits);
		//客户地址
		mainCustomer.setHomeAddress(allHomeAddress);
		//联系方式
		mainCustomer.setBeforeContacts(beforeContacts);
		//配偶
		mainCustomer.setSpouse(spouse);
		
		//房产信息
		houseProperty = marketingBeforehandVo.getHousePropertyVo().toPO();
		
		//押品情况
		List<PledgeInfo> pledgeInfos = new ArrayList<PledgeInfo>();
		List<PledgeInfoVo> pledgeInfoVos = marketingBeforehandVo.getPledgeInfoVoList();
		if(ObjectHelper.isNotEmpty(pledgeInfoVos)){
			for (PledgeInfoVo pledgeInfoVo : pledgeInfoVos) {
				PledgeInfo pledgeInfo = new PledgeInfo();
				if(ObjectHelper.isNotEmpty(pledgeInfoVo.getId())){
					try {
						pledgeInfo = pledgeInfoService.findOne(pledgeInfoVo.getId());
					} catch (BusinessException e) {
						e.printStackTrace();
					}
				}else{
					pledgeInfo.setIsDeleted(false);
					pledgeInfo.setCreateTime(new Date());
				}
				pledgeInfo.setPledgeType(pledgeInfoVo.getPledgeType());
				pledgeInfo.setLoanBank(pledgeInfoVo.getLoanBank());
				pledgeInfo.setDeadline(pledgeInfoVo.getDeadline());
				pledgeInfo.setType(pledgeInfoVo.getType());
				pledgeInfo.setLoanBalance(pledgeInfoVo.getLoanBalance());
				pledgeInfo.setPledgeAmout(pledgeInfoVo.getPledgeAmout());
				pledgeInfo.setFrontLoanBalance(pledgeInfoVo.getFrontLoanBalance());
				pledgeInfo.setPercentage(pledgeInfoVo.getPercentage());
				pledgeInfos.add(pledgeInfo);
			}
		}
		houseProperty.setPledgeInfoList(pledgeInfos);
		
		//产权人信息
		List<PropertyOwner> propertyOwners = new ArrayList<PropertyOwner>();
		List<PropertyOwnerVo> propertyOwnerVos = marketingBeforehandVo.getPropertyOwnerVoList();
		if(ObjectHelper.isNotEmpty(propertyOwnerVos)){
			for (PropertyOwnerVo propertyOwnerVo : propertyOwnerVos) {
				PropertyOwner propertyOwner = new PropertyOwner();
				if(ObjectHelper.isNotEmpty(propertyOwnerVo.getId())){
					try {
						propertyOwner = propertyOwnerService.findOne(propertyOwnerVo.getId());
					} catch (BusinessException e) {
						e.printStackTrace();
					}
				}else{
					propertyOwner.setIsDeleted(false);
					propertyOwner.setCreateTime(new Date());
				}
				propertyOwner.setOwnerName(propertyOwnerVo.getOwnerName());
				propertyOwner.setCredentialNo(propertyOwnerVo.getCredentialNo());
				propertyOwner.setIdentityStartDate(propertyOwnerVo.getIdentityStartDate());
				propertyOwner.setIdentityEndDate(propertyOwnerVo.getIdentityEndDate());
				propertyOwner.setIdentityTerm(propertyOwnerVo.getIdentityTerm());
				propertyOwner.setEmailAddress(propertyOwnerVo.getEmailAddress());
				propertyOwner.setPhoneNumber(propertyOwnerVo.getPhoneNumber());
				propertyOwner.setProvince(propertyOwnerVo.getProvince());
				propertyOwner.setCity(propertyOwnerVo.getCity());
				propertyOwner.setDistrict(propertyOwnerVo.getDistrict());
				propertyOwner.setMailingAddress(propertyOwnerVo.getMailingAddress());
				propertyOwners.add(propertyOwner);
			}
		}
		//房产设置产权人信息
		houseProperty.setPropertyOwnerList(propertyOwners);
		
		voTurnPoMap.put("beforehandApply", beforehandApply);
		voTurnPoMap.put("mainCustomer", mainCustomer);
		voTurnPoMap.put("houseProperty", houseProperty);
		return voTurnPoMap;
	}
	
	/**
	 * 
	 * 案件终端和资金来源转换
	 *
	 * @author xj
	 * @param beforehandApplyVo
	 * @throws Exception
	 */
	private void caseApplyFullcodeTransform(CaseApplyVo caseApplyVo) throws Exception {
		//终端
		String terminalId = caseApplyVo.getTerminalId();
		if(ObjectHelper.isNotEmpty(terminalId)){
			CooperatorTerminal cooperatorTerminal = cooperatorTerminalService.findOne(terminalId);
			if(ObjectHelper.isNotEmpty(cooperatorTerminal)){
				String terminalFullName = cooperatorTerminal.getTerminalFullName();
				caseApplyVo.setTerminalIdName(terminalFullName);
			}
		}
		//资金来源	
		String capitalSource = caseApplyVo.getCapitalSource();
		if(ObjectHelper.isNotEmpty(capitalSource)){
			Capitalist capitalist = capitalistService.findOne(capitalSource);
			if(ObjectHelper.isNotEmpty(capitalist)){
				caseApplyVo.setCapitalSourceName(capitalist.getCooperatorName());
			}
		}
	}
}
