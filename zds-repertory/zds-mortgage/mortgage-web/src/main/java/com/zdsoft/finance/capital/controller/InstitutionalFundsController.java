package com.zdsoft.finance.capital.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zdsoft.essential.client.service.CED;
import com.zdsoft.essential.dto.emp.EmpDto;
import com.zdsoft.essential.dto.org.OrgDto;
import com.zdsoft.finance.capital.entity.InstitutionFunds;
import com.zdsoft.finance.capital.service.InstitutionFundsService;
import com.zdsoft.finance.capital.vo.InstitutionFundsVo;
import com.zdsoft.finance.common.base.QueryObj;
import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.finance.cooperator.entity.Capitalist;
import com.zdsoft.finance.cooperator.service.CapitalistService;
import com.zdsoft.finance.cooperator.vo.CapitalistVo;
import com.zdsoft.framework.core.common.dto.ResponseMsg;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.PageRequest;
import com.zdsoft.framework.core.common.util.DateHelper;
import com.zdsoft.framework.core.common.util.ObjectHelper;
import com.zdsoft.framework.core.commweb.annotation.UriKey;
import com.zdsoft.framework.core.commweb.component.BaseController;
import com.zdsoft.framework.cra.annotation.Menu;
import com.zdsoft.framework.cra.annotation.Reference;

/**
 * 机构资金计划分配Controller
 * 
 * @createTime:2017年1月10日
 * @author liuwei
 * @version 1.0
 */
@Controller
@RequestMapping("/institutionalFunds")
public class InstitutionalFundsController extends BaseController {

	@Autowired
	CapitalistService capitalistService;

	@Autowired
	CED CED;

	@Autowired
	InstitutionFundsService institutionFundsService;

	@RequestMapping("/initInstitutionalFunds")
	@UriKey(key = "com.zdsoft.finance.capital.initInstitutionalFunds")
	@Menu(resource = "com.zdsoft.finance.capital.initInstitutionalFunds", label = "机构资金计划分配", group = "capital", order = 3)
	public ModelAndView initInstitutionalFunds() {

		// 获取组织机构信息，得到所有分公司
		List<OrgDto> organizations = null;
		try {
			organizations = CED.queryAllCompany();
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("获取机构失败", e);
		}
		List<InstitutionFunds> fundsList = institutionFundsService.findAll();
		checkInstitutionFunds(fundsList, organizations);

		return new ModelAndView("/capital/instritutional_funds_list");
	}

	/**
	 * 查询列表
	 * 
	 * @param request
	 *            请求
	 * @param jsoncallback
	 * @param pageable
	 * @return
	 */
	@RequestMapping("/getInstitutionFunds")
	@UriKey(key = "com.zdsoft.finance.capital.getInstitutionFunds")
	@ResponseBody
	public String getInstitutionFunds(HttpServletRequest request, String jsoncallback, PageRequest pageable) {

		// 获取查询参数
		@SuppressWarnings("unchecked")
		List<QueryObj> queryObjs = (List<QueryObj>) request.getAttribute("listObj");

		// 获取资方机构配置信息
		Page<InstitutionFunds> fundsPage = institutionFundsService.findByHqlConditions(pageable, queryObjs);
		List<InstitutionFundsVo> fundsVo = new ArrayList<InstitutionFundsVo>();

		for (InstitutionFunds funds : fundsPage.getRecords()) {
			InstitutionFundsVo fundVo = new InstitutionFundsVo(funds);

			fundsVo.add(fundVo);
		}

		ResponseMsg msg = new ResponseMsg();
		msg.setMsg("列表查询成功");
		msg.setResultStatus(ResponseMsg.SUCCESS);
		msg.setTotal(fundsPage.getTotalRows());
		msg.setRows(fundsVo);

		return ObjectHelper.objectToJson(msg, jsoncallback);
	}

