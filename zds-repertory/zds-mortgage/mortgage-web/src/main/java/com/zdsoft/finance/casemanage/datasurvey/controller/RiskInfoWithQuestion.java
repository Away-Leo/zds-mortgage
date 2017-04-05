package com.zdsoft.finance.casemanage.datasurvey.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.zdsoft.essential.client.service.CED;
import com.zdsoft.finance.businesssetting.entity.QuestionEnum;
import com.zdsoft.finance.businesssetting.entity.Questionnaire;
import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.finance.common.utils.TimeUtil;
import com.zdsoft.finance.customer.entity.BeforeAddress;
import com.zdsoft.finance.customer.entity.BeforePersonalAssociation;
import com.zdsoft.finance.customer.entity.BeforePersonalCustomer;
import com.zdsoft.finance.customer.entity.BeforeWorkUnit;
import com.zdsoft.finance.customer.service.BeforePersonalAssociationService;
import com.zdsoft.finance.customer.service.BeforePersonalCustomerService;
import com.zdsoft.finance.customer.vo.BeforeWorkUnitVo;
import com.zdsoft.finance.marketing.entity.HouseProperty;
import com.zdsoft.finance.marketing.service.HousePropertyService;
import com.zdsoft.framework.core.common.util.ObjectHelper;

/** 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: RiskInfoWithQuestion.java 
 * @ClassName: RiskInfoWithQuestion 
 * @Description: 资调-风险信息-风控评价-获取类
 * @author liuhuan 
 * @date 2017年3月7日 上午11:32:01 
 * @version V1.0 
 */ 
@Component
public class RiskInfoWithQuestion {
	
	@Autowired
	private BeforePersonalCustomerService beforePersonalCustomerService;
	@Autowired
	private CED CED;
	@Autowired
	private BeforePersonalAssociationService beforePersonalAssociationService;
	@Autowired
	private HousePropertyService housePropertyService;
	
	/** 
	 * @Title: getCollateralInfo 
	 * @Description: 获取抵押物综述
	 * @author liuhuan 
	 * @param question 智能问卷问题
	 * @return  
	 */ 
	private String getCollateralInfo(Questionnaire question){
		String property = "";
		if(ObjectHelper.isEquals(question.getQuestionId(), QuestionEnum.CLEAR_PROPERTY_RIGHTS.value)){
			if(ObjectHelper.isEquals(question.getQuestionValue(), "是") || ObjectHelper.isEquals(question.getQuestionValue(), "有")){
				property = "产权:清晰; ";
			}else {
				property = "产权:不清晰; ";
			}
		}
		String commercial = "";
		if(ObjectHelper.isEquals(question.getQuestionId(), QuestionEnum.COMMERCIAL_ATMOSPHERE.value)){
			if(ObjectHelper.isEquals(question.getQuestionValue(), "是") || ObjectHelper.isEquals(question.getQuestionValue(), "有")){
				commercial = "商业氛围:浓厚; ";
			}else {
				commercial = "商业氛围:不浓厚; ";
			}
		}
		String drive = "";
		if(ObjectHelper.isEquals(question.getQuestionId(), QuestionEnum.DRIVE.value)){
			if(ObjectHelper.isEquals(question.getQuestionValue(), "是") || ObjectHelper.isEquals(question.getQuestionValue(), "有")){
				drive = "距机构车程1.5小时:以内; ";
			}else {
				drive = "距机构车程1.5小时:以外; ";
			}
		}
		String standard = "";
		if(ObjectHelper.isEquals(question.getQuestionId(), QuestionEnum.STANDARD.value)){
			if(ObjectHelper.isEquals(question.getQuestionValue(), "是") || ObjectHelper.isEquals(question.getQuestionValue(), "有")){
				standard = "结构:符合我司业务标准; ";
			}else {
				standard = "结构:不符合我司业务标准; ";
			}
		}
		String activity = "";
		if(ObjectHelper.isEquals(question.getQuestionId(), QuestionEnum.ACTIVITY_DEGREE.value)){
			activity = question.getQuestionValue();
		}
		//以下数据可出现为空
		String binding = "";
		if(ObjectHelper.isEquals(question.getQuestionId(), QuestionEnum.BINDING_LOAN.value)){
			binding = "一抵"+question.getQuestionValue()+"捆绑其他贷款信息; ";
		}
		String mianRoad = "";
		if(ObjectHelper.isEquals(question.getQuestionId(), QuestionEnum.MAIN_ROAD.value)){
			mianRoad = question.getQuestionValue() +"为临街商铺或是否为小区主干道商铺; ";
		}
		String lease = "";
		if(ObjectHelper.isEquals(question.getQuestionId(), QuestionEnum.LEASE.value)){
			if(ObjectHelper.isEquals(question.getQuestionValue(), "是") || ObjectHelper.isEquals(question.getQuestionValue(), "有")){
				lease = "带租约; ";
			}else {
				lease = "不带租约; ";
			}
		}
		String returnRate = "";
		if(ObjectHelper.isEquals(question.getQuestionId(), QuestionEnum.RETURN_RATE.value)){
			returnRate = "租金回报率:"+question.getQuestionValue()+"; ";
		}
		String platoon = "";
		if(ObjectHelper.isEquals(question.getQuestionId(), QuestionEnum.PLATOON.value)){
			platoon = "属"+question.getQuestionValue()+";";
		}
		String middleSide = "";
		if(ObjectHelper.isEquals(question.getQuestionId(), QuestionEnum.MIDDLE_SIDE.value)){
			middleSide = "属"+question.getQuestionValue()+"; ";
		}
		String renovation = "";
		if(ObjectHelper.isEquals(question.getQuestionId(), QuestionEnum.RENOVATION.value)){
			renovation = "装修情况:"+question.getQuestionId()+"; ";
		}
		return property+commercial+drive+standard+activity+binding+mianRoad+lease+returnRate+platoon+middleSide+renovation;
	}
	
