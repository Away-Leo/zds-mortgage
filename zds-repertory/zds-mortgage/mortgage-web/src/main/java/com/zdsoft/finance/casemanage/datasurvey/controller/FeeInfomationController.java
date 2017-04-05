package com.zdsoft.finance.casemanage.datasurvey.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zdsoft.essential.client.service.CED;
import com.zdsoft.essential.dto.basedata.SimpleCodeDto;
import com.zdsoft.finance.casemanage.datasurvey.entity.FeeInfomation;
import com.zdsoft.finance.casemanage.datasurvey.service.FeeInfomationService;
import com.zdsoft.finance.casemanage.datasurvey.vo.FeeEvaluationInfoVo;
import com.zdsoft.finance.casemanage.datasurvey.vo.FeeInfomationListVo;
import com.zdsoft.finance.casemanage.datasurvey.vo.FeeInfomationVo;
import com.zdsoft.finance.casemanage.datasurvey.vo.FeeReceiverVo;
import com.zdsoft.finance.common.base.QueryObj;
import com.zdsoft.finance.common.base.impl.CustomCommon;
import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.finance.common.utils.ParameterUtil;
import com.zdsoft.finance.common.utils.TimeUtil;
import com.zdsoft.finance.customer.entity.BeforeCustomer;
import com.zdsoft.finance.marketing.entity.CaseApplyBeforeCustomer;
import com.zdsoft.finance.marketing.service.CaseApplyBeforeCustomerService;
import com.zdsoft.framework.core.common.annotation.Log;
import com.zdsoft.framework.core.common.dto.ResponseMsg;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.PageRequest;
import com.zdsoft.framework.core.common.util.ObjectHelper;
import com.zdsoft.framework.core.commweb.annotation.UriKey;
import com.zdsoft.framework.core.commweb.component.BaseController;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * 
 * @Title:FeeInfomationController.java
 * @Package:com.zdsoft.finance.casemanage.datasurvey.controller
 * @Description:费用信息
 * @author: laijun
 * @date:2017年1月10日 下午9:40:54
 * @version:v1.0
 */
@Controller
@RequestMapping("/casemanage/datasurvey/feeinfomation")
public class FeeInfomationController extends BaseController {
	
	@Autowired
	private FeeInfomationService feeInfomationService;
	
	@Log
	private Logger log;
	
	@Autowired
	private CaseApplyBeforeCustomerService caseApplyBeforeCustomerService;
	
	@Autowired
	private CED CED;
	
	
	/**
	 * 
	 * 费用信息编辑
	 *
	 * @author laijun
	 * @date:2017年1月10日 下午9:41:18
	 * @param projectId
	 * @return
	 * @throws BusinessException
	 * @updater jingyh
	 */
	@RequestMapping(value = "/edit")
	@UriKey(key = "com.zdsoft.finance.casemanage.datasurvey.feeinfomation.edit")
	public ModelAndView edit(String caseApplyId) {
		log.info("进入费用编辑页面");
		log.debug("caseApplyId:{}", caseApplyId);
		ModelMap model = new ModelMap();
		// 主借人类型值
		model.put("mainBorrowType", CaseApplyBeforeCustomer.MAIN_BORROW);
		// 其他类型值
		model.put("otherType", FeeInfomationService.JOIN_TYPE_OTHER_FULLCODE);
		try {
			List<FeeInfomation> feeList = feeInfomationService.findFeeInfomationByCaseApplyId(caseApplyId);
			if (ObjectHelper.isEmpty(feeList)) {
				model.put("hasResultMap", false);
			}
			// 放入Model中
			model.put("feeInfoMap", this.switchFeeInfoToVoMap(feeList));
			// 获得案件参与人信息
			model.put("customerInfos", this.getCaseApplyCustomerJson(caseApplyId));
			// 获取参与类型
			model.put("joinTypeInfos", this.getFeeCustomerTypes());
			
		} catch (Exception e) {
			log.error("查询数据错误", e);
			e.printStackTrace();
		}
		return new ModelAndView("casemanage/datasurvey/feeinfomation_edit", model);
	}

