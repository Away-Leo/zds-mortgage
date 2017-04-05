package com.zdsoft.finance.risk.huifa.repository;

import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.finance.risk.entity.HuifaRequest;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title: HuifaRequestRepository.java 
 * @ClassName: HuifaRequestRepository 
 * @Description: 汇法请求数据仓储接口
 * @author panshm 
 * @date 2017年2月18日 下午2:16:28 
 * @version V1.0
 */
public interface HuifaRequestRepository extends CustomRepository<HuifaRequest, String>  {
	
	/**
	 * 根据个人证件信息取得汇法请求数据
	 * <br>在汇法网信息页展示该出借人对应的汇法请求数据时调用
	 * 
	 * @Title: findByCodeAndStyle 
	 * @Description: 根据个人证件信息取得汇法请求数据
	 * @author panshm 
	 * @param code 出借人对应的证件号
	 * @param stype 企业请求或个人请求类型
	 * @return 汇法网请求数据
	 */
	public HuifaRequest findByCodeAndStype(String code, int stype);
	
	/**
	 * 根据个人证件信息取得汇法请求数据
	 * <br>在汇法网信息页展示企业对应的汇法请求数据时调用
	 * 
	 * @Title: findByNameAndStype 
	 * @Description: 根据企业名称取得汇法请求数据
	 * @author panshm 
	 * @param name 企业名称
	 * @param stype 企业请求或个人请求类型
	 * @return 汇法网请求数据
	 */
	public HuifaRequest findByNameAndStype(String name, int stype);

}
