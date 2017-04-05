package com.zdsoft.finance.contract.controller;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import com.zdsoft.essential.dto.basedata.AttachmentDto;
import com.zdsoft.essential.dto.emp.EmpDto;
import com.zdsoft.finance.common.base.QueryObj;
import com.zdsoft.finance.common.utils.DateUtil;
import com.zdsoft.finance.common.utils.ExcelUtil;
import com.zdsoft.finance.contract.entity.ConAgencyContractTpl;
import com.zdsoft.finance.contract.entity.ConContractTpl;
import com.zdsoft.finance.contract.service.ConAgencyContractTplService;
import com.zdsoft.finance.contract.service.ConContractTplService;
import com.zdsoft.finance.contract.vo.ConAgencyContractTplVo;
import com.zdsoft.finance.contract.vo.ConContractTplVo;
import com.zdsoft.framework.core.common.dto.ResponseMsg;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.PageRequest;
import com.zdsoft.framework.core.common.util.DateHelper;
import com.zdsoft.framework.core.common.util.ObjectHelper;
import com.zdsoft.framework.core.commweb.annotation.UriKey;
import com.zdsoft.framework.core.commweb.component.BaseController;
import com.zdsoft.framework.cra.annotation.Menu;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title: AgencyContractController.java 
 * @ClassName: AgencyContractController 
 * @Description: 机构合同报备控制器
 * @author zhongyong 
 * @date 2017年2月28日 下午3:58:41 
 * @version V1.0
 */
@Controller
@RequestMapping("agencyContract")
public class AgencyContractController extends BaseController{

	@Autowired
	private ConAgencyContractTplService conAgencyContractTplService;
	@Autowired
	private ConContractTplService conContractTplService;
	@Autowired
	private CED CED;
	
	/**
	 * 机构合同报备路径
	 */
	public static final String SPARE_AGENCY_CONTRACT_PATH = "/WEB-INF/modules/exportExcel/机构合同报备报表.xls";
	/**
	 * @Title: initContract 
	 * @Description: 机构合同报备入口
	 * @author zhongyong 
	 * @return
	 */
	@RequestMapping("/initAgencyContract")
	@UriKey(key = "com.zdsoft.finance.contract.initAgencyContract")
	@Menu(resource = "com.zdsoft.finance.contract.initAgencyContract", label = "机构合同报备", group = "contract", order = 2)
	public ModelAndView initAgencyContract() {
		return new ModelAndView("/contract/agency_contract_list");
	}
	
	
	/**
	 * @Title: agencyContractList 
	 * @Description: 机构合同报备查询
	 * @author zhongyong 
	 * @param pageable 分页信息
	 * @param jsoncallback
	 * @return
	 */
	@RequestMapping("/agencyContractList")
	@UriKey(key = "com.zdsoft.finance.contract.agencyContractList")
	@ResponseBody
	public String agencyContractList(HttpServletRequest request, PageRequest pageable, String jsoncallback) {
		ResponseMsg msg = new ResponseMsg();
		try {
			// 获取页面封装的查询参数
			@SuppressWarnings("unchecked")
			List<QueryObj> queryObjs = (List<QueryObj>) request.getAttribute("listObj");
			// 分页查询
			Page<Map<String, Object>> page = conAgencyContractTplService.findPageAgencyContract(pageable, queryObjs);
			msg.setMsg("列表查询成功");
			msg.setResultStatus(ResponseMsg.SUCCESS);
			msg.setTotal(page.getTotalRows());
			msg.setRows(page.getRecords());
		} catch (Exception e) {
			msg.setResultStatus(ResponseMsg.SYS_ERROR);
			logger.error("机构合同报备查询异常：", e);
			e.printStackTrace();
		}
		return ObjectHelper.objectToJson(msg, jsoncallback);
	}
	
	
	/**
	 * @Title: editAgencyContract 
	 * @Description: 机构合同报备编辑
	 * @author zhongyong 
	 * @param id 机构合同id
	 * @return
	 */
	@RequestMapping("/editAgencyContract")
	@UriKey(key = "com.zdsoft.finance.contract.editAgencyContract")
	public ModelAndView editAgencyContract(String id) {
		ModelMap model =  new ModelMap();
		try {
			EmpDto empDto = null;
			ConAgencyContractTplVo vo = null;
			if (StringUtils.isEmpty(id)) {
				vo = new ConAgencyContractTplVo();
				empDto = CED.getLoginUser();
				//自动生成申请编号
				vo.setApplyNo(CED.resolveExpression("1000000000004", null ));
				//自动得到申请时间
				vo.setApplyDate(DateUtil.getCurrentDate());
				vo.setApplyDateStr(DateHelper.longToDate(DateUtil.getCurrentDate(), DateHelper.DATE_WITHMINUTE_FORMAT));
			} else {
				ConAgencyContractTpl info = conAgencyContractTplService.findOne(id);
				vo = new ConAgencyContractTplVo(info);
				empDto = CED.loadEmployeeByCode(info.getBusiForm().getLaunchEmpCode());
			}
			model.put("vo", vo);
			model.put("emp", empDto);
		} catch (Exception e) {
			logger.error("进入机构合同报备编辑页面异常：", e);
			e.printStackTrace();
		}
		return new ModelAndView("/contract/agency_contract_edit", model);
	}
	
