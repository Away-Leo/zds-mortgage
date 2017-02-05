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
import com.zdsoft.finance.customer.entity.PostLoanAddress;
import com.zdsoft.finance.customer.entity.PostLoanPersonaAssociation;
import com.zdsoft.finance.customer.entity.PostLoanPersonal;
import com.zdsoft.finance.customer.service.PostLoanAddressService;
import com.zdsoft.finance.customer.service.PostLoanPersonaAssociationService;
import com.zdsoft.finance.customer.service.PostLoanPersonalService;
import com.zdsoft.finance.customer.vo.PostLoanPersonalVo;
import com.zdsoft.framework.core.common.dto.ResponseMsg;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.PageRequest;
import com.zdsoft.framework.core.common.util.ObjectHelper;
import com.zdsoft.framework.core.commweb.annotation.UriKey;
import com.zdsoft.framework.core.commweb.component.BaseController;
import com.zdsoft.framework.cra.annotation.Menu;

/**
 * 贷后个人客户信息
 * @author <a href="mailto:502549751@qq.com">zhangchao</a>
 * @createTime: 2017-01-10 14::41
 * @version: 1.0
 *
 */
@Controller
@RequestMapping("postLoanPersonal")
public class PostLoanPersonalController extends BaseController{
	
	@Autowired
	private PostLoanPersonalService postLoanPersonalService;
	
	@Autowired
	private PostLoanPersonaAssociationService postLoanPersonaAssociationService;
	
	@Autowired
	private PostLoanAddressService postLoanAddressService;

	@Autowired
	private com.zdsoft.essential.client.service.CED CED;
	
	/**
	 * 客户管理注册菜单
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
	 * 客户管理查询列表
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
		Page<PostLoanPersonal> postLoanPersonalPage = postLoanPersonalService.findByHqlConditions(pageable, queryObjs);
		List<PostLoanPersonalVo> postLoanPersonalVos = new ArrayList<PostLoanPersonalVo>();
		for (PostLoanPersonal postLoanPersonal : postLoanPersonalPage.getRecords()) {
			PostLoanPersonalVo postLoanPersonalVo = new PostLoanPersonalVo(postLoanPersonal);
			postLoanPersonalVo = SimpleCodeToName(postLoanPersonalVo);
			PostLoanAddress postLoanAddress = postLoanAddressService.findByCustomerNameAndCustomerIdAndAddressType(postLoanPersonalVo.getCustomerName(), postLoanPersonalVo.getId(), "t0930");
			if(ObjectHelper.isNotEmpty(postLoanAddress)){
				postLoanAddress = SimpleCodeToName(postLoanAddress);
				postLoanPersonalVo.setAddress(postLoanAddress.getProvince()+"-"+postLoanAddress.getCity()+"-"+postLoanAddress.getDistrict()+"-"+postLoanAddress.getAddress());
			}
			postLoanPersonalVos.add(postLoanPersonalVo);
		}
		ResponseMsg msg = new ResponseMsg();
		msg.setMsg("列表查询成功");
		msg.setResultStatus(ResponseMsg.SUCCESS);
		msg.setTotal(postLoanPersonalPage.getTotalRows());
		msg.setRows(postLoanPersonalVos);

		return ObjectHelper.objectToJson(msg, jsoncallback);
	}
	
	/**
	 * 客户新增
	 * @return 客户新增页面
	 */
	@RequestMapping("/addCustomer")
	@UriKey(key = "com.cnfh.customer.addCustomer")
	@ResponseBody
	public ModelAndView addCustomer() {
		return new ModelAndView("/customer/addcustomer");
	}
	
