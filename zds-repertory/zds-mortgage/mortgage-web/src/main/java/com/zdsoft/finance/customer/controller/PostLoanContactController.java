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

import com.zdsoft.finance.common.base.QueryObj;
import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.finance.customer.entity.PostLoanContact;
import com.zdsoft.finance.customer.service.PostLoanContactService;
import com.zdsoft.finance.customer.vo.ContactVo;
import com.zdsoft.finance.customer.vo.PostLoanContactVo;
import com.zdsoft.framework.core.common.dto.ResponseMsg;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.PageRequest;
import com.zdsoft.framework.core.common.util.ObjectHelper;
import com.zdsoft.framework.core.commweb.annotation.UriKey;
import com.zdsoft.framework.core.commweb.component.BaseController;

/**
 * 贷后联系方式
 * @author <a href="mailto:502549751@qq.com">zhangchao</a>
 * @createTime: 2017-01-10 14::41
 * @version: 1.0
 *
 */
@Controller
@RequestMapping("postLoanContact")
public class PostLoanContactController extends BaseController{
	
	@Autowired
	private PostLoanContactService postLoanContactService;

	@Autowired
	private com.zdsoft.essential.client.service.CED CED;
	
	/**
	 * 根据客户id查询相关联系方式
	 * @param request
	 * @param jsoncallback
	 * @param pageable
	 * @return 查询列表
	 */
	@RequestMapping("/findByClientId")
	@UriKey(key = "com.cnfh.postLoanContact.findByClientId")
	@ResponseBody
	public String findByClientId(HttpServletRequest request, String jsoncallback, PageRequest pageable){
		// 获取页面封装的查询参数
		@SuppressWarnings("unchecked")
		List<QueryObj> queryObjs = (List<QueryObj>) request.getAttribute("listObj");
		
		ResponseMsg msg = new ResponseMsg();
		if(ObjectHelper.isNotEmpty(queryObjs)&&queryObjs.size()>0){
			// 分页客户联系方式
			Page<PostLoanContact> postLoanContactPage = postLoanContactService.findByHqlConditions(pageable, queryObjs);
			List<PostLoanContactVo> postLoanContactVos = new ArrayList<PostLoanContactVo>();
			for (PostLoanContact postLoanContact : postLoanContactPage.getRecords()) {
				PostLoanContactVo postLoanContactVo = new PostLoanContactVo(postLoanContact);
				postLoanContactVo = SimpleCodeToName(postLoanContactVo);
				postLoanContactVos.add(postLoanContactVo);
			}
			msg.setMsg("列表查询成功");
			msg.setResultStatus(ResponseMsg.SUCCESS);
			msg.setTotal(postLoanContactPage.getTotalRows());
			msg.setRows(postLoanContactVos);
		}else{
			msg.setMsg("列表查询成功");
			msg.setResultStatus(ResponseMsg.SUCCESS);
			msg.setTotal(0L);
			msg.setRows(new ArrayList<ContactVo>());
		}
		return ObjectHelper.objectToJson(msg, jsoncallback);
	}
	
