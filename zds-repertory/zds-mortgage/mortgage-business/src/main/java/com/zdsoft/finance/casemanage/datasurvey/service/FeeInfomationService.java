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
	public static final String FEE_CALCULATE_FIXED = "chargeCalculateWay1";
	
	/**
	 * 收费用计算方式：比例
	 */
	public static final String FEE_CALCULATE_RATIO = "chargeCalculateWay2";
	
	/**
	 * 付费用计算方式：固定
	 */
	public static final String FEE_PAY_CALCULATE_FIXED = "payCalculateWay1";
	
	/**
	 * 付费用计算方式：比例
	 */
	public static final String FEE_PAY_CALCULATE_RATIO = "payCalculateWay2";
	
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
	 * 查询某个案件的数据(若无则带出产品信息)
	 *
	 * @author jingyh
	 * @param caseApplyId
	 * @return
	 * @throws BusinessException
	 */
	public List<FeeInfomation> findFeeInfomationByCaseApplyId(String caseApplyId) throws Exception;
	
	/**
	 * 
	 * @Title: findFeeByCaseApplyId 
	 * @Description: 查询某个案件的数据
	 * @author xiangjx 
	 * @param caseApplyId
	 * @return
	 * @throws BusinessException
	 */
	public List<FeeInfomation> findFeeByCaseApplyId(String caseApplyId) throws BusinessException;
	
	/**
	 * 
	 * @Title: findByCaseApplyId 
	 * @Description: 查询案件收费集合信息
	 * @author jingyh 
	 * @param caseApplyId
	 * @return
	 * @throws BusinessException
	 */
	public List<FeeInfomation> findByCaseApplyId(String caseApplyId) throws Exception;
	/**
	 * 
	 * @Title: findByCaseApplyIdAnd 
	 * @Description: 收款列表数据列表
	 * @author xiangjx 
	 * @param caseApplyId
	 * @param payerType
	 * @return
	 * @throws BusinessException
	 */
	public List<FeeInfomation> findByCaseApplyIdAndPayerType(String caseApplyId,String payerType) throws BusinessException;
	
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
	
	/**
	 * 
	 * @Title: findReviceObjTypeInfosByCaseApplyId 
	 * @Description: 根据案件Id查询对应的收费对象类别下拉数据
	 * @author jingyh 
	 * @param caseApplyId
	 * @return
	 */
	public List<Map<String,Object>> findReviceObjTypeInfosByCaseApplyId(String caseApplyId) throws BusinessException;
	
	/**
	 * 
	 * @Title: findByCaseApplyIdAndReceiveObjTypes 
	 * @Description: 根据案件Id和收费对象类别信息查询收费明细记录
	 * @author jingyh 
	 * @param caseApplyId
	 * @param receiveObjTypes
	 * @return
	 */
	public List<FeeInfomation> findByCaseApplyIdAndReceiveObjTypes(String caseApplyId,List<String> receiveObjTypes) throws BusinessException;
	

    /**
     * @Title: findByCaseApplyIdAndFeeItemAndFeeType 
     * @Description: 根据费用类型、费用项获取费用
     * @author jincheng 
     * @param caseApplyId
     * @param feeItem
     * @param feeType
     * @return
     */
	public List<FeeInfomation> findByCaseApplyIdAndFeeItemAndFeeType(String caseApplyId,String feeItem, String feeType);

	/**
	 * @Title: getCaseApplyAndFeeList 
	 * @Description: 获取案件费用合计
	 * @author jincheng 
	 * @param page
	 * @param li
	 * @return
	 */
	public Page<Map<String, Object>> getCaseApplyAndFeeList(PageRequest page, List<QueryObj> li)throws Exception;

   
}