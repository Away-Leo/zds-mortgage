package com.zdsoft.finance.casemanage.datasurvey.service;

import java.util.List;
import java.util.Map;

import com.zdsoft.finance.base.service.BaseService;
import com.zdsoft.finance.casemanage.datasurvey.entity.FeeInfomation;
import com.zdsoft.finance.common.base.QueryObj;
import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.PageRequest;
import com.zdsoft.framework.core.common.page.Pageable;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title:FeeInfomationService.java
 * @Package:com.zdsoft.finance.casemanage.datasurvey.service
 * @Description:费用信息
 * @author: jingyh
 * @date:2017年1月17日 下午10:00:04
 * @version:v1.0
 */
public interface FeeInfomationService extends BaseService<FeeInfomation> {
	
	/**
	 * 收费用计算方式：固定
	 */
	public static final String FEE_CALCULATE_FIXED = "collectionMethodCode1";
	
	/**
	 * 收费用计算方式：比例
	 */
	public static final String FEE_CALCULATE_RATIO = "collectionMethodCode2";
	
	/**
	 * 付费用计算方式：固定
	 */
	public static final String FEE_PAY_CALCULATE_FIXED = "paymentMethodCode1";
	
	/**
	 * 付费用计算方式：比例
	 */
	public static final String FEE_PAY_CALCULATE_RATIO = "paymentMethodCode2";
	
	/**
	 * 参与对象类型类别Id
	 */
	public static final String JOIN_TYPE_CATEGORY_ID = "YWDM0056";
	
	/**
	 * 其他参与类型代码
	 */
	public static final String JOIN_TYPE_OTHER_FULLCODE = "otherJoinType";
	
	/**
	 * 其他参与类型名称
	 */
	public static final String JOIN_TYPE_OTHER_NAME = "其他";

	/**
	 * 
	 * 查询某个案件的数据
	 *
	 * @author jingyh
	 * @param caseApplyId
	 * @return
	 * @throws BusinessException
	 */
	public List<FeeInfomation> findFeeInfomationByCaseApplyId(String caseApplyId) throws BusinessException;
	
	/**
	 * 
	 * 批量保存费用信息
	 *
	 * @author jingyh
	 * @param caseApplyId
	 * @param feeInfos
	 * @throws Exception
	 */
	public void saveOrUpdateFeeInfos(String caseApplyId, List<FeeInfomation> feeInfos) throws Exception;
	
	/**
	 * 
	 * 根据案件Id和参与类型查询收费对象集合(含配偶信息)
	 *
	 * @author jingyh
	 * @param caseApplyId
	 * @param pageInfo
	 * @return
	 */
	public Page<Map<String, Object>> findReceiveCustomer(String caseApplyId, Pageable pageInfo);

	/**
	 * 
	 * 查询评估机构信息
	 *
	 * @author jingyh
	 * @param pageReq
	 * @param queryInfo
	 * @return
	 */
	public Page<Map<String, Object>> findEvaluationInfos(List<QueryObj> queryInfo,PageRequest pageReq);
	
}