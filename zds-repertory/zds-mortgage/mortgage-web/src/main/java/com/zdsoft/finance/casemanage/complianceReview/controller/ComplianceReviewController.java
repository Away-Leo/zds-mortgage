package com.zdsoft.finance.casemanage.complianceReview.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zdsoft.bpm.annotation.client.FinishJob;
import com.zdsoft.bpm.annotation.client.ManualJob;
import com.zdsoft.essential.client.service.CED;
import com.zdsoft.essential.dto.CodeDto;
import com.zdsoft.essential.dto.basedata.SimpleCodeDto;
import com.zdsoft.essential.dto.emp.EmpDto;
import com.zdsoft.finance.casemanage.complianceReview.entity.ComplianceReview;
import com.zdsoft.finance.casemanage.complianceReview.entity.ReviewInformation;
import com.zdsoft.finance.casemanage.complianceReview.service.ComplianceReviewService;
import com.zdsoft.finance.casemanage.complianceReview.service.ReviewInformationService;
import com.zdsoft.finance.casemanage.complianceReview.vo.ComplianceReviewVo;
import com.zdsoft.finance.casemanage.handleMortgage.entity.MaterialPromise;
import com.zdsoft.finance.casemanage.handleMortgage.service.MaterialPromiseService;
import com.zdsoft.finance.casemanage.handleMortgage.vo.MaterialPromiseVo;
import com.zdsoft.finance.cooperator.entity.Capitalist;
import com.zdsoft.finance.cooperator.service.CapitalistService;
import com.zdsoft.finance.customer.entity.BeforePersonalCustomer;
import com.zdsoft.finance.customer.service.BeforePersonalCustomerService;
import com.zdsoft.finance.customer.vo.BeforePersonalCustomerVo;
import com.zdsoft.finance.marketing.entity.CaseApply;
import com.zdsoft.finance.marketing.entity.CaseApplyBeforeCustomer;
import com.zdsoft.finance.marketing.service.CaseApplyService;
import com.zdsoft.finance.marketing.vo.CaseApplyVo;
import com.zdsoft.finance.specialapprove.service.FeeRulesService;
import com.zdsoft.finance.specialapprove.service.RiskRulesService;
import com.zdsoft.framework.core.common.dto.ResponseMsg;
import com.zdsoft.framework.core.common.util.ObjectHelper;
import com.zdsoft.framework.core.commweb.annotation.UriKey;
import com.zdsoft.framework.core.commweb.component.BaseController;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;
/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: ComplianceReviewController.java 
 * @ClassName: ComplianceReviewController 
 * @Description: 案件复核controller
 * @author xj 
 * @date 2017年2月20日 下午7:33:50 
 * @version V1.0
 */
@Controller
@Service("/complianceReview")
public class ComplianceReviewController extends BaseController {
	/**
	 * 债权公证书CODE
	 */
	private static String NOTARIAL_DEED_CODE = "YWDM001500410";
	/**
	 * 债权公证书案件复核显示NAME
	 */
	private static String NOTARIAL_DEED_NAME = "债权公证书";
	/**
	 * 父级债权公证书
	 */
	private static String PARENT_NOTARIAL_DEED_CODE = "YWDM0015004";
	/**
	 * 父级债权公证书NAME
	 */
	private static String PARENT_NOTARIAL_DEED_NAME = "权证类";
	/**
	 * 其他
	 */
	private static String NOTARIAL_OTHER_CODE = "YWDM001500601";
	/**
	 * 其他案件复核显示NAME
	 */
	private static String NOTARIAL_OTHER_NAME = "其他";
	/**
	 * 父级其他
	 */
	private static String PARENT_NOTARIAL_OTHER_CODE = "YWDM0015006";
	/**
	 * 父级其他NAME
	 */
	private static String PARENT_NOTARIAL_OTHER_NAME = "其他";
	@Autowired
	private ComplianceReviewService complianceReviewService;
	@Autowired
	private ReviewInformationService reviewInformationService;
	@Autowired
	private CaseApplyService caseApplyService;
	@Autowired
	private BeforePersonalCustomerService beforePersonalCustomerService;
	@Autowired
	private CapitalistService capitalistService;
	@Autowired
	private MaterialPromiseService materialPromiseService;
	@Autowired
	private RiskRulesService riskRulesService;
	@Autowired
	private FeeRulesService feeRulesService;
	@Autowired
	private CED CED;
	

