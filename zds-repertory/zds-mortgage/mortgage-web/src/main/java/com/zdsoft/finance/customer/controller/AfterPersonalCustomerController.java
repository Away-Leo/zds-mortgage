package com.zdsoft.finance.customer.controller;

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
import com.zdsoft.finance.customer.entity.AfterAddress;
import com.zdsoft.finance.customer.entity.AfterContact;
import com.zdsoft.finance.customer.entity.AfterPersonaAssociation;
import com.zdsoft.finance.customer.entity.AfterPersonalCustomer;
import com.zdsoft.finance.customer.service.AfterAddressService;
import com.zdsoft.finance.customer.service.AfterContactService;
import com.zdsoft.finance.customer.service.AfterPersonaAssociationService;
import com.zdsoft.finance.customer.service.AfterPersonalCustomerService;
import com.zdsoft.finance.customer.vo.AfterAddressVo;
import com.zdsoft.finance.customer.vo.AfterPersonalCustomerVo;
import com.zdsoft.finance.marketing.entity.CaseApplyAfterCustomer;
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
 * @Title: PostLoanPersonalController.java 
 * @ClassName: PostLoanPersonalController 
 * @Description: 贷后个人客户信息Controller 
 * @author zhangchao 
 * @date 2017年2月6日 上午10:43:57 
 * @version V1.0
 */
@Controller
@RequestMapping("afterPersonalCustomer")
public class AfterPersonalCustomerController extends BaseController{
	
	@Autowired
	private AfterPersonalCustomerService afterPersonalCustomerService;
	
	@Autowired
	private AfterPersonaAssociationService afterPersonaAssociationService;
	
	@Autowired
	private AfterAddressService afterAddressService;
	@Autowired
	private AfterContactService afterContactService;

	@Autowired
	private com.zdsoft.essential.client.service.CED CED;
	
	/**
	 * 
	 * @Title: initCreditEntrust 
	 * @Description: 客户管理注册菜单
	 * @author zhangchao 
	 * @return 客户管理页面
	 */
	@RequestMapping("/initCustomer")
	@UriKey(key = "com.cnfh.customer.initCustomer")
	@Menu(resource = "com.cnfh.customer.initCustomer", label = "客户信息管理", group = "customer", order = 1)
	public ModelAndView initCreditEntrust() {
		String EmpCd = null;
		try {
			EmpCd = CED.getLoginUser().getEmpCd();
		} catch (Exception e) {
			logger.error("获取当前登录人失败", e);
			e.printStackTrace();
		}
		Map<String, Object> optional = new HashMap<String, Object>();
		optional.put("EmpCd", EmpCd);
		return new ModelAndView("/customer/viewlistcustomer" , optional);
	}
	
	/**
	 * 
	 * @Title: getlatestCustomers 
	 * @Description: 客户管理查询列表
	 * @author zhangchao 
	 * @param request
	 * @param jsoncallback
	 * @param pageable
	 * @return
	 */
	@RequestMapping("/getCustomers")
	@UriKey(key = "com.cnfh.customer.getCustomers")
	@ResponseBody
	public String getlatestCustomers(HttpServletRequest request, String jsoncallback, PageRequest pageable) {

		// 获取页面封装的查询参数 
		@SuppressWarnings("unchecked")
		List<QueryObj> queryObjs = (List<QueryObj>) request.getAttribute("listObj");

		// 分页客户信息管理
		Page<AfterPersonalCustomer> postLoanPersonalPage = afterPersonalCustomerService.findByHqlConditions(pageable, queryObjs);
		List<AfterPersonalCustomerVo> postLoanPersonalVos = new ArrayList<AfterPersonalCustomerVo>();
		for (AfterPersonalCustomer postLoanPersonal : postLoanPersonalPage.getRecords()) {
			AfterPersonalCustomerVo postLoanPersonalVo = new AfterPersonalCustomerVo(postLoanPersonal);
			postLoanPersonalVo = SimpleCodeToName(postLoanPersonalVo);
			AfterAddress postLoanAddress = afterAddressService.findByCustomerNameAndCustomerIdAndAddressType(postLoanPersonalVo.getCustomerName(), postLoanPersonalVo.getId(), "t0930");
			if(ObjectHelper.isNotEmpty(postLoanAddress)){
				postLoanAddress = SimpleCodeToName(postLoanAddress);
				postLoanPersonalVo.setHomeAddressStr(postLoanAddress.getProvince()+"-"+postLoanAddress.getCity()+"-"+postLoanAddress.getDistrict()+"-"+postLoanAddress.getAddress());
				postLoanPersonalVos.add(postLoanPersonalVo);
			}
			
		}
		ResponseMsg msg = new ResponseMsg();
		msg.setMsg("列表查询成功");
		msg.setResultStatus(ResponseMsg.SUCCESS);
		msg.setTotal((long) postLoanPersonalVos.size());
		msg.setRows(postLoanPersonalVos);

		return ObjectHelper.objectToJson(msg, jsoncallback);
	}
	
