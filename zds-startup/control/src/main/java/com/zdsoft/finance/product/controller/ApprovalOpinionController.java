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
import com.zdsoft.finance.product.entity.ApprovalOpinion;
import com.zdsoft.finance.product.entity.Product;
import com.zdsoft.finance.product.service.ApprovalOpinionService;
import com.zdsoft.finance.product.service.ProductService;
import com.zdsoft.finance.product.vo.ApprovalOpinionVo;
import com.zdsoft.finance.product.vo.ProductVo;
import com.zdsoft.framework.core.common.dto.ResponseMsg;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.PageRequest;
import com.zdsoft.framework.core.common.util.ObjectHelper;
import com.zdsoft.framework.core.commweb.annotation.UriKey;
import com.zdsoft.framework.core.commweb.component.BaseController;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title: ApprovalOpinionController.java 
 * @ClassName: ApprovalOpinionController 
 * @Description: 产品审批意见
 * @author gufeng 
 * @date 2017年3月6日 下午6:07:18 
 * @version V1.0
 */
@Controller
@RequestMapping("/approvalOpinion")
public class ApprovalOpinionController extends BaseController{

	@Autowired
	private ProductService productService;
	
	@Autowired
	private ApprovalOpinionService approvalOpinionService;
	
	@Autowired
	private CED CED;
	
	/**
	 * @Title: list 
	 * @Description: 入口
	 * @author gufeng 
	 * @param productId 产品id
	 * @return 审批意见页
	 */
	@RequestMapping("/list")
	@UriKey(key="com.zdsoft.finance.approvalOpinion.list")
	public ModelAndView list(String productId){
		ModelAndView modelAndView=new ModelAndView("product/approval_opinion_list");
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
	 * @Title: getList 
	 * @Description: 获取列表
	 * @author gufeng 
	 * @param approvalOpinionVo 条件
	 * @param pageable 分页
	 * @return 分页数据
	 */
	@ResponseBody
	@RequestMapping("/getList")
	@UriKey(key="com.zdsoft.finance.approvalOpinion.getList")
	public ResponseMsg getList(ApprovalOpinionVo approvalOpinionVo,PageRequest pageable) {
		ResponseMsg msg=new ResponseMsg();
		ApprovalOpinion approvalOpinion = approvalOpinionVo.toPo();
		
		try {
			Page<ApprovalOpinion> page=approvalOpinionService.findPage(approvalOpinion, pageable);
			List<ApprovalOpinionVo> list=new ArrayList<ApprovalOpinionVo>();
			for(ApprovalOpinion queryApprovalOpinion:page.getRecords()){
				list.add(new ApprovalOpinionVo(queryApprovalOpinion));
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
	 * @param approvalOpinionId 审批意见id
	 * @return dialog页
	 */
	@RequestMapping("/dialog")
	@UriKey(key="com.zdsoft.finance.approvalOpinion.dialog")
	public ModelAndView dialog(String productId,String approvalOpinionId){
		ModelAndView modelAndView=new ModelAndView("product/approval_opinion_dialog");
		if(ObjectHelper.isNotEmpty(approvalOpinionId)){
			ApprovalOpinion approvalOpinion = null;
			try {
				approvalOpinion = approvalOpinionService.findOne(approvalOpinionId);
			} catch (BusinessException e) {
				logger.error("查询出错", e);
				e.printStackTrace();
			}
			if(ObjectHelper.isEmpty(approvalOpinion)){
				logger.error("审批意见不存在");
			}
			modelAndView.addObject("approvalOpinion", new ApprovalOpinionVo(approvalOpinion));
		}
		modelAndView.addObject("productId", productId);
		return modelAndView;
	}
	
	/**
	 * @Title: saveOrUpdate 
	 * @Description: 添加或修改审批意见
	 * @author gufeng 
	 * @param approvalOpinionVo 数据
	 * @return 保存结果
	 */
	@ResponseBody
	@RequestMapping("/saveOrUpdate")
	@UriKey(key="com.zdsoft.finance.approvalOpinion.saveOrUpdate")
	public ResponseMsg saveOrUpdate(ApprovalOpinionVo approvalOpinionVo){
		ResponseMsg msg=new ResponseMsg();
		ApprovalOpinion approvalOpinion = approvalOpinionVo.toPo();
		try{
			// 构建公共字段
			buildCommonField(approvalOpinion);
			approvalOpinionService.saveOrUpdate(approvalOpinion);
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
	 * @param approvalOpinionId 审批意见id
	 * @return 删除结果
	 */
	@ResponseBody
	@RequestMapping("/delete")
	@UriKey(key="com.zdsoft.finance.approvalOpinion.delete")
	public ResponseMsg delete(String approvalOpinionId){
		ResponseMsg msg=new ResponseMsg();
		if(ObjectHelper.isEmpty(approvalOpinionId)){
			logger.error("参数为空");
			msg.setMsg("参数为空");
			msg.setResultStatus(ResponseMsg.APP_ERROR);
		}
		try {
			ApprovalOpinion approvalOpinion = approvalOpinionService.findOne(approvalOpinionId);
			if(ObjectHelper.isEmpty(approvalOpinion)){
				logger.error("对象不存在，无法删除！");
				msg.setMsg("对象不存在，无法删除！");
				msg.setResultStatus(ResponseMsg.APP_ERROR);
			}
			approvalOpinionService.logicDelete(approvalOpinion);
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
	 * @Description: 构建数据
	 * @author gufeng 
	 * @param approvalOpinion 数据
	 * @throws Exception 构建异常
	 */
	private void buildCommonField(ApprovalOpinion approvalOpinion) throws Exception{
		
		if(ObjectHelper.isEmpty(approvalOpinion)){
			logger.error("对象为空");
			throw new BusinessException("对象为空");
		}
		
		// 创建、更新人员
		EmpDto empDto = CED.getLoginUser();
		if(ObjectHelper.isEmpty(empDto)){
			logger.error("获取平台资源失败，当前登录人为空");
			throw new BusinessException("获取平台资源失败，当前登录人为空");
		}
		if(ObjectHelper.isNotEmpty(approvalOpinion.getId())){
			approvalOpinion.setUpdateBy(empDto.getEmpCd());
			approvalOpinion.setUpdateOrgCd(empDto.getOrgCd());
			approvalOpinion.setUpdateTime(new Date());
		}else{
			approvalOpinion.setCreateBy(empDto.getEmpCd());
			approvalOpinion.setCreateOrgCd(empDto.getOrgCd());
			approvalOpinion.setCreateTime(new Date());
		}
		
		// simplecode
		if(ObjectHelper.isNotEmpty(approvalOpinion.getApprovalType())){
			approvalOpinion.setApprovalTypeName(CED.loadSimpleCodeNameByFullCode(approvalOpinion.getApprovalType()));
		}
		if(ObjectHelper.isNotEmpty(approvalOpinion.getMortgageOrder())){
			approvalOpinion.setMortgageOrderName(CED.loadSimpleCodeNameByFullCode(approvalOpinion.getMortgageOrder()));
		}
	}
}
