package com.zdsoft.finance.customer.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zdsoft.essential.client.service.CED;
import com.zdsoft.essential.dto.basedata.AttachmentDto;
import com.zdsoft.finance.common.base.ConstantParameter;
import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.finance.customer.entity.BeforeAddress;
import com.zdsoft.finance.customer.entity.BeforePersonalAssociation;
import com.zdsoft.finance.customer.entity.BeforePersonalCustomer;
import com.zdsoft.finance.customer.service.BeforeAddressService;
import com.zdsoft.finance.customer.service.BeforePersonalCustomerService;
import com.zdsoft.finance.customer.vo.BeforeAddressVo;
import com.zdsoft.finance.customer.vo.BeforePersonalCustomerVo;
import com.zdsoft.framework.core.common.util.ObjectHelper;
import com.zdsoft.framework.core.commweb.annotation.UriKey;
import com.zdsoft.framework.core.commweb.component.BaseController;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: BeforePersonalCustomerController.java 
 * @ClassName: BeforePersonalCustomerController 
 * @Description: 个人客户
 * @author xj 
 * @date 2017年3月13日 上午9:20:13 
 * @version V1.0
 */
@Controller
@RequestMapping("beforePersonalCustomer")
public class BeforePersonalCustomerController extends BaseController {
	@Autowired
	private BeforePersonalCustomerService beforePersonalCustomerService;
	@Autowired
	private BeforeAddressService beforeAddressService;
	@Autowired
	private CED CED;
	
	/**
	 * 
	 * @Title: getLatestByCredentialNoAndType 
	 * @Description: 根据证件号码和证件类型获取最新的客户信息
	 * @author xj 
	 * @param credentialNo 证件号码
	 * @param credentialType 证件类型
	 * @param jsoncallback
	 * @return
	 */
	@RequestMapping("/getLatestByCredentialNoAndType")
	@UriKey(key = "com.zdsoft.finance.beforePersonalCustomer.getLatestByCredentialNoAndType")
	@ResponseBody
	public Map<String,Object> getLatestByCredentialNoAndType(String credentialNo,String credentialType,String jsoncallback){
		Map<String,Object> reslutMap = new HashMap<String,Object>();
		//客户
		BeforePersonalCustomerVo mainCustomerVo = null;
		//配偶
		BeforePersonalCustomerVo spouseVo = null;
		//家庭地址
		BeforeAddressVo homeAddress = null;
		//户籍地址
		BeforeAddressVo residenceAddress = null;
		
		//贷前数据
		List<BeforePersonalCustomer> beforePersonalCustomers = beforePersonalCustomerService.loadCustomerByCredentiaTypeAndCredentialNo(credentialType, credentialNo);
		if(ObjectHelper.isNotEmpty(beforePersonalCustomers)){
			BeforePersonalCustomer mainCustomer = beforePersonalCustomers.get(0);
			mainCustomerVo = new BeforePersonalCustomerVo(mainCustomer);
			//头像
			String spouseAttachmentId = mainCustomerVo.getAttachmentId();
			if(ObjectHelper.isNotEmpty(spouseAttachmentId)){
				AttachmentDto attachment=null;
				try {
					attachment = CED.findAttachmentById(spouseAttachmentId);
				} catch (Exception e) {
					logger.error("获取头像附件失败", e);
					e.printStackTrace();
				}
				if(ObjectHelper.isNotEmpty(attachment)){
					//url地址
					String headPortraitPath = ConstantParameter.getAppDownloadUrl()+attachment.getFilePath();
					mainCustomerVo.setHeadPortraitPath(headPortraitPath);
				}
			}
			//查询配偶
			List<BeforePersonalCustomer> bcs = beforePersonalCustomerService.queryRelationCustomer(mainCustomer.getId(), BeforePersonalAssociation.SPOUSE);
			if(ObjectHelper.isNotEmpty(bcs)){
				BeforePersonalCustomer spouse = bcs.get(0);
				spouseVo = new  BeforePersonalCustomerVo(spouse);
				AttachmentDto attachment=null;
				try {
					spouseAttachmentId = spouseVo.getAttachmentId();
					if(ObjectHelper.isNotEmpty(spouseAttachmentId)){
						attachment = CED.findAttachmentById(spouseAttachmentId);
					}
				} catch (Exception e) {
					logger.error("获取头像附件失败", e);
					e.printStackTrace();
				}
				if(ObjectHelper.isNotEmpty(attachment)){
					//url地址
					String headPortraitPath = ConstantParameter.getAppDownloadUrl()+attachment.getFilePath();
					spouseVo.setHeadPortraitPath(headPortraitPath);
				}
			}
			//查询地址
			List<BeforeAddress> queryAddresss = beforeAddressService.queryAddresss(mainCustomer.getId());
			//户籍地址
			//家庭地址
			if(ObjectHelper.isNotEmpty(queryAddresss)){
				for (BeforeAddress beforeAddress : queryAddresss) {
					BeforeAddressVo vo = new BeforeAddressVo(beforeAddress);
					if(BeforeAddress.HOME_ADDRESS.equals(beforeAddress.getAddressType())){
						homeAddress = vo;
					}else{
						residenceAddress = vo;
					}
				}
			}
			
		}
		reslutMap.put("mainCustomerVo", mainCustomerVo);
		reslutMap.put("spouseVo", spouseVo);
		reslutMap.put("homeAddress", homeAddress);
		reslutMap.put("residenceAddress", residenceAddress);
		return reslutMap;
	}
	