	/**
	 * 
	 * @Title: editProcessAgencyContract 
	 * @Description: 机构合同报备编辑 流程中
	 * @author dengyy 
	 * @param projectId 案件id（项目id，业务id）
	 * @param processInstanceId 流程id
	 * @param businessKey 业务id
	 * @return
	 */
	@RequestMapping("/editProcessAgencyContract")
    @UriKey(key = "com.zdsoft.finance.contract.editProcessAgencyContract")
	@ManualJob(resource="com.zdsoft.finance.contract.editProcessAgencyContract",label="机构合同报备编辑")
    public ModelAndView editProcessAgencyContract(String projectId, String processInstanceId, String businessKey) {
        ModelMap model =  new ModelMap();
        try {
            ConAgencyContractTpl info = conAgencyContractTplService.findOne(projectId);
            ConAgencyContractTplVo vo = new ConAgencyContractTplVo(info);
            EmpDto empDto = CED.loadEmployeeByCode(info.getBusiForm().getLaunchEmpCode());
            model.put("vo", vo);
            model.put("emp", empDto);
        } catch (Exception e) {
            logger.error("进入机构合同报备编辑流程页面异常：", e);
            e.printStackTrace();
        }
        return new ModelAndView("/contract/agency_contract_process_edit", model);
    }
	
	/**
     * @Title: savePrecssAgencyContract 
     * @Description: 保存机构合同报备 流程
     * @author zhongyong 
     * @param vo 机构合同vo
     * @return
     */
    @RequestMapping("/savePrecssAgencyContract")
    @UriKey(key = "com.zdsoft.finance.contract.savePrecssAgencyContract")
    @FinishJob(resource="com.zdsoft.finance.contract.editProcessAgencyContract",businessId="vo.id",projectId="vo.id")
    @ResponseBody
    public ResponseMsg savePrecssAgencyContract(ConAgencyContractTplVo vo){
        ResponseMsg msg = new ResponseMsg();
        try {
            ConAgencyContractTpl agencyContract = vo.toPo();
            agencyContract = conAgencyContractTplService.saveOrUpdateAgencyContract(agencyContract);
            msg.setId(agencyContract.getId());
            msg.setMsg("保存成功！");
            msg.setResultStatus(ResponseMsg.SUCCESS);
        } catch (Exception e) {
            msg.setResultStatus(ResponseMsg.SYS_ERROR);
            msg.setMsg("保存机构合同报备申请失败！");
            logger.error("保存机构合同报备申请失败！", e);
            e.printStackTrace();
        }
        return msg;
    }
	
	/**
	 * @Title: viewAgencyContract 
	 * @Description: 机构合同报备查看
	 * @author zhongyong 
	 * @param id 机构合同id
	 * @return
	 */
	@RequestMapping("/viewAgencyContract")
	@UriKey(key = "com.zdsoft.finance.contract.viewAgencyContract")
	public ModelAndView viewAgencyContract(String projectId, String businessKey,String id) {
		ModelMap model =  new ModelMap();
		try {
			ConAgencyContractTpl info = conAgencyContractTplService.findOne(id);
			ConAgencyContractTplVo vo = new ConAgencyContractTplVo(info);
			EmpDto empDto = CED.loadEmployeeByCode(info.getBusiForm().getLaunchEmpCode());
			model.put("vo", vo);
			model.put("emp", empDto);
		} catch (Exception e) {
			logger.error("进入机构合同报备查看页面异常：", e);
			e.printStackTrace();
		}
		return new ModelAndView("/contract/agency_contract_view", model);
	}
	
