package com.zdsoft.finance.risk.huifa.service;

import java.util.List;

import com.zdsoft.finance.base.service.BaseService;
import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.finance.risk.entity.HuifaResultInfo;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title: HuifaDetailService.java 
 * @ClassName: HuifaDetailService 
 * @Description: 汇法网信息详情Service接口
 * @author panshm 
 * @date 2017年2月24日 下午4:39:46 
 * @version V1.0
 */
public interface HuifaDetailService extends BaseService<HuifaResultInfo> {

	/**
	 * 根据汇法网请求结果id和类型取得结果列表
	 * <br>取得汇法网详情子栏目时调用，用于拼接详情内单一表格
	 * 
	 * @param type 结果类型
	 * @param resultId 所属结果id
	 * @return List<HuifaResultInfo>
	 */
	public List<HuifaResultInfo> findSingleHuifaDetail(String type, String resultId);
	
	/**
	 * @Title: callHuifaInterface 
	 * @Description: 调用汇法网接口取得汇法网数据
	 * @author panshm 
	 * @param type 1:个人/2:企业
	 * @param name 姓名/企业名称
	 * @param code 身份证(type=1必填)
	 * @param businessId 我方业务id
	 * @return 结果状态：true成功、false失败
	 * @throws BusinessException
	 */
	public Boolean callHuifaInterface(String type, String name, String code, String businessId)
			throws BusinessException;
	
	/**
	 * @Title: findSingleHuifaDetail 
	 * @Description: 根据数据类型、查询者身份证号/组织机构代码、查询者类型取得结果列表
	 * <br>供其他后端逻辑需要取得指定汇法网数据时调用
	 * @author panshm 
	 * @param stype 被查对象类型，通过HuifaRequest类中的常量来赋值
	 * @param code 身份证号/组织机构代码
	 * @param infoType 数据类型，通过HuifaResultInfo类中的常量来赋值
	 * @return List<HuifaResultInfo>
	 */
	public List<HuifaResultInfo> findSingleHuifaDetail(int stype, String code, String infoType);

}
