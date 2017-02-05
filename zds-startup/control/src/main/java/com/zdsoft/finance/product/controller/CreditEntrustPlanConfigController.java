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
import com.zdsoft.finance.product.entity.CreditEntrustPlanConfig;
import com.zdsoft.finance.product.entity.Product;
import com.zdsoft.finance.product.service.CreditEntrustPlanConfigService;
import com.zdsoft.finance.product.service.ProductService;
import com.zdsoft.finance.product.vo.CreditEntrustPlanConfigVo;
import com.zdsoft.finance.product.vo.ProductVo;
import com.zdsoft.framework.core.common.dto.ResponseMsg;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.PageRequest;
import com.zdsoft.framework.core.common.util.ObjectHelper;
import com.zdsoft.framework.core.commweb.annotation.UriKey;
import com.zdsoft.framework.core.commweb.component.BaseController;

/**
 * 资金计划配置控制器
 * @author longwei
 * @date 2017/01/17
 * @version 1.0
 */
@Controller
@RequestMapping("/creditEntrustPlanConfig")
public class CreditEntrustPlanConfigController extends BaseController {

	@Autowired
	private ProductService productService;
	
	@Autowired
	private CreditEntrustPlanConfigService creditEntrustPlanConfigService;
	
	@Autowired
	private CED CED;
	
	/**
	 * 资金计划配置页面
	 */
	@RequestMapping("/list")
	@UriKey(key="com.zdsoft.finance.creditEntrustPlanConfig.list")
	public ModelAndView list(String productId) throws BusinessException {
		ModelAndView  modelAndView=new ModelAndView("product/credit_entrust_plan_config_list");
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
	 * 对话框
	 */
	@RequestMapping("/dialog")
	@UriKey(key="com.zdsoft.finance.creditEntrustPlanConfig.dialog")
	public ModelAndView dialog(String productId,String creditEntrustPlanConfigId) throws BusinessException {
		ModelAndView modelAndView=new ModelAndView("product/credit_entrust_plan_config_dialog");
		if(ObjectHelper.isNotEmpty(creditEntrustPlanConfigId)){
			CreditEntrustPlanConfig creditEntrustPlanConfig=creditEntrustPlanConfigService.findOne(creditEntrustPlanConfigId);
			if(ObjectHelper.isEmpty(creditEntrustPlanConfig)){
				logger.error("资金计划配置不存在");
				throw new BusinessException("资金计划配置不存在");
			}
			modelAndView.addObject("creditEntrustPlanConfig", new CreditEntrustPlanConfigVo(creditEntrustPlanConfig));
		}
		modelAndView.addObject("productId", productId);
		return modelAndView;
	}
	
	/**
	 * 资金计划列表
	 */
	@ResponseBody
	@RequestMapping("/getList")
	@UriKey(key="com.zdsoft.finance.creditEntrustPlanConfig.getList")
	public ResponseMsg getList(CreditEntrustPlanConfigVo creditEntrustPlanConfigVo,PageRequest pageRequest){
		ResponseMsg msg=new ResponseMsg();
		CreditEntrustPlanConfig creditEntrustPlanConfig=creditEntrustPlanConfigVo.toPo();
		try {
			Page<CreditEntrustPlanConfig> page=creditEntrustPlanConfigService.findByPage(creditEntrustPlanConfig,pageRequest);
			List<CreditEntrustPlanConfigVo> list=new ArrayList<CreditEntrustPlanConfigVo>();
			for(CreditEntrustPlanConfig entrustPlanConfig:page.getRecords()){
				list.add(new CreditEntrustPlanConfigVo(entrustPlanConfig));
			}
			msg.setRows(list);
			msg.setTotal(page.getTotalRows());
			return msg;
		} catch (BusinessException e) {
			logger.error("查询列表失败");
			e.printStackTrace();
			msg.setResultStatus(ResponseMsg.APP_ERROR);
			msg.setMsg("查询列表失败");
		}
		return msg;
	}
	
	@ResponseBody
	@RequestMapping("/saveOrUpdate")
	@UriKey(key="com.zdsoft.finance.creditEntrustPlanConfig.saveOrUpdate")
	public ResponseMsg saveOrUpdate(CreditEntrustPlanConfigVo creditEntrustPlanConfigVo) {
		ResponseMsg msg=new ResponseMsg();
		CreditEntrustPlanConfig creditEntrustPlanConfig=creditEntrustPlanConfigVo.toPo();
		try {
			buildCommonField(creditEntrustPlanConfig);
			creditEntrustPlanConfigService.saveOrUpdate(creditEntrustPlanConfig);
		} catch (BusinessException e) {
			logger.error("保存或修改失败");
			e.printStackTrace();
			msg.setResultStatus(ResponseMsg.APP_ERROR);
			msg.setMsg("保存或修改失败");
		}
		return msg;
	}
	
	@ResponseBody
	@RequestMapping("/delete")
	@UriKey(key="com.zdsoft.finance.creditEntrustPlanConfig.delete")
	public ResponseMsg delete(String creditEntrustPlanConfigId) {
		ResponseMsg msg=new ResponseMsg();
		if(ObjectHelper.isEmpty(creditEntrustPlanConfigId)){
			logger.error("参数为空");
			msg.setResultStatus(ResponseMsg.APP_ERROR);
			msg.setMsg("参数为空");
			return msg;
		}
		
		try {
			creditEntrustPlanConfigService.delete(creditEntrustPlanConfigId);
		} catch (BusinessException e) {
			logger.error("删除错误",e);
			e.printStackTrace();
			msg.setResultStatus(ResponseMsg.APP_ERROR);
			msg.setMsg("删除错误");
		}
		
		return msg;
	}
	
	public void buildCommonField(CreditEntrustPlanConfig creditEntrustPlanConfig) throws BusinessException {
		try {
			EmpDto empDto = CED.getLoginUser();
			if(ObjectHelper.isEmpty(empDto)){
				logger.error("获取平台资源失败，未获取到当前登录人");
				throw new BusinessException("获取平台资源失败，未获取到当前登录人");
			}
			if(ObjectHelper.isEmpty(creditEntrustPlanConfig.getId())){
				creditEntrustPlanConfig.setCreateBy(empDto.getEmpCd());
				creditEntrustPlanConfig.setCreateOrgCd(empDto.getOrgCd());
				creditEntrustPlanConfig.setCreateTime(new Date());
			}else{
				creditEntrustPlanConfig.setUpdateBy(empDto.getEmpCd());
				creditEntrustPlanConfig.setUpdateOrgCd(empDto.getOrgCd());
				creditEntrustPlanConfig.setUpdateTime(new Date());
			}
		} catch (Exception e) {
			logger.error("获取平台资源失败，未获取到当前登录人",e);
			e.printStackTrace();
			throw new BusinessException("获取平台资源失败，未获取到当前登录人");
		}
	}
}