	/**
	 * @Title: saveAgencyContract 
	 * @Description: 保存机构合同报备
	 * @author zhongyong 
	 * @param vo 机构合同vo
	 * @return
	 */
	@RequestMapping("/saveAgencyContract")
	@UriKey(key = "com.zdsoft.finance.contract.saveAgencyContract")
	@ResponseBody
	public ResponseMsg saveAgencyContract(ConAgencyContractTplVo vo){
		ResponseMsg msg = new ResponseMsg();
        try {
        	ConAgencyContractTpl agencyContract = vo.toPo();
        	agencyContract = conAgencyContractTplService.saveOrUpdateAgencyContract(agencyContract);
            msg.setId(agencyContract.getId());
            if(agencyContract.getIsSubmit()){
                msg.setMsg("提交成功！下一处理人："+agencyContract.getCurrentDealEmpNm());
            }else{
                msg.setMsg("保存成功！");
            }
            msg.setResultStatus(ResponseMsg.SUCCESS);
        } catch (Exception e) {
            msg.setResultStatus(ResponseMsg.SYS_ERROR);
            msg.setMsg("保存机构合同报备申请失败！");
            logger.error("保存机构合同报备申请失败！", e);
            e.printStackTrace();
        }
		return msg;
	}
	
	/**
	 * @Title: delAgencyContract 
	 * @Description: 删除机构合同报备
	 * @author zhongyong 
	 * @param id 机构合同id
	 * @param jsoncallback
	 * @return
	 */
	@RequestMapping("/delAgencyContract")
	@UriKey(key = "com.zdsoft.finance.contract.delAgencyContract")
	@ResponseBody
	public ResponseMsg delAgencyContract(String id, String jsoncallback) {
		ResponseMsg msg = new ResponseMsg();
		try {
			conAgencyContractTplService.logicDelete(id);
			msg.setResultStatus(ResponseMsg.SUCCESS);
			msg.setMsg("删除成功");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("机构合同报备删除失败", e);
			msg.setResultStatus(ResponseMsg.APP_ERROR);
			msg.setMsg("系统内部错误，请查看日志");
		}
		return msg;
	}
	
	
	/**
	 * @Title: processViewAgencyContract 
	 * @Description: 机构合同报备审批页面
	 * @author zhongyong 
	 * @param projectId
	 * @param processInstanceId
	 * @param businessKey
	 * @return
	 */
	@RequestMapping(value = "/processViewAgencyContract")
	@UriKey(key = "com.zdsoft.finance.contract.processViewAgencyContract")
	@ManualJob(resource = "com.zdsoft.finance.contract.processViewAgencyContract", label = "机构合同报备审批页面")
	@FinishJob(resource = "com.zdsoft.finance.contract.processViewAgencyContract", businessId = "businessKey", projectId = "projectId")
	public ModelAndView processViewAgencyContract(String projectId, String processInstanceId,
			String businessKey) {
		ModelMap model =  new ModelMap();
		try {
			ConAgencyContractTpl info = conAgencyContractTplService.findOne(projectId);
			ConAgencyContractTplVo vo = new ConAgencyContractTplVo(info);
			EmpDto empDto = CED.loadEmployeeByCode(info.getBusiForm().getLaunchEmpCode());
			model.put("vo", vo);
			model.put("emp", empDto);
		} catch (Exception e) {
			logger.error("进入机构合同报备审批页面异常：", e);
			e.printStackTrace();
		}
		return new ModelAndView("/contract/agency_contract_process_view", model);
	}
	
	
	/**
	 * @Title: agencyContractSubList 
	 * @Description: 获取机构合同报备下的合同列表
	 * @author zhongyong 
	 * @param request
	 * @param jsoncallback
	 * @param pageable 分页信息
	 * @return
	 */
	@RequestMapping("/agencyContractSubList")
	@UriKey(key = "com.zdsoft.finance.contract.agencyContractSubList")
	@ResponseBody
	public String agencyContractSubList(HttpServletRequest request, String jsoncallback, PageRequest pageable) {
		ResponseMsg msg = new ResponseMsg();
		try {
			// 获取页面封装的查询参数
			@SuppressWarnings("unchecked")
			List<QueryObj> queryObjs = (List<QueryObj>) request.getAttribute("listObj");
			if(ObjectHelper.isEmpty(queryObjs)){
				String agencyContractIdBack = request.getParameter("agencyContractIdBack");
				if(ObjectHelper.isNotEmpty(agencyContractIdBack)){
					QueryObj qObj = new QueryObj();
					qObj.setElement("orgCantractApplyId");
					qObj.setObj("tpl");
					qObj.setValue(agencyContractIdBack);
					qObj.setOperator("=");
					queryObjs.add(qObj);
				}else{
					return ObjectHelper.objectToJson(msg, jsoncallback);
				}
			}
			// 分页查询
			Page<Map<String, Object>> page = conContractTplService.findPageContract(pageable, queryObjs, 2);
			msg.setMsg("列表查询成功");
			msg.setResultStatus(ResponseMsg.SUCCESS);
			msg.setTotal(page.getTotalRows());
			msg.setRows(page.getRecords());
		} catch (Exception e) {
			msg.setResultStatus(ResponseMsg.SYS_ERROR);
			logger.error("机构合同报备下的合同列表查询异常：", e);
		}
		return ObjectHelper.objectToJson(msg, jsoncallback);
	}
	