	/**
	 *
	 * @Title: addCustomer 
	 * @Description:  客户新增
	 * @author zhangchao 
	 * @return 客户新增页面
	 */
	@RequestMapping("/addCustomer")
	@UriKey(key = "com.cnfh.customer.addCustomer")
	@ResponseBody
	public ModelAndView addCustomer() {
		return new ModelAndView("/customer/addcustomer");
	}
	
	/**
	 *
	 * @Title: saveCustomer 
	 * @Description:  客户信息保存
	 * @author zhangchao 
	 * @param postLoanPersonalVo 客户信息
	 * @return
	 */
	@RequestMapping("/saveCustomer")
	@UriKey(key = "com.cnfh.customer.saveCustomer")
	@ResponseBody
	public ResponseMsg saveCustomer(AfterPersonalCustomerVo postLoanPersonalVo) {
		AfterPersonalCustomer postLoanPersonal = null;
		ResponseMsg msg = new ResponseMsg();
		Map<String, Object> latestCustomerMap = new HashMap<String, Object>();
		
		// 将Vo转成Po
		postLoanPersonal = postLoanPersonalVo.toPO();
		
		AfterPersonalCustomerVo newPostLoanPersonalVo = null;
		AfterPersonalCustomer newPostLoanPersonal = new AfterPersonalCustomer();

		// 执行保存
		try {
			if(ObjectHelper.isNotEmpty(postLoanPersonal.getId())){
				newPostLoanPersonal = afterPersonalCustomerService.findOne(postLoanPersonal.getId());
				Date date = new Date();
				postLoanPersonal.setUpdateTime(date);
				postLoanPersonal.setCreateTime(newPostLoanPersonal.getCreateTime());
				postLoanPersonal.setCreateBy(CED.getLoginUser().getEmpCd());
				postLoanPersonal.setCreateOrgCd(CED.getLoginUser().getOrgCd());
				postLoanPersonal.setCreatorName(CED.getLoginUser().getEmpNm());
				newPostLoanPersonal = afterPersonalCustomerService.updateEntity(postLoanPersonal);
			}else{
				Date date = new Date();
				postLoanPersonal.setCreateTime(date);
				postLoanPersonal.setCreateBy(CED.getLoginUser().getEmpCd());
				postLoanPersonal.setCreateOrgCd(CED.getLoginUser().getOrgCd());
				postLoanPersonal.setCreatorName(CED.getLoginUser().getEmpNm());
				newPostLoanPersonal = afterPersonalCustomerService.saveEntity(postLoanPersonal);
			}
			newPostLoanPersonalVo = new AfterPersonalCustomerVo(newPostLoanPersonal);
			latestCustomerMap.put("newPostLoanPersonalVo", newPostLoanPersonalVo);
			msg.setResultStatus(ResponseMsg.SUCCESS);
			msg.setOptional(latestCustomerMap);
			msg.setMsg("保存成功");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("postLoanPersonal保存失败", e);
			msg.setResultStatus(ResponseMsg.APP_ERROR);
			msg.setMsg(e.getMessage());
		}
		return msg;
	}
	
