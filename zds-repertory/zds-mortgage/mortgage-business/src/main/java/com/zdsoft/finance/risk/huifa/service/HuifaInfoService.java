package com.zdsoft.finance.risk.huifa.service;

import java.util.List;

import com.zdsoft.finance.base.service.BaseService;
import com.zdsoft.finance.risk.entity.HuifaRequest;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title: HuifaInfoService.java 
 * @ClassName: HuifaInfoService 
 * @Description: 汇法网基本信息详情Service接口
 * @author panshm 
 * @date 2017年2月24日 下午4:40:06 
 * @version V1.0
 */
public interface HuifaInfoService extends BaseService<HuifaRequest> {
	
	/**
	 * 根据个人证件号取得汇法请求数据
	 * <br>在汇法网信息页展示该出借人对应的汇法请求数据时调用
	 * 
	 * @Title: findByCodeAndStype 
	 * @Description: 根据个人证件信息取得汇法请求数据
	 * @author panshm 
	 * @param codes 出借人对应的证件号
	 * @param stype 企业请求或个人请求类型
	 * @return 汇法网请求数据
	 */
	public List<HuifaRequest> findByCodeAndStype(String[] codes, Integer stype);
	
	/**
	 * 根据企业名称取得汇法请求数据
	 * <br>在汇法网信息页展示企业对应的汇法请求数据时调用
	 * 
	 * @Title: findByNameAndStype 
	 * @Description: 根据企业名称取得汇法请求数据
	 * @author panshm 
	 * @param names 企业名称
	 * @param stype 企业请求或个人请求类型
	 * @return 汇法网请求数据
	 */
	public List<HuifaRequest> findByNameAndStype(String[] names, Integer stype);
}
