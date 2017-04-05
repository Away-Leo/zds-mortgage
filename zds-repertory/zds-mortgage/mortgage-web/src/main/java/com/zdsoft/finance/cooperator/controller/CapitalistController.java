package com.zdsoft.finance.cooperator.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zdsoft.essential.client.service.CED;
import com.zdsoft.finance.common.base.QueryObj;
import com.zdsoft.finance.cooperator.entity.Capitalist;
import com.zdsoft.finance.cooperator.service.CapitalistService;
import com.zdsoft.finance.cooperator.service.CapitalistTrustService;
import com.zdsoft.finance.cooperator.service.CooperatorBankService;
import com.zdsoft.finance.cooperator.vo.CapitalistVo;
import com.zdsoft.finance.product.entity.Product;
import com.zdsoft.finance.product.service.ProductService;
import com.zdsoft.framework.core.common.dto.ResponseMsg;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.PageRequest;
import com.zdsoft.framework.core.common.util.DateHelper;
import com.zdsoft.framework.core.common.util.ObjectHelper;
import com.zdsoft.framework.core.commweb.annotation.UriKey;
import com.zdsoft.framework.core.commweb.component.BaseController;
import com.zdsoft.framework.cra.annotation.Menu;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * 
 * @Title: CapitalistController.java
 * @ClassName: CapitalistController
 * @Description: 资方Controller
 * @author liuwei
 * @date 2017年3月2日 下午2:29:49
 * @version V1.0
 */
@Controller
@RequestMapping("/capitalist")
public class CapitalistController extends BaseController {

	@Autowired
	CapitalistService capitalistService;
	@Autowired
	CapitalistTrustService capitalistTrustService;
	@Autowired
	CooperatorBankService cooperatorBankService;
	@Autowired
	CED CED;
	@Autowired
	private ProductService productService;
	/**
	 * 资方状态——启用
	 */
	public static final String CAPITALIST_STATUS = "YWDM0049001";

	/**
	 * 
	 * @Title: initContactsInfo
	 * @Description: 资方管理菜单组
	 * @author liuwei
	 * @return ModelAndView
	 */
	@RequestMapping("/initCapitalist")
	@UriKey(key = "com.zdsoft.finance.cooperator.capitalist.initCapitalist")
	@Menu(resource = "com.zdsoft.finance.cooperator.capitalist.initCapitalist", label = "资方管理", group = "cooperator", order = 3)
	public ModelAndView initContactsInfo() {
		ModelAndView model = new ModelAndView("/cooperator/capitalist_list");
		return model;
	}

	/**
	 * 
	 * @Title: capitalistSimpleCode
	 * @Description: 资方列表信息
	 * @author liuwei
	 * @param jsoncallback
	 * @return 资方列表json
	 */
	@RequestMapping("/capitalistSimpleCode")
	@UriKey(key = "com.zdsoft.finance.cooperator.capitalist.capitalistSimpleCode")
	@ResponseBody
	public String capitalistSimpleCode(String jsoncallback) {
		List<Capitalist> list = capitalistService.findListByStatus(CAPITALIST_STATUS);
		List<CapitalistVo> listVo = new ArrayList<CapitalistVo>();
		for (Capitalist info : list) {
			CapitalistVo vo = new CapitalistVo(info);
			listVo.add(vo);
		}
		return ObjectHelper.objectToJson(listVo, jsoncallback);
	}

	/**
	 * 
	 * @Title: capitalistOrgSimpleCode
	 * @Description: 返回根据部门的资方集合
	 * @author liuwei
	 * @param jsoncallback
	 * @param createOrgCd
	 *            部门编号
	 * @return 资方集合json
	 */
	@RequestMapping("/capitalistOrgSimpleCode")
	@UriKey(key = "com.zdsoft.finance.cooperator.capitalist.capitalistOrgSimpleCode")
	@ResponseBody
	public String capitalistOrgSimpleCode(String jsoncallback, String createOrgCd) {
		List<Capitalist> list = capitalistService.findListByStatusAndOrgCd(createOrgCd, CAPITALIST_STATUS);
		List<CapitalistVo> listVo = new ArrayList<CapitalistVo>();
		for (Capitalist info : list) {
			CapitalistVo vo = new CapitalistVo(info);
			listVo.add(vo);
		}
		return ObjectHelper.objectToJson(listVo, jsoncallback);
	}

