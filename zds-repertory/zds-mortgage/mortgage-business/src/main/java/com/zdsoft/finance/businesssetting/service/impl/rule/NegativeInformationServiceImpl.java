package com.zdsoft.finance.businesssetting.service.impl.rule;

import java.util.List;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zdsoft.finance.businesssetting.service.QuestionJudgeRuleService;
import com.zdsoft.finance.customer.entity.BlanckList;
import com.zdsoft.finance.customer.service.BlanckListService;
import com.zdsoft.finance.marketing.entity.CaseApplyBeforeCustomer;
import com.zdsoft.finance.marketing.service.CaseApplyBeforeCustomerService;
import com.zdsoft.finance.spi.businesssetting.QuestionRuleParamDto;
import com.zdsoft.framework.core.common.annotation.Log;
import com.zdsoft.framework.core.common.util.ObjectHelper;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title: NegativeInformationServiceImpl.java 
 * @ClassName: NegativeInformationServiceImpl 
 * @Description: 所有人负面信息核实
 * @author jincheng 
 * @date 2017年3月2日 上午10:06:36 
 * @version V1.0
 */
@Service("negativeInformationService")
public class NegativeInformationServiceImpl implements QuestionJudgeRuleService {

//	@Autowired
//	private HuifaResultInfoRepository huifaResultInfoRepository;
	
	@Autowired
	private CaseApplyBeforeCustomerService caseApplyBeforeCustomerService;
	
	@Autowired
	private BlanckListService  blanckListService;
	
//	@Autowired
//	private DaasBasicRepository daasBasicRepository;
	
	@Log
	private Logger log; 
	 
	@Override
	public boolean analysisJudgeRule(QuestionRuleParamDto paramDto) {
		boolean bool=false;
		String caseApplyId=paramDto.getCaseApplyId();
		
		log.error("问卷题库进入negativeInformationService案件id{}",caseApplyId);
		log.error("需要回答的问题:{}",paramDto.getQuestionContent());
		List<CaseApplyBeforeCustomer> beforeCustomerList=caseApplyBeforeCustomerService.queryByCaseApplyId(caseApplyId);
		for(CaseApplyBeforeCustomer cbc:beforeCustomerList){
			BlanckList bt=blanckListService.findByCredentialNo(cbc.getBeforeCustomer().getCredentialNo());
			if(ObjectHelper.isNotEmpty(bt)){
				log.error("问卷题库进入判断案件客户关系人员是否在黑名单");
				bool=true;
				break;
			}
			
//			DaasBasic dbc=daasBasicRepository.findByRegno(cbc.getBeforeCustomer().getCredentialNo());
//			if(ObjectHelper.isNotEmpty(dbc)){
//				String orderId=dbc.getOrderId();
//				
//				
//				
//				
//				
//			}
		}
		
		//1、征信逾期超标有无相应凭证
		//1.1、触发条件：资调-征信信息-主借人逾期征信信息概览（最近12个月）中超标笔数>0 ；{取征信汇总表。表待设计直接取字段值}
		
		
		//2、法院诉讼有无相应凭证
	   //2.1、触发条件：资调-风险信息-企业法院信息/个人法院信息中有数据；{T_HUIFA_RESULT_INFO（汇法请求结果详细信息） 关联 T_HUIFA_REQUEST（汇法请求数据），其中T_HUIFA_RESULT_INFO 存数据}
		
		
		//3、工商负面信息有无相应凭证
	   //3.1、{T_DAAS_ORDER_REQUEST（订单请求信息）T_DAAS_BASIC（企业照面信息）关联 T_DAAS_PUNISHBREAK（失信被执行人信息） 关联T_DAAS_CASEINFO（行政处罚历史信息） 关联 T_DAAS_PUNISHED（被执行人信息） 其中 T_DAAS_PUNISHBREAK 、T_DAAS_CASEINFO、 T_DAAS_PUNISHED 存在数据即命中}
			
		//4、税务负面信息有无相应凭证
	 //4.1、{T_HUIFA_RESULT_INFO（汇法请求结果详细信息） 关联 T_HUIFA_REQUEST（汇法请求数据），其中T_HUIFA_RESULT_INFO 存数据 其中TYPE字段 欠税名单、纳税非正常户 存在数据}
			
			
		//5、黑名单有无相应凭证
	//5.1、触发条件：是否在黑名单中cust_blacklist；
		
		
		return bool;
	}
}
