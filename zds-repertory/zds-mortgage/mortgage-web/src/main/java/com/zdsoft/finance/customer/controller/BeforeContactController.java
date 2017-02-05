package com.zdsoft.finance.customer.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zdsoft.essential.client.service.CED;
import com.zdsoft.finance.customer.entity.BeforeContact;
import com.zdsoft.finance.customer.service.BeforeContactService;
import com.zdsoft.finance.customer.vo.BeforeContactVo;
import com.zdsoft.framework.core.common.dto.ResponseMsg;
import com.zdsoft.framework.core.common.util.ObjectHelper;
import com.zdsoft.framework.core.commweb.annotation.UriKey;
import com.zdsoft.framework.core.commweb.component.BaseController;
/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title:BeforeContactController.java
 * @Package:com.zdsoft.finance.customer.controller
 * @Description:贷前客户联系方式
 * @author: xj
 * @date:2017年1月12日 下午10:13:12
 * @version:v1.0
 */
@Controller
@RequestMapping("beforeContact")
public class BeforeContactController extends BaseController {
	@Autowired
	private BeforeContactService beforeContactService;
	@Autowired
	private CED CED;
	@RequestMapping("/listContactByCustomerId")
	@UriKey(key = "com.zdsoft.finance.beforeContact.listContactByCustomerId")
	@ResponseBody
	public String listContactByCustomerId(String customerId,String jsoncallback){
		ResponseMsg msg = new ResponseMsg();
		try {
			List<BeforeContact> beforeContacts = beforeContactService.queryContact(customerId);
			List<BeforeContactVo> beforeContactVos = new ArrayList<BeforeContactVo>();
			if(ObjectHelper.isNotEmpty(beforeContacts)){
				for (BeforeContact beforeContact : beforeContacts) {
					BeforeContactVo beforeContactVo = new BeforeContactVo(beforeContact);
					//联系方式
					beforeContactVo.setContactTypeNm(CED.loadSimpleCodeNameByFullCode(beforeContactVo.getContactType()));
					beforeContactVos.add(beforeContactVo);
					
				}
			}
			msg.setResultStatus(ResponseMsg.SUCCESS);
			msg.setTotal(Long.parseLong(beforeContactVos.size()+""));
			msg.setRows(beforeContactVos);
		} catch (Exception e) {
			msg.setResultStatus(ResponseMsg.SYS_ERROR);
			logger.error("查询贷前工作地址异常：", e);
		}
		return ObjectHelper.objectToJson(msg, jsoncallback);
		
	}
}
