package com.zdsoft.finance.contract.controller;

import java.util.ArrayList;
import java.util.HashMap;
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

import com.zdsoft.essential.client.service.CED;
import com.zdsoft.finance.common.base.QueryObj;
import com.zdsoft.finance.contract.entity.ConContractTpl;
import com.zdsoft.finance.contract.entity.ConFormatContractDetail;
import com.zdsoft.finance.contract.service.ConContractTplService;
import com.zdsoft.finance.contract.service.ConFormatContractDetailService;
import com.zdsoft.finance.contract.vo.ConFormatContractDetailVo;
import com.zdsoft.finance.cooperator.entity.Capitalist;
import com.zdsoft.finance.cooperator.service.CapitalistService;
import com.zdsoft.framework.core.common.dto.ResponseMsg;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.PageRequest;
import com.zdsoft.framework.core.common.util.ObjectHelper;
import com.zdsoft.framework.core.commweb.annotation.UriKey;
import com.zdsoft.framework.core.commweb.component.BaseController;
/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title: ConFormatContractDetailController.java 
 * @ClassName: ConFormatContractDetailController 
 * @Description: 格式化合同清单明细控制器
 * @author zhongyong 
 * @date 2017年3月7日 下午5:34:35 
 * @version V1.0
 */
@Controller
@RequestMapping("formatContractDetail")
public class ConFormatContractDetailController extends BaseController {
	
	@Autowired
	private ConFormatContractDetailService conFormatContractDetailService;
	@Autowired
	private CapitalistService capitalistService;
	@Autowired
	private CED CED;
	@Autowired
	private ConContractTplService conContractTplService;
	
	/**
	 * @Title: formatContractDetailList 
	 * @Description: 分页获取格式化合同清单明细
	 * @author zhongyong 
	 * @param request
	 * @param jsoncallback
	 * @param pageable 分页信息
	 * @return
	 */
	@RequestMapping("/formatContractDetailList")
	@UriKey(key = "com.zdsoft.finance.contract.formatContractDetailList")
	@ResponseBody
	public String formatContractDetailList(HttpServletRequest request, String jsoncallback, PageRequest pageable,String formatContractApplyId) {
		ResponseMsg msg = new ResponseMsg();
		try {
			// 获取页面封装的查询参数
			@SuppressWarnings("unchecked")
			List<QueryObj> queryObjs = (List<QueryObj>) request.getAttribute("listObj");
			if(ObjectHelper.isEmpty(queryObjs)){
				if(ObjectHelper.isNotEmpty(formatContractApplyId)){
					QueryObj qo = new QueryObj();
					qo.setObj("formatContractApplyId");
					qo.setElement("String");
					qo.setOperator("E");
					qo.setValue(formatContractApplyId);
					queryObjs.add(qo);
				}else{
					return ObjectHelper.objectToJson(msg, jsoncallback);
				}
			}
			// 分页查询
			Page<ConFormatContractDetail> page = conFormatContractDetailService.findByHqlConditions(pageable, queryObjs);
			List<ConFormatContractDetail> list = page.getRecords();
			List<ConFormatContractDetailVo> listVo = new ArrayList<ConFormatContractDetailVo>();
			for (ConFormatContractDetail info : list) {
				ConFormatContractDetailVo vo = new ConFormatContractDetailVo(info);
				Capitalist capitalist = capitalistService.findOne(vo.getCapitalistId());
				vo.setCapitalistName(capitalist.getCapitalName());
				vo.setContractTypeName(CED.loadSimpleCodeNameByFullCode(vo.getContractType()));
				ConContractTpl contract = conContractTplService.findOne(vo.getContractTemplateId());
				vo.setAttachmentId(contract.getAttachmentId());
				listVo.add(vo);
			}
			msg.setMsg("列表查询成功");
			msg.setResultStatus(ResponseMsg.SUCCESS);
			msg.setTotal(page.getTotalRows());
			msg.setRows(listVo);
			return ObjectHelper.objectToJson(msg, jsoncallback);
		} catch (Exception e) {
			msg.setResultStatus(ResponseMsg.SYS_ERROR);
			logger.error("分页获取格式化合同清单明细异常：", e);
			e.printStackTrace();
		}
		return ObjectHelper.objectToJson(msg, jsoncallback);
	}
	