	/**
	 * @Title: editSubAgencyContract 
	 * @Description: 编辑机构合同报备下的合同
	 * @author zhongyong 
	 * @param id 机构合同id
	 * @param agencyContractId
	 * @return
	 */
	@RequestMapping("/editSubAgencyContract")
	@UriKey(key = "com.zdsoft.finance.contract.editSubAgencyContract")
	public ModelAndView editSubAgencyContract(String id, String agencyContractId, String operationType) {
		ModelMap model = new ModelMap();
		try {
			if (StringUtils.isNotEmpty(id)) {
				ConContractTpl info = conContractTplService.findOne(id);
				ConContractTplVo vo = new ConContractTplVo(info);
				List<AttachmentDto> dto = new ArrayList<>();
				if (vo != null && StringUtils.isNotEmpty(vo.getAttachmentId())
						&& vo.getAttachmentId().contains(",")) {
					String[] att = vo.getAttachmentId().split(",");
					dto = getListAttr(att);
				} else {
					if (!StringUtils.isEmpty(vo.getAttachmentId())) {
						AttachmentDto attr = CED.findAttachmentById(vo.getAttachmentId());
						dto.add(attr);
					}
				}
				model.put("attrs", dto);
				model.put("vo", vo);
			}
			model.put("agencyContractId", agencyContractId);
			model.put("operationType", operationType);
		} catch (Exception e) {
			logger.error("进入机构合同报备下的合同编辑页面异常：", e);
			e.printStackTrace();
		}
		return new ModelAndView("/contract/agency_contract_sub_edit", model);
	}
	
	/**
	 * @Title: saveSubAgencyContract 
	 * @Description: 保存机构合同报备下的合同
	 * @author zhongyong 
	 * @param vo 机构合同vo
	 * @return
	 */
	@RequestMapping("/saveSubAgencyContract")
	@UriKey(key = "com.zdsoft.finance.contract.saveSubAgencyContract")
	@ResponseBody
	public String saveSubAgencyContract(ConContractTplVo vo){
		ResponseMsg msg = new ResponseMsg();
		try {
			ConContractTpl conContractTpl = vo.toPo();
			//机构合同报备下的合同默认为不启用
			conContractTpl.setContractTplState("Disable");
			conContractTplService.saveOrUpdateEntity(conContractTpl);
			msg.setResultStatus(ResponseMsg.SUCCESS);
			msg.setMsg("保存成功");
		} catch (Exception e) {
			msg.setResultStatus(ResponseMsg.APP_ERROR);
			msg.setMsg("系统内部错误，请查看日志");
			logger.error("机构合同报备下的合同保存失败", e);
			e.printStackTrace();
		}
		return ObjectHelper.objectToJson(msg);
	}
	
