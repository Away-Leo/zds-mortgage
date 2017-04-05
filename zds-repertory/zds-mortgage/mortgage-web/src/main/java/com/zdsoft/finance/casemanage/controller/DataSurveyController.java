package com.zdsoft.finance.casemanage.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zdsoft.bpm.annotation.client.FinishJob;
import com.zdsoft.bpm.annotation.client.ManualJob;
import com.zdsoft.finance.casemanage.datasurvey.entity.MatterModuleValidate;
import com.zdsoft.finance.casemanage.datasurvey.entity.MatterNameEnum;
import com.zdsoft.finance.casemanage.datasurvey.service.MatterModuleValidateService;
import com.zdsoft.finance.casemanage.vo.MatterModuleValidateVo;
import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.finance.customer.entity.BeforePersonalCustomer;
import com.zdsoft.finance.customer.entity.BeforeWorkUnit;
import com.zdsoft.finance.customer.entity.Credit;
import com.zdsoft.finance.customer.service.BeforeWorkUnitService;
import com.zdsoft.finance.customer.service.CreditService;
import com.zdsoft.finance.marketing.entity.CaseApplyBeforeCustomer;
import com.zdsoft.finance.marketing.service.CaseApplyBeforeCustomerService;
import com.zdsoft.finance.risk.entity.HuifaRequest;
import com.zdsoft.finance.risk.huifa.service.BusinessDetailService;
import com.zdsoft.finance.risk.huifa.service.HuifaDetailService;
import com.zdsoft.framework.core.common.dto.ResponseMsg;
import com.zdsoft.framework.core.common.util.ObjectHelper;
import com.zdsoft.framework.core.commweb.annotation.UriKey;
import com.zdsoft.framework.core.commweb.component.BaseController;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: DataSurveyController.java 
 * @ClassName: DataSurveyController 
 * @Description: 资调Controller 
 * @author zhoushichao 
 * @date 2017年3月3日 上午11:15:29 
 * @version V1.0
 */
@Controller
@RequestMapping("/casemanage/datasurvey")
public class DataSurveyController extends BaseController {

	@Autowired
	private MatterModuleValidateService matterModuleValidateService;
	
	@Autowired
	private BusinessDetailService businessDetailService;
	
	@Autowired
	private CaseApplyBeforeCustomerService caseApplyBeforeCustomerService;
	
	@Autowired
	private BeforeWorkUnitService beforeWorkUnitService;
	
	@Autowired
	private CreditService creditService;
	
	@Autowired
	private HuifaDetailService huifaDetailService;
	
	/**
	 * 
	 * @Title: editJob 
	 * @Description: 流程中资调编辑入口
	 * @author zhoushichao 
	 * @param projectId
	 * @param processInstanceId
	 * @param businessKey
	 * @return
	 */
	@RequestMapping(value = "/editJob")
	@UriKey(key = "com.zdsoft.finance.casemanage.datasurvey.editJob")
	@ManualJob(resource = "com.zdsoft.finance.casemanage.datasurvey.editJob", label = "资调编辑")
	public ModelAndView editJob(String projectId, String processInstanceId, String businessKey) {
		ModelMap model = new ModelMap();
		
		try {
			//设置事项名称
			String matterName = MatterNameEnum.DATA_SURVEY.value;
			List<MatterModuleValidate> validateList = matterModuleValidateService.findByBusinessKeyAndMatterName(projectId, matterName);
			List<MatterModuleValidateVo> validateVoList = new ArrayList<MatterModuleValidateVo>();
			for (MatterModuleValidate matterModuleValidate : validateList) {
				MatterModuleValidateVo validateVo = new MatterModuleValidateVo();
				validateVo.setTabName(matterModuleValidate.getTabName());
				validateVo.setExecuteTag(matterModuleValidate.getExecuteTag());
				validateVoList.add(validateVo);
			}
			String validateVoJson = ObjectHelper.objectToJson(validateVoList);
			model.put("validateVoJson", validateVoJson);
			model.put("caseApplyId", projectId);
			model.put("processInstanceId", processInstanceId);
			model.put("businessKey", businessKey);
		} catch (BusinessException e) {
			e.printStackTrace();
			logger.error("进入资调查看事项发生错误！", e);
		}
		
		return new ModelAndView("casemanage/datasurvey/datasurvey_edit", model);
	}

