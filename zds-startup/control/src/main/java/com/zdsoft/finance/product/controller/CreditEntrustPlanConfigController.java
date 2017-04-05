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
 * 版权所有：重庆正大华日软件有限公司
 * @Title: CreditEntrustPlanConfigController.java 
 * @ClassName: CreditEntrustPlanConfigController 
 * @Description: 资金计划配置
 * @author gufeng 
 * @date 2017年3月6日 下午8:22:32 
 * @version V1.0
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
	 * @Title: list 
	 * @Description: 入口
	 * @author gufeng 
	 * @param productId 产品id
	 * @return 资金计划配置页面
	 * @throws BusinessException
	 */
	@RequestMapping("/list")
	@UriKey(key="com.zdsoft.finance.creditEntrustPlanConfig.list")
	public ModelAndView list(String productId) {
		ModelAndView  modelAndView=new ModelAndView("product/credit_entrust_plan_config_list");
		if(ObjectHelper.isEmpty(productId)){
			logger.error("参数异常");
		}
		Product product = null;
		try {
			product = productService.findOne(productId);
		} catch (BusinessException e) {
			logger.error("查询出错",e);
			e.printStackTrace();
		}
		if(ObjectHelper.isEmpty(product)){
			logger.error("主产品已不存在，请确认是否已删除");
		}
		modelAndView.addObject("product", new ProductVo(product));
		return modelAndView;
	}
	
	/**
	 * @Title: dialog 
	 * @Description: 对话框
	 * @author gufeng 
	 * @param productId 产品id
	 * @param creditEntrustPlanConfigId 资金计划id
	 * @return dialog页面
	 */
	@RequestMapping("/dialog")
	@UriKey(key="com.zdsoft.finance.creditEntrustPlanConfig.dialog")
	public ModelAndView dialog(String productId,String creditEntrustPlanConfigId){
		ModelAndView modelAndView=new ModelAndView("product/credit_entrust_plan_config_dialog");
		if(ObjectHelper.isNotEmpty(creditEntrustPlanConfigId)){
			CreditEntrustPlanConfig creditEntrustPlanConfig = null;
			try {
				creditEntrustPlanConfig = creditEntrustPlanConfigService.findOne(creditEntrustPlanConfigId);
			} catch (BusinessException e) {
				logger.error("查询出错",e);
				e.printStackTrace();
			}
			if(ObjectHelper.isEmpty(creditEntrustPlanConfig)){
				logger.error("资金计划配置不存在");
			}
			modelAndView.addObject("creditEntrustPlanConfig", new CreditEntrustPlanConfigVo(creditEntrustPlanConfig));
		}
		try {
			Product product = productService.findOne(productId);
			ProductVo vo = new ProductVo(product);
			modelAndView.addObject("product", vo);
		} catch (BusinessException e) {
			logger.error("查询出错",e);
			e.printStackTrace();
		}
		modelAndView.addObject("productId", productId);
		return modelAndView;
	}
	
	/**
	 * @Title: getList 
	 * @Description: 资金计划列表
	 * @author gufeng 
	 * @param creditEntrustPlanConfigVo 查询条件
	 * @param pageRequest 分页
	 * @return 分页数据
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
			logger.error("查询列表失败",e);
			e.printStackTrace();
			msg.setResultStatus(ResponseMsg.APP_ERROR);
			msg.setMsg("查询列表失败");
		}
		return msg;
	}
	
	/**
	 * @Title: saveOrUpdate 
	 * @Description: 保存或更新
	 * @author gufeng 
	 * @param creditEntrustPlanConfigVo 保存数据
	 * @return 保存结果
	 */
	@ResponseBody
	@RequestMapping("/saveOrUpdate")
	@UriKey(key="com.zdsoft.finance.creditEntrustPlanConfig.saveOrUpdate")
	public ResponseMsg saveOrUpdate(CreditEntrustPlanConfigVo creditEntrustPlanConfigVo) {
		ResponseMsg msg=new ResponseMsg();
		CreditEntrustPlanConfig creditEntrustPlanConfig = creditEntrustPlanConfigVo.toPo();
		try {
			buildCommonField(creditEntrustPlanConfig);
			creditEntrustPlanConfigService.saveOrUpdate(creditEntrustPlanConfig);
		} catch (BusinessException e) {
			logger.error("保存或修改失败",e);
			e.printStackTrace();
			msg.setResultStatus(ResponseMsg.APP_ERROR);
			msg.setMsg(e.getExceptionMessage());
		}
		return msg;
	}
	
	/**
	 * @Title: delete 
	 * @Description: 删除
	 * @author gufeng 
	 * @param creditEntrustPlanConfigId 资金计划id
	 * @return 删除结果
	 */
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
	
	/**
	 * @Title: buildCommonField 
	 * @Description: 数据配置
	 * @author gufeng 
	 * @param creditEntrustPlanConfig 资金计划
	 * @throws BusinessException 数据出错
	 */
	private void buildCommonField(CreditEntrustPlanConfig creditEntrustPlanConfig) throws BusinessException {
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
