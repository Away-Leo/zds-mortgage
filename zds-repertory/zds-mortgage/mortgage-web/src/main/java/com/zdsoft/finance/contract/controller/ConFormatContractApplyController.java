package com.zdsoft.finance.contract.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zdsoft.bpm.annotation.client.FinishJob;
import com.zdsoft.bpm.annotation.client.ManualJob;
import com.zdsoft.essential.client.service.CED;
import com.zdsoft.essential.dto.emp.EmpDto;
import com.zdsoft.finance.common.base.QueryObj;
import com.zdsoft.finance.common.utils.DateUtil;
import com.zdsoft.finance.contract.entity.ConFormatContractApply;
import com.zdsoft.finance.contract.service.ConFormatContractApplyService;
import com.zdsoft.finance.contract.vo.ConFormatContractApplyVo;
import com.zdsoft.framework.core.common.dto.ResponseMsg;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.PageRequest;
import com.zdsoft.framework.core.common.util.ObjectHelper;
import com.zdsoft.framework.core.commweb.annotation.UriKey;
import com.zdsoft.framework.core.commweb.component.BaseController;
import com.zdsoft.framework.cra.annotation.Menu;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title: ConFormatContractApplyController.java 
 * @ClassName: ConFormatContractApplyController 
 * @Description: 格式化合同申领控制器
 * @author zhongyong 
 * @date 2017年3月7日 下午5:34:13 
 * @version V1.0
 */
@Controller
@RequestMapping("formatContractApply")
public class ConFormatContractApplyController extends BaseController {
	
	@Autowired
	private CED CED;
	@Autowired
	private ConFormatContractApplyService conFormatContractApplyService;
	
	/**
	 * @Title: initFormatContractApply 
	 * @Description: 格式化合同申领入口
	 * @author zhongyong 
	 * @return
	 */
	@RequestMapping("/initFormatContractApply")
	@UriKey(key = "com.zdsoft.finance.contract.initFormatContractApply")
	@Menu(resource = "com.zdsoft.finance.contract.initFormatContractApply", label = "格式化合同申领", group = "contract", order = 3)
	public ModelAndView initFormatContractApply() {
		return new ModelAndView("/contract/formatContract/format_contract_apply_list");
	}
	
	/**
	 * @Title: formatContractApplyList 
	 * @Description: 格式化合同申领分页查询
	 * @author zhongyong 
	 * @param request
	 * @param pageable 分页信息
	 * @param jsoncallback
	 * @return
	 */
	@RequestMapping("/formatContractApplyList")
	@UriKey(key = "com.zdsoft.finance.contract.formatContractApplyList")
	@ResponseBody
	public String formatContractApplyList(HttpServletRequest request, PageRequest pageable, String jsoncallback) {
		ResponseMsg msg = new ResponseMsg();
		try {
			// 获取页面封装的查询参数
			@SuppressWarnings("unchecked")
			List<QueryObj> queryObjs = (List<QueryObj>) request.getAttribute("listObj");
			// 分页查询
			Page<Map<String, Object>> page = conFormatContractApplyService.findPageFormatContractApply(pageable, queryObjs);
			msg.setMsg("列表查询成功");
			msg.setResultStatus(ResponseMsg.SUCCESS);
			msg.setTotal(page.getTotalRows());
			msg.setRows(page.getRecords());
		} catch (Exception e) {
			msg.setResultStatus(ResponseMsg.SYS_ERROR);
			logger.error("格式化合同申领查询异常：", e);
			e.printStackTrace();
		}
		return ObjectHelper.objectToJson(msg, jsoncallback);
	}
	
	/**
	 * @Title: editFormatContractApply 
	 * @Description: 格式化合同申领编辑
	 * @author zhongyong 
	 * @param id 格式化合同id
	 * @return
	 */
	@RequestMapping("/editFormatContractApply")
	@UriKey(key = "com.zdsoft.finance.contract.editFormatContractApply")
	public ModelAndView editFormatContractApply(String id) {
		ModelMap model =  new ModelMap();
		try {
			EmpDto empDto = null;
			ConFormatContractApplyVo vo = null;
			if (StringUtils.isEmpty(id)) {
				vo = new ConFormatContractApplyVo();
				empDto = CED.getLoginUser();
				//自动生成申请编号
				vo.setApplyNo(CED.resolveExpression("1000000000004", null ));
				//自动得到申请时间
				vo.setApplyDate(DateUtil.getCurrentDate());
			} else {
				ConFormatContractApply info = conFormatContractApplyService.findOne(id);
				vo = new ConFormatContractApplyVo(info);
				empDto = CED.loadEmployeeByCode(info.getBusiForm().getLaunchEmpCode());
			}
			model.put("vo", vo);
			model.put("emp", empDto);
		} catch (Exception e) {
			logger.error("进入格式化合同申领编辑异常：", e);
			e.printStackTrace();
		}
		return new ModelAndView("/contract/formatContract/format_contract_apply_edit", model);
	}
	
	
	/**
	 * @Title: editFormatContractApply 
	 * @Description: 格式化合同申领详情
	 * @author zhongyong 
	 * @param id 格式化合同申领id
	 * @return
	 */
	@RequestMapping("/viewFormatContractApply")
	@UriKey(key = "com.zdsoft.finance.contract.viewFormatContractApply")
	public ModelAndView viewFormatContractApply(String projectId, String businessKey) {
		ModelMap model =  new ModelMap();
		try {
			EmpDto empDto = null;
			ConFormatContractApply info = conFormatContractApplyService.findOne(businessKey);
			ConFormatContractApplyVo vo = new ConFormatContractApplyVo(info);
			empDto = CED.loadEmployeeByCode(info.getBusiForm().getLaunchEmpCode());
			model.put("vo", vo);
			model.put("emp", empDto);
		} catch (Exception e) {
			logger.error("进入格式化合同申领详情异常：", e);
			e.printStackTrace();
		}
		return new ModelAndView("/contract/formatContract/format_contract_apply_view", model);
	}
	
