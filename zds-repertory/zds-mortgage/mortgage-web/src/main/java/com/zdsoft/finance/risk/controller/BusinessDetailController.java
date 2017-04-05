package com.zdsoft.finance.risk.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zdsoft.finance.risk.huifa.service.BusinessDetailService;
import com.zdsoft.finance.risk.huifa.service.bo.BusinessDetailBo;
import com.zdsoft.finance.risk.vo.BusinessDetailVo;
import com.zdsoft.framework.core.commweb.annotation.UriKey;
import com.zdsoft.framework.core.commweb.component.BaseController;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title: HuifaDetailController.java 
 * @ClassName: HuifaDetailController 
 * @Description: 风险信息-汇法网明细数据控制器
 * @author panshm 
 * @date 2017年2月18日 下午2:45:59 
 * @version V1.0
 */
@Controller
@RequestMapping("/businessDetail")
public class BusinessDetailController extends BaseController {

	@Autowired
	private BusinessDetailService businessDetailService;
	
	/**
	 * @Title: findByOrderId 
	 * @Description: 根据订单号取得工商详情信息
	 * @author panshm 
	 * @param orderId 订单id
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/findByOrderId")
	@UriKey(key="com.zdsoft.finance.risk.findByOrderId")
	//@Menu(resource = "com.zdsoft.finance.risk.findByOrderId", label = "工商详情", group = "project", order = 3)
	public ModelAndView findByOrderId(String orderId){
		BusinessDetailVo detailVo = new BusinessDetailVo();
		ModelMap modelMap = new ModelMap();
		try {
			// 获取工商信息
			BusinessDetailBo detailBo = businessDetailService.findByOrderId(orderId);
			BeanUtils.copyProperties(detailBo, detailVo);
			modelMap.put("detailVo", detailVo);
		} catch (Exception e) {
			logger.error("通过订单id查询工商数据", e);
		}
		return new ModelAndView("risk/business_detail",modelMap);
	}

}