	/**
	 * 
	 * 费用信息查看
	 *
	 * @author laijun
	 * @date:2017年1月10日 下午9:41:18
	 * @param projectId
	 * @return
	 * @throws BusinessException
	 * @updater jingyh
	 * @date:2017年1月15日 下午6:41:18
	 */
	@RequestMapping(value = "/view")
	@UriKey(key = "com.zdsoft.finance.casemanage.datasurvey.feeinfomation.view")
	public ModelAndView view(HttpServletRequest req, String caseApplyId)  {
		ModelMap model = new ModelMap();
		log.info("进入费用查看页面");
		try {
			List<FeeInfomation> feeList = feeInfomationService.findByCaseApplyId(caseApplyId);
			if (ObjectHelper.isEmpty(feeList)) {
				model.put("hasResultMap", false);
			}
			model.put("feeInfoMap",  this.switchFeeInfoToVoMap(feeList));
		} catch (Exception e) {
			model.put("errorMsg", e.getMessage());
			log.error("进入费用查看页面出错！", e);
			e.printStackTrace();
		}
		model.put("caseApplyId", caseApplyId);
		return new ModelAndView("casemanage/financialreview/feeinfomation_view", model);

	}
	
	/**
	 * @Title: findFeeInfomationByCaseApplyId 
	 * @Description: 根据案件id获取收款明细
	 * @author jincheng 
	 * @param caseApplyId
	 * @return
	 */
	@RequestMapping(value="/findFeeInfomationByCaseApplyId")
	@UriKey(key = "com.zdsoft.finance.casemanage.findFeeInfomationByCaseApplyId")
	@ResponseBody
	public ResponseMsg  findFeeInfomationByCaseApplyId(String caseApplyId){
			ResponseMsg msg = new ResponseMsg();
			try {
				List<FeeInfomation> feeList = feeInfomationService.findFeeInfomationByCaseApplyId(caseApplyId);
				List<FeeInfomationVo> feeVoList=new ArrayList<FeeInfomationVo>();
				for(FeeInfomation fee:feeList){
					FeeInfomationVo feeVo=new FeeInfomationVo(fee);
					feeVoList.add(feeVo);
				}
				msg.setResultStatus(ResponseMsg.SUCCESS);
		        msg.setRows(feeVoList);
		        msg.setTotal(Long.parseLong(""+feeVoList.size()));
			} catch (Exception e) {
				log.error("进入费用查看页面出错！", e);
				e.printStackTrace();
			}
        return msg;
	}

	/**
	 * 
	 * 费用信息保存
	 *
	 * @author jingyh
	 * @param req
	 * @param voInfos
	 * 			费用集合Vo
	 * @return
	 */
	@RequestMapping("/save")
	@UriKey(key = "com.zdsoft.finance.casemanage.datasurvey.feeinfomation.save")
	@ResponseBody
	public ResponseMsg save(HttpServletRequest req,FeeInfomationListVo voInfos) {
		ResponseMsg msg = new ResponseMsg();
		log.info("保存费用信息");
		log.debug("caseApplyId:{}", voInfos.getCaseApplyId());
		log.debug("数据量大小:{}", voInfos.getFeeInfos());
		try {
			// 校验数据
			validateFeeInfos(voInfos.getFeeInfos());
			// 保存数据
			feeInfomationService.saveOrUpdateFeeInfos(voInfos.getCaseApplyId(), switchFeeInfoToPoList(voInfos.getFeeInfos()));
			msg.setId(voInfos.getCaseApplyId());
			msg.setMsg("保存费用信息成功");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("保存出错", e);
			msg.setResultStatus(ResponseMsg.APP_ERROR);
			msg.setMsg(e.getMessage());
		}
		return msg;
	}
	
