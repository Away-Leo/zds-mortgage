package com.zdsoft.finance.workbench.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zdsoft.bpm.dto.TaskInstanceQueryDto;
import com.zdsoft.bpm.service.client.BPM;
import com.zdsoft.essential.dto.basedata.SimpleCodeDto;
import com.zdsoft.finance.busiform.entity.BusiForm;
import com.zdsoft.finance.busiform.entity.BusiFormStatus;
import com.zdsoft.finance.busiform.service.BusiFormService;
import com.zdsoft.finance.common.base.QueryObj;
import com.zdsoft.finance.common.enums.busiform.ApplyModelTypeEnum;
import com.zdsoft.finance.workbench.vo.BusiFormVo;
import com.zdsoft.framework.core.common.dto.ResponseMsg;
import com.zdsoft.framework.core.common.exception.AppException;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.PageRequest;
import com.zdsoft.framework.core.common.util.ObjectHelper;
import com.zdsoft.framework.core.commweb.annotation.UriKey;
import com.zdsoft.framework.core.commweb.component.BaseController;
import com.zdsoft.framework.cra.annotation.Menu;

/** 
* 版权所有：重庆正大华日软件有限公司
* @Title: BusiFormController.java 
* @Package com.zdsoft.finance.workbench.controller 
* @Description: 业务表单Controller
* @author jingyh 
* @date 2017年2月17日 下午8:43:58 
* @version V1.0 
*/
@Controller
@RequestMapping("/busiForm")
public class BusiFormController extends BaseController {
	
	@Autowired
	private BusiFormService busiFormService;
	
	@Autowired
	private BPM BPM;

	/**
	 * 默认废弃urikey
	 */
	public static final String DEFAULT_WASTE_URIKEY = "com.zdsoft.finance.workbench.wasteMyApplyInfo";
	/**
	 * 
	 * @Title: getBusiFormStatus 
	 * @Description: 获得流程审批状态
	 * @author jingyh 
	 * @param req
	 * @param jsoncallback
	 * @return
	 */
    @RequestMapping("/getBusiFormStatus")
    @UriKey(key = "com.zdsoft.finance.workbench.getBusiFormStatus")
    @ResponseBody
    public String getBusiFormStatus(HttpServletRequest req, String jsoncallback) {
        List<SimpleCodeDto> simpleCodes = new ArrayList<SimpleCodeDto>();
        BusiFormStatus[] values = BusiFormStatus.values();
        for (BusiFormStatus status : values) {
        	SimpleCodeDto simpleMap = new SimpleCodeDto();
            // 封闭成下拉选项，页面上对应fullcode和name
            simpleMap.setFullCode(String.valueOf(status.value));
            simpleMap.setName(BusiFormStatus.getName(status.value));
            simpleCodes.add(simpleMap);
        }
        return ObjectHelper.objectToJson(simpleCodes, jsoncallback);
    }
	
    
    /**
     * 
     * @Title: getBusiModelTypes 
     * @Description: 获得申请类型
     * @author jingyh 
     * @param req
     * @param jsoncallback
     * @return
     */
    @RequestMapping("/getBusiModelTypes")
    @UriKey(key = "com.zdsoft.finance.workbench.getBusiModelTypes")
    @ResponseBody
    public String getBusiModelTypes(HttpServletRequest req, String jsoncallback) {
    	 List<SimpleCodeDto> simpleCodes = new ArrayList<SimpleCodeDto>();
    	 ApplyModelTypeEnum[] values = ApplyModelTypeEnum.values();
        for (ApplyModelTypeEnum status : values) {
        	SimpleCodeDto simpleMap = new SimpleCodeDto();
            // 封闭成下拉选项，页面上对应fullcode和name
            simpleMap.setFullCode(status.value);
            simpleMap.setName(status.name);
            simpleCodes.add(simpleMap);
        }
        return ObjectHelper.objectToJson(simpleCodes, jsoncallback);
    }
    
	/**
	 * @Title: list 
	 * @Description: 我的申请入口
	 * @author jingyh 
	 * @param req
	 * @return
	 */
	@RequestMapping("/initMyApplyInfoPage")
	@UriKey(key = "com.zdsoft.finance.workbench.initMyApplyInfoPage")
	@Menu(resource = "menu.com.zdsoft.finance.workbench.initMyApplyInfoPage", label = "我的申请", group = "workbench", order = 2)
	public ModelAndView initMyApplyInfoPage(HttpServletRequest req) {
		logger.info("进入我的申请页面");
		ModelMap model = new ModelMap();
		try {
			model.put("processViewUrl", BPM.getProStatusDiagramUrl());
		} catch (AppException e) {
			e.printStackTrace();
			logger.error("获得流程图url失败！");
		}
		return new ModelAndView("/workbench/my_apply_info_list", model);
	}
	