	/**
	 * @Title: delSubAgencyContract 
	 * @Description: 删除机构合同报备下的合同
	 * @author zhongyong 
	 * @param id 机构合同id
	 * @param jsoncallback
	 * @return
	 */
	@RequestMapping("/delSubAgencyContract")
	@UriKey(key = "com.zdsoft.finance.contract.delSubAgencyContract")
	@ResponseBody
	public ResponseMsg delSubAgencyContract(String id, String jsoncallback) {
		ResponseMsg msg = new ResponseMsg();
		try {
			conContractTplService.logicDelete(id);
			msg.setResultStatus(ResponseMsg.SUCCESS);
			msg.setMsg("删除成功");
		} catch (Exception e) {
			msg.setResultStatus(ResponseMsg.APP_ERROR);
			msg.setMsg("系统内部错误，请查看日志");
			logger.error("机构合同报备下的合同删除失败", e);
			e.printStackTrace();
		}
		return msg;
	}
	
	/**
	 * @Title: getListAttr 
	 * @Description: 附件处理
	 * @author zhongyong 
	 * @param args 附件ids
	 * @return
	 * @throws Exception
	 */
	public List<AttachmentDto> getListAttr(String[] args) throws Exception{
		List<String> list = new ArrayList<>();
		for (String str : args) {
			list.add(str);
		}
		List<AttachmentDto> dto = CED.findAttachmentByIds(list);
		return dto;
	}
	
	
	/**
	 * 
	 * @Title: exportSpareCapital 
	 * @Description: 导出机构合同报备
	 * @author xj 
	 * @param request 请求
	 * @param response response
	 * @return
	 */
	@RequestMapping("/exportSpareCapital")
	@UriKey(key = "com.zdsoft.finance.capital.exportSpareCapital")
	@ResponseBody
	public void exportSpareCapital(HttpServletRequest request, HttpServletResponse response) {
		try {
			PageRequest pageRequest = new PageRequest();
			pageRequest.setPage(1);
			pageRequest.setRows(Integer.MAX_VALUE);
			List<QueryObj> queryObjs = new ArrayList<QueryObj>();
			Page<Map<String, Object>> page = conAgencyContractTplService.findPageAgencyContract(pageRequest, queryObjs);
			List<Map<String, Object>> records = page.getRecords();
			String tempFilePath = request.getSession().getServletContext().getRealPath("/") + SPARE_AGENCY_CONTRACT_PATH;

			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/x-download");
			response.addHeader("Content-Disposition",
					"attachment;filename=\"" + new String("机构合同报备报表".getBytes("gb2312"), "ISO8859-1")
							+ DateHelper.dateToLong(new Date(), DateHelper.DATE_LONG_SIMPLE_FORMAT) + ".xls" + "\"");

			OutputStream os = response.getOutputStream();
			ExcelUtil excel = new ExcelUtil();
			List<String> head = new ArrayList<String>();
			List<Map<Integer, Object>> datalist = new ArrayList<Map<Integer, Object>>();
			
			// 占用excel数据单元格
			head.add("A1");
			head.add("B1");
			head.add("C1");
			head.add("D1");
			
			for (Map<String, Object> map : records) {
				try {
					Map<Integer, Object> data = new HashMap<Integer, Object>();
					data.put(1, map.get("applyTitle"));

					data.put(2, map.get("applyType"));

					data.put(3, map.get("applyEmpNm"));

					data.put(4, map.get("applyStatusNm"));

					datalist.add(data);
				} catch (Exception e) {
					logger.error("导出机构合同报备", e);
				}
			}
			
			String[] heads = head.toArray(new String[head.size()]);// 必须为列表头部所有位置集合，

			// 输出
			// 数据单元格样式和头部单元格样式保持一致
			excel.writeDateList(tempFilePath, heads, datalist, 0);
			// 写到输出流并移除资源
			excel.writeAndClose(tempFilePath, os);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("导出机构合同报备失败", e);
		}
	}
}
