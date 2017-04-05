package com.zdsoft.finance.risk.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.zdsoft.finance.risk.entity.DaasBasic;
import com.zdsoft.finance.risk.entity.HuifaRequest;
import com.zdsoft.finance.risk.entity.HuifaResult;
import com.zdsoft.finance.risk.huifa.service.BusinessDetailService;
import com.zdsoft.finance.risk.huifa.service.HuifaInfoService;
import com.zdsoft.finance.risk.huifa.service.HuifaResultService;
import com.zdsoft.finance.risk.vo.DaasBasicVo;
import com.zdsoft.finance.risk.vo.HuifaRequestVo;
import com.zdsoft.framework.core.commweb.annotation.UriKey;
import com.zdsoft.framework.core.commweb.component.BaseController;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title: RiskForHuifaAndBusinessController.java 
 * @ClassName: RiskForHuifaAndBusinessController 
 * @Description: 风险信息-汇法网和工商基本数据控制器
 * @author panshm 
 * @date 2017年2月18日 下午2:45:45 
 * @version V1.0
 */
@Controller
@RequestMapping("/huifaAndBusiness")
public class RiskForHuifaAndBusinessController extends BaseController {
	
	@Autowired
	private HuifaInfoService huifaService;
	
	@Autowired
	private HuifaResultService huifaResultService;
	
	@Autowired
	private BusinessDetailService businessDetailService;
	
	/**
	 * @Title: findHuifaCompany 
	 * @Description: 根据企业名称取得汇法网个人数据
	 * @author panshm 
	 * @param queryDatas 企业名称与申请人姓名组合的数组，示例：公司A,申请1,公司B,申请2,公司C,申请3,公司D,申请3
	 * @return
	 */
	@RequestMapping("/findHuifaCompany")
	@UriKey(key="com.zdsoft.finance.risk.findHuifaCompany")
	public ModelAndView findHuifaCompany(String queryDatas, HttpServletRequest req){
		ModelMap modelMap = new ModelMap();
		List<HuifaRequestVo> voList = new ArrayList<HuifaRequestVo>();
		try {
			if (StringUtils.isEmpty(queryDatas)) {				
				logger.error("企业名称为空");
			} else {
				String[] queryDataList = queryDatas.split(",");
				if (queryDataList.length%2 != 0) {
					logger.error("企业名称数据不正确");
				}
				String companyNames = "";
				Map<String, String> queryDataMap = new HashMap<String, String>();
				for (int i=0;i<queryDataList.length;i=i+2) {
					if (StringUtils.isNotEmpty(companyNames)) {
						companyNames +=",";
					}
					companyNames += queryDataList[i];
					queryDataMap.put(queryDataList[i], queryDataList[i+1]);
				}
				// 解析公司名称列表
				String[] companyNameList = companyNames.split(",");
				// 获取企业汇法网数据
				List<HuifaRequest> infoList = huifaService.findByNameAndStype(companyNameList, HuifaRequest.STYPE_COMPANY);
				voList = this.copyHuifaList(infoList);
				// 将传递的申请人放入显示列表内（前提是：本批企业对应的申请人通过本功能传入）
				for (HuifaRequestVo vo : voList) {
					String applicant = queryDataMap.get(vo.getName());
					vo.setApplicant(applicant);
				}
				modelMap.put("companyVoList", voList);
			}
		} catch (Exception e) {
			logger.error("通过企业名查询企业汇法网数据出错", e);
		}
		return new ModelAndView("risk/huifa_company",modelMap);
	}
	
	/**
	 * @Title: findHuifaPersonal 
	 * @Description: 根据出借人对应的证件号取得汇法网个人数据
	 * @author panshm 
	 * @param codes 出借人对应的证件号
	 * @return
	 */
	@RequestMapping("/findHuifaPersonal")
	@UriKey(key="com.zdsoft.finance.risk.findHuifaPersonal")
	public ModelAndView findHuifaPersonal(String codes, HttpServletRequest req){
		ModelMap modelMap = new ModelMap();
		List<HuifaRequestVo> voList = new ArrayList<HuifaRequestVo>();
		try {
			if (StringUtils.isEmpty(codes)) {				
				logger.error("个人身份证号为空");
			} else {
				// 解析公司名称列表
				String[] codeList = codes.split(",");
				// 获取个人汇法网数据
				List<HuifaRequest> infoList = huifaService.findByCodeAndStype(codeList, HuifaRequest.STYPE_PERSONAL);
				voList = this.copyHuifaList(infoList);
				modelMap.put("personalVoList", voList);
			}
		} catch (Exception e) {
			logger.error("通过指定证件号查询个人汇法网数据", e);
		}
		return new ModelAndView("risk/huifa_personal",modelMap);
	}
	
	/**
	 * @Title: findBusinessByName 
	 * @Description: 根据企业名称获取工商数据
	 * @author panshm 
	 * @param companyNames 被查企业名称（多个企业名用英文逗号分隔）
	 * @return
	 */
	@RequestMapping("/findBusinessByName")
	@UriKey(key="com.zdsoft.finance.risk.findBusinessByName")
	public ModelAndView findBusinessByName(String companyNames, HttpServletRequest req){
		ModelMap modelMap = new ModelMap();
		try {
			List<DaasBasicVo> basicVoList = new ArrayList<DaasBasicVo>();
			if (StringUtils.isEmpty(companyNames)) {				
				logger.error("企业名称为空");
			} else {
				// 解析公司名称列表
				String[] companyNameList = companyNames.split(",");
				// 引入工商的数据
				List<DaasBasic> basicList = businessDetailService.findByCompanyNames(companyNameList);
				basicVoList = this.copyBusinessList(basicList);
				modelMap.put("basicVoList", basicVoList);
			}
			
		} catch (Exception e) {
			logger.error("通过指定证件号查询汇法网和工商数据", e);
		}
		return new ModelAndView("risk/business_info",modelMap);
	}
	
	/**
	 * @Title: copyHuifaList 
	 * @Description: 将指定List进行Vo化拷贝
	 * @author panshm 
	 * @param oriList 原List对象
	 * @return 目标Vo的List
	 * @throws Exception 
	 */
	private List<HuifaRequestVo> copyHuifaList(List<HuifaRequest> oriList)
			throws Exception {
		List<HuifaRequestVo> returnList = new ArrayList<HuifaRequestVo>();
		for (HuifaRequest ori : oriList) {
			HuifaRequestVo vo = new HuifaRequestVo();
			BeanUtils.copyProperties(ori, vo);
			HuifaResult result = huifaResultService.findByRequestId(ori.getId());
			if (null != result) {				
				vo.setSuccess(result.getSuccess());
				vo.setTotalNumber(result.getTotalNumber());
				vo.setResultId(result.getId());
			}
			returnList.add(vo);
		}
		return returnList;
	}
	
	/**
	 * @Title: copyBusinessList 
	 * @Description: 将指定List进行Vo化拷贝
	 * @author panshm 
	 * @param oriList 原List对象
	 * @return 目标Vo的List
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	private List<DaasBasicVo> copyBusinessList(List<DaasBasic> oriList)
			throws IllegalAccessException, InvocationTargetException {
		List<DaasBasicVo> returnList = new ArrayList<DaasBasicVo>();
		for (DaasBasic ori : oriList) {
			DaasBasicVo vo = new DaasBasicVo();
			BeanUtils.copyProperties(ori, vo);
			returnList.add(vo);
		}
		return returnList;
	}
}
