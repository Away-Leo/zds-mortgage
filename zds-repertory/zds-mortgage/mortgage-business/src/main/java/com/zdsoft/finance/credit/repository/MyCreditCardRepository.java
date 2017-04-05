package com.zdsoft.finance.credit.repository;

import java.util.List;

import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.finance.credit.entity.MyCreditCard;

/** 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: MyCreditCardRepository.java 
 * @ClassName: MyCreditCardRepository 
 * @Description: 本人信用卡
 * @author dengyy 
 * @date 2017年2月22日 下午9:54:04 
 * @version V1.0 
 */
public interface MyCreditCardRepository  extends CustomRepository<MyCreditCard, String> {
	
	/**
	 * @Title: findByCreditSituationIdAndIsDeletedFalse 
	 * @Description: 根据综合征信id查找未删除的信用卡数据
	 * @author zhongyong 
	 * @param creditSituationId 综合征信id
	 * @return
	 */
	public List<MyCreditCard> findByCreditSituationIdAndIsDeletedFalse(String creditSituationId);

}
