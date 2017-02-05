package com.zdsoft.finance.app.customer.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zdsoft.finance.app.customer.vo.AppCustomerVo;
import com.zdsoft.finance.common.utils.AppStatus;
import com.zdsoft.finance.common.utils.app.AppServerUtil;
import com.zdsoft.finance.customer.entity.BeforeAddress;
import com.zdsoft.finance.customer.entity.BeforeContact;
import com.zdsoft.finance.customer.entity.BeforePersonalCustomer;
import com.zdsoft.finance.customer.entity.BeforeWorkUnit;
import com.zdsoft.finance.customer.service.BeforePersonalCustomerService;
import com.zdsoft.finance.marketing.entity.BeforehandApply;
import com.zdsoft.finance.marketing.service.BeforehandApplyService;
import com.zdsoft.framework.core.common.util.ObjectHelper;
import com.zdsoft.framework.core.commweb.component.BaseController;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;
/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title:AppCustomerController.java
 * @Package:com.zdsoft.finance.app.customer.controller
 * @Description:app客户基本信息
 * @author: xj
 * @date:2017年1月13日 上午9:48:23
 * @version:v1.0
 */
@Controller
@RequestMapping("/server/bizCenter/loanApplication")
public class AppCustomerController extends BaseController {
	@Autowired
	private BeforePersonalCustomerService beforePersonalCustomerService;
	@Autowired
	private BeforehandApplyService beforehandApplyService;
	/**
	 * 
	 * 贷款申请-添加/修改客户
	 *
	 * @author xj
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "/addCustomer",produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String addCustomer(AppCustomerVo appCustomerVo) throws Exception{
		String result = null;
		try {
			//参与人
			BeforePersonalCustomer customer = this.transformCustomer(appCustomerVo);
			//地址
			List<BeforeAddress> allAddress = this.transformAddress(appCustomerVo);
			//工作单位
			List<BeforeWorkUnit> beforeWorkUnits = this.transformWorkUnit(appCustomerVo);
			//联系方式
			List<BeforeContact> contacts = this.transformContact(appCustomerVo);
			//获取案件Id
			BeforehandApply beforehandApply = beforehandApplyService.findOne(appCustomerVo.getApplyId());
			result = beforePersonalCustomerService.saveOrUpdateAppCustomer(beforehandApply.getCaseApplyId(), customer,
					allAddress, beforeWorkUnits, contacts, false,appCustomerVo.getToken());
		} catch (Exception e) {
			logger.error("贷款申请-添加/修改客户", e);
			result = AppServerUtil.buildJsonMessage(AppStatus.SystemError,"系统异常！");
		}
		return result;
	}
	/**
	 * 
	 * 新增配偶信息
	 *
	 * @author xj
	 * @param appCustomerVo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/addCustomerSpouse",produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String addCustomerSpouse(AppCustomerVo appCustomerVo) throws Exception{
		String result = null;
		try {
			//参与人
			BeforePersonalCustomer customer = this.transformCustomer(appCustomerVo);
			//地址
			List<BeforeAddress> allAddress = this.transformAddress(appCustomerVo);
			//工作单位
			List<BeforeWorkUnit> beforeWorkUnits = this.transformWorkUnit(appCustomerVo);
			//联系方式
			List<BeforeContact> contacts = this.transformContact(appCustomerVo);
			//获取案件Id
			BeforehandApply beforehandApply = beforehandApplyService.findOne(appCustomerVo.getApplyId());
			result = beforePersonalCustomerService.saveOrUpdateAppCustomer(beforehandApply.getCaseApplyId(), customer,
					allAddress, beforeWorkUnits, contacts, true,appCustomerVo.getToken());
			logger.info("新增配偶信息成功");
		} catch (Exception e) {
			logger.error("新增配偶信息", e);
			result = AppServerUtil.buildJsonMessage(AppStatus.SystemError,"系统异常！");
		}
		return result;
	}
	
	//联系方式
	private List<BeforeContact> transformContact(AppCustomerVo appCustomerVo) {
		//联系方式json字符串
		String contactsJson = appCustomerVo.getContacts();
		List<BeforeContact> result = new ArrayList<BeforeContact>();
		if(ObjectHelper.isNotEmpty(contactsJson)){
			JSONArray fromObject = JSONArray.fromObject(contactsJson);
			@SuppressWarnings("unchecked")
			List<Map<String,String>> contacts = JSONArray.toList(fromObject, new HashMap<String,String>(),
					new JsonConfig());
			for (Map<String, String> map : contacts) {
				BeforeContact beforeContact = new BeforeContact();
				//联系类型
				beforeContact.setContactType(map.get("type"));
				//电话号码
				beforeContact.setPhoneNumber(map.get("phoneNum"));
				//姓名
				beforeContact.setCustomerName(map.get("customerName"));
				result.add(beforeContact);
			}
		}
		return result;
	}
	//工作单位
	private List<BeforeWorkUnit> transformWorkUnit(AppCustomerVo appCustomerVo) {
		List<BeforeWorkUnit> result = new ArrayList<BeforeWorkUnit>();
		//工作单位json字符串
		String workUnitsJson = appCustomerVo.getWorkUnits();
		if(ObjectHelper.isNotEmpty(workUnitsJson)){
			JSONArray fromObject = JSONArray.fromObject(workUnitsJson);
			@SuppressWarnings("unchecked")
			List<Map<String,String>> receivablePlanListVo = JSONArray.toList(fromObject, new HashMap<String,String>(),
					new JsonConfig());
			for (Map<String, String> map : receivablePlanListVo) {
				BeforeWorkUnit beforeWorkUnit = new	BeforeWorkUnit();
				//姓名
				beforeWorkUnit.setWorkUnitName(map.get("workUnitName"));
				//工作单位名称
				beforeWorkUnit.setCompanyName(map.get("companyName"));
				//单位 省
				beforeWorkUnit.setProvince(map.get("province"));
				//单位 市
				beforeWorkUnit.setCity(map.get("city"));
				//单位 区
				beforeWorkUnit.setDistrict(map.get("district"));
				//单位详细地址
				beforeWorkUnit.setWorkUnitAddress(map.get("detail"));
				//职务
				beforeWorkUnit.setPosition(map.get("position"));
				//单位性质
				beforeWorkUnit.setWorkUnitNature(map.get("workUnitNature"));
				//行业性质
				beforeWorkUnit.setIndustryType(map.get("industryType"));
				//行业
				beforeWorkUnit.setIndustry(map.get("industry"));
				//工作年限
				beforeWorkUnit.setWorkYears(Integer.parseInt(map.get("workYears")));
				result.add(beforeWorkUnit);
			}

		}
		return result;
	}
	//地址
	private List<BeforeAddress> transformAddress(AppCustomerVo appCustomerVo) {
		List<BeforeAddress> list = new ArrayList<BeforeAddress>();
		//户籍地址
		//省
		String province = appCustomerVo.getProvince();
		if(ObjectHelper.isNotEmpty(province)){
			//市
			String city = appCustomerVo.getCity();
			//区
			String district = appCustomerVo.getDistrict();
			//详细地址
			String address = appCustomerVo.getAddress();
			BeforeAddress residenceAddress = new BeforeAddress();
			residenceAddress.setProvince(province);
			residenceAddress.setCity(city);
			residenceAddress.setDistrict(district);
			residenceAddress.setAddress(address);
			residenceAddress.setAddressType(BeforeAddress.HOUSEHOLD_REGISTRATION_ADDRESS);
			list.add(residenceAddress);
		}
		return list;
	}
	//参与人
	private BeforePersonalCustomer transformCustomer(AppCustomerVo appCustomerVo) {
		BeforePersonalCustomer  customer = new BeforePersonalCustomer();
		//id
		String customerId = appCustomerVo.getCustomerId();
		if(ObjectHelper.isNotEmpty(customerId)){
			customer.setId(customerId);
		}
		//姓名
		String customerName = appCustomerVo.getName();
		customer.setCustomerName(customerName);
		//证件类型
		String credentiaType = appCustomerVo.getCredentiaType();
		customer.setCredentialType(credentiaType);
		//证件号码
		String credentialNo = appCustomerVo.getCredentialNo();
		customer.setCredentialNo(credentialNo);
		//出生日期
		String birthdayDate = appCustomerVo.getBirthdayDate();
		if(ObjectHelper.isNotEmpty(birthdayDate)){
			customer.setBirthdayDate(Long.valueOf(birthdayDate));
		}
		//性别 
		String gender = appCustomerVo.getGender();
		customer.setGender(gender);
		//曾用名
		String formerName = appCustomerVo.getFormerName();
		customer.setFormerName(formerName);
		//参与类型
		String joinType = appCustomerVo.getJoinType();
		customer.setJoinType(joinType);
		//婚姻状况
		String maritalStatus = appCustomerVo.getMaritalStatus();
		customer.setMaritalStatus(maritalStatus);
		//是否是实际用款人
		String actualUsePerson = appCustomerVo.getActualUsePerson();
		customer.setActualUsePerson(actualUsePerson);
		//职业类型
		String careerType = appCustomerVo.getCareerType();
		customer.setCareerType(careerType);
		//教育程度
		String degree = appCustomerVo.getDegree();
		customer.setDegree(degree);
		//居住年限
		String liveAge = appCustomerVo.getLiveAge();
		customer.setLiveAge(liveAge);
		//与主借人关系
		String relationship = appCustomerVo.getRelationship();
		customer.setRelationship(relationship);
		//邮箱地址
		String email = appCustomerVo.getEmail();
		customer.setEmail(email);
		//配偶id
		String spouseId = appCustomerVo.getSpouseId();
		customer.setSpouseId(spouseId);
		//身份证头像
		String fileId = appCustomerVo.getFileId();
		customer.setAttachmentId(fileId);
		return customer;
	}

}
