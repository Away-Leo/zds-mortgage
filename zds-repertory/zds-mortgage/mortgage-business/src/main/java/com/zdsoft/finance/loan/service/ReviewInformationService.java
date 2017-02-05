package com.zdsoft.finance.loan.service;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.zdsoft.finance.base.service.BaseService;
import com.zdsoft.finance.loan.entity.ReviewInformation;

/** 	
* 版权所有：重庆正大华日软件有限公司	
* @Title: ReviewInformationService.java 	
* @Package com.zdsoft.finance.loan.service 	
* @Description: TODO	
* @author liuhuan 	
* @date 2017年1月18日 上午9:53:16 	
* @version V1.0 	
*/
public interface ReviewInformationService extends BaseService<ReviewInformation>{
	
	/**
	 * 根据三级标示查询数据
	 * @param firstMark
	 * @param secondMark
	 * @param thirdMark
	 * @return
	 */
	public List<ReviewInformation> findAll(String firstMark, String secondMark, String thirdMark) throws Exception;
	
	/**
	 * 查询所有复核信息
	 */
	public List<ReviewInformation> findAllInfo() throws Exception;
	
}
