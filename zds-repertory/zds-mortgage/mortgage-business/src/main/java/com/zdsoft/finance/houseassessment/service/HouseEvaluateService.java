package com.zdsoft.finance.houseassessment.service;

import java.util.List;
import java.util.Map;

import com.zdsoft.finance.base.service.BaseService;
import com.zdsoft.finance.houseassessment.entity.HouseEvaluate;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: HouseEvaluateService.java 
 * @ClassName: HouseEvaluateService 
 * @Description: HouseEvaluateService
 * @author caixiekang 
 * @date 2017年2月27日 下午8:09:07 
 * @version V1.0
 */
public interface HouseEvaluateService extends BaseService<HouseEvaluate> {
	
	/**
	 * 
	 * @Title: findByHousePropertyId 
	 * @Description: 根据房产id找到HouseEvaluate
	 * @author caixiekang 
	 * @param housePropertyId 房产id
	 * @return
	 * @throws Exception
	 */
	public HouseEvaluate findByHousePropertyId(String housePropertyId) throws Exception;
	
	/**
	 * 
	 * @Title: queryHouseEvaluateInfos 
	 * @Description: 根据房产Id查询估价信息(若关联Id为空，则根据明细信息查询估价)
	 * @author jingyh 
	 * @param houseId
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> queryHouseEvaluateInfos(String houseId) throws Exception;

}