	/**
	 * 
	 * 根据案件Id和参与类型查询收费对象列表信息（包含配偶信息）
	 *
	 * @author jingyh
	 * @param req
	 * @param caseApplyId
	 * @param receiveType
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping("/findReceiveCustomer")
	@UriKey(key = "com.zdsoft.finance.casemanage.datasurvey.feeinfomation.findReceiveCustomer")
	@ResponseBody
	public ResponseMsg findReceiveCustomer(HttpServletRequest req, String caseApplyId,PageRequest pageReq) {
		ResponseMsg msg = new ResponseMsg();
		log.info("查询收费对象");
		log.debug("caseApplyId:{}", caseApplyId);
		try {
			// 1、列表显示案件的所有角色
			// 包括申请人及其配偶/
			// 2、没有参与类型的也在这里显示
			Page<Map<String, Object>> resultMap = this.feeInfomationService.findReceiveCustomer(caseApplyId,pageReq);
			List<FeeReceiverVo> result = (List)CustomCommon.convert(FeeReceiverVo.class, resultMap.getRecords());
			List<String> fullCodeList = new ArrayList<String>();
			for (FeeReceiverVo voInfo : result) {
				if (ObjectHelper.isNotEmpty(voInfo.getJoinType()) && !fullCodeList.contains(voInfo.getJoinType())) {
					fullCodeList.add(voInfo.getJoinType());
				}
				// 计算年龄
				if (ObjectHelper.isNotEmpty(voInfo.getBirthdayDate())) {
					Map<String, Object> map = TimeUtil.getMonthCha(voInfo.getBirthdayDate().toString(), TimeUtil.getCurrentDateInteger().toString());
					int month = Integer.parseInt(map.get("month").toString());
					voInfo.setCustomerAge(month/12);
				}
			}
			if (ObjectHelper.isNotEmpty(fullCodeList)) {
				Map<String, String> fullCodeMap = CED.loadSimpleCodeNameByFullCodes(fullCodeList);
				if (ObjectHelper.isNotEmpty(fullCodeMap)) {
					for (String key : fullCodeMap.keySet()) {
						for (FeeReceiverVo voInfo : result) {
							if (key.equals(voInfo.getJoinType())) {
								voInfo.setJoinTypeName(fullCodeMap.get(key));
							}
						}
					}
				}
			}
			if (ObjectHelper.isEmpty(result)) {
				result = new ArrayList<FeeReceiverVo>();
			}
			log.debug("结果集大小为：{}", result.size());
			msg.setTotal(resultMap.getTotalRows());
			msg.setRows(result);
		} catch (Exception e) {
			log.error("查询收费对象出错！", e);
			e.printStackTrace();
			msg.setMsg(e.getMessage());
			msg.setResultStatus(ResponseMsg.APP_ERROR);
		}
		return msg;
	}
	
	/**
	 * 
	 * 查询评估机构信息
	 *
	 * @author jingyh
	 * @param req
	 * @param pageReq
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping("/findEvaluationInfos")
	@UriKey(key = "com.zdsoft.finance.casemanage.datasurvey.feeinfomation.findEvaluationInfos")
	@ResponseBody
	public ResponseMsg findEvaluationInfos(HttpServletRequest req,PageRequest pageReq) {
		ResponseMsg msg = new ResponseMsg();
		log.info("查询评估机构信息");
		try {
			List<QueryObj> queryInfo = ParameterUtil.getQueryObjByParameter(req, new String[]{"zeov"});
			// 查询评估机构
			Page<Map<String, Object>> resultMap = this.feeInfomationService.findEvaluationInfos(queryInfo,pageReq);
			List<FeeEvaluationInfoVo> result = (List)CustomCommon.convert(FeeEvaluationInfoVo.class, resultMap.getRecords());
			if (ObjectHelper.isEmpty(result)) {
				result = new ArrayList<FeeEvaluationInfoVo>();
			}
			log.debug("结果集大小为：{}", result.size());
			msg.setTotal(resultMap.getTotalRows());
			msg.setRows(result);
		} catch (Exception e) {
			log.error("查询评估机构出错！", e);
			e.printStackTrace();
			msg.setMsg(e.getMessage());
			msg.setResultStatus(ResponseMsg.APP_ERROR);
		}
		return msg;
	}
	
	/**
	 * 
	 * @Title: findReviceObjTypeInfos 
	 * @Description: 查询案件相关的收费对象类型信息
	 * @author jingyh 
	 * @param req
	 * @param caseApplyId
	 * @return
	 */
	@RequestMapping("/findReviceObjTypeInfos")
	@UriKey(key = "com.zdsoft.finance.casemanage.datasurvey.feeinfomation.findReviceObjTypeInfos")
	@ResponseBody
	public String findReviceObjTypeInfos(HttpServletRequest req, String caseApplyId,String jsoncallback) {
		log.info("查询收费对象类型下拉信息");
		log.debug("caseApplyId:{}", caseApplyId);
		try {
			List<Map<String,Object>> list = this.feeInfomationService.findReviceObjTypeInfosByCaseApplyId(caseApplyId);
			List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
			// key值转换
			for (Map<String,Object> temp : list) {
				Map<String, Object> tempRes = new HashMap<String, Object>();
				Set<String> keySet = temp.keySet();
				for (String key : keySet) {
					if (key.equalsIgnoreCase("feeObjectType")) {
						// 类型值
						tempRes.put("feeObjectType", temp.get(key));
						if (FeeInfomationService.JOIN_TYPE_OTHER_FULLCODE.equals(temp.get(key))) {
							tempRes.put("feeObjectTypeName", FeeInfomationService.JOIN_TYPE_OTHER_NAME);
						} else {
							tempRes.put("feeObjectTypeName", CED.loadSimpleCodeNameByFullCode((String)temp.get(key)));
						}
					} else if (key.equalsIgnoreCase("feeObjectIds")) {
						// 收费对象Id
						tempRes.put("feeObjectIds", temp.get(key));
					} else if (key.equalsIgnoreCase("feeObjectNames")) {
						// 收费对象名称
						tempRes.put("feeObjectNames", temp.get(key));
					} else if (key.equalsIgnoreCase("expectedAmountTotal")) {
						// 预收合计金额
						tempRes.put("expectedAmountTotal", temp.get(key));
					}
				}
				result.add(tempRes);
			}
			return ObjectHelper.objectToJson(result,jsoncallback);
		} catch (Exception e) {
			logger.error("查询出错！",e);
			e.printStackTrace();
		}
		return "";
	}
	