	/** 
	 * @Title: getBusinessAnalysis 
	 * @Description: 获取业务综析
	 * @author liuhuan 
	 * @param caseApplyId 案件id
	 * @param question	问题
	 * @return
	 * @throws Exception  
	 */ 
	private StringBuffer getBusinessAnalysis(BeforePersonalCustomer customer, StringBuffer businessAnalysis) throws Exception{
		if(ObjectHelper.isEmpty(customer)){
			throw new BusinessException("传入参数为空");
		}
		
		businessAnalysis.append("用款人"+customer.getCustomerName()+", ");
		businessAnalysis = getCustomerInfo(customer,businessAnalysis);
		
		return businessAnalysis;
	}
	
	/** 
	 * @Title: getCustomerInfo 
	 * @Description: 业务综析中的客户信息获取方法
	 * @author liuhuan 
	 * @param customer 需获取信息的客户对象
	 * @param businessAnalysis 需返回的字符串
	 * @return
	 * @throws Exception  
	 */ 
	private StringBuffer getCustomerInfo(BeforePersonalCustomer customer, StringBuffer businessAnalysis) throws Exception{
		if(ObjectHelper.isEmpty(customer)){
			throw new BusinessException("客户信息异常,无实际用款人");
		}
		List<BeforeAddress> addresss = customer.getBeforeAddresss();
		if(ObjectHelper.isEmpty(addresss) || addresss.size() <=0){
			return businessAnalysis.append("无地址信息。");
		}
		BeforeAddress address = addresss.get(0);
		String province = CED.loadSimpleCodeNameByFullCode(address.getProvince());
		String marital = CED.loadSimpleCodeNameByFullCode(customer.getMaritalStatus());
		String degree = CED.loadSimpleCodeNameByFullCode(customer.getDegree());
		
		String age="";
		if (ObjectHelper.isNotEmpty(customer.getBirthdayDate())) {
            Map<String, Object> map = TimeUtil.getMonthCha(customer.getBirthdayDate().toString(),
                    TimeUtil.getCurrentDateInteger().toString());
            int month = Integer.parseInt(map.get("month").toString());
            int ageInt = month / 12;
            age = Integer.toString(ageInt);
        }
		
		businessAnalysis.append(province+"人, ");
		businessAnalysis.append(age+"岁, ");
		businessAnalysis.append(marital+", ");
		businessAnalysis.append(degree+"学历, ");
		return businessAnalysis;
	}
	
