package com.zdsoft.finance.customer.controller;

import java.util.ArrayList;
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
import com.zdsoft.finance.customer.entity.LatestCustomer;
import com.zdsoft.finance.customer.entity.RelationshipType;
import com.zdsoft.finance.customer.entity.WorkUnits;
import com.zdsoft.finance.customer.service.LatestCustomerService;
import com.zdsoft.finance.customer.service.WorkUnitsService;
import com.zdsoft.finance.customer.vo.WorkUnitsVo;
import com.zdsoft.framework.core.common.dto.ResponseMsg;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.PageRequest;
import com.zdsoft.framework.core.common.util.ObjectHelper;
import com.zdsoft.framework.core.commweb.annotation.UriKey;
import com.zdsoft.framework.core.commweb.component.BaseController;

/**
 * 工作单位
 * @author zhangchao
 *	2016/12/22
 */
@Controller
@RequestMapping("workUnits")
public class WorkUnitsController extends BaseController {

	@Autowired
	private WorkUnitsService workUnitsService;
	
	@Autowired
	private LatestCustomerService latestCustomerService;
	
	@Autowired
	private com.zdsoft.essential.client.service.CED CED;
	
	/**
	 * 根据客户id查询客户相关的工作单位(列表显示)
	 * @param request
	 * @param clientId 客户id
	 * @param jsoncallback
	 * @param pageable
	 * @return
	 */
	@RequestMapping("/findByClientId")
	@UriKey(key = "com.zdsoft.finance.workUnits.findByClientId")
	@ResponseBody
	public String findByClientId(HttpServletRequest request, String clientId ,String jsoncallback, PageRequest pageable){
		// 获取页面封装的查询参数
		@SuppressWarnings("unchecked")
		List<QueryObj> queryObjs = (List<QueryObj>) request.getAttribute("listObj");
		
		ResponseMsg msg = new ResponseMsg();
		if(ObjectHelper.isNotEmpty(queryObjs)&&queryObjs.size()>0){
			// 分页客户信息管理
			Page<WorkUnits> latestCustomerPage = workUnitsService.findByHqlConditions(pageable, queryObjs);
			List<WorkUnitsVo> LatestCustomerVos = new ArrayList<WorkUnitsVo>();
			for (WorkUnits latestCustomer : latestCustomerPage.getRecords()) {
				WorkUnitsVo LatestCustomerVo = new WorkUnitsVo(latestCustomer);
				LatestCustomerVo = SimpleCodeToName(LatestCustomerVo);
				LatestCustomerVos.add(LatestCustomerVo);
			}
			msg.setMsg("列表查询成功");
			msg.setResultStatus(ResponseMsg.SUCCESS);
			msg.setTotal(latestCustomerPage.getTotalRows());
			msg.setRows(LatestCustomerVos);
		}else{
			msg.setMsg("列表查询成功");
			msg.setResultStatus(ResponseMsg.SUCCESS);
			msg.setTotal(0L);
			msg.setRows(new ArrayList<WorkUnitsVo>());
		}
		return ObjectHelper.objectToJson(msg, jsoncallback);
	}
	
	/**
	 * 保存客户工作单位信息
	 * @param workUnitsVo 工作单位数据
	 * @param clientId 客户id
	 * @param jsoncallback
	 * @return
	 */
	@RequestMapping("/saveWorkUnits")
	@UriKey(key = "com.zdsoft.finance.workUnits.saveWorkUnits")
	@ResponseBody
	public ResponseMsg saveWorkUnits(WorkUnitsVo workUnitsVo, String clientId, String jsoncallback) {
		WorkUnits workUnits = null;
		ResponseMsg msg = new ResponseMsg();
		Map<String, Object> workUnitsMap = new HashMap<String, Object>();

		// 将Vo转成Po
		workUnits = workUnitsVo.toPO();
		
		WorkUnitsVo newWorkUnitsVo = null;
		WorkUnits newWorkUnits = new WorkUnits();
		
		LatestCustomer latestCustomer = null;
		
		//查询
		try {
			latestCustomer = latestCustomerService.findOne(clientId);
		} catch (BusinessException e1) {
			e1.printStackTrace();
			logger.error("latestCustomer查询失败", e1);
		}
		workUnits.setCustomer(latestCustomer);

		// 执行保存
		try {
			newWorkUnits = workUnitsService.saveOrUpdateEntity(workUnits);
			newWorkUnitsVo = new WorkUnitsVo(newWorkUnits);
			workUnitsMap.put("newWorkUnitsVo", newWorkUnitsVo);
			msg.setResultStatus(ResponseMsg.SUCCESS);
			msg.setOptional(workUnitsMap);
			msg.setMsg("保存成功");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("workUnits保存失败", e);
			msg.setResultStatus(ResponseMsg.APP_ERROR);
			msg.setMsg("系统内部错误，请查看日志");
		}
		return msg;
	}
	
	/**
	 * 根据id删除工作单位
	 * @param id 工作单位id
	 * @param jsoncallback
	 * @return
	 */
	@RequestMapping("/delWorkUnits")
	@UriKey(key = "com.zdsoft.finance.workUnits.delWorkUnits")
	@ResponseBody
	public ResponseMsg delWorkUnits(String id, String jsoncallback) {
		WorkUnits workUnits = null;
		ResponseMsg msg = new ResponseMsg();

		//查询
		try {
			workUnits = workUnitsService.findOne(id);
		} catch (BusinessException e1) {
			e1.printStackTrace();
			logger.error("latestCustomer查询失败", e1);
		}
		
		// 删除
		try {
			workUnitsService.logicDelete(workUnits);
			msg.setResultStatus(ResponseMsg.SUCCESS);
			msg.setMsg("删除成功");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("workUnits删除失败", e);
			msg.setResultStatus(ResponseMsg.APP_ERROR);
			msg.setMsg("系统内部错误，请查看日志");
		}
		return msg;
	}
	
	/**
	 * SimpleCode值转换
	 * @param latestCustomerVo
	 * @return
	 */
	public WorkUnitsVo SimpleCodeToName(WorkUnitsVo latestCustomerVo){
		try {
			latestCustomerVo.setRelationshipType(RelationshipType.getName(latestCustomerVo.getRelationshipType()));
			latestCustomerVo.setUnitNature(SimpleCodeTurnOn(latestCustomerVo.getUnitNature()));
			latestCustomerVo.setPosition(SimpleCodeTurnOn(latestCustomerVo.getPosition()));
			latestCustomerVo.setIndustryType(SimpleCodeTurnOn(latestCustomerVo.getIndustryType()));
			latestCustomerVo.setIndustry(SimpleCodeTurnOn(latestCustomerVo.getIndustry()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return latestCustomerVo;
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
