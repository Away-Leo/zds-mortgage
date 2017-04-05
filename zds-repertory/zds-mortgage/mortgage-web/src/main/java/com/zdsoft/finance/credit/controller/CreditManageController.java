package com.zdsoft.finance.credit.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zdsoft.essential.client.service.CED;
import com.zdsoft.essential.dto.basedata.AttachmentDto;
import com.zdsoft.finance.common.enums.ENUM_MEDIA_TYPE;
import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.finance.credit.dto.CreditReceiveDto;
import com.zdsoft.finance.credit.entity.CreditSituation;
import com.zdsoft.finance.credit.entity.CustomerCreditStatistics;
import com.zdsoft.finance.credit.entity.HmQuery;
import com.zdsoft.finance.credit.service.CreditManageService;
import com.zdsoft.finance.credit.service.CreditSituationService;
import com.zdsoft.finance.credit.service.CustomerCreditStatisticsService;
import com.zdsoft.finance.credit.service.HmQueryService;
import com.zdsoft.finance.credit.vo.CustomerCreditStatisticsVo;
import com.zdsoft.finance.marketing.entity.CaseApply;
import com.zdsoft.finance.marketing.service.CaseApplyService;
import com.zdsoft.framework.core.common.annotation.Log;
import com.zdsoft.framework.core.common.util.ObjectHelper;
import com.zdsoft.framework.core.commweb.annotation.UriKey;
import com.zdsoft.framework.core.commweb.component.BaseController;


/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title: CreditManageController.java 
 * @ClassName: CreditManageController 
 * @Description: 征信管理
 * @author gufeng 
 * @date 2017年2月17日 下午3:47:04 
 * @version V1.0
 */
@Controller
@RequestMapping("/creditManage")
public class CreditManageController extends BaseController{
	
	@Autowired
	private CreditManageService creditManageService;
	
	@Autowired
	private CustomerCreditStatisticsService customerCreditStatisticsService;
	
	@Autowired
	private CaseApplyService caseApplyService;
	
	@Log
	private Logger logger;
	
	@Autowired
	private HmQueryService hmQueryService;
	
	@Autowired
	private CreditSituationService creditSituationService;
	
	@Autowired
	private CED CED;
	
	/**
	 * @Title: initCustomerCreditViews 
	 * @Description: 进入客户征信查看页面
	 * @author jingyh 
	 * @param req
	 * @param customerIds
	 * 			客户Id字符串(多个以逗号分隔)
	 * @param mainCustomerId
	 * 			主借人客户Id
	 * @return
	 */
	@RequestMapping("/initCustomerCreditViews")
	@UriKey(key = "com.zdsoft.finance.creditManage.initCustomerCreditViews")
	public ModelAndView initCustomerCreditViews(HttpServletRequest req,String caseApplyId,String caseApplyStage, String customerIds,String mainCustomerId) {
		logger.info("进入征信统计查看页面");
		logger.debug("客户Ids：{}", customerIds);
		logger.debug("主借人Id：{}", mainCustomerId);
		logger.debug("案件Id：{}", caseApplyId);
		logger.debug("案件阶段：{}", caseApplyStage);
		String viewName = "creditmanage/customer_credit_info_view";
		String tabFlg = req.getParameter("isTab");
		if ("true".equalsIgnoreCase(tabFlg)) {
			// tab打开
			 viewName = "creditmanage/customer_credit_info_view_tab";
		}
		ModelMap model =  new ModelMap();
		if (ObjectHelper.isNotEmpty(customerIds) && ObjectHelper.isNotEmpty(caseApplyId)) {
			try {
				if (ObjectHelper.isEmpty(caseApplyStage)) {
					CaseApply caseApply = caseApplyService.findOne(caseApplyId);
					if (ObjectHelper.isEmpty(caseApply)) {
						throw new BusinessException("10010002", "未找到对应的案件信息!案件Id：" + caseApplyId);
					}
					caseApplyStage = caseApply.getStage();
				}
				String[] ids = customerIds.split(",");
				List<CustomerCreditStatistics> creditList = customerCreditStatisticsService.findByCustomerIdsAndCaseApplyIdAndCaseApplyStage(caseApplyId,Arrays.asList(ids),caseApplyStage);
				// 同一可以，优先使用线下数据，去除重复
				List<CustomerCreditStatistics> newCredtiList = null;
				if (ObjectHelper.isNotEmpty(creditList)) {
					List<String> offLineCustomerIds = new ArrayList<String>();
					newCredtiList = new ArrayList<CustomerCreditStatistics>();
					for (CustomerCreditStatistics entity : creditList) {
						if (CustomerCreditStatistics.SOURCE_OFFLINE.equals(entity.getSourceFrom())) {
							offLineCustomerIds.add(entity.getCustomerId());
						}
						if (CustomerCreditStatistics.SOURCE_ONLINE.equals(entity.getSourceFrom()) && offLineCustomerIds.contains(entity.getCustomerId())) {
							// 依据客户Id，线上数据存在，且线下数据也存在，则不使用线上数据
							continue;
						}
						newCredtiList.add(entity);
					}
				}
				List<CustomerCreditStatisticsVo> otherCredits = new ArrayList<CustomerCreditStatisticsVo>();
				if (ObjectHelper.isNotEmpty(newCredtiList)) {
					for (CustomerCreditStatistics entity : newCredtiList) {
						if (ObjectHelper.isNotEmpty(mainCustomerId) && mainCustomerId.equals(entity.getCustomerId())) {
							// 主借人征信统计信息
							model.put("mianCreditInfo", new CustomerCreditStatisticsVo(entity));
						} else {
							// 其他人的征信信息
							otherCredits.add(new CustomerCreditStatisticsVo(entity));
						}
					}
				}
				model.put("otherCreditInfos", otherCredits);
			} catch (Exception e) {
				e.printStackTrace();
				logger.error("查询征信信息发生错误！", e);
				model.put("errorMsg", e.getMessage());
			}
			
		}
		return new ModelAndView(viewName, model);
	}
	
