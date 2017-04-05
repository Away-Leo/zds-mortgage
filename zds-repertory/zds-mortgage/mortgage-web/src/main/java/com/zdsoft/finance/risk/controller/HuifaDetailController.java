package com.zdsoft.finance.risk.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.zdsoft.finance.risk.entity.HuifaResultInfo;
import com.zdsoft.finance.risk.huifa.service.HuifaDetailService;
import com.zdsoft.finance.risk.vo.HuifaResultInfoVo;
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
@RequestMapping("/huifaDetail")
public class HuifaDetailController extends BaseController {
	
	@Autowired
	private HuifaDetailService huifaDetailService;
	
	/**
	 * @Title: findByResultId 
	 * @Description: 根据结果id取得汇法网详情信息
	 * @author panshm 
	 * @param type 汇法网请求结果id
	 * @return
	 */
	@RequestMapping("/findByResultId")
	@UriKey(key="com.zdsoft.finance.risk.findByResultId")
	//@Menu(resource = "com.zdsoft.finance.risk.findByResultId", label = "汇法信息", group = "project", order = 2)
	public ModelAndView findByResultId(String resultId){
		ModelMap modelMap = new ModelMap();
		try {
			List<HuifaResultInfoVo> resultInfoVoList = new ArrayList<HuifaResultInfoVo>();
			// 获取执行公开信息
			List<HuifaResultInfo> resultInfoList = huifaDetailService.findSingleHuifaDetail(HuifaResultInfo.TYPE_PUBLIC,
					resultId);
			resultInfoVoList = copyList(resultInfoList);
			
			modelMap.put("publishList", resultInfoVoList);
			
			// 获取失信老赖名单
			resultInfoList = huifaDetailService.findSingleHuifaDetail(HuifaResultInfo.TYPE_DISHONEST,
					resultId);
			resultInfoVoList = copyList(resultInfoList);
			modelMap.put("dishonestList", resultInfoVoList);
			
			// 获取限制高消费名单
			resultInfoList = huifaDetailService.findSingleHuifaDetail(HuifaResultInfo.TYPE_RESTRICTED_HIGH_CONSUME,
					resultId);
			resultInfoVoList = copyList(resultInfoList);
			modelMap.put("restrictedHighConsumeList", resultInfoVoList);
			
			// 限制出入境名单
			resultInfoList = huifaDetailService.findSingleHuifaDetail(HuifaResultInfo.TYPE_RESTRICTED_ENTRY_AND_EXIT,
					resultId);
			resultInfoVoList = copyList(resultInfoList);
			modelMap.put("restrictedEntryAndExitList", resultInfoVoList);
			
			// 民商事裁判文书
			resultInfoList = huifaDetailService.findSingleHuifaDetail(HuifaResultInfo.TYPE_CIVIL_JUDGMENT,
					resultId);
			resultInfoVoList = copyList(resultInfoList);
			modelMap.put("civilJudgmentList", resultInfoVoList);
			
			// 民商事审判流程
			resultInfoList = huifaDetailService.findSingleHuifaDetail(HuifaResultInfo.TYPE_CIVIL_APPROVAL_PROCESS,
					resultId);
			resultInfoVoList = copyList(resultInfoList);
			modelMap.put("civilApprovalProcessList", resultInfoVoList);
			
			// 罪犯及嫌疑人名单
			resultInfoList = huifaDetailService.findSingleHuifaDetail(HuifaResultInfo.TYPE_CRIMINAL,
					resultId);
			resultInfoVoList = copyList(resultInfoList);
			modelMap.put("criminalList", resultInfoVoList);
			
			// 行政违法记录
			resultInfoList = huifaDetailService.findSingleHuifaDetail(HuifaResultInfo.TYPE_ILLEGALITY,
					resultId);
			resultInfoVoList = copyList(resultInfoList);
			modelMap.put("illegalityList", resultInfoVoList);
			
			// 欠税名单
			resultInfoList = huifaDetailService.findSingleHuifaDetail(HuifaResultInfo.TYPE_OWE_TAXES,
					resultId);
			resultInfoVoList = copyList(resultInfoList);
			modelMap.put("oweTaxesList", resultInfoVoList);
			
			// 纳税非正常户
			resultInfoList = huifaDetailService.findSingleHuifaDetail(HuifaResultInfo.TYPE_ABNORMAL_TAX,
					resultId);
			resultInfoVoList = copyList(resultInfoList);
			modelMap.put("abnormalTaxList", resultInfoVoList);
			
			// 欠款欠费名单
			resultInfoList = huifaDetailService.findSingleHuifaDetail(HuifaResultInfo.TYPE_ARREARAGE,
					resultId);
			resultInfoVoList = copyList(resultInfoList);
			modelMap.put("arrearageList", resultInfoVoList);
			
		} catch (Exception e) {
			logger.error("通过查询结果id查询汇法网返回的数据", e);
		}
		return new ModelAndView("risk/huifa_detail",modelMap);
	}
	
	/**
	 * @Title: copyList 
	 * @Description: 将指定List进行Vo化拷贝
	 * @author panshm 
	 * @param oriList 原List对象
	 * @return 目标Vo的List
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	private List<HuifaResultInfoVo> copyList(List<HuifaResultInfo> oriList)
			throws IllegalAccessException, InvocationTargetException {
		List<HuifaResultInfoVo> returnList = new ArrayList<HuifaResultInfoVo>();
		for (HuifaResultInfo ori : oriList) {
			HuifaResultInfoVo vo = new HuifaResultInfoVo();
			BeanUtils.copyProperties(ori, vo);
			returnList.add(vo);
		}
		return returnList;
	}
	
	

}
