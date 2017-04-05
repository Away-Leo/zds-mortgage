package com.zdsoft.finance.risk.huifa.service;

import java.util.List;

import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.finance.risk.entity.DaasBasic;
import com.zdsoft.finance.risk.huifa.service.bo.BusinessDetailBo;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title: BusinessDetailService.java 
 * @ClassName: BusinessDetailService 
 * @Description: 工商信息详情Service接口
 * @author panshm 
 * @date 2017年2月24日 下午4:39:21 
 * @version V1.0
 */
public interface BusinessDetailService {
	
	/**
	 * @Title: findByOrderId 
	 * @Description: 根据订单号取得工商信息详情
	 * @author panshm 
	 * @param orderId 订单编号
	 * @return 工商信息详情数据
	 */
	public BusinessDetailBo findByOrderId(String orderId);
	
	/**
	 * @Title: findByCompanyName 
	 * @Description: 根据企业名查询工商基础信息
	 * @author panshm 
	 * @param companyNameList 企业名称数组
	 * @return 工商基础信息
	 */
	public List<DaasBasic> findByCompanyNames(String[] companyNameList);
	
	/**
	 * @Title: callBusinessInterface 
	 * @Description: 调用工商接口取得工商数据
	 * @author panshm 
	 * @param companyName 企业名称
	 * @param businessId 我方业务id
	 * @param orgName 机构名称(非必填)
	 * @param operator 操作员(非必填)
	 * @return 结果状态：true成功、false失败
	 * @throws BusinessException
	 */
	public Boolean callBusinessInterface(String companyName, String businessId, String orgName, String operator)
			throws BusinessException;
	
	/**
	 * @Title: findAllBusinessInfo 
	 * @Description: 根据企业名称取得工商接口数据
	 * <br>供其他后端逻辑需要取得工商数据时调用
	 * <br>查询不到结果或结果无法唯一时，返回null
	 * @author panshm 
	 * @param companyName 公司名称
	 * @return 整合后的工商数据BusinessObject
	 */
	public BusinessDetailBo findAllBusinessInfo(String companyName);

	/**
	 * 
	 * @Title: hasDaasInfos 
	 * @Description: 根据企业名字判断是否已获取工商信息
	 * @author jingyh 
	 * @param companyName
	 * @return
	 */
	public boolean hasDaasInfos(String companyName);
}