	/**
	 * 
	 * @Title: initComplianceReview 
	 * @Description: 流程中合规复核入口
	 * @author xj 
	 * @param caseApplyId 案件id
	 * @param businessKey 业务id
	 * @return
	 */
	@RequestMapping("/initComplianceReview")
	@UriKey(key = "com.cnfh.rms.casemanage.complianceReview.initComplianceReview")
	@ManualJob(resource = "com.cnfh.rms.casemanage.complianceReview.initComplianceReview", label = "合规复核") 
	public ModelAndView initComplianceReview(@RequestParam(value="projectId")String caseApplyId,String businessKey){
		
		ModelMap model = new ModelMap();
		logger.info("进入合规复核审批节点");
		logger.info("caseApplyId:"+caseApplyId);
		logger.info("businessKey:"+businessKey);
		try {
			CaseApply caseApply = caseApplyService.findOne(caseApplyId);
			CaseApplyVo caseApplyVo = new CaseApplyVo(caseApply);
			List<BeforePersonalCustomer> beforePersonalCustomers = beforePersonalCustomerService.queryCustomerByCaseApplyIdAndJoinType(caseApplyId, CaseApplyBeforeCustomer.MAIN_BORROW);
			if(ObjectHelper.isNotEmpty(beforePersonalCustomers) && beforePersonalCustomers.size()>0){
				BeforePersonalCustomerVo customerVo = new BeforePersonalCustomerVo(beforePersonalCustomers.get(0));
				model.put("customerVo", customerVo);
			}
			//资金来源  
			String capitalSource = caseApply.getCapitalSource();
			if(ObjectHelper.isNotEmpty(capitalSource)){
				Capitalist capitalist = capitalistService.findOne(capitalSource);
				if(ObjectHelper.isNotEmpty(capitalist)){
					String cooperatorName = capitalist.getCapitalName();
					model.put("cooperatorName", cooperatorName);
				}
			}
			//查询合规复核
			ComplianceReview complianceReview = complianceReviewService.findByCaseApplyId(caseApplyId);
			if(ObjectHelper.isNotEmpty(complianceReview)){
				ComplianceReviewVo complianceReviewVo = new ComplianceReviewVo(complianceReview);
				model.put("complianceReviewVo", complianceReviewVo);
			}
			
			model.put("caseApplyVo", caseApplyVo);
			model.put("businessKey", businessKey);
		} catch (Exception e) {
			logger.error("进入合规复核审批", e);
			e.printStackTrace();
		}
		//案件基本信息
		return new ModelAndView("/casemanage/complianceReview/complianceReview_process_edit", model);
	}
	