	/** 
	 * @Title: getExceptionalCase 
	 * @Description: 特殊情况信息获取
	 * @author liuhuan 
	 * @param question 智能问卷问题
	 * @return  
	 */ 
	private String getExceptionalCase(Questionnaire question){
		StringBuffer exceptionalCase = new StringBuffer("");
		if(ObjectHelper.isEquals(question.getQuestionId(), QuestionEnum.PORNOGRAPHY.value)){
			if(ObjectHelper.isEquals(question.getQuestionValue(), "是") || ObjectHelper.isEquals(question.getQuestionValue(), "有")){
				exceptionalCase.append("用款人和产权人有黄、赌、毒等不良信息,");
			}else {
				exceptionalCase.append("用款人和产权人无黄、赌、毒等不良信息,");
			}
		}
		if(ObjectHelper.isEquals(question.getQuestionId(), QuestionEnum.CREDIT.value)){
			exceptionalCase.append("征信逾期超标"+question.getQuestionValue()+"相应凭证,");
		}
		if(ObjectHelper.isEquals(question.getQuestionId(), QuestionEnum.COURT_PROCEEDINGS.value)){
			exceptionalCase.append("法院诉讼"+question.getQuestionValue()+"相应凭证,");
		}
		if(ObjectHelper.isEquals(question.getQuestionId(), QuestionEnum.BUSINESS_NEGATIVE.value)){
			exceptionalCase.append("工商负面信息"+question.getQuestionValue()+"相应凭证,");
		}
		if(ObjectHelper.isEquals(question.getQuestionId(), QuestionEnum.TAX_INFORMATION.value)){
			exceptionalCase.append("税务负面信息"+question.getQuestionValue()+"相应凭证,");
		}
		if(ObjectHelper.isEquals(question.getQuestionId(), QuestionEnum.BLACKLIST.value)){
			exceptionalCase.append("黑名单"+question.getQuestionValue()+"相应凭证,");
		}
		return exceptionalCase.toString();
	}
	
	/** 
	 * @Title: getManagement 
	 * @Description: 经营情况/工作情况 获取
	 * @author liuhuan 
	 * @param question 智能问卷问题
	 * @param customer 操作的客户对象
	 * @param isMarried 是否结婚
	 * @return
	 * @throws Exception  
	 */ 
	private String getManagement(Questionnaire question, String position) throws Exception{
		if(ObjectHelper.isEmpty(position) || ObjectHelper.isEquals(position, "")){
			return "";
		}
		StringBuffer management = new StringBuffer("");
		//是企业主
		if(BeforeWorkUnitVo.BENEFICIAL_CONTROLLING_OWNER.equals(position) 
				|| BeforeWorkUnitVo.CHAIRMAN_OF_THE_BOARD.equals(position)
				|| BeforeWorkUnitVo.FORMAT_CONTROLLING_OWNER.equals(position)){
			
			if(ObjectHelper.isEquals(question.getQuestionId(), QuestionEnum.BAN_INDUSTRY.value)){
				management.append("公司是否禁入行业:"+question.getQuestionValue()+",\n");
			}
			if(ObjectHelper.isEquals(question.getQuestionId(), QuestionEnum.PAYROLL.value)){
				management.append("工资发放情况:"+question.getQuestionValue()+",\n");
			}
			if(ObjectHelper.isEquals(question.getQuestionId(), QuestionEnum.PRODUCTION_OPERATION.value)){
				management.append("生产经营情况:"+question.getQuestionValue()+",\n");
			}
			if(ObjectHelper.isEquals(question.getQuestionId(), QuestionEnum.CHECK_OPERATION.value)){
				management.append("是否能够通过经营场所照片核实实际经营产品及经营状况:"+question.getQuestionValue()+",\n");
			}
			
		}else {
			if(ObjectHelper.isEquals(question.getQuestionId(), QuestionEnum.TELEPHONE_CHECK.value)){
				management.append("电话核查工作信息是否一致:"+question.getQuestionValue()+",\n");
			}
			if(ObjectHelper.isEquals(question.getQuestionId(), QuestionEnum.FIELD_AUDIT.value)){
				management.append("实地核查工作信息是否一致:"+question.getQuestionValue()+",\n");
			}
		}
		return management.toString();
	}
	
