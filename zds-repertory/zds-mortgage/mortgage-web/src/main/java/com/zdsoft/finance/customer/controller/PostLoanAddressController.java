package com.zdsoft.finance.customer.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.finance.customer.entity.PostLoanAddress;
import com.zdsoft.finance.customer.service.PostLoanAddressService;
import com.zdsoft.finance.customer.vo.PostLoanAddressVo;
import com.zdsoft.framework.core.common.dto.ResponseMsg;
import com.zdsoft.framework.core.common.util.ObjectHelper;
import com.zdsoft.framework.core.commweb.annotation.UriKey;
import com.zdsoft.framework.core.commweb.component.BaseController;

/**
 * 贷后客户地址
 * @author <a href="mailto:502549751@qq.com">zhangchao</a>
 * @createTime: 2017-01-10 14::41
 * @version: 1.0
 *
 */
@Controller
@RequestMapping("postLoanAddress")
public class PostLoanAddressController extends BaseController{

	@Autowired
	private PostLoanAddressService postLoanAddressService;
	
	@Autowired
	private com.zdsoft.essential.client.service.CED CED;
	
	/**
	 * 保存客户地址
	 * @param postLoanAddressVo 客户地址
	 * @return
	 */
	@RequestMapping("/saveAddress")
	@UriKey(key = "com.cnfh.postLoanAddress.saveAddress")
	@ResponseBody
	public ResponseMsg saveAddress(PostLoanAddressVo postLoanAddressVo){
		PostLoanAddress postLoanAddress = null;
		ResponseMsg msg = new ResponseMsg();
		Map<String, Object> postLoanAddressMap = new HashMap<String, Object>();
		
		if(ObjectHelper.isNotEmpty(postLoanAddressVo.getLiveAddress())){
//			String[] code = postLoanAddressVo.getLiveCode().split(",");
//			postLoanAddressVo.setProvince(code[0]);
//			postLoanAddressVo.setCity(code[1]);
//			postLoanAddressVo.setDistrict(code[2]);
			postLoanAddressVo.setAddress(postLoanAddressVo.getLiveAddress());
		}
		if(ObjectHelper.isNotEmpty(postLoanAddressVo.getDomicileAddress())){
//			String[] code = postLoanAddressVo.getDomicileCode().split(",");
//			postLoanAddressVo.setProvince(code[0]);
//			postLoanAddressVo.setCity(code[1]);
//			postLoanAddressVo.setDistrict(code[2]);
			postLoanAddressVo.setAddress(postLoanAddressVo.getDomicileAddress());
		}
		postLoanAddress = postLoanAddressVo.toPO();
		
		PostLoanAddress newPostLoanAddress = new PostLoanAddress();
		PostLoanAddressVo newPostLoanAddressVo = null;
		
		try {
			if(ObjectHelper.isEmpty(postLoanAddress.getId())){
				Date date = new Date();
				postLoanAddress.setCreateTime(date);
				postLoanAddress.setCreateBy(CED.getLoginUser().getEmpCd());
				postLoanAddress.setCreateOrgCd(CED.getLoginUser().getOrgCd());
				newPostLoanAddress = postLoanAddressService.saveEntity(postLoanAddress);
			}else{
				Date date = new Date();
				postLoanAddress.setUpdateTime(date);
				postLoanAddress.setCreateBy(CED.getLoginUser().getEmpCd());
				postLoanAddress.setCreateOrgCd(CED.getLoginUser().getOrgCd());
				newPostLoanAddress = postLoanAddressService.findOne(postLoanAddress.getId());
				postLoanAddress.setCreateTime(newPostLoanAddress.getCreateTime());
				newPostLoanAddress = postLoanAddressService.updateEntity(postLoanAddress);
			}
			newPostLoanAddressVo = new PostLoanAddressVo(newPostLoanAddress);
			postLoanAddressMap.put("newPostLoanAddressVo", newPostLoanAddressVo);
			msg.setResultStatus(ResponseMsg.SUCCESS);
			msg.setOptional(postLoanAddressMap);
			msg.setMsg("保存成功");
		} catch (BusinessException e) {
			e.printStackTrace();
			logger.error("postLoanPersonal保存失败", e);
			msg.setResultStatus(ResponseMsg.APP_ERROR);
			msg.setMsg(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("postLoanPersonal保存失败", e);
			msg.setResultStatus(ResponseMsg.APP_ERROR);
			msg.setMsg(e.getMessage());
		}
		return msg;
	}
	
	/**
	 * 根据客户姓名和客户id查询客户地址
	 * @param customerName 客户姓名
	 * @param customerId 客户id
	 * @return
	 */
	@RequestMapping("/findByCustomerNameAndCustomerId")
	@UriKey(key = "com.cnfh.postLoanAddress.findByCustomerNameAndCustomerId")
	@ResponseBody
	public ResponseMsg findByCustomerNameAndCustomerId(String customerName, String customerId, String addressType) {
		PostLoanAddressVo postLoanAddressVo = null;
		PostLoanAddress postLoanAddress = null;
		ResponseMsg msg = new ResponseMsg();
		Map<String, Object> postLoanAddressMap = new HashMap<String, Object>();

		//根据条件查询
		try {
			postLoanAddress = postLoanAddressService.findByCustomerNameAndCustomerIdAndAddressType(customerName, customerId, addressType);
		} catch (Exception e) {
			e.printStackTrace();
			msg.setResultStatus(ResponseMsg.APP_ERROR);
			msg.setMsg(e.getMessage());
			logger.error("postLoanAddress查询失败", e);
		}
		if(ObjectHelper.isNotEmpty(postLoanAddress)){
			postLoanAddressVo = new PostLoanAddressVo(postLoanAddress);
			if(postLoanAddressVo.getAddressType().equals("t0930")){
				String liveCode = postLoanAddressVo.getProvince()+","+postLoanAddressVo.getCity()+","+postLoanAddressVo.getDistrict();
				postLoanAddressVo.setLiveCode(liveCode);
				postLoanAddressVo.setLiveAddress(postLoanAddressVo.getAddress());
			}
			if(postLoanAddressVo.getAddressType().equals("t0931")){
				String domicileCode = postLoanAddressVo.getProvince()+","+postLoanAddressVo.getCity()+","+postLoanAddressVo.getDistrict();
				postLoanAddressVo.setDomicileCode(domicileCode);
				postLoanAddressVo.setDomicileAddress(postLoanAddressVo.getAddress());
			}
		}else{
			postLoanAddressVo = new PostLoanAddressVo();
		}
		postLoanAddressMap.put("postLoanAddressVo", postLoanAddressVo);
		msg.setResultStatus(ResponseMsg.SUCCESS);
		msg.setMsg("查询成功");
		msg.setOptional(postLoanAddressMap);
		return msg;
	}
}
