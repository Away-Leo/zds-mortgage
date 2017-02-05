package com.zdsoft.finance.loan.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zdsoft.finance.base.service.impl.BaseServiceImpl;
import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.finance.loan.entity.ReviewInformation;
import com.zdsoft.finance.loan.repository.ReviewInformationRepository;
import com.zdsoft.finance.loan.service.ReviewInformationService;

/** 	
* 版权所有：重庆正大华日软件有限公司	
* @Title: ReviewInformationServiceImpl.java 	
* @Package com.zdsoft.finance.loan.service.impl 	
* @Description: TODO	
* @author liuhuan 	
* @date 2017年1月18日 上午9:54:36 	
* @version V1.0 	
*/
@Service("reviewInformationServie")
public class ReviewInformationServiceImpl extends BaseServiceImpl<ReviewInformation, CustomRepository<ReviewInformation,String>>
implements ReviewInformationService{

	@Autowired
	private ReviewInformationRepository reviewInformationRepository;

	@Override
	public List<ReviewInformation> findAll(String firstMark, String secondMark, String thirdMark) {
		return reviewInformationRepository.findAllByFirstMarkAndSecondMarkAndThirdMark(firstMark, secondMark, thirdMark);
	}

	@Override
	public List<ReviewInformation> findAllInfo() {
		return reviewInformationRepository.findAllInfo();
	}

	
}