	/** 
	 * @Title: getManagementCustomerInfo 
	 * @Description: 经营情况/工作情况 中的客户信息获取
	 * @author liuhuan 
	 * @param customer 需要查询的客户
	 * @param management 需要修改返回的字符串
	 * @return	
	 * @throws Exception  
	 */ 
	private StringBuffer getManagementCustomerInfo(BeforeWorkUnit workUnit, StringBuffer management) throws Exception{
		String industry = CED.loadSimpleCodeNameByFullCode(workUnit.getIndustry());
		String position = CED.loadSimpleCodeNameByFullCode(workUnit.getPosition());
		
		management.append("就职于"+workUnit.getCompanyName()+",");
		management.append(industry);
		management.append(",工作年限"+workUnit.getWorkYears());
		management.append("年,职务:"+position+".");
		return management;
	}
	
	
	/** 
	 * @Title: getRiskControlEvaluation 
	 * @Description: 获取 资调-风险信息-风控评价
	 * @author liuhuan 
	 * @param questionList 智能问卷问题集
	 * @param caseApplyId 案件id
	 * @return 
	 * 		pledgeReview：抵押物综述
	 * 		businessAnalysis：业务综析
	 *		workSituation：经营情况/工作情况
	 *		specialSituation：特殊情况
	 * @throws Exception  
	 */ 
	public Map<String,String> getRiskControlEvaluation(List<Questionnaire> questionList, String caseApplyId) throws Exception{
		if(ObjectHelper.isEmpty(questionList) || questionList.size() <=0){
			throw new BusinessException("智能问卷为空,没有问题.");
		}
		Map<String,String> riskControl = new HashMap<String,String>();
		
		List<BeforePersonalCustomer> customerList = beforePersonalCustomerService.queryByCaseApplyId(caseApplyId);
		if(ObjectHelper.isEmpty(customerList) || customerList.size() <=0){
			throw new BusinessException("传入参数错误");
		}
		//获取实际用款人
		BeforePersonalCustomer customer = null;
		for(BeforePersonalCustomer customerTemp : customerList){
			if(ObjectHelper.isEquals(customerTemp.getActualUsePerson(), "YWDM0049002")){
				customer = customerTemp;
				break;
			}
		}
		if(ObjectHelper.isEmpty(customer)){
			throw new BusinessException("异常！案件中不存在实际用款人");
		}
		//判断是否已婚
		boolean isMarried = ObjectHelper.isEquals(customer.getMaritalStatus(), "YWDM0011302")  
						|| ObjectHelper.isEquals(customer.getMaritalStatus(), "YWDM0011303")
						|| ObjectHelper.isEquals(customer.getMaritalStatus(), "YWDM0011304")
						|| ObjectHelper.isEquals(customer.getMaritalStatus(), "YWDM0011305");
		
		List<HouseProperty> houseList = housePropertyService.findByCaseApplyId(caseApplyId);
		if(ObjectHelper.isEmpty(houseList) || houseList.size() <=0){
			throw new BusinessException("无房产信息！");
		}
		HouseProperty house = houseList.get(0);
		String province = CED.loadSimpleCodeNameByFullCode(house.getProvince());
		String city = CED.loadSimpleCodeNameByFullCode(house.getCity());
		String district = CED.loadSimpleCodeNameByFullCode(house.getDistrict());
		String address = "地址："+province + city + district + house.getMailingAddress()+"; ";
		
		StringBuffer collateralInfo = new StringBuffer(address);
		
		StringBuffer businessAnalysis = new StringBuffer("");
		businessAnalysis = getBusinessAnalysis(customer,businessAnalysis);
		
		Map<String, String> mapManagement = getWorkSituation(customer,isMarried);
		StringBuffer management = new StringBuffer(mapManagement.get("management"));
		
		StringBuffer exceptionalCase = new StringBuffer("");
		for(Questionnaire question : questionList){
			collateralInfo.append(getCollateralInfo(question));
			
			if(ObjectHelper.isEquals(question.getQuestionId(), QuestionEnum.RESIDENT.value)){
				businessAnalysis.append("同住人为:"+question.getQuestionValue()+"; \n");
			}
			
			management.append(getManagement(question,mapManagement.get("position")));
			
			exceptionalCase.append(getExceptionalCase(question));
		}
		//业务综析的判断
		if(isMarried){
			List<BeforePersonalAssociation> associationList = beforePersonalAssociationService.queryAssociation(BeforePersonalAssociation.SPOUSE, customer.getId());
			if(ObjectHelper.isEmpty(associationList) || associationList.size() <=0){
				throw new BusinessException("客户信息异常,未录入配偶信息");
			}
			BeforePersonalCustomer spouse = associationList.get(0).getBeforePersonalCusomer();
			businessAnalysis.append("用款人配偶"+spouse.getCustomerName()+", ");
			businessAnalysis = getCustomerInfo(spouse,businessAnalysis);
		}
		
		//抵押物综述
		riskControl.put("pledgeReview", collateralInfo.toString());
		//业务综析
		riskControl.put("businessAnalysis", businessAnalysis.toString());
		//经营情况/工作情况
		riskControl.put("workSituation", management.toString());
		//特殊情况
		riskControl.put("specialSituation", exceptionalCase.toString());
		
		return riskControl;
	}
	