	/**
	 * 判断资方机构配置信息与分公司信息是否对等
	 * 
	 * @param fundsPage
	 *            资方机构配置信息
	 * @param organizations
	 * @return
	 */
	private void checkInstitutionFunds(List<InstitutionFunds> fundsList, List<OrgDto> organizations) {

		List<OrgDto> branchOrgs = new ArrayList<OrgDto>();
		for (OrgDto orgDto : organizations) {
			// 判断所属分公司机构
			if ("1".equals(orgDto.getOrgType())) {
				branchOrgs.add(orgDto);
			}
		}
		try {
			if (fundsList.size() == 0L) { // 如果资方机构配置信息为空，则新增资方机构配置信息

				institutionFundsService.saveInstitutionFundsList(branchOrgs);

			} else if (fundsList.size() != organizations.size()) { // 如果资方机构配置信息与分公司数量不匹配，则新增资方机构配置信息
				institutionFundsService.updateInstitutionFundsList(fundsList, branchOrgs);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("保存资方机构配置信息出错", e);
		}

	}

	/**
	 * 信托计划分配弹框
	 * 
	 * @return
	 */
	@RequestMapping("/initCasePlanDistribution")
	@UriKey(key = "com.zdsoft.finance.capital.initCasePlanDistribution")
	@Reference(resource = "com.zdsoft.finance.capital.initCasePlanDistribution", label = "信托计划分配")
	public ModelAndView initCasePlanDistribution(String id) {
		ModelMap modelMap = new ModelMap();

		try {
			InstitutionFunds institutionFunds = institutionFundsService.findOne(id);
			InstitutionFundsVo fundsVo = new InstitutionFundsVo(institutionFunds);

			modelMap.put("fundsVo", fundsVo);
			modelMap.put("updateTime", DateHelper.dateToString(new Date(), "yyyy/MM/dd"));
		} catch (BusinessException e) {
			e.printStackTrace();
			logger.error("查询信托计划分配出现错误", e);
		}

		return new ModelAndView("/capital/instritutional_funds_add", modelMap);
	}

	@RequestMapping("/updateInstitutionFunds")
	@UriKey(key = "com.zdsoft.finance.capital.updateInstitutionFunds")
	@ResponseBody
	public ResponseMsg updateInstitutionFunds(InstitutionFundsVo institutionFundsVo, String jsoncallback) {

		ResponseMsg msg = new ResponseMsg();
		// 转换po
		InstitutionFunds funds = institutionFundsVo.toPo();

		// 查询资方信息
		String capitalIds = institutionFundsVo.getCapitalIds();
		String[] ids = capitalIds.split(",");
		List<Capitalist> capitalists = new ArrayList<Capitalist>();
		if (ObjectHelper.isNotEmpty(ids)) {
			for (String id : ids) {
				try {
					capitalists.add(capitalistService.findOne(id));
				} catch (BusinessException e) {
					e.printStackTrace();
					logger.error("查询资方信息失败", e);
				}
			}
		}

		// 组装institutionFunds
		funds.setCapitalists(capitalists);

		// 执行修改方法
		institutionFundsService.updateInstitutionFunds(funds);
		msg.setResultStatus(ResponseMsg.SUCCESS);
		msg.setMsg("修改资方配置信息成功");

		return msg;
	}

	/**
	 * 获取所有资方信息
	 * 
	 * @param jsoncallback
	 * @return
	 */
	@RequestMapping("/getManagements")
	@UriKey(key = "com.zdsoft.finance.capital.getManagements")
	@ResponseBody
	public String getManagements(String jsoncallback) {
		List<Capitalist> capitalists = capitalistService.findList();
		List<CapitalistVo> capitalistVos = new ArrayList<CapitalistVo>();

		for (Capitalist capitalist : capitalists) {
			CapitalistVo vo = new CapitalistVo(capitalist);
			capitalistVos.add(vo);
		}
		return ObjectHelper.objectToJson(capitalistVos, jsoncallback);
	}
	/**
	 * 
	 * 根据公司code获取资方
	 *
	 * @author xj
	 * @param jsoncallback
	 * @return
	 */
	@RequestMapping("/getCapitalistList")
	@UriKey(key = "com.zdsoft.finance.capital.getCapitalistList")
	@ResponseBody
	public String getCapitalistList(String jsoncallback) {
		List<CapitalistVo> capitalistVos = new ArrayList<CapitalistVo>();
		EmpDto loginUser;
		try {
			loginUser = CED.getLoginUser();
			InstitutionFunds institutionFundsList = institutionFundsService.findByOrgCd(loginUser.getCompanyCd());
			if(ObjectHelper.isNotEmpty(institutionFundsList)){
				List<Capitalist> capitalists = institutionFundsList.getCapitalists();
				if(ObjectHelper.isNotEmpty(capitalists)){
					for (Capitalist capitalist : capitalists) {
						CapitalistVo vo = new CapitalistVo(capitalist);
						capitalistVos.add(vo);
					}
				}
			}
		} catch (Exception e) {
			logger.error("根据code获取资方失败", e);
			e.printStackTrace();
		}
		
		return ObjectHelper.objectToJson(capitalistVos, jsoncallback);
	}
	
}
