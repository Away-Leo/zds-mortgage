package com.zdsoft.finance.businesssetting.service.impl.rule;

import java.util.List;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zdsoft.finance.businesssetting.service.QuestionJudgeRuleService;
import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.finance.customer.entity.BeforeCustomer;
import com.zdsoft.finance.customer.entity.BeforeWorkUnit;
import com.zdsoft.finance.customer.service.BeforeWorkUnitService;
import com.zdsoft.finance.marketing.entity.CaseApplyBeforeCustomer;
import com.zdsoft.finance.marketing.service.CaseApplyBeforeCustomerService;
import com.zdsoft.finance.spi.businesssetting.QuestionRuleParamDto;
import com.zdsoft.framework.core.common.annotation.Log;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title: CompanyCapitalTransferServiceImpl.java 
 * @ClassName: CompanyCapitalTransferServiceImpl 
 * @Description: 申请人/用款人（含配偶）资调环节-公司
 * @author jincheng 
 * @date 2017年3月2日 上午10:06:36 
 * @version V1.0
 */
@Service("companyCapitalTransferService")
public class CompanyCapitalTransferServiceImpl implements QuestionJudgeRuleService {
	
	@Autowired
	private CaseApplyBeforeCustomerService caseApplyBeforeCustomerService;
	
	@Autowired
	private BeforeWorkUnitService beforeWorkUnitService;
	
	@Log
	private Logger log; 
	
	@Override
	public boolean analysisJudgeRule(QuestionRuleParamDto paramDto) {
		boolean bool=false;
		String caseApplyId=paramDto.getCaseApplyId();
		try {
			List<BeforeCustomer> beforeCustomerList=caseApplyBeforeCustomerService.findCustomerByCaseApplyIdAndJoinType(caseApplyId, CaseApplyBeforeCustomer.MAIN_BORROW);
			String	customerId=beforeCustomerList.get(0).getId();
		//1、公司所属行业是否为我司禁入行业
		//1.1、触发条件：资调-客户信息中工作单位信息的职位：法人/董事/股东，名义控制人，实际控制人；{cust_before_personal（贷前个人客户信息） 和 （cust_before_work_unit）（贷前工作单位） 关联，职位为 “法人/董事/股东，名义控制人，实际控制人”其中的所属行业为 待确认，可列举}
		
		//2、工资发放情况
	   //2.1、触发条件：资调-客户信息中工作单位信息的职位：法人/董事/股东，名义控制人，实际控制人；{cust_before_personal（贷前个人客户信息） 和 （cust_before_work_unit）（贷前工作单位） 关联，职位为 “法人/董事/股东，名义控制人，实际控制人”}
		
		//3、生产经营情况
	   //3.1、触发条件：资调-客户信息中工作单位信息的职位：法人/董事/股东，名义控制人，实际控制人；{cust_before_personal（贷前个人客户信息） 和 （cust_before_work_unit）（贷前工作单位） 关联，职位为 “法人/董事/股东，名义控制人，实际控制人”}
			
		//4、是否能够通过经营场所照片核实实际经营产品种类及经营状况
	 //4.1、触发条件：资调-客户信息中工作单位信息的职位：法人/董事/股东，名义控制人，实际控制人；{cust_before_personal（贷前个人客户信息） 和 （cust_before_work_unit）（贷前工作单位） 关联，职位为 “法人/董事/股东，名义控制人，实际控制人”}
//			YWDM008801	法人/董事/股东
//			YWDM008802	高级管理
//			YWDM008803	中级管理
//			YWDM008804	低级管理
//			YWDM008805	一般员工
//			YWDM008806	公司名义控制人
//			YWDM008807	公司实际控制人
//			YWDM008808	其他
			log.error("问卷题库进入companyCapitalTransferService客户id{}",customerId);
			log.error("需要回答的问题:{}",paramDto.getQuestionContent());
			List<BeforeWorkUnit> bwuList=beforeWorkUnitService.queryByCustomerId(customerId);
			for(BeforeWorkUnit bwu:bwuList){
				if("YWDM008801".equals(bwu.getPosition())){
					log.error("问卷题库进入判断法人/董事/股东:YWDM008801");
					bool=true;
					break;
				}
				if("YWDM008806".equals(bwu.getPosition())){
					log.error("问卷题库进入判断公司名义控制人:YWDM008806");
					bool=true;
					break;
				}
				if("YWDM008807".equals(bwu.getPosition())){
					log.error("问卷题库进入判断公司实际控制人:YWDM008807");
					bool=true;
					break;
				}
			}
		 } catch (BusinessException e) {
			e.printStackTrace();
		}
		return bool;
	}
}
