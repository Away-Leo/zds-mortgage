package com.zdsoft.finance.loan.service.impl;

import org.springframework.stereotype.Service;

import com.zdsoft.finance.base.service.impl.BaseServiceImpl;
import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.finance.loan.entity.ReviewInformationOpinion;
import com.zdsoft.finance.loan.service.ReviewInformationOpinionService;

/** 	
* 版权所有：重庆正大华日软件有限公司	
* @Title: ReviewInformationOpinionServiceImpl.java 	
* @Package com.zdsoft.finance.loan.service.impl 	
* @Description: TODO	
* @author liuhuan 	
* @date 2017年1月20日 下午5:00:03 	
* @version V1.0 	
*/
@Service("reviewInformationOpinionService")
public class ReviewInformationOpinionServiceImpl extends BaseServiceImpl<ReviewInformationOpinion, CustomRepository<ReviewInformationOpinion,String>>
implements ReviewInformationOpinionService{
	
}