	/**
	 * 
	 * @Title: findByProductSubtypeId
	 * @Description: 返回根据产品信息获取的资方集合
	 * @author jingjiyan
	 * @param productSubtypeId
	 *            子产品id
	 * @param jsoncallback
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/findByProductSubtypeId")
	@UriKey(key = "com.zdsoft.finance.cooperator.capitalist.findByProductSubtypeId")
	public String findByProductSubtypeId(String productSubtypeId, String jsoncallback) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		try {
			if (ObjectHelper.isNotEmpty(productSubtypeId)) {

				Product product = productService.findOne(productSubtypeId);
				if (ObjectHelper.isNotEmpty(product)) {
					Capitalist capitalist = capitalistService.findOne(product.getCapitalistId());
					if (ObjectHelper.isNotEmpty(capitalist)) {
						Map<String, Object> returnData = new HashMap<String, Object>();
						returnData.put("id", capitalist.getId());
						returnData.put("capitalName", capitalist.getCapitalName());
						returnData.put("isDefault", true);
						list.add(returnData);
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("通过产品信息获取的资方集合", e);
		}
		return ObjectHelper.objectToJson(list, jsoncallback);

	}

	/**
	 * 
	 * @Title: findByCapitalSourceId
	 * @Description: 返回根据产品信息获取的资方
	 * @author zhoushichao
	 * @param productSubtypeId
	 *            子产品id
	 * @param jsoncallback
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/findByCapitalSourceId")
	@UriKey(key = "com.zdsoft.finance.cooperator.capitalist.findByCapitalSourceId")
	public String findByCapitalSourceId(String productSubtypeId, String jsoncallback) {
		Map<String, Object> returnData = new HashMap<String, Object>();
		try {
			if (ObjectHelper.isNotEmpty(productSubtypeId)) {

				Product product = productService.findOne(productSubtypeId);
				if (ObjectHelper.isNotEmpty(product)) {
					Capitalist capitalist = capitalistService.findOne(product.getCapitalistId());
					if (ObjectHelper.isNotEmpty(capitalist)) {
						returnData.put("id", capitalist.getId());
						returnData.put("capitalName", capitalist.getCapitalName());
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("通过产品信息获取的资方集合", e);
		}
		return ObjectHelper.objectToJson(returnData, jsoncallback);

	}

	/**
	 * 
	 * @Title: tab
	 * @Description: 资方信息页签
	 * @author liuwei
	 * @param capitalistId
	 *            资方id
	 * @param operationType
	 *            操作类型
	 * @param capitalistType
	 *            资方类型
	 * @param cooperatorName
	 *            资方名称
	 * @return ModelAndView
	 */
	@RequestMapping("/tab")
	@UriKey(key = "com.zdsoft.finance.cooperator.capitalist.tab")
	public ModelAndView tab(String capitalistId, String operationType, String capitalistType, String cooperatorName) {
		ModelAndView modelAndView = new ModelAndView("/cooperator/capitalist_edit_tab");
		Capitalist ca = new Capitalist();
		if ("add".equals(operationType)) {
			ca.setCapitalName(cooperatorName);
			ca.setCapitalistType(capitalistType);
			ca = capitalistService.saveCapitalTemp(ca);
			modelAndView.addObject("capitalist", ca);
		} else {
			ca.setId(capitalistId);
			modelAndView.addObject("capitalist", ca);
		}
		modelAndView.addObject("operationType", operationType);
		return modelAndView;
	}

	@RequestMapping("/validateOnlyName")
	@UriKey(key = "com.zdsoft.finance.cooperator.capitalist.validateOnlyName")
	@ResponseBody
	public ResponseMsg validateOnlyName(String cooperatorName, String capitalistType) {
		ResponseMsg responseMsg = new ResponseMsg();
		Capitalist capitalist = capitalistService.findByCooperatorNameAndCapitalistType(cooperatorName, capitalistType);
		if (ObjectHelper.isNotEmpty(capitalist)) {
			responseMsg.setMsg("已有重复的资方,请检查后添加");
			responseMsg.setResultStatus(ResponseMsg.APP_ERROR);
		} else {
			responseMsg.setMsg("可以添加");
			responseMsg.setResultStatus(ResponseMsg.SUCCESS);
		}

		return responseMsg;

	}

