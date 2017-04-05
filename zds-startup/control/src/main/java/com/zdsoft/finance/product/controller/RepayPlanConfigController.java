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
import com.zdsoft.finance.product.entity.Product;
import com.zdsoft.finance.product.entity.RepayPlanConfig;
import com.zdsoft.finance.product.service.ProductService;
import com.zdsoft.finance.product.service.RepayPlanConfigService;
import com.zdsoft.finance.product.vo.ProductVo;
import com.zdsoft.finance.product.vo.RepayPlanConfigVo;
import com.zdsoft.framework.core.common.dto.ResponseMsg;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.PageRequest;
import com.zdsoft.framework.core.common.util.ObjectHelper;
import com.zdsoft.framework.core.commweb.annotation.UriKey;
import com.zdsoft.framework.core.commweb.component.BaseController;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title: RepayPlanConfigController.java 
 * @ClassName: RepayPlanConfigController 
 * @Description: 还款计划配置控制器
 * @author gufeng 
 * @date 2017年3月6日 下午5:01:22 
 * @version V1.0
 */
@Controller
@RequestMapping("/repayPlanConfig")
public class RepayPlanConfigController extends BaseController{

	@Autowired
	private ProductService productService;
	
	@Autowired
	private RepayPlanConfigService repayPlanConfigService;
	
	@Autowired
	private CED CED;
	
	/**
	 * @Title: list 
	 * @Description: 审批意见配置入口
	 * @author gufeng 
	 * @param productId 产品id
	 * @return 配置页面
	 */
	@RequestMapping("/list")
	@UriKey(key="com.zdsoft.finance.repayPlanConfig.list")
	public ModelAndView list(String productId){
		ModelAndView modelAndView=new ModelAndView("product/repay_plan_config_list");
		if(ObjectHelper.isEmpty(productId)){
			logger.error("参数异常");
		}
		Product product = null;
		try {
			product = productService.findOne(productId);
		} catch (BusinessException e) {
			logger.error("未找到产品",e);
			e.printStackTrace();
		}
		if(ObjectHelper.isEmpty(product)){
			logger.error("主产品已不存在，请确认是否已删除");
		}
		modelAndView.addObject("product", new ProductVo(product));
		return modelAndView;
	}
	
