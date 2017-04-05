package com.zdsoft.finance.customer.repository;

import java.util.List;

import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.finance.customer.entity.CreditAttachment;


/** 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: CreditAttachmentRepository.java 
 * @ClassName: CreditAttachmentRepository 
 * @Description: 案件客户征信附件信息Repository
 * @author liuhuan 
 * @date 2017年2月23日 上午11:37:58 
 * @version V1.0 
 */ 
public interface CreditAttachmentRepository extends CustomRepository<CreditAttachment, String> {
	
	/**
	 * 
	 * @Title: findByCreditId 
	 * @Description: 根据征信id查找征信附件
	 * @author zhongyong 
	 * @param creditId
	 * @return
	 */
	public List<CreditAttachment> findByCreditId(String creditId);

}