	/**
	 * 
	 * @Title: findFeeDeatailInfosByReceiveObjTypes 
	 * @Description: 根据收费对象类型查询收费明细
	 * @author jingyh 
	 * @param req
	 * @param caseApplyId
	 * @param receiveObjTypes
	 * @return
	 */
	@RequestMapping("/findFeeDeatailInfosByReceiveObjTypes")
	@UriKey(key = "com.zdsoft.finance.casemanage.datasurvey.feeinfomation.findFeeDeatailInfosByReceiveObjTypes")
	@ResponseBody
	public ResponseMsg findFeeDeatailInfosByReceiveObjTypes(HttpServletRequest req, String caseApplyId, String receiveObjTypes) {
		ResponseMsg msg = new ResponseMsg();
		log.info("收费明细信息");
		log.debug("caseApplyId:{}", caseApplyId);
		log.debug("receiveObjTypes:{}", receiveObjTypes);
		if(ObjectHelper.isEmpty(receiveObjTypes)){
			return msg;
		}
		try {
			if (ObjectHelper.isEmpty(receiveObjTypes)) {
				throw new BusinessException("10010004","传入案件Id或收费对象类型为空！");
			}
			List<String> receiveObjTypesList = Arrays.asList(receiveObjTypes.split(","));
			List<FeeInfomation> resultList = this.feeInfomationService.findByCaseApplyIdAndReceiveObjTypes(caseApplyId, receiveObjTypesList);
			List<FeeInfomationVo> resultListVo = new ArrayList<FeeInfomationVo>();
			for (FeeInfomation entity : resultList) {
				resultListVo.add(new FeeInfomationVo(entity));
			}
			msg.setRows(resultListVo);
			msg.setTotal(Long.valueOf(resultListVo.size()));
		} catch (Exception e) {
			logger.error("查询出错！",e);
			e.printStackTrace();
			msg.setMsg(e.getMessage());
			msg.setResultStatus(ResponseMsg.APP_ERROR);
		}
		return msg;
	}
	