	/**
	 * 
	 * @Title: getParentProduct 
	 * @Description: 通过案件id查询所有参与人姓名
	 * @author xj 
	 * @param jsoncallback
	 * @param caseApplyId 案件id
	 * @return
	 */
    @RequestMapping("/getAllCustomerNames")
    @UriKey(key = "com.zdsoft.finance.beforePersonalCustomer.getAllCustomerNames")
    @ResponseBody
    public String getParentProduct(String jsoncallback,String caseApplyId) {
        List<Map<String, Object>> returnData = new ArrayList<Map<String, Object>>();
		List<BeforePersonalCustomer> customers = beforePersonalCustomerService.queryByCaseApplyId(caseApplyId);
		for (BeforePersonalCustomer customer : customers) {
			Map<String, Object> rowData = new HashMap<String, Object>();
			rowData.put("id", customer.getId());
			rowData.put("text", customer.getCustomerName());
			returnData.add(rowData);
			BeforePersonalCustomer spouse = customer.getSpouse();
			if(ObjectHelper.isNotEmpty(spouse)){
				rowData = new HashMap<String, Object>();
				rowData.put("id", spouse.getId());
				rowData.put("text", spouse.getCustomerName());
				returnData.add(rowData);
				
			}
		}
      
        return ObjectHelper.objectToJson(returnData, jsoncallback);
    }
    /**
     * 
     * @Title: findByCustomerId 
     * @Description: 根据客户Id查询客户的邮箱和身份证号
     * @author zhoushichao 
     * @param customerId
     * @return
     */
    @RequestMapping("/findByCustomerId")
    @UriKey(key = "com.zdsoft.finance.beforePersonalCustomer.findByCustomerId")
    @ResponseBody
    public String findByCustomerId(String customerId) {
    	Map<String, Object> map = new HashMap<String, Object>();
		try {
			BeforePersonalCustomer customer = beforePersonalCustomerService.findOne(customerId);
			String credentialNo = "";
			if("YWDM002501".equals(customer.getCredentialType())){
				credentialNo = customer.getCredentialNo();
			}
			map.put("email", customer.getEmail());
			map.put("credentialNo", credentialNo);
		} catch (BusinessException e) {
			e.printStackTrace();
			logger.error("查询客户信息失败：",e);
		}
    	
    	return ObjectHelper.objectToJson(map);
    }
}
