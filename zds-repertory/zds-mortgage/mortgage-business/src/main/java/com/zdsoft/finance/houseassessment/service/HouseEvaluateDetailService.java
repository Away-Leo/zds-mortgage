package com.zdsoft.finance.houseassessment.service;

import java.util.List;
import java.util.Map;

import com.zdsoft.finance.base.service.BaseService;
import com.zdsoft.finance.houseassessment.entity.HouseEvaluateDetail;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: HouseEvaluateDetailService.java 
 * @ClassName: HouseEvaluateDetailService 
 * @Description: 房产评估详情服务类
 * @author yangjia 
 * @date 2017年2月20日 上午10:26:01 
 * @version V1.0
 */
public interface HouseEvaluateDetailService extends BaseService<HouseEvaluateDetail>{
	
	/**
	 * 
	 * @Title: queryHouseEvaluateInfo 
	 * @Description: 根据传入条件查询房产估价信息
	 * @author jingyh 
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public List<Map<String,Object>> queryHouseEvaluateInfo(Map<String, Object> param) throws Exception;
	
	/**
	 * 
	 * @Title: getPriceForCompany 
	 * @Description: 查询评估信息
	 * @author yangjia 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public List<Map<String,Object>> getPriceForCompany(Map<String,Object> map) throws Exception;
	
	/**
	 * 
	 * @Title: getPriceByBizId 
	 * @Description: 根据押品ID查询评估信息
	 * @author yangjia 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public List<Map<String,Object>> getPriceByBizId(String bizid) throws Exception;
	
	/**
	 * 
	 * @Title: getHouseEvaluateDetailByHousePropertyId 
	 * @Description: 根据houseEvaluateId查找houseEvaluateId(评估细节)
	 * @author caixiekang 
	 * @param housePropertyId 评估表id
	 * @return
	 * @throws Exception
	 */
	public List<HouseEvaluateDetail> getHouseEvaluateDetailByHouseEvaluateId(String houseEvaluateId) throws Exception;
	

}