	/**
	 * @Title: saveFormatContractApply 
	 * @Description: 保存格式化合同申领
	 * @author zhongyong 
	 * @param vo
	 * @return
	 */
	@RequestMapping("/saveFormatContractApply")
	@UriKey(key = "com.zdsoft.finance.contract.saveFormatContractApply")
	@ResponseBody
	public ResponseMsg saveFormatContractApply(ConFormatContractApplyVo vo){
		ResponseMsg msg = new ResponseMsg();
        try {
        	ConFormatContractApply entity = vo.toPo();
        	entity = conFormatContractApplyService.saveOrUpdateFormatContractApply(entity);
            msg.setId(entity.getId());
            if(entity.getIsSubmit()){
                msg.setMsg("提交成功！下一处理人："+entity.getCurrentDealEmpNm());
            }else{
                msg.setMsg("保存成功！");
                msg.setId(entity.getId());
            }
            msg.setResultStatus(ResponseMsg.SUCCESS);
        } catch (Exception e) {
            msg.setResultStatus(ResponseMsg.SYS_ERROR);
            msg.setMsg("保存机构合同报备申请失败！");
            logger.error("保存机构合同报备申请失败！");
            e.printStackTrace();
        }
		return msg;
	}
	
	
	/**
	 * @Title: processViewFormatContractApply 
	 * @Description: 格式化合同申领详情审核页面
	 * @author zhongyong 
	 * @param projectId 格式化合同申领id
	 * @param processInstanceId 流程id
	 * @param businessKey 流程key
	 * @return
	 */
	@RequestMapping(value = "/processViewFormatContractApply")
	@UriKey(key = "com.zdsoft.finance.contract.processViewFormatContractApply")
	@ManualJob(resource = "com.zdsoft.finance.contract.processViewFormatContractApply", label = "格式化合同申领详情审核页面")
	@FinishJob(resource = "com.zdsoft.finance.contract.processViewFormatContractApply", businessId = "businessKey", projectId = "projectId")
	public ModelAndView processViewFormatContractApply(String projectId, String processInstanceId,
			String businessKey) {
		ModelMap model =  new ModelMap();
		try {
			ConFormatContractApply info = conFormatContractApplyService.findOne(projectId);
			ConFormatContractApplyVo vo = new ConFormatContractApplyVo(info);
			EmpDto empDto = CED.loadEmployeeByCode(info.getBusiForm().getLaunchEmpCode());
			model.put("vo", vo);
			model.put("emp", empDto);
		} catch (Exception e) {
			logger.error("进入格式化合同申领详情审核页面异常：", e);
			e.printStackTrace();
		}
		return new ModelAndView("/contract/formatContract/format_contract_apply_process_view", model);
	}
	
	/**
	 * @Title: processEditStationSend 
	 * @Description: 格式化合同驻点寄出编辑页面
	 * @author zhongyong 
	 * @param projectId 格式化合同申领id
	 * @param processInstanceId 流程id
	 * @param businessKey 流程key
	 * @return
	 */
	@RequestMapping(value = "/processEditStationSend")
	@UriKey(key = "com.zdsoft.finance.contract.processEditStationSend")
	@ManualJob(resource = "com.zdsoft.finance.contract.processStationSend", label = "格式化合同驻点寄出")
	public ModelAndView processEditStationSend(String projectId, String processInstanceId,
			String businessKey) {
		ModelMap model =  new ModelMap();
		try {
			ConFormatContractApply info = conFormatContractApplyService.findOne(projectId);
			ConFormatContractApplyVo vo = new ConFormatContractApplyVo(info);
			EmpDto empDto = CED.loadEmployeeByCode(info.getBusiForm().getLaunchEmpCode());
			model.put("vo", vo);
			model.put("emp", empDto);
		} catch (Exception e) {
			logger.error("进入格式化合同驻点寄出编辑页面异常：", e);
			e.printStackTrace();
		}
		return new ModelAndView("/contract/formatContract/format_contract_station_send_edit", model);
	}
	
	/**
	 * @Title: processSaveStationSend 
	 * @Description: 格式化合同驻点寄出提交
	 * @author zhongyong 
	 * @param applyVo 格式化合同vo
	 * @param jsoncallback
	 * @param id 格式化合同申领id
	 * @param processInstanceId 流程id
	 * @param businessKey 流程key
	 * @return
	 */
	@RequestMapping(value = "/processSaveStationSend")
	@UriKey(key = "com.zdsoft.finance.contract.processSaveStationSend")
	@FinishJob(resource = "com.zdsoft.finance.contract.processStationSend", businessId = "id", projectId = "id")
	@ResponseBody
	public ResponseMsg processSaveStationSend(ConFormatContractApplyVo applyVo, String jsoncallback, String id,
			String processInstanceId, String businessKey) {
		ResponseMsg msg = new ResponseMsg();
		try {
			ConFormatContractApply apply = applyVo.toPo();
			conFormatContractApplyService.saveStationSend(apply);
			msg.setResultStatus(ResponseMsg.SUCCESS);
		} catch (Exception e) {
			msg.setResultStatus(ResponseMsg.SYS_ERROR);
			msg.setMsg("保存失败，请查看系统日志！");
			logger.error("流程中格式化合同驻点寄出保存", e);
			e.printStackTrace();
		}
		return msg;
	}

}
