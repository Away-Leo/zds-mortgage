package com.zdsoft.finance.casemanage.promotion.otherproperty.service;

import java.util.List;
import java.util.Map;

import com.zdsoft.finance.base.service.BaseService;
import com.zdsoft.finance.casemanage.promotion.otherproperty.entity.AssetsDevice;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.PageRequest;

/**
 * 版权所有：重庆正大华日软件有限公司
 * @Title:AssetsDeviceService.java
 * @Package:com.zdsoft.finance.casemanage.promotion.otherproperty.service
 * @Description:其他资产之设备信息服务
 * @author: xiongpan
 * @date:2017年2月22日 下午2:33:48
 * @version:v1.0
 */
public interface AssetsDeviceService extends BaseService<AssetsDevice>{

	/**
	 * 
	 * @Title: findPageAssetsDevice 
	 * @Description: 根据案件id获取其他资产之设备的所有情况的分页信息
	 * @author xiongpan
	 * @param pageable 分页信息
	 * @param caseApplyId 案件id
	 * @return
	 * @throws Exception
	 */
	Page<Map<String, Object>> findPageAssetsDevice(PageRequest pageable, String caseApplyId) throws Exception;
	
	/**
	 * 
	 * @Title: findByCaseApplyId 
	 * @Description: 根据案件id获取其他资产之设备的所有情况的列表信息
	 * @author xiongpan
	 * @param caseApplyId 案件id
	 * @return
	 */
	public List<AssetsDevice> findByCaseApplyId(String caseApplyId);

	/**
	 * 
	 * @Title: delete 
	 * @Description: 根据设备id物理删除此条设备数据
	 * @author xiongpan
	 * @param assetsDeviceId 需要删除的设备id
	 */
	void delete(String assetsDeviceId);

}