	/**
	 * @Title: editFormatContractDetail 
	 * @Description: 格式化合同清单编辑
	 * @author zhongyong 
	 * @param id 清单id
	 * @param formatContractApplyId 格式化合同id
	 * @param operationType 操作类型
	 * @return
	 */
	@RequestMapping("/editFormatContractDetail")
	@UriKey(key = "com.zdsoft.finance.contract.editFormatContractDetail")
	public ModelAndView editFormatContractDetail(String id, String formatContractApplyId, String operationType) {
		ModelMap model = new ModelMap();
		try {
			if (StringUtils.isNotEmpty(id)) {
				ConFormatContractDetail info = conFormatContractDetailService.findOne(id);
				ConFormatContractDetailVo vo = new ConFormatContractDetailVo(info);
				model.put("vo", vo);
			}
			model.put("formatContractApplyId", formatContractApplyId);
			model.put("operationType", operationType);
		} catch (Exception e) {
			logger.error("进入格式化合同清单编辑页面异常：", e);
			e.printStackTrace();
		}
		return new ModelAndView("/contract/formatContract/format_contract_detail_edit", model);
	}
	
	
	/**
	 * @Title: saveFormatContractDetail 
	 * @Description: 保存格式化合同清单
	 * @author zhongyong 
	 * @param vo 格式化合同清单Vo
	 * @return
	 */
	@RequestMapping("/saveFormatContractDetail")
	@UriKey(key = "com.zdsoft.finance.contract.saveFormatContractDetail")
	@ResponseBody
	public String saveFormatContractDetail(ConFormatContractDetailVo vo){
		ResponseMsg msg = new ResponseMsg();
		try {
			ConFormatContractDetail detail = vo.toPO();
			conFormatContractDetailService.saveOrUpdateFormatContractDetail(detail);
			msg.setResultStatus(ResponseMsg.SUCCESS);
			msg.setMsg("保存成功");
		} catch (Exception e) {
			msg.setResultStatus(ResponseMsg.APP_ERROR);
			msg.setMsg("系统内部错误，请查看日志");
			logger.error("格式化合同清单保存失败", e);
			e.printStackTrace();
		}
		return ObjectHelper.objectToJson(msg);
	}
	
	/**
	 * @Title: delFormatContractDetail 
	 * @Description: 删除格式化合同清单明细
	 * @author zhongyong 
	 * @param id 清单明细id
	 * @param jsoncallback
	 * @return
	 */
	@RequestMapping("/delFormatContractDetail")
	@UriKey(key = "com.zdsoft.finance.contract.delFormatContractDetail")
	@ResponseBody
	public ResponseMsg delFormatContractDetail(String id, String jsoncallback) {
		ResponseMsg msg = new ResponseMsg();
		try {
			conFormatContractDetailService.logicDelete(id);
			msg.setResultStatus(ResponseMsg.SUCCESS);
			msg.setMsg("删除成功");
		} catch (Exception e) {
			msg.setResultStatus(ResponseMsg.APP_ERROR);
			msg.setMsg("系统内部错误，请查看日志");
			logger.error("格式化合同清单删除失败", e);
			e.printStackTrace();
		}
		return msg;
	}
	
	/**
	 * @Title: getCapitalistCategorys 
	 * @Description: 获取所有有效的资方列表
	 * @author zhongyong 
	 * @return
	 */
	@RequestMapping("/findAllCapitalist")
	@UriKey(key = "com.zdsoft.finance.contract.findAllCapitalist")
	@ResponseBody
	public String getCapitalistCategorys() {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		try {
			List<Capitalist> capitalists = capitalistService.findList();
			if (ObjectHelper.isNotEmpty(capitalists)) {
				for (Capitalist capitalist : capitalists) {
					Map<String, Object> returnData = new HashMap<String, Object>();
					returnData.put("fullcode", capitalist.getId());
					returnData.put("name", capitalist.getCapitalName());
					list.add(returnData);
				}
			}
		} catch (Exception e) {
			logger.error("获取资方出错", e);
			e.printStackTrace();
		}
		return ObjectHelper.objectToJson(list);

	}
	
	/**
	 * @Title: getContractTemplate 
	 * @Description: 获取标准合同模板
	 * @author zhongyong 
	 * @param capitalId 资方id
	 * @param contractType 合同类型
	 * @return
	 */
	@RequestMapping("/getContractTemplate")
	@UriKey(key = "com.zdsoft.finance.contract.getContractTemplate")
	@ResponseBody
	public String getContractTemplate(String capitalistId, String contractType) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		try {
			if (ObjectHelper.isNotEmpty(capitalistId) && ObjectHelper.isNotEmpty(contractType)) {
				List<ConContractTpl> capitalists = conContractTplService.findContractTplListForFormatContract(capitalistId, contractType);
				if (ObjectHelper.isNotEmpty(capitalists)) {
					for (ConContractTpl contract : capitalists) {
						Map<String, Object> returnData = new HashMap<String, Object>();
						returnData.put("fullcode", contract.getId());
						returnData.put("name", contract.getContractName());
						list.add(returnData);
					}
				}
			}

		} catch (Exception e) {
			logger.error("获取合同出错", e);
			e.printStackTrace();
		}
		return ObjectHelper.objectToJson(list);

	}

}
