package com.zdsoft.finance.product.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zdsoft.essential.client.service.CED;
import com.zdsoft.essential.dto.emp.EmpDto;
import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.finance.product.entity.ProcessConfig;
import com.zdsoft.finance.product.entity.Product;
import com.zdsoft.finance.product.service.ProcessConfigService;
import com.zdsoft.finance.product.service.ProductService;
import com.zdsoft.finance.product.vo.ProcessConfigVo;
import com.zdsoft.finance.product.vo.ProductVo;
import com.zdsoft.framework.core.common.dto.ResponseMsg;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.PageRequest;
import com.zdsoft.framework.core.common.util.ObjectHelper;
import com.zdsoft.framework.core.commweb.annotation.UriKey;
import com.zdsoft.framework.core.commweb.component.BaseController;

/**
 * 流程配置控制器
 * @author longwei
 * @date 2016/12/28
 * @version 1.0
 */
@Controller
@RequestMapping("/processConfig")
public class ProcessConfigController extends BaseController{

	@Autowired
	private ProductService productService;
	
	@Autowired
	private ProcessConfigService processConfigService;
	
	@Autowired
	private CED CED;
	
	/**
	 * 审批意见配置页面
	 */
	@RequestMapping("/list")
	@UriKey(key="com.zdsoft.finance.processConfig.list")
	public ModelAndView list(String productId) throws BusinessException{
		ModelAndView modelAndView=new ModelAndView("product/process_config_list");
		if(ObjectHelper.isEmpty(productId)){
			logger.error("参数异常");
			throw new BusinessException("参数异常");
		}
		
		Product product=productService.findOne(productId);
		if(ObjectHelper.isEmpty(product)){
			logger.error("主产品已不存在，请确认是否已删除");
			throw new BusinessException("主产品已不存在，请确认是否已删除");
		}
		
		modelAndView.addObject("product", new ProductVo(product));
		
		return modelAndView;
	}
	
	/**
	 * 获取列表
	 */
	@ResponseBody
	@RequestMapping("/getList")
	@UriKey(key="com.zdsoft.finance.processConfig.getList")
	public ResponseMsg getList(ProcessConfigVo processConfigVo,PageRequest pageable) {

		ResponseMsg msg=new ResponseMsg();
		ProcessConfig processConfig = processConfigVo.toPo();
		if(ObjectHelper.isEmpty(processConfig.getIsEnable())){
			processConfig.setIsEnable(true);
		}
		try {
			Page<ProcessConfig> page=processConfigService.findPage(processConfig, pageable);
			List<ProcessConfigVo> list=new ArrayList<ProcessConfigVo>();
			for(ProcessConfig queryProcessConfig:page.getRecords()){
				list.add(new ProcessConfigVo(queryProcessConfig));
			}
			msg.setRows(list);
			msg.setTotal(page.getTotalRows());
		} catch (BusinessException e) {
			logger.error("查询分页失败",e);
			e.printStackTrace();
			msg.setResultStatus(ResponseMsg.APP_ERROR);
			msg.setMsg("查询分页失败");
		}
		return msg;
	}
	
	/**
	 * 审批意见对话框
	 */
	@RequestMapping("/dialog")
	@UriKey(key="com.zdsoft.finance.processConfig.dialog")
	public ModelAndView dialog(String productId,String processConfigId) throws BusinessException{
		ModelAndView modelAndView=new ModelAndView("product/process_config_dialog");
		if(ObjectHelper.isNotEmpty(processConfigId)){
			ProcessConfig processConfig=processConfigService.findOne(processConfigId);
			if(ObjectHelper.isEmpty(processConfig)){
				logger.error("该流程不存在");
				throw new BusinessException("该流程不存在");
			}
			modelAndView.addObject("processConfig", new ProcessConfigVo(processConfig));
		}
		modelAndView.addObject("productId", productId);
		return modelAndView;
	}
	
	/**
	 * 添加或修改审批意见
	 */
	@ResponseBody
	@RequestMapping("/saveOrUpdate")
	@UriKey(key="com.zdsoft.finance.processConfig.saveOrUpdate")
	public ResponseMsg saveOrUpdate(ProcessConfigVo processConfigVo){
		ResponseMsg msg=new ResponseMsg();
		ProcessConfig processConfig = processConfigVo.toPo();
		
		try{
			// 构建公共字段
			buildCommonField(processConfig);
			
			processConfigService.saveOrUpdate(processConfig);
		}catch (Exception e) {
			logger.error("更新失败",e);
			msg.setResultStatus(ResponseMsg.APP_ERROR);
			msg.setMsg("更新失败");
		}
		
		return msg;
	}
	
	/**
	 * 删除
	 */
	@ResponseBody
	@RequestMapping("/delete")
	@UriKey(key="com.zdsoft.finance.processConfig.delete")
	public ResponseMsg delete(String processConfigId){
		ResponseMsg msg=new ResponseMsg();
		if(ObjectHelper.isEmpty(processConfigId)){
			logger.error("参数为空");
			msg.setMsg("参数为空");
			msg.setResultStatus(ResponseMsg.APP_ERROR);
			return msg;
		}
		
		try {
			ProcessConfig processConfig = processConfigService.findOne(processConfigId);
			if(ObjectHelper.isEmpty(processConfig)){
				logger.error("对象不存在，无法删除！");
				msg.setMsg("对象不存在，无法删除！");
				msg.setResultStatus(ResponseMsg.APP_ERROR);
				return msg;
			}
			processConfigService.logicDelete(processConfig);
		} catch (BusinessException e) {
			e.printStackTrace();
			logger.error("系统异常");
			msg.setMsg("系统异常！");
			msg.setResultStatus(ResponseMsg.APP_ERROR);
		}
		
		return msg;
	}
	
	private void buildCommonField(ProcessConfig processConfig) throws Exception{
		
		if(ObjectHelper.isEmpty(processConfig)){
			logger.error("对象为空");
			throw new BusinessException("对象为空");
		}
		
		// 创建、更新人员
		EmpDto empDto = CED.getLoginUser();
		if(ObjectHelper.isEmpty(empDto)){
			logger.error("获取平台资源失败，当前登录人为空");
			throw new BusinessException("获取平台资源失败，当前登录人为空");
		}
		if(ObjectHelper.isNotEmpty(processConfig.getId())){
			processConfig.setUpdateBy(empDto.getEmpCd());
			processConfig.setUpdateOrgCd(empDto.getOrgCd());
			processConfig.setUpdateTime(new Date());
		}else{
			processConfig.setCreateBy(empDto.getEmpCd());
			processConfig.setCreateOrgCd(empDto.getOrgCd());
			processConfig.setCreateTime(new Date());
		}
		
		// simplecode
		if(ObjectHelper.isNotEmpty(processConfig.getProcessFormCd())){
			processConfig.setProcessFormNm(CED.loadSimpleCodeNameByFullCode(processConfig.getProcessFormCd()));
		}
	}
}