	/**
	 * 
	 * @Title: initCustomerCreditDetailReportView 
	 * @Description: 查看征信征信报告页面
	 * @author jingyh 
	 * @param req
	 * @param customerId
	 * @return
	 */
	@RequestMapping("/initCustomerCreditDetailReportView")
	@UriKey(key = "com.zdsoft.finance.creditManage.initCustomerCreditDetailReportView")
	public ModelAndView initCustomerCreditDetailReportView(HttpServletRequest req, String statisticsId) {
		logger.info("进入征信报告查看页面");
		logger.debug("statisticsId:{}", statisticsId);
		ModelMap model = new ModelMap();
		try {
			if (ObjectHelper.isNotEmpty(statisticsId)) {
				CustomerCreditStatistics creditStatistics = this.customerCreditStatisticsService.findById(statisticsId);
				if (ObjectHelper.isNotEmpty(creditStatistics)) {
					// 统计信息不为空!
					// 附件Id集合
					List<AttachmentDto> attachmentFiles = new ArrayList<AttachmentDto>();
					if (CustomerCreditStatistics.SOURCE_OFFLINE.equals(creditStatistics.getSourceFrom())) {
						// 来源线下，通过HmQuery获取征信id
						HmQuery query = this.hmQueryService.findOne(creditStatistics.getSourceMarkId());
						if (ObjectHelper.isNotEmpty(query)) {
							AttachmentDto attachmentDto = CED.findAttachmentById(query.getReportPdf());
							if (ObjectHelper.isNotEmpty(attachmentDto)) {
								attachmentFiles.add(attachmentDto);
							}
						}
					} else if (CustomerCreditStatistics.SOURCE_ONLINE.equals(creditStatistics.getSourceFrom())) {
						// 线上录入，获得对应的照片的信息
						CreditSituation situation = creditSituationService.findOne(creditStatistics.getSourceMarkId());
						if (ObjectHelper.isNotEmpty(situation)) {
							attachmentFiles = creditSituationService.findAttachments(situation.getCreditId());
						}
						
					}
					// 若有PDF以pdf为准,文件类型：ENUM_MEDIA_TYPE
					if (ObjectHelper.isNotEmpty(attachmentFiles)) {
						// 附件信息存在
						AttachmentDto pdfInfo = null;
						List<AttachmentDto> imgFiles = new ArrayList<AttachmentDto>();
						Boolean isPdfReport = false;
						for (AttachmentDto file : attachmentFiles) {
							String fileNmSuffix = file.getFileNm().substring(file.getFileNm().lastIndexOf(".") + 1);
							if (ENUM_MEDIA_TYPE.IMG.types.contains(fileNmSuffix)) {
								// 图片的类型
								imgFiles.add(file);
							} else if (pdfInfo == null && "pdf".equalsIgnoreCase(fileNmSuffix)) {
								// pdf类型
								pdfInfo = file;
								isPdfReport = true;
							}
						}
						model.put("isPdfReport", isPdfReport);
						model.put("pdfInfo", pdfInfo);
						model.put("imgFiles", imgFiles);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("查询客户征信报告出错！", e);
			model.put("errorMsg", e.getMessage());
		}
		return new ModelAndView("creditmanage/customer_credit_detail_report_view",model);
	}
	
	/**
	 * @Title: creditReceive 
	 * @Description: 接收请求入口
	 * @author gufeng 
	 * @param dto 接收dto
	 * @return 接收信息
	 */
	@RequestMapping("/creditReceive")
	@ResponseBody
	@UriKey(key = "com.zdsoft.finance.creditManage.creditReceive")
	public String creditReceive(CreditReceiveDto dto){
		Map<String,Object> map = new HashMap<String, Object>();
		try{
			logger.debug("接受征信数据");
			logger.debug("FuncName:{}", dto.getFuncName());
			logger.debug("QueryCredNum:{}", dto.getQueryCredNum());
			logger.debug("QueryName:{}", dto.getQueryName());
			logger.debug("QueryCertType:{}", dto.getQueryCertType());
			logger.debug("ObjectId:{}", dto.getObjectId());
			creditManageService.receiveData(dto);
			map.put("code", "00");
			map.put("mes", "接收成功");
		}catch(Exception e){
			e.printStackTrace();
			logger.error("=====controller",e);
			map.put("code", "08");
			map.put("mes", e.getMessage());
		}
		return ObjectHelper.objectToJson(map);
	}
	
	/**
	 * @Title: testRequest 
	 * @Description: 测试请求
	 * @author gufeng 
	 * @return 测试结果
	 */
	/*@RequestMapping("/testRequest")
	@ResponseBody
	public ResponseMsg testRequest(String caseApplyId){
		ResponseMsg msg = new ResponseMsg();
		try{
			//请求发送测试
//			String msgs = creditRequestService.getOutDataInfo(caseApplyId);
			caseApplyId = "4028a1205ab6ccf6015ab6d4cb010000";
			String creditNo = "320502198011163018";
			String creditName = "蒋波";
			String msgs = "";
			String objectId = caseApplyId + "_" + DateHelper.dateToString(new Date(), DateHelper.DATE_LONG_SIMPLE_FORMAT);
			String m_certFile = "F://mnt//upload//f263f37c-7635-4bae-8ee4-b2ec23a935a3.jpg";
			String m_authoFile = m_certFile;
			try{
				//如果已经查询直接获取PDF去解析
				//DAL.ControllerFactory.GetNewController(DAL.e_ConsType.Main2).GetByCondition(Risk_BaseServiceLog.EntityType, string.Format("ObjectCardNo='{0}'and RiskID={1} AND DATEDIFF(minute,RequestDatetime,GETDATE())<={2}", creditNo, riskID, limiDate));
				Boolean isEmpty = creditManageService.getCreditData(creditNo,caseApplyId);
				
	            if (isEmpty){
	            	msgs = "姓名：" + creditName + ",身份证：" + creditNo + "，已成功申请，2分钟后方可重查，请等待或联系深圳驻点专员回复后，刷新页面查看征信查询记录!";
	            }
	            Boolean success= this.creditRequestService.RequestInfoURL(objectId, creditName, "0", creditNo, imgToBase64String(m_certFile), imgToBase64String(m_authoFile));
	            if (success == false){
	            	msgs = "查询失败";
	            }
			}catch(Exception e){
				e.printStackTrace();
				logger.error("征信请求:" + e.getMessage());
				msgs = "查询失败";
			}
			msg.setResultStatus(ResponseMsg.SUCCESS);
			msg.setMsg(msgs);
		}catch(Exception e){
			e.printStackTrace();
			logger.error("=====controller",e.getMessage());
			msg.setResultStatus(ResponseMsg.APP_ERROR);
			msg.setMsg("发送失败");
		}
		return msg;
	}*/
	
}