	/**
	 *
	 * @Title: saveMarital 
	 * @Description:  配偶信息保存
	 * @author zhangchao 
	 * @param postLoanPersonalVo 客户信息
	 * @param customerId 主借人客户id
	 * @return
	 */
	@RequestMapping("/saveMarital")
	@UriKey(key = "com.cnfh.customer.saveMarital")
	@ResponseBody
	public ResponseMsg saveMarital(AfterPersonalCustomerVo postLoanPersonalVo, String customerId) {
		AfterPersonalCustomer postLoanPersonal = null;
		ResponseMsg msg = new ResponseMsg();
		Map<String, Object> latestCustomerMap = new HashMap<String, Object>();

		// 将Vo转成Po
		postLoanPersonal = postLoanPersonalVo.toPO();
		
		//返回页面的值
		AfterPersonalCustomerVo newPostLoanPersonalVo = null;
		AfterPersonalCustomer newPostLoanPersonal = new AfterPersonalCustomer();

		// 执行保存
		try {
			//查询主借人信息
			AfterPersonalCustomer customer = afterPersonalCustomerService.findOne(customerId);
			
			//将主借人的id赋值给配偶的配偶id字段
			//postLoanPersonal.setCustomerId(customerId);
			if(ObjectHelper.isNotEmpty(postLoanPersonal.getId())){
				newPostLoanPersonal = afterPersonalCustomerService.findOne(postLoanPersonal.getId());
				Date date = new Date();
				postLoanPersonal.setUpdateTime(date);
				postLoanPersonal.setCreateTime(newPostLoanPersonal.getCreateTime());
				postLoanPersonal.setCreateBy(CED.getLoginUser().getEmpCd());
				postLoanPersonal.setCreateOrgCd(CED.getLoginUser().getOrgCd());
				postLoanPersonal.setCreatorName(CED.getLoginUser().getEmpNm());
				newPostLoanPersonal = afterPersonalCustomerService.updateEntity(postLoanPersonal);
			}else{
				Date date = new Date();
				postLoanPersonal.setCreateTime(date);
				postLoanPersonal.setCreateBy(CED.getLoginUser().getEmpCd());
				postLoanPersonal.setCreateOrgCd(CED.getLoginUser().getOrgCd());
				postLoanPersonal.setCreatorName(CED.getLoginUser().getEmpNm());
				newPostLoanPersonal = afterPersonalCustomerService.saveEntity(postLoanPersonal);
				//保存主借人和配偶的关系
				AfterPersonaAssociation postLoanPersonaAssociation = new AfterPersonaAssociation();
				//xj 注释
				postLoanPersonaAssociation.setCustomerId(customer.getId());
				postLoanPersonaAssociation.setAfterPersonalCustomer(newPostLoanPersonal);
				postLoanPersonaAssociation.setRelationship("YWDM0014804");
				Date newDate = new Date();
				postLoanPersonaAssociation.setCreateTime(newDate);
				postLoanPersonaAssociation.setCreateBy(CED.getLoginUser().getEmpCd());
				postLoanPersonaAssociation.setCreateOrgCd(CED.getLoginUser().getOrgCd());
				afterPersonaAssociationService.saveEntity(postLoanPersonaAssociation);
			}
			
			//将配偶id赋值给主借人的配偶id字段，然后保存
			//customer.setCustomerId(newPostLoanPersonal.getId());
			Date newDate = new Date();
			customer.setCreateTime(newDate);
			customer.setCreateTime(customer.getCreateTime());
			customer.setCreateBy(CED.getLoginUser().getEmpCd());
			customer.setCreateOrgCd(CED.getLoginUser().getOrgCd());
			customer.setCreatorName(CED.getLoginUser().getEmpNm());
			afterPersonalCustomerService.updateEntity(customer);
			
			//返回页面值设置
			newPostLoanPersonalVo = new AfterPersonalCustomerVo(newPostLoanPersonal);
			latestCustomerMap.put("newLatestCustomerVo", newPostLoanPersonalVo);
			msg.setResultStatus(ResponseMsg.SUCCESS);
			msg.setOptional(latestCustomerMap);
			msg.setMsg("保存成功");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("latestCustomer保存失败", e);
			msg.setResultStatus(ResponseMsg.APP_ERROR);
			msg.setMsg(e.getMessage());
		}
		return msg;
	}
	