	/**
	 * 
	 * @Title: saveComplianceReview 
	 * @Description: 流程中合规复核保存
	 * @author xj 
	 * @param jsoncallback
	 * @param reviewInformationStr 复核资料json字符串
	 * @param complianceReviewVo 复核
	 * @param materialPromiseStr 后补资料json字符串
	 * @param caseApplyId 案件id
	 * @param isSubmit 是否提交，用于判断特批是否审批通过
	 * @param businessKey 业务id
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/saveComplianceReview")
	@UriKey(key = "com.cnfh.rms.casemanage.complianceReview.saveComplianceReview")
	@FinishJob(resource = "com.cnfh.rms.casemanage.complianceReview.initComplianceReview", businessId = "businessKey", projectId = "caseApplyId")
	@ResponseBody
	public String saveComplianceReview(String jsoncallback,String reviewInformationStr,ComplianceReviewVo complianceReviewVo,String materialPromiseStr,String caseApplyId,Boolean isSubmit,String businessKey){
		logger.info("流程中合规复核保存complianceReviewList:"+reviewInformationStr);
		ResponseMsg msg = new ResponseMsg();
		msg.setResultStatus(ResponseMsg.SUCCESS);
		try {
			//判断合规复核是否审批通过,如果有属于草稿状态或者审批没有通过或者审批不通过则不能让审批通过
			if(isSubmit){
				boolean judgeComplianceReviewPassed = riskRulesService.judgeComplianceReviewPassed(caseApplyId);
				boolean judgeComplianceReviewPassed2 = feeRulesService.judgeComplianceReviewPassed(caseApplyId);
				if(!judgeComplianceReviewPassed || !judgeComplianceReviewPassed2){
					msg.setResultStatus(ResponseMsg.APP_ERROR);
					msg.setMsg("提交失败，特批事项没有审批通过！");
					logger.info("合规复核提交失败，特批没有审批通过");
					return  ObjectHelper.objectToJson(msg, jsoncallback);
				}
				
			}
			//转换复核资料
			List<ReviewInformation> reviewInformations = null;
			if(ObjectHelper.isNotEmpty(reviewInformationStr)){
				JSONArray fromObject = JSONArray.fromObject(reviewInformationStr);
				reviewInformations = JSONArray.toList(fromObject, new ReviewInformation(), new JsonConfig());
				logger.info(reviewInformations.toString());
				
			}
			//后补资料
			List<MaterialPromise> materialPromises = null;
			if(ObjectHelper.isNotEmpty(materialPromiseStr)){
				JSONArray fromObject = JSONArray.fromObject(materialPromiseStr);
				materialPromises = JSONArray.toList(fromObject, new MaterialPromise(), new JsonConfig());
				for (MaterialPromise m : materialPromises) {
					m.setCaseApplyId(caseApplyId);
				}
				logger.info(materialPromises.toString());
				
			}
			
			ComplianceReview complianceReview = complianceReviewVo.toPO();
			complianceReview = complianceReviewService.saveOrUpdateReviewAndData(complianceReview, reviewInformations,materialPromises);
			msg.setId(complianceReview.getId());
		} catch (Exception e) {
			msg.setResultStatus(ResponseMsg.APP_ERROR);
			msg.setMsg("保存失败，请查看系统日志！");
			e.printStackTrace();
			logger.error("流程中合规复核保存", e);
		}
		return  ObjectHelper.objectToJson(msg, jsoncallback);
	}
	
	/**
	 * 
	 * @Title: getFirstMark 
	 * @Description: 获取一级标示
	 * @author xj 
	 * @param jsoncallback
	 * @return
	 */
	@RequestMapping("/getFirstMark")
	@UriKey(key = "com.cnfh.rms.casemanage.complianceReview.getFirstMark") 
	@ResponseBody
	public String getFirstMark(String jsoncallback){
		List<Map<String,Object>> listRt = new ArrayList<Map<String,Object>>();
		try {
			List<SimpleCodeDto> list = CED.querySimpleCodeByCategoryId("YWDM0083");
			for (SimpleCodeDto simpleCodeDto : list) {
				if(ObjectHelper.isEmpty(simpleCodeDto.getPid())){
					 Map<String, Object> map = new HashMap<>();
					 map.put("fullCode", simpleCodeDto.getFullCode());
		             map.put("name", simpleCodeDto.getName());
		            listRt.add(map);
				 }
			}
		} catch (Exception e) {
			logger.error("获取一级标示:", e);
			e.printStackTrace();
		}
		
		
		
		return ObjectHelper.objectToJson(listRt, jsoncallback);
		
	}
	