	/**
	 * 
	 * 将查询得到的费用信息分类处理
	 *
	 * @author jingyh
	 * @param feeList
	 * @return
	 */
	private Map<String,List<FeeInfomationVo>> switchFeeInfoToVoMap(List<FeeInfomation> feeList) {
		Map<String,List<FeeInfomationVo>> resultMap = new HashMap<String, List<FeeInfomationVo>>();
		if (ObjectHelper.isNotEmpty(feeList)) {
			// 按费用类型排序
			Collections.sort(feeList, new Comparator<FeeInfomation>() {
	            public int compare(FeeInfomation o1, FeeInfomation o2) {
	                return o1.getFeeType().compareTo(o2.getFeeType());
	            }
	        });
			String feeType = "";
			String feeTypeName = "";
			List<FeeInfomationVo> voList = null;
			for (int i = 0; i < feeList.size(); i++) {
				FeeInfomation info = feeList.get(i);
				if (!feeType.equals(info.getFeeType())) {
					if (ObjectHelper.isNotEmpty(feeType) && ObjectHelper.isNotEmpty(voList)) {
						resultMap.put(feeTypeName, voList);
					}
					feeType = info.getFeeType();
					feeTypeName = info.getFeeTypeName();
					voList = new ArrayList<FeeInfomationVo>();
				}
				voList.add(new FeeInfomationVo(info,i));
			}
			// 添加最后的一条数据
			if (ObjectHelper.isNotEmpty(feeType) && ObjectHelper.isNotEmpty(voList)) {
				resultMap.put(feeTypeName, voList);
			}
		}
		return resultMap;
	}
	
	/**
	 * 
	 * 批量转换vo对象为实体对象
	 *
	 * @author jingyh
	 * @param feeVos
	 * @return
	 */
	private List<FeeInfomation> switchFeeInfoToPoList(List<FeeInfomationVo> feeVos) {
		List<FeeInfomation> feeInfos = null;
		if (ObjectHelper.isNotEmpty(feeVos)) {
			feeInfos = new ArrayList<FeeInfomation>();
			for (FeeInfomationVo vo : feeVos) {
				feeInfos.add(vo.toPO());
			}
		}
		return feeInfos;
	}
	
	/**
	 * 
	 * 获得案件的参与人Id和name集合
	 *
	 * @author jingyh
	 * @param caseApplyId
	 * @return
	 * @throws Exception
	 */
	private String getCaseApplyCustomerJson(String caseApplyId) throws BusinessException{
		List<CaseApplyBeforeCustomer> caseApplyCustomers = caseApplyBeforeCustomerService.queryByCaseApplyId(caseApplyId);
		if (ObjectHelper.isEmpty(caseApplyCustomers)) {
			throw new BusinessException("10010002", "案件参与人员查询失败！");
		}
		Map<String,List<BeforeCustomer>> customerMap = new HashMap<String,List<BeforeCustomer>>();
		for (CaseApplyBeforeCustomer cabc : caseApplyCustomers) {
			if (!customerMap.containsKey(cabc.getJoinType())) {
				// 不存在对应的Key,新建key
				customerMap.put(cabc.getJoinType(), new ArrayList<BeforeCustomer>());
			}
			List<BeforeCustomer> list = customerMap.get(cabc.getJoinType());
			if (!list.contains(cabc.getBeforeCustomer())) {
				// 放入map中
				customerMap.get(cabc.getJoinType()).add(cabc.getBeforeCustomer());
			}
		}
		// 拼接Id和name的字符串
		Set<String> keySet = customerMap.keySet();
		Map<String,Map<String,String>> customerMapRes = new HashMap<String,Map<String,String>>();
		List<String> ids = null;
		List<String> names = null;
		for (String key : keySet) {
			Map<String,String> datas = new HashMap<String,String>();
			List<BeforeCustomer> list = customerMap.get(key);
			ids = new ArrayList<String>();
			names = new ArrayList<String>();
			for (BeforeCustomer bc : list) {
				if (!ids.contains(bc.getId())) {
					ids.add(bc.getId());
				}
				if (!names.contains(bc.getCustomerName())) {
					names.add(bc.getCustomerName());
				}
			}
			datas.put("ids", StringUtils.join(ids, ","));
			datas.put("names", StringUtils.join(names, ","));
			customerMapRes.put(key, datas);
		}
		// 转化为json
		return ObjectHelper.objectToJson(customerMapRes);
	}
	