	/**
	 *
	 * @Title: findByCredentiaTypeAndCredentialNo 
	 * @Description:  根据证件类型和证件编号判断客户是否存在，如果存在则页面赋值
	 * @author zhangchao 
	 * @param credentiaType 证件类型
	 * @param credentialNo 证件编号
	 * @return
	 */
	@RequestMapping("/findByCredentiaTypeAndCredentialNo")
	@UriKey(key = "com.cnfh.customer.findByCredentiaTypeAndCredentialNo")
	@ResponseBody
	public ResponseMsg findByCredentiaTypeAndCredentialNo(String credentialType, String credentialNo) {
		AfterPersonalCustomer postLoanPersonal = null;
		AfterPersonalCustomerVo postLoanPersonalVo = null;
		ResponseMsg msg = new ResponseMsg();
		Map<String, Object> postLoanPersonalMap = new HashMap<String, Object>();

		//根据条件查询
		try {
			postLoanPersonal = afterPersonalCustomerService.findByCredentiaTypeAndCredentialNo(credentialType, credentialNo);
		} catch (Exception e) {
			e.printStackTrace();
			msg.setResultStatus(ResponseMsg.APP_ERROR);
			msg.setMsg("查询出错，请看日志");
			logger.error("latestCustomer查询失败", e);
		}
		if(ObjectHelper.isNotEmpty(postLoanPersonal)){
			postLoanPersonalVo = new AfterPersonalCustomerVo(postLoanPersonal);
			postLoanPersonalMap.put("postLoanPersonalVo", postLoanPersonalVo);
			msg.setResultStatus(ResponseMsg.SUCCESS);
			msg.setMsg("该证件号已存在，页面赋值");
			msg.setOptional(postLoanPersonalMap);
		}else{
			msg.setResultStatus(ResponseMsg.APP_ERROR);
			msg.setMsg("证件号不存在，可新增");
		}
		return msg;
	}
	
	/**
	 *
	 * @Title: findMaritalByCustomerId 
	 * @Description:  根据客户id查询配偶信息（编辑页面）
	 * @author zhangchao 
	 * @param clientId 主借人客户id
	 * @return
	 */
	@RequestMapping("/findMaritalByCustomerId")
	@UriKey(key = "com.cnfh.customer.findMaritalByCustomerId")
	@ResponseBody
	public ResponseMsg findMaritalByCustomerId(String clientId) {
		AfterPersonalCustomer postLoanPersonal = null;
		AfterPersonalCustomerVo postLoanPersonalVo = null;
		ResponseMsg msg = new ResponseMsg();
		Map<String, Object> postLoanPersonalVoMap = new HashMap<String, Object>();

		//根据条件查询
		try {
			String id = afterPersonaAssociationService.findByPostLoanCustomerIdAndRelationship(clientId, "YWDM0014804");
			postLoanPersonal = afterPersonalCustomerService.findOne(id);
		} catch (Exception e) {
			e.printStackTrace();
			msg.setResultStatus(ResponseMsg.APP_ERROR);
			msg.setMsg("查询出错，请看日志");
			logger.error("latestCustomer查询失败", e);
		}
		if(ObjectHelper.isNotEmpty(postLoanPersonal)){
			postLoanPersonalVo = new AfterPersonalCustomerVo(postLoanPersonal);
			postLoanPersonalVoMap.put("maritalCustomerVo", postLoanPersonalVo);
			msg.setResultStatus(ResponseMsg.SUCCESS);
			msg.setMsg("查询配偶信息成功");
			msg.setOptional(postLoanPersonalVoMap);
		}else{
			msg.setResultStatus(ResponseMsg.APP_ERROR);
			msg.setMsg("配偶信息不存在");
		}
		return msg;
	}
	
	/**
	 *
	 * @Title: editCustomer 
	 * @Description:  客户信息编辑
	 * @author zhangchao 
	 * @param id 客户id
	 * @param jsoncallback
	 * @return
	 */
	@RequestMapping("/editCustomer")
	@UriKey(key = "com.cnfh.customer.editCustomer")
	@ResponseBody
	public ModelAndView editCustomer(String id, String jsoncallback) {
		Map<String, Object> optional = new HashMap<String, Object>();
		AfterPersonalCustomer postLoanPersonal = null;
		AfterPersonalCustomerVo postLoanPersonalVo = null;

		// 查询
		try {
			postLoanPersonal = afterPersonalCustomerService.findOne(id);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("latestCustomer查询失败", e);
		}
		
		//客户对象数据组装
		if(ObjectHelper.isNotEmpty(postLoanPersonal)){
			postLoanPersonalVo = new AfterPersonalCustomerVo(postLoanPersonal);
		}else{
			logger.error("latestCustomer查询结果为空");
		}
		
		optional.put("postLoanPersonalVo", postLoanPersonalVo);
		return new ModelAndView("/customer/editcustomer", optional);
	}
	