	/**
	 * 保存或更新客户联系方式
	 * @param postLoanContactVo 客户联系方式
	 * @param customerId 客户id
	 * @param jsoncallback
	 * @return
	 */
	@RequestMapping("/savePostLoanContact")
	@UriKey(key = "com.cnfh.postLoanContact.savePostLoanContact")
	@ResponseBody
	public ResponseMsg savePostLoanContact(PostLoanContactVo postLoanContactVo, String customerId, String jsoncallback) {
		PostLoanContact postLoanContact = null;
		ResponseMsg msg = new ResponseMsg();
		Map<String, Object> postLoanContactMap = new HashMap<String, Object>();

		// 将Vo转成Po
		postLoanContact = postLoanContactVo.toPO();
		
		PostLoanContactVo newPostLoanContactVo = null;
		PostLoanContact newPostLoanContact = new PostLoanContact();

		postLoanContact.setCustomerId(customerId);
		// 执行保存
		try {
			if(ObjectHelper.isNotEmpty(postLoanContact.getId())){
				Date date = new Date();
				postLoanContact.setUpdateTime(date);
				newPostLoanContact = postLoanContactService.findOne(postLoanContact.getId());
				postLoanContact.setCreateTime(newPostLoanContact.getCreateTime());
				newPostLoanContact = postLoanContactService.updateEntity(postLoanContact);
			}else{
				Date date = new Date();
				postLoanContact.setCreateTime(date);
				postLoanContact.setCreateBy(CED.getLoginUser().getEmpCd());
				postLoanContact.setCreateOrgCd(CED.getLoginUser().getOrgCd());
				newPostLoanContact = postLoanContactService.saveEntity(postLoanContact);
			}
			newPostLoanContactVo = new PostLoanContactVo(newPostLoanContact);
			postLoanContactMap.put("newPostLoanContactVo", newPostLoanContactVo);
			msg.setResultStatus(ResponseMsg.SUCCESS);
			msg.setOptional(postLoanContactMap);
			msg.setMsg("保存成功");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("postLoanContact保存失败", e);
			msg.setResultStatus(ResponseMsg.APP_ERROR);
			msg.setMsg(e.getMessage());
		}
		return msg;
	}
	
	/**
	 * 根据id删除联系方式
	 * @param id 联系方式id
	 * @param jsoncallback
	 * @return
	 */
	@RequestMapping("/delPostLoanContact")
	@UriKey(key = "com.cnfh.postLoanContact.delPostLoanContact")
	@ResponseBody
	public ResponseMsg delPostLoanContact(String id, String jsoncallback) {
		PostLoanContact postLoanContact = null;
		ResponseMsg msg = new ResponseMsg();

		//查询
		try {
			postLoanContact = postLoanContactService.findOne(id);
		} catch (BusinessException e1) {
			e1.printStackTrace();
			logger.error("postLoanContact查询失败", e1);
		}
		
		// 删除
		try {
			postLoanContactService.logicDelete(postLoanContact);
			msg.setResultStatus(ResponseMsg.SUCCESS);
			msg.setMsg("删除成功");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("postLoanContact删除失败", e);
			msg.setResultStatus(ResponseMsg.APP_ERROR);
			msg.setMsg(e.getMessage());
		}
		return msg;
	}
	
	/**
	 * 根据id查询联系方式
	 * @param id 联系方式id
	 * @param jsoncallback
	 * @return
	 */
	@RequestMapping("/findPostLoanContactById")
	@UriKey(key = "com.cnfh.postLoanContact.findPostLoanContactById")
	@ResponseBody
	public ResponseMsg findPostLoanContactById(String id, String jsoncallback) {
		PostLoanContact postLoanContact = null;
		ResponseMsg msg = new ResponseMsg();
		Map<String, Object> postLoanContactMap = new HashMap<String, Object>();

		//查询
		try {
			postLoanContact = postLoanContactService.findOne(id);
			PostLoanContactVo postLoanContactVo = new PostLoanContactVo(postLoanContact);
			postLoanContactMap.put("postLoanContactVo", postLoanContactVo);
			msg.setOptional(postLoanContactMap);
			msg.setResultStatus(ResponseMsg.SUCCESS);
			msg.setMsg("查询成功");
		} catch (BusinessException e1) {
			e1.printStackTrace();
			logger.error("postLoanContact查询失败", e1);
			msg.setResultStatus(ResponseMsg.APP_ERROR);
			msg.setMsg(e1.getMessage());
		}
		return msg;
	}

	/**
	 * SimpleCode值转换
	 * @param latestCustomerVo
	 * @return
	 */
	public PostLoanContactVo SimpleCodeToName(PostLoanContactVo postLoanContactVo){
		if(ObjectHelper.isNotEmpty(postLoanContactVo.getContactType())){
			postLoanContactVo.setContactType(SimpleCodeTurnOn(postLoanContactVo.getContactType()));
		}
		return postLoanContactVo;
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