	/**
	 * 
	 * @Title: getMarkByParent 
	 * @Description: 通过父级id获取子类
	 * @author xj 
	 * @param jsoncallback
	 * @param id
	 * @return
	 */
	@RequestMapping("/getMarkByParent")
	@UriKey(key = "com.cnfh.rms.casemanage.complianceReview.getMarkByParent")
	@ResponseBody
	public String getMarkByParent(String jsoncallback,String id){
		List<CodeDto> queryCodeByParentId =new ArrayList<CodeDto>();
		try {
			if(ObjectHelper.isNotEmpty(id)){
				queryCodeByParentId = CED.queryCodeByParentId(id);
				if(ObjectHelper.isNotEmpty(queryCodeByParentId)){
					for (CodeDto codeDto : queryCodeByParentId) {
						codeDto.setIsDefault(false);
					}
				}
			}
			
		} catch (Exception e) {
			logger.error("getMarkByParent", e);
			e.printStackTrace();
		}
		
		return ObjectHelper.objectToJson(queryCodeByParentId, jsoncallback);
		
	}
	
	/**
	 * 
	 * @Title: getAllMarkByParent 
	 * @Description: 通过父级id获取子类
	 * @author xj 
	 * @param firstMark
	 * @param secondMark
	 * @param jsoncallback
	 * @return
	 */
	@RequestMapping("/getAllMarkByParent")
	@UriKey(key = "com.cnfh.rms.casemanage.complianceReview.getAllMarkByParent")
	@ResponseBody
	public String getMarkByParent(String firstMark,String secondMark,String threeMark ,String complianceReviewId,String jsoncallback){
		ResponseMsg msg = new ResponseMsg();
		msg.setMsg("列表查询成功");
		msg.setResultStatus(ResponseMsg.SUCCESS);
		//获取以前所有被选中的 用于回显负责人
		Map<String, ReviewInformation> riMap = new HashMap<String,ReviewInformation>();
		if(ObjectHelper.isNotEmpty(complianceReviewId)){
			List<ReviewInformation> reviewInformations = reviewInformationService.findByComplianceReviewId(complianceReviewId);
			if(ObjectHelper.isNotEmpty(reviewInformations)){
				for (ReviewInformation reviewInformation : reviewInformations) {
					riMap.put(reviewInformation.getThirdMarkCode(), reviewInformation);
				}
			}
		}
		
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		try {
			if(ObjectHelper.isNotEmpty(secondMark)){
				//一级fullcode
				String firstMarkFullCode =firstMark;
				//一级fullcodeName
				String firstMarkFullCodeName =CED.loadSimpleCodeNameByFullCode(firstMark);
				//二级fullcode
				String secondMarkFullCode =firstMark;
				//二级fullcodeName
				String secondMarkFullCodeName =CED.loadSimpleCodeNameByFullCode(secondMark);
				//二级做完父类查询三级
				List<CodeDto> threeList = CED.queryCodeByParentId(secondMark);
				list = this.capsulationThreeMark(firstMarkFullCode,firstMarkFullCodeName,secondMarkFullCode,secondMarkFullCodeName,riMap,threeList);
				
				//如果只选择了第一季
			}else if(ObjectHelper.isNotEmpty(firstMark)){
				//一级fullcode
				String firstMarkFullCode =firstMark;
				//一级fullcodeName
				String firstMarkFullCodeName =CED.loadSimpleCodeNameByFullCode(firstMark);
				List<CodeDto> secondList = CED.queryCodeByParentId(firstMark);
				if(ObjectHelper.isNotEmpty(secondList)){
					for (CodeDto codeDto : secondList) {
						//二级fullcode
						String secondMarkFullCode =codeDto.getFullCode();
						//二级fullcodeName
						String secondMarkFullCodeName =codeDto.getName();
						if(ObjectHelper.isNotEmpty(secondMarkFullCode)){
							List<CodeDto> threeList = CED.queryCodeByParentId(secondMarkFullCode);
							List<Map<String, Object>> capsulationThreeMark = this.capsulationThreeMark(firstMarkFullCode,firstMarkFullCodeName,secondMarkFullCode,secondMarkFullCodeName,riMap,threeList);
							list.addAll(capsulationThreeMark);
						}
					}
				}
			}else{
				//都为空，全部查询 查询第一级
				List<CodeDto> fistList = new ArrayList<CodeDto>();
				List<SimpleCodeDto> simpleCodeDtos = CED.querySimpleCodeByCategoryId("YWDM0083");
				for (SimpleCodeDto simpleCodeDto : simpleCodeDtos) {
					if(ObjectHelper.isEmpty(simpleCodeDto.getPid())){
						CodeDto codeDto3 = new CodeDto();
						codeDto3.setFullCode(simpleCodeDto.getFullCode());
						codeDto3.setName(simpleCodeDto.getName());
						fistList.add(codeDto3);
					 }
				}
				if(ObjectHelper.isNotEmpty(fistList)){
					for (CodeDto codeDto : fistList) {
						//一级fullcode
						String firstMarkFullCode =codeDto.getFullCode();
						//一级fullcodeName
						String firstMarkFullCodeName =codeDto.getName();
						List<CodeDto> secondList = CED.queryCodeByParentId(firstMarkFullCode);
						if(ObjectHelper.isNotEmpty(secondList)){
							for (CodeDto secondCodeDto : secondList) {  
								//二级fullcode
								String secondMarkFullCode =secondCodeDto.getFullCode();
								//二级fullcodeName
								String secondMarkFullCodeName =secondCodeDto.getName();
								if(ObjectHelper.isNotEmpty(secondMarkFullCode)){
									List<CodeDto> threeList = CED.queryCodeByParentId(secondMarkFullCode);
									if(ObjectHelper.isNotEmpty(threeList)){
										List<Map<String, Object>> capsulationThreeMark = this.capsulationThreeMark(firstMarkFullCode,firstMarkFullCodeName,secondMarkFullCode,secondMarkFullCodeName,riMap,threeList);
										list.addAll(capsulationThreeMark);
									}
									
								}
							}
						}
					}
				}
				
			
			}
		} catch (Exception e) {
			logger.error("getMarkByParent", e);
			e.printStackTrace();
		}
		msg.setTotal(Long.valueOf(list.size()));
		msg.setRows(list);
		return ObjectHelper.objectToJson(msg, jsoncallback);
	}
	