	/**
	 * 根据客户id查询配偶信息（显示页面）
	 * @Title: findMaritalCustomerByCustomerId 
	 * @Description: 
	 * @author zhangchao 
	 * @param clientId 主借人客户id
	 * @return
	 */
	@RequestMapping("/findMaritalCustomerByCustomerId")
	@UriKey(key = "com.cnfh.customer.findMaritalCustomerByCustomerId")
	@ResponseBody
	public ResponseMsg findMaritalCustomerByCustomerId(String clientId) {
		AfterPersonalCustomer postLoanPersonal = null;
		AfterPersonalCustomerVo postLoanPersonalVo = null;
		ResponseMsg msg = new ResponseMsg();
		Map<String, Object> postLoanPersonalMap = new HashMap<String, Object>();

		//根据条件查询
		try {
			String id = afterPersonaAssociationService.findByPostLoanCustomerIdAndRelationship(clientId, "YWDM0014804");
			postLoanPersonal = afterPersonalCustomerService.findOne(id);
		} catch (Exception e) {
			e.printStackTrace();
			msg.setResultStatus(ResponseMsg.APP_ERROR);
			msg.setMsg("查询出错，请看日志");
			logger.error("latestCustomer查询失败", e);
		}
		if(ObjectHelper.isNotEmpty(postLoanPersonal)){
			postLoanPersonalVo = new AfterPersonalCustomerVo(postLoanPersonal);
			//值转换
			postLoanPersonalVo = SimpleCodeToName(postLoanPersonalVo);
			postLoanPersonalMap.put("maritalCustomerVo", postLoanPersonalVo);
			msg.setResultStatus(ResponseMsg.SUCCESS);
			msg.setMsg("查询配偶信息成功");
			msg.setOptional(postLoanPersonalMap);
		}else{
			msg.setResultStatus(ResponseMsg.APP_ERROR);
			msg.setMsg("配偶信息不存在");
		}
		return msg;
	}
	
	/**
	 *
	 * @Title: viewCustomer 
	 * @Description:  客户信息查看
	 * @author zhangchao 
	 * @param id 客户id
	 * @param jsoncallback
	 * @return
	 */
	@RequestMapping("/viewCustomer")
	@UriKey(key = "com.cnfh.customer.viewCustomer")
	@ResponseBody
	public ModelAndView viewCustomer(String id, String jsoncallback) {
		Map<String, Object> optional = new HashMap<String, Object>();
		AfterPersonalCustomer postLoanPersonal = null;
		AfterPersonalCustomerVo postLoanPersonalVo = null;

		// 查询
		try {
			postLoanPersonal = afterPersonalCustomerService.findOne(id);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("latestCustomer查询失败", e);
		}
		
		//客户对象数据组装
		if(ObjectHelper.isNotEmpty(postLoanPersonal)){
			postLoanPersonalVo = new AfterPersonalCustomerVo(postLoanPersonal);
			postLoanPersonalVo = SimpleCodeToName(postLoanPersonalVo);
		}else{
			logger.error("latestCustomer查询结果为空");
		}
		
		optional.put("postLoanPersonalVo", postLoanPersonalVo);
		return new ModelAndView("/customer/viewcustomer", optional);
	}
	
