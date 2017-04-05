package com.zdsoft.finance.app.usercenter.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zdsoft.finance.app.usercenter.MyBusiInfoDto;
import com.zdsoft.finance.app.usercenter.service.MyBusiService;
import com.zdsoft.finance.app.usercenter.vo.MyBusiInfoVo;
import com.zdsoft.finance.busiform.entity.BusiForm;
import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.finance.common.utils.AppStatus;
import com.zdsoft.finance.common.utils.app.AppServerUtil;
import com.zdsoft.framework.core.common.annotation.Log;
import com.zdsoft.framework.core.common.exception.AppException;
import com.zdsoft.framework.core.common.page.PageRequest;
import com.zdsoft.framework.core.common.util.ObjectHelper;
import com.zdsoft.framework.core.commweb.component.BaseController;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title:MyBusinessController.java
 * @Package:com.zdsoft.finance.app.flow.controller
 * @Description:我的业务Controller
 * @author: jingyh
 * @date:2017年1月13日 下午5:33:17
 * @version:v1.0
 */
@Controller
@RequestMapping("/server/bizCenter/loanApplication")
public class MyBusinessController extends BaseController {

	@Log
	private Logger log;
	
	@Autowired
	private MyBusiService myBusiService;
	
	/**
	 * 
	 * 我的申请信息
	 *
	 * @author jingyh
	 * @param req
	 * @param resp
	 * @return
	 */
	@RequestMapping("/myApplication")
	@ResponseBody
	public String myApplication(HttpServletRequest req, HttpServletResponse resp) {
		Integer pageIndex = AppServerUtil.DefaultPageIndex;
        Integer pageSize = AppServerUtil.DefaultPageSize;
        if (ObjectHelper.isNotEmpty(req.getParameter("pageIndex"))) {
        	pageIndex = new Integer(req.getParameter("pageIndex"));
        }
        if (ObjectHelper.isNotEmpty(req.getParameter("pageSize"))) {
        	pageSize = new Integer(req.getParameter("pageSize"));
        }
        PageRequest pageInfo = new PageRequest(pageIndex, pageSize);
        log.debug("分页参数为，页号：{},分页大小：{}",pageIndex, pageSize);
        Map<String, Object> params = new HashMap<String,Object>();
        params.put("keyWord", req.getParameter("keyword"));
        params.put("formStatus", req.getParameter("formStatus"));
        log.debug("查询条件为：{}", params);
        try {
        	// 查询数据
        	List<MyBusiInfoDto> listResult = this.myBusiService.findMyBusiPageInfo(params, pageInfo);
        	// 封装数据
        	List<MyBusiInfoVo> result = new ArrayList<MyBusiInfoVo>();
        	for (MyBusiInfoDto info : listResult) {
        		result.add(new MyBusiInfoVo(info));
        	}
        	log.debug("查询结果记录大小为：{}", result.size());
            return AppServerUtil.buildJsonList(result);
        } catch (AppException e) {
        	log.error("查询数据报错：{}", e);
            e.printStackTrace();
            return AppServerUtil.buildError(AppStatus.SystemError,e);
        } catch (Exception e) {
        	log.error("查询数据报错：{}", e);
            e.printStackTrace();
            return AppServerUtil.buildError(AppStatus.SystemError,e);
        }
	}
	
	/**
	 * 
	 * 启动流程案件申请流程
	 *
	 * @author jingyh
	 * @param req
	 * @param resp
	 * @return
	 */
	@RequestMapping("/feedback")
	@ResponseBody
	public String startCaseAapplyProcess(HttpServletRequest req, HttpServletResponse resp, String caseApplyId) {
		log.info("移动端发起案件申请流程！");
		log.debug("申请Id:{}", caseApplyId);
		try {
			Map<String,String> map = new HashMap<String,String>();
			BusiForm busiForm = this.myBusiService.startCaseApplyMainProcess(caseApplyId);
			log.debug("当前节点处理人：{}", busiForm.getCurrentDealEmpNm());
			// 下一节点处理人
			map.put("feedback", busiForm.getCurrentDealEmpNm());
			if (ObjectHelper.isNotEmpty(busiForm.getHadRulesRefuse()) && busiForm.getHadRulesRefuse()) {
				// 状态为规则拒绝
				return AppServerUtil.buildError(AppStatus.BlackListError);
			}
			return AppServerUtil.buildJsonObject(map);
		} catch (Exception e) {
			log.error("启动流程失败：{}",e);
			e.printStackTrace();
			if (e instanceof BusinessException) {
				BusinessException busiE = (BusinessException)e;
				if ("10010012".equals(busiE.getExceptionCode())) {
					// 黑名单异常
					return AppServerUtil.buildError(AppStatus.BlackListError,busiE.getExceptionMessage());
				}
				// 业务异常
				return AppServerUtil.buildError(AppStatus.LogicError,busiE.getExceptionMessage());
			} else {
				// 服务器系统异常
				return AppServerUtil.buildError(AppStatus.SystemError,e);
			}
		}
	}
}
