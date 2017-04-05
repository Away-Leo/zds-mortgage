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
import com.zdsoft.essential.dto.org.OrgDto;
import com.zdsoft.finance.common.base.ConstantParameter;
import com.zdsoft.finance.common.base.QueryObj;
import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.finance.common.utils.ParameterUtil;
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
import com.zdsoft.finance.filestore.entity.FileStore;
import com.zdsoft.finance.filestore.service.FileStoreService;
import com.zdsoft.finance.filestore.vo.FileStoreVo;
import com.zdsoft.finance.marketing.entity.BeforehandApply;
import com.zdsoft.finance.marketing.entity.CaseApply;
import com.zdsoft.finance.marketing.entity.CaseApplyBeforeCustomer;
import com.zdsoft.finance.marketing.entity.CaseApplyStageEnumSimpleCodeEnum;
import com.zdsoft.finance.marketing.entity.CaseTail;
import com.zdsoft.finance.marketing.entity.HouseProperty;
import com.zdsoft.finance.marketing.entity.PledgeInfo;
import com.zdsoft.finance.marketing.entity.PropertyOwner;
import com.zdsoft.finance.marketing.entity.TerminalCaseApprovalOpinion;
import com.zdsoft.finance.marketing.service.BeforehandApplyService;
import com.zdsoft.finance.marketing.service.CaseApplyService;
import com.zdsoft.finance.marketing.service.CaseTailService;
import com.zdsoft.finance.marketing.service.HousePropertyService;
import com.zdsoft.finance.marketing.service.PledgeInfoService;
import com.zdsoft.finance.marketing.service.PropertyOwnerService;
import com.zdsoft.finance.marketing.service.TerminalCaseApprovalOpinionService;
import com.zdsoft.finance.marketing.vo.CaseApplyVo;
import com.zdsoft.finance.marketing.vo.CaseTailVo;
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
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: BeforehandApplyController.java 
 * @ClassName: BeforehandApplyController 
 * @Description: 案件预申请Controller
 * @author zhoushichao 
 * @date 2017年3月9日 上午9:34:34 
 * @version V1.0
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
	private CooperatorTerminalService cooperatorTerminalService;	
	@Autowired
	private CapitalistService capitalistService;	
	@Autowired
	private PledgeInfoService pledgeInfoService;	
	@Autowired
	private PropertyOwnerService propertyOwnerService;	
	@Autowired
	private CED CED;
	@Autowired
	private CaseTailService caseTailService;
	@Autowired
	private FileStoreService fileStoreService;
	
	@Autowired
	private TerminalCaseApprovalOpinionService terminalCaseApprovalOpinionService;
	
	/**
	 * 
	 * @Title: beforehandApplyList 
	 * @Description: 营销登记列表菜单注册入口
	 * @author zhoushichao 
	 * @return
	 */
	@RequestMapping("/beforehandApplyList")
	@UriKey(key = "com.zdsoft.finance.marketing.beforehandApplyList")
	@Menu(resource = "com.zdsoft.finance.marketing.beforehandApplyList", label = "营销登记", group = "marketing", order = 11)
	public ModelAndView beforehandApplyList() {
		ModelMap modelMap = new ModelMap();
		EmpDto empDto = null;
		try {
			empDto = CED.getLoginUser();
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("获取登录人信息失败：",e);
		}
		modelMap.put("empDto", empDto);
		return new ModelAndView("marketing/beforehand_apply_list",modelMap);
	}
	/**
	 * 
	 * @Title: getBeforehandApplyList 
	 * @Description:  营销登记查询列表
	 * @author zhoushichao 
	 * @param request  请求数据
	 * @param jsoncallback 
	 * @param pageable  分页信息
	 * @return
	 */
	@RequestMapping("/getBeforehandApplyList")
	@UriKey(key = "com.zdsoft.finance.marketing.getBeforehandApplyList")
	@ResponseBody
	public String getBeforehandApplyList(HttpServletRequest request, String jsoncallback, PageRequest pageable) {

		// 获取页面封装的查询参数 
		List<QueryObj> queryObjs =ParameterUtil.getQueryObjByParameter(request, new String[]{"cus","contact","c"});
		 QueryObj obj = new QueryObj();
         obj.setObj("bus");
         obj.setElement("formStatus");
         obj.setOperator("=");
         obj.setValue("0");
         queryObjs.add(obj);
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
	 * @Title: viewBeforehandApply 
	 * @Description: 查看营销申请详情页面
	 * @author zhoushichao 
	 * @param caseApplyId 案件Id
	 * @param projectId 案件Id
	 * @return
	 */
	@RequestMapping("/viewBeforehandApply")
	@UriKey(key = "com.zdsoft.finance.marketing.viewBeforehandApply")
	public ModelAndView viewBeforehandApply(String caseApplyId,String projectId) {
		ModelAndView modelAndView = this.addBeforehandApply(caseApplyId,projectId);
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
	 * 废弃申请
	 * @Title: discardApply 
	 * @author jingjiyan 
	 * @param caseApplyId
	 * 			案件ID
	 * @param projectId	
	 * 			我的申请列表ID
	 * @return
	 */
	@RequestMapping("/discardApply")
	@UriKey(key = "com.zdsoft.finance.marketing.discardApply")
	@ResponseBody
	public ResponseMsg discardApply(String caseApplyId,String projectId) {
		ResponseMsg msg = new ResponseMsg();
		try {
			/**modify jingjiyan*/
			if(ObjectHelper.isEmpty(caseApplyId)){
				caseApplyId = projectId;
			}
				caseApplyService.discardApply(caseApplyId);
				msg.setResultStatus(ResponseMsg.SUCCESS);
				msg.setMsg("废弃营销申请成功！");
			} catch (Exception e) {
				logger.error("废弃营销申请失败", e);
				msg.setResultStatus(ResponseMsg.SYS_ERROR);
				msg.setMsg("系统内部错误，请查看日志");
			}
		return msg;
	}

	/**
	 * 
	 * @Title: addBeforehandApply 
	 * @Description: 新增或编辑贷款申请页面跳转
	 * @author zhoushichao 
	 * @param caseApplyId 案件Id
	 * @param projectId 案件Id
	 * @return
	 */
	@RequestMapping("/addBeforehandApply")
	@UriKey(key = "com.zdsoft.finance.marketing.addBeforehandApply")
	public ModelAndView addBeforehandApply(String caseApplyId,String projectId) {
		/**modify jingjiyan*/
		if(ObjectHelper.isEmpty(caseApplyId)){
			caseApplyId = projectId;
		}
		ModelMap modelMap = new ModelMap();
		try {
			//获取登陆人信息
			EmpDto emp = CED.getLoginUser();
			modelMap.put("empDto",emp); 
			modelMap.put("developmentManagerName",emp.getEmpNm());
			modelMap.put("developmentDepartmentName",emp.getOrgNm() );
			/*model by liuhuan 新增参与类型判断-贷款申请只能是主借人*/
			modelMap.put("joinTypeFlag", true);
			if(ObjectHelper.isNotEmpty(caseApplyId)){
				//获取征信附件信息
				modelMap = this.pullCaseMaterial(modelMap, caseApplyId, caseApplyId);
				
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
				}
				//客户和其配偶信息
				modelMap.put("persCustomerVo", beforePersonalCustomerVo);
				
				//案件申请 caseApplyVo
				CaseApply caseApply = caseApplyService.findOne(caseApplyId);
				CaseApplyVo caseApplyVo = new CaseApplyVo(caseApply);
				//获取资金来源
				this.caseApplyFullcodeTransform(caseApplyVo);
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
			logger.error("跳转到营销登记页面出错", e);
		}
		return new ModelAndView("/marketing/beforehand_apply_edit",modelMap);
	}
	/**
	 * 
	 * @Title: pullHouseProperty 
	 * @Description: 根据案件Id查询房产
	 * @author zhoushichao 
	 * @param caseApplyId 案件Id
	 * @return
	 */
	private HousePropertyVo pullHouseProperty(String caseApplyId) {
		List<HouseProperty> housePropertys = housePropertyService.findByCaseApplyId(caseApplyId);
		HouseProperty housePropertyPo = housePropertys.get(0);
		HousePropertyVo housePropertyVo = new HousePropertyVo(housePropertyPo);
		return housePropertyVo;
	}
	
	/** 
	 * @Title: pullCaseMaterial 
	 * @Description: 征信获取
	 * @author liuhuan 
	 * @param modelMap 
	 * @param caseApplyId 案件id
	 * @param businessId 业务id
	 * @return
	 * @throws Exception  
	 */ 
	private ModelMap pullCaseMaterial(ModelMap modelMap,String caseApplyId, String businessId) throws Exception {
		if(ObjectHelper.isEmpty(caseApplyId) || ObjectHelper.isEmpty(businessId)){
			return modelMap;
		}
		String caseStatue = CaseApplyStageEnumSimpleCodeEnum.ACCEPTANCE.value;
		String linkCode = caseStatue.substring(caseStatue.length()-2, caseStatue.length());
		List<FileStore> fileStores = fileStoreService.findByCaseApplyIdAndBusinessIdAndLinkCode(caseApplyId, businessId, linkCode);
		
		if(ObjectHelper.isNotEmpty(fileStores)){
			String attachmentIds="";
			List<FileStoreVo> fileStoreVos = new ArrayList<FileStoreVo>();
			
			for(FileStore fileStore : fileStores){
				FileStoreVo fileStoreVo = new FileStoreVo(fileStore);
				String attachmentId = fileStore.getAttachmentId();
				attachmentIds +=","+attachmentId;
				fileStoreVos.add(fileStoreVo);
			}
			
			attachmentIds = attachmentIds.replaceFirst(",", "");
			modelMap.put("attachmentIds", attachmentIds);
			modelMap.put("fileStoreVos", fileStoreVos);
		}
		return modelMap;
	}
	
	/**
	 * 
	 * @Title: saveBeforehandApply 
	 * @Description: 保存营销登记
	 * @author zhoushichao 
	 * @param marketingBeforehandVo 营销登记
	 * @param submitStatus 提交状态
	 * @return
	 */
	@RequestMapping("/saveBeforehandApply")
	@UriKey(key = "com.zdsoft.finance.marketing.saveBeforehandApply")
	@ResponseBody
	public ResponseMsg saveBeforehandApply(MarketingBeforehandVo marketingBeforehandVo,Boolean submitStatus) {
		
		ResponseMsg msg = new ResponseMsg();
		
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
			
			int applyTerm = beforehandApply.getApplyTerm();
			
			if("0931001".equals(beforehandApply.getApplyTermUnit())){
				applyTerm = applyTerm*360;
			}else if("0931002".equals(beforehandApply.getApplyTermUnit())){
				applyTerm = applyTerm*30;
			}
			//检查贷款期限
			Long cnt = beforehandApplyService.checkLoanTerm(beforehandApply.getProductSubtypeId(), applyTerm);
			if(cnt>0){
			// 执行保存
			//保存案件预申请信息
			Map<String, Object> map = beforehandApplyService.saveOrUpdateBeforehandApply(beforehandApply,mainCustomer,houseProperty,marketingBeforehandVo.getCreditAttachmentIds(),submitStatus);
			
			msg.setResultStatus(ResponseMsg.SUCCESS);
			msg.setOptional(map);
			msg.setMsg("保存营销申请成功！");
			}else{
				msg.setResultStatus(ResponseMsg.APP_ERROR);
				msg.setMsg("贷款期限不在产品期限范围内");
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("营销申请保存失败", e);
			msg.setResultStatus(ResponseMsg.SYS_ERROR);
			msg.setMsg("系统内部错误，联系系统管理员");
		}
		return msg;
	}
	
	/**
	 * 
	 * @Title: voTurnPo 
	 * @Description: 营销申请Vo转Po 
	 * @author zhoushichao 
	 * @param marketingBeforehandVo  营销申请信息 
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
		//终端进件审批意见
		TerminalCaseApprovalOpinion terminalCaseApprovalOpinion = null;
		
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
		houseProperty = marketingBeforehandVo.getHousePropertyVo().toPo();
		
		//终端进件审批意见信息
		if(ObjectHelper.isNotEmpty(marketingBeforehandVo.getTerminalCaseApprovalOpinionVo())){
			terminalCaseApprovalOpinion = marketingBeforehandVo.getTerminalCaseApprovalOpinionVo().toPo();
			voTurnPoMap.put("terminalCaseApprovalOpinion", terminalCaseApprovalOpinion);
		}
		
		//抵押情况
		List<PledgeInfo> pledgeInfoList = new ArrayList<PledgeInfo>();
		List<PledgeInfoVo> pledgeInfoVoList = marketingBeforehandVo.getPledgeInfoVoList();
		if(ObjectHelper.isNotEmpty(pledgeInfoVoList)){
			for (PledgeInfoVo pledgeInfoVo : pledgeInfoVoList) {
				PledgeInfo pledgeInfo = pledgeInfoVo.toPo();
				PledgeInfo pledge = new PledgeInfo();
				if(ObjectHelper.isNotEmpty(pledgeInfoVo.getId())){
					try {
						pledge = pledgeInfoService.findOne(pledgeInfoVo.getId());
						pledge.setUpdateTime(new Date());
					} catch (BusinessException e) {
						e.printStackTrace();
					}
				}else{
					pledge.setCreateTime(new Date());
				}
				BeanUtils.copyProperties(pledgeInfo, pledge , new String[]{"isDeleted","createTime","updateTime"});
				pledgeInfoList.add(pledge);
			}
		}
		houseProperty.setPledgeInfoList(pledgeInfoList);
		
		//产权人信息
		List<PropertyOwner> propertyOwnerList = new ArrayList<PropertyOwner>();
		List<PropertyOwnerVo> propertyOwnerVoList = marketingBeforehandVo.getPropertyOwnerVoList();
		//产权人信息
		if(ObjectHelper.isNotEmpty(propertyOwnerVoList)){
			for (PropertyOwnerVo propertyOwnerVo : propertyOwnerVoList) {
				PropertyOwner propertyOwner = propertyOwnerVo.toPo();
				PropertyOwner property = new PropertyOwner();
				if(ObjectHelper.isNotEmpty(propertyOwnerVo.getId())){
					try {
						property = propertyOwnerService.findOne(propertyOwnerVo.getId());
						property.setUpdateTime(new Date());
					} catch (BusinessException e) {
						e.printStackTrace();
					}
				}else{
					property.setCreateTime(new Date());
				}
				BeanUtils.copyProperties(propertyOwner, property , new String[]{"isDeleted","createTime","updateTime"});
				propertyOwnerList.add(property);
			}
		}
		//房产设置产权人信息
		houseProperty.setPropertyOwnerList(propertyOwnerList);
		
		voTurnPoMap.put("beforehandApply", beforehandApply);
		voTurnPoMap.put("mainCustomer", mainCustomer);
		voTurnPoMap.put("houseProperty", houseProperty);
		return voTurnPoMap;
	}
	
	/**
	 * 
	 * @Title: caseApplyFullcodeTransform 
	 * @Description: 案件终端和资金来源转换为名称
	 * @author zhoushichao 
	 * @param caseApplyVo 案件信息
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
				caseApplyVo.setCapitalSourceName(capitalist.getCapitalName());
			}
		}
	}
	
	
	/**
	 * 
	 * @Title: terminalCaseList 
	 * @Description: 终端进件列表菜单注册入口
	 * @author xiongpan
	 * @return
	 */
	@RequestMapping("/terminalCaseList")
	@UriKey(key = "com.zdsoft.finance.marketing.terminalCaseList")
	@Menu(resource = "com.zdsoft.finance.marketing.terminalCaseList", label = "终端进件", group = "marketing", order = 13)
	public ModelAndView terminalCaseList() {
		ModelMap modelMap = new ModelMap();
		EmpDto empDto = null;
		try {
			empDto = CED.getLoginUser();
			//获取机构信息
			List<Map<String,String>> orgList = new ArrayList<Map<String,String>>();
			List<OrgDto> orgDtos = CED.queryAllCompany();
			if(ObjectHelper.isNotEmpty(orgDtos)){
				for(OrgDto orgDto : orgDtos){
					Map<String,String> objMap = new HashMap<String,String>();
					objMap.put("code", orgDto.getOrgCd());
					objMap.put("py", orgDto.getOrgNm());
					objMap.put("name", orgDto.getOrgNm());
					orgList.add(objMap);
				}
			}
			modelMap.put("data", ObjectHelper.objectToJson(orgList));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		modelMap.put("empDto", empDto);
		
		ModelAndView modelAndView = new ModelAndView("marketing/terminalcase/terminal_case_list",modelMap);
		return modelAndView;
	}
	
	/**
	 * 
	 * @Title: getTerminalCaseList 
	 * @Description: 终端进件查询列表
	 * @author xiongpan
	 * @param request 请求
	 * @param jsoncallback 返回json数据
	 * @param pageable 分页信息
	 * @return
	 */
	@RequestMapping("/getTerminalCaseList")
	@UriKey(key = "com.zdsoft.finance.marketing.getTerminalCaseList")
	@ResponseBody
	public String getTerminalCaseList(HttpServletRequest request, String jsoncallback, PageRequest pageable) {

		// 获取页面封装的查询参数 
		@SuppressWarnings("unchecked")
		List<QueryObj> queryObjs = (List<QueryObj>) request.getAttribute("listObj");
		
		Map<String,Object> returnData=new HashMap<String,Object>();

		try {
			Page<Map<String, Object>> terminalCasePage = caseApplyService.findPageTerminalCase(pageable, queryObjs);
			returnData.put("total",terminalCasePage.getTotalRows());
			returnData.put("rows",terminalCasePage.getRecords());
		} catch (Exception e) {
			 logger.error("终端进件列表数据出错！",e);
			e.printStackTrace();
		}
		
    return ObjectHelper.objectToJson(returnData,jsoncallback);
	}
	
	/**
	 * 
	 * @Title: editTerminalCase 
	 * @Description: 终端进件编辑页面
	 * @author xiongpan
	 * @param caseApplyId 案件id
	 * @return
	 */
	@RequestMapping("/editTerminalCase")
	@UriKey(key = "com.zdsoft.finance.marketing.editTerminalCase")
	public ModelAndView editTerminalCase(String caseApplyId) {
		ModelMap modelMap = new ModelMap();
		try {
			//获取登陆人信息
			EmpDto emp = CED.getLoginUser();
			modelMap.put("empDto",emp); 
			modelMap.put("developmentManagerName",emp.getEmpNm());
			modelMap.put("developmentDepartmentName",emp.getDepartmentName() );
			if(ObjectHelper.isNotEmpty(caseApplyId)){
				
				//获取所有客户信息
				List<BeforePersonalCustomer> beforePersonalCustomers = beforePersonalCustomerService.queryByCaseApplyId(caseApplyId);
				BeforePersonalCustomer beforePersonalCustomer = null;
				if(ObjectHelper.isNotEmpty(beforePersonalCustomers)){
					for (BeforePersonalCustomer bcs : beforePersonalCustomers) {
						if(CaseApplyBeforeCustomer.MAIN_BORROW.equals(bcs.getJoinType())){
							beforePersonalCustomer = bcs;
							break;
						}
					}
					
				}
				
				if(ObjectHelper.isNotEmpty(beforePersonalCustomer)){
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
					if(ObjectHelper.isNotEmpty(beforeAddress)){
						BeforeAddressVo homeAddressVo = new BeforeAddressVo(beforeAddress);
						modelMap.put("homeAddressVo", homeAddressVo);
					}
					//户籍地址
					BeforeAddress registrationSddress = beforeAddressService.loadAddresss(beforePersonalCustomer.getId(), BeforeAddress.HOUSEHOLD_REGISTRATION_ADDRESS);
					if(ObjectHelper.isNotEmpty(registrationSddress)){
						BeforeAddressVo beforeAddressVo = new BeforeAddressVo(registrationSddress);
						modelMap.put("beforeAddressVo", beforeAddressVo);
					}
					
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
				}
				
				
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
				
				//终端进件的审批意见
				TerminalCaseApprovalOpinion terminalCaseApprovalOpinion = terminalCaseApprovalOpinionService.findByCaseApplyId(caseApplyId);
				if(ObjectHelper.isNotEmpty(terminalCaseApprovalOpinion)){
					modelMap.put("terminalCaseApprovalOpinionVo", terminalCaseApprovalOpinion);
				}
				//获取机构信息
				List<Map<String,String>> orgList = new ArrayList<Map<String,String>>();
				List<OrgDto> orgDtos = CED.queryAllCompany();
				if(ObjectHelper.isNotEmpty(orgDtos)){
					for(OrgDto orgDto : orgDtos){
						Map<String,String> objMap = new HashMap<String,String>();
						objMap.put("code", orgDto.getOrgCd());
						objMap.put("py", orgDto.getOrgNm());
						objMap.put("name", orgDto.getOrgNm());
						orgList.add(objMap);
					}
				}
				modelMap.put("data", ObjectHelper.objectToJson(orgList));
				
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("跳转到终端进件编辑页面出错", e);
		}
		return new ModelAndView("marketing/terminalcase/terminal_case_edit",modelMap);
	}
	
	/**
	 * 
	 * @Title: viewTerminalCase 
	 * @Description: 跳转到终端进件详情页面
	 * @author xiongpan
	 * @param caseApplyId 案件id
	 * @param projectId 申请id
	 * @return
	 */
	@RequestMapping("/viewTerminalCase")
	@UriKey(key = "com.zdsoft.finance.marketing.viewTerminalCase")
	public ModelAndView viewTerminalCase(String caseApplyId,String projectId) {
		if(ObjectHelper.isEmpty(caseApplyId)){
			caseApplyId = projectId;
		}
		ModelAndView modelAndView = this.editTerminalCase(caseApplyId);
		try {
			ModelMap modelMap = modelAndView.getModelMap();
			//案件信息
			CaseApplyVo caseApplyVo = (CaseApplyVo) modelMap.get("beforehandApplyVo");
			this.caseApplyFullcodeTransform(caseApplyVo);
		
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("跳转到营销详情", e);
		}
		modelAndView.setViewName("marketing/terminalcase/terminal_case_view");
		return modelAndView;
	}
	
	
	/**
	 * 
	 * @Title: saveTerminalCase 
	 * @Description: 保存终端进件编辑信息
	 * @author xiongpan
	 * @param marketingBeforehandVo 需要保存信息封装的Vo
	 * @param submitStatus 提交状态
	 * @return
	 */
	@RequestMapping("/saveTerminalCase")
	@UriKey(key = "com.zdsoft.finance.marketing.saveTerminalCase")
	@ResponseBody
	public ResponseMsg saveTerminalCase(MarketingBeforehandVo marketingBeforehandVo,Boolean submitStatus) {
		
		ResponseMsg msg = new ResponseMsg();
		Map<String, Object> beforehandApplyMap = new HashMap<String, Object>();
		
		//案件预申请
		BeforehandApply beforehandApply = null;
		//主借人
		BeforePersonalCustomer mainCustomer=null;
		//房产
		HouseProperty houseProperty = null;
		//终端进件审批信息
		TerminalCaseApprovalOpinion terminalCaseApprovalOpinion = null;
		
		try {	
			// 将Vo转成Po
			Map<String, Object> voTurnPoMap = this.voTurnPo(marketingBeforehandVo);
			//获取数据
			beforehandApply = (BeforehandApply) voTurnPoMap.get("beforehandApply");
			mainCustomer = (BeforePersonalCustomer) voTurnPoMap.get("mainCustomer");
			houseProperty = (HouseProperty) voTurnPoMap.get("houseProperty");
			terminalCaseApprovalOpinion = (TerminalCaseApprovalOpinion) voTurnPoMap.get("terminalCaseApprovalOpinion");
			
			// 执行保存
			//保存案件预申请信息
			Map<String, Object> map = beforehandApplyService.saveOrUpdateTerminalCaseApply(beforehandApply,mainCustomer,houseProperty,terminalCaseApprovalOpinion,submitStatus);
			
			//页面相应信息
			//客户
			BeforePersonalCustomer resultCustomer = (BeforePersonalCustomer) map.get("customer");
			//配偶
			BeforePersonalCustomer resultSpouse = resultCustomer.getSpouse();
			//欲申请
			BeforehandApply resultBeforehandApply = (BeforehandApply) map.get("beforehandApply");
			//押品信息
			HouseProperty housePropertyPo = (HouseProperty) map.get("houseProperty");
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
	 * 查看营销跟踪详情页面
	 *
	 * @author caixiekang
	 * @param caseApplyId 案件id
	 * @return
	 */
	@RequestMapping("/viewCaseTail")
	@UriKey(key = "com.zdsoft.finance.marketing.viewCaseTail")
	public ModelAndView viewCaseTail(String caseApplyId,String projectId) {
		if(ObjectHelper.isEmpty(caseApplyId)){
			caseApplyId = projectId;
		}
		ModelAndView modelAndView = this.viewBeforehandApply(caseApplyId,projectId);
		try {
			//获取当前操作的员工信息
			EmpDto employee = CED.getLoginUser();
			CaseTailVo caseTailVo = new CaseTailVo();
			caseTailVo.setMarketingPersonId(employee.getEmpCd());
			caseTailVo.setMarketingPersonName(employee.getEmpNm());
			modelAndView.addObject("caseTailVo", caseTailVo);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("跳转到营销跟踪失败", e);
		}
		modelAndView.setViewName("/marketing/beforehand_tail_view");
		return modelAndView;
	}
	
	/**
	 * 
	 * @Title: saveOrUpdateCaseTail 
	 * @Description: 保存营销跟踪信息
	 * @author caixiekang 
	 * @param caseTailVo 案件跟踪信息Vo
	 * @return
	 */
	@RequestMapping("/saveOrUpdateCaseTail")
	@UriKey(key = "com.zdsoft.finance.marketing.saveOrUpdateCaseTail")
	@ResponseBody
	public ResponseMsg saveOrUpdateCaseTail(CaseTailVo caseTailVo) {
		ResponseMsg msg = new ResponseMsg();
		try{
			String id = caseTailVo.getId();
			CaseTail caseTail = caseTailVo.toPo();
			CaseTail caseTail2 = null;
			if(ObjectHelper.isNotEmpty(id)){
				caseTail2 = caseTailService.findOne(id);
				BeanUtils.copyProperties(caseTail, caseTail2, new String[]{"id","createBy","createOrgCd","createTime"});
				caseTail2 = caseTailService.updateEntity(caseTail);
			}else{
				caseTail2 = caseTailService.saveEntity(caseTail);
			}
			msg.setMsg("保存营销跟踪信息成功");
			msg.setResultStatus(ResponseMsg.SUCCESS);
			//msg.setId(caseTail2.getId());
		}catch(Exception exception){
			logger.error("保存营销跟踪信息失败",exception);
			exception.printStackTrace();
			msg.setResultStatus(ResponseMsg.APP_ERROR);
			msg.setMsg("保存营销跟踪信息失败");
		}
		return msg;
	}
	
	/**
	 * 
	 * @Title: listCaseTails 
	 * @Description: 显示案件跟踪历史记录列表
	 * @author caixiekang 
	 * @param mv
	 * @param caseApplyId 案件id
	 * @param projectId
	 * @return
	 */
	@RequestMapping("/listCaseTails")
	@UriKey(key = "com.zdsoft.finance.marketing.listCaseTails")
	public ModelAndView listCaseTails(ModelAndView mv,String caseApplyId,String projectId) {
		if(ObjectHelper.isEmpty(caseApplyId)){
			caseApplyId = projectId;
		}
		try {
			//获取案件跟踪信息历史记录,拼装
			List<CaseTail> caseTails = caseTailService.findByCaseApplyId(caseApplyId);
			if(ObjectHelper.isNotEmpty(caseTails)){
				List<CaseTailVo> caseTailVos = new ArrayList<>();
				for (CaseTail caseTail : caseTails) {
					caseTailVos.add(new CaseTailVo(caseTail));
				}
				mv.addObject("caseTailVos", caseTailVos);
			}
			//获取当前操作的员工信息
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("跳转到营销跟踪失败", e);
		}
		mv.setViewName("/marketing/beforehand_tail_records_view");
		return mv;
	}
}