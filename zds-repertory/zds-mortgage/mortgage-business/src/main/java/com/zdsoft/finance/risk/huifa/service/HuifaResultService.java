package com.zdsoft.finance.risk.huifa.service;

import com.zdsoft.finance.base.service.BaseService;
import com.zdsoft.finance.risk.entity.HuifaResult;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title: HuifaResultService.java 
 * @ClassName: HuifaResultService 
 * @Description: 汇法网请求结果信息Service接口
 * @author panshm 
 * @date 2017年2月24日 下午4:39:46 
 * @version V1.0
 */
public interface HuifaResultService extends BaseService<HuifaResult> {
	
	/**
	 * @Title: findByRequestId 
	 * @Description: 通过主键取得汇法请求结果数据
	 * @author panshm 
	 * @param id resultId
	 * @return HuifaResult
	 */
	public HuifaResult findByRequestId(String id) throws Exception;
	
	/**
	 * 
	 * @Title: hasHuifaMessage 
	 * @Description: 判断是否获取了汇法信息(优先使用业务Id进行判断)
	 * @author jingyh 
	 * @param type 1:个人/2:企业
	 * @param name 姓名/企业名称
	 * @param code  身份证(type=1必填)
	 * @param businessId 业务Id
	 * @return
	 */
	public boolean hasHuifaMessage(Integer type, String name, String code, String businessId);

}
