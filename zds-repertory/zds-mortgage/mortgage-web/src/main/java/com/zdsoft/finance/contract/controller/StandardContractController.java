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
import com.zdsoft.essential.dto.basedata.AttachmentDto;
import com.zdsoft.finance.common.base.QueryObj;
import com.zdsoft.finance.contract.entity.ConContractTpl;
import com.zdsoft.finance.contract.service.ConContractTplService;
import com.zdsoft.finance.contract.vo.ConContractTplVo;
import com.zdsoft.finance.contract.vo.StandardContractVo;
import com.zdsoft.finance.cooperator.entity.Capitalist;
import com.zdsoft.finance.cooperator.service.CapitalistService;
import com.zdsoft.framework.core.common.dto.ResponseMsg;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.PageRequest;
import com.zdsoft.framework.core.common.util.ObjectHelper;
import com.zdsoft.framework.core.commweb.annotation.UriKey;
import com.zdsoft.framework.core.commweb.component.BaseController;
import com.zdsoft.framework.cra.annotation.Menu;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title: StandardContractController.java
 * @ClassName: StandardContractController
 * @Description: 标准合同控制器
 * @author zhongyong
 * @date 2017年2月28日 上午11:04:55
 * @version V1.0
 */
@Controller
@RequestMapping("/standardContract")
public class StandardContractController extends BaseController {

	@Autowired
	private ConContractTplService conContractTplService;
	@Autowired
	private CapitalistService capitalistService;
	@Autowired
	private CED CED;

	/**
	 * @Title: getStandardContract 
	 * @Description: 标准合同初始化
	 * @author zhongyong 
	 * @return
	 */
	@RequestMapping("/initStandardContract")
	@UriKey(key = "com.zdsoft.finance.contract.initStandardContract")
	@Menu(resource = "com.zdsoft.finance.contract.initStandardContract", label = "标准合同设置", group = "contract", order = 1)
	public ModelAndView initStandardContract() {
		return new ModelAndView("/contract/standard_contract_list");
	}

	/**
	 * @Title: standardContractList 
	 * @Description: 标准合同查询
	 * @author zhongyong 
	 * @param request
	 * @param jsoncallback
	 * @param pageable 分页信息
	 * @return
	 */
	@RequestMapping("/standardContractList")
	@UriKey(key = "com.zdsoft.finance.contract.standardContractList")
	@ResponseBody
	public String standardContractList(HttpServletRequest request, String jsoncallback, PageRequest pageable) {
		ResponseMsg msg = new ResponseMsg();
		try {
			// 获取页面封装的查询参数
			@SuppressWarnings("unchecked")
			List<QueryObj> queryObjs = (List<QueryObj>) request.getAttribute("listObj");
			// 分页查询
			Page<Map<String, Object>> page = conContractTplService.findPageContract(pageable, queryObjs, 1);
			msg.setMsg("列表查询成功");
			msg.setResultStatus(ResponseMsg.SUCCESS);
			msg.setTotal(page.getTotalRows());
			msg.setRows(page.getRecords());
		} catch (Exception e) {
			msg.setResultStatus(ResponseMsg.SYS_ERROR);
			logger.error("标准合同查询异常：", e);
			e.printStackTrace();
		}
		return ObjectHelper.objectToJson(msg, jsoncallback);
	}
	
	/**
	 * @Title: getListAttr 
	 * @Description: 附件文件操作
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
	 * @Title: dialog 
	 * @Description: 标准合同编辑页面
	 * @author zhongyong 
	 * @param id 标准合同id
	 * @param operationType 操作类型
	 * @return
	 */
	@RequestMapping("/editStandardContract")
	@UriKey(key = "com.zdsoft.finance.contract.editStandardContract")
	public ModelAndView editStandardContract(String id, String operationType){
		ModelMap model = new ModelMap();
		try {
			if ("mod".equals(operationType) || "view".equals(operationType)) {
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
			}else{
				ConContractTplVo vo = new ConContractTplVo();
				vo.setContractTplState("Enable");
				model.put("vo",vo);
			}
			model.put("operationType", operationType);
		} catch (Exception e) {
			logger.error("进入标准合同编辑页面异常：", e);
			e.printStackTrace();
		}
		
		return new ModelAndView("/contract/standard_contract_edit", model);
	}

	/**
	 * @Title: getCapitalistCategorys 
	 * @Description: 获取资方并转为json
	 * @author zhongyong 
	 * @param capitalistType 资方类型code
	 * @return
	 */
	@RequestMapping("/getCapitalistCategorys")
	@UriKey(key = "com.zdsoft.finance.contract.getCapitalistCategory")
	@ResponseBody
	public String getCapitalistCategorys(String capitalistType) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		try {
			if (ObjectHelper.isNotEmpty(capitalistType)) {
				List<Capitalist> capitalists = capitalistService.findCapitalistByCapitalistType(capitalistType, Capitalist.ENABLE);
				if (ObjectHelper.isNotEmpty(capitalists)) {
					for (Capitalist capitalist : capitalists) {
						Map<String, Object> returnData = new HashMap<String, Object>();
						returnData.put("fullcode", capitalist.getId());
						returnData.put("name", capitalist.getCapitalName());
						list.add(returnData);
					}
				}
			}
		} catch (Exception e) {
			logger.error("获取资方出错", e);
			e.printStackTrace();
		}
		return ObjectHelper.objectToJson(list);

	}
	
	/**
	 * @Title: saveOrUpdatestandardContract 
	 * @Description: 保存标准合同
	 * @author zhongyong 
	 * @param standardContractVo 标准合同vo
	 * @return
	 */
	@RequestMapping("/saveStandardContract")
	@UriKey(key = "com.zdsoft.finance.contract.saveStandardContract")
	@ResponseBody
	public ResponseMsg saveStandardContract(StandardContractVo standardContractVo) {
		ResponseMsg msg = new ResponseMsg();
		// 增加
		try {
			ConContractTpl conContractTpl = standardContractVo.toPo();
			conContractTplService.saveOrUpdateEntity(conContractTpl);
			msg.setResultStatus(ResponseMsg.SUCCESS);
			msg.setMsg("保存成功");
		} catch (Exception e) {
			msg.setResultStatus(ResponseMsg.APP_ERROR);
			msg.setMsg("系统内部错误，请查看日志");
			logger.error("标准合同保存失败", e);
			e.printStackTrace();
		}
		return msg;
	}

	/**
	 * @Title: deleteStandardContract 
	 * @Description: 删除标准合同
	 * @author zhongyong 
	 * @param id 标准合同id
	 * @param jsoncallback
	 * @return
	 */
	@RequestMapping("/delStandardContract")
	@UriKey(key = "com.zdsoft.finance.contract.standardContract.delStandardContract")
	@ResponseBody
	public ResponseMsg delStandardContract(String id, String jsoncallback) {
		ResponseMsg msg = new ResponseMsg();
		try {
			conContractTplService.logicDelete(id);
			msg.setResultStatus(ResponseMsg.SUCCESS);
			msg.setMsg("删除成功");
		} catch (Exception e) {
			msg.setResultStatus(ResponseMsg.APP_ERROR);
			msg.setMsg("系统内部错误，请查看日志");
			logger.error("标准合同删除失败", e);
			e.printStackTrace();
		}
		return msg;
	}
}