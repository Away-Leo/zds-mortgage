package com.zdsoft.finance.businesssetting.service.impl.rule;

import java.util.List;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zdsoft.finance.businesssetting.service.QuestionJudgeRuleService;
import com.zdsoft.finance.customer.entity.BeforeCustomer;
import com.zdsoft.finance.customer.entity.BeforePersonalCustomer;
import com.zdsoft.finance.customer.service.BeforeCustomerService;
import com.zdsoft.finance.customer.service.BeforePersonalCustomerService;
import com.zdsoft.finance.marketing.entity.HouseProperty;
import com.zdsoft.finance.marketing.entity.PropertyOwner;
import com.zdsoft.finance.marketing.service.HousePropertyService;
import com.zdsoft.finance.spi.businesssetting.QuestionRuleParamDto;
import com.zdsoft.framework.core.common.annotation.Log;
import com.zdsoft.framework.core.common.util.ObjectHelper;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title: PropertyOwnerInfoServiceImpl.java 
 * @ClassName: PropertyOwnerInfoServiceImpl 
 * @Description: 产权人个人基本信息核实---目前是否有子女
 * @author jincheng 
 * @date 2017年3月1日 下午5:44:10 
 * @version V1.0
 */
@Service("propertyOwnerInfoService")
public class PropertyOwnerInfoServiceImpl implements QuestionJudgeRuleService {

	@Autowired
	private HousePropertyService housePropertyService;
	
	@Autowired
	private BeforeCustomerService beforeCustomerService;
	
	@Autowired
	private BeforePersonalCustomerService beforePersonalCustomerService;
	
	@Log
	private Logger log; 
	
	@Override
	@Transactional(readOnly=true)
	public boolean analysisJudgeRule(QuestionRuleParamDto paramDto) {
		boolean bool=false;
		String caseApplyId=paramDto.getCaseApplyId();
		List<HouseProperty> housePropertyList = housePropertyService.findByCaseApplyId(caseApplyId);
		log.error("问卷题库进入propertyOwnerInfoService案件id{}",caseApplyId);
		log.error("需要回答的问题:{}",paramDto.getQuestionContent());
		for (HouseProperty houseProperty : housePropertyList) {
			for (PropertyOwner propertyOwner : houseProperty.getPropertyOwnerList()) {
				log.error("问卷题库进入propertyOwnerInfoService产权人{}",propertyOwner.getOwnerName());
				BeforeCustomer bc=beforeCustomerService.findByCredentialTypeAndCredentialNo("YWDM002501", propertyOwner.getCredentialNo(),caseApplyId);
				BeforePersonalCustomer bpc=beforePersonalCustomerService.findById(bc.getId());
				if(ObjectHelper.isNotEmpty(bpc)){
					//				YWDM0011301	未婚
					//				YWDM0011302	已婚
					//				YWDM0011303	初婚
					//				YWDM0011304	再婚
					//				YWDM0011305	复婚
					//				YWDM0011306	丧偶
					//				YWDM0011307	离婚
					//				YWDM0011308	未说明的婚姻状况
					if("YWDM0011302".equals(bpc.getMaritalStatus())){//已婚
						log.error("问卷题库进入判断已婚:YWDM0011302");
						bool=true;
						break;
					}
					if("YWDM0011307".equals(bpc.getMaritalStatus())){//离异
						log.error("问卷题库进入判断离异:YWDM0011307");
						bool=true;
						break;
					}
					if("YWDM0011306".equals(bpc.getMaritalStatus())){//丧偶
						log.error("问卷题库进入判断丧偶:YWDM0011306");
						bool=true;
						break;
					}
				}
			}
		}
		return bool;
	}
}