	/** 
	 * @Title: getWorkSituation 
	 * @Description: 经营情况/工作情况-部分信息获取
	 * @author liuhuan 
	 * @param customer 需要修改的客户对象
	 * @param isMarried 是否结婚
	 * @return
	 * @throws Exception  
	 */ 
	private Map<String, String> getWorkSituation(BeforePersonalCustomer customer, boolean isMarried) throws Exception{
		StringBuffer management = new StringBuffer("");
		Map<String, String> map = new HashMap<String, String>();
		
		if(ObjectHelper.isEmpty(customer)){
			throw new BusinessException("传入参数为空");
		}
		management.append("用款人"+customer.getCustomerName()+", ");
		List<BeforeWorkUnit> workUnits = customer.getBeforeWorkUnits();
		BeforeWorkUnit workUnit = null;
		boolean bool_customer = false;
		if(ObjectHelper.isEmpty(workUnits) || workUnits.size()<=0){
			management.append("无工作信息.");
		}else {
			for(BeforeWorkUnit workUnits_new : workUnits){
				if(customer.getCustomerName().equals(workUnits_new.getWorkUnitName())){
					management = getManagementCustomerInfo(workUnits_new,management);
					workUnit = workUnits_new;
					bool_customer = true;
					break;
				}
			}
			if(!bool_customer){
				management.append("无工作信息.");
			}
		}
		if(ObjectHelper.isEmpty(workUnit) || ObjectHelper.isEmpty(workUnit.getPosition())){
			map.put("position", "");
		}else{
			map.put("position", workUnit.getPosition());
		}
		
		if(isMarried){
			List<BeforePersonalAssociation> associationList = beforePersonalAssociationService.queryAssociation(BeforePersonalAssociation.SPOUSE, customer.getId());
			if(ObjectHelper.isEmpty(associationList) || associationList.size() <=0){
				throw new BusinessException("客户信息异常,未录入配偶信息");
			}
			BeforePersonalCustomer spouse = associationList.get(0).getBeforePersonalCusomer();
			management.append("用款人配偶"+spouse.getCustomerName()+",");
			boolean bool_spouse = false;
//			List<BeforeWorkUnit> spouseWorkUnits = spouse.getBeforeWorkUnits();
			if(ObjectHelper.isEmpty(workUnits) || workUnits.size() <= 0  ){
				management.append("无工作信息.");
			}else {
				for(BeforeWorkUnit spouseWorkUnit : workUnits){
					if(spouse.getCustomerName().equals(spouseWorkUnit.getWorkUnitName())){
//						management = getManagementCustomerInfo(spouseWorkUnit,management);
						BeforeWorkUnit spouseWorkUnit_new = spouseWorkUnit;
						management = getManagementCustomerInfo(spouseWorkUnit_new,management);
						bool_spouse = true;
							break;
					}
				}
				if(!bool_spouse){
					management.append("无工作信息.");
				}
			}
		}
		map.put("management", management.toString());
		return map;
	}
	
}