	/**
	 * 
	 * 获得收费，付费的参与类型
	 *
	 * @author jingyh
	 * @return
	 * @throws BusinessException
	 */
	private String getFeeCustomerTypes() throws BusinessException {
		try {
			List<SimpleCodeDto> list = CED.querySimpleCodeByCategoryId(FeeInfomationService.JOIN_TYPE_CATEGORY_ID);
			if (ObjectHelper.isEmpty(list)) {
				list = new ArrayList<SimpleCodeDto>();
			}
			// 无默认值
			for (SimpleCodeDto dto : list) {
				dto.setIsDefault(false);
			}
			SimpleCodeDto other = new SimpleCodeDto();
			other.setName(FeeInfomationService.JOIN_TYPE_OTHER_NAME);
			other.setFullCode(FeeInfomationService.JOIN_TYPE_OTHER_FULLCODE);
			list.add(other);
			return ObjectHelper.objectToJson(list);
		} catch (Exception e) {
			e.printStackTrace();
			throw new BusinessException("10010008", "未找到simplecode数据");
		}
	}
	
	/**
	 * 
	 * 检验保存的数据
	 *
	 * @author jingyh
	 * @param feeVos
	 * @throws BusinessException
	 */
	private void validateFeeInfos(List<FeeInfomationVo> feeVos) throws BusinessException {
		if (ObjectHelper.isEmpty(feeVos)) {
			throw new BusinessException("10010004", "传入费用信息为空！");
		}
		// 循环校验数据
		for (FeeInfomationVo vo : feeVos) {
			if (ObjectHelper.isEmpty(vo.getFeeItem())) {
				throw new BusinessException("10010003", "费用项目为空！");
			}
			if (ObjectHelper.isNotEmpty(vo.getExpectedAmount()) && vo.getExpectedAmount().compareTo(BigDecimal.ZERO) > 0) {
				// 预计应收不为空时，收费对象信息不为空！大于0
				if (ObjectHelper.isEmpty(vo.getFeeObjectType())) {
					throw new BusinessException("10010003", "收费对象类型为空！");
				}
				if (ObjectHelper.isEmpty(vo.getFeeeObjectName())) {
					throw new BusinessException("10010003", "收费对象名称为空！");
				}
				if (ObjectHelper.isEmpty(vo.getFeeObjectId())) {
					throw new BusinessException("10010003", "收费对象Id为空！");
				}
			}
			if (ObjectHelper.isNotEmpty(vo.getExpectedPayableAmount()) && vo.getExpectedPayableAmount().compareTo(BigDecimal.ZERO) > 0) {
				// 预计应付不为空时，付费对象不能为空，大于0
				if (ObjectHelper.isEmpty(vo.getPayObjectType())) {
					throw new BusinessException("10010003", "付费对象类型为空！");
				}
				if (ObjectHelper.isEmpty(vo.getPayObjectId())) {
					throw new BusinessException("10010003", "付费对象Id为空！");
				}
				if (ObjectHelper.isEmpty(vo.getPayObjectName())) {
					throw new BusinessException("10010003", "付费对象名称为空！");
				}
			}
			if (BigDecimal.ZERO.compareTo(vo.getExpectedAmount()) > 0) {
				throw new BusinessException("10010003", "预计应收金额错误！");
			}
			if (BigDecimal.ZERO.compareTo(vo.getExpectedPayableAmount()) > 0) {
				throw new BusinessException("10010003", "预计应付金额错误！");
			}
		}
	}
}