	/**
	 * 
	 * @Title: capsulationThreeMark 
	 * @Description: 封装三级复核信息
	 * @author xj 
	 * @param firstMarkFullCode 一级code
	 * @param firstMarkFullCodeName 一级name
	 * @param secondMarkFullCode 二级code
	 * @param secondMarkFullCodeName 二级name
	 * @param riMap 数据库原有的复核信息
	 * @param threeList 三级复核信息
	 * @return
	 */
	private List<Map<String,Object>> capsulationThreeMark(String firstMarkFullCode, String firstMarkFullCodeName, String secondMarkFullCode,
			String secondMarkFullCodeName, Map<String, ReviewInformation> riMap, List<CodeDto> threeList) {
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		if(ObjectHelper.isNotEmpty(threeList)){
			for (CodeDto codeDto : threeList) {
				Map<String, Object> map = new HashMap<String, Object>();
				//一级标示
				map.put("firstMarkFullCode", firstMarkFullCode);
				map.put("firstMarkFullCodeName", firstMarkFullCodeName);
				//二级标示
				map.put("secondMarkFullCode", secondMarkFullCode);
				map.put("secondMarkFullCodeName", secondMarkFullCodeName);
				//三级标示
				map.put("threeMarkFullCode", codeDto.getFullCode());
				map.put("threeMarkFullCodeName",ObjectHelper.isNotEmpty(riMap.get(codeDto.getFullCode())));
				map.put("threeMarkFullCodeNameText", codeDto.getName());
				map.put("personLiableName", "");
				map.put("personLiableCode", "");
				if(ObjectHelper.isNotEmpty(riMap.get(codeDto.getFullCode()))){
					map.put("personLiableName", riMap.get(codeDto.getFullCode()).getPersonLiableName());
					map.put("personLiableCode", riMap.get(codeDto.getFullCode()).getPersonLiableCode());
				}
				list.add(map);
			}
		}
		return list;
		
	}
	
