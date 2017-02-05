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
import com.zdsoft.finance.customer.entity.PostLoanWorkUnit;
import com.zdsoft.finance.customer.service.PostLoanWorkUnitService;
import com.zdsoft.finance.customer.vo.ContactVo;
import com.zdsoft.finance.customer.vo.PostLoanWorkUnitVo;
import com.zdsoft.framework.core.common.dto.ResponseMsg;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.PageRequest;
import com.zdsoft.framework.core.common.util.ObjectHelper;
import com.zdsoft.framework.core.commweb.annotation.UriKey;
import com.zdsoft.framework.core.commweb.component.BaseController;

/**
 * 贷后工作单位
 * @author <a href="mailto:502549751@qq.com">zhangchao</a>
 * @createTime: 2017-01-10 14::41
 * @version: 1.0
 *
 */
@Controller
@RequestMapping("postLoanWorkUnit")
public class PostLoanWorkUnitController extends BaseController{

	@Autowired
	private PostLoanWorkUnitService postLoanWorkUnitService;
	
	@Autowired
	private com.zdsoft.essential.client.service.CED CED;
	
	/**
	 * 根据客户id查询相关工作单位
	 * @param request
	 * @param jsoncallback
	 * @param pageable
	 * @return
	 */
	@RequestMapping("/findByClientId")
	@UriKey(key = "com.cnfh.postLoanWorkUnit.findByClientId")
	@ResponseBody
	public String findByClientId(HttpServletRequest request, String jsoncallback, PageRequest pageable){
		// 获取页面封装的查询参数
		@SuppressWarnings("unchecked")
		List<QueryObj> queryObjs = (List<QueryObj>) request.getAttribute("listObj");
		
		ResponseMsg msg = new ResponseMsg();
		if(ObjectHelper.isNotEmpty(queryObjs)&&queryObjs.size()>0){
			// 分页客户联系方式
			Page<PostLoanWorkUnit> postLoanWorkUnitPage = postLoanWorkUnitService.findByHqlConditions(pageable, queryObjs);
			List<PostLoanWorkUnitVo> postLoanWorkUnitVos = new ArrayList<PostLoanWorkUnitVo>();
			for (PostLoanWorkUnit postLoanWorkUnit : postLoanWorkUnitPage.getRecords()) {
				PostLoanWorkUnitVo postLoanWorkUnitVo = new PostLoanWorkUnitVo(postLoanWorkUnit);
				postLoanWorkUnitVo = SimpleCodeToName(postLoanWorkUnitVo);
				postLoanWorkUnitVo.setWorkUnitAddress(postLoanWorkUnitVo.getProvince()+"-"+postLoanWorkUnitVo.getCity()+"-"+postLoanWorkUnitVo.getDistrict()+"-"+postLoanWorkUnitVo.getWorkUnitAddress());
				postLoanWorkUnitVos.add(postLoanWorkUnitVo);
			}
			msg.setMsg("列表查询成功");
			msg.setResultStatus(ResponseMsg.SUCCESS);
			msg.setTotal(postLoanWorkUnitPage.getTotalRows());
			msg.setRows(postLoanWorkUnitVos);
		}else{
			msg.setMsg("列表查询成功");
			msg.setResultStatus(ResponseMsg.SUCCESS);
			msg.setTotal(0L);
			msg.setRows(new ArrayList<ContactVo>());
		}
		return ObjectHelper.objectToJson(msg, jsoncallback);
	}
	
	/**
	 * 保存客户工作单位
	 * @param postLoanWorkUnitVo 工作单位
	 * @param clientId 客户id
	 * @param jsoncallback
	 * @return
	 */
	@RequestMapping("/savePostLoanWorkUnit")
	@UriKey(key = "com.cnfh.postLoanWorkUnit.savePostLoanWorkUnit")
	@ResponseBody
	public ResponseMsg savePostLoanWorkUnit(PostLoanWorkUnitVo postLoanWorkUnitVo, String customerId, String jsoncallback) {
		PostLoanWorkUnit postLoanWorkUnit = null;
		ResponseMsg msg = new ResponseMsg();
		Map<String, Object> workUnitsMap = new HashMap<String, Object>();
		
		if(ObjectHelper.isNotEmpty(postLoanWorkUnitVo.getWorkUnitCode())){
			String[] workUnitCode = postLoanWorkUnitVo.getWorkUnitCode().split(",");
			postLoanWorkUnitVo.setProvince(workUnitCode[0]);
			postLoanWorkUnitVo.setCity(workUnitCode[1]);
			postLoanWorkUnitVo.setDistrict(workUnitCode[2]);
		}

		// 将Vo转成Po
		postLoanWorkUnit = postLoanWorkUnitVo.toPO();
		
		PostLoanWorkUnitVo newPostLoanWorkUnitVo = null;
		PostLoanWorkUnit newPostLoanWorkUnit = new PostLoanWorkUnit();

		postLoanWorkUnit.setCustomerId(customerId);
		// 执行保存
		try {
			if(ObjectHelper.isNotEmpty(postLoanWorkUnit.getId())){
				Date date = new Date();
				postLoanWorkUnit.setUpdateTime(date);
				postLoanWorkUnit.setCreateBy(CED.getLoginUser().getEmpCd());
				postLoanWorkUnit.setCreateOrgCd(CED.getLoginUser().getOrgCd());
				newPostLoanWorkUnit = postLoanWorkUnitService.findOne(postLoanWorkUnit.getId());
				postLoanWorkUnit.setCreateTime(newPostLoanWorkUnit.getCreateTime());
				newPostLoanWorkUnit = postLoanWorkUnitService.updateEntity(postLoanWorkUnit);
			}else{
				Date date = new Date();
				postLoanWorkUnit.setCreateTime(date);
				postLoanWorkUnit.setCreateBy(CED.getLoginUser().getEmpCd());
				postLoanWorkUnit.setCreateOrgCd(CED.getLoginUser().getOrgCd());
				newPostLoanWorkUnit = postLoanWorkUnitService.saveEntity(postLoanWorkUnit);
			}
			newPostLoanWorkUnitVo = new PostLoanWorkUnitVo(newPostLoanWorkUnit);
			workUnitsMap.put("newPostLoanWorkUnitVo", newPostLoanWorkUnitVo);
			msg.setResultStatus(ResponseMsg.SUCCESS);
			msg.setOptional(workUnitsMap);
			msg.setMsg("保存成功");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("postLoanWorkUnit保存失败", e);
			msg.setResultStatus(ResponseMsg.APP_ERROR);
			msg.setMsg(e.getMessage());
		}
		return msg;
	}
	