	/**
	 * @Title: findMyApplyListInfos 
	 * @Description: 我的申请列表信息
	 * @author jingyh 
	 * @param req
	 * @param pageReq
	 * @return
	 */
	@RequestMapping("/myApplyListInfos")
	@UriKey(key = "com.zdsoft.finance.workbench.myApplyListInfos")
	@ResponseBody
	public ResponseMsg findMyApplyListInfos(HttpServletRequest req, PageRequest pageReq) {
		ResponseMsg msg = new ResponseMsg();
		logger.info("分页查询我的申请记录！");
		try {
			@SuppressWarnings("unchecked")
			List<QueryObj> listObj = (List<QueryObj>) req.getAttribute("listObj");
			Page<BusiForm> applyInfos = busiFormService.findMyApplyInfos(pageReq, listObj);
			List<BusiFormVo> applyInfoVos = new ArrayList<BusiFormVo>();
			List<BusiForm> records = applyInfos.getRecords();
			Set<String> processIds = new HashSet<String>();
			for (BusiForm entity : records) {
				if (ObjectHelper.isNotEmpty(entity.getProcessInstanceKey())) {
					processIds.add(entity.getProcessInstanceKey());
				}
				applyInfoVos.add(new BusiFormVo(req, entity));
			}
			// 封装当前任务处理信息
			if (ObjectHelper.isNotEmpty(processIds)) {
				Map<String, List<TaskInstanceQueryDto>> taskInfoMap = BPM.findProInstanceByIds(processIds);
				for (BusiFormVo voInfo : applyInfoVos) {
					if (ObjectHelper.isNotEmpty(voInfo.getProcessInstanceKey()) && taskInfoMap.containsKey(voInfo.getProcessInstanceKey())) {
						List<TaskInstanceQueryDto> taskList = taskInfoMap.get(voInfo.getProcessInstanceKey());
						if (ObjectHelper.isNotEmpty(taskList)) {
							String currentDealEmpNm = null;
							String currentTaskId = null;
							String currentTaskName = null;
							for (TaskInstanceQueryDto taskDto : taskList) {
								// 当前处理人
								if (currentDealEmpNm == null) {
									currentDealEmpNm = taskDto.getAssigneeNm();
								} else {
									currentDealEmpNm += ";" +  taskDto.getAssigneeNm(); 
								}
								// 当前任务Id
								if (currentTaskId == null) {
									currentTaskId = taskDto.getTaskInstanceId();
								} else {
									currentTaskId += ";" +  taskDto.getTaskInstanceId(); 
								}
								// 当前任务名
								if (currentTaskName == null) {
									currentTaskName = taskDto.getTaskName();
								} else {
									currentTaskName += ";" +  taskDto.getTaskName(); 
								}
							}
							voInfo.setCurrentDealEmpNm(currentDealEmpNm);
							voInfo.setCurrentTaskId(currentTaskId);
							voInfo.setCurrentTaskName(currentTaskName);
						}
					}
				}
			}
			msg.setRows(applyInfoVos);
			msg.setTotal(applyInfos.getTotalRows());
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("查询我的申请发生错误！");
			msg.setResultStatus(ResponseMsg.APP_ERROR);
			msg.setMsg(e.getMessage());
		}
		return msg;
	}
	
	/**
	 * 
	 * @Title: wasteMyApplyInfo 
	 * @Description: 废弃我的申请
	 * @author jingyh 
	 * @param req
	 * @param busiFormId
	 * @return
	 */
	@RequestMapping("/wasteMyApplyInfo")
	@UriKey(key = DEFAULT_WASTE_URIKEY)
	@ResponseBody
	public ResponseMsg wasteMyApplyInfo(HttpServletRequest req, String busiFormId) {
		ResponseMsg msg = new ResponseMsg();
		logger.info("废弃我的申请！");
		logger.debug("表单Id为：{}", busiFormId);
		try {
			this.busiFormService.wasteApplyInfo(busiFormId);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("废弃申请发生异常！");
			msg.setResultStatus(ResponseMsg.APP_ERROR);
			msg.setMsg(e.getMessage());
		}
		return msg;
	}
}
