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
import com.zdsoft.finance.customer.entity.AfterWorkUnit;
import com.zdsoft.finance.customer.service.AfterWorkUnitService;
import com.zdsoft.finance.customer.vo.AfterWorkUnitVo;
import com.zdsoft.framework.core.common.dto.ResponseMsg;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.PageRequest;
import com.zdsoft.framework.core.common.util.ObjectHelper;
import com.zdsoft.framework.core.commweb.annotation.UriKey;
import com.zdsoft.framework.core.commweb.component.BaseController;

/**
 * 贷后工作单位
 * 版权所有：重庆正大华日软件有限公司
 * @Title: PostLoanWorkUnitController.java 
 * @ClassName: PostLoanWorkUnitController 
 * @Description: 
 * @author zhangchao 
 * @date 2017年2月6日 上午10:55:52 
 * @version V1.0
 */
@Controller
@RequestMapping("postLoanWorkUnit")
public class AfterWorkUnitController extends BaseController{

	@Autowired
	private AfterWorkUnitService afterWorkUnitService;
	
	@Autowired
	private com.zdsoft.essential.client.service.CED CED;
	
	/**
	 *
	 * @Title: findByClientId 
	 * @Description:  根据客户id查询相关工作单位
	 * @author zhangchao 
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
			Page<AfterWorkUnit> postLoanWorkUnitPage = afterWorkUnitService.findByHqlConditions(pageable, queryObjs);
			List<AfterWorkUnitVo> postLoanWorkUnitVos = new ArrayList<AfterWorkUnitVo>();
			for (AfterWorkUnit postLoanWorkUnit : postLoanWorkUnitPage.getRecords()) {
				AfterWorkUnitVo postLoanWorkUnitVo = new AfterWorkUnitVo(postLoanWorkUnit);
				postLoanWorkUnitVo = SimpleCodeToName(postLoanWorkUnitVo);
				if(ObjectHelper.isNotEmpty(postLoanWorkUnitVo.getProvince())){
					postLoanWorkUnitVo.setWorkUnitAddress(postLoanWorkUnitVo.getProvince()+"-"+postLoanWorkUnitVo.getCity()+"-"+postLoanWorkUnitVo.getDistrict()+"-"+postLoanWorkUnitVo.getWorkUnitAddress());
				}
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
			msg.setRows(new ArrayList<AfterWorkUnitVo>());
		}
		return ObjectHelper.objectToJson(msg, jsoncallback);
	}
	
	/**
	 * 
	 * @Title: savePostLoanWorkUnit 
	 * @Description: 保存客户工作单位
	 * @author zhangchao 
	 * @param postLoanWorkUnitVo 工作单位
	 * @param customerId 客户id
	 * @param jsoncallback
	 * @return
	 */
	@RequestMapping("/savePostLoanWorkUnit")
	@UriKey(key = "com.cnfh.postLoanWorkUnit.savePostLoanWorkUnit")
	@ResponseBody
	public ResponseMsg savePostLoanWorkUnit(AfterWorkUnitVo postLoanWorkUnitVo, String customerId, String jsoncallback) {
		AfterWorkUnit postLoanWorkUnit = null;
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
		
		AfterWorkUnitVo newPostLoanWorkUnitVo = null;
		AfterWorkUnit newPostLoanWorkUnit = new AfterWorkUnit();

		postLoanWorkUnit.setCustomerId(customerId);
		// 执行保存
		try {
			if(ObjectHelper.isNotEmpty(postLoanWorkUnit.getId())){
				Date date = new Date();
				postLoanWorkUnit.setUpdateTime(date);
				postLoanWorkUnit.setCreateBy(CED.getLoginUser().getEmpCd());
				postLoanWorkUnit.setCreateOrgCd(CED.getLoginUser().getOrgCd());
				newPostLoanWorkUnit = afterWorkUnitService.findOne(postLoanWorkUnit.getId());
				postLoanWorkUnit.setCreateTime(newPostLoanWorkUnit.getCreateTime());
				newPostLoanWorkUnit = afterWorkUnitService.updateEntity(postLoanWorkUnit);
			}else{
				Date date = new Date();
				postLoanWorkUnit.setCreateTime(date);
				postLoanWorkUnit.setCreateBy(CED.getLoginUser().getEmpCd());
				postLoanWorkUnit.setCreateOrgCd(CED.getLoginUser().getOrgCd());
				newPostLoanWorkUnit = afterWorkUnitService.saveEntity(postLoanWorkUnit);
			}
			newPostLoanWorkUnitVo = new AfterWorkUnitVo(newPostLoanWorkUnit);
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
	 * 
	 * @Title: delPostLoanWorkUnit 
	 * @Description: 根据id删除工作单位
	 * @author zhangchao 
	 * @param id 工作单位id
	 * @param jsoncallback
	 * @return
	 */
	@RequestMapping("/delPostLoanWorkUnit")
	@UriKey(key = "com.cnfh.postLoanWorkUnit.delPostLoanWorkUnit")
	@ResponseBody
	public ResponseMsg delPostLoanWorkUnit(String id, String jsoncallback) {
		AfterWorkUnit postLoanWorkUnit = null;
		ResponseMsg msg = new ResponseMsg();

		//查询
		try {
			postLoanWorkUnit = afterWorkUnitService.findOne(id);
		} catch (BusinessException e1) {
			e1.printStackTrace();
			logger.error("postLoanWorkUnit查询失败", e1);
		}
		
		// 删除
		try {
			afterWorkUnitService.logicDelete(postLoanWorkUnit);
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
	 * 
	 * @Title: findPostLoanWorkUnitById 
	 * @Description:  根据id查询工作单位
	 * @author zhangchao 
	 * @param id 工作单位id
	 * @param jsoncallback
	 * @return
	 */
	@RequestMapping("/findPostLoanWorkUnitById")
	@UriKey(key = "com.cnfh.postLoanWorkUnit.findPostLoanWorkUnitById")
	@ResponseBody
	public ResponseMsg findPostLoanWorkUnitById(String id, String jsoncallback) {
		AfterWorkUnit postLoanWorkUnit = null;
		ResponseMsg msg = new ResponseMsg();
		Map<String, Object> postLoanWorkUnitMap = new HashMap<String, Object>();

		//查询
		try {
			postLoanWorkUnit = afterWorkUnitService.findOne(id);
			AfterWorkUnitVo postLoanWorkUnitVo = new AfterWorkUnitVo(postLoanWorkUnit);
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
	 * 
	 * @Title: SimpleCodeToName 
	 * @Description: SimpleCode值转换
	 * @author zhangchao 
	 * @param postLoanWorkUnitVo 客户工作单位
	 * @return
	 */
	public AfterWorkUnitVo SimpleCodeToName(AfterWorkUnitVo postLoanWorkUnitVo){
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