	/**
	 *
	 * @Title: SimpleCodeToName 
	 * @Description:  SimpleCode值转换
	 * @author zhangchao 
	 * @param postLoanPersonalVo 客户对象
	 * @return
	 */
	public AfterPersonalCustomerVo SimpleCodeToName(AfterPersonalCustomerVo postLoanPersonalVo){
		try {
			if(ObjectHelper.isNotEmpty(postLoanPersonalVo.getCredentialType())){
				postLoanPersonalVo.setCredentialType(SimpleCodeTurnOn(postLoanPersonalVo.getCredentialType()));
			}
			
			if(ObjectHelper.isNotEmpty(postLoanPersonalVo.getMaritalStatus())){
				postLoanPersonalVo.setMaritalStatus(SimpleCodeTurnOn(postLoanPersonalVo.getMaritalStatus()));
			}
			
			if(ObjectHelper.isNotEmpty(postLoanPersonalVo.getCareerType())){
				postLoanPersonalVo.setCareerType(SimpleCodeTurnOn(postLoanPersonalVo.getCareerType()));
			}
			
			if(ObjectHelper.isNotEmpty(postLoanPersonalVo.getDegree())){
				postLoanPersonalVo.setDegree(SimpleCodeTurnOn(postLoanPersonalVo.getDegree()));
			}
			
			if(ObjectHelper.isNotEmpty(postLoanPersonalVo.getGender())){
				postLoanPersonalVo.setGender(SimpleCodeTurnOn(postLoanPersonalVo.getGender()));
			}
			
			if(ObjectHelper.isNotEmpty(postLoanPersonalVo.getLiveAge())){
				postLoanPersonalVo.setLiveAge(SimpleCodeTurnOn(postLoanPersonalVo.getLiveAge()));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return postLoanPersonalVo;
	}
	
	/**
	 *
	 * @Title: SimpleCodeToName 
	 * @Description: SimpleCode值转换 
	 * @author zhangchao 
	 * @param postLoanAddress 客户地址
	 * @return
	 */
	public AfterAddress SimpleCodeToName(AfterAddress postLoanAddress){
		try {
			if(ObjectHelper.isNotEmpty(postLoanAddress.getProvince())){
				postLoanAddress.setProvince(SimpleCodeTurnOn(postLoanAddress.getProvince()));
			}
			
			if(ObjectHelper.isNotEmpty(postLoanAddress.getCity())){
				postLoanAddress.setCity(SimpleCodeTurnOn(postLoanAddress.getCity()));
			}
			
			if(ObjectHelper.isNotEmpty(postLoanAddress.getDistrict())){
				postLoanAddress.setDistrict(SimpleCodeTurnOn(postLoanAddress.getDistrict()));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return postLoanAddress;
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
	
	/**
	 * 
	 * @Title: listGuarantor 
	 * @Description: 担保人信息
	 * @author xj 
	 * @param caseApplyId 案件id
	 * @return
	 */
  	@RequestMapping("/listGuarantor")
    @UriKey(key = "com.cnfh.rms.postLoanPersonal.listGuarantor")
    @ResponseBody
    public ResponseMsg listGuarantor(String caseApplyId){
        ResponseMsg msg = new ResponseMsg();
        List<AfterPersonalCustomer> list = afterPersonalCustomerService.findByCaseApplyIdAndJoinType(caseApplyId, CaseApplyAfterCustomer.GUARANTOR);
        List<AfterPersonalCustomerVo> listVo = new ArrayList<>();
        for (AfterPersonalCustomer postLoanPersonal : list) {
        	AfterPersonalCustomerVo postLoanPersonalVo = new AfterPersonalCustomerVo(postLoanPersonal);
        	//家庭地址
        	AfterAddress homeAddress = afterAddressService.findByAddressTypeAndCustomerId(AfterAddress.HOME_ADDRESS, postLoanPersonal.getId());
        	if(ObjectHelper.isNotEmpty(homeAddress)){
        		AfterAddressVo postLoanAddressVo = new  AfterAddressVo(homeAddress);
        		postLoanPersonalVo.setHomeAddressStr(postLoanAddressVo.getProvinceName()+" "+postLoanAddressVo.getCityName()+" "+ postLoanAddressVo.getDistrictName());
        	}
        	//户籍地址
        	AfterAddress householdAddress = afterAddressService.findByAddressTypeAndCustomerId(AfterAddress.HOUSEHOLD_REGISTRATION_ADDRESS, postLoanPersonal.getId());
        	if(ObjectHelper.isNotEmpty(householdAddress)){
        		AfterAddressVo postLoanAddressVo = new  AfterAddressVo(householdAddress);
        		postLoanPersonalVo.setHouseholdRegistrationStr(postLoanAddressVo.getProvinceName()+" "+postLoanAddressVo.getCityName()+" "+ postLoanAddressVo.getDistrictName());
        	}
        	//移动电话
        	List<AfterContact> postLoanContacts = afterContactService.findContactListByCustomerId(postLoanPersonal.getId());
        	if(ObjectHelper.isNotEmpty(postLoanContacts)){
        		for (AfterContact postLoanContact : postLoanContacts) {
					if("YWDM0011701".equals(postLoanContact.getContactType())){
						postLoanPersonalVo.setDefaultPhoneNumber(postLoanContact.getPhoneNumber());
						break;
					}
				}
        	}
        	listVo.add(postLoanPersonalVo);
        }
        msg.setMsg("担保人信息列表查询成功");
        msg.setResultStatus(ResponseMsg.SUCCESS);
        msg.setRows(listVo);
        
        return msg;
    }
	
}