	/**
	 * 
	 * @Title: conditionJobEditSave 
	 * @Description: 资调提交判断
	 * @author zhoushichao 
	 * @param businessKey 流程实例key
	 * @param projectId  案件Id
	 * @return
	 */
	@RequestMapping(value = "/editJobSave")
	@UriKey(key = "com.zdsoft.finance.casemanage.datasurvey.editJobSave")
	@ResponseBody
	@FinishJob(resource = "com.zdsoft.finance.casemanage.datasurvey.editJob", businessId = "businessKey", projectId = "projectId")
	public ResponseMsg conditionJobEditSave(String businessKey, String projectId) {
		ResponseMsg msg = new ResponseMsg();
		logger.info("提交编辑事项");
		logger.debug("projectId：{}", projectId);
		logger.debug("businessKey:{}", businessKey);
		try {
			logger.info("判断工商、汇法、征信信息是否获取完全！");
			// 判断是否获取，工商，汇法，征信信息
			boolean notHaveInfo = false;
			String message = "";
			List<Credit> creditList = creditService.findByCaseApplyId(projectId);
			List<CaseApplyBeforeCustomer> customers = caseApplyBeforeCustomerService.queryByCaseApplyId(projectId);
			for(CaseApplyBeforeCustomer customer : customers){
				if (CaseApplyBeforeCustomer.MAIN_BORROW.equals(customer.getJoinType())) {
					// 只判断主借人是否获得征信
					// 判断征信
					BeforePersonalCustomer personalCustomer = (BeforePersonalCustomer) customer.getBeforeCustomer();
					boolean flg = false;
					for (Credit credit : creditList) {
						// 资调，已获取征信
						if (personalCustomer.getId().equals(credit.getCustomerId()) && Credit.LINK_CODE_SURVEY.equals(credit.getCreditLinkCode())
								&& Credit.LINK_STATUS_SUCCESSFUL.equals(credit.getLinkStatusCode())) {
							flg = true;
							break;
						}
					}
					if (!flg) {
						notHaveInfo = true;
						message += "未获得客户：" + personalCustomer.getCustomerName() + "(" + personalCustomer.getCredentialNo() + ")的征信信息；";
						break;
					}
				}
			}
			if (!notHaveInfo) {
				// 发起汇法工商接口请求
				for(CaseApplyBeforeCustomer customer : customers){
					BeforePersonalCustomer personalCustomer = (BeforePersonalCustomer) customer.getBeforeCustomer();
					logger.info("查询个人汇法=====》"+personalCustomer.getCustomerName()+"===>"+personalCustomer.getCredentialNo());
					this.huifaDetailService.callHuifaInterface(HuifaRequest.STYPE_PERSONAL.toString(), personalCustomer.getCustomerName(), personalCustomer.getCredentialNo(), projectId);
					List<BeforeWorkUnit> workUnits =  beforeWorkUnitService.queryByCustomerId(personalCustomer.getId());
					for(BeforeWorkUnit workUnit : workUnits){
						if(BeforeWorkUnit.CHAIRMAN_OF_THE_BOARD.equals(workUnit.getPosition())){
							logger.info("查询汇法企业信息=====》"+workUnit.getCompanyName());
							this.huifaDetailService.callHuifaInterface(HuifaRequest.STYPE_COMPANY.toString(), workUnit.getCompanyName(), null, projectId);
							logger.info("查询企业工商信息=====》"+workUnit.getCompanyName());
							this.businessDetailService.callBusinessInterface(workUnit.getCompanyName(), projectId, "admin", "admin");
						}
					}
				}
				msg.setResultStatus(ResponseMsg.SUCCESS);
				msg.setMsg("保存成功");
			} else {
				// 未获得征信信息
				msg.setResultStatus(ResponseMsg.APP_ERROR);
				msg.setMsg(message);
			}
		} catch (Exception e) {
			msg.setResultStatus(ResponseMsg.APP_ERROR);
			msg.setMsg("保存失败!" + e.getMessage());
			logger.error("保存失败", e);
			e.printStackTrace();
		}
		return msg;

	}

	/**
	 * 
	 * 资调查看
	 *
	 * @author laijun
	 * @date:2017年1月10日 下午9:39:43
	 * @param projectId
	 * @param processInstanceId
	 * @param businessKey
	 * @return
	 * @throws BusinessException
	 */
	@RequestMapping(value = "/viewJob")
	@UriKey(key = "com.zdsoft.finance.casemanage.datasurvey.viewJob")
	@ManualJob(resource = "com.zdsoft.finance.casemanage.datasurvey.viewJob", label = "资调查看")
	@FinishJob(resource = "com.zdsoft.finance.casemanage.datasurvey.viewJob", businessId = "businessKey", projectId = "projectId")
	public ModelAndView viewJob(String projectId, String processInstanceId, String businessKey) {
		ModelMap model = new ModelMap();
		model.put("caseApplyId", projectId);
		model.put("processInstanceId", processInstanceId);
		model.put("businessKey", businessKey);
		return new ModelAndView("casemanage/datasurvey/datasurvey_view", model);
	}
}