	/**
	 * 
	 * @Title: findMaterialPromiseByCaseApplyId 
	 * @Description: 查询或者初始化合规复核后补资料
	 * @author xj 
	 * @param caseApplyId 案件id
	 * @param jsoncallback
	 * @return
	 */
	@RequestMapping("/findMaterialPromiseByCaseApplyId")
	@UriKey(key = "com.cnfh.rms.casemanage.complianceReview.findMaterialPromiseByCaseApplyId")
	@ResponseBody
	public String findMaterialPromiseByCaseApplyId(String caseApplyId,String jsoncallback){
		ResponseMsg msg = new ResponseMsg();
		msg.setMsg("列表查询成功");
		msg.setResultStatus(ResponseMsg.SUCCESS);
		List<MaterialPromiseVo> reault = new ArrayList<MaterialPromiseVo>();
		try {
			EmpDto loginUser = CED.getLoginUser();
			MaterialPromiseVo materialPromiseVo1 = new MaterialPromiseVo();
			MaterialPromise materialPromise1 = materialPromiseService.findByCaseApplyIdAndMaterialTypeCode(caseApplyId, ComplianceReviewController.NOTARIAL_DEED_CODE);
			if(ObjectHelper.isEmpty(materialPromise1)){
				//办理人
				materialPromiseVo1.setOperatorCode(loginUser.getEmpCd());
				materialPromiseVo1.setOperatorName(loginUser.getEmpNm());
				//后补资料类型
				materialPromiseVo1.setMaterialTypeCode(ComplianceReviewController.NOTARIAL_DEED_CODE);
				//后补资料NAME
				materialPromiseVo1.setMaterialTypeName(ComplianceReviewController.NOTARIAL_DEED_NAME);
				//父级资料类型
				materialPromiseVo1.setpMaterialTypeCode(ComplianceReviewController.PARENT_NOTARIAL_DEED_CODE);
				//父级资料NAME
				materialPromiseVo1.setpMaterialTypeName(ComplianceReviewController.PARENT_NOTARIAL_DEED_NAME);
			}else{
				materialPromiseVo1 = new MaterialPromiseVo(materialPromise1);
				materialPromiseVo1.setOperatorCode(loginUser.getEmpCd());
				materialPromiseVo1.setOperatorName(loginUser.getEmpNm());
			}
			//组装数据 其他 
			MaterialPromiseVo materialPromiseVo2 = new MaterialPromiseVo();
			MaterialPromise materialPromise2 = materialPromiseService.findByCaseApplyIdAndMaterialTypeCode(caseApplyId, ComplianceReviewController.NOTARIAL_OTHER_CODE);
			if(ObjectHelper.isEmpty(materialPromise2)){
				materialPromiseVo2.setOperatorCode(loginUser.getEmpCd());
				materialPromiseVo2.setOperatorName(loginUser.getEmpNm());
				//后补资料类型
				materialPromiseVo2.setMaterialTypeCode(ComplianceReviewController.NOTARIAL_OTHER_CODE);
				//后补资料NAME
				materialPromiseVo2.setMaterialTypeName(ComplianceReviewController.NOTARIAL_OTHER_NAME);
				//父级资料类型
				materialPromiseVo2.setpMaterialTypeCode(ComplianceReviewController.PARENT_NOTARIAL_OTHER_CODE);
				//父级资料NAME
				materialPromiseVo2.setpMaterialTypeName(ComplianceReviewController.PARENT_NOTARIAL_OTHER_NAME);
			}else{
				materialPromiseVo2 = new MaterialPromiseVo(materialPromise2);
				materialPromiseVo2.setOperatorCode(loginUser.getEmpCd());
				materialPromiseVo2.setOperatorName(loginUser.getEmpNm());
			}
			reault.add(materialPromiseVo1);
			reault.add(materialPromiseVo2);
			msg.setRows(reault);
		} catch (Exception e) {
			logger.error("合规复核——后补资料列表:", e);
			e.printStackTrace();
			msg.setMsg("后补资料列表查询失败，请联系管理员！");
			msg.setResultStatus(ResponseMsg.APP_ERROR);
		}
		
		
		return ObjectHelper.objectToJson(msg, jsoncallback);
	}
	
	
}