	/**
	 * @Title: getList 
	 * @Description: 获取列表
	 * @author gufeng 
	 * @param repayPlanConfigVo 条件
	 * @param pageable 分页
	 * @return 分页数据
	 */
	@ResponseBody
	@RequestMapping("/getList")
	@UriKey(key="com.zdsoft.finance.repayPlanConfig.getList")
	public ResponseMsg getList(RepayPlanConfigVo repayPlanConfigVo,PageRequest pageable) {
		ResponseMsg msg=new ResponseMsg();
		RepayPlanConfig repayPlanConfig = repayPlanConfigVo.toPo();
	
		try {
			Page<RepayPlanConfig> page=repayPlanConfigService.findPage(repayPlanConfig, pageable);
			List<RepayPlanConfigVo> list=new ArrayList<RepayPlanConfigVo>();
			for(RepayPlanConfig queryRepayPlanConfig:page.getRecords()){
				list.add(new RepayPlanConfigVo(queryRepayPlanConfig));
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
	 * @param repayPlanConfigId 还款计划配置id
	 * @return dialog页
	 */
	@RequestMapping("/dialog")
	@UriKey(key="com.zdsoft.finance.repayPlanConfig.dialog")
	public ModelAndView dialog(String productId,String repayPlanConfigId){
		ModelAndView modelAndView=new ModelAndView("product/repay_plan_config_dialog");
		if(ObjectHelper.isNotEmpty(repayPlanConfigId)){
			RepayPlanConfig repayPlanConfig = null;
			try {
				repayPlanConfig = repayPlanConfigService.findOne(repayPlanConfigId);
			} catch (BusinessException e) {
				logger.error("还款计划配置查询出错，", e);
				e.printStackTrace();
			}
			if(ObjectHelper.isEmpty(repayPlanConfig)){
				logger.error("还款计划配置不存在");
			}
			modelAndView.addObject("repayPlanConfig", new RepayPlanConfigVo(repayPlanConfig));
		}
		modelAndView.addObject("productId", productId);
		return modelAndView;
	}
	
	/**
	 * @Title: saveOrUpdate 
	 * @Description: 添加或修改审批意见
	 * @author gufeng 
	 * @param repayPlanConfigVo 操作保存数据
	 * @return 保存结果
	 */
	@ResponseBody
	@RequestMapping("/saveOrUpdate")
	@UriKey(key="com.zdsoft.finance.repayPlanConfig.saveOrUpdate")
	public ResponseMsg saveOrUpdate(RepayPlanConfigVo repayPlanConfigVo){
		ResponseMsg msg=new ResponseMsg();
		RepayPlanConfig repayPlanConfig = repayPlanConfigVo.toPo();
		try{
			// 构建公共字段
			buildCommonField(repayPlanConfig);
			repayPlanConfigService.saveOrUpdate(repayPlanConfig);
		}catch (BusinessException e) {
			logger.error("更新失败",e);
			msg.setResultStatus(ResponseMsg.APP_ERROR);
			msg.setMsg(e.getExceptionMessage());
		}
		return msg;
	}
	
	/**
	 * @Title: delete 
	 * @Description: 删除
	 * @author gufeng 
	 * @param repayPlanConfigId 配置id
	 * @return 删除结果
	 */
	@ResponseBody
	@RequestMapping("/delete")
	@UriKey(key="com.zdsoft.finance.repayPlanConfig.delete")
	public ResponseMsg delete(String repayPlanConfigId){
		ResponseMsg msg=new ResponseMsg();
		if(ObjectHelper.isEmpty(repayPlanConfigId)){
			logger.error("参数为空");
			msg.setMsg("参数为空");
			msg.setResultStatus(ResponseMsg.APP_ERROR);
		}
		try {
			RepayPlanConfig repayPlanConfig = repayPlanConfigService.findOne(repayPlanConfigId);
			if(ObjectHelper.isEmpty(repayPlanConfig)){
				logger.error("对象不存在，无法删除！");
				msg.setMsg("对象不存在，无法删除！");
				msg.setResultStatus(ResponseMsg.APP_ERROR);
			}
			repayPlanConfigService.logicDelete(repayPlanConfig);
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
	 * @Description: 构建对象
	 * @author gufeng 
	 * @param repayPlanConfig 原还款假话配置
	 * @throws Exception 构建出错
	 */
	private void buildCommonField(RepayPlanConfig repayPlanConfig) throws BusinessException{
		if(ObjectHelper.isEmpty(repayPlanConfig)){
			logger.error("对象为空");
			throw new BusinessException("对象为空");
		}
		// 创建、更新人员
		EmpDto empDto = null;
		try {
			empDto = CED.getLoginUser();
		} catch (Exception e) {
			e.printStackTrace();
			throw new BusinessException("CED获取出错");
		}
		if(ObjectHelper.isEmpty(empDto)){
			logger.error("获取平台资源失败，当前登录人为空");
			throw new BusinessException("获取平台资源失败，当前登录人为空");
		}
		if(ObjectHelper.isNotEmpty(repayPlanConfig.getId())){
			repayPlanConfig.setUpdateBy(empDto.getEmpCd());
			repayPlanConfig.setUpdateOrgCd(empDto.getOrgCd());
			repayPlanConfig.setUpdateTime(new Date());
		}else{
			repayPlanConfig.setCreateBy(empDto.getEmpCd());
			repayPlanConfig.setCreateOrgCd(empDto.getOrgCd());
			repayPlanConfig.setCreateTime(new Date());
		}
		// simplecode
		if(ObjectHelper.isNotEmpty(repayPlanConfig.getFeeCode())){
			try {
				repayPlanConfig.setFeeName(CED.loadSimpleCodeNameByFullCode(repayPlanConfig.getFeeCode()));
			} catch (Exception e) {
				e.printStackTrace();
				throw new BusinessException("CED获取出错");
			}
		}
		if(ObjectHelper.isNotEmpty(repayPlanConfig.getReceiverId())){
			try {
				repayPlanConfig.setReceiverName(CED.loadSimpleCodeNameByFullCode(repayPlanConfig.getReceiverId()));
			} catch (Exception e) {
				e.printStackTrace();
				throw new BusinessException("CED获取出错");
			}
		}
	}
}