	/**
	 * 根据id删除工作单位
	 * @param id 工作单位id
	 * @param jsoncallback
	 * @return
	 */
	@RequestMapping("/delPostLoanWorkUnit")
	@UriKey(key = "com.cnfh.postLoanWorkUnit.delPostLoanWorkUnit")
	@ResponseBody
	public ResponseMsg delPostLoanWorkUnit(String id, String jsoncallback) {
		PostLoanWorkUnit postLoanWorkUnit = null;
		ResponseMsg msg = new ResponseMsg();

		//查询
		try {
			postLoanWorkUnit = postLoanWorkUnitService.findOne(id);
		} catch (BusinessException e1) {
			e1.printStackTrace();
			logger.error("postLoanWorkUnit查询失败", e1);
		}
		
		// 删除
		try {
			postLoanWorkUnitService.logicDelete(postLoanWorkUnit);
			msg.setResultStatus(ResponseMsg.SUCCESS);
			msg.setMsg("删除成功");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("postLoanWorkUnit删除失败", e);
			msg.setResultStatus(ResponseMsg.APP_ERROR);
			msg.setMsg(e.getMessage());
		}
		return msg;
	}
	
	/**
	 * 根据id查询工作单位
	 * @param id 工作单位id
	 * @param jsoncallback
	 * @return
	 */
	@RequestMapping("/findPostLoanWorkUnitById")
	@UriKey(key = "com.cnfh.postLoanWorkUnit.findPostLoanWorkUnitById")
	@ResponseBody
	public ResponseMsg findPostLoanWorkUnitById(String id, String jsoncallback) {
		PostLoanWorkUnit postLoanWorkUnit = null;
		ResponseMsg msg = new ResponseMsg();
		Map<String, Object> postLoanWorkUnitMap = new HashMap<String, Object>();

		//查询
		try {
			postLoanWorkUnit = postLoanWorkUnitService.findOne(id);
			PostLoanWorkUnitVo postLoanWorkUnitVo = new PostLoanWorkUnitVo(postLoanWorkUnit);
			if(ObjectHelper.isNotEmpty(postLoanWorkUnitVo.getProvince())){
				String workUnitCode = postLoanWorkUnitVo.getProvince()+","+postLoanWorkUnitVo.getCity()+","+postLoanWorkUnitVo.getDistrict();
				postLoanWorkUnitVo.setWorkUnitCode(workUnitCode);
			}
			postLoanWorkUnitMap.put("postLoanWorkUnitVo", postLoanWorkUnitVo);
			msg.setOptional(postLoanWorkUnitMap);
			msg.setResultStatus(ResponseMsg.SUCCESS);
			msg.setMsg("查询成功");
		} catch (BusinessException e1) {
			e1.printStackTrace();
			logger.error("postLoanWorkUnit查询失败", e1);
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
	public PostLoanWorkUnitVo SimpleCodeToName(PostLoanWorkUnitVo postLoanWorkUnitVo){
		if(ObjectHelper.isNotEmpty(postLoanWorkUnitVo.getIndustryType())){
			postLoanWorkUnitVo.setIndustryType(SimpleCodeTurnOn(postLoanWorkUnitVo.getIndustryType()));
		}
		if(ObjectHelper.isNotEmpty(postLoanWorkUnitVo.getIndustry())){
			postLoanWorkUnitVo.setIndustry(SimpleCodeTurnOn(postLoanWorkUnitVo.getIndustry()));
		}
		if(ObjectHelper.isNotEmpty(postLoanWorkUnitVo.getWorkUnitNature())){
			postLoanWorkUnitVo.setWorkUnitNature(SimpleCodeTurnOn(postLoanWorkUnitVo.getWorkUnitNature()));
		}
		if(ObjectHelper.isNotEmpty(postLoanWorkUnitVo.getPosition())){
			postLoanWorkUnitVo.setPosition(SimpleCodeTurnOn(postLoanWorkUnitVo.getPosition()));
		}
		if(ObjectHelper.isNotEmpty(postLoanWorkUnitVo.getProvince())){
			postLoanWorkUnitVo.setProvince(SimpleCodeTurnOn(postLoanWorkUnitVo.getProvince()));
		}
		if(ObjectHelper.isNotEmpty(postLoanWorkUnitVo.getCity())){
			postLoanWorkUnitVo.setCity(SimpleCodeTurnOn(postLoanWorkUnitVo.getCity()));
		}
		if(ObjectHelper.isNotEmpty(postLoanWorkUnitVo.getDistrict())){
			postLoanWorkUnitVo.setDistrict(SimpleCodeTurnOn(postLoanWorkUnitVo.getDistrict()));
		}
		return postLoanWorkUnitVo;
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
