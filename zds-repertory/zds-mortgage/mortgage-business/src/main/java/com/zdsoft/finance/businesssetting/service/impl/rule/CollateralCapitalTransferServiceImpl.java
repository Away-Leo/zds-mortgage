package com.zdsoft.finance.businesssetting.service.impl.rule;

import java.util.List;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zdsoft.finance.businesssetting.service.QuestionJudgeRuleService;
import com.zdsoft.finance.marketing.entity.HouseProperty;
import com.zdsoft.finance.marketing.service.HousePropertyService;
import com.zdsoft.finance.spi.businesssetting.QuestionRuleParamDto;
import com.zdsoft.framework.core.common.annotation.Log;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title: CollateralCapitalTransferServiceImpl.java 
 * @ClassName: CollateralCapitalTransferServiceImpl 
 * @Description: 资调环节-抵押物
 * @author jincheng 
 * @date 2017年3月2日 上午10:06:36 
 * @version V1.0
 */
@Service("collateralCapitalTransferService")
public class CollateralCapitalTransferServiceImpl implements QuestionJudgeRuleService {

	@Autowired
	private HousePropertyService  housePropertyService;
	
	@Log
	private Logger log;
	
	@Override
	public boolean analysisJudgeRule(QuestionRuleParamDto paramDto) {
		boolean bool=false;
		String caseApplyId=paramDto.getCaseApplyId();
//		YWDM0051047	商品房
//		YWDM0051048	商铺
//		YWDM0051049	商住
//		YWDM0051050	别墅
//		YWDM0051051	写字楼
//		YWDM0051052	自建房
//		YWDM0051053	其他
//		YWDM0051079	厂房
//		YWDM0051080	宅基地
//		YWDM0051081	酒店式公寓
//		YWDM0051082	不能上市交易其他房产
		
//		YWDM0051011	1抵
//		YWDM0051012	2抵
//		YWDM0051013	3抵
//		YWDM0051014	其他
		log.error("问卷题库进入collateralCapitalTransferService案件id{}",caseApplyId);
		log.error("需要回答的问题:{}",paramDto.getQuestionContent());
		List<HouseProperty> houseList=housePropertyService.findByCaseApplyId(caseApplyId);
		for(HouseProperty house:houseList){
			
			if("A".equals(paramDto.getFlag())&&"YWDM0051048".equals(house.getEstateProperties())){
				log.error("问卷题库进入判断商铺:YWDM0051048");
				bool=true;
				break;
			}
			
			if("B".equals(paramDto.getFlag())&&"YWDM0051050".equals(house.getEstateProperties())){
				log.error("问卷题库进入判断别墅:YWDM0051050");
				bool=true;
				break;
			}
			
			if("C".equals(paramDto.getFlag())&&!"YWDM0051011".equals(house.getMortgageSituation())){
				log.error("问卷题库进入判断2抵:YWDM0051012");
				bool=true;
				break;
			}
			
		}
		
		//	1、是否为临街商铺或是否为小区内主干道商铺	资调-押品信息中的房产属性：商铺	mkt_house_property表estateProperties 字段
		//	2、是否带租约	资调-押品信息中的房产属性：商铺	mkt_house_property表estateProperties 字段
		//	3、租金回报率（填写栏）	资调-押品信息中的房产属性：商铺	mkt_house_property表estateProperties 字段
		
		//4、属联排/独幢	资调-押品信息中的房产属性：别墅	mkt_house_property表estateProperties 字段
		//	5、属中间户/边户	资调-押品信息中的房产属性：别墅	mkt_house_property表estateProperties 字段
		//6、有无附带花园	资调-押品信息中的房产属性：别墅	mkt_house_property表estateProperties 字段
		//	7、装修情况	资调-押品信息中的房产属性：别墅	mkt_house_property表estateProperties 字段
		//	8、一抵有无捆绑其他贷款信息	资调-押品信息中的抵押情况（第N抵）：二抵，三抵，其他	mkt_house_property表mortgageSituation 字段
		return bool;
	}
}
