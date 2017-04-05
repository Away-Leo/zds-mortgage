package com.zdsoft.finance.risk.huifa.repository;

import java.util.List;

import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.finance.risk.entity.HuifaResultInfo;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title: HuifaResultInfoRepository.java 
 * @ClassName: HuifaResultInfoRepository 
 * @Description: 汇法结果数据详情仓储接口
 * @author panshm 
 * @date 2017年2月18日 下午2:19:58 
 * @version V1.0
 */
public interface HuifaResultInfoRepository extends CustomRepository<HuifaResultInfo, String>  {
	
	/**
	 * 根据汇法网请求结果id和类型取得结果列表
	 * @Title: findByTypeAndResultId 
	 * @Description: 根据汇法网请求结果id和类型取得结果列表
	 * @author panshm 
	 * @param type 结果类型
	 * @param resultId 所属结果id
	 * @return List<HuifaResultInfo>
	 */
	public List<HuifaResultInfo> findByTypenAndResultId(String type, String resultId);

}