	/**
	 * 
	 * @Title: getCapitalist
	 * @Description: 资方列表
	 * @author liuwei
	 * @param request
	 *            请求
	 * @param jsoncallback
	 * @param pageable
	 *            分页信息
	 * @return 处理信息json
	 */
	@RequestMapping("/getCapitalist")
	@UriKey(key = "com.zdsoft.finance.cooperator.capitalist.getCapitalist")
	@ResponseBody
	public String getCapitalist(HttpServletRequest request, String jsoncallback, PageRequest pageable) {

		// 获取页面封装的查询参数
		@SuppressWarnings("unchecked")
		List<QueryObj> queryObjs = (List<QueryObj>) request.getAttribute("listObj");
		// 分页查询资方信息
		Page<Capitalist> infos = capitalistService.findByHqlConditions(pageable, queryObjs);
		List<Capitalist> list = infos.getRecords();
		List<CapitalistVo> listVo = new ArrayList<CapitalistVo>();
		for (Capitalist info : list) {
			CapitalistVo vo = new CapitalistVo(info, null, new String[] { "capitalistType" });
			listVo.add(vo);
		}
		ResponseMsg msg = new ResponseMsg();
		msg.setMsg("列表查询成功");
		msg.setResultStatus(ResponseMsg.SUCCESS);
		msg.setTotal(infos.getTotalRows());
		msg.setRows(listVo);
		return ObjectHelper.objectToJson(msg, jsoncallback);
	}

	/**
	 * 
	 * @Title: capitalistEdit
	 * @Description: 联系人编辑
	 * @author liuwei
	 * @param capitalistId
	 *            资方id
	 * @param operationType
	 *            操作类型
	 * @return ModelAndView
	 */
	@RequestMapping("/edit")
	@UriKey(key = "com.zdsoft.finance.cooperator.capitalist.edit")
	public ModelAndView capitalistEdit(String capitalistId, String operationType) {
		ModelAndView modelAndView = null;
		try {
			Capitalist info = capitalistService.findOne(capitalistId);
			if (info.getCapitalistType().equals("YWDM0011201")) {
				// 银行
				modelAndView = new ModelAndView("/cooperator/capitalist_edit_bank");
			} else {
				// 非银
				modelAndView = new ModelAndView("/cooperator/capitalist_edit_nonsilver");
			}
			CapitalistVo capitalistVo = new CapitalistVo(info);
			modelAndView.addObject("capitalist", capitalistVo);
			modelAndView.addObject("capitalistId", capitalistId);
			modelAndView.addObject("operationType", operationType);
			modelAndView.addObject("maxDate", DateHelper.dateToString(new Date(), DateHelper.DATE_SHORT_FORMAT));
		} catch (Exception e) {
			e.printStackTrace();
		}

		return modelAndView;
	}

	/**
	 * 
	 * @Title: dialog
	 * @Description: 资方新增dialog
	 * @author liuwei
	 * @return ModelAndView
	 */
	@RequestMapping("/dialog")
	@UriKey(key = "com.zdsoft.finance.cooperator.capitalist.dialog")
	public ModelAndView dialog() {
		ModelAndView modelAndView = new ModelAndView("/cooperator/capitalist_dialog");
		return modelAndView;
	}

	/**
	 * 
	 * @Title: save
	 * @Description: 资方信息保存
	 * @author liuwei
	 * @param infoVo
	 *            资方信息
	 * @return msg处理信息json
	 */
	@RequestMapping("/save")
	@UriKey(key = "com.zdsoft.finance.cooperator.capitalist.save")
	@ResponseBody
	public String save(CapitalistVo infoVo) {
		ResponseMsg msg = new ResponseMsg();

		// 转换为资方Po
		Capitalist capitalist = infoVo.toPO();
		try {
			capitalist = capitalistService.saveOrUpdateCapitalist(capitalist);
			msg.setMsg("保存成功");
			msg.setResultStatus(ResponseMsg.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			msg.setMsg("保存失败");
			msg.setResultStatus(ResponseMsg.APP_ERROR);
		}
		return ObjectHelper.objectToJson(msg);
	}

	/**
	 * 
	 * @Title: del
	 * @Description: 资方信息逻辑删除
	 * @author liuwei
	 * @param jsoncallback
	 * @param id
	 *            资方id
	 * @return 处理消息json
	 */
	@RequestMapping("/del")
	@UriKey(key = "com.zdsoft.finance.cooperator.capitalist.del")
	@ResponseBody
	public String del(String jsoncallback, String id) {
		ResponseMsg msg = new ResponseMsg();
		try {
			capitalistService.logicDelete(id);
			msg.setMsg("操作成功！");
			msg.setResultStatus(ResponseMsg.SUCCESS);
		} catch (Exception e) {
			msg.setMsg("操作失败！" + e.getMessage());
			msg.setResultStatus(ResponseMsg.SYS_ERROR);
		}
		return ObjectHelper.objectToJson(msg);
	}
}