	/**
	 * 客户信息保存
	 * @param latestCustomerVo 客户信息
	 * @return
	 */
	@RequestMapping("/saveCustomer")
	@UriKey(key = "com.cnfh.customer.saveCustomer")
	@ResponseBody
	public ResponseMsg saveCustomer(PostLoanPersonalVo postLoanPersonalVo) {
		PostLoanPersonal postLoanPersonal = null;
		ResponseMsg msg = new ResponseMsg();
		Map<String, Object> latestCustomerMap = new HashMap<String, Object>();
		
		// 将Vo转成Po
		postLoanPersonal = postLoanPersonalVo.toPO();
		
		PostLoanPersonalVo newPostLoanPersonalVo = null;
		PostLoanPersonal newPostLoanPersonal = new PostLoanPersonal();

		// 执行保存
		try {
			if(ObjectHelper.isNotEmpty(postLoanPersonal.getId())){
				newPostLoanPersonal = postLoanPersonalService.findOne(postLoanPersonal.getId());
				Date date = new Date();
				postLoanPersonal.setUpdateTime(date);
				postLoanPersonal.setCreateTime(newPostLoanPersonal.getCreateTime());
				postLoanPersonal.setCreateBy(CED.getLoginUser().getEmpCd());
				postLoanPersonal.setCreateOrgCd(CED.getLoginUser().getOrgCd());
				postLoanPersonal.setCreatorName(CED.getLoginUser().getEmpNm());
				newPostLoanPersonal = postLoanPersonalService.updateEntity(postLoanPersonal);
			}else{
				Date date = new Date();
				postLoanPersonal.setCreateTime(date);
				postLoanPersonal.setCreateBy(CED.getLoginUser().getEmpCd());
				postLoanPersonal.setCreateOrgCd(CED.getLoginUser().getOrgCd());
				postLoanPersonal.setCreatorName(CED.getLoginUser().getEmpNm());
				newPostLoanPersonal = postLoanPersonalService.saveEntity(postLoanPersonal);
			}
			newPostLoanPersonalVo = new PostLoanPersonalVo(newPostLoanPersonal);
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
	 * 配偶信息保存
	 * @param latestCustomerVo 客户信息
	 * @param customerId 主借人客户id
	 * @return
	 */
	@RequestMapping("/saveMarital")
	@UriKey(key = "com.cnfh.customer.saveMarital")
	@ResponseBody
	public ResponseMsg saveMarital(PostLoanPersonalVo postLoanPersonalVo, String customerId) {
		PostLoanPersonal postLoanPersonal = null;
		ResponseMsg msg = new ResponseMsg();
		Map<String, Object> latestCustomerMap = new HashMap<String, Object>();

		// 将Vo转成Po
		postLoanPersonal = postLoanPersonalVo.toPO();
		
		//返回页面的值
		PostLoanPersonalVo newPostLoanPersonalVo = null;
		PostLoanPersonal newPostLoanPersonal = new PostLoanPersonal();

		// 执行保存
		try {
			//查询主借人信息
			PostLoanPersonal customer = postLoanPersonalService.findOne(customerId);
			
			//将主借人的id赋值给配偶的配偶id字段
			postLoanPersonal.setCustomerId(customerId);
			if(ObjectHelper.isNotEmpty(postLoanPersonal.getId())){
				newPostLoanPersonal = postLoanPersonalService.findOne(postLoanPersonal.getId());
				Date date = new Date();
				postLoanPersonal.setUpdateTime(date);
				postLoanPersonal.setCreateTime(newPostLoanPersonal.getCreateTime());
				postLoanPersonal.setCreateBy(CED.getLoginUser().getEmpCd());
				postLoanPersonal.setCreateOrgCd(CED.getLoginUser().getOrgCd());
				postLoanPersonal.setCreatorName(CED.getLoginUser().getEmpNm());
				newPostLoanPersonal = postLoanPersonalService.updateEntity(postLoanPersonal);
			}else{
				Date date = new Date();
				postLoanPersonal.setCreateTime(date);
				postLoanPersonal.setCreateBy(CED.getLoginUser().getEmpCd());
				postLoanPersonal.setCreateOrgCd(CED.getLoginUser().getOrgCd());
				postLoanPersonal.setCreatorName(CED.getLoginUser().getEmpNm());
				newPostLoanPersonal = postLoanPersonalService.saveEntity(postLoanPersonal);
				//保存主借人和配偶的关系
				PostLoanPersonaAssociation postLoanPersonaAssociation = new PostLoanPersonaAssociation();
				postLoanPersonaAssociation.setPostLoanCustomerId(customer.getId());
				postLoanPersonaAssociation.setRelationtCustomerId(newPostLoanPersonal.getId());
				postLoanPersonaAssociation.setRelationship("r01434");
				Date newDate = new Date();
				postLoanPersonaAssociation.setCreateTime(newDate);
				postLoanPersonaAssociation.setCreateBy(CED.getLoginUser().getEmpCd());
				postLoanPersonaAssociation.setCreateOrgCd(CED.getLoginUser().getOrgCd());
				
				postLoanPersonaAssociationService.saveEntity(postLoanPersonaAssociation);
			}
			
			//将配偶id赋值给主借人的配偶id字段，然后保存
			customer.setCustomerId(newPostLoanPersonal.getId());
			Date newDate = new Date();
			customer.setCreateTime(newDate);
			customer.setCreateTime(customer.getCreateTime());
			customer.setCreateBy(CED.getLoginUser().getEmpCd());
			customer.setCreateOrgCd(CED.getLoginUser().getOrgCd());
			customer.setCreatorName(CED.getLoginUser().getEmpNm());
			postLoanPersonalService.updateEntity(customer);
			
			//返回页面值设置
			newPostLoanPersonalVo = new PostLoanPersonalVo(newPostLoanPersonal);
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
	 * 根据证件类型和证件编号判断客户是否存在，如果存在则页面赋值
	 * @param credentiaType 证件类型
	 * @param credentialNo 证件编号
	 * @return
	 */
	@RequestMapping("/findByCredentiaTypeAndCredentialNo")
	@UriKey(key = "com.cnfh.customer.findByCredentiaTypeAndCredentialNo")
	@ResponseBody
	public ResponseMsg findByCredentiaTypeAndCredentialNo(String credentiaType, String credentialNo) {
		PostLoanPersonal postLoanPersonal = null;
		PostLoanPersonalVo postLoanPersonalVo = null;
		ResponseMsg msg = new ResponseMsg();
		Map<String, Object> postLoanPersonalMap = new HashMap<String, Object>();

		//根据条件查询
		try {
			postLoanPersonal = postLoanPersonalService.findByCredentiaTypeAndCredentialNo(credentiaType, credentialNo);
		} catch (Exception e) {
			e.printStackTrace();
			msg.setResultStatus(ResponseMsg.APP_ERROR);
			msg.setMsg("查询出错，请看日志");
			logger.error("latestCustomer查询失败", e);
		}
		if(ObjectHelper.isNotEmpty(postLoanPersonal)){
			postLoanPersonalVo = new PostLoanPersonalVo(postLoanPersonal);
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
	 * 根据客户id查询配偶信息（编辑页面）
	 * @param clientId 主借人客户id
	 * @return
	 */
	@RequestMapping("/findMaritalByCustomerId")
	@UriKey(key = "com.cnfh.customer.findMaritalByCustomerId")
	@ResponseBody
	public ResponseMsg findMaritalByCustomerId(String clientId) {
		PostLoanPersonal postLoanPersonal = null;
		PostLoanPersonalVo postLoanPersonalVo = null;
		ResponseMsg msg = new ResponseMsg();
		Map<String, Object> postLoanPersonalVoMap = new HashMap<String, Object>();

		//根据条件查询
		try {
			String id = postLoanPersonaAssociationService.findByPostLoanCustomerIdAndRelationship(clientId, "r01434");
			postLoanPersonal = postLoanPersonalService.findOne(id);
		} catch (Exception e) {
			e.printStackTrace();
			msg.setResultStatus(ResponseMsg.APP_ERROR);
			msg.setMsg("查询出错，请看日志");
			logger.error("latestCustomer查询失败", e);
		}
		if(ObjectHelper.isNotEmpty(postLoanPersonal)){
			postLoanPersonalVo = new PostLoanPersonalVo(postLoanPersonal);
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
	 * 客户信息编辑
	 * @param id 客户id
	 * @param jsoncallback
	 * @return
	 */
	@RequestMapping("/editCustomer")
	@UriKey(key = "com.cnfh.customer.editCustomer")
	@ResponseBody
	public ModelAndView editCustomer(String id, String jsoncallback) {
		Map<String, Object> optional = new HashMap<String, Object>();
		PostLoanPersonal postLoanPersonal = null;
		PostLoanPersonalVo postLoanPersonalVo = null;

		// 查询
		try {
			postLoanPersonal = postLoanPersonalService.findOne(id);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("latestCustomer查询失败", e);
		}
		
		//客户对象数据组装
		if(ObjectHelper.isNotEmpty(postLoanPersonal)){
			postLoanPersonalVo = new PostLoanPersonalVo(postLoanPersonal);
		}else{
			logger.error("latestCustomer查询结果为空");
		}
		
		optional.put("postLoanPersonalVo", postLoanPersonalVo);
		return new ModelAndView("/customer/editcustomer", optional);
	}
	
	/**
	 * 根据客户id查询配偶信息（显示页面）
	 * @param clientId 主借人客户id
	 * @return
	 */
	@RequestMapping("/findMaritalCustomerByCustomerId")
	@UriKey(key = "com.cnfh.customer.findMaritalCustomerByCustomerId")
	@ResponseBody
	public ResponseMsg findMaritalCustomerByCustomerId(String clientId) {
		PostLoanPersonal postLoanPersonal = null;
		PostLoanPersonalVo postLoanPersonalVo = null;
		ResponseMsg msg = new ResponseMsg();
		Map<String, Object> postLoanPersonalMap = new HashMap<String, Object>();

		//根据条件查询
		try {
			String id = postLoanPersonaAssociationService.findByPostLoanCustomerIdAndRelationship(clientId, "r01434");
			postLoanPersonal = postLoanPersonalService.findOne(id);
		} catch (Exception e) {
			e.printStackTrace();
			msg.setResultStatus(ResponseMsg.APP_ERROR);
			msg.setMsg("查询出错，请看日志");
			logger.error("latestCustomer查询失败", e);
		}
		if(ObjectHelper.isNotEmpty(postLoanPersonal)){
			postLoanPersonalVo = new PostLoanPersonalVo(postLoanPersonal);
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
	 * 客户信息查看
	 * @param id 客户id
	 * @param jsoncallback
	 * @return
	 */
	@RequestMapping("/viewCustomer")
	@UriKey(key = "com.cnfh.customer.viewCustomer")
	@ResponseBody
	public ModelAndView viewCustomer(String id, String jsoncallback) {
		Map<String, Object> optional = new HashMap<String, Object>();
		PostLoanPersonal postLoanPersonal = null;
		PostLoanPersonalVo postLoanPersonalVo = null;

		// 查询
		try {
			postLoanPersonal = postLoanPersonalService.findOne(id);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("latestCustomer查询失败", e);
		}
		
		//客户对象数据组装
		if(ObjectHelper.isNotEmpty(postLoanPersonal)){
			postLoanPersonalVo = new PostLoanPersonalVo(postLoanPersonal);
			postLoanPersonalVo = SimpleCodeToName(postLoanPersonalVo);
		}else{
			logger.error("latestCustomer查询结果为空");
		}
		
		optional.put("postLoanPersonalVo", postLoanPersonalVo);
		return new ModelAndView("/customer/viewcustomer", optional);
	}
	
	/**
	 * SimpleCode值转换
	 * @param latestCustomerVo
	 * @return
	 */
	public PostLoanPersonalVo SimpleCodeToName(PostLoanPersonalVo postLoanPersonalVo){
		try {
			if(ObjectHelper.isNotEmpty(postLoanPersonalVo.getCredentiaType())){
				postLoanPersonalVo.setCredentiaType(SimpleCodeTurnOn(postLoanPersonalVo.getCredentiaType()));
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
	
	public PostLoanAddress SimpleCodeToName(PostLoanAddress postLoanAddress){
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
	
}
