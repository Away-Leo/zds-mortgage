package com.zdsoft.finance.cooperator.service;

import com.zdsoft.finance.base.service.BaseService;
import com.zdsoft.finance.cooperator.entity.OtherCooperater;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * 
 * @Title: OtherCooperaterService.java
 * @ClassName: OtherCooperaterService
 * @Description: 其他合作单位Serivce
 * @author liuwei
 * @date 2017年3月2日 下午8:04:01
 * @version V1.0
 */
public interface OtherCooperaterService extends BaseService<OtherCooperater> {

	/**
	 * 
	 * @Title: saveOrUpdateOtherCooperater
	 * @Description: 新增修改其他合作单位
	 * @author liuwei
	 * @param otherCooperater
	 *            其他合作单位信息
	 * @return 其他合作单位信息
	 * @throws Exception
	 */
	public OtherCooperater saveOrUpdateOtherCooperater(OtherCooperater otherCooperater) throws Exception;

}
