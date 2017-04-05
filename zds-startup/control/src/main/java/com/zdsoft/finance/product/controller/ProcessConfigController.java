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
import com.zdsoft.finance.common.exception.CodeException;
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
 * 版权所有：重庆正大华日软件有限公司
 * @Title: ProcessConfigController.java 
 * @ClassName: ProcessConfigController 
 * @Description: 流程配置控制器
 * @author longwei 
 * @date 2017年2月6日 上午11:04:29 
 * @version V1.0
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
	 * @Title: list 
	 * @Description: 审批意见配置页面
	 * @author gufeng 
	 * @param productId 产品id
	 * @return 流程配置页
	 */
	@RequestMapping("/list")
	@UriKey(key="com.zdsoft.finance.processConfig.list")
	public ModelAndView list(String productId){
		ModelAndView modelAndView=new ModelAndView("product/process_config_list");
		if(ObjectHelper.isEmpty(productId)){
			logger.error("参数异常");
		}
		
		Product product = null;
		try {
			product = productService.findOne(productId);
			modelAndView.addObject("product", new ProductVo(product));
		} catch (BusinessException e) {
			logger.error("主产品已不存在，请确认是否已删除");
			e.printStackTrace();
		}
		return modelAndView;
	}
	
	/**
	 * @Title: getList 
	 * @Description: 获取列表
	 * @author gufeng 
	 * @param processConfigVo 条件
	 * @param pageable 分页
	 * @return 分页数据
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
	 * @Title: dialog 
	 * @Description: 审批意见对话框
	 * @author gufeng 
	 * @param productId 产品id
	 * @param processConfigId 流程配置id
	 * @return dialog页面
	 */
	@RequestMapping("/dialog")
	@UriKey(key="com.zdsoft.finance.processConfig.dialog")
	public ModelAndView dialog(String productId,String processConfigId){
		ModelAndView modelAndView=new ModelAndView("product/process_config_dialog");
		if(ObjectHelper.isNotEmpty(processConfigId)){
			try {
				ProcessConfig processConfig = processConfigService.findOne(processConfigId);
				modelAndView.addObject("processConfig", new ProcessConfigVo(processConfig));
			} catch (BusinessException e) {
				logger.error("该流程不存在");
			}
		}
		modelAndView.addObject("productId", productId);
		return modelAndView;
	}
	
	/**
	 * @Title: saveOrUpdate 
	 * @Description: 添加或修改审批意见
	 * @author gufeng 
	 * @param processConfigVo 数据
	 * @return 修改信息
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
		}catch (CodeException e) {
			logger.error("更新失败",e);
			msg.setResultStatus(ResponseMsg.APP_ERROR);
			msg.setMsg("操作失败，代码重复！");
		}catch (Exception e) {
			logger.error("更新失败",e);
			msg.setResultStatus(ResponseMsg.APP_ERROR);
			msg.setMsg("更新失败");
		}
		
		return msg;
	}
	
	/**
	 * @Title: delete 
	 * @Description: 删除
	 * @author gufeng 
	 * @param processConfigId 流程配置id
	 * @return 删除结果
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
	
	/**
	 * @Title: buildCommonField 
	 * @Description: 数据组装
	 * @author gufeng 
	 * @param processConfig 配置信息
	 * @throws Exception 组装异常
	 */
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
	}
}